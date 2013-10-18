(defproject quicksbi "0.1.0-SNAPSHOT"
  :description "Quicksbi is not Qu*cken nor Gr*sb*"
  :url "https://github.com/giacinti/quicksbi"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1934"]
                 [compojure "1.1.5"]
                 [domina "1.0.2"]
                 [hiccups "0.2.0"]
                 [shoreleave/shoreleave-remote-ring "0.3.0"]
                 [shoreleave/shoreleave-remote "0.3.0"]
                 [com.cemerick/valip "0.3.2"]
                 [enlive "1.1.4"]]

  :plugins [[lein-ring "0.8.7"]
            [lein-cljsbuild "0.3.4"]]

  :hooks [leiningen.cljsbuild]

  :ring {:handler quicksbi.core/app}

  :cljsbuild {:crossovers [valip.core 
                           valip.predicates]
              
              :builds {:prod
                       {:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/quicksbi.js"
                                   :optimizations :advanced
                                   :pretty-print false}}}}
  
  :profiles {:dev 
             {:dependencies [[com.cemerick/piggieback "0.1.0"]]

              :plugins [[com.cemerick/clojurescript.test "0.1.0"]
                        [com.keminglabs/cljx "0.3.0"]]

              :test-paths ["target/test/clj"]
              :clean-targets ["out"]

              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
              :injections [(require '[cljs.repl.browser :as brepl]
                                    '[cemerick.piggieback :as pb])
                           (defn browser-repl []
                             (pb/cljs-repl :repl-env (brepl/repl-env :port 9000)))]

              
              :cljx {:builds [{:source-paths ["test/cljx"]
                               :output-path "target/test/clj"
                               :rules :clj}
                              {:source-paths ["test/cljx"]
                               :output-path "target/test/cljs"
                               :rules :cljs}]}
              
              :cljsbuild {:test-commands {"phantomjs-whitespace"
                                          ["phantomjs" :runner "target/test/js/testable_dbg.js"]
                                          
                                          "phantomjs-simple"
                                          ["phantomjs" :runner "target/test/js/testable_pre.js"]
                                          
                                          "phantomjs-advanced"
                                          ["phantomjs" :runner "target/test/js/testable.js"]}
                          
                          :builds {
                                   :dev
                                   {:source-paths ["src/cljs"]
                                    :compiler {:output-to "resources/public/js/quicksbi_dev.js"
                                               :optimizations :whitespace
                                               :pretty-print true}}

                                   :ws-unit-tests
                                   {:source-paths ["src/cljs" "target/test/cljs"]
                                    :compiler {:output-to "target/test/js/testable_dbg.js"
                                               :optimizations :whitespace
                                               :pretty-print true}}
                                   
                                   :simple-unit-tests
                                   {:source-paths ["src/cljs" "target/test/cljs"]
                                    :compiler {:output-to "target/test/js/testable_pre.js"
                                               :optimizations :simple
                                               :pretty-print false}}
                                   
                                   :advanced-unit-tests
                                   {:source-paths ["src/cljs" "target/test/cljs"]
                                    :compiler {:output-to "target/test/js/testable.js"
                                               :optimizations :advanced
                                               :pretty-print false}}
                                   }}}})
