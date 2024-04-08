package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Curso;
import model.Telefone;
import persistence.AlunoDao;
import persistence.GenericDao;

@WebServlet("/aluno")
public class AlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlunoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Entrada
		String cmd = request.getParameter("botao");
		String cod_curso = request.getParameter("codigo_curso");
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String nome_social = request.getParameter("nome_social");
		String data_nasc = request.getParameter("data_nasc");
		String conclusao = request.getParameter("conclusao_segundo_grau");
		String email_pessoal = request.getParameter("email_pessoal");
		String email_corporativo = request.getParameter("email_corporativo");
		String instituicao = request.getParameter("instituicao_segundo_grau");
		String pontuacao = request.getParameter("pontuacao_vestibular");
		String posicao = request.getParameter("posicao_vestibular");
		String ra = "";
		
		// Retorno
		String saida = "";
		String erro = "";
		Aluno a = new Aluno();
		List<Aluno> alunos = new ArrayList<>();
		
		if(cmd.contains("Buscar") || cmd.contains("Alterar")) {
			ra = request.getParameter("ra");
			a.setRa(ra);
		}
		if (cmd.contains("Cadastrar")|| cmd.contains("Alterar")){
			if( cpf.isEmpty() || nome.isEmpty() || data_nasc.isEmpty() || conclusao.isEmpty() || 
					email_pessoal.isEmpty() || email_corporativo.isEmpty() || instituicao.isEmpty() || pontuacao.isEmpty()
					|| posicao.isEmpty() || cod_curso.isEmpty()) {
						saida = "Todos campos são obrigatórios, com excessão de nome social";
					}
			else {
			a.setCpf(cpf);
			a.setNome(nome);
			a.setNome_social(nome_social);
			a.setData_nasc(LocalDate.parse(data_nasc));
			a.setConclusao_segundo_grau(LocalDate.parse(conclusao));
			a.setEmail_pessoal(email_pessoal);
			a.setEmail_corporativo(email_corporativo);
			a.setInstituicao_segundo_grau(instituicao);
			a.setPontuacao_vestibular(Double.parseDouble(pontuacao));
			a.setPosicao_vestibular(Integer.parseInt(posicao));
			Curso c = new Curso();
			c.setCodigo(Integer.parseInt(cod_curso));
			a.setCurso(c);
			}
		}
		
		try {
			if (cmd.contains("Cadastrar")) {
				if( cpf.isEmpty() || nome.isEmpty() || data_nasc.isEmpty() || conclusao.isEmpty() || 
				email_pessoal.isEmpty() || email_corporativo.isEmpty() || instituicao.isEmpty() || pontuacao.isEmpty()
				|| posicao.isEmpty()) {
					saida = "Todos campos são obrigatórios, com excessão de nome social";
				}
				else {
					a.setRa(geraRa());
					saida = cadastrarAluno(a);
					if(saida.contains("Aluno cadastrado com sucesso")){
						saida += ", Ra Gerado: " + a.getRa();
					}
					a = null;		
				}
			}
			if(cmd.contains("Buscar")){
				a = buscarAluno(a);
			}
			if (cmd.contains("Alterar")) {
				if(ra.isEmpty() || cpf.isEmpty() || nome.isEmpty() || data_nasc.isEmpty() || conclusao.isEmpty() || 
				email_pessoal.isEmpty() || email_corporativo.isEmpty() || instituicao.isEmpty() || pontuacao.isEmpty()
				|| posicao.isEmpty() || cod_curso.isEmpty()) {
					saida = "Todos campos são obrigatórios, com excessão de nome social";
				}
				else {
					saida=alterarAluno(a);
					if(saida == null) {
						saida = "CPF e Email corporativo não podem ser alterado";
					}
					a = null;
				}
			}
			if (cmd.contains("Listar")) {
				alunos=listarAlunos();
			}
			
		}
		catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("aluno", a);
			request.setAttribute("alunos", alunos);
			
			if(cmd.contains("Cadastrar") || cmd.contains("Gerar Ra")) {
				RequestDispatcher rd = request.getRequestDispatcher("secretaria.jsp");
				rd.forward(request, response);}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("visualizarAluno.jsp");
				rd.forward(request, response);
			}
		}
		
	}

	private List<Aluno> listarAlunos() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		List<Aluno> alunos = aDao.listar();
		return alunos;
	}

	private String alterarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		String saida = aDao.atualizar(a);
		return saida;
	}

	private Aluno buscarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		a = aDao.consultar(a);
		return a;
	}

	private String geraRa() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		String saida = aDao.GeraRa();
		return saida;
	}

	private String cadastrarAluno(Aluno a) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		AlunoDao aDao = new AlunoDao(gDao);
		String saida = aDao.inserir(a);
		return saida;
	}
	
}
