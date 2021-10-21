package objects.material;

import objects.geometry.Geometry;
import test.Ray;
import test.V3;

public class Mirror implements Material{
	
	private float r;
	private float g;
	private float b;
	
	public Mirror(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void doSomethinkWithRay(Ray ray, Geometry geometry) {
		V3[] cross = geometry.calculateCrossPoints(ray);
		V3 crossPoint = cross[0];
		V3 normal = cross[1];
		
		normal.multiply(-2 * normal.dot(ray.getRayDirection()));
		ray.getRayDirection().add(normal);
		ray.setRayStart(crossPoint);
		ray.mult(r, g, b);
	}
}
