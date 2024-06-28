package services;

import java.util.ArrayList;
import java.util.List;

public class PrintService<T> {
	List<T> values = new ArrayList<>();
	
	public PrintService() {
	}
	
	public void addValue(T value) {
		this.values.add(value);
	}
	
	public T first() {
		return values.get(0);
	}
	
	public void print() {
		StringBuilder str = new StringBuilder();
		
		str.append("[");
		
		for(int i = 0; i < this.values.size(); i++) {
			str.append(this.values.get(i));
			
			if(i != this.values.size() - 1) str.append(", ");
		}
		
		str.append("]");
		
		System.out.println(str.toString());
	}
}
