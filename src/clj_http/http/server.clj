(ns clj-http.http.server
  (:require [clj-http.http.handlers :as handlers]
            [clj-http.http.middlewares :as middlewares]))

(defn root-handler
  [routes]
  (fn [request]
    (let [method (:request-method request)
          path (:uri request)
          handler (some (fn [[m p h]] (when (and (= m method) (= p path)) h)) routes)]
      ((middlewares/log (middlewares/json (or handler handlers/not-found)))
       request))))