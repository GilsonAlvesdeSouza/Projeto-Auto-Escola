package br.com.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotaçoes para permissões de pesquisas
 * 
 * @author gilsonalves
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampoPesquisa {
	String descricaoCampo(); // descrição de campo para a tela

	String campoConsulta(); // campo do banco

	int principal() default 10000;// posição que ira aparecer no combo
}
