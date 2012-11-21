/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.w3c.dom.Element;
import static poseur.PoseurSettings.*;

/**
 *
 * @author Michael Salerno
 */
public class PoseurEllipse extends PoseurShape{

    private Ellipse2D.Double geometry;
    private static Ellipse2D.Double sharedGeometry = new Ellipse2D.Double();
    
    /**
     * PoseurRectangle objects are constructed with their geometry, which
     * can be updated later via service methods.
     * 
     * @param initGeometry The geometry to associate with this Ellipse.
     */
    public PoseurEllipse( Ellipse2D.Double initGeometry)
    {
        super();
        geometry = initGeometry;
    }

    /**
     * This static method constructs and returns a new Ellipse with an
     * x location of the poseSpaceX argument, a y location of poseSpaceY,
     * and a width and height of 0.
     * 
     * @param poseSpaceX The requested x value for the new, factory
     * built PoseurEllipse object.
     * 
     * @param poseSpaceY The requested y value for the new, factory
     * built PoseurEllipse object.
     * 
     * @return A constructed PoseurEllipse.
     */    
    public static PoseurEllipse factoryBuildRectangle(int poseSpaceX, int poseSpaceY)
    {
        Ellipse2D.Double ellipse = new Ellipse2D.Double(poseSpaceX, poseSpaceY,0,0);
        return new PoseurEllipse(ellipse);        
    }

    /**
     * Accessor method for getting this shape type.
     * 
     * @return The PoseurShapeType associated with this object.
     */    
    @Override
    public PoseurShapeType getShapeType() { return PoseurShapeType.ELLIPSE; }

        
    /**
     * This method tests if the testPoint argument is inside this
     * Ellipse. If it does, we return true, if not, false.
     * 
     * @param testPoint The point we want to test and see if it is
     * inside this Ellipse
     * 
     * @return true if the point is inside this Ellipse, false otherwise.
     */
    @Override
    public boolean containsPoint(Point2D testPoint)
    {
        return geometry.contains(testPoint);
    }
   
    /**
     * This method renders this Ellipse to whatever context the g2 argument
     * comes from. 
     * 
     * @param g2 The graphics context for rendering. It may refer to that
     * of a canvas or an image.
     * 
     * @param poseOriginX The x coordinate location of the pose box.
     * 
     * @param poseOriginY The y coordinate location of the pose box.
     * 
     * @param zoomLevel Used for scaling all that gets rendered.
     * 
     * @param isSelected Selected items are highlighted.
     */   
    @Override
    public void render(Graphics2D g2, int poseOriginX, int poseOriginY, float zoomLevel, boolean isSelected) {
        sharedGeometry.x = poseOriginX + (geometry.x * zoomLevel);
        sharedGeometry.y = poseOriginY + (geometry.y * zoomLevel);
        sharedGeometry.width = geometry.width * zoomLevel;
        sharedGeometry.height = geometry.height * zoomLevel;
        
        renderShape(g2, sharedGeometry, isSelected);
    }
    
    /**
     * This method makes a clone, i.e. a duplicate, of this Ellipse. This
     * is useful for cut/copy/paste types of operations in applications.
     * 
     * @return A constructed object that is identical to this one.
     */    
    @Override
    public PoseurShape clone()
    {
        Ellipse2D.Double copyGeometry = (Ellipse2D.Double)geometry.clone();
        
        // SINCE Color AND Stroke ARE IMMUTABLE,
        // WE DON'T MIND SHARING THEM 
        PoseurShape copy = new PoseurEllipse( copyGeometry);
        copy.fillColor = this.fillColor;
        copy.outlineColor = this.outlineColor;
        copy.outlineThickness = this.outlineThickness;
        copy.alpha = this.alpha;
        
        return copy;
    }
 
    /**
     * This method moves this shape to the x, y location without doing
     * any error checking on whether it's a good location or not.
     * 
     * @param x The x coordinate of where to move this Ellipse.
     * 
     * @param y The y coordinate of where to move this Ellipse.
     */
    @Override
    public void move(int x, int y)
    {
        geometry.x = x;
        geometry.y = y;
    }

    /**
     * This is a smarter method for moving this Ellipse, it considers
     * the pose area and prevents it from being moved off the pose area
     * by clamping at the edges.
     * 
     * @param incX The amount to move this Ellipse in the x-axis.
     * 
     * @param incY The amount to move this Ellipse in the y-axis.
     * 
     * @param poseArea The box in the middle of the rendering canvas
     * where the shapes are being rendered.
     */
    @Override
    public void moveShape(  int incX, int incY, 
                            Rectangle2D.Double poseArea) 
    {
        // MOVE THE SHAPE
        geometry.x += incX;
        geometry.y += incY;
        
        // AND NOW CLAMP IT SO IT DOESN'T GO OFF THE EDGE
        
        // CLAMP ON LEFT SIDE
        if (geometry.x < 0)
        {
            geometry.x = 0;
        }
        // CLAMP ON RIGHT
        if ((geometry.x + geometry.width) > poseArea.width)
        {
            geometry.x = poseArea.width - geometry.width -1;
        }
        // CLAMP ON TOP
        if (geometry.y < 0)
        {
            geometry.y = 0;
        }
        // CLAMP ON BOTTOM
        if ((geometry.y + geometry.height) > poseArea.height)
        {
            geometry.y = poseArea.height - geometry.height - 1;
        }
    }
    
    /**
     * This method tests to see if the x,y arguments would be valid
     * lower-right corner points for a Ellipse in progress.
     * 
     * @param x The x-axis coordinate for the test point.
     * 
     * @param y The y-axis coordinate for the test point.
     * 
     * @return true if (x,y) would be a valid lower-right corner
     * point based on where this Ellipse is currently located.
     */  
    @Override
    public boolean completesValidShape(int x, int y)
    {
        // WE ONLY LET SHAPES BE BUILT TOP LEFT TO BOTTOM RIGHT
        if ( (x < geometry.x) ||
             (y < geometry.y))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * This method helps to update the a Ellipse that's being
     * sized, testing to make sure it doesn't draw in illegal 
     * coordinates.
     * 
     * @param updateX The x-axis coordinate for the update point.
     * 
     * @param updateY The y-axis coordinate for the update point.
     */  
    @Override
    public void updateShapeInProgress(int updateX, int updateY)
    {
        if (updateX < geometry.x)
        {
            geometry.width = 0;
        }
        else
        {
            geometry.width = updateX - geometry.x;
        }
        
        if (updateY < geometry.y)
        {
            geometry.height = 0;
        }
        else
        {
            geometry.height = updateY - geometry.y;
        }    
    }
    
    /**
     * This method helps to build a .pose file. Ellipse know what data
     * they have, so this fills in the geometryNode argument DOC element
     * with the Ellipse data that would be needed to recreate it when
     * it's loaded back from the .pose (xml) file.
     * 
     * @param geometryNode The node where we'll put attributes regarding
     * the geometry of this Ellipse.
     */    
    @Override
    public void addNodeData(Element geometryNode)
    {
        geometryNode.setAttribute(SHAPE_TYPE_ATTRIBUTE, getShapeType().name());
        geometryNode.setAttribute(X_ATTRIBUTE, "" + geometry.x);
        geometryNode.setAttribute(Y_ATTRIBUTE, "" + geometry.y);
        geometryNode.setAttribute(WIDTH_ATTRIBUTE, "" + geometry.width);
        geometryNode.setAttribute(HEIGHT_ATTRIBUTE, "" + geometry.height);
    }
    
}
