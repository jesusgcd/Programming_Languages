#include "lista_doble.h"
#include <stdio.h>
#include <stdlib.h>

int main() {
  struct Lista *lista_doble = calloc(1, sizeof(struct Lista));

  InsertarElemento(lista_doble, 3);
  InsertarElemento(lista_doble, 3);

  for (int i = 0; i < 6; i++) {
    InsertarElemento(lista_doble, 3);
    InsertarElemento(lista_doble, i);
    InsertarElemento(lista_doble, 3);
  }

  InsertarElemento(lista_doble, 3);

  MostrarLista(lista_doble);
  printf("---------------------------------\n");
  int eliminar = 3;
  printf("se elimina el %d\n", eliminar);
  EliminarElemento(lista_doble, eliminar);
  MostrarLista(lista_doble);
  printf("---------------------------------\n");
  // retornar el nodo entero
  int buscar = 0;
  struct nodo *cambio = BuscarElemento(lista_doble, buscar);
  if (cambio == NULL) {
    printf("no existe el valor %d en la lista\n", buscar);
  } else {
    cambio->valor = 123;
  }
  MostrarLista(lista_doble);
  printf("---------------------------------\n");

  return 0;
}
