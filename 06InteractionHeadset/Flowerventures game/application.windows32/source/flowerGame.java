import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class flowerGame extends PApplet {


SoundFile file;
PImage title;

PImage playAgainMushroom;
PlayButton playButton;

Grass grass;
Flower flower;
BeeSwarm beeSwarm;
Sky sky;

public void initialize(){
  grass = new Grass();
  flower = new Flower(new Point(870, 500));
  sky = new Sky(flower, 24, 24, 6);
  beeSwarm = new BeeSwarm(flower, 60, new Amplitude(this),new AudioIn(this, 0));
  
  playButton = new PlayButton(1390.0f, 800.0f, 100.0f, 150.0f, 20.0f, color(0.0f));
  playAgainMushroom = loadImage("playAgain.png");
}

public void setup(){
  
  title = loadImage("title.png");
  file = new SoundFile(this,"data/gameOver.mp3");
  
  initialize();
}

public void draw(){
  if(flower.getFlowerHealth() > 0){
    sky.draw();
    flower.draw();
    grass.draw();
    image(title, width/2 - 320, 900);
    fill(197, 125, 201);
    rect(0, height - 140, 400 * flower.flowerHealth/100, 70, 0, 15, 15, 0);
    beeSwarm.draw();
  }else{
    sky.draw();
    flower.draw();
    grass.draw();
    
    //playButton.display();
    image(playAgainMushroom, 1270.0f, 600.0f);
    
    if(playButton.isButtonPressed(mouseX, mouseY)){
      file.play();
      file.amp(.2f);
      initialize();
    }
  }
}
class Bee{
  
  PImage bee;
  int beeRotation = 45;
  int size;
  Point position;
  
  Bee(Point position){
    bee = loadImage("bee.png");
    size = bee.width;
    this.position = position;
  }
  
  public void setBee(Point position){
    this.position = position;
  }
  
  //Default maybe 100
  public void setSize(int size){
    this.size = size;
  }
  
  public void draw(){    
    imageMode(CENTER);
    
    pushMatrix();
    translate(position.x, position.y);
    if(millis() % (long)8 == 7){
      beeRotation++;
    }
    rotate(radians(sin(beeRotation % 360) * 40));
    image(bee, 0, 0, size, size);
    popMatrix();
    
    imageMode(CORNER);
    
  }
}


class BeeSwarm{
  Amplitude amp;
  AudioIn in;
  int dayRotation;
  int singingCounter = 0;
  
  Bee bee1;
  Bee bee2;
  Bee bee3;
  Point position;
  
  BeeSwarm(Flower flower, int dayRotation, Amplitude beeAmp, AudioIn mic){
    position = flower.getPos();
    this.dayRotation = dayRotation;
    
    bee1 = new Bee(new Point(0 + position.x, 0 + position.y));
    bee2 = new Bee(new Point(245 + position.x, 100 + position.y));
    bee3 = new Bee(new Point(163 + position.x, (-29) + position.y));
    
    amp = beeAmp;
    in = mic;
    in.start();
    amp.input(in);
  }
  
  public void draw(){  
    int result = (int)map(amp.analyze(), 0.0f, 1.0f, 0.0f, 1000.0f);
    if(result > 5 && millis() % (dayRotation * 1000)  < 24000){
      bee1.setSize(100);
      bee1.draw();
      bee2.setSize(100);
      bee2.draw();
      bee3.setSize(100);
      bee3.draw();
      singingCounter++;
      if(result > 100){
        flower.pollinate();
      }
      if(singingCounter % 20 == 19){
        flower.pollinate();
      }
    }   
  }
}
class Flower{
  int flowerHealth = 50;
  PImage flower;
  PImage flowerHead;
  PImage deadFlowerHead;
  Point position;
  
  Flower(Point position){
    flower = loadImage("flower.png");
    flowerHead = loadImage("flowerhead.png");
    deadFlowerHead = loadImage("rip.png");
    this.position = position;
  }
  
  public Point getPos(){
    return position;
  }
  
  public void pollinate(){
    if(flowerHealth < 100){
      flowerHealth++;
    }
  }
  
  public void wither(){
    if(flowerHealth >= 0){
      flowerHealth--;
    }
  }
  
  public int getFlowerHealth(){
    return flowerHealth;
  }
  
  public void draw(){
    image(flower, position.x, position.y + 70);
    if(flowerHealth > 0){
      image(flowerHead, position.x - 30, position.y - 40);
    }else{
      image(deadFlowerHead, position.x - 30, position.y - 80);
    }
  }
}
class Grass{
  
  PImage grass;
  Point position;
  
  Grass(){
    grass = loadImage("grass.png");
    this.position = new Point(0, 0);
  }
  
  public void draw(){
    image(grass, position.x, position.y);
  }
}
class Moon{
  
  PImage moon;
  Point position;
  
  Moon(){
    moon = loadImage("moon.png");
    this.position = new Point(1550, 20);
  }
  
  public void setMoon(Point position){
    this.position = position;
  }
  
  public Point getMoon(){
    return position;
  }
  
  public void draw(){
    image(moon, position.x, position.y);
  }
}
class PlayButton {

  private float rectX;
  private float rectY;
  private float rectWidth;
  private float rectHeight;
  private float rectRadius;
  private boolean pressed;
  private boolean released;
  private int rectColor;

  PlayButton(float x, float y, float w, float h, float r, int c) {
    rectX = x;
    rectY = y;
    rectWidth = w;
    rectHeight = h;
    rectRadius = r;
    rectColor = c;
    pressed = false;
    released = false;
  }

  public void display() {
    fill(rectColor);
    strokeWeight((width / 270 + height / 480)/2);
    rectMode(RADIUS);
    rect(rectX, rectY, rectWidth, rectHeight, rectRadius);
  }

  public boolean checkCollision(float colX, float colY) {
    if (colX > (rectX - rectWidth) && colX < (rectX + rectWidth)) {
      if (colY > (rectY - rectHeight) && colY < (rectY + rectHeight)) {
        return true;
      }
    }
    return false;
  }

  public boolean isButtonPressed(float colX, float colY) {
    if (mousePressed) {
      pressed = true;
    } else {
      released = true;
    }
    if (pressed && released) {
      if (checkCollision(colX, colY)) {
        pressed = false;
        released = false;
        return true;
      } else {
        pressed = false;
        released = false;
      }
    }
    released = false;
    return false;
  }

  public void changePos(float x, float y) {
    rectX = x;
    rectY = y;
  }

  public void changeSize(float w, float h, float r) {
    rectWidth = w;
    rectHeight = h;
    rectRadius = r;
  }

  public void changeSize(float r) {
    rectWidth = r;
    rectHeight = r;
    rectRadius = r;
  }
  
  public float getX(){
    return rectX;
  }
  
  public float getY(){
    return rectY;
  }

  public float[] getPos() {
    float rtrn[] = {rectX, rectY};
    return rtrn;
  }

  public float getXRadius() {
    return rectWidth;
  }

  public float getYRadius() {
    return rectHeight;
  }

  public float[] getSize() {
    float rtrn[] = {rectWidth, rectHeight};
    return rtrn;
  }

}
class Point{
  public int x;
  public int y;
  
  Point(int x, int y){
    this.x = x;
    this.y = y;
  }
}
class Sky{
  long currentTime;
  int timeFrame;
  int flowerCounter = 0;
  
  int dayTime;
  int nightTime;
  int transitionTime; 
  int fullRotation;
  Moon moon;
  Sun sun;
  Point moonTopPos;
  Point sunTopPos;
  
  int[] skyColor;
  
  int[] nightColor = {7, 40, 132};
  int[] dayColor = {184, 223, 255};
  int[] transitionColor = {242, 148, 64};
  
  Sky(Flower flower, int dayTime, int nightTime, int transitionTime){
    currentTime = millis();
    this.dayTime = dayTime;
    this.nightTime = nightTime;
    this.transitionTime = transitionTime;
    fullRotation = dayTime + nightTime + transitionTime * 2;
    
    moon = new Moon();
    moonTopPos = moon.getMoon();
    
    sun = new Sun();
    sunTopPos = sun.getSun();
    
    skyColor = dayColor;   
  }
  
  public void setSky(int[] skyColor){
    this.skyColor = skyColor;
  }
  
  public void transitionTo(int[] timeColor, int[] currentStage){
    int[] mapColor = {0 , 0, 0};
    
    for(int i = 0; i < mapColor.length; i++){
      mapColor[i] = (int)map(timeFrame % 30, 0, 29, currentStage[i], timeColor[i]);
    }
    
    setSky(mapColor);
  }
  
  public void drawSunAndMoon(){
    moon.setMoon(new Point(moonTopPos.x, (int)(1060 + 1080 * cos((float)millis() % (fullRotation * 1000) / 3600000 * 360))));
    sun.setSun(new Point(sunTopPos.x, (int)(1280 - 1080 * cos((float)millis() % (fullRotation * 1000) / 3600000 * 360))));
    moon.draw();
    sun.draw();
  }
  
  public void draw(){
    currentTime = millis();
    timeFrame = (int)((currentTime / 1000) % fullRotation);
    
    if(timeFrame  >= (dayTime - 1)  && timeFrame < dayTime + transitionTime / 2){
      transitionTo(transitionColor, dayColor);
      
    }else if(timeFrame >= (dayTime + transitionTime - transitionTime / 3) && timeFrame < (dayTime + transitionTime)){
      transitionTo(nightColor, transitionColor);
      
    }else if(timeFrame >= (dayTime + transitionTime + nightTime - 1) 
    && timeFrame < (dayTime + transitionTime + nightTime + transitionTime / 2)){
      transitionTo(transitionColor, nightColor); 
      
    }else if(timeFrame >= (dayTime + transitionTime + nightTime + transitionTime - transitionTime / 3) 
    && timeFrame < (dayTime + transitionTime * 2 + nightTime)){
      transitionTo(dayColor, transitionColor);
      
    }
    
    
    if(millis() % (long)(fullRotation * 1000) > 26000){
      flowerCounter++;
      if(flowerCounter % 30 == 29){
        flower.wither();
      }
    }
    
    background(skyColor[0], skyColor[1], skyColor[2]);
    drawSunAndMoon();
  }
}
class Sun{
  
  PImage sun;
  int sunRotation = 45;
  Point position;
  
  Sun(){
    sun = loadImage("sun.png");
    this.position = new Point(250, 200);
  }
  
  public void setSun(Point position){
    this.position = position;
  }
  
  public Point getSun(){
    return position;
  }
  
  public void draw(){
    imageMode(CENTER);
    
    pushMatrix();
    translate(position.x, position.y);
    if(millis() % (long)4 == 3){
      sunRotation++;
    }
    rotate(radians(sunRotation % 360));
    image(sun, 0, 0);
    popMatrix();
    
    imageMode(CORNER);
  }
}
  public void settings() {  size(1928, 1080); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "flowerGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
