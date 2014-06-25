package br.ufg.inf.fs.es.mobile.snaufg.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.ufg.inf.fs.es.mobile.snaufg.R;
import br.ufg.inf.fs.es.mobile.snaufg.util.Constantes;
import br.ufg.inf.fs.es.mobile.snaufg.util.GCM;

public class SNAActivity extends ActionBarActivity {
	
	private Button botaoAtivarDesativar;
	private boolean statusGCM;

	/**
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sna);
		botaoAtivarDesativar = (Button) findViewById(R.id.botao_ativar_desativar);
		
		statusGCM = GCM.statusGCM(getApplicationContext());
		
		botaoNome();
	}
	
	public void listaMensagem(View view){
		Intent intent = new Intent(this, ListaMensagemActivity.class);
		startActivity(intent);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void ativaDesativaSNA(View view) {
		if (GCM.statusGCM(getApplicationContext())) {
			GCM.desativaSNA(getApplicationContext());
			statusGCM = false;
			Toast.makeText(getApplicationContext(), Constantes.SNA_DESATIVADO, Toast.LENGTH_LONG).show();
		} else {
			GCM.ativaSNA(getApplicationContext());
			statusGCM = true;
			Toast.makeText(getApplicationContext(), Constantes.SNA_ATIVO, Toast.LENGTH_LONG).show();
		}
		botaoNome();
	}
	
	/**
	 * 
	 */
	private void botaoNome() {
		if (statusGCM) {
			botaoAtivarDesativar.setText("Desativar SNA");
		} else {
			botaoAtivarDesativar.setText("Ativar SNA");
		}
	}

}
