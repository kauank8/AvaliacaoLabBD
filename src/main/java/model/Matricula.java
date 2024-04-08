package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Matricula {
	 private Aluno aluno;
	 private int ano_semestre;
	 private int frequencia;
	 private double nota;
	 private String situacao;
	 private Disciplina disciplina;
}
