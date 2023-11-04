package br.edu.unichristus.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.backend.converter.DozerConverter;
import br.edu.unichristus.backend.data.dto.ProfessorDTO;
import br.edu.unichristus.backend.data.model.Professor;
import br.edu.unichristus.backend.exception.CommonsException;
import br.edu.unichristus.backend.repository.ProfessorRepository;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository repository;
	
	public ProfessorDTO save(ProfessorDTO dto) {
		if(dto.getNome().length() > 100) {
			throw new CommonsException(HttpStatus.BAD_REQUEST, 
					"unichristus.backend.service.professor.badrequest.exception", 
					"O nome do professor excede o limite de 100 caracteres.");
		}
		
		if(!(repository.findByEmail(dto.getEmail()) == null)) {
			throw new CommonsException(HttpStatus.CONFLICT, 
					"unichristus.backend.service.professor.conflict.exception", 
					"O email informado já existe.");
		}
		
		if(dto.getId() != null) {
			this.findById(dto.getId());
		}
		
		var professor = DozerConverter.parseObject(dto, Professor.class);
		var professorDTOSaved = DozerConverter
				.parseObject(repository.save(professor), ProfessorDTO.class);
		
		return professorDTOSaved;
	}
	
	public void delete(Long id) {
		this.findById(id);
		repository.deleteById(id);
	}
	
	
	
	public ProfessorDTO findById(Long id) {
		var professor = repository.findById(id);
		if(professor == null || professor.isEmpty()) {
			throw new CommonsException(HttpStatus.NOT_FOUND, 
					"unichristus.backend.service.professor.notfound.exception",
					"Professor não encontrado.");
		}
		return DozerConverter.parseObject(professor.get(), ProfessorDTO.class);
	}
	
	public List<ProfessorDTO> findAll(){
		return DozerConverter.parseListObjects
				(repository.findAll(), ProfessorDTO.class);
	}
	
}
