package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
                throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        long from = simpleDateFormat.parse(dateFrom).getTime();
        long to = simpleDateFormat.parse(dateTo).getTime();
        if (from > to) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        String result = "";
        for (String name : names) {
            int salary = 0;

            for (String d : data) {
                String[] dd = d.split(" ");
                if (dd[1].equals(name) && simpleDateFormat.parse(dd[0]).getTime() >= from
                        && simpleDateFormat.parse(dd[0]).getTime() <= to) {
                    salary += (Integer.valueOf(dd[2]) * Integer.valueOf(dd[3]));
                }
            }
            result += name + " - " + salary + "\n";
        }
        return "Отчёт за период " + dateFrom + " - " + dateTo + "\n" + result;
    }
}
