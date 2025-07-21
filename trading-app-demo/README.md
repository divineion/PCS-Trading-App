# spring-boot
## Technical:

1. Spring Boot 3.1.0
2. Java 17
3. Thymeleaf
4. Bootstrap v.4.3.1


## Setup with Intellij IDE
1. Clone the project and open it. 
3. Project folders
    - Source root: src/main/java
    - View: src/main/resources/templates
    - Static: src/main/resource/static
4. Create database with name "pcs_trading_demo" as configuration in application.properties
5. Run application to create tables and insert data. 

## Implement a Feature
1. Create a domain entity in the package com.pcs.tradingapp.domain.
2. Create one or more DTOs in `com.pcs.tradingapp.dto.request.[entity]` (for request objects)
or `com.pcs.tradingapp.dto.response` (for response objects).   
If multiple response DTOs exist for a given entity, use separate subpackages in response for each entity (response.[entity]).
3. Create repository interface in package `com.pcs.tradingapp.repositories`.
4. Implement service logic in the appropriate `com.pcs.tradingapp.services.[entity]` package.
5. Create a controller class in package `com.pcs.tradingapp.controllers`

## Security
1. User management and authentication logic in `com.pcs.tradingapp.config`.
2. Security configuration in `com.pcs.tradingapp.config`.