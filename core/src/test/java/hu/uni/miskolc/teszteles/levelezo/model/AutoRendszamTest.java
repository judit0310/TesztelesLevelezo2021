package hu.uni.miskolc.teszteles.levelezo.model;

import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class AutoRendszamTest {

    String rendszam;
    Auto auto;

    @Parameterized.Parameters
    public static Collection data(){
        List data = new ArrayList<>();
        data.add("AAAA-123");
        data.add("123-AAA");
        data.add("AAA-AAA");
        data.add("123-123");
        data.add("AAA-1234");
        data.add("AA-123");
        data.add("AAA-12");
        data.add("aaa-123");
        data.add("AAA-000");
        data.add("AAA123");
        data.add("-");
        return data;
    }

    public AutoRendszamTest(String rendszam) {
        this.rendszam = rendszam;
    }
    @Before
    public void initAuto(){
        auto = new Auto();
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testRosszRendszam() throws RendszamNemMegfelelo {
        System.out.println("A vizsgált rendszám: "+rendszam);
        auto.setRendszam(rendszam);
    }
}
