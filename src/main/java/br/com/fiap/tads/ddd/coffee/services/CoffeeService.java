package br.com.fiap.tads.ddd.coffee.services;

import br.com.fiap.tads.ddd.coffee.model.Coffee;
import br.com.fiap.tads.ddd.coffee.model.repository.CoffeeRepository;

public class CoffeeService {

	public static boolean existe(Long id) {
		return CoffeeRepository.findById(id) != null ? true : false;
	}

	public static Coffee findById(Long id) {
		return CoffeeRepository.findById(id);
	}

	public static Coffee save(Coffee cafe) {
		return CoffeeRepository.save(cafe);
	}

	public static Coffee update(Long id, Coffee cafe) {

		Coffee velho = CoffeeService.findById(id);
		Coffee novo = null;
		if (velho == null || velho.getId() != cafe.getId()) {
			return CoffeeService.save(cafe);
		}
		novo = CoffeeRepository.update(cafe);
		return novo;
	}

	public static boolean delete(Long coffeeId) {
		if (existe(coffeeId)) {
			return CoffeeRepository.delete(coffeeId);
		} else {
			return false;
		}

	}

}
