package ohtu.ohtuvarasto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1, 1);
        varasto = new Varasto(1, 2);
        varasto = new Varasto(-1, 2);
        varasto = new Varasto(-1, -1);
        varasto.toString();
    }

    @Test
    public void negLisaysEiVahennaSaldoa() {
        final double result = 5D;
        varasto.lisaaVarastoon(result);
        varasto.lisaaVarastoon(-1D);
        assertEquals(result, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiYlitaSaldoa() {
        final double result = 10D;
        varasto.lisaaVarastoon(result);
        varasto.lisaaVarastoon(1D);
        assertEquals(result, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yliLisaysEiYlitaSaldoa() {
        final double result = 10D;
        varasto.lisaaVarastoon(result + 1D);
        assertEquals(result, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negVientiEiMuutaSaldoa() {
        final double result = 10D;
        varasto.lisaaVarastoon(result);
        varasto.otaVarastosta(-1D);
        assertEquals(result, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negVientiPalauttaaNollan() {
        final double result = 0D;
        assertEquals(result, varasto.otaVarastosta(-1D), vertailuTarkkuus);
    }

    @Test
    public void viedaanKaikki() {
        final double result = 7.5345D;
        final double tryTake = 123D;
        varasto.lisaaVarastoon(result);
        final double ahneOtto = varasto.otaVarastosta(tryTake);
        assertEquals(result, ahneOtto, vertailuTarkkuus);
    }
}
