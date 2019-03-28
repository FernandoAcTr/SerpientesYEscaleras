package controller;

public class Die {
    private int result;

    public Die(){

    }

    public void shoot(){
        result = (int)(Math.random()* 6 + 1);
    }

    public int getResult(){
        return result;
    }
}
