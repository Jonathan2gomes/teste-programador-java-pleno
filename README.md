# Summary

[Api controllers](#api-controllers)

[Order Service](#order-service)

# order-service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/order-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and more
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

# API Controllers

This repository contains the API controllers for managing orders, customers, and products.

## OrderController

### Create Order

- Method: POST
- Endpoint: `/order`
- Request Body: JSON
```json
{
  "descricao": "Order description",
  "listaProdutos": [1, 2, 3],
  "customerId": 12345
}
```
- Response: 201 Created

Create a new order by providing the order details in the request body.

### Get All Orders

- Method: GET
- Endpoint: `/order`
```
{
  "paginaAtual": "1",
  "totalPaginas": "5",
  "totalItens": "25",
  "itens": [
    {
      "codigo": 1,
      "dataEmissao": "2023-01-01",
      "descricao": "Order 1",
      "valorTotal": "R$100.00",
      "listaProdutos": [
        {
          "codigo": 1,
          "descricao": "Product 1",
          "unidade": "Unit",
          "valor": "R$10.00"
        },
        {
          "codigo": 2,
          "descricao": "Product 2",
          "unidade": "Unit",
          "valor": "R$20.00"
        }
      ]
    },
    {
      "codigo": 2,
      "dataEmissao": "2023-01-02",
      "descricao": "Order 2",
      "valorTotal": "R$150.00",
      "listaProdutos": [
        {
          "codigo": 3,
          "descricao": "Product 3",
          "unidade": "Unit",
          "valor": "R$30.00"
        }
      ]
    }
  ]
}
```
- Response: 200 OK

Retrieve all orders.

### Get Order by Code

- Method: GET
- Endpoint: `/order/{code}`
```
{
  "codigo": 1,
  "dataEmissao": "2023-01-01",
  "descricao": "Order 1",
  "valorTotal": "R$100.00",
  "listaProdutos": [
    {
      "codigo": 1,
      "descricao": "Product 1",
      "unidade": "Unit",
      "valor": "R$10.00"
    },
    {
      "codigo": 2,
      "descricao": "Product 2",
      "unidade": "Unit",
      "valor": "R$20.00"
    }
  ]
}
```
- Response: 200 OK

Retrieve an order by its code.

### Update Order

- Method: PUT
- Endpoint: `/order/update/{code}`
- Request Body: JSON
```
{
  "descricao": "Updated order description",
  "listaProdutos": [4, 5, 6],
  "customerId": 12345
}
```
- Response: 200 OK

Update an existing order by providing the updated order details in the request body.

### Delete Order

- Method: DELETE
- Endpoint: `/order/{code}`
- Response: 200 OK

Delete an order by its code.


## CustomerController

### Get Customer

- Method: GET
- Endpoint: `/customer/{code}`
```
{
  "codigo": 1,
  "nome": "John Doe",
  "telefone": "(123) 45678-9101",
  "cpf": "123.456.789-01",
  "email": "john.doe@example.com"
}
```
- Response: 200 OK

Retrieve a customer by their code.

### Get Customers (Paginated)

- Method: GET
- Endpoint: `/customer/pageable`
- Query Parameters:
    - `page` (optional): Page number (default: 1)
    - `size` (optional): Page size (default: 10)
```
{
  "paginaAtual": "1",
  "totalPaginas": "3",
  "totalItens": "25",
  "itens": [
    {
      "codigo": 1,
      "nome": "John Doe",
      "telefone": "(123) 45678-9101",
      "cpf": "123.456.789-01",
      "email": "john.doe@example.com"
    },
    {
      "codigo": 2,
      "nome": "Jane Smith",
      "telefone": "(987) 65432-1098",
      "cpf": "987.654.321-09",
      "email": "jane.smith@example.com"
    }
  ]
}
```
- Response: 200 OK

Retrieve customers in a paginated format.

### Create Customer

- Method: POST
- Endpoint: `/customer`
- Request Body: JSON
```
{
  "nome": "John Doe",
  "telefone": "(12) 34567-8901",
  "cpf": "123.456.789-00",
  "email": "john.doe@example.com"
}
```
- Response: 201 Created

Create a new customer by providing the customer details in the request body.

### Update Customer

- Method: PUT
- Endpoint: `/customer/update/{code}`
- Request Body: JSON
```
{
  "nome": "John Doe",
  "telefone": "(12) 34567-8901",
  "cpf": "123.456.789-00",
  "email": "john.doe@example.com"
}
```
- Response: 200 OK

Update an existing customer by providing the updated customer details in the request body.

### Delete Customer

- Method: DELETE
- Endpoint: `/customer/delete/{code}`
- Response: 200 OK

Delete a customer by their code.


## ProductController

### Get All Products (Paginated)

- Method: GET
- Endpoint: `/product/pageable`
- Query Parameters:
    - `page` (optional): Page number (default: 1)
    - `size` (optional): Page size (default: 10)
```
{
  "paginaAtual": "1",
  "totalPaginas": "2",
  "totalItens": "10",
  "itens": [
    {
      "codigo": 1,
      "descricao": "Product 1",
      "unidade": "Unit",
      "valor": "R$10.00"
    },
    {
      "codigo": 2,
      "descricao": "Product 2",
      "unidade": "Unit",
      "valor": "R$20.00"
    }
  ]
}
```
- Response: 200 OK

Retrieve all products in a paginated format.

### Get Product by Code

- Method: GET
- Endpoint: `/product/{code}`
```
{
  "codigo": 1,
  "descricao": "Product 1",
  "unidade": "Unit",
  "valor": "R$10.00"
}
```
- Response: 200 OK

Retrieve a product by its code.

### Create Product

- Method: POST
- Endpoint: `/product`
- Request Body: JSON
```
{
  "descricao": "Product description",
  "unidade": "Unit",
  "valor": 9.99
}
```

- Response: 201 Created

Create a new product by providing the product details in the request body.

### Update Product

- Method: PUT
- Endpoint: `/product/update/{code}`
- Request Body: JSON
```
{
"descricao": "Product description",
"unidade": "Unit",
"valor": 9.99
}
```
- Response: 200 OK

Update an existing product by providing the updated product details in the request body.

### Delete Product

- Method: DELETE
- Endpoint: `/product/delete/{code}`
- Response: 200 OK

Delete a product by its code.


