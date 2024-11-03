package bg.tu_varna.sit;

public class Help implements Command{
    public void execute(String[] args) {
        System.out.println("The following commands are supported:");
        System.out.println("open <file> - opens <file>");
        System.out.println("save - saves the currently open file");
        System.out.println("saveas <source file> <target file> - saves the currently open file in <file>");
        System.out.println("close - closes the currently opened file");
        System.out.println("exit - exits the program");
        System.out.println("help - prints this information");
        System.out.println("books all - displays all books");
        System.out.println("books info <id> - displays information about the book with the specified <id>");
        System.out.println("books add - adds a new book (prompts for details interactively)");
        System.out.println("books find <attribute> <value> - finds books by the specified attribute and value");
        System.out.println("books delete <id> - deletes the book with the specified <id>");
        System.out.println("books sort <attribute> [desc] - sorts books by the specified attribute in ascending order, or descending order if 'desc' is specified");
    }
}

