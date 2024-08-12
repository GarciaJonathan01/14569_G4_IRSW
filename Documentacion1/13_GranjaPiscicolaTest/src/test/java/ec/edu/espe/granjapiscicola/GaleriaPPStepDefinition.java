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
        addText("Como comprador");
        addText("Quiero poder mostrar informacion galeria de productos");
        addText("Para dar a conocer los productos del emprendimiento");

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("file:///C:/Users/noobp/OneDrive/Escritorio/Nueva%20carpeta/Nueva%20carpeta/Requisitos%20(1)/Requisitos/Visualizar_Producto.html");
        wait(1);
        captureScreenShot();
    }

    @When("Selecciono una imagen o video de la galeria")
    public void selecciono_una_imagen_o_video_de_la_galeria() {
        WebElement imageElement = driver.findElement(By.id("card-cosecha")).findElement(By.tagName("img"));
        imageElement.click(); // Abre la imagen en el modal
        addText("Se selecciona la primera imagen:");
        wait(1);
        captureScreenShot();
    }

    @Then("El modal deberia mostrar la imagen o video correctamente")
    public void el_modal_deberia_mostrar_la_imagen_o_video_correctamente() {
        WebElement modal = driver.findElement(By.id("modal"));
        WebElement modalImage = driver.findElement(By.id("modal-image"));

        if (modal.isDisplayed() && modalImage.isDisplayed()) {
            addText("Modal mostrado correctamente con la imagen.");
        } else {
            addText("Error: Modal no se mostró correctamente.");
            fail("El modal no se muestra correctamente.");
        }

        captureScreenShot();

        // Cerrar el modal
        WebElement closeModal = driver.findElement(By.id("close-modal"));
        closeModal.click();
        wait(1);
        addText("Cerrando modal");
        captureScreenShot();

        // Verifica si el modal se cerró correctamente
        if (!modal.isDisplayed()) {
            addText("Modal cerrado correctamente.");
        } else {
            addText("Error: Modal no se cerró.");
            driver.quit();
            closePDF();
            fail("El modal no se cierra correctamente.");
        }
        addText("Fin de la prueba.");

        driver.quit();
        closePDF();
    }

    @Then("Debería poder navegar entre los elementos del modal")
    public void deberia_poder_navegar_entre_los_elementos_del_modal() {
        WebElement nextButton = driver.findElement(By.id("next-slide"));
        WebElement prevButton = driver.findElement(By.id("prev-slide"));

        // Verifica navegación hacia adelante
        nextButton.click();
        wait(1);
        captureScreenShot();
        addText("Navegación hacia el siguiente elemento realizada.");

        // Verifica navegación hacia atrás
        prevButton.click();
        wait(1);
        captureScreenShot();
        addText("Navegación hacia el elemento anterior realizada.");

        // Cerrar el modal
        WebElement closeModal = driver.findElement(By.id("close-modal"));
        closeModal.click();
        wait(1);
        captureScreenShot();

        // Verifica si el modal se cerró correctamente
        if (!driver.findElement(By.id("modal")).isDisplayed()) {
            addText("Modal cerrado correctamente después de la navegación.");
        } else {
            addText("Error: El modal no se cerró después de la navegación.");
            driver.quit();
            closePDF();
            fail("El modal no se cierra correctamente después de la navegación.");
        }

        addText("Fin de la prueba de navegación en el modal.");
        driver.quit();
        closePDF();
    }


}
