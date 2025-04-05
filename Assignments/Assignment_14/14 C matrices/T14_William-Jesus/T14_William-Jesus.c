/*
 * Instituto Tecnologico de Costa Rica
 *
 * Escuela de Ingenieria en Computacion
 *
 * Lenguajes de Programacion
 *
 * Tarea 14 - C matrices
 *
 * William Alfaro Quiros - 2022437996
 *
 * Jesus Cordero Diaz - 2020081049
 */


#include <stdio.h>

// Estas son constantes, se cambian para modelar el codigo

#define ROW 2
#define COL 2

// Función para leer una matriz del usuario
void readMatrix(int matrix[ROW][COL], char matrixName) {
    printf("Ingrese los elementos de la matriz %c (%dx%d):\n", matrixName, ROW, COL);
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            scanf("%d", &matrix[i][j]);
        }
    }
}

// Función para mostrar una matriz
void showMatrix(int matrix[ROW][COL], char matrixName) {
    printf("Matriz %c (%dx%d):\n", matrixName, ROW, COL);
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}

// Función para multiplicar dos matrices
void multiply2Matrixs(int matrixA[ROW][COL], int matrixB[ROW][COL]) {
    int result[ROW][COL];

    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            result[i][j] = 0;
            for (int k = 0; k < COL; k++) {
                result[i][j] += matrixA[i][k] * matrixB[k][j];
            }
        }
    }

    printf("Resultado de la multiplicacion de las matrices:\n");
    showMatrix(result, 'C');
}

// Función para evaluar la propiedad de matrices transpuestas
void evaluateProperty(int matrixA[ROW][COL], int matrixB[ROW][COL]) {
    // Verificar si las matrices tienen las dimensiones apropiadas para la multiplicación
    if (COL != ROW) {
        printf("No se pueden multiplicar las matrices. Las dimensiones no son apropiadas.\n");
        return;
    }

    // Calcular A^T
    int transpuestaA[COL][ROW];
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            transpuestaA[j][i] = matrixA[i][j];
        }
    }

    // Calcular B^T
    int transpuestaB[COL][ROW];
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            transpuestaB[j][i] = matrixB[i][j];
        }
    }

    // Calcular (AB)
    int temp_result1[COL][ROW];
    for (int i = 0; i < COL; i++) {
        for (int j = 0; j < ROW; j++) {
            temp_result1[i][j] = 0;
            for (int k = 0; k < ROW; k++) {
                temp_result1[i][j] += matrixA[i][k] * matrixB[k][j];
            }
        }
    }

    // Calcular (AB)^T
    int result1[ROW][COL];
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            result1[j][i] = temp_result1[i][j];
        }
    }
    

    // Calcular B^T x A^T
    int result2[ROW][COL];
    for (int i = 0; i < COL; i++) {
        for (int j = 0; j < ROW; j++) {
            result2[i][j] = 0;
            for (int k = 0; k < COL; k++) {
                result2[i][j] += transpuestaB[i][k] * transpuestaA[k][j];
            }
        }
    }

    // Comparar (AB)^T con B^T x A^T
    int equal = 1;
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            if (result1[i][j] != result2[i][j]) {
                equal = 0;
                break;
            }
        }
    }

    printf("Matriz (AB)^T:\n");
    showMatrix(result1, 'C');

    printf("Matriz B^T x A^T:\n");
    showMatrix(result2, 'D');

    if (equal) {
        printf("-> La propiedad de matrices transpuestas se cumple.\n");
    } else {
        printf("-> La propiedad de matrices transpuestas no se cumple.\n");
    }
}




int main() {
    printf("Bienvenid@ al programa de matrices!\n");
    
    int matrixA[ROW][COL];
    int matrixB[ROW][COL];
    
    readMatrix(matrixA, 'A');
    readMatrix(matrixB, 'B');
  
    showMatrix(matrixA, 'A');
    showMatrix(matrixB, 'B');

    multiply2Matrixs(matrixA, matrixB);
    
    evaluateProperty(matrixA, matrixB);

    return 0;
}

/*
 * @Tests
 *
 * Ingrese los elementos de la matriz A (2x2):
 * 1 2
 * 3 4
 *
 * Ingrese los elementos de la matriz B (2x2):
 * 5 6
 * 7 8
 *
 * [Output]
 *
 *  Matriz A (2x2):
    1 2
    3 4
    Matriz B (2x2):
    5 6
    7 8
    Resultado de la multiplicacion de las matrices:
    Matriz C (2x2):
    19 22
    43 50
    Matriz A por B transpuesta:
    Matriz C (2x2):
    17 23
    39 53
    Matriz B transpuesta por A transpuesta:
    Matriz D (2x2):
    17 39
    23 53
    -> La propiedad de matrices transpuestas no se cumple.
 */