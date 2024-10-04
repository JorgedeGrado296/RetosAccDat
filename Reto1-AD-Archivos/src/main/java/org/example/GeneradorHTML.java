package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GeneradorHTML {

    private String templatePath;
    private String csvPath;

    public GeneradorHTML(String templatePath, String csvPath) {
        this.templatePath = templatePath;
        this.csvPath = csvPath;
    }

    public void generarArchivosHTML() {
        // Limpiar la carpeta de salida
        File carpetaSalida = new File("salida");
        if (carpetaSalida.exists()) {
            for (File file : carpetaSalida.listFiles()) {
                file.delete();
            }
        } else {
            carpetaSalida.mkdir();
        }

        // Leer plantilla HTML
        String plantillaHTML = "";
        try {
            plantillaHTML = new String(Files.readAllBytes(Paths.get(templatePath)));
        } catch (IOException e) {
            System.err.println("Error al leer la plantilla HTML: " + e.getMessage());
            return;
        }

        // Leer CSV y generar archivos HTML
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length != 5) {
                    System.err.println("Error en la línea del CSV: " + linea);
                    continue;
                }

                // Reemplazar los marcadores en la plantilla
                String htmlGenerado = plantillaHTML
                        .replace("%%1%%", campos[0])
                        .replace("%%2%%", campos[1])
                        .replace("%%3%%", campos[2])
                        .replace("%%4%%", campos[3])
                        .replace("%%5%%", campos[4]);

                // Crear archivo HTML
                String nombreArchivo = "salida/" + campos[1] + " - " + campos[0] + ".html";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
                    bw.write(htmlGenerado);
                } catch (IOException e) {
                    System.err.println("Error al escribir el archivo HTML: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}

