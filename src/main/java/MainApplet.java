package main.java;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import de.looksgood.ani.Ani;
import controlP5.ControlP5;
import ddf.minim.AudioPlayer;
import ddf.minim.*;
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
	private Character nowChar, tempChar;
	private boolean onTheNode;
	
	private Ani animate;
	private Minim minim;
	private AudioPlayer bgm;
	private ControlP5 cp5;  
	
	@SuppressWarnings("static-access")
	public void setup() {
		animate.init(this);
		minim = new Minim(this);
		bgm = minim.loadFile(this.getClass().getResource("/main/resources/bgm.mp3").getPath());
		
		size(width, height);
		smooth();
		loadData();
		
		
		cp5 = new ControlP5(this);
		cp5.addButton("buttonA").setLabel("Add All").setPosition(900, 100).setSize(200,100);
		cp5.getController("buttonA").getCaptionLabel().setSize(36);
		cp5.addButton("buttonB").setLabel("Clear").setPosition(900, 300).setSize(200,100);
		cp5.getController("buttonB").getCaptionLabel().setSize(36);
		cp5.addButton("buttonC").setLabel("Sound").setPosition(900, 500).setSize(200,100);
		cp5.getController("buttonC").getCaptionLabel().setSize(36);
		
		
	}
	
	public void buttonA(){
		for(Character i: chacArr){
			i.setInCircle(true);
		}
		drawOnCircle();
	}
	
	public void buttonB(){
		for(Character i: chacArr){
			i.setInCircle(false);
		animate = Ani.to(i, (float)0.1, "x", i.getStartX());
		animate = Ani.to(i, (float)0.1, "y", i.getStartY());
		}
	}
	
	public void buttonC() {
		if(bgm.isPlaying()) bgm.pause();
		else bgm.play();
	}
	
	public void drawOnCircle(){
		int counter = 0;
		for(Character i: chacArr){
			if(i.isInCircle()){
				counter++;
			}
		}
		float angle = 0;
		for(Character i: chacArr){
			if(i.isInCircle()){
				i.x = 600 + 250*cos(angle);
				i.y = 350 + 250*sin(angle);
				angle += 2*PI/counter;
			}
		}
		
	}

	public void draw() {
		background(255);
		
		fill(255,255,255);
		if(dist(mouseX, mouseY, 600, 350) <= 250) strokeWeight(7);
		else strokeWeight(3);
		ellipse(600,350,500, 500);
		
		strokeWeight(0);
		for(Character i: chacArr){
			i.display();
			
			if(dist(mouseX, mouseY, i.x, i.y) < 35/2)
				nowChar = i;
			else
				animate =  Ani.to(i, (float)0.1, "diameter", 35);
		}
		
		for(Character i : chacArr) {
			if(dist(i.x, i.y, mouseX, mouseY) < 35) {
				onTheNode = true;
				break;
			} else onTheNode = false;
		}
		
		String s = "Star Wars "+ episode;
		fill(50);
		textSize(48);
		text(s, 500, 50);
		
		if(nowChar != null && dist(mouseX, mouseY, nowChar.x, nowChar.y) < 35/2){
			animate = Ani.to(nowChar, (float)0.5, "diameter", 45);
			
			fill(unhex("FF00E3E3"));
			
			rect(mouseX, mouseY-20, nowChar.getName().length()*12, 40, 7);
			
			textSize(18);
			fill(0, 102, 153, 204);
			text(nowChar.getName(), mouseX, mouseY);
		}
		
	}
	
	@SuppressWarnings("deprecation")
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
	
	public void mousePressed(){
		if(onTheNode){
			tempChar = nowChar;
		}
		
	}
	
	public void mouseDragged(){
		if(tempChar!=null){
			tempChar.x = mouseX;
			tempChar.y = mouseY;
		}
	}
	
	public void mouseReleased(){
		if(tempChar != null){
			 
			if( dist(tempChar.x, tempChar.y, 600, 350)< 500/2 ){
				tempChar.setInCircle(true);
				drawOnCircle();
				
			}else{
				animate = Ani.to(tempChar, (float) 0.1, "x", tempChar.getStartX());
				animate = Ani.to(tempChar, (float) 0.1, "y", tempChar.getStartY());
				tempChar.setInCircle(false);
				drawOnCircle();
			}
		}	tempChar = null;
		
		
	}
	
	
}
