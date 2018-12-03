$(document).ready(
		function() {
			var amount = $("#amount").attr("value");
			$("select").val(amount)
			$("select").change(function() {
				var value = this.value;
				var url = $("#serverUrl").attr("value");
				var page = $("#page").attr("value");
				window.location = url + "?amount=" + value
			});

			$('#statsTabs a').click(function(e) {
				e.preventDefault()
				$(this).tab('show')
			});

			$('#myTab a[href="#matches"]').tab('show');
			$('#myTab a[href="#charts"]').tab('show');

			// CHARTS
			// ////////////////FIRST//////////////////////////////////////

			// ////////////////////////SECOND///////////////////////////////////

			$.ajax({
				url : "winLooseData",
				type : "POST",
				dataType : "json",
				success : function(result) {
					var ctx = document.getElementById("winsLooses").getContext(
							'2d');
					var myChart = new Chart(ctx, {
						type : 'bar',
						data : {
							labels : [ "Wins", "Looses" ],
							datasets : [ {
								label : 'Wins vs Looses',
								data : result,
								backgroundColor : [ 'rgba(54, 162, 235, 0.2)',
										'rgba(255, 99, 132, 0.2)' ],
								borderColor : [ 'rgba(54, 162, 235, 1)',
										'rgba(255,99,132,1)' ],
								borderWidth : 1
							} ]
						},
						options : {
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ]
							}
						}
					});
				},
				error : function(e) {
					console.log(e.responseText);
				}
			});

			$.ajax({
				url : "matchesDuration",
				type : "POST",
				dataType : "json",
				success : function(result) {
					
					var avg = [];
					$.each(result.times, function( index, value ) {
						  avg[index] = result.avg;
					});
					var ctx = document.getElementById("timeChart").getContext(
							'2d');
					var myChart = new Chart(ctx, {
						type : 'line',
						data : {
							labels : result.labels,
							datasets : [ {
								label : 'Games Duration',
								data : result.times,
								backgroundColor : [ 'rgba(255, 99, 132, 0.2)' ],
								borderColor : [ 'rgba(255,99,132,1)' ],
								borderWidth : 1
							},{
						          label: "Avg",
						          data: avg,
						          backgroundColor: [
						            'rgba(0, 137, 132, .2)',
						          ],
						          borderColor: [
						            'rgba(0, 10, 130, .7)',
						          ],
						          borderWidth: 2
						        } ]
						},
						options : {
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ],
								xAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ]
							}
						}
					});
				},
				error : function(e) {
					console.log(e.responseText);
				}
			});
		});