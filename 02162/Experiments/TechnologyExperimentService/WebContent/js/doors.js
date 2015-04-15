  var canvas = document.getElementById('myCanvas');
      var context = canvas.getContext('2d');
      var imageObj = new Image();
		var destX = canvas.width;
		var destY = canvas.height;
      imageObj.onload = function() {
        context.drawImage(imageObj, 0, 0,destX,destY);
      };
      imageObj.src = 'floor_plan_example.png';