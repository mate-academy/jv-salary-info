package core.basesyntax;

public class SalaryInfoMain {
    public static void main(String[] args) {
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"
        };

        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";

        SalaryInfo salaryInfo = new SalaryInfo();
        String report = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);

        System.out.println(report);
    }
}

