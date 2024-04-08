package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Disciplina;
import model.Matricula;

public interface IMatricula {
	public String inserir(Matricula m) throws SQLException, ClassNotFoundException;
	public List<Disciplina> listarDisciplinas(Matricula m) throws SQLException, ClassNotFoundException;
	public List<Matricula> listarMatriculas(Matricula m) throws SQLException, ClassNotFoundException;
}
