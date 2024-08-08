Feature: Mostrar Galeria de Productos
  # Como propietario
  # Quiero poder mostrar informacion galeria de productos
  # Para dar a conocer los productos del emprendimiento

  Scenario: Verificar que el modal muestra correctamente una imagen
    Given Quiero verificar la galeria de procesos
    When Selecciono una imagen o video de la galeria
    Then El modal deberia mostrar la imagen o video correctamente

  Scenario: Navegar entre elementos en el modal
    Given Quiero verificar la galeria de procesos
    When Selecciono una imagen o video de la galeria
    Then Debería poder navegar entre los elementos del modal



