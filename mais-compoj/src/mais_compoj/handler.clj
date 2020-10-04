(ns mais-compoj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [mais-compoj.translate :as translate]
            [mais-compoj.lang :as lang]
            [mais-compoj.page :as page]
            [clojure.string :as str]
            ))

;; todo make a nice button thing
;; - use hiccup?


;; todo language dropdown

(defroutes app-routes
  (GET "/" []
       (page/test-index (lang/list-languages)))
  
  (GET "/:lang" [lang]
       (str "We offer " lang))

  (POST "/" request
        (let [text (get-in request [:params :text])
              arg-list (str/split text #"\s")
              in-lang (first arg-list)
              out-lang (second arg-list)
              sentence (->> arg-list rest rest (str/join " "))]
          (if (and in-lang out-lang sentence)
            (page/compute              in-lang out-lang sentence) ;text
            (page/compute "en" "fr" "some text"))))


  (GET "/translate/:inlang/:outlang/:sentence" [inlang outlang sentence]
       (str
        "Translating " sentence " from " inlang " to " outlang ":\n"
        (translate/translate-text inlang outlang sentence)))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
