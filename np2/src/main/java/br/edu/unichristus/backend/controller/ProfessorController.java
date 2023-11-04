package br.edu.unichristus.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.backend.data.dto.ProfessorDTO;
import br.edu.unichristus.backend.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService service;

	@Operation(summary = "Cadastra os dados de um professor | role: [ADMIN]", tags = "Professor")
	@PostMapping
	public ProfessorDTO create(@RequestBody ProfessorDTO professor) {
		return service.save(professor);
	}
	
	@Operation(summary = "Edita os dados de um professor | role: [ADMIN]", tags = "Professor")
	@PutMapping
	public ProfessorDTO update(@RequestBody ProfessorDTO professor) {
		return service.save(professor);
	}
	
	@Operation(summary = "Retorna os dados de um professor a partir do ID | role: [ADMIN]", tags = "Professor")
	@ApiResponses({ 
		@ApiResponse(responseCode = "200", description = "Professor retornado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Professor não encontrado"),
		@ApiResponse(responseCode = "500", description = "Erro interno no servidor - unichristus.backend.service.professor.notfound.exception") })
	@GetMapping("/{id}")
	public ProfessorDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@Operation(summary = "Deleta um professor | role: [ADMIN]", tags = "Professor")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@Operation(summary = "Lista dados de todos os professores | role: [ADMIN]", tags = "Professor")
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
}
