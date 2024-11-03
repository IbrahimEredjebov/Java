package bg.tu_varna.sit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeTools {



    public static Node[] sortNodes(NodeList nl, String attributeName, boolean asc) {
        class NodeComparator<T> implements Comparator<T> {
            @Override
            public int compare(T a, T b) {
                int ret;
                Comparable bda = null, bdb = null;
                try {
                    Element elementA = (Element) a;
                    Element elementB = (Element) b;

                    String valueA = elementA.getElementsByTagName(attributeName).item(0).getTextContent();
                    String valueB = elementB.getElementsByTagName(attributeName).item(0).getTextContent();


                    bda = new BigDecimal(valueA);
                    bdb = new BigDecimal(valueB);
                } catch (Exception e) {
                    return 0;
                }
                ret = bda.compareTo(bdb);
                return asc ? ret : -ret;
            }
        }

        List<Node> x = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            x.add(nl.item(i));
        }
        Node[] ret = new Node[x.size()];
        ret = x.toArray(ret);
        Arrays.sort(ret, new NodeComparator<Node>());
        return ret;
    }

    public static boolean attributeExistsInAllNodes(NodeList nodeList, String attributeName) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            if (element.getElementsByTagName(attributeName).getLength() == 0) {
                return false;
            }
        }
        return true;
    }
}