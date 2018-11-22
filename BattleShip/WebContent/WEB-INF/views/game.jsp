<html>

<head>
	<title>Battleship</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">


	<script type="text/javascript" src="resources/assetsGame/javascripts/ext/underscore.js"></script>
	<script type="text/javascript" src="resources/assetsGame/vendor/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="resources/assetsGame/javascripts/ext/jshashtable-2.1.js"></script>
	<script type="text/javascript" src="resources/assetsGame/javascripts/ext/jquery.numeric.js"></script>
	<script type="text/javascript" src="resources/assetsGame/javascripts/ext/jquery.numberformatter.js"></script>
	<script type="text/javascript" src="resources/assetsGame/javascripts/ext/backbone.js"></script>
	<script type="text/javascript" src="resources/assetsGame/javascripts/ext/icanhaz.js"></script>
	<script type="text/javascript" src="resources/assetsGame/javascripts/gameScript.js"></script>
	<script type="text/javascript" src="resources/assetsGame/vendor/bootstrap/js/bootstrap.min.js"></script>


	<link href='https://fonts.googleapis.com/css?family=Russo+One' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="resources/assetsGame/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/assetsGame/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="resources/assetsGame/vendor/animate.css">
	<link rel="stylesheet" href="resources/assetsGame/stylesheets/main.css" type="text/css" media="screen" charset="utf-8">

</head>

<body>
	<div class="row">

		<div class="col-6">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-6 text-center">
				<div id="container">
					<table class="board table table-responsive animated fadeInUp">
						<tbody>
							<tr>
								<td class="cell cell-title-left cell-title-top" id="cell-0-0"></td>
								<td class="cell cell-title-top" id="cell-1-0">A</td>
								<td class="cell cell-title-top" id="cell-2-0">B</td>
								<td class="cell cell-title-top" id="cell-3-0">C</td>
								<td class="cell cell-title-top" id="cell-4-0">D</td>
								<td class="cell cell-title-top" id="cell-5-0">E</td>
								<td class="cell cell-title-top" id="cell-6-0">F</td>
								<td class="cell cell-title-top" id="cell-7-0">G</td>
								<td class="cell cell-title-top" id="cell-8-0">H</td>
								<td class="cell cell-title-top" id="cell-9-0">I</td>
								<td class="cell cell-title-top" id="cell-10-0">J</td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-1">1</td>
								<td class="cell miss-cell" id="cell-1-1"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-2-1"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-3-1"></td>
								<td class="cell" id="cell-4-1"></td>
								<td class="cell miss-cell" id="cell-5-1"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-6-1"></td>
								<td class="cell" id="cell-7-1"></td>
								<td class="cell" id="cell-8-1"></td>
								<td class="cell" id="cell-9-1"></td>
								<td class="cell" id="cell-10-1"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-2">2</td>
								<td class="cell showBoat destroyer" id="cell-1-2"></td>
								<td class="cell" id="cell-2-2"></td>
								<td class="cell miss-cell" id="cell-3-2"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-4-2"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell showBoat aircraft-carrier" id="cell-5-2"><img class="hit marker animated bounceIn" src="resources/images/1331900690_fire.png"></td>
								<td class="cell showBoat aircraft-carrier" id="cell-6-2"><img class="hit marker animated bounceIn" src="images/1331900690_fire.png"></td>
								<td class="cell showBoat aircraft-carrier" id="cell-7-2"><img class="hit marker animated bounceIn" src="images/1331900690_fire.png"></td>
								<td class="cell showBoat aircraft-carrier" id="cell-8-2"></td>
								<td class="cell showBoat aircraft-carrier" id="cell-9-2"></td>
								<td class="cell" id="cell-10-2"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-3">3</td>
								<td class="cell showBoat destroyer" id="cell-1-3"><img class="hit marker animated bounceIn" src="images/1331900690_fire.png"></td>
								<td class="cell miss-cell" id="cell-2-3"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-3-3"></td>
								<td class="cell" id="cell-4-3"></td>
								<td class="cell" id="cell-5-3"></td>
								<td class="cell" id="cell-6-3"></td>
								<td class="cell" id="cell-7-3"></td>
								<td class="cell" id="cell-8-3"></td>
								<td class="cell" id="cell-9-3"></td>
								<td class="cell" id="cell-10-3"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-4">4</td>
								<td class="cell miss-cell" id="cell-1-4"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-2-4"></td>
								<td class="cell miss-cell" id="cell-3-4"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-4-4"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-5-4"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell showBoat battleship" id="cell-6-4"><img class="hit marker animated bounceIn" src="images/1331900690_fire.png"></td>
								<td class="cell showBoat battleship" id="cell-7-4"></td>
								<td class="cell showBoat battleship" id="cell-8-4"><img class="hit marker animated bounceIn" src="images/1331900690_fire.png"></td>
								<td class="cell showBoat battleship" id="cell-9-4"><img class="hit marker animated bounceIn" src="images/1331900690_fire.png"></td>
								<td class="cell" id="cell-10-4"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-5">5</td>
								<td class="cell miss-cell" id="cell-1-5"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell showBoat submarine" id="cell-2-5"><img class="hit marker animated bounceIn" src="images/1331900690_fire.png"></td>
								<td class="cell showBoat submarine" id="cell-3-5"></td>
								<td class="cell showBoat submarine" id="cell-4-5"></td>
								<td class="cell" id="cell-5-5"></td>
								<td class="cell" id="cell-6-5"></td>
								<td class="cell" id="cell-7-5"></td>
								<td class="cell miss-cell" id="cell-8-5"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-9-5"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-10-5"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-6">6</td>
								<td class="cell miss-cell" id="cell-1-6"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-2-6"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-3-6"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-4-6"></td>
								<td class="cell miss-cell" id="cell-5-6"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-6-6"></td>
								<td class="cell miss-cell" id="cell-7-6"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-8-6"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-9-6"></td>
								<td class="cell" id="cell-10-6"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-7">7</td>
								<td class="cell" id="cell-1-7"></td>
								<td class="cell miss-cell" id="cell-2-7"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-3-7"></td>
								<td class="cell miss-cell" id="cell-4-7"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-5-7"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-6-7"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-7-7"></td>
								<td class="cell" id="cell-8-7"></td>
								<td class="cell" id="cell-9-7"></td>
								<td class="cell" id="cell-10-7"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-8">8</td>
								<td class="cell miss-cell" id="cell-1-8"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell miss-cell" id="cell-2-8"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-3-8"></td>
								<td class="cell" id="cell-4-8"></td>
								<td class="cell miss-cell" id="cell-5-8"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-6-8"></td>
								<td class="cell miss-cell" id="cell-7-8"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-8-8"></td>
								<td class="cell" id="cell-9-8"></td>
								<td class="cell" id="cell-10-8"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-9">9</td>
								<td class="cell miss-cell" id="cell-1-9"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-2-9"></td>
								<td class="cell miss-cell" id="cell-3-9"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell showBoat cruiser" id="cell-4-9"></td>
								<td class="cell showBoat cruiser" id="cell-5-9"></td>
								<td class="cell showBoat cruiser" id="cell-6-9"></td>
								<td class="cell" id="cell-7-9"></td>
								<td class="cell miss-cell" id="cell-8-9"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-9-9"></td>
								<td class="cell" id="cell-10-9"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-10">10</td>
								<td class="cell miss-cell" id="cell-1-10"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-2-10"></td>
								<td class="cell" id="cell-3-10"></td>
								<td class="cell miss-cell" id="cell-4-10"><i class="miss marker animated flipInX fa fa-times fa-2x text-muted"></i></td>
								<td class="cell" id="cell-5-10"></td>
								<td class="cell" id="cell-6-10"></td>
								<td class="cell" id="cell-7-10"></td>
								<td class="cell" id="cell-8-10"></td>
								<td class="cell" id="cell-9-10"></td>
								<td class="cell" id="cell-10-10"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>



		<div class="col-6">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-6 text-center">
				<div id="container">
					<table class="board table table-responsive animated fadeInUp">
						<tbody>
							<tr>
								<td class="cell cell-title-left cell-title-top" id="cell-0-0"></td>
								<td class="cell cell-title-top" id="cell-1-0">A</td>
								<td class="cell cell-title-top" id="cell-2-0">B</td>
								<td class="cell cell-title-top" id="cell-3-0">C</td>
								<td class="cell cell-title-top" id="cell-4-0">D</td>
								<td class="cell cell-title-top" id="cell-5-0">E</td>
								<td class="cell cell-title-top" id="cell-6-0">F</td>
								<td class="cell cell-title-top" id="cell-7-0">G</td>
								<td class="cell cell-title-top" id="cell-8-0">H</td>
								<td class="cell cell-title-top" id="cell-9-0">I</td>
								<td class="cell cell-title-top" id="cell-10-0">J</td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-1">1</td>
								<td class="cell" id="cell-1-1"></td>
								<td class="cell" id="cell-2-1"></td>
								<td class="cell" id="cell-3-1"></td>
								<td class="cell" id="cell-4-1"></td>
								<td class="cell" id="cell-5-1"></td>
								<td class="cell" id="cell-6-1"></td>
								<td class="cell" id="cell-7-1"></td>
								<td class="cell" id="cell-8-1"></td>
								<td class="cell" id="cell-9-1"></td>
								<td class="cell" id="cell-10-1"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-2">2</td>
								<td class="cell" id="cell-1-2"></td>
								<td class="cell" id="cell-2-2"></td>
								<td class="cell" id="cell-3-2"></td>
								<td class="cell" id="cell-4-2"></td>
								<td class="cell" id="cell-5-2"></td>
								<td class="cell" id="cell-6-2"></td>
								<td class="cell" id="cell-7-2"></td>
								<td class="cell" id="cell-8-2"></td>
								<td class="cell" id="cell-9-2"></td>
								<td class="cell" id="cell-10-2"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-3">3</td>
								<td class="cell" id="cell-1-3"></td>
								<td class="cell" id="cell-2-3"></td>
								<td class="cell" id="cell-3-3"></td>
								<td class="cell" id="cell-4-3"></td>
								<td class="cell" id="cell-5-3"></td>
								<td class="cell" id="cell-6-3"></td>
								<td class="cell" id="cell-7-3"></td>
								<td class="cell" id="cell-8-3"></td>
								<td class="cell" id="cell-9-3"></td>
								<td class="cell" id="cell-10-3"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-4">4</td>
								<td class="cell" id="cell-1-4"></td>
								<td class="cell" id="cell-2-4"></td>
								<td class="cell" id="cell-3-4"></td>
								<td class="cell" id="cell-4-4"></td>
								<td class="cell" id="cell-5-4"></td>
								<td class="cell" id="cell-6-4"></td>
								<td class="cell" id="cell-7-4"></td>
								<td class="cell" id="cell-8-4"></td>
								<td class="cell" id="cell-9-4"></td>
								<td class="cell" id="cell-10-4"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-5">5</td>
								<td class="cell" id="cell-1-5"></td>
								<td class="cell" id="cell-2-5"></td>
								<td class="cell" id="cell-3-5"></td>
								<td class="cell" id="cell-4-5"></td>
								<td class="cell" id="cell-5-5"></td>
								<td class="cell" id="cell-6-5"></td>
								<td class="cell" id="cell-7-5"></td>
								<td class="cell" id="cell-8-5"></td>
								<td class="cell" id="cell-9-5"></td>
								<td class="cell" id="cell-10-5"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-6">6</td>
								<td class="cell" id="cell-1-6"></td>
								<td class="cell" id="cell-2-6"></td>
								<td class="cell" id="cell-3-6"></td>
								<td class="cell" id="cell-4-6"></td>
								<td class="cell" id="cell-5-6"></td>
								<td class="cell" id="cell-6-6"></td>
								<td class="cell" id="cell-7-6"></td>
								<td class="cell" id="cell-8-6"></td>
								<td class="cell" id="cell-9-6"></td>
								<td class="cell" id="cell-10-6"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-7">7</td>
								<td class="cell" id="cell-1-7"></td>
								<td class="cell" id="cell-2-7"></td>
								<td class="cell" id="cell-3-7"></td>
								<td class="cell" id="cell-4-7"></td>
								<td class="cell" id="cell-5-7"></td>
								<td class="cell" id="cell-6-7"></td>
								<td class="cell" id="cell-7-7"></td>
								<td class="cell" id="cell-8-7"></td>
								<td class="cell" id="cell-9-7"></td>
								<td class="cell" id="cell-10-7"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-8">8</td>
								<td class="cell" id="cell-1-8"></td>
								<td class="cell" id="cell-2-8"></td>
								<td class="cell" id="cell-3-8"></td>
								<td class="cell" id="cell-4-8"></td>
								<td class="cell" id="cell-5-8"></td>
								<td class="cell" id="cell-6-8"></td>
								<td class="cell" id="cell-7-8"></td>
								<td class="cell" id="cell-8-8"></td>
								<td class="cell" id="cell-9-8"></td>
								<td class="cell" id="cell-10-8"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-9">9</td>
								<td class="cell" id="cell-1-9"></td>
								<td class="cell" id="cell-2-9"></td>
								<td class="cell" id="cell-3-9"></td>
								<td class="cell" id="cell-4-9"></td>
								<td class="cell" id="cell-5-9"></td>
								<td class="cell" id="cell-6-9"></td>
								<td class="cell" id="cell-7-9"></td>
								<td class="cell" id="cell-8-9"></td>
								<td class="cell" id="cell-9-9"></td>
								<td class="cell" id="cell-10-9"></td>
							</tr>
							<tr>
								<td class="cell cell-title-left" id="cell-0-10">10</td>
								<td class="cell" id="cell-1-10"></td>
								<td class="cell" id="cell-2-10"></td>
								<td class="cell" id="cell-3-10"></td>
								<td class="cell" id="cell-4-10"></td>
								<td class="cell" id="cell-5-10"></td>
								<td class="cell" id="cell-6-10"></td>
								<td class="cell" id="cell-7-10"></td>
								<td class="cell" id="cell-8-10"></td>
								<td class="cell" id="cell-9-10"></td>
								<td class="cell" id="cell-10-10"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>



	</div>

<h2 id="message"></h2>
	<div class="row vertical-align">

		<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 col-lg-offset-1">
			<div class="row stats animated fadeInRight">

				<div class="col-xs-12 text-center">
					<i class="fa fa-ship fa-5x animated pulse"></i>
					<h2></h2>
				</div>

				<div class="col-xs-12">
					<div class="panel panel-info">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-bullseye fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">
										<span id="totalShotsRemaining">40</span>
									</div>
									<div>Colpi andati bene</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-12">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-retweet fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">
										<span id="shotsRemainingForIteration">40</span>
									</div>
									<div>Colpi rimasti</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-money fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">
										<span id="funds">US$ 400.000</span>
									</div>
									<div>Bilancio rimanente</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

		<div class="col-xs-12 col-sm-8 col-md-8 col-lg-6 text-center">
			<table class="table">
				<thead>
					<tr>
						<th>Player Name</th>
						<th>Cell</th>
						<th>Result</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Default</td>
						<td>Defaultson</td>
						<td>def@somemail.com</td>
					</tr>
					<tr class="success">
						<td>Success</td>
						<td>Doe</td>
						<td>john@example.com</td>
					</tr>
					<tr class="danger">
						<td>Danger</td>
						<td>Moe</td>
						<td>mary@example.com</td>
					</tr>
					<tr class="info">
						<td>Info</td>
						<td>Dooley</td>
						<td>july@example.com</td>
					</tr>
					<tr class="warning">
						<td>Warning</td>
						<td>Refs</td>
						<td>bo@example.com</td>
					</tr>
					<tr class="active">
						<td>Active</td>
						<td>Activeson</td>
						<td>act@example.com</td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>

</body>

</html>
