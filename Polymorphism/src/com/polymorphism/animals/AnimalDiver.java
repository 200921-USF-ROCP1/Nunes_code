package com.polymorphism.animals;

public class AnimalDiver {
	public static void main(String[] args) {
		Dog fido = new Dog("fido");
		Dog buster = new Dog(10,"buster");
		fido.setAge(7);
		
		Cat shadow = new Cat(7);
		Cat bolt = new Cat(3,"bolt");
		shadow.setName("shadow");
		
		System.out.println(fido.getName() + " Goes :");
		fido.makeNoise();
		
		System.out.println(buster.getName() + " eats :");
		fido.whatsMyDiet();
		
		System.out.println(shadow.getName() + " Goes :");
		shadow.makeNoise();
		
		System.out.println(bolt.getName() + " eats :");
		bolt.whatsMyDiet();
	}
}
