package Messager;

import Messager.IMessager;

import java.util.Scanner;

public class ConsoleMessager implements IMessager {
    private static Scanner scanner = new Scanner(System.in);

    public String read() {
        return scanner.nextLine();
    }

    public void write(String output, String id) {
        System.out.println(String.format("@%s: %s",id, output));
    }
}
