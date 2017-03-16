package co.edu.ucatolica.sd.primenumber.controler;

import java.util.Random;

public class Orchestrator {

    boolean hostIsRival;
    int gameDigits;
    private Random rndGeneral = new Random();

    public Orchestrator(char role) {
        if (role == 'H') {
            System.out.println("Bienvenido anfitrión");
        } else {
            System.out.println("Bienvenido invitado");
        }
        System.out.println("Se procede con la inicialización de los parametros de juego...\nPor favor espere");
    }

    public boolean setRival() {
        rndGeneral.setSeed(System.currentTimeMillis());
        this.hostIsRival = rndGeneral.nextBoolean();
        return this.hostIsRival;
    }

    public int setDigits() {
        rndGeneral.setSeed(System.currentTimeMillis());
        this.gameDigits = rndGeneral.nextInt(5) + 2;
        return this.gameDigits;
    }

    public int getGameDigits() {
        return gameDigits;
    }

    public boolean isHostRival() {
        return hostIsRival;
    }

    public void defineWinner(String time1, String time2) {
        char pos1, pos2;
        double elapsed1, elapsed2;
        pos1 = time1.charAt(0);
        elapsed1 = Double.parseDouble(time1.substring(1, time1.length()));
        pos2 = time2.charAt(0);
        elapsed2 = Double.parseDouble(time2.substring(1, time2.length()));
        if (elapsed1 == elapsed2) {
            System.out.println("Hubo un empate, el tiempo de los jugadores fue de: " + time2.substring(1, time2.length()) + " segundos");
        } else if (elapsed1 < elapsed2) {
            if (pos1 == 'R') {
                System.out.println("El ganador de la prueba fue el retador con un tiempo de: " + time1.substring(1, time1.length()) + " segundos");
            } else {
                System.out.println("El ganador de la prueba fue el oponente con un tiempo de: " + time1.substring(1, time1.length())+ " segundos");
            }                
        } else {
            if (pos2 == 'R') {
                System.out.println("El ganador de la prueba fue el retador con un tiempo de: " + time2.substring(1, time2.length())+ " segundos");
            } else {
                System.out.println("El ganador de la prueba fue el oponente con un tiempo de: " + time2.substring(1, time2.length())+ " segundos");
            }            
        }
    }

}
