package jmta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FlReader {    
    
    public FlReader() {
        File dirLogs = new File("dirLogs");
        System.out.println("Creación del directorio");
        dirLogs.mkdir();
        System.out.println(dirLogs.getAbsolutePath());
        readDefaultFile(dirLogs.getAbsolutePath());
    }

    public void readDefaultFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.concat("\\big.txt")));
            String currentLine;
            System.out.println("Cargando archivo");
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);                
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
