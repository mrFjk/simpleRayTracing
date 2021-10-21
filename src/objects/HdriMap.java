package objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import test.Ray;

public class HdriMap extends Obj{
	
	private float distanceToSky = 100000000.0f;
	private static BufferedImage img;
	private int width;
	private int height;

	public HdriMap() {
		super(null, null);
		try {
			img = ImageIO.read(new File("F:\\Programs\\eclipseProjects\\test\\result", "16b.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = img.getWidth();
		height = img.getHeight();
	}
	
	@Override
	public float distanceToNearestCross(Ray ray) {
		return distanceToSky;
	}
	
	@Override
	public void changeRay(Ray ray) {
		
		float x = ray.getRayDirection().getX();
		float y = ray.getRayDirection().getY();
		float z = ray.getRayDirection().getZ();
		
		float u = (float) (0.5 - ((Math.atan2(x, z)) / Math.PI) * 0.5);
		float v = (float) (0.5 - (Math.asin(y) / Math.PI));
		
		u *= width;
		v *= height;
		if (u > (width - 1)) u = (width - 1);
		if (v > (height - 1)) v = (height - 1);
		
		int rgb = img.getRGB((int) Math.floor(u), (int) Math.floor(v));
		
		long r = ((rgb + 256*256*256*128) % (256*256*256)) / (256*256);
		long g = ((rgb + 256*256*256*128) % (256*256)) / 256;
		long b = (rgb + 256*256*256*128) % 256;
		
		float rr = (r*1.0f) / 256.0f;
		float gg = (g*1.0f) / 256.0f;
		float bb = (b*1.0f) / 256.0f;
		ray.mult(rr, gg, bb);
		
		ray.setEnded();
	}

}