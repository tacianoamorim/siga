package br.ufrpe.siga.dado.excecao;

public class RegistroNaoEncontradoException extends Exception {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2772606902896141095L;
	
	private String mensagem;
	
	public RegistroNaoEncontradoException() {
	}
	
	public RegistroNaoEncontradoException(String mensagem) {
		this.mensagem= mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
