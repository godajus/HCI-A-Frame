// // changing individual properties with code and using setInterval
 var rotationSpeed = 0.01;
var rotationSpeed2 = 0.01;
 var myOtherBox = document.getElementById('myOtherBox');
var myBox = document.getElementById('myOtherBox');

 function spin(){
	myOtherBox.object3D.rotation.y += rotationSpeed;
 	console.log(myOtherBox.object3D.rotation.y);
	rotationSpeed += 0.0001;
	if (rotationSpeed > 1) {
		rotationSpeed = 0.01;
		}
	 
	 
	 
	 myBox.object3D.rotation.x += rotationSpeed2;
 	console.log(myBox.object3D.rotation.x);
	rotationSpeed2 += 0.001;
	if (rotationSpeed2 > 1) {
		rotationSpeed2 = 0.01;
		}
 }

 setInterval(spin, 16); //equivalent to 60 fps
