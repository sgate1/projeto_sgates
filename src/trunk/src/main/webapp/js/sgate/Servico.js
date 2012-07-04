;(function( window ){
  
  var url_salvar = ROOT + "/servico.json"; 
  var url_delete = ROOT + "/servico.json"; 
  var url_editar = ROOT + "/servico.json"; 
  var Servico;

  // construtor
  Servico = window.Servico = function( obj ){
    this.id = obj==null ? null : obj.id;
    this.titulo = obj==null ? null : obj.titulo;
    this.descricao = obj==null ? null : obj.descricao;
    this.preco = obj==null ? null : obj.preco;
  }

  Servico.fn = Servico.prototype;

  // funcoes
  Servico.fn.set = function( attribute, value ){
    if( !attribute || !value ) return this;

    if( this.hasOwnProperty( attribute ) ){
      this[attribute] = value;
    }
    
    return this;
  };

  Servico.fn.salvar = function( callback ){
    
    var instanceOfServico = this;
    $.ajax({
      url: url_salvar,
      type: "PUT",
      data: $.toJSON(instanceOfServico),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function(data){
        callback( data );
      },
      complete: function(){
        
      },
      error: function(error){
        new Alert().error(error);
      }
    });
    
  };
  
  Servico.fn.editar = function( callback ){
    
    var instanceOfServico = this;
    $.ajax({
      url: url_editar,
      type: "POST",
      data: $.toJSON(instanceOfServico),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function(data){
        callback( data );
      },
      complete: function(){
        
      },
      error: function(error){
        new Alert().error(error);
      }
    });
    
  };
  
  Servico.fn.remove = function( callback ){
    
    var instanceOfServico = this;
    $.ajax({
      url: url_delete,
      type: "DELETE",
      data: $.toJSON(instanceOfServico),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function(data){
        callback(data);
      },
      complete: function(){
        
      },
      error: function(error){
        new Alert().error(error);
      }
    });
    
  };

})( window );