package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import javax.sound.sampled.*;

public class start extends JFrame implements ActionListener{
	JButton b;int a=1;
	private Clip clip;
	public start()
	{
		super ("Space Shooter");
		setSize(850, 650);setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon k= new ImageIcon("background.gif");
        JLabel image=new JLabel(k);
        image.setBounds(0, -6, 850, 650);
        add(image);
		b= new JButton("start");b.setBounds(500, 500, 130, 40);b.setBackground(Color.LIGHT_GRAY);
		b.setFont(new Font("serif", Font.BOLD,25));
		image.add(b);
		b.addActionListener(this);
		setVisible(true);
		 playmusic();
	}

	 public void playmusic()
	    {
	    	File file = new File("music.wav");
	    	  try {
	    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
	    	 clip = AudioSystem.getClip();
	    	  clip.open(audioStream);
	
	    	  clip.start();
	    	  }
	    	  catch(Exception e)
	    	  {System.out.println(e.getMessage()); }
	    }	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		clip.stop();
		setVisible(false);
		gameframe a=new gameframe();
		a.setVisible(true);
		
	}

}
