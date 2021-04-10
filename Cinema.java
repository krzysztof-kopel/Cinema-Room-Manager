package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static int currentIncome = 0;

    public static void showTheSeats(String[][] cinema) {
        System.out.println("Cinema:");
        for (String[] row : cinema) {
            for (String seat : row) {
                System.out.print(seat);
            }
            System.out.println();
        }
    }

    public static void buyATicket(String[][] cinema, int numberOfRows, int numberOfSeats) {
        Scanner scanner = new Scanner(System.in);
        byte numberOfFrontRows = (byte) (numberOfRows / 2);
        int rowBound = cinema.length - 1;
        int columnBound = cinema[0].length - 1;
        int rowNumber, seatNumber;
        do {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber > rowBound || seatNumber  > columnBound || rowNumber == 0 || seatNumber == 0) {
                System.out.println("Wrong input!");
            } else if (cinema[rowNumber][seatNumber].equals(" B")) {
                System.out.println("That ticket has already been purchased!");
            }
        } while (rowNumber > rowBound || seatNumber  > columnBound || rowNumber == 0 || seatNumber == 0 || cinema[rowNumber][seatNumber].equals(" B"));

        int results = 0;
        if (numberOfSeats <= 60) {
            results += 10;
            Cinema.currentIncome += 10;
        } else {
            if (rowNumber <= numberOfFrontRows) {
                results += 10;
                Cinema.currentIncome += 10;
            } else {
                results += 8;
                Cinema.currentIncome += 8;
            }
        }
        System.out.println();
        System.out.printf("Ticket price: $%d \n \n", results);

        cinema[rowNumber][seatNumber] = " B";
    }

    public static String[][] generateCinema(byte numberOfRows, byte numberOfSeatsInRow) {
        String[][] cinema = new String[numberOfRows + 1][numberOfSeatsInRow + 1];
        for (int i = 0; i <= numberOfRows; i++) {
            Arrays.fill(cinema[i], " S");
        }
        cinema[0][0] = "  ";
        for (int i = 1; i <= numberOfRows; i++) {
            cinema[i][0] = Integer.toString(i);
        }
        for (int i = 1; i <= numberOfSeatsInRow; i++) {
            cinema[0][i] = i + " ";
        }
        return cinema;
    }

    public static void printStatistics(int totalTicketsSold, int numberOfSeats, int totalIncome) {
        System.out.printf("Number of purchased tickets: %d \n", totalTicketsSold);
        double percentOfBookedSeats = ((double) totalTicketsSold / numberOfSeats) * 100d;
        System.out.printf("Percentage: %.2f%c \n", percentOfBookedSeats, '%');
        System.out.printf("Current income: $%d \n", Cinema.currentIncome);
        System.out.printf("Total income: $%d \n", totalIncome);
        System.out.println();
    }

    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        byte numberOfRows = scanner.nextByte();
        System.out.println("Enter the number of seats in each row:");
        byte numberOfSeatsInRow = scanner.nextByte();
        String[][] cinema = generateCinema(numberOfRows, numberOfSeatsInRow);
        byte numberOfSeats = (byte) (numberOfRows * numberOfSeatsInRow);
        int totalTicketsSold = 0;
        int totalIncome = 0;
        if (numberOfSeats <= 60) {
            totalIncome += 10 * numberOfSeats;
        } else {
            totalIncome += 10 * ((numberOfRows / 2) * numberOfSeatsInRow);
            totalIncome += 8 * ((numberOfRows - numberOfRows / 2) * numberOfSeatsInRow);
        }
        while (flag) {

            System.out.println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit");
            byte choice = scanner.nextByte();
            switch (choice) {
                case 1:
                    showTheSeats(cinema);
                    break;
                case 2:
                    buyATicket(cinema, numberOfRows, numberOfSeats);
                    totalTicketsSold++;
                    showTheSeats(cinema);
                    break;
                case 3:
                    printStatistics(totalTicketsSold, numberOfSeats, totalIncome);
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("I didn't understand your choice, please repeat.");
                    break;
            }
        }
    }
}