package objects.geometry;

import test.Ray;
import test.V3;

public class Plane implements Geometry{
	
	public enum Orientation {
		XZ,
		YX,
		YZ
	}
	
	Orientation orientation;
	V3 firstPoint;
	V3 secondPoint;

	public Plane(V3 pos, float side, Orientation orientation) {
		side /= 2.0f;
		this.orientation = orientation;
		
		if (orientation == Orientation.XZ) { 
			firstPoint = pos.copy();
			firstPoint.substract(new V3(side, 0, side));
			secondPoint = pos.copy();
			secondPoint.add(new V3(side, 0, side));
		}
		if (orientation == Orientation.YX) {
			firstPoint = pos.copy();
			firstPoint.substract(new V3(side, side, 0));
			secondPoint = pos.copy();
			secondPoint.add(new V3(side, side, 0));
		}
		if (orientation == Orientation.YZ) {
			firstPoint = pos.copy();
			firstPoint.substract(new V3(0, side, side));
			secondPoint = pos.copy();
			secondPoint.add(new V3(0, side, side));
		}
	}

	@Override
	public V3[] calculateCrossPoints(Ray ray) {
		V3 rayDirection = ray.getRayDirection();
		V3 rayStart = ray.getRayStart();
		
		float p = 0;
		float dist = 0;
		V3 normal = null;
		
		if (orientation == Orientation.XZ) {
			p = rayDirection.getY();
			if (p == 0) return new V3[] {};
			dist = -firstPoint.getY() + rayStart.getY();
			if (p > 0) {
				normal = new V3(0, -1, 0);
			} else {
				normal = new V3(0, 1, 0);
			}
		}
		if (orientation == Orientation.YX) {
			p = rayDirection.getZ();
			if (p == 0) return new V3[] {};
			dist = -firstPoint.getZ() + rayStart.getZ();
			if (p > 0) {
				normal = new V3(0, 0, -1);
			} else {
				normal = new V3(0, 0, 1);
			}
		}
		if (orientation == Orientation.YZ) {
			p = rayDirection.getX();
			if (p == 0) return new V3[] {};
			dist = -firstPoint.getX() + rayStart.getX();
			if (p > 0) {
				normal = new V3(-1, 0, 0);
			} else {
				normal = new V3(1, 0, 0);
			}
		}
		
		float mult = dist / -p;
		
		if (mult < 0) return new V3[] {};
		
		V3 crossPoint = rayDirection.copy();
		crossPoint.multiply(dist / (-p));
		crossPoint.add(rayStart);
		
		boolean b = true;
		if (orientation == Orientation.XZ) {
			b = b & (crossPoint.getX() >= firstPoint.getX());
			b = b & (crossPoint.getX() <= secondPoint.getX());
			b = b & (crossPoint.getZ() >= firstPoint.getZ());
			b = b & (crossPoint.getZ() <= secondPoint.getZ());
		}
		if (orientation == Orientation.YX) {
			b = b & (crossPoint.getX() >= firstPoint.getX());
			b = b & (crossPoint.getX() <= secondPoint.getX());
			b = b & (crossPoint.getY() >= firstPoint.getY());
			b = b & (crossPoint.getY() <= secondPoint.getY());
		}
		if (orientation == Orientation.YZ) {
			b = b & (crossPoint.getZ() >= firstPoint.getZ());
			b = b & (crossPoint.getZ() <= secondPoint.getZ());
			b = b & (crossPoint.getY() >= firstPoint.getY());
			b = b & (crossPoint.getY() <= secondPoint.getY());
		}
		
		if (b) {
			return new V3[] {crossPoint, normal};
		}
		return new V3[] {};
	}
	
}
