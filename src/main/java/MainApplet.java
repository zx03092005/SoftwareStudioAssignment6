package main.java;

import java.util.ArrayList;

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
	
	private final static int width = 1200, height = 650;
	
	private ArrayList<Character> chacArr =  new ArrayList<Character>();
	public void setup() {

		size(width, height);
		smooth();
		loadData();
		
	}

	public void draw() {

	}

	private void loadData(){
		JSONObject var;
		JSONArray arr;
		
		var = loadJSONObject(path+file);
		arr = var.getJSONArray("nodes");
		
		for(int i=0; i<arr.size(); i++){
			JSONObject temp = arr.getJSONObject(i);
			String n = temp.getString("name");
			String c = temp.getString("colour");
			chacArr.add(new Character(this, n, c));
		}
	}

}
