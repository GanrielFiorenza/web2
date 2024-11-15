package Controler;

import java.io.*;
import java.sql.*;
import java.util.*;

import Model.Orçamento;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.OrcamentoDAO;

public class ListarOrcamentosServlet extends HttpServlet {
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
        OrcamentoDAO dao = new OrcamentoDAO(connection);
        try {
            List<Orçamento> orcamentos = dao.listarOrcamentos();
            request.setAttribute("orcamentos", orcamentos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listarOrcamentos.jsp");
            dispatcher.forward(request, response);  // Exibe a lista de orçamentos em uma página JSP
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar os orçamentos", e);
        }
    }
}
