package br.com.project.bean.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.SessionController;
import br.com.srv.interfaces.SrvLogin;

@Controller
@Scope(value = "request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	@Autowired
	private SessionController sessionController;
	@Autowired
	private SrvLogin srvLogin;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Método para invalidar a sessão aberta
	 * 
	 * @param actionEvent
	 * @throws Exception
	 */
	public void invalidar(ActionEvent actionEvent) throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean loggedIn = false;
		FacesMessage msg = null;

		if (srvLogin.autentico(getUserName(), getPassword())) {
			sessionController.invalidateSession(getUserName());
			loggedIn = true;
		} else {
			loggedIn = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso negado!", "Login ou senha icorretos.");
		}

		if (msg != null) {
			FacesContext.getCurrentInstance().addMessage("msg", msg);
		}

		context.addCallbackParam("loggedIn", loggedIn);
	}

}
