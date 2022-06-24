package SQL;

import Model.*;

public class EditSQL {

	private static EditSQL editSQL;

	Connect con = Connect.getConnection();
	
	public void editMeal(Meal meal) {
		String query = String.format("UPDATE meal SET Name = '%s', Price = %d, Grams = %d WHERE ID = %d", 
				meal.getMenuName(), meal.getMenuPrice(), meal.getGrams(), meal.getID());
		con.executeUpdate(query);
	}
	
	public void editBeverage(Beverage bev) {
		String query = String.format("UPDATE beverage SET Name = '%s', Price = %d, Ml = %d WHERE ID = %d", 
				bev.getMenuName(), bev.getMenuPrice(), bev.getMl(), bev.getID());
		con.executeUpdate(query);
	}
	
	public static synchronized EditSQL getEdit() {
		// menggunakan pattern Singleton
		return editSQL = (editSQL == null) ? new EditSQL() : editSQL;
    }
}
