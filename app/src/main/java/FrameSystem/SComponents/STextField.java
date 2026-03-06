
package FrameSystem.SComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.JTextField;

@JavaBean(description = "A textfield with a placeholder")
public class STextField extends JTextField{

    private String hint = "";
    private Color hintColor = new Color(255, 255, 255);
    private int hintOffest = 25;

    public STextField(){
        super();
        setOpaque(false);
        setBorder(null);
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

    @BeanProperty(preferred = true, visualUpdate = true, description = "The offset of the hint that will be displayed")
    public void setHintOffest(int hintOffest){
        this.hintOffest = hintOffest;
    }

    public int getHintOffest(){
        return hintOffest;
    }

// Methods ===================================================================================================

    private Font hintFont;
    
    @Override
    public void setFont(Font f){
        super.setFont(f);
        hintFont = f;
    }
    
// Overrided Methods =========================================================================================

    @Override
    public void paint(Graphics g){
        if(getText().isEmpty()){
            g.setFont(hintFont);
            g.setColor(hintColor);
            g.drawString(hint, 0, hintOffest);
        }
        super.paint(g);
    }
    
}
