package modelo;

/** Pedido que realiza un cliente. Es inmutable, por lo que se puede compartir entre hilos sin riesgo. */
public class Pedido {
    private final int cliente;
    private final String platillo;

    public Pedido(int cliente, String platillo) {
        this.cliente = cliente;
        this.platillo = platillo;
    }

    public int getCliente() { return cliente; }
    public String getPlatillo() { return platillo; }
}
