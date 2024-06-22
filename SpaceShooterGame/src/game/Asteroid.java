package game;

public class Asteroid  extends movingobj{
    int a;
  
	public Asteroid(int x, int y,int a) {
		super(x, y);
		this.a=a;
		if(a==0) {
	        loadImage("asteroid.gif");
	        getImageDimensions();}
            else if(a==1) {
    	        loadImage("jupiter.gif");
    	        getImageDimensions();}
            else{
    	        loadImage("saturn.gif");
    	        getImageDimensions();}
	}

	    public void move() {

	    	  
	        if(a==0) {
	        	 if (x > 850) {
		               x = 0;
		            }
		    	    if (y > 650) {
		                y = 0;
		        }x += 2;y+=2;}
	        else if(a==1){
	        	 if (x <0) {
		               x = 850;
		            }
		    	    if (y < 0) {
		                y = 650;
		        }x -=2;y-=2;}
	        else
	        {
	        	if (x >850) {
		               x = 0;
		            }
		    	    if (y < 0) {
		                y = 650;
		        }x +=2;y-=2;}
	        }
	    
}
