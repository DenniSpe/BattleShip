function getEventsFromServer() {
	$.ajax({
		url : "refreshLobbyList",
		type : "POST",
		dataType : "json",
		success : function(result) {

			// $("#lobbies_div").html(result);

			// var ul = $("<ul></ul>").addClass("list-group");
			var url = $("#serverUrl").attr("value");
			// var chall = $("#callenger").attr("value");
			$("#lobbyList").html("")
			$.each(result, function(index, value) {
				var link = $("<a></a>").addClass("list-group-item text-left")
						.text(value.name).attr("href",
								url + "?lobby_id=" + value.id);
				var badge = $("<span></span>").addClass("badge").text(value.owner);
				link.append(badge);
				if (value.full == true) {
					link.addClass("list-group-item-danger");
					badge.text(value.owner + " vs " + value.challenger);
				} else if (index % 2 > 0) {
					link.addClass("list-group-item-secondary");
				}
				$("#lobbyList").append(link)
			});

			// $("#lobbyList").html(ul);

			setTimeout(function() {
				getEventsFromServer();
			}, 5000);

		},
		error : function(e) {
			// alert(e.responseText);
			console.log("JOIN ERROR: ", e);

			setTimeout(function() {
				getEventsFromServer();
			}, 15000);
		}
	});
}
function showModalInst() {
	var first = $("#firstTime").attr('value');
	if (first == "true") {
		$('#modal').modal('show');
	}
}
$(document).ready(function() {
	getEventsFromServer();
	showModalInst();

});