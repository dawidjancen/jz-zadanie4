package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Product;

@Path("/products")
@Stateless
public class ProductResources {
	@PersistenceContext
	EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}
	
	@GET
	@Path("/name")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByName(@QueryParam("name") String name) {
		return em.createNamedQuery("product.name", Product.class)
			.setParameter("name", name)
			.getResultList();
	}
	
	@GET
	@Path("/category")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByCategory(@QueryParam("category") String category) {
		return em.createNamedQuery("product.category", Product.class)
			.setParameter("category", category)
			.getResultList();
	}
	
	@GET
	@Path("/price_range")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByPriceRange(@QueryParam("min") Double min, @QueryParam("max") Double max) {
		return em.createNamedQuery("product.priceRange", Product.class)
			.setParameter("min", min)
			.setParameter("max", max)
			.getResultList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Product product) {
		em.persist(product);

		return Response.ok(product.getId()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Product result = em.createNamedQuery("product.id", Product.class)
			.setParameter("productId", id)
			.getSingleResult();
		
		if (result == null) {
			return Response.status(404).build();
		}
		
		return Response.ok(result).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product product) {
		Product result = em.createNamedQuery("product.id", Product.class)
			.setParameter("productId", id)
			.getSingleResult();
		
		if (result == null) {
			return Response.status(404).build();
		}

		if (product.getName() != null) {
			result.setName(product.getName());
		}
		if (product.getDescription() != null) {
			result.setDescription(product.getDescription());
		}
		if (product.getPrice() != null) {
			result.setPrice(product.getPrice());
		}
		if (product.getCategory() != null) {
			result.setCategory(product.getCategory());
		}

		em.persist(result);

		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Product result = em.createNamedQuery("product.id", Product.class)
			.setParameter("productId", id)
			.getSingleResult();
		
		if (result == null) {
			return Response.status(404).build();
		}
		
		em.remove(result);
		
		return Response.ok().build();
	}
}

