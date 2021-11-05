class PlayButton {

  private float rectX;
  private float rectY;
  private float rectWidth;
  private float rectHeight;
  private float rectRadius;
  private boolean pressed;
  private boolean released;
  private color rectColor;

  PlayButton(float x, float y, float w, float h, float r, color c) {
    rectX = x;
    rectY = y;
    rectWidth = w;
    rectHeight = h;
    rectRadius = r;
    rectColor = c;
    pressed = false;
    released = false;
  }

  void display() {
    fill(rectColor);
    strokeWeight((width / 270 + height / 480)/2);
    rectMode(RADIUS);
    rect(rectX, rectY, rectWidth, rectHeight, rectRadius);
  }

  boolean checkCollision(float colX, float colY) {
    if (colX > (rectX - rectWidth) && colX < (rectX + rectWidth)) {
      if (colY > (rectY - rectHeight) && colY < (rectY + rectHeight)) {
        return true;
      }
    }
    return false;
  }

  boolean isButtonPressed(float colX, float colY) {
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

  void changePos(float x, float y) {
    rectX = x;
    rectY = y;
  }

  void changeSize(float w, float h, float r) {
    rectWidth = w;
    rectHeight = h;
    rectRadius = r;
  }

  void changeSize(float r) {
    rectWidth = r;
    rectHeight = r;
    rectRadius = r;
  }
  
  float getX(){
    return rectX;
  }
  
  float getY(){
    return rectY;
  }

  float[] getPos() {
    float rtrn[] = {rectX, rectY};
    return rtrn;
  }

  float getXRadius() {
    return rectWidth;
  }

  float getYRadius() {
    return rectHeight;
  }

  float[] getSize() {
    float rtrn[] = {rectWidth, rectHeight};
    return rtrn;
  }

}
