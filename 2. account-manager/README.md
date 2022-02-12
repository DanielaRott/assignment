account-manager
=========================
A simple RESTful API for checking the account balance and making a withdrawal operation.

Business workflow
----------
- The purpose of this application is to give users the possibility to check their account balance and to
make a withdrawal operation (amount should be multiple of 10) at the ATM;
- The user is a person with a valid bank account;
- The ATM where the user wants to make the transaction may or may not contain the amount requested by the user;
- If the user situation passes all the checks, the cash is dispensed to the user, the account balance
and also the ATM cash situation is updated.

Prerequisites
-------------
- JDK 11
- Maven 3.2+
or
- Docker

Run the application - 1st option
---
1```mvn clean install```
2```mvn spring-boot:run```

Build an executable JAR
---
1. ```mvn clean package```
2. ```java -jar target/account-manager-0.0.1.jar```

Run the application - 2nd option
---
1. ```docker-compose build```
2. ```docker-compose up```

Testing
---
1. Performs a withdrawal transaction
- POST http://localhost:8080/api/v1/atm/withdraw
  RequestBody
```json
{
  "accountId": "4a8a7c36-d32a-4101-a161-177e6178c7e6",
  "atmId": "fbdae6dc-5ea0-4293-ad8a-d8d0f294fbe5",
  "amount": 3430
}
```

2. Fetches all ATM details
- GET http://localhost:8080/api/v1/atm

3. Fetches ATM details by id
- GET http://localhost:8080/api/v1/atm/fbdae6dc-5ea0-4293-ad8a-d8d0f294fbe5

4. Fetches all Account details
- GET http://localhost:8080/api/v1/accounts

5. Fetches Account details by id
- GET http://localhost:8080/api/v1/accounts/4a8a7c36-d32a-4101-a161-177e6178c7e6

## Author
Daniela Rott