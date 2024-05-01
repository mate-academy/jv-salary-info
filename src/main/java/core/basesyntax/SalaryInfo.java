package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DATE_INDEX = 0;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int PAY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        String[] reportForPeriod = names.clone();
        StringBuilder reportBuilder = new StringBuilder("Report for period ")
                .append(FORMATTER.format(fromDate))
                .append(" - ")
                .append(FORMATTER.format(toDate));

        for (int i = 0; i < names.length; i++) {
            int reportForPeriodSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] temp = data[j].split(" ");
                LocalDate workingDate = LocalDate.parse(temp[WORKING_DATE_INDEX], FORMATTER);
                double workingHours = Double.parseDouble(temp[WORKING_HOURS_INDEX]);
                double workingRate = Double.parseDouble(temp[PAY_RATE_INDEX]);
                double salary = workingHours * workingRate;
                if (data[j].contains(names[i])
                        && (!workingDate.isBefore(fromDate) && !workingDate.isAfter(toDate))) {
                    reportForPeriodSalary += salary;
                }
            }
            reportForPeriod[i] = reportForPeriod[i] + " - " + reportForPeriodSalary;
        }

        for (String element : reportForPeriod) {
            reportBuilder.append(System.lineSeparator())
                    .append(element);
        }
        return reportBuilder.toString();
    }
}
