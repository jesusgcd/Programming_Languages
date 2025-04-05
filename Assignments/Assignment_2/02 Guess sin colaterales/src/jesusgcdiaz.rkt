#lang racket

; Implementa el juego Guess
; Utilizando el algoritmo de búsqueda binaria que divide a la mitad las posibilidades
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

; Procedimiento principal para iniciar el juego.
(define (start n m)
  (cond [(and (> n 0) (> m 0) (> m n))
         (define lower n)
         (define upper m)
         (define result (guess lower upper))
         result]
        [else "Debe definir un rango válido"]))

; Realiza el cálculo del valor central del intervalo.
; Entradas: límites inferior y superior del intervalo
; Devuelve el valor central del intervalo
;(define (guess lower upper)
;  (quotient (+ lower upper) 2))

#|Realiza el cálculo del valor central del intervalo.
  Entradas: limite inferior, limite superior 
  Devuelve el valor central del intervalo
|#
(define (guess lower upper)
  (quotient (+ lower upper) 2)
  )

#|Realiza la prediccion del numero seleccionado por el usuario
  Entradas: limite inferior, limite superior 
  No devuelve nada, usa la funcion (guess) para el calculo de la prediccion

  Nota: se puede remplazar "start" por "guess", se usa start para aprovechar
  la validacion de los extremos
|#
(define (smaller lower result)
  (start lower result))

#|Realiza la prediccion del numero seleccionado por el usuario
  Entradas: limite inferior, limite superior 
  No devuelve nada, usa la funcion (guess) para el calculo de la prediccion

  Nota: se puede remplazar "start" por "guess", se usa start para aprovechar
  la validacion de los extremos
|#
(define (bigger result upper)
  (start result upper)) 
