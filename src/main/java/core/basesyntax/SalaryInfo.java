package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private StringBuilder report;

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

        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(dateFrom, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse DATE FROM, invalid value: " + dateFrom);
        }
        try {
            to = LocalDate.parse(dateTo, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can't parse DATE TO, invalid value: " + dateTo);
        }

        report = new StringBuilder()
                .append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
                String currentData = data[dataIndex];

                //if it's not our employee, continue until we find him or her
                if (!currentData.contains(name)) {
                    continue;
                }

                //checks if the date is reliable
                LocalDate currentDate;
                try {
                    currentDate = LocalDate.parse(currentData.substring(0, 10), DATE_FORMAT);
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Can't parse DATE in the row " + dataIndex
                            + ", invalid value: " + currentData.substring(0, 10));
                }

                if (currentDate.isBefore(from) || currentDate.isAfter(to)) {
                    continue;
                }

                //parsing the salary data
                int salaryIndex = currentData.lastIndexOf(' ');
                String salaryString = currentData.substring(salaryIndex + 1);
                int salaryInt;
                try {
                    salaryInt = Integer.parseInt(salaryString);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Can't parse SALARY in the row " + dataIndex
                            + ", invalid value: " + salaryString);
                }

                int workingHoursIndex = currentData.substring(0, salaryIndex).lastIndexOf(' ');
                String workingHoursString = currentData
                        .substring(workingHoursIndex + 1, salaryIndex);
                int workingHoursInt;
                try {
                    workingHoursInt = Integer.parseInt(workingHoursString);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Can't parse WORKING HOURS in the row " + dataIndex
                            + ", invalid value: " + workingHoursString);
                }

                //if everything is ok, raising current salary
                salary += workingHoursInt * salaryInt;
            }
            //appending the employee's salary data
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
