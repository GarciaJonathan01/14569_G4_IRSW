Feature: Mostrar Información General
  # Como comprador
  # Quiero visualizar la informacion de la pagina web
  # Para  conocer el proposito del emprendimiento

  Scenario: Validar que se ingrese correctamente a la pagina web
    Given Quiero visualizar informacion
    When Ingreso a la pagina web "<pagina>" visualizo la informacion inicial
    Then Se debe validar que se ingrese correctamente a la pagina web

    Examples:
      | pagina               |
      | https://youtube.com/ |




