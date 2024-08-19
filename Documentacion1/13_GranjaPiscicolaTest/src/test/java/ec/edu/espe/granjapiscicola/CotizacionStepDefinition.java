package ec.edu.espe.granjapiscicola;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import java.util.List;
import static org.junit.Assert.fail;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CotizacionStepDefinition extends BasicStepDefinition {

    @Given("Quiero cotizar productos")
    public void quiero_cotizar_productos() {
        createPDF("Cotizacion de productos.");
        addText("Inicio de prueba: Cotizar productos. Requisito Funcional 04.");
        addText("Como comprador Quiero poder cotizar productos");
        addText("Para enviar al vendedor");
        addText(" ");
        addText("Ingresamos a la página del catálogo de productos.");

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("file:///C:/Users/noobp/OneDrive/Escritorio/Requisitos%20(2)/Requisitos/Cotizar.html#");
            addText("El sistema muestra una lista de productos con sus descripciones de imagenes y precios.");
            addText(" ");
            wait(1);
            captureScreenShot();
        } catch (Exception e) {
            addText("Error al cargar la página: " + e.getMessage());
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("No se pudo cargar la pagina correctamente.");

        }
    }

    @When("Doy click en el bot1 {string}")
    public void doy_click_en_el_bot1(String bot1) {
        addText("El comprador puede seleccionar un producto para añadir a la lista.");
        addText("Se da click en el primer producto " + bot1);
        try {
            // Crear una instancia de WebDriverWait con Duration
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de hasta 10 segundos

            // Buscar el botón usando el texto de enlace (COTIZAR)
            List<WebElement> quoteButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".quote-button")));
            WebElement targetButton = null;

            for (WebElement button : quoteButtons) {
                if (button.getText().equals(bot1)) {
                    targetButton = button;
                    break;
                }
            }

            if (targetButton != null) {
                targetButton.click();
                addText("Se coloca en la lista");
            } else {
                addText("Error: No se encontró el botón con el texto: " + bot1);
                addText("Fin de la prueba");
                driver.quit();
                closePDF();
                fail("No se encontro el boton con el texto: " + bot1);
            }
        } catch (Exception e) {
            addText("Error: No se pudo hacer clic en el botón: " + e.getMessage());
            captureScreenShot();
            driver.quit();
            closePDF();
            fail("No se pudo hacer clic en el boton.");
        }
        wait(1);
        captureScreenShot();
    }

    @Then("Se debe validar que los productos se ingresen correctamente en la lista")
    public void se_debe_validar_que_los_productos_se_ingresen_correctamente_en_la_lista() {
        try {
            // Localizar el contenedor de la lista de productos cotizados
            WebElement productListContainer = driver.findElement(By.id("productListItems"));

            // Obtener todos los elementos dentro de la lista
            List<WebElement> productItems = productListContainer.findElements(By.tagName("li"));

            // Verificar si la lista contiene productos cotizados
            if (productItems.size() > 0) {
                addText("La lista de productos cotizados contiene " + productItems.size() + " elementos.");
            } else {
                addText("Error: La lista de productos cotizados está vacía.");
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
        addText("Fin de la prueba");
        driver.quit();
        closePDF();

    }
}

