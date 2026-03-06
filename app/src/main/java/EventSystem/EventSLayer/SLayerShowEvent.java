
package EventSystem.EventSLayer;

import java.util.EventObject;

public class SLayerShowEvent extends EventObject{

    public boolean alreadyShowing;
    public String recent;
    
    public SLayerShowEvent(Object source, boolean alreadyShowing, String recent){
        super(source);
        this.alreadyShowing = alreadyShowing;
        this.recent = recent;
    }

}
