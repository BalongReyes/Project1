
package MainSystem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class CustomGraphics{

    public final static RenderingHints qualityHints;
    
    static{
        qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }
    
    public static Graphics2D getGraphics2D(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHints(qualityHints);
        return g2;
    }

}
