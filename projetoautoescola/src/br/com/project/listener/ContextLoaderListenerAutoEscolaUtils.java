package br.com.project.listener;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Classe que carrega as configurações do spring
 * 
 * @author gilsonalves
 *
 */
@ApplicationScoped
public class ContextLoaderListenerAutoEscolaUtils extends ContextLoaderListener implements Serializable {

	private static final long serialVersionUID = 1L;

	private static WebApplicationContext getWAC() {
		return WebApplicationContextUtils
				.getWebApplicationContext(getCurrentWebApplicationContext().getServletContext());
	}

	/**
	 * Método que carrega um objeto pelo id
	 * 
	 * @param IdNomeBean
	 * @return
	 */
	public static Object getBean(String IdNomeBean) {
		return getWAC().getBean(IdNomeBean);
	}

	/**
	 * Método que carrega uma classe pelo nome da classe
	 * 
	 * @param classe
	 * @return
	 */
	public static Object getBean(Class<?> classe) {
		return getWAC().getBean(classe);
	}

}
