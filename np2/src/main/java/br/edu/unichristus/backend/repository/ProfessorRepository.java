package br.edu.unichristus.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unichristus.backend.data.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	Professor findByEmail(String email);
}
