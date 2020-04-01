package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate dateWorkFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateWorkTo = LocalDate.parse(dateTo, FORMATTER);
        if (dateWorkTo.isBefore(dateWorkFrom)) {
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
                        && (LocalDate.parse(personInformation[0], FORMATTER)
                        .isBefore(dateWorkTo)
                        || LocalDate.parse(personInformation[0], FORMATTER)
                        .isEqual(dateWorkTo))
                        && (LocalDate.parse(personInformation[0], FORMATTER)
                        .isAfter(dateWorkFrom)
                        || LocalDate.parse(personInformation[0], FORMATTER)
                        .isEqual(dateWorkFrom))) {
                    payment += Integer.parseInt(personInformation[2])
                            * Integer.parseInt(personInformation[3]);
                }
            }
            report.append(strName).append(" - ").append(payment).append("\n");
        }
        return report.toString().trim();
    }
}
