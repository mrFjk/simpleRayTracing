package Window;

public class FPS {
	
	static long lt = 0;
	static int period = 100;
	static int timer = 0;
	
	public static void fps() {
		timer++;
		if (timer == period) {
			timer = 0;
			long nt = System.nanoTime();
			float lrez = 1.0f / ((nt - lt)/(1000000000.0f * period));
			lt = nt;
			System.out.println(lrez);
		}
	}
}
