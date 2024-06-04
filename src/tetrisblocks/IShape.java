package tetrisblocks;

import tetris.TetrisBlock;

public class IShape extends TetrisBlock {
    public IShape() {
        super(new int[][]{ {1, 1, 1, 1} });
    }
    
    // IShape's rotation is ugly, override it 
    // horisontal -> vertical -> move up and right 
    // vertical -> horisontal -> move down and left 
    @Override
    public void rotate() {
        super.rotate();
        
        if(this.getWidth() == 1) {
            this.setX( this.getX() + 1 );
            this.setY( this.getY() - 1 );
        }
        else {
            this.setX( this.getX() - 1 );
            this.setY( this.getY() + 1 );
        }
    }
    
}
