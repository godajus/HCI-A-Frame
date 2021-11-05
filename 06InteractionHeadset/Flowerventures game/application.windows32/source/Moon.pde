class Moon{
  
  PImage moon;
  Point position;
  
  Moon(){
    moon = loadImage("moon.png");
    this.position = new Point(1550, 20);
  }
  
  void setMoon(Point position){
    this.position = position;
  }
  
  Point getMoon(){
    return position;
  }
  
  void draw(){
    image(moon, position.x, position.y);
  }
}
