package br.com.caelum.relatorio.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.relatorio.ConnectionFactory;
import br.com.caelum.relatorio.gerador.GeradorRelatorio;

@WebServlet("/movimentacoes")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection connection = new ConnectionFactory().getConnection();

			String realPath = request.getServletContext().getRealPath("/WEB-INF/jasper/gasto_por_mes.jasper");
			//String nome = "gasto_por_mes.jasper";
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			String dataIni = request.getParameter("data_ini");
			String datFim = request.getParameter("data_fim");
			
			
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			Date dataInicio = dateFormat.parse(dataIni);
			
			Date dataFim = dateFormat.parse(datFim);
			
			parameters.put("dataInicio", dataInicio);
			parameters.put("dataFim", dataFim);
			
			GeradorRelatorio geradorRelatorio = new GeradorRelatorio(realPath, parameters, connection);
			
			geradorRelatorio.geraPDFParaOutputStream(response.getOutputStream());
			
			//Aqui gera o PDF.
			//O arquivo .jasper se encontra na pasta /WEB-INF/jasper/movimentacoes.jasper
			//Voc� pode utilizar a classe GeradorRelatorio que j‡ existe no projeto.
			
			
			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
