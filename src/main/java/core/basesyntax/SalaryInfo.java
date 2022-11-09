package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(
            String[] names, String[] data,
            String dateFrom, String dateTo) {

        StringBuilder reportForPeriod = new StringBuilder("Report for period ");
        reportForPeriod.append(dateFrom).append(" - ").append(dateTo);
        SalaryInfo dateParser = new SalaryInfo();
        LocalDate dateStringFrom = dateParser.dateParsing(dateFrom);
        LocalDate dateStringTo = dateParser.dateParsing(dateTo);

        for (String name : names) {
            int amount = 0;
            for (String each1 : data) {
                String[] dataEach = each1.split(" ");
                LocalDate dateTempFromData = dateParser.dateParsing(dataEach[0]);
                String nameTemp = dataEach[1];
                int hours = Integer.parseInt(dataEach[2]);
                int salaryPerHour = Integer.parseInt(dataEach[3]);

                if ((dateTempFromData.isAfter(dateStringFrom)
                        && dateTempFromData.isBefore(dateStringTo)
                        || dateTempFromData.equals(dateStringFrom)
                        || dateTempFromData.equals(dateStringTo))
                        && nameTemp.equals(name)) {
                    {
                        amount += hours * salaryPerHour;
                    }
                }
            }
            reportForPeriod.append(System.lineSeparator())
                    .append(name).append(" - ").append(amount);
        }
        return reportForPeriod.toString();
    }

    public LocalDate dateParsing(String date) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate resultDate;
        try {
            resultDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", date);
            throw exc; // Rethrow the exception.
        }
        return resultDate;
    }
}
