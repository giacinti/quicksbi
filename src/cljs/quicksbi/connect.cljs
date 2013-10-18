(ns quicksbi.connect
  (:require [clojure.browser.repl :refer [connect]]))

(connect (str "http://" js/location.hostname ":9000/repl"))

