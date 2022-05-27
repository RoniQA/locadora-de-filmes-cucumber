package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/inserir_conta.feature",	// Path: features = "classpath:features"
        glue = "steps_definition",					// Path: Steps
        tags = {"~@ignore"},
        monochrome = true,							// Cores no Terminal default: false
        snippets = SnippetType.UNDERSCORE,			// METHODOS do Steps em CAMELCASE
        strict = true,								// Considerar steps indefinidos erros ou nao, default: false
        dryRun = false,								// Validar Steps sem executar o teste
        plugin = {									// Plugins Cucumber para possivel integracao com Jenkins
                "pretty",
                "html:target/cucumber-reports",
                "json:target/report.json"
        }
        //,tags = { "@tagScenario" }

        /**
         * Executar testes no termial
         *
         *	$ mvn test
         *	$ mvn test -Dcucumber.options="--tags @tagScenario"
         *
         *	$ mvn clean
         *	$ mvn clean test
         *
         *
         * Gerar Relatorio Cucumber localmente
         *
         *	$ mvn verify -DskipTests
         *	$ mvn test verify
         *	$ mvn clean test verify
         *	$ mvn clean test -Dcucumber.options="--tags @tagScenario" verify
         * */
)

public class RunCucumberTest
{
        @BeforeClass
        public static void reset() {
                WebDriver driver = new ChromeDriver();
                driver.get("https://seubarriga.wcaquino.me/");
                driver.findElement(By.id("email")).sendKeys("ronierisoncosta@gmail.com");
                driver.findElement(By.name("senha")).sendKeys("zeca21121991");
                driver.findElement(By.tagName("button")).click();
                driver.findElement(By.linkText("reset")).click();
                driver.quit();
        }
}
