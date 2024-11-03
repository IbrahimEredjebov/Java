package bg.tu_varna.sit;

public class Close implements Command {
    private Menu menu;

    public Close(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        menu.closeFile();
    }
}

