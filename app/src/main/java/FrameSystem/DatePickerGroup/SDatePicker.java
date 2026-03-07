package FrameSystem.DatePickerGroup;

import FrameSystem.SComponents.SDialog;
import MethodsSystem.MethodDate;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;

public class SDatePicker extends SDialog{

    private final String[] MONTH_TEXT = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
    private Date startDate, endDate, todayDate;
    
    private Calendar calendarToday;
    private int monthToday, yearToday, dayToday;
    
// Constructor ===============================================================================================
    
    public SDatePicker(JFrame parent, Date oldStartDate, Date oldEndDate){
        super(parent);
        initComponents();
        generateDefaultPanels();
        setDefault(oldStartDate, oldEndDate);
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent evt) -> {
            if(evt.getID() == KeyEvent.KEY_PRESSED) switch(evt.getKeyCode()){
                case KeyEvent.VK_ESCAPE, KeyEvent.VK_HOME -> {
                    buttonCancelMousePressed(null);
                }
                case KeyEvent.VK_ENTER -> {
                    buttonConfirmMousePressed(null);
                }
            }
            return false;
        });
        
        setVisible();
    }
    
// Main Methods ==============================================================================================
    
    private void setDefault(Date oldStartDate, Date oldEndDate){
        todayDate = new Date();
        
        calendarToday = Calendar.getInstance();
        calendarToday.setTime(todayDate);
        
        monthToday = calendarToday.get(Calendar.MONTH) + 1;
        yearToday = calendarToday.get(Calendar.YEAR);
        dayToday = calendarToday.get(Calendar.DAY_OF_MONTH);
        
        int month, year;
        
        if(oldStartDate != null){
            startDate = oldStartDate;
            if(oldEndDate != null) endDate = oldEndDate;
            
            selectDate = 2;
            
            int[] oldDate = MethodDate.toIntegerDate(startDate);
            month = oldDate[1];
            year = oldDate[2];
            
            setMonthDisplay(month);
            setYearDisplay(year);
        }else{
            month = monthToday;
            year = yearToday;

            setMonthDisplay(month);
            setYearDisplay(year);
        }
        
        showDate(month, year, 1);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private int monthDisplay, yearDisplay, yearTemp;
    
    private void setMonthDisplay(int month){
        monthDisplay = month;
        buttonMonth.setText(MONTH_TEXT[monthDisplay - 1]);
    }
    
    private void setYearDisplay(int year){
        yearDisplay = year;
        yearTemp = yearDisplay;
        buttonYear.setText(String.valueOf(yearDisplay));
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private boolean isTodayDate(int day, int month, int year){
        return day == dayToday && month == monthToday && year == yearToday;
    }
    
    private boolean isBetweenDate(int day, int month, int year){
        if(startDate == null || endDate == null) return false;
        
        Date compareDate = MethodDate.toDate(day, month, year);
        if(compareDate == null) return false;
        
        if(!startDate.after(compareDate) && !endDate.before(compareDate)){
            return true;
        }
        
        return false;
    }
    
    private int isSelectedDate(int day, int month, int year){
        
        int[] compareDate = new int[]{day, month, year};
        int[] strStartDate = MethodDate.toIntegerDate(startDate);
        int[] strEndDate = MethodDate.toIntegerDate(endDate);
        
        if(strStartDate != null && Arrays.equals(strStartDate, compareDate)){
            return 1;
        }
        if(strEndDate != null && Arrays.equals(strEndDate, compareDate)){
            return 2;
        }
        return 0;
    }
    
    private int selectDate = 1;
    
    private void selectDate(int day, int month, int year){
        Date date = MethodDate.toDate(day, month, year);
        if(date == null) return;
        
        switch(selectDate){
            case 1 -> {
                startDate = date;
                selectDate = 2;
            }
            case 2 -> {
                if(endDate != null && date.before(startDate)){
                    startDate = date;
                    break;
                }
                if(MethodDate.isEqual(startDate, date)){
                    endDate = null;
                    startDate = null;
                    selectDate = 1;
                    break;
                }
                if(endDate != null && MethodDate.isEqual(endDate, date)){
                    endDate = null;
                    selectDate = 2;
                }else{
                    endDate = date;
                }
            }
        }
        
        if(startDate != null && endDate != null && startDate.after(endDate)){
            Date temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        
        setMonthDisplay(month);
        setYearDisplay(year);
        
        showDate(month, year, 1);
    }
    
    private void selectMonthDisplay(int month, Integer panel){
        setMonthDisplay(month);
        showDate(monthDisplay, yearDisplay, panel);
    }
    
    private void selectYearDisplay(int year){
        setYearDisplay(year);
        showDate(monthDisplay, yearDisplay, 2);
    }
    
    private Calendar calendar = Calendar.getInstance();
    
    private boolean blockNextMonth = false, blockNextYear = false, blockNextYearTemp = false;
    
    private void showDate(int month, int year, Integer panel){
        calendar.set(year, month - 1, 1);
        
        if(panel == null) panel = panelShowing;
        
        blockNextYear = (year >= yearToday);
        blockNextMonth = (year >= yearToday) && (month >= monthToday);
        
        int startYear = year - 4;
        blockNextYearTemp = (year + 4 >= yearToday);
        
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if(blockNextYear && month > monthToday){
            selectMonthDisplay(monthToday, panel);
            return;
        }
        
        daysPanelWrapper.setVisible(false);
        for(int i = 0, d = 1; i < 42; i++){
            DatePickerPanel comp = daysPanelArray[i];
            comp.setToday(false);
            comp.setFont(new Font("Segoe UI", 0, 14));
            if(i >= startDay - 1 && d <= maxDay){
                comp.setText(String.valueOf(d));
                if(blockNextMonth && d > dayToday){
                    comp.setForeground(new Color(200, 200, 200));
                    comp.defaultDayPanel();
                }else{
                    comp.setForeground(new Color(255, 255, 255));
                    comp.setEnabled(true);
                    comp.setHoverable(true);
                    comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    comp.setActiveDate(isSelectedDate(d, month, year));
                    comp.setBetweenDates(isBetweenDate(d, month, year));
                    
                    if(isTodayDate(d, month, year)){
                        comp.setToday(true);
                        comp.setFont(new Font("Segoe UI", 1, 14));
                    }
                    
                    int selectDay = d;
                    comp.setDatePickerExecute(() -> {
                        selectDate(selectDay, monthDisplay, yearDisplay);
                    });
                }
                d++;
            }else{
                comp.setText("");
                comp.defaultDayPanel();
            }
        }
        daysPanelWrapper.setVisible(true);
        
        monthsPanelWrapper.setVisible(false);
        for(int i = 0; i < 12; i++){
            DatePickerPanel comp = monthsPanelArray[i];
            
            if(blockNextYear && i >= monthToday){
                comp.setForeground(new Color(200, 200, 200));
                comp.setEnabled(false);
                comp.setHoverable(false);
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                
                comp.setDatePickerExecute(null);
            }else{
                comp.setForeground(new Color(250, 250, 250));
                comp.setEnabled(true);
                comp.setHoverable(true);
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                
                int selectMonth = i + 1;
                comp.setDatePickerExecute(() -> {
                    selectMonthDisplay(selectMonth, 1);
                });
            }
        }
        monthsPanelWrapper.setVisible(true);
        
        yearsPanelWrapper.setVisible(false);
        for(int i = 0; i < 9; i++){
            DatePickerPanel comp = yearsPanelArray[i];
            comp.setText(String.valueOf(startYear));
            
            if(startYear > yearToday){
                comp.setForeground(new Color(200, 200, 200));
                comp.setEnabled(false);
                comp.setHoverable(false);
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    
                comp.setDatePickerExecute(null);
            }else{
                comp.setForeground(new Color(255, 255, 255));
                comp.setEnabled(true);
                comp.setHoverable(true);
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    
                int selectYear = startYear;
                comp.setDatePickerExecute(() -> {
                    selectYearDisplay(selectYear);
                });
            }
            startYear++;
        }
        yearsPanelWrapper.setVisible(true);
        
        showPanel(panel);
    }
    
// -----------------------------------------------------------------------------------------------------------
    
    private int panelShowing = 1;
    
    private void showPanel(int n){
        panelShowing = n;
        switch(n){
            case 1 -> {
                daysPanel.setVisible(true);
                monthsPanel.setVisible(false);
                yearsPanel.setVisible(false);
                
                buttonPrev.setText("Prev Month");
                buttonNext.setText("Next Month");
                
                if(blockNextMonth){
                    buttonNext.setEnabled(false);
                }else{
                    buttonNext.setEnabled(true);
                }
            }
            case 2 -> {
                daysPanel.setVisible(false);
                monthsPanel.setVisible(true);
                yearsPanel.setVisible(false);
                
                buttonPrev.setText("Prev Year");
                buttonNext.setText("Next Year");
                
                if(blockNextYear){
                    buttonNext.setEnabled(false);
                }else{
                    buttonNext.setEnabled(true);
                }
            }
            case 3 -> {
                daysPanel.setVisible(false);
                monthsPanel.setVisible(false);
                yearsPanel.setVisible(true);
                
                buttonPrev.setText("Prev");
                buttonNext.setText("Next");
                
                if(blockNextYearTemp){
                    buttonNext.setEnabled(false);
                }else{
                    buttonNext.setEnabled(true);
                }
            }
        }
    }
    
// -----------------------------------------------------------------------------------------------------------

    public Date[] getDates(){
        return new Date[]{startDate, endDate};
    }
    
// Generated =================================================================================================
    
    private DatePickerPanel[] daysPanelArray = new DatePickerPanel[42];
    private DatePickerPanel[] monthsPanelArray = new DatePickerPanel[12];
    private DatePickerPanel[] yearsPanelArray = new DatePickerPanel[9];
    
    private void generateDefaultPanels(){
        String[] days = new String[]{"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        for(String s : days){
            daysPanelWrapper.add(createComponent(String.valueOf(s), false));
        }
        for(int i = 0; i < 42; i++){
            DatePickerPanel comp = createComponent("", true);
            daysPanelWrapper.add(comp);
            daysPanelArray[i] = comp;
        }
        for(int i = 0; i < 12; i++){
            DatePickerPanel comp = createComponent(String.valueOf(MONTH_TEXT[i]), true);
            monthsPanelWrapper.add(comp);
            monthsPanelArray[i] = comp;
        }
        for(int i = 0; i < 9; i++){
            DatePickerPanel comp = createComponent("", true);
            yearsPanelWrapper.add(comp);
            yearsPanelArray[i] = comp;
        }
    }
    
    private DatePickerPanel createComponent(String text, boolean hoverable){
        DatePickerPanel comp = new DatePickerPanel();
        comp.setForeground(new Color(255, 255, 255));
        comp.setFont(new Font("Segoe UI", 0, 14));
        comp.setText(text);
        if(!hoverable){
            comp.setHoverable(hoverable);
            comp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return comp;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sPanel2 = new FrameSystem.SComponents.SPanel();
        sPanel1 = new FrameSystem.SComponents.SPanel();
        buttonPrev = new FrameSystem.SComponents.SLabelHover();
        buttonNext = new FrameSystem.SComponents.SLabelHover();
        buttonMonth = new FrameSystem.SComponents.SLabelHover();
        buttonYear = new FrameSystem.SComponents.SLabelHover();
        sLabel1 = new FrameSystem.SComponents.SLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        daysPanel = new FrameSystem.SComponents.SPanel();
        daysPanelWrapper = new FrameSystem.SComponents.SPanel();
        monthsPanel = new FrameSystem.SComponents.SPanel();
        monthsPanelWrapper = new FrameSystem.SComponents.SPanel();
        yearsPanel = new FrameSystem.SComponents.SPanel();
        yearsPanelWrapper = new FrameSystem.SComponents.SPanel();
        buttonConfirm = new FrameSystem.SComponents.SLabelHover();
        buttonCancel = new FrameSystem.SComponents.SLabelHover();
        buttonToday = new FrameSystem.SComponents.SLabelHover();
        buttonClear = new FrameSystem.SComponents.SLabelHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Date Picker");
        setResizable(false);

        sPanel2.setBackground(new java.awt.Color(24, 29, 37));

        sPanel1.setBackground(new java.awt.Color(30, 35, 46));
        sPanel1.setRadius(10);
        sPanel1.setRounded(true);
        sPanel1.setCornerUL(false);
        sPanel1.setCornerUR(false);
        sPanel1.setPreferredSize(new java.awt.Dimension(100, 60));

        buttonPrev.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonPrev.setForeground(new java.awt.Color(255, 255, 255));
        buttonPrev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonPrev.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonPrev.setRadius(15);
        buttonPrev.setText("Prev Month");
        buttonPrev.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        buttonPrev.setPreferredSize(new java.awt.Dimension(82, 35));
        buttonPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonPrevMousePressed(evt);
            }
        });

        buttonNext.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonNext.setForeground(new java.awt.Color(255, 255, 255));
        buttonNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonNext.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonNext.setRadius(15);
        buttonNext.setText("Next Month");
        buttonNext.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        buttonNext.setPreferredSize(new java.awt.Dimension(96, 35));
        buttonNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonNextMousePressed(evt);
            }
        });

        buttonMonth.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonMonth.setForeground(new java.awt.Color(255, 255, 255));
        buttonMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonMonth.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonMonth.setRadius(15);
        buttonMonth.setText("December");
        buttonMonth.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonMonth.setPreferredSize(new java.awt.Dimension(100, 35));
        buttonMonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonMonthMousePressed(evt);
            }
        });

        buttonYear.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonYear.setForeground(new java.awt.Color(255, 255, 255));
        buttonYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonYear.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonYear.setRadius(15);
        buttonYear.setText("2024");
        buttonYear.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonYear.setPreferredSize(new java.awt.Dimension(100, 35));
        buttonYear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonYearMousePressed(evt);
            }
        });

        sLabel1.setForeground(new java.awt.Color(255, 255, 255));
        sLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sLabel1.setText("-");

        javax.swing.GroupLayout sPanel1Layout = new javax.swing.GroupLayout(sPanel1);
        sPanel1.setLayout(sPanel1Layout);
        sPanel1Layout.setHorizontalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(buttonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        sPanel1Layout.setVerticalGroup(
            sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(sPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        daysPanel.setBackground(new java.awt.Color(24, 29, 37));

        daysPanelWrapper.setBackground(new java.awt.Color(24, 29, 37));
        daysPanelWrapper.setLayout(new java.awt.GridLayout(7, 7, 10, 10));

        javax.swing.GroupLayout daysPanelLayout = new javax.swing.GroupLayout(daysPanel);
        daysPanel.setLayout(daysPanelLayout);
        daysPanelLayout.setHorizontalGroup(
            daysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, daysPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(daysPanelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        daysPanelLayout.setVerticalGroup(
            daysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(daysPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(daysPanelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLayeredPane1.add(daysPanel, "card2");

        monthsPanel.setBackground(new java.awt.Color(24, 29, 37));

        monthsPanelWrapper.setBackground(new java.awt.Color(24, 29, 37));
        monthsPanelWrapper.setLayout(new java.awt.GridLayout(4, 3, 10, 10));

        javax.swing.GroupLayout monthsPanelLayout = new javax.swing.GroupLayout(monthsPanel);
        monthsPanel.setLayout(monthsPanelLayout);
        monthsPanelLayout.setHorizontalGroup(
            monthsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monthsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(monthsPanelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        monthsPanelLayout.setVerticalGroup(
            monthsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(monthsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(monthsPanelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLayeredPane1.add(monthsPanel, "card3");

        yearsPanel.setBackground(new java.awt.Color(24, 29, 37));

        yearsPanelWrapper.setBackground(new java.awt.Color(24, 29, 37));
        yearsPanelWrapper.setLayout(new java.awt.GridLayout(3, 3, 10, 10));

        javax.swing.GroupLayout yearsPanelLayout = new javax.swing.GroupLayout(yearsPanel);
        yearsPanel.setLayout(yearsPanelLayout);
        yearsPanelLayout.setHorizontalGroup(
            yearsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearsPanelLayout.createSequentialGroup()
                .addComponent(yearsPanelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        yearsPanelLayout.setVerticalGroup(
            yearsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearsPanelLayout.createSequentialGroup()
                .addComponent(yearsPanelWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLayeredPane1.add(yearsPanel, "card4");

        buttonConfirm.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonConfirm.setForeground(new java.awt.Color(255, 255, 255));
        buttonConfirm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonConfirm.setHoverColor(new java.awt.Color(86, 158, 240));
        buttonConfirm.setRadius(15);
        buttonConfirm.setText("Confirm");
        buttonConfirm.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonConfirm.setPreferredSize(new java.awt.Dimension(82, 35));
        buttonConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonConfirmMousePressed(evt);
            }
        });

        buttonCancel.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonCancel.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonCancel.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonCancel.setRadius(15);
        buttonCancel.setText("Cancel");
        buttonCancel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonCancel.setPreferredSize(new java.awt.Dimension(82, 35));
        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonCancelMousePressed(evt);
            }
        });

        buttonToday.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonToday.setForeground(new java.awt.Color(255, 255, 255));
        buttonToday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonToday.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonToday.setRadius(15);
        buttonToday.setText("Go To Today");
        buttonToday.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonToday.setPreferredSize(new java.awt.Dimension(82, 35));
        buttonToday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonTodayMousePressed(evt);
            }
        });

        buttonClear.setDefaultColor(new java.awt.Color(34, 41, 52));
        buttonClear.setForeground(new java.awt.Color(255, 255, 255));
        buttonClear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonClear.setHoverColor(new java.awt.Color(46, 55, 70));
        buttonClear.setRadius(15);
        buttonClear.setText("Clear");
        buttonClear.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        buttonClear.setPreferredSize(new java.awt.Dimension(82, 35));
        buttonClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buttonClearMousePressed(evt);
            }
        });

        javax.swing.GroupLayout sPanel2Layout = new javax.swing.GroupLayout(sPanel2);
        sPanel2.setLayout(sPanel2Layout);
        sPanel2Layout.setHorizontalGroup(
            sPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
            .addGroup(sPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(sPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(buttonToday, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        sPanel2Layout.setVerticalGroup(
            sPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(sPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonToday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(sPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonConfirmMousePressed
        confirmed = true;
        this.dispose();
    }//GEN-LAST:event_buttonConfirmMousePressed

    private void buttonCancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMousePressed
        confirmed = false;
        this.dispose();
    }//GEN-LAST:event_buttonCancelMousePressed

    private void buttonPrevMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPrevMousePressed
        switch(panelShowing){
            case 1 -> {
                if(monthDisplay == 1){
                    monthDisplay = 12;
                    yearDisplay--;
                }else{
                    monthDisplay--;
                }
                buttonMonth.setText(MONTH_TEXT[monthDisplay - 1]);
                buttonYear.setText(String.valueOf(yearDisplay));
                showDate(monthDisplay, yearDisplay, 1);
            }
            case 2 -> {
                setYearDisplay(yearDisplay - 1);
                showDate(monthDisplay, yearDisplay, 2);
            }
            case 3 -> {
                yearTemp -= 9;
                showDate(monthDisplay, yearTemp, 3);
            }
        }
    }//GEN-LAST:event_buttonPrevMousePressed

    private void buttonNextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonNextMousePressed
        switch(panelShowing){
            case 1 -> {
                if(blockNextMonth) return;
                
                if(monthDisplay == 12){
                    monthDisplay = 1;
                    yearDisplay++;
                }else{
                    monthDisplay++;
                }
                buttonMonth.setText(MONTH_TEXT[monthDisplay - 1]);
                buttonYear.setText(String.valueOf(yearDisplay));
                showDate(monthDisplay, yearDisplay, 1);
            }
            case 2 -> {
                if(blockNextYear) return;
                
                setYearDisplay(yearDisplay + 1);
                showDate(monthDisplay, yearDisplay, 2);
            }
            case 3 -> {
                if(blockNextYearTemp) return;
                
                yearTemp += 9;
                showDate(monthDisplay, yearTemp, 3);
            }
        }
    }//GEN-LAST:event_buttonNextMousePressed

    private void buttonMonthMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMonthMousePressed
        if(panelShowing == 2){
            showDate(monthDisplay, yearDisplay, 1);
        }else{
            showDate(monthDisplay, yearDisplay, 2);
        }
    }//GEN-LAST:event_buttonMonthMousePressed

    private void buttonYearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonYearMousePressed
        if(panelShowing == 3){
            showDate(monthDisplay, yearDisplay, 1);
        }else{
            showDate(monthDisplay, yearDisplay, 3);
        }
    }//GEN-LAST:event_buttonYearMousePressed

    private void buttonTodayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonTodayMousePressed
        Date date = new Date();
        String[] today = formatDate.format(date).split("-");
        
        int tempMonth = Integer.parseInt(today[1]);
        int tempYear = Integer.parseInt(today[2]);
        
        setMonthDisplay(tempMonth);
        setYearDisplay(tempYear);
        showDate(tempMonth, tempYear, 1);
    }//GEN-LAST:event_buttonTodayMousePressed

    private void buttonClearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonClearMousePressed
        startDate = null;
        endDate = null;
        selectDate = 1;
        
        showDate(monthDisplay, yearDisplay, null);
    }//GEN-LAST:event_buttonClearMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private FrameSystem.SComponents.SLabelHover buttonCancel;
    private FrameSystem.SComponents.SLabelHover buttonClear;
    private FrameSystem.SComponents.SLabelHover buttonConfirm;
    private FrameSystem.SComponents.SLabelHover buttonMonth;
    private FrameSystem.SComponents.SLabelHover buttonNext;
    private FrameSystem.SComponents.SLabelHover buttonPrev;
    private FrameSystem.SComponents.SLabelHover buttonToday;
    private FrameSystem.SComponents.SLabelHover buttonYear;
    private FrameSystem.SComponents.SPanel daysPanel;
    private FrameSystem.SComponents.SPanel daysPanelWrapper;
    private javax.swing.JLayeredPane jLayeredPane1;
    private FrameSystem.SComponents.SPanel monthsPanel;
    private FrameSystem.SComponents.SPanel monthsPanelWrapper;
    private FrameSystem.SComponents.SLabel sLabel1;
    private FrameSystem.SComponents.SPanel sPanel1;
    private FrameSystem.SComponents.SPanel sPanel2;
    private FrameSystem.SComponents.SPanel yearsPanel;
    private FrameSystem.SComponents.SPanel yearsPanelWrapper;
    // End of variables declaration//GEN-END:variables
}
