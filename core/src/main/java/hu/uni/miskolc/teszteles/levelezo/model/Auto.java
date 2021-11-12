package hu.uni.miskolc.teszteles.levelezo.model;

import hu.uni.miskolc.teszteles.levelezo.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.IsmeretlenHengerurtartalomNev;
import hu.uni.miskolc.teszteles.levelezo.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.levelezo.exceptions.UresErtek;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Kivitel;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Uzemanyag;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Auto implements HanggalRendelkezo {

    public static Map<String, Integer> hengerurtartalomErtekek;

    static {
        hengerurtartalomErtekek = new HashMap<>();
        hengerurtartalomErtekek.put("1.0", 998);
        hengerurtartalomErtekek.put("1.2",1199);
        hengerurtartalomErtekek.put("1.4",1390);
        hengerurtartalomErtekek.put("1.6",1560);
    }

    protected String rendszam;
    protected String marka;
    protected String modell;
    protected Kivitel kivitel;
    protected String szinHexadecimalis;
    protected LocalDate gyartasiIdo;
    protected Uzemanyag uzemanyag;
    protected int ajtokSzama;
    protected int futottKilometer;
    protected boolean korozottE;
    protected int hengerurtartalom;


    public String getRendszam() {
        return rendszam;
    }

    public void setRendszam(String rendszam) throws RendszamNemMegfelelo {
        String minta = "(^[A-Z]{3}-((?!000)\\d{3})$)";
        if (!rendszam.matches(minta)) {
            throw new RendszamNemMegfelelo(rendszam);
        }
        this.rendszam = rendszam;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) throws UresErtek {
        if (marka.trim().length() == 0) {
            throw new UresErtek();
        }
        this.marka = marka;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public Kivitel getKivitel() {
        return kivitel;
    }

    public void setKivitel(Kivitel kivitel) {
        this.kivitel = kivitel;
    }

    public String getSzinHexadecimalis() {
        return szinHexadecimalis;
    }

    public void setSzinHexadecimalis(String szinHexadecimalis) {
        this.szinHexadecimalis = szinHexadecimalis;
    }

    public LocalDate getGyartasiIdo() {
        return gyartasiIdo;
    }

    public void setGyartasiIdo(LocalDate gyartasiIdo) throws GyartasiIdoNemMegfelelo {
       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
        LocalDate lt
                = LocalDate.parse("31 Dec 2018", formatter);*/

        if (gyartasiIdo.isAfter(LocalDate.now()) || gyartasiIdo.isBefore(LocalDate.parse("1886-01-01"))) {
            throw new GyartasiIdoNemMegfelelo();
        }
        this.gyartasiIdo = gyartasiIdo;
    }

    public Uzemanyag getUzemanyag() {
        return uzemanyag;
    }

    public void setUzemanyag(Uzemanyag uzemanyag) {
        this.uzemanyag = uzemanyag;
    }

    public int getAjtokSzama() {
        return ajtokSzama;
    }

    public void setAjtokSzama(int ajtokSzama) {
        this.ajtokSzama = ajtokSzama;
    }

    public int getFutottKilometer() {
        return futottKilometer;
    }

    public void setFutottKilometer(int futottKilometer) {
        this.futottKilometer = futottKilometer;
    }

    public boolean isKorozottE() {
        return korozottE;
    }

    public void setKorozottE(boolean korozottE) {
        this.korozottE = korozottE;
    }

    public int getHengerurtartalom() {
        return hengerurtartalom;
    }

    public void setHengerurtartalom(String hengerurtartalom) throws IsmeretlenHengerurtartalomNev {
        if (!hengerurtartalomErtekek.containsKey(hengerurtartalom)){
            throw new IsmeretlenHengerurtartalomNev();
        }
        Integer ertek = hengerurtartalomErtekek.get(hengerurtartalom);
        this.hengerurtartalom = ertek.intValue();
    }

    public Auto(String rendszam, String marka, String modell, Kivitel kivitel,
                String szinHexadecimalis, LocalDate gyartasiIdo,
                Uzemanyag uzemanyag, int ajtokSzama, int futottKilometer,
                boolean korozottE, int hengerurtartalom) throws
            RendszamNemMegfelelo, GyartasiIdoNemMegfelelo, UresErtek {
        setRendszam(rendszam);
        setMarka(marka);
        setModell(modell);
        setKivitel(kivitel);
        setSzinHexadecimalis(szinHexadecimalis);
        setGyartasiIdo(gyartasiIdo);
        setUzemanyag(uzemanyag);
        setAjtokSzama(ajtokSzama);
        setFutottKilometer(futottKilometer);
        setKorozottE(korozottE);
        setHengerurtartalom(hengerurtartalom);
    }

    public void setHengerurtartalom(int hengerurtartalom) {
        this.hengerurtartalom = hengerurtartalom;
    }

    public Auto() {
    }

    @Override
    public String toString() {
        return "Auto{" +
                "rendszam='" + rendszam + '\'' +
                ", marka='" + marka + '\'' +
                ", modell='" + modell + '\'' +
                ", kivitel=" + kivitel +
                ", szinHexadecimalis='" + szinHexadecimalis + '\'' +
                ", gyartasiIdo=" + gyartasiIdo +
                ", uzemanyag=" + uzemanyag +
                ", ajtokSzama=" + ajtokSzama +
                ", futottKilometer=" + futottKilometer +
                ", korozottE=" + korozottE +
                ", hengerurtartalom=" + hengerurtartalom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return ajtokSzama == auto.ajtokSzama && futottKilometer == auto.futottKilometer && hengerurtartalom == auto.hengerurtartalom && Objects.equals(rendszam, auto.rendszam) && Objects.equals(marka, auto.marka) && Objects.equals(modell, auto.modell) && kivitel == auto.kivitel && Objects.equals(szinHexadecimalis, auto.szinHexadecimalis) && Objects.equals(gyartasiIdo, auto.gyartasiIdo) && uzemanyag == auto.uzemanyag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rendszam, marka, modell, kivitel, szinHexadecimalis, gyartasiIdo, uzemanyag, ajtokSzama, futottKilometer, korozottE, hengerurtartalom);
    }
}
