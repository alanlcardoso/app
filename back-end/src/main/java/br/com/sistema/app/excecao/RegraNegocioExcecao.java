package br.com.sistema.app.excecao;


public class RegraNegocioExcecao extends RuntimeException {

	private static final long serialVersionUID = 201806011521l;

	public RegraNegocioExcecao(String mensagem) {
		super(mensagem);
	}
}
