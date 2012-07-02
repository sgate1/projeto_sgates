;(function( window ){
  
  // construtor
  Alert = window.Alert = function( target_container ){
    this.target = target_container!=null ? target_container : '#alert_container' ;
    Alert.map = Alert.map!=null ? Alert.map : { SUCCESS:this.success, ERROR:this.error, INFO: this.info, ALERT:this.alert };
  }

  Alert.fn = Alert.prototype;

  Alert.fn.info = function( text, target ){
    target = target==null ? this.target : target;
    $(target).html(""); // limpa conteudo
    var alert = Alert.create( 'INFO', text, target );
    alert.attr( 'class', 'alert alert-info' );
    alert.appendTo( target );
  };
  
  Alert.fn.success = function( text, target ){
    target = target==null ? this.target : target;
    $(target).html(""); // limpa conteudo
    var alert = Alert.create( 'Sucesso', text, target );
    alert.attr( 'class', 'alert alert-success' );
    alert.appendTo( target );
  };
  
  Alert.fn.error = function( text, target ){
    target = target==null ? this.target : target;
    $(target).html(""); // limpa conteudo
    var alert = Alert.create( 'ERROR', text, target );
    alert.attr( 'class', 'alert alert-error' );
    alert.appendTo( target );
  };
  
  Alert.fn.alert = function( text, target ){
    target = target==null ? this.target : target;
    $(target).html(""); // limpa conteudo
    var alert = Alert.create( 'Alerta', text, target );
    alert.appendTo( target );
  };
  
  Alert.fn.show = function( data ){
    var alerta = Alert.map[ data!=null ? data.status : null ];
    if( alerta )
      alerta( data.message, this.target );
    else
      this.error( 'Ocorreu algum erro tente mais tarde' );
  };
  
  Alert.create = function( label, text, target ){
    var alert = $('<div/>', {
      'class': 'alert'
    });
    
    var button = $('<button/>', {
      'type': 'button',
      'class': 'close',
      'data-dismiss': 'alert',
      html: 'x'
    });
    button.appendTo( alert );
    
    var strong = $('<strong/>', {
      html: label
    });
    strong.appendTo( alert );
    
    alert.html( alert.html() + ' ' + text );
    
    Alert.timeout( target );
    return alert;
  };
  
  Alert.timeout = function( target ){
    setTimeout( "$('"+target+"').html('')", 4000 );
  }

})( window );