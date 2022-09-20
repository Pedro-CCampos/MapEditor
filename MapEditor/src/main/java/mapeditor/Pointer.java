package mapeditor;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Pointer {

    private Rectangle pointer;
    private Grid grid;
    private Rectangle[][] squares;
    private Keyboard keyboard;

    private boolean paint = false;

    public Pointer(Grid grid) {
        Controls controls = new Controls();
        this.grid = grid;
        pointer = new Rectangle(grid.getPointerPositionX(), grid.getPointerPositionY(), grid.getCellSize(), grid.getCellSize());
        pointer.setColor(Color.GREEN);
        pointer.fill();
    }

    public void setSquares(Rectangle[][] squares) {
        this.squares = squares;
    }

    public void moveUp() {
        if (pointer.getY() > grid.getCellSize()) {
            pointer.translate(0, -grid.getCellSize());
        }
    }

    public void moveDown() {
        if (pointer.getY() < grid.getHeight() - grid.getCellSize()) {
            pointer.translate(0, grid.getCellSize());
        }
    }

    public void moveLeft() {
        if (pointer.getX() > grid.getCellSize()) {
            pointer.translate(-grid.getCellSize(), 0);
        }
    }

    public void moveRight() {
        if (pointer.getX() < grid.getWidth() - grid.getCellSize()) {
            pointer.translate(grid.getCellSize(), 0);
        }
    }

    public void paint() {

        int pointerCol = pointer.getX() / grid.getCellSize();
        int pointerRow = pointer.getY() / grid.getCellSize();


        if (paint) {
            if (!squares[pointerCol][pointerRow].isFilled()) {
                squares[pointerCol][pointerRow].setColor(Color.BLACK);
                squares[pointerCol][pointerRow].fill();
            } else {
                squares[pointerCol][pointerRow].draw();
            }
        }


    }


    public class Controls implements KeyboardHandler {

        public Controls() {

            keyboard = new Keyboard(this);

            KeyboardEvent w = new KeyboardEvent();
            w.setKey(KeyboardEvent.KEY_W);
            w.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent s = new KeyboardEvent();
            s.setKey(KeyboardEvent.KEY_S);
            s.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent a = new KeyboardEvent();
            a.setKey(KeyboardEvent.KEY_A);
            a.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent d = new KeyboardEvent();
            d.setKey(KeyboardEvent.KEY_D);
            d.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent space = new KeyboardEvent();
            space.setKey(KeyboardEvent.KEY_SPACE);
            space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent releaseSpace = new KeyboardEvent();
            releaseSpace.setKey(KeyboardEvent.KEY_SPACE);
            releaseSpace.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);


            keyboard.addEventListener(w);
            keyboard.addEventListener(s);
            keyboard.addEventListener(a);
            keyboard.addEventListener(d);
            keyboard.addEventListener(space);
            keyboard.addEventListener(releaseSpace);

        }


        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_W:
                    moveUp();
                    paint();
                    break;
                case KeyboardEvent.KEY_S:
                    moveDown();
                    paint();
                    break;
                case KeyboardEvent.KEY_A:
                    moveLeft();
                    paint();
                    break;
                case KeyboardEvent.KEY_D:
                    moveRight();
                    paint();
                    break;
                case KeyboardEvent.KEY_SPACE:
                    paint = true;
                    paint();
            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

            if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
                System.out.println("Stop painting");
                paint = false;
            }
        }
    }

}
