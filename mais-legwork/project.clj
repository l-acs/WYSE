(defproject mais-legwork "0.1.0-SNAPSHOT"
  :description "Work for MAIS 2020 Hackathon, October 2020."
  :url "maishacks.com"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-http "3.10.3"]
                 [cheshire "5.10.0"]
                 [org.clojure/data.csv "1.0.0"]]
  :main ^:skip-aot mais-legwork.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
