package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter LOCAL_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DATA_SPLITTER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null
                || names.length == 0 || data.length == 0 || dateFrom.isEmpty()
                || dateTo.isEmpty()) {
            return "";
        }
        LocalDate localDateFrom = LocalDate.parse(dateFrom, LOCAL_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, LOCAL_FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        String[] dataItemSplited = new String[data[0].length()];
        LocalDate itemDate;
        String itemEmployerName;
        int itemWorkedHours;
        int itemSalaryForHour;
        int moneyEarned;
        for (String nameItem : names) {
            moneyEarned = 0;
            for (String dataItem : data) {
                dataItemSplited = dataItem.split(DATA_SPLITTER);
                itemDate = LocalDate.parse(dataItemSplited[0], LOCAL_FORMATTER);
                if (itemDate.isAfter(localDateFrom.minusDays(1))
                        && itemDate.isBefore(localDateTo.plusDays(1))) {
                    itemEmployerName = dataItemSplited[1];
                    if (itemEmployerName.equals(nameItem)) {
                        itemWorkedHours = Integer.parseInt(dataItemSplited[2]);
                        itemSalaryForHour = Integer.parseInt(dataItemSplited[3]);
                        moneyEarned += (itemWorkedHours * itemSalaryForHour);
                    }
                }
            }
            salaryInfo.append("\r\n").append(nameItem).append(" - ").append(moneyEarned);
        }
        return salaryInfo.toString();
    }
}
