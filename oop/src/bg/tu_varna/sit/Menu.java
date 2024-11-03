package bg.tu_varna.sit;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private boolean fileOpened = false;
    private String openedFileName;
    private Document document;
    private String currentFileName;
    private Command openCommand;
    private Command saveCommand;
    private Command saveAsCommand;
    private Command closeCommand;
    private Command exitCommand;
    private Command helpCommand;
    private Command bookAddCommand;
    private Command bookAllCommand;
    private Command bookInfoCommand;
    private Command bookFindCommand;
    private Command bookDeleteCommand;
    private Command bookSortCommand;

    public Menu() {
        this.openedFileName = null;
        this.document = null;
        this.currentFileName = null;
        this.openCommand = new Open(this);
        this.saveCommand = new Save(this);
        this.saveAsCommand = new SaveAs(this);
        this.closeCommand = new Close(this);
        this.exitCommand = new Exit();
        this.helpCommand = new Help();
        this.bookAddCommand = new BookAdd(this);
        this.bookAllCommand = new BookAll(this);
        this.bookInfoCommand = new BookInfo(this);
        this.bookFindCommand = new BookFind(this);
        this.bookDeleteCommand = new DeleteBook(this);
        this.bookSortCommand = new BookSort(this);
    }

    public void executeCommand(String command) {
        String[] tokens = command.split(" ");
        String commandName = tokens[0].toLowerCase();

        switch (commandName) {
            case "open":
                openCommand.execute(tokens);
                break;
            case "save":
                if (fileOpened) {
                    saveCommand.execute(tokens);
                } else {
                    System.out.println("No file opened. Please open a file first.");
                }
                break;
            case "saveas":
                if (fileOpened) {
                    saveAsCommand.execute(tokens);
                } else {
                    System.out.println("No file opened. Please open a file first.");
                }
                break;
            case "close":
                if (fileOpened) {
                    closeCommand.execute(tokens);
                    fileOpened = false;
                    openedFileName = null;
                } else {
                    System.out.println("No file opened. Please open a file first.");
                }
                break;
            case "exit":
                exitCommand.execute(tokens);
                break;
            case "help":
                helpCommand.execute(tokens);
                break;
            case "books":
                if (fileOpened) {
                    if (tokens.length > 1) {
                        switch (tokens[1].toLowerCase()) {
                            case "all":
                                bookAllCommand.execute(tokens);
                                break;
                            case "info":
                                if (tokens.length == 3) {
                                    bookInfoCommand.execute(tokens);
                                } else {
                                    System.out.println("Invalid syntax. Usage: book info <id>");
                                }
                                break;
                            case "add":
                                if (tokens.length >= 2) {
                                    bookAddCommand.execute(tokens);
                                } else {
                                    System.out.println("Invalid syntax. Usage: book add <id> <author> <title> <genre> <description> <year> <keywords> <rating>");
                                }
                                break;
                            case "find":
                                if (tokens.length >= 4) {
                                    bookFindCommand.execute(tokens);
                                } else {
                                    System.out.println("Invalid syntax. Usage: book find <attribute> <value>");
                                }
                                break;
                            case "delete":
                                if (tokens.length == 3) {
                                    bookDeleteCommand.execute(tokens);
                                } else {
                                    System.out.println("Invalid syntax. Usage: book delete <id>");
                                }
                                break;
                            case "sort":
                                if (tokens.length >= 3) {
                                    bookSortCommand.execute(tokens);
                                } else {
                                    System.out.println("Invalid syntax. Usage: book sort <attribute> [desc]");
                                }
                                break;
                            default:
                                System.out.println("Unknown command. Type 'help' for a list of commands.");
                                break;
                        }
                    } else {
                        System.out.println("Unknown command. Type 'help' for a list of commands.");
                    }
                } else {
                    System.out.println("No file opened. Please open a file first.");
                }
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
                break;
        }
    }

    public void setFileOpened(boolean fileOpened) {
        this.fileOpened = fileOpened;
    }

    public void setOpenedFileName(String openedFileName) {
        this.openedFileName = openedFileName;
    }

    public boolean isFileOpened() {
        return fileOpened;
    }

    public String getOpenedFileName() {
        return openedFileName;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }


    public void openFile(String fileName) {
        if (fileOpened) {
            System.out.println("A file is already opened. Please close the current file first.");
            return;
        }
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(file);
            document.getDocumentElement().normalize();
            fileOpened = true;
            currentFileName = fileName;
            System.out.println("File opened successfully: " + fileName);
        } catch (Exception e) {
            System.out.println("Error: Could not open file.");
            e.printStackTrace();
        }
    }

    public void closeFile() {
        if (!fileOpened) {
            System.out.println("No file is currently opened.");
            return;
        }
        document = null;
        fileOpened = false;
        currentFileName = null;
        System.out.println("File closed successfully.");
    }

    public void readFileContent() {
        if (openedFileName != null) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(new File(openedFileName));
                document.getDocumentElement().normalize();
            } catch (Exception e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("No file opened.");
        }
    }

    public void appendToFile(Book book) {
        if (document != null) {
            Element root = document.getDocumentElement();
            if (root == null) {

                root = document.createElement("library");
                document.appendChild(root);
            }

            Element booksElement = getOrCreateElement(root, "books");

            Element bookElement = document.createElement("book");
            bookElement.setAttribute("id", book.getId());

            Element authorElement = document.createElement("author");
            authorElement.appendChild(document.createTextNode(book.getAuthor()));
            bookElement.appendChild(authorElement);

            Element titleElement = document.createElement("title");
            titleElement.appendChild(document.createTextNode(book.getTitle()));
            bookElement.appendChild(titleElement);

            Element genreElement = document.createElement("genre");
            genreElement.appendChild(document.createTextNode(book.getGenre()));
            bookElement.appendChild(genreElement);

            Element descriptionElement = document.createElement("description");
            descriptionElement.appendChild(document.createTextNode(book.getDescription()));
            bookElement.appendChild(descriptionElement);

            Element yearElement = document.createElement("yearOfPublication");
            yearElement.appendChild(document.createTextNode(book.getYear()));
            bookElement.appendChild(yearElement);

            Element keywordsElement = document.createElement("keywords");
            keywordsElement.appendChild(document.createTextNode(book.getKeywords()));
            bookElement.appendChild(keywordsElement);

            Element ratingElement = document.createElement("rating");
            ratingElement.appendChild(document.createTextNode(book.getRating()));
            bookElement.appendChild(ratingElement);


            booksElement.appendChild(bookElement);
        } else {
            System.out.println("No document loaded.");
        }
    }

    private Element getOrCreateElement(Element parent, String tagName) {
        Element element = getElementByTagName(parent, tagName);
        if (element == null) {
            element = document.createElement(tagName);
            parent.appendChild(element);
        }
        return element;
    }

    private Element getElementByTagName(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return (Element) nodeList.item(0);
        }
        return null;
    }


    public void displayFileContent() {
        if (document != null) {
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(System.out);
                transformer.transform(domSource, streamResult);
            } catch (TransformerException e) {
                System.out.println("Error: Failed to display file content.");
                e.printStackTrace();
            }
        } else {
            System.out.println("The document is empty.");
        }
    }

    public Element getBookById(String id) {
        if (document != null) {
            NodeList nodeList = document.getElementsByTagName("book");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element bookElement = (Element) nodeList.item(i);
                if (bookElement.getAttribute("id").equals(id)) {
                    return bookElement;
                }
            }
        }
        return null;
    }

    public List<String> findBooksByAttribute(String attribute, String value) {
        List<String> booksInfo = new ArrayList<>();
        if (document != null) {
            NodeList books = document.getElementsByTagName("book");
            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                String attributeValue = getElementValue(book, attribute);
                if (attributeValue != null && attributeValue.equals(value)) {
                    booksInfo.add(getBookInfo(book));
                }
            }
        }
        return booksInfo;
    }

    private String getBookInfo(Element book) {
        StringBuilder info = new StringBuilder();
        info.append("ID: ").append(book.getAttribute("id")).append("\n");
        info.append("Author: ").append(getElementValue(book, "author")).append("\n");
        info.append("Title: ").append(getElementValue(book, "title")).append("\n");
        info.append("Genre: ").append(getElementValue(book, "genre")).append("\n");
        info.append("Description: ").append(getElementValue(book, "description")).append("\n");
        info.append("Year: ").append(getElementValue(book, "year")).append("\n");
        info.append("Keywords: ").append(getElementValue(book, "keywords")).append("\n");
        info.append("Rating: ").append(getElementValue(book, "rating")).append("\n");
        return info.toString();
    }

    public String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null) {
                return node.getTextContent();
            }
        }
        return "N/A";
    }

    public boolean deleteBookById(String id) {
        if (document != null) {
            NodeList books = document.getElementsByTagName("book");
            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                String bookId = book.getAttribute("id");
                if (bookId.equals(id)) {

                    book.getParentNode().removeChild(book);
                    return true;
                }
            }
        }
        return false;
    }



    public String getBookInfoSort(Element bookElement) {
        String id = bookElement.getAttribute("id");
        String author = getElementValue(bookElement, "author");
        String title = getElementValue(bookElement, "title");
        String genre = getElementValue(bookElement, "genre");
        String description = getElementValue(bookElement, "description");
        String year = getElementValue(bookElement, "year");
        String keywords = getElementValue(bookElement, "keywords");
        String rating = getElementValue(bookElement, "rating");

        return "ID: " + id + ", Author: " + author + ", Title: " + title +
                ", Genre: " + genre + ", Description: " + description +
                ", Year of Publication: " + year + ", Keywords: " + keywords +
                ", Rating: " + rating;
    }



}

