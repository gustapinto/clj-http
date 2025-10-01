(ns clj-http.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.http.handlers :as handlers]
            [clj-http.http.middlewares :as middlewares]))

(defn- server
  [request]
  (let [method (:request-method request)
        path (:uri request)
        handler (case [method path]
                  [:get "/health"] handlers/health
                  handlers/not-found)]
    ((middlewares/log (middlewares/json handler)) request)))

(defn -main
  [& _]
  (jetty/run-jetty server {:port 9090}))
