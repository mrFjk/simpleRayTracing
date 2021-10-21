package objects.material;

import objects.geometry.Geometry;
import test.Ray;

public class Emissive implements Material {
	
	private float emissionR;
	private float emissionG;
	private float emissionB;
	
	public Emissive(float emissionR, float emissionG, float emissionB) {
		this.emissionR = emissionR;
		this.emissionG = emissionG;
		this.emissionB = emissionB;
	}

	@Override
	public void doSomethinkWithRay(Ray ray, Geometry geometry) {
		ray.mult(emissionR, emissionG, emissionB);
		ray.setEnded();
	}

}
