package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        //checking validity
        if (dateFrom == null || dateFrom.isEmpty()) {
            throw new RuntimeException("Date from is not valid!");
        }
        if (dateTo == null || dateTo.isEmpty()) {
            throw new RuntimeException("Date to is not valid!");
        }
        if (names == null || names.length == 0) {
            throw new RuntimeException("Names array is not valid!");
        }
        if (data == null || data.length == 0) {
            throw new RuntimeException("Data array is not valid!");
        }

        int[] salaries = new int[names.length];
        LocalDate date1;
        LocalDate date2;
        try {
            date1 = LocalDate.parse(dateFrom, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse DATE FROM, invalid value: " + dateFrom);
        }
        try {
            date2 = LocalDate.parse(dateTo, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse DATE TO, invalid value: " + dateTo);
        }

        for (int d = 0; d < data.length; d++) {
            String currentData = data[d];

            //searching for employee's index in names[]
            int currentNameIndex = -1;
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
            LocalDate currentDate;
            try {
                currentDate = LocalDate.parse(currentData.substring(0, 10), DATE_FORMAT);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Can't parse DATE in the row " + d + ", invalid value: " + currentData.substring(0, 10));
            }

            if (currentDate.isBefore(date1) || currentDate.isAfter(date2)) {
                continue;
            }

            //parsing the salary data
            int salaryIndex = currentData.lastIndexOf(' ');
            String salaryString = currentData.substring(salaryIndex + 1);
            int salaryInt;
            try {
                salaryInt = Integer.parseInt(salaryString);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Can't parse SALARY in the row " + d+ ", invalid value: " + salaryString);
            }

            int workingHoursIndex = currentData.substring(0, salaryIndex).lastIndexOf(' ');
            String workingHoursString = currentData.substring(workingHoursIndex + 1, salaryIndex);
            int workingHoursInt;
            try {
                workingHoursInt = Integer.parseInt(workingHoursString);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Can't parse WORKING HOURS in the row " + d+ ", invalid value: " + workingHoursString);
            }

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
