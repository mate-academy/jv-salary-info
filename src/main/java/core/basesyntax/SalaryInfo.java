package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DATA_SPLITTER = " ";
    private static final int ITEM_DATE_INDEX = 0;
    private static final int ITEM_EMPLOYER_NAME_INDEX = 1;
    private static final int ITEM_WORKED_HOURS_INDEX = 2;
    private static final int ITEM_SALARY_FOR_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String nameItem : names) {
            int moneyEarned = 0;
            for (String dataItem : data) {
                String[] dataItemSplited = dataItem.split(DATA_SPLITTER);
                LocalDate itemDate = LocalDate.parse(dataItemSplited[ITEM_DATE_INDEX],
                        DATE_FORMATTER);
                if (itemDate.isAfter(localDateFrom.minusDays(1))
                        && itemDate.isBefore(localDateTo.plusDays(1))) {
                    String itemEmployerName =
                            dataItemSplited[ITEM_EMPLOYER_NAME_INDEX];
                    if (itemEmployerName.equals(nameItem)) {
                        int itemWorkedHours = Integer.parseInt(dataItemSplited[
                                ITEM_WORKED_HOURS_INDEX]);
                        int itemSalaryForHour = Integer.parseInt(dataItemSplited[
                                ITEM_SALARY_FOR_HOUR_INDEX]);
                        moneyEarned += (itemWorkedHours * itemSalaryForHour);
                    }
                }
            }
            salaryInfo.append("\n").append(nameItem).append(" - ").append(moneyEarned);
        }
        return salaryInfo.toString();
    }
}
