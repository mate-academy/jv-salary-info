package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";
        String[] data = {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100",
                "01.04.2019 Dmitry 9 100",
                "30.04.2019 Dmitry 9 100",
        };
        String[] names = {"John", "Andrew", "Kate"};

        SalaryInfo salary = new SalaryInfo();
        System.out.println(salary.getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
