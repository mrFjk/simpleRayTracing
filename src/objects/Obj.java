package objects;

import objects.geometry.Geometry;
import objects.material.Material;
import test.Ray;
import test.V3;

public class Obj {
	
	private Geometry geometry;
	private Material material;

	public Obj(Geometry geometry, Material material) {
		this.geometry = geometry;
		this.material = material;
	}

	public float distanceToNearestCross(Ray ray) {
		V3[] cross = geometry.calculateCrossPoints(ray);
		if (cross.length != 0) {
			cross[0].substract(ray.getRayStart());
			float cameraToCrossDistance = cross[0].length();
			return cameraToCrossDistance;
		} else {
			return -1;
		}
	}
	
	public void changeRay(Ray ray) {
		material.doSomethinkWithRay(ray, geometry);
	}
	
}
