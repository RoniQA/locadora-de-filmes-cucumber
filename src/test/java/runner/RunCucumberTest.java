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
        features = "src/test/resources/features/",	// Path: features = "classpath:features"
        glue = "steps_definition",					// Path: Steps
        tags = {"@unitários"},
        monochrome = true,							// Cores no Terminal default: false
        snippets = SnippetType.UNDERSCORE,			// METHODOS do Steps em CAMELCASE
        strict = false,								// Considerar steps indefinidos erros ou nao, default: false
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

public class RunCucumberTest {
}
