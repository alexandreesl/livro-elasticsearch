package br.com.alexandreesl.handson.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.alexandreesl.handson.domain.Cliente;

@Named
@Path("/")
public class ClienteRestService {

	private static final Logger logger = LogManager.getLogger(ClienteRestService.class.getName());

	private static Map<Long, Cliente> clientes = new HashMap<Long, Cliente>();

	private static long contadorErroCaotico;

	static {

		Cliente cliente1 = new Cliente();
		cliente1.setId(1);
		cliente1.setNome("Cliente 1");
		cliente1.setEmail("customer1@gmail.com");

		Cliente cliente2 = new Cliente();
		cliente2.setId(2);
		cliente2.setNome("Cliente 2");
		cliente2.setEmail("customer2@gmail.com");

		Cliente cliente3 = new Cliente();
		cliente3.setId(3);
		cliente3.setNome("Cliente 3");
		cliente3.setEmail("customer3@gmail.com");

		Cliente cliente4 = new Cliente();
		cliente4.setId(4);
		cliente4.setNome("Cliente 4");
		cliente4.setEmail("customer4@gmail.com");

		Cliente cliente5 = new Cliente();
		cliente5.setId(5);
		cliente5.setNome("Cliente 5");
		cliente5.setEmail("customer5@gmail.com");

		clientes.put(cliente1.getId(), cliente1);
		clientes.put(cliente2.getId(), cliente2);
		clientes.put(cliente3.getId(), cliente3);
		clientes.put(cliente4.getId(), cliente4);
		clientes.put(cliente5.getId(), cliente5);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Cliente> getClientes() {

		logger.info("Foram buscados " + clientes.values().size() + " clientes");

		return clientes.values();
	}

	@GET
	@Path("cliente")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente getCliente(@QueryParam("id") long id) {

		Cliente cli = null;

		for (Cliente c : clientes.values()) {

			if (c.getId() == id)
				cli = c;

		}

		logger.info("foi buscado o cliente " + cli.getNome());

		return cli;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCliente(Cliente cliente) {

		logger.warn("O cliente " + cliente.getId() + " foi inserido!");

		clientes.put(cliente.getId(), cliente);

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void mergeCliente(Cliente cliente) {

		contadorErroCaotico++;

		if ((contadorErroCaotico) % 7 == 0) {
			throw new RuntimeException("Ocorreu um erro caótico!");
		}


		logger.info("O cliente " + cliente.getId() + " foi alterado!");

		Cliente temp = clientes.get(cliente.getId());

		temp.setNome(cliente.getNome());
		temp.setEmail(cliente.getEmail());

	}

	@DELETE
	public void deleteCliente(@QueryParam("id") long id) {

		logger.info("O cliente " + id + " foi excluido!");

		clientes.remove(id);
	}

}
