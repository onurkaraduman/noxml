package noxml.editor.listener;

import noxml.editor.XmlTreeCell;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author Onur Karaduman
 * @since 03.11.17
 */
public class AddListener implements EventHandler {
    private XmlTreeCell treeCell;

    public AddListener(final XmlTreeCell treeCell) {
        this.treeCell = treeCell;
    }


    @Override
    public void handle(Event event) {

    }
}
