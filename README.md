## UtilitiesCalculator

Данное приложение призвано упростить ежемесячные хлопоты по подсчётам оплаты комунальных услуг. С его помощью можно создать и распечатать квитанцию, в которой будут все необходимые данные для оплаты в банковской кассе. Дополнительно к этому, приложение сохраняет все данные об оплате, которые в дальнейшем используются в виде статистики, для наглядного отображения того, какие суммы на какие коммунальные услуги были затрачены.


Главное окно приложения имеет вид как на рисунке 1. В этом окне присутствует пять панелей. Панель даты, на которой автоматически устанавливаются текущие месяц и год; панель показаний электросчётчика; панель показаний газового счётчика; панель позиций по которым производится оплата; и панель общей суммы к оплате.

![Главное окно приложения UtilitiesCalculator](readme-pics/main-window.png)

Рисунок 1 - Главное окно приложения UtilitiesCalculator

Использование панелей показания электросчётчика и газового счётчика опционально. Например, при отсутствии газового счётчика, можно отключить соответствующую панель и вводить сумму за газ в панели платежей (см. ниже).
В панелях электросчётчика и газового счётчика есть кнопки, открывающие диалоговые окна, в которых нужно указать соответствующие тарифы и некоторые другие данные. Диалоговые окна тарифа на электроэнергию и тарифа на газ изображены, соответственно, на рисунке 2 и 3.

![Диалоговое окно тарифа на электроэнергию](readme-pics/elec-tariff.png)

Рисунок 2 - Диалоговое окно тарифа на электроэнергию

Как видно из рисунка 2, в данных о тарифе на электроэнергию также необходимо указать граничный объём электроэнергии в киловаттах в час, до достижения которого сумма вычисляется по одной цене, а после - по другой. Указываются цены на электроэнергию. Размер льготы, если таковая имеется. А также необходимо указать максимальное значение вашего электросчётчика.

![Диалоговое окно тарифа на газ](readme-pics/gas-tariff.png)

Рисунок 3 - Диалоговое окно тарифа на газ

На рисунке 3 видно, что в данных о тарифе на газ указывается цена в гривнах за кубометр газа, указывается максимальное значение газового счётчика. Также, при желании, можно указать граничное значение потребления газа в месяц, в метрах кубических (данное значение используется в части приложения отвечающем за статистику, см. ниже).

В панели платежей выбираются желаемые позиции, которые будут учитываться при составлении квитанции. Если вы используете панели электроэнергии и газа, то будете получать соответствующие суммы автоматически, и в позициях платежей, при желании, их можно корректировать (округлять).

Диалоговое окно персональных данных имеет вид как на рисунке 4, в нём необходимо указать ваши персональные данные. А именно, ваш персональный счёт, который указан в квитанции присылаемой вам по почте, ваша фамилия, имя, отчество и адрес.

![Диалоговое окно персональных данных](readme-pics/personal-data.png)

Рисунок 4 - Диалоговое окно персональных данных

При нажатии кнопки печати в главном окне приложения, вначале откроется диалоговое окно предварительного просмотра, в котором можно проверить правильность введённых данных. Оно имеет вид как на рисунке 5.

![Диалоговое окно предварительного просмотра](readme-pics/preview-page.png)

Рисунок 5 - Диалоговое окно предварительного просмотра

После печати лист квитанции имеет вид, показанный на рисунке 6.

![Распечатанный лист квитанции](readme-pics/printed-page.png)

Рисунок 6 - Распечатанный лист квитанции

Это было описание основного функционала данного приложения. Дополнительный функционал описан ниже.

К дополнительным функциям относится сбор данных об оплате и демонстрация их в табличном виде и в виде графика. Каждый раз, после успешной печати квитанции, сохраняются следующие данные: часовой маркер - время, когда была произведена печать; данные о месяце и годе платежа; суммы всех позиций платежей; количество использованных киловатт электроэнергии и кубометров газа.
На рисунке 7 показано окно со статистикой в табличном виде. Для лёгкого визуального восприятия информации, в таблице используется подсветка ячеек. В колонке "год-месяц" ячейки строк подсвечиваются в зависимости от периода года. Ячейки колонок с данными о платежах имеют свой общий цвет фона. А в колонках с данными об электроэнергии и газе красным подсвечиваются места, когда была превышена установленная граница (для электроэнергии это 100 кВт/час, а для газа это вами выбранная граница, в соответствующем диалоговом окне).

![Окно статистики в табличном виде](readme-pics/table.png)

Рисунок 7 - Окно статистики в табличном виде (отдельная закладка)

В контекстном меню шапки таблицы, можно выбрать какие колонки показывать, а какие скрыть. Это продемонстрировано ниже на рисунке 8.

![Выбор отображаемых колонок](readme-pics/table-context-menu.png)

Рисунок 8 - Выбор отображаемых колонок

В другой закладке окна статистики можно просмотреть данные о платежах в виде диаграммы. Здесь демонстрируются данные не более чем за 13 предыдущих месяцев. Так сделано для того чтобы можно было сравнить текущий месяц с аналогичным месяцем прошлого года. Данные о статистике берутся из файла, автоматически созданным приложением. Данные в этот файл добавляются каждый раз при успешной печати квитанции. При желании можно вручную добавить данные за прошлые периоды, когда вы ещё не пользовались приложением (см. ниже).
Окно с данными платежей в виде диаграммы имеет вид, показанный на рисунке 9. Слева на оси Y указан уровень суммы, а внизу по оси X – месяц и год оплачиваемого периода.

![Окно статистики в виде диаграммы](readme-pics/chart.png)

Рисунок 9 - Окно статистики в виде диаграммы (отдельная закладка)

В данном окне есть возможность просмотра конкретной позиции в режиме выделения из общей картины. Для этого нужно нажать на соответствующую кнопку, например, для позиции "газ" график будет иметь вид как на рисунке 10.

![Окно статистики в виде диаграммы с выделением оплаты по газу](readme-pics/chart-gas.png)

Рисунок 10 - Окно статистики в виде диаграммы с выделением оплаты по газу

Чтобы добавить данные в файл сбора статистики, необходимо придерживаться определённого формата. Сам файл с данными имеет имя "`statistics.ucs`" и находится в папке "`.ucfiles`", которая, в свою очередь, находится в папке пользователя (`%USERPROFILE%` / `$USER`). Формат данных в файле схож с форматом JSON и имеет следующий вид:
```
{"timestamp":1531404343341,"month":07,"year":2018,"electricity":{"kwh":100,"price":90.0},"rent":0.0,"heating":735.0,"hotWater":0.0,"coldWater":50.0,"sewerage":50.0,"gas":{"meterCubic":30,"price":208.74},"garbage":0.0,"intercom":0.0,"tv":0.0}
```
Каждая такая строка соответствует данным одного платежа со всей информацией из него.
