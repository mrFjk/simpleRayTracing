package test;

import Window.Mouse;
import render.Render;

public class Main {
	
	public static int width = 1920/2;
	public static int height = 1080/2;
	public static int tcWidth = 1;
	public static int tcHeight = 1;
	
	public static Mouse mouse = null;
	public volatile static double[][][] colors;
	public volatile static int[][] iterations;
	
	public static void main(String[] args) {
		
		if (args.length > 0) {
			width = Integer.parseInt(args[0]);
			height = Integer.parseInt(args[1]);
			tcWidth = Integer.parseInt(args[2]);
			tcHeight = Integer.parseInt(args[3]);
		}
		colors = new double[width][height][3];
		iterations = new int[width][height];
		
		Runnable task = new Runnable() {
            public void run() {
            	Window.Display.show(width, height, "window", 200);
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        
        boolean b = true;
        while(b) {
        	if (mouse != null)
        		b = false;
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for (int i = 0; i < tcWidth; i++) {
        	for (int j = 0; j < tcHeight; j++) {
        		Render r = new Render(i, j);
	        	r.start();
        	}
        }
        
        int hash = mouse.hashCode();
        while(true) {
    	    int hash2 = mouse.hashCode();
    	    if (hash != hash2) {
    	 	   hash = hash2;
    	 	  cleanArr();
    	    }
    	    try {
    		    Thread.sleep(1);
    	    } catch (InterruptedException e) {}
        }
	}
	public static void cleanArr() {
		 for (int i = 0; i < width; i ++) {
	 		   for (int j = 0; j < height; j ++) {
	 			   colors[i][j][0] = 0.0f;
	 			   colors[i][j][1] = 0.0f;
	 			   colors[i][j][2] = 0.0f;
	 			   iterations[i][j] = 0;
	 		   }
	 	   }
	}
}