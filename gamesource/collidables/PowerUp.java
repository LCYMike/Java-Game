package gamesource.collidables;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;


public class PowerUp extends GameObject
{

	public double xPrevious = 0;
	public double yPrevious = 0;


	public PowerUp(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
	}


	@Override
	public void Update()
	{
    xPrevious = x;
    yPrevious = y;

    if (Helper.CheckCollisionRectangle(this, Player.class) != null){
        Helper.RemoveGameObject(this);
    }
	}


	@Override
	public void Draw(GraphicsContext gc)
	{

		// EXTRA SHADOW EFFECT:
		gc.setFill(Color.rgb(0, 0, 0, 0.5));
		gc.fillOval(x, y + (getHeight() * 0.9), getWidth(), 50 * scaleY);


		// ORIGINAL DRAWING METHOD FROM THE INHERITED GAMEOBJECT CLASS:
		super.Draw(gc);

	}


}
