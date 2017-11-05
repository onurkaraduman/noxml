package noxml.editor;

import com.noxml.log.Logger;
import com.noxml.reader.XmlReader;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import noxml.editor.listener.XmlTreeViewListener;
import noxml.editor.listener.type.XmlTreeViewAction;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.tree.DefaultText;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Onur Karaduman
 * @since 02.11.17
 */
public class XmlTreeView extends TreeView {

    public static Logger LOG = Logger.getLogger(XmlTreeView.class);

    private Map<XmlTreeViewAction, List<XmlTreeViewListener>> actionListMap = new HashMap<>();
    private Document document;
    private TreeItem<Object> selectedItem;

    private EventHandler cellClickHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (getSelectedItems() != null && getSelectedItems().size() > 0) {
                if (selectedItem != getSelectedItems().get(0)) {
                    selectedItem = getSelectedItems().get(0);
                }
            } else {
                LOG.error("SelectedItems couldn't be null");
            }
        }
    };

    public XmlTreeView() {
        init();
    }

    private void init() {
        setEditable(true);
        setCellFactory(param -> new XmlTreeCell(cellClickHandler));
    }

    public void loadXml(String path) throws DocumentException {
        XmlReader xmlReader = new XmlReader(Paths.get(path));
        document = xmlReader.getDocument();
        Element rootElement = document.getRootElement();
        recreateTreeItem(rootElement);
    }


    public void addActionListener(XmlTreeViewAction action, XmlTreeViewListener listener) {
        List<XmlTreeViewListener> xmlTreeViewListeners = actionListMap.get(action);
        if (xmlTreeViewListeners == null) {
            xmlTreeViewListeners = new ArrayList<>();
        }
        xmlTreeViewListeners.add(listener);
        actionListMap.put(action, xmlTreeViewListeners);
    }

    /**
     * Convert tree view to xml
     *
     * @return
     * @throws IOException
     */
    public String toXml() throws IOException {
        TreeItem<Object> root = getRoot();
        Element value = (Element) root.getValue();
        document.setRootElement(value);
        return document.asXML();

    }

    /**
     * Creating treeItems from scratch with given root element
     *
     * @param element
     */
    public void recreateTreeItem(Element element) {
        setRoot(convertToTreeItem(element));
    }

    public Element getRootElement() {
        return (Element) getRoot().getValue();
    }

    public Document getDocument() {
        return document;
    }

    public List<TreeItem> getSelectedItems() {
        return (List<TreeItem>) getSelectionModel().getSelectedItems().stream().collect(Collectors.toList());
    }

    /**
     * Creating treeItems from xml element with recursion
     *
     * @param element
     * @return
     */
    private TreeItem<Object> convertToTreeItem(Object element) {
        TreeItem<Object> treeItem = new TreeItem<>(element);
        if (element instanceof Element) {
            for (Object o : ((Element) element).content()) {
                if (o instanceof DefaultText) {
                    DefaultText defaultText = (DefaultText) o;
                    String text = defaultText.getText();
                    String newline = System.getProperty("line.separator");
                    if (!text.isEmpty() && !text.contains(newline)) {
                        TreeItem<Object> treeTextItem = new TreeItem<>(o);
                        treeItem.getChildren().add(treeItem);
                    }
                }
                if (o instanceof Element) {
                    treeItem.getChildren().add(convertToTreeItem(o));
                }
            }
        }
        return treeItem;
    }
}
