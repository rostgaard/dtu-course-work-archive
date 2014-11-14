function saveFloorplan(){
	var offsets = document.getElementById('sensor').getBoundingClientRect();
	var sensor1_y = offsets.top;
	var sensor1_x = offsets.left;
	alert("Coordinates for Sensor 1: x = " + sensor1_x + ", y = " + sensor1_y);
}