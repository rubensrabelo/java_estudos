package ex_03_rectangle.entities;

public class Rectangle {
	
	private double width;
	private double height;
	
	public Rectangle() {
	}

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public double perimenter() {
		return 2 * (width + height);
	}
	
	public double area() {
		return width * height;
	}
	
	public double diagonal() {
		return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + ", perimenter()=" + perimenter() + ", area()="
				+ area() + ", diagonal()=" + diagonal() + "]";
	}
}
