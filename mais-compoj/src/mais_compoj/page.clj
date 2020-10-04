(ns mais-compoj.page
  (:gen-class)
  (:require
   [hiccup.element :refer (link-to)]
   [hiccup.form :as f]
   [hiccup.page :refer [html5 include-css]]
   [ring.util.response :refer [redirect]]
   [ring.util.anti-forgery :as util]))

(defn test-index [lang-list]
  (html5
    [:head
     [:title "MAIS 2020"]
     (include-css "/css/mycss.css")]
    [:body
     [:h1 "MAIS 2020: Machine Translation"]
     [:h2 "Emi Baylor, Luc Sahar, Elodie Ithier" ]
     [:p "We have the following languages:" ]
     [:ul
      (map
       #(vector :li (str % "<br/>"))
       lang-list)
      ]

     (f/form-to [:post "/"]
                (util/anti-forgery-field)
                (f/text-field "text")
                (f/submit-button "Translate"))]))


(defn compute [inlang outlang s]
  (redirect (str "/translate/" inlang "/" outlang "/" s)))

