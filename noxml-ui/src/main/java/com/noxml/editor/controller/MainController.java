package com.noxml.editor.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import com.noxml.editor.XmlEditor;
import org.dom4j.DocumentException;


/**
 * @author Onur Karaduman
 * @since 03.11.17
 */
public class MainController {

    @FXML
    private HistoryTabController historyTabController;

    @FXML
    private XmlTabController xmlTabController;

    @FXML
    private EditTabController editTabController;

    @FXML
    private MenuBarController menuController;

    public static XmlEditor editor;

    @FXML
    private void initialize() throws DocumentException {
        String xmlPath = "noxml-ui/src/main/resources/com/noxml/tree/fix44-short.xml";
        editor = new XmlEditor(xmlPath, xmlTabController.treeView, editTabController.gpEdit, historyTabController.tblHistory);
        menuController.injectXmlTabController(xmlTabController);
    }
}
