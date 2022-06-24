package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.*;

public class LoadSQL {

	private static LoadSQL loadSQL;

	Connect con = Connect.getConnection();
	MenuFactory factory = MenuFactory.getFactory();
	
	public ArrayList<Menu> getAllMenu(){
		ArrayList<Menu> list = new ArrayList<>();
		
		String mealQuery = "SELECT * FROM meal";
		ResultSet mealRes = con.executeQuery(mealQuery);
		try {
			while(mealRes.next()) {
				// Menggunakan design pattern Factory
				Meal m = (Meal) factory.getMenu("Meal");
				m.setID(mealRes.getInt("ID"));
				m.setMenuName(mealRes.getString("Name"));
				m.setMenuPrice(mealRes.getInt("Price"));
				m.setGrams(mealRes.getInt("Grams"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String bevQuery = "SELECT * FROM beverage";
		ResultSet bevRes = con.executeQuery(bevQuery);
		try {
			while(bevRes.next()) {
				// Menggunakan design pattern Factory
				Beverage b = (Beverage) factory.getMenu("Beverage");
				b.setID(bevRes.getInt("ID"));
				b.setMenuName(bevRes.getString("Name"));
				b.setMenuPrice(bevRes.getInt("Price"));
				b.setMl(bevRes.getInt("Ml"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static synchronized LoadSQL getLoad() {
		// menggunakan pattern Singleton
		return loadSQL = (loadSQL == null) ? new LoadSQL() : loadSQL;
    }
}
