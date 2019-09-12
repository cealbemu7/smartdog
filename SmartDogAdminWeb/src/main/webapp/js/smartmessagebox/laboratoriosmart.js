		$(document).ready(function() {
			$("#smart-mod-eg1").click(function(e) {
				$.SmartMessageBox({
					title : "Confirmación!",
					content : "Esta seguro que desea eliminar el registro seleccionado...",
					buttons : '[No][Si]'
				}, function(ButtonPressed) {
					if (ButtonPressed === "Si") {
		
						$.smallBox({
							title : "Alerta!",
							content : "<i class='fa fa-clock-o'></i> <i>Usted ha confirmado la eliminación...</i>",
							color : "#659265",
							iconSmall : "fa fa-check fa-2x fadeInRight animated",
							timeout : 4000
						});
					}
					if (ButtonPressed === "No") {
						$.smallBox({
							title : "Alerta!",
							content : "<i class='fa fa-clock-o'></i> <i>Usted ha cancelado la eliminación...</i>",
							color : "#C46A69",
							iconSmall : "fa fa-times fa-2x fadeInRight animated",
							timeout : 4000
						});
					}
		
				});
				e.preventDefault();
			})	
		
		})
