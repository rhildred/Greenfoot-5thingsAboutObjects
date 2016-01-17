import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.util.List;
import java.util.ArrayList;

/**
 * Wombat. A Wombat moves forward until it can't do so anymore, at
 * which point it turns left. If a wombat finds a leaf, it eats it.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Wombat extends Actor
{
    private static final int EAST = 0;
    private static final int WEST = 1;
    private static final int NORTH = 2;
    private static final int SOUTH = 3;

    private int direction;
    private int leavesEaten;

    public Wombat()
    {
        setDirection(EAST);
        leavesEaten = 0;
        setImage("wombat.gif");
    }

    /**
     * Do whatever the wombat likes to to just now.
     */
    public void act()
    {
        optChangeDirection();
        if(foundFood()) {
            eatFood();
        }
        else if(canMove()) {
            move();
        }
        else {
            turnLeft();
        }
    }
    
    private void optChangeDirection() {
		if (Greenfoot.mousePressed(null)) {
			MouseInfo mouse = Greenfoot.getMouseInfo();
			int px = mouse.getX();
			int py = mouse.getY();
			int hx = this.getX();
			int hy = this.getY();
			switch (direction) {
			case WEST:
			case EAST:
				if (py <= hy) {
					setDirection(NORTH);
				} else {
					setDirection(SOUTH);
				}
				break;
			case NORTH:
			case SOUTH:
				if (px <= hx) {
					setDirection(WEST);
				} else {
					setDirection(EAST);
				}
				break;
			}
		}
	}


    /**
     * Check whether there is a leaf in the same cell as we are.
     */
    public boolean foundFood()
    {
        Actor food = getOneObjectAtOffset(0, 0, Food.class);
        if(food != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Eat a leaf.
     */
    public void eatFood()
    {
        Food food = (Food)getOneObjectAtOffset(0, 0, Food.class);
        if(food != null) {
            // eat the leaf...
            World world = getWorld();
            if(food.bPoisonous()){
                world.removeObject(food);
                world.removeObject(this);
                Greenfoot.stop();
            }else{
                world.removeObject(food);
                leavesEaten = leavesEaten + 1; 
            }
        }
    }
    
    /**
     * Move one cell forward in the current direction.
     */
    public void move()
    {
        if (!canMove()) {
            return;
        }
        switch(direction) {
            case SOUTH :
                setLocation(getX(), getY() + 1);
                break;
            case EAST :
                setLocation(getX() + 1, getY());
                break;
            case NORTH :
                setLocation(getX(), getY() - 1);
                break;
            case WEST :
                setLocation(getX() - 1, getY());
                break;
        }
    }

    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean canMove()
    {
        World myWorld = getWorld();
        int x = getX();
        int y = getY();
        switch(direction) {
            case SOUTH :
                y++;
                break;
            case EAST :
                x++;
                break;
            case NORTH :
                y--;
                break;
            case WEST :
                x--;
                break;
        }
        // test for outside border
        if (x >= myWorld.getWidth() || y >= myWorld.getHeight()) {
            return false;
        }
        else if (x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    /**
     * Turns towards the left.
     */
    public void turnLeft()
    {
        switch(direction) {
            case SOUTH :
                setDirection(EAST);
                break;
            case EAST :
                setDirection(NORTH);
                break;
            case NORTH :
                setDirection(WEST);
                break;
            case WEST :
                setDirection(SOUTH);
                break;
        }
    }

    /**
     * Sets the direction we're facing.
     */
    public void setDirection(int direction)
    {
        this.direction = direction;
        switch(direction) {
            case SOUTH :
                setRotation(90);
                break;
            case EAST :
                setRotation(0);
                break;
            case NORTH :
                setRotation(270);
                break;
            case WEST :
                setRotation(180);
                break;
            default :
                break;
        }
    }

    /**
     * Tell how many leaves we have eaten.
     */
    public int getLeavesEaten()
    {
        return leavesEaten;
    }
}
