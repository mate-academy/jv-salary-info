package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PATTERN = "dd.MM.yyyy";
    private static final String SEPARATOR = " ";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
    private static final int DAY_INDEX_TO_STRING = 0;
    private static final int HOURS_INDEX_TO_STRING = 2;
    private static final int PAY_INDEX_TO_STRING = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startWork = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate finishWork = LocalDate.parse(dateTo, dateTimeFormatter);
        String[] dataOnWorkersWages = new String[names.length];
        int[] wages = new int[names.length];
        for (int a = 0; a < names.length; a++) {
            String workerName = names[a];
            wages[a] = 0;
            for (int b = 0; b < data.length; b++) {
                String dataString = data[b];
                if (dataString.contains(workerName)) {
                    String[] daysData = dataString.split(SEPARATOR);
                    String stringDayWork = daysData[DAY_INDEX_TO_STRING];
                    LocalDate dayWork = LocalDate.parse(stringDayWork, dateTimeFormatter);
                    int daysAfterStartWork = dayWork.compareTo(startWork);
                    int daysBeforeFinishWork = dayWork.compareTo(finishWork);
                    if (daysAfterStartWork >= 0 && daysBeforeFinishWork <= 0) {
                        int numberOfHours = Integer.parseInt(daysData[HOURS_INDEX_TO_STRING]);
                        int hourlyPay = Integer.parseInt(daysData[PAY_INDEX_TO_STRING]);
                        wages[a] = wages[a] + numberOfHours * hourlyPay;
                    }
                }
                int oneWorkersWages = wages[a];
                StringBuilder outputStringBuilder = new StringBuilder();
                outputStringBuilder.append(workerName).append(" - ").append(oneWorkersWages);
                dataOnWorkersWages[a] = outputStringBuilder.toString();
            }
        }
        StringBuilder dataOnAllWorkers = new StringBuilder();
        dataOnAllWorkers.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int f = 0; f < dataOnWorkersWages.length; f++) {
            dataOnAllWorkers.append(System.lineSeparator()).append(dataOnWorkersWages[f]);
        }
        return dataOnAllWorkers.toString();
    }
}
