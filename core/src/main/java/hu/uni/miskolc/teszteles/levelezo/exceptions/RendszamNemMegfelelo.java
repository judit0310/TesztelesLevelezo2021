package hu.uni.miskolc.teszteles.levelezo.exceptions;

public class RendszamNemMegfelelo extends Throwable {
    public RendszamNemMegfelelo(String rendszam) {
        super("A következő rendszám nem megfelelő: "+rendszam);
    }
}
