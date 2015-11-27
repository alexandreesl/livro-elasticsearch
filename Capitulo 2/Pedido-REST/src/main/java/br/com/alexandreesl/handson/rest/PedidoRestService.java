package br.com.alexandreesl.handson.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.alexandreesl.handson.domain.Pedido;
import br.com.alexandreesl.handson.domain.StatusPedido;
import br.com.alexandreesl.handson.dto.ItemPedidoDTO;

@Named
@Path("/")
public class PedidoRestService {
 
	private static List<Pedido> pedidosMock = new ArrayList<Pedido>();

	private static final Logger logger = LogManager.getLogger(PedidoRestService.class.getName());

	private static long contadorErroCaotico;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> buscarPedidos() {

		logger.info("foram buscados todos os pedidos!");

		return pedidosMock;

	}

	@GET
	@Path("pedido/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> buscarPedidosPorCliente(@PathParam("idCliente") long idCliente) {

		List<Pedido> pedidos = new ArrayList<Pedido>();

		for (Pedido pedido : pedidosMock) {

			if (pedido.getIdCliente() == idCliente)
				pedidos.add(pedido);
		}

		logger.info("cliente " + idCliente + " possui " + pedidos.size() + " pedidos");

		return pedidos;

	}

	@POST
	@Path("item/adiciona")
	@Consumes(MediaType.APPLICATION_JSON)
	public void adicionaItemPedido(ItemPedidoDTO item) {

		contadorErroCaotico++;

		if ((contadorErroCaotico) % 7 == 0) {
			throw new RuntimeException("Ocorreu um erro caótico!");
		}

		// se for pedido novo, cria, senao somente adiciona o item

		long idCliente = 0;

		boolean pedidoNovo = true;

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == item.getIdPedido()) {
				pedido.getItems().add(item.getItem());

				idCliente = pedido.getIdCliente();

				pedidoNovo = false;
			}

		}

		if (pedidoNovo) {
			Pedido pedido = new Pedido();

			idCliente = item.getIdCliente();
			pedido.setId(item.getIdPedido());
			pedido.setDataPedido(new Date());
			pedido.setIdCliente(item.getIdCliente());
			pedido.getItems().add(item.getItem());
			pedido.setStatus(StatusPedido.ABERTO);

			pedidosMock.add(pedido);

		}

		logger.info("pedido " + item.getIdPedido() + " do cliente " + idCliente + " adicionou o produto "
				+ item.getItem().getIdProduto());

	}

	@POST
	@Path("item/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeItemPedido(ItemPedidoDTO item) {

		long idCliente = 0;

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == item.getIdPedido()) {

				pedido.getItems().remove(item.getItem());

				idCliente = pedido.getIdCliente();

			}

		}

		logger.info("pedido " + item.getIdPedido() + " do cliente " + idCliente + " removeu o produto "
				+ item.getItem().getIdProduto());

	}

	@PUT
	@Path("pedido/{idPedido}")
	public void pagaPedido(@PathParam("idPedido") long idPedido) {

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == idPedido) {

				pedido.setStatus(StatusPedido.CONCLUIDO);

			}

		}

		logger.info("pedido " + idPedido + " efetivado");

	}

	@DELETE
	@Path("pedido/{idPedido}")
	public void cancelaPedido(@PathParam("idPedido") long idPedido) {

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == idPedido) {

				pedido.setStatus(StatusPedido.CANCELADO);

			}

		}

		logger.info("pedido " + idPedido + " cancelado");

	}

}
