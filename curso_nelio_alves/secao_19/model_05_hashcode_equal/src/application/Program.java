package application;

import model.entities.Person;

public class Program {

	public static void main(String[] args) {
		Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);
        
        System.out.println("Pessoas são iguais? " + person1.equals(person2));
        
        System.out.println();
        System.out.println("HashCode da pessoa 1: " + person1.hashCode());
        System.out.println("HashCode da pessoa 2: " + person2.hashCode());
	}

}
