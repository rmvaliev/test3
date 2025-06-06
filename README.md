# Java Web Server Example

## Описание
Простой web-сервер на Java, который принимает POST-запрос на `/hello` с JSON:
```json
{
  "name": "Имя",
  "fam": "Фамилия"
}
```
и возвращает ответ:
```json
{
  "status": "Ok",
  "description": "hello world"
}
```

## Зависимости
- Java 11+
- [Gson](https://github.com/google/gson)

## Сборка и запуск
1. Скачайте [Gson jar](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar) и положите рядом с Main.java или в папку libs.
2. Скомпилируйте:
   ```bash
   javac -cp gson-2.10.1.jar Main.java
   ```
3. Запустите:
   ```bash
   java -cp .:gson-2.10.1.jar Main
   ```

## Пример запроса
```bash
curl -X POST http://localhost:8080/hello \
  -H "Content-Type: application/json" \
  -d '{"name":"Иван","fam":"Иванов"}'
``` 