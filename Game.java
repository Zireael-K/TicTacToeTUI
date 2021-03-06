import java.util.Scanner;

public class Game {

	private Player playerOne;
	private Player playerTwo;
	private Player nextPlayer;
	private Player previousPlayer;
	private Scanner in;

	public Game(Player one, Player two) {
		this.playerOne = one;
		this.playerTwo = two;
		this.nextPlayer = one;
		this.previousPlayer = null;
		this.in = new Scanner(System.in);
	}

	protected void computerPlay() {
		Grid gameGrid = new Grid();
		gameGrid.print();
		System.out.println("");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (gameGrid.gameOver() == 0) {
			try {
				Thread.sleep(800);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("Your turn " + nextPlayer.getName() + ":");
			nextPlayer.takeTurnRandom(gameGrid);
			gameGrid.print();
			System.out.println("");
			previousPlayer = nextPlayer;
			nextPlayer = otherPlayer(nextPlayer);
		}
		int winner = gameGrid.gameOver();
		if (playerOne.getNumber() == winner)
			System.out.println("Player: " + playerOne.getName() + " wins! Congrats!");
		else if (playerTwo.getNumber() == winner)
			System.out.println("Player: " + playerTwo.getName() + " wins! Congrats!");
		else
			System.out.println("Draw!");

		System.out.println("");

	}

	protected void singlePlayer() {
		Grid gameGrid = new Grid();
		gameGrid.print();
		int entry;
		int gameStatus = gameGrid.gameOver();
		System.out.println("");

		while (gameGrid.gameOver() == 0) {
			System.out.println("Your turn " + nextPlayer.getName() + ":");
			if (nextPlayer.getName().equals("Computer")) {
				try {
					Thread.sleep(800);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				nextPlayer.takeTurnRandom(gameGrid);
				previousPlayer = nextPlayer;
				nextPlayer = otherPlayer(previousPlayer);
			} else {
				System.out.println("Enter a valid space number:");
				entry = in.nextInt();
				int[] coordinates = new int[2];
				coordinates = convert(entry);
				if (coordinates[0] == -1 && coordinates[1] == -1)
					System.out.println("Invalid input!");
				else if (gameGrid.isOccupied(coordinates[0], coordinates[1])) {
					System.out.println("Invalid input!");
				} else {
					gameGrid.markSpace(coordinates[0], coordinates[1], nextPlayer.getNumber());
					previousPlayer = nextPlayer;
					nextPlayer = otherPlayer(previousPlayer);
				}
			}
			gameGrid.print();
			gameStatus = gameGrid.gameOver();
			System.out.println("");
		}

		if (gameStatus == 3)
			System.out.println("It's a draw!");
		else if (gameStatus == playerOne.getNumber())
			System.out.println("Congratulations " + playerOne.getName() + "! You won!");
		else
			System.out.println("Congratulations " + playerTwo.getName() + "! You won!");
		System.out.println("");
	}

	protected void multiplayer() {
		Grid gameGrid = new Grid();
		gameGrid.print();
		int entry;
		int gameStatus = gameGrid.gameOver();

		while (gameStatus == 0) {
			System.out.println("Your move " + nextPlayer.getName() + ". Enter a valid space number:");
			entry = in.nextInt();
			int[] coordinates = new int[2];
			coordinates = convert(entry);
			if (coordinates[0] == -1 && coordinates[1] == -1)
				System.out.println("Invalid input!");
			else if (gameGrid.isOccupied(coordinates[0], coordinates[1])) {
				System.out.println("Invalid input!");
			} else {
				gameGrid.markSpace(coordinates[0], coordinates[1], nextPlayer.getNumber());
				previousPlayer = nextPlayer;
				nextPlayer = otherPlayer(previousPlayer);
			}

			gameGrid.print();
			gameStatus = gameGrid.gameOver();
			System.out.println("");
		}

		if (gameStatus == 3)
			System.out.println("It's a draw!");
		else if (gameStatus == playerOne.getNumber())
			System.out.println("Congratulations " + playerOne.getName() + "! You won!");
		else
			System.out.println("Congratulations " + playerTwo.getName() + "! You won!");
		System.out.println("");

	}

	private int[] convert(int entry) {
		int[] coordinates = new int[2];
		switch (entry) {
		case 1:
			coordinates[0] = 0;
			coordinates[1] = 0;
			break;
		case 2:
			coordinates[0] = 0;
			coordinates[1] = 1;
			break;
		case 3:
			coordinates[0] = 0;
			coordinates[1] = 2;
			break;
		case 4:
			coordinates[0] = 1;
			coordinates[1] = 0;
			break;
		case 5:
			coordinates[0] = 1;
			coordinates[1] = 1;
			break;
		case 6:
			coordinates[0] = 1;
			coordinates[1] = 2;
			break;
		case 7:
			coordinates[0] = 2;
			coordinates[1] = 0;
			break;
		case 8:
			coordinates[0] = 2;
			coordinates[1] = 1;
			break;
		case 9:
			coordinates[0] = 2;
			coordinates[1] = 2;
			break;
		default:
			coordinates[0] = -1;
			coordinates[1] = -1;
			break;

		}

		return coordinates;
	}

	private Player otherPlayer(Player player) {
		if (player == playerOne)
			return playerTwo;
		else
			return playerOne;
	}

}
