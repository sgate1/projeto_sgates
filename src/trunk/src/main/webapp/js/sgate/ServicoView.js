;(function(window) {

  var ServicoView;

  // construtor
  ServicoView = window.ServicoView = function() {

  };

  ServicoView.fn = ServicoView.prototype;

  // funcoes publicas
  ServicoView.fn.show = function( target_container ) {

    $(target_container).html(""); // limpa conteudo
    
    var id_btn_inserir_servico = 'btn_inserir_servico';
    var id_do_container_da_view = 'servicoTableContainer'; 
    createView( id_btn_inserir_servico, id_do_container_da_view ).appendTo( target_container );
    new ServicoTable( ROOT + '/servico.json', id_do_container_da_view );
    
  };
  
  var createView = function( id_btn_inserir_servico, id_do_container_da_view ){
    var view = $( '<div/>' );
    
    var botao_salvar = $( '<button/>', {
      'class': 'btn btn-primary',
      id: id_btn_inserir_servico,
      html: $( '<i/>', { 'class': 'icon-file icon-white' }),
      click: function(){
        new ServicoNew().show( "#content" );
      }
    })
    botao_salvar.appendTo( view );
    botao_salvar.html( botao_salvar.html() + ' ' + 'Inserir Novo Serviço' );
    
    var quebra_de_linha = $( '<br/>' );
    quebra_de_linha.appendTo( view );
    
    var titulo_da_view = $( '<h2/>', {
      html : 'Serviços Cadastrados'
    } );
    titulo_da_view.appendTo( view );
    
    var container_da_view = $( '<div/>', {
      id : id_do_container_da_view
    } );
    container_da_view.appendTo( view );
    
    return view;
  };
  
})(window);