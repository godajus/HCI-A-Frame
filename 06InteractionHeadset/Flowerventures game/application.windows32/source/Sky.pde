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
  
  void setSky(int[] skyColor){
    this.skyColor = skyColor;
  }
  
  void transitionTo(int[] timeColor, int[] currentStage){
    int[] mapColor = {0 , 0, 0};
    
    for(int i = 0; i < mapColor.length; i++){
      mapColor[i] = (int)map(timeFrame % 30, 0, 29, currentStage[i], timeColor[i]);
    }
    
    setSky(mapColor);
  }
  
  void drawSunAndMoon(){
    moon.setMoon(new Point(moonTopPos.x, (int)(1060 + 1080 * cos((float)millis() % (fullRotation * 1000) / 3600000 * 360))));
    sun.setSun(new Point(sunTopPos.x, (int)(1280 - 1080 * cos((float)millis() % (fullRotation * 1000) / 3600000 * 360))));
    moon.draw();
    sun.draw();
  }
  
  void draw(){
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
