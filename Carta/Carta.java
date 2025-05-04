package Carta;

public abstract class Carta {
    private boolean estaVolteada;

    public void voltearCarta(){
        estaVolteada = true;
    }
    public abstract boolean cartaEsIgualAOtraCarta(Carta carta);
}
