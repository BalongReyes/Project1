
package EventSystem.EventSLayer;

import java.util.EventListener;

@FunctionalInterface
public interface SLayerHideListener extends EventListener{

    public boolean layeredPanelHide(SLayerHideEvent evt);
    
}
