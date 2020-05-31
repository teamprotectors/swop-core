# Swop 

This app allow us malke several exchanges between people.

this project include:

     - product-catalog domain
     - users domain
     - swop cart
     
     
     
<div align="center">
<img src="https://github.com/teamprotectors/swop-core/blob/develop/doc/domain_model.png" width="1250"/>
</div>
     
services:

| Domain          | Url                          | Type | Description                                              |
|-----------------|------------------------------|------|-----------------------------------------------------     |
| Product Catalog | http://127.0.0.1:8085/products    | GET  | Returns all items saved in product catalog database |
| Product Catalog | http://127.0.0.1:8085/products    | POST | Save a new item in product catalog database         |
| Cart Shop       | http://127.0.0.1:8086/cart/idcart | GET  | Returns a cart shop by id                           |
| Cart Shop       | http://127.0.0.1:8086/cart    | POST | Save a new cart shop                                |
| Cart Shop       | http://127.0.0.1:8086/cart    | GET | Get all products requested by users                                |
| Users           | http://127.0.0.1:8081/users | idUser  | Get an existent user                                   |
| Users           | http://127.0.0.1:8081/users    | POST | Register new user                                      |

