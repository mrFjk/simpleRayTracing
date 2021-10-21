package save;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SavePng {
	
	public static void save(int[][][] a, String name) {
		
		int width = a.length;
		int height = a[0].length;
		
		BufferedImage img = new BufferedImage(width, height, 1);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgbColor = (int) (a[i][j][2] + a[i][j][1] * 256 + a[i][j][0] * 256 * 256);
				img.setRGB(i, j, rgbColor);   
			}
		}
		
		try {
			//ImageIO.write(img, "png", new File("F:\\Programs\\eclipseProjects\\test\\result", name + ".png"));
			ImageIO.write(img, "png", new File("F:\\Programs\\eclipseProjects\\test\\result", name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
