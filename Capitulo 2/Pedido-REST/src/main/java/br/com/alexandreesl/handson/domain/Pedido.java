package br.com.alexandreesl.handson.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

	private long id;

	private Date dataPedido;

	private long idCliente;

	private List<ItemPedido> items;

	private StatusPedido status;

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemPedido> getItems() {

		if (items == null) {
			items = new ArrayList<ItemPedido>();
		}

		return items;
	}

	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object obj) {

		Pedido pedido = (Pedido) obj;

		return id == pedido.getId() ? true : false;
	}
}
