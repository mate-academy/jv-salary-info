package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        String onlyDateFrom = dateFrom.split("\\s")[3].substring(1, 11);
        String onlyDateTo = dateTo.split("\\s")[4].substring(1, 11);
        builder.append("Report for period #").append(onlyDateFrom).append("# - #").append(onlyDateTo).append("#");
        for(int i = 0; i < names.length; i++ ){
            int sumSalary = 0;
            for(int j = 0; j < data.length; j++){
                String[]elements = data[j].split("\\s");
                if(names[i].equals(elements[1]) && dateIsInRange(onlyDateFrom, onlyDateTo, elements[0])){
                    sumSalary += Integer.parseInt(elements[2])
                            * Integer.parseInt(elements[3]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(sumSalary);
        }

        return builder.toString();
    }
    private boolean dateIsInRange(String onlyDateFrom, String onlyDateTo, String workDay) {
        LocalDate from = getDate(onlyDateFrom);
        LocalDate to = getDate(onlyDateTo);
        LocalDate day = getDate(workDay);
        return (from.equals(day) && to.equals(day)) || (day.isBefore(to) && day.isAfter(from));

    }
    private LocalDate getDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT));
    }
}
