ROOT = 'http://localhost:8080/Sgate';


jQuery(document).ready(function() {
	
	// a��o do menu 'Servi�os'
	$('#menu_servicos').click( function() {
	
		var servicoView = new ServicoView();
		servicoView.listView();
		
	} );
	
	
});