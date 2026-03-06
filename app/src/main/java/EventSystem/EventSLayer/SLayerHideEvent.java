
package EventSystem.EventSLayer;

import java.util.EventObject;

public class SLayerHideEvent extends EventObject{
    
    public String next;
    
    public SLayerHideEvent(Object source, String next){
        super(source);
        this.next = next;
    }

}
