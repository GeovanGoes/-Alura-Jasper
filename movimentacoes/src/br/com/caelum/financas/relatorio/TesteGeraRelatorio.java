package br.com.caelum.financas.relatorio;

import br.com.caelum.financas.ConnectionFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class TesteGeraRelatorio {

	public static void main(String[] args) throws SQLException, JRException,
			FileNotFoundException {

		try {
			Connection conexao = new ConnectionFactory().getConnection();
			JasperCompileManager.compileReportToFile("financas.jrxml");
			/*JasperCompileManager.compileReportToFile("gasto_por_mes.jrxml");*/			
			
			Statement stmt = null;
			
			stmt = conexao.createStatement();
			
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM movimentacoes");

			Collection<Movimentacao> listaMovimentacao = new ArrayList<Movimentacao>();
			
			while (rs.next())
			{
				Integer id = rs.getInt("id");
				Date data = rs.getDate("data");
				String descricao = rs.getString("descricao");
				String tipoMovimentacao = rs.getString("tipoMovimentacao");
				BigDecimal valor = rs.getBigDecimal("valor");
				long categoria_id = rs.getLong("categoria_id");
				long conta_id = rs.getLong("conta_id");
				
				Movimentacao movimentacao = new Movimentacao(id, data, descricao, tipoMovimentacao, valor, categoria_id, conta_id);
				listaMovimentacao.add(movimentacao);
			}		

			Map<String, Object> parameters = new HashMap<String, Object>();

			JRDataSource jrDataSource = new JRBeanCollectionDataSource(listaMovimentacao);
			
			JasperPrint fillReport = JasperFillManager.fillReport(
					"financas.jasper", parameters, jrDataSource);
			
			conexao.close();
			
			JRExporter exporter = new JRPdfExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, fillReport);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("minhaRola.pdf"));
			
			exporter.exportReport();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
