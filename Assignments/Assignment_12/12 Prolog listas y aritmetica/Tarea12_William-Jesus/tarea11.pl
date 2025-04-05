
% Ejercicio 1

% Reglas de taxonomía para el colibrí
% Define la relación taxonómica entre diferentes niveles de la jerarquía taxonómica.

taxonomica_coli(chordata, animalia).        % Chordata está en el nivel Animalia.
taxonomica_coli(aves, chordata).            % Aves están en el nivel Chordata.
taxonomica_coli(apodiformes, aves).         % Apodiformes están en el nivel Aves.
taxonomica_coli(trochilidae, apodiformes).  % Trochilidae están en el nivel Apodiformes.
taxonomica_coli(colibri, trochilidae).      % Colibrí está en el nivel Trochilidae.
taxonomica_coli(eukaryota, colibri).        % Eukaryota está en el nivel Colibrí.

% Reglas para determinar si un nivel es superior a otro en la jerarquía taxonómica.
% La regla 'super' utiliza recursión para encontrar niveles superiores.

super(X, Y) :- taxonomica_coli(X, Y).               % Si X está directamente relacionado con Y, entonces X es superior a Y.
super(X, Y) :- taxonomica_coli(X, Z), super(Z, Y).  % X es superior a Y si Z es superior a Y y X está relacionado con Z.

% Reglas de taxonomía para el ceiba

% Define la relación taxonómica entre diferentes niveles de la jerarquía taxonómica del ceiba.

taxonomica_ceib(magnoliophyta, plantae).         % Magnoliophyta está en el nivel Plantae.
taxonomica_ceib(magnoliopsida, magnoliophyta).   % Magnoliopsida está en el nivel Magnoliophyta.
taxonomica_ceib(dilleniidae, magnoliopsida).     % Dilleniidae está en el nivel Magnoliopsida.
taxonomica_ceib(malvales, dilleniidae).          % Malvales está en el nivel Dilleniidae.
taxonomica_ceib(malvaceae, malvales).            % Malvaceae está en el nivel Malvales.
taxonomica_ceib(bombacoideae, malvaceae).        % Bombacoideae está en el nivel Malvaceae.
taxonomica_ceib(ceiba, bombacoideae).            % Ceiba está en el nivel Bombacoideae.

% Reglas para determinar si un nivel es superior a otro en la jerarquía taxonómica del ceiba.
% La regla 'super_' utiliza recursión para encontrar niveles superiores.

super_(X, Y) :- taxonomica_ceib(X, Y).                  % Si X está directamente relacionado con Y, entonces X es superior a Y.
super_(X, Y) :- taxonomica_ceib(X, Z), super_(Z, Y).    % X es superior a Y si Z es superior a Y y X está relacionado con Z.



% Ejercicio2

miembro(X, [X|_]).                      % Verifica si X está en la cabeza de la lista.
miembro(X, [_|T]) :- miembro(X, T).     % Recursivamente verifica en el resto de la lista.


% Ejercicio 3

maximo([X], X).                         % Caso base: lista de un elemento, X es el maximo.
maximo([H|T], X) :-                     % Caso recursivo: divide la lista en cabeza (H) y cola (T).
    maximo(T, MaxT),                    % Llamada recursiva para encontrar el maximo en la cola (MaxT).
    (H >= MaxT -> X = H ; X = MaxT).    % Compara H con MaxT para encontrar el maximo final.



% Ejercicio 4

subconjunto([], _).                     % Caso base: un conjunto vacío es un subconjunto válido.
subconjunto([X|T], K) :-                % Verifica si X está en K y verifica recursivamente el resto de la lista.
    miembro(X, K),
    subconjunto(T, K).


/*
  Tests:

    Ejercicio 1:

% funtor para "Colibri thalassinus"

?- super(X,animalia). 
X = chordata ;
X = aves ;
X = apodiformes ;
X = trochilidae ;
X = colibri ;
X = eukaryota ;
false.


% funtor para "Ceiba pentandra"

?- super_(X,plantae).
X = magnoliophyta ;
X = magnoliopsida ;
X = dilleniidae ;
X = malvales ;
X = malvaceae ;
X = bombacoideae ;
X = ceiba ;
false.

    Ejercicio 2:
?- miembro(1, [1,2,3,4,5]).
true.

    Ejercicio 3:
?- maximo([1,2,3,4,5], X).
X = 5.
?- maximo([10,8,3,9,5], X).
X = 10.

    Ejercicio 4:
?- subconjunto([1,4,6], [1,2,3,4,5,6]).
true.
 
*/