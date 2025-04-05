

% Base de datos de ejemplo

proveedor(1, wallmart, heredia).
proveedor(2, toyota, sanJose).
pieza(1, carro, 100000).
pieza(2, juguete, 50).
pieza(3, paraBrisas, 150).
prov_pieza(2, 1, 100).
prov_pieza(1, 2, 20).
prov_pieza(1, 3, 10).

% Predicados

ciudades_para_pieza(NombrePieza, CiudadProv):-
  % Buscamos la primera pieza con el nombre especificado
  pieza(_, NombrePieza, _),
  % Buscamos el proveedor que vende la pieza
  prov_pieza(IdProv, Pieza, _),
  % Obtenemos la ciudad del proveedor
  proveedor(IdProv, _, CiudadProv).

