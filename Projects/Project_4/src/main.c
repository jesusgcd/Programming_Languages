#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>

// variables globales
int num_leopardos = 6;         // numero de leopardos sin colocar
int num_leopardos_comidos = 0; // numero de leopardos comidos
int es_vacio = 0;              // variable para saber si el nodo esta vacio o no
int es_leopardo = 1;           // variable para saber si el nodo es un leopardo o no
int es_tigre = 2;              // variable para saber si el nodo es un tigre o no
int puede_ganar = 0;           // variable para saber si el jugador puede ganar o no

// structs

// struct para definir el cuerpo de la piramide (los nodos)
typedef struct nodo
{
    int pos;
    int tipo;
    struct nodo *arriba;
    struct nodo *izquierda;
    struct nodo *derecha;
    struct nodo *abajo;
};

// struct para definir la cabeza de la piramide
typedef struct cabeza
{
    int pos;
    int tipo;
    struct nodo *izquierda;
    struct nodo *medio;
    struct nodo *derecha;
};

// functions

/*
 * Funcion  crear_nodo
 *
 * Descripcion: Funcion para crear un nodo, usando la estructura nodo,
 * para la creacion del nodo se usa malloc para reservar el espacio
 * de memoria necesario para el nodo
 * @param void
 * @return nodo
 *
 */
struct nodo *crear_nodo()
{

    struct nodo *nodo = (struct nodo *)malloc(sizeof(struct nodo));
    nodo->pos = 1;
    nodo->tipo = es_vacio;
    nodo->arriba = NULL;
    nodo->izquierda = NULL;
    nodo->derecha = NULL;
    nodo->abajo = NULL;

    return nodo;
};

/*
 *
 * Funcion  crear_cabeza
 *
 * Descripcion: Funcion para crear la cabeza de la piramide, usando la estructura cabeza,
 * para la creacion de la cabeza se usa malloc para reservar el espacio de memoria necesario
 * para la cabeza
 * @param void
 * @return cabeza
 *
 */
struct cabeza *crear_cabeza()
{

    struct cabeza *cabeza = (struct cabeza *)malloc(sizeof(struct cabeza));
    cabeza->pos = 1;
    cabeza->tipo = es_tigre;
    cabeza->izquierda = NULL;
    cabeza->medio = NULL;
    cabeza->derecha = NULL;

    return cabeza;
};

/*
 *
 * Funcion crear_nivel
 *
 * Descripcion: Funcion crear un nivel de la piramide, usando la estructura de nodo, para la creacion
 * del nivel se usa malloc para reservar el espacio de memoria necesario para el nivel, se crean 3 nodos,
 * izquierda, derecha y medio, los cuales se enlazan entre si y se retorna el nodo medio
 *
 * @param int nivel, este representa el nivel de la piramide que se quiere crear, y representa la posicion
 * del nodo medio
 * @return nodo enlazado a los nodos de la derecha e izquierda (se retorna el del medio)
 */
struct nodo *crear_nivel(int nivel)
{

    struct nodo *izquierda = crear_nodo();
    struct nodo *derecha = crear_nodo();
    struct nodo *medio = crear_nodo();

    izquierda->pos = (nivel * 3) - 1;
    derecha->pos = (nivel * 3) + 1;
    medio->pos = (nivel * 3);

    medio->izquierda = izquierda;
    medio->derecha = derecha;
    izquierda->derecha = medio;
    derecha->izquierda = medio;

    return medio;
};

/*
 *
 * Funcion crear_piramide
 *
 * Descripcion: Funcion para crear la piramide, usando la estructura de cabeza, y la funcion crear_nivel
 * para la creacion de la piramide se usa malloc para reservar el espacio de memoria necesario para la piramide,
 *
 * @param int niveles, este representa el numero de niveles de la piramide que se quiere crear
 * @return cabeza
 */
struct cabeza *crear_piramide(int niveles)
{

    struct cabeza *cabeza = crear_cabeza();
    struct nodo *nodo_medio = crear_nivel(1);

    // se enlaza la cabeza con los nodos del primer nivel
    cabeza->izquierda = nodo_medio->izquierda;
    cabeza->medio = nodo_medio;
    cabeza->derecha = nodo_medio->derecha;

    // se enlazalan los nodos del primer nivel con la cabeza
    nodo_medio->izquierda->arriba = cabeza;
    nodo_medio->arriba = cabeza;
    nodo_medio->derecha->arriba = cabeza;

    // se crea el resto de la piramide
    struct nodo *nodo_medio_anterior = nodo_medio;
    for (int i = 2; i <= niveles; i++)
    {
        // se crea un nuevo nivel
        struct nodo *nodo_nuevo_nivel = crear_nivel(i);

        // se enlazan los nodos del nuevo nivel con los nodos del nivel anterior

        // enlazando el nodo izquierdo del nuevo nivel con el nodo izquierdo del nivel anterior
        nodo_nuevo_nivel->izquierda->arriba = nodo_medio_anterior->izquierda;
        // enlazando el nodo izquierdo del nivel anterior con el nodo izquierdo del nuevo nivel
        nodo_medio_anterior->izquierda->abajo = nodo_nuevo_nivel->izquierda;

        // enlazando el nodo medio del nuevo nivel con el nodo medio del nivel anterior
        nodo_nuevo_nivel->arriba = nodo_medio_anterior;
        // enlazando el nodo medio del nivel anterior con el nodo medio del nuevo nivel
        nodo_medio_anterior->abajo = nodo_nuevo_nivel;

        // enlazando el nodo derecho del nuevo nivel con el nodo derecho del nivel anterior
        nodo_nuevo_nivel->derecha->arriba = nodo_medio_anterior->derecha;
        // enlazando el nodo derecho del nivel anterior con el nodo derecho del nuevo nivel
        nodo_medio_anterior->derecha->abajo = nodo_nuevo_nivel->derecha;

        // se actualiza el nodo medio anterior
        nodo_medio_anterior = nodo_nuevo_nivel;
    }

    return cabeza;
};

/*
 *
 * Funcion imprimir_piramide
 *
 * Descripcion: Funcion para imprimir la piramide
 * @param cabeza
 * @return void
 */
void imprimir_piramide(struct cabeza *cabeza)
{

    printf("imprimiendo piramide\n");
    // printf("%d\n", cabeza->tipo);
    printf("[T:%d,P:%d]\n", cabeza->tipo, cabeza->pos);

    struct nodo *nodo_medio_actual = cabeza->medio;
    while (nodo_medio_actual != NULL)
    {
        // printf("%d  %d  %d",
        //        nodo_medio_actual->izquierda->tipo,
        //        nodo_medio_actual->tipo,
        //        nodo_medio_actual->derecha->tipo);
        // printf("\n");

        printf("[T:%d,P:%d][T:%d,P:%d][T:%d,P:%d]",
               nodo_medio_actual->izquierda->tipo,
               nodo_medio_actual->izquierda->pos,
               nodo_medio_actual->tipo,
               nodo_medio_actual->pos,
               nodo_medio_actual->derecha->tipo,
               nodo_medio_actual->derecha->pos);
        printf("\n");

        nodo_medio_actual = nodo_medio_actual->abajo;
    };
};

/*
 *
 * Funcion buscar_nodo
 *
 * Descripcion: Funcion para buscar un nodo en la piramide
 * @param pos, representa la posicion del nodo que se quiere buscar
 * @param cabeza
 * @return nodo
 */
struct nodo *buscar_nodo(int pos, struct cabeza *cabeza)
{

    // se busca el nodo en los niveles
    struct nodo *nodo_medio_actual = cabeza->medio;
    while (nodo_medio_actual != NULL)
    {
        int pos_nodo_medio = nodo_medio_actual->pos;
        // es el nodo de la izquierda
        if (pos_nodo_medio - 1 == pos)
        {
            return nodo_medio_actual->izquierda;
        }
        else if (pos_nodo_medio == pos)
        {
            return nodo_medio_actual;
        }
        // es el nodo de la derecha
        else if (pos_nodo_medio + 1 == pos)
        {
            return nodo_medio_actual->derecha;
        }
        // no es ninguno de los nodos del nivel actual
        else
        {
            // bajar al siguiente nivel
            nodo_medio_actual = nodo_medio_actual->abajo;
        }
    };
    return NULL;
};

/*
 *
 * Funcion cambiar_tipo_nodo
 *
 * Descripcion: Funcion para cambiar el tipo de un nodo
 * @param pos, representa la posicion del nodo que se quiere cambiar
 * @param tipo, representa el tipo al que se quiere cambiar el nodo
 * @param cabeza
 * @return void
 */
void cambiar_tipo_nodo(int pos, int tipo, struct cabeza *cabeza)
{

    // // Imprimir el cuarto parámetro
    // printf("Cuarto parametro: %d\n", come_leopardo);

    // si hay que cambiar en la cabeza
    if (pos == 1 && (cabeza->tipo == es_vacio || tipo == es_vacio))
    {
        cabeza->tipo = tipo;
        num_leopardos = (tipo == es_leopardo) ? num_leopardos - 1 : num_leopardos;
    }
    // si no es en la cabeza
    struct nodo *nodo_cambiar = buscar_nodo(pos, cabeza); // buscar el nodo en la piramide (el del medio)
    if (nodo_cambiar != NULL)
    {
        if (nodo_cambiar->pos == pos && (nodo_cambiar->tipo == es_vacio || tipo == es_vacio))
        {
            nodo_cambiar->tipo = tipo;
            num_leopardos = (tipo == es_leopardo) ? num_leopardos - 1 : num_leopardos;
        }
    }
};

/*
 *
 * Funcion colocar_leopardo
 *
 * Descripcion: Funcion para colocar un leopardo en la piramide
 * @param cabeza
 * @param int pos, representa la posicion del nodo donde se quiere colocar el leopardo
 * @return void
 */
void colocar_leopardo(struct cabeza *cabeza)
{
    // leopardos sin colocar
    if (num_leopardos > 0)
    {
        // indicar que aun falta un leopardo por colocar e indicar cuantos faltan
        printf("Aun faltan %d leopardos por colocar\n", num_leopardos);

        // solicitar la posicion del nodo donde se quiere colocar el leopardo
        printf("ingrese la posicion en donde quiere colocar el leopardo: ");
        int pos = 1;
        scanf("%d", &pos);

        // cambiar el tipo del nodo
        cambiar_tipo_nodo(pos, es_leopardo, cabeza);
    }
    // no hay leopardos sin colocar
    else
    {
        // modificar el valor de la variable para indicar que el jugador (tigre) puede ganar, esto solo si ya se an colocado todos los leopardos almenos una vez
        puede_ganar = 1;

        // indicar que ya no hay leopardos por colocar
        printf("Ya no hay leopardos por colocar\n");
        // pedir la posicion del leopardo a mover y la posicion a donde se quiere mover
        printf("ingrese la posicion del leopardo a mover: ");
        int pos_leopardo = 0;
        scanf("%d", &pos_leopardo);
        printf("ingrese la posicion a donde quiere mover el leopardo: ");
        int pos_mover = 0;
        scanf("%d", &pos_mover);

        // buscar el nodo a mover
        struct nodo *nodo_pos_leopardo = buscar_nodo(pos_leopardo, cabeza);

        // buscar el nodo a donde se quiere mover
        struct nodo *nodo_pos_mover = buscar_nodo(pos_mover, cabeza);

        // validar que el nodo_pos_leopardo y el nodo_pos_mover no sean nulos
        if (nodo_pos_leopardo != NULL && nodo_pos_mover != NULL)
        {

            // // validar que el nodo_pos_leopardo y el nodo_pos_mover sean vecinos
            // if (nodo_pos_leopardo->izquierda->pos == pos_mover ||
            //     nodo_pos_leopardo->arriba->pos == pos_mover ||
            //     nodo_pos_leopardo->derecha->pos == pos_mover ||
            //     nodo_pos_leopardo->abajo->pos == pos_mover)
            // {

            //     cambiar_tipo_nodo(pos_mover, es_leopardo, cabeza);
            //     cambiar_tipo_nodo(pos_leopardo, es_vacio, cabeza);
            // }
            if (nodo_pos_leopardo->izquierda != NULL)
            {
                // printf("izquierda: %d\n", nodo_pos_leopardo->izquierda->pos);
                if (nodo_pos_leopardo->izquierda->pos == pos_mover)
                {
                    cambiar_tipo_nodo(pos_mover, es_leopardo, cabeza);
                    cambiar_tipo_nodo(pos_leopardo, es_vacio, cabeza);
                }
            }
            if (nodo_pos_leopardo->arriba != NULL)
            {
                // printf("arriba: %d\n", nodo_pos_leopardo->arriba->pos);
                if (nodo_pos_leopardo->arriba->pos == pos_mover)
                {
                    cambiar_tipo_nodo(pos_mover, es_leopardo, cabeza);
                    cambiar_tipo_nodo(pos_leopardo, es_vacio, cabeza);
                }
            }
            if (nodo_pos_leopardo->derecha != NULL)
            {
                // printf("derecha: %d\n", nodo_pos_leopardo->derecha->pos);
                if (nodo_pos_leopardo->derecha->pos == pos_mover)
                {
                    cambiar_tipo_nodo(pos_mover, es_leopardo, cabeza);
                    cambiar_tipo_nodo(pos_leopardo, es_vacio, cabeza);
                }
            }
            if (nodo_pos_leopardo->abajo != NULL)
            {
                // printf("abajo: %d\n", nodo_pos_leopardo->abajo->pos);
                if (nodo_pos_leopardo->abajo->pos == pos_mover)
                {
                    cambiar_tipo_nodo(pos_mover, es_leopardo, cabeza);
                    cambiar_tipo_nodo(pos_leopardo, es_vacio, cabeza);
                }
            }
        }
        else if (nodo_pos_leopardo != NULL && pos_mover == 1)
        {
            if (nodo_pos_leopardo->arriba->pos == pos_mover)
            {
                cambiar_tipo_nodo(pos_mover, es_leopardo, cabeza);
                cambiar_tipo_nodo(pos_leopardo, es_vacio, cabeza);
            }
        }
        else if (nodo_pos_leopardo == NULL && nodo_pos_mover != NULL)
        {

            if (cabeza->derecha->pos == pos_mover ||
                cabeza->medio->pos == pos_mover ||
                cabeza->izquierda->pos == pos_mover)
            {
                cambiar_tipo_nodo(pos_mover, es_leopardo, cabeza);
                cambiar_tipo_nodo(pos_leopardo, es_vacio, cabeza);
            }
        }
    }
};

/*
 *
 * Funcion mover_tigre
 *
 * Descripcion: Funcion para mover el tigre en la piramide
 * @param cabeza
 * @return void
 */
void mover_tigre(struct cabeza *cabeza)
{
    // pedir la posicion del tigre a mover y la posicion a donde se quiere mover
    printf("ingrese la posicion del tigre a mover: ");
    int pos_tigre = 0;
    scanf("%d", &pos_tigre);
    printf("ingrese la posicion a donde quiere mover el tigre: ");
    int pos_mover = 0;
    scanf("%d", &pos_mover);

    // buscar el nodo a mover
    struct nodo *nodo_pos_tigre = buscar_nodo(pos_tigre, cabeza);

    // buscar el nodo a donde se quiere mover
    struct nodo *nodo_pos_mover = buscar_nodo(pos_mover, cabeza);

    // validar que el nodo_pos_tigre y el nodo_pos_mover no sean nulos
    if (nodo_pos_tigre != NULL && nodo_pos_mover != NULL)
    {
        if (nodo_pos_tigre->izquierda != NULL)
        {
            // printf("izquierda: %d\n", nodo_pos_tigre->izquierda->pos);
            if (nodo_pos_tigre->izquierda->pos == pos_mover &&
                nodo_pos_tigre->izquierda->tipo == es_vacio)
            {
                cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
            }
            else if (nodo_pos_tigre->izquierda->izquierda != NULL)
            {
                // printf("izquierda: %d\n", nodo_pos_tigre->izquierda->izquierda->pos);
                if (nodo_pos_tigre->izquierda->izquierda->pos == pos_mover &&
                    nodo_pos_tigre->izquierda->izquierda->tipo == es_vacio)
                {
                    cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                    cambiar_tipo_nodo(nodo_pos_tigre->izquierda->pos, es_vacio, cabeza);
                    cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
                    num_leopardos_comidos = num_leopardos_comidos + 1;
                }
            }
        }
        if (nodo_pos_tigre->arriba != NULL)
        {
            // printf("arriba: %d\n", nodo_pos_tigre->arriba->pos);
            if (nodo_pos_tigre->arriba->pos == pos_mover &&
                nodo_pos_tigre->arriba->tipo == es_vacio)
            {
                cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
            }
            else if (nodo_pos_tigre->arriba->arriba != NULL)
            {
                // printf("izquierda: %d\n", nodo_pos_tigre->arriba->arriba->pos);
                if (nodo_pos_tigre->arriba->arriba->pos == pos_mover &&
                    nodo_pos_tigre->arriba->arriba->tipo == es_vacio)
                {
                    cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                    cambiar_tipo_nodo(nodo_pos_tigre->arriba->pos, es_vacio, cabeza);
                    cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
                    num_leopardos_comidos = num_leopardos_comidos + 1;
                }
            }
        }
        if (nodo_pos_tigre->derecha != NULL)
        {
            // printf("derecha: %d\n", nodo_pos_tigre->derecha->pos);
            if (nodo_pos_tigre->derecha->pos == pos_mover &&
                nodo_pos_tigre->derecha->tipo == es_vacio)
            {
                cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
            }
            else if (nodo_pos_tigre->derecha->derecha != NULL)
            {
                // printf("izquierda: %d\n", nodo_pos_tigre->derecha->derecha->pos);
                if (nodo_pos_tigre->derecha->derecha->pos == pos_mover &&
                    nodo_pos_tigre->derecha->derecha->tipo == es_vacio)
                {
                    cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                    cambiar_tipo_nodo(nodo_pos_tigre->derecha->pos, es_vacio, cabeza);
                    cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
                    num_leopardos_comidos = num_leopardos_comidos + 1;
                }
            }
        }
        if (nodo_pos_tigre->abajo != NULL)
        {
            // printf("abajo: %d\n", nodo_pos_tigre->abajo->pos);
            if (nodo_pos_tigre->abajo->pos == pos_mover &&
                nodo_pos_tigre->abajo->tipo == es_vacio)
            {
                cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
            }
            else if (nodo_pos_tigre->abajo->abajo != NULL)
            {
                // printf("izquierda: %d\n", nodo_pos_tigre->abajo->abajo->pos);
                if (nodo_pos_tigre->abajo->abajo->pos == pos_mover &&
                    nodo_pos_tigre->abajo->abajo->tipo == es_vacio)
                {
                    cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                    cambiar_tipo_nodo(nodo_pos_tigre->abajo->pos, es_vacio, cabeza);
                    cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
                    num_leopardos_comidos = num_leopardos_comidos + 1;
                }
            }
        }
    }
    else if (nodo_pos_tigre != NULL && pos_mover == 1)
    {
        if (nodo_pos_tigre->arriba->pos == pos_mover &&
            nodo_pos_tigre->arriba->tipo == es_vacio)
        {
            cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
            cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
        }
    }
    else if (nodo_pos_tigre == NULL && nodo_pos_mover != NULL)
    {

        if (cabeza->derecha->pos == pos_mover &&
            cabeza->derecha->tipo == es_vacio)
        {
            cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
            cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
        }
        else if (cabeza->derecha->abajo != NULL)
        {
            if (cabeza->derecha->abajo->pos == pos_mover &&
                cabeza->derecha->abajo->tipo == es_vacio)
            {
                cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                cambiar_tipo_nodo(cabeza->derecha->pos, es_vacio, cabeza);
                cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
                num_leopardos_comidos = num_leopardos_comidos + 1;
            }
        }
        if (cabeza->medio->pos == pos_mover &&
            cabeza->medio->tipo == es_vacio)
        {
            cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
            cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
        }
        if (cabeza->medio->abajo != NULL)
        {
            if (cabeza->medio->abajo->pos == pos_mover &&
                cabeza->medio->abajo->tipo == es_vacio)
            {
                cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
            }
        }
        if (cabeza->izquierda->pos == pos_mover &&
            cabeza->izquierda->tipo == es_vacio)
        {
            cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
            cambiar_tipo_nodo(cabeza->izquierda->pos, es_vacio, cabeza);
            cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
            num_leopardos_comidos = num_leopardos_comidos + 1;
        }
        if (cabeza->izquierda->abajo != NULL)
        {
            if (cabeza->izquierda->abajo->pos == pos_mover &&
                cabeza->izquierda->abajo->tipo == es_vacio)
            {
                cambiar_tipo_nodo(pos_mover, es_tigre, cabeza);
                cambiar_tipo_nodo(cabeza->izquierda->pos, es_vacio, cabeza);
                cambiar_tipo_nodo(pos_tigre, es_vacio, cabeza);
                num_leopardos_comidos = num_leopardos_comidos + 1;
            }
        }
    }
};

/*
 *
 * Funcion puede_ganar_jugador
 *
 * Descripcion: Funcion para saber si el jugador puede ganar o no
 * @param cabeza
 * @return int, 1 si gano el de los leopardos, 2 si gano el tigre, 0 si nadie gano
 */
int puede_ganar_jugador(struct cabeza *cabeza)
{
    if (num_leopardos_comidos >= 3)
        return es_tigre;

    // buscar el nodo del tigre
    struct nodo *nodo_tigre = NULL;
    struct nodo *nodo_medio_actual = cabeza->medio;

    while (nodo_medio_actual != NULL)
    {
        if (nodo_medio_actual->izquierda->tipo == es_tigre)
        {
            nodo_tigre = nodo_medio_actual->izquierda;
            break;
        }
        if (nodo_medio_actual->tipo == es_tigre)
        {
            nodo_tigre = nodo_medio_actual;
            break;
        }
        if (nodo_medio_actual->derecha->tipo == es_tigre)
        {
            nodo_tigre = nodo_medio_actual->derecha;
            break;
        }
        nodo_medio_actual = nodo_medio_actual->abajo;
    }
    // validar que el tigre este atrapado, es decir,
    // que no tenga ningun nodo vacio a su alrededor, ni sus vecinos tengan nodos vacios a su alrededor
    // no es la cabeza
    if (nodo_tigre != NULL)
    {
        int lista_pos_adyacentes[8] = {3, 3, 3, 3, 3, 3, 3, 3};
        // [0] = izquierda
        // [1] = izquierda->izquierda
        // [2] = arriba
        // [3] = arriba->arriba
        // [4] = derecha
        // [5] = derecha->derecha
        // [6] = abajo
        // [7] = abajo->abajo
        // optener el tipo de los nodos adyacentes del nodo_tigre

        if (nodo_tigre->izquierda != NULL)
        {
            lista_pos_adyacentes[0] = nodo_tigre->izquierda->tipo;
            if (nodo_tigre->izquierda->izquierda != NULL)
            {
                lista_pos_adyacentes[1] = nodo_tigre->izquierda->izquierda->tipo;
            }
        }
        if (nodo_tigre->arriba != NULL)
        {
            lista_pos_adyacentes[2] = nodo_tigre->arriba->tipo;
            if (nodo_tigre->arriba->arriba != NULL)
            {
                lista_pos_adyacentes[3] = nodo_tigre->arriba->arriba->tipo;
            }
        }
        if (nodo_tigre->derecha != NULL)
        {
            lista_pos_adyacentes[4] = nodo_tigre->derecha->tipo;
            if (nodo_tigre->derecha->derecha != NULL)
            {
                lista_pos_adyacentes[5] = nodo_tigre->derecha->derecha->tipo;
            }
        }
        if (nodo_tigre->abajo != NULL)
        {
            lista_pos_adyacentes[6] = nodo_tigre->abajo->tipo;
            if (nodo_tigre->abajo->abajo != NULL)
            {
                lista_pos_adyacentes[7] = nodo_tigre->abajo->abajo->tipo;
            }
        }

        for (int i = 0; i < 8; i++)
        {
            // 1 3 1 3 1 3 1 1
            if (lista_pos_adyacentes[i] == es_vacio)
            {
                return es_vacio;
            }
        }
        return es_leopardo;
    }
    // tigre en la cabeza y no puede moverse
    if (nodo_tigre == NULL && cabeza->tipo == es_tigre)
    {
        int lista_pos_adyacentes[6] = {3, 3, 3, 3, 3, 3};
        // [0] = izquierda
        // [1] = izquierda->abajo
        // [2] = medio
        // [3] = medio->abajo
        // [4] = derecha
        // [5] = derecha->abajo
        // optener el tipo de los nodos adyacentes del cabeza

        if (cabeza->izquierda != NULL)
        {
            lista_pos_adyacentes[0] = cabeza->izquierda->tipo;
            if (cabeza->izquierda->abajo != NULL)
            {
                lista_pos_adyacentes[1] = cabeza->izquierda->abajo->tipo;
            }
        }
        if (cabeza->medio != NULL)
        {
            lista_pos_adyacentes[2] = cabeza->medio->tipo;
            if (cabeza->medio->abajo != NULL)
            {
                lista_pos_adyacentes[3] = cabeza->medio->abajo->tipo;
            }
        }
        if (cabeza->derecha != NULL)
        {
            lista_pos_adyacentes[4] = cabeza->derecha->tipo;
            if (cabeza->derecha->abajo != NULL)
            {
                lista_pos_adyacentes[5] = cabeza->derecha->abajo->tipo;
            }
        }

        for (int i = 0; i < 6; i++)
        {
            // 1 3 1 3 1 3 1 1
            if (lista_pos_adyacentes[i] == es_vacio)
            {
                return es_vacio;
            }
        }
        return es_leopardo;
    }

    return es_vacio;
}

/*
 *
 * Funcion instrucciones_juego
 *
 * Descripcion: Funcion para mostrar el instrucciones_juego del juego
 * @param void
 * @return void
 */
void instrucciones_juego()
{
    printf("Bienvenid@ al juego Len Choa: Leopards and Tigers\n");
    printf("\n[Instrucciones del juego]\n");
    printf("* Cada jugador tiene un turno para realizar su movimiento\n");
    printf("* El jugador de los leopardos iniciara la partida con su movimiento\n");
    printf("* El jugador del tigre realiza su movimiento posterior al de su oponente\n");
    printf("* El tigre puede saltar sobre un leopardo siempre que el punto en el lado "
           "\n  opuesto del leopardo en la misma linea este libre \n");
    printf("* El jugador del leopardo posee 6 fichas (leopardos) para distribuir en"
           "\n  las casillas del tablero\n");

    printf("\n[Elementos de la interfaz]\n");
    printf("P = Posicion\n");
    printf("T = Tipo\n");
    printf("0 -> Casilla libre\n");
    printf("1 -> Leopardo\n");
    printf("2 -> Tigre\n");
}

/*
 *
 * Funcion main del programa
 *
 * Descripcion: Funcion principal del programa
 * @param void
 * @return 0
 */
int main()
{
    instrucciones_juego();
    printf("\nIngrese el numero de niveles de la piramide: ");
    int niveles = 1;
    scanf("%d", &niveles);
    struct cabeza *cabeza = crear_piramide(niveles);
    while (1)
    {
        printf("leopardos comidos: %d\n", num_leopardos_comidos);
        imprimir_piramide(cabeza);
        colocar_leopardo(cabeza);
        imprimir_piramide(cabeza);
        mover_tigre(cabeza);
        int condicion = puede_ganar_jugador(cabeza);

        if (condicion == es_leopardo)
        {
            printf("Gano el jugador de los leopardos\n");
            break;
        }
        else if (condicion == es_tigre)
        {
            printf("Gano el jugador del tigre\n");
            break;
        }
        // limpiar la consola
        system("cls");
    }

    return 0;
};