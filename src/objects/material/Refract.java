package objects.material;

import objects.geometry.Geometry;
import test.Ray;
import test.V3;

public class Refract implements Material{
	
	float refractIndex;
	
	public Refract(float refractIndex) {
		this.refractIndex = refractIndex;
	}

	@Override
	public void doSomethinkWithRay(Ray ray, Geometry geometry) {
		ray.mult(0.95f, 0.95f, 0.95f);
		V3 rayDirection = ray.getRayDirection();
		
		V3[] cross = geometry.calculateCrossPoints(ray);
		
		V3 crossPoint = cross[0];
		V3 normal = cross[1];
		
		float dot = -normal.dot(rayDirection);
		if (Math.random()/1.15f < dot) {
		
			float sin = (float) Math.sin(Math.acos(-normal.dot(rayDirection)));
			
			V3 antiNormal = normal.copy();
			antiNormal.multiply(-1f);
			V3 secondVectorInRefractPlane = normal.copy();
			secondVectorInRefractPlane.multiply(-normal.dot(rayDirection));
			secondVectorInRefractPlane.add(rayDirection);
			secondVectorInRefractPlane.normalize();
			
			antiNormal.multiply((float) Math.sqrt(1-((sin/refractIndex)*(sin/refractIndex))));
			secondVectorInRefractPlane.multiply(sin/refractIndex);
			antiNormal.add(secondVectorInRefractPlane);
			V3 refractedRay = antiNormal;
			
			ray.setRayDirection(refractedRay);
			ray.setRayStart(crossPoint);
			
			cross = geometry.calculateCrossPoints(ray);
			
			if (cross.length < 4)
				return;
			
			crossPoint = cross[2];
			cross[3].multiply(-1f);
			normal = cross[3];
			
			sin = (float) Math.sin(Math.acos(-normal.dot(rayDirection)));
			
			antiNormal = normal.copy();
			antiNormal.multiply(-1f);
			secondVectorInRefractPlane = normal.copy();
			secondVectorInRefractPlane.multiply(-normal.dot(rayDirection));
			secondVectorInRefractPlane.add(rayDirection);
			secondVectorInRefractPlane.normalize();
			
			antiNormal.multiply((float) Math.sqrt(1-(sin*refractIndex*sin*refractIndex)));
			secondVectorInRefractPlane.multiply(sin*refractIndex);
			antiNormal.add(secondVectorInRefractPlane);
			refractedRay = antiNormal;
			
			ray.setRayDirection(refractedRay);
			ray.setRayStart(crossPoint);
		} else {
			ray.setRayStart(crossPoint);
			normal.multiply(-2 * normal.dot(ray.getRayDirection()));
			ray.getRayDirection().add(normal);
		}
	}

}
