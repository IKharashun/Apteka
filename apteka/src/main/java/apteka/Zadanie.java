package apteka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class Zadanie {
    public static void main(String[] args) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        List<String[]> kategorie = podzielNaKategorie();
        try {
            List<Producent> producenci = przypiszDostawcow(kategorie.get(0));
            List<Apteka> apteki = przypiszApteki(kategorie.get(1));
            List<Raport> raporty = przypiszRaporty(kategorie.get(2));
            raporty.sort(Comparator.comparing(Raport::getCena));

            for (int i = 0; i < apteki.size(); i++) {
                int zapotrzebowanieApteki = apteki.get(i).getZapotrzebowanie();
                for (int j = 0; j < raporty.size(); j++) {
                    int iloscZamowionychSzczepionek = apteki.get(i).getIloscZamowionychSzczepionek();
                    if (raporty.get(j).getIdApteki() == apteki.get(i).getId()) {
                        int idProducenta = raporty.get(j).getIdProducenta();
                        Double cena = raporty.get(j).getCena();
                        int ilosc = 0;
                        int brakującaIloscSzczepionek = zapotrzebowanieApteki - iloscZamowionychSzczepionek;
                        if ((brakującaIloscSzczepionek > 0) && (brakującaIloscSzczepionek < raporty.get(j).getMaksymalnaLiczbaSzczepionek())) {
                            ilosc += brakującaIloscSzczepionek;
                        } else if (brakującaIloscSzczepionek > 0) {
                            ilosc += raporty.get(j).getMaksymalnaLiczbaSzczepionek();
                        } else {
                            break;
                        }
                        apteki.get(i).addZamowienie(new Zamowienie(idProducenta, cena, ilosc, pobierzNazweProducenta(idProducenta, producenci)));
                    }
                }
            }
            zapisDoPliku(apteki);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Nieprawidłowy raport");
        }
    }

    private static void zapisDoPliku(List<Apteka> apteki) throws FileNotFoundException {
        Double oplatyCalkowite = 0d;
        //todo !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //podmienić ścieżkę;
        PrintWriter zapis = new PrintWriter("C:\\Users\\choro\\OneDrive\\Desktop\\МУСОР\\apteka\\src\\main\\java\\aptekaodpowiedz.txt");
        for (int i = 0; i < apteki.size(); i++) {
            oplatyCalkowite += apteki.get(i).pobierzCalkowityKoszt();
            zapis.println(apteki.get(i) + "\n");
        }
        zapis.println("----------------------------------");
        zapis.println("Opłaty całkowiete: " + String.format("%.2f", oplatyCalkowite) + " zł");
        zapis.close();
    }

    private static String pobierzNazweProducenta(int idProducenta, List<Producent> producenci) {
        for (int i = 0; i < producenci.size(); i++) {
            if (producenci.get(i).getId() == idProducenta) {
                return producenci.get(i).getNazwa();
            }
        }
        return "Brak nazwy producenta";
    }

    private static List<Raport> przypiszRaporty(String[] informacje) {
        ArrayList<Raport> raporty = new ArrayList<Raport>();

        for (int i = 0; i < informacje.length; i++) {
            String[] szczegoly = informacje[i].split("\\|");
            Raport raport = new Raport(
                    Integer.parseInt(szczegoly[0].trim()),
                    Integer.parseInt(szczegoly[1].trim()),
                    Integer.parseInt(szczegoly[2].trim()),
                    Double.parseDouble(szczegoly[3].trim()));
            raporty.add(raport);
        }
        return raporty;
    }

    private static List<Apteka> przypiszApteki(String[] informacje) {
        List<Apteka> apteki = new ArrayList<Apteka>();

        for (int i = 0; i < informacje.length; i++) {
            String[] szczegoly = informacje[i].split("\\|");
            Apteka apteka = new Apteka(
                    Integer.parseInt(szczegoly[0].trim()),
                    szczegoly[1],
                    Integer.parseInt(szczegoly[2].trim()));
            apteki.add(apteka);
        }
        return apteki;
    }

    private static List<Producent> przypiszDostawcow(String[] informacje) {
        List<Producent> dostawcy = new ArrayList<Producent>();

        for (int i = 0; i < informacje.length; i++) {
            String[] szczegoly = informacje[i].split("\\|");
            Producent producent = new Producent(
                    Integer.parseInt(szczegoly[0].trim()),
                    szczegoly[1],
                    Integer.parseInt(szczegoly[2].trim()));
            dostawcy.add(producent);
        }
        return dostawcy;
    }

    private static List<String[]> podzielNaKategorie() {
        try {
            File myObj = new File(
                    //todo !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    //podmienić ścieżkę;
                    "C:\\Users\\choro\\OneDrive\\Desktop\\МУСОР\\apteka\\src\\main\\java\\apteka\\przykład_danych.txt");
            Scanner myReader = new Scanner(myObj);
            StringBuilder wymagania = new StringBuilder();
            while (myReader.hasNextLine()) {
                wymagania.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
            String[] split = wymagania.toString().split("#");

            String[] dostawcy = split[1].substring(split[1].indexOf('\n') + 1).split("\n");
            String[] apteki = split[2].substring(split[2].indexOf('\n') + 1).split("\n");
            String[] ofertyOdDostawcow = split[3].substring(split[3].indexOf('\n') + 1).split("\n");

            return Arrays.asList(dostawcy, apteki, ofertyOdDostawcow);
        } catch (FileNotFoundException e) {
            System.out.println("Nie można odtożyć pliku z danymi \n." +
                    " Sprawdz czy znajduje się on w odpowiednim folderze is spróbuj ponownie");
        }
        return null;
    }
}
