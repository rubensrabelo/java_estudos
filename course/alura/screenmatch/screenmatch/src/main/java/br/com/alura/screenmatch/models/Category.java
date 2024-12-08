package br.com.alura.screenmatch.models;

public enum Category {

	Action("Action"),
	ROMANCE("Romance"),
	COMEDY("Comedy"),
	DRAMA("Drama"), 
	CRIME("Crime");
	
	private String categoryOmdb;
	
	private Category(String categoryOmdb) {
		this.categoryOmdb = categoryOmdb;
	}
	
	public String getCategoryOmdb() {
		return categoryOmdb;
	}

	public static Category fromString(String text) {
		for(Category cat : Category.values())
			if(cat.getCategoryOmdb().equalsIgnoreCase(text))
				return cat;
		
		throw new IllegalArgumentException("Invalid category value");
	}
}
