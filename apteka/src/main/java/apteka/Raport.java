package apteka;

public class Raport {
    private int idProducenta;
    private int idApteki;
    private int maksymalnaIloscSzczepionek;
    private Double cena;

    public Raport(int idProducenta, int idApteki, int maksymalnaIloscSzczepionek, Double cena) {
        this.idProducenta = idProducenta;
        this.idApteki = idApteki;
        this.maksymalnaIloscSzczepionek = maksymalnaIloscSzczepionek;
        this.cena = cena;
    }

    public int getIdProducenta() {
        return idProducenta;
    }

    public void setIdProducenta(int idProducenta) {
        this.idProducenta = idProducenta;
    }

    public int getIdApteki() {
        return idApteki;
    }

    public void setIdApteki(int idApteki) {
        this.idApteki = idApteki;
    }

    public int getMaksymalnaLiczbaSzczepionek() {
        return maksymalnaIloscSzczepionek;
    }

    public void setMaksymalnaIloscSzczepionek(int maksymalnaIloscSzczepionek) {
        this.maksymalnaIloscSzczepionek = maksymalnaIloscSzczepionek;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Raport raport = (Raport) o;

        return getCena() != null ? getCena().equals(raport.getCena()) : raport.getCena() == null;
    }

    @Override
    public int hashCode() {
        return getCena() != null ? getCena().hashCode() : 0;
    }
}
