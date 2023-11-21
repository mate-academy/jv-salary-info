package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        LocalDate comparisonDate;
        int[] salaryOfNames = new int[names.length];
        int writtenLineCounter = 0;
        int countsOfValideDays = 0;
        int numberOfName = 0;

        for (String datum : data) {
            String[] oneLineArray = datum.split(" ");
            comparisonDate = LocalDate.parse(oneLineArray[0], formatter);

            if (comparisonDate.isAfter(dateStart.minusDays(1))
                    && comparisonDate.isBefore(dateEnd.plusDays(1))) {
                countsOfValideDays++;
            }
        }

        for (int i = 0; i < data.length; i++) {
            String[] oneLineArray = data[i].split(" ");
            comparisonDate = LocalDate.parse(oneLineArray[0], formatter);
            if (comparisonDate.isAfter(dateStart.minusDays(1))
                    && comparisonDate.isBefore(dateEnd.plusDays(1))) {
                if (oneLineArray[1].equals(names[numberOfName])) {
                    salaryOfNames[numberOfName] += (Integer.parseInt(oneLineArray[2]))
                            * (Integer.parseInt(oneLineArray[3]));
                    writtenLineCounter++;
                }

            }
            if (i == data.length - 1 && writtenLineCounter < countsOfValideDays) {
                numberOfName++;
                i = 0;
            }
        }
        numberOfName = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateStart.format(formatter)).append(" - ").append(dateEnd.format(formatter))
                .append(System.lineSeparator());
        for (int i = 0; i < salaryOfNames.length; i++) {

            stringBuilder.append(names[numberOfName]).append(" - ")
                    .append(salaryOfNames[numberOfName]);
            stringBuilder.append(System.lineSeparator());
            numberOfName++;
        }
        return stringBuilder.toString().trim();
    }
}
