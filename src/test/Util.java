package test;

public class Util {
	
	public static float[][] matrixByAngles(float x, float y, float z) {
		
		float[][] matrix = new float[3][3];

		matrix[0][0] = (float) (Math.cos(y) * Math.cos(z));
		matrix[0][1] = (float) (Math.cos(y) * (-Math.sin(z)));
		matrix[0][2] = (float) Math.sin(y);

		matrix[1][0] = (float) (Math.sin(x) * Math.sin(y) * Math.cos(z) + Math.sin(z) * Math.cos(x));
		matrix[1][1] = (float) (-Math.sin(x) * Math.sin(y) * Math.sin(z) + Math.cos(z) * Math.cos(x));
		matrix[1][2] = (float) (-Math.sin(x) * Math.cos(y));

		matrix[2][0] = (float) (-Math.sin(y) * Math.cos(x) * Math.cos(z) + Math.sin(x) * Math.sin(z));
		matrix[2][1] = (float) (Math.sin(z) * Math.sin(y) * Math.cos(x) + Math.sin(x) * Math.cos(z));
		matrix[2][2] = (float) (Math.cos(x) * Math.cos(y));
		
		return matrix;
	}
	
	public static V3 crossProduct(V3 firstVector, V3 secondVector) {
		
		float x1 = firstVector.getX();
		float y1 = firstVector.getY();
		float z1 = firstVector.getZ();
		
		float x2 = secondVector.getX();
		float y2 = secondVector.getY();
		float z2 = secondVector.getZ();
		
		float x3 = y1 * z2 - z1 * y2;
		float y3 = x1 * z2 - z1 * x2;
		float z3 = y1 * x2 - z1 * x2;
		
		V3 rez = new V3(x3, y3, z3);
		
		return rez;
	}

}
