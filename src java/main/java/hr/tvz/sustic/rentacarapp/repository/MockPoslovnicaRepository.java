package hr.tvz.sustic.rentacarapp.repository;


import hr.tvz.sustic.rentacarapp.model.Poslovnica;
import hr.tvz.sustic.rentacarapp.model.Vozilo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
@Component

public class MockPoslovnicaRepository implements PoslovnicaRepository {
    private static final List<Vozilo> vozila = new ArrayList<>();
    private static final List<Vozilo> vozila2 = new ArrayList<>();

    private static final List<Poslovnica> poslovnice = new ArrayList<>();

    static {
        Vozilo prvoVozilo =
                new Vozilo(1,4,"Automatik",
                        true,4,"Dizel",
                        new Date(2023, Calendar.NOVEMBER,10),new Date(2025, Calendar.NOVEMBER,10),
                        2000,"ZG123-WK","41234123412341243");
        Vozilo drugoVozilo =
                new Vozilo(2,5,"6 brzina",
                        true,4,"Benzin",
                        new Date(2023, Calendar.JUNE,20),new Date(2024, Calendar.DECEMBER,23),
                        10000,"ZG125-ZK","52352351234123413");
        vozila.add(prvoVozilo);
        vozila.add(drugoVozilo);
        Vozilo treceVozilo =
                new Vozilo(3,5,"Automatik",
                        true,4,"Dizel",
                        new Date(2023, Calendar.NOVEMBER,10),new Date(2025, Calendar.NOVEMBER,10),
                        2000,"ZG123-WK","41234123412341243");
        Vozilo cetvrtoVozilo =
                new Vozilo(4,6,"6 brzina",
                        true,4,"Benzin",
                        new Date(2023, Calendar.JUNE,20),new Date(2024, Calendar.DECEMBER,23),
                        10000,"ZG125-ZK","52352351234123413");
        vozila2.add(prvoVozilo);
        vozila2.add(drugoVozilo);

        Poslovnica prvaPoslovnica =
                new Poslovnica(1,"Opel poslovnica",vozila,"Zagreb Ilica 100","Marko Marković");
        Poslovnica drugaPoslovnica =
                new Poslovnica(2,"Ford poslovnica",vozila2,"Varaždin Ulica 200","Ivo Ivić");

        poslovnice.add(prvaPoslovnica);
        poslovnice.add(drugaPoslovnica);
    }
    @Override
    public List<Poslovnica> findAll() {
        return poslovnice;
    }

    @Override
    public Optional<Poslovnica> savePoslovnica(Poslovnica poslovnica) {
        if(poslovnice.stream().anyMatch(poslovnica1 -> Objects.equals(poslovnica.getCode(), poslovnica1.getCode()))){

            return Optional.empty();
        } else {

            poslovnice.add(poslovnica);
            return Optional.of(poslovnica);
        }
    }

    @Override
    public Optional<Poslovnica> findPoslovnicaByCode(Integer code) {
        return poslovnice.stream().filter(fi -> fi.getCode().equals(code)).findAny();
    }


    @Override
    public Optional<Poslovnica> findPoslovnicaByAdress(String adress) {
        return poslovnice.stream().filter(fi -> fi.getAdresa().equals(adress)).findAny();
    }

    @Override
    public void deletPoslovnica(Integer code) {
        for (int i=0; i<poslovnice.size(); i++){
            if (poslovnice.get(i).getCode().equals(code)){
                poslovnice.remove(i);
            }
        }
    }


}
