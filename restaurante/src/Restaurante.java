import controlador.RestauranteControlador;
import vista.VistaRestaurante;

public class Restaurante {
    public static void main(String[] args) throws InterruptedException {
        RestauranteControlador c = new RestauranteControlador(new VistaRestaurante());
        c.abrirRestaurante(6, 2); // 6 clientes, 2 meseros, 1 cocina
    }
}
