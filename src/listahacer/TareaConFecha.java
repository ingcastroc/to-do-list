
package listahacer;

import java.time.LocalDate;
public class TareaConFecha {
    String titulo;
    String descripcion;
    LocalDate fecha;

    public TareaConFecha(String titulo, String descripcion, LocalDate fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return titulo + " - " + descripcion + " - " + fecha;
    }
}
