;(function(window) {

  var ServicoView;

  // construtor
  ServicoView = window.ServicoView = function() {

  }

  ServicoView.fn = ServicoView.prototype;

  // funcoes publicas
  ServicoView.fn.listView = function() {

    $("#content").html(""); // limpa conteudo

    $("#listarServicosTemplate").tmpl().appendTo("#content");

    inserirNovoServico();
  }

  // funcoes privadas
  var inserirNovoServico = function() {

    $('#btn_inserir_servico').click(function() {

      $("#content").html(""); // limpa conteudo

      // desenha o menu principal atraves do template 'menuTemplate'
      $("#inserirServicoTemplate").tmpl().appendTo("#content");

      // $('.obrigatorio').required(); TODO

      salvarServico();

    });
  }

  var salvarServico = function() {

    $('#form_inserir_servico').submit(function(e) {

      e.preventDefault();

      var servico = new Servico();

      $(this).find('input,select,textarea').each(function() {

        servico.set($(this).attr('id'), $(this).val());
      });

      servico.salvar();
    });

  }

})(window);