package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int NUMBERS_IN_DATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ");
        result.append(dateFrom);
        result.append(" - ");
        result.append(dateTo);
        result.append("\n");
        LocalDate dateOnWork;
        LocalDate localDateFrom = convert(dateFrom);
        LocalDate localDateTo = convert(dateTo);
        String[] dataInArray;
        for (int i = 0; i < names.length; i++) {
            int moneyEarned = 0;
            for (int j = 0; j < data.length; j++) {
                dataInArray = data[j].split(" ");
                dateOnWork = convert(dataInArray[0]);
                if (dateOnWork.compareTo(localDateFrom) >= 0
                        && dateOnWork.compareTo(localDateTo) <= 0) {
                    if (dataInArray[1].equals(names[i])) {
                        moneyEarned += ((Integer.parseInt(dataInArray[2]))
                                * (Integer.parseInt(dataInArray[3])));
                    }
                }
            }
            result.append(names[i]);
            result.append(" - ");
            result.append(moneyEarned);
            result.append("\n");
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }

    private LocalDate convert(String date) {
        String[] arrayFromDate = date.split("\\.");
        int[] dateInInt = new int[NUMBERS_IN_DATE];
        for (int i = 0; i < NUMBERS_IN_DATE; i++) {
            dateInInt[i] = Integer.parseInt(arrayFromDate[i]);
        }
        return LocalDate.of(dateInInt[2], dateInInt[1], dateInInt[0]);
    }
}
