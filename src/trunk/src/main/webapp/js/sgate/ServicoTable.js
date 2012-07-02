;(function(window) {
  var headers = [ {id:"titulo", label:"Título"},
                  {id:"descricao", label:"Descrição"},
                  {id:"preco", label:"Preço" },
                  {id:"acoes", label:"Ações"}
                ];
  var Actions = { Edit:{ label:"Editar" }, Remove:{ label:"Excluir" } };
  var ServicoTable;

  // construtor
  ServicoTable = window.ServicoTable = function( url_da_listagem_dos_servicos, id_target ) {
    
    var instanceServicoTable = this;
    $.ajax({
      url: url_da_listagem_dos_servicos,
      type: "GET",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function(data){
        instanceServicoTable.servicos = data.returnObject;
        var target = $( '#' + id_target );
        instanceServicoTable.create().appendTo( target );
      },
      complete: function(){
      },
      error: function(error){
        instanceServicoTable.servicos = [];
      }
    });
    
  }

  ServicoTable.fn = ServicoTable.prototype;

  // funcoes publicas
  ServicoTable.fn.create = function() {
    var table = createTable();
    var header = new Header( table, headers );
    header.create();
    var body = new Body( table, headers, Actions, this.servicos );
    body.create();
    
    return table;
  };
  
  var createTable = function(){
    var table = $('<table/>').attr({
      'class': 'table table-bordered' 
    });
    
    return table;
  };
  
  var Header = function( table, headers ){
    
    this.create = function(){
      var headerTable = $('<tr/>').attr({  });
      
      $.each( headers, function(index, cell_header) {
        createCell( cell_header ).appendTo( headerTable ); 
      });
      
      headerTable.appendTo(table);
    };
    
    var createCell = function( cell_header ){
      return $('<th/>', { 
        html: cell_header.label 
      });
    };
    
  };
  
  var Body = function( table, headers, Actions, servicos ){
    
    this.create = function(){
      $.each( servicos, function(index, servico) {
        createLine( servico ).appendTo( table ); 
      });
    }
    
    var createLine = function( servico ){
      var line = $('<tr/>').attr({  });
      $.each( headers, function(index, cell_header) {
        
        if( cell_header.id == 'acoes' ){
          createAction( servico ).appendTo( line );
        }else{
          createCell( servico[cell_header.id] ).appendTo( line );
        }
          
      });
      
      return line;
    }
    
    var createCell = function( label ){
      return $('<td/>', { 
        html: label!=null?label:'' 
      });
    };
    
    var createAction = function( servico ){
      var cell = $('<td/>');
      $.each( Actions, function(index, actionType) {
        actionType.create( servico ).appendTo(cell);
      });
      return cell;
    };
    
  };
  
  Actions.Edit.create = function( obj ){
    var action = $('<button/>', { 
      'class': "btn btn-success",
      'style': 'margin-left: 5px',
      click: function(){
        
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
          var servicoView = new ServicoView();
          servicoView.show( "#content" );
        });
      }
    });
    
    var icon = $( '<i/>', { 'class': 'icon-trash icon-white' });
    icon.appendTo( action );
    
    action.html( action.html() + '  ' + this.label );
    
    return action;
  };
  
})(window);