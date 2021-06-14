package tests;


import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = {})
public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();


    @Before
    public void setUp() {

        navegador = Web.createChrome();

        // Clicar no link que possui o texto sign in
        // Primeira abordagem de clicar no Sign in
        // WebElement linkSignin = navegador.findElement(By.linkText("Sign in"));
        //  linkSignin.click();
        // Segunda abordagem de clicar no Sign in
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulário de login "signinbox";
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar  no campo com name login que está dentro do formulário de id do "signinbox" o texto JeanHeberth
        formularioSignInBox.findElement(By.name("login")).sendKeys("JeanHeberth");
        //Clicar no campo com name password que está dentro do formulário de id do "signinbox"
        formularioSignInBox.findElement(By.name("password")).sendKeys("JeanHeberth");

        // Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar em link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que tem o  texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }


    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipo") String tipo, @Param(name = "contato") String contato, @Param(name = "mensagem") String mensagemEsperada) {


        // Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está formulário de id addmoredata
        WebElement poupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name Type escolher a opção "Phone"
        WebElement campoType = poupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);


        // No campo de name "contact" colocar o telefone
        poupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "save" que está na poup
        poupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar se  o texto é: "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario() {

        // Clicar no elemento pelo seu xpath //span[text()="+5561992062328"]/follwing-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+5561992062328\"]/following-sibling::a")).click();

        // Confirmar a janela javaScript
        navegador.switchTo().alert().accept();

        String screenshotArquivo = "C://Users//jean.vieira//OneDrive//DesenvolvimentoIntellij/ScreenShot/Taskit/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);


        // Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        // Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com texto "logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {

        //Fechar o navegador
        // navegador.quit();
    }


}
