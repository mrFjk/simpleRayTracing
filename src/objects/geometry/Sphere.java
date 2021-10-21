package objects.geometry;

import test.Ray;
import test.V3;

public class Sphere implements Geometry{
	
	private V3 pos;
	private float radius;
	
	public Sphere(V3 pos, float radius) {
		this.pos = pos;
		this.radius = radius;
	}

	@Override
	public V3[] calculateCrossPoints(Ray ray) {
		
		V3 rayStart = ray.getRayStart();
		V3 rayDirection = ray.getRayDirection();
		
		V3 oc = rayStart.copy();
		oc.substract(pos);
		float b = oc.dot(rayDirection);
		float c = oc.dot(oc) - radius * radius;
		float h = b * b - c;
		if (h < 0.0f) return new V3[] {};
		h = (float) Math.sqrt(h);
		if (((-b - h)<0)|((-b + h)<0)) return new V3[] {};
		V3 crossPoint1 = rayDirection.copy();
		crossPoint1.multiply(-b - h);
		crossPoint1.add(rayStart);
		V3 normal1 = getNormalByPoint(crossPoint1);
		V3 crossPoint2 = rayDirection.copy();
		crossPoint2.multiply(-b + h);
		crossPoint2.add(rayStart);
		V3 normal2 = getNormalByPoint(crossPoint2);
		V3[] result = new V3[] {crossPoint1, normal1, crossPoint2, normal2};
		return result;
	}
	
	V3 getNormalByPoint(V3 crossPoint) {
		V3 crossPoint2 = crossPoint.copy();
		crossPoint2.substract(pos);
		crossPoint2.normalize();
		return crossPoint2;
	}
	
}
