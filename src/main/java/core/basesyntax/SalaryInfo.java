package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATOFDATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) throws Exception {
        checkInputDates(dateFrom, dateTo);

        int[] salaries = new int[names.length];

        for (String singleData : data) {
            DataLineInfo dataLineInfo = new DataLineInfo(singleData);
            if (isNeedDate(dateFrom, dateTo, dataLineInfo.getDate())) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(dataLineInfo.getName())) {
                        salaries[i] += dataLineInfo.getHours() * dataLineInfo.getPayment();
                    }
                }
            }
        }

        return makeReport(dateFrom, dateTo, names, salaries);
    }

    private String makeReport(String dateFrom, String dateTo, String[] names, int[] salaries) {
        StringBuilder result = new StringBuilder();

        result.append("Отчёт за период ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(salaries[i]).append("\n");
        }

        result.append("\n");

        return result.toString().replace("\n\n", "");
    }

    private void checkInputDates(String dateFrom, String dateTo) throws Exception {
        LocalDate from = LocalDate.parse(dateFrom, FORMATOFDATE);
        LocalDate to = LocalDate.parse(dateTo, FORMATOFDATE);

        if (from.compareTo(to) > 0) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
    }

    private boolean isNeedDate(String dateFrom, String dateTo,
                                      String currentDate) throws Exception {
        LocalDate from = LocalDate.parse(dateFrom, FORMATOFDATE);
        LocalDate to = LocalDate.parse(dateTo, FORMATOFDATE);
        LocalDate current = LocalDate.parse(currentDate, FORMATOFDATE);

        if (current.compareTo(from) >= 0
                && current.compareTo(to) <= 0) {
            return true;
        }
        return false;
    }
}
