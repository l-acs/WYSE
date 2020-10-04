(ns mais-compoj.translate
  (:gen-class)
  (:use
   [clojure.java.shell :only [sh]]))



(defn python-execute [filename & args]
  (:out
   (apply
    sh
     "python" filename args)))


(defn translate-text [in-lang out-lang s]
  (str
   "Congrats, this is your translation: "
   (python-execute "junk.py"
                   in-lang out-lang s)))
  

;(translate-text "en" "fr" "hey there this is a sentence")
