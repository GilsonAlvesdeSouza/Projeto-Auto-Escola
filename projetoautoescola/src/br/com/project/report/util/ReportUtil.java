package br.com.project.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * classe responsável por gerar os relatórios
 * 
 * @author gilsonalves
 *
 */
@Component
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String UNDERLINE = "_";
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String EXTENSION_ODS = "ods";
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_HTML = "html";
	private static final String EXTENSION_PDF = "pdf";
	private String separator = File.separator;
	private static final int REALTORIO_PDF = 1;
	private static final int REALTORIO_EXCEL = 2;
	private static final int REALTORIO_HTML = 3;
	private static final int REALTORIO_PLANILHA_OPEN_OFFICE = 4;
	private static final String PONTO = ".";
	private StreamedContent arquivoRetorno = null;
	private String caminhoArquiRelatorio = null;
	private JRExporter tipoArquiExportado = null;
	private String extensaoArquiExportado = "";
	private String caminhoSubReport_dir = "";
	private File arquivoGerado = null;

	/**
	 * Método responsável por toda a criação de relátorios
	 */
	public StreamedContent gerarRelatorio(List<?> listDataBeanCollectionReport, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio) throws Exception {

		/*
		 * cria a lista de collectionDataSource de beans que carrega os dados para o
		 * relatório
		 */
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(listDataBeanCollectionReport);

		/*
		 * formece o caminho fisico ate a pasta que contem os relatórios compilados
		 * .jasper
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);

		/*
		 * Caminho: c:/aplicaçao/relatorios/rel_bairro.jasper
		 */
		File file = new File(caminhoRelatorio + separator + nomeRelatorioJasper + PONTO + "jasper");
		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			separator = "";
		}

		/*
		 * caminho para imagens
		 */
		parametrosRelatorio.put("REPORT_PARAMTERS_IMG", caminhoRelatorio);

		/*
		 * caminho completo ate o relatório complilado indicado
		 */
		String caminhoArquivoJapser = caminhoRelatorio + separator + nomeRelatorioJasper + PONTO + "japer";

		/*
		 * Faz o carregamento do relatório indicado
		 */
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJapser);

		/*
		 * seta parmetro SUBREPORT_DIR como caminho para SubReports
		 */
		caminhoSubReport_dir = caminhoRelatorio + separator;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_dir);

		/*
		 * Carrega o arquivo compilado para a memória
		 */
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrDataSource);

		/*
		 * Definido os tipos de impressões
		 */
		switch (tipoRelatorio) {
		case REALTORIO_PDF:
			tipoArquiExportado = new JRPdfExporter();
			extensaoArquiExportado = EXTENSION_PDF;
			break;
		case REALTORIO_HTML:
			tipoArquiExportado = new JRHtmlExporter();
			extensaoArquiExportado = EXTENSION_HTML;
			break;
		case REALTORIO_EXCEL:
			tipoArquiExportado = new JRXlsExporter();
			extensaoArquiExportado = EXTENSION_XLS;
			break;
		case REALTORIO_PLANILHA_OPEN_OFFICE:
			tipoArquiExportado = new JROdtExporter();
			extensaoArquiExportado = EXTENSION_ODS;
			break;

		default:
			tipoArquiExportado = new JRPdfExporter();
			extensaoArquiExportado = EXTENSION_PDF;
			break;
		}
		/*
		 * gerando o nome do relatório
		 */
		nomeRelatorioSaida += UNDERLINE + DateUtils.getDateAtualReportName();

		/*
		 * caminho do relatório exportado
		 */
		caminhoArquiRelatorio = caminhoRelatorio + separator + nomeRelatorioSaida + PONTO + extensaoArquiExportado;

		/*
		 * cria novo arquivo exportado
		 */
		arquivoGerado = new File(caminhoArquiRelatorio);

		/*
		 * Preparando a impressão
		 */
		tipoArquiExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);

		/*
		 * nome do arquivo fisico a ser exportado/impresso
		 */
		tipoArquiExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

		/*
		 * Executa a exportação
		 */
		tipoArquiExportado.exportReport();

		/*
		 * Remove o arquivo do servidor após ser feito o download pelo Usuário
		 */
		arquivoGerado.deleteOnExit();

		/*
		 * cria um inputstream para ser usado pelo PrimeFaces
		 */
		InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);

		/*
		 * faz o retorno para aplicação
		 */
		arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "aplication/" + extensaoArquiExportado,
				nomeRelatorioSaida + PONTO + extensaoArquiExportado);

		return arquivoRetorno;
	}

}
