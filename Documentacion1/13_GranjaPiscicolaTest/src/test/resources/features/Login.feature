Feature: Comunicarse con el vendedor
  # Como comprador
  # Quiero poder comunicarme con el vendedor
  # Para pedir cualquier tipo de informacion

  Scenario Outline: Validar que se pueda ingresar datos como correo electronico y comentarios Passed
    Given Quiero comunicarme con el vendedor
    When Ingreso el nombre "<nombre>" el correo "<correo>" y el comentario "<comentario>"
    Then Se debe validar que el correcto y el nombre sean correctos

    Examples:
      | nombre          | correo                 | comentario        |
      | Jonathan Garcia | jdgarcia15@hotmail.com | Deseo informacion |

  Scenario Outline: Validar que se pueda ingresar datos como nombre, correo electronico y comentarios Failed
    Given Quiero comunicarme con el vendedor
    When Ingreso el nombre "<nombre>" el correo "<correo>" y el comentario "<comentario>"
    Then Se debe validar que el correcto y el nombre sean correctos

    Examples:
      | nombre            | correo             | comentario        |
      | Fernando Tipan843 | frtipan1@gmail.com | Deseo informacion |

  Scenario Outline: Validar que se pueda ingresar datos como nombre, correo electronico y comentarios Failed 2
    Given Quiero comunicarme con el vendedor
    When Ingreso el nombre "<nombre>" el correo "<correo>" y el comentario "<comentario>"
    Then Se debe validar que el correcto y el nombre sean correctos

    Examples:
      | nombre            | correo             | comentario        |
      | John Limones | jjlimones@espe.crud | Deseo informacion |

  Scenario Outline: Validar que se pueda ingresar datos como nombre, correo electronico y comentarios Failed 3
    Given Quiero comunicarme con el vendedor
    When Ingreso el nombre "<nombre>" el correo "<correo>" y el comentario "<comentario>"
    Then Se debe validar que el correcto y el nombre sean correctos

    Examples:
      | nombre            | correo             | comentario        |
      |  |  |  |




