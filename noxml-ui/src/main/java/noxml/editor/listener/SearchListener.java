package noxml.editor.listener;

import noxml.editor.XmlTreeCell;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author Onur Karaduman
 * @since 03.11.17
 */
public class SearchListener implements EventHandler {

    private XmlTreeCell treeCell;

    public SearchListener(XmlTreeCell treeCell) {
        this.treeCell = treeCell;
    }

    @Override
    public void handle(Event event) {

    }
}
