package fr.imie.poo.tp1;

public class Main {

	public static void main(String[] args) {

		Student s1=new Student("Nico");
		Professor p1=new Professor("Pierre",5);
		
		s1.sayHello();
		p1.sayHello();
		p1.getSalary();
		
	}

}
