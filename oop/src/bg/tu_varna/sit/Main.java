package bg.tu_varna.sit;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TU Library!");
        System.out.println("Type 'help' for a list of commands.");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            menu.executeCommand(command);
        }
    }
}
