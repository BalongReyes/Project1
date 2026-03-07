
package FrameSystem.HeroGroup.Components;

import ConsoleSystem.Console;
import ConsoleSystem.ConsoleColors;
import FrameSystem.SAbstractComponents.SLayer;
import FrameSystem.SAbstractComponents.SLayerButton;
import MainSystem.Main;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class HeroLayer extends SLayer{

// Constructor ===============================================================================================
    
    public HeroLayer(){
        addLayer();
    }

    private void addLayer(){
        addLayer(this);
    }
    
// Implementations ===========================================================================================
    
    @Override
    protected void showLayeredPanel(){
        showLayer(this);
    }

// Static Methods ============================================================================================

    private static ArrayList<HeroLayer> layeredPanels = new ArrayList<>();
    private static HeroLayer currentLayeredPanel = null;
    
    private static void addLayer(HeroLayer layer){
        layeredPanels.add(layer);
    }
    
    public static HeroLayer getCurrentLayeredPanel(){
        return currentLayeredPanel;
    }
    
    public static void showLayer(HeroLayer showLayer){
        if(currentLayeredPanel != null && (!currentLayeredPanel.fireLayeredPanelHideListener(showLayer.getAccessibleContext().getAccessibleName()))){
            return;
        }
        if(!showLayer.fireLayeredPanelBeforeShowListener(currentLayeredPanel == showLayer, showLayer.toString())){
            return;
        }
        
        if(Main.debugDataHandlerRefresh) Console.line().out("SHOWING LAYER " + showLayer.getName(), ConsoleColors.GREEN);
        
        for(HeroLayer layer : layeredPanels){
            SLayerButton layeredButton = layer.getLayerButton();
            
            layer.setVisible(layer == showLayer);
            if(layeredButton != null) layeredButton.setActiveButton(layer == showLayer);
        }
        showLayer.fireLayeredPanelShowListener(currentLayeredPanel == showLayer, showLayer.toString());
        currentLayeredPanel = showLayer;
    }
    
    public static void keyPressed(KeyEvent evt){
        if(currentLayeredPanel == null) return;
        currentLayeredPanel.fireLayeredPanelKeyPressedListener(evt);
    }

}
