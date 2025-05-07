package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy"); //Define the date format for the program
    private static final int DEAFULT_DATA_TO_ARAY = 0;
    private static final int DATE_POSS = 0;
    //Setting the Date position in the Table
    private static final int EMPLOY_NAME_POSS = 1;
    //Setting the position of the Employee's Name in the Board
    private static final int HOURS_WORKED_POSS = 2;
    //Setting the Number of hours worked on a given day
    private static final int SALARY_ERNED_POSS = 3;
    //Setting the hourly rate item

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String header = "Report for period " + dateFrom + " - " + dateTo;
        //Defining a header for an array
        StringBuilder stringBuilder = new StringBuilder(header);
        //Building StringBuilder

        LocalDate localFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        //Retrieving dateFrom and converting it to LocalDate
        LocalDate localTo = LocalDate.parse(dateTo,DATE_TIME_FORMATTER);
        //Retrieving dateTo and converting it to LocalDate

        int[] salaries = new int[names.length];
        //Creating a pay table with the length of the number of employee names
        Arrays.fill(salaries,DEAFULT_DATA_TO_ARAY);
        //Setting position no. 0 as empty under the header

        for (int i = 0; i < names.length; i++) {
            for (String split : data) {
                String[] splitData = split.split(" ");
                if (isValid(names[i], splitData, localFrom, localTo)) {
                    salaries[i] += Integer.parseInt(splitData[HOURS_WORKED_POSS])
                            * Integer.parseInt(splitData[SALARY_ERNED_POSS]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaries[i]);
        }

        return stringBuilder.toString();
        //Returning the resulting result as "String" text
    }

    private boolean isValid(String employeName, String[] recordData,
                            LocalDate dateFrom, LocalDate dateTo) {
        LocalDate recordDate = LocalDate.parse(recordData[DATE_POSS], DATE_TIME_FORMATTER);
        return employeName.equals(recordData[EMPLOY_NAME_POSS])
                && !recordDate.isBefore(dateFrom)
                && !recordDate.isAfter(dateTo);
    }
}
