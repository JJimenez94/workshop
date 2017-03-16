package jmta;

import java.util.ArrayList;
import java.util.Random;

public class Hilo extends Thread {

    private int threadID;
    boolean[][] newTrash;
    ArrayList<boolean[][]> listado;

    public Hilo(int threadID) {
        this.threadID = threadID;
        this.listado = new ArrayList<>();
        this.newTrash = new boolean[1024][1024];
    }

    public boolean[][] genTrash() {
        long seed = System.currentTimeMillis();
        Random rnd = new Random(seed);
        boolean[][] matrix = new boolean[1024][1024];
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                matrix[i][j] = rnd.nextBoolean();
            }
        }
        return matrix;
    }        
        
    @Override
    public void run() {        
        System.out.println("Hilo: "+threadID+" iniciado");
        int count = 0;
        while (true) {             
            System.out.println("Hilo "+threadID+"\nGenerando archivos basura matriz booleana [1024 x 1024]");
            newTrash = genTrash();            
            System.out.println("Cargando archivos basura en memoria");
            listado.add(newTrash);
            count = count + 1;
            if(count%20==0){
                System.out.println("Limpiando primer archivo cargado en memoria");
                listado.remove(0);
                if(count%200==0){
                    System.out.println("Limpiando memoria");
                    listado.clear();
                }
            }            
        }

    }

}
