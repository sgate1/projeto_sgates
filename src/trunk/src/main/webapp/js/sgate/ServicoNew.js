;(function(window) {

  var ServicoNew;

  // construtor
  ServicoNew = window.ServicoNew = function() {

  };

  ServicoNew.fn = ServicoNew.prototype;

  ServicoNew.fn.show = function( target_container ) {
    $(target_container).html(""); // limpa conteudo
    
    var view = $('<div/>');
    
    var titulo_da_view = $( '<h2/>', {
      html : 'Inserir Novo Serviço'
    });
    titulo_da_view.appendTo( view );
    
    var quebra_de_linha = $( '<br/>' );
    quebra_de_linha.appendTo( view );
    
    var formulario = createFormulario();
    formulario.appendTo( view );
    
    view.appendTo( target_container );
  };
  
  var getServico = function( formulario ) {
    var servico = new Servico();

    $(formulario).find('input,select,textarea').each(function( index, field ) {
      servico.set( $(field).attr('id'), $(field).val() );
    });
    
    return servico;
  };
  
  var createFormulario = function(){
    var formulario = $('<form/>', {
      'class': 'form-horizontal' 
    });
    
    var fieldset = $('<fieldset/>');
    fieldset.appendTo( formulario );
    
    createFieldFormulario( 'titulo', 'Título' ).appendTo( fieldset );
    createFieldFormulario( 'descricao', 'Descrição' ).appendTo( fieldset );
    createFieldFormulario( 'preco', 'Preço' ).appendTo( fieldset );
    
    var container_botao = $( '<div/>', {
      'class': 'form-actions',
      html: $('<button/>', {
        type: 'submit',
        'class': 'btn btn-primary',
        html: 'Salvar',
        click: function(){
          var servico = getServico( formulario );
          servico.salvar( function(data){
            new Alert().show(data);
            var servicoView = new ServicoView();
            servicoView.show( "#content" );
          });
        }
      })
    });
    container_botao.appendTo( fieldset );
    
    return formulario;
  };
  
  var createFieldFormulario = function( idField, labelField ){
    var field = $( '<div/>', {
      'class': 'control-group'
    });
    
    var label = $( '<label/>', {
      'class': 'control-label',
      'for': idField,
      html: labelField
    });
    label.appendTo( field );
    
    var input = $( '<div/>', {
      'class': 'controls',
      'for': idField,
      html: $('<input/>', {
        'class': 'input-xlarge focused',
        id: idField,
        type: 'text',
        value: 'Digite algo...'
      })
    });
    input.appendTo( field );
    
    return field;
  };
  
})(window);