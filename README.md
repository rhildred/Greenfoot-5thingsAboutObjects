# 3 things about objects ... [introducing Greenfoot](https://github.com/rhildred/Greenfoot-5thingsAboutObjects)

I worked in banking for a number of years. I had a bank account when I started, but I had to learn or at least apply a new level of focus to the kinds of things/objects that bankers deal with.

###Account

As I said I had a bank account. Accounts have properties and behaviors. An account has a balance, and a primary account number property. You can deposit and withdraw from an account.

##Thing 1 

Objects are made of behavior and properties together. An instance of an object like account 12345 belongs to a class of objects. The class of the object is like a template that a new object is created from. When an instance of an object is created from a template there is a special behavior `new` that is performed. In Java when `new` is performed to make a new instance of an object a method with the same name as the class is called automatically.

###Customer

When I started writing banking software I had been a customer of a bank since high school already. I actually had more than 1 bank account, a chequeing account, savings and Mastercard.

## Thing 2

Objects are interrelated. A customer can have more than 1 account. You don't actually have a plain account as a customer, what you have is a specialization of an account, like a savings account. An account is a generalization from which specializations inherit properties like a balance, an account number .... There are 2 ways an object can be related to an object:
1. aggregation - a customer has 1 - infinity accounts
2. inheritance - an account is a generalization of all of the different types of accounts like savings chequeing  ...

###Using an Account

When I make a withdrawal from my Visa, the account checks to make sure I have sufficient credit limit to cover the withdrawal. When I use Interac with my chequeing account, the account is checked to make sure that my balance is sufficient to cover the withdrawal.

##Thing 3

To use an object, I perform some behavior on it, in the above case withdrawing. Depending on the specialization of the account, I do withdrawing in slightly different ways. Performing the same behavior with an object in different specialized ways is called polymorphism.

#My First Game

It was after my banking software days were over that I wrote my first game as a teaching tool for Javascript. It was recently enough that serious games were starting to become important and this game was to help people with a learning disability. Like many games, my game had a playing surface, a `world` or board on which it was played. It also had actors in this world, some controlled by the computer, and some controlled by the player.

##World

The world is the board that the game is played on. In [Greenfoot](http://www.greenfoot.org/), the gaming environment that runs [this repository](https://github.com/rhildred/Greenfoot-5thingsAboutObjects) you make your world by specializing or extending a World object that is built in to Greenfoot. 

![From Greenfoot Tutorial](http://www.greenfoot.org/images/tutorials/wombat/scenario-main.png?1325954072 "From Greenfoot Tutorial")

Generally a Greenfoot world creates the actors in that world:

```
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

```

You can get Greenfoot to write the `prepare` method by creating new actors on the world and using the`Controls/Save The world` menu item. 

##Actor

A Greenfoot actor, like World, is a specialization of a built in actor. The built in actor can `act` every once in a while. Each actor knows how to act for themselves (polymorphism).


##Making the wombat scenario more "gamey"

The poor wombat in [tutorial 1](http://www.greenfoot.org/doc/tut-1) could only reach food that was on the edge of the screen. I added a method that let the user show the wombat where to go by tapping or mouse clicking past the wombat in the direction that you are leading him:

```

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

```

##Poisonous Shrooms

The other thing that I added to the wombat scenario was poisonous shrooms. To do that I needed a few steps. I needed to generalize leaves to be food, generally non poisonous.

```
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Actor
{
    
    public boolean bPoisonous(){
        return false;
    }
}
```

Then I made Leaf extend Food rather than actor:

```

import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * Leaf - a class for representing leafs.
 *
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Leaf extends Food
{
}

```

Then I made my new PoisonousShroom class:

```
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PoisonousShroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PoisonousShroom extends Food
{
    public boolean bPoisonous(){
        return true;
    }
}

```

Finally I changed the Wombat code to take advantage of the new structure:

```
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
        Food food = getOneObjectAtOffset(0, 0, Food.class);
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
```

I hope that you will agree that this makes the Wombat more of a game.