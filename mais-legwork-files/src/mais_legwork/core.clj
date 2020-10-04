(ns mais-legwork.core
  (:gen-class)
  (:require
   [clj-http.client :as client]
   [cheshire.core :as json]
   [clojure.java.io :as io]
   [clojure.data.csv :as csv]
   [clojure.string :as str])
  (:use
   [clojure.java.shell :only [sh]]))

(defn curl-json [url]
  (:body (client/get url {:accept :json})))

(defn curl-edn [url]
  (-> url curl-json (json/parse-string true)))
  
(defn read-tsv [f]
  (csv/read-csv f :separator \tab))

(defn csv-data->maps [csv-data]
  ;; see: https://github.com/clojure/data.csv/
  (map zipmap
       (->> (first csv-data) ;; First row is the header
            (map keyword) ;; Drop if you want string keys instead
            repeat)
       (rest csv-data)))

(defn tsv->edn [tsv-fname]
  (csv-data->maps (read-tsv (io/reader tsv-fname))))

(defn replace-empty-strings-with-nil [m]
  (map
   #(into {} (map
              (fn [[k v]]
                (if (= v "")
                  [k nil]
                  [k v])) %))
   m))


(defn map-overlap [m1 k1 m2 k2]
 (clojure.set/intersection
   (into #{} (map k1 m1))
   (into #{} (map k2 m2))))

(defn map-lookup
  "Get the first hashmap in a list whose key k has the value v"
  [map-list k v]
  (->> map-list (filter #(= (k %) v)) first))

(defn lookup-all [map-list k value-list]
  (map
   #(map-lookup map-list k %)
   value-list))

(defn download-parallel-corpora [dest id1 id2]
  ; opus_read -d JW300 -s af -t bg -wm moses -w jw300.af jw300.bg -dl ../corpus --suppress_prompts
  (let [opus_read "/home/l-acs/.local/bin/opus_read"]
    (str opus_read
              " -d JW300"
              " -s " id1
              " -t " id2
              " -wm moses"
              " -w jw300." id1 " jw300." id2
              " -dl " dest
              " --suppress_prompts")))


(download-parallel-corpora "../corpus" "af" "bg")




(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(comment
  (def url "https://object.pouta.csc.fi/OPUS-JW300/v1/languages.json")
  (def jw-edn (curl-edn url))
  (def features-edn (-> "whole_df.tsv" tsv->edn replace-empty-strings-with-nil))
;; (map-overlap features-edn :wals_code jw-edn :language_short)
  (def overlap-names (map-overlap features-edn :name jw-edn :language_en))
  (def overlap-jw-data (lookup-all jw-edn :language_en overlap-names))
  (def opus_read "/home/l-acs/.local/bin/opus_read")
  
  (:out (sh opus_read "-h"))
  
  
  )

