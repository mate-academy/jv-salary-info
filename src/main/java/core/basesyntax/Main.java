package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String [] employeeRecords = new String[] {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"
        };
        String [] employeeNames = new String[] {
                "John",
                "Andrew",
                "Kate"
        };
        String dateFrom = "01.04.2024";
        String dateTo = "30.04.2024";

        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(employeeNames, employeeRecords, dateFrom, dateTo));
    }
}
