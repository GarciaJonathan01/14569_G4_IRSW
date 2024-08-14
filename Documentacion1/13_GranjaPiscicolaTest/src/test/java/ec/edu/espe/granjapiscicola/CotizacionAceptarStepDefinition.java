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


    @Given("Quiero enviar la cotizacion")
    public void quiero_enviar_la_cotizacion() {
        createPDF("Enviar la cotizacion");
        addText("Inicio de prueba: Enviar la cotizacion.");
        addText("Como comprador");
        addText("Quiero poder cotizar productos");
        addText("Para enviar al vendedor\n");

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            addText("Iniciamos abriendo la pagina web");
            driver.get("file:///C:/Users/noobp/OneDrive/Escritorio/Requisitos%20(2)/Requisitos/Cotizar.html#");
            wait(1);
            captureScreenShot();
        } catch (Exception e) {
            addText("Error al cargar la página: " + e.getMessage());
            captureScreenShot();
            fail("No se pudo cargar la página correctamente.");
            driver.quit();
            closePDF();
        }
    }

    @When("Doy click en el bot2 {string} se envian los datos nombre {string} y correo {string}")
    public void doy_click_en_el_bot2_se_envian_los_datos_nombre_y_correo(String bot2, String nombre, String correo) {
        addText("Doy click en el primer producto " + bot2);
        try {
            WebElement inputName = driver.findElement(new By.ById("name"));
            WebElement inputEmail = driver.findElement(new By.ById("email"));
            WebElement inputComment = driver.findElement(new By.ById("comment"));
            inputName.sendKeys(nombre);
            inputEmail.sendKeys(correo);
            inputComment.sendKeys("Deseo informacion");
            wait(2);
            captureScreenShot();



        }catch (Exception e){
            addText("No se pudo obtener los datos " + e.getMessage());
            captureScreenShot();
            fail("No se pudo obtener los datos");
        }
        try {
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
            } else {
                addText("Error: No se encontró el botón con el texto: " + bot2);
                fail("No se encontró el botón con el texto: " + bot2);
            }
        } catch (Exception e) {
            addText("Error: No se pudo hacer clic en el botón: " + e.getMessage());
            captureScreenShot();
            fail("No se pudo hacer clic en el botón.");
        }


        nam=nombre;
        crr=correo;


    }

    @Then("Se debe validar que los productos y datos se envien correctamente de la lista")
    public void se_debe_validar_que_los_productos_y_datos_se_envien_correctamente_de_la_lista() {
        addText("Ahora ingresamos los datos de nombre, correo y comentario esperado: "+nam+", "+crr+" y deseo informacion.");
        wait(2);
        try {
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
        }catch (Exception e){
            addText("No se pudo obtener los datos " + e.getMessage());
            captureScreenShot();
            fail("No se pudo obtener los datos");
            driver.quit();
            closePDF();
        }

        try {
            // Localizar el contenedor de la lista de productos cotizados
            WebElement productListContainer = driver.findElement(By.id("productListItems"));

            // Obtener todos los elementos dentro de la lista
            List<WebElement> productItems = productListContainer.findElements(By.tagName("li"));

            // Verificar si la lista contiene productos cotizados
            if (productItems.size() > 0) {
                addText("La lista de productos cotizados contiene " + productItems.size() + " elementos.");
                captureScreenShot();
            } else {
                addText("Error: La lista de productos cotizados está vacía.");
                captureScreenShot();
                fail("No se ingresaron productos en la lista.");
                closePDF();
                driver.quit();

            }
        } catch (Exception e) {
            addText("Error durante la validación de productos en la lista: " + e.getMessage());
            captureScreenShot();
            fail("Ocurrió un error al intentar validar la lista de productos.");
            driver.quit();
            closePDF();
        }

        addText("Una vez ingresado los datos, el sistema valida la información y la lista de productos");

        WebElement submitButton = driver.findElement(new By.ByCssSelector("button[onclick='enviarCotizacion()']"));
        submitButton.click();

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

