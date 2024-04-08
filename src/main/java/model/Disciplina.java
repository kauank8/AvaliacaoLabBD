package model;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Disciplina {
	 private int codigo;
	 private String nome;
	 private int aulas_semanais;
	 private String dia_semana;
	 private LocalTime hora_inicio;
	 private LocalTime hora_fim;
	 private Curso curso;
}
