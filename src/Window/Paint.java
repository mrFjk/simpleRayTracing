package Window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import test.Main;

public class Paint {
	
	private static int width = Main.width;
	private static int height = Main.height;
	
	private static BufferedImage img = new BufferedImage(width, height, 1);

	public static void paint(Graphics gg, Mouse m) {
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int r = (int) Math.round((Main.colors[i][j][0] * 256) / Main.iterations[i][j]);
				int g = (int) Math.round((Main.colors[i][j][1] * 256) / Main.iterations[i][j]);
				int b = (int) Math.round((Main.colors[i][j][2] * 256) / Main.iterations[i][j]);
				
				if (r > 255) r = 255;
				if (g > 255) g = 255;
				if (b > 255) b = 255;
				
				int rgbColor = ((r * 256 * 256) + (g * 256) + b);
				img.setRGB(i, j, rgbColor);
			}
		}
		
		gg.drawImage(img, 0, 0, null);
		FPS.fps();
		
	}
}
