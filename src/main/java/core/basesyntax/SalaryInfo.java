package core.basesyntax;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static final int DAY = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;
    private static final int WORK_DAY = 0;
    private static final int EMP_NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = parseToLocalDate(dateFrom);
        LocalDate to = parseToLocalDate(dateTo);
        HashMap<String, Integer> workersSalary = getNamesOnHash(names);

        for (int i = 0; i < data.length; i++) {
            String[] splitData = data[i].split(" ");
            LocalDate dateOfWork = parseToLocalDate(splitData[WORK_DAY]);
            if (dateOfWork.compareTo(from) >= 0 && dateOfWork.compareTo(to) <= 0) {
                workersSalary.merge(splitData[EMP_NAME],
                        Integer.parseInt(splitData[WORK_HOURS])
                                * Integer.parseInt(splitData[PER_HOUR]),
                        Integer::sum);
            }
        }
        return getReport(workersSalary, dateFrom, dateTo);
    }

    private LocalDate parseToLocalDate(String date) {
        String[] splitDate = date.split("\\.");
        LocalDate localDate =
                LocalDate.of(Integer.parseInt(splitDate[YEAR]),
                        Integer.parseInt(splitDate[MONTH]),
                        Integer.parseInt(splitDate[DAY]));
        return localDate;
    }

    private HashMap<String, Integer> getNamesOnHash(String[] names) {
        HashMap<String, Integer> hashMap = new LinkedHashMap<>(16, 0.75f, false);
        for (int i = 0; i < names.length; i++) {
            hashMap.put(names[i], 0);
        }
        return hashMap;
    }

    private String getReport(HashMap<String, Integer> workers, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo);
        for (Map.Entry<String, Integer> entry : workers.entrySet()) {
            salaryInfo.append(System.lineSeparator())
                    .append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue());
        }
        return salaryInfo.toString();
    }
}
