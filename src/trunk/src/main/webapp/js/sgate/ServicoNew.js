;(function(window) {

  var ServicoNew;

  // construtor
  ServicoNew = window.ServicoNew = function( target_container ) {
    this.container = target_container;
    
    var formulario = this.formulario = new ServicoForm( 'Salvar', function(){
      var servico = formulario.getServico();
      servico.salvar( function(data){
        new Alert().show(data);
        var servicoView = new ServicoView();
        servicoView.show( target_container );
      });
    });
    
  };

  ServicoNew.fn = ServicoNew.prototype;

  ServicoNew.fn.show = function() {
    var target_container = this.container;
    
    $(target_container).html(""); // limpa conteudo
    
    var view = $('<div/>');
    
    var titulo_da_view = $( '<h2/>', {
      html : 'Inserir Novo Serviço'
    });
    titulo_da_view.appendTo( view );
    
    var quebra_de_linha = $( '<br/>' );
    quebra_de_linha.appendTo( view );
    
    var formulario = this.formulario.create();
    formulario.appendTo( view );
    
    view.appendTo( target_container );
  };
  
})(window);