package client;

import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Scanner;

import server.InterfaceRMI;

public class Client {

	public static void main(String[] args) {
		try {
			InterfaceRMI i = (InterfaceRMI) Naming.lookup("rmi://localhost:29000//saludar");
			String name;
			Scanner sc = new Scanner(new InputStreamReader(System.in));
			System.out.println("¿Como es tu nombre?");
			name = sc.nextLine();			
			System.out.println(i.saludar(name));
		} catch (Exception e) {
			System.out.println("Error en el cliente" + e.getMessage());
		}

	}

}
