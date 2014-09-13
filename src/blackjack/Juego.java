/**
 * Juego simple de blackjack.
 */
package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase representa un juego,
 * contiene el método main,
 * hereda de JFrame, por lo cual contiene la interfaz gráfica,
 * es un juego simple de BlackJack.
 * @author Santiago Vanegas
 */
public class Juego extends JFrame {
    
    private JLabel fondo;
    private JLabel[][] imgCartas;
    private JLabel[] imgCroupier;
    private JLabel[] imgMazo;
    private JLabel numeroRonda;
    private JLabel turnoJugador;
    private JLabel valorCroupier;
    private JLabel resultadosRonda;
    private JLabel[] resultadoFinal;
    
    private JButton creditos;
    private JButton salir;
    private JButton nuevoJuego;
    private JButton pedir;
    private JButton plantarse;
    
    private boolean pedirOn;
    private boolean plantarseOn;
    private boolean salirOn;
    private boolean nuevoJuegoOn;
    public boolean creditosOn;

    Mazo mazo;
    Mano jugador[];
    Mano croupier;
    
    /**
     * Atributo que indica la cantidad de jugadores en la partida.
     */
    public int cantJugadores;
    public int turno;
    /**
     * Atributo que indica el número de ronda que se está jugando.
     */
    private int ronda;
    /**
     * Atributo que indica si la partida está en curso o ya se terminó.
     */
    private boolean gameOver;
    
    /**
     * Método constructor,
     * creará la ventana de juego, instancia cada uno de los labels y botones, pregunta la cantidad de jugadores e inicia el juego.
     */
    Juego() {
        JOptionPane.showMessageDialog(null, "Se recomienda usar una resolución de 1440x900 ó superior");

        inicializarVentana();
        
        String rpta;
        for(int i=0; i<1; i++) {
            rpta = JOptionPane.showInputDialog("Digite la cantidad de jugadores (Máximo 7)");
            if(rpta == null || rpta.equals(JOptionPane.CANCEL_OPTION)) {
                System.exit(0);
            }
            if(rpta.length()==0 || rpta.length()>1 || !Character.isDigit(rpta.charAt(0))){
                JOptionPane.showMessageDialog(null, "Debe ingresar un número de un solo dígito");
                i--;
                continue;
            }
            int players = Integer.parseInt(rpta);
            if(players > 7 || players < 1) {
                JOptionPane.showMessageDialog(null, "Mínimo 1 jugador, máximo 7");
                i--;
                continue;
            }
            cantJugadores = players;
        }

        jugador = new Mano[cantJugadores];
        for(int i = 0; i<cantJugadores; i++) {
            jugador[i] = new Mano();
        }
        croupier = new Mano();
        mazo = new Mazo();
        ronda = 1;
        turno = 0;
        
        pedirOn = false;
        plantarseOn = false;
        salirOn = false;
        nuevoJuegoOn = false;
        creditosOn = false;
        
        gameOver = false;
        System.out.println();
        System.out.println("         -- -- -- Se ha comenzado la ronda "+(ronda++)+ " -- -- -- ");
        System.out.println();
        repartir();
    }
    
    /**
     * Método main, 
     * único método de arranque.
     */
    public static void main (String args[]){
        Juego juego = new Juego();
    }

    /**
     * Método que permite iniciar un juego nuevo, 
     * permite el ingreso de la cantidad de jugadores.
     */
    public void iniciarNuevoJuego() {
        String rpta;
        for(int i=0; i<1; i++) {
            rpta = JOptionPane.showInputDialog("Digite la cantidad de jugadores (Máximo 7)");
            if(rpta == null || rpta.equals(JOptionPane.CANCEL_OPTION)) {
                System.exit(0);
            }
            if(rpta.length()==0 || rpta.length()>1 || !Character.isDigit(rpta.charAt(0))){
                JOptionPane.showMessageDialog(null, "Debe ingresar un número de un solo dígito");
                i--;
                continue;
            }
            int players = Integer.parseInt(rpta);
            if(players > 7 || players < 1) {
                JOptionPane.showMessageDialog(null, "Mínimo 1 jugador, máximo 7");
                i--;
                continue;
            }
            cantJugadores = players;
        }
        jugador = new Mano[cantJugadores];
        for(int i = 0; i<cantJugadores; i++) {
            jugador[i] = new Mano();
        }
        croupier = new Mano();
        mazo = new Mazo();
        ronda = 1;
        turno = 0;
        
        pedirOn = false;
        plantarseOn = false;
        salirOn = false;
        nuevoJuegoOn = false;
        
        gameOver = false;

        nuevoJuego.setVisible(false);
        nuevoJuego.setEnabled(false);
        salir.setVisible(false);
        salir.setEnabled(false);

        numeroRonda.setVisible(true);
        turnoJugador.setVisible(true);
        for(int i = 0; i<8; i++) {
            resultadoFinal[i].setVisible(true);
        }
        valorCroupier.setVisible(true);

        System.out.println();
        System.out.println("         -- -- -- Se ha comenzado la ronda "+(ronda++)+ " -- -- -- ");
        System.out.println();
        repartir();
    }
    
    /**
     * Método que inicia la ventana y los componentes de la interfaz gráfica.
     */
    public void inicializarVentana() {
        
        fondo = new JLabel();
        
        imgCroupier = new JLabel[10];
        imgCartas = new JLabel[7][11];
        imgMazo = new JLabel[52];
        
        fondo.setBounds(0, 0, 1500, 800);
        fondo.setIcon(new ImageIcon(getClass().getResource("/cartas/fondo-copia.gif")));

        resultadoFinal = new JLabel[8];
        for(int i = 0; i<8; i++) {
            resultadoFinal[i] = new JLabel();
            resultadoFinal[i].setBounds(1000, 15+(20*i), 300, 30);
            getContentPane().add(resultadoFinal[i]);
        }
        
        numeroRonda = new JLabel();
        numeroRonda.setBounds(80, 20, 500, 30);
        numeroRonda.setFont(new Font("Verdana", Font.BOLD, 16));
        getContentPane().add(numeroRonda);
        
        turnoJugador = new JLabel();
        turnoJugador.setBounds(80, 50, 500, 30);
        turnoJugador.setFont(new Font("Verdana", Font.BOLD, 16));
        turnoJugador.setForeground(Color.CYAN);
        getContentPane().add(turnoJugador);
        
        valorCroupier = new JLabel();
        valorCroupier.setBounds(460, 80, 500, 30);
        valorCroupier.setFont(new Font("Verdana", Font.BOLD, 22));
        getContentPane().add(valorCroupier);

        resultadosRonda = new JLabel();
        resultadosRonda.setBounds(40, 165, 500, 30);
        resultadosRonda.setFont(new Font("Verdana", Font.BOLD, 18));
        resultadosRonda.setForeground(Color.RED);
        resultadosRonda.setVisible(false);
        getContentPane().add(resultadosRonda);
        
        creditos = new JButton();
        creditos.setBounds(80, 150, 121, 28);
        creditos.setVisible(false);
        creditos.setEnabled(false);
        creditos.setIcon(new ImageIcon(getClass().getResource("/cartas/creditos.jpg")));
        creditos.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                Creditos creditos = new Creditos();
                creditos.setVisible(true);
                creditos.setLocationRelativeTo(null);
                creditosOn = true;
            }
        });
        getContentPane().add(creditos);
        
        pedir = new JButton();
        pedir.setBounds(80, 150, 121, 28);
        pedir.setVisible(false);
        pedir.setEnabled(false);
        pedir.setIcon(new ImageIcon(getClass().getResource("/cartas/pedir.jpg")));
        pedir.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                pedirOn = true;
            }
        });
        getContentPane().add(pedir);
        
        plantarse = new JButton();
        plantarse.setBounds(80, 120, 121, 28);
        plantarse.setVisible(false);
        plantarse.setEnabled(false);
        plantarse.setIcon(new ImageIcon(getClass().getResource("/cartas/plantarse.jpg")));
        plantarse.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                plantarseOn = true;
            }
        });
        getContentPane().add(plantarse);

        nuevoJuego = new JButton();
        nuevoJuego.setBounds(80, 50, 121, 29);
        nuevoJuego.setVisible(false);
        nuevoJuego.setEnabled(false);
        nuevoJuego.setIcon(new ImageIcon(getClass().getResource("/cartas/nuevoJuego.jpg")));
        nuevoJuego.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                nuevoJuegoOn = true;
            }
        });
        getContentPane().add(nuevoJuego);
        
        salir = new JButton();
        salir.setBounds(80, 100, 121, 28);
        salir.setVisible(false);
        salir.setEnabled(false);
        salir.setIcon(new ImageIcon(getClass().getResource("/cartas/salir.jpg")));
        salir.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e) {
                salirOn = true;
            }
        });
        getContentPane().add(salir);
       

        for(int i = 0; i<imgCartas.length; i++) {

            for(int j=0; j<11; j++) {
                if(j!=0) {
                    imgCartas[i][j] = new JLabel();
                    int y = 200+(38*j);
                    int x = 80+(200*i);
                    imgCartas[i][j].setBounds(x,20+y,120,160);
                }
                else {
                    imgCartas[i][j] = new JLabel();
                    int y = 200+(38*j);
                    int x = 80+(200*i);
                    imgCartas[i][j].setBounds(x, 20+y, 120, 30);
                    imgCartas[i][j].setHorizontalAlignment(JLabel.CENTER);
                    imgCartas[i][j].setFont(new Font("Verdana", Font.BOLD, 16));
                }
            }
        }
        for(int i = imgCartas.length-1; i>=0; i--) {
            for(int j=10; j>=0; j--) {
                getContentPane().add(imgCartas[i][j]);
            }
        }
        
        for ( int i = 0 ; i < imgCroupier.length ; i++ ) {
    	    imgCroupier[i]= new JLabel();
    	    imgCroupier[i].setBounds(535 + (i*30) , 18, 120, 160);
        }

	for ( int i = imgCroupier.length-1 ; i >= 0  ; i-- ) {
            getContentPane().add ( imgCroupier[i] );
	}
        
        for ( int i = 0 ; i < imgMazo.length ; i++ ) {
    	    imgMazo[i] = new JLabel();
    	    imgMazo[i].setBounds(280 + (i*1) , 10, 120, 160);
        }

	for ( int i = imgMazo.length-1 ; i >= 0  ; i-- ) {
            getContentPane().add ( imgMazo[i] );
	}
        
        getContentPane().add(fondo);

        setTitle("Black Jack");
        Image icono = new ImageIcon(getClass().getResource("/cartas/icono.png")).getImage();
        setIconImage(icono);
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        setResizable(false);
        getContentPane().setBackground(Color.GREEN.darker().darker());
        getContentPane().setLayout(null);
    }
    
    /**
     * Método que reparte a cada jugador dos cartas del mazo,
     * si algún jugador obtuvo blackjack en las cartas que le fueron repartidas, 
     * automáticamente se entrará a mostrar los resultados de la ronda, 
     * por lo cual no se ingresa a la tanda de turnos.
     */
    public void repartir() {
        if(!gameOver) {
            if(!mazo.getRepartido()){
                boolean hayBlackJack = false;
              
                
                dibujarResultados(cantJugadores);
                System.out.println("-- Repartiendo cartas --");
                for(int i = 0; i<cantJugadores; i++) {
                    String espec1="";
                    String espec2="";
                    String pinta1="";
                    String pinta2="";
                    for(int j = 0; j<2; j++) {
                        Carta carta = mazo.ultimaCarta();
                        jugador[i].recibirCarta(carta);
                        if(j==0) {
                            espec1 = carta.getEspec();
                            pinta1 = carta.getPinta();
                        }
                        else {
                            espec2 = carta.getEspec();
                            pinta2 = carta.getPinta();
                        }
                    }
                    if(jugador[i].getSuma()==21) {
                        System.out.println("-- Jugador "+(i+1)+" obtuvo Blackjack --");
                        jugador[i].setPlantado(true);
                        jugador[i].setBlackJack(true);
                        hayBlackJack = true;
                    }
                    else {
                        System.out.println("-- Jugador "+(i+1)+" recibe "+espec1+" de "+pinta1+" y "+espec2+" de "+pinta2+" --");
                    }
                }
                String espec1="";
                String espec2="";
                String pinta1="";
                String pinta2="";
                for(int i = 0; i<2; i++){
                    Carta carta = mazo.ultimaCarta();
                    croupier.recibirCarta(carta);
                    if(i==0) {
                        espec1 = carta.getEspec();
                        pinta1 = carta.getPinta();
                    }
                    else {
                        espec2 = carta.getEspec();
                        pinta2 = carta.getPinta();
                    }
                }
                if(croupier.getSuma()==21) {
                    croupier.setBlackJack(true);
                    hayBlackJack = true;
                    turno = 8;
                    dibujarLabels();
                    dibujarTodasLasCartas();
                }
               
                
                System.out.println("-- Croupier recibe "+espec1+" de "+pinta1+" y "+espec2+" de "+pinta2+" --");
                System.out.println();

                boolean croupierBlackJack = croupier.getBlackJack();
                if(croupierBlackJack) {
                    croupier.setBlackJack(false);
                }
                dibujarTodasLasCartas();
                mazo.setRepartido(true);
                dibujarSumas();
                dibujarLabels();
                
                System.out.println("-- Reparto completado --");

                croupier.setBlackJack(croupierBlackJack);
                if(hayBlackJack) {
                    ganador();
                }
                else {
                    jugada();
                }
            }
            else System.out.println("-- ¡Ya se han repartido las cartas iniciales! --");
        }
        else System.out.println(" -- El juego ya terminó, debe inicar uno nuevo --");
    }
    
    /**
     * Método en el que ocurren las decisiones de cada actor
     * Cada jugador toma la decisión de plantarse o pedir otra carta.
     */
    public void jugada() {
        if(mazo.getRepartido() && !gameOver) {
            //Scanner in = new Scanner(System.in);
            for(int i = 0; i<cantJugadores; i++) {
                if(!jugador[i].getVolo() && !jugador[i].getPlantado()){
                    //boolean pedir;
                    System.out.println();
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("                   -- Turno del jugador "+(i+1)+" --");
                    turno = i;
                    
                    dibujarLabels();
                    
                    System.out.print("-- Sus cartas son -");
                    for(int j = 0; j<10; j++) {
                        if(jugador[i].saberCarta(j) != null) {
                            System.out.print(jugador[i].saberCarta(j).getEspec()+"-");
                        }
                        else {
                            break;
                        }
                    }
                    System.out.println(" y suman "+jugador[i].getSuma()+" -- El croupier suma "+croupier.getSuma()+" --");
                    //if(jugador[i].getSuma() > 21) {
                        //pedir = false;
                    //}
                    //else pedir = true;
                    //if(!pedir) System.out.println("-- Puede plantarse --");
                    //else //System.out.println("-- Puede plantarse o pedir una carta--");
                    //System.out.println(" >> Digite la opción que desee (plantarse/pedir) <<");
                    
                    while(turno==i) {
                        int rpta = -1;
                        rpta = pedirOPlantarse(i);
                        /*for(int j=0; j<1; j++) {
                            rpta = JOptionPane.showOptionDialog(null, "¿Desea plantarse o pedir?", "Jugador "+(i+1), 2, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Plantarse", "Pedir"}, null);
                            if(rpta == -1) {
                                j--;
                            }
                        }*/
                        if(rpta == 0) {
                            System.out.println("-- Jugador "+(i+1)+" se ha plantado");
                            jugador[i].setPlantado(true);
                            turno++;
                            dibujarSumas();
                            dibujarLabels();
                        }
                        else if(rpta == 1){
                            System.out.print("-- Jugador "+(i+1)+" ha tomado un ");
                            pedirCarta(jugador[i]);
                            if(jugador[i].getSuma()>21) {
                                System.out.println(" y voló con "+jugador[i].getSuma()+" --");
                            }
                            else System.out.println(" --");
                            i--;
                        }
                    }
                }
            }

            System.out.println();
            try{
                System.out.println("----------------------------------------------------------------------");
                turno = 8;

                dibujarSumas();
                dibujarTodasLasCartas();
                dibujarLabels();
               

                while(croupier.getSuma()<17){
                    System.out.print("-- El croupier ha tomado un ");
                    Thread.currentThread().sleep(1000);
                    pedirCarta(croupier);
                    System.out.println(" y ahora suma...");
                    System.out.println("-- "+croupier.getSuma());
                    if(croupier.getSuma()>=17){
                        break;
                    }
                }
                System.out.println();
            }catch(Exception e){}
            

            ganador();
        }
        else System.out.println("-- ¡No se ha repartido o el juego ya terminó! --");
    }
    
    /**
     * Método que activa los botones plantarse y pedir, 
     * espera que alguno de los dos sea presionado para ejecutar la respectiva acción.
     * @param i Jugador que tiene el turno en el momento de activar los botones.
     * @return 1 si fue presionado el boton pedir y 0 si fue presionado el boton plantarse.
     */
    public int pedirOPlantarse(int i){
        
        pedir.setLocation(80+(200*i), 600);
        pedir.setVisible(true);
        pedir.setEnabled(true);
        plantarse.setLocation(80+(200*i), 630);
        plantarse.setVisible(true);
        plantarse.setEnabled(true);
        while(!pedirOn && !plantarseOn) {
            //System.out.println("Esperando a undir boton");
        }
        if(pedirOn) {
            pedirOn = false;
            plantarseOn = false;
            pedir.setVisible(false);
            pedir.setEnabled(false);
            plantarse.setVisible(false);
            plantarse.setEnabled(false);
            return 1;
        }
        else {
            pedirOn = false;
            plantarseOn = false;
            pedir.setVisible(false);
            pedir.setEnabled(false);
            plantarse.setVisible(false);
            plantarse.setEnabled(false);
            return 0;
        }
    }
    
    /**
     * Método que determina y muestra el o los ganador(es) de la ronda, 
     * pregunta si se desea terminar el juego o seguir con otra ronda.
     */
    public void ganador() {
        if(!gameOver) {
            
            System.out.println("      -- Resultados --");
            for(int i = 0; i<cantJugadores; i++) {
                if(jugador[i].getBlackJack()==true) {
                    System.out.println("-- Valor jugador "+(i+1)+": "+jugador[i].getSuma()+" (Blackjack)");
                }
                else {
                    System.out.println("-- Valor jugador "+(i+1)+": "+jugador[i].getSuma());
                }
            }
            System.out.println("-- Valor croupier: "+croupier.getSuma());
            System.out.println();
        
            int corte = croupier.getSuma();
            boolean ganan = false;
            if(corte > 21) {
                ganan = true;
            }
            int ganaCroupier = 0;
            int ganadores[] = new int [cantJugadores];
            int volo = 0;
            for(int i = 0; i<cantJugadores; i++) {
                int valor = jugador[i].getSuma();
                if(!jugador[i].getVolo() && jugador[i].getPlantado()) {
                    if((ganan || (corte<=21 && (valor>corte||jugador[i].getBlackJack()))) && jugador[i].getVolo()==false) {
                        ganadores[i] = valor;
                    }
                    else {
                        ganaCroupier++;   
                    }
                }
                else ganaCroupier++;
                if(jugador[i].getVolo()==true) {
                    volo++;
                }
            }
            if(croupier.getSuma()<=21 && ganaCroupier==cantJugadores) {
                resultadosRonda.setText("-- El croupier gana con "+croupier.getSuma()+" puntos --");
                System.out.println("-- El croupier gana con "+croupier.getSuma()+" puntos --");
                int confirm = JOptionPane.showOptionDialog(null, resultadosRonda.getText(), "Ganadores", 0, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Aceptar"}, null);
            }
            else if(volo==cantJugadores) {
                System.out.println(" -- Todos los jugadores volaron --");
                resultadosRonda.setText(" -- Todos los jugadores volaron --");
                int confirm = JOptionPane.showOptionDialog(null, resultadosRonda.getText(), "Ganadores", 0, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Aceptar"}, null);
            }
            else {
                System.out.print("-- Punto para jugador(es) ");
                resultadosRonda.setText("-- Punto para jugador(es) ");
                boolean hayBlackJack = false;
                for(int i=0; i<cantJugadores; i++) {
                    if(jugador[i].getBlackJack()) {
                        hayBlackJack = true;
                        break;
                    }
                }
                for(int i=0; i<cantJugadores; i++) {
                    if(ganadores[i]>0) {
                        if(hayBlackJack) {
                            if(jugador[i].getBlackJack()) {
                                jugador[i].setPuntaje(1);
                                System.out.print((i+1)+" ");
                                resultadosRonda.setText(resultadosRonda.getText()+(i+1)+" ");

                            }
                        }
                        else {
                            jugador[i].setPuntaje(1);
                            System.out.print((i+1)+" ");
                            resultadosRonda.setText(resultadosRonda.getText()+(i+1)+" ");
                        }
                    }
                }
                System.out.println("--");
                resultadosRonda.setText(resultadosRonda.getText()+"--");
                int confirm = JOptionPane.showOptionDialog(null, resultadosRonda.getText(), "Ganadores", 0, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Aceptar"}, null);
            }
            System.out.println();
            System.out.println(">> Ronda finalizada, ¿desea empezar una nueva ronda o terminar el juego? (nueva/terminar) <<");
            //Scanner in = new Scanner(System.in);
            //String rpta = in.next();
            int rpta = -1;
            rpta = JOptionPane.showOptionDialog(null, "¿Desea empezar una nueva ronda o terminar el juego?", "Ronda finalizada", 2, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Nueva ronda", "Terminar"}, null);
            if(rpta == 0) {
                siguienteRonda();
            }
            if(rpta == 1 || rpta == -1) {
                terminarJuego();
            }
            botonSalirONuevo();
        }
        else System.out.println("-- El juego ya terminó, debe iniciar uno nuevo --");
    }
    
    /**
     * Método que activa los botones salir y nuevo juego, 
     * espera a que alguno de los dos sea presionado para ejecutar la respectiva acción.
     */
    public void botonSalirONuevo() {
        for(int i = 0; i<1; i++) {
            creditos.setVisible(true);
            creditos.setEnabled(true);
            nuevoJuego.setVisible(true);
            nuevoJuego.setEnabled(true);
            salir.setVisible(true);
            salir.setEnabled(true);
            while(!nuevoJuegoOn && !salirOn) {
                
            }
            if(nuevoJuegoOn) {
                nuevoJuegoOn = false;
                salirOn = false;
                creditos.setVisible(false);
                creditos.setEnabled(false);
                nuevoJuego.setVisible(false);
                nuevoJuego.setEnabled(false);
                salir.setVisible(false);
                salir.setEnabled(false);
                iniciarNuevoJuego();
            }
            else {
                nuevoJuegoOn = false;
                salirOn = false;
                creditos.setVisible(false);
                creditos.setEnabled(false);
                nuevoJuego.setVisible(false);
                nuevoJuego.setEnabled(false);
                salir.setVisible(false);
                salir.setEnabled(false);
                int confirm = 1;
                confirm = JOptionPane.showOptionDialog(null, "¿Seguro que desea salir?", "Salir", 2, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Salir", "Cancelar"}, null);
                if(confirm == 0) {
                    System.exit(0);
                }
                else {
                    i--;
                }
            }
        }
    }
    
    /**
     * Método para reiniciar todos los atributos y valores de cada jugador, croupier, y del mazo,
     * reinicia los valores necesarios que pertenecen a inerfaz gráfica.
     */
    public void siguienteRonda() {
        if(mazo.getRepartido() && !gameOver) {
            for(int i = 0; i<cantJugadores; i++) {
                jugador[i].setVolo(false);
                jugador[i].setPlantado(false);
                jugador[i].vaciarMano();
                jugador[i].setBlackJack(false);
                jugador[i].setAses(0);
                croupier.vaciarMano();
                croupier.setAses(0);
                mazo.setRepartido(false);
                mazo.revolverMazo();
            }
            
            for(int i=0; i<7; i++) {
                for(int j=0; j<11; j++) {
                    if(j!=0) {
                        imgCartas[i][j].setIcon(null);
                    }
                    else {
                        imgCartas[i][j].setBackground(null);
                        imgCartas[i][j].setText("");
                    }
                }
            }
 
            for(int i=0; i<10; i++) {
                imgCroupier[i].setIcon(null);
            }
            System.out.println();
            System.out.println("          -- -- -- Se ha comenzado la ronda "+(ronda++)+ " -- -- -- ");
            System.out.println();
            
            turno = 0;
            resultadosRonda.setText("");
            nuevoJuego.setVisible(false);
            nuevoJuego.setEnabled(false);
            dibujarResultados(cantJugadores);
            dibujarLabels();
            repartir();
        }
        else System.out.println("-- ¡No se ha repartido o el juego ya terminó! --");
    }
    
    /**
     * Método para terminar la partida, 
     * ocultará componentes gráficos que no se deben mostrar una vez terminada la partida,
     * habilitará dos botones: salir, y nuevo juego.
     */
    public void terminarJuego() {
        dibujarResultados(cantJugadores);
        System.out.println();
        System.out.println("  -- El juego se ha terminado, los resultados finales son los siguientes --");
        for(int i = 0; i<cantJugadores; i++) {
            int puntos = jugador[i].getPuntaje();
            System.out.println("Jugador "+(i+1)+": "+puntos+" puntos");
        }
        
        for(int i=0; i<7; i++) {
            for(int j=0; j<11; j++) {
                if(j!=0) {
                    imgCartas[i][j].setIcon(null);
                }
                else {
                    imgCartas[i][j].setBackground(null);
                    imgCartas[i][j].setText("");
                }
            }
        }
        for(int i=0; i<10; i++) {
            imgCroupier[i].setIcon(null);
        }
        
        for(int i=0;i<52; i++) {
            imgMazo[i].setIcon(new ImageIcon(getClass().getResource("/cartas/reverso.gif")));
        }

        numeroRonda.setVisible(false);
        turnoJugador.setVisible(false);
        resultadosRonda.setVisible(false);
        valorCroupier.setVisible(false);
        for(int i = 0; i<8; i++) {
            resultadoFinal[i].setVisible(false);
        }
        
        gameOver = true;
    }

    /**
     * Método que le entrega a un jugador una carta del mazo.
     * @param mano Jugador que está pidiendo la carta.
     */
    public void pedirCarta(Mano mano) {
        if(!gameOver) {
            Carta carta = mazo.ultimaCarta();
            if(carta!=null) {
                mano.recibirCarta(carta);
                
                dibujarTodasLasCartas();
                
                System.out.print(carta.getEspec()+" de "+carta.getPinta());
                if(mano.getSuma()>21){
                    mano.setVolo(true);
                    turno++;
                }
                else if(mano.getSuma()==21) {
                    mano.setPlantado(true);
                    turno++;
                }
                dibujarSumas();
            }
        }
        else System.out.println("-- El juego ya terminó, debe iniciar uno nuevo --");
    }
    
    /**
     * Método que pinta cada una de las cartas de cada jugador, del croupier y del mazo,
     * si no es el turno del croupier éste debe tener una carta tapada.
     */
    public void dibujarTodasLasCartas() {
        
        if(!mazo.getRepartido()) {
            for(int i=0; i<imgMazo.length; i++) {
                imgMazo[i].setIcon(new ImageIcon(getClass().getResource("/cartas/reverso.gif")));
            }
        }
        else {
            for(int i=0; i<imgMazo.length; i++) {
                if(mazo.cartaEnPos(i)!=null) {
                    imgMazo[i].setIcon(new ImageIcon(getClass().getResource("/cartas/reverso.gif")));
                }
                else {
                    imgMazo[i].setIcon(null);       
                }
            }
        }
        
        int k = 51;
        for(int i=0; i<cantJugadores; i++) {
            for(int j=1; j<11; j++){
                Carta carta = jugador[i].saberCarta(j-1);
                if(carta != null) {
                    ImageIcon imagen = carta.getImagen();
                    try {
                        if(!mazo.getRepartido()) {
                            Thread.currentThread().sleep(250);
                            imgMazo[k--].setIcon(null);
                        }
                        imgCartas[i][j].setIcon(imagen);
                    }
                    catch (Exception e) {}
                }
            }
        }
        for(int i = 0; i<10; i++) {
            Carta carta = croupier.saberCarta(i);
            if(carta != null) {
                try {
                    ImageIcon imagen = carta.getImagen();
                    if(!mazo.getRepartido()) {
                        Thread.currentThread().sleep(300);
                        imgMazo[k--].setIcon(null);
                        
                    }
                    if((i==0 && turno<8) || (croupier.getBlackJack() && i==0)) {
                        imgCroupier[i].setIcon(new ImageIcon(getClass().getResource("/cartas/reverso.gif")));
                    }
                    else {
                        imgCroupier[i].setIcon(imagen);
                    }
                }
                catch (Exception e) {}
            }
        }
    }
    
    /**
     * Método que pinta la suma de cada jugador y del croupier en su respectivo label, 
     * si no es el turno del croupier, sólo debe imprimir la suma de las cartas visibles.
     */
    public void dibujarSumas() {
        for(int i=0; i<cantJugadores; i++) {
            for(int j=0; j<1; j++) {
                int suma = jugador[i].getSuma();
                imgCartas[i][j].setText(""+suma);
                if(jugador[i].getVolo()) {
                    imgCartas[i][j].setOpaque(true);
                    imgCartas[i][j].setBackground(Color.RED);
                }
                else if(jugador[i].getPlantado()) {
                    imgCartas[i][j].setOpaque(true);
                    imgCartas[i][j].setBackground(Color.YELLOW);
                    if(jugador[i].getBlackJack()) {
                        imgCartas[i][j].setText("Blackjack");
                        imgCartas[i][j].setBackground(Color.LIGHT_GRAY);
                        continue;
                    }
                }
                else if(turno == i) {
                    imgCartas[i][j].setOpaque(true);
                    imgCartas[i][j].setBackground(Color.CYAN);
                }
                else {
                    imgCartas[i][j].setForeground(null);
                    imgCartas[i][j].setBackground(null);
                }
            }
        }
        int suma = croupier.getSuma();
        if(turno < 8 && !croupier.getBlackJack()) {
            if(croupier.saberCarta(0)!=null) {
                valorCroupier.setText(""+(suma-croupier.saberCarta(0).getValor()));
            }
            else {
                valorCroupier.setText(""+(suma));
            }
        }
        else {
            valorCroupier.setText(""+(suma));
        }
    }
    
    /**
     * Método para pintar los componentes gráficos: número de ronda y turno.
     */
    public void dibujarLabels() {
        numeroRonda.setText("Ronda número: "+(this.ronda-1));
        if(turno == 8) {
            turnoJugador.setText("Turno del croupier");
        } 
        else {
            turnoJugador.setText("Turno del jugador "+(turno+1));
            dibujarSumas();
        }
    }

    /**
     * Método para pintar la tabla de puntuación de los jugadores,
     * esta tabla se actualizará cada vez que se fialice una ronda.
     * @param jugadores Cantidad de jugadores.
     */
    public void dibujarResultados(int jugadores) {
        for(int i = 0; i<jugadores+1; i++) {
            if(i!=0)
                resultadoFinal[i].setText("Jugador "+i+": "+jugador[i-1].puntaje);
            else {
                resultadoFinal[i].setText("PUNTUACIÓN");
                resultadoFinal[i].setHorizontalAlignment(JLabel.CENTER);
            }
            resultadoFinal[i].setFont(new Font("Verdana", Font.BOLD, 16));
       
        }
    }
}