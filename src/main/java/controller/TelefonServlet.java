package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Telefone;
import persistence.GenericDao;
import persistence.TelefoneDao;

@WebServlet("/telefone")
public class TelefonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TelefonServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("telefone.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Entrada
		String cmd = request.getParameter("botao");
		String ra = request.getParameter("ra");
		String numero = request.getParameter("numero");
		String numero_novo = request.getParameter("numero_novo");
		String numero_antigo = request.getParameter("numero_antigo");
		
		// Retorno
		String saida = "";
		String erro = "";
		Telefone tel = new Telefone();
		List<Telefone> telefones = new ArrayList<>();

		// Objetos
		
		if(!ra.isEmpty()) {
			Aluno a = new Aluno();
			a.setRa(ra);
			tel.setAluno(a);
		}
		

		if (cmd.contains("Cadastrar") || cmd.contains("Excluir")) {
			tel.setNumero(numero);
		}
		if (cmd.contains("Alterar")) {
			tel.setNumero(numero_novo);
		}

		try {
			if (cmd.contains("Cadastrar")) {
				if (numero.isEmpty() || ra.isEmpty()) {
					saida = "Preencha todos os campos referente a Cadastrar";
				} else {
					saida = cadastrarTelefone(tel);
					tel = null;
				}
			}

			if (cmd.contains("Alterar")) {
				if (numero_novo.isEmpty() || ra.isEmpty() || numero_antigo.isEmpty() || numero_novo.isEmpty()) {
					saida = "Preencha todos os campos referente a Alterar";
				} else {
					Telefone tel_antigo = new Telefone();
					tel_antigo.setNumero(numero_antigo);
					saida = alterarTelefone(tel, tel_antigo);
					tel = null;
				}
			}
			if (cmd.contains("Excluir")) {
				if (numero.isEmpty() || ra.isEmpty()) {
					saida = "Preencha todos os campos referente a Excluir";
				} else {
					saida = excluirTelefone(tel);
					tel = null;
				}
			}
			if (cmd.contains("Listar")) {
				if (ra.isEmpty()) {
					saida = "Preencha o ra do aluno que deseja listar os telefones";
				} else {
					telefones = listarTelefone(tel);
					tel = null;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
			tel = null;
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("telefone", tel);
			request.setAttribute("telefones", telefones);
			
			RequestDispatcher rd = request.getRequestDispatcher("telefone.jsp");
			rd.forward(request, response);
		}
	}

	private List<Telefone> listarTelefone(Telefone tel) throws ClassNotFoundException, SQLException {
		List<Telefone> lista = new ArrayList<>();
		GenericDao gDao = new GenericDao();
		TelefoneDao tDao = new TelefoneDao(gDao);
		lista = tDao.listar(tel);
		return lista;
	}

	private String excluirTelefone(Telefone tel) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		TelefoneDao tDao = new TelefoneDao(gDao);
		String saida = tDao.exclui(tel);
		return saida;
	}

	private String alterarTelefone(Telefone tel, Telefone tel_antigo) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		TelefoneDao tDao = new TelefoneDao(gDao);
		String saida = tDao.atualizar(tel, tel_antigo);
		return saida;
	}

	private String cadastrarTelefone(Telefone tel) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		TelefoneDao tDao = new TelefoneDao(gDao);
		String saida = tDao.inserir(tel);
		return saida;
	}
}