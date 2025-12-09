package com.udemyfullstack.microservicios.app.examenes.controllers;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemyfullstack.microservicios.app.examenes.services.ExamenService;
import com.udemyfullstack.microservicios.generic.controllers.GenericController;
import com.udemyfullstack.microservicios.generic.examenes.models.entity.Examen;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/examenes")
public class ExamenController extends GenericController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @PathVariable Long id, BindingResult result, @RequestBody Examen examen) {
		
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Examen> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());

		examenDb.getPreguntas().stream().filter(pdb -> !examen.getPreguntas().contains(pdb))
				.collect(Collectors.toList()).forEach(p -> {
					examenDb.removePregunta(p);
				});

		examenDb.setPreguntas(examen.getPreguntas());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}

	@GetMapping("/filtrar/{text}")
	public ResponseEntity<?> filtrar(@PathVariable String text) {
		return ResponseEntity.ok(service.findByNombre(text));
	}

	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas() {
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
