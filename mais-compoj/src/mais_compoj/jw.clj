(ns mais-compoj.jw
  (:gen-class)
  (:require
   [clj-http.client :as client]
   [cheshire.core :as json]))
;   [clojure.java.io :as io]
;   [clojure.data.csv :as csv]
;   [clojure.string :as str]))

(defn curl-json [url]
  (:body (client/get url {:accept :json})))

(defn curl-edn [url]
  (-> url curl-json (json/parse-string true)))
  
;; (defn read-tsv [f]
;;   (csv/read-csv f :separator \tab))

;; (defn csv-data->maps [csv-data]
;;   ;; see: https://github.com/clojure/data.csv/
;;   (map zipmap
;;        (->> (first csv-data) ;; First row is the header
;;             (map keyword) ;; Drop if you want string keys instead
;;             repeat)
;;        (rest csv-data)))

;; (defn tsv->edn [tsv-fname]
;;   (csv-data->maps (read-tsv (io/reader tsv-fname))))
