package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATA_NUMBER_IN_LINE = 0;
    static final int NAME_NUMBER_IN_LINE = 1;
    static final int HOURS_NUMBER_IN_LINE = 2;
    static final int MONEY_PER_HOUR_NUMBER_IN_LINE = 3;
    final LocalDate startDate;
    final LocalDate finishDate;
    final LocalDate currentDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {   
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        startDate = LocalDate.parse(dateFrom, formatter);
        finishDate = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo)
                .append(System.lineSeparator());
        String [] dataNames = new String[data.length];
        String[] personData;
        for (int i = 0; i < data.length; i++) {
            personData = data[i].split(" ");
            dataNames[i] = personData[NAME_NUMBER_IN_LINE];
        }
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < dataNames.length; i++) {
                if (name.equals(dataNames[i])) {
                    personData = data[i].split(" ");
                    currentDate = LocalDate.parse(personData[DATA_NUMBER_IN_LINE], formatter);
                    if ((currentDate.isAfter(startDate)
                            || (currentDate.equals(startDate)))
                            && (currentDate.isBefore(finishDate))
                            || (currentDate.equals(finishDate))) {
                        salary += Integer.parseInt(personData[HOURS_NUMBER_IN_LINE])
                                * Integer.parseInt(personData[MONEY_PER_HOUR_NUMBER_IN_LINE]);
                    }
                }
            }
            report.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
