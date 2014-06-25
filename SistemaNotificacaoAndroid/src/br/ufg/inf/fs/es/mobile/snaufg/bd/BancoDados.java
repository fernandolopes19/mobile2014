package br.ufg.inf.fs.es.mobile.snaufg.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BancoDados extends SQLiteOpenHelper{
	
	private static final int BANCO_DE_DADOS_VERSAO = 1;
	private static final String BANCO_DE_DADOS_NOME = "snaufg.db";
	
	public static final String NOME_TABELA_REGISTRO = "tRegistro";
	public static final String NOME_TABELA_MENSAGEM = "tMensagem";
	
	// Usado para criar tabela para armazenar Mensagens
	public static final String ID = "_id";
	public static final String REMETENTE_MENSAGEM = "remetente";
	public static final String HORA_ENVIO_MENSAGEM = "hora";
	public static final String MENSAGEM = "mensagem";
	private static final String ESTRUTURA_TABELA_MENSAGEM = "( "
			+ ID + " integer primary key autoincrement, "
			+ REMETENTE_MENSAGEM + " text not null, "
			+ HORA_ENVIO_MENSAGEM + " datetime not null, "
			+ MENSAGEM + " text not null);";
	
	// Usado para criar tabela para armazenar Registros
	public static final String LOGIN = "login";
	public static final String SENHA = "senha";
	private static final String ESTRUTURA_TABELA_REGISTRO = "( "
			+ LOGIN + " integer primary key not null, "
			+ SENHA + " text not null);";
	
	
	public BancoDados(Context context) {
		super(context, BANCO_DE_DADOS_NOME, null, BANCO_DE_DADOS_VERSAO);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String stmtSQL;
		
		// Criar tabela para armazenar mensagens
		stmtSQL = "create table " + NOME_TABELA_MENSAGEM + ESTRUTURA_TABELA_MENSAGEM;
		db.execSQL(stmtSQL);
		
		// Criar tabela para armazenar registros
		//stmtSQL = "create table " + NOME_TABELA_REGISTRO + ESTRUTURA_TABELA_REGISTRO;
		//db.execSQL(stmtSQL);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(BancoDados.class.getName(), "Fazendo upgrade do banco de dados da versao "
				+ oldVersion + " para " + newVersion + ". Os dados antigos serao excluidos.");
		db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA_MENSAGEM);
		onCreate(db);
		
	}

}
