(defproject c2 "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.6.0"]]
  :profiles {:dev {:dependencies [[nginx-clojure/nginx-clojure-embed "0.4.5"]
                                  [ring/ring-jetty-adapter "1.6.2"]]}})
