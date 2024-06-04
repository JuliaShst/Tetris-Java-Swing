package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape; // member variables
    private Color color;
    private int x, y;
    private int[][][] shapes; // array of arrays of int arrays...4 shapes with different rotation angles
    private int currentRotation;
    
    private Color[] availableColors = {
        new Color(233, 196, 106),   // Dark yellow
        new Color(231, 111, 81),     // Dark red
        new Color(42, 157, 135),     //  Green
        new Color(14, 93, 125),   //  Blue
        new Color(244, 162, 97) // Orange
    };

    
    public TetrisBlock(int[][] shape) { // member parameters 
        this.shape = shape;
        
        initShapes();
    }
    
    private void initShapes() {
        shapes = new int[4][][];
        
        for (int i = 0; i < 4; i++) {
            int r = shape[0].length; // N of rows in new array is N of columns in given array
            int c = shape.length;
            
            shapes[i] = new int[r][c];
                                                                           
            for (int y = 0; y < r; y++) {                                       // shape
                for (int x = 0; x < c; x++) {              // shapes[i]         |0-0|0-1|      shapes[i]         |0-0|0-1|
                    shapes[i][y][x] = shape[c - x - 1][y]; // |0-0|0-1|0-2|     |1-0|1-1|      |2-0|1-0|0-0|     |1-0|1-1|
                }                                          // |1-0|1-1|1-2|     |2-0|2-1|      |2-1|1-1|0-1|     |2-0|2-1|
            }
            shape = shapes[i];
        }
    }
    
    public void spawn(int gridWidth) {
        Random r = new Random();
        
        currentRotation = r.nextInt(shapes.length);
        shape = shapes[currentRotation]; // bc the height and weight of the block depends on the shape
        
        y = -getHeight();
        x = r.nextInt(gridWidth - getWidth());
        
        color = availableColors[r.nextInt(availableColors.length)];
    }
    
    public int[][] getShape(){ return shape; } // accessor 
    
    public Color getColor(){ return color; }
    
    public int getHeight(){ return shape.length; }
    
    public int getWidth(){ return shape[0].length; }
    
    public int getX(){ return x; }
    public void setX(int newX){ x = newX; }
    
    public int getY(){ return y; }
    public void setY(int newY){ y = newY; }
    
    public void moveDowm(){ y++; }
    
    public void moveLeft(){ x--; }
    
    public void moveRight(){ x++; }
    
    public void rotate() {
        currentRotation++;
        if( currentRotation > 3 ) currentRotation = 0;
        shape = shapes[currentRotation];
    }
    
    public void rotateBack() {
        currentRotation--;
        if ( currentRotation < 0 ) currentRotation = 3;
        shape = shapes[currentRotation];
    }
    
    public int getBottomEdge(){ return y + getHeight(); }
    
    public int getRightEdge() { return x + getWidth(); }
    
    public int getLeftEdge() { return x; } 
}
