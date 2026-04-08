package stepDefinitions;

import com.orangehrmlive.tasks.Login;
import com.orangehrmlive.tasks.UrlTask;
import com.orangehrmlive.ui.LoginUI;
import com.orangehrmlive.ui.DashboardUI;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.Text;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDef {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        setTheStage(new OnlineCast());
    }

    @Given("que el usuario se encuentra en la página de login de OrangeHRM Live")
    public void queElUsuarioSeEncuentraEnLaPáginaDeLoginDeOrangeHRMLive() {
        theActorCalled("Ingresar a la URL de orangehrmlive").wasAbleTo(
                UrlTask.urlTask()
        );
    }

    @When("ingresa el username {string} y la password {string}")
    public void ingresaElUsernameYLaPassword(String username, String password) {
        System.out.println("USERNAME: " + username);
        System.out.println("PASSWORD: " + password);

        theActorInTheSpotlight().attemptsTo(
                Login.conDatos(username, password)
        );
    }
    @Then("debería ver {string}")
    public void deberíaVer(String resultado) {
        System.out.println("RESULTADO: " + resultado);

        if ("Dashboard".equals(resultado)) {
            theActorInTheSpotlight().should(
                    seeThat(Text.of(DashboardUI.DASHBOARD), equalTo(resultado))
            );
        } else {
            theActorInTheSpotlight().should(
                    seeThat(Text.of(LoginUI.ERROR), equalTo(resultado))

            );
        }
    }
}