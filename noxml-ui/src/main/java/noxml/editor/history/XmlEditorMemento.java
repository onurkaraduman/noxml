package noxml.editor.history;

import lombok.Getter;
import lombok.Setter;
import org.dom4j.Element;

/**
 * @author Onur Karaduman
 * @since 01.11.17
 */
@Getter
@Setter
public class XmlEditorMemento {
    private final Element state;

    public XmlEditorMemento(Element state) {
        this.state = state;
    }
}
