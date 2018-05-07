package CUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.KontaktImplementation;
import dto.Kontakti;


public class UserMenu {

	public static int UserCUIMenu(Scanner input) {
		// Scanner input= new Scanner(System.in);
		char choice = '1';
		ArrayList<Character> validInputs = new ArrayList<>();

		validInputs.add('1');
		validInputs.add('2');
		validInputs.add('3');
		validInputs.add('4');
		validInputs.add('5');
		validInputs.add('6');

		do {
			if (!validInputs.contains(choice)) {
				System.out.println("\nPogresan unos. Biraj ponovo.\n");
			}
			System.out.println("\nIzbornik:\n");
			System.out.println("*************************************************");
			System.out.print("1. Izlistaj sve kontakte iz svog imenika \n");
			System.out.print("2. Dodaj novi kontakt u imenik \n");
			System.out.print("3. Pronaði kontakt po imenu \n");
			System.out.print("4. Edituj postojeæi kontakt u imeniku \n");
			System.out.print("5. Obriši kontakt iz imenika \n");
			System.out.print("6. Odjava \n");
			System.out.println("*************************************************");
			choice = input.next().charAt(0);

		} while (!validInputs.contains(choice));

		// input.close();

		return ((int) choice - 48);
	}

	public static void UserChoice(int i, int userID, Scanner input) {
		switch (i) {
		case 1:
			list(userID);
			break;
		case 2:
			Add(userID, input);
			break;
		case 3:
			input.nextLine();
			System.out.print("Unesite ime koje tražite: ");
			String ime = input.nextLine();
			find(ime,userID);
			break;
		case 4:
			edit(userID, input);
			break;
		case 5:
			delete(userID,input);
			break;
		case 6:
			
			break;
		default:
			break;
		}
	}
	
	public static void Add(int userID, Scanner input){
		KontaktImplementation kontaktDAO = new KontaktImplementation();

		System.out.println();
		System.out.println("Unesite podatke");
		System.out.println("----------------------------------------------");
		input.nextLine();
		System.out.print("Ime: ");
		String ime = input.nextLine();
		System.out.print("Prezime: ");
		String prezime = input.nextLine();
		System.out.print("broj telefona: ");
		String br_tel = input.nextLine();
		System.out.print("e-mail: ");
		String email = input.nextLine();
		System.out.print("adresa: ");
		String adresa = input.nextLine();
		System.out.print("grad: ");
		String grad = input.nextLine();
		System.out.println("----------------------------------------------");

		Kontakti kontakt = new Kontakti(userID, ime, prezime, br_tel, email, adresa, grad);
		try {

			if (kontaktDAO.DodajKontakt(kontakt,userID)) {
				//welcome(user);
				System.out.println("Kontakt je upisan u bazu!");
				System.out.println(kontakt);
				//kontaktDAO.IspisiKontakt(kontakt.getId());
				// mainMenu(input);
			}
			;

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public static void list(int userID){
		KontaktImplementation kontaktDAO = new KontaktImplementation();

		ArrayList<Kontakti> lista;
		try {
			lista = kontaktDAO.getSviKontakti(userID);
			int i=0;
			for (Kontakti kontakt : lista) {
				i++;
				System.out.print(i + ". ");
				System.out.println(kontakt);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void find(String ime,int userID){
		KontaktImplementation kontaktDAO = new KontaktImplementation();

		ArrayList<Kontakti> lista;
		try {
			lista = kontaktDAO.PronadjiKontakt(ime, userID);
			int i=0;
			for (Kontakti kontakt : lista) {
				i++;
				System.out.print(i + ". ");
				System.out.println(kontakt);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(int userID, Scanner input){
		KontaktImplementation kontaktDAO = new KontaktImplementation();
		Kontakti kontakt = new Kontakti();
		try {
			list(userID);
			System.out.println("----------------------------------------------");
			System.out.println("Napišite ID kontakta kojeg zelite izbrisati: ");
			int kontaktID = input.nextInt();
			kontakt = kontaktDAO.uzmiKontakt(kontaktID);
			System.out.println(kontakt);
			kontaktDAO.ObrisiKontakt(kontakt);
			System.out.println("----------------------------------------------");
			list(userID);
			System.out.println("----------------------------------------------");
			System.out.println("Kontakt je izbrisan!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void edit(int userID, Scanner input){
		KontaktImplementation kontaktDAO = new KontaktImplementation();
		Kontakti kontakt = new Kontakti();
		try {
			list(userID);
			System.out.println("----------------------------------------------");
			System.out.println("Napišite ID kontakta kojeg zelite editovati: ");
			int kontaktID = input.nextInt();
			kontakt = kontaktDAO.uzmiKontakt(kontaktID);
			System.out.println(kontakt);
		
			input.nextLine();
			kontakt = azuriraj(userID, kontakt, input);
			System.out.println(kontakt);
			if (kontaktDAO.AzurirajKontakt(kontakt)) {
				System.out.println("----------------------------------------------");
				list(userID);
				System.out.println("----------------------------------------------");
				System.out.println("Kontakt je azuriran!");
			} else {
				System.out.println("KOntakt NIJE azuriran!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Kontakti azuriraj(int userID, Kontakti kontakt, Scanner input){
		System.out.println();
		System.out.println("Promijenite podatke koje zelite podatke (a koje ne zelite mijenjati prepisite)");
		System.out.println("----------------------------------------------");
		System.out.print("Ime (" + kontakt.getIme() + ") :");
		String ime = input.nextLine();
		System.out.print("Prezime (" + kontakt.getPrezime() + ") :");
		String prezime = input.nextLine();
		System.out.print("Broj telefona (" + kontakt.getBrTel() + ") :");
		String brTel = input.nextLine();
		System.out.print("e-mail adresa (" + kontakt.getEmail() + ") :");
		String email = input.nextLine();
		System.out.print("Adresa (" + kontakt.getAdresa() + ") :");
		String adresa = input.nextLine();
		System.out.print("Grad (" + kontakt.getGrad() + ") :");
		String grad = input.nextLine();
		System.out.println("----------------------------------------------");

		Kontakti editKontakt = new Kontakti(kontakt.getId(), userID, ime, prezime, brTel, email, adresa, grad);
		return editKontakt;
	}
}
