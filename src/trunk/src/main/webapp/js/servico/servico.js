;(function(window) {

	var Servico;
	
	// construtor
	Servico = window.Servico = function() {
		
		this.id = null;
		this.titulo = null;
		this.descricao = null;
		this.preco = null;

	}
	
	Servico.fn = Servico.prototype;
	
	// funcoes
	Servico.fn.set = function( attribute, value ) {
	
		if (!attribute || !value)
			return;

		if (this.hasOwnProperty(attribute)) {
			this[attribute] = value;
		}
	};
	
	Servico.fn.salvar = function() {
		
		$.ajax( {
			
			type: "POST",
			url: ROOT + "/servico/salvar.json",
			data: this,
			
			complete: function() {
				alert("Complete");
			},
			
			success: function(response) {
				alert("Sucesso!");
			},
			
			error: function(response) {
				alert("Erro!");
			}
			
		} );
	}

} )(window);