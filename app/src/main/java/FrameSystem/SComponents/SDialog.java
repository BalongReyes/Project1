
package FrameSystem.SComponents;

import MainSystem.SFrame;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class SDialog extends JDialog{

    private SDialog childDialog, parentDialog;
    private JFrame parentJFrame;
    
// Constructor ===============================================================================================
    
    public SDialog(JFrame parent, SDialog parentDialog){
        this(parent);
        
        if(parentDialog == null) return;
        this.parentDialog = parentDialog;
        addToParentDialog(parentDialog);
    }   
    
    public SDialog(JFrame parent){    
        super(parent, true);
        this.parentJFrame = parent;
        
        getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(34, 39, 46));
        getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);
    }

// Main Methods ==============================================================================================

    private void addToParentDialog(SDialog parentDialog){
        parentDialog.childDialog = this;
    }
    
    public void setVisible(){
        SFrame.setKeyLock(true);
        
        if(parentDialog == null) visibleDialog = this;
        setLocationRelativeTo(parentJFrame);
        setVisible(true);
        
        SFrame.setKeyLock(false);
    }
    
    protected boolean confirmed = false;

    public boolean isConfirmed(){
        return confirmed;
    }

    @Override
    public void dispose(){
        if(childDialog != null) childDialog.dispose();
        super.dispose();
    }

// Static Methods ============================================================================================

    private static SDialog visibleDialog = null;
    
    public static void closeByAutoLogout(){
        if(visibleDialog == null) return;
        visibleDialog.confirmed = false;
        visibleDialog.dispose();
        visibleDialog = null;
    }
    
}
