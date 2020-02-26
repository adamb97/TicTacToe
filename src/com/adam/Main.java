package com.adam;

import java.util.*;

public class Main {

    //global and static arraylist of integers for player position

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
	    char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};






	    while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter position 1-9: ");
            int pos = scan.nextInt();
            while(playerPositions.contains(pos) || cpuPositions.contains((pos))){
                System.out.println("Position already taken; please enter a valid number");
                pos = scan.nextInt();
            }

            setPlay(gameBoard, pos, "player" );
            String winner = checkWinner();
            if(winner.length() > 0 ){
                System.out.println(winner);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains((cpuPos))){
                cpuPos = rand.nextInt(9) + 1;
            }

            setPlay(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);
            winner = checkWinner();
            if(winner.length() > 0 ){
                System.out.println(winner);
                break;
            }

        }


    }


    public static void printGameBoard(char [] [] gameBoard){
        for(char[] row : gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void setPlay(char[][] gameBoard, int pos, String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add((pos));
        } else if (user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch(pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;


        }
    }
    public static String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftColumn = Arrays.asList(1,4,7);
        List midColumn = Arrays.asList(2,5,8);
        List rightColumn = Arrays.asList(3,6,9);
        List diagnol1 = Arrays.asList(1,5,9);
        List diagnol2 = Arrays.asList(7,5,3);


        //list of lists for winning conditions

        List<List>winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftColumn);
        winningConditions.add(midColumn);
        winningConditions.add(rightColumn);
        winningConditions.add(diagnol1);
        winningConditions.add(diagnol2);

        for(List l : winningConditions){
            if(playerPositions.containsAll(l)){
                return "Congrats! You win!";
            } else if (cpuPositions.containsAll(l)){
                return "CPU Wins!";
            } else if (playerPositions.size() + cpuPositions.size() == 9){
                return "Cat's game! Tie!";
            }
        }

        return "";

    }
}
