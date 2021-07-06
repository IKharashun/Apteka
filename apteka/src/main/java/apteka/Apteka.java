package apteka;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Apteka {
    private String nazwa;
    private int id;
    private int zapotrzebowanie;
    private List<Zamowienie> zamowienia;

    public Apteka(int id, String nazwa, int zapotrzebowanie) {
        this.nazwa = nazwa;
        this.id = id;
        this.zapotrzebowanie = zapotrzebowanie;
        this.zamowienia = new ArrayList<>();
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public int getIloscZamowionychSzczepionek() {
        int ilosc = 0;
        for (int i = 0; i < zamowienia.size(); i++) {
            ilosc += zamowienia.get(i).getIlosc();
        }
        return ilosc;
    }

    public void addZamowienie(Zamowienie zamowienia) {
        this.zamowienia.add(zamowienia);
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

    public int getZapotrzebowanie() {
        return zapotrzebowanie;
    }

    public void setZapotrzebowanie(int zapotrzebowanie) {
        this.zapotrzebowanie = zapotrzebowanie;
    }

    public double pobierzCalkowityKoszt() {
        Double calkowityKoszt = 0d;
        for (int i = 0; i < zamowienia.size(); i++) {
            calkowityKoszt += zamowienia.get(i).koszt();
        }
        return calkowityKoszt;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zamowienia.size(); i++) {
            stringBuilder.append(zamowienia.get(i).getNazwaProducenta())
                    .append(" -> ").append(nazwa).append(" [Koszt = ")
                    .append(zamowienia.get(i).getIlosc()).append(" * ")
                    .append(String.format("%.2f",zamowienia.get(i).getCena())).append(" = ")
                    .append(String.format("%.2f",zamowienia.get(i).koszt())).append("zł]")
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
