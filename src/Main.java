import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        final int maxNumPlayers = 6;
        final int maxDieSides = 20;

        Scanner read = new Scanner(System.in);
        
        // chiyoung part
        System.out.println();
        System.out.println("Welcome to the Snakes and Ladders !");
        System.out.println();
        System.out.println("The objective of this game is to reach the 100 case first !");
        System.out.println("But be careful ! If you step on a snake case, you will fall down...");
        System.out.println("But also if you step on a ladder case, you will go up !");
        System.out.println("The snake cases are colored in RED and ladder cases in GREEN");
        System.out.println("For every turn, you can choose to roll a 0 and skip it");
        System.out.println("Or you can roll multiple dice at the same time and use the sum of the result for the number of steps");
        System.out.println("You can also roll the die or dice multiple times in one turn");
        System.out.println("The game can be played multiplayer or singleplayer");
        System.out.println();
        System.out.println("Now, please enter the number of players (max " + maxNumPlayers + ").");

        // get the number of players
        // chiyoung part
        int numPlayers = 0;
        while (true) {
            numPlayers = read.nextInt();
            read.nextLine();
            if(numPlayers > maxNumPlayers) {
                System.out.println("Maximum number of players is " + maxNumPlayers + "!");
            } else if (numPlayers <= 0) {
                System.out.println("Number of players cannot be less than 1!");
            } else {
                break;
            }
            System.out.println("Please enter the number of players (max " + maxNumPlayers + ").");
        }

        System.out.println("Now, please enter the number of sides for the dice (max " + maxDieSides + ").");

        // get the sides of die
        int dieSides = 0;
        while (true) {
            dieSides = read.nextInt();
            read.nextLine();
            if(dieSides > maxDieSides) {
                System.out.println("Maximum dice sides is " + maxDieSides + "!");
            } else if (numPlayers <= 0) {
                System.out.println("Dice sides cannot be less than 1!");
            } else {
                break;
            }
            System.out.println("Please enter the number of dice sides (max " + maxDieSides + ").");
        }

        System.out.println("Now, please enter the number of rolls gained per turn.");

        // get the sides of die
        int rollsPerTurn = 0;
        while (true) {
            rollsPerTurn = read.nextInt();
            read.nextLine();
            if (rollsPerTurn <= 0) {
                System.out.println("Rolls per turn cannot be less than 1!");
            } else {
                break;
            }
            System.out.println("Please enter the number of rolls gained per turn.");
        }

        System.out.println("Would you like to play in singleplayer against bots? (Y/N)");
        // get singerplayer
        boolean isSingleplayer = false;
        while (true) {
            String isSingleplayerText = read.nextLine();
            if(!(isSingleplayerText.equalsIgnoreCase("N") || isSingleplayerText.equalsIgnoreCase("Y"))) {
                System.out.println("Enter Y or N!");
            } else {
            if (isSingleplayerText.equalsIgnoreCase("Y")) {
                isSingleplayer = true;
            }
            break;
            }
        }

        System.out.println(isSingleplayer);

        Game game = new Game(numPlayers, 1);

        Dice dice = new Dice(dieSides);

        System.out.println(
            "\n\n" +
             "Players: " + numPlayers + "\n" +
             "Board Width " + game.getBoardWidth() + "\n" +
             "Board Height " + game.getNumberOfTiles() / game.getBoardWidth() + "\n" +
             "Tile Size " + game.getTileSize()
        );

        game.printTable();

        while (game.isActive()) {
            boolean breakGameLoop = false;
            for (int i = 0; i < numPlayers; i++) {

                if (breakGameLoop) {
                    break;
                }

                Player currPlayer = game.getPlayer(i);
                currPlayer.addTurns(rollsPerTurn);
            
                boolean doLoopCurrentTurn = true;
                int currentTurnCounter = 0;
                while (doLoopCurrentTurn) {
                    int numberOfRolls = 0;
                    if (!isSingleplayer || (isSingleplayer && (currPlayer.getName().equals("Player 1")))) {
                        // get input
                        
                        currPlayer.printStatus();
                        
                        if (currentTurnCounter == 0) {
                        System.out.println(
                                        "\tPlease enter the number of rolls you want to use in one go\n");
                        } else {
                            System.out.println(
                            "\tYou still have rolls, do you want to roll again? If not, enter 0.");
                        }
                        String rolling = "";
                        
                        while(true) {
                            rolling = read.nextLine(); // DONE ADD VARIABLE NUMBER OF ROLLS
                            numberOfRolls = 0;

                            try {
                                numberOfRolls = Integer.parseInt(rolling);
                                
                                if (numberOfRolls > currPlayer.getNumberOfTurns()) {
                                    System.out.println("\tNumber of rolls exceeding your turns!");
                                    System.out.println("\tPlease enter the number of rolls you want to use");
                                } else if (numberOfRolls < 0) {
                                    System.out.println("\tNumber of rolls cannot be negative!");
                                    System.out.println("\tPlease enter the number of rolls you want to use");
                                }
                                else {
                                    currPlayer.setNumberOfTurns(currPlayer.getNumberOfTurns() - numberOfRolls);
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("\tPlease enter the number of rolls you want to use in one go");
                                System.out.println("\tMust be an integer!");
                                continue;
                            }
                        }
                    } else { // CPU character ai
                        currPlayer.printStatus();
                        System.out.println("\tPlease enter the number of rolls you want to use");

                        Functions.wait((int) Math.random() * 500);

                        numberOfRolls = (int) (Math.random() * currPlayer.getNumberOfTurns());
                        currPlayer.setNumberOfTurns(currPlayer.getNumberOfTurns() - numberOfRolls);
                        System.out.println(numberOfRolls);

                        Functions.wait((int) Math.random() * 1000 + 500);
                    }


                    //Functions.printLoop("\n", 150);

                    if (numberOfRolls == 0) {
                        doLoopCurrentTurn = false;
                    }

                    if (currPlayer.getNumberOfTurns() <= 0) {
                        doLoopCurrentTurn = false;
                    }

                    // roll dice and move
                    int moveSteps = 0;

                    System.out.print(currPlayer.getColour());

                    for (int m = 0; m < numberOfRolls; m++) {
                        int diceRoll = dice.roll();
                        moveSteps += diceRoll;
                        System.out.println(currPlayer.getName() + " has rolled " + diceRoll);

                        if (numberOfRolls > 1) {
                            Functions.wait(250);
                        }
                    }

                    System.out.println("\n" + currPlayer.getName() + " will move for " + moveSteps + " steps.");
                    
                    int previousCase = currPlayer.getCurrentCase();

                    game.movePlayer(i, moveSteps);
                    System.out.println(currPlayer.getName() + " has moved from " + (previousCase) + " to " + (currPlayer.getCurrentCase()));

                    System.out.print(Pallette.ANSI_RESET);
                    
                    // -------- check if player has stepped on special case-

                    while (true) {
                        previousCase = currPlayer.getCurrentCase();
                        int playerPosition = currPlayer.getCurrentCase();
                        Tile tileAtPosition = game.getTile(playerPosition);
                        String tileName = tileAtPosition.getName();

                        System.out.println(
                            game.getTile(currPlayer.getCurrentCase()).getColour() + currPlayer.getName() + " has stepped on a " + game.getTile(currPlayer.getCurrentCase()).getName() + " (Tile: " + (game.getTileIndex(game.getTile(currPlayer.getCurrentCase()))) + ")" + Pallette.ANSI_RESET
                        );

                        if (tileAtPosition.isScoreTile()) {
                            int score = (int) (Math.random() * 5);
                            System.out.println(Pallette.ANSI_YELLOW + currPlayer.getName() + " has gained " + score + " points and an extra turn" + Pallette.ANSI_RESET);
                            tileAtPosition.setScoreTile(false);
                            currPlayer.addScore(score);
                            currPlayer.addTurns(1);
                        }

                        if (tileName == TileNames.ladder || tileName == TileNames.snake) {
                            game.setPlayerPosition(i, tileAtPosition.getJumpIndex());
                            System.out.println(currPlayer.getName() + " has moved from " + (previousCase) + " to " + (currPlayer.getCurrentCase()));
                        } else {
                            // if the player has stepped on a normal tile, break the loop
                            break;
                        }
                    }

                    // check if anyone has won
                    game.setWinner();

                    Player winner = game.getWinner();

                    if (winner != null) {
                        System.out.println("The winner is: " + winner.getName() + " with " + winner.getScore() + " points!");
                        game.endGame();
                        game.printTable();
                        doLoopCurrentTurn = false;
                        breakGameLoop = true;
                        break;
                    }
                    
                    System.out.println();
                    game.printTable();

                    currentTurnCounter++;
                }
            }
        }
        read.close();
    }
}
