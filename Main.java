import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class Main {

 public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
  
  Scanner scanner = new Scanner(System.in);
  
  File file1 = new File("vocal.wav");
  File file2 = new File("boss_theme.wav");
  File file3 = new File("super_boss.wav");
  AudioInputStream audioStream1  = AudioSystem.getAudioInputStream(file1);
  AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
  AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(file3);
  Clip clip = AudioSystem.getClip();
  System.out.println("Do you want to listen to song one, song two, or song three? ");
  int input = scanner.nextInt();
  if(input ==  1){
  	clip.open(audioStream1);
  }
  if(input ==  2){
	clip.open(audioStream2);
  }
  if(input == 3){
	  clip.open(audioStream3);
}
  else {
	System.out.println("Invalid input");
  } 
  String response = "";
  

  while(!response.equals("Q")) {
   System.out.println("P = play, S = Stop, R = Reset, Q = Quit");
   System.out.print("Enter your choice: ");
   
   response = scanner.next();
   response = response.toUpperCase();
   
   switch(response) {
    case ("P"): clip.start();
    break;
    case ("S"): clip.stop();
    break;
    case ("R"): clip.setMicrosecondPosition(0);
    break;
    case ("Q"): clip.close();
    break;
    default: System.out.println("Not a valid response");
   }
   
  }
  System.out.println("Byeeee!"); 
 }
}
