package objects.geometry;

import test.Ray;
import test.V3;

public interface Geometry {
	
	public V3[] calculateCrossPoints(Ray ray);

}
