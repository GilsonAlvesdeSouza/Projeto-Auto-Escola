package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;

@Repository
public class DAOEntidade extends ImplementacaoCrud<Entidade> implements RepositoryEntidade {

	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existeUsuario(String entLogin) {
		// TODO Auto-generated method stub
		return false;
	}

}
