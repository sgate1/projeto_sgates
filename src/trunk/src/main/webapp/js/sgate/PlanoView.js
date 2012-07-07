;(function(window) {

  var PlanoView;

  // construtor
  PlanoView = window.PlanoView = function( target_container ) {
    this.target = target_container;
  };

  PlanoView.fn = PlanoView.prototype;

  // funcoes publicas
  PlanoView.fn.show = function() {
    var target_container = this.target;
    $(target_container).html(""); // limpa conteudo
    
    var id_btn_inserir_plano = 'btn_inserir_plano';
    var id_do_container_da_view = 'planoTableContainer'; 
    createView( id_btn_inserir_plano, id_do_container_da_view ).appendTo( target_container );
    new PlanoTable( Plano.URL_RECUPERAR_TUDO, id_do_container_da_view );
    
  };
  
  var createView = function( id_btn_inserir_plano, id_do_container_da_view ){
    var view = $( '<div/>' );
    
    var botao_salvar = $( '<button/>', {
      'class': 'btn btn-primary',
      id: id_btn_inserir_plano,
      html: $( '<i/>', { 'class': 'icon-file icon-white' }),
      click: function(){
        //new PlanoNew( "#content" ).show();
        new Alert().alert('Ainda não implementado!');
      }
    })
    botao_salvar.appendTo( view );
    botao_salvar.html( botao_salvar.html() + ' ' + 'Inserir Novo Plano' );
    
    var quebra_de_linha = $( '<br/>' );
    quebra_de_linha.appendTo( view );
    
    var titulo_da_view = $( '<h2/>', {
      html : 'Planos Cadastrados'
    } );
    titulo_da_view.appendTo( view );
    
    var container_da_view = $( '<div/>', {
      id : id_do_container_da_view
    } );
    container_da_view.appendTo( view );
    
    return view;
  };
  
})(window);