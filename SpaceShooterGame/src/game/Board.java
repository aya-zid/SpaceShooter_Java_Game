package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	
	private int energy=500;
	ImageIcon k= new ImageIcon("space.gif");
	Image space=k.getImage();
	  private Timer timer;
	  private SpaceShip spaceship;
	  private List<Alien> aliens;
	  private List<Asteroid> asteroids;
	  private boolean ingame;
	  private final int B_WIDTH = 850;
	  private final int B_HEIGHT = 650;
	  private final int[][] pos = {
		        {1000, 30}, {2500, 100}, {1380, 200},
		        {1490, 300}, {3000, 400}, {1200, 500},
		        {3000, 30}, {5000, 100}, {4000, 200},
		        {4500, 300}, {7000, 400}, {3690, 500}
		    };
	  private final int[][] posast = {{-200,-200},{600,600},{-400,900}};
    public Board() {

    	addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        ingame=true;
        spaceship = new SpaceShip(40, 60);
        initAliens();
        initAsteroids();
        timer = new Timer(15, this);
        timer.start();
    }
    
        
    public void initAliens() {
      
        aliens = new ArrayList<>();

        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));
        }
    }
    public void initAsteroids() {
    	asteroids=new ArrayList<>();
    	int i=0;
    	for (int[] p : posast) {
            asteroids.add(new Asteroid(p[0], p[1],i));i++;
        }
    	
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(space, 1,   1, this);
        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
       String number="aliens left : "+aliens.size() ;      String health="energy = "+energy;
       Font small = new Font("Helvetica", Font.BOLD, 14);
       FontMetrics fm = getFontMetrics(small);

       g.setColor(Color.white);
       g.setFont(small);
    	g.drawString(health,7, 20);g.drawString(number,690, 20);
        if(spaceship.isVisible())
        g.drawImage(spaceship.getImage(), spaceship.getX(),
                spaceship.getY(), this);

        List<bullet> ms = spaceship.getbullets();

        for (bullet bl : ms) {
            
            g.drawImage(bl.getImage(), bl.getX(),
                    bl.getY(), this);
        }
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }
        for (Asteroid ast : asteroids) {
            if (ast.isVisible()) {
                g.drawImage(ast.getImage(), ast.getX(), ast.getY(), this);
            }}

    }
    private void drawGameOver(Graphics g) {
    	String msg = "game over u won !!";
    	if (energy<=0) msg="game over u lost !!";
        Font small = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    	
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        updateSpaceShip();
        updatebullets();
        updateAliens();
        updateAsteroids();
        checkCollisions();
       
        repaint();
    }
    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }
   
    private void updatebullets() {

        List<bullet> missiles = spaceship.getbullets();

        for (int i = 0; i < missiles.size(); i++) {

            bullet bl = missiles.get(i);

            if (bl.isVisible()) {

                bl.move();
            } else {

                missiles.remove(i);
            }
        }
    }

    private void updateSpaceShip() {

        spaceship.move();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceship.keyPressed(e);
        }
    }

private void updateAliens() {

    if (aliens.isEmpty()) {

        ingame = false;
        return;
    }

    for (int i = 0; i < aliens.size(); i++) {

        Alien a = aliens.get(i);
        
        if (a.isVisible()) {
            a.move();
        } else {
            aliens.remove(i);
        }
    }
}
private void updateAsteroids() {

    if (asteroids.isEmpty()) {

        ingame = false;
        return;
    }

    for (int i = 0; i < asteroids.size(); i++) {

        Asteroid a = asteroids.get(i);
        
        if (a.isVisible()) {
            a.move();
        } else {
            asteroids.remove(i);
        }
    }
}
public void checkCollisions() {

	  if(energy==0)
          ingame = false;
    Rectangle r3 = spaceship.getBounds();

    for (Alien alien : aliens) {
        
        Rectangle r2 = alien.getBounds();

        if (r3.intersects(r2)) {
            energy--;
            if(energy==0)
            ingame = false;
        }
    }
   
    for (Asteroid ast : asteroids) {
        Rectangle r4 = ast.getBounds();

        if (r3.intersects(r4)) {
            energy--;
           if(energy==0) ingame = false;
        }
        }


    List<bullet> ms = spaceship.getbullets();

    for (bullet m : ms) {

        Rectangle r1 = m.getBounds();

        for (Alien alien : aliens) {

            Rectangle r2 = alien.getBounds();

            if (r1.intersects(r2)) {
                
                m.setVisible(false);
                alien.setVisible(false);
            }
        }
        for (Asteroid ast : asteroids) {
            Rectangle r4 = ast.getBounds();
            if (r1.intersects(r4))
            	{m.setVisible(false);
            	}
            	}
    }
}

}