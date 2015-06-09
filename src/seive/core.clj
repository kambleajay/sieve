(ns seive.core
  (:require [clojure.core.async :as async :refer :all
             :exclude [map into reduce merge partition partition-by take]]))

(defn factor?
  [x y]
  (zero? (mod x y)))

(defn get-primes 
  [limit]
  (let [primes (chan)
        numbers (to-chan (range  2 limit))]
    (go
      (loop [copy-of-numbers numbers]
        (let [x (<! copy-of-numbers)]
          (if x
            (do
              (>! primes x)
              (recur (remove< #(factor? % x) copy-of-numbers)))
            (close! primes)))))
    primes))
