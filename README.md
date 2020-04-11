<h1> Weather-forecast </h1>
<p> Сервис по  прогнозу погоды </p>
<p> Уровень сложности: средний уровень сложности с задачей со звездочкой. </p>
<h2> Проектирование сервиса </h2>
<p> Для решения задачи был выбран язык программирования Java, использовалась среда разработки Android studio </p>
<p> Пользовательский интерфейс - мобильное приложение</p>
<p> На выходе пользователь получает картинку с погодой, а также температура в градусах, "состояние неба" и совет как нужно одеться</p>
<p> <b> Пример для Санкт-Петербурга: </b> </p>
<img src="img_to_readme/SPB.jpg" width="200" height="400"/>
<p> <b> Пример для Лондона: </b></p>
<img src="img_to_readme/London.jpg" width="200" height="400"/>
<h2> Видеопрезентация </h2>
<p>Видео будет доступно в папке video</p>
<p> Либо по ссылке https://drive.google.com/open?id=1aV6E_nlUmYvkBEAetgoJXmEYCJX219oi </p>
<h2> Процесс работы программы по шагам: </h2>
<p> 1. Пользователь вводит в EditText свой город (в будущем можно будет сделать и по координатам, метод для того, чтобы связаться с API есть, но не до конца разобрался с location в андроиде, а костыли писать не хочется, поэтому это будет доступно в будущем) </p>
<p> 2. Приложение создает запрос на сервер. Кроме города, на сервер отправляются данные о языке пользователя. </p>
<p> 3. Приложение отправляет запрос на сервер, затем получает ответ в форме JSON. </p>
<p> 4. Приложение обрабатывает полученный JSON и заносит данные в класс Weather.</p>
<p> 5. Данные выводятся пользователю на экран. </p>
<h2> Как запустить программу </h2>
<h3> Первый способ, если есть среда разработки Android studio</h3>
<p> 1. Клонировать гит репозиторий к себе на компьютер  </p>
<p> 2. Дождаться, когда Gradle все соберет  </p>
<p> 3. Запустить на девайсе или эмуляторе</p>
<h3> Второй способ</h3>
<p> 1. Клонировать гит репозиторий к себе на компьютер  </p>
<p> 2. Из папки app\build\outputs\apk\debug, скопировать app-debug.apk на телефон и затем его установить  </p>

