package br.com.project.util.report;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Método para colocar a data no relatório
	 * 
	 * @return
	 */
	public static String getDateAtualReportName() {
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		return df.format(Calendar.getInstance().getTime());
	}

	/**
	 * Método para formatar a data para ser inserida na base de dados
	 * 
	 * @param data
	 * @return
	 */
	public static String formatDateSQL(Date data) {
		StringBuffer retorno = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		retorno.append("'");
		retorno.append(df.format(data));
		retorno.append("'");
		return retorno.toString();
	}

	/**
	 * Método para formatar a data sem as aspas
	 * 
	 * @param data
	 * @return
	 */
	public static String formatDateSQLSimple(Date data) {
		StringBuffer retorno = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		retorno.append(df.format(data));
		return retorno.toString();
	}

}
