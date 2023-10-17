# Демо программы для цифрового учёта книг в "Мурманской областной детско-юношеской библиотеки им. В.П. Махаевой"

Программа позволяет библиотекарям:
1) возможность регистрировать/удалять читателей
2) возможность регистрировать/удалять книги
3) возможно следить сколько читатель имеен книг на руках
4) узнать у какого читателя находится конкретная книга
5) возможность выдавать/освобождать книги у читателей

**Для запуска необходимо:**

1.tomcat 11

2.intellij idea ultimate (или аналог)

3.БД (прим. PostGreSql)
   
**Инструкция по запуску:**

1. Скачайте этот репозиторий к себе на компьютер
```
gh repo clone git@github.com:Alf51/Digital_accounting_of_books
```
2. запустите веб-приложения используя версию:
**tomcat 11**

![image](https://github.com/Alf51/Digital_accounting_of_books/assets/103650910/80571e4a-8cb8-462c-af3d-91ef9ed6f8a0)


4. Параметры для подключения БД пропишите в файле **resource** -> **database.properties**

5. Для перехода на страницу пользователей используй в браузере  **/people**
  
   Пример:
  
   [![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=http://localhost:8080/people)](https://git.io/typing-svg)
6. Для перехода на страницу с книгами используй в браузере **/book**
   
   Пример:
   
   [![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=http://localhost:8080/book)](https://git.io/typing-svg)
