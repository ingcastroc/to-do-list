
package listahacer;

import java.util.ArrayList;

public class listaEnlazada {
     private NodoTarea cabeza;
     private class NodoTarea {
        String titulo;
        String descripcion;
        int prioridad;
        NodoTarea siguiente;

        public NodoTarea(String titulo, String descripcion, int prioridad) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.prioridad = prioridad;
            this.siguiente = null;
        }
    }
    public listaEnlazada() {
        cabeza = null;
    }
        public void agregarTareaAlInicio(String titulo, String descripcion, int prioridad) {
        NodoTarea nueva = new NodoTarea(titulo, descripcion, prioridad);
        nueva.siguiente = cabeza; 
        cabeza = nueva;           
    }
    public void agregarTarea(String titulo, String descripcion, int prioridad) {
        NodoTarea nueva = new NodoTarea(titulo, descripcion, prioridad);
        if (cabeza == null) {
            cabeza = nueva;
        } else {
            NodoTarea actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nueva;
        }
    }
    public void mostrarTareas() {
        NodoTarea actual = cabeza;
        while (actual != null) {
            System.out.println("Título: " + actual.titulo);
            System.out.println("Descripción: " + actual.descripcion);
            System.out.println("Prioridad: " + actual.prioridad);
            System.out.println("-----");
            actual = actual.siguiente;
        }
    }
    private String prioridadTexto(int p) {
        switch (p) {
            case 1: return "Leve";
            case 2: return "Media";
            case 3: return "Alta";
            default: return "Desconocida";
        }
    }
    public String obtenerDescripcionPorTitulo(String tituloBuscado) {
    NodoTarea actual = cabeza;
    while (actual != null) {
        if (actual.titulo.equals(tituloBuscado)) {
            return actual.descripcion;
        }
        actual = actual.siguiente;
    }
    return null;
}
    public void eliminarTarea(String titulo) {
    if (cabeza == null) return;

    if (cabeza.titulo.equals(titulo)) {
        cabeza = cabeza.siguiente;
        return;
    }

    NodoTarea anterior = cabeza;
    NodoTarea actual = cabeza.siguiente;

    while (actual != null) {
        if (actual.titulo.equals(titulo)) {
            anterior.siguiente = actual.siguiente;
            return;
        }
        anterior = actual;
        actual = actual.siguiente;
    }
}
    public ArrayList<String> obtenerTareasComoTexto() {
    ArrayList<String> lista = new ArrayList<>();
    NodoTarea actual = cabeza;
        
    while (actual != null) {
        String tareaTexto = actual.titulo ;
        lista.add(tareaTexto);

        actual = actual.siguiente;
    }

    return lista;
}
    
}
