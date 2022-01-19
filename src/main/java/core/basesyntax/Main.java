package core.basesyntax;

public class Main {
    public static void main(String[]args) {
        SalaryInfo info = new SalaryInfo();
        String dateTo = "22.05.2022";
        String dateFrom = "20.03.2022";
        String[] names = new String[] {"Bob", "Nik", "Din"};
        String[] date = new String[] {"20.04.2022 Nik 5 100", "15.05.2022 Din 1 100",
                "23.05.2022 Nik 10 100", "19.03.2022 Din 5 100",
                "05.04.2022 Bob 9 100"};

        System.out.println(info.getSalaryInfo(names, date, dateFrom, dateTo));
    }
}
