ROOT = 'http://localhost:8080/Sgate';

jQuery(document).ready(function() {

  // ação do menu 'Serviços'
  $('#menu_servicos').click(function() {

    var servicoView = new ServicoView();
    servicoView.show( "#content" );

  });

});