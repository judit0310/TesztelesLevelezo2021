package hu.uni.miskolc.teszteles.levelezo.dao.mongo;

import hu.uni.miskolc.teszteles.levelezo.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.UresErtek;
import hu.uni.miskolc.teszteles.levelezo.model.Auto;

public class AutoPojoConverter {

    public static AutoPojo convertAutoToPojo(Auto auto){
        AutoPojo pojo;
        pojo = new AutoPojo(auto.getRendszam(),
                auto.getMarka(), auto.getModell(),
                auto.getKivitel(),auto.getSzinHexadecimalis(),
                auto.getGyartasiIdo(),auto.getUzemanyag(),
                auto.getAjtokSzama(),auto.getFutottKilometer(),
                auto.isKorozottE(),auto.getHengerurtartalom());
        return pojo;
    }

    public static Auto convertPojoToAuto(AutoPojo pojo){
        Auto auto;
        try {
            auto = new Auto(pojo.rendszam,pojo.marka,pojo.modell,
                    pojo.kivitel,pojo.szinHexadecimalis,
                    pojo.gyartasiIdo,pojo.uzemanyag,pojo.ajtokSzama,
                    pojo.futottKilometer,pojo.korozottE,
                    pojo.hengerurtartalom);
            return auto;
        } catch (RendszamNemMegfelelo e) {
            e.printStackTrace();
        } catch (GyartasiIdoNemMegfelelo e) {
            e.printStackTrace();
        } catch (UresErtek e) {
            e.printStackTrace();
        }
        return new Auto();

    }
}
