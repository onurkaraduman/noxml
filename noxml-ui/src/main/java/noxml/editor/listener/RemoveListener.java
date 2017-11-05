package noxml.editor.listener;

import noxml.editor.XmlTreeCell;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import org.dom4j.Element;

/**
 * @author Onur Karaduman
 * @since 03.11.17
 */
public class RemoveListener implements EventHandler {

    private XmlTreeCell treeCell;

    public RemoveListener(final XmlTreeCell treeCell) {
        this.treeCell = treeCell;
    }

    @Override
    public void handle(Event event) {
        TreeItem c = treeCell.getTreeItem();
        c.getParent().getChildren().remove(c);
        Element item = (Element) treeCell.getItem();
        item.getParent().content().remove(item);
    }
}
