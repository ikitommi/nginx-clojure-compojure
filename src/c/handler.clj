(ns c.handler
  (:require [compojure.core :as cc]
            [nginx.clojure.embed :as embed]
            [ring.adapter.jetty :as jetty]))

(defn mw [x]
  (fn [handler]
    (fn [request]
      (handler (update-in request [:params :x] str x "->")))))

(def app
  (cc/routes
    (cc/wrap-routes
      (cc/GET "/a" [x]
        {:status 200, :body x})
      (mw "a"))
    (cc/wrap-routes
      (cc/GET "/b" [x]
        {:status 200, :body x})
      (mw "b"))))

(defn start-nginx-clojure [port]
  (embed/run-server #'app {:port port}))

(defn start-jetty [port]
  (jetty/run-jetty #'app {:port port, :join? false}))

(defn start-servers []
  (start-nginx-clojure 3000)
  (start-jetty 3001))
