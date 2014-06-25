package br.ufg.inf.fs.es.mobile.snaufg.activity;

import br.ufg.inf.fs.es.mobile.snaufg.bd.BancoDadosAdapter;
import br.ufg.inf.fs.es.mobile.snaufg.R;
import br.ufg.inf.fs.es.mobile.snaufg.R.id;
import br.ufg.inf.fs.es.mobile.snaufg.R.layout;
import br.ufg.inf.fs.es.mobile.snaufg.R.menu;
import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.os.Build;

public class ListaMensagemActivity extends ListActivity {
	ListAdapter adapter;
	BancoDadosAdapter datasource;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_mensagem);
		
		datasource = new BancoDadosAdapter(this);
		datasource.abrirBancoDados();
		
		Cursor cursor = datasource.getListaMensagens();
		String[] columns = new String[] { "remetente", "mensagem" };
		int[] to = new int[] {R.id.remetente, R.id.mensagem};
		adapter = new SimpleCursorAdapter(this, R.layout.lista_mensagem_item,
				cursor, columns, to);
		this.setListAdapter(adapter);
		datasource.fecharBancoDados();
		
	}
}





















