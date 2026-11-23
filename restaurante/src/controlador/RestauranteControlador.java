package controlador;

import modelo.*;
import vista.VistaRestaurante;
import java.util.ArrayList;
import java.util.List;

/** Controlador: crea el modelo, lanza los hilos, recibe eventos del modelo y actualiza la vista. */
public class RestauranteControlador implements ObservadorRestaurante {
    private final VistaRestaurante vista;
    private final ColaPedidos colaClientes = new ColaPedidos();
    private final ColaPedidos colaCocina = new ColaPedidos();

    public RestauranteControlador(VistaRestaurante vista) {
        this.vista = vista;
    }

    @Override
    public void onEvento(String actor, String mensaje) {
        vista.mostrar("[" + actor + "] " + mensaje);
    }

    public void abrirRestaurante(int numClientes, int numMeseros) throws InterruptedException {
        String[] menu = {"Tacos", "Pizza", "Hamburguesa", "Pasta", "Ensalada", "Sopa"};
        List<Thread> clientes = new ArrayList<>();
        List<Thread> meseros = new ArrayList<>();

        vista.mostrar("=== El restaurante abre ===");

        Thread cocina = new Thread(new Cocina("Cocina", colaCocina, this));
        cocina.start();
        for (int i = 1; i <= numMeseros; i++) {
            Thread t = new Thread(new Mesero("Mesero " + i, colaClientes, colaCocina, this));
            meseros.add(t);
            t.start();
        }
        for (int i = 1; i <= numClientes; i++) {
            Thread t = new Thread(new Cliente(i, menu[(i - 1) % menu.length], colaClientes, this));
            clientes.add(t);
            t.start();
        }

        // Cierre ordenado: primero ya no llegan clientes, luego meseros, al final la cocina.
        for (Thread t : clientes) t.join();
        colaClientes.cerrar();
        for (Thread t : meseros) t.join();
        colaCocina.cerrar();
        cocina.join();

        vista.mostrar("El restaurante cierra");
    }
}
