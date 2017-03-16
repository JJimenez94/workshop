package co.edu.ucatolica.sd.primenumber.model;

import co.edu.ucatolica.sd.primenumber.controler.Orchestrator;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkProtocol {

    private final int localPort = 29000, waitTime = 5000;
    private final String host = "localhost";
    private CoreLogic logica = new CoreLogic();
    private ServerSocket ss;
    private Socket sc;
    public Orchestrator orquestador;
    public DataOutputStream salida;
    public DataInputStream entrada;

    public NetworkProtocol(char role) {
        System.out.println("Inicializando protocolo de red");
        if (role == 'H') {
            try {
                orquestador = new Orchestrator(role);
                System.out.println("Inicializando Socket servidor");
                ss = new ServerSocket(localPort);
                sc = new Socket();
                System.out.println("Esperando una conexión...");
                sc = ss.accept();
                entrada = new DataInputStream(sc.getInputStream());
                salida = new DataOutputStream(sc.getOutputStream());
                System.out.println("Conexion recibida desde: " + sc.getInetAddress());
                for (;;) {
                    boolean rival = orquestador.setRival();
                    System.out.println("Transmitiendo valores del juego:\nRival: " + String.valueOf(rival));
                    salida.writeUTF(String.valueOf(rival));
                    int digito;
                    if (rival) {
                        digito = orquestador.setDigits();
                        System.out.println("Digito: " + String.valueOf(digito));
                        salida.writeUTF(String.valueOf(digito));
                    } else {
                        digito = Integer.parseInt(entrada.readUTF());
                        System.out.println("Digito: " + String.valueOf(digito));
                    }
                    System.out.println("Ejecutando juego con las variables asignadas");
                    String timeElapsed = String.valueOf(logica.primeSeq(digito));
                    String compare;
                    if (rival) {
                        compare = 'R' + timeElapsed;
                    } else {
                        compare = 'O' + timeElapsed;
                    }
                    System.out.println("Se reciben los datos del invitado");
                    String tiempo = entrada.readUTF();
                    System.out.println("Se transmiten los datos al invitado");
                    salida.writeUTF(compare);
                    System.out.println("Valores recibidos \nAnfitrion: " + compare + "\nInvitado: " + tiempo);
                    orquestador.defineWinner(compare, tiempo);
                    //sc.wait(waitTime);
                    Thread.sleep(waitTime);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            try {
                orquestador = new Orchestrator(role);
                System.out.println("Inicializando Socket cliente");
                sc = new Socket(host, localPort);
                entrada = new DataInputStream(sc.getInputStream());
                salida = new DataOutputStream(sc.getOutputStream());
                for (;;) {                    
                    System.out.println("Recibiendo valores del juego: ");                    
                    boolean rival = Boolean.valueOf(entrada.readUTF());
                    System.out.println("Rival: " + String.valueOf(!rival));
                    int digito;
                    if (!rival) {
                        digito = orquestador.setDigits();
                        System.out.println("Digito: " + String.valueOf(digito));
                        salida.writeUTF(String.valueOf(digito));
                    } else {
                        digito = Integer.parseInt(entrada.readUTF());
                        System.out.println("Digito: " + String.valueOf(digito));
                    }
                    System.out.println("Ejecutando juego con las variables asignadas");
                    String timeElapsed = String.valueOf(logica.primeSeq(digito));
                    String compare;
                    if (!rival) {
                        compare = 'R' + timeElapsed;
                    } else {
                        compare = 'O' + timeElapsed;
                    }
                    System.out.println("Se transmite los datos al anfitrion");
                    salida.writeUTF(compare);
                    System.out.println("Se reciben los datos del anfitrion");
                    String tiempo = entrada.readUTF();
                    System.out.println("Valores recibidos \nAnfitrion: " + tiempo + "\nInvitado: " + compare);
                    orquestador.defineWinner(compare, tiempo);                    
                    Thread.sleep(waitTime);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
