package steps_definition;

import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InserirContasSteps {

    private WebDriver driver;
    @Dado("^que estou acessando a aplicação$")
    public void que_estou_acessando_a_aplicação() throws Throwable {
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
    }

    @Quando("^informo o usuário \"([^\"]*)\"$")
    public void informo_o_usuário(String arg1) throws Throwable {
        driver.findElement(By.id("email")).sendKeys(arg1);
    }

    @Quando("^a senha \"([^\"]*)\"$")
    public void a_senha(String arg1) throws Throwable {
        driver.findElement(By.name("senha")).sendKeys(arg1);
    }

    @Quando("^seleciono entrar$")
    public void seleciono_entrar() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("^visualizo a página inicial$")
    public void visualizo_a_página_inicial() throws Throwable {
       String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Bem vindo, RONIERISON MOREIRA DA COSTA!", texto);
    }

    @Quando("^seleciono Contas$")
    public void seleciono_Contas() throws Throwable {
        driver.findElement(By.linkText("Contas")).click();
    }

    @Quando("^seleciono Adicionar$")
    public void seleciono_Adicionar() throws Throwable {
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("^informo a conta \"([^\"]*)\"$")
    public void informo_a_conta(String arg1) throws Throwable {
        driver.findElement(By.id("nome")).sendKeys(arg1);
    }

    @Quando("^seleciono Salvar$")
    public void seleciono_Salvar() throws Throwable {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("^a conta é inserida com sucesso$")
    public void a_conta_é_inserida_com_sucesso() throws Throwable {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", texto);
    }
}
