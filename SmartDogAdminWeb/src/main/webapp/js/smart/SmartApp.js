/**
 * Inicializa la aplicacion. Los demas datos son implementaciones angular
 * 
 * (ui.router es para manejar las opciones de la aplicacion web...)
 * (ui.bootstrap es para programar por javascript componentes bootstrap) (ngGrid
 * es para crear tablas "conocidas como grid") (angularFileUpload es para crear
 * componentes para subir archivos al servidor)
 */
angular
		.module(
				'smartApp',
				['ngWebsocket'])
		.config(
		         function ($websocketProvider) {
				    $websocketProvider.$setup({
				        lazy: false,
				        reconnect: false,
				        mock: false,
				        enqueue: false
				    });
				}
		)
		.constant('AUTH_EVENTS', {
			loginSuccess : 'auth-login-success',
			loginFailed : 'auth-login-failed',
			logoutSuccess : 'auth-logout-success',
			sessionTimeout : 'auth-session-timeout',
			notAuthenticated : 'auth-not-authenticated',
			notAuthorized : 'auth-not-authorized'
		})

		.constant('USER_ROLES', {
			all : '*',
			admin : 'adminsmart',
			editor : 'editorsmart',
			guest : 'guestsmart'
		})
		
		.directive('modalDialog', function($window, $templateCache, $compile, $http) {
		  return {
		    restrict: 'EA',
		    scope: {
		      show: '=',
		      model: '=',
		      callback: '&',
		      template: '@'
		    },
		    replace: true, // Replace with the template below
		    //transclude: true, // we want to insert custom content inside the directive
		    link: function(scope, element, attrs) {
		
		      $http.get(scope.template, {cache: $templateCache}).success(function(tplContent){
		                element.replaceWith($compile(tplContent)(scope));                
		              });
		      
		      scope.dialogStyle = {};
		      if (attrs.width) {
		        scope.dialogStyle.width = attrs.width + '%';
		        scope.dialogStyle.left = ( ( 100 - attrs.width ) / 2 ) + '%';
		      }
		      if (attrs.height) {
		        scope.dialogStyle.height = attrs.height + '%';
		        scope.dialogStyle.top = ( ( 100 - attrs.height ) / 2 ) + '%';
		      }else{
		    	  scope.dialogStyle.height = 'auto';
			      scope.dialogStyle.top = '1%';
		      }
		        
		      scope.hideModal = function() {
		        scope.show = false;
		      };
		
		      scope.clone = function(obj) {
		        if (obj === null || typeof obj !== 'object') {
		            return obj;
		        }
		        var temp = obj.constructor(); // give temp the original obj's constructor
		        for (var key in obj) {
		            temp[key] = scope.clone(obj[key]);
		        }
		        return temp;
		      };
		
		      var tempModel = scope.clone(scope.model);
		      
		      scope.finishModal = function() {
		    	  var response ={};
		        scope.callback(response);
		        scope.show = false;
		      };
		      
		      scope.cancel = function() {
		        scope.model = scope.clone(tempModel);
		        scope.show = false;
		      };
		    }
		    //template: "<div class='ng-modal' ng-show='show'><div class='ng-modal-overlay'></div><div class='ng-modal-dialog' ng-style='dialogStyle'><div class='ng-modal-close' ng-click='hideModal()'>X</div><div class='ng-modal-dialog-content' ng-transclude></div></div></div>"
		    //templateUrl: 'my-customer.html'
		    //templateUrl: scope.template
		  };
		})

		.directive('ngBlur', function() {
			return function(scope, elem, attrs) {
				elem.bind('blur', function() {
					scope.$apply(attrs.ngBlur);
				});
			};
		}).directive('ngFocus', function($timeout) {
			return function(scope, elem, attrs) {
				scope.$watch(attrs.ngFocus, function(newval) {
					if (newval) {
						$timeout(function() {
							elem[0].focus();
						}, 0, false);
					}
				});
			};
		})

		.directive('keyEnter', function() {
			return function(scope, element, attrs) {
				element.bind("keydown keypress", function(event) {
					if (event.which === 13) {
						scope.$apply(function() {
							scope.$eval(attrs.keyEnter);
						});

						event.preventDefault();
					}
				});
			};
		})

		.directive('autoFocus', function($timeout) {
			return {
				restrict : 'AC',
				link : function(_scope, _element) {
					$timeout(function() {
						_element[0].focus();
					}, 0);
				}
			};
		})
		
		.directive('loadFile', ['$parse', function ($parse) {
        return {
           restrict: 'A',
           link: function(scope, element, attrs) {
              var model = $parse(attrs.loadFile);
              var modelSetter = model.assign;
              element.bind('change', function(){
                 scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                 });
              });
           }
        };
        }])


		.value("rndAddToLatLon", function() {
			return Math.floor(((Math.random() < 0.5 ? -1 : 1) * 2) + 1)
		})

		.factory('StatusNewConstant', function(HTTP) {
			return estadoNoticiaVar;
		})
		
		.factory('messageFactory', function () {
	        var messageFac = {};
	        var divMessages  = document.querySelector('#messageBox');
	    	var iMessagesImage  = document.querySelector('#messageBoxImage');
	        
	    	var getComplemnent = function(msg){
	        	return (msg != null && msg != '' ? " - "+msg : '');
	        }
	    	
	    	var moveScrollTop = function(){
	    		window.scrollTo(0,0);
	    	}
	        
	        messageFac.showMessage = function(scope, title, description, system_description){
	        	
	        	scope.titulo_mensajes = title;
	        	scope.descripcion_mensajes  = description + getComplemnent(system_description);
    			
	        	if(title==="Exito"){
	    			divMessages.className = "alert alert-success fade in";
	    			iMessagesImage.className = "fa-fw fa fa-check";
	    		}
	    		
	    		if(title==="Informativo"){
	    			divMessages.className = "alert alert-info fade in";
	    			iMessagesImage.className = "fa-fw fa fa-info";
	    		}
	    		
	    		if(title==="Advertencia"){
	    			divMessages.className = "alert alert-warning fade in";
	    			iMessagesImage.className = "fa-fw fa fa-warning";
	    		}
	    		
	    		if(title==="Error"){
	    			divMessages.className = "alert alert-danger fade in";
	    			iMessagesImage.className = "fa-fw fa fa-times";
	    		}
	    		
	    		moveScrollTop();
	        };
	        return messageFac;
	    })

		.filter('mapStatusNew', function(StatusNewConstant) {
			return function(input) {
				if (StatusNewConstant[input]) {
					return StatusNewConstant[input];
				} else {
					return 'Desconocido';
				}
			};
		});


 ///
$(document).ready(function(){
	var defaults = {
			closeText: "Cerrar",
			currentText: "Hoy",
			monthNames: [ "Enero","Febrero","Marzo","Abril","Mayo","Junio",
			"Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" ],
			monthNamesShort: [ "Ene","Feb","Mar","Abr","May","Jun",
			"Jul","Ago","Sep","Oct","Nov","Dic" ],
			dayNames: [ "Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado" ],
			dayNamesShort: [ "Dom","Lun","Mar","Mié","Jue","Vie","Sáb" ],
			dayNamesMin: [ "D","L","M","X","J","V","S" ],
			dateFormat: "dd/mm/yy",
			changeMonth: true,
		    changeYear: true,
		    prevText : '<i class="fa fa-chevron-left"></i>',
		    nextText : '<i class="fa fa-chevron-right"></i>' 
		};

	$.datepicker.setDefaults(defaults);

});
