package jmta;

import java.util.ArrayList;

public class Main {

    ArrayList<Hilo> listado  = new ArrayList<>();
    Hilo hilo;
            
    public Main(int cantidad) {
                for(int i=0; i<cantidad; i++){
                    hilo = new Hilo(i+1);
                    hilo.start();
                    listado.add(hilo);
                    System.out.println(listado.size());
                }
    }
    
    

    public static void main(String[] args) {
        Main test = new Main(10);
    }

}
