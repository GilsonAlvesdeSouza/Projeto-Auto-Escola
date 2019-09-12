package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de Implemntação dos métodos das transações no banco
 * 
 * @author gilsonalves
 *
 */
@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

	/**
	 * Método que salva os dados na base de dados
	 * 
	 * @param Obj
	 * @throws Exception
	 */
	void save(T obj) throws Exception;

	/**
	 * Método que Persiste os dados na base de dados
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void persit(T obj) throws Exception;

	/**
	 * Método que salva ou atualiza os dados na base de dados
	 * 
	 * @param obj
	 */
	void saveOrUpdate(T obj) throws Exception;

	/**
	 * Método que atualiza os dado na base de dados
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void update(T obj) throws Exception;

	/**
	 * Método que exclui os dados na base de dados
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void delete(T obj) throws Exception;

	/**
	 * Método que Salva ou atualiza os dados e retorna o Objeto em estado
	 * persistente
	 * 
	 * @param obj
	 * @throws Exception
	 */
	T merge(T obj) throws Exception;

	/**
	 * Carrega uma lista de dados de uma determinada classe
	 * 
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	List<T> findList(Class<T> objs) throws Exception;

	/**
	 * Método que busca uma entidade por ID
	 * 
	 * @param entidade
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Object findById(Class<T> entidade, Long id) throws Exception;

	/**
	 * Método que busca um Objeto por ID
	 * 
	 * @param entidade
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T findPorId(Class<T> entidade, Long id) throws Exception;

	/**
	 * Método que retorna uma lista usando HQL
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	List<T> findListQueryDinamic(String s) throws Exception;

	/**
	 * Método que execulta update com HQL
	 * 
	 * @param s
	 * @throws Exception
	 */
	void executeUpdateQueryDinamic(String s) throws Exception;

	/**
	 * Método que execulta update com SQL puro
	 * 
	 * @param s
	 * @throws Exception
	 */
	void executeUpdateSQLDinamic(String s) throws Exception;

	/**
	 * Método para limpar a Sessão do hibernate
	 * 
	 * @throws Exception
	 */
	void clearSession() throws Exception;

	/**
	 * Método que retira um objeto da sessão do hibernate
	 * 
	 * @param objs
	 * @throws Exception
	 */
	void evict(Object objs) throws Exception;

	/**
	 * Método que retorna a sessão do hibernate
	 * 
	 * @return
	 * @throws Exception
	 */
	Session getSession() throws Exception;

	/**
	 * Método que recebe um SQL e o hibernate retorna uma lista de dados
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	List<?> getListSQLDinamic(String sql) throws Exception;

	/**
	 * Método que recebe um SQL e o hibernate retorna um lista com Array de Objetos
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	List<Object[]> getListSQLDinamicArray(String sql) throws Exception;

	/**
	 * Método que execulta consultas SQL utilizando Spring
	 * 
	 * @return
	 */
	JdbcTemplate getJdbcTemplate();

	/**
	 * Método que execulta consultas SQL utilizando Spring
	 * 
	 * @return
	 */
	SimpleJdbcTemplate getSimpleJdbcTemplate();

	/**
	 * Método que execulta consultas SQL utilizando Spring
	 * 
	 * @return
	 */
	SimpleJdbcInsert getSimpleJdbcInsert();

	/**
	 * Método que execulta consultas SQL utilizando Spring
	 * 
	 * @return
	 */
	SimpleJdbcCall getSimpleJdcClass();

	/**
	 * Método que retorna o total de registro de uma determinada tabela
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	Long totalRegistro(String table) throws Exception;

	/**
	 * Método para recuperar uma determinada query Dinamica para consultas
	 * especificas na base de dados
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Query obterQuery(String query) throws Exception;

	/**
	 * Método que faz consultas por demanda com JSF e PrimeFaces Realiza consulta no
	 * banco de dados, Iniciando o carregamento a partir do registro passado no
	 * parametro -> iniciaRegistro e obtendo o máximo de resultados passados em ->
	 * maximoResultado.
	 * 
	 * @param query
	 * @param iniciaNoRegistro
	 * @param maximoResultado
	 * @return List<T>
	 * @throws Exception
	 */
	List<T> findByQueryDinamic(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;

}
