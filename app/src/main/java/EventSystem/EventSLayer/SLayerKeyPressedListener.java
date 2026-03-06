
package EventSystem.EventSLayer;

import java.awt.event.KeyEvent;
import java.util.EventListener;

@FunctionalInterface
public interface SLayerKeyPressedListener extends EventListener{

    public void layeredPanelKeyPressed(KeyEvent evt);

}
