package bg.tu_varna.sit;

public class DeleteBook implements Command {
    private Menu menu;

    public DeleteBook(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Invalid syntax. Usage: book delete <id>");
            return;
        }

        if (!menu.isFileOpened()) {
            System.out.println("No file opened. Please open a file first.");
            return;
        }

        String idToDelete = args[2];


        boolean deleted = menu.deleteBookById(idToDelete);
        if (deleted) {
            System.out.println("Book with ID " + idToDelete + " deleted successfully.");
        } else {
            System.out.println("No book found with ID " + idToDelete);
        }
    }
}

