package CUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.UserImplementation;
import dto.User;

public class LogIn {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int choice = 1;
		int i;
		

		do {
			choice = logInMenu(input);
			switch (choice) {
			case 1:
				User validUser = login(input);

				if (validUser == null) {
					System.out.println("Sorry, you are not a registered user! Please sign up first");
				} else {
					welcome(validUser);
					do {
						i = UserMenu.UserCUIMenu(input);
						
						UserMenu.UserChoice(i, validUser.getId(), input);
					} while (i != 6);
				}
				break;
			case 2:

				signup(input);
				break;
			case 3:
				exit();
				break;
			}
			
		} while (choice != 3);

		input.close();

	}

	public static int logInMenu(Scanner input) {

		char choice = '1';
		ArrayList<Character> validInputs = new ArrayList<>();

		validInputs.add('1');
		validInputs.add('2');
		validInputs.add('3');

		do {
			if (!validInputs.contains(choice)) {
				System.out.println("\nWrong input. Choose again.\n");
			}
			System.out.println("\nChoose an option:\n");
			System.out.println("*************************************************");
			System.out.print("1. Log In \n");
			System.out.print("2. Sign Up \n");
			System.out.print("3. Exit \n");
			System.out.println("*************************************************");
			choice = input.next().charAt(0);

		} while (!validInputs.contains(choice));

		return ((int) choice - 48);
	}

	public static User login(Scanner input) {
		System.out.println("----------------------------------------------");
		System.out.print("username: ");
		String username = input.next();
		System.out.print("password: ");
		String passwrd = input.next();
		System.out.println("----------------------------------------------");

		UserImplementation user = new UserImplementation();

		User validUser = user.validateUser(username, passwrd);

		return validUser;

	}

	public static void welcome(User validUser) {
		System.out.println("Welcome " + validUser.getUsername());
		System.out.println("***********************************");
		System.out.println(" First name: " + validUser.getFirstName());
		System.out.println(" Last  name: " + validUser.getLastName());
		System.out.println("   username: " + validUser.getUsername());
		System.out.println("   password: " + validUser.getPassword());
		System.out.println("***********************************");
	}

	public static void signup(Scanner input) {
		UserImplementation userDAO = new UserImplementation();

		System.out.println();
		System.out.println("Please fill the fields");
		System.out.println("----------------------------------------------");
		System.out.print("First name: ");
		String firstname = input.nextLine();
		System.out.print("Last name: ");
		String lastname = input.nextLine();
		System.out.print("username: ");
		String username = input.nextLine();
		System.out.print("password: ");
		String password = input.nextLine();
		System.out.println("----------------------------------------------");

		User user = new User(firstname, lastname, username, password);
		try {

			if (userDAO.register(user)) {
				welcome(user);
				System.out.println("You are succesfully registered!");
				// mainMenu(input);
			}
			;

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void exit() {
		System.out.println("---------------------------------------------");
		System.out.println("You are succesfully loged out!");
	}
}
