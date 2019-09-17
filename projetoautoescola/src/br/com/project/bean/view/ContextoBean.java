package br.com.project.bean.view;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.project.model.classes.Entidade;

@Scope(value = "session")
@Component(value = "contextoBean") // essa anotação permite a classe ser usada em qualquer lugar do sistema
public class ContextoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Método que retona todas as informações do usuário logado no spring security
	 * 
	 * @return Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Entidade getEntidadeLogada() {

		return null;
	}

}
