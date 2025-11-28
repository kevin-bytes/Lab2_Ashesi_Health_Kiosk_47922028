import java.util.Scanner;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Ashesi Health Kiosk");

        System.out.print("Enter service code (P/L/T/C): ");
        char serviceCode = scanner.next().toUpperCase().charAt(0);

        String serviceDesk = "";
        switch(serviceCode) {
            case 'P': serviceDesk = "Pharmacy Desk"; break;
            case 'L': serviceDesk = "Lab Desk"; break;
            case 'T': serviceDesk = "Triage Desk"; break;
            case 'C': serviceDesk = "Counseling Desk"; break;
            default: serviceDesk = "Invalid service code";
        }
        System.out.println("Go to: " + serviceDesk);

        double metricValue = 0;
        int roundedMetric = 0;
        String metricDisplay = "";

        if(serviceCode == 'T') {
            System.out.print("Enter health metric (1 for BMI, 2 for Dosage, 3 for Trig): ");
            int choice = scanner.nextInt();

            if(choice == 1) {
                System.out.print("Enter weight(kg): ");
                double weight = scanner.nextDouble();
                System.out.print("Enter height(m): ");
                double height = scanner.nextDouble();
                double bmi = weight / (height * height);
                double roundedBmi = Math.round(bmi * 10) / 10.0;
                metricValue = roundedBmi;
                roundedMetric = (int) Math.round(bmi);

                String category = "";
                if(bmi < 18.5) category = "Underweight";
                else if(bmi < 25.0) category = "Normal";
                else if(bmi < 30.0) category = "Overweight";
                else category = "Obese";

                System.out.println("BMI: " + roundedBmi + " Category: " + category);
                metricDisplay = "BMI=" + roundedBmi;
            }
            else if(choice == 2) {
                System.out.print("Enter required dosage mg: ");
                double dosage = scanner.nextDouble();
                int tablets = (int) Math.ceil(dosage / 250);
                metricValue = tablets;
                roundedMetric = tablets;
                System.out.println("Tablets: " + tablets);
                metricDisplay = "Tablets=" + tablets;
            }
            else if(choice == 3) {
                System.out.print("Enter angle in degrees: ");
                double degrees = scanner.nextDouble();
                double radians = Math.toRadians(degrees);
                double sinValue = Math.round(Math.sin(radians) * 1000) / 1000.0;
                double cosValue = Math.round(Math.cos(radians) * 1000) / 1000.0;
                metricValue = sinValue * 100;
                roundedMetric = (int) Math.round(sinValue * 100);
                System.out.println("Sin: " + sinValue + " Cos: " + cosValue);
                metricDisplay = "Sin=" + sinValue;
            }
        }

        char randomChar = (char) ('A' + Math.random() * 26);
        int num1 = 3 + (int)(Math.random() * 7);
        int num2 = 3 + (int)(Math.random() * 7);
        int num3 = 3 + (int)(Math.random() * 7);
        int num4 = 3 + (int)(Math.random() * 7);

        String shortID = "" + randomChar + num1 + num2 + num3 + num4;

        if(shortID.length() != 5) {
            System.out.println("Invalid length");
        }
        else if(!Character.isLetter(shortID.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
        }
        else if(!Character.isDigit(shortID.charAt(1)) || !Character.isDigit(shortID.charAt(2)) || !Character.isDigit(shortID.charAt(3)) || !Character.isDigit(shortID.charAt(4))) {
            System.out.println("Invalid: last 4 must be digits");
        }
        else {
            System.out.println("ID OK");
        }

        System.out.print("Enter your first name: ");
        String firstName = scanner.next();

        char baseCode = firstName.toUpperCase().charAt(0);
        char shiftedLetter = (char) ('A' + (baseCode - 'A' + 2) % 26);
        String lastTwoID = shortID.substring(3);

        String displayCode = shiftedLetter + lastTwoID + "-" + roundedMetric;
        System.out.println("Display Code: " + displayCode);

        String serviceName = "";
        switch(serviceCode) {
            case 'P': serviceName = "PHARMACY"; break;
            case 'L': serviceName = "LAB"; break;
            case 'T': serviceName = "TRIAGE"; break;
            case 'C': serviceName = "COUNSELING"; break;
        }

        String summary = serviceName + " | ID=" + shortID;
        if(serviceCode == 'T' && !metricDisplay.isEmpty()) {
            summary += " | " + metricDisplay;
        }
        summary += " | Code=" + displayCode;

        System.out.println("Summary: " + summary);

        scanner.close();
    }
}