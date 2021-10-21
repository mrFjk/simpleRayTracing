package render;

import test.Main;
import test.Ray;
import test.V3;

public class CreateRay {
	
	private float random1 = (float) Math.random();
	private float ramdom2 = (float) Math.random();
	
	private int width;
	private int height;
	
	public CreateRay(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Ray getRay(int x, int y) {

		float xx = width;
		float yy = -y + (height / 2.0f) + random1;
		float zz = x - (width / 2.0f) + ramdom2;

		V3 rayDirection = new V3(xx, yy, zz);
		rayDirection.rotate(Main.mouse.getRotationMatrix());
		rayDirection.normalize();
		
		V3 cameraPosition = new V3(-Main.mouse.getMoveZ(), Main.mouse.getMoveY(), Main.mouse.getMoveX());
		
		return new Ray(cameraPosition, rayDirection);
	}
	
	public void updateRandom() {
		random1 = (float) Math.random();
		ramdom2 = (float) Math.random();
	}

}
