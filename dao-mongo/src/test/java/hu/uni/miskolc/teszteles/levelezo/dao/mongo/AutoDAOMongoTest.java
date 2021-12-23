package hu.uni.miskolc.teszteles.levelezo.dao.mongo;

import hu.uni.miskolc.teszteles.levelezo.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.UresErtek;
import hu.uni.miskolc.teszteles.levelezo.model.Auto;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Kivitel;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Uzemanyag;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.AutoNemTalalhato;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AutoDAOMongoTest {
    AutoDAOMongo dao;

    @Before
    public void init() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo {
        dao = new AutoDAOMongo("mongodb+srv://test:test@" +
                "szoftverteszteles2021." +
                "bqwgi.mongodb.net/test?retryWrites=true&w=majority",
                "levelezotest", "autok");
        Auto auto = new Auto("ABC-123", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 60000,
                false, 999);
        dao.createAuto(auto);
    }

    @Test
    public void testDatabaseConnection(){
        Assert.assertNotEquals(0,dao.readAutos());
    }

    @Test
    public void updateAuto() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo, AutoNemTalalhato {
        Auto auto = new Auto("ABC-123", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 80000,
                false, 999);
        Auto original = dao.readAutoById(auto.getRendszam());
        dao.updateAuto(auto);
        Auto updated = dao.readAutoById(auto.getRendszam());
        Assert.assertNotEquals(original,updated);

    }
    @After
    public void tearDown(){
        dao.clearCollection();
    }
}