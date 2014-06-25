package br.ufg.inf.fs.es.mobile.snaufg.bd;

public class Mensagem {
	private long _id;
	private String remetente;
	private String hora;
	private String mensagem;

	public Mensagem(long id, String remetente, String hora, String mensagem) {
		this._id = id;
		this.remetente = remetente;
		this.hora = hora;
		this.mensagem = mensagem;
	}

	// Get e Set do campo ID no Banco de Dados
	public long getId() {
		return _id;
	}

	public void setId(long id) {
		this._id = id;
	}

	// Get e Set do campo REMETENTE_MENSAGEM no Banco de Dados
	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	// Get e Set do campo HORA_ENVIO_MENSAGEM no Banco de Dados
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	// Get e Set do campo MENSAGEM no Banco de Dados
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
