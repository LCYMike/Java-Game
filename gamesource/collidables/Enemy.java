package gamesource.collidables;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;


public class Enemy extends GameObject
{

  public double xPrevious = 0;
	public double yPrevious = 0;

	public Enemy(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
    x = 1100;
    setRotation(180);
    scaleX = 0.8;
    scaleY = 0.8;
	}


  @Override
	public void Update()
	{
    xPrevious = x;
    yPrevious = y;

    x -= 2.5;

      if(x <= -201)
      {
        Helper.RemoveGameObject(this);
      }
  }


}
