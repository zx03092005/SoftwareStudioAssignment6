package main.java;

import java.util.ArrayList;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name;
	private int color;
	public float x, y;
	private float startX, startY, diameter = 35;
	private boolean inCircle;
	private ArrayList<Character> targets = new ArrayList<Character>();
	private ArrayList<Integer> values= new ArrayList<Integer>();

	@SuppressWarnings("static-access")
	public Character(MainApplet parent, String name, String colour, float x, float y){

		this.parent = parent;
		this.name = name;
		this.color = this.parent.unhex(colour.substring(1));
		
		this.x = this.startX = x;
		this.y = this.startY = y;
		this.inCircle = false;
	}

	public void display(){
		this.parent.fill(color);
		this.parent.stroke(color);
		this.parent.ellipse(x, y, diameter, diameter);
		
	}
	
	public void addTarget(Character target, Integer value) {
		this.targets.add(target);
		this.values.add(value);
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getStartX() {
		return this.startX;
	}
	
	public float getStartY() {
		return this.startY;
	}
	
	public boolean isInCircle() {
		return this.inCircle;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setInCircle(boolean inCircle) {
		this.inCircle = inCircle;
	}
}
