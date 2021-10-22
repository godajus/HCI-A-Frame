

// changing individual properties with code and using setInterval
var rotationSpeed = 0.01;
var rotationSpeed2 = 0.01;
var myOtherBox = document.getElementById('myOtherBox');
var myBox = document.getElementById('myBox');


function spin(){
myOtherBox.object3D.rotation.y -= rotationSpeed;
rotationspeed+= 1;
 if (rotationSpeed > 100){
  rotationSpeed = 0;
  }
console.log(myOtherBox.object3D.rotation.y);
 console.log(rotationSpeed);
 }

function spin2(){
myBox.object3D.rotation.x -= rotationSpeed2;
rotationspeed2 += 1;
 if (rotationSpeed2 > 500){
  rotationSpeed = 0;
  }
console.log(myBox.object3D.rotation.x);
 console.log(rotationSpeed2);
 }

 setInterval(spin, 16); //equivalent to 60 fps
setInterval(spin2, 16); //equivalent to 60 fps
