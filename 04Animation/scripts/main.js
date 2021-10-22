// // changing individual properties with code and using setInterval
 var rotationSpeed = 0.01;
 var myOtherBox = document.getElementById('myOtherBox');
var myBox = document.getElementById('myBox');

 function spin(){
	myOtherBox.object3D.rotation.y += rotationSpeed;
 	console.log(myOtherBox.object3D.rotation.y);
	myBox.object3D.rotation.y += rotationSpeed;
	console.log(myBox.object3D.rotation.y);
	rotationSpeed += 0.0001;
	if (rotationSpeed > 1) {
		rotationSpeed = 0.01;
		}
 }

 setInterval(spin, 16); //equivalent to 60 fps
