package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Disciplina;
import model.Matricula;
import persistence.GenericDao;
import persistence.MatriculaDao;

@WebServlet("/matricula")
public class MatriculaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MatriculaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Entrada
		String cmd = request.getParameter("botao");
		String ra = request.getParameter("ra");
		String nome_disciplina = request.getParameter("nome");
		String dia_semana = request.getParameter("dia_semana");
		String hora_inicio = request.getParameter("hora_inicio");
		String hora_fim = request.getParameter("hora_fim");
		String aulas_semanais = request.getParameter("aulas_semanais");
		String codigo_disciplina = request.getParameter("codigo");

		// Retorno
		String saida = "";
		String erro = "";
		Matricula m = new Matricula();
		List<Matricula> matriculas = new ArrayList<>();
		List<Disciplina> disciplinas = new ArrayList<>();

		if (cmd.equals("Listar Disciplinas") || cmd.equals("Matricular") || cmd.equals("Listar Matriculas") || 
				cmd.equals("Listar Matriculas Ativas") || cmd.equals("Matricular-se") || cmd.equals("Listar Disciplinas Disponiveis")) {
			if (ra.trim().isEmpty()) {
				saida = "Ra em branco";
				request.setAttribute("saida", saida);
				
				if(cmd.equals("Listar Matriculas")){
					RequestDispatcher rd = request.getRequestDispatcher("consultarMatricula.jsp");
					rd.forward(request, response);
					return;
				}
				if(cmd.equals("Listar Disciplinas") || cmd.equals("Matricular")) {
					RequestDispatcher rd = request.getRequestDispatcher("matricula.jsp");
					rd.forward(request, response);
					return;
				}
				if(cmd.equals("Listar Matriculas Ativa")){
					RequestDispatcher rd = request.getRequestDispatcher("aluno.jsp");
					rd.forward(request, response);
					return;
				}
				if(cmd.equals("Matricular-se")){
					RequestDispatcher rd = request.getRequestDispatcher("alunoMatricula.jsp");
					rd.forward(request, response);
					return;
				}
				if(cmd.equals("Listar Disciplinas Disponiveis")){
					RequestDispatcher rd = request.getRequestDispatcher("alunoMatricula.jsp");
					rd.forward(request, response);
					return;
				}
			} else {
				Aluno a = new Aluno();
				a.setRa(ra);
				m.setAluno(a);
			}
		}
		
		if (cmd.contains("Matricular")) {
			Disciplina d = new Disciplina();
			d.setCodigo(Integer.parseInt(codigo_disciplina));
			d.setAulas_semanais(Integer.parseInt(aulas_semanais));
			d.setDia_semana(dia_semana);
			d.setHora_inicio(LocalTime.parse(hora_inicio));
			d.setHora_fim(LocalTime.parse(hora_fim));
			d.setNome(nome_disciplina);
			m.setDisciplina(d);
		}

		try {
			if (cmd.contains("Listar Disciplinas")) {
				disciplinas = listarDisciplinas(m);
				if (disciplinas.isEmpty()) {
					saida = "Ra Inexistente";
				}
			}
			if (cmd.contains("Matricular")) {
				saida = inserirMatricula(m);
			}
			if (cmd.contains("Listar Matriculas") || cmd.contains("Listar Matriculas Ativas")) {
				matriculas = listarMatricula(m);
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
			m = null;
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("matricula", m);
			request.setAttribute("matriculas", matriculas);
			request.setAttribute("disciplinas", disciplinas);
			
			if(cmd.equals("Listar Matriculas")) {
				RequestDispatcher rd = request.getRequestDispatcher("consultarMatricula.jsp");
				rd.forward(request, response);
			}
			if (cmd.equals("Listar Disciplinas") || cmd.equals("Matricular")) {
				RequestDispatcher rd = request.getRequestDispatcher("matricula.jsp");
				rd.forward(request, response);
			}
			if(cmd.equals("Listar Matriculas Ativas")){
				RequestDispatcher rd = request.getRequestDispatcher("aluno.jsp");
				rd.forward(request, response);
				return;
			}
			if(cmd.equals("Matricular-se")){
				RequestDispatcher rd = request.getRequestDispatcher("alunoMatricula.jsp");
				rd.forward(request, response);
				return;
			}
			if(cmd.equals("Listar Disciplinas Disponiveis")){
				RequestDispatcher rd = request.getRequestDispatcher("alunoMatricula.jsp");
				rd.forward(request, response);
				return;
			}
		}
	}

	private List<Matricula> listarMatricula(Matricula m) throws ClassNotFoundException, SQLException {
		List<Matricula> matriculas = new ArrayList<>();
		GenericDao gDao = new GenericDao();
		MatriculaDao mDao = new MatriculaDao(gDao);
		matriculas = mDao.listarMatriculas(m);
		return matriculas;
	}

	private String inserirMatricula(Matricula m) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		MatriculaDao mDao = new MatriculaDao(gDao);
		String saida = mDao.inserir(m);
		return saida;
	}

	private List<Disciplina> listarDisciplinas(Matricula m) throws ClassNotFoundException, SQLException {
		List<Disciplina> lista = new ArrayList<>();
		GenericDao gDao = new GenericDao();
		MatriculaDao mDao = new MatriculaDao(gDao);
		lista = mDao.listarDisciplinas(m);
		return lista;
	}

}
