package render;

import test.Main;

public class PixelCordsGenerator {
	
	private int widthDegree;
	private int heightDegree;
	private int widthSquare;
	private int heightSquare;
	
	private int x;
	private int y;
	
	int bitCount;
	int[] arr;
	CreateRay cr;
	
	public PixelCordsGenerator(int x, int y, CreateRay cr) {
		this.cr = cr;
		this.x = x;
		this.y = y;
		
		int sq = 1;
		boolean hb = true;
		boolean wb = true;
		for (int  i = 1; i < 20; i++) {
			sq = sq*2;
			if (sq >= Main.height/Main.tcHeight && hb == true) {
				hb = false;
				heightDegree = i;
				heightSquare = sq;
			}
			if (sq >= Main.width/Main.tcWidth && wb == true) {
				wb = false;
				widthDegree = i;
				widthSquare = sq;
			}
		}

		bitCount = widthDegree + heightDegree;
		arr = new int[bitCount];
	}
	
	public int[] getPixelCords() {
		arr[0]++;
		for (int i = 0; i < bitCount; i++) {
			if (arr[i] >= 2) {
				if ((i+1) != bitCount) {
					arr[i+1]++;
				} else {
					cr.updateRandom();
				}
				arr[i] = 0;
			}
		}
		
		int width = 0;
		int height = 0;
		
		int sqrt = widthSquare / 2;
		for (int i = 0; i < bitCount; i += 2) {
			if (arr[i] == 1) {
				width += sqrt;
			}
			sqrt /= 2;
		}
		
		sqrt = heightSquare / 2;
		for (int i = 1; i < bitCount; i += 2) {
			if (arr[i] == 1) {
				height += sqrt;
			}
			sqrt /= 2;
		}
		
		width = (width*Main.tcWidth) + x;
		height = (height*Main.tcHeight) + y;
		
		if (width >= Main.width) return new int[] {};
		if (height >= Main.height) return new int[] {};

		return new int[] {width, height};
	}
}