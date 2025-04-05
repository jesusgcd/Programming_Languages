% Hechos
padre(pedro, juan).
padre(pedro, marta).
padre(pedro, maria).
mujer(marta).
mujer(maria).

% Reglas
hermanos(X, Y) :- padre(Z, X), padre(Z, Y), X \= Y.

hermana(X, Y) :- mujer(X), hermanos(X, Y).
