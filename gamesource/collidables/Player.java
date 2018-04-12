package gamesource.collidables;

import gamesource.main.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;


public class Player extends GameObject
{

	public double xPrevious = 0;
	public double yPrevious = 0;

	public int walkSpeed = 5;
	public int runSpeed = 10;
	private int seconde = 0;
	private int minuten = 0;
	private int frames = 0;
	private int totalFrames = 0;
	public int health = 5;

	public Player(double xPosition, double yPosition)
	{
		super(xPosition, yPosition);
		scaleX = 0.2;
		scaleY = 0.2;
	}


	@Override
	public void Update()
	{
		totalFrames++;
		frames++;
		if (frames == 60){
			frames = 0;
			seconde++;
		}
		if (seconde == 60){
			seconde = 0;
			minuten++;
		}

		System.out.println("[HEALTH] : " + health);

		// REMEMBER PREVIOUS POSITION FIRST:
		xPrevious = x;
		yPrevious = y;


		// THEN APPLY NEW POSITION:
		// Set movement border
		if (Input.KEYS_PRESSED.contains("A") && x >= 5)
		{
			x -= walkSpeed;
			setRotation(270);
		}
		else if (Input.KEYS_PRESSED.contains("D") && x <= 1080 - 106)
		{
			x += walkSpeed;
			setRotation(90);
		}

		if (Input.KEYS_PRESSED.contains("W") && y >= 5)
		{
			y -= walkSpeed;
			setRotation(0);
		}
		else if (Input.KEYS_PRESSED.contains("S") && y <= 720 - 106)
		{
			y += walkSpeed;
			setRotation(180);
		}

		if (Input.KEYS_PRESSED.contains("SHIFT")){
			walkSpeed = runSpeed;
		}
		if (Input.KEYS_RELEASED.contains("SHIFT")){
			walkSpeed = 5;
		}

		// ADJUST SCALE:
		if (Input.MOUSE_PRESSED_LB)
		{
			scaleX -= 0.001;
			scaleY -= 0.001;
			x += 0.2;
			y += 0.2;
			if (scaleX <= 0.05 || scaleY <= 0.05){
				System.out.println("GAME OVER!");
				GameManager().PlayerDie();
				Helper.RemoveGameObject(this);
			}
		}
		else if (scaleX < 0.2 || scaleY < 0.2)
		{
			// CONDITIONAL ASSIGNMENT, WORKS AS FOLLOWS.
			// VALUE = CONDITION (if-statement) ? VALUE WHEN TRUE : VALUE WHEN FALSE;
			scaleX += scaleX < 0.2 ? 0.001 : 0;
			scaleY += scaleY < 0.2 ? 0.001 : 0;
			x -= 0.2;
			y -= 0.2;
		}

		if (Helper.CheckCollisionRectangle(this, Wall.class) != null){
			x = xPrevious;
			y = yPrevious;
		}

		if (Helper.CheckCollisionRectangle(this, PowerUp.class) != null){
				health += 5;
		}

		if (Helper.CheckCollisionRectangle(this, Enemy.class) != null){
			System.out.println("GAME OVER!");
			Helper.RemoveGameObject(this);
		}
		if (Helper.CheckCollisionRectangle(this, Enemy2.class) != null){
			System.out.println("GAME OVER!");
			Helper.RemoveGameObject(this);
		}

		// Wall wallObj = (wall)helper.CheckCollisionRectangle(this, Wall.class);
		// if (wallObj != null){
		// 	x = xPosition;
		// 	y = yPosition;
		//
		// 	wallObj.isVisible = false;
		// }

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
