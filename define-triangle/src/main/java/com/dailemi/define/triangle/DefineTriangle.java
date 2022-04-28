package com.dailemi.define.triangle;

import java.util.*;

public class DefineTriangle {
    public static void main(String[] args) {
        boolean invalid_answer = true;
        Scanner sc = new Scanner(System.in);
        Triangle triangle = new Triangle();
        
        while(invalid_answer){
            System.out.println("Do you have the length of all the sides? [y/n]");
            String choice = sc.nextLine();
            choice = choice.toLowerCase();
            
            if(choice.equals("y") || choice.equals("yes")){
                triangle.prompt_lengths();
                invalid_answer = false;
            } else if(choice.equals("n") || choice.equals("no")){
                triangle.prompt_coordinates();
                invalid_answer = false;
            } else {
                System.out.println("Something went wrong :/");
            }
        }
        
        triangle.print_type();
    }
}
