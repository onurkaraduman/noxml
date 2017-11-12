package com.noxml.editor;

import com.noxml.editor.handler.type.XmlEventHandlerAction;
import com.noxml.editor.tab.edit.EditTab;
import com.noxml.editor.tab.edit.pane.EditPane;
import com.noxml.editor.tab.history.HistoryTab;
import com.noxml.editor.tab.xml.XmlTab;
import com.noxml.editor.tab.xml.XmlTreeCell;
import com.noxml.editor.tab.xml.XmlTreeView;
import com.noxml.logging.Logger;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;

public class XmlEditor implements Editor {

    public static Logger LOG = Logger.getLogger(XmlEditor.class);

    private XmlTab xmlTab;
    private EditTab editTab;
    private HistoryTab historyTab;

    public XmlEditor(String path, XmlTreeView treeView, EditPane gridPane, TableView tableView) throws DocumentException {
        xmlTab = new XmlTab(path, treeView);
        editTab = new EditTab(gridPane);
        historyTab = new HistoryTab();
        xmlTab.getXmlTreeView().addEventHandler(XmlEventHandlerAction.ITEM_CLICK, event -> {
            editTab.laodCell((XmlTreeCell) event.getSource());
        });
        xmlTab.getXmlTreeView().addEventHandler(XmlEventHandlerAction.ADD_FIELD, event -> {
            editTab.createNewElement();
        });
    }

    public XmlEditor(XmlTreeView treeView, EditPane gridPane, TableView tableView) throws DocumentException {
        xmlTab = new XmlTab(treeView);
        editTab = new EditTab(gridPane);
        historyTab = new HistoryTab();
        xmlTab.getXmlTreeView().addEventHandler(XmlEventHandlerAction.ITEM_CLICK, event -> {
            editTab.laodCell((XmlTreeCell) event.getSource());
        });
        xmlTab.getXmlTreeView().addEventHandler(XmlEventHandlerAction.ADD_FIELD, event -> {
            editTab.createNewElement();
        });
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void save() throws IOException {
        String xmlPath = xmlTab.getXmlTreeView().getXmlPath();
        xmlTab.export(xmlPath);
    }

    @Override
    public void saveAs(String path) throws IOException {
        xmlTab.export(path);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void close() {
        // any component can be used to reach the window
        Stage stage = (Stage) xmlTab.getXmlTreeView().getScene().getWindow();
        stage.close();
    }

    @Override
    public void open() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Xml files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(App.stage);
        String xmlPath = file.getAbsolutePath();
        try {
            xmlTab.loadXml(xmlPath);
        } catch (DocumentException e) {
            LOG.error("Error during loading xml. Path:" + xmlPath, e);
        }

    }

    @Override
    public void help() {

    }

    @Override
    public void clear() {

    }

    @Override
    public void export(String path) throws IOException {

    }
}
