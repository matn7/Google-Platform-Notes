# Jakiego buga znalazłem ostatnio?

- Błąd w UI narzędzie Sabrowego używanego do instalacji. To było UI nie wyswietlał ticketów jak były w tej samej minucie otwarte.
- Błąd w tworzeniu odpowiedzi w formacjie JSON, po podbiciu wersji jednej z bibliotek. JSON był konstruowany na podstawie 
LinkedHashMap a nowa biblioteka wymuszała HashMap (kolejność została zmianiona).