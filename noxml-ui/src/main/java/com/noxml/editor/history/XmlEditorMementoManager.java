package com.noxml.editor.history;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Onur Karaduman
 * @since 01.11.17
 */
public class XmlEditorMementoManager {

    private XmlEditorMemento currentState;
    private List<XmlEditorMemento> mementoList;


    public XmlEditorMementoManager() {
        mementoList = new ArrayList<>();
    }

    public void save(Element element) {
        currentState = new XmlEditorMemento((Element) element.clone());
        mementoList.add(currentState);
    }

    public XmlEditorMemento getCurrentState() {
        return currentState;
    }

    public XmlEditorMemento undo() {
        int i = mementoList.indexOf(currentState);
        if (i > 0) {
            return mementoList.get(i - 1);
        } else {
            return mementoList.get(0);
        }
    }

    public XmlEditorMemento redo() {
        int i = mementoList.indexOf(currentState);
        if (i == mementoList.size() - 1) {
            return mementoList.get(i);
        } else {
            return mementoList.get(i + 1);
        }
    }
}
