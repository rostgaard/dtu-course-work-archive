/**
 * 
 */
function generateLists(){

	$.ajax({
		url     : "localhost:8080/Prototype1/rest/events/getAllEvents",
		dataType: "jsonp",
		jsonp   : "jsonp",
		success : function(data) { 
			items = data; 
			for(var i in items.reverse())
			{
				var value = items[i].value;
				var id = items[i].id;
				var element = '<a href="#" class="list-group-item"><i class="fa fa-shield fa-fw"></i> '+id+'<span class="pull-right text-muted small"><em>'+value+'</em></span></a>';
				$('#box').append(element);
			} } 
	});


	$
	.getJSON(
			"localhost:8080/Prototype1/rest/apps/getAllApps",
			function(data) {

				$
				.each(
						data.items,
						function(i, items) {
							var id = items[i].id;
							var devicetype = items[i].mac;
							var element = '<a href="#" class="list-group-item"><i class="fa fa-mobile fa-fw"></i> '
								+ mac
								+ '<span class="pull-right text-muted small"><em>'
								+ devicetype
								+ '</em></span></a>';
							$('#devs').append(element);
						});
			});

}