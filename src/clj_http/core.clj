(ns clj-http.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.http.handlers :as handlers]
            [clj-http.http.server :as server]))

(def routes [[:get "/health" handlers/health]])

(defn -main
  [& _]
  (jetty/run-jetty (server/root-handler routes) {:port 9090}))
