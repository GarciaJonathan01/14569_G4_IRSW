package ec.edu.espe.granjapiscicola;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.fail;

public class GaleriaPPStepDefinition extends BasicStepDefinition {

    @Given("Quiero verificar la galeria de procesos")
    public void quiero_verificar_la_galeria_de_procesos() {
        createPDF("Galeria de Procesos de Producción");
        addText("Inicio de prueba: Verificar la galería de procesos.");
        addText("");
        addText("Como comprador Quiero poder mostrar informacion galeria de productos");
        addText("Para dar a conocer los productos del emprendimiento");
        addText("");
        addText("Ingresamos a la pagina web en la seccion Inicio");

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("file:///C:/Users/noobp/OneDrive/Escritorio/Requisitos (2)/Requisitos/Visualizar_Producto.html");
        wait(1);
        captureScreenShot();
    }

    @When("Selecciono una imagen o video de la galeria {string}")
    public void selecciono_una_imagen_o_video_de_la_galeria(String imagen) {
        if (imagen.equals("1")) {
            WebElement imageElement = driver.findElement(By.id("card-cosecha")).findElement(By.tagName("img"));
            imageElement.click(); // Abre la imagen en el modal
            addText("Se selecciona la primera imagen:");
            wait(1);
            captureScreenShot();
        } else if (imagen.equals("2")) {
            WebElement imageElement = driver.findElement(By.id("card-procesamiento")).findElement(By.tagName("img"));
            imageElement.click(); // Abre la imagen en el modal
            addText("Se selecciona la segunda imagen:");
            wait(1);
            captureScreenShot();
        }
    }

    @Then("El modal deberia mostrar la imagen o video correctamente")
    public void el_modal_deberia_mostrar_la_imagen_o_video_correctamente() {
        WebElement modal = driver.findElement(By.id("modal"));
        WebElement modalImage = driver.findElement(By.id("modal-image"));

        // Verifica si el modal está visible
        if (modal.isDisplayed()) {
            String imageSrc = modalImage.getAttribute("src");

            // Verifica si el src de la imagen no está vacío y tiene una imagen válida
            if (modalImage.isDisplayed() && imageSrc != null && !imageSrc.isEmpty()) {
                addText("Modal mostrado correctamente con la imagen.");
                captureScreenShot();
            } else {
                addText("Error: La imagen no se cargó correctamente en el modal.");

                // Cerrar el modal y fallar la prueba
                WebElement closeModal = driver.findElement(By.className("close"));
                closeModal.click();
                wait(1);
                addText("Cerrando modal");
                captureScreenShot();
                addText("Fin de la prueba.");
                driver.quit();
                closePDF();
                fail("El modal no muestra una imagen válida.");
            }
        } else {
            addText("Error: El modal no se mostró correctamente.");
            captureScreenShot();
            addText("Fin de la prueba.");
            driver.quit();
            closePDF();
            fail("El modal no se muestra correctamente.");
        }
        driver.quit();
        closePDF();
    }


}
