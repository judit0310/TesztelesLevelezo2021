package hu.uni.miskolc.teszteles.levelezo.service;

import hu.uni.miskolc.teszteles.levelezo.dao.AutoDAO;
import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.model.Auto;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Uzemanyag;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.AutoNemTalalhato;
import hu.uni.miskolc.teszteles.levelezo.service.exceptions.RendszamMarFoglalt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

public class AutoService {

    private AutoDAO dao;

    public AutoService(AutoDAO dao) {
        this.dao = dao;
    }

    public Collection<Auto> getAutok() {
        return dao.readAutos();
    }

    public Collection<Auto> getAutokByUzemanyag(Uzemanyag uzemanyag){
        Collection<Auto> valasztott;
        Collection<Auto> osszesAuto = getAutok();
        valasztott = osszesAuto.stream().filter(a -> a.getUzemanyag() == uzemanyag).collect(Collectors.toList());
        return valasztott;
    }

    public Collection<Auto> getKorozottAutok() {
        Collection<Auto> korozott;
        Collection<Auto> osszesAuto = getAutok();
        korozott = osszesAuto.stream().filter(a -> a.isKorozottE()).
                collect(Collectors.toList());
        return korozott;
    }

    public Collection<Auto> getDatumKozottiAutok(LocalDate tol, LocalDate ig) {
        if (tol.isAfter(ig)) {
            throw new InvalidParameterException();
        }
        Collection<Auto> osszesAuto = getAutok();
        Predicate<Auto> pred = a -> a.getGyartasiIdo().isAfter(tol) &&
                a.getGyartasiIdo().isBefore(ig);
        CollectionUtils.filter(osszesAuto, pred);
        return osszesAuto;
    }


    public Auto getAutoRendszamAlapjan(String rendszam) throws RendszamNemMegfelelo, AutoNemTalalhato {
        return dao.readAutoById(rendszam);
    }

    public void addAuto(Auto auto) throws RendszamNemMegfelelo, RendszamMarFoglalt {
        try {
            getAutoRendszamAlapjan(auto.getRendszam());
        } catch (AutoNemTalalhato e) {
            dao.createAuto(auto);
            return;
        }
        throw new RendszamMarFoglalt();
    }
}
