package project_01_triangle.entities;

public class Triangle {
	
	private double firtSide;
	private double secondSide;
	private double thirdSide;
	
	public Triangle() {
	}

	public Triangle(double firtSide, double secondSide, double thirdSide) {
		this.firtSide = firtSide;
		this.secondSide = secondSide;
		this.thirdSide = thirdSide;
	}

	public double getFirtSide() {
		return firtSide;
	}

	public void setFirtSide(double firtSide) {
		this.firtSide = firtSide;
	}

	public double getSecondSide() {
		return secondSide;
	}

	public void setSecondSide(double secondSide) {
		this.secondSide = secondSide;
	}

	public double getThirdSide() {
		return thirdSide;
	}

	public void setThirdSide(double thirdSide) {
		this.thirdSide = thirdSide;
	}
	
	public Double perimeter() {
		return (firtSide + secondSide + thirdSide) / 2;
	}
	
	public Double area() {
		Double p = perimeter();
		return Math.sqrt(p * (p - firtSide) * (p - secondSide) * (p - thirdSide));
	}

	@Override
	public String toString() {
		return "Triangle [firtSide=" + firtSide + ", secondSide=" + secondSide + ", thirdSide=" + thirdSide + "]";
	}
}
