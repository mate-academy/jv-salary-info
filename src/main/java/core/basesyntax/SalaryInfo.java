package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<CustomerSalary> customerSalaryList = new ArrayList<>();

        for (String record : data) {
            String[] recordArr = record.split(" ");
            String recordDate = recordArr[0];
            String name = recordArr[1];
            String hours = recordArr[2];
            String rate = recordArr[3];

            LocalDate currentDate = LocalDate.parse(recordDate, formatter);
            LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
            LocalDate toDate = LocalDate.parse(dateTo, formatter);
            if ((currentDate.isAfter(fromDate) || currentDate.equals(fromDate))
                    && (currentDate.isBefore(toDate) || currentDate.equals(toDate))) {
                customerSalaryList.add(new CustomerSalary(currentDate, name,
                        Integer.valueOf(hours), Integer.valueOf(rate)));
            }
        }
        String result = "Report for period " + dateFrom + " - " + dateTo;
        for (String name : names) {
            Integer salary = 0;
            for (CustomerSalary reportItem : customerSalaryList) {
                if (name.equals(reportItem.getName())) {
                    salary += reportItem.getHours() * reportItem.getRate();
                }
            }
            result += "\n" + name + " - " + salary;
        }

        return result;
    }

    public class CustomerSalary {
        private LocalDate date;
        private String name;
        private Integer hours;
        private Integer rate;

        public CustomerSalary(LocalDate date, String name, Integer hours, Integer rate) {
            this.date = date;
            this.name = name;
            this.hours = hours;
            this.rate = rate;
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

        public Integer getRate() {
            return rate;
        }
    }
}
