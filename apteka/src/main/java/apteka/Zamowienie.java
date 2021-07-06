package apteka;

public class Zamowienie {
    private int IdDostawcy;
    private Double cena;
    private int ilosc;
    private String nazwaProducenta;

    public Zamowienie(int idDostawcy, Double cena, int ilosc, String nazwaProducenta) {
        IdDostawcy = idDostawcy;
        this.cena = cena;
        this.ilosc = ilosc;
        this.nazwaProducenta = nazwaProducenta;
    }

    public int getIdDostawcy() {
        return IdDostawcy;
    }

    public Zamowienie setIdDostawcy(int idDostawcy) {
        IdDostawcy = idDostawcy;
        return this;
    }

    public Double getCena() {
        return cena;
    }

    public Zamowienie setCena(Double cena) {
        this.cena = cena;
        return this;
    }

    public int getIlosc() {
        return ilosc;
    }

    public Zamowienie setIlosc(int ilosc) {
        this.ilosc = ilosc;
        return this;
    }

    public String getNazwaProducenta() {
        return nazwaProducenta;
    }

    public Zamowienie setNazwaProducenta(String nazwaProducenta) {
        this.nazwaProducenta = nazwaProducenta;
        return this;
    }
    public double koszt() {
        return cena * ilosc;
    }

}
