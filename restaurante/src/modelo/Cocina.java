package modelo;

import java.util.Random;

/** Prepara los pedidos que le llegan de los meseros. */
public class Cocina implements Runnable {
    private final String nombre;
    private final ColaPedidos colaCocina;
    private final ObservadorRestaurante obs;

    public Cocina(String nombre, ColaPedidos colaCocina, ObservadorRestaurante obs) {
        this.nombre = nombre;
        this.colaCocina = colaCocina;
        this.obs = obs;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            Pedido p;
            while ((p = colaCocina.tomarPedido()) != null) {
                obs.onEvento(nombre, "prepara " + p.getPlatillo() + " (cliente " + p.getCliente() + ")");
                Thread.sleep(500 + rand.nextInt(800));
                obs.onEvento(nombre, "TERMINÓ " + p.getPlatillo() + " -> entregado al cliente " + p.getCliente());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
