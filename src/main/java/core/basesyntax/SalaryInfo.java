package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date1 = LocalDate.parse(dateFrom, formatter);
        LocalDate date2 = LocalDate.parse(dateTo, formatter);
        if (date1.compareTo(date2) >= 0) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder report = new StringBuilder();
        report.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo)
                .append("\n");
        for (String strName : names) {
            int payment = 0;
            for (String strData : data) {
                String[] personInformation = strData.split(" ");
                if (strName.equals(personInformation[1])
                        && LocalDate.parse(personInformation[0], formatter)
                        .compareTo(date1) >= 0
                        && LocalDate.parse(personInformation[0], formatter)
                        .compareTo(date2) <= 0) {
                    payment += Integer.parseInt(personInformation[2])
                            * Integer.parseInt(personInformation[3]);
                }
            }
            report.append(strName).append(" - ").append(payment).append("\n");
        }
        return report.toString().trim();
    }
}
