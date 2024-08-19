package ec.edu.espe.granjapiscicola;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

public class CotizacionAceptarStepDefinition extends BasicStepDefinition {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$";
    private static final String NAME_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
    private String nam;
    private String crr;


    @Given("Quiero enviar la cotizacion.")
    public void quiero_enviar_la_cotizacion() {
        createPDF("Enviar la cotizacion");
        addText("Inicio de prueba: Enviar la cotizacion. Requisito Funcional 05.");
        addText("Como comprador Quiero poder cotizar productos Para enviar al vendedor");
        addText(" ");
        addText("Ingresamos a la página de cotización en la sección de catálogo.");

        try {
            driver.manage().window().maximize();
            driver.get("file:///C:/Users/noobp/OneDrive/Escritorio/Requisitos%20(2)/Requisitos/Cotizar.html#");
            wait(1);
            captureScreenShot();
        }
        catch (Exception e) {
            addText("Error al cargar la página: " + e.getMessage());
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("No se pudo cargar la pagina correctamente.");

        }
    }

    @When("Doy click en el bot2 {string} se envian los datos nombre {string} y correo {string}.")
    public void doy_click_en_el_bot2_se_envian_los_datos_nombre_y_correo(String bot2, String nombre1, String correo1) {
        try {
            addText("Doy click en el primer producto " + bot2);
            // Crear una instancia de WebDriverWait con Duration
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de hasta 10 segundos

            // Buscar el botón usando el texto de enlace (COTIZAR)
            List<WebElement> quoteButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".quote-button")));
            WebElement targetButton = null;

            for (WebElement button : quoteButtons) {
                if (button.getText().equals(bot2)) {
                    targetButton = button;
                    break;
                }
            }

            if (targetButton != null) {
                targetButton.click();
                addText("Se coloca en la lista.");
                wait(1);
                captureScreenShot();
            } else {
                addText("Error: No se encontro el boton con el texto: " + bot2);
                driver.quit();
                closePDF();
                fail("No se encontro el boton con el texto: " + bot2);
            }
        } catch (Exception e) {
            addText("Error: No se pudo hacer clic en el boton: " + e.getMessage());
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("No se pudo hacer clic en el boton.");
        }
        try {
            addText("Ingresamos los datos del formulario con los datos esperados: "+nombre1+", "+correo1+", "+bot2+".");
            WebElement inputName = driver.findElement(new By.ById("name"));
            WebElement inputEmail = driver.findElement(new By.ById("email"));
            WebElement inputComment = driver.findElement(new By.ById("comment"));
            inputName.sendKeys(nombre1);
            inputEmail.sendKeys(correo1);
            inputComment.sendKeys("Deseo informacion");
        }catch (Exception e){
            addText("No se pudo obtener los datos " + e.getMessage());
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("No se pudo obtener los datos");
        }

        nam=nombre1;
        crr=correo1;
        wait(1);

    }

    @Then("Se debe validar que los productos y datos se envien correctamente de la lista.")
    public void se_debe_validar_que_los_productos_y_datos_se_envien_correctamente_de_la_lista() {
        WebElement submitButton = driver.findElement(new By.ByCssSelector("button[onclick='enviarCotizacion()']"));
        try {
            if (isValidEmail(crr)) {
                addText("Correo electrónico validado correctamente.");
            }
            else {
                addText("Correo electrónico no tiene un formato válido: " + crr);
                wait(1);
                captureScreenShot();
                addText("Fin de la prueba");
                driver.quit();
                closePDF();
                fail("Correo electronico no valido.");
            }
            if (isValidName(nam)) {
                addText("Nombre validado correctamente.");
                captureScreenShot();
                wait(2);
            }
            else {
                addText("Nombre no válido. Contiene caracteres no permitidos: " + nam);
                wait(1);
                captureScreenShot();
                addText("Fin de la prueba");
                driver.quit();
                closePDF();
                fail("Nombre no valido.");
            }
        }catch (Exception e){
            addText("No se pudo obtener los datos " + e.getMessage());
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("No se pudo obtener los datos");

        }

        try {
            // Localizar el contenedor de la lista de productos cotizados
            WebElement productListContainer = driver.findElement(By.id("productListItems"));
            // Obtener todos los elementos dentro de la lista
            List<WebElement> productItems = productListContainer.findElements(By.tagName("li"));
            // Verificar si la lista contiene productos cotizados
            if (productItems.size() > 0) {
                addText("La lista de productos cotizados contiene " + productItems.size() + " elementos.");
            } else {
                addText("Error: La lista de productos cotizados está vacia.");
                captureScreenShot();
                driver.quit();
                closePDF();
                fail("No se ingresaron productos en la lista.");

            }
        } catch (Exception e) {
            addText("Error durante la validación de productos en la lista: " + e.getMessage());
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("Ocurrio un error al intentar validar la lista de productos.");
        }

        addText("Una vez ingresado los datos, el sistema valida la informacion y la lista de productos");
        addText("Se da click en enviar cotización");

        submitButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String whatsappUrl = "https://web.whatsapp.com";

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.contains(whatsappUrl)) {
            wait(5);
            addText("No estamos en la página de WhatsApp Web. ");
            captureScreenShot();
            addText("Fin de la prueba");
            driver.quit();
            closePDF();
            fail("No estamos en la pagina de WhatsApp Web");
        }
        else {
            wait(2);
            addText("Ingresado con exito. URL actual. ");
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

