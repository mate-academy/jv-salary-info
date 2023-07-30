package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryInfo {
    private Map<String, Integer> nameSalaryMap = new LinkedHashMap<>();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            return null;
        }

        Map<String, Integer> resultInfoMap = new LinkedHashMap<>();

        LocalDate dateStart = findDate(dateFrom);
        LocalDate dateEnd = findDate(dateTo);
        StringBuffer stringBuffer = new StringBuffer();

        for (String name: names) {
            nameSalaryMap.put(name, 0);
        }

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if ((findDate(data[j]).compareTo(dateStart) > 0
                        && findDate(data[j]).compareTo(dateEnd) < 0)
                        || findDate(data[j]).equals(dateStart)
                        || findDate(data[j]).equals(dateEnd)) {
                    if (data[j].contains(names[i])) {
                        String dataEmployee = data[j].substring(data[j].indexOf(names[i]));
                        String[] hourSalary = dataEmployee.split(" ");
                        int hour = Integer.parseInt(hourSalary[1]);
                        int salaryValue = Integer.parseInt(hourSalary[2]);
                        int resultSalary = hour * salaryValue;
                        Integer salary = nameSalaryMap.get(names[i]);
                        resultSalary += salary;
                        resultInfoMap.put(names[i], resultSalary);
                        nameSalaryMap.put(names[i], resultSalary);
                    }
                }
            }
        }

        stringBuffer.append("Report for period " + dateFrom + " - " + dateTo);

        if (resultInfoMap.isEmpty()) {
            for (int i = 0; i < names.length; i++) {
                stringBuffer.append(System.lineSeparator())
                        .append(names[i]).append(" - ").append(0);
            }
            return stringBuffer.toString();
        }

        Iterator iterator = resultInfoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String name = (String) entry.getKey();
            int salary = (int) entry.getValue();

            stringBuffer.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return stringBuffer.toString();
    }

    private LocalDate findDate(String string) {
        String regex = "\\b(\\d{2})\\.(\\d{2})\\.(\\d{4})\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            String day = matcher.group(1);
            String month = matcher.group(2);
            String year = matcher.group(3);

            String dateString = day + "." + month + "." + year;
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return date;
        }
        return null;
    }
}
