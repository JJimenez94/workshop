package co.edu.ucatolica.sd.primenumber.model;

public class CoreLogic {

    public double primeSeq(int digits) {
        double elapsedTime = -1;
        if (digitsValidity(digits)) {
            System.out.println("Se ingresa un digito valido\nIniciando calculos");
            double exp = Math.pow(10, digits);
            System.out.println("Se captura timestamp inicial");
            double ti = System.currentTimeMillis();
            for (int i = 1; i < exp; i++) {
                System.out.println("Calculando para el número: " + Integer.toString(i) + " - " + String.valueOf(isPrime(i)));                
            }
            double tf = System.currentTimeMillis();
            elapsedTime = tf - ti;
            elapsedTime = (elapsedTime / 1000.0);
            System.out.println("El tiempo transcurrido es de: " + Double.toString(elapsedTime));
        } else {
            System.out.println("El digito ingresado es incorrecto\nRecuerde que debe estar entre 2 y 6");
        }
        return elapsedTime;
    }

    private boolean digitsValidity(int number) {
        boolean flag = false;
        if (2 <= number && number <= 6) {
            flag = true;
        }
        return flag;
    }

    private boolean isPrime(int number) {
        boolean prime = true;
        int count = 2;
        if (number == 1) {
            return prime;
        } else {
            while ((prime) && (count != number)) {
                if (number % count == 0) {
                    prime = false;
                }
                count++;
            }
            return prime;
        }
    }

}