
package FrameSystem.SComponents;

import java.awt.Graphics;
import java.awt.Image;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.Icon;
import javax.swing.ImageIcon;

@JavaBean(description = "A SLabel that is activatable")
public class SLabelActivatable extends SLabel{

// Constructor ===============================================================================================
    
    public SLabelActivatable(){
        super();
    }

// Setters and Getters =======================================================================================

    protected boolean active = false;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "If SLabel is active")
    public void setActive(boolean active){
        if(this.active == active) return;
        this.active = active;
        if(active){
            setIcon(scaledActiveImageIcon);
        }else{
            setIcon(scaledImageIcon);
        }
        repaint();
    }

    public boolean isActive(){
        return active;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    @Override
    public void setScaledIcon(ImageIcon scaledIcon){
        this.scaledIcon = scaledIcon;
        scaledImageIcon = scaledIcon;
        if(iconSize > 0){
            scaledImageIcon = new ImageIcon(scaledIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        }
        if(!active) setIcon(scaledImageIcon);
    }
    
    private ImageIcon scaledActiveIcon = null;
    private ImageIcon scaledActiveImageIcon = null;
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "Scaled active icon")
    public void setScaledActiveIcon(ImageIcon scaledActiveIcon){
        this.scaledActiveIcon = scaledActiveIcon;
        scaledActiveImageIcon = scaledActiveIcon;
        if(iconSize > 0){
            scaledActiveImageIcon = new ImageIcon(scaledActiveIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        }
        if(active) setIcon(scaledActiveImageIcon);
    }

    public Icon getScaledActiveIcon(){
        return scaledActiveIcon;
    }

// -----------------------------------------------------------------------------------------------------------
    
    @Override
    public void setIconSize(int iconSize){
        this.iconSize = iconSize;
        if(scaledIcon != null){
            scaledImageIcon = scaledIcon;
            if(iconSize > 0){
                scaledImageIcon = new ImageIcon(scaledIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            }
            if(!active) setIcon(scaledImageIcon);
        }
        if(scaledActiveIcon != null){
            scaledActiveImageIcon = scaledActiveIcon;
            if(iconSize > 0){
                scaledActiveImageIcon = new ImageIcon(scaledActiveIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            }
            if(active) setIcon(scaledActiveImageIcon);
        }
    }
    
// -----------------------------------------------------------------------------------------------------------

    @Override
    @BeanProperty(hidden = true)
    public void setIcon(Icon icon){
        super.setIcon(icon);
    }
    
// Overrided Methods =========================================================================================
    
    @Override
    public void paintOverrideAll(Graphics g){
        super.paintOverrideAll(g);
    }
    
    @Override
    public void paintOverride(Graphics g, int n){
        if(n > 0){
            n--;
            if(n > 0){
                super.paintOverride(g, n);
            }else{
                super.paintOverride(g);
            }
        }else{
            super.paint(g);
        }
    }
    
    @Override
    public void paintOverride(Graphics g){
        super.paint(g);
    }

}
