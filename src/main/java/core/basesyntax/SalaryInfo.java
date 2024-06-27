package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder getSalaryOutput = new StringBuilder();
        int[] salary = new int[names.length];
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate dateFromFormated = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateToFormated = LocalDate.parse(dateTo, formatter);
        getSalaryOutput.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String dataArray : data) {
            String[] dataSplit = dataArray.split(" ");
            LocalDate localDate = LocalDate.parse(dataSplit[0], formatter);
            if (localDate.isAfter(dateFromFormated)
                    && localDate.isBefore(dateToFormated)
                    || localDate.isEqual(dateFromFormated)
                    || localDate.isEqual(dateToFormated)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(dataSplit[1])) {
                        salary[j] = salary[j]
                                + (Integer.parseInt(dataSplit[2])
                                * Integer.parseInt(dataSplit[3]));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < salary.length; i++) {
            getSalaryOutput.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return getSalaryOutput.toString();
    }

}

