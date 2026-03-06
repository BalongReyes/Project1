
package EventSystem.EventSLayer;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.EventListener;

public class SLayerEventMulticaster implements SLayerShowListener, SLayerHideListener, SLayerBeforeShowListener, SLayerKeyPressedListener{

    protected final EventListener a;
    protected final EventListener b;
    
    protected SLayerEventMulticaster(EventListener a, EventListener b){
        this.a = a; 
        this.b = b;
    }
    
    protected EventListener remove(EventListener oldl) {
        if (oldl == a)  return b;
        if (oldl == b)  return a;
        EventListener a2 = removeInternal(a, oldl);
        EventListener b2 = removeInternal(b, oldl);
        if (a2 == a && b2 == b) {
            return this;        // it's not here
        }
        return addInternal(a2, b2);
    }
    
// ===========================================================================================================

    @Override
    public void layeredPanelShow(SLayerShowEvent evt){
        ((SLayerShowListener)a).layeredPanelShow(evt);
        ((SLayerShowListener)b).layeredPanelShow(evt);
    }
    
    @Override
    public boolean layeredPanelHide(SLayerHideEvent evt){
        boolean l1 = ((SLayerHideListener)a).layeredPanelHide(evt);
        boolean l2 = ((SLayerHideListener)b).layeredPanelHide(evt);
        return l1 || l2;
    }

    @Override
    public boolean layeredPanelBeforeShow(SLayerBeforeShowEvent evt){
        boolean l1 = ((SLayerBeforeShowListener)a).layeredPanelBeforeShow(evt);
        boolean l2 = ((SLayerBeforeShowListener)b).layeredPanelBeforeShow(evt);
        return l1 || l2;
    }

    @Override
    public void layeredPanelKeyPressed(KeyEvent evt){
        ((SLayerKeyPressedListener)a).layeredPanelKeyPressed(evt);
        ((SLayerKeyPressedListener)b).layeredPanelKeyPressed(evt);
    }
    
// ===========================================================================================================
    
    public static SLayerShowListener add(SLayerShowListener a, SLayerShowListener b) {
        return (SLayerShowListener)addInternal(a, b);
    }
    
    public static SLayerHideListener add(SLayerHideListener a, SLayerHideListener b) {
        return (SLayerHideListener)addInternal(a, b);
    }
    
    public static SLayerBeforeShowListener add(SLayerBeforeShowListener a, SLayerBeforeShowListener b) {
        return (SLayerBeforeShowListener)addInternal(a, b);
    }
    
    public static SLayerKeyPressedListener add(SLayerKeyPressedListener a, SLayerKeyPressedListener b) {
        return (SLayerKeyPressedListener)addInternal(a, b);
    }
    
// ===========================================================================================================
    
    public static SLayerShowListener remove(SLayerShowListener l, SLayerShowListener oldl) {
        return (SLayerShowListener)removeInternal(l, oldl);
    }
    
    public static SLayerHideListener remove(SLayerHideListener l, SLayerHideListener oldl) {
        return (SLayerHideListener)removeInternal(l, oldl);
    }
    
    public static SLayerBeforeShowListener remove(SLayerBeforeShowListener l, SLayerBeforeShowListener oldl) {
        return (SLayerBeforeShowListener)removeInternal(l, oldl);
    }
    
    public static SLayerKeyPressedListener remove(SLayerKeyPressedListener l, SLayerKeyPressedListener oldl) {
        return (SLayerKeyPressedListener)removeInternal(l, oldl);
    }
    
// ===========================================================================================================

    protected static EventListener addInternal(EventListener a, EventListener b) {
        if(a == null) return b;
        if(b == null) return a;
        return new SLayerEventMulticaster(a, b);
    }
    
    protected static EventListener removeInternal(EventListener l, EventListener oldl) {
        if(l == oldl || l == null){
            return null;
        }else if(l instanceof SLayerEventMulticaster bm){
            return bm.remove(oldl);
        }else{
            return l;           // it's not here
        }
    }
    
    private static int getListenerCount(EventListener l, Class<?> listenerType){
        if(l instanceof SLayerEventMulticaster bm) {
            return getListenerCount(bm.a, listenerType) + getListenerCount(bm.b, listenerType);
        }else{
            // Only count listeners of correct type
            return listenerType.isInstance(l) ? 1 : 0;
        }
    }
    
    private static int populateListenerArray(EventListener[] a, EventListener l, int index){
        if(l instanceof SLayerEventMulticaster bm){
            int lhs = populateListenerArray(a, bm.a, index);
            return populateListenerArray(a, bm.b, lhs);
        }else if(a.getClass().getComponentType().isInstance(l)){
            a[index] = l;
            return index + 1;
        }
        // Skip nulls, instances of wrong class
        else {
            return index;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends EventListener> T[] getListeners(EventListener l, Class<T> listenerType){
        if (listenerType == null) {
            throw new NullPointerException ("Listener type should not be null");
        }
        int n = getListenerCount(l, listenerType);
        T[] result = (T[])Array.newInstance(listenerType, n);
        populateListenerArray(result, l, 0);
        return result;
    }
    
}
