#lang racket

;  Implementa el juego Guess
; Utilizando el algoritmo de búsqueda binaria que  divide a la mitad las posibilidades
; en cada iteración, implemente el juego adivine el número pensado por el usuario:
; Juego:
; - Establezca los límites superior e inferior del número del jugador. 
; - Solicite al jugador que piense un número en ese rango.
; - Adivine un número (la mitad entre esos dos números).
; - Si el jugador dice que el número es más pequeño, baje el límite superior. 
; - Si el jugador dice que el número es más grande, suba el límite inferior.
; 
; Ejemplo de cómo se juega:
; (start 1 100)
;   El sistema responde con el valor de la mitad del intervalo.
;   Si el usuario seleccionó un número menor indica (smaller)
;   Si seleccionó uno mayor indica (bigger)


;=== Variables 

(define lower 0)

(define upper 0)

(define result 0)


#| Procedimiento principal para iniciar el juego.  Se utiliza de la siguiente forma:
    (start 1 100)
; Input: intervalo en el que el usuario selecciona el número.
|#
(define (start n m)
  (cond [ (and (> n 0) (> m 0) (> m n)) 
     (set! lower n)
     (set! upper m)
     (set! result (guess))
     result]
  [else "Debe definir un rango válido"]))

#|Realiza el cálculo del valor central del intervalo.
  Entradas: ninguna
  Devuelve el valor central del intervalo
|#
(define (guess)
  (quotient (+ lower upper) 2)
  )

;  actualiza el límite superior modificando la variable global 
(define (smaller)
  (set! upper (max lower (sub1 result)))
  (set! result (guess))
  result)

;  Cambia el límite inferior modificando la variable global
(define (bigger)
  (set! lower (min upper (add1 result)))
  (set! result (guess))
  result)




