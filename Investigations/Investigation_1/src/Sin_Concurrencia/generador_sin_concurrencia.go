/*
	Programa para generar una lista de numeros desde el 0 hasta el 16777216 (0xFFFFFF)
	en formato hexadecimal sin utilizar concurrencia
*/

package main

import (
	"fmt"
	"os"
)

func main() {
	// Crear un archivo llamado "archivo.txt"
	f, err := os.Create("archivo.txt")
	if err != nil {
		// Si ocurre un error al crear el archivo, el programa se bloqueará y mostrará el error.
		panic(err)
	}

	// Definir el valor final para la secuencia de números hexadecimales
	final := 16777216

	// Loop que genera números hexadecimales y los escribe en el archivo
	for i := 0; i < final; i++ {
		// Convertir el número decimal 'i' en una cadena hexadecimal con formato "XXXXXX\n"
		hexStr := fmt.Sprintf("%06x\n", i)

		// Escribir la cadena hexadecimal en el archivo
		_, err = f.WriteString(hexStr)
		if err != nil {
			// Si ocurre un error al escribir en el archivo, el programa se bloqueará y mostrará el error.
			panic(err)
		}
	}
}
