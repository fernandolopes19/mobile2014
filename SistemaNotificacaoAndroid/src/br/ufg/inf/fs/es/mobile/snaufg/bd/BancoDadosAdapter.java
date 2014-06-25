package br.ufg.inf.fs.es.mobile.snaufg.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BancoDadosAdapter {
	private SQLiteDatabase database;
	private BancoDados bancoDados;
	private String[] allColumns = { BancoDados.ID,
			BancoDados.REMETENTE_MENSAGEM, BancoDados.HORA_ENVIO_MENSAGEM,
			BancoDados.MENSAGEM };

	/**
	 * Construtor da classe que ir� inicializar uma inst�ncia da classe
	 * BancoDados
	 * 
	 * @param context
	 */
	public BancoDadosAdapter(Context context) {
		bancoDados = new BancoDados(context);
	}

	public void abrirBancoDados() throws SQLException {
		database = bancoDados.getWritableDatabase();
	}

	public void fecharBancoDados() {
		bancoDados.close();
	}

	/**
	 * M�todo para guardar uma nova mensagem no banco de dados
	 * 
	 * @param remetente
	 * @param hora
	 * @param mensagem
	 * @return
	 */
	public Mensagem guardarMensagem(String remetente, String hora,
			String mensagem) {
		ContentValues values = new ContentValues();
		values.put(BancoDados.REMETENTE_MENSAGEM, remetente);
		values.put(BancoDados.HORA_ENVIO_MENSAGEM, hora);
		values.put(BancoDados.MENSAGEM, mensagem);
		long insertId = database.insert(BancoDados.NOME_TABELA_MENSAGEM, null,
				values);

		Cursor cursor = database.query(BancoDados.NOME_TABELA_MENSAGEM,
				allColumns, BancoDados.ID + " = " + insertId, null, null, null,
				null);

		cursor.moveToFirst();
		return cursorToMensagem(cursor);
	}

	/**
	 * M�todo para eliminar uma mensagem
	 * 
	 * @param idMensagem
	 */
	public void EliminaMensagem(int idMensagem) {
		database.delete(BancoDados.NOME_TABELA_MENSAGEM, BancoDados.ID + " = "
				+ idMensagem, null);
	}

	/**
	 * M�todo para devolver uma Mensagem passando como par�metro um cursor
	 * 
	 * @param cursor
	 * @return
	 */
	private Mensagem cursorToMensagem(Cursor cursor) {
		Mensagem mensagem = new Mensagem(cursor.getLong(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3));
		return mensagem;
	}

	/**
	 * M�todo para devolver todas as mensagens da Tabela
	 * 
	 * @return
	 */
	public Cursor getListaMensagens() {
		Cursor cursor = database.rawQuery("select " + BancoDados.ID + " "
				+ BancoDados.REMETENTE_MENSAGEM + " "
				+ BancoDados.HORA_ENVIO_MENSAGEM + " " + BancoDados.MENSAGEM
				+ " from " + BancoDados.NOME_TABELA_MENSAGEM, null);
		return cursor;
	}

	/**
	 * M�todo para retornar uma mensagem passando como par�metro o id da
	 * mensagem
	 * 
	 * @param idMensagem
	 * @return
	 */
	public Mensagem getMensagem(int idMensagem) {
		Cursor cursor = database.query(BancoDados.NOME_TABELA_MENSAGEM,
				allColumns, BancoDados.ID + " = " + idMensagem, null, null,
				null, null);
		cursor.moveToFirst();
		return cursorToMensagem(cursor);
	}
}
