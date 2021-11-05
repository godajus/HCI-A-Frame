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
  
  Point getPos(){
    return position;
  }
  
  void pollinate(){
    if(flowerHealth < 100){
      flowerHealth++;
    }
  }
  
  void wither(){
    if(flowerHealth >= 0){
      flowerHealth--;
    }
  }
  
  int getFlowerHealth(){
    return flowerHealth;
  }
  
  void draw(){
    image(flower, position.x, position.y + 70);
    if(flowerHealth > 0){
      image(flowerHead, position.x - 30, position.y - 40);
    }else{
      image(deadFlowerHead, position.x - 30, position.y - 80);
    }
  }
}
