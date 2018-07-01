package br.com.sistema.app.eventos;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvento extends ApplicationEvent {

	private static final long serialVersionUID = 201806191810l;

	private HttpServletResponse response;
	private Long id;

	public RecursoCriadoEvento(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return id;
	}
}