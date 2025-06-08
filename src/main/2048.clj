(ns main.2048)

(def empty-board
  [[0 0 0 0]
   [0 0 0 0]
   [0 0 0 0]
   [0 0 0 0]])

(defn compress [row]
  (let [non-zero (filterv pos? row)
        zeros (repeat (- 4 (count non-zero)) 0)]
    (vec (concat non-zero zeros))))

(defn merge-row [row]
  (loop [[a b & rest] row
         acc []]
    (cond
      (nil? a) acc
      (nil? b) (conj acc a)
      (= a b)
      (recur rest (conj acc (* 2 a) 0)) ; merge + placeholder 0
      :else
      (recur (cons b rest) (conj acc a)))))

(defn move-left [board]
  (mapv #(compress (merge-row (compress %))) board))

(defn transpose [m]
  (apply mapv vector m))

(defn rotate-left [m]
  (->> m transpose reverse vec))

(defn rotate-right [m]
  (->> m reverse transpose vec))


(defn move-up [board]
  (-> board transpose move-left transpose))

(defn move-right [board]
  (mapv #(reverse (compress (merge-row (compress (reverse %))))) board))

(defn move-down [board]
  (-> board transpose move-right transpose))

(defn empty-positions [board]
  (for [r (range 4) c (range 4)
        :when (= 0 (get-in board [r c]))]
    [r c]))

(defn add-tile [board]
  (let [positions (empty-positions board)
        [r c] (rand-nth positions)
        value (if (< (rand) 0.9) 2 4)]
    (assoc-in board [r c] value)))

(defn new-game []
  (-> empty-board add-tile add-tile))

(defn print-board [board]
  (doseq [row board]
    (println (map #(if (zero? %) "." %) row))))

(defn play []
  (loop [board (new-game)]
    (print-board board)
    (println "Move (w/a/s/d):")
    (let [input (read-line)
          move (case input
                 "w" move-up
                 "a" move-left
                 "s" move-down
                 "d" move-right
                 identity)
          new-board (move board)]
      (if (= board new-board)
        (recur board)
        (recur (add-tile new-board))))))


play



