package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long entCodigo;

	private String entLogin = null;

	private String entSenha = null;

	private boolean entInativo = false;

	@Temporal(TemporalType.TIMESTAMP)
	private Date entUltimoAcesso;

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

	public boolean getEntInativo() {
		return entInativo;
	}

	public void setEntInativo(boolean entInativo) {
		this.entInativo = entInativo;
	}

	public Date getEntUltimoAcesso() {
		return entUltimoAcesso;
	}

	public void setEntUltimoAcesso(Date entUltimoAcesso) {
		this.entUltimoAcesso = entUltimoAcesso;
	}

}
