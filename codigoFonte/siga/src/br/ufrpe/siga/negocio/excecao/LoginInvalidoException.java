package br.ufrpe.siga.negocio.excecao;

public class LoginInvalidoException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3778358302590295010L;
	
	private String mensagem;
	
	public LoginInvalidoException() {
		this.mensagem= "C�digo ou senha inv�lida, favor verificar os dados digitados.";
	}

	public String getMensagem() {
		return mensagem;
	}

}
