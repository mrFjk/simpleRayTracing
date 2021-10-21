package test;

public class Ray {
	
	private float r = 1;
	private float g = 1;
	private float b = 1;
	
	private V3 rayStart;
	private V3 rayDirection;
	
	private boolean isEnded = false;
	
	public Ray(V3 rayStart, V3 rayDirection) {
		this.rayStart = rayStart;
		this.rayDirection = rayDirection;
	}
	
	public void setEnded() {
		isEnded = true;
	}
	
	public boolean isEnded() {
		return isEnded;
	}
	
	public void setRayStart(V3 rayStart) {
		this.rayStart = rayStart;
	}
	
	public void setRayDirection(V3 rayDirection) {
		this.rayDirection = rayDirection;
	}
	
	public V3 getRayStart() {
		return rayStart;
	}
	
	public V3 getRayDirection() {
		return rayDirection;
	}
	
	public void mult(float r, float g, float b) {
		this.r *= r;
		this.g *= g;
		this.b *= b;
	}
	
	public float[] getColors() {
		return new float[] {r, g, b};
	}
	
	public void reflect(V3 normal) {
		V3 normalCopy = normal.copy();
		float f = rayDirection.dot(normal) * (-2);
		normalCopy.multiply(f);
		rayDirection.add(normalCopy);
		rayDirection.normalize();
	}

}
