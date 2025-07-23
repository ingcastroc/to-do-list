package listahacer;

import java.util.ArrayList;

public class listaEnlazada {
    private NodoTarea cabeza;

    private class NodoTarea {
        String titulo;
        String descripcion;
        NodoTarea siguiente;

        public NodoTarea(String titulo, String descripcion) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.siguiente = null;
        }
    }

    public listaEnlazada() {
        cabeza = null;
    }

    public void agregarTareaAlInicio(String titulo, String descripcion) {
        NodoTarea nueva = new NodoTarea(titulo, descripcion);
        nueva.siguiente = cabeza;
        cabeza = nueva;
    }

    public void agregarTarea(String titulo, String descripcion) {
        NodoTarea nueva = new NodoTarea(titulo, descripcion);
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
            System.out.println("-----");
            actual = actual.siguiente;
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
            lista.add(actual.titulo);
            actual = actual.siguiente;
        }

        return lista;
    }

}