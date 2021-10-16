package hu.uni.miskolc.teszteles.levelezo.model;

import hu.uni.miskolc.teszteles.levelezo.exceptions.IsmeretlenHengerurtartalomNev;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class AutoHengerurtartalomTest {
    String nev;
    int urtartalom;


    @Parameterized.Parameters
    public static Collection data(){
        List data = new ArrayList<>();
        data.add(new Object[]{"1.0",998});
        data.add(new Object[]{"1.2",1199});
        data.add(new Object[]{"1.4",1390});
        data.add(new Object[]{"1.6",1560});

        return data;
    }

    public AutoHengerurtartalomTest(String nev, int urtartalom) {
        this.nev = nev;
        this.urtartalom = urtartalom;
    }

    @Test
    public void testJoHengerurtartalom() throws IsmeretlenHengerurtartalomNev {
        System.out.println("A vizsgált hengerűrtartalom: "+ nev);
        Auto auto = new Auto();
        auto.setHengerurtartalom(nev);
        Assert.assertEquals(urtartalom, auto.getHengerurtartalom());
    }
}
