package render;

import objects.HdriMap;
import objects.Obj;
import objects.Sky;
import objects.geometry.Plane;
import objects.geometry.Plane.Orientation;
import objects.geometry.Polygon;
import objects.geometry.Sphere;
import objects.material.BB;
import objects.material.Emissive;
import objects.material.Mirror;
import objects.material.Refract;
import objects.material.RoundnessMirror;
import test.Ray;
import test.V3;

public class CalcRay {
	
	static Obj[] obj = new Obj[] {
		//new HdriMap(),
		//new Sky(),
		new Obj(new Sphere(new V3(18f,2.0f,0),2f), new RoundnessMirror(0.9f, 0.9f, 0.9f, 0.3f)),
		new Obj(new Sphere(new V3(18f,2.0f,4.0f),2f), new RoundnessMirror(0.9f, 0.9f, 0.9f, 0.3f)),
		//new Obj(new Sphere(new V3(18f,2.0f,4.0f),2f), new Refract(1.516f)),
		new Obj(new Plane(new V3(0, 0, 0), 2000.0f, Orientation.XZ), new RoundnessMirror(0.4f, 0.4f, 0.4f, 1f)),
		
		new Obj(new Plane(new V3(20, 1, 12), 15f, Orientation.YX), new Emissive(15f, 10f, 10f)),
		new Obj(new Plane(new V3(20, 1, -12), 15f, Orientation.YX), new Emissive(10f, 10f, 15f)),
	};
	
	public float[] calcRay(Ray ray) {
		int noCrossObjectIndex = -1;
		for (int i0 = 0; i0 < 8; i0++) {
			
			float nearestCrossDistance = -1;
			int nearestCrossObjectIndex = -1;
			for (int i1 = 0; i1 < obj.length; i1++) {
				if (i1 != noCrossObjectIndex) {
					Obj o = obj[i1];
					float ncd = o.distanceToNearestCross(ray);
					if ((ncd < nearestCrossDistance || nearestCrossDistance < 0) && ncd > 0) {
						nearestCrossDistance = ncd;
						nearestCrossObjectIndex = i1;
					}
				}
			}
			if (nearestCrossObjectIndex == -1)
				return new float[] {0, 0, 0};
			obj[nearestCrossObjectIndex].changeRay(ray);
			noCrossObjectIndex = nearestCrossObjectIndex;
			if (ray.isEnded()) return ray.getColors();
			
			
		}
		return new float[] {0, 0, 0};
	}
}