package noxml.editor;

import java.io.IOException;

/**
 * @author Onur Karaduman
 * @since 02.11.17
 */
public interface Editor {
    void undo();

    void redo();

    void save();

    void refresh();

    void close();

    void open(String path);

    void help();

    void clear();

    void export(String path) throws IOException;
}
