#+clj (ns quicksbi.test-core
        (:require [clojure.test :refer [deftest are testing]]))

#+cljs (ns quicksbi.test-core
         (:require-macros [cemerick.cljs.test :refer [deftest are testing]])
         (:require [cemerick.cljs.test :as t]))

;; empty for now - must exists for compilation
