#include "lista_doble.h"
#include <stdio.h>
#include <stdlib.h>

/* funcion CrearNodo
 *
 * entradas: un elemento, => "el", a insertar en un nodo 
 *
 * salida: un nodo doble con el elemento insertado
 *
 * comportamiento: reservamos memoria con calloc, despues asignamos el elemento
 * al campo valor y por ultimo retornamos el nodo que creamos
 */
struct nodo* CrearNodo(int el) {
    struct nodo *NuevoNodo = calloc(1, sizeof(struct nodo));
    NuevoNodo->valor = el;
    return NuevoNodo;
}

/* funcion InsertarElemento
 *
 * entradas: una lista a la cual le insertaresmo un elemento y el elemento a insertar
 *
 * salida: no tiene salidas
 *
 * comportamiento: usamos la funcion CrearNodo para crear el nodo con el lemento a insertar
 * eso nos retorna un NuevoNodo, despues recorremos la lista buscando el final de la misma y 
 * es hay en donde insertamos el nodo que creamos
 */
void InsertarElemento(struct Lista *lista_doble, int el) {
    if (lista_doble == NULL) {
        return;
        }
    if (lista_doble -> inicio == NULL) {
        struct nodo *NuevoNodo = CrearNodo(el);
        lista_doble->inicio = NuevoNodo;
        return; 
    } else {
        struct nodo *actual = lista_doble->inicio;
        while (actual != NULL) {
            if (actual->sig == NULL)  {
                struct nodo *NuevoNodo = CrearNodo(el);
                actual->sig = NuevoNodo;
                NuevoNodo->ant = actual;
                return;
            }
            actual = actual->sig;
        }
    }
    return;
}

/* funcion EliminarElemento
 *
 * entradas: una lista a en la cual queremos eliminar un elemento y el  elemento a eliminar
 *
 * salida: no tiene salidas
 *
 * comportamiento: no movemos en la lista buscando los elementos que coincidan con el 
 * elemento a borrar, cuando encontramos coincidencia borramos el nodo y seguimos buscando
 * hasta que no encontrar mas elementos a borrar
 */
void EliminarElemento(struct Lista *lista_doble, int el) {
    if (lista_doble == NULL) {
        return;
    }
    if (lista_doble->inicio == NULL) {
        return;
    }
    struct nodo *actual = lista_doble->inicio;
    // caso especial, es el primero de la lista
    // es que hace cosas muy locas en el bucle
    // if (actual->valor == el){
    //     printf("primero\n");
    //     struct nodo *temp = actual;
    //     lista_doble->inicio = temp->sig;
    //     if (temp->sig != NULL) {
    //         temp->sig->ant = NULL;
    //     }
    //     free(temp);
    //     temp = NULL;
    //     // return;
    // }
    actual = lista_doble->inicio;
    while (actual != NULL) {
        // caso especial, es el primero de la lista
        // es que hace cosas muy locas en el bucle
        if (lista_doble->inicio->valor == el && lista_doble->inicio->sig != NULL){
            printf("primero\n");
            struct nodo *temp = lista_doble->inicio;
            lista_doble->inicio = temp->sig;
            if (temp->sig != NULL) {
                temp->sig->ant = NULL;
            }
            free(temp);
            temp = NULL;
            actual = lista_doble->inicio;
            // return;
        } 
        // caso especial, ultimo de la lista
        else if (actual->valor == el && actual->sig == NULL) { 
            printf("especial\n");
            struct nodo *temp = actual;
            temp->ant->sig = NULL;
            free(temp);
            temp = NULL;
            // return;
            actual = lista_doble->inicio;
        } 
        // caso normal
        else if (actual->valor == el && actual->sig != NULL){
            printf("normal\n");
            struct nodo *temp = actual;
            struct nodo *anterior = temp->ant;
            struct nodo *siguiente = temp->sig;
            anterior->sig = siguiente;
            siguiente->ant = anterior;
            free(temp);
            temp = NULL;
            // return;
            // esta feo pero no se me ocurre otra forma xd
            actual = lista_doble->inicio;
        }
        actual = actual->sig;
    }
}

/* funcion BuscarElemento
 *
 * entradas: una lista en la cual buscar y un elemento a buscar
 *
 * salida: el nodo, "un puntero", que se busca
 *
 * comportamiento: recorremos la lista hasta encontrar la primer coincidencia con 
 * el elemento a buscar, cuando encontramos el nodo buscado retornamos el puntero
 */
struct nodo *BuscarElemento(struct Lista *lista_doble, int el) {
    if (lista_doble == NULL) {
        return NULL;
    }
    struct nodo *actual = lista_doble->inicio;
    while (actual != NULL) {
        if (actual->valor == el) {
            // printf("valor: %d \n",actual->valor);
            return actual;
        }
        actual = actual->sig;
    }
    return NULL;
}

/* funcion MostrarLista
 *
 * entradas: una lista a mostrar
 *
 * salida: no tiene salidas como tal, pero se imprime por consola los valores de los nodos
 *
 * comportamiento: recorremos la lista e imprimos el valor almacenado en el nodo, tanto 
 * hacia delante como para atras
 */
void MostrarLista(struct Lista *lista_doble) {
    if (lista_doble == NULL) {
        return;
    }
    struct nodo *actual = lista_doble->inicio;
    struct nodo* ultimo = lista_doble->inicio;
    while (actual != NULL) {
        printf("%d -> ", actual->valor);
        actual = actual->sig;
    }
    printf("NULL\n");
    printf("NULL");
    actual = lista_doble->inicio;
    int cambio = 0;
    while (actual != NULL) {
        if (cambio == 0) {
            if (actual->sig == NULL) {
                cambio = 1;
            } else {
                actual = actual->sig;
            }
        }
        else {
            printf(" <- %d", actual->valor);
            actual = actual->ant;
        }
    }
    printf("\n");
    return;
}