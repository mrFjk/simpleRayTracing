package render;

import test.Main;
import test.Ray;

public class Render extends Thread {
	
	private CalcRay rayCalcultor = new CalcRay();
	private CreateRay rayCreator = new CreateRay(Main.width, Main.height);
	private PixelCordsGenerator pcg;
	
	public Render(int x, int y) {
		pcg = new PixelCordsGenerator(x, y, rayCreator);
	}
	
	int oldHash = 0;

	@Override
	public void run() {
		while (true) {
			int[] arr = pcg.getPixelCords();
			if (arr.length > 0) {
				int width = arr[0];
				int height = arr[1];
				
				Ray ray = rayCreator.getRay(width, height);
				
				float[] color = rayCalcultor.calcRay(ray);
				
				Main.iterations[width][height]++;
				Main.colors[width][height][0] += color[0];
				Main.colors[width][height][1] += color[1];
				Main.colors[width][height][2] += color[2];
				
			}
		}
	}
}