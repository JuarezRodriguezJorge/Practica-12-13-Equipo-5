package modelo;

import java.util.Random;

public class Cliente implements Runnable {
    private final int id;
    private final ColaPedidos cola;
    private final ObservadorRestaurante obs;
    private final String platillo;

    public Cliente(int id, String platillo, ColaPedidos cola, ObservadorRestaurante obs) {
        this.id = id;
        this.platillo = platillo;
        this.cola = cola;
        this.obs = obs;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1500)); // llega en distinto momento
            obs.onEvento("Cliente " + id, "llegó y pide: " + platillo);
            cola.agregarPedido(new Pedido(id, platillo));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
