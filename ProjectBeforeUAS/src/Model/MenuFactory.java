package Model;

public class MenuFactory {

	private static MenuFactory menuFactory;
	
	public Menu getMenu(String menuType) {
		if(menuType.isEmpty()) {
			return null;
		}
		if(menuType.equalsIgnoreCase("Meal")) {
			return new Meal();
		}else if(menuType.equalsIgnoreCase("Beverage")) {
			return new Beverage();
		}
		
		return null;
	}
	
	public static synchronized MenuFactory getFactory() {
		// menggunakan pattern Singleton
		return menuFactory = (menuFactory == null) ? new MenuFactory() : menuFactory;
    }
}
