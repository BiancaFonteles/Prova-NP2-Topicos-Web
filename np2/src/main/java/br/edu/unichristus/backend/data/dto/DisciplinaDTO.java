package br.edu.unichristus.backend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {
	private Long id;
	private String nome;
	private String descricao;
	
	private Long idProfessor;
}
