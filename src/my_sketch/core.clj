(ns my-sketch.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))


;Play with these to get different patterns
(def param-A 23)
(def param-B 11)
(def scale 7)

(defn hypocycloid [a b scale]
  (fn [t]
    [(* scale (+ (* (- a b) (q/cos t)) (* b (q/cos (* t (/ (- a b) b )))))) 
     (* scale (- (* (- a b) (q/sin t)) (* b (q/sin (* t (/ (- a b) b ))))))]))

(defn draw []
  (q/with-translation [(/ (q/width) 2) (/ (q/height) 2 )]
    (let [t (/ (q/frame-count) 20)
          f (hypocycloid param-A param-B scale)]
      (q/line (f t) (f (+ t 0.1))))))

(defn setup []
  (q/background 255)
  (q/frame-rate 60))

(q/defsketch spiral
  :size [512 512]
  :draw draw
  :setup setup)

