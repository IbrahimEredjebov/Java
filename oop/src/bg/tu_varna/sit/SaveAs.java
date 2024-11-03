package bg.tu_varna.sit;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SaveAs implements Command {
    private Menu menu;

    public SaveAs(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(String[] args) {
        if (!menu.isFileOpened()) {
            System.out.println("No file opened. Please open a file first.");
            return;
        }

        if (args.length != 2) {
            System.out.println("Invalid syntax. Usage: saveas <newfilename>");
            return;
        }

        String newFileName = args[1].trim();


        if (!newFileName.toLowerCase().endsWith(".xml")) {
            newFileName += ".xml";
        }

        try {
            Document document = menu.getDocument();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(newFileName));

            transformer.transform(domSource, streamResult);
            System.out.println("File saved as " + newFileName + " successfully.");
        } catch (Exception e) {
            System.out.println("Error: Failed to save file.");
            e.printStackTrace();
        }
    }
}
