(ns mais-compoj.lang
  (:require
   [mais-compoj.jw :as jw]))


(def jw-edn (jw/curl-edn "https://object.pouta.csc.fi/OPUS-JW300/v1/languages.json"))

(def langs
  '(
   "ab", "arn", "bla", "crs", "en", "es", "ewo", "fo", "fr", "ht", "luo", "ko", "mfe", "no", "ts", "xh", "yo", "yup", "zlm", "zu"
   ))

(defn map-lookup
  "Get the first hashmap in a list whose key k has the value v"
  [map-list k v]
  (->> map-list (filter #(= (k %) v)) first))

(defn lang-name-lookup [code]
  (:language_en (map-lookup jw-edn :language_short code)))

(defn list-languages []
  (sort 
   (map lang-name-lookup langs)))


;;   (def features-edn (-> "whole_df.tsv" tsv->edn replace-empty-strings-with-nil))
;; ;; (map-overlap features-edn :wals_code jw-edn :language_short)
;;   (def overlap-names (map-overlap features-edn :name jw-edn :language_en))
;;   (def overlap-jw-data (lookup-all jw-edn :language_en overlap-names))
;;   (def opus_read "/home/l-acs/.local/bin/opus_read")
  
;;   (:out (sh opus_read "-h"))
  
  
;  )
