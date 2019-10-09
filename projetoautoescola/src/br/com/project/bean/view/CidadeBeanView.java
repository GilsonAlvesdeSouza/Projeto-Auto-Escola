package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")

public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Cidade cidade = new Cidade();
	private List<Cidade> cidades = new ArrayList<>();
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";
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

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_cidade");
		super.setNomeRelatorioSaida("relatório_cidades");
		super.setListDataBeanCollectionReport(cidadeController.findList(getClassImplement()));
		return super.getArquivoReport();
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
		cidades.clear();
		cidade = new Cidade();
	}

	@Override
	public String save() throws Exception {
		cidade = cidadeController.merge(cidade);
		Messages.addGlobalInfo("Dados salvo com sucesso!");
		novo();
		getCidade();
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		cidade = cidadeController.merge(cidade);
		novo();
		cidades.add(cidade);
		Messages.addGlobalInfo("Dados salvo com sucesso!");
	}

	@Override
	public void saveEdit() throws Exception {
		// faz algum processamento
		saveNotReturn();
	}

	@Override
	public String editar() throws Exception {
		cidades.clear();
		return url;
	}

	@Override
	public void excluir() throws Exception {
		cidade = (Cidade) cidadeController.getSession().get(getClassImplement(), cidade.getId());
		cidadeController.delete(cidade);
		cidades.remove(cidade);
		novo();
		Messages.addGlobalInfo("Cidade excluida com sucesso!");
	}

	@Override
	protected Class<Cidade> getClassImplement() {
		return Cidade.class;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<Cidade> getController() {
		return cidadeController;
	}

}
