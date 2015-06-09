(ns seive.core-test
  (:require [clojure.test :refer :all]
            [seive.core :refer :all]
            [clojure.core.async :as async :refer :all
             :exclude [map into reduce merge partition partition-by take]]))

(deftest sieve-test
  (testing "generation of primes"
    (is (= (<!! (async/into [] (get-primes 10))) [2 3 5 7]))))
