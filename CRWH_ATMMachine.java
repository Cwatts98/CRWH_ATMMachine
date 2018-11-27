/*
*By: Caesar R. Watts-Hall
*Class: JAVA 1
*Instructor: Dr.Primo
*Assignment: ATM Machine
*Date: 09/25/2018 [COMPLETED]
*Due: 10/02/2018 @9:00am
*/

//START
package crwh_atmmachine;
import java.util.Scanner;

public class CRWH_ATMMachine {
static String crwhUserName = " ";     
public static Scanner scr = new Scanner(System.in);
// The registerID method (found below), determines if acctUSER and acctPWD are both validated into the system. 

public static String registerID(String acctUSER, String acctPWD)
    {
        String result = "ERROR!";
        // Strings [RA, RB, and RC] (found below), contain the predefined account information. Its as follows:
        // [Username (space), Password (space), and Account Balance]

        String RA = "JohnDoe passwordDoe 10000.00";     //Username: JohnDoe     | Password: passwordDoe     | Account: $10,000.00
        String RB = "JaneDoe passwordJane 500.00";      //Username: JaneDoe     | Password: passwordJane    | Account: $500.00
        String RC = "SarahConner helloword2000 600.00"; //Username: SarahConner | Password: helloword2000   | Account: $600.00 
        
        if (acctUSER.equals(RA.substring(0, RA.indexOf(" "))) && 
         acctPWD.equals(RA.substring(RA.indexOf(" ")+1,RA.lastIndexOf(" ")))){
         crwhUserName = RA.substring(0,7);
         result = RA.substring(RA.lastIndexOf(" "));
        }
        
        if (acctUSER.equals(RB.substring(0, RB.indexOf(" "))) && 
         acctPWD.equals(RB.substring(RB.indexOf(" ")+1,RB.lastIndexOf(" ")))){
         crwhUserName = RB.substring(0,7);
         result = RB.substring(RB.lastIndexOf(" "));
        }

        if (acctUSER.equals(RC.substring(0, RC.indexOf(" "))) && 
         acctPWD.equals(RC.substring(RC.indexOf(" ") + 1,RC.lastIndexOf(" ")))){
         crwhUserName = RC.substring(0,11);
         result = RC.substring(RC.lastIndexOf(" "));
        }
        return result;
    }

public static void main(String[] args) {

        String acctUSER, acctPWD, origBALANCE = "ERROR!";
        int count = 0, menuOption = 0;
        double depositAMT = 0, withdrawAMT = 0, currentBALANCE=0; 
        boolean NonDigit;
        
        //This loop will keep track of the amount of attempts made into the system.
        //Any more than three will automatically result in a temporary log-out, where your
        //account will be suspended.
        do{
            System.out.println("***********************************************************************");
            System.out.println("Hello, and welcome to the bank of C.R.W.H."); //Introduction
            System.out.println("\n");
            
                NonDigit = true;
                System.out.print("Please enter your UserName: "); //Username
                acctUSER = scr.next();

                System.out.print("Please enter your Password: "); //Password
                acctPWD = scr.next();

            origBALANCE = registerID(acctUSER, acctPWD);
            count++;

            if (count >= 3 && origBALANCE.equals("ERROR!")){
                System.out.println("-----------------------------------------------------");
                System.out.println("Maximum Login Attempts Reached. Please renew account"
                           + "username or password online, or, reach out at 210-978-XXXX."); //Automated suspension message
                System.out.println("-----------------------------------------------------");
                System.exit(0);
            }
            
            if (!(origBALANCE.equals("ERROR!"))){
                System.out.println("--------------------------------------------------------------");
                System.out.println(crwhUserName + "," + " Your Current Balance is: $" + origBALANCE); //Completed task message
                System.out.println("--------------------------------------------------------------");
            }
            
            else
                System.out.println(origBALANCE);
        }
        
        while(origBALANCE.equals("ERROR!"));
        currentBALANCE=Double.parseDouble(origBALANCE);
        //Here, the created loop will hold the inputs from the current user.

        while (menuOption != 4)
        { 
            menuOption=interfaceMenu();
            switch (menuOption)
            {
                
            case 1:
                balanceDisplay(currentBALANCE); //Current account balance
                break;
                
            case 2:
                System.out.print("Please enter the amount you'd like to Deposit: $ "); //Putting money into the account.
                depositAMT = scr.nextDouble();
                currentBALANCE=deposit(depositAMT, currentBALANCE);
                break;
                
            case 3:
                System.out.print("Please enter the amount you'd like to Withdrawal: $ "); //Taking money out of the account.
                withdrawAMT = scr.nextDouble();

                while(withdrawAMT > currentBALANCE)
                {
                    System.out.println("System Error - Inadequate funding!"
                    + "Choose a different amount.");
                     withdrawAMT = scr.nextDouble();
                }
                currentBALANCE = withdrawal(currentBALANCE, withdrawAMT);
                break;
                
            case 4:
                System.out.println("\n");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Thank you for your time " + crwhUserName + "," + " See you again soon."); //Thank you message
                System.out.println("----------------------------------------------------------------------");
                System.out.println("\n");
                System.out.println("*************************************************************************");
                System.exit(0);
                break;
            } //System End
        } 
    }

    public static int interfaceMenu()
    {
        int menuChoice;
        do
        {   
            System.out.println("\n");
            System.out.println("=========================================");
            System.out.println(" Choose either of the following Options: "); //The main menu interface has 4 distinct options to choose from:
            System.out.println("=========================================");
            
            System.out.println("==========================="); 
            System.out.println("  [1]  Account Balance     "); //[1] Displaying the current account balance
            System.out.println("  [2]  Deposit             "); //[2] Depositing Money into account
            System.out.println("  [3]  Withdrawal          "); //[3] Withdrawing Money from account
            System.out.println("  [4]  Exit                "); //[4] Exiting the system
            System.out.println("==========================="); 
            
            System.out.print("Option: "); //Input the decided number [1, 2, 3, or 4]. 
            menuChoice = scr.nextInt();

            if (menuChoice < 1 || menuChoice > 4){
                System.out.println("ERROR! Choose from keypad menu options above!"); //Any non-defined number will give an error message.
            }

        }
        while (menuChoice < 1 || menuChoice > 4);
        return menuChoice;
    }

    public static void balanceDisplay (double x)
            //Displays the crwhUserName's account balance.
    {   
        
        System.out.println("\n");
        System.out.println("******************************************************");
        System.out.printf (crwhUserName + "," + " here is your balance: $%.2f\n", x); //Completed task message.
        System.out.println("******************************************************"); 
    }
 
    public static double deposit(double x, double y) 
            //Here, a subtext will ask you to input the desired amount into your account.
    {
        double depositAmt = y, currentBal = x;
        double newBalance = depositAmt + currentBal;

        System.out.println("\n");
        System.out.println("*******************************************************************");
        System.out.printf (crwhUserName + "," + " here is your new balance: $%.2f\n", newBalance); //Completed task message.
        System.out.println("*******************************************************************");
        return newBalance;
    }

    public static double withdrawal(double x, double y)
            //Like Deposit, a subtext will ask you to input the amount you'd wish to withdraw from your account.
    {
        double withdrawAmt = y, currentBal = x, newBalance;
        newBalance = currentBal - withdrawAmt;
        
        System.out.println("\n");
        System.out.println("*******************************************************************");
        System.out.printf (crwhUserName + "," + " here is your new balance: $%.2f\n", newBalance); //Completed task message.
        System.out.println("*******************************************************************");
        return newBalance;  
    }
}
//END