import processing.sound.*;

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
  
  void draw(){  
    int result = (int)map(amp.analyze(), 0.0, 1.0, 0.0, 1000.0);
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
