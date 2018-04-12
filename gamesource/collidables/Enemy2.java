package gamesource.collidables;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;


public class Enemy2 extends GameObject
{

  public double xPrevious = 0;
	public double yPrevious = 0;

	public Enemy2(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
    x = -100;
    setRotation(0);
    scaleX = 0.8;
    scaleY = 0.8;
	}


  @Override
	public void Update()
	{
    xPrevious = x;
    yPrevious = y;

    x += 2.5;

      if(x >= 1201)
      {
        Helper.RemoveGameObject(this);
      }
  }


}
