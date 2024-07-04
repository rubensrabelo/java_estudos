package model.utils;

import java.util.function.Predicate;

import model.entities.Product;

public class SumPricePredicate implements Predicate<Product> {

	@Override
	public boolean test(Product product) {
		return product.getName().charAt(0) != 'T';
	}

}
