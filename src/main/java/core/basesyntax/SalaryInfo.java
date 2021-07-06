package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        //TODO: HERE MAY APPEARS NullPointerException
        // (if dateFrom == null || dateTo == null):
        dateFrom = dateFrom.trim();
        dateTo = dateTo.trim();
        //TODO: HERE MAY APPEARS NullPointerException
        // (if names == null):
        int[] salaries = new int[names.length];
        //TODO: HERE MAY APPEARS NullPointerException
        // (if dateFrom == null or dateTo == null)
        // or DateTimeParseException
        // (if dateFrom or dateTo has invalid formats):
        LocalDate date1 = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate date2 = LocalDate.parse(dateTo, DATE_FORMAT);

        //TODO: HERE MAY APPEARS NullPointerException
        // (if data == null):
        for (String currentData : data) {
            //searching for employee's index in names[]
            int currentNameIndex = -1;
            //TODO: if we hadn't check it yet in 15th row, HERE MAY APPEARS NullPointerException
            // (if names == null):
            for (int i = 0; i < names.length; i++) {
                if (!currentData.contains(names[i])) {
                    continue;
                }
                currentNameIndex = i;
                break;
            }
            if (currentNameIndex == -1) {
                //if there is no such name in names[], so that means we
                // don't need to calculate the employee's salary
                continue;
            }

            //checks if the date is reliable
            //TODO: HERE MAY APPEARS DateTimeParseException
            // (if the parsed substring has invalid format):
            LocalDate currentDate = LocalDate.parse(currentData.substring(0, 10), DATE_FORMAT);
            if (currentDate.isBefore(date1) || currentDate.isAfter(date2)) {
                continue;
            }

            //parsing the salary data
            int salaryIndex = currentData.lastIndexOf(' ');
            String salaryString = currentData.substring(salaryIndex + 1);
            //TODO: HERE MAY APPEARS NumberFormatException
            // (if we hadn't find space symbol and salaryString contains not only salary data):
            int salaryInt = Integer.parseInt(salaryString);

            //TODO: if we hadn't check it yet in 47th row, HERE MAY APPEARS
            // StringIndexOutOfBoundsException
            // (if we hadn't find space symbol and salaryIndex < 0):
            int workingHoursIndex = currentData.substring(0, salaryIndex).lastIndexOf(' ');
            String workingHoursString = currentData.substring(workingHoursIndex + 1, salaryIndex);
            //TODO: HERE MAY APPEARS NumberFormatException
            // (if we hadn't find space symbol and workingHoursString contains
            // not only working hours data):
            int workingHoursInt = Integer.parseInt(workingHoursString);

            //if everything is ok, raising current salary in salaries[]
            salaries[currentNameIndex] += workingHoursInt * salaryInt;
        }

        //resulting the string with salary data
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }

        return result.toString();
    }
}
