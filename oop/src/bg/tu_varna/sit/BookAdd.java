package bg.tu_varna.sit;

import java.util.Scanner;

public class BookAdd implements Command {
    private Menu menu;

    public BookAdd(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter year of publication: ");
        String year = scanner.nextLine();

        System.out.print("Enter keywords: ");
        String keywords = scanner.nextLine();

        System.out.print("Enter rating: ");
        String rating = scanner.nextLine();

        Book book = new Book(id, author, title, genre, description, year, keywords, rating);
        menu.appendToFile(book);
        menu.displayFileContent();
    }
}
