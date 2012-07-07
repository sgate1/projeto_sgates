;(function( window ){
  var Plano;
  
  // construtor
  Plano = window.Plano = function( obj ){
    this.id = obj==null ? null : obj.id;
    this.descricao = obj==null ? null : obj.descricao;
    this.desconto = obj==null ? null : obj.desconto;
    this.preco = obj==null ? null : obj.preco;
  }
  
  Plano.URL_SALVAR = Plano.URL_DELETAR = Plano.URL_EDITAR = Plano.URL_RECUPERAR_TUDO = ROOT + "/plano.json"; 
  
  Plano.fn = Plano.prototype;

  // funcoes
  Plano.fn.set = function( attribute, value ){
    if( !attribute || !value ) return this;

    if( this.hasOwnProperty( attribute ) ){
      this[attribute] = value;
    }
    
    return this;
  };

  Plano.fn.salvar = function( callback ){
    
    var instanceOfPlano = this;
    $.ajax({
      url: Plano.URL_SALVAR,
      type: "PUT",
      data: $.toJSON(instanceOfPlano),
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
  
  Plano.fn.editar = function( callback ){
    
    var instanceOfPlano = this;
    $.ajax({
      url: Plano.URL_EDITAR,
      type: "POST",
      data: $.toJSON(instanceOfPlano),
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
  
  Plano.fn.remove = function( callback ){
    
    var instanceOfPlano = this;
    $.ajax({
      url: Plano.URL_DELETAR,
      type: "DELETE",
      data: $.toJSON(instanceOfPlano),
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