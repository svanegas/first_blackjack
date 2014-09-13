package blackjack;

/**
 * Esta clase representa un jugador, 
 * Será usada para almacenar las cartas de cada uno, incluyendo el croupier.
 * @author Santiago Vanegas
 */
public class Mano
{
    private Carta [] mano;
    /**
     * Este atributo indica si el jugador está eliminado de la ronda.
     */
    private boolean volo;
    /**
     * Este atributo indica si el jugador está plantado.
     */
    private boolean plantado;
    public int puntaje;
    /**
     * Este atributo indica el número de ases que posee el jugador en su mano.
     */
    private int ases;
    /**
     * Este atributo indica si el jugador obtuvo un blackjack en la ronda.
     */
    private boolean blackJack;

    /**
     * Método constructor, 
     * creará una instancia de Mano, que inicialmente estará vacía, 
     * cada mano contendrá una capacidad de 10 cartas.
     */
    public Mano()
    {
        mano = new Carta [10];
        volo = false;
        puntaje = 0;
        ases = 0;
        blackJack = false;
    }
    
    /**
     * Método para recibir una carta y organizarla en la mano,
     * la carta se organizará automáticamente de acuerdo al espacio disponible.
     * @param carta Carta a recibir.
     */
    public void recibirCarta(Carta carta) {
        boolean recibido = false;
        for(int i=0; i<10; i++) {
            if(mano[i]==null){
                mano[i] = carta;
                if(carta.getValor()==11){
                    this.ases++;
                }
                recibido = true;
                break;
            }
        }
        if(!recibido) {
            System.out.println("-- La mano está llena, no puede recibir más cartas --");
        }
    }
    
    /**
     * Método para saber qué carta hay en una posición solicitada de la mano
     * @param pos Posición solicitada.
     * @return Carta en la posición solicitada, en caso de que no haya carta retorna null.
     */
    public Carta saberCarta(int pos) {
        if(pos>=0 && pos<=10) {
            return mano[pos];
        }
        //System.out.println("-- La posicion requerida no existe --");
        return null;
    }
    
    /**
     * Método para remover todas las cartas de la mano, 
     * el método se utilizará cuando se desea iniciar una nueva ronda.
     */
    public void vaciarMano() {
        for(int i = 0; i<10; i++) {
            mano[i] = null;
        }
    }
    
    /**
     * Método setter para el atributo voló,
     * modificará si el jugador está eliminado o no.
     * @param v Nuevo valor de voló.
     */
    public void setVolo(boolean v){
        volo = v;
    }
    
    /**
     * Método setter para el atributo plantado, 
     * modificará si el jugador está plantado o no.
     * @param v Nuevo valor de plantado.
     */
    public void setPlantado(boolean v){
        plantado = v;
    }
    
    /**
     * Método setter para el el puntaje del jugador, 
     * sumará los puntos que se le ingresan por parámetro al puntaje total del jugador.
     * @param x Puntos que serán sumados al puntaje actual.
     */
    public void setPuntaje(int x) {
        puntaje += x;
    }
    
    /**
     * Método setter para el atributo blackJack, 
     * modificará si el jugador obtuvo blackjack en la ronda.
     * @param b Nuevo valor del atributo.
     */
    public void setBlackJack(boolean b) {
        blackJack = b;
    }

    /**
     * Método setter para el atributo ases, 
     * modificará la cantidad de ases que posee la mano.
     * @param ases Nueva cantidad de ases.
     */
    public void setAses(int ases) {
        this.ases = ases;
    }

    
    /**
     * Método para obtener la suma del valor de las cartas de la mano,
     * en caso de poseer ases, se obtendrá la suma máxima que pueda obtener el jugador, sin exceder 21 puntos.
     * @return Valor de la mano
     */
    public int getSuma() {
        int valor = 0;
        for(int i=0; i<10; i++) {
            Carta carta = saberCarta(i);
            if(carta != null) {
                valor += carta.getValor();
            }
            else break;
        }
        int totAses = this.ases;
        while(totAses>0 && valor>21) {
            valor -= 10;
            totAses--;
        }
        return valor;
    }
    
    /**
     * Método getter para el atributo voló,
     * a partir de éste se conocerá si el jugador está eliminado o no.
     * @return Valor de voló
     */
    public boolean getVolo(){
        return volo;
    }
    
    /**
     * Método getter para el atributo plantado, 
     * a partir de éste se conocerá si el jugador está plantado o no.
     * @return Valor de plantado
     */
    public boolean getPlantado() {
        return plantado;
    }
    
    /**
     * Método getter para el puntaje, 
     * a partir de éste se conocerá el puntaje total del jugador.
     * @return Puntaje del jugador
     */
    public int getPuntaje() {
        return puntaje;
    }
    
    /**
     * Método getter para el atributo blackJack, 
     * a partir de éste se conocerá si el jugador obtuvo blackjack.
     * @return Valor de blackJack
     */
    public boolean getBlackJack() {
        return blackJack;
    }
}
