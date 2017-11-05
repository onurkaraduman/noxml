package noxml.editor;

import noxml.editor.listener.AddListener;
import noxml.editor.listener.move.MoveListener;
import noxml.editor.listener.RemoveListener;
import noxml.editor.listener.SearchListener;
import noxml.editor.util.XmlEditorUtil;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Onur Karaduman
 * @since 31.10.17
 */
public class XmlTreeCell extends TreeCell<Object> implements Serializable {

    private HBox editLine = new HBox();

    private ContextMenu contextMenu;
    private Map<DefaultAttribute, TextField> attributeTextFieldMap;
    private Map<DefaultAttribute, Label> attributeLabelMap;

    public XmlTreeCell(EventHandler listener) {
        initContextMenu();
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            editAttribute();
            commitEdit(getItem());
            listener.handle(event);
        });
    }

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            String text = "";
            if (item instanceof DefaultElement) {
                text = XmlEditorUtil.asString((DefaultElement) item);
            } else if (item instanceof String) {
                text = (String) item;
            } else {
                text = getText();
            }
            if (!isEditing()) {
                setText(text);
                setGraphic(getTreeItem().getGraphic());
                setContextMenu(contextMenu);
            }
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (attributeTextFieldMap == null) {
            createTextFields();
        }
        setGraphic(editLine);
        setText(null);
    }

    private void createTextFields() {
        attributeTextFieldMap = new HashMap<>();
        Object item = getItem();
        if (item instanceof DefaultElement) {
            DefaultElement element = (DefaultElement) item;
            List attributes = element.attributes();
            Label lblName = new Label(element.getName());
            editLine.getChildren().add(lblName);
            for (Object attributeObj : attributes) {
                DefaultAttribute attribute = (DefaultAttribute) attributeObj;
                Label label = new Label(attribute.getName());
                editLine.getChildren().add(label);
                TextField textField = createTextField(attribute.getValue());
                editLine.getChildren().add(textField);
                attributeTextFieldMap.put(attribute, textField);
            }
        }
    }

    private TextField createTextField(String value) {
        TextField textField = new TextField(value);
        textField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                editAttribute();
                commitEdit(getItem());
            } else if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
        return textField;
    }


    private void editAttribute() {
        if (getItem() != null && getItem() instanceof DefaultElement && attributeTextFieldMap != null) {
            DefaultElement item = (DefaultElement) getItem();
            for (Object o : item.attributes()) {
                DefaultAttribute attribute = (DefaultAttribute) o;
                TextField textField = attributeTextFieldMap.get(attribute);
                attribute.setValue(textField.getText());
                int attrIndex = ((DefaultElement) getItem()).attributes().indexOf(o);
                ((DefaultElement) getItem()).attributes().set(attrIndex, attribute);
            }
            attributeTextFieldMap = null;
        }
    }

    private void initContextMenu() {
        contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("Add Field");
        menuItem.setOnAction(new AddListener(this));
        contextMenu.getItems().add(menuItem);

        MenuItem remove = new MenuItem("Remove");
        remove.setOnAction(new RemoveListener(this));
        contextMenu.getItems().add(remove);

        MenuItem search = new MenuItem("Search");
        search.setOnAction(new SearchListener(this));
        contextMenu.getItems().add(search);

        setOnDragDetected(new MoveListener.DetectListener(this));
        setOnDragDropped(new MoveListener.DroppedListener(this));
        setOnDragOver(new MoveListener.DragOverListener(this));
//        setOnDragDone(new MoveListener.DragDoneListener(this));
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
