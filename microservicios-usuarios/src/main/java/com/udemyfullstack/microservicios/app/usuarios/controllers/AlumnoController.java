package com.udemyfullstack.microservicios.app.usuarios.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.udemyfullstack.microservicios.app.usuarios.services.AlumnoService;
import com.udemyfullstack.microservicios.generic.alumnos.models.entity.Alumno;
import com.udemyfullstack.microservicios.generic.controllers.GenericController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends GenericController<Alumno, AlumnoService> {

	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {
		Optional<Alumno> o = service.findById(id);
		if (o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}

		ByteArrayResource image = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

	// se quita el autowired aqui porque lo manejamos en el controlador generico
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Alumno> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());

		return ResponseEntity.ok(service.save(alumnoDb));
	}

	@GetMapping("/filtrar/{texto}")
	public ResponseEntity<?> filtrar(@PathVariable String texto) {
		return ResponseEntity.ok(service.finByNombreOrApellido(texto));
	}

	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile imagen) throws IOException {
		if (!imagen.isEmpty()) {
			alumno.setFoto(imagen.getBytes());
		}
		return super.crear(alumno, result);
	}

	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile imagen) throws IOException {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Alumno> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		if (!imagen.isEmpty()) {
			alumnoDb.setFoto(imagen.getBytes());
		}

		return ResponseEntity.ok(service.save(alumnoDb));
	}

}
