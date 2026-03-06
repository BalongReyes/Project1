
package EventSystem.EventSLayer;

import java.util.EventListener;

@FunctionalInterface
public interface SLayerBeforeShowListener extends EventListener{

    public boolean layeredPanelBeforeShow(SLayerBeforeShowEvent evt);

}
