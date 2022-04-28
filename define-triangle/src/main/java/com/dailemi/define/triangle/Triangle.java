package com.dailemi.define.triangle;

import java.util.*;

public class Triangle {
    
    /**
     * Length A is opposite of points A
     * Angel A is at points A
     */
    private double length_A, length_B, length_C,
                   angle_A, angle_B, angle_C;
    
    private type triangle_type;
    
    enum type {
        EQUILATERAL,
        ISOSCELES,
        SCALENE,
        INVALID
    }
    
    public double getLength_A() {
        return length_A;
    }

    public double getLength_B() {
        return length_B;
    }

    public double getLength_C() {
        return length_C;
    }

    public double getAngle_A() {
        return angle_A;
    }

    public double getAngle_B() {
        return angle_B;
    }

    public double getAngle_C() {
        return angle_C;
    }
    
    public void print_type(){
        System.out.println("This triangle is " + this.triangle_type);
    }
    
    /**
     * This setup runs after the prompt which constructs the class
     */
    private void triangleSetup(){
        check_validity();
        
        this.angle_A = calc_angle(this.length_B, this.length_C, this.length_A);
        this.angle_B = calc_angle(this.length_C, this.length_A, this.length_B);
        this.angle_C = calc_angle(this.length_A, this.length_B, this.length_C);
        
        determine_type();
    }
    
    /**
     * This function prompts the user for the coordinate points
     */
    public void prompt_coordinates(){
        String input;
        String[] strArray = new String[6];
        Scanner sc = new Scanner(System.in);
        boolean no_answer = true;
        
        
        while(no_answer){
            System.out.println("Enter the three coordinates coordinate x,y x,y x,y");
            input = sc.nextLine();
            strArray = input.split(",|\\s+|\\(|\\)");
            
            if(strArray.length != 6){
                System.out.println("Something went wrong :/");
            } else {
                no_answer = false;
            }
        }
        
        Point[] corner_points = new Point[3];
        
        corner_points[0] = new Point(Integer.parseInt(strArray[0]),
                                      Integer.parseInt(strArray[1]));
        corner_points[1] = new Point(Integer.parseInt(strArray[2]),
                                      Integer.parseInt(strArray[3]));
        corner_points[2] = new Point(Integer.parseInt(strArray[4]),
                                      Integer.parseInt(strArray[5]));
        
        this.length_A = calc_length(corner_points[1], corner_points[2]);
        this.length_B = calc_length(corner_points[0], corner_points[2]);
        this.length_C = calc_length(corner_points[0], corner_points[1]);
        
        this.triangleSetup();
    }
    
    /**
     * This function prompts the user for the lengths of the sides
     */
    public void prompt_lengths(){
        String input;
        String[] strArray = new String[3];
        Scanner sc = new Scanner(System.in);
        boolean no_answer = true;
        
        while(no_answer){
            System.out.println("Enter lengths of sides seperated by commas (decimal seperated by periods)");
            input = sc.nextLine();
            strArray = input.split(",|\\s+|\\(|\\)");

            if(strArray.length != 3){
                System.out.println("Something went wrong :/");
            } else {
                no_answer = false;
            }
        }
        
        this.length_A = Double.parseDouble(strArray[0]);
        this.length_B = Double.parseDouble(strArray[1]);
        this.length_C = Double.parseDouble(strArray[2]);
        
        this.triangleSetup();
    }
    
    /**
     * Calculate distance using euclidean equation
     * @param x_side calculates the difference between
     *               the x coordinates of the two given points
     */
    private double calc_length(Point first, Point second){
        double result, x_side, y_side;
        
        x_side = second.x - first.x;
        
        x_side = Math.pow(x_side ,2);
        
        y_side = second.y - first.y;
        
        y_side = Math.pow(y_side ,2);
        
        result = Math.sqrt(x_side + y_side);
        
        return result;
    }
    
    /**
     * Calculate angle using the law of cosine
     * cos-1((a2 + b2 − c2)/2ab)
     * 
     * The last calculation is to convert from rad to degree
     * 
     * @param num is the numerator of the equation
     * @param den is the denominator
     * @param l_len is the length of the side on the left of the point
     * @param r_len is the length of the side on the right of the point
     * @param opp_len is the length of the side opposite of the point
     * 
     * It is important not to mix the right and the left sides
     * since it will not give the same result
     */
    private double calc_angle(double l_len, double r_len, double opp_len){
        double result, num, den;
        
        num = Math.pow(l_len, 2) + Math.pow(r_len, 2) - Math.pow(opp_len, 2);
        
        den = 2 * l_len * r_len;
        
        result = num / den;
        
        result = Math.acos(result);
        
        result = result * (180/Math.PI);
        
        return result;
    }
    
    /**
     * Checking the type of the triangle
     * 
     * First we check if the triangle is valid, if it is not then we just return
     * 
     * Then we check if it is equilateral, when this is checked then
     * the angles are also checked for an extra safety
     * 
     * Then we check if it is isosceles, when this is checked then
     * the angles are also checked for an extra safety
     * 
     * If it is none of them then the triangle must be scalene
     */
    private void determine_type(){
        if(this.triangle_type == type.INVALID){
            return;
        }
        
        if((this.length_A == this.length_B) && (this.length_B == this.length_C) &&
           (this.angle_A == this.angle_B) && (this.angle_B == this.angle_C)){
            this.triangle_type = type.EQUILATERAL;
            return;
        }
        
        if((this.length_A == this.length_B && this.angle_A == this.angle_B) ||
           (this.length_A == this.length_C && this.angle_A == this.angle_C) ||
           (this.length_B == this.length_C && this.angle_B == this.angle_C)){
            this.triangle_type = type.ISOSCELES;
            return;
        }
        
        this.triangle_type = type.SCALENE;
    }
    
    /**
     * Checks if it is possible to create a triangle
     * with the given lengths of the sides
     * 
     * If two sides are longer than the third side then it is not valid
     * 
     * Only sets the triangle_type to INVALID if it is not valid
     */
    private void check_validity(){
        if(this.length_A + this.length_B <= this.length_C ||
           this.length_A + this.length_C <= this.length_B ||
           this.length_B + this.length_C <= this.length_A){
            this.triangle_type = type.INVALID;
        }
    }
}
