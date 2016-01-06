import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

import java.util.Random;

/**
 * A world where wombats live.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class WombatWorld extends World
{
    /**
     * Create a new world with 8x8 cells and
     * with a cell size of 60x60 pixels
     */
    public WombatWorld() 
    {
        super(8, 8, 60);        
        setBackground("cell.jpg");
        prepare();
    }

    /**
     * Populate the world with a fixed scenario of wombats and leaves.
     */    
    public void populate()
    {
        Wombat w1 = new Wombat();
        addObject(w1, 3, 3);

        Wombat w2 = new Wombat();
        addObject(w2, 1, 7);

        Leaf l1 = new Leaf();
        addObject(l1, 5, 3);

        Leaf l2 = new Leaf();
        addObject(l2, 0, 2);

        Leaf l3 = new Leaf();
        addObject(l3, 7, 5);

        Leaf l4 = new Leaf();
        addObject(l4, 2, 6);

        Leaf l5 = new Leaf();
        addObject(l5, 5, 0);

        Leaf l6 = new Leaf();
        addObject(l6, 4, 7);
    }

    /**
     * Place a number of leaves into the world at random places.
     * The number of leaves can be specified.
     */
    public void randomLeaves(int howMany)
    {
        for(int i=0; i<howMany; i++) {
            Leaf leaf = new Leaf();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(leaf, x, y);
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        PoisonousShroom poisonousshroom = new PoisonousShroom();
        addObject(poisonousshroom,1,2);
        PoisonousShroom poisonousshroom2 = new PoisonousShroom();
        addObject(poisonousshroom2,6,5);
        Leaf leaf = new Leaf();
        addObject(leaf,4,0);
        Leaf leaf2 = new Leaf();
        addObject(leaf2,3,6);
        Leaf leaf3 = new Leaf();
        addObject(leaf3,5,3);
        Wombat wombat = new Wombat();
        addObject(wombat,1,0);
    }
}