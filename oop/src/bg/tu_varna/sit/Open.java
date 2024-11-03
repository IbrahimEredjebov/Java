package bg.tu_varna.sit;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Open implements Command {
    private Menu menu;

    public Open(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        if (menu.isFileOpened()) {
            System.out.println("A file is already opened. Please close the current file first.");
            return;
        }

        if (args.length != 2) {
            System.out.println("Invalid syntax. Usage: open <file>");
            return;
        }

        String fileName = args[1].endsWith(".xml") ? args[1] : args[1] + ".xml";
        String directoryPath = "D:\\4 Kurs\\Final\\Final\\oop";
        File file = new File(directoryPath, fileName);

        if (file.exists()) {
            menu.setOpenedFileName(file.getAbsolutePath());
            menu.readFileContent();
            menu.setFileOpened(true);
            System.out.println("Successfully opened " + fileName);
        } else {
            System.out.println("File does not exist. Do you want to create a new file? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                try {
                    if (file.createNewFile()) {
                        menu.setOpenedFileName(file.getAbsolutePath());
                        menu.setFileOpened(true);
                        System.out.println("Successfully created and opened " + fileName);
                    } else {
                        System.out.println("Error: Failed to create file.");
                    }
                } catch (IOException e) {
                    System.out.println("Error: Failed to create file.");
                }
            } else {
                System.out.println("Please enter a valid command.");
            }
        }
    }
}
