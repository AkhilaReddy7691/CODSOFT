import java.util.*;
public class guessinggame 
{
    public static void main(String[] args) 
	{
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain = true;

        while (playAgain) 
		{
            int originalNumber = random.nextInt(100) + 1; 
            int attempts = 0;
            int maxAttempts = 10;
            boolean guessedCorrectly = false;

            System.out.println("I'm thinking of a number between 1 and 100. Can you guess what it is?");

            while (attempts < maxAttempts && !guessedCorrectly) 
			{
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                if (userGuess == originalNumber) 
				{
                    System.out.println("Congratulations! You guessed the number correctly.");
                    guessedCorrectly = true;
                    score += maxAttempts - attempts + 1; 
                } 
				else if (userGuess < originalNumber) 
				{
                    System.out.println("userGuess is too low than the originalNumber ! Try again.");
                } 
				else 
				{
                    System.out.println("userGuess is too high than the originalNumber ! Try again.");
                }
            }

            if (!guessedCorrectly) 
			{
                System.out.println("You've used all your attempts. The number was: " + originalNumber);
            }

            System.out.println("Your current score is: " + score);

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();

            if (!response.equalsIgnoreCase("yes")) 
			{
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing! Your final score is: " + score);
        scanner.close();
    }
}
