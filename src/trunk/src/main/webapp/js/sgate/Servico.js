;(function( window ){
  var Servico;
  
  // construtor
  Servico = window.Servico = function( obj ){
    this.id = obj==null ? null : obj.id;
    this.titulo = obj==null ? null : obj.titulo;
    this.descricao = obj==null ? null : obj.descricao;
    this.preco = obj==null ? null : obj.preco;
  }
  
  Servico.URL_SALVAR = Servico.URL_DELETAR = Servico.URL_EDITAR = Servico.URL_RECUPERAR_TUDO = ROOT + "/servico.json"; 
  
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
      url: Servico.URL_SALVAR,
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
      url: Servico.URL_EDITAR,
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
      url: Servico.URL_DELETAR,
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