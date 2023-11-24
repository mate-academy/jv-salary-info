package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        int[] salaries = new int[names.length];

        for (String record : data) {
            try {
                String[] partsData = record.split(" ");
                String dateFromEmployee = partsData[0];
                String nameEmployee = partsData[1];
                String workHourEmployee = partsData[2];
                String salaryHourEmployee = partsData[3];

                LocalDate fromDateEmployee = LocalDate.parse(dateFromEmployee, formatter);

                if ((fromDateEmployee.isAfter(fromDate) || fromDateEmployee.isEqual(fromDate))
                        && (fromDateEmployee.isBefore(toDate)
                        || fromDateEmployee.isEqual(toDate))) {
                    int salaryDay = Integer.parseInt(salaryHourEmployee)
                            * Integer.parseInt(workHourEmployee);

                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(nameEmployee)) {
                            salaries[i] += salaryDay;
                        }
                    }
                }
            } catch (DateTimeParseException exception) {
                System.out.println("Wrong date format.");;
            }
        }

        StringBuilder resultInfo = new StringBuilder("Report for period ");
        resultInfo.append(fromDate.format(formatter))
                .append(" - ").append(toDate.format(formatter));
        for (int i = 0; i < names.length; i++) {
            resultInfo.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }

        return resultInfo.toString();

    }
}
