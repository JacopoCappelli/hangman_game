/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman extends ConsoleProgram {

    /***********************************************************
     *              CONSTANTS                                  *
     ***********************************************************/

    /* The number of guesses in one game of Hangman */
    private static final int N_GUESSES = 8;
    /* The width and the height to make the karel image */
    private static final int KAREL_SIZE = 150;
    /* The y-location to display karel */
    private static final int KAREL_Y = 230;
    /* The width and the height to make the parachute image */
    private static final int PARACHUTE_WIDTH = 300;
    private static final int PARACHUTE_HEIGHT = 130;
    /* The y-location to display the parachute */
    private static final int PARACHUTE_Y = 50;
    /* The y-location to display the partially guessed string */
    private static final int PARTIALLY_GUESSED_Y = 430;
    /* The y-location to display the incorrectly guessed letters */
    private static final int INCORRECT_GUESSES_Y = 460;
    /* The fonts of both labels */
    private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
    private static final String INCORRECT_GUESSES_FONT = "Courier-26";

    /***********************************************************
     *              Instance Variables                         *
     ***********************************************************/

    /* An object that can produce pseudo random numbers */
    private RandomGenerator rg = new RandomGenerator();

    private GCanvas canvas = new GCanvas();

    /***********************************************************
     *                    Methods                              *
     ***********************************************************/
    public int counter=1;
    String visibleWord=getRandomWord();
    char[]censoredWord=new char[visibleWord.length()];

    public void run() {

        for(int i=0;i<visibleWord.length();i++){
            censoredWord[i]='-';
        }


        Scanner scanner=new Scanner(System.in);

        String[] components = {
                "____", "|  ", "|  ", "|", "|", "|", "|____"
        };
        while(counter<=N_GUESSES) {
            System.out.println("Ecco la tua parola");

            System.out.println( components[0] );
            System.out.print( components[1] );


            if (counter > 1) {
                System.out.print( "|" );
            }

            System.out.println();

            System.out.print( components[2] );
            if (counter > 2) {

                System.out.print( "O" );
            }
            System.out.println();
            System.out.print( components[3] );

            if (counter > 3) {
                System.out.print( " /" );
            }
            if (counter > 4) {
                System.out.print( "|" );
            }
            if (counter > 5) {
                System.out.print( "\\" );
            }

            System.out.println();
            System.out.print( components[4] );
            if (counter > 6) {
                System.out.print( " /" );
            }
            if (counter > 7) {
                System.out.print( "\\" );
            }
            System.out.println();

            System.out.println( components[6] );


            System.out.println();

            if(counter==N_GUESSES){
                System.out.println("Hai perso!");
                break;
            }
            System.out.println( censoredWord );

            System.out.println("Indovina la lettera");
            String letter=scanner.next().toUpperCase();
            boolean letterMatch=false;
            for(int i=0;i<visibleWord.length();i++){
                if(visibleWord.charAt(i)==letter.charAt(0)){
                    censoredWord[i]=letter.charAt( 0 );
                    letterMatch=true;
                }
            }

            if(hasWon()){
                System.out.println("Congratualzioni hai vinto!!!");
                break;
            }

            if(!letterMatch){
                counter++;
            }



        }
        scanner.close();


    }

    private boolean hasWon(){

        for(char c: censoredWord){
            if(c=='-')return false;

        }
        return true;
    }





    /**
     * Method: Get Random Word
     * -------------------------
     * This method returns a word to use in the hangman game. It randomly
     * selects from among 10 choices.
     */
    private String getRandomWord() {
        int index = rg.nextInt( 10 );
        if (index == 0) return "BUOY";
        if (index == 1) return "COMPUTER";
        if (index == 2) return "CONNOISSEUR";
        if (index == 3) return "DEHYDRATE";
        if (index == 4) return "FUZZY";
        if (index == 5) return "HUBBUB";
        if (index == 6) return "KEYHOLE";
        if (index == 7) return "QUAGMIRE";
        if (index == 8) return "SLITHER";
        if (index == 9) return "ZIRCON";
        throw new ErrorException( "getWord: Illegal index" );
    }

}
