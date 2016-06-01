var gtApp = angular.module('gtApp',['angular.filter']);






gtApp.controller('homeController', function($http, $scope){
	
	$scope.getAllDrivers = function(){
		$http.get('api/driver/get/all').success(function(data){
			$scope.drivers = data;
		})
	};
	
});

gtApp.controller('adminController', function($http, $scope){
	$scope.driver={nick:""};
	$scope.editDriver;
	$scope.info='';
	$scope.show = true;
	$scope.editShow = function(){
		if($scope.show==true)
			$scope.show = false;
		else $scope.show = true;
		
	};
	$scope.createDriver = function(){
		driver=$scope.driver;
		$http.post('api/driver/create',driver).success(function(){
			$scope.info = 'Kierowca został dodany';
			$scope.driver = {nick:""};
			$scope.getAllDrivers();
			$scope.show = true;
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	$scope.getDriver = function(id){
		$http.get('api/driver/get/'+id).success(function(data){
			$scope.editDriver = data;
		})
	};
	
	
	
	$scope.getAllDrivers = function(){
		$http.get('api/driver/get/all').success(function(data){
			$scope.drivers = data;
		})
	};
	
	$scope.updateDriver = function(){
		driver = $scope.editDriver;
		$http.put('api/driver/update', driver).success(function(){
			$scope.info = 'Dane kierowcy zostały zaktualizowane';
			$scope.editDriver = null;
			$scope.show = true;
			$scope.getAllDrivers();
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	
	$scope.deleteDriver = function(id){
		$http.delete('api/driver/delete/'+id).success(function(){
			$scope.info = 'Kierowca został usunięty';
			$scope.editDriver = null;
			$scope.show = true;
			$scope.getAllDrivers();
		})
	};
	
	
	
	$scope.champ={name:"", numberOfRaces:"" };
	$scope.editChamp;
	
	$scope.createChamp = function(){
		champ=$scope.champ;
		$http.post('api/champ/create',champ).success(function(){
			$scope.info = 'Mistrzostwa zostały dodane';
			$scope.champ = {name:"", numberOfRaces:""};
			$scope.getAllChamps();
			$scope.show = true;
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	$scope.getChamp = function(id){
		$http.get('api/champ/get/'+id).success(function(data){
			$scope.editChamp = data;
		})
	};
	
	$scope.getAllChamps = function(){
		$http.get('api/champ/get/all').success(function(data){
			$scope.champs = data;
		})
	};
	
	$scope.updateChamp = function(){
		champ = $scope.editChamp;
		$http.put('api/champ/update', champ).success(function(){
			$scope.info = 'Dane mistrzostw zostały zaktualizowane';
			$scope.editChamp = null;
			$scope.show = true;
			$scope.getAllChamps();
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	
	$scope.deleteChamp = function(id){
		$http.delete('api/champ/delete/'+id).success(function(){
			$scope.info = 'Mistrzostwa zostały usunięte';
			$scope.editChamp = null;
			$scope.show = true;
			$scope.getAllChamps();
		})
	};
	
	
	
	$scope.race={id:"",championship:"",track:"",inOldboyRank:"",rw:"",date:""};
	$scope.editRace;
	
	$scope.createRace = function(){
		race=$scope.race;
		var rw = race.rw.toString();
		var repRw = rw.replace(",",".");
		race.rw = repRw;
		$http.post('api/race/create',race).success(function(){
			$scope.info = 'Wyścig został dodany';
			$scope.race = {id:"",championship:"",track:"",inOldboyRank:"",rw:"",date:""};
			$scope.getAllRaces();
			$scope.show = true;
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	$scope.getByRaceId = function(id){
		$http.get('api/race/get/byRaceId/'+id).success(function(data){
			$scope.editRace = data;
		})
	};
	
	$scope.getAllRaces = function(){
		$http.get('api/race/get/all').success(function(data){
			$scope.races = data;
		})
	};
	
	$scope.updateRace = function(){
		race = $scope.editRace;
		var rw = race.rw.toString();
		var repRw = rw.replace(",",".");
		race.rw = repRw;
		$http.put('api/race/update', race).success(function(){
			
			$scope.info = 'Dane wyścigu zostały zaktualizowane';
			$scope.editRace = null;
			$scope.show = true;
			$scope.getAllRaces();
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	
	$scope.deleteRace = function(id){
		$http.delete('api/race/delete/'+id).success(function(){
			$scope.info = 'Wyścig został usunięty';
			$scope.editRace = null;
			$scope.show = true;
			$scope.getAllRaces();
		})
	};
	
	
	
	
	$scope.track={name:""};
	$scope.editTrack;
	
	$scope.createTrack = function(){
		track=$scope.track;
		$http.post('api/track/create',track).success(function(){
			$scope.info = 'Tor został dodany';
			$scope.track = {name:""};
			$scope.getAllTracks();
			$scope.show = true;
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	$scope.getTrack = function(id){
		$http.get('api/track/get/'+id).success(function(data){
			$scope.editTrack = data;
		})
	};
	
	$scope.getAllTracks = function(){
		$http.get('api/track/get/all').success(function(data){
			$scope.tracks = data;
		})
	};
	
	$scope.updateTrack = function(){
		track = $scope.editTrack;
		$http.put('api/track/update', track).success(function(){
			$scope.info = 'Dane toru zostały zaktualizowane';
			$scope.editTrack = null;
			$scope.show = true;
			$scope.getAllTracks();
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	
	$scope.deleteTrack = function(id){
		$http.delete('api/track/delete/'+id).success(function(){
			$scope.info = 'Tor został usunięty';
			$scope.editTrack = null;
			$scope.show = true;
			$scope.getAllTracks();
		})
	};
	
	$scope.positions=[];
	$scope.editPositions;
	$scope.createPositions = function(){
		
		$scope.cleanPositions();
		$scope.deletePositionsByRaceId(editRace.id);
		$scope.show=true;
	}
	$scope.makePositions = function(){
		
		positions=$scope.positions;
		$http.post('api/position/create/many',positions).success(function(){
			$scope.positions=[]; 
			$scope.info = "Pozycje zostały ustalone";
			
		}).error(function(data){
			$scope.info = data.message;
			
		});
	};
	
	$scope.getPositionsByRaceId = function(id){
		
		$http.get('api/position/get/byRaceId/'+id).success(function(data){
			$scope.positions = data;
		})
	
	};
	$scope.cleanPositions = function(){
		editRace = $scope.editRace
		for(i = 0; i<$scope.positions.length;i++){
			if($scope.positions[i]==null||$scope.positions[i].driver==null){
			$scope.positions.splice(i,1);
			$scope.cleanPositions();
			}
			
	}
	};
	$scope.saveRaceInPosition = function(id){
		editRace = $scope.editRace
		$scope.positions[id].race=editRace;
	};
	
	$scope.saveRaceInPositions = function(){
		editRace = $scope.editRace
		for(i = 0; i<$scope.positions.length;i++){
			$scope.positions[i].race = editRace;
	}
	};
	
	$scope.deletePosition = function(position){
		
		if(position>-1)
			$scope.positions.splice(position,1);
	};
	
	
	
	$scope.deletePositionsByRaceId = function(id){
		$http.delete('api/position/delete/byRaceId/'+id).success(function(){
			
			setTimeout($scope.makePositions());
			
		})
	};
});