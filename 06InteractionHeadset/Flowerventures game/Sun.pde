class Sun{
  
  PImage sun;
  int sunRotation = 45;
  Point position;
  
  Sun(){
    sun = loadImage("sun.png");
    this.position = new Point(250, 200);
  }
  
  void setSun(Point position){
    this.position = position;
  }
  
  Point getSun(){
    return position;
  }
  
  void draw(){
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
