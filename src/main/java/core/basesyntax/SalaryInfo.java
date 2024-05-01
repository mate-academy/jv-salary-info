package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        String[] reportForPeriod = names.clone();
        StringBuilder reportBuilder = new StringBuilder("Report for period ")
                .append(dateFormatter.format(fromDate))
                .append(" - ")
                .append(dateFormatter.format(toDate));

        for (int i = 0; i < names.length; i++) {
            int reportForPeriodSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] temp = data[j].split(" ");
                LocalDate workingDate = LocalDate.parse(temp[0], dateFormatter);
                double workingHours = Double.parseDouble(temp[2]);
                double workingRate = Double.parseDouble(temp[3]);
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
