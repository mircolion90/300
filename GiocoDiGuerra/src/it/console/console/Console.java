package it.console.console;

import java.util.Scanner;

public class Console {
    public static String readString(){
        String readLine = "";
        try {
            Scanner toRead = new Scanner(System.in);
            readLine = toRead.nextLine();
        }catch(Exception e){
            System.out.println("Ma cosa hai scritto?");
        }
        return readLine;
    }
    public static int readInt(){
        int readInt = 0;
        try {
            Scanner toRead = new Scanner(System.in);
            readInt = toRead.nextInt();
        }catch(Exception e){
            System.out.println("Ma che numero scrivi?");
        }
        return readInt;
    }
}
