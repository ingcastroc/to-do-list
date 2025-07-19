
package listahacer;

import java.time.LocalDate;
import java.util.ArrayList;


public class MinHeap {
    private TareaConFecha[] heap;
    private int size;
    private int capacidad;

    public MinHeap(int capacidad) {
        this.capacidad = capacidad;
        this.size = 0;
        this.heap = new TareaConFecha[capacidad];
    }

    private int padre(int i) { return (i - 1) / 2; }
    private int izq(int i) { return 2 * i + 1; }
    private int der(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        TareaConFecha temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insertar(TareaConFecha tarea) {
        if (size == capacidad) {
            System.out.println("Heap lleno");
            return;
        }

        heap[size] = tarea;
        int i = size;
        size++;

        // Sube mientras la fecha padre sea mayor (más lejana)
        while (i != 0 && heap[padre(i)].getFecha().isAfter(heap[i].getFecha())) {
            swap(i, padre(i));
            i = padre(i);
        }
    }

    public TareaConFecha extraerMinimo() {
        if (size <= 0) return null;
        if (size == 1) return heap[--size];

        TareaConFecha raiz = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return raiz;
    }

    private void heapify(int i) {
        int menor = i;
        int izquierda = izq(i);
        int derecha = der(i);

        if (izquierda < size && heap[izquierda].getFecha().isBefore(heap[menor].getFecha()))
            menor = izquierda;

        if (derecha < size && heap[derecha].getFecha().isBefore(heap[menor].getFecha()))
            menor = derecha;

        if (menor != i) {
            swap(i, menor);
            heapify(menor);
        }
    }

    public void imprimirHeap() {
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }
    }
    public ArrayList<TareaConFecha> buscarPorFecha(LocalDate fecha) {
    ArrayList<TareaConFecha> resultado = new ArrayList<>();
    for (int i = 0; i < size; i++) {
        if (heap[i].getFecha().isEqual(fecha)) {
            resultado.add(heap[i]);
        }
    }
    return resultado;
}
    public boolean estaVacio() {
        return size == 0;
    }
}
