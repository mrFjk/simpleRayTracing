package objects.geometry;

import test.Ray;
import test.V3;

public class Polygon implements Geometry {

	V3 p1;
	V3 p2;
	V3 p3;
	V3 normal;

	public Polygon(V3 p1, V3 p2, V3 p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		V3 e1 = p2.copy();
		V3 e2 = p3.copy();
		e1.substract(p1);
		e2.substract(p1);
		this.normal = e1.cross(e2);
		this.normal.normalize();
	}

	@Override
	public V3[] calculateCrossPoints(Ray ray) {

		V3 e1 = p2.copy();
		p2.substract(p1);
		V3 e2 = p3.copy();
		p3.substract(p1);

		V3 d = ray.getRayDirection();
		V3 t = ray.getRayStart().copy();
		t.substract(p1);

		V3 p = d.cross(e2);
		V3 q = t.cross(e1);

		float dotPE1 = p.dot(e1);
		float dotPT = p.dot(t);
		float dotQD = q.dot(d);
		float u = dotPT / dotPE1;
		float v = dotQD / dotPE1;

		if (u >= 0 & u <= 1 & v >= 0 & v <= 1 & (u+v) <=1) {
			V3 pp1 = p1.copy();
			V3 pp2 = p2.copy();
			V3 pp3 = p3.copy();
			pp1.multiply(v);
			pp2.multiply(1-(u+v));
			pp3.multiply(u);
			
			pp1.add(pp2);
			pp1.add(pp3);
			
			V3 m = pp1.copy();
			m.substract(ray.getRayStart());
			if (d.dot(m) < 0.0f) {
				return new V3[0];
			}
			
			V3 n = normal.copy();
			if (d.dot(n) > 0.0f) {
				n.multiply(-1.0f);
			}
			return new V3[] {pp1, n};
		}
			
		return new V3[0];
	}

}
