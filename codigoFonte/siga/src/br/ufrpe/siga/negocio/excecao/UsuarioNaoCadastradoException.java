package br.ufrpe.siga.negocio.excecao;

public class UsuarioNaoCadastradoException extends Exception {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3853876455807604131L;
	
	private String mensagem;
	
	public UsuarioNaoCadastradoException(String mensagem) {
		this.mensagem= mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
