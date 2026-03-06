
package EventSystem.EventSLayer;

import java.util.EventListener;

@FunctionalInterface
public interface SLayerShowListener extends EventListener{

    public void layeredPanelShow(SLayerShowEvent evt);

}
