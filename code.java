//M.L.L.Weerasinghe
//IT20664862
//4.1 batch malabe branch


import java.util.Scanner;
import java.util.Random;

//Parent class of soldier and hunter
class Person {
	protected String color = new String();
	protected int x_position;
	protected int y_position;
	
	//initialize x,y coordinates
	public void setxPOS(int x) {
		x_position = x;
	}
	
	public void setYPOS(int y) {
		y_position = y;
	}
	
}

//sound exception
class SoundException extends Exception{
	
	public SoundException(String msg) {
		super(msg);
	}
	
}

//abstract class for Soldier
abstract class Soldier extends Person{
	
	public abstract void hunt();
	protected void gameOver() {
		System.out.println("Game over");
	}
	
}

//3 solder types green , blue , red
class GreenSoldier extends Soldier{
	
	public void hunt() {
		System.out.println("Killed using a knife");
		gameOver();
	}
	
}

class BlueSoldier extends Soldier{

	public void hunt() {
		System.out.println("Killed using a gun");
		gameOver();
	}
	
}

class RedSoldier extends Soldier{
	
	public void hunt() {
		System.out.println("Killed using the hand");
		gameOver();
	}
	
}

class Hunter extends Person{
	
	private String user_name = new String();
	
	Hunter(String user_name , String color){
		this.user_name = user_name;
		this.color = color;
	}
	
	public void move(Hunter movement) throws SoundException {
		
		//check moving position hit to the wall
		if(movement.x_position >= 250 || movement.y_position >= 360) {
			throw new SoundException("Oh oo!!");
		}else {
			System.out.println("Hunter is moving, X:" + movement.x_position + " Y:" + movement.y_position);
		}
		
	}
	
	public void hunt(Board board) {
		String dTyp = new String(board.dotType);
		
		//check dot type
		if(dTyp.equals("superDot")) {
			board.superDot -= 1;
			System.out.println("Hunting super dots");
		}else {
			board.dot -= 1;
			System.out.println("Hunting dots");
		}
		
		//check user won or not (superdon <= 0 and dot <=97 mean hunter won)
		if(board.superDot <= 0 && board.dot <= 0) {
			System.out.println("You won the game!");
		}
		
	}
}

class Board {
	
	protected String dotType ;
	protected Hunter myHunter;
	protected Soldier threeSoldiers[];
	protected int dot = 97;
	protected int superDot = 3;
	
	Board(String dotType,Hunter hunter, Soldier threesoldiers[]){
		this.dotType = new String(dotType);
		myHunter = hunter;
		this.threeSoldiers = threesoldiers;
		
		//positioning hunter and 3 soldiers
		init();
	}
	
	//initialize the hunter and solders
	private void init(){
		myHunter.x_position = 0;
		myHunter.y_position = 0;
		
		//Random Number Genereting for soldiers
		Random num = new Random();

		for(int i = 0;i < 3;i++){
			threeSoldiers[i].x_position = num.nextInt(250);
			threeSoldiers[i].y_position = num.nextInt(360);
		}
		
		System.out.println("Board is ready and three soldiers and the hunter is positioned in the board");
	}
	
}

public class Main {

	public static void main(String[] args) throws SoundException {
		
		Scanner sc = new Scanner(System.in);
		Hunter myhunter = new Hunter("Maha Deva","Brown");
		Soldier threeSoldiers[] = {new RedSoldier(),new RedSoldier(),new GreenSoldier()};
		Board myboard = new Board("superDot", myhunter, threeSoldiers);
		
		System.out.println("Use the keyboard up,down,left ,right arrow keys to move the hunter");
		myhunter.setxPOS(sc.nextInt());
		myhunter.setYPOS(sc.nextInt());
		myhunter.move(myhunter);
		myhunter.hunt(myboard);
		threeSoldiers[2].hunt();
		
	}

}