# Перечень автотестов
## Позитивные
- Покупка APPROVED картой "1111 2222 3333 4444", с валидными данными, сгенерированными с помощью Faker
  *Ожидаемый результат: получено уведомление "Операция одобрена банком" *
- Покупка DECLINED картой "5555 6666 7777 8888" с валидными данными, сгенерированными с помощью Faker
  *Ожидаемый результат:  получено уведомление "Карта заблокирована" * 
- Покупка картой с любыми другими числами с с валидными данными, сгенерированными с помощью Faker
  *Ожидаемый результат: получено уведомление "Банк отказал в проведении операции"*
## Негативные
- Покупка APPROVED картой "1111 2222 3333 4444" с невалидными данными, сгенерированными с помощью Faker
  *Ожидаемый результат: приложение выдает ошибку*
-  Покупка картой с любыми другими числами с невалидными данными, сгенерированными с помощью Faker
  *Ожидаемый результат: приложение выдает ошибку*
# Перечень используемых инструментов
- IntelliJ IDEA. Интегрированная среда разработки ПО. Используется для написания кода, в моем случае на языке Java.
- JUnit5. Среда для тестирования приложеный, написанных на Java.
- Git и Github. Система контроля версий, для хранения кодов автотестов и настроек окружения.
- Gradle. Cистема для автоматизации сборки приложений.
- Selenide. Позволяет автоматизировать тестирования веб-приложений.
- Docker. Программная платформа, которая позволяет быстро создавать, тестировать и развертывать приложения с помощью контейнеров.
# Возможные риски при автоматизации
- Затраты времени и средств, потраченных на автоматизацию тестирования, слишком усложняют и растягивают процесс.
# Интервальная оценка с учётом рисков в часах
30 часов
# План сдачи работ
1. 24.10.2023 - написание плана
2. 25.10.2023 - 30.10.2023 - написание автотестов, проведение тестирования
3. 31.10.2023 - сдача курсовой работы


