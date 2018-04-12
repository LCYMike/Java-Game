package gamesource.main;


import java.util.*;

import gamesource.collidables.*;

import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;


public class GameManager
{


	private Pane _mainPane;
	private Canvas _mainCanvas;
	private GraphicsContext _context;

	/*
	 * CHANGE THE LOCATION INTO WHERE YOU WILL STORE YOUR GAME IMAGES:
	 *
	 * 		"file:src/../../Art/EnemyBlob.png"
	 *
	 * This location goes 2 steps up from the "src" [ROOT_FOLDER] folder, then goes into the Art folder where the image is located.
	 * Change the location as instructed below:
	 *
	 * 		[ROOT]		= Name of the root folder (where the "MainApplication.java" is located).
	 * 		[LOCATION]	= The location RELATIVE to where the root folder is.
	 * 		[FILENAME]	= The name of the image file INCLUDING the file extension.
	 *
	 * 		"file:[ROOT]/[LOCATION]/[FILENAME]"
	 *
	 * Example:
	 *
	 * 		"file:MyGame/../ArtFiles/MySprite.png"
	 *
	 */
	private String _imageLocation = "art/Player.png";
	private String _enemyLocation = "art/EnemySprite.png";
	private String _powerUp = "art/powerUp.png";

	private int seconde = 0;
	private int minuten = 0;
	private int frames = 0;
	private int totalFrames = 0;
	private int spawnTime = 180;
	private int wave = 300;

	private int spawnerX;
	private int spawnerY;
	private int spawner2X;
	private int spawner2Y;
	private int spawnSideInt;

	private boolean playerSpawned = false;
	private boolean wallSpawned = false;
	private boolean powerUpSpawned = false;

    private double spawner;
    private double spawner2;
    private double spawsSide;
    private int spawnSwitch;
    private int spawnSwitch2;

	// PROPERTY GAME OBJECT LIST:
	private ArrayList<GameObject> _gameObjList = new ArrayList<GameObject>();
	public ArrayList<GameObject> getGameObjectsList()
	{
		return _gameObjList;
	}


	public GameManager(Pane paneReference)
	{
		_mainPane = paneReference;
		_mainCanvas = (Canvas)_mainPane.getChildren().get(0);
		_context = _mainCanvas.getGraphicsContext2D();
		
		Helper.SetGameManagerReference(this);


		InitializeGame();
	}


	public void Update()
	{
		System.out.println("[SPAWNTIME] : " + spawnTime);
		System.out.println("[WAVE] : " + wave);



		spawner = Math.round(Math.ceil(Math.random() * 12 + 1));
		spawnSwitch = (int) spawner;

		spawner2 = Math.round(Math.ceil(Math.random() * 12 + 1));
		spawnSwitch2 = (int) spawner2;

		spawsSide = Math.round(Math.ceil(Math.random() * 2 - 1));
		spawnSideInt = (int) spawsSide;

        if (spawnTime > 0){
            spawnTime--;
        } else {
            InitializeGame();
            spawnTime += wave;
            if(wave > 120 && wave <= 300){
				wave -= 5;
			}
        }

        // UPDATE ALL THE GAME OBJECTS:
		if (_gameObjList != null)
		{
			for (int i = 0; i < _gameObjList.size(); i++)
			{
				_gameObjList.get(i).Update();
			}
		}
	}


	public void Draw()
	{
		// DRAW ALL THE GAME OBJECTS:
		if (_gameObjList != null)
		{
			for (int i = 0; i < _gameObjList.size(); i++)
			{
				_gameObjList.get(i).Draw(_context);
			}
		}
	}

	public void PlayerDie(){
		System.out.println("RRREEEEEEEEEEEEE!!!");
	}

	public void DrawGUI()
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

		_context.fillText("[MINUTES] : " + minuten, 5, 685);
		_context.fillText("[SECONDS] : " + seconde, 5, 700);
	  _context.fillText("[FRAMES] : " + totalFrames, 5, 715);
	}




	// PRIVATE FUNCTIONS, INTERNAL GAME STRUCTURE:
	private void InitializeGame()
	{
	    if (playerSpawned == false){
	        playerSpawned = true;
            Player myPlayer = new Player(300, 200);
            myPlayer.setSprite(new Image(_imageLocation));
            // ADD PLAYER TO LIST OF OBJECTS TO UPDATE AND DRAW:
            _gameObjList.add(myPlayer);
	    }

	    //Spawner selection


		switch (spawnSwitch){
			case 1:
				spawnerX = 500;
				spawnerY = 0;
				break;
			case 2:
				spawnerX = 500;
				spawnerY = 50;
				break;
			case 3:
				spawnerX = 500;
				spawnerY = 100;
				break;
			case 4:
				spawnerX = 500;
				spawnerY = 150;
				break;
			case 5:
				spawnerX = 500;
				spawnerY = 200;
				break;
			case 6:
				spawnerX = 500;
				spawnerY = 250;
				break;
			case 7:
				spawnerX = 500;
				spawnerY = 300;
				break;
			case 8:
				spawnerX = 500;
				spawnerY = 350;
				break;
			case 9:
				spawnerX = 500;
				spawnerY = 400;
				break;
			case 10:
				spawnerX = 500;
				spawnerY = 450;
				break;
			case 11:
				spawnerX = 500;
				spawnerY = 500;
				break;

		}

		switch (spawnSwitch2){
			case 1:
				spawner2X = 100;
				spawner2Y = 0;
				break;
			case 2:
				spawner2X = 100;
				spawner2Y = 50;
				break;
			case 3:
				spawner2X = 100;
				spawner2Y = 100;
				break;
			case 4:
				spawner2X = -100;
				spawner2Y = 150;
				break;
			case 5:
				spawner2X = 100;
				spawner2Y = 200;
				break;
			case 6:
				spawner2X = 100;
				spawner2Y = 250;
				break;
			case 7:
				spawner2X = 100;
				spawner2Y = 300;
				break;
			case 8:
				spawner2X = 100;
				spawner2Y = 350;
				break;
			case 9:
				spawner2X = 100;
				spawner2Y = 400;
				break;
			case 10:
				spawner2X = 100;
				spawner2Y = 450;
				break;
			case 11:
				spawner2X = 100;
				spawner2Y = 500;
				break;

		}

		if (spawnSideInt == 1){
			Enemy myEnemy = new Enemy(spawnerX, spawnerY);
			myEnemy.setSprite(new Image(_enemyLocation));

			// ADD ENEMY TO LIST OF OBJECTS TO UPDATE AND DRAW:
			_gameObjList.add(myEnemy);
		} else if (spawnSideInt == 0){
			Enemy2 myEnemy2 = new Enemy2(spawner2X, spawner2Y);
			myEnemy2.setSprite(new Image(_enemyLocation));

			// ADD ENEMY TO LIST OF OBJECTS TO UPDATE AND DRAW:
			_gameObjList.add(myEnemy2);
		}


        if (powerUpSpawned == false && Math.ceil(Math.random() * 1000000) == 1){
            powerUpSpawned = true;
            PowerUp myPowerUp = new PowerUp(300, 200);
            myPowerUp.setSprite(new Image(_powerUp));
            // ADD POWER UP TO LIST OF OBJECTS TO UPDATE AND DRAW:
            _gameObjList.add(myPowerUp);
        }

        if (wallSpawned == false){
			wallSpawned = true;
			Wall myWall = new Wall(100, 100);
			_gameObjList.add(myWall);

			wallSpawned = true;
			Wall myWall2 = new Wall(700, 300);
			_gameObjList.add(myWall2);

			wallSpawned = true;
			Wall myWall3 = new Wall(100, 500);
			_gameObjList.add(myWall3);

			wallSpawned = true;
			Wall myWal4 = new Wall(700, 100);
			_gameObjList.add(myWal4);

			wallSpawned = true;
			Wall myWall5 = new Wall(700, 500);
			_gameObjList.add(myWall5);
        }

	}




	// PUBLIC FUNCTIONS:
	public GameObject CheckCollisionRectangle(GameObject objA, Class type)
	{
		for (GameObject objB : _gameObjList)
		{
			// FIRST CHECK IF IT'S THE TYPE OF OBJECT WE'RE LOOKING FOR:
			if (type.isInstance(objB))
			{
				// CHECK FOR COLLISION:
				if (
						objA.getBBoxLeft() <= objB.getBBoxRight() &&
						objA.getBBoxRight() >= objB.getBBoxLeft() &&
						objA.getBBoxTop() <= objB.getBBoxBottom() &&
						objA.getBBoxBottom() >= objB.getBBoxTop()
					)
				{
					// THERE IS COLLISION! RETURN THE OBJECT THAT HAS COLLISION WITH THE GIVEN OBJECT:
					// (The method stops executing after that.)
					return objB;
				}
			}
		}

		return null;
	}


}
