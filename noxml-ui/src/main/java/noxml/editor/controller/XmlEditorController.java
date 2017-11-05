package noxml.editor.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import noxml.editor.XmlEditor;
import noxml.editor.XmlTreeView;
import org.dom4j.DocumentException;


/**
 * @author Onur Karaduman
 * @since 03.11.17
 */
public class XmlEditorController {


    @FXML
    VBox vBoxLay;

    @FXML
    XmlTreeView treeView;

    @FXML
    TableView tblHistory;

    public void initialize() throws DocumentException {
        XmlEditor xmlEditor = new XmlEditor("noxml-ui/src/main/resources/noxml/tree/fix44-short.xml", treeView);

    }
}
