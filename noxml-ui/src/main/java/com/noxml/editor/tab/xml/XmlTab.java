package com.noxml.editor.tab.xml;

import com.noxml.editor.history.XmlEditorMementoManager;
import javafx.scene.control.TreeItem;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Onur Karaduman
 * @since 02.11.17
 */
public class XmlTab {

    private XmlTreeView xmlTreeView;
    private XmlEditorMementoManager mementoManager;

    /**
     * Open xml file with given path
     *
     * @param path
     */
    public XmlTab(String path, XmlTreeView treeView) throws DocumentException {
        init();
        this.xmlTreeView = treeView;
        xmlTreeView.loadXml(path);
    }

    public XmlTab() {
        init();
    }

    private void init() {
        if (xmlTreeView == null) {
            xmlTreeView = new XmlTreeView();
        }
        mementoManager = new XmlEditorMementoManager();
    }

    public void add() {

    }

    /**
     * Remove item from tree and remove element from parent node
     */
    public void remove() {
        List<TreeItem> selectedItems = xmlTreeView.getSelectedItems();
        for (TreeItem selectedItem : selectedItems) {
            selectedItem.getParent().getChildren().remove(selectedItem);
            ((Element) selectedItem.getParent().getValue()).remove((Element) selectedItem.getValue());
        }
    }

    public XmlTreeView getXmlTreeView() {
        return xmlTreeView;
    }

    public void undo() {
        xmlTreeView.recreateTreeItem(mementoManager.undo().getState());
    }

    public void redo() {
        xmlTreeView.recreateTreeItem(mementoManager.redo().getState());
    }

    public void save() {
        mementoManager.save(xmlTreeView.getRootElement());
    }

    public void refresh() {
        xmlTreeView.recreateTreeItem(mementoManager.getCurrentState().getState());
    }

    public void close() {
    }

    public void open(String path) {
    }

    public void help() {
    }

    public void clear() {
    }

    public void export(String path) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(path)) {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter(outputStream, format);
            writer.write(xmlTreeView.getDocument());
        }
    }
}
