package model.entities;

public class Circle implements Shape {
	
	private Double radius;
	
	public Circle() {
	}
	
	public Circle(Double radius) {
		super();
		this.radius = radius;
	}
	
	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	@Override
	public Double area() {
		return Math.PI * Math.pow(radius, 2);
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + 
				", area=" + String.format("%.2f", area()) +"]";
	}

}
