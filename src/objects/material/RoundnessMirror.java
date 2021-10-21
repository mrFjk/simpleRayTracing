package objects.material;

import objects.geometry.Geometry;
import test.Main;
import test.Ray;
import test.V3;

public class RoundnessMirror implements Material{
	
	private float cos;
	private float sin;
	private Mirror mirror;
	
	public RoundnessMirror(float r, float g, float b, float roundness) {
		mirror = new Mirror(r,g,b);
		this.cos = (float) Math.cos(Math.PI*roundness/2);
		this.sin = (float) Math.sin(Math.PI*roundness/2);
	}

	@Override
	public void doSomethinkWithRay(Ray ray, Geometry geometry) {
		mirror.doSomethinkWithRay(ray, geometry);
		
		float theta = (float) (Math.random() * 2 * Math.PI);
		float v = (float) Math.random();
		float phi = (float) Math.acos(2 * v - 1);
		float r = (float) Math.pow(Math.random(), 1.0d/3.0d);
		float x = (float) (r * Math.sin(phi) * Math.cos(theta));
		float y = (float) (r * Math.sin(phi) * Math.sin(theta));
		float z = (float) (r * Math.cos(phi));
		V3 random = new V3(x, y, z);
		
		V3 rayDirection = ray.getRayDirection();
		rayDirection.multiply(cos);
		random.multiply(sin);
		rayDirection.add(random);
	}

}
