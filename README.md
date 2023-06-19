## Rest Assured


# Setup PC
- JDK 8 ou superior
- Eclipse IDE for Java Developers
- Configurar as variáveis de ambiente (ex: JAVA_HOME) de acordo com SO
- Postman


# Setup Prjeto (Maven dependencies)
- rest-assured 4.4.0 (https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.4.0)
- junit 4.12 (https://mvnrepository.com/artifact/junit/junit/4.12)
- gson 2.8.5 (https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5)

# Cenários back-end
| Nome dos Cenários              |
|--------------------------------|
| Cenário 1: validateProductList |
| test_createAccount_1           |
| test_getUserDetailByEmail_2    |
| test_updateAccount3            |


# Execução dos Cenário:
- Os testes devem ser executados como 'Junit Test'
- A execução pode ser feita individualmente nas classes 'Users.java' e/ou 'Products.java'
- A execução pode ser feita por meio da suite de testes 'Runner.java'

# Obs.:
- Junto ao projeto na pasta src/main/resources/colections há um arquivo onde estão os testes que realizei manualmente

# Resources
##### [Rest Assured](https://rest-assured.io)
##### [Junit](https://junit.org/)
