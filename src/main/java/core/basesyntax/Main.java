package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String[] data = new String[]{
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"
        };
        String[] names = new String[]{
                "John",
                "Andrew",
                "Kate"
        };
        String dateFrom = "date from = `01.04.2019`";
        String dateTo = "date to = `30.04.2019`";
        SalaryInfo salaryInfo = new SalaryInfo();
        String result = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(result);
    }
}
