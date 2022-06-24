package SQL;

import Model.*;

public class InsertSQL {
	
	private static InsertSQL insertSQL;

	Connect con = Connect.getConnection();
	
	public void insertMeal(Meal meal) {
		String query = String.format("INSERT INTO meal VALUES (NULL, '%s', %d, %d)", 
				meal.getMenuName(), meal.getMenuPrice(), meal.getGrams());
		
		con.executeUpdate(query);
	}
	
	public void insertBeverage(Beverage bev) {
		String query = String.format("INSERT INTO beverage VALUES (NULL, '%s', %d, %d)", 
				bev.getMenuName(), bev.getMenuPrice(), bev.getMl());
		
		con.executeUpdate(query);
	}
	
	public static synchronized InsertSQL getInsert() {
		// menggunakan pattern Singleton
		return insertSQL = (insertSQL == null) ? new InsertSQL() : insertSQL;
    }
}
