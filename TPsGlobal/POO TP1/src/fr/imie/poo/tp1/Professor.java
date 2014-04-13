package fr.imie.poo.tp1;

public class Professor extends Person {

	private int salary=0;
	
	public Professor(String name, int salary) {
		
		super(name);
		this.salary=salary;

	}
	
	public void getSalary() {

		System.out.println("Salaire: "+salary);

	}

}
