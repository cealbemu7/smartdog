/**
 * Archivo de servicios principales para la aplicacion
 */
angular.module('smartApp')
		.config(
				function($httpProvider) {

					var spinnerReqFunction = function(data, headersGetter) {
						if ($('body #loading-backdrop').length <= 0) {
							$('body')
									.append(
											'<div id="loading-backdrop">'
													+ '  <div id="loading">'
													+ '    Cargando <br><img src="'+getContextPath()+'/img/cargando.gif" width="50px" />'
													+ '  </div>' + '</div>');
						}
						return data;
					};

					var spinnerResFunction = function(data, headersGetter) {
						if ($('body #loading-backdrop').length) {
							$('body #loading-backdrop').remove();
						}
						return data;
					};

					$httpProvider.defaults.transformRequest.push(spinnerReqFunction);
					$httpProvider.defaults.transformResponse.push(spinnerResFunction);
				})
		.service('smartServices',
				function($http,$websocket,$window) {
					$http.defaults.useXDomain = true;
					var headers = {
						'Access-Control-Allow-Origin' : '*',
						'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS, PUT',
						'Access-Control-Allow-Headers' : 'Content-Type, Accept, X-Requested-With',
						'Content-Type' : 'application/json',
						'Accept' : 'application/json',
						'secureToken' : ''
					};
					
					var headersDownload = {
							'Access-Control-Allow-Origin' : '*',
							'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS, PUT',
							'Access-Control-Allow-Headers' : 'Content-Type, Accept, X-Requested-With',
							'Content-Type' : 'application/octet-stream',
							'Accept' : 'application/octet-stream'
						};
					
					var headersUpload ={
							'Access-Control-Allow-Origin' : '*',
							'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS, PUT',
							'Access-Control-Allow-Headers' : 'Content-Type, Accept, X-Requested-With',
							'Content-Type': undefined,
							'secureToken' : ''
					}
					

					this.sendGet = function(urlEndPoint, success, error) {
						if (getUserSession() != null){
							headers.secureToken = getUserSession().secureToken;
						}
							$http({
								url : urlEndPoint,
								method : 'GET',
								headers : headers
							}).then(function(response) {
								if(HTTPValidateResponse(response)){
									  success(response);			
								}
							}, function(response) {
								if ($('body #loading-backdrop').length) {
									$('body #loading-backdrop').remove();
								}
								error(response);
							});
					}
					
			        this.sendUpload = function(json, urlEndPoint, success, error){
			        	
			        	if (getUserSession() != null){
			        		headersUpload.secureToken = getUserSession().secureToken;
						}
			            var formData = new FormData();
			            formData.append('file', json.file);
			            $http.post(urlEndPoint, formData, {
			               transformRequest: angular.identity,
			               headers: headersUpload
			            })
			            .success(function(response){
			            	if(HTTPValidateResponse(response)){
								  success(response);			
							}
			            })
			            .error(function(response){
			            	if ($('body #loading-backdrop').length) {
								$('body #loading-backdrop').remove();
							}
							error(response);
			            });
			         }
					
					this.sendDownload = function(urlEndPoint, success, error) {
						try{
							var request =  new XMLHttpRequest();
							request.open("GET",urlEndPoint);
							
							request.setRequestHeader('Access-Control-Allow-Origin' , '*');
							request.setRequestHeader('Access-Control-Allow-Methods' , 'POST, GET, OPTIONS, PUT');
							request.setRequestHeader('Access-Control-Allow-Headers' , 'Content-Type, Accept, X-Requested-With');
							request.setRequestHeader("secureToken",getUserSession().secureToken);
							request.responseType = 'blob';
							request.onload = function(e) { 
							   if (request.status >= 200 && request.status < 400) {
								    var blob = new Blob([this.response], { type: type });
								    var type = this.getResponseHeader('Content-Type');
								    var contentDispo = this.getResponseHeader('Content-Disposition');
								    var fileName = contentDispo.match(/filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/)[1];
								    var a = document.createElement('a');
								    a.href = window.URL.createObjectURL(blob);
								    a.download = fileName;
								    a.dispatchEvent(new MouseEvent('click'));
								    success();
								}else{
									error();
								}
							};
						    request.send();
								
						}catch(error){
							error();
						}
					}

					this.sendPost = function(json, urlEndPoint, success, error) {
						if (getUserSession() != null){
							headers.secureToken = getUserSession().secureToken;
						}
							$http({
								url : urlEndPoint,
								method : 'POST',
								data : json,
								headers : headers
							}).then(function(response) {
								if(HTTPValidateResponse(response)){
									  success(response);
							    }
							}, function(response) {
								if ($('body #loading-backdrop').length) {
									$('body #loading-backdrop').remove();
								}
								error(response);
							});
					}

					this.sendPut = function(json, urlEndPoint, success, error) {
						if (getUserSession() != null){
							headers.secureToken = getUserSession().secureToken;
						}
							$http({
								url : urlEndPoint,
								method : 'PUT',
								headers : headers,
								data : json
							}).then(function(response) {
								if(HTTPValidateResponse(response)){
								  success(response);
								}
							}, function(response) {
								if ($('body #loading-backdrop').length) {
									$('body #loading-backdrop').remove();
								}
								error(response);
							});
					}
					
					this.sendPrint = function (data,success,error) {
						var sendData = prototypePrint;
						sendData.data = utf8_to_b64(data.data);
						sendData.url = utf8_to_b64 (data.url);
						sendData.isJasper = data.isJasper;
						var ws = $websocket.$new('ws://'+appProperties.printServer+':'+appProperties.printPort)
				          .$on('$open', function () {
				        	  prototypePrint.data= utf8_to_b64("connect");
				            ws.$emit('connect', prototypePrint);
				            ws.$emit('print', sendData);
				          }).$on('$message', function (data) {
				        	  if(data != 'print' && data.event != "connect"){
					        	  if(data.code == 200){
						            	success(data);
						          }else{
						            	alert("Error imprimiendo: \n"+data.data);
						            	error(data);
							      }
				        	  }
				        	  
				          })
				          .$on('print', function (message) {
				        	  console.log('RESPONSE: '+message);
				        	  ws.$close();
				          })
				          .$on('$error', function () {
				            alert('Error tratando de invocar el servicio de impresion,\nPor favor verificar que el servicio de impresion se este ejecutando ');
				            ws.$close();
				          })
				          .$on('$close', function () {
				            console.log('socket desconectado!');
				            $window.location.reload();
				          });
						
					}

					this.validateInfo = function() {
						var data = arguments[0];
						var isValidData = arguments[1];
						var whenIsTrue = arguments[2];
						var whenisFalse = (arguments.length > 2) ? arguments[3]
								: null;

						if (isValidData(data)) {
							whenIsTrue(angular.copy(data));
						} else if (whenisFalse != null) {
							whenisFalse(angular.copy(data));
						}
					};

					this.focus = function() {
						var idField = arguments[0];
						var domField = $('#' + idField);
						if (domField != null) {
							domField.focus();
						}
					};
					
				});
