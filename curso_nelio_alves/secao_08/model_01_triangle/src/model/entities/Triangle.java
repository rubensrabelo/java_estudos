package model.entities;

public class Triangle {
	private double side01;
	private double side02;
	private double side03;
	
	public Triangle() {
	}

	public Triangle(double side01, double side02, double side03) {
		this.side01 = side01;
		this.side02 = side02;
		this.side03 = side03;
	}

	public double getSide01() {
		return side01;
	}

	public void setSide01(double side01) {
		this.side01 = side01;
	}

	public double getSide02() {
		return side02;
	}

	public void setSide02(double side02) {
		this.side02 = side02;
	}

	public double getSide03() {
		return side03;
	}

	public void setSide03(double side03) {
		this.side03 = side03;
	}
	
	public double perimeter() {
		return (side01 + side02 + side03) / 2;
	}
	
	public double area() {
		double p = perimeter();
		double area = Math.sqrt(p*(p - side01)*(p - side02)*(p - side03));
		return area;
	}
}
