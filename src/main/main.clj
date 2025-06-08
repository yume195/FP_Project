(ns main.main)
"" "
 minesweeper test 
" ""
"define rows and columns"
(def rows 8)
(def cols 8)
(def mine_count 5)

"field object"


"makes an empty board"
(def make-board (vec (repeat rows (vec (repeat cols 0)))))

make-board

(defn pick-mines [rows cols mine_count]
(take mine_count (shuffle (for  [r (range rows) c (range cols)] [r c]))))
