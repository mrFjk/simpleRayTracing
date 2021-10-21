package objects.material;

import objects.geometry.Geometry;
import test.Ray;

public interface Material {
	
	public void doSomethinkWithRay(Ray ray, Geometry geometry);

}
