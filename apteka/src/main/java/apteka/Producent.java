package apteka;

public class Producent {
    private String nazwa;
    private int id;
    private int produkcja;

    public Producent(int id, String nazwa, int produkcja) {
        this.nazwa = nazwa;
        this.id = id;
        this.produkcja = produkcja;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdukcja() {
        return produkcja;
    }

    public void setProdukcja(int produkcja) {
        this.produkcja = produkcja;
    }

}
