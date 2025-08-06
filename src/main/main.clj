(ns main.main)
;; immutability + pure functions
;; the apply-discount function applies a 10% discount to each item in the cart
;; this demonstrates how to use the function without modifying the original cart
;; the cart remains unchanged after applying the discount


(defn apply-discount [items]
  (map (fn [item]
         (update item :price #(* % 0.9)))  ; 10% discount
       items))

(def cart [{:name "Apfel" :price 1.0}
           {:name "Banane" :price 2.0}])

(apply-discount cart)

cart                   ;; Original remains unchanged




;; Higher order functions example
;; This example demonstrates how to manipulate a list of words using higher order functions



(def words ["Clojure" "is" "a" "functional" "language" "for" "the" "JVM"])

;; 1. Make everything lowercase
(def lowercase-words
  (map clojure.string/lower-case words))

;; 2. only keep words longer than 4 characters
(def long-words
  (filter #(> (count %) 4) lowercase-words))

;; 3. Calculate the total number of letters in the long words
(def total-letters
  (reduce + (map count long-words)))

;; Ausgabe:
(println "Original:" words)
(println "Kleinbuchstaben:" lowercase-words)
(println "Lange Wörter:" long-words)
(println "Gesamtanzahl Buchstaben:" total-letters)



;; First class functions example
;; This example demonstrates how to use functions as first-class citizens

(defn transform [f coll]     ;; takes a function and a collection
  (map f coll))

(transform inc [1 2 3]) ;; => (2 3 4)
(transform #(* % 2) [1 2 3]) ;; => (2 4 6)

;; Lazy Sequencing example
;; This example demonstrates the creation of an infinite lazy sequence of prime numbers.

(def primes
  (remove
   (fn [x]
     (some (fn [y] (zero? (mod x y))) primes)) ;; filter function
   (iterate inc 2))) 

(take 10 primes) ;; primes only calculated in this statement, thus lazy

;; Recursion example
;; This example demonstrates a simple recursive function using 'loop' and 'recur'.

(defn sum_up_to [n] 
  (loop [current n accumulator 0] (if (zero? current) accumulator (recur (dec current) (+ accumulator current))))) 

(sum_up_to 5)