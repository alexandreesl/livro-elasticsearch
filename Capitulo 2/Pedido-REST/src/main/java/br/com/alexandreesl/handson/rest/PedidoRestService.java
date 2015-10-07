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

import br.com.alexandreesl.handson.domain.Pedido;
import br.com.alexandreesl.handson.domain.StatusPedido;
import br.com.alexandreesl.handson.dto.ItemPedidoDTO;

@Named
@Path("/")
public class PedidoRestService {

	private List<Pedido> pedidosMock;

	@GET
	@Path("pedido")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> buscarPedidosPorCliente(@PathParam("idCliente") long idCliente) {

		List<Pedido> pedidos = new ArrayList<Pedido>();

		for (Pedido pedido : pedidosMock) {

			if (pedido.getIdCliente() == idCliente)
				pedidos.add(pedido);
		}

		return pedidos;

	}

	@POST
	@Path("item/adiciona")
	@Consumes(MediaType.APPLICATION_JSON)
	public void adicionaItemPedido(ItemPedidoDTO item) {

		// se for pedido novo, cria, senao somente adiciona o item

		boolean pedidoNovo = true;

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == item.getIdPedido()) {
				pedido.getItems().add(item.getItem());

				pedidoNovo = false;
			}

		}

		if (pedidoNovo) {
			Pedido pedido = new Pedido();

			pedido.setId(item.getIdPedido());
			pedido.setDataPedido(new Date());
			pedido.setIdCliente(item.getIdCliente());
			pedido.getItems().add(item.getItem());
			pedido.setStatus(StatusPedido.ABERTO);

			pedidosMock.add(pedido);

		}

	}

	@POST
	@Path("item/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeItemPedido(ItemPedidoDTO item) {

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == item.getIdPedido()) {
				pedido.getItems().remove(item.getItem());

			}

		}

	}

	@PUT
	@Path("pedido")
	public void pagaPedido(@PathParam("idPedido") long idPedido) {

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == idPedido) {

				pedido.setStatus(StatusPedido.CONCLUIDO);

			}

		}

	}

	@DELETE
	@Path("pedido")
	public void cancelaPedido(@PathParam("idPedido") long idPedido) {

		for (Pedido pedido : pedidosMock) {

			if (pedido.getId() == idPedido) {

				pedido.setStatus(StatusPedido.CANCELADO);

			}

		}

	}

}
