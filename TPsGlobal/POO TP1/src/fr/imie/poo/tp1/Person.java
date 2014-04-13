package fr.imie.poo.tp1;

public class Person {

	private String name;
	
	public Person() {

		this.name="Anonymous";

	}

	public Person(String name) {

		this.name=name;

	}

	public void sayHello(){

		System.out.println("Bonjour, je m'appelle "+name);

	}
	
}
