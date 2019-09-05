package br.com.framework.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import br.com.framework.implementacao.crud.VariavelConexaoUtil;

/**
 * Classe responsavel por estabelecer conexão entre o hibernate e o Banco
 * 
 * @author gilsonalves
 *
 */
@ApplicationScoped
public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Método Responsável por ler o arquivo de configuração do Hirbernate.cfg.xml
	 * 
	 * @return sessionFactory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar a conexão SessionFactory");
		}
	}

	/**
	 * Método que retorna um SessionFactory corrente
	 * 
	 * @return sessionFactory
	 */
	public static SessionFactory getSessonFactory() {
		return sessionFactory;
	}

	/**
	 * Método Retorna a sessão do SessionFactory
	 * 
	 * @return session
	 */
	public static Session getCurrentSession() {
		return getSessonFactory().getCurrentSession();
	}

	/**
	 * Método que Abre uma nova sessão no SessionFactory
	 * 
	 * @return session
	 */
	public static Session openSession() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory.openSession();
	}

	/**
	 * Método que Obtem a conexão do provedor de conexões configurado
	 * 
	 * @return Connection Sql
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException {

		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();

	}

	/**
	 * Método que retorna connection no InitialContest
	 * java:/comp/env/jdbc/datasource
	 * 
	 * @return connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		return ds.getConnection();
	}

	/**
	 * Método que captura um DataSource JNDI tomcat
	 * 
	 * @return DataSource
	 * @throws NamingException
	 */
	public DataSource getDataSourceJndi() throws NamingException {
		InitialContext context = new InitialContext();
		return (DataSource) context.lookup(VariavelConexaoUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);

	}

}
