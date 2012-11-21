package poseur.files;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import poseur.PoseurSettings;
import static poseur.PoseurSettings.*;
import poseur.state.ColorPalletState;

/**
 * This class can be used to load color pallet data from an XML file into a
 * constructed ColorPalletState. Note that the XML file must validate against
 * the color_pallet_settings.xsd schema. This class demonstrates how application
 * settings can be loaded dynamically from a file.
 *
 * @author Richard McKenna Debugging Enterprises
 * @version 1.0
 */
public class ColorPalletLoader {

    /**
     * This method will extract the data found in the provided XML file argument
     * and use it to load the color pallet argument.
     *
     * @param colorPalletXMLFile Path and file name to an XML file containing
     * information about a custom color pallet. Note this XML file must validate
     * against the aforementioned schema.
     *
     * @param colorPalletState The state manager for the color pallet, we'll
     * load all the data found in the XML file inside here.
     */
    public void initColorPallet(String colorPalletXMLFile, ColorPalletState colorPalletState) {
        XMLUtilities xmlLoader = new XMLUtilities();
        try {
            Document doc = xmlLoader.loadXMLDocument(colorPalletXMLFile, COLOR_PALLET_SETTINGS_SCHEMA);
            
            //the size of the pallet
            int pSize = xmlLoader.getIntData(doc, PoseurSettings.PALLET_SIZE_NODE);
            //how many rows there will be in the pallet
            int pRows = xmlLoader.getIntData(doc, PoseurSettings.PALLET_ROWS_NODE);
            //how many colors there are in the pallet
            int manyColors = xmlLoader.getNumNodesOfElement(doc, PoseurSettings.PALLET_COLOR_NODE);
            //an array to store all of the loaded colors
            Color[] colors = new Color[pSize];
            
            Node tmp;
            //to hold the RGB values
            int red, blue, green;
            Color defaultColor;

            //first get the default color
            tmp = xmlLoader.getNodeInSequence(doc, RED_NODE, 0);
            red = Integer.parseInt(tmp.getFirstChild().getNodeValue());

            tmp = xmlLoader.getNodeInSequence(doc, BLUE_NODE, 0);
            blue = Integer.parseInt(tmp.getFirstChild().getNodeValue());

            tmp = xmlLoader.getNodeInSequence(doc, GREEN_NODE, 0);
            green = Integer.parseInt(tmp.getFirstChild().getNodeValue());

            defaultColor = new Color(red, green, blue);
            
            //get the rest of the colors
            for (int i = 0; i < manyColors; i++) {
                tmp = xmlLoader.getNodeInSequence(doc, RED_NODE, i);                
                red = Integer.parseInt(tmp.getFirstChild().getNodeValue());
                
                tmp = xmlLoader.getNodeInSequence(doc, BLUE_NODE, i);                
                blue = Integer.parseInt(tmp.getFirstChild().getNodeValue());
                
                tmp = xmlLoader.getNodeInSequence(doc, GREEN_NODE, i);                
                green = Integer.parseInt(tmp.getFirstChild().getNodeValue());
                colors[i] = new Color(red, green, blue);
            }
            colorPalletState.loadColorPalletState(colors, pRows, manyColors, defaultColor);
            
        } catch (InvalidXMLFileFormatException ex) {
            Logger.getLogger(ColorPalletLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}