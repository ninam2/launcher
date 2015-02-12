angular.module('launcherApplication', ['ngResource'])
    .controller('launcherWebController', function($scope, $resource){

        var resource = $resource("http://localhost:8080/nina/launcher",{},
        {'save':   {method:'POST'}});


{$scope.ctl = { ctl: 'd'};}


        $scope.executeCommand = function() {
            console.info('executeCommand()');
            resource.save($scope.ctl, function(data){
                console.info('success');
                //$scope.executeCommand();
            });
        }
    });