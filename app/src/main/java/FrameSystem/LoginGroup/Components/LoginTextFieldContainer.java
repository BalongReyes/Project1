
package FrameSystem.LoginGroup.Components;

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
public class LoginTextFieldContainer extends SPanel{

    private JTextField textFieldChild = null;
    
    private boolean focused = false;
    private Color focusedColor = Color.white;
    
// Constructor ===============================================================================================
    
    public LoginTextFieldContainer(){
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

    @BeanProperty(preferred = true, description = "The color of the highlighted border")
    public void setFocusedColor(Color focusedColor){
        this.focusedColor = focusedColor;
    }

    public Color getFocusedColor(){
        return focusedColor;
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
            g2.fillRoundRect(0, 10, 3, 30, 3, 3);
        }
        
        super.paint(g);
    }
    
}
