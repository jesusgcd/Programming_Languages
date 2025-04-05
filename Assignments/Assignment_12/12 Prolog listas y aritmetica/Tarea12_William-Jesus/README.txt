/*
Instituto Tecnologico de Costa Rica
Escuela de Ingenieria en Computacion
Lenguajes de Programacion
Tarea 12 - Prolog listas y aritmetica
WIlliam Alfaro Quiros - 2022437996
Jesus Cordero Diaz - 2020081049  
*/

% -----------------------------------------------------------------------------------------------

/*
1) Para una lista de números devuelva la sumatoria de todos los miembros de la lista

        Ejemplo:

        ?- sumarLista([1,2,3,4,5],X).
        X = 15.
*/

/* Recursivo con Pila */

sumarLista([], 0).
sumarLista([H|T], X) :-
    sumarLista(T, Resto),
    X is H + Resto.

/* Recursivo con Cola */

sumarListaCola(Lista, Suma) :- sumarListaColaAux(Lista, 0, Suma).

sumarListaColaAux([], Acumulador, Acumulador).
sumarListaColaAux([Cabeza|Resto], AcumuladorParcial, Suma) :-
    AcumuladorNuevo is AcumuladorParcial + Cabeza,
    sumarListaColaAux(Resto, AcumuladorNuevo, Suma).


/*
2) Calcule el producto interno de dos vectores.

        Ejemplo:

        ?- prodint([1,2,3,4], [1,2,3,4], PI).
        PI = 30.
        PI = 1*1 + 2*2 + 3*3 + 4*4
*/

/* Recursivo con Pila */
% Caso base
prodint([], [], 0).

% Caso recursivo
prodint([X1|Xs], [Y1|Ys], PI) :-
    prodint(Xs, Ys, RestoPI), % Llamada recursiva
    PI is X1 * Y1 + RestoPI.

/* Recursivo con Cola */
prodintCola(Vector1, Vector2, PI) :- prodintCola(Vector1, Vector2, 0, PI).

prodintCola([], [], PI, PI).
prodintCola([X1|Resto1], [X2|Resto2], Temp, PI) :-
    TempN is Temp + X1 * X2,
    prodintCola(Resto1, Resto2, TempN, PI).


/*
3) Defina el predicado fibonacci(N,X). 
        Donde
        Sucesión de Fibonacci: 0,1,1,2,3,5,8,13,21, … cada término, salvo los dos primeros, es
        la suma de los dos anteriores.
*/

/* Recursivo con Pila */
% Casos base
fibonacci(0, 0).
fibonacci(1, 1).

% Definición recursiva
fibonacci(N, X) :-
    N > 1,
    N1 is N - 1,
    N2 is N - 2,
    fibonacci(N1, F1),
    fibonacci(N2, F2),
    X is F1 + F2.

/* Recursivo con Cola */
% Regla principal que inicializa los valores iniciales
fibonacciCola(N, Resultado) :- fibonacciCola(N, 0, 1, Resultado).

% Caso base
fibonacciCola(0, X, _, X).

% Regla recursiva
fibonacciCola(N, A, B, Resultado) :-
    N > 0,
    N1 is N - 1,
    Suma is A + B,
    fibonacciCola(N1, B, Suma, Resultado).

% -----------------------------------------------------------------------------------------------

/*
@ Pruebas

Ejercicio 1

    Recursivo con Pila

        ?- sumarLista([1, 2, 3, 4, 5], X).
        Debería devolver: X = 15.

    Recursivo con Cola

        ?- sumarListaCola([1, 2, 3, 4, 5], X).
        Debería devolver: X = 15.

Ejercicio 2

    Recursivo con Pila

        ?- prodint([1,2,3,4], [1,2,3,4], PI).
        Debería devolver: PI = 30.

    Recursivo con Cola

        ?- prodintCola([1,2,3,4], [1,2,3,4], PI).
        Debería devolver PI = 30.


Ejercicio 3

    Recursivo con Pila

        ?- fibonacci(10, X).
        Debería devolver: X = 55.

    Recursivo con Cola

        ?- fibonacciCola(10, X).
        X = 55.
*/




