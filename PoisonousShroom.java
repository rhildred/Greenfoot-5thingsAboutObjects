import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PoisonousShroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PoisonousShroom extends Food
{
    public PoisonousShroom(){
        setImage("mushroom.png");
    }

    public boolean bPoisonous(){
        return true;
    }
}
