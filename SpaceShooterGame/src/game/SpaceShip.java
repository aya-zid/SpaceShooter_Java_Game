package game;


import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SpaceShip extends movingobj {
    private Clip clip;
    private int dx;
    private int dy;
    private List<bullet> bullets;

    public SpaceShip(int x, int y) {
        super(x, y);
        bullets = new ArrayList<>();
        loadImage("spaceship.gif"); 
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
    }

   

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void fire() {
        bullets.add(new bullet(x + width, y + height / 2));
        playshot();
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    public void playshot()
    {
    	File file = new File("shot.wav");
    	  try {
    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    	 clip = AudioSystem.getClip();
    	  clip.open(audioStream);
          clip.addLineListener(new LineListener() {

    		@Override
    		public void update(LineEvent event) {
    			// TODO Auto-generated method stub
    			if (event.getType()==LineEvent.Type.STOP)
    				clip.close();
    			
    		}});
    	  clip.start();
    	  }
    	  catch(Exception e)
    	  {System.out.println(e.getMessage()); }
    }	
    public List<bullet> getbullets() {
        return bullets;
    }
}
