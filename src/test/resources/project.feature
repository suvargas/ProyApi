Feature: Project
  Scenario: como usuario quiero verificar el crud del project por api

    Given que uso el token en todo.ly
    When envio un POST url "/api/projects.json" con body
    """
    {
      "Content":"Cucumber",
      "Icon":1
    }
    """
    Then mi codigo de respuesta es 200
    And mi atributo "Content" deberia ser "Cucumber"
    And guardo mi Id en la variable IdProject
    When envio un PUT url "/api/projects/IdProject.json" con body
    """
    {
      "Content":"Cucumber2"
    }
    """
    Then mi codigo de respuesta es 200
    And mi atributo "Content" deberia ser "Cucumber2"
    When envio un DELETE url "/api/projects/IdProject.json" con body
    """
    """
    Then mi codigo de respuesta es 200
    And mi atributo "Content" deberia ser "Cucumber2"