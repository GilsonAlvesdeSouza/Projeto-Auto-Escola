package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;

@Repository
public class DAOEntidade extends ImplementacaoCrud<Entidade> implements RepositoryEntidade {

	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		SqlRowSet rowSet = super.getJdbcTemplate().queryForRowSet(
				"select entUltimoAcesso from entidade where entInativo is false and entLogin = ?",
				new Object[] { name });
		return rowSet.next() ? rowSet.getDate("entUltimoAcesso") : null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql = "update entidade set entUltimoAcesso = current_timestamp where entInativo is false and entLogin = ? ";
		super.getSimpleJdbcTemplate().update(sql, login);
	}

	@Override
	public boolean existeUsuario(String entLogin) {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(1) >= 1 from entidade where entLogin = '").append(entLogin).append("' ");
		return super.getJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
