package bg.tu_varna.sit;

public class BookAll implements Command {
    private Menu menu;

    public BookAll(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        menu.displayFileContent();
    }
}

