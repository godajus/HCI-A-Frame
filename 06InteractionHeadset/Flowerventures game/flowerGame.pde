import processing.sound.*;
SoundFile file;
PImage title;

PImage playAgainMushroom;
PlayButton playButton;

Grass grass;
Flower flower;
BeeSwarm beeSwarm;
Sky sky;

void initialize(){
  grass = new Grass();
  flower = new Flower(new Point(870, 500));
  sky = new Sky(flower, 24, 24, 6);
  beeSwarm = new BeeSwarm(flower, 60, new Amplitude(this),new AudioIn(this, 0));
  
  playButton = new PlayButton(1390.0, 800.0, 100.0, 150.0, 20.0, color(0.0));
  playAgainMushroom = loadImage("playAgain.png");
}

void setup(){
  size(1928, 1080);
  title = loadImage("title.png");
  file = new SoundFile(this,"data/gameOver.mp3");
  
  initialize();
}

void draw(){
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
    image(playAgainMushroom, 1270.0, 600.0);
    
    if(playButton.isButtonPressed(mouseX, mouseY)){
      file.play();
      file.amp(.2);
      initialize();
    }
  }
}
