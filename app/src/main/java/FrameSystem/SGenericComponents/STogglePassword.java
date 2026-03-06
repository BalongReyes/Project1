
package FrameSystem.SGenericComponents;

import FrameSystem.SComponents.SLabelHover;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.ImageIcon;
import EventSystem.Listeners.MousePressedAdaptor;
import FrameSystem.SComponents.SPasswordField;

@JavaBean(description = "A component that will toggle password field to show or hide")
public class STogglePassword extends SLabelHover{

    private boolean show = false;
    private SPasswordField sPasswordField = null;
    
// Constructor ===============================================================================================
    
    public STogglePassword(){
        super();
        addMouseListener((MousePressedAdaptor) evt -> {
            if(sPasswordField == null) return;
            showPassword(!show);
        });
        
        setText("");
        setIconSize(15);
        setScaledIcon(new ImageIcon(getClass().getResource("/Icons/showPassword.png")));
    }

// Setters and Getters =======================================================================================
    
    @BeanProperty(preferred = true, description = "set SPasswordField to toggle")
    public void setPasswordField(SPasswordField sPasswordField){
        this.sPasswordField = sPasswordField;
    }

    public SPasswordField getPasswordField(){
        return sPasswordField;
    }
    
// Methods ===================================================================================================
    
    public void showPassword(boolean show){
        if(sPasswordField == null) return;
        this.show = show;
        sPasswordField.showPassword(show);
        if(show){
            setScaledIcon(new ImageIcon(getClass().getResource("/Icons/hidePassword.png")));
        }else{
            setScaledIcon(new ImageIcon(getClass().getResource("/Icons/showPassword.png")));
        }
    }
    
}
