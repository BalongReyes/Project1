
package FrameSystem.DatePickerGroup;

import EventSystem.Listeners.MousePressedAdaptor;
import FrameSystem.SComponents.SLabelActivatableHover;
import MainSystem.CustomGraphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.SwingConstants;

public class DatePickerPanel extends SLabelActivatableHover{

    private DatePickerExecute datePickerExecute;
    
// Constructor ===============================================================================================
    
    public DatePickerPanel(){
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
        setHoverBackgroundColor(new Color(34, 41, 52));
        setActiveBackgroundColor(new Color(35, 116, 209));
        setInactiveBackgroundColor(new Color(24, 29, 37));
        setRadius(15);
        
        addMouseListener((MousePressedAdaptor) evt -> {
            if(datePickerExecute != null) datePickerExecute.select();
        });
    }

// Main Methods ==============================================================================================

    public void defaultDayPanel(){
        setEnabled(false);
        setHoverable(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setActive(false);
        setBetweenDates(false);
        setDatePickerExecute(null);
        setFont(new Font("Segoe UI", 0, 14));
    }
    
// Setters and Getters =======================================================================================

    public void setDatePickerExecute(DatePickerExecute datePickerExecute){
        this.datePickerExecute = datePickerExecute;
    }

    public DatePickerExecute getDatePickerExecute(){
        return datePickerExecute;
    }
    
// -----------------------------------------------------------------------------------------------------------

    protected Color defaultForegroundColor = Color.white;
    
    @Override
    public void setForeground(Color fg){
        super.setForeground(fg);
        defaultForegroundColor = fg;
    }
    
    public boolean betweenDates = false;

    public void setBetweenDates(boolean betweenDates){
        this.betweenDates = betweenDates;
    }

// -----------------------------------------------------------------------------------------------------------
    
    private boolean today = false;
    
    public void setToday(boolean b){
        today = b;
        if(!b) return;
        
        if(active){
            setForeground(defaultForegroundColor);
        }else{
            setForeground(todayForegroundColor);
        }
    }
    
// Overrided Methods =========================================================================================

    private int dateActive = 0;
    
    public void setActiveDate(int activeDate){
        super.setActive(activeDate != 0);
        dateActive = activeDate;
    }

    @Override
    public void setActive(boolean active){
        super.setActive(active);
        if(!active) dateActive = 0;
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private Color betweenBackgroundColor = new Color(25, 44, 68);
    private Color endActiveBackgroundColor = new Color(33, 87, 153);
    private Color todayForegroundColor = new Color(86, 158, 240);
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = CustomGraphics.getGraphics2D(g);
        Dimension s = getSize();
        
        if(active && betweenDates){
            g2.setColor(betweenBackgroundColor);
            g2.fillRoundRect(0, 0, s.width, s.height, radius, radius);
            if(dateActive != 0){
                switch(dateActive){
                    case 1 -> {
                        g2.setColor(activeBackgroundColor);
                        g2.fillRoundRect(0, 0, s.width - 5, s.height, radius, radius);
                    }
                    case 2 -> {
                        g2.setColor(endActiveBackgroundColor);
                        g2.fillRoundRect(5, 0, s.width - 5, s.height, radius, radius);
                    }
                }
            }
        }else{
            if(active){
                switch(dateActive){
                    case 1 -> g2.setColor(activeBackgroundColor);
                    case 2 -> g2.setColor(endActiveBackgroundColor);
                }
            }else if(betweenDates){
                g2.setColor(betweenBackgroundColor);
            }else if(hovering){
                g2.setColor(hoverBackgroundColor);
            }else{
                g2.setColor(inactiveBackgroundColor);
            }
            g2.fillRoundRect(0, 0, s.width, s.height, radius, radius);
        }
        
        if(today){
            if(active){
                g2.setColor(defaultForegroundColor);
            }else{
                g2.setColor(todayForegroundColor);
            }
            g2.fillRoundRect(26, 37, 10, 2, 2, 2);
        }
        
        super.paintOverrideAll(g);
    }
    
}
