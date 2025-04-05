


#lang racket
(require racket/list)


(define-struct nodo (id name valor hijos) #:transparent )





; Función para imprimir el árbol n-ario
(define (imprimir-arbol arbol nivel)
  (define (espacios n)
    (make-string (* n 2) #\space))
  
  (cond
    ((not (nodo? arbol)) '())
    (else
     (begin
       (display (espacios nivel))
       (display (nodo-id arbol))
       (display " - ")
       (display (nodo-name arbol))
       (display " (valor: ")
       (display (nodo-valor arbol))
       (display ")")
       (newline)
       (imprimir-hijos (nodo-hijos arbol) (+ nivel 1))))))
  
(define (imprimir-hijos hijos nivel)
  (cond 
    ((null? hijos) "Fin del arbol" )
    (else
     (begin
       (imprimir-arbol (car hijos) nivel)
       (imprimir-hijos (cdr hijos) nivel)))))



; Función para insertar en el arbol
(define (insert-node arbol padre-id nuevo-id nuevo-name nuevo-valor)
  (define (insertar-en-nodo nodo)
    (if (= (nodo-id nodo) padre-id)
        (make-nodo (nodo-id nodo) (nodo-name nodo) (nodo-valor nodo)
                   (cons (make-nodo nuevo-id nuevo-name nuevo-valor '()) (nodo-hijos nodo)))
        (make-nodo (nodo-id nodo) (nodo-name nodo) (nodo-valor nodo) (map insertar-en-nodo (nodo-hijos nodo)))))
  (make-nodo (nodo-id arbol) (nodo-name arbol) (nodo-valor arbol) (map insertar-en-nodo (nodo-hijos arbol))))


; Funcion de busqueda
(define (buscar-nodo-con-padre arbol objetivo)
  (define (buscar-en-nodo nodo padre)
    (cond
      ((= (nodo-id nodo) objetivo) (list padre nodo))
      (else
       (ormap (lambda (hijo)
                (buscar-en-nodo hijo nodo))
              (nodo-hijos nodo)))))
  
  
  (let ((resultados (buscar-en-nodo arbol '())))
    (if (null? resultados)
        '()
        resultados)))


; imprime bonito buscado
(define (resultado-busqueda dato)
(cond
  ((null? dato) (display "Nodo no encontrado") )
  (else
   (let ((padre (car dato))
         (nodo-buscado (cadr dato)))
     (display "\nNodo encontrado:")
     (newline)
     (display "\nNodo Padre:")
     (newline)
     (imprimir-arbol padre 0)
     (display "Nodo buscado:")
     (newline)
     (imprimir-arbol nodo-buscado 0)))))


; Buscar y mostrar los datos de la primer lista en el árbol
(define (ancestor arbol-n-ario nodo_id)
  (define resultado-busqueda (buscar-nodo-con-padre arbol-n-ario nodo_id))
  (define nodo (car resultado-busqueda))
  (display "ID: ")
  (display (nodo-id nodo))
  (display ", Nombre: ")
  (display (nodo-name nodo))
  (display ", Valor: ")
  (display (nodo-valor nodo))
  (newline)
  )



;eliminar nodo
(define (eliminar-nodo-recursivo arbol nodo_id)
  (define (eliminar-en-nodo nodo)
    (if (= (nodo-id nodo) nodo_id)
        #f
        (make-nodo (nodo-id nodo) (nodo-name nodo) (nodo-valor nodo)
                   (eliminar-hijos (nodo-hijos nodo)))))

(define (eliminar-hijos hijos)
   (if (null? hijos) '()
        (cons (eliminar-en-nodo (car hijos))
              (eliminar-hijos (cdr hijos)))))

  (eliminar-en-nodo arbol))




(define (create-tree)
  
  (define arbol-n-ario
  (make-nodo 1 "root" 100
    (list
      (make-nodo 2 "nodo2" 50
        (list
          (make-nodo 4 "nodo4" 25 '())
          (make-nodo 5 "nodo5" 25 '())))
      (make-nodo 3 "nodo3" 50
        (list
          (make-nodo 6 "nodo6" 30 '())
          (make-nodo 7 "nodo7" 20
            (list
              (make-nodo 8 "nodo8" 10 '())
              (make-nodo 9 "nodo9" 10 '()))))))))
  
  ; imprimir el arbol
  (imprimir-arbol arbol-n-ario 0)


  ; Insertar un nodo con id 10 en el nodo padre con id 4
  (set! arbol-n-ario (insert-node arbol-n-ario 4 10 "hijo de 4" 15))
  (set! arbol-n-ario (insert-node arbol-n-ario 4 11 "hijo de 4" 15))
  (set! arbol-n-ario (insert-node arbol-n-ario 4 12 "hijo de 4" 15))
  (imprimir-arbol arbol-n-ario 0)
 
  (resultado-busqueda (buscar-nodo-con-padre arbol-n-ario 8))

  ; Ejemplo de uso de eliminar
  (set! arbol-n-ario (eliminar-nodo-recursivo arbol-n-ario 7))
  (imprimir-arbol arbol-n-ario 0)

 ;ejemplo de buscar ancestro
 (ancestor arbol-n-ario 6)

  

  )


; %%%%%%%%%%%%  Test  %%%%%%%%%%%%

(create-tree)
