package main.java;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import de.looksgood.ani.Ani;
import de.looksgood.ani.easing.Circ;
import controlP5.ControlP5;
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
	private Character nowChar;
	private Ani animate;

	private ControlP5 cp5;
	public void setup() {
		animate.init(this);
		size(width, height);
		smooth();
		loadData();
		
	}

	public void draw() {
		background(255);
		for(Character i: chacArr){
			i.display();
			
			if( dist(mouseX, mouseY, i.x, i.y) < 35/2){
				nowChar = i;
				
			}else{
				animate =  Ani.to(i, (float)0.1, "diameter", 35);
			}
		}
		
		fill(255,255,255);
		ellipse(600,350,500, 500);
		
		String s = "Star Wars "+ episode;
		fill(50);
		textSize(48);
		text(s, 500, 50);
		
		if(nowChar != null && dist(mouseX, mouseY, nowChar.x, nowChar.y) < 35/2){
			animate =animate = Ani.to(nowChar, (float)0.1, "diameter", 45);
			
			fill(unhex("FF00E3E3"));
			noStroke();
			rect(mouseX, mouseY-20, nowChar.getName().length()*12, 40, 7);
			
			textSize(18);
			fill(0, 102, 153, 204);
			text(nowChar.getName(), mouseX, mouseY);
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
