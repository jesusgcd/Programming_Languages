/*
Programa para generar una lista de números desde 0 hasta 16777215 (0xFFFFFF)
en formato hexadecimal utilizando concurrencia de manera eficiente.
*/
package main

import (
	"fmt"
	"os"
	"strings"
)

// calcNums es una función que calcula y concatena números en formato hexadecimal
// desde 'start' hasta 'end' y los envía al canal 'resultCh'. Luego, señala su
// finalización en el canal 'doneCh'.
func calcNums(start, end int, resultCh chan string, doneCh chan struct{}) {
	var sBuilder strings.Builder
	for i := start; i <= end; i++ {
		fmt.Fprint(&sBuilder, fmt.Sprintf("%06x\n", i))
	}
	resultCh <- sBuilder.String()
	doneCh <- struct{}{}
}

func main() {
	f, err := os.Create("archivo_concurrente_eficiente.txt")
	if err != nil {
		panic(err)
	}

	outCh := make(chan string)       // Canal para enviar datos calculados
	doneWrite := make(chan struct{}) // Canal para indicar que la escritura ha terminado

	// Writer goroutine - goroutine que escribe los datos en el archivo
	go func() {
		for s := range outCh {
			_, err := f.WriteString(s)
			if err != nil {
				panic(err)
			}
		}
		doneWrite <- struct{}{}
	}()

	numGoRutines := 11 // Número de goroutines concurrentes

	doneCh := make(chan struct{}) // Canal para señalar la finalización de las goroutines de cálculo

	final := 16777215 // 0xFFFFFF - Valor final de los cálculos
	for i := 0; i <= final; i = i + (final / numGoRutines) + 1 {
		paso := i + (final / numGoRutines)
		if paso > final {
			paso = final
		}
		fmt.Printf("Ejecutando %d %d\n", i, paso)
		go calcNums(i, paso, outCh, doneCh) // Inicia goroutines de cálculo concurrentes
	}

	doneNum := 0
	for doneNum < numGoRutines {
		<-doneCh
		fmt.Println("[Process done]")
		doneNum++
	}
	close(outCh) // Cierra el canal de salida después de que todas las goroutines hayan terminado
	<-doneWrite
	fmt.Println("-> All processes done")
}
