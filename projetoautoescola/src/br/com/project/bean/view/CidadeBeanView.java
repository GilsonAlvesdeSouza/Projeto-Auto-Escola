package br.com.project.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Cidade cidade = new Cidade();
	private List<Cidade> cidades;
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	@Autowired
	private CidadeController cidadeController;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() throws Exception {
		cidades = cidadeController.getListCidade();
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public String novo() throws Exception {
		cidade = new Cidade();
		return url;
	}

	@Override
	public String save() throws Exception {
		cidade = cidadeController.merge(cidade);
		getCidade();
		return "";
	}

	@Override
	public String editar() throws Exception {

		return url;
	}

	@Override
	public void excluir() throws Exception {
		cidadeController.delete(cidade);
		novo();
		getCidades();
	}

}
