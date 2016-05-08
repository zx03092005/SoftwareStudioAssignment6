package main.java;

import java.util.ArrayList;

import de.looksgood.ani.easing.Circ;
import processing.core.PApplet;
import processing.core.PShape;
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
	
	private final static int width = 1200, height = 650;
	
	private ArrayList<Character> chacArr =  new ArrayList<Character>();
	
	public void setup() {

		size(width, height);
		smooth();
		loadData();
		
	}

	public void draw() {
		for(Character i: chacArr){
			i.display();
		}
		
		ellipse(1000,500,300, 300);
		
	}

	private void loadData(){
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
