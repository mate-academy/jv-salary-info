package core.basesyntax;

public class ApplicationMain {
    public static void main(String[] args) {
        String [] names = {"John", "Andrew", "Kate"};
        String [] data = {
                "25.04.2019 John 4 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 9 100",
                "26.04.2019 Andrew 3 150",
                "26.04.2019 Kate 5 100",
                "27.04.2019 John 7 100",
                "27.04.2019 Kate 1 200",
                "29.04.2019 Kate 2 100"
        };
        String  dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";

        SalaryInfo info =  new SalaryInfo();
        System.out.println(info.getSalaryInfo(names, data, dateFrom, dateTo));

    }
}
