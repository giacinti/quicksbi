(ns quicksbi.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [shoreleave.middleware.rpc :refer [wrap-rpc]]))

(defroutes app-routes
  (GET "/" [] "<p>Hello from quicksbi</p>")
  (resources "/")
  (not-found "Page not found"))

(def handler
  (site app-routes))

(def app (-> (var handler)
             (wrap-rpc)
             (site)))

