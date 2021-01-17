(ns my-sketch.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn hypocycloid [a b scale]
  (fn [t]
    [(* scale (+ (* (- a b) (q/cos t)) (* b (q/cos (* t (/ (- a b) b )))))) 
     (* scale (- (* (- a b) (q/sin t)) (* b (q/sin (* t (/ (- a b) b ))))))]))

(defn draw []
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2 )]
    (let [t (/ (q/frame-count) 10)
          f (hypocycloid 21 17 20)]
      (q/line (f t) (f (+ t 0.01))))))

(defn setup []
  (q/background 255)
  (q/frame-rate 60))

(q/defsketch spiral
  :size [1024 1024]
  :draw draw
  :setup setup)
