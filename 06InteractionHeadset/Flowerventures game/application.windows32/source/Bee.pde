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
  
  void setBee(Point position){
    this.position = position;
  }
  
  //Default maybe 100
  void setSize(int size){
    this.size = size;
  }
  
  void draw(){    
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
