



Feature: Project
  Scenario: como usuario quiero verificar el crud de USER por api


    Given envio un POST url para user "/api/user.json" con body


    """
   {

    "Email":"Susivargass@gmail.com",
    "FullName":"Susana Vargas",
    "Password":"12345"

  }


    """
    Then mi codigo de respuesta es 200
    And mi atributo "Email" deberia ser "Susivargass@gmail.com"


    When uso el token en todo.ly para user
    When envio un PUT url user "/api/user/0.json" con body
    """
   {

    "Email":"Susivargass@gmail.com"


   }
    """
    Then mi codigo de respuesta es 200
    And mi atributo "Email" deberia ser "Susivargass@gmail.com"
    When uso el token en todo.ly para usuario actualizado
    When envio un DELETE url user "/api/user/0.json" con body

    """
    """


    Then mi codigo de respuesta es 200
    And mi atributo "Email" deberia ser "Susivargass@gmail.com"