package br.edu.unichristus.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.unichristus.backend.data.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{

}
