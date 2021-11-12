package hu.uni.miskolc.teszteles.levelezo.dao;

import hu.uni.miskolc.teszteles.levelezo.model.Auto;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.AutoNemTalalhato;

import java.util.Collection;

public interface AutoDAO {
    public Collection<Auto> readAutos();

    public Auto readAutoById(String rendszam) throws AutoNemTalalhato;

    public void createAuto(Auto auto);
}
