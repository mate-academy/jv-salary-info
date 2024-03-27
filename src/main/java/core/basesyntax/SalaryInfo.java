package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) throws SalaryInfoException {
        if (names.length == 0 || data.length == 0 || dateFrom == null || dateTo == null) {
            throw new SalaryInfoException("incorrect data entry");
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFrom1 = LocalDate.parse(dateFrom.trim(), df);
        LocalDate dateTo1 = LocalDate.parse(dateTo.trim(), df);
        String info;
        StringBuilder stringBuilder = new StringBuilder();
        int[]sums = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String dat : data) {
                String[] dateLine = dat.split(" ");
                LocalDate d1 = LocalDate.parse(dateLine[0].trim(), df);
                if (names[i].equals(dateLine[1]) && (d1.isAfter(dateFrom1.minusDays(1))
                        && d1.isBefore(dateTo1.plusDays(1)))) {
                    sums[i] += Integer.parseInt(dateLine[2]) * Integer.parseInt(dateLine[3]);
                }
            }
            info = names[i] + " - " + sums[i];
            stringBuilder.append(System.lineSeparator()).append(info);
        }
        return "Report for period " + dateFrom + " - " + dateTo + stringBuilder;
    }
}

