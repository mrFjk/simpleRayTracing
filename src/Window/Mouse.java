package Window;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import test.Main;
import test.Util;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private float moveX = 1000;
	private int moveY = 4; //1
	private float moveZ = -3000; //-4000
	private int rotateX = 0;
	private int rotateY = 300;
	
	private int deviderMX = 400;
	private int deviderMY = 1;
	private int deviderMZ = 400;
	private int deviderRX = 1140;
	private int deviderRY = 1140;

	private boolean canMove = false;
	private boolean MoveOrRotate = false; // true=rotate false=move
	private Robot robot;
	
	float[][] matrix = new float[3][3];

	{
		int xCoord = 500;
		int yCoord = 500;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.mouseMove(xCoord, yCoord);
		this.updateRotationMatrix();
	}

	public float getMoveX() {return ((float) moveX) / deviderMX;}
	public float getMoveY() {return ((float) moveY) / deviderMY;}
	public float getMoveZ() {return ((float) moveZ) / deviderMZ;}
	public float getRotateX() {return ((float) rotateX) / deviderRX;}
	public float getRotateY() {return ((float) rotateY) / deviderRY;}
	public float[][] getRotationMatrix() {
		return matrix;
	}
	
	private void updateRotationMatrix() {
		matrix = Util.matrixByAngles(0, -this.getRotateX(), -this.getRotateY());
	}
	
	@Override
	public int hashCode() {
		return (int) (moveX+moveY+moveZ+rotateX+rotateY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!canMove) return;
		if (MoveOrRotate) {
			rotateX = e.getXOnScreen() - 500 + rotateX;
			rotateY = e.getYOnScreen() - 500 + rotateY;
			this.updateRotationMatrix();
		} else {
			moveX = (float) (((e.getXOnScreen() - 500)*Math.cos(this.getRotateX())) - ((e.getYOnScreen() - 500)*Math.sin(this.getRotateX())) + moveX);
			moveZ = (float) (((e.getYOnScreen() - 500)*Math.cos(this.getRotateX())) + ((e.getXOnScreen() - 500)*Math.sin(this.getRotateX())) + moveZ);
		}
		robot.mouseMove(500, 500);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int button = e.getButton();
		
		if (button == 3) canMove = !canMove;
		if (button == 1) MoveOrRotate = !MoveOrRotate;
		if (button == 4) moveY--;
		if (button == 5) moveY++;
		
		if (button == 2) {
			int[][][] a = new int[Main.width][Main.height][3];
			for (int i = 0; i < Main.width; i++) {
				for (int j = 0; j < Main.height; j++) {
					for (int k = 0; k < 3; k++) {
						a[i][j][k] = (int) ((Main.colors[i][j][k]*256) / Main.iterations[i][j]);
						if (a[i][j][k] >= 255) a[i][j][k] = 255;
					}
				}
			}
			save.SavePng.save(a, "ggh");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
}
