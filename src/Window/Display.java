package Window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import test.Main;

public class Display {
	public static void show(int width, int height, String windowName, int fps) {

		JFrame frame = new JFrame(windowName);

		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		Canvas canvas = new Canvas();

		canvas.setSize(width, height);
		canvas.setVisible(true);
		canvas.setFocusable(false);

		Mouse mouse = new Mouse();
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		canvas.addMouseWheelListener(mouse);

		Main.mouse = mouse;

		frame.add(canvas);

		canvas.createBufferStrategy(3);

		BufferStrategy bufferStrategy;
		Graphics graphics;

		while (true) {
			bufferStrategy = canvas.getBufferStrategy();
			graphics = bufferStrategy.getDrawGraphics();

			Paint.paint(graphics, mouse);

			bufferStrategy.show();
			graphics.dispose();
		}
	}
}
