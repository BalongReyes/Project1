
package FrameSystem.LoginGroup.Components;

import FrameSystem.SComponents.SPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.Icon;
import javax.swing.ImageIcon;

@JavaBean(description = "A component that displays the login panel")
public class LoginPanel extends SPanel{

    private Icon backgroundIcon;
    private Image backgroundImage;
    
// Constructor ===============================================================================================
    
    public LoginPanel(){
        super();
    }
    
// Setters and Getters =======================================================================================

    @BeanProperty(preferred = true, visualUpdate = true, description = "The icon this component will display.")
    public void setBackgroundImage(Icon backgroundIcon){
        this.backgroundIcon = backgroundIcon;
        this.backgroundImage = ((ImageIcon)backgroundIcon).getImage();
    }

    public Icon getBackgroundImage(){
        return backgroundIcon;
    }
    
// Overrided Methods =========================================================================================

    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        Dimension s = getSize();
        g.drawImage(backgroundImage, 0, (s.height - 99), this);
    }

}
