angular
		.module('gtApp', [ 'angular.filter' ])
		.controller(
				'homeController',
				function($http, $scope) {
					$scope.driver = {id:'',nick:''};
					$scope.editChamp = {id:'',name:''};
					$scope.show = true;
					$scope.showTable = false;
					$scope.showChampRaces = false;
					$scope.showChampRacesDetails = false;
					$scope.raceIds = [];
					$scope.info;
					$scope.points = [ 0, 25, 18, 15, 12, 10, 8, 6, 4, 2, 1, 0,
							0, 0, 0, 0, 0 ];
					$scope.lastThreeRaces=[];
					$scope.positions=[];
					$scope.champTable = [];
					$scope.editShow = function() {
						if ($scope.show == true) {
							
							$scope.show = false;

						} else {
							
							$scope.champTable.length = 0;
							$scope.champResults.length = 0;
							$scope.show = true;
							$scope.showTable = false;
							$scope.showChampRaces = false;
						}

					};
					$scope.editShowTable = function() {
						if ($scope.showTable == true) {
							$scope.show = true;
							$scope.showTable = false;
							
						} else {
							
							$scope.champTable.length = 0;
							
							$scope.showTable = true;
							$scope.show = false;
							$scope.showChampRaces = false;
						}
						
					};
					$scope.editShowChampRaces = function() {
						if ($scope.showChampRaces == true) {
							$scope.show = true;
							$scope.showChampRaces = false;
							
						} else {
							
							$scope.champTable.length = 0;
							
							$scope.showChampRaces = true;
							$scope.showTable = false;
							$scope.show = false;
						}
						
					};
					$scope.editShowChampRacesDetails = function() {
						if ($scope.showChampRacesDetails == true) {
							$scope.showChampRaces = true;
							$scope.showChampRacesDetails = false;
							
						} else {
							
							$scope.champTable.length = 0;
							
							$scope.showChampRacesDetails = true;
							$scope.showChampRaces = false;
							$scope.showTable = false;
							$scope.show = false;
						}
						
					};

					$scope.getAllDrivers = function() {
						$http.get('api/driver/get/all').success(function(data) {
							$scope.drivers = data;
						})
					};

					$scope.getAllChamps = function() {
						$http.get('api/champ/get/all').success(function(data) {
							$scope.champs = data;
						})
					};

					$scope.getChamp = function(id) {
						$http.get('api/champ/get/' + id).success(
								function(data) {
									$scope.editChamp = data;
								})
					};
					$scope.getRacesByChampId = function(id){
						$http.get('api/race/get/byChampId/'+ id).success(function(data){
							$scope.racesByChampId = data;
							$scope.info = $scope.racesByChampId;
						})
					};
					
					$scope.getRaceById = function(id){
						$http.get('api/race/get/byRaceId/'+id).success(function(data){
							$scope.race = data;
						})
					};
					$scope.getLastThreeRaces = function() {
						$http.get('api/race/get/lastThree').success(
								function(data) {
									$scope.lastThreeRaces = data;
									getPositionsFormLastThree();
								})
					};

					getPositionsFormLastThree = function() {
						for (i = 0; i < $scope.lastThreeRaces.length; i++) {
							$scope
									.getPositionsByRaceId($scope.lastThreeRaces[i].id);
						}
					};

					

					
					createTable = function(){
						
						var position = {nick:'', points:'', position:''};
						for(i=0;i<$scope.champResults.first.length;i++){
							position = {nick:$scope.champResults.first[i].nick , points:$scope.champResults.second[i], position:i+1};
							$scope.champTable.push(position);
						}
					};
					
					$scope.getTableByChampId = function(id) {

						var ids = $scope.raceIds;
						for (i = 0; i < ids.length; i++) {
							$scope.getPositionsByRaceId(ids[i]);
						}

					};
					
					$scope.getResultsByChampId = function(id) {
						$scope.getChamp(id);
						$http.get('api/position/get/resultsByChampId/' + id)
								.success(function(data) {

									$scope.champResults = data;
									createTable();
									$scope.getRacesByChampId(id);

								})

					};
					
					$scope.getPositionsByRaceId = function(id) {
						$scope.positions.length = 0;
						$http.get('api/position/get/byRaceId/' + id).success(
								function(data) {
									$scope.positions.push(data);

								})

					};
					
					$scope.info = $scope.racesByChampId;
				});