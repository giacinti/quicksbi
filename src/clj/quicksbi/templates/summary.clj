(ns quicksbi.templates.summary
  (:require [net.cgrand.enlive-html :refer [deftemplate defsnippet]]))

;; (defsnippet account-summary "public/summary.html"
;;   [[:#accsum]]
;;   [{name :name actual :actual saldo :saldo}]
