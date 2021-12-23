package hu.uni.miskolc.teszteles.levelezo;

import hu.uni.miskolc.teszteles.levelezo.dao.AutoDAO;
import hu.uni.miskolc.teszteles.levelezo.dao.mongo.AutoDAOMongo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.UresErtek;
import hu.uni.miskolc.teszteles.levelezo.model.Auto;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Kivitel;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Uzemanyag;
import hu.uni.miskolc.teszteles.levelezo.service.AutoService;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.AutoNemTalalhato;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.RendszamMarFoglalt;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AutoServiceIT {
    private static AutoService service;
    private static AutoDAO dao;

    @BeforeClass
    public static void setUp(){
        dao =  new AutoDAOMongo("mongodb+srv://test:test@" +
                "szoftverteszteles2021." +
                "bqwgi.mongodb.net/test?retryWrites=true&w=majority",
                "levelezotest", "autok");
        service = new AutoService(dao);
        ((AutoDAOMongo) dao).clearCollection();
    }

    @Before
    public void initData() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo {
        Auto auto = new Auto("ABC-123", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 60000,
                false, 999);
        dao.createAuto(auto);
    }

    @Test(expected = RendszamMarFoglalt.class)
    public void testRendszamMarFoglalt() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo, RendszamMarFoglalt {
        Auto auto = new Auto("ABC-123", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 60000,
                false, 999);
        service.addAuto(auto);
    }


    @Test(expected = AutoNemTalalhato.class)
    public void testAutoByRendszamHianyzoErtek() throws RendszamNemMegfelelo, AutoNemTalalhato {
        service.getAutoRendszamAlapjan("ZZZ-999");
    }

    @After
    public void dropData(){
        ((AutoDAOMongo) dao).clearCollection();
    }


    @Test
    public void testBenzinesAuto() {
        assertEquals(1,
                service.getAutokByUzemanyag(Uzemanyag.BENZIN).size());
    }

    @Test
    public void testVanKorozottAuto() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo, RendszamMarFoglalt {
        assertEquals(0, service.getKorozottAutok().size());
        Auto auto = new Auto("ABC-127", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 60000,
                true, 999);
        service.addAuto(auto);
        assertEquals(1, service.getKorozottAutok().size());
    }

}
