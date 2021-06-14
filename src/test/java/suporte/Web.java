package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {

    public static WebDriver createChrome() {
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jean Heberth\\OneDrive\\DesenvolvimentoIntellij\\DriveGoogle\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Caso desejar maximar a tela do chrome usar o comando a baixo.
        navegador.manage().window().maximize();
        // Caso for MAc
        // navegador.manage().window().setSize(new Dimension(1280, 800));

        // Navegando para a página do teste
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
}
