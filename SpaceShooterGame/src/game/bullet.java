package game;

public class bullet extends movingobj {

    public bullet(int x, int y) {
        super(x, y);
        loadImage("missile.png");  
        getImageDimensions();
    }
    
    public void move() {
        
        x += 3;
        
        if (x > 810) {
            visible = false;
        }
    }
}