Feature: Mostrar Galeria de Productos
  # Como propietario
  # Quiero poder mostrar informacion galeria de productos
  # Para dar a conocer los productos del emprendimiento

  Scenario Outline: Verificar que el modal muestra correctamente una imagen Passed
    Given Quiero verificar la galeria de procesos
    When Selecciono una imagen o video de la galeria "<imagen>"
    Then El modal deberia mostrar la imagen o video correctamente

    Examples:
      | imagen |
      |    1   |

  Scenario Outline: Verificar que el modal muestra correctamente una imagen Failed
    Given Quiero verificar la galeria de procesos
    When Selecciono una imagen o video de la galeria "<imagen>"
    Then El modal deberia mostrar la imagen o video correctamente

    Examples:
      | imagen |
      |    2   |



