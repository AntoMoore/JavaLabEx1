// MooreAnthony2017LabEx1 - Anthony Moore G00170900
// Re-Run of previous lab exam

import java.io.*; // ignore 1
import java.util.*;
import javax.swing.JOptionPane;

public class MooreAnthony2017LabEx1
{
	public static void main(String[] args)throws FileNotFoundException // ignore 2
	{
		//Scanner console = new Scanner(new FileReader("LabEx1.txt")); //ignore 3

		//constants
		final double STANDARD_CHARGE = 2.10;
		final double PREMIUM_CHARGE = 2.90;
		final double PASSANGER_CHARGE = 1.00;

		final double STANDARD_RATE_A = 1.10;
		final double STANDARD_RATE_B = 1.45;
		final double PREMIUM_RATE_C = 1.40;
		final double PREMIUM_RATE_D = 1.65;

		final double CREDIT_CHARGE_1 = 3.00;
		final double CREDIT_CHARGE_2 = 4.00;
		final double CREDIT_CHARGE_3 = 5.00;

		final double CARGO_CHARGE = 10.00;
		final double VAT_RATE = 0.23;

		//variables
		int i;
		int trips;
		int passangers;
		double distance;
		char fare;
		int creditCard;
		char accommodations;

		double fareAmount = 0;
		double pasFeeAmount = 0;
		double accAmount = 0;
		double fullAmount;
		double cardAmount;
		double discAmount;
		double netAmount;
		double vatAmount;
		double finalAmount;

		double fareAmountTotal = 0;
		double pasFeeAmountTotal = 0;
		double accAmountTotal = 0;
		double fullAmountTotal = 0;
		double cardAmountTotal = 0;
		double discAmountTotal = 0;
		double netAmountTotal = 0;
		double vatAmountTotal = 0;
		double finalAmountTotal = 0;

		int tripCounterA = 0;
		int tripCounterB = 0;
		int tripCounterC = 0;
		int tripCounterD = 0;

		double highFare = 0;
		double lowFare = 0;
		int highTrip = 0;
		int lowTrip = 0;
		double avgDiscount;
		int discCounter = 0;

		Scanner console = new Scanner(System.in);

		// Prompt user for number of trips
		System.out.print("Enter number of trips today: ");
		trips = console.nextInt();
		System.out.println();

		// header
		System.out.println("Moore Anthony Lab Exam 1    TRIP  FARE   P-FEE   ACCO   FULL   CARD   DISC    NET   VAT   FINAL");
		System.out.println("===============================================================================================");

		//create loop
		for(i = 1; i <= trips; i++)
		{
			//Take all the user inputs on a single line under the string
			System.out.println("Pas/Dist/Fare/CCard/Acc " + i + ":");
			passangers = console.nextInt();
			distance = console.nextDouble();
			fare = console.next().charAt(0);
			creditCard = console.nextInt();
			accommodations = console.next().charAt(0);

			//calculate fare cost
			if (fare == 's' || fare == 'S')
			{
				if (distance <= 15.00)
				{
					fareAmount = (STANDARD_RATE_A * distance) + STANDARD_CHARGE;
					++tripCounterA;
				}
				else if (distance > 15.00)
				{
					fareAmount = (STANDARD_RATE_B * distance) + STANDARD_CHARGE;
					++tripCounterB;
				}
			}
			else if (fare == 'p' || fare == 'P')
			{
				if (distance < 15.00)
				{
					fareAmount = (PREMIUM_RATE_C * distance) + PREMIUM_CHARGE;
					++tripCounterC;
				}
				else if (distance > 15.00)
				{
					fareAmount = (PREMIUM_RATE_D * distance) + PREMIUM_CHARGE;
					++tripCounterD;
				}
			}

			//calculate passanger fee
			if (passangers == 1)
			{
				pasFeeAmount = 0.00;
			}
			else
			{
				pasFeeAmount = (passangers - 1 ) * PASSANGER_CHARGE;
			}

			//calculate Accom
			if (accommodations == 'y' || accommodations == 'Y')
			{
				accAmount = CARGO_CHARGE;
			}
			else if (accommodations == 'n' || accommodations == 'N')
			{
				accAmount = 0.00;
			}

			//calculate full amount
			fullAmount = fareAmount + pasFeeAmount + accAmount;

			//calculate credit card
			cardAmount = (fullAmount * creditCard) / 100;

			//calculate discounts
			if (distance > 150.00)
			{
				discAmount = fullAmount * 0.07;
				++discCounter;
			}
			else if (distance >= 100.00 && distance <= 150.00)
			{
				discAmount = fullAmount * 0.06;
				++discCounter;
			}
			else if (distance >= 50.00 && distance < 100.00)
			{
				discAmount = fullAmount * 0.05;
				++discCounter;
			}
			else
			{
				discAmount = 0.00;
			}

			//calculate net
			netAmount = (fullAmount + cardAmount) - discAmount;

			//calculate VAT
			vatAmount = netAmount * VAT_RATE;

			//calculate final cost
			finalAmount = netAmount + vatAmount;

			//output calculations
			System.out.printf("%32d %6.2f %6.2f %6.2f %6.2f %6.2f %6.2f %6.2f %6.2f %6.2f\n",
			       i, fareAmount, pasFeeAmount, accAmount, fullAmount, cardAmount, discAmount, netAmount, vatAmount, finalAmount);

			//calculate totals for inside footer
			fareAmountTotal += fareAmount;
			pasFeeAmountTotal += pasFeeAmount;
			accAmountTotal += accAmount;
			fullAmountTotal += fullAmount;
			cardAmountTotal += cardAmount;
			discAmountTotal += discAmount;
			netAmountTotal += netAmount;
			vatAmountTotal += vatAmount;
			finalAmountTotal += finalAmount;

			//calculate lowest cost
			if(lowFare <= 0)
			{
				lowFare = finalAmount;
				lowTrip = i;
			}
			else if(lowFare > finalAmount)
			{
				lowFare = finalAmount;
				lowTrip = i;
            }

            //calculate highest cost
			if(highFare < finalAmount)
			{
				highFare = finalAmount;
				highTrip = i;
            }

		}//for

		//output calculations
		System.out.println("===============================================================================================");
		System.out.printf("%39.2f %6.2f %6.2f %6.2f %6.2f %6.2f %6.2f %6.2f %6.2f\n",
	    	fareAmountTotal, pasFeeAmountTotal, accAmountTotal, fullAmountTotal, cardAmountTotal, discAmountTotal, netAmountTotal, vatAmountTotal, finalAmountTotal);
	    System.out.println("===============================================================================================");

		//trip counter output
		System.out.println("  " + tripCounterA + " trip(s) were fare category A");
		System.out.println("  " + tripCounterB + " trip(s) were fare category B");
		System.out.println("  " + tripCounterC + " trip(s) were fare category C");
		System.out.println("  " + tripCounterD + " trip(s) were fare category D");

		//format high/low to 2 decimal places
		String high = String.format("%.2f", highFare);
		String low = String.format("%.2f", lowFare);

		//output highest/lowest
		System.out.println();
		System.out.println("Trip " + highTrip + " Had the highest fare at: " + high);
        System.out.println("Trip " + lowTrip + " Had the lowest fare at: " + low);
        System.out.println();

		//calculate average discount
		avgDiscount = discAmountTotal / discCounter;

		//format average discount to 2 decimal places
		String disc = String.format("%.2f", avgDiscount);

		//output the average discount
		System.out.println("The average discount was: " + disc);
		System.out.println("\n=============================================================================================");

	}// main

}// class