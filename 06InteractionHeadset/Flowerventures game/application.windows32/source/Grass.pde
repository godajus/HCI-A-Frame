class Grass{
  
  PImage grass;
  Point position;
  
  Grass(){
    grass = loadImage("grass.png");
    this.position = new Point(0, 0);
  }
  
  void draw(){
    image(grass, position.x, position.y);
  }
}
