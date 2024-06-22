package game;

import javax.swing.JFrame;

public class gameframe extends JFrame {
	 public gameframe (){
	        
	        add(new Board());
	   
	        setTitle("Space Shooter");
	        setSize(850, 650);
	        setLocationRelativeTo(null);
	        setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }

}
