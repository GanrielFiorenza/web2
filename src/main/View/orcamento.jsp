<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Orçamento" %>
<%@ page import="Model.OrcamentoDAO" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestão de Orçamentos</title>
</head>
<body>
    <h1>Gestão de Orçamentos</h1>

    <!-- Formulário para Inserir ou Editar Orçamento -->
    <h2><%= request.getAttribute("orcamentoEdit") == null ? "Inserir Novo Orçamento" : "Editar Orçamento" %></h2>
    <form action="OrcamentoInserirServlet" method="POST">
        <input type="hidden" name="id" value="<%= request.getAttribute("orcamentoEdit") != null ? ((Orçamento)request.getAttribute("orcamentoEdit")).getId() : "" %>">
        
        <label>Projeto:</label>
        <input type="text" name="projeto" value="<%= request.getAttribute("orcamentoEdit") != null ? ((Orçamento)request.getAttribute("orcamentoEdit")).getProjeto() : "" %>" required/><br/>
        
        <label>Valor:</label>
        <input type="number" step="0.01" name="valor" value="<%= request.getAttribute("orcamentoEdit") != null ? ((Orçamento)request.getAttribute("orcamentoEdit")).getValor() : "" %>" required/><br/>
        
        <label>ID do Cliente:</label>
        <input type="number" name="clienteId" value="<%= request.getAttribute("orcamentoEdit") != null ? ((Orçamento)request.getAttribute("orcamentoEdit")).getClienteId() : "" %>" required/><br/>
        
        <label>Status:</label>
        <select name="status" required>
            <option value="Pendente" <%= request.getAttribute("orcamentoEdit") != null && "Pendente".equals(((Orçamento)request.getAttribute("orcamentoEdit")).getStatus()) ? "selected" : "" %>>Pendente</option>
            <option value="Aprovado" <%= request.getAttribute("orcamentoEdit") != null && "Aprovado".equals(((Orçamento)request.getAttribute("orcamentoEdit")).getStatus()) ? "selected" : "" %>>Aprovado</option>
            <option value="Rejeitado" <%= request.getAttribute("orcamentoEdit") != null && "Rejeitado".equals(((Orçamento)request.getAttribute("orcamentoEdit")).getStatus()) ? "selected" : "" %>>Rejeitado</option>
        </select><br/>
        
        <button type="submit"><%= request.getAttribute("orcamentoEdit") == null ? "Salvar" : "Atualizar" %></button>
    </form>

    <!-- Listagem dos Orçamentos -->
    <h2>Lista de Orçamentos</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Projeto</th>
            <th>Valor</th>
            <th>ID Cliente</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="orcamento" items="${orcamentos}">
            <tr>
                <td>${orcamento.id}</td>
                <td>${orcamento.projeto}</td>
                <td>${orcamento.valor}</td>
                <td>${orcamento.clienteId}</td>
                <td>${orcamento.status}</td>
                <td>
                    <a href="OrcamentoEditarServlet?id=${orcamento.id}">Editar</a> | 
                    <a href="OrcamentoExcluirServlet?id=${orcamento.id}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
