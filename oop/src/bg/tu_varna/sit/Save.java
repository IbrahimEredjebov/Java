package bg.tu_varna.sit;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Save implements Command {
    private Menu menu;

    public Save(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid syntax. Usage: save");
            return;
        }

        if (menu.isFileOpened()) {
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(menu.getDocument());
                StreamResult result = new StreamResult(new File(menu.getOpenedFileName()));
                transformer.transform(source, result);
                System.out.println("File saved successfully.");
            } catch (TransformerException e) {
                System.out.println("Error: Failed to save file.");
            }
        } else {
            System.out.println("No file opened. Please open a file first.");
        }
    }
}
