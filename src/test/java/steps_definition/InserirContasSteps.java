package steps_definition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class InserirContasSteps {

    private WebDriver driver;

    @Dado("^que desejo adicionar uma conta$")
    public void que_desejo_adicionar_uma_conta() throws Throwable {
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
        driver.findElement(By.id("email")).sendKeys("ronierisoncosta@gmail.com");
        driver.findElement(By.name("senha")).sendKeys("zeca21121991");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("Contas")).click();
        driver.findElement(By.linkText("Adicionar")).click();

    }

    @Quando("^adiciono a conta \"([^\"]*)\"$")
    public void adiciono_a_conta(String arg1) throws Throwable {
        driver.findElement(By.id("nome")).sendKeys(arg1);
        driver.findElement(By.tagName("button")).click();
    }

    @Então("^recebo a mensagem \"([^\"]*)\"$")
    public void recebo_a_mensagem(String arg1) throws Throwable {
        String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(arg1, texto);
    }

    @After (order = 1, value = {"@funcionais"})
    public void screenshot(Scenario cenario) {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshots/"+cenario.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After (order = 0, value = {"@funcionais"})
    public  void fecharBrowser() {
        driver.quit();
    }
}
