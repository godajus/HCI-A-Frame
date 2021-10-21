

// changing individual properties with code and using setInterval
var rotationSpeed = 0.01;
var myOtherBox = document.getElementById('myOtherBox');

function spin(){
myOtherBox.object3D.rotation.y -= rotationSpeed;
rotationspeed+= 1;
 if (rotationSpeed > 100){
  rotationSpeed = 0;
  }
console.log(myOtherBox.object3D.rotation.y);
 console.log(rotationSpeed);
 }

 setInterval(spin, 16); //equivalent to 60 fps
