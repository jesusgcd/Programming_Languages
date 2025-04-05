/*
Programa para generar una lista de números desde 0 hasta 16777215 (0xFFFFFF)
en formato hexadecimal utilizando concurrencia de manera ineficiente.
*/

package main

import (
	"fmt"
	"os"
	"sync"
)

// La función calcNums realiza la escritura de números hexadecimales en un archivo entre los valores 'start' y 'end'.
// Utiliza un canal 'wg' para indicar que ha terminado su ejecución.
func calcNums(start, end int, f *os.File, wg *sync.WaitGroup) {
	defer wg.Done() // Avisa al WaitGroup que esta goroutine ha terminado al salir de la función.

	for i := start; i <= end; i++ { // Ajusta el límite superior para incluir 'end'.
		_, err := f.WriteString(fmt.Sprintf("%06x\n", i)) // Escribe un número hexadecimal en el archivo.
		if err != nil {
			panic(err) // En caso de error, se detiene el programa y muestra el error.
		}
	}
}

func main() {
	// Abre un archivo llamado "archivo_concurrente_ineficiente.txt" para escritura.
	f, err := os.Create("archivo_concurrente_ineficiente.txt")
	if err != nil {
		panic(err) // Si no se puede abrir el archivo, se detiene el programa y muestra el error.
	}
	defer f.Close() // Asegura que el archivo se cierre al salir de la función main.

	numGoRoutines := 10 // Número de goroutines (hilos de ejecución) que se crearán.
	final := 0xFFFFFF   // Valor final hasta el cual se generarán números hexadecimales.

	// Crea un WaitGroup para esperar a que todas las goroutines terminen.
	var wg sync.WaitGroup

	// Divide el trabajo en varias goroutines, cada una escribirá un rango de números hexadecimales en el archivo.
	step := final / numGoRoutines
	for i := 0; i <= final; i += step + 1 { // Ajusta la condición para incluir 'final'.
		end := i + step
		if end > final {
			end = final
		}
		fmt.Printf("Ejecutando %x %x\n", i, end)

		// Incrementa el WaitGroup para cada goroutine que se inicia.
		wg.Add(1)

		// Inicia una goroutine para realizar el cálculo y escritura concurrente.
		go calcNums(i, end, f, &wg)
	}

	// Espera a que todas las goroutines terminen.
	wg.Wait()

	fmt.Println("-> Todos los procesos han terminado")
}
