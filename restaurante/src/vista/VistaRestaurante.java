package vista;

/** Vista: solo sabe mostrar. synchronized evita que las líneas de distintos hilos se mezclen. */
public class VistaRestaurante {
    public synchronized void mostrar(String texto) {
        System.out.println(texto);
    }
}
