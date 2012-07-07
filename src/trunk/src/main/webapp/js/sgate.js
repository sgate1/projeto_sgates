ROOT = 'http://localhost:8080/Sgate';

jQuery(document).ready(function() {
  
  var container = '#content';
  var menu_servicos = $('#menu_servicos');
  var menu_planos = $('#menu_planos');
  
  var limpar_selecao_de_menu = function(){
    $('.container-fluid').find('li').removeClass();
  };
  
  var selecionar_menu = function(item_menu){
    item_menu.parent().attr('class', 'active');
  };

  menu_servicos.click(function() {
    limpar_selecao_de_menu();
    var servicoView = new ServicoView( container );
    selecionar_menu( menu_servicos );
    servicoView.show();

  });
  
  menu_planos.click(function() {
    limpar_selecao_de_menu();
    
    var planoView = new PlanoView( container );
    selecionar_menu( menu_planos );
    planoView.show();
    
  });

});