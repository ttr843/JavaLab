package ru.itis.javalab;


import java.util.Scanner;
import ru.itis.javalab.app.ThreadDownloadFile;

public class Main {

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        while (sc.hasNext()){
        	ThreadDownloadFile tdf = new ThreadDownloadFile(sc.nextLine());
        }
    }
}
