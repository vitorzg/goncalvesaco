# GonçalvesAço.com.br


## Pré-requisitos ⚙️

Antes de começar, certifique-se de que os seguintes itens estão instalados no seu sistema:

* **Java Development Kit (JDK)**
    - Versão mínima: 23

* **Apache Maven**
    - Certifique-se de que o Maven está configurado no `PATH`.

* **Git**
    - Para clonar o repositório.

* **Docker**
    - Recomenda-se o uso do Docker para subir o banco de dados.

## Passos para Instalação 🚀

1. **Clone o Repositório**  

   Execute o comando abaixo para clonar o repositório em sua máquina local:

   ```bash
   git clone git@github.com:vitorzg/goncalvesaco.git
   
2. **Navegue até o Diretório do Projeto**

    Entre na pasta onde o projeto foi clonado:

    ```bash
   cd goncalvesaco
   
3. **Configure as Dependências**

   Utilize o Maven para baixar as dependências necessárias:

    ```bash
   mvn clean install
   
4. **Configure o Application.properties de desenvolvimento**
    
    Ajuste as configurações abaixo para seu caso em particular:

    ```yaml
   # Configurações do banco de dados
    spring.datasource.url=jdbc:postgresql://localhost:5432/{DataBaseName}
    spring.datasource.username={DataBaseUSer}
    spring.datasource.password={DataBasePassword}
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    
    # Configurações de JPA
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true


5. **Inicie a Aplicação**

    Use o seguinte comando para iniciar o servidor Spring:

    ```bash
   mvn spring-boot:run
