(ns clj-http.http.middlewares
  (:require [clojure.tools.logging :as logging]
            [clojure.data.json :as json]))

(defn log [handler]
  (fn [request]
    (let [method (:request-method request)
          path (:uri request)
          res (handler request)
          status (:status res)]
      (logging/infof "%s %s - %s" method path status)
      res)))

(defn json
  [handler]
  (fn [request]
    (let [res (handler request)
          status (:status res)
          body (:body res)
          headers (:headers res)]
      {:status status
       :headers (merge {"Content-Type", "application/json"} headers)
       :body (cond (or (= status 204) (= body nil)) nil
                   :else (json/write-str body))})))
