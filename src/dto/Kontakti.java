package dto;

public class Kontakti {

	private int id;
	private int userId;
	private String ime;
	private String prezime;
	private String broj_telefona;
	private String e_mail;
	private String adresa;
	private String grad;

	public Kontakti() {
		}

	public Kontakti(String ime) {
		}

	public Kontakti(int userId, String ime, String prezime, String br_tel, String email, String adr, String grad) {
			this.ime = ime;
			this.prezime = prezime;
			this.broj_telefona = br_tel;
			this.e_mail = email;
			this.adresa = adr;
			this.grad = grad;
			this.userId = userId;
		}

	public Kontakti(int id,int userid, String ime, String prezime, String br_tel, String email, String adr, String grad) {
		this.ime = ime;
		this.prezime = prezime;
		this.broj_telefona = br_tel;
		this.e_mail = email;
		this.adresa = adr;
		this.grad = grad;
		this.userId = userid;
		this.id = id;
			
		}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getBrTel() {
		return broj_telefona;
	}

	public void setBrTel(String br_tel) {
		this.broj_telefona = br_tel;
	}

	public String getEmail() {
		return e_mail;
	}

	public void setEmail(String email) {
		this.e_mail = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	
	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Kontakt [ID = " + id + "; ime = " + ime + ", prezime =" + prezime + ", broj telefona =" + broj_telefona 
				+ ", email = "  + e_mail +", adresa = "+ adresa + ", grad = "
				+ grad + "]";
	}

}