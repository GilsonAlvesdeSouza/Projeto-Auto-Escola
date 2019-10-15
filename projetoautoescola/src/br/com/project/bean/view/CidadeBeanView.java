package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")

public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private Cidade cidade = new Cidade();
	private CarregamentoLazyListForObject<Cidade> cidades = new CarregamentoLazyListForObject<Cidade>();
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

	public CarregamentoLazyListForObject<Cidade> getCidades() throws Exception {
		return cidades;
	}

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_cidade");
		super.setNomeRelatorioSaida("relatório_cidades");
		super.setListDataBeanCollectionReport(cidadeController.findList(getClassImplement()));
		return super.getArquivoReport();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
		cidades.getList().clear();
		cidade = new Cidade();
	}

	@Override
	public String save() throws Exception {
		cidade = cidadeController.merge(cidade);
		sucesso();
		novo();
		getCidade();
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		cidade = cidadeController.merge(cidade);
		cidades.add(cidade);
		cidade = new Cidade();
		sucesso();
	}

	@Override
	public void saveEdit() throws Exception {
		// faz algum processamento
		saveNotReturn();
	}

	@Override
	public String editar() throws Exception {
		cidades.getList().clear();
		return url;
	}

	@Override
	public void excluir() throws Exception {
		cidade = (Cidade) cidadeController.getSession().get(getClassImplement(), cidade.getId());
		cidadeController.delete(cidade);
		cidades.remove(cidade);
		novo();
		sucesso();
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

	@Override
	public void consultarEntidade() throws Exception {
		cidade = new Cidade();
		cidades.clean();
		cidades.setTotalResgistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}
}
