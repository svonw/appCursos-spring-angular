package com.udemyfullstack.microservicios.generic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public class GenericServiceImpl<E, R extends CrudRepository<E, Long> & PagingAndSortingRepository<E, Long>>
		implements GenericService<E> {
	@Autowired
	protected R repository;
	// es protected para poder usarlo en la clase que hereda este servicio

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {

		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {

		return repository.findById(id);
	}

	@Override
	@Transactional
	public E save(E entity) {

		return repository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {

		return repository.findAll(pageable);
	}

}
