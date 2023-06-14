package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio, ServletContext context) throws Exception {
		
		/*Cria Lista de dados do Banco*/
		
		JRAbstractBeanDataSource jrDataSource= new JRBeanCollectionDataSource(listaDados);
		String caminhoJasper= context.getRealPath("relatorio")+ File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint impressoraJasper= JasperFillManager.fillReport(caminhoJasper, new HashMap<>(), jrDataSource);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
		
	}

}
