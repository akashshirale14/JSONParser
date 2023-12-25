package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static Stack<String> bracketStack;
    public static Map<String, String> bracketMap;
    static boolean mainBracketOpened = false;
    static boolean mainBracketClosed = false;
    public static void main(String[] args) throws FileNotFoundException {
        File jsonFile = new File("/Users/akashshirale/Downloads/tests/step1/valid.json");
        Scanner sc = new Scanner(jsonFile);
        bracketStack = new Stack<>();
        bracketMap = new HashMap<>();
        bracketMap.put("}", "{");
        bracketMap.put("]","[");


        boolean result = parseJson(sc);
        if(result) {
            System.out.println("0");
        }else{
            System.out.println("1");
        }
    }

    public static boolean parseJson(Scanner sc) {
        boolean isEmpty = true;
        String currLine = null;
        boolean ans = false;
        while(sc.hasNext()) {
            isEmpty = false;
           currLine = sc.nextLine().trim();
            System.out.println(currLine);
           char[] letters = currLine.toCharArray();
           for (char letter :  letters) {
               System.out.println("Letter: " + letter);
               if(letter == '{' || letter == '}' || letter=='[' || letter ==']') {
                   ans = checkValidParenthesis(String.valueOf(letter));
                   if(!ans) {
                       return false;
                   }
                   
               } if(letter == '"') {

               }

           }
        }


        return (ans && !isEmpty);
    }

    public static boolean checkValidParenthesis(String letter) {
        boolean isBracketValid = false;
        if(mainBracketOpened && mainBracketClosed) {
            return false;
        }
        if (letter.equals("{") || letter.equals("[")) {
            if(letter.equals("{") && !mainBracketOpened) {
                mainBracketOpened = true;
            }
            bracketStack.push(letter);
            isBracketValid = true;
        } else {
            String value = bracketMap.get(letter);
            if(!bracketStack.isEmpty() && bracketStack.peek().equals(value)) {
                isBracketValid = true;
                bracketStack.pop();

                if(bracketStack.isEmpty() && !mainBracketClosed) {
                    mainBracketClosed = true;
                }
            }else {
                isBracketValid = false;
            }

        }


        return isBracketValid;
    }


}