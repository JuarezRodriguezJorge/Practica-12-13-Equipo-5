package modelo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Cola compartida entre hilos (productores y consumidores).
 * Los métodos son synchronized y usan wait/notifyAll para que
 * un consumidor espere sin gastar CPU cuando no hay pedidos.
 */
public class ColaPedidos {
    private final Queue<Pedido> pedidos = new LinkedList<>();
    private boolean cerrada = false;

    public synchronized void agregarPedido(Pedido p) {
        pedidos.add(p);
        notifyAll(); // despierta a los hilos que esperan un pedido
    }

    /** Bloquea hasta que haya un pedido. Regresa null si la cola se cerró y ya está vacía. */
    public synchronized Pedido tomarPedido() throws InterruptedException {
        while (pedidos.isEmpty() && !cerrada) {
            wait();
        }
        return pedidos.poll(); // null si está vacía y cerrada
    }

    /** Se avisa que ya no llegarán más pedidos. */
    public synchronized void cerrar() {
        cerrada = true;
        notifyAll();
    }
}
