package hu.uni.miskolc.teszteles.levelezo.service;

import hu.uni.miskolc.teszteles.levelezo.dao.AutoDAO;
import hu.uni.miskolc.teszteles.levelezo.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.UresErtek;
import hu.uni.miskolc.teszteles.levelezo.model.Auto;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Kivitel;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Uzemanyag;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.AutoNemTalalhato;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.RendszamMarFoglalt;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AutoServiceTest {
    private AutoService service;
    private AutoDAO dao;
    private Collection<Auto> autok;


    @Before
    public void setUp() throws Exception, RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo {
        dao = Mockito.mock(AutoDAO.class);
        service = new AutoService(dao);
        Auto auto = new Auto("ABC-123", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 60000,
                false, 999);
        Auto auto2 = new Auto("ABC-222", "Kia", "Picanto",
                Kivitel.FERDEHATU, "#121212",
                LocalDate.of(2012, 11, 30)
                , Uzemanyag.BENZIN, 3, 100000,
                true, 999);
        Auto auto3 = new Auto("ABC-333", "Renault", "Thalia",
                Kivitel.SEDAN, "#000000",
                LocalDate.of(2007, 1, 20)
                , Uzemanyag.DIESEL, 5, 160000,
                false, 1390);
        autok = new ArrayList<>();
        autok.add(auto);
        autok.add(auto2);
        autok.add(auto3);

        Mockito.when(dao.readAutos()).thenReturn(autok);
        Mockito.when(dao.readAutoById(org.mockito.Matchers.anyString())).
                thenThrow(new AutoNemTalalhato());
        Mockito.doReturn(auto).when(dao).readAutoById("ABC-123");
        Mockito.doReturn(auto2).when(dao).readAutoById("ABC-222");
        Mockito.doReturn(auto3).when(dao).readAutoById("ABC-333");
        Mockito.doThrow(RendszamNemMegfelelo.class).when(dao).readAutoById(
                AdditionalMatchers.not(Mockito.matches("\\w\\w\\w-\\d\\d\\d"))
        );
        Mockito.doThrow(RendszamMarFoglalt.class).when(dao).createAuto(auto);
        Mockito.doThrow(RendszamMarFoglalt.class).when(dao).createAuto(auto2);
        Mockito.doThrow(RendszamMarFoglalt.class).when(dao).createAuto(auto3);
    }

    @Test
    public void testAllAutos() {
        Collection<Auto> lekerdezett = service.getAutok();
        assertEquals(3, lekerdezett.size());
        for (Auto a : autok) {
            MatcherAssert.assertThat(lekerdezett, Matchers.hasItem(a));
        }
    }

    @Test
    public void testUgyanaz() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo {
        Auto auto = new Auto("ABC-123", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 60000,
                false, 999);
        MatcherAssert.assertThat(service.getAutok(), Matchers.hasItem(auto));
    }

    @Test
    public void testVanKorozottAuto() {
        assertNotEquals(0, service.getKorozottAutok().size());
    }

    @Test
    public void testBenzinesAuto() {
        assertEquals(2,
                service.getAutokByUzemanyag(Uzemanyag.BENZIN).size());
    }

    @Test(expected = InvalidParameterException.class)
    public void testRosszIntervallum() {
        service.getDatumKozottiAutok(LocalDate.now(),
                LocalDate.now().minusMonths(2));
    }

    @Test
    public void testEgyNaposIntervallum() {
        service.getDatumKozottiAutok(LocalDate.now(),
                LocalDate.now());
    }

    @Test
    public void testJoIntervallum() {
        assertEquals(2, service.getDatumKozottiAutok(LocalDate.parse("2011-01-01"),
                LocalDate.now()).size());
    }

    @Test
    public void testAutoByRendszamJoErtekek() throws AutoNemTalalhato, RendszamNemMegfelelo {
        for (Auto a : autok
        ) {
            assertEquals(a, service.getAutoRendszamAlapjan(a.getRendszam()));
        }
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testAutoByRendszamRosszErtek() throws AutoNemTalalhato, RendszamNemMegfelelo {
        service.getAutoRendszamAlapjan("A");
    }

    @Test(expected = AutoNemTalalhato.class)
    public void testAutoByRendszamHianyzoErtek() throws RendszamNemMegfelelo, AutoNemTalalhato {
        service.getAutoRendszamAlapjan("ZZZ-999");
    }

    @Test(expected = RendszamMarFoglalt.class)
    public void testRendszamMarFoglalt() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo, RendszamMarFoglalt {
        Auto auto = new Auto("ABC-123", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 3, 60000,
                false, 999);
        service.addAuto(auto);
    }

    @Test(expected = RendszamMarFoglalt.class)
    public void testAutoRendszamMarFoglaltMasodjara() throws RendszamNemMegfelelo, UresErtek, GyartasiIdoNemMegfelelo, AutoNemTalalhato, RendszamMarFoglalt {
        Auto auto = new Auto("ABC-777", "Opel", "Astra",
                Kivitel.KOMBI, "#ffffff",
                LocalDate.of(2017, 5, 12)
                , Uzemanyag.BENZIN, 5, 60000,
                false, 999);
        service.addAuto(auto);
        Collection<Auto> ujAutok = autok;
        ujAutok.add(auto);
        Mockito.when(dao.readAutos()).thenReturn(ujAutok);
        assertEquals(4, service.getAutok().size());
        Mockito.doThrow(RendszamMarFoglalt.class).when(dao).
                readAutoById(auto.getRendszam());
        service.addAuto(auto);
    }


}