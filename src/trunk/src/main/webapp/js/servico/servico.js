;(function( window ){

  var Servico;

  // construtor
  Servico = window.Servico = function(){
    this.id = null;
    this.titulo = null;
    this.descricao = null;
    this.preco = null;
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

  Servico.fn.salvar = function(){
    
    $.ajax({
      url: ROOT + "/servico.json",
      type: "POST",
      data: $.toJSON(this),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function(data){
        alert(data.message);
      },
      complete: function(){
        alert( "Complete" );
      },
      error: function(error){
        alert( "Erro!" );
      }
    });
    
  }

})( window );