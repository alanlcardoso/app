package br.com.sistema.app.eventos.ouvinte;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sistema.app.eventos.RecursoCriadoEvento;

@Component
public class RecursoCriadoOuvinte implements ApplicationListener<RecursoCriadoEvento> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvento recursoCriadoEvento) {
		adicionarHeaderLocation(recursoCriadoEvento.getResponse(), recursoCriadoEvento.getId());
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}