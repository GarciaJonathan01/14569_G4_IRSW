package ec.edu.espe.granjapiscicola;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static org.junit.Assert.fail;

public class LoginStepDefinition extends BasicStepDefinition {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$";
    private static final String NAME_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
    private String nam;
    private String crr;

    @Given("Quiero comunicarme con el vendedor")
    public void quiero_comunicarme_con_el_vendedor() {
        createPDF("Comunicacion");
        addText("\t\tInicio de prueba: Requisito Funcional 01.");
        addText("Como comprador Quiero poder comunicarme con el vendedor Para pedir cualquier tipo de informacion");
        addText("Ingresamos a la pagina web en la seccion de Contactos");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("file:///C:/Users/noobp/OneDrive/Escritorio/Requisitos (2)/Requisitos/Maqueta.html");
        captureScreenShot();
    }
    @When("Ingreso el nombre {string} el correo {string} y el comentario {string}")
    public void ingreso_el_nombre_el_correo_y_el_comentario(String nombre, String correo, String comentario) {
        addText("Ingreso el nombre " + nombre + " el correo " + correo + " y el comentario "+comentario);
        WebElement inputName = driver.findElement(new By.ById("name"));
        WebElement inputEmail = driver.findElement(new By.ById("email"));
        WebElement inputComment = driver.findElement(new By.ById("comment"));
        WebElement submitButton = driver.findElement(new By.ByCssSelector("button[onclick='enviarMensaje()']"));
        inputName.sendKeys(nombre);
        inputEmail.sendKeys(correo);
        inputComment.sendKeys(comentario);
        nam=nombre;
        crr=correo;


        wait(1);
        captureScreenShot();
        submitButton.click();
    }

    @Then("Se debe validar que el correcto y el nombre sean correctos")
    public void se_debe_validar_que_el_correcto_y_el_nombre_sean_correctos() {

        if (isValidEmail(crr)) {
            addText("Correo electrónico validado correctamente.");
        } else {
            addText("Correo electrónico no tiene un formato válido: " + crr);
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("Correo electrónico no válido.");
        }
        if (isValidName(nam)) {
            addText("Nombre validado correctamente.");
        } else {
            addText("Nombre no válido. Contiene caracteres no permitidos: " + nam);
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("Nombre no válido.");
        }

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        addText("Con datos correctos ya ingresados el sistema nos lleva al chat del whatsapp del vendedor");

        String whatsappUrl = "https://web.whatsapp.com";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains(whatsappUrl)) {
            wait(5);
            addText("No estamos en la página de WhatsApp Web. URL actual: " + currentUrl);
            captureScreenShot();
            addText("Fin de la prueba");
            driver.quit();
            closePDF();
            fail("No estamos en la página de WhatsApp Web");
        }else {
            wait(2);
            addText("Ingresado con exito. URL actual: " + currentUrl);
            captureScreenShot();
        }
        wait(1);
        addText("Fin de la prueba");
        driver.quit();
        closePDF();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
