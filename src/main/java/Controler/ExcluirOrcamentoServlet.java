package Controler;

import java.io.*;
import java.sql.*;

import Model.Orçamento;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.OrcamentoDAO;

public class ExcluirOrcamentoServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        // Estabelece a conexão com o banco de dados
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuBanco", "usuario", "senha");
        } catch (SQLException e) {
            throw new ServletException("Erro ao conectar ao banco de dados", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperando o ID do orçamento que será excluído
        int id = Integer.parseInt(request.getParameter("id"));
        OrcamentoDAO dao = new OrcamentoDAO(connection);

        try {
            dao.excluirOrcamento(id);
            response.sendRedirect("orcamentos");  // Redireciona para a lista de orçamentos
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir o orçamento", e);
        }
    }
}
