
package EventSystem.Interface;

import java.awt.event.MouseListener;

@FunctionalInterface
public interface InnerListener{

    public void addInnerListeners(MouseListener listener);
    
}
