Feature: Comunicarse con el vendedor
  # Como comprador
  # Quiero poder comunicarme con el vendedor
  # Para pedir cualquier tipo de informacion

  Scenario Outline: Validar que se pueda ingresar los productos correctamente a la lista
  : Validar que se pueda ingresar datos como nombre, correo electronico y comentarios
    Given Quiero comunicarme con el vendedor
    When Ingreso el nombre "<nombre>" el correo "<correo>" y el comentario "<comentario>"
    Then Se debe validar que el correcto y el nombre sean correctos

    Examples:
      | nombre            | correo             | comentario        |
      | Fernando Tipan843 | frtipan1@gmail.com | Deseo información |

  Scenario Outline: Validar que se pueda ingresar datos como correo electronico y comentarios
    Given Quiero comunicarme con el vendedor
    When Ingreso el nombre "<nombre>" el correo "<correo>" y el comentario "<comentario>"
    Then Se debe validar que el correcto y el nombre sean correctos

    Examples:
      | nombre          | correo                 | comentario        |
      | Jonathan Garcia | jdgarcia15@hotmail.com | Deseo información |

  Scenario Outline: Validar que se pueda ingresar datos como correo electronico y comentarios
    Given Quiero comunicarme con el vendedor
    When Ingreso el nombre "<nombre>" el correo "<correo>" y el comentario "<comentario>"
    Then Se debe validar que el correcto y el nombre sean correctos

    Examples:
      | nombre          | correo                 | comentario        |
      | John Limones | jjlimones@espe.crud | Deseo información |
      |              |                     |                   |



