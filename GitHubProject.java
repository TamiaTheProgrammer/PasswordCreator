import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class GitHubProject 
{
    // Function to create or check file
    public static boolean FileChecker(String fileName, Scanner scanner) 
    {
        File file = new File(fileName);

        // Check if the file exists
        if (file.exists()) 
        {
            System.out.println("The file \"" + fileName + "\" already exists.");
            System.out.print("Would you like to add more to this file? (Y/N): ");
            String answer = scanner.nextLine();
            
            // Check user's answer with a loop for invalid inputs
            while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) 
            {
                System.out.println("Invalid input. Please enter Y or N.");
                answer = scanner.nextLine();
            }
            
            if(answer.equalsIgnoreCase("y"))
            {
                return true;
            }
            if(answer.equalsIgnoreCase("n"))
            {
                return false;
            }
            
        } 
        else 
        {
            try 
            {
                // Try to create the file
                if (file.createNewFile()) 
                {
                    System.out.println("The file \"" + fileName + "\" has been created.");
                    return true;
                } 
                else 
                {
                    System.out.println("The file could not be created.");
                    return false;
                }
            } 
            catch (IOException e) 
            {
                System.out.println("An error occurred while creating the file.");
            }
        }

        return false;  // Default return to handle all cases
    }
    
    public static String generateRandomPassword(int length) 
    {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$&_?";
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) 
        {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    // Function to add text to the file
    public static void PasswordCreator(String fileName, Scanner scanner) 
    {
        try (FileWriter fw = new FileWriter(fileName, true); PrintWriter pw = new PrintWriter(fw)) 
        {
            int amount = 0;
            int length = 0;
            
            
            System.out.println("How many passwords would you like to create?: ");
            amount = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("What length would you like the password to be?");
            System.out.println("Note: A Strong viable password should be at least 8 characters long, ");
            System.out.println("And contain At least 1 special character and 1 number");
            
            length = scanner.nextInt();
            scanner.nextLine();
            
            if(length < 8)
            {
                System.out.println("This is a very insecure password length, ");
                System.out.println("Are you sure this is the length you want? (Y/N)");
                
                String ConfirmBadPassword = scanner.nextLine();
                
                if(ConfirmBadPassword.equalsIgnoreCase("y"))
                {
                }
                
                if(ConfirmBadPassword.equalsIgnoreCase("n"))
                {
                    System.out.println("What length would you like the password to be?: ");
                    length = scanner.nextInt();
                    scanner.nextLine();
                }
                
            }
            
            for (int i = 0; i < amount; i++) 
            {
                String password = generateRandomPassword(length);
                pw.println(password);  // Write each password to the file
            }

            System.out.println("Passwords added to the file successfully.");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    public static void main(String[] args) 
    {
        System.out.println("This program is going to create string passwords that you can use.");
        try (Scanner scanner = new Scanner(System.in)) {
            String fileName;
            
            boolean result = false;
            
            System.out.print("Enter the name of the file you would like to use/create: ");
            fileName = scanner.nextLine();
            while (!result) 
            {
                // Call the FileChecker function
                result = FileChecker(fileName, scanner);
                
                if (!result)
                {
                    System.out.println("No file created. Let's try again.");
                    System.out.print("Enter the name of the file you would like to use/create: ");
                    fileName = scanner.nextLine();
                }
            }
            
            // After creating or confirming the file, prompt the user to add text
            PasswordCreator(fileName, scanner);
        }
    }
}