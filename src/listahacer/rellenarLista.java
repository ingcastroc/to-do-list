
package listahacer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class rellenarLista {
    public listaEnlazada lista;
    public listaEnlazada lector(String fechaArchivo) {
        listaEnlazada lista = new listaEnlazada();

        try (BufferedReader br = new BufferedReader(new FileReader(fechaArchivo))) {
            String linea;
            String titulo = "", descripcion = "";

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Título:")) {
                    titulo = linea.substring(7).trim();
                } else if (linea.startsWith("Descripción:")) {
                    descripcion = linea.substring(12).trim();
                } 
                 else if (linea.startsWith("---")) {
                    lista.agregarTarea(titulo, descripcion);
                    titulo = "";
                    descripcion = "";
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista; 
    }
}
