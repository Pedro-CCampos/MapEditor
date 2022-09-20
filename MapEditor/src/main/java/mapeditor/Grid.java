package mapeditor;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

    private Rectangle grid;
    private int maxCols = 25;
    private int maxRows = 25;
    private int cellSize = 25;
    private int pointerPositionX= 35;
    private int pointerPositionY = 35;
    private int displacement = 10;
    private Rectangle[][] quadrados;
    private int width = maxCols * cellSize;
    private int height = maxRows * cellSize;

    public int getCellSize() {
        return cellSize;
    }

    public int getPointerPositionY() {
        return pointerPositionY;
    }

    public int getPointerPositionX() {
        return pointerPositionX;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Grid() {

        //Squares squares = new Squares();
        Pointer pointer = new Pointer(this);
        grid = new Rectangle(displacement,displacement,width, height);
        grid.draw();
        int y = 10;
        quadrados = new Rectangle[maxCols][maxRows];

        for(int rows = 0; rows < maxRows; rows++) {
            for (int cols = 0; cols < maxCols; cols++) {
                grid = new Rectangle(displacement,y, cellSize, cellSize);
                quadrados[cols][rows] = grid;
                quadrados[cols][rows].draw();
                displacement += cellSize;
            }
            y += cellSize;
            displacement = 10;

        }
        pointer.setSquares(quadrados);
    }

}
