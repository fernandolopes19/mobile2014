package br.ufg.inf.fs.es.mobile.snaufg.activity;

import br.ufg.inf.fs.es.mobile.snaufg.R;
import br.ufg.inf.fs.es.mobile.snaufg.bd.BancoDadosAdapter;
import br.ufg.inf.fs.es.mobile.snaufg.bd.Mensagem;
import android.app.Activity;
import android.os.Bundle;

public class MensagemDetalheActivity extends Activity {

	int idMensagem;
	BancoDadosAdapter datasource;
	Mensagem mensagem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mensagem_detalhe);

		carregarMensagemDetalhes();

	}

	private void carregarMensagemDetalhes() {
		idMensagem = getIntent().getIntExtra("idMensagem", 0);

		datasource = new BancoDadosAdapter(this);
		datasource.abrirBancoDados();
		mensagem = datasource.getMensagem(idMensagem);
		datasource.fecharBancoDados();
	}
}
