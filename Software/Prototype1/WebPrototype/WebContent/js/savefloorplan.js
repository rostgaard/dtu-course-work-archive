function saveFloorplan(){
	var offsets = document.getElementById('sensor').getBoundingClientRect();
	var top = offsets.top;
	var left = offsets.left;
	alert("Coordinates for Sensor 1: x = " + left + ", y = " + top);
}