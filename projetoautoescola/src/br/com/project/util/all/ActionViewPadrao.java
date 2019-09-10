package br.com.project.util.all;

import java.io.Serializable;

import javax.annotation.PostConstruct;

/**
 * Implementação dos ManegedBeans
 * 
 * @author gilsonalves
 *
 */
public interface ActionViewPadrao extends Serializable {

	/**
	 * Método que limpa a lista
	 * 
	 * @throws Exception
	 */
	abstract void limpaLista() throws Exception;

	/**
	 * Método que Salva o registro e retorna para alguma página
	 * 
	 * @return
	 * @throws Exception
	 */
	abstract String save() throws Exception;

	/**
	 * Método que salva o registro e não retorna para lugar nenhum
	 */
	abstract void saveNotReturn() throws Exception;

	/**
	 * Método que salva a edição de um registro
	 */
	abstract void saveEdit() throws Exception;

	/**
	 * Método para excluir um registro
	 * 
	 * @throws Exception
	 */
	abstract void excluir() throws Exception;

	/**
	 * Metodo que ativa um registro e direciona para alguma página caso necessário
	 * 
	 * @return
	 * @throws Exception
	 */
	abstract String ativar() throws Exception;

	/**
	 * Método que cria um novo registro
	 * 
	 * @return
	 * @throws Exception
	 * @PostConstruct realiza inicialização de métodos, valores ou variáveis
	 */
	@PostConstruct
	abstract String novo() throws Exception;

	/**
	 * Método que edita um registro
	 * 
	 * @return
	 * @throws Exception
	 */
	abstract String editar() throws Exception;

	/**
	 * Método que limpa os dados do ManegedBean
	 * 
	 * @throws Exception
	 */
	abstract void setarVariaveisNulas() throws Exception;

	/**
	 * Método para consultar um registro
	 * 
	 * @throws Exception
	 */
	abstract void consultarEntidade() throws Exception;

	/**
	 * Método que recebe o estatus da persistência
	 * 
	 * @param a
	 * @throws Exception
	 */
	abstract void statusOperation(EstatusPersistencia a) throws Exception;

	/**
	 * Método que cria um novo registro e redireciona
	 * 
	 * @return
	 * @throws Exception
	 */
	abstract String redirecinarNewEntidade() throws Exception;

	/**
	 * Método que realiza uma consulta e redireciona
	 */
	abstract String redirecionarFindEntidade() throws Exception;

	/**
	 * Método que adiona mensagem ao ManegedBean
	 */
	abstract void addMsg(String msg) throws Exception;

}
