package br.com.project.geral.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Estado;

@Controller
public class EstadoController extends ImplementacaoCrud<Estado> implements InterfaceCrud<Estado> {

	private static final long serialVersionUID = 1L;

	public List<Estado> getListEstado() throws Exception {
		List<Estado> estados = super.findListQueryDinamic("from Estado order by estado");
		return estados;
	}

/////////////////////sem usar Omnifaces///////////////////////////////////
//	@SuppressWarnings("unused")
//	public List<SelectItem> getListEstado() throws Exception {
//		List<SelectItem> list = new ArrayList<SelectItem>();
//		List<Estado> estados = super.findListQueryDinamic("from Estado order by estado");
//
//		for (Estado estado : estados) {
//			list.add(new SelectItem(estado, estado.getDescricao()));
//
//		}
//		return list;
//	}
}
