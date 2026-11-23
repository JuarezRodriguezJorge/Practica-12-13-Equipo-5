package modelo;

import java.util.Random;

/** Toma pedidos de los clientes y los pasa a la cocina. */
public class Mesero implements Runnable {
    private final String nombre;
    private final ColaPedidos colaClientes;
    private final ColaPedidos colaCocina;
    private final ObservadorRestaurante obs;

    public Mesero(String nombre, ColaPedidos colaClientes, ColaPedidos colaCocina, ObservadorRestaurante obs) {
        this.nombre = nombre;
        this.colaClientes = colaClientes;
        this.colaCocina = colaCocina;
        this.obs = obs;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            Pedido p;
            while ((p = colaClientes.tomarPedido()) != null) {
                obs.onEvento(nombre, "atiende al cliente " + p.getCliente());
                Thread.sleep(200 + rand.nextInt(500));
                colaCocina.agregarPedido(p);
                obs.onEvento(nombre, "envió a cocina " + p.getPlatillo() + " (cliente " + p.getCliente() + ")");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
