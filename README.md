# ApMiniProject
A simple console application developed by [me](https://github.com/PouriaYazdani) and [Mohammad Bahrami](https://github.com/Mohammad-Bahrami) to put into practice our
taught lessons untill AP mid-term including [inheritance](https://en.wikipedia.org/wiki/Inheritance_(object-oriented_programming)),
[polymorphism](https://www.geeksforgeeks.org/perl-polymorphism-in-oops/) and basic object-oriented programming topics.
## Monopoly
This program simulates the famous [Monopoly](https://en.wikipedia.org/wiki/Monopoly_(game)) board game.The program has a single board which is loaded once the game is started.![Screenshot 2022-04-19 194456](https://user-images.githubusercontent.com/91950706/164037427-b282c284-0da2-4222-bb7e-b8bcddea86ca.png)
## User Interface
Several commands are defined in this game and the flow of the game is controled by them.
you can see the documentaion [here](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/Commands.java).  
Enetring two main commands -(*create_game* and *start_game*) are required to start the game. 
## Abilites
+ You can set the game duration and play the game in a limited time.
+ Player's turns are set by their dice roll.(*if were equal the player who had entered the game earlier has priority*)
+ The observer of the game (Bank Manager) can swap the wealth of 2 players which are not broke at the end of each round.
## Elements of the board
+ [Free parking](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/FreeParking.java)
+ [Buyable Fields](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/BuyableProperties.java)
+ [Airport](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/Airport.java)
+ [Cinema](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/Cinema.java)
+ [Road](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/Road.java)
+ [Prize](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/Prize.java)
+ [Prison](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/Prison.java)
+ [Tax](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/Tax.java)
+ [Bank](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/Bank.java)
+ [Question Mark](https://github.com/PouriaYazdani/ApMiniProject/blob/master/game/properties/QuestionMark.java)
## Implementaion
The details of each method and class relations and their functionality are documented as javadoc in corresponding files and our [UMl](https://github.com/PouriaYazdani/ApMiniProject/tree/master/uml) class.
