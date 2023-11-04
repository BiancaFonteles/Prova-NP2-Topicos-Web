package br.edu.unichristus.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.backend.converter.DozerConverter;
import br.edu.unichristus.backend.data.dto.DisciplinaDTO;
import br.edu.unichristus.backend.data.model.Disciplina;
import br.edu.unichristus.backend.data.model.Professor;
import br.edu.unichristus.backend.exception.CommonsException;
import br.edu.unichristus.backend.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository repository;
	
	@Autowired
	private ProfessorService professorService;
	
	public DisciplinaDTO save(DisciplinaDTO dto) {
		var professor = DozerConverter.parseObject(professorService.findById(dto.getIdProfessor()), Professor.class);
		
		if(dto.getNome().length() > 100) {
			throw new CommonsException(HttpStatus.BAD_REQUEST, 
					"unichristus.backend.service.disciplina.badrequest.exception", 
					"O nome da disciplina excede o limite de 100 caracteres.");
		}
		
		if(dto.getDescricao().length() > 100) {
			throw new CommonsException(HttpStatus.BAD_REQUEST, 
					"unichristus.backend.service.disciplina.badrequest.exception", 
					"A descricao da disciplina excede o limite de 100 caracteres.");
		}
		
		if(dto.getId() != null) {
			this.findById(dto.getId());
		}
		
		var disciplina = DozerConverter.parseObject(dto, Disciplina.class);
		disciplina.setProfessor_responsavel(professor);
		
		var disciplinaDTOSaved = DozerConverter
				.parseObject(repository.save(disciplina), DisciplinaDTO.class);
		disciplinaDTOSaved.setIdProfessor(professor.getId());
		
		return disciplinaDTOSaved;
	}
	
	public void delete(Long id) {
		this.findById(id);
		repository.deleteById(id);
	}
	
	
	
	public DisciplinaDTO findById(Long id) {
		var disciplina = repository.findById(id);
		if(disciplina == null || disciplina.isEmpty()) {
			throw new CommonsException(HttpStatus.NOT_FOUND, 
					"unichristus.backend.service.disciplina.notfound.exception",
					"Disciplina não encontrada.");
		}
		return DozerConverter.parseObject(disciplina.get(), DisciplinaDTO.class);
	}
	
	public List<DisciplinaDTO> findAll(){
		return DozerConverter.parseListObjects
				(repository.findAll(), DisciplinaDTO.class);
	}
	
}
