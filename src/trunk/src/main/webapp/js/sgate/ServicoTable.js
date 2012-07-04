;(function(window) {
  var headers = [ {id:"titulo", label:"Título"},
                  {id:"descricao", label:"Descrição"},
                  {id:"preco", label:"Preço" },
                  {id:"acoes", label:"Ações"}
                ];
  var Actions = { Edit:{ label:"Editar" }, Remove:{ label:"Excluir" } };
  var ServicoTable;
  
  ServicoTable = window.ServicoTable = function( url_da_listagem_dos_items, id_target_container ) {
    var parameters = {
        'headers': headers,
        'actions': Actions,
        'url_da_listagem_dos_items': url_da_listagem_dos_items,
        'id_target_container': id_target_container 
    };  
    
    this.table = new Table( parameters );
  };
  
  Actions.Edit.create = function( obj ){
    var action = $('<button/>', { 
      'class': "btn btn-success",
      'style': 'margin-left: 5px',
      click: function(){
        new ServicoUpdate( "#content", obj ).show();
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
        var servico = new Servico( obj );
        servico.remove( function(data){
          new Alert().show(data);
          var servicoView = new ServicoView( "#content" );
          servicoView.show();
        });
      }
    });
    
    var icon = $( '<i/>', { 'class': 'icon-trash icon-white' });
    icon.appendTo( action );
    
    action.html( action.html() + '  ' + this.label );
    
    return action;
  };
  
})(window);