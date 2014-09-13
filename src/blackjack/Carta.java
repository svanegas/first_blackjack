
package blackjack;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Esta clase representa las cartas del BlackJack, 
 * cada carta posee una pinta, especificación y un valor, 
 * no puede existir más de una carta con los mismos atributos.
 * @author Santiago Vanegas
 */
public class Carta
{
    private String pinta;
    private String especificacion;
    private int valor;
    private ImageIcon imagen;
    
    
    /**
     * Método constructor
     * Creará una instancia de Carta con unos atributos iniciales,
     * estos atributos podrán ser modificados más adelante.
     * @param pinta Pinta de la carta
     * @param especificacion Especificación de la carta 
     * @param valor Valor de la carta
     */
    public Carta(String pinta, String especificacion, int valor)
    {
        this.pinta = pinta.toLowerCase();
        this.especificacion = especificacion.toUpperCase();
        this.valor = valor;
        setImagen();
    }
    
    /**
     * Método setter para la pinta, 
     * modificará la pinta de la carta.
     * @param pinta Nueva pinta de la carta
     */
    public void setPinta(String pinta) {
        this.pinta = pinta;
    }
    
    /**
     * Método setter para la especificación, 
     * modificará la especificación de la carta.
     * @param espec Nueva especificación de la carta
     */
    public void setEspec(String espec) {
        especificacion = espec;
    }
    
    /**
     * Método setter para el valor,
     * modificará el valor de la carta a partir de la especificación recibida, 
     * si se recibe un valor numérico se establecerá este,
     * si es un A el valor de la carta será 11,
     * si es una figura (J, Q, K) el valor de la carta será 10.
     * @param s Especificación que pasa a ser convertida en valor
     */
    public void setValor(String s) {
        if(!s.equals("10") && Character.isDigit(s.charAt(0))) {
            int x = Integer.parseInt(s);
            valor = x;
        }
        else if(s.toUpperCase().equals("A")){
            valor = 11;
        }
        else {
            valor = 10;
        }
    }
    
    /**
     * Método para definir una especificación dependiendo de una posición en un arreglo,
     * este método será usado únicamente por el constructor de la clase mazo.
     * @param n Posición solicitada
     * @return String con la especificación que se solicitó
     */
    public String obtenerEspec(int n) {
        String espec [] = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        return espec[n];
    } 
    
    /**
     * Método getter para la pinta,
     * a partir de éste se conocerá la pinta actual de la carta.
     * @return Pinta de la carta
     */
    public String getPinta() {
        return pinta;
    }
    
    /**
     * Método getter para la especificación, 
     * a partir de éste se conocerá la especificación actual de la carta.
     * @return Especificación de la carta
     */
    public String getEspec() {
        return especificacion;
    }
    
    /**
     * Método getter para el valor, 
     * a partir de éste se conocerá el valor actual de la carta.
     * @return Valor de la carta
     */
    public int getValor() {
        return valor;
    }

    /**
     * Método setter para la imagen de la carta,
     * se buscará la imagen en el directorio especificado según el nombre de su pinta y su especificación.
     */
    public void setImagen() {
        String nombreImagen = this.getPinta().toLowerCase()+""+this.getEspec()+".gif";
        try
        {
            imagen = new ImageIcon(getClass().getResource("/cartas/"+nombreImagen));
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Método getter para la imagen de la carta, 
     * a partir de éste se conocerá la imagen actual de la carta.
     * @return Imagen de la carta
     */
    public ImageIcon getImagen() {
        return imagen;
    }
}