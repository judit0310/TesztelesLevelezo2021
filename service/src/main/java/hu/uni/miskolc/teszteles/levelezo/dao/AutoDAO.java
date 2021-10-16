package hu.uni.miskolc.teszteles.levelezo.dao;

import hu.uni.miskolc.teszteles.levelezo.model.Auto;

import java.util.List;

public interface AutoDAO {
    public List<Auto> readAutos();

    public Auto readAutoById(String rendszam);

    public void createAuto(Auto auto);
}
