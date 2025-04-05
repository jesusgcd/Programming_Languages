#lang racket/base
(require racket/list)


#|
   a) Escriba una función recursiva que reciba como parámetro un número n y calcule la
   suma de todos los enteros comprendidos entre [1,n]
  
  Entradas: un numero entero => n 
  Salida: la suma de los numeros desde 1 hasta n 
|#

(define (sum-rank n)
  (cond [(= n 1) 1]
        [else (+ n (sum-rank (- n 1)))]))

; Ejemplo de uso
(sum-rank 4)
(sum-rank 6)
(sum-rank 10)


#|
  b) Escriba una función recursiva que reciba una lista y elimine los duplicados continuos
  
  Entradas: una lista 
  salida: uan lista sin duplicados continuos
|#

(define (compress lst)
  (cond
    ((null? (cdr lst)) lst) ; si lista = vacia usar null? -> '() o empty? -> empty
    ((equal? (car lst) (cadr lst))
     (compress (cdr lst))) 
    (else
     (cons (car lst) (compress (cdr lst)))))) 
; Ejemplo de uso
(compress '(1 2 2 3 4 5 5 5 5))



#|
  c) Escriba una función recursiva que reciba una lista de listas y devuelva una lista plana 
  (sin lista internas)

  Entradas: una lista con listas
  Salidas: una lista plana
|#

(define (flatten lst)
  (cond
    ((null? lst) '()) ; si lista = vacia usar null? -> '() o empty? -> empty
    ((not (pair? lst)) (list lst)) 
    (else (append (flatten (car lst)) (flatten (cdr lst)))))) 

; Ejemplo de uso:
(flatten '((1 2 3) (4 5) (6 7 8) (9 10 11)))


#|
  d) Escriba una función recursiva que duplique cada elemento de una lista.

  Entrada: una lista
  Salida:  una lista con cada elemento duplicado 
|#

(define (duplicate lst)
  (cond
    ((empty? lst) empty) 
    (else (cons (car lst) (cons (car lst) (duplicate (cdr lst)))))))

; Ejemplo de uso:
(duplicate '(A B C C D))




#|
  e) Escriba una función recursiva que divida una lista en dos partes dada la longitud de la 
  primera parte.

  Entrada: una lista y un numero entero => n
  Salida: dos lista, donde la primera es de tamano n
|#

(define (split lista n)
  (cond
    ((or (null? lista) (<= n 0)) (list '() '()))
    ((= n 1) (list (list (car lista)) (cdr lista)))
    (else
     (let ((resto (split (cdr lista) (- n 1))))
       (list (cons (car lista) (car resto)) (cadr resto)))))) 

; Ejemplo de uso

(split '(A B C D E F G H I K) 5)


#|
  f) Escriba una función recursiva que empaquete duplicados consecutivos de elementos 
  en sub-listas. Es decir, si una lst contiene elementos consecutivos repetidos, deben 
  colocarse en sub-listas separadas.

  Entrada: una lista con elementos duplicados consecutivos
  Salida: una lista con los elementos duplicados separados en listas individuales 
|#

(define (pack lst)
  (cond
    ((null? lst) '()) 
    (else
     (let loop ((resto lst) (actual (car lst)) (sublst '()))
       (cond
         ((null? resto) (list (reverse sublst))) 
         ((equal? (car resto) actual)
          (loop (cdr resto) actual (cons (car resto) sublst))) 
         (else
          (cons (reverse sublst) (pack resto)))))))) 

; Ejemplo de uso
(pack '(A A A A B C C A A D E E E E))






