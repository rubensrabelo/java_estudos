package model.utils;

import java.util.function.Function;

import model.entities.Product;

public class UpperCaseName implements Function<Product, String> {

	@Override
	public String apply(Product prod) {
		return prod.getName().toUpperCase();
	}

}
