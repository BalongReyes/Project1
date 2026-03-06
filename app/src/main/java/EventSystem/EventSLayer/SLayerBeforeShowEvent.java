
package EventSystem.EventSLayer;

import java.util.EventObject;

public class SLayerBeforeShowEvent extends EventObject{

    public boolean alreadyShowing;
    public String recent;
    
    public SLayerBeforeShowEvent(Object source, boolean alreadyShowing, String recent){
        super(source);
        this.alreadyShowing = alreadyShowing;
        this.recent = recent;
    }

}
