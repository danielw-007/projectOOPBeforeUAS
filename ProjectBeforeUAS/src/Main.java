import java.util.ArrayList;
import java.util.Scanner;

import Model.*;
import SQL.*;

// Daftar anggota kelompok :
// 2440014420 - Daniel Widjaja
// 2440015562 - Erika Elaine
// 2440021205 - Yeremia Sutanto A.P
// 2440103514 - Falencia Chandra

// Design Pattern yang digunakan :
//	Singleton :
//		- Connect.java
//		- LoadSQL.java
//		- InsertSQL.java
//		- EditSQL.java
//		- DeleteSQL.java
//		- MenuFactory.java
//		- dan implementasinya ada di Main.java
//	Factory :
//		- MenuFactory.java
//		- dan implementasinya ada di Main.java dan LoadSQL.java


public class Main {
	
	Scanner sc = new Scanner(System.in);
	// pembuatan class menggunakan Singleton
	MenuFactory menuFactory = MenuFactory.getFactory();
	InsertSQL insert = InsertSQL.getInsert();
	LoadSQL load = LoadSQL.getLoad();
	EditSQL edit = EditSQL.getEdit();
	DeleteSQL delete = DeleteSQL.getDelete();
	
	public Main() {
		int menu = -1;
		do {
			clr();
			System.out.println("Welcome to SteaknBeer");
			System.out.println("1. Add Menu");
			System.out.println("2. View All Menu");
			System.out.println("3. Edit Menu");
			System.out.println("4. Delete Menu");
			System.out.println("5. Exit");
			System.out.print(">> ");
			do {
				try {
					menu = sc.nextInt();
				} catch (Exception e) {
					menu = -1;
				}
				sc.nextLine();
			} while (menu < 1 || menu > 5);
			
			switch (menu) {
			case 1:
				addMenu();
				break;
			case 2:
				viewAllMenu();
				System.out.print("Press Enter to continue...");
				sc.nextLine();
				break;
			case 3:
				editMenu();
				break;
			case 4:
				deleteMenu();
				break;
			case 5:
				System.out.println("Thankyou for using our program!");
				break;
			}
			
		} while (menu != 5);
	}

	private void addMenu() {
		String menuType = "";
		String menuName = "";
		int menuPrice = -1;
		
		do {
			System.out.print("Input the type of menu you want to add ['Meal' or 'Beverage' case insensitive]: ");
			menuType = sc.nextLine();
		} while (!menuType.equalsIgnoreCase("Meal") && !menuType.equalsIgnoreCase("Beverage"));
		
		do {
			System.out.print("Input the name of your menu: ");
			menuName = sc.nextLine();
		} while (menuName.isEmpty());
		
		do {
			System.out.print("Input the price of your menu [must be more than 0]: ");
			try {
				menuPrice = sc.nextInt();
			} catch (Exception e) {
				menuPrice = -1;
			}
			sc.nextLine();
		} while (menuPrice < 1);

		if(menuType.equalsIgnoreCase("Meal")) {
			int grams = -1;
			do {
				System.out.print("Input the size of your meal [in grams and must be more than 0]: ");
				try {
					grams = sc.nextInt();
				} catch (Exception e) {
					grams = -1;
				}
				sc.nextLine();
			} while (grams < 1);
			// Menggunakan design pattern Factory
			Meal meal = (Meal) menuFactory.getMenu("Meal");
			meal.setMenuName(menuName);
			meal.setMenuPrice(menuPrice);
			meal.setGrams(grams);
			insert.insertMeal(meal);
		}else if(menuType.equalsIgnoreCase("Beverage")) {
			int ml = -1;
			do {
				System.out.print("Input the size of your beverage [in mililitres and must be more than 0]: ");
				try {
					ml = sc.nextInt();
				} catch (Exception e) {
					ml = -1;
				}
				sc.nextLine();
			} while (ml < 1);
			// Menggunakan design pattern Factory
			Beverage bev = (Beverage) menuFactory.getMenu("Beverage");
			bev.setMenuName(menuName);
			bev.setMenuPrice(menuPrice);
			bev.setMl(ml);
			insert.insertBeverage(bev);
		}
		System.out.println("Insert successful!");
		System.out.println("Press Enter to continue...");
		sc.nextLine();
	}
	
	private void viewAllMenu() {
		ArrayList<Menu> list = load.getAllMenu();
		if(list.isEmpty()) {
			System.out.println("Menu is empty!");
		}else {
			int i = 1;
			System.out.println("==========================================================================");
			System.out.println("| No. | Menu Type | Menu Name             | Menu Price     | Size        |");
			System.out.println("==========================================================================");
			for (Menu m : list) {
				if(m instanceof Meal) {
					System.out.format("| %-3d | Meal      | %-21s | Rp%-12d | %-11s |\n", i++, m.getMenuName(), m.getMenuPrice(), ((Meal)m).getGrams()+" grams");
				}else if(m instanceof Beverage) {
					System.out.format("| %-3d | Beverage  | %-21s | Rp%-12d | %-11s |\n", i++, m.getMenuName(), m.getMenuPrice(), ((Beverage)m).getMl()+" ml");
				}
			}
			System.out.println("==========================================================================");
		}
	}
	
	private void editMenu() {
		viewAllMenu();
		ArrayList<Menu> list = load.getAllMenu();
		if(!list.isEmpty()) {
			int choose = -1;
			
			do {
				System.out.print("Input the number of menu you want to edit [1 - "+list.size()+"]: ");
				try {
					choose = sc.nextInt();
				} catch (Exception e) {
					choose = -1;
				}
				sc.nextLine();
			} while (choose < 1 || choose > list.size());
			
			
			String name = "";
			int price = -1;
			do {
				System.out.print("Input new name for your menu: ");
				name = sc.nextLine();
			} while (name.isEmpty());
			
			do {
				System.out.print("Input new price for your menu [must be more than 0]: ");
				try {
					price = sc.nextInt();
				} catch (Exception e) {
					price = -1;
				}
				sc.nextLine();
			} while (price < 1);

			if(list.get(choose - 1) instanceof Meal) {
				int grams = -1;
				do {
					System.out.print("Input new size for your meal [in grams and must be more than 0]: ");
					try {
						grams = sc.nextInt();
					} catch (Exception e) {
						grams = -1;
					}
					sc.nextLine();
				} while (grams < 1);
				Meal meal = (Meal) list.get(choose - 1);
				meal.setMenuName(name);
				meal.setMenuPrice(price);
				meal.setGrams(grams);
				edit.editMeal(meal);
			}else if(list.get(choose - 1) instanceof Beverage) {
				int ml = -1;
				do {
					System.out.print("Input new size for your beverage [in mililitres and must be more than 0]: ");
					try {
						ml = sc.nextInt();
					} catch (Exception e) {
						ml = -1;
					}
					sc.nextLine();
				} while (ml < 1);
				Beverage bev = (Beverage) list.get(choose - 1);
				bev.setMenuName(name);
				bev.setMenuPrice(price);
				bev.setMl(ml);
				edit.editBeverage(bev);
			}
			System.out.println("Update successful!");
		}
		System.out.println("Press Enter to continue...");
		sc.nextLine();
	}
	
	private void deleteMenu() {
		viewAllMenu();
		ArrayList<Menu> list = load.getAllMenu();
		if(!list.isEmpty()) {
			int choose = -1;
			do {
				System.out.print("Input the number of menu you want to delete [1 - "+list.size()+"]: ");
				try {
					choose = sc.nextInt();
				} catch (Exception e) {
					choose = -1;
				}
				sc.nextLine();
			} while (choose < 1 || choose > list.size());
			
			if(list.get(choose - 1) instanceof Meal) {
				delete.deleteMeal(list.get(choose - 1).getID());
			}else if(list.get(choose - 1) instanceof Beverage) {
				delete.deleteBeverage(list.get(choose - 1).getID());
			}
			System.out.println("Delete successful!");
		}
		System.out.println("Press Enter to continue...");
		sc.nextLine();
	}

	private void clr() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
