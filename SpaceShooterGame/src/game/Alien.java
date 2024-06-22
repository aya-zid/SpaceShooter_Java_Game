package game;

public class Alien extends movingobj {
	
    public Alien(int x, int y) {
        super(x, y);
        loadImage("alien.gif");
        getImageDimensions();
    }
    public void move() {

        if (x < 0) {
            x = 900;
        }

        x -= 2;
    }
}