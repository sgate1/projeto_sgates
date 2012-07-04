;(function(window) {

  var ServicoUpdate;

  // construtor
  ServicoUpdate = window.ServicoUpdate = function( target_container, obj_servico ) {
    this.container = target_container;
    this.servico = obj_servico;
    
    var formulario = this.formulario = new ServicoForm( 'Editar', function(){
      var servico = formulario.getServico();
      servico.editar( function(data){
        new Alert().show(data);
        var servicoView = new ServicoView( target_container );
        servicoView.show();
      });
    });
    
  };

  ServicoUpdate.fn = ServicoUpdate.prototype;

  ServicoUpdate.fn.show = function() {
    var target_container = this.container;
    
    $(target_container).html(""); // limpa conteudo
    
    var view = $('<div/>');
    
    var titulo_da_view = $( '<h2/>', {
      html : 'Editar Serviço'
    });
    titulo_da_view.appendTo( view );
    
    var quebra_de_linha = $( '<br/>' );
    quebra_de_linha.appendTo( view );
    
    var formulario = this.formulario.create( this.servico );
    formulario.appendTo( view );
    
    view.appendTo( target_container );
  };
  
})(window);