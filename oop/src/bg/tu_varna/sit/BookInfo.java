package bg.tu_varna.sit;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BookInfo implements Command {
    private Menu menu;

    public BookInfo(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid syntax. Usage: book info <id>");
            return;
        }

        String id = args[2];
        Element bookElement = menu.getBookById(id);

        if (bookElement != null) {
            printBookInfo(bookElement);
        } else {
            System.out.println("Book with ID '" + id + "' not found.");
        }
    }

    private void printBookInfo(Element bookElement) {
        System.out.println("Book Information:");
        System.out.println("ID: " + bookElement.getAttribute("id"));
        System.out.println("Author: " + getElementValue(bookElement, "author"));
        System.out.println("Title: " + getElementValue(bookElement, "title"));
        System.out.println("Genre: " + getElementValue(bookElement, "genre"));
        System.out.println("Description: " + getElementValue(bookElement, "description"));
        System.out.println("Year of Publication: " + getElementValue(bookElement, "yearOfPublication"));
        System.out.println("Keywords: " + getElementValue(bookElement, "keywords"));
        System.out.println("Rating: " + getElementValue(bookElement, "rating"));
    }

    private String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return "";
        }
    }
}

