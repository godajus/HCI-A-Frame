var rotationSpeed = 0.001;
var rotationSpeed2 = 0.001;
var myOtherBox = document.getElementById('myOtherBox');
var myBox = document.getElementById('myBox');

function spin(){
	myOtherBox.object3D.rotation.x += rotationSpeed;
	myBox.object3D.rotation.x += rotationSpeed2;
	myOtherBox.object3D.rotation.y += rotationSpeed;
	myOtherBox.object3D.rotation.z += rotationSpeed/2;
	console.log(myOtherBox.object3D.rotation);
	console.log(myBox.object3D.rotation);
}

setInterval(spin, 16);


myBox.addEventListener('mouseenter', function(){
 	rotationSpeed2 = 0.01;
 	console.log('mouse enter');
});

myBox.addEventListener('mouseleave', function(){
 	rotationSpeed2 = 0.001;
 	console.log('mouse leave');
});

myOtherBox.addEventListener('mouseenter', function(){
 	rotationSpeed = 0.01;
 	console.log('mouse enter');
});

myOtherBox.addEventListener('mouseleave', function(){
 	rotationSpeed = 0.001;
 	console.log('mouse leave');
});
