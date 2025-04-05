
#ifndef JUEGO_H
#define JUEGO_H

/*struct  nodo
 *
 * entradas: no tiene entradas, pero sus campos son valor, puntero a siguinete y puentero a anterior 
 *
 * salida: no tiene salidas como tal, pero serian dos punteros uno a anterior y otro a siguiente
 *
 * comportamiento: modelo para 0 o muchos nodos
 */
struct nodo {
    int valor;
    struct nodo *sig;
    struct nodo *ant;
};

/*struct  Lista
 *
 * entradas: no tiene entradas
 *
 * salida: no tiene salidas como tal, pero este struct nos indica el inicio de nustra lista
 *
 * comportamiento: modelo que indica el inicio de nustra listra doble
 */
struct Lista {
    struct nodo *inicio;
};

/* funcion BuscarElemento
 *
 * entradas: una lista en la cual buscar y un elemento a buscar
 *
 * salida: el nodo, "un puntero", que se busca
 *
 * comportamiento: recorremos la lista hasta encontrar la primer coincidencia con 
 * el elemento a buscar, cuando encontramos el nodo buscado retornamos el puntero
 */
struct nodo *BuscarElemento(struct Lista *lista_doble, int el) ;

/* funcion MostrarLista
 *
 * entradas: una lista a mostrar
 *
 * salida: no tiene salidas como tal, pero se imprime por consola los valores de los nodos
 *
 * comportamiento: recorremos la lista e imprimos el valor almacenado en el nodo, tanto 
 * hacia delante como para atras
 */
void MostrarLista(struct Lista *lista_doble);

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
void EliminarElemento(struct Lista *lista_doble, int el) ;

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
void InsertarElemento(struct Lista *lista_doble, int el);

// CrearNodo no se coloca pues es llamado desde InsertarElemento


#endif