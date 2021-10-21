package objects.material;

import objects.geometry.Geometry;
import test.Ray;

public class BB implements Material {

	@Override
	public void doSomethinkWithRay(Ray ray, Geometry geometry) {
		ray.mult(0, 0, 0);
		ray.setEnded();
	}

}
