package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<CustomerSalary> castomerSalaryList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (String record : data) {
            String[] recordArr = record.split(" ");
            LocalDate currentDate = LocalDate.parse(recordArr[0], formatter);
            LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
            LocalDate toDate = LocalDate.parse(dateTo, formatter);
            if ((currentDate.isAfter(fromDate) || currentDate.equals(fromDate) )
                    && (currentDate.isBefore(toDate) || currentDate.equals(toDate))) {
                castomerSalaryList.add(new CustomerSalary(currentDate, recordArr[1],
                        Integer.valueOf(recordArr[2]), Integer.valueOf(recordArr[3])));
            }
        }
        String result = "Report for period " + dateFrom + " - " + dateTo;
        for (String name: names) {
            Integer salary = 0;
            for (CustomerSalary reportItem : castomerSalaryList) {
                if (name.equals(reportItem.getName())) {
                  salary += reportItem.getHours() * reportItem.getTarif();
                }
            }
            result += "\n" + name + " - " + salary;
        }

        return result;
    }

        class CustomerSalary {
            private LocalDate date;
            private String name;
            private Integer hours;
            private Integer tarif;

            public CustomerSalary(LocalDate date, String name, Integer hours, Integer tarif) {
                this.date = date;
                this.name = name;
                this.hours = hours;
                this.tarif = tarif;
            }

            public LocalDate getDate() {
                return date;
            }

            public String getName() {
                return name;
            }

            public Integer getHours() {
                return hours;
            }

            public Integer getTarif() {
                return tarif;
            }
        }
}
