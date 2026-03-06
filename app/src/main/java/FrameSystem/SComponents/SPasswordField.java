
package FrameSystem.SComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

@JavaBean(description = "A passwordfield with a placeholder")
public class SPasswordField extends JPasswordField{

    private String hint = "";
    private Color hintColor = new Color(255, 255, 255);
    private int offsetHint = 26;

    public SPasswordField(){
        super();
        setOpaque(false);
        setFont(new java.awt.Font("Segoe UI", 1, 11));
        super.setBorder(null);
    }
    
// Setters and Getters =======================================================================================

    @BeanProperty(preferred = true, visualUpdate = true, description = "The hint that will be displayed")
    public void setHint(String hint){
        this.hint = hint;
    }

    public String getHint(){
        return hint;
    }

    @BeanProperty(preferred = true, visualUpdate = true, description = "The color of the hint that will be displayed")
    public void setHintColor(Color hintColor){
        this.hintColor = hintColor;
    }

    public Color getHintColor(){
        return hintColor;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description = "The offset of hint")
    public void setOffsetHint(int offsetHint){
        this.offsetHint = offsetHint;
    }

    public int getOffsetHint(){
        return offsetHint;
    }
    
    public void showPassword(boolean b){
        if(b){
            setEchoChar((char)0);
        }else{
            setEchoChar('\u2022');
        }
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
    public void paint(Graphics g){
        if(String.valueOf(getPassword()).isEmpty()){
            g.setColor(hintColor);
            g.drawString(hint, 0, offsetHint);
        }
        super.paint(g);
    }
    
}
