;(function(window) {
  
  var Table;

  /*
   * construtor recebe como parâmetro
   * parameters = {
   *   headers:{},
   *   actions:{},
   *   url_da_listagem_dos_items: 'String',
   *   id_target_container: 'String'
   * }
   */
  Table = window.Table = function( parameters ) {
    this.parameters = parameters;  
    
    var instanceTable = this;
    $.ajax({
      url: parameters.url_da_listagem_dos_items,
      type: "GET",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function(data){
        instanceTable.items_da_tabela = data.returnObject;
        var target = $( '#' + parameters.id_target_container );
        instanceTable.create().appendTo( target );
      },
      complete: function(){
      },
      error: function(error){
        instanceTable.items_da_tabela = [];
        new Alert().error(error);
      }
    });
    
  }

  Table.fn = Table.prototype;

  // funcoes publicas
  Table.fn.create = function() {
    var table = createTable();
    var header = new Header( table, this.parameters.headers );
    header.create();
    var body = new Body( table, this.parameters.headers, this.parameters.actions, this.items_da_tabela );
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
  
  var Body = function( table, headers, Actions, items_da_tabela ){
    
    this.create = function(){
      $.each( items_da_tabela, function(index, item) {
        createLine( item ).appendTo( table ); 
      });
    }
    
    var createLine = function( item ){
      var line = $('<tr/>').attr({  });
      $.each( headers, function(index, cell_header) {
        
        if( cell_header.id == 'acoes' ){
          createAction( item ).appendTo( line );
        }else{
          createCell( item[cell_header.id] ).appendTo( line );
        }
          
      });
      
      return line;
    }
    
    var createCell = function( label ){
      return $('<td/>', { 
        html: label!=null?label:'' 
      });
    };
    
    var createAction = function( item ){
      var cell = $('<td/>');
      $.each( Actions, function(index, actionType) {
        actionType.create( item ).appendTo(cell);
      });
      return cell;
    };
    
  };
  
})(window);