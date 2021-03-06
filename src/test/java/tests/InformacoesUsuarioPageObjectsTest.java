package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

public class InformacoesUsuarioPageObjectsTest {

    private WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin("JeanHeberth", "JeanHeberth")
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoAddMoreDataAboutYou();
                ;


    }


    @After
    public void tearDown() {
        // navegador.quit();
    }
}
