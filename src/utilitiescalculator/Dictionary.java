package utilitiescalculator;

import java.util.*;

public enum Dictionary {
    INSTANCE;

    public enum Language {
        UKRAINIAN, RUSSIAN
    }

    private static Language language;

    public enum Keyword {
        TITLE_MAIN, TITLE_ELEC, TITLE_GAS, TITLE_PERSONAL, TITLE_PRINT,

        PN_DATE, PN_ELEC, PN_GAS, PN_PAYMENT, PN_TOTAL, PN_ELEC_BOUNDARY, PN_ELEC_PRICE,
        PN_ELEC_PRIVILEGE, PN_ELEC_MAX_VALUE, PN_GAS_PRICE, PN_GAS_MAX_VALUE, PN_NAMES, PN_ADDRESS,

        MTH_JAN, MTH_FEB, MTH_MAR, MTH_APR, MTH_MAY, MTH_JUN, MTH_JUL, MTH_AUG, MTH_SEP, MTH_OCT, MTH_NOV, MTH_DEC,

        BT_TARIFF, BT_CALCULATE, BT_PERSONAL_DATA, BT_VIEW_AND_PRINT, BT_SAVE, BT_CANCEL,
        BT_FONT, BT_LANGUAGE, BT_LANG_DESCRIPTION, BT_PRINT,

        PAY_ELEC, PAY_RENT, PAY_HEATING, PAY_HOT_WATER, PAY_COLD_WATER, PAY_SEVERAGE, PAY_GAS, PAY_GARBAGE,
        PAY_INTERCOM, PAY_TV,

        LB_MONTH, LB_YEAR, LB_BEGIN, LB_END, LB_DIFF, LB_TOTAL, LB_BOUNDARY, LB_BELOW_BOUNDARY, LB_ABOVE_BOUNDARY,
        LB_PRIVILEGE_VALUE, LB_PRICE, LB_PERSONAL_ACCOUNT, LB_SURNAME, LB_FIRST_NAME, LB_PATRONYMIC, LB_STREET,
        LB_BUILDING, LB_APARTMENT, 

        LB_KWH, LB_MCUBIC, LB_HRN, LB_CENTS, LB_PERCENT, LB_HRN_PER_MCUBIC,

        LINE_00, LINE_01, LINE_02, LINE_03, LINE_04, LINE_05, LINE_06, LINE_07, LINE_08, LINE_09, LINE_10, LINE_11,
        LINE_12, LINE_13, LINE_14, LINE_15, LINE_16, LINE_17, LINE_18, LINE_19, LINE_20
    }

    private static final Map<Keyword, String[]> DICTIONARY = new EnumMap<Keyword, String[]>(Keyword.class) {{
        // frames
        put(Keyword.TITLE_MAIN, new String[]{"Сплата комунальних послуг", "Оплата коммунальных услуг"});
        put(Keyword.TITLE_ELEC, new String[]{"Тариф на електроенергію", "Тариф на электроенергию"});
        put(Keyword.TITLE_GAS, new String[]{"Тариф на газ", "Тариф на газ"});
        put(Keyword.TITLE_PERSONAL, new String[]{"Персональні дані платника", "Персональные данные плательщика"});
        put(Keyword.TITLE_PRINT, new String[]{"Перегляд і роздруківка", "Просмотр и печать"});
        // titled borders
        put(Keyword.PN_DATE, new String[]{"Дата", "Дата"});
        put(Keyword.PN_ELEC, new String[]{"Показання електричного лічильника", "Показания электрического счётчика"});
        put(Keyword.PN_GAS, new String[]{"Показання газового лічильника", "Показания газового счётчика"});
        put(Keyword.PN_PAYMENT, new String[]{"Платежі", "Платежи"});
        put(Keyword.PN_TOTAL, new String[]{"Всьго", "Всего"});
        put(Keyword.PN_ELEC_BOUNDARY, new String[]{"Граничний обсяг електроенергії", "Граничный объём электроенергии"});
        put(Keyword.PN_ELEC_PRICE, new String[]{"Ціна на електроенергію", "Цена на электроенергию"});
        put(Keyword.PN_ELEC_PRIVILEGE, new String[]{"Пільга", "Льгота"});
        put(Keyword.PN_ELEC_MAX_VALUE, new String[]{"Максимальне значення електролічильника", "Максимальное значение электросчётчика"});
        put(Keyword.PN_GAS_PRICE, new String[]{"Ціна на газ", "Цена на газ"});
        put(Keyword.PN_GAS_MAX_VALUE, new String[]{"Максимальне значення газового лічильника", "Максимальное значение электрического счётчика"});
        put(Keyword.PN_NAMES, new String[]{"Прізвище, ім’я та по батькові", "Фамилия, имя и отчество"});
        put(Keyword.PN_ADDRESS, new String[]{"Адреса", "Адрес"});
        // combo boxes
        put(Keyword.MTH_JAN, new String[]{"01 - Січень", "01 - Январь"});
        put(Keyword.MTH_FEB, new String[]{"02 - Лютий", "02 - Ферваль"});
        put(Keyword.MTH_MAR, new String[]{"03 - Березень", "03 - Март"});
        put(Keyword.MTH_APR, new String[]{"04 - Квітень", "04 - Апрель"});
        put(Keyword.MTH_MAY, new String[]{"05 - Травень", "05 - Май"});
        put(Keyword.MTH_JUN, new String[]{"06 - Червень", "06 - Июнь"});
        put(Keyword.MTH_JUL, new String[]{"07 - Липень", "07 - Июль"});
        put(Keyword.MTH_AUG, new String[]{"08 - Серпень", "08 - Август"});
        put(Keyword.MTH_SEP, new String[]{"09 - Вересень", "09 - Сентябрь"});
        put(Keyword.MTH_OCT, new String[]{"10 - Жовтень", "10 - Октябрь"});
        put(Keyword.MTH_NOV, new String[]{"11 - Листопад", "11 - Ноябрь"});
        put(Keyword.MTH_DEC, new String[]{"12 - Грудень", "12 - Декабрь"});
        // buttons
        put(Keyword.BT_TARIFF, new String[]{"Тариф...", "Тариф..."});
        put(Keyword.BT_CALCULATE, new String[]{"Розрахувати", "Рассчитать"});
        put(Keyword.BT_PERSONAL_DATA, new String[]{"Персональні дані...", "Персональные данные..."});
        put(Keyword.BT_VIEW_AND_PRINT, new String[]{"Роздрукувати...", "Распечатать..."});
        put(Keyword.BT_SAVE, new String[]{"Зберегти", "Сохранить"});
        put(Keyword.BT_CANCEL, new String[]{"Відмінити", "Отменить"});
        put(Keyword.BT_FONT, new String[]{"Шрифт", "Шрифт"});
        put(Keyword.BT_LANGUAGE, new String[]{"Мова", "Язык"});
        put(Keyword.BT_LANG_DESCRIPTION, new String[]{"Укр", "Рус"});
        put(Keyword.BT_PRINT, new String[]{"Роздрукувати", "Распечатать"});
        // check boxes
        put(Keyword.PAY_ELEC, new String[]{"01 Електроенергія:", "01 Электроэнергия:"});
        put(Keyword.PAY_RENT, new String[]{"02 Квартплата:", "02 Квартплата:"});
        put(Keyword.PAY_HEATING, new String[]{"03 Опалення:", "03 Отопление:"});
        put(Keyword.PAY_HOT_WATER, new String[]{"04 Гаряча вода:", "04 Горячая вода:"});
        put(Keyword.PAY_COLD_WATER, new String[]{"05 Холодна вода:", "05 Холодная вода:"});
        put(Keyword.PAY_SEVERAGE, new String[]{"06 Каналізація:", "06 Канализация:"});
        put(Keyword.PAY_GAS, new String[]{"07,08 Газ природній:", "07,08 Газ природный:"});
        put(Keyword.PAY_GARBAGE, new String[]{"33 Вивіз сміття:", "33 Вывоз мусора:"});
        put(Keyword.PAY_INTERCOM, new String[]{"35 Домофон:", "35 Домофон:"});
        put(Keyword.PAY_TV, new String[]{"49 Воля Т.П.:", "49 Воля Т.П.:"});
        // labels (before)
        put(Keyword.LB_MONTH, new String[]{"Місяць:", "Месяц:"});
        put(Keyword.LB_YEAR, new String[]{"Рік:", "Год:"});
        put(Keyword.LB_BEGIN, new String[]{"Початкові:", "Начальные:"});
        put(Keyword.LB_END, new String[]{"Кінцеві:", "Конечные:"});
        put(Keyword.LB_DIFF, new String[]{"Різниця:", "Разница:"});
        put(Keyword.LB_TOTAL, new String[]{"Загалом:", "Итого:"});
        put(Keyword.LB_BOUNDARY, new String[]{"Граничний обсяг електроенергії (ГОЕ):", "Граничный объём электроэнергии (ГОЭ):"});
        put(Keyword.LB_BELOW_BOUNDARY, new String[]{"За обсяг, спожитий до ГОЕ:", "За объём, потреблённый до ГОЭ:"});
        put(Keyword.LB_ABOVE_BOUNDARY, new String[]{"За обсяг, спожитий понад ГОЕ:", "За объём, потреблённый сверх ГОЭ:"});
        put(Keyword.LB_PRIVILEGE_VALUE, new String[]{"Розмір пільги:", "Размер льготы:"});
        put(Keyword.LB_PRICE, new String[]{"Ціна:", "Цена:"});
        put(Keyword.LB_PERSONAL_ACCOUNT, new String[]{"Особистий рахунок:", "Персональный счёт:"});
        put(Keyword.LB_SURNAME, new String[]{"Прізвище:", "Фамилия:"});
        put(Keyword.LB_FIRST_NAME, new String[]{"Ім’я:", "Имя:"});
        put(Keyword.LB_PATRONYMIC, new String[]{"По батькові:", "Отчество:"});
        put(Keyword.LB_STREET, new String[]{"Вулиця:", "Улица:"});
        put(Keyword.LB_BUILDING, new String[]{"Будинок:", "Дом:"});
        put(Keyword.LB_APARTMENT, new String[]{"Квартира:", "Квартира:"});
        // labels (after)
        put(Keyword.LB_KWH, new String[]{"кВт·год", "кВт·час"});
        put(Keyword.LB_MCUBIC, new String[]{"м куб", "м куб"});
        put(Keyword.LB_HRN, new String[]{"грн", "грн"});
        put(Keyword.LB_CENTS, new String[]{"копійок", "копеек"});
        put(Keyword.LB_PERCENT, new String[]{"%", "%"});
        put(Keyword.LB_HRN_PER_MCUBIC, new String[]{"грн за м куб", "грн за м куб"});
        // print lines
        put(Keyword.LINE_00, new String[]{"СПЛАТА ЗА КОМУНАЛЬНІ ПОСЛУГИ", null});
        put(Keyword.LINE_01, new String[]{"ВАТ «Мегабанк» рахунок одержувача 290231 МФО 351629 ЄДРПОУ 09804119", null});
        put(Keyword.LINE_02, new String[]{"Особистий рахунок:", null});//+
        put(Keyword.LINE_03, new String[]{"Прізвище, ім’я, по батькові:", null});
        put(Keyword.LINE_04, new String[]{"Адреса:", null});
        put(Keyword.LINE_05, new String[]{"вул.", null});
        put(Keyword.LINE_06, new String[]{"буд.", null});
        put(Keyword.LINE_07, new String[]{"кв.", null});
        put(Keyword.LINE_08, new String[]{"Пільга, %", null});
        put(Keyword.LINE_09, new String[]{"Вид платежу", null});
        put(Keyword.LINE_10, new String[]{"Міс.", null});
        put(Keyword.LINE_11, new String[]{"Рік", null});
        put(Keyword.LINE_12, new String[]{"Сума", null});
        put(Keyword.LINE_13, new String[]{"Показання лічильників", null});
        put(Keyword.LINE_14, new String[]{"Кінцеві", null});
        put(Keyword.LINE_15, new String[]{"Початкові", null});
        put(Keyword.LINE_16, new String[]{"Різниця", null});
        put(Keyword.LINE_17, new String[]{"Тариф", null});
        put(Keyword.LINE_18, new String[]{"Інші", null});
        put(Keyword.LINE_19, new String[]{"Усього:", null});
        put(Keyword.LINE_20, new String[]{"Підпис платника:", null});
    }};

    public void setLanguage(Language language) {
        Dictionary.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public String getWord(Keyword key) {
        return DICTIONARY.get(key)[language.ordinal()] != null ? 
                DICTIONARY.get(key)[language.ordinal()] :           // use current language
                DICTIONARY.get(key)[Language.UKRAINIAN.ordinal()];  // use ukrainian
    }

    public String getText() {
        return INSTANCE.getWord(Keyword.BT_LANGUAGE) + "(" + INSTANCE.getWord(Keyword.BT_LANG_DESCRIPTION) + ")";
    }

    public Language getNextLanguage() {
        Language[] languages = Language.values();
        int next = (language.ordinal() + 1) % languages.length;
        language = languages[next];
        return language;
    }

    public static void main(String[] args) {
        Dictionary dict = Dictionary.INSTANCE;
        
        EnumSet.allOf(Dictionary.Keyword.class).forEach((key) -> {
            dict.setLanguage(Dictionary.Language.UKRAINIAN);
            System.out.format("%-26s -> %-68s -> ", key, dict.getWord(key));
            dict.setLanguage(Dictionary.Language.RUSSIAN);
            System.out.format("%s\n", dict.getWord(key));
        });
    }
}
