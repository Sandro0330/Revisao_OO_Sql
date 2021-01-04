package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("SELECT * FROM tb_order " + 
				"INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id " + 
				"INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id ");
			
		while (rs.next()) {
			
			Order order = instanciandoOrder(rs);
			
			System.out.println(order);
		}
	}
	
	//Criando um método para instanciar um objeto
	
	private static Order instanciandoOrder(ResultSet rs) throws SQLException  {
		Order order = new Order();
		order.setId(rs.getLong("Id"));
		order.setLatitude(rs.getDouble("latitude"));
		order.setLongitude(rs.getDouble("longitude"));
		order.setMoment(rs.getTimestamp("moment").toInstant());
		order.setStatus(OrderStatus.values()[rs.getInt("status")]); //convertendo um inteiro para orderStatus
		return order;
	
	}
	
	
	private static Product instanciandoProduto(ResultSet rs) throws SQLException  {
		Product p = new Product();
		p.setId(rs.getLong("Id"));
		p.setDescription(rs.getString("description"));
		p.setName(rs.getString("name"));
		p.setImageUri(rs.getString("image_uri"));
		p.setPrice(rs.getDouble("price"));
		return p;
	}
}
