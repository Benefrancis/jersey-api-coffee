package br.com.fiap.tads.ddd.coffee.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.tads.ddd.coffee.model.Coffee;
import br.com.fiap.tads.ddd.coffee.model.repository.CoffeeRepository;
import br.com.fiap.tads.ddd.coffee.services.CoffeeService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/coffee")
public class CoffeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Coffee> retorno = CoffeeRepository.findAll();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);
		return response.build();
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long coffeeId) {
		
		Coffee cafe = CoffeeRepository.findById(coffeeId);

		if (cafe != null) {
			ResponseBuilder response = Response.ok();
			response.entity(cafe);
			return response.build();
		} else {
			ResponseBuilder response = Response.status(404);
			return response.build();
		}

	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, @Valid Coffee cafe) {
		Coffee novo = null;
		novo = CoffeeService.update(id, cafe);
		return Response.ok(novo).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Valid Coffee coffee) {

		Coffee resp = CoffeeService.save(coffee);

		// @formatter:off
 		final URI coffeeUri = UriBuilder
				.fromResource(CoffeeResource.class)
				.path("/coffee/{id}")
				.build(resp.getId());
 		// @formatter:on

		ResponseBuilder response = Response.created(coffeeUri);
		response.entity(resp);

		return response.build();

	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long coffeeId) {
		if (CoffeeService.delete(coffeeId)) {
			ResponseBuilder response = Response.noContent();
			return response.build();
		} else {
			System.out.println("Não foi possível remover o coffee: " + coffeeId);
			ResponseBuilder response = Response.status(404);
			return response.build();
		}
	}

}
