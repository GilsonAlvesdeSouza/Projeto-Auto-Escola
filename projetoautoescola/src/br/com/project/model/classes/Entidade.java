package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long entCodigo;

	private String entLogin = null;

	private String entSenha = null;

	public Long getEntCodigo() {
		return entCodigo;
	}

	public void setEntCodigo(Long entCodigo) {
		this.entCodigo = entCodigo;
	}

	public String getEntLogin() {
		return entLogin;
	}

	public void setEntLogin(String entLogin) {
		this.entLogin = entLogin;
	}

	public String getEntSenha() {
		return entSenha;
	}

	public void setEntSenha(String entSenha) {
		this.entSenha = entSenha;
	}

}
