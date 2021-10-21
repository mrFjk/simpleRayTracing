package test;

public class V3 {

	private float x;
	private float y;
	private float z;

	public V3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}
	
	public V3 copy() {
		return new V3(x, y, z);
	}

//=================================================================================================================================

	public void normalize() {
		float size = (float) Math.sqrt((x * x) + (y * y) + (z * z));
		x = x / size;
		y = y / size;
		z = z / size;
	}

	public void add(V3 vector) {
		x = vector.getX() + x;
		y = vector.getY() + y;
		z = vector.getZ() + z;
	}

	public void substract(V3 vector) {
		x = x - vector.getX();
		y = y - vector.getY();
		z = z - vector.getZ();
	}

	public void multiply(float f) {
		x = x * f;
		y = y * f;
		z = z * f;
	}
	
	public void rotate(float[][] matrix) {
		float rx = x;
		float ry = y;
		float rz = z;
		
		x = matrix[0][0] * rx + matrix[0][1] * ry + matrix[0][2] * rz;
		y = matrix[1][0] * rx + matrix[1][1] * ry + matrix[1][2] * rz;
		z = matrix[2][0] * rx + matrix[2][1] * ry + matrix[2][2] * rz;
	}
	
	public V3 cross(V3 sVector) {
		
		float x1 = x;
		float y1 = y;
		float z1 = z;
		
		float x2 = sVector.getX();
		float y2 = sVector.getY();
		float z2 = sVector.getZ();
		
		float x3 = y1 * z2 - z1 * y2;
		float y3 = x1 * z2 - z1 * x2;
		float z3 = y1 * x2 - z1 * x2;
		
		V3 rez = new V3(x3, y3, z3);
		
		return rez;
	}

	public float dot(V3 vector) {
		float x0 = vector.getX();
		float y0 = vector.getY();
		float z0 = vector.getZ();
		float dot = ((x0 * x) + (y0 * y) + (z0 * z));
		return dot;
	}

	public float length() {
		float length = (float) Math.sqrt((x * x) + (y * y) + (z * z));
		return length;
	}
}
