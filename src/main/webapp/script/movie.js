
	var mainApp = angular.module("movieApp", ['ngRoute']);
    
    mainApp.config(['$routeProvider',
       function($routeProvider) {
          $routeProvider.
             when('/addMovie', {
                templateUrl: 'template/addmovie.html',
                controller: 'AddMovieController'
             }).
             when('/addShow', {
                templateUrl: 'template/addshow.html',
                controller: 'AddShowController'
             }).
             when('/listMovie', {
                 templateUrl: 'template/listmovie.html',
                 controller: 'ListMovieController'
              }).
	         when('/update', {
	              templateUrl: 'template/updatemovie.html',
	              controller: 'UpdateMovieController'
	         }). 
             otherwise({
            	templateUrl: 'template/default.html',
                controller: 'DefaultMovieController'
             });
    }]);

    mainApp.controller('AddMovieController', ['$rootScope', '$location', '$scope', '$http',function($rootScope, $location, $scope, $http) 
    {
    	$scope.movieName = "";
		$scope.duration = "";
		
		$scope.message = " " ;
		
		$scope.reset = function() 
		{
			$scope.movieName = "";
			$scope.duration = "";
		}
		
        $scope.submit = function() 
		{
			var dataObj = 
			{
				movieName: $scope.movieName,
				duration:  $scope.duration
			};
			
			var res = $http.post('/SpringDataJPA/rest/api/movie', dataObj);
			res.success(function(data, status, headers, config)
			{
				$rootScope.message = "Movie details has been successfully saved, movie id - " + data.movieId;
				$scope.reset();
				$location.path( "/index" );
			});
			
			res.error(function(data, status, headers, config)
			{
				$rootScope.message = "Failed to save movie details. Please try after some time." + status;
				$scope.reset();
				$location.path( "/index" );
			});	
		}
		
	}]);
    
    
    mainApp.controller('AddShowController', ['$rootScope','$location', '$scope', '$http',function($rootScope, $location, $scope, $http) 
    {
        $scope.message = " " ;
        
        
    }]);
	
    mainApp.controller('ListMovieController', ['$rootScope','$location', '$scope', '$http', function($rootScope,$location, $scope, $http) 
    {
        $scope.message = "List if Movies";
        $http.get('/SpringDataJPA/rest/api/movie').
        success(function(data) {
            $scope.movies = data;
        });
        
        $scope.selectedMovie = {
        		movieId:0
        }
        
        $scope.update = function(path) 
		{
        	$scope.message = "Selected Movie - " + $scope.selectedMovie.movieId ;
        	$rootScope.selMovieId = $scope.selectedMovie.movieId;
			$location.path( path );
		}
    }]);
    
    mainApp.controller('UpdateMovieController', ['$rootScope','$location', '$scope', '$http', function($rootScope,$location, $scope, $http) 
    { 
       $scope.message = "Selected Movie Details " + $rootScope.selMovieId;
       $http.get('/SpringDataJPA/rest/api/movie/' + $rootScope.selMovieId).
       success(function(data) 
       {
           $scope.movie = data;
           $scope.movieId = $scope.movie.movieId;
           $scope.movieName = $scope.movie.movieName;
           $scope.duration = $scope.movie.duration;
           $scope.version = $scope.movie.version;
           
       });
       
       $scope.submit = function() 
	   {
			var dataObj = 
			{
				movieId : $scope.movieId,
				movieName: $scope.movieName,
				duration:  $scope.duration,
				version: $scope.version
			};
			
			var res = $http.put('/SpringDataJPA/rest/api/movie', dataObj);
			res.success(function(data, status, headers, config)
			{
				$rootScope.message = "Movie details has been successfully updated, movie id - " + data.movieId;
				$location.path( "/index" );
			});
			
			res.error(function(data, status, headers, config)
			{
				$rootScope.message = "Failed to save movie details. Please try after some time." + status;
				$location.path( "/index" );
			});	
		}
       
       
    }]);
    
	
	mainApp.controller('DefaultMovieController', ['$rootScope','$location', '$scope', '$http',function($rootScope,$location, $scope, $http) 
	{
        if ($rootScope.message != null)
        {
        	$scope.message = $rootScope.message;
        }
        else
    	{
        	$scope.message = "Welcome to Movie Application !";
    	}
		
    }]);
