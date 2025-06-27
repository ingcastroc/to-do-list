
package listahacer;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
public class persistenciaDatos {
    public static void crearArchivoTarea(String titulo, String descripcion, int prioridad) {
        
        
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        String fecha= fechaActual.format(formato);
        String nombreArchivo = fecha + ".txt";

  
        String prioridadTexto = switch (prioridad) {
            case 1 -> "Leve";
            case 2 -> "Media";
            case 3 -> "Alta";
            default -> "Desconocida";
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo,true))) {
            writer.write("Título: " + titulo);
            writer.newLine();
            writer.write("Descripción: " + descripcion);
            writer.newLine();
            writer.write("Prioridad: " + prioridadTexto);
            writer.newLine();
            writer.write("------------------------------------------\n");
            System.out.println("Archivo creado: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}
