package bg.tu_varna.sit;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.util.List;
import java.util.ArrayList;

public class BookSort implements Command {
    private Menu menu;
    private NodeTools nodeTools;

    public BookSort(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        if (!menu.isFileOpened()) {
            System.out.println("No file opened. Please open a file first.");
            return;
        }


        if (args.length < 3) {
            System.out.println("Invalid syntax. Usage: books sort <attribute> [desc]");
            return;
        }

        String attribute = args[2].toLowerCase();


        NodeList books = menu.getDocument().getElementsByTagName("book");
        boolean attributeExists =nodeTools.attributeExistsInAllNodes(books, attribute);

        if (!attributeExists) {
            System.out.println("Attribute \"" + attribute + "\" does not exist.");
            return;
        }


        boolean ascending = !(args.length > 3 && args[3].equalsIgnoreCase("desc"));
        Node[] sortedBooks =nodeTools.sortNodes(books, attribute, ascending);


        for (Node book : sortedBooks) {
            String bookInfo = menu.getBookInfoSort((Element) book);
            System.out.println(bookInfo);
        }
    }
}

