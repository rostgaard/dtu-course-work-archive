var wsUri = "ws://" + document.location.host + document.location.pathname + "endpoint";

var websocket = new WebSocket(wsUri);
websocket.onmessage = function(evt) { onMessage(evt) };

function onError(evt) {
	writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
                
function onMessage(evt) {
    console.log("received: " + evt.data);
    eventUpdate(evt);
}

function eventUpdate(evt){
	var items;
	$.getJSON( "http://se-se2-e14-glassfish-c.compute.dtu.dk:8080/TechnologyExperiment/rest/events/getAllEvents", function( data ) {
		items = data;
		for(var i in items.reverse())
		{
			var type = items[i].eventType;
			var time = jQuery.timeago(new Date(items[i].time));
			var element = '<a href="#" class="list-group-item"><i class="fa fa-shield fa-fw"></i> '+type.replace("SHAKE","Door moved").replace("PLAY_SOUND","Sound played")+'<span class="pull-right text-muted small"><em>'+time+'</em></span></a>';
			$('#box').append(element);
		}
	});

}