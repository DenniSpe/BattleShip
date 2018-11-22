$(document).ready(function() {
  for (var i = 1; i <= 10; i++) {
    for (var j = 1; j <= 10; j++) {
      console.log("for");
      $("#cell-" + i + "-" + j).click(function() {
        getEventsFromServer(this.id);
      });
    }
  }
})

function getEventsFromServer(cellacliccata) {
	$.ajax({
		url : "getEvents",
		data: {'cella': cellacliccata},
		success : function(result) {
			$("#message").html(result);
		},
		error : function() {
			//call events again after some time
			setTimeout(function() {
				getEventsFromServer();
			}, 5000);
		}
	});
}
