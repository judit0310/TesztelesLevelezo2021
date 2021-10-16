package hu.uni.miskolc.teszteles.levelezo.model;

import hu.uni.miskolc.teszteles.levelezo.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.IsmeretlenHengerurtartalomNev;
import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AutoTest {

    Auto auto;

    @Before
    public void initAuto(){
        auto = new Auto();
    }

    @Test
    public void testJoRendszam() throws RendszamNemMegfelelo {
        String rendszam = "AAA-123";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testRendszamKotojelNelkul() throws RendszamNemMegfelelo {
        String rendszam = "AAA123";
        auto.setRendszam(rendszam);
    }

    @Test
    public void testGetterRendszam() throws RendszamNemMegfelelo {
        String rendszam = "AAA-213";
        auto.setRendszam(rendszam);
        assertEquals(rendszam, auto.getRendszam());
    }

    @Test
    public void testJoGyartasiIdo() throws GyartasiIdoNemMegfelelo {
        LocalDate datum = LocalDate.now();
        auto.setGyartasiIdo(datum);
    }

    @Test(expected = GyartasiIdoNemMegfelelo.class)
    public void testKesobbiGyartasiIdo() throws GyartasiIdoNemMegfelelo{
        LocalDate datum = LocalDate.now().plusDays(1);
        auto.setGyartasiIdo(datum);
    }


    @Test(expected = GyartasiIdoNemMegfelelo.class)
    public void testKorabbiGyartasiIdo() throws GyartasiIdoNemMegfelelo{
        LocalDate datum = LocalDate.of(1885,12,31);
        auto.setGyartasiIdo(datum);
    }

    @Test(expected = IsmeretlenHengerurtartalomNev.class)
    public void testNemSzereploHengerurtartalom() throws IsmeretlenHengerurtartalomNev {
        String motorMeret = "0.5";
        auto.setHengerurtartalom(motorMeret);
    }
}