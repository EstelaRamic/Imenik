package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import dto.Kontakti;


	

	public class KontaktImplementation implements KontaktInterface {

		Connection connection = ConnectionManager.getInstance().getConnection();

		ArrayList<Kontakti> list = new ArrayList<>();

		@Override
		public ArrayList<Kontakti> getSviKontakti(int userId) throws SQLException {

			Kontakti kontakt = null;
			String query = "SELECT * FROM imenik.kontakti where userId = ?;";

			ResultSet rs = null;
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, userId);
				rs = statement.executeQuery();
				while (rs.next()) {

					kontakt = new Kontakti();
					kontakt.setId(rs.getInt("id"));
					kontakt.setUserId(rs.getInt("userid"));
					kontakt.setIme(rs.getString("ime"));
					kontakt.setPrezime(rs.getString("prezime"));
					kontakt.setEmail(rs.getString("e_mail"));
					kontakt.setBrTel(rs.getString("broj_telefona"));
					kontakt.setAdresa(rs.getString("adresa"));
					kontakt.setGrad(rs.getString("grad"));

					list.add(kontakt);

				}
			}
			rs.close();
			return list;

		}

		@Override
		public boolean DodajKontakt(Kontakti kontakt, int id) throws SQLException {

			String query = "INSERT INTO imenik.kontakti ( userID, ime, prezime, broj_telefona, e_mail, adresa, grad) VALUES(?,?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(query);) {
				statement.setInt(1, id);
				statement.setString(2, kontakt.getIme());
				statement.setString(3, kontakt.getPrezime());
				statement.setString(5, kontakt.getEmail());
				statement.setString(4, kontakt.getBrTel());
				statement.setString(6, kontakt.getAdresa());
				statement.setString(7, kontakt.getGrad());

				statement.executeUpdate();
			}
			return true;
		}

		@Override
		public boolean ObrisiKontakt(Kontakti kontakt) throws SQLException {

			String query = "DELETE FROM imenik.kontakti WHERE id=?;";

			try (PreparedStatement statement = connection.prepareStatement(query);) {

				statement.setInt(1, kontakt.getId());

				statement.executeUpdate();
			}
			return true;
		}

		@Override
		public ArrayList<Kontakti> PronadjiKontakt(String ime, int id) throws SQLException {
			Kontakti kontakt = null;

			String query = "SELECT * FROM imenik.kontakti WHERE ime= ? and userID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, ime);
			statement.setInt(2, id);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				kontakt = new Kontakti();

				kontakt.setIme(rs.getString("ime"));
				kontakt.setPrezime(rs.getString("prezime"));
				kontakt.setEmail(rs.getString("e_mail"));
				kontakt.setBrTel(rs.getString("broj_telefona"));
				kontakt.setAdresa(rs.getString("adresa"));
				kontakt.setGrad(rs.getString("grad"));
				kontakt.setId(1);
				kontakt.setUserId(2);
				list.add(kontakt);
			}

			return list;
		}

		@Override
		public boolean AzurirajKontakt(Kontakti kontakt) throws SQLException {

			String query = "UPDATE imenik.kontakti SET ime = ?, prezime = ?, broj_telefona=?, e_mail = ?, adresa = ?, grad = ? WHERE ID = ?";

			try (PreparedStatement statement = connection.prepareStatement(query);) {

				statement.setString(1, kontakt.getIme());
				statement.setString(2, kontakt.getPrezime());
				statement.setString(4, kontakt.getEmail());
				statement.setString(3, kontakt.getBrTel());
				statement.setString(6, kontakt.getGrad());
				statement.setString(5, kontakt.getAdresa());

				statement.setInt(7, kontakt.getId());

				statement.executeUpdate();
			}
			return true;

		}

		
		@Override
		public Kontakti uzmiKontakt(int id) throws SQLException {

			Kontakti kontakt = null;

			String query = "SELECT * FROM imenik.kontakti WHERE ID = ?";
			ResultSet rs = null;

			try (PreparedStatement statement = connection.prepareStatement(query);) {

				statement.setInt(1, id);

				rs = statement.executeQuery();

				if (rs.next()) {

					kontakt = new Kontakti();

					kontakt.setId(rs.getInt("ID"));
					kontakt.setUserId(rs.getInt("userId"));
					kontakt.setIme(rs.getString("ime"));
					kontakt.setPrezime(rs.getString("prezime"));
					kontakt.setEmail(rs.getString("e_mail"));
					kontakt.setBrTel(rs.getString("broj_telefona"));
					kontakt.setGrad(rs.getString("grad"));
					kontakt.setAdresa(rs.getString("adresa"));

				}
			}
			rs.close();

			return kontakt;
		}
		 
	}
