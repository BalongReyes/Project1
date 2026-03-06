
package EventSystem.Listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@FunctionalInterface
public interface DocumentAdapter extends DocumentListener{
    
    public void documentChanged(DocumentEvent evt);

    @Override
    default void insertUpdate(DocumentEvent evt) {
        documentChanged(evt);
    }
    
    @Override
    default void removeUpdate(DocumentEvent evt) {
        documentChanged(evt);
    }
    
    @Override
    default void changedUpdate(DocumentEvent evt) {
        documentChanged(evt);
    }
}
