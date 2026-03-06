
package FrameSystem.SGenericComponents;

import EventSystem.Listeners.MouseClickedAdaptor;
import FrameSystem.SComponents.SPanel;
import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.BeanProperty;
import java.beans.JavaBean;
import javax.swing.JTextField;

@JavaBean(description = "A component that displays a jpanel with corner radius")
public class STextFieldContainer extends SPanel{

    private JTextField textFieldChild = null;
    
    private boolean focused = false;
    private Color focusedColor = Color.white;
    
// Constructor ===============================================================================================
    
    public STextFieldContainer(){
        setOpaque(false);
        setPreferredSize(new Dimension(50, 50));
    }
    
// Setters and Getters =======================================================================================

    @BeanProperty(preferred = true, description = "Setup listener to the child")
    public void setTextFieldChild(JTextField textFieldChild){
        if(this.textFieldChild != null || textFieldChild == null) return;
        
        this.textFieldChild = textFieldChild;
        textFieldChild.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt){
                super.focusGained(evt);
                focused = true;
                repaint();
            }
            @Override
            public void focusLost(FocusEvent evt){
                super.focusLost(evt);
                focused = false;
                repaint();
            }
        });
        
        addMouseListener((MouseClickedAdaptor) evt -> {
            textFieldChild.requestFocus();
        });
    }

    public JTextField getTextFieldChild(){
        return textFieldChild;
    }

    @BeanProperty(preferred = true, description = "")
    public void setFocusedColor(Color focusedColor){
        this.focusedColor = focusedColor;
    }

    public Color getFocusedColor(){
        return focusedColor;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private int lineGap = 0;

    @BeanProperty(preferred = true, visualUpdate = true, description = "")
    public void setLineGap(int lineGap){
        this.lineGap = lineGap;
    }

    public int getLineGap(){
        return lineGap;
    }
    
// Overrided Methods =========================================================================================

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, s.width, s.height, radius, radius);
        
        if(focused){
            g2.setColor(focusedColor);
            g2.fillRoundRect(0, lineGap, 3, s.height - (lineGap * 2), 3, 3);
        }
        
        super.paintOverrideAll(g);
    }
    
}
