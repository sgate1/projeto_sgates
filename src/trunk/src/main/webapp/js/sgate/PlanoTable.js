;(function(window) {
  var headers = [ {id:"descricao", label:"Descrição"},
                  {id:"desconto", label:"Desconto"},
                  {id:"preco", label:"Preço" },
                  {id:"acoes", label:"Ações"}
                ];
  var Actions = { View:{ label:"Visualizar" }, Edit:{ label:"Editar" }, Remove:{ label:"Excluir" } };
  var PlanoTable;
  
  //construtor
  PlanoTable = window.PlanoTable = function( url_da_listagem_dos_items, id_target_container ) {
    var parameters = {
        'headers': headers,
        'actions': Actions,
        'url_da_listagem_dos_items': url_da_listagem_dos_items,
        'id_target_container': id_target_container 
    };  
    
    this.table = new Table( parameters );
  };
  
  Actions.View.createModal = function( obj ){
    var modal = $('#modalVisualizacaoEntidades');
    var body = modal.find('.modal-body');
    body.html('');
    
    $('<h2/>', {
      'html': obj.descricao
    }).appendTo(body);
    
    $('<h3/>', {
      'html': 'Desconto de R$ ' + obj.desconto
    }).appendTo(body);
    
    $('<h2/>', {
      'html': 'Preço: R$ ' + obj.preco
    }).appendTo(body);
    
  };
  
  Actions.View.create = function( obj ){
    
    var action = $('<button/>', { 
      'class': "btn btn-primary",
      'style': 'margin-left: 5px',
      'data-toggle' : 'modal',
      'href' : '#modalVisualizacaoEntidades',
      click: function(){
        Actions.View.createModal( obj ); 
      }
    });
    
    var icon = $( '<i/>', { 'class': 'icon-edit icon-white' });
    icon.appendTo( action );
    
    action.html( action.html() + '  ' + this.label );
    
    return action;
  };
  
  Actions.Edit.create = function( obj ){
    var action = $('<button/>', { 
      'class': "btn btn-success",
      'style': 'margin-left: 5px',
      click: function(){
        new Alert().alert('Ainda não implementado!');
//        new ServicoUpdate( "#content", obj ).show();
      }
    });
    
    var icon = $( '<i/>', { 'class': 'icon-edit icon-white' });
    icon.appendTo( action );
    
    action.html( action.html() + '  ' + this.label );
    
    return action;
  };
  
  Actions.Remove.create = function( obj ){
    var action = $('<button/>', { 
      'class': "btn btn-danger",
      'style': 'margin-left: 5px',
      click: function(){
        var plano = new Plano( obj );
        plano.remove( function(data){
          new Alert().show(data);
          new PlanoView( "#content" ).show();
        });
      }
    });
    
    var icon = $( '<i/>', { 'class': 'icon-trash icon-white' });
    icon.appendTo( action );
    
    action.html( action.html() + '  ' + this.label );
    
    return action;
  };
  
})(window);