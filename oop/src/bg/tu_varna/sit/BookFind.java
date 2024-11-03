package bg.tu_varna.sit;

import java.util.List;

public class BookFind implements Command {
    private Menu menu;

    public BookFind(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Invalid syntax. Usage: book find <attribute> <value>");
            return;
        }

        if (!menu.isFileOpened()) {
            System.out.println("No file opened. Please open a file first.");
            return;
        }

        String attribute = args[2].toLowerCase();
        String value = args[3];


        if (!attribute.equals("title") && !attribute.equals("author") && !attribute.equals("description")) {
            System.out.println("Invalid attribute. You can search only by title, author, or description.");
            return;
        }


        List<String> booksInfo = menu.findBooksByAttribute(attribute, value);
        if (!booksInfo.isEmpty()) {
            System.out.println("Books found:");
            for (String bookInfo : booksInfo) {
                System.out.println(bookInfo);
            }
        } else {
            System.out.println("No books found with attribute " + attribute + " and value " + value);
        }
    }
}

