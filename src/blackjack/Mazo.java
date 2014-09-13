
package blackjack;

/**
 * Esta clase representa el mazo de cada juego, 
 * Contiene máximo 52 instanciaciones de la clase Carta,
 * @author Santiago Vanegas
 */
public class Mazo
{
  
    private Carta [] mazo;
    private boolean repartido;

    /**
     * Método constructor, 
     * creará una instancia de Mazo, creará 52 instanciaciones de Carta y las organizará aleatoriamente en el mazo.
     */
    public Mazo()
    {
        mazo = new Carta [52];
        revolverMazo();
    }
    
    /**
     * Método que instancia cada una de las diferentes cartas y las organiza aleatoriamente en el mazo.
     */
    public void revolverMazo() {
        for(int i = 0; i<52; i++) {
            mazo[i] = null;
        }
        for(int i=1; i<=4; i++) {
            for(int j=0; j<13; j++) {
                Carta temp = new Carta("","",0);
                if(i==1){
                    temp.setPinta("Pica");
                    temp.setEspec(temp.obtenerEspec(j));
                    temp.setValor(temp.getEspec());
                    temp.setImagen();
                    ordenarEnMazo(temp);
                }
                else if(i==2){
                    temp.setPinta("Corazón");
                    temp.setEspec(temp.obtenerEspec(j));
                    temp.setValor(temp.getEspec());
                    temp.setImagen();
                    ordenarEnMazo(temp);
                } 
                else if(i==3){
                    temp.setPinta("Diamante");
                    temp.setEspec(temp.obtenerEspec(j));
                    temp.setValor(temp.getEspec());
                    temp.setImagen();
                    ordenarEnMazo(temp);
                }
                else {
                    temp.setPinta("Trébol");
                    temp.setEspec(temp.obtenerEspec(j));
                    temp.setValor(temp.getEspec());
                    temp.setImagen();
                    ordenarEnMazo(temp);
                }
            }
        }
    }
    
    /**
     * Método que genera un número aleatorio, comprueba si en esa posición de mazo hay una carta,
     * almacenará la carta en caso de que esté vació el espacio, de lo contrario generará otro número y hará el mismo procedimiento.
     * @param carta Carta que va a ser organizada en el mazo
     */
    public void ordenarEnMazo(Carta carta){
        for(int i=0; i<1; i++) {
            int pos = (int) Math.round(Math.random()*51);
            if(mazo[pos]==null){
                mazo[pos] = carta;
            }
            else {
                i--;
            }
        }
    }
    
    /**
     * Método para obtener la última carta del mazo, 
     * realiza un recorrido inverso del mazo hasta que encuentre una carta.
     * @return Última carta del mazo
     */
    public Carta ultimaCarta() {
        for(int i = 51; i>=0; i--) {
            if(mazo[i]!=null) {
                Carta temp = mazo[i];
                mazo[i] = null;
                return temp;
            }
        }
        System.out.println("¡No hay cartas en el mazo!");
        return null;
    }
    
    /**
     * Método setter para repartido
     * @param v Nuevo valor de repartido
     */
    public void setRepartido(boolean v) {
        repartido = v;
    }
    
    /**
     * Método getter para repartido
     * Saber si el mazo ya se repartió o no
     * @return Valor de repartido
     */
    public boolean getRepartido() {
        return repartido;
    }
    
    /**
     * Método para conocer la carta en una posición requerida
     * @param x Posición requerida
     * @return Carta en la posición requerida, si no hay ninguna carta en dicha posición retorna null.
     */
    public Carta cartaEnPos(int x) {
        if(x>51 && x<0) {
            return null;
        }
        return mazo[x];
    }
}
