package main.java;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name;
	private String colour;
	

	public Character(MainApplet parent, String name, String colour){

		this.parent = parent;
		this.name = name;
		this.colour = colour;
	}

	public void display(){

	}
	
}
