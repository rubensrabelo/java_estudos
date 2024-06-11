package model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.entities.enums.OrderStatus;

public class Order {
	private LocalDateTime moment;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> items = new ArrayList<>();
	
	public Order() {
	}

	public Order(LocalDateTime moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void addItem(OrderItem item) {
		this.items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		this.items.remove(item);
	}
	
	public Double total() {
		double value = 0.0;
		
		for(OrderItem item : items)
			value += item.subTotal();
		
		return value;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		str.append("ORDER SUMMARY:\n");
		str.append("Order moment: " + fmt.format(getMoment()) + "\n");
		str.append("Order status: " + getStatus() + "\n");
		str.append(getClient());
		
		str.append("Order items: ");
		
		for(OrderItem item : items) {
			System.out.println(item);
		}
		
		str.append("Total price: S" + String.format("%.2f", total()) + "\n");
		
		return str.toString();
	}
}
