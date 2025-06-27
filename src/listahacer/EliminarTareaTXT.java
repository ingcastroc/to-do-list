
package listahacer;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EliminarTareaTXT {
    public static void eliminarPorTitulo(String tituloABorrar) {
        String nombreArchivo = obtenerNombreArchivoDelDia();
        File archivoOriginal = new File(nombreArchivo);
        File archivoTemporal = new File("temp.txt");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;
            boolean borrar = false;

            while ((linea = lector.readLine()) != null) {
                if (linea.startsWith("Título:")) {
                    String tituloActual = linea.substring(7).trim();
                    borrar = tituloActual.equals(tituloABorrar);
                }

                if (!borrar) {
                    escritor.write(linea);
                    escritor.newLine();
                }

                if (linea.startsWith("---")) {
                    borrar = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // Reemplazar el archivo original con el temporal
        if (archivoOriginal.delete()) {
            archivoTemporal.renameTo(archivoOriginal);
            System.out.println(" Tarea eliminada del archivo con éxito.");
        } else {
            System.out.println(" No se pudo eliminar el archivo original.");
        }
    }

    private static String obtenerNombreArchivoDelDia() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", new Locale("es", "ES"));
        return fechaActual.format(formato) + ".txt";
    }
}
