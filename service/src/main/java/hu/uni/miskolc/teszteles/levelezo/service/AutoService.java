package hu.uni.miskolc.teszteles.levelezo.service;

import hu.uni.miskolc.teszteles.levelezo.dao.AutoDAO;
import hu.uni.miskolc.teszteles.levelezo.model.Auto;

import java.util.List;

public class AutoService {

    private AutoDAO dao;

    public AutoService(AutoDAO dao){
        this.dao = dao;
    }

    public List<Auto> getAutok(){
        return dao.readAutos();};

    public List<Auto> getKorozottAutok(){
        //TODO logika
        return dao.readAutos();
    }


    public Auto getAutoRendszamAlapjan(String rendszam){
        return dao.readAutoById(rendszam);
    }

    public void addAuto(Auto auto){
        dao.createAuto(auto);
    }
}
