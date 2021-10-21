package objects;

import test.Ray;
import test.V3;

public class Sky extends Obj{
	
	private float distanceToSky = 100000000.0f;
	
	private float sunR = 10;
	private float sunG = 10;
	private float sunB = 5f;
	
	private float skyR = 0.8f;
	private float skyG = 0.8f;
	private float skyB = 0.9f;
	
	V3 toSunVector = new V3(2,0.83f,0f);
	float sunTreshold = 11f;

	public Sky() {
		super(null, null);
		toSunVector.normalize();
	}
	
	@Override
	public float distanceToNearestCross(Ray ray) {
		return distanceToSky;
	}
	
	@Override
	public void changeRay(Ray ray) {
		if (ray.getRayDirection().dot(toSunVector) > sunTreshold) {
			ray.mult(sunR, sunG, sunB);
		} else {
			ray.mult(skyR, skyG, skyB);
		}
		ray.setEnded();
	}
}
