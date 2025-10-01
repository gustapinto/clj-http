(ns clj-http.http.handlers)

(defn health
  [_]
  {:status 200
    :body {:health {:http {:status "alive"}}}})

(defn not-found
  [request]
  {:status 404
   :body {:error "route-not-found" :retriable false :details {:message "route not found" :route (:uri request)}}})