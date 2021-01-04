package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("select * from tb_product");
			
		while (rs.next()) {
			
			Product p = instanciandoProduto(rs);
			
			System.out.println(p);
		}
	}
	
	//Criando um método para instanciar um objeto
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
