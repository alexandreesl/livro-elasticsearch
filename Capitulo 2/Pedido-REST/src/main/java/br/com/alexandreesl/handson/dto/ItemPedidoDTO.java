package br.com.alexandreesl.handson.dto;

import br.com.alexandreesl.handson.domain.ItemPedido;

public class ItemPedidoDTO {

	private long idPedido;

	private long idCliente;

	private ItemPedido item;

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public ItemPedido getItem() {
		return item;
	}

	public void setItem(ItemPedido item) {
		this.item = item;
	}

}
