package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.Kontakti;

public interface KontaktInterface {
	
	public ArrayList<Kontakti> getSviKontakti(int id) throws SQLException;

	public boolean DodajKontakt(Kontakti kontakt,int id) throws SQLException;

	public boolean ObrisiKontakt(Kontakti kontakt)throws SQLException;

	public ArrayList<Kontakti> PronadjiKontakt(String ime, int id) throws SQLException;

	public boolean AzurirajKontakt(Kontakti kontakt) throws SQLException;

	public Kontakti uzmiKontakt(int id) throws SQLException;
	
}

