package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
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
     * Отчёт за период 01.04.2019 - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        SimpleDateFormat sdformat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFromFormatted = sdformat.parse(dateFrom);
        Date dateToFormatted = sdformat.parse(dateTo);

        if (dateFromFormatted.compareTo(dateToFormatted) >= 0) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        int[] salaries = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] splitedData = data[i].split(" ");
            Date date = sdformat.parse(splitedData[0]);
            int nameIndex = Arrays.asList(names).indexOf(splitedData[1]);

            if (date.compareTo(dateFromFormatted) >= 0
                    && date.compareTo(dateToFormatted) <= 0
                    && nameIndex >= 0) {
                salaries[nameIndex] +=
                        Integer.parseInt(splitedData[2]) * Integer.parseInt(splitedData[3]);
            }
        }

        String result = "Отчёт за период " + dateFrom + " - " + dateTo;

        for (int i = 0; i < salaries.length; i++) {
            result += "\n" + names[i] + " - " + String.valueOf(salaries[i]);
        }

        return result;
    }
}
