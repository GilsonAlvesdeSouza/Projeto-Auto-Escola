package br.com.project.bean.geral;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

@SuppressWarnings("unchecked")
public class ViewScope implements Serializable, Scope {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callBacks";

	/**
	 * Método que retorna a instancia do escopo
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Object instance = getViewMap().get(name);
		if (instance == null) {
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		return instance;
	}

	/**
	 * Método que pega o id da sessão
	 */
	@Override
	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
	}

	/**
	 * Método que registra a destruição do escopo
	 */
	@Override
	public void registerDestructionCallback(String nome, Runnable runnable) {
		Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);

		if (callBacks != null) {
			callBacks.put(VIEW_SCOPE_CALLBACKS, runnable);
		}

	}

	/**
	 * Método que remove a instancia do escopo
	 */
	@Override
	public Object remove(String name) {
		Object instance = getViewMap().remove(name);

		if (instance != null) {
			Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			if (callBacks != null) {
				callBacks.remove(name);
			}
		}

		return instance;
	}

	/**
	 * Método que resolve a referencia retornado o Objeto correto
	 */
	@Override
	public Object resolveContextualObject(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);

		return facesRequestAttributes.resolveReference(name);
	}

	/**
	 * Método getViewRoot() -> retorna o componente raiz que está associado a esta
	 * solicitção 'request' getViewMap() -> retorna uma Map que atua com a interface
	 * para o armazemamento de dados
	 * 
	 * @return
	 */
	private Map<String, Object> getViewMap() {
		return FacesContext.getCurrentInstance() != null ? FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				: new HashMap<String, Object>();
	}

}
