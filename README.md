# Game Of Three

### Description
Game of three - coding challenge

The goal is to implement a back end part of “computer-human” game communicating with each other using a web interface.

The game can be started by the computer or by the human player.

Game starts with some message to be sent to API, containing the new game flag, and who starts the game - computer or human. When a player starts, a random integer is generated and sent to the other player, which indicates the start of the game. The receiving player must now add one of { -1, 0, 1 } to get a number that is divisible by 3 and then divide it. The resulting number is then sent back to the original sender. The same rules apply until one of the players reaches the number 1 after division, which ends the game.

Computer must validate all the human moves and indicate if the input is wrong.

All the game moves and the result must be persisted in the DB and displayed on the request.

Please use the following technologies: Spring Boot, Spring MVC, Spring Data JPA, MySQL, we also expect the appropriate test suite written with Spring Boot Test framework.

### Prerequisites

* Java - version 11
* MySQL
* Docker

### Installing

1. git clone
2. run docker-compose
