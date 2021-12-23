package hu.uni.miskolc.teszteles.levelezo.dao.mongo;

import hu.uni.miskolc.teszteles.levelezo.model.enums.Kivitel;
import hu.uni.miskolc.teszteles.levelezo.model.enums.Uzemanyag;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDate;

public class AutoPojo {
    @BsonId
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

    public AutoPojo() {
    }

    public AutoPojo(String rendszam, String marka, String modell, Kivitel kivitel, String szinHexadecimalis, LocalDate gyartasiIdo, Uzemanyag uzemanyag, int ajtokSzama, int futottKilometer, boolean korozottE, int hengerurtartalom) {
        this.rendszam = rendszam;
        this.marka = marka;
        this.modell = modell;
        this.kivitel = kivitel;
        this.szinHexadecimalis = szinHexadecimalis;
        this.gyartasiIdo = gyartasiIdo;
        this.uzemanyag = uzemanyag;
        this.ajtokSzama = ajtokSzama;
        this.futottKilometer = futottKilometer;
        this.korozottE = korozottE;
        this.hengerurtartalom = hengerurtartalom;
    }

    public String getRendszam() {
        return rendszam;
    }

    public void setRendszam(String rendszam) {
        this.rendszam = rendszam;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
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

    public void setGyartasiIdo(LocalDate gyartasiIdo) {
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

    public void setHengerurtartalom(int hengerurtartalom) {
        this.hengerurtartalom = hengerurtartalom;
    }
}
