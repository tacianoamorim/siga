package br.ufrpe.siga.negocio.excecao;

public class TurmaLotadaException extends Exception {

	private String mensagem;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6740043768251442988L;
	
	public TurmaLotadaException(String mensagem) {
		this.mensagem= mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
