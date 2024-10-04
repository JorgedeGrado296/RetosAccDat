package org.example;

public class Main {
    public static void main(String[] args) {
        // Rutas de los archivos (ajusta según la ubicación real de los archivos)
        String templatePath = "src/main/resources/template.html";
        String csvPath = "src/main/resources/peliculas.csv";

        // Crear instancia de GeneradorHTML y generar archivos
        GeneradorHTML generador = new GeneradorHTML(templatePath, csvPath);
        generador.generarArchivosHTML();
    }
}
