package main.java;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;


/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	private String episode1 = "starwars-episode-1-interactions.json",
				   episode2 = "starwars-episode-2-interactions.json",
				   episode3 = "starwars-episode-3-interactions.json",
				   episode4 = "starwars-episode-4-interactions.json",
				   episode5 = "starwars-episode-5-interactions.json",
				   episode6 = "starwars-episode-6-interactions.json",
				   episode7 = "starwars-episode-7-interactions.json";
	private int episode = 1;
					
	private final static int width = 1200, height = 650;
	
	private ArrayList<Character> chacArr =  new ArrayList<Character>();
	private ControlP5 cp5;
	public void setup() {

		size(width, height);
		smooth();
		loadData();
		
	}

	public void draw() {
		background(255);
		for(Character i: chacArr){
			i.display();
		}
		
	}
	
	public void keyPressed() {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_1 :
				episode = 1;
				file = episode1;
				break;
			case KeyEvent.VK_2 :
				episode = 2;
				file = episode2;
				break;
			case KeyEvent.VK_3 :
				episode = 3;
				file = episode3;
				break;
			case KeyEvent.VK_4 :
				episode = 4;
				file = episode4;
				break;
			case KeyEvent.VK_5 :
				episode = 5;
				file = episode5;
				break;
			case KeyEvent.VK_6 :
				episode = 6;
				file = episode6;
				break;
			case KeyEvent.VK_7 :
				episode = 7;
				file = episode7;
				break;
			default :
				file = episode1;
				break;
		}
		setup();
	}

	private void loadData(){
		chacArr.clear();
		JSONObject var;
		JSONArray arrN;
		JSONArray arrL;
		
		var = loadJSONObject(path+file);
		arrN = var.getJSONArray("nodes");
		
		for(int i=0; i<arrN.size(); i++){
			JSONObject temp = arrN.getJSONObject(i);
			String n = temp.getString("name");
			String c = temp.getString("colour");
			float x = 50;
			x = x+ 50*((i)/10);
			float y = 50;
			y = y+ 50*((i)%10);
			
			chacArr.add(new Character(this, n, c, x, y));
		}
		
		arrL = var.getJSONArray("links");
		for(int i=0; i<arrL.size(); i++){
			JSONObject tm = arrL.getJSONObject(i);
			int src = tm.getInt("source");
			int tar = tm.getInt("target");
			Integer val = tm.getInt("value"); 
			
			chacArr.get(src).addTarget(chacArr.get(tar), val);
		}
	
	}

}
