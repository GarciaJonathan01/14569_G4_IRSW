package ec.edu.espe.granjapiscicola;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class InformacionGeneralStepDefinition extends BasicStepDefinition {

    private String pag;

    @Given("Quiero visualizar informacion")
    public void quiero_visualizar_informacion() {
        createPDF("Informacion General");
        addText("Inicio de prueba: Quiero mostrar informacion");
        addText("Como comprador Quiero visualizar la informacion de la pagina web");
        addText("Para  conocer el proposito del emprendimiento");
        addText(" ");
        addText("Ingresamos a la pagina web inicial");


    }
    @When("Ingreso a la pagina web {string} visualizo la informacion inicial")
    public void ingreso_a_la_pagina_web_visualizo_la_informacion_inicial(String pagina){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(pagina);
        pag=pagina;

        wait(1);
        captureScreenShot();

    }

    @Then("Se debe validar que se ingrese correctamente a la pagina web")
    public void se_debe_validar_que_se_ingrese_correctamente_a_la_pagina_web() {
        wait(1);
        String currentUrl = driver.getCurrentUrl();

        String specificUrl = "file:///C:/Users/noobp/OneDrive/Escritorio/Requisitos%20(2)/Requisitos/Pagina_Inicial.html";
        if (currentUrl.equals(pag)) {
            addText("Pagina correcta. "+ currentUrl);
            captureScreenShot();

        } else {
            addText("Pagina incorrecta: "+ pag+ " se esperaba: "+ specificUrl);
            captureScreenShot();
            addText("Fin de la prueba");
            driver.quit();
            closePDF();
            fail("Pagina no válida.");
        }
        addText("Fin de la prueba");
        driver.quit();
        closePDF();
    }

}

