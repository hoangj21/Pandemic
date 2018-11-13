package com.example.joann.pandemic.game;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.joann.pandemic.game.actionMsg.GameOverAckAction;
import com.example.joann.pandemic.game.actionMsg.MyNameIsAction;
import com.example.joann.pandemic.game.actionMsg.ReadyAction;
import com.example.joann.pandemic.game.infoMsg.BindGameInfo;
import com.example.joann.pandemic.game.infoMsg.GameInfo;
import com.example.joann.pandemic.game.infoMsg.GameOverInfo;
import com.example.joann.pandemic.game.infoMsg.GameState;
import com.example.joann.pandemic.game.infoMsg.StartGameInfo;
import com.example.joann.pandemic.game.infoMsg.TimerInfo;
import com.example.joann.pandemic.game.util.GameTimer;
import com.example.joann.pandemic.game.util.MessageBox;
import com.example.joann.pandemic.game.util.Tickable;
import com.example.joann.pandemic.pandemic.City;
import com.example.joann.pandemic.pandemic.PandemicGameState;


/**
 * An abstract computerized game player player. This is an abstract class, that
 * should be sub-classed to implement different AIs. The subclass must implement
 * the {@link #receiveInfo} method.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew Nuxoll
 * @version July 2013
 */
public abstract class GameComputerPlayer implements GamePlayer, Tickable {
	/**
	 * the current game state
	 */
	protected Game game; // the game object
	protected int playerNum; // which player number I am
	protected String name; // my name
	protected String[] allPlayerNames; // list of all player names, in ID order
	private Handler myHandler; // the handler for this player's thread
	private boolean running; // whether the player's thread is running
	private boolean gameOver = false; // whether the game is over
	private GameMainActivity myActivity; // the game's main activity, set only
			// this game is connected to the GUI
	private GameTimer myTimer = new GameTimer(this); // my timer
	
	/**
	 * Returns this game's timer.
	 * 
	 * @return this game's timer object
	 */
	protected final GameTimer getTimer() {
		return myTimer;
	}

	/**
	 * Called when the timer ticks; satisfies the Tickable interface.
	 */
	public final void tick(GameTimer timer) {
		sendInfo(new TimerInfo(timer));
	}

	/*
	 * ====================================================================
	 * Abstract Methods
	 * 
	 * Create the game specific functionality for this human player by
	 * sub-classing this class and implementing the following methods.
	 * --------------------------------------------------------------------
	 */

	/*
	 * ====================================================================
	 * Public Methods
	 * --------------------------------------------------------------------
	 */

	/**
	 * constructor
	 * 
	 * @param name
	 * 			the player's name (e.g., "John")
	 */
	public GameComputerPlayer(String name) {
		this.name = name;
	}

	/**
	 * Sets this player to be the one connected to the GUI.
	 * Should only be called if the supportsGUI method returns
	 * true.
	 * 
	 * @param a
	 * 			the activity that is being run
	 */
	public final void gameSetAsGui(GameMainActivity a) {
		myActivity = a;
		setAsGui(a);
	}
	
	/**
	 * Subclass-behavior for setting this player to be the
	 * one associated with the GUI. Typically, changes the
	 * current screen to have a new layout, and sets up
	 * listeners, animators, etc.
	 * 
	 * @param activity
	 * 			the activity that is being run
	 */
	public void setAsGui(GameMainActivity activity) {
		// default behavior is to do nothing
	}
	
	/**
	 * perform any initialization that needs to be done after the player
	 * knows what their game-position and opponents' names are.
	 */
	protected void initAfterReady() {
		// by default, we do nothing
	}
	
	/**
	 * Method used to send updated state to this player.
	 * 
	 * @param info
	 * 			the information message to send
	 */
	public final void sendInfo(GameInfo info) {
		// post the state to the player's thread, waiting (if needed) until handler is there
		while (myHandler == null) Thread.yield();
		myHandler.post(new MyRunnable(info));
	}
	
	/**
	 * Starts the player.
	 */
	public final void start() {
		// if the player's thread is not presently running, start it up, keeping
		// track of its handler so that messages can be sent to the thread.
		synchronized(this) {
			if (running) return;
			running = true;
			Runnable runnable = new Runnable() {
				public void run() {
					Looper.prepare();
					myHandler = new Handler();
					Looper.loop();
				}
			};
			Thread thread = new Thread(runnable);
			thread.setName("Computer Player");
			thread.start();
		}
	}
	
	/**
	 * Callback-method implemented in the subclass whenever updated
	 * state is received.
	 * 
	 * @param info
	 * 			the object representing the information from the game
	 */
	protected abstract void receiveInfo(GameInfo info);
	
	/**
	 * Helper-class to post a message to this player's thread
	 *
	 */
	private class MyRunnable implements Runnable {
		
		// the object to post
		private Object data;
		
		// constructor
		public MyRunnable(Object data) {
			this.data = data;
		}
		
		// run-method: executed in this player's thread, handling a message from
		// the game, or the timer
		public void run() {
			
			// if game is over, do nothing
			if (gameOver) return;
			
			// if it's a GameInfo object, process it
			if (data instanceof GameInfo) { // ignore non GameInfo objects
				GameInfo myInfo = (GameInfo)data;
				if (game == null) {
					
					// CASE 1: we don't know who our game is; the only thing we're
					// looking for is BindGameInfo object; ignore everything else
					if (myInfo instanceof BindGameInfo) {
						BindGameInfo bgs = (BindGameInfo)myInfo;
						game = bgs.getGame(); // set our game
						playerNum = bgs.getPlayerNum(); // set our player ID
						
						// send a message to the game with our player's name
						game.sendAction(new MyNameIsAction(GameComputerPlayer.this, name));
					}
				}
				else if (allPlayerNames == null) {
					
					// CASE 2: we don't know the names of a the players; the only thing we're
					// looking for is a StartGameInfo object; ignore everything else
					if (myInfo instanceof StartGameInfo) {
						// set our instance variable with the players' names
						allPlayerNames = ((StartGameInfo)myInfo).getPlayerNames();
						// perform game-specific initialization
						initAfterReady();
						// tell game that we're ready to play
						game.sendAction(new ReadyAction(GameComputerPlayer.this));
					}
				}
				else if (myInfo instanceof GameOverInfo) {
					
					// CASE 3: we get a "game over" message
					
					// if we are the GUI, pop up a message box and tell the
					// activity that the game is over
					if (myActivity != null) {
						gameIsOver(((GameOverInfo)myInfo).getMessage());
						myActivity.setGameOver(true);
					}
					
					// acknowledge to the game that we have receive the message
					game.sendAction(new GameOverAckAction(GameComputerPlayer.this));
					
					// mark game as being over
					gameOver = true;
				}
				else if (myInfo instanceof TimerInfo) {

					// CASE 4: we have a timer "tick"
					// if we have a timer-tick, and it's our timer object,
					// directly invoke the subclass method; otherwise, pass
					// it on as a message
					if (((TimerInfo)myInfo).getTimer() == myTimer) {
						// checking that it's from our timer
						timerTicked();
					}
					else {
						receiveInfo(myInfo);
					}
				}
				else {
					// invoke subclass method
					receiveInfo(myInfo);
				}
			}
		}
	}
	
	/**
	 * callback method--called when we are notified that the game is over
	 * 
	 * @param msg
	 * 		the "game over" message sent by the game
	 */
	protected void gameIsOver(String msg) {
		// the default behavior is to put a pop-up for the user to see that tells
		// the game's result
		MessageBox.popUpMessage(msg, myActivity);
	}
	
	/**
	 * Sleeps for a particular amount of time. Utility method.
	 * 
	 * @param milliseconds
	 * 			the number of milliseconds to sleep for
	 */
	protected void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
	
	/**
	 * Tells whether this player requires a GUI. Since this is a computer
	 * player, the answer should be 'false'.
	 */
	public final boolean requiresGui() {
		return false;
	}
	
	/** tells whether this player supports a GUI. Some computer players may be
	 * implemented to do so. In that case, they should implement the 'setAsGui'
	 * method.
	 */
	public boolean supportsGui() {
		return false;
	}
	
	/**
	 * Invoked whenever the player's timer has ticked. It is expected
	 * that this will be overridden in many games.
	 */
	protected void timerTicked() {
		// by default, do nothing
	}

	//Helper method for Smart AI
	protected boolean canCureDisease(PandemicGameState state) {
			if (state.getPlayer().getActionsLeft() <= 0) {
				return false;
			}

			else {

				//If the AI has no moves left, return false.
					if (state.getPlayer().getActionsLeft() <= 2) {
						return false;
					}

					//counters for number of each card color in player hand
					int numYellow = 0;
					int numRed = 0;
					int numBlue = 0;
					int numBlack = 0;

					//Loop through AI hand and count colors
					for (int j = 0; j < state.getPlayer().getPlayerHand().size(); j++) {
						String color = state.getPlayer().getPlayerHand().get(j).getdiseaseColor();
						if (color.equals("Yellow")) {
							numYellow++;
						}
						if (color.equals("Red")) {
							numRed++;
						}
						if (color.equals("Blue")) {
							numBlue++;
						}
						if (color.equals("Black")) {
							numBlack++;
						}

						//Return true so the AI can create a cure action.
						if (numYellow >= 5) {
							return true;
						}
						if (numRed >= 5) {
							return true;
						}
						if (numBlue >= 5) {
							return true;
						}
						if (numBlack >= 5) {
							return true;
						}
					}


			}

			return false;
		}


	//Helper method for smart AI
	protected City canMoveToResearchCenter(PandemicGameState state){
		//If the AI has no moves left, return a null city.
		if(state.getPlayer().getActionsLeft() <= 2){
			return null;
		}

		else{
			//If the current city the AI is in has a research center, just return their city
			if(state.getPlayer().getCurrentLocation().getHasResearchLab()){
				return state.getPlayer().getCurrentLocation();
			}

			else {
				//Iterate through all of the cities.
				for (City c : state.getAllCities()) {
					//If the city has a research lab...
					if (c.getHasResearchLab()) {
						//And if the AI can move to the city with the research lab...
						if (state.movePawn(state.getPlayer(), state.getPlayer().getCurrentLocation(), c)) {
							//Return the city.
							return c;
						}
					}
				}
			}
		}

		Log.e("GameComputerPlayer: ", "Something went wrong in canMoveToResearchCenter");
		return null;
	}

	}
// class GameComputerPlayer
