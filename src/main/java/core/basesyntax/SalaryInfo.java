package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        String[] employeeFullInfo = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            String nameFromStringNames = names[i];
            int fullSalaryPerName = 0;

            for (int j = 0; j < data.length; j++) {
                String nameFromString = null;
                String[] sourceString = data[j].split(" ");
                LocalDate actualDate = LocalDate.parse(sourceString[0], DATE_TIME_FORMATTER);
                nameFromString = sourceString[1];

                if (actualDate.isBefore(toDate.plusDays(1))
                        && actualDate.isAfter(fromDate.minusDays(1))) {
                    int salaryFor = 0;
                    if (nameFromString.equals(nameFromStringNames)) {
                        int hours = Integer.parseInt(sourceString[2]);
                        int hourlyRate = Integer.parseInt(sourceString[3]);
                        salaryFor = hours * hourlyRate;
                        fullSalaryPerName += salaryFor;
                    }
                }
            }
            employeeFullInfo[i] = nameFromStringNames + " - " + fullSalaryPerName;
        }

        String finalList = String.join(System.lineSeparator(), employeeFullInfo);
        String fullInfo = "Report for period " + DATE_TIME_FORMATTER.format(fromDate) + " - "
                + DATE_TIME_FORMATTER.format(toDate) + System.lineSeparator() + finalList;

        return fullInfo;
    }

}
