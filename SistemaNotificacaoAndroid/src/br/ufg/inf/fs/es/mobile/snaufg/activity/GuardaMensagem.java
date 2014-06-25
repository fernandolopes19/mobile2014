package br.ufg.inf.fs.es.mobile.snaufg.activity;

import br.ufg.inf.fs.es.mobile.snaufg.bd.BancoDadosAdapter;
import br.ufg.inf.fs.es.mobile.snaufg.bd.Mensagem;

public class GuardaMensagem {

	private BancoDadosAdapter datasource;
	private Mensagem msg;

	public void guardaMensagem(String mensagem) {

		// [0]: Remetente
		// [1]: Hora
		// [2]: Mensagem
		String[] mensagemSeparada = mensagem.split("\n");

		datasource.abrirBancoDados();
		msg = datasource.guardarMensagem(mensagemSeparada[0],
				mensagemSeparada[1], mensagemSeparada[2]);
		datasource.fecharBancoDados();
	}
}
