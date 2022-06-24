package SQL;

public class DeleteSQL {

	private static DeleteSQL deleteSQL;

	Connect con = Connect.getConnection();
	
	public void deleteMeal(int id) {
		String query = String.format("DELETE FROM meal WHERE ID = %d", id);
		con.executeUpdate(query);
	}
	
	public void deleteBeverage(int id) {
		String query = String.format("DELETE FROM beverage WHERE ID = %d", id);
		con.executeUpdate(query);
	}
	
	public static synchronized DeleteSQL getDelete() {
		// menggunakan pattern Singleton
		return deleteSQL = (deleteSQL == null) ? new DeleteSQL() : deleteSQL;
    }
}
