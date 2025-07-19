
package listahacer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
public class PersistenciaHeap {
    public static void guardarTareasHeap(ArrayList<TareaConFecha> tareas) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");

        for (TareaConFecha tarea : tareas) {
            LocalDate fecha = tarea.getFecha();
            String nombreArchivo = fecha.format(formato) + "_heap.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
                writer.write("Título: " + tarea.titulo);
                writer.newLine();
                writer.write("Descripción: " + tarea.descripcion);
                writer.newLine();
                writer.write("------------------------------------------");
                writer.newLine();
                System.out.println("Tarea guardada en archivo: " + nombreArchivo);
            } catch (IOException e) {
                System.err.println("Error al guardar tarea: " + e.getMessage());
            }
        }
    }

   
    public static void guardarTareaIndividual(TareaConFecha tarea) {
        ArrayList<TareaConFecha> lista = new ArrayList<>();
        lista.add(tarea);
        guardarTareasHeap(lista);
    }
    public static void reconstruirHeapDesdeArchivos(MinHeap heap) {
        File directorioActual = new File(".");  // Directorio del proyecto

        File[] archivos = directorioActual.listFiles((dir, name) ->
            name.endsWith("_heap.txt") // Solo archivos del heap
        );

        if (archivos == null) return;

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", new Locale("es"));

        for (File archivo : archivos) {
            try {
                String nombreArchivo = archivo.getName().replace("_heap.txt", ""); // Solo la parte de la fecha
                LocalDate fecha = LocalDate.parse(nombreArchivo, formato); // Parseamos la fecha

                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                String linea;
                String titulo = null, descripcion = null;

                while ((linea = reader.readLine()) != null) {
                    if (linea.startsWith("Título: ")) {
                        titulo = linea.substring(8).trim();
                    } else if (linea.startsWith("Descripción: ")) {
                        descripcion = linea.substring(13).trim();
                    }

                    if (titulo != null && descripcion != null) {
                        TareaConFecha tarea = new TareaConFecha(titulo, descripcion, fecha);
                        heap.insertar(tarea);
                        titulo = null;
                        descripcion = null;
                    }
                }

                reader.close();
            } catch (Exception e) {
                System.err.println("Error leyendo archivo " + archivo.getName() + ": " + e.getMessage());
            }
        }
    }
}
