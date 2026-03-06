
package FrameSystem.SComponents;

import MainSystem.CustomGraphics;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

@JavaBean(description = "A component that displays a jlabel that renders with RenderingHints")
public class SLabel extends JLabel{

// Constructor ===============================================================================================
    
    public SLabel(){
        super();
        setDefault();
    }
    
// Methods ===================================================================================================
    
    private void setDefault(){
        setFont(new java.awt.Font("Segoe UI", 1, 11));
    }
    
// Getters And Setters =======================================================================================
    
    protected ImageIcon scaledIcon = null;
    protected ImageIcon scaledImageIcon = null;

    @BeanProperty(preferred = true, visualUpdate = true, description = "Scaled icon")
    public void setScaledIcon(ImageIcon scaledIcon){
        this.scaledIcon = scaledIcon;
        if(scaledIcon == null){
            setIcon(null);
            return;
        }
        scaledImageIcon = scaledIcon;
        if(iconSize > 0){
            scaledImageIcon = new ImageIcon(scaledIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        }
        setIcon(scaledImageIcon);
    }

    public Icon getScaledIcon(){
        return scaledIcon;
    }

// -----------------------------------------------------------------------------------------------------------
    
    protected int iconSize = 16;

    @BeanProperty(preferred = true, visualUpdate = true, description = "Size of scaled icon")
    public void setIconSize(int iconSize){
        this.iconSize = iconSize;
        if(scaledIcon != null){
            scaledImageIcon = scaledIcon;
            if(iconSize > 0){
                scaledImageIcon = new ImageIcon(scaledIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            }
            setIcon(scaledImageIcon);
        }
    }

    public int getIconSize(){
        return iconSize;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    @Override
    @BeanProperty(hidden = true)
    public void setToolTipText(String text){
        super.setToolTipText(text);
    }

    @Override
    @BeanProperty(hidden = true)
    public void setBorder(Border border){
        super.setBorder(border);
    }
    
// Overrided Methods =========================================================================================
    
    @Override
    public void setCursor(Cursor cursor){
        super.setCursor(cursor);
    }
    
    public void paintOverrideAll(Graphics g){
        super.paint(g);
    }
    
    public void paintOverride(Graphics g, int n){
        super.paint(g);
    }
    
    public void paintOverride(Graphics g){
        super.paint(g);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    @Override
    @BeanProperty(preferred = true)
    public void setFont(Font font){
        super.setFont(font);
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        super.paint(g2);
    }

}
