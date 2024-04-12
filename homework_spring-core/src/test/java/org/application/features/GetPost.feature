Feature:
  Verify different GET operations using REST-assured

  Scenario: Verify one task of list
    Given Perform GET operation for "http://localhost:8080/api/v1/task/get"
    Then Should see the status as "DONE"

  Scenario: Verify collection of tasks
    Given Perform GET operation for "http://localhost:8080/api/v1/task/get"
    Then Should see the task statuses

  Scenario: Verify parameter GET
    Given Perform GET operation for "http://localhost:8080/api/v1/task/get"
    Then Should verify GET parameters

#    Scenario: Verify Post operation
#      Given Perform Post operation for "http://localhost:8080/api/v1/user/create"

      Scenario: Verify POST operation for createTask
        Given Perform POST operation for "http://localhost:8080/api/v1/task/create" with body
          |   id   | name | status | description | deadline | priority |
          |   45   | test |  TO_DO |    test     |2024-04-05|    4     |
        Then Should see the body has taskId 15