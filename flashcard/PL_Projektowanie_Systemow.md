Projektowanie Systemów

1) Zookeeper i Rejestr Schematów?
Zookeeper: Zarządza klastrem, przechowuje metadane. Rejestr schematów: Przechowuje wersjonowaną historię wszystkich schematów według ID.
Producent: Wysyła schemat do rejestru schematów i otrzymuje ID schematu. Konsument: Pobiera schemat według ID. 
Dane są serializowane/deserializowane za pomocą zarejestrowanego schematu.

2) Dlaczego replikacja na wielu serwerach Kafka jest potrzebna w środowisku produkcyjnym?
Aby zrekompensować awarię któregokolwiek z nich.

3) Testowanie Białoskrzynkowe (white-box)?
Polega na testowaniu wewnętrznej struktury, kodu i logiki aplikacji.
Zastosowanie: Służy do zapewnienia, że kod działa poprawnie i efektywnie, koncentrując się na pokryciu kodu i analizie ścieżek.
Zaleta: Może identyfikować złożone błędy logiczne, luki w bezpieczeństwie i możliwości optymalizacji kodu. 
Może zapewnić wyższy poziom pokrycia i ujawnić ukryte wady.
Wady: Testerzy muszą mieć wiedzę programistyczną.

4) Jakie opcje zwiększą przepustowość producenta Kafka?
Zwiększenie rozmiaru wsadu (batch). Ustawienie typu kompresji na snappy. Zwiększenie linger-ms.

5) Gdzie wyzwalać zdarzenie? Architektura mikroserwisów?
W Usłudze Aplikacji. Warstwa Domeny nie powinna wiedzieć, jak wyzwalać zdarzenie.

6) Kiedy wybrać komunikację synchroniczną a asynchroniczną?
Synchroniczna:
a) Natychmiastowe odpowiedzi są kluczowe.
b) Interakcje typu żądanie-odpowiedź.
c) Wąskie gardło wydajności i zmniejszona tolerancja na błędy.
Asynchroniczna:
a) Odpowiedzi w czasie rzeczywistym nie są konieczne, a chcesz odseparować komponenty dla skalowalności, 
niezawodności i tolerancji na błędy.
b) Przetwarzanie w tle, architektura oparta na zdarzeniach.
c) Wprowadza złożoność w obsłudze ostatecznej spójności i przesyłania wiadomości.

7) Czym jest mikroserwis?
Usługa internetowa. Mała i odpowiedzialna za jedną rzecz (wyszukiwanie, resetowanie hasła, weryfikacja e-mail). 
Skonfigurowana do pracy w chmurze i łatwo skalowalna.

8) Jaka jest miara wydajności?
Opóźnienie (Latency), łagodna degradacja (graceful degradation), przepustowość (Throughput), czas odpowiedzi (Response Time), 
krótki czas opóźnienia (Short tail latency) (wąska dystrybucja opóźnienia), 
instrukcje na cykl (Instructions per cycle).

9) Jaka jest maksymalna liczba konsumentów, którzy mogą pracować na temacie Kafka?
Maksymalna liczba konsumentów, którzy mogą pracować na Kafka, jest równa liczbie partycji. Dodatkowy konsument 
będzie bezczynny i nie wykona żadnej pracy.

10) Co oznacza termin "thrashing" w kontekście informatyki?
Sytuacja, w której system operacyjny spędza więcej czasu na zarządzaniu zasobami niż na umożliwieniu aplikacji 
uruchomienia ich kodu. Zwykle "thrashing" jest związany z wyczerpywaniem pamięci fizycznej z powodu nadmiernego użycia 
pamięci wirtualnej, co prowadzi do dużej liczby błędów stron (page faults). Może to się zdarzyć, jeśli uruchomimy zbyt 
wiele procesów, przydzielimy zbyt wiele wątków lub przydzielimy zbyt dużo pamięci.

11) Jakie czynniki wpływają na techniczne mikroserwisy?
Komunikacja, odporność, nowe mikroserwisy. Operacje: dzienniki, metryki, konfiguracja, wdrożenie.

12) Jakie są niektóre role systemu operacyjnego?
a) Zarządzanie pamięcią.
b) Izolacja między procesami.
c) Planowanie procesów i ich wątków na rdzeniach CPU.
d) Zapewnienie bezpieczeństwa i interfejsu do dostępu do sprzętu.

13) Jakie są argumenty za większą liczbą partycji?
Więcej możliwości dla współbieżności.

14) Definicja usługi internetowej?
System oprogramowania zaprojektowany w celu wsparcia interoperacyjnej interakcji maszyna-maszyna przez sieć. 
3 kluczowe cechy:
a) Zaprojektowana do interakcji maszyna-maszyna (lub aplikacja do aplikacji).
b) Powinna być interoperacyjna, niezależna od platformy.
c) Powinna umożliwiać komunikację przez sieć.

15) Mamy komputer z jednym rdzeniem?
Możemy osiągnąć współbieżność za pomocą wielu wątków. Możemy osiągnąć współbieżność za pomocą wielu procesów.
Równoległość (parallelism) możemy osiągnąć tylko przy wielu rdzeniach, ale dzięki wielozadaniowości (concurrency)
system operacyjny może osiągnąć współbieżność, używając tylko jednego rdzenia za pomocą wielowątkowości.

16) Czym są maszyny wirtualne?
Maszyny wirtualne to symulowane komputery, które działają na tym samym fizycznym sprzęcie. Dla systemu 
operacyjnego i aplikacji maszyny wirtualne wyglądają dokładnie jak fizyczny serwer.

17) Przypadek testowy?
Przypadek testowy to szczegółowy i udokumentowany zestaw instrukcji lub kroków, które tester wykonuje, aby 
przeprowadzić określony scenariusz testowy i ocenić, czy aplikacja działa zgodnie z oczekiwaniami. ID przypadku testowego, 
tytuł/opis przypadku testowego, cel testu, warunki wstępne, kroki testowe, oczekiwane wyniki, dane testowe, środowisko 
testowe, status wykonania, rzeczywisty wynik, załącznik.

18) Czym jest T C P?
Warstwa transportowa w modelu O S I. Niezawodne połączenie, uporządkowane wysyłanie danych. Funkcje, kontrola 
przepływu, kontrola błędów, powtarzanie danych. Niezawodny sposób przesyłania danych. Strumień bajtów, segmenty (nagłówek, dane).

19) Proces projektowania systemu krok po kroku?
Zadawaj pytania, aby uchwycić:
a) Wymagania funkcjonalne.
b) Wymagania niefunkcjonalne.
c) Ograniczenia systemowe.
Zdefiniuj interfejsy API systemu. Stwórz diagram architektury oprogramowania, aby spełnić wymagania funkcjonalne. 
Udoskonal diagram architektury oprogramowania, aby uwzględnić wymagania niefunkcjonalne.

20) Wzorzec architektury rozszerzalności oprogramowania w projektowaniu systemu?
a) Wzorzec Sidecar i Ambasador.
b) Wzorzec adaptera antykorupcyjnego.
c) Wzorzec Backend dla Frontendów.

21) Latencja w projektowaniu systemu?
Czas potrzebny do wysłania pakietów między dwoma punktami w sieci komputerowej. Mierzony w milisekundach. 
Spowodowany czasem propagacji oraz oczekiwaniem na dostępne zasoby. Im niższy, tym lepiej.

22) Wzorzec skalowalności w chmurze w projektowaniu systemu?
a) Równoważenie obciążenia (Load Balancing).
b) Rury i filtry (Pipes and Filters).
c) Rozpraszanie i zbieranie (Scatter Gather).
d) Orkiestrator wykonania dla architektury mikroserwisów (Execution Orchestrator for Microservices Architecture).
e) Choreografia dla architektury mikroserwisów (Choreography for Microservices Architecture).

23) Wzorzec wydajności w chmurze w projektowaniu systemu dla systemów intensywnie wykorzystujących dane?
a) Map Reduce.
b) Wzorzec Saga.
c) Wzorzec Transakcyjnej Skrzynki Wyjściowej (Transactional Outbox Pattern): Niezawodność w architekturze opartej na zdarzeniach.
d) Materialized View: Projektowanie systemów o wysokiej wydajności.
e) CQRS.
f) CQRS + Materialized View Pattern.
g) Wzorzec Źródłowy Zdarzeń (Event Sourcing Pattern).

24) Czym jest szyfrowanie symetryczne?
Pojedynczy klucz do szyfrowania i deszyfrowania.

25) Struktury danych procesów?
Tabela procesów, tabela wątków, tabela plików, IPC (komunikacja międzyprocesowa), I / O (wejście/wyjście).

26) Jakie są różnice między strategicznym D D D a taktycznym D D D?
Strategiczne D D D: Wprowadza granice z modelu domeny. Pojedynczy kontekst ograniczony dla każdej domeny. 
Czym jest domena? Obszar operacyjny twojej aplikacji (przykład: zamawianie jedzenia online).
Kontekst nieograniczony: Centralny wzorzec w D D D. Granica w ramach domeny.
Taktyczne D D D: Wzorce implementacji:
a) Byty (Entities): Kluczowe obiekty domeny z unikalnymi identyfikatorami.
b) Agregaty: Grupa obiektów bytowych, które zawsze muszą być w spójnym stanie.
c) Aggregate Root (AR).
d) Obiekty wartości: Niemutowalne obiekty bez tożsamości. Liczy się tylko wartość. Nadają kontekst wartości.
e) Domain Service.
f) Usługa aplikacji.
g) Domain Event.

27) Różnica między SQL a No SQL?
SQL to relacyjna baza danych oparta na tabelach. Dane są przechowywane w precyzyjnie zdefiniowanych strukturach, 
a relacje między nimi są reprezentowane za pomocą kluczy obcych. Dobrze nadaje się do zapytań dotyczących wielu tabel 
i dużych zbiorów danych, a także do utrzymywania spójności transakcji i bezpieczeństwa. No SQL pozwala na przechowywanie 
danych w różnych formatach, takich jak dokumenty, grafy, para klucz-wartość lub kolumny. Jest bardziej elastyczny i 
skalowalny niż SQL. Dobry do projektów z dużym obciążeniem danych. Odporny na błędy, ponieważ dane są replikowane.

28) Jakie są różne specjalizowane paradygmaty przechowywania?
a) Blob Storage: GCS lub S3.
b) Baza danych czasowych: Specjalny rodzaj bazy danych zoptymalizowanej do przechowywania i analizy danych indeksowanych 
czasowo. Punkty danych, które występują w określonym momencie czasowym. Influx DB, Prometheus i Grafana.
c) Baza danych grafowa: Sieci społecznościowe, np. N e o 4j.
d) Cypher: Język zapytań grafowych, który został pierwotnie opracowany dla bazy danych grafowej N e o 4 j.
e) Baza danych przestrzenna: Przechowywanie danych przestrzennych, takich jak lokalizacje na mapie. Indeksy przestrzenne, 
takie jak drzewa czwórkowe (Quad Trees).

29) Czym jest SOAP?
Format danych: Żądanie SOAP XML. Odpowiedź SOAP XML.
Transport: SOAP przez MQ. SOAP przez HTTP.
Definicja usługi: WSDL definiuje: punkt końcowy, wszystkie operacje, strukturę żądania, strukturę odpowiedzi.

30) Czym jest testowanie dymne (smoke testing)?
Testowanie dymne, znane również jako testowanie weryfikacji budowy, polega na uruchomieniu podzbioru przypadków 
testowych w celu weryfikacji, czy podstawowe funkcjonalności aplikacji są operacyjne po nowej budowie lub wydaniu. Szybka 
informacja zwrotna na temat tego, czy budowa jest wystarczająco stabilna do przeprowadzenia bardziej szczegółowych testów. 
Identyfikuje poważne problemy. Zapobiega marnowaniu czasu na kompleksowe testowanie.

31) Spójność oparta na zestawach w CQRS (Set Based Consistency CQRS)?
Jak sprawdzić, czy rekord już istnieje w tabeli bazy danych? Jak sprawdzić, czy użytkownik już istnieje? 
Rozwiązanie: tabela bazy danych do wyszukiwania.
Interceptor wysyłania wiadomości:
a) Przechwyci polecenie przed jego przetworzeniem przez obsługującego polecenie.
b) Użyje JPA do zapytania tabeli wyszukiwania.
c) Obsługa zdarzeń w celu zapisania productID i tytułu w tabeli wyszukiwania.

32) Odkrywanie usług i równoważenie obciążenia w mikroserwisach?
Odkrywanie usług:
a) Ułatwia komunikację między usługami.
b) Ułatwia stabilność i tolerancję na błędy.
c) Wspiera dynamiczne środowiska.
Równoważenie obciążenia:
a) Zwiększa niezawodność.
b) Poprawia skalowalność.
c) Tolerancja na błędy.
Narzędzia i technologie:
Odkrywanie usług: consul, etcd, zookeeper, kubernetes.
Równoważenie obciążenia: Reverse Proxy: N g i n x, HaProxy.
Service Mesh: Istio, Linkerd.
Load Balancers: Google Cloud LB.

33) Definicja usługi SOAP?
Format żądania/odpowiedzi, struktura żądania, struktura odpowiedzi, punkt końcowy.

34) Czym są systemy samodzielne (SCS)?
SCS to typ mikroserwisu, który określa elementy makroarchitektury.

35) Czym jest testowanie bezpieczeństwa?
Testowanie bezpieczeństwa obejmuje szereg technik testowania zaprojektowanych w celu oceny poufności, 
integralności, uwierzytelniania, autoryzacji i dostępności aplikacji oprogramowania. Łagodzenie ryzyka. Zgodność: 
PCI DSS, GDPR. Zaufanie klientów.

36) Jaka jest rola Schema Registry w Kafka?
Wymusza na producencie i konsumencie korzystanie z prawidłowego schematu.

37) Przykłady zastosowania wzorca Scatter Gather?
Wzorzec skalowalności. Przykłady zastosowania: Usługi wyszukiwania, Usługi hotelarskie.

38) Przykład skalowalnych rozwiązań do przechowywania danych nieustrukturyzowanych w chmurze i rozwiązań open source?
Amazon S3, Google Cloud Storage, Azure Blob Storage.

39) Przykłady zastosowania wzorca SAGA?
Rozproszone, długoterminowe transakcje między usługami. Używany do długotrwałych transakcji (Long Term Transactions). 
Łańcuch lokalnych transakcji ACID, aby sfinalizować długotrwałą transakcję między usługami. Różne rodzaje: oparte na 
choreografii, oparte na orkiestracji. 
Transakcje ACID: Gwarantują, że baza danych będzie w spójnym stanie po wykonaniu grupy operacji.
Outbox: Pomaga w wykorzystaniu lokalnych transakcji ACID do zapewnienia spójnych (w końcu Eventually) transakcji rozproszonych.

40) Sposoby zapewnienia solidności?
a) Obsługa błędów.
b) Monitorowanie i logowanie.
c) Wersjonowanie i wycofywanie.
d) Testowanie i walidacja.
e) Redundancja i przełączanie awaryjne.
f) Protokoły komunikacyjne.
g) Odkrywanie usług.
h) Przełącznik obwodowy (Circuit Breaking): Zapobieganie kaskadowym błędom.
i) Łagodne degradacja (Graceful Degradation): Aplikacja działa nawet na obniżonym poziomie.

41) Co oznacza solidny system (Robust system)?
Solidny system potrafi działać efektywnie, nawet w obecności błędów lub innych problemów.
Analogia do samolotu z dwoma silnikami. Kluczowe cechy: Tolerancja na błędy, Wysoka dostępność, Skalowalność, Odporność, 
Bezpieczeństwo, Efektywność, Zarządzalność, Elastyczność.

42) Kluczowa abstrakcja REST?
Zasób ma U R I. Zasób może mieć różne reprezentacje: XML, J S O N, HTML.

43) Jakie są zasoby REST API - najlepsze praktyki?
a) Nazwanie zasobów za pomocą rzeczowników (nouns).
b) Rozróżnienie między zasobami kolekcji a zasobami pojedynczymi.
c) Nadawanie zasobom jasnych i znaczących nazw.
d) Identyfikatory zasobów powinny być unikalne i przyjazne dla URL.

44) Jakie są ważne koncepcje REST API?
a) H A T E O A S.
b) Bezstanowość: Skalowalność i wysoka dostępność.
c) Możliwość buforowania: Wydajność.
d) Nazwany zasób: Zasób prosty, zasób kolekcji.

45) Czym są replikacja i partycjonowanie?
Replikacja i partycjonowanie to techniki używane w systemach baz danych, które umożliwiają skalowanie i zwiększanie wydajności.
Replikacja polega na tworzeniu kopii danych rozłożonych na węzłach w celu zapewnienia dostępności i ochrony przed awariami.
Kopie są w wielu węzłach, z których każdy jest identyczny, np. BANKI, HOTELE, TELEKOMUNIKACJA.
Partycionowanie dzieli dużą bazę danych na mniejsze (partycje), przechowywane w różnych częściach sieci. Każdy węzeł 
obsługuje tylko podzbiór danych. To umożliwia równoległe przetwarzanie danych i szybsze odzyskiwanie danych z dużych 
baz danych. Duża wydajność i skalowalność, systemy handlowe, analiza danych.

46) Sposoby odtwarzania zdarzeń w Kafka?
Utworzenie nowej grupy konsumentów. Domyślnie będzie ona odczytywać zdarzenia od początku.

47) Technologie zdalnego wywoływania procedur?
g R P C, Apache Thrift, Java Remote Method Invocation (RMI).

48) Czym jest Redis?
Jest to magazyn danych klucz-wartość w pamięci. Oferuje pewne opcje przechowywania trwałego, ale zazwyczaj 
jest używany jako bardzo szybkie rozwiązanie do buforowania. Redis jest także wykorzystywany do implementacji rate limitu.

49) Czym jest programowanie reaktywne?
a) Reagujący (Responsive): System odpowiada tak szybko, jak to możliwe.
b) Odporność (Resilient): System pozostaje dostępny, nawet jeśli niektóre części zawodzą.
c) Elastyczność (Elastic): System potrafi radzić sobie z różnymi poziomami obciążenia, na przykład poprzez wykorzystanie 
dodatkowych zasobów.
d) Komunikacja asynchroniczna (Asynchronous communication): System korzysta z komunikacji asynchronicznej (opartej na wiadomościach).

50) Co to jest Quorum w Kafka?
Ustala minimalną liczbę brokerów potrzebnych do utworzenia sieci, zapobiegając problemowi podzielonego umysłu
(split brain).

51) Opisz atrybut jakości Czas odpowiedzi?
Czas między wysłaniem żądania przez klienta a otrzymaniem odpowiedzi. 
Czas odpowiedzi = Czas przetwarzania + Czas oczekiwania.

52) Opisz model Push w Postgres?
Model push, w którym Postgres powiadamia inne aplikacje o każdej zmianie.

53) Różnica między procesami a wątkami?
Proces to niezależna jednostka wykonawcza, która zawiera własną przestrzeń adresową, deskryptory plików, 
przestrzeń pamięci i wirtualne urządzenia.
Wątki wykorzystują zasoby systemowe, do których należą. W procesie może być wiele wątków, a wątek może mieć tylko jeden proces.
Procesy wymagają więcej czasu i zasobów systemowych do uruchomienia i komunikacji z innymi procesami, ponieważ wymagają 
przełączania kontekstu. Wątki są bardziej wydajne i wymagają mniej zasobów.

54) Opisz procesy w systemie operacyjnym?
Programy wykonywane na komputerze. Zawierają własne zasoby, takie jak przestrzeń adresowa, wątki i pamięć. 
Posiadają unikalny identyfikator P I D. Kluczowe struktury danych w kontekście procesu: tabela procesów, tabela plików, 
tabela stron.

55) Zasada odkrywania usług w architekturze mikroserwisów. Przykład narzędzi?
Umożliwia usługom znajdowanie się nawzajem i komunikowanie się ze sobą. 
Zasady odkrywania usług: Rejestracja, Wyszukiwanie, Pulsacje (Heartbeats). 
Znaczenie: Skalowalność, Równoważenie obciążenia, Dynamiczne aktualizacje, Odporność.
Przykłady narzędzi w branży: Consul, Etcd, Zookeeper, Kubernetes, Netflix Eureka, oparte na DNS (Netflix Ribbon).

56) Co to jest P O S I X?
Przenośny interfejs systemu operacyjnego. Standard określający, jak aplikacje mogą korzystać z funkcji 
systemowych. Przykłady funkcji systemowych: 'open()', 'read()', 'write()', 'fork()', 'exec()', 'pthread create()'. 
Alternatywą jest Win32.

57) Popularne narzędzia i techniki odkrywania usług?
a) Consul: Stworzony przez Hashicorp. Oferuje rozproszony rejestr usług i sprawdzanie stanu usług.
b) Etcd: Rozproszony magazyn klucz-wartość. Jest częścią ekosystemu Kubernetes.
c) Zookeeper: Usługa koordynacji rozproszonej. Oferuje hierarchiczną przestrzeń nazw i jest znana z silnej spójności.
d) Eureka: Netflix Eureka to rejestr usług i serwer odkrywania.
e) Kubernetes: Platforma orkiestracji, która zawiera wbudowane funkcje odkrywania usług poprzez DNS i zmienne środowiskowe.

58) Opisz wzorzec Pipes and Filters?
Wzorzec skalowalności. Rozwiązany problem: silne powiązanie (tight coupling), ograniczenia sprzętowe, niska skalowalność.
Korzyści: Możliwość używania różnych języków programowania, wysoka skalowalność, wysoka przepustowość.
Przykłady zastosowania: Biznes reklam cyfrowych, przetwarzanie danych I O T, przetwarzanie wideo i audio.

59) Opisz sieć Peer-to-Peer?
Zbiór maszyn zwanych peer ami, które dzielą obciążenie między sobą, aby przypuszczalnie zakończyć pracę 
szybciej, niż byłoby to możliwe w przeciwnym razie. Sieci Peer-to-Peer często stosowane są w systemach dystrybucji plików.
Protokół gossip: Kiedy zestaw maszyn komunikuje się ze sobą w niekoordynowany sposób w klastrze, aby rozprzestrzeniać 
informacje w systemie, bez konieczności posiadania centralnego źródła danych.

60) Co to jest pamięć podręczna stron (Page Cache)?
Składa się z fizycznych stron w pamięci RAM, odpowiadających fizycznym blokom na dysku.

61) Opisz wzorzec Outbox?
Służy do zapewnienia silnej spójności między lokalnymi transakcjami ACID a operacją publikacji zdarzeń.
Opiera się na przechowywaniu zdarzeń domenowych w lokalnej bazie danych, które będą publikowane później. 
Istnieją dwa sposoby implementacji części publikacji: pobieranie z tabeli outbox oraz CDC (change data capture). 
Umożliwia to spójność wzorca SAGA, gdy jest używany w połączeniu z SAGA.
Outbox: Pomaga w wykorzystaniu lokalnych transakcji ACID do zapewnienia spójnych (eventually) transakcji rozproszonych.
Umożliwia bezpieczne i spójne zakończenie SAGA. Automatycznie zapisuje zdarzenia w lokalnej bazie danych z wykorzystaniem transakcji ACID.
Odczytuje zdarzenia i publikuje:
a) Pobieranie z tabeli Outbox: Odczytuje zdarzenia za pomocą harmonogramu.
b) Zmiana danych (CDC): Nasłuchuje dzienników transakcji. Śledzi SAGA i status zamówienia w tabeli Outbox.
Zapewnia idempotencję: Nie konsumuje tych samych danych. 
Optymistyczne blokady (Optimistic Locking) i ograniczenia Biznesowe: Zapobiegają uszkodzeniu danych.

62) Opisz wątki systemu operacyjnego?
Podprocesy wykonywane jako część procesu. Służą do równoległego przetwarzania, co sprawia, że aplikacja staje 
się bardziej responsywna i zwiększa wydajność. Umożliwiają synchronizację pracy za pomocą mutexów lub semaforów.

63) Opisz livelock w systemie operacyjnym?
Wątki lub procesy nieustannie zmieniają stany i nie są w stanie zakończyć zadań, jednocześnie wykorzystując zasoby.
Jak unikać: Unikaj wykonywania podobnych lub identycznych zadań przez wiele procesów jednocześnie (semafory, synchronizacja).
Zastosuj strategię opóźnienia. Algorytmy do wykrywania i rozwiązywania konfliktów. Unikaj niekoordynowanego użycia zasobów.

64) Opisz jądro systemu operacyjnego?
Główna część systemu operacyjnego, zarządza zasobami i zapewnia interfejs do ich użycia.
Zarządza pamięcią. Zarządza procesami - przydziela zasoby, czas procesora, pamięć, I/O.
Zarządza zasobami systemowymi. Kontrola bezpieczeństwa. Interfejs do zasobów. Obsługuje przerwania: takie jak naciśnięcie 
klawisza, zakończenie działania aplikacji. Zarządzanie energią - hibernacja.

65) Opisz wirtualizację systemów operacyjnych?
Uruchamianie wielu maszyn wirtualnych na jednym fizycznym komputerze. Mechanizm wirtualizacji:
a) Maszyny wirtualne.
b) Konteneryzacja.
c) Oparta na systemie plików.
d) Sprzęt.

66) Co to jest OpenID Connect?
Służy do uwierzytelniania użytkownika za pomocą tokena ID.

67) Opisz OAuth 2?
Służy do delegowanej autoryzacji za pomocą tokena dostępu. Terminologia:
Właściciel zasobów, Klient, Serwer autoryzacji, Serwer zasobów, Uprawnienie do autoryzacji, URL przekierowania, 
Typ odpowiedzi, Token dostępu, Token OpenID.

68) Jakie są przypadki użycia No SQL?
Lepsze, jeśli chodzi o szybkość zapytań. Doskonały wybór do buforowania (caching). Obsługuje dane w czasie rzeczywistym 
i dużą ilość danych. Dane są nieustrukturyzowane.

69) Rozwiązania NoSQL?
a) Magazyn klucz/wartość (key value store): Przykłady to Redis, Aerospike, Amazon DynamoDB.
b) Magazyn dokumentów (document sture): Przykłady to Cassandra, MongoDB.
c) Baza danych grafowa: Przykłady to Amazon Neptune, NEO Four j.

70) Narzędzia do wymagań niefunkcjonalnych?
a) Skalowalność: Równoważenie obciążenia, partycjonowanie bazy danych, brama API.
b) Wydajność: CDN, buforowanie, indeksowanie bazy danych.
c) Wysoka dostępność: Replikacja bazy danych, redundancja, broker wiadomości, wdrażanie w wielu centrach danych.
d) Dostępność kosztem spójności: Wybór/konfiguracja baz danych typu A-P.
e) Trwałość: Replikacja i kopie zapasowe.

71) Przykłady wymagań niefunkcjonalnych?
a) Skalowalność: Miliony użytkowników dziennie.
b) Wydajność: Czas odpowiedzi poniżej 500 ms w 99 % przypadków.
c) Tolerancja na błędy/Wysoka dostępność: 99,9 % czasu dostępności (3 dziewiątki).
d) Dostępność + Tolerancja podziału (partition tolerance): AP ponad CP.
e) Trwałość (Durability).

72) Mikroserwisy, strategie i wzorce zapewniające wysoką dostępność i tolerancję na błędy?
Strategie i wzorce:
a) Redundancja i replikacja.
b) Wzorzec Circuit Breaker: Przykłady narzędzi to Netflix Hystrix, Resilience 4j.
c) Mechanizm ponowienia.
d) Sprawdzanie stanu i monitorowanie: Przykłady narzędzi to Prometheus, Grafana.
e) Usługi bezstanowe.
f) Rozproszone śledzenie: Przykład narzędzia to Zipkin.
g) Łagodna degradacja.
h) Inżynieria chaosu: Przykład narzędzia to Netflix Chaos Monkey.

73) Mikroserwisy, decyzje architektoniczne mikro i makro?
a) Makro lub Mikro: Język programowania, baza danych, wygląd i odczucie, dokumentacja.
b) Architektura mikro: Pipeline dostarczania ciągłego, autoryzacja, testy mikroserwisu w izolacji.
c) Architektura makro: Protokół komunikacyjny, uwierzytelnianie, testy integracyjne.

74) Jakie są kompromisy w mikroserwisach?
Robustość (Robustness) jest celem. Robustość (Robustness) nie ma znaczenia.

75) Jakie są wymagania skalowalności mikroserwisów?
Bezstanowe.

76) Jak osiągnąć odporność (robustness) mikroserwisów?
Aby osiągnąć odporność, mikroserwisy mogą buforować wartości i używać ich w przypadku problemu. 
Alternatywnie, może być zastosowana procedura zapasowa do uproszczonego algorytmu. Bez odporności dostępność systemu 
mikroserwisów może stanowić problem.

77) Opisz mikroserwisy?
Niezależny rozwój i wdrażanie przez różne zespoły. Łatwe skalowanie dla konkretnej usługi.
Lepsza tolerancja na błędy. Umożliwia używanie różnych technologii i języków dla różnych usług.
Czyste i heksagonalne architektury: Izolują logikę domeny od zewnętrznych zależności.

78) Silniejsza izolacja (Stronger Isolation) mikroserwisów?
Uproszczone dostarczanie ciągłe (CD), skalowanie wdrożeń, zrównoważone wdrożenie, swobodny wybór technologii, 
robustność, niezależne skalowanie, bezpieczeństwo, wymiana systemów dziedzicznych.

79) Rozwiązania i technologie chmurowe brokerów wiadomości?
Apache Kafka, Rabbit MQ, Amazon Simple Queue Service (SQS), GCP Pub/Sub i Cloud Tasks,
Microsoft Azure: Service BUS, Event Hub, Event Grid.

80) Co to jest megabajt (M B)?
10 do potęgi 6, czyli 1 000 000 bajtów.

81) Mebibajt (M I)?
2 do potęgi 20, czyli 1 048 576 bajtów.

82) Opisz wzorzec Map Reduce?
Wzorzec wydajności. Dominujące czynniki: równoległość, dystrybucja danych, harmonogramowanie wykonania, 
agregacja wyników, obsługa i odzyskiwanie z błędów. 
Popularny framework do efektywnego przetwarzania bardzo dużych zbiorów danych w rozproszonym środowisku, szybko i w 
sposób odporny na błędy. Składa się z 3 głównych kroków:
a) Map: uruchom funkcję map na fragmentach i przekształć je w pośrednie pary klucz-wartość.
b) Shuffle: rozpoznaje pośrednie pary klucz-wartość, tak aby pary o tym samym kluczu były kierowane do tej samej maszyny 
w końcowym kroku.
c) Reduce: uruchamia funkcję reduce na nowo przetasowanych parach klucz-wartość i przekształca je w bardziej znaczące dane.
Przetwarzanie i analiza dużych zbiorów danych, takich jak logi czy dane finansowe. Generowanie raportów i statystyk na 
podstawie zbioru danych. Analiza sentymentu w sieciach społecznościowych. Wykrywanie oszustw w transakcjach finansowych. 
Przetwarzanie danych przestrzennych (spatial data).
Przykłady technologii: Apache Hadoop, Spark, Google Cloud Dataflow.

83) Zarządzanie dokumentacją API i wyzwaniami związanymi z wersjonowaniem?
Narzędzia do dokumentacji: Swagger, OpenAPI. Jasne wytyczne.
Stosowanie strategii wersjonowania: URI, nagłówki. Dokumentacja jako kod. Testowanie i walidacja. Polityki deprecacji. 
Pętla sprzężenia zwrotnego. Współpraca. Materiały edukacyjne.

84) Co to jest testowanie obciążeniowe (load testing)?
Jak aplikacja oprogramowania działa w warunkach przewidywanego i ekstremalnego obciążenia. Zapewnia wydajność.
Identyfikuje wąskie gardła w wydajności. Pomaga w planowaniu pojemności i skalowaniu zasobów infrastruktury.
Doświadczenie użytkownika, minimalizacja ryzyka, optymalizacja zasobów.
Metryki wydajności:
a) Czas odpowiedzi.
b) Przepustowość.
c) Wskaźnik błędów.
d) Wykorzystanie CPU i pamięci.

85) Rozwiązania i technologie chmurowe równoważenia obciążenia?
HA Proxy, NGINX. Rozwiązania równoważenia obciążenia w chmurze: AWS Elastic Load Balancer (ELB), 
GCP Cloud Load Balancer, Microsoft Azure Load Balancer.
Rozwiązania GSLB: Amazon Route 53, Google Cloud Platform Load Balancer i Cloud DNS, Azure Traffic Manager.

86) Rozważania dotyczące implementacji równoważenia obciążenia?
a) Round Robin: Dla aplikacji bezstanowych.
b) Sticky Session / Session Affinity.
c) Najmniej połączeń (Least Connection): Zadania związane z długoterminowymi połączeniami (SQL, LDAP).

87) Algorytm lidera?
Algorytm konsensusu: rodzaj złożonego algorytmu używanego do osiągnięcia porozumienia między wieloma podmiotami 
w sprawie jednej wartości danych, na przykład, kto jest liderem w grupie maszyn. Paxos, Raft. Narzędzia używane do 
implementacji/wyboru lidera: Etcd, Zookeeper.

88) Opóźnienie i przepustowość?
Opóźnienie i przepustowość to dwa najważniejsze wskaźniki wydajności systemu.
Opóźnienie: Czas potrzebny na przesłanie pakietu z jednego komputera do drugiego w sieci komputerowej. Mierzone w mili sekundach.
Sprawdza czas propagacji między węzłami.
Przepustowość: Ilość danych, która może być przesłana przez sieć w określonym czasie. Mierzona w bitach na sakundę, kilo
bitach na sekundę. Ograniczenie przepustowości połączenia, wykorzystanie zasobów przez aplikację lub jakość połączenia.

89) Baza danych klucz-wartość?
Baza danych klucz-wartość to elastyczna baza danych No SQL, często używana do buforowania i dynamicznej konfiguracji.
Popularne opcje to Dynamo DB, Etcd, Redis i Zookeeper.

90) Kluczowe różnice między architekturą mikroserwisów a architekturą monolityczną?
Kluczowe różnice: rozmiar i złożoność, skalowalność, rozwój i wdrażanie, stos technologiczny.
Zalety mikroserwisów: skalowalność, elastyczność, odporność, różnorodność technologii, łatwość w utrzymaniu, 
ciągłe wdrażanie, decentralizacja.
Wyzwania: złożoność, komunikacja, narzut komunikacyjny, zarządzanie danymi, testowanie, narzut operacyjny, 
bezpieczeństwo, odkrywanie usług (service discovery).

91) Kafka, jakie są argumenty za mniejszą liczbą partycji?
Mniejsze zużycie pamięci po stronie klienta. Zajmuje mniej czasu, aby znaleźć lidera dla każdej partycji, 
jeśli węzeł zawiedzie.

92) Czym są tematy (topics) w Kafka?
Temat to nazwany zbiór rekordów. Producenci wysyłają rekordy do topiców, a konsumenci odbierają je z tematu.
Przechowuje dane/wydarzenia. Niezmienne i tylko do dopisywania. Temat Kafka: składa się z jednej lub więcej partycji do 
przechowywania danych/wydarzeń.

93) Czym jest szablon (template) Kafka? 
Bezpieczny wątkowo szablon do wykonywania operacji na poziomie producenta.

94) Dlaczego Kafka jest szybka? 
Kafka jest szybka, ponieważ polega na pamięci podręcznej na dysku i plikach mapowanych w pamięci. 
Pliki mapowane w pamięci (memory mapped files): Zawierają zawartość pliku w pamięci wirtualnej. 
Oferują niższą latencję I/O niż bezpośredni dostęp do dysku.

95) Kompresja snappy w Kafka? 
Ustala współczynnik kompresji dla wyższej prędkości kompresji/dekompresji.

96) Skalowalność i porządek (order) w Kafka? 
Kafka jest skalowalna: Naturalnie skalowanie przez partycje. Porządek: Gwarantowany przez partycję. 
Zachowanie porządku: Użyj strategii partycjonowania, aby wprowadzić powiązane dane do tej samej partycji.

97) Odporność (Resilience) w Kafka? 
Opiera się na dysku i wykorzystuje repliki. Odporność dzięki współczynnikowi replikacji.

98) Współczynnik replikacji w Kafka? 
Replikacja danych partycji na różnych brokerach.

99) Replikacja w Kafka? 
Partycje mogą być rozproszone po serwerach. Partycje mogą być również replikowane, co oznacza, że dane są 
przechowywane na kilku serwerach, co sprawia, że Kafka może być odporna na awarie. Jeśli jeden serwer ulegnie awarii 
lub utraci dane, inne repliki nadal istnieją.

100) Rekordy w Kafka? 
Kafka organizuje dane w rekordy. To, co inne systemy MOM nazywają „wiadomościami” (messages). Zawierają transportowane 
dane jako wartość. Rekordy mają klucz i znacznik czasu.

101) Polling w Kafka? 
Konsumenci pobierają dane, co oznacza, że ściągają nowe dane i je przetwarzają. Konsumenci mogą sami decydować, 
kiedy przetwarzają rekordy.

102) Trwałość (Persistence) w Kafka? 
Kafka jest połączeniem systemu wiadomości i rozwiązania do przechowywania danych. Domyślny czas przechowywania 
rekordów wynosi siedem dni.

103) Partycje w Kafka? 
Tematy są podzielone na partycje. Partycje umożliwiają silne gwarancje dotyczące porządku rekordów, a także 
równoległe przetwarzanie. Dla każdej partycji zachowany jest porządek rekordów. Nie ma gwarancji porządku między partycjami. 
Przetwarzanie między partycjami może być równoległe. W zasadzie partycja to po prostu plik, do którego dodawane są rekordy.

104) Klucz partycji w Kafka? 
Ustala docelową partycję dla wiadomości.

105) Kompaktowanie logów (Log compaction) w Kafka? 
Wszystkie rekordy z tym samym kluczem są usuwane, z wyjątkiem ostatniego. Tylko ostatnia aktualizacja porządku 
pozostaje dostępna w Kafka.

106) Czas oczekiwania (linger m s) w Kafka? 
Dodaje opóźnienie w przypadku lekkiego obciążenia.

107) Lider i obserwatorzy w Kafka? 
Replikacja jest realizowana w taki sposób, że jeden lider zapisuje, a pozostałe repliki działają jako 
obserwatorzy. Producent zapisuje bezpośrednio do lidera.

108) Wysoka dostępność w Kafka? 
Eliminacja pojedynczego punktu awarii.

109) Grupy konsumentów w Kafka? 
Konsumenci są zorganizowani w grupy konsumentów, gdzie każda partycja wysyła rekordy do dokładnie jednego 
konsumenta w grupie. Jeden konsument może być odpowiedzialny za kilka partycji.

110) Podstawy konsumentów w Kafka? 
Kafka opiera się na logach, które są znane jako partycje. Producenci Kafka zapisują na końcu konkretnej 
partycji, a konsumenci Kafka odczytują logi, zaczynając od początku, pamiętając, gdzie skończyli, dzięki offsetowi. 
Kafka rozdziela partycje między konsumentów na podstawie identyfikatora grupy konsumentów. Każda partycja jest przypisana 
do jednego konsumenta w grupie konsumentów. Różne grupy konsumentów mogą odczytywać dane z tej samej partycji. 
Ustaw liczbę partycji równą liczbie konsumentów. Ustaw czas oczekiwania na polling na wartość, która nie jest zbyt duża, 
aby zapobiec blokowaniu na czas nieokreślony, i nie zbyt mała, aby uniknąć stagnacji CPU. Semantyka dostarczania: 
co najmniej raz (at least once), maksymalnie raz )at most oncce), dokładnie raz (exactly once). Partycja może mieć tylko 
jednego konsumenta. Konsument może odczytywać z wielu partycji. Poziom współbieżności konsumenta Kafka: wątki, procesy, maszyny.

111) Zatwierdzanie (Commit) w Kafka? 
Kafka przechowuje offset każdej partycji. Ten offset wskazuje, który rekord w partycji konsument odczytał 
i przetworzył jako ostatni. Gdy konsumenci przetworzą rekord, zatwierdzają nowy offset.

112) Wyzwania w Kafka? 
Konfiguracja często nie jest łatwa. Jeśli Kafka jest używana jako magazyn zdarzeń, rekordy muszą zawierać 
wszystkie dane, których potrzebują wszyscy klienci. Często trudno to zaimplementować z powodu ograniczonego kontekstu.

113) Korzyści z Kafka? 
Może przechowywać rekordy na stałe, co ułatwia korzystanie z event sourcing. Podejścia takie jak Avro do 
rozwiązywania ewolucji schematu. Może gwarantować dostarczenie do konsumenta, rozdzielając pracę między kilku konsumentów. 
Może gwarantować dostarczenie wiadomości. Konsument zatwierdza rekordy, które pomyślnie przetworzył. Jeśli wystąpi błąd, 
nie zatwierdza rekordu i próbuje go przetworzyć ponownie (retry).

114) Rozmiar partycji w Kafka? 
Dostosuj do wyższej przepustowości.

115) Podstawy Kafka? 
Niemutowalne, tylko do dodawania logi. Szybkie, odporne, skalowalne, o wysokiej przepustowości. Opiera się na 
systemie plików do przechowywania i pamięci podręcznej wiadomości. Odporne i tolerancyjne na awarie dzięki replikacji. 
Pamięć podręczna na dysku, pliki mapowane w pamięci zamiast pamięci kwalifikowanej do GC. Skalowanie przez partycje. 
Uporządkowane wewnątrz partycji. Jako magazyn zdarzeń: doskonałe dopasowanie dla mikroserwisów opartych na zdarzeniach.

116) Avro jako format danych w Kafka? 
Avro to protokół binarny, ale oferuje również reprezentację opartą na J S O N. Avro wspiera schemat, co oznacza, 
że każdy zbiór danych jest zapisywany lub wysyłany razem ze swoim schematem. Ewolucja schematu, nowe pola mogą być dodawane, 
gdy zdefiniowane są wartości domyślne, dzięki czemu stare dane mogą być konwertowane na nowy schemat przy użyciu wartości domyślnej.

117) Architektura Kafka? 
Brokery Kafka: Serwery działające w klastrze. 
Tematy (topics): Logiczna jednostka danych, która zawiera wiele partycji. 
Partycje: Najmniejsza jednostka przechowywania, która przechowuje podzbiór rekordów. 
Producenci: Zapisują na końcu konkretnej partycji. 
Konsumenci: Odczytują z partycji, używając offsetu. 
Replikacja: Odporna i tolerancyjna na awarie. 
Skalowanie: Strategia partycjonowania. Niemutowalne logi zdarzeń tylko do dodawania.

118) API w Kafka? 
API producenta: serwery do wysyłania danych. 
API konsumenta: serwery do odbierania danych. 
API strumieni: serwery do przekształcania danych.

119) Kafka i programowanie w Kafka? 
KafkaTemplate: To komponent Spring, który opakowuje producenta Kafka i udostępnia metody do łatwego 
produkowania danych w Kafka. Aby stworzyć konsumenta Kafka z użyciem Spring, wystarczy użyć KafkaListener na prostej 
metodzie. Jeśli moje tematy Kafka mają n partycji, nie mogę używać więcej niż n wątków do konsumowania danych z tematów. 
Konsument Kafka ma właściwość max-poll-records, która ogranicza liczbę rekordów zwracanych w jednym pollu.

120) Zalety Kafka? 
Wysoka przepustowość. Niska latencja. Replikacja i możliwość skalowania. Przechowuje historię wiadomości 
(możliwe są podejścia takie jak event sourcing).

121) AdminClient w Kafka? 
Zarządza i inspekcjonuje brokerami, tematami i konfiguracjami.

122) Potwierdzenia (acks) w Kafka? 
all: czekaj na potwierdzenie od wszystkich replik. 
1: czekaj tylko na potwierdzenie od bieżącego brokera. 
0: brak potwierdzenia.

123) Jak zapewnić wysoką wydajność w systemach rozproszonych? 
Rozkład obciążenia (partycjonowanie, replikacja i skalowanie). Minimalizowanie przetwarzania w sieci. 
Optymalizacja przetwarzania w węzłach. Optymalizacja komunikacji między węzłami. Monitorowanie i diagnostyka.

124) Jak uprościć systemy rozproszone? 
Unikaj komplikowania systemu. Używaj standardowych protokołów. Stosuj architekturę mikroserwisów. Wdrażaj 
projekt modułowy. Automatyzacja: instalacja, testowanie i monitorowanie. Odpowiednia dokumentacja. Prosty model danych. 
Używanie prostego języka programowania.

125) Jak przełączanie kontekstu jest wywoływane przez system operacyjny i sprzęt? 
Wiele procesorów ma specjalne instrukcje przełączania kontekstu, takie jak instrukcje CALL, RET, JMP lub INT, 
które umożliwiają przeniesienie kontroli z jednego procesu do drugiego. Procesory mają również specjalne rejestry, które 
przechowują informacje o stanie procesu, takie jak liczniki instrukcji, status rejestrów i flagi. W systemach operacyjnych 
opartych na przerwaniach (interruption based systems) przełączanie kontekstu może być wywołane przez przerwanie sprzętowe, 
takie jak z zegara czasu rzeczywistego, które wywołuje przerwanie programowe. Inne są oparte na wątkach.

126) Czym jest protokół IP? 
Warstwa sieciowa. Pakiety. Dzieli dane na pakiety, przypisuje im adres sieciowy i wysyła do odbiorców. 
Bezstanowy (brak informacji o wcześniejszych operacjach). Bezpołączeniowy (connectionless).

127) Pakiety IP? 
Składają się z nagłówka i danych użytkownika. Adres źródłowy / docelowy, wersja protokołu, długość pakietu, 
identyfikator pakietu, flagi, TTL, typ ładunku (TCP/UDP), suma kontrolna (checksum).

128) Integralność danych - metody zapewnienia? 
Walidacja danych. Replikacja danych. Kopie zapasowe. Szyfrowanie danych. Spójność danych: zapewnienie, 
że dane są takie same między serwerami, przy użyciu technik takich jak dwufazowe zatwierdzenie (two phase commit) lub 
algorytm konsensusu rozproszonego. Audyt danych. Wykrywanie i korekta błędów, sumy kontrolne lub kody korekcyjne. 
Kontrola dostępu.

129) Zasady architektury systemów niezależnych?
Zasada 1: System musi być podzielony na moduły.
Zasada 2: Dwa oddzielne poziomy decyzji architektonicznych: Architektura makro: Dotyczy wszystkich modułów.
Architektura mikro: Decyzje, które mogą być podejmowane różnie dla każdego indywidualnego modułu.
Zasada 3: Moduły muszą być oddzielnymi procesami / kontenerami / maszynami wirtualnymi.
Zasada 4: Standaryzowana integracja i komunikacja.
Zasada 5: Standaryzowane metadane. Metadane do śledzenia i autoryzacji muszą być standaryzowane. Takie metadane muszą 
być przesyłane między mikroserwisami.
Zasada 6: Niezależny ciągły proces dostarczania.
Zasada 7: Operacje powinny być standaryzowane.
Zasada 8: Standaryzowany interfejs - ładunek J S O N używający HTTP.
Zasada 9: Moduły muszą być odporne.

130) W systemie opartym na Kafka istnieje N konsumentów i M partycji. Co jest możliwe? 
N (konsumenci) większe niż M (partycje) nie jest możliwe. Wiele konsumentów nie może odczytywać z tej samej 
partycji.

131) Znaczenie (TDD)? 
Znaczenie: Zapewnienie jakości, Specyfikacja, Szybka informacja zwrotna, Bezpieczeństwo refaktoryzacji.
Workflow TDD: Napisz test, Napisz kod, Uruchom test, Refaktoryzuj kod, Powtarzaj.
Korzyści z TDD: Wczesne wykrywanie problemów, Poprawa projektu, Dokumentacja, Pewność siebie.
Wyzwania: Początkowa krzywa uczenia się, Czasochłonność, Utrzymanie, Nadmierny nacisk na testowanie.

132) Znaczenie bramy API (API Gateways) w architekturze mikroserwisów? 
Zcentralizowany punkt wejścia obsługuje: Ruting, równoważenie obciążenia, uwierzytelnianie i autoryzację, 
ograniczanie przepustowości. Przykład: Amazon API Gateway.

133) Operacje idempotentne? 
Operacja nie ma żadnego dodatkowego wpływu na wynik, jeśli jest wykonywana więcej niż raz.

134) Jak odkrywanie usług ułatwia komunikację? 
Dynamiczne adresowanie, równoważenie obciążenia, sprawdzanie stanu, odzyskiwanie po awarii.

135) Architektura heksagonalna, adaptery podstawowe (primary adapters), adaptery wtórne (secondary adapters)? 
Adaptery podstawowe (Primary Adapters): Implementują porty wejściowe do wykonywania przypadków użycia.
Adaptery wtórne (Secondary Adapters): Implementują porty wyjściowe i są wywoływane przez logikę biznesową w celu 
realizacji zadań zewnętrznych.
Wstrzykiwanie zależności: Wstrzykiwanie implementacji części w czasie wykonywania.
Porty i adaptery: Oddzielają wnętrze (warstwa domenowa) od zewnętrza (warstwa infrastruktury).
Zasada polega na izolowaniu domeny od jakiejkolwiek zależności, takiej jak UI, warstwa danych czy nawet framework, 
taki jak Spring.
Warstwa domenowa: Powinna być najbardziej niezależnym i stabilnym komponentem systemu.

136) Czym jest Hadoop? 
Framework do przetwarzania dużych zbiorów danych na klastrze komputerów. Dwa główne moduły to 
Hadoop Distributed File System (HDFS) i MapReduce. HDFS to rozproszony system plików, który dzieli pliki na bloki i 
replikuję je na różnych węzłach w klastrze, aby zapewnić niezawodność i wydajność. MapReduce przetwarza rozproszone dane 
w klastrze Hadoop. Hadoop jest używany do analizy dużych zbiorów danych, logów, danych finansowych.

137) Testowanie funkcjonalne versus testowanie regresyjne? 
Testowanie funkcjonalne: Testuje aplikację w odniesieniu do wymagań i specyfikacji funkcjonalnych.
Testowanie regresyjne: Ma na celu identyfikację i wykrycie wszelkich niezamierzonych skutków ubocznych lub nowych defektów, 
które mogły zostać wprowadzone przez zmiany.

138) Polityka wykładniczego opóźnienia (exponential backoff policy)? 
Zwiększa czas oczekiwania przy każdym kolejnym podejściu do ponowienia.

139) Testowanie eksploracyjne? 
Testowanie eksploracyjne to nieskryptowane podejście do testowania, w którym testerzy jednocześnie projektują, 
wykonują i dostosowują testy na podstawie swojego zrozumienia oprogramowania. Zachęca do krytycznego myślenia.

140) Wyjaśnij różne metody lub protokoły, które mogą być używane do komunikacji między usługami? 
a) HTTP/Restful API: Proste, łatwe do wdrożenia, wymagają kompatybilności.
b) g R P C: Mikro w różnych językach programowania. Komunikacja wewnętrzna w prywatnej sieci.
c) Kolejki wiadomości (message queues): RabbitMQ, Apache Kafka. Komunikacja asynchroniczna.
d) Graph Q L: Język zapytań dla API, który umożliwia klientom żądanie tylko tych danych, których potrzebują.
e) WebSocket: Dwukierunkowy kanał komunikacyjny w pełnym dupleksie przez jedno, długoterminowe połączenie, 
używany w czatach, grach.
f) Service Mesh (Istio, Linkerd): Dedykowana warstwa infrastruktury do obsługi komunikacji między usługami.
Zarządza i zabezpiecza komunikację w złożonym ekosystemie mikroserwisów.

141) Wyjaśnij koncepcję transakcji rozproszonych i protokół dwuetapowego zatwierdzania (2PC)? 
Transakcje rozproszone: Wiele operacji w rozproszonych systemach lub mikroserwisach, gdzie każda operacja jest częścią 
większej transakcji. W transakcjach rozproszonych, albo wszystkie operacje w transakcji są pomyślnie zakończone, 
albo żadna z nich. Zapewnia to spójność danych między usługami.
Protokół dwuetapowego zatwierdzania (2PC): Jest protokołem zarządzania transakcjami rozproszonymi, używanym do zapewnienia, 
że transakcje rozproszone są albo zatwierdzane, albo wycofywane w sposób spójny w wielu usługach lub bazach danych.
Fazy protokołu dwuetapowego zatwierdzania (2PC):
a) Faza przygotowawcza: Koordynator pyta wszystkich uczestników, czy są gotowi do zatwierdzenia. Gdy się zgadzają, 
odpowiadają wiadomością „przygotowane”.
b) Faza zatwierdzenia: Jeśli wszyscy uczestnicy odpowiadają pozytywnie w fazie przygotowawczej, koordynator wysyła 
wiadomość zatwierdzającą do wszystkich uczestników. Jeśli którykolwiek uczestnik odpowiada negatywnie lub wcale, 
koordynator wysyła wiadomość wycofującą, aby cofnąć transakcje. Spójność danych między usługami lub bazami danych jest 
kluczowa. Złożony proces biznesowy.
Wyzwania: Przeciążenie wydajności (performance overflow). Blokowanie. Pojedynczy punkt awarii. Złożoność. Zamykanie zasobów. 
Ograniczona skalowalność.
Przykłady narzędzi: Java Atomikos, adnotacja @Transactional.

142) Wyjaśnij koncepcję wyłączników obwodowych (circuit breakers) w mikroserwisach i jak przyczyniają się do odporności systemu? 
Zwiększają odporność na błędy i niezawodność. Mogą pomóc zapobiegać kaskadowym awariom i poprawić stabilność systemu. 
Stany: Stan zamknięty, Stan otwarty, Stan półotwarty. Poprawiają odporność na błędy i zapobiegają kaskadowym awariom 
podczas interakcji z zdalnymi usługami. Zapewniają mechanizm do łagodnego zarządzania degradacją usług i chronią 
ogólną niezawodność systemu.

143) Wzorzec orkiestratora wykonania (execution orchestrator pattern)? 
Wzorzec skalowalności. Jedna scentralizowana usługa, działająca jako mózg. Jedyna usługa świadoma:
kontekstu i kroku wykonania. Odpowiedzialna za obsługę problemów i ponowne próby.

144) Ostateczna spójność (eventual consistency) w systemach rozproszonych? 
Pozwala na tymczasowe niespójności między kopiami danych, ale gwarantuje, że zostaną one rozwiązane.
Dostępność i tolerancja podziału są kluczowe. Zapewnienie spójności danych:
a) Użyj operacji idempotentnych.
b) Wdrożenie wersjonowania danych.
c) Strategie rozwiązywania konfliktów „least write wins”, „scalanie”.
d) Komunikacja asynchroniczna.
e) Wzorzec CQRS.
f) Event sourcing.
g) Monitorowanie i alarmowanie.
h) Testowanie.
i) Dokumentacja i śledzenie.

145) Ostateczna spójność (eventual consistency)? 
Model spójności używany w obliczeniach rozproszonych do osiągnięcia wysokiej dostępności, który nieformalnie 
gwarantuje, że jeśli nie wprowadza się nowych aktualizacji do danego elementu danych, to ostatecznie wszystkie dostępy do 
tego elementu zwrócą ostatnią zaktualizowaną wartość.

146) Event store? 
Przechowuje dane jako niezmienne zdarzenia.

147) Architektura oparta na zdarzeniach? 
Zdarzenie to niezmienna deklaracja faktu lub zmiany.
Przykłady zdarzeń faktowych: Użytkownik klika na cyfrową reklamę. Element jest dodawany do koszyka zakupowego.

148) ETL (Extract, Transfer, Load)? 
Termin odnoszący się do procesu przenoszenia i przetwarzania danych z różnych źródeł.
Pipeline ETL to sekwencja etapów: pobieranie danych, ich transformacja i przetwarzanie oraz ładowanie do systemu docelowego.
Extract: pobieranie danych ze źródła, np. Apache NiFi, Kafka Connect.
Transfer: przetwarzanie zgodnie z regułami Apache Spark, Apache Beam.
Load: przenoszenie danych do systemu docelowego. 
Przykłady narzędzi: Apache Kafka, Amazon S3, ElasticSearch.

149) Baza danych Etcd? 
Etcd to silnie spójny i wysoko dostępna baza danych klucz-wartość, często używany do wdrażania wyboru lidera 
w systemie.

150) Projektowanie zorientowane na domenę (D D D)?
Ograniczony kontekst: Każdy model domeny jest ważny tylko w ograniczonym kontekście. 
Na przykład proces zamówienia, płatności i wysyłki to ograniczone konteksty. 
Ograniczony kontekst: encje, agregaty, obiekty wartości, usługi domenowe, usługi aplikacyjne i zdarzenia domenowe. 
Mapowanie domeny biznesowej i ograniczonego kontekstu dla modelu domeny.

151) Projektowanie zorientowane na domenę (D D D)?
Model domeny: Koncepcyjny model domeny, który definiuje zachowanie i dane systemu.
Logika domeny: W centrum oprogramowania. Niezależna od innych zależności.

152) Zapytania DNS?
Z postaci czytelnej dla człowieka na adres IP.
Polecenia: ns-lookup, dig.
Proces: Zapytanie, sprawdzanie pamięci podręcznej DNS, wyszukiwanie:
a) Wysyłanie do głównego serwera DNS dla domeny.
b) Wysyłanie do jednego z serwisów odpowiedzialnych za domenę.
c) Odbieranie adresu IP z jednego z serwerów.
DNS zapisuje w pamięci podręcznej. Zwracanie adresu do klienta.

153) Śledzenie rozproszone (Distributed Tracing) w architekturze mikroserwisów i dlaczego jest cenne dla monitorowania 
i rozwiązywania problemów?
Śledzenie rozproszone polega na śledzeniu zapytań lub transakcji, gdy poruszają się przez różne mikroserwisy 
w systemie rozproszonym. Każda usługa rejestruje informacje o czasie oraz dane kontekstowe, co umożliwia rekonstrukcję 
ścieżek zapytań i identyfikację wąskich gardeł w wydajności. Przykład: Jaeger.

154) Rozproszony system plików (distributed file system)?
Rozproszony system plików to abstrakcja nad (zwykle dużym) klastrem maszyn, która pozwala im działać jak 
jeden duży system plików. Dwie najpopularniejsze implementacje DFS to Google File System (GFS) oraz 
Hadoop Distributed File System (HDFS). Zwykle DFS zajmuje się klasycznymi gwarancjami dostępności i replikacji, 
które mogą być trudne do uzyskania w środowisku systemu rozproszonego. Ogólnym pomysłem jest podział plików na kawałki 
o określonym rozmiarze (na przykład 4 MB lub 64 MB), które są dzielone w dużym klastrze maszyn. 
Centralna płaszczyzna sterująca (central control plane) odpowiada za decydowanie, gdzie każdy kawałek się znajduje, 
kierowanie odczytów do odpowiednich węzłów oraz zarządzanie komunikacją między maszynami. Różne implementacje DFS mają 
nieco różne interfejsy API i semantykę, ale osiągają ten sam wspólny cel: bardzo dużą, trwałą pamięć masową.

155) Różnice między komunikacją synchroniczną a asynchroniczną w mikroserwisach?
Synchroniczna: Klient wysyła zapytanie i czeka na odpowiedź przed kontynuowaniem komunikacji.
Odpowiedniość (Suitability): Klient wymaga natychmiastowej odpowiedzi i może tolerować opóźnienia, 
na przykład przy zapytaniach do bazy danych. Gdy silna spójność (strong consistency) jest kluczowa.
Asynchronicznie: Klient wysyła zapytanie i kontynuuje swoje operacje bez czekania na natychmiastową odpowiedź.
Odpowiedniość: Niskie opóźnienie jest kluczowe. Długoterminowe zadania, przetwarzanie w tle, architektura zdarzeniowa 
oraz odseparowanie usług. Priorytetem są skalowalność, responsywność i tolerancja błędów.
Tolerancja opóźnień, złożoność, silna (strong) kontra eventualna spójność (eventual consistency), brokerzy wiadomości, 
obsługa błędów. Złożone interakcje: wzorzec choreografii lub saga. Architektura zdarzeniowa.

156) Różnica między testowaniem dymnym a testowaniem sanity?
Testowanie dymne: To test o wysokim poziomie, które sprawdza, czy aplikacja może się uruchomić i wykonać podstawowe 
operacje bez poważnych problemów.
Testowanie sanity: Przeprowadza się, aby upewnić się, że ostatnie zmiany nie wpłynęły negatywnie na powiązaną funkcjonalność.
Różnice: Cel, zakres, czas, głębokość, kryteria zaliczenia/niezaliczenia.

157) Różnica między testowaniem funkcjonalnym a niefunkcjonalnym?
Funkcjonalne: Weryfikuje, czy funkcje i cechy aplikacji działają zgodnie z określonymi wymaganiami i specyfikacjami. 
Składa się z: testów jednostkowych, integracyjnych, systemowych, testów akceptacyjnych użytkownika (UAT) – "co".
Niefunkcjonalne: Ocenia wydajność, bezpieczeństwo, użyteczność i niezawodność. Składa się z: testów wydajności, 
bezpieczeństwa, użyteczności, niezawodności – "jak".

158) Opisz typowe fazy cyklu życia testowania oprogramowania (STLC)?
Analiza wymagań, planowanie testów, projektowanie testów, przygotowanie środowiska testowego, wykonanie testów, 
raportowanie i śledzenie defektów, rozwiązywanie defektów i ponowne testowanie, zakończenie testów.

159) Czym jest inwersja zależności (Dependency Inversion)?
Moduły na wysokim poziomie nie powinny zależeć od modułów na niskim poziomie. Oba powinny zależeć od abstrakcji.

160) Czym jest Debezium?
Otwarta platforma do przechwytywania zmian danych. Reaguje za każdym razem, gdy dane się zmieniają, 
bez zmiany aplikacji, która modyfikuje dane. Connector zrzutu do Kafki - obraz dockera 'debezium/connect'.
Wsparcie dla Confluent Schema Registry. Connectory źródłowe dla różnych baz danych. Connector źródłowy Postgres 
'io.debezium.connector.postgresql.PostgresConnector'.

161) Strategiczne projektowanie D D D (Strategic Design) i wspólne wzorce?
a) Wzorzec klient / dostawca: Dostawca jest w górę (up stream), a konsument w dół (down stream). 
Na przykład wykorzystuje model procesu zamówienia. Jednak płatność definiuje wymagania dla procesu zamówienia.
b) Wzorzec konformisty: Ograniczony kontekst po prostu korzysta z modelu domeny innego ograniczonego kontekstu.
c) Warstwa antykorupcyjna (anti corruption pattern): Ograniczony kontekst nie korzysta bezpośrednio z modelu domeny 
innego ograniczonego kontekstu.
d) Wzorzec oddzielnej drogi: Ograniczone konteksty nie są ze sobą powiązane na poziomie oprogramowania, chociaż związek 
byłby wyobrażalny.
e) Wzorzec wspólnego rdzenia (The Shared Kernel Pattern): Opisuje wspólny rdzeń, który jest współdzielony przez wiele 
ograniczonych kontekstów, antywzorzec mikroserwisów.
f) Wzorzec otwartej usługi hosta: Ograniczony kontekst oferuje ogólny interfejs z kilkoma usługami, często spotykany 
w publicznych API w Internecie.
g) Model języka publikacji: Jest to model domeny dostępny dla wszystkich ograniczonych kontekstów. 
Na przykład standardowy format, taki jak EDIFACT, dla transakcji między firmami.

162) Szczegółowy plan testowania?
Przegląd wymagań. Ocena ryzyka. Planowanie testów. Typy testów: testy jednostkowe, integracyjne, systemowe, 
bezpieczeństwa, wydajności, bezpieczeństwa, użyteczności. Przygotowanie środowiska testowego. Dane testowe i scenariusze. 
CI i ciągłe testowanie. Śledzenie. Weryfikacja krytyczna dla bezpieczeństwa. Lista rzeczy do zrobienia i praktyki. 
Dokumentacja i raportowanie. Certyfikacja. Wykonanie testów i walidacja. Łagodzenie ryzyka. Monitorowanie po wdrożeniu.

163) Trwałość (Durability) danych?
Replikacja danych. Kopie zapasowe. Przechowywanie w RAID: dane przechowywane na kilku dyskach, 
co pozwala na przetrwanie awarii dysku.
Gwarancje trwałości danych: takie jak logowanie przed zapisem (write-ahead logging) lub tworzenie migawki (shapshoting). 
Wykrywanie i korekcja błędów: sumy kontrolne lub kody korekcji błędów. 
Kompozycja danych: algorytm dwuetapowego zatwierdzania lub rozproszony algorytm konsensusu. 
Trwałe kolejki wiadomości. 
Rozwiązania w chmurze.

164) Różnice między CRUD a CQRS?
a) Odpowiedzialność: 
CRUD: odczyt + zapis razem (warstwa dostępu do danych). 
CQRS: oddzielona odpowiedzialność — warstwa poleceń, warstwa zapytań.
b) Operacje: 
CRUD: 4 operacje — tworzenie, odczyt, aktualizacja, usunięcie. 
CQRS: polecenia zmieniają stan, zapytania odczytują.
c) Model danych: 
CRUD: jeden model. 
CQRS: podzielony model, baza danych zoptymalizowana pod kątem zapisu lub odczytu.
d) Skalowalność: 
CRUD: wszystkie operacje skalują się razem. 
CQRS: niezależna skalowalność.
e) Zdarzenia: 
CRUD: brak naturalnego mechanizmu śledzenia zmian. 
CQRS: magazyn zdarzeń (event store).

165) Typy wiadomości w CQRS?
Polecenie (Command): Wyraża zamiar zmiany stanu aplikacji, na przykład CreateProductCommand, Update-Product-Command.
Zapytanie (Query): Wyraża chęć uzyskania informacji, na przykład FindProductQuery, Get-User-Query.
Zdarzenie (Event): Reprezentuje powiadomienie, że coś istotnego się wydarzyło, na przykład Product-Created-Event, 
ProductUpdatedEvent.

166) Opisz CQRS.
Oddziela operacje odczytu i zapisu. Lepsza wydajność części odczytowej dzięki zastosowaniu odpowiedniej 
technologii do odczytu oraz zapobieganiu konfliktom z poleceniami aktualizacji. Każda część może być skalowana osobno. 
Prowadzi do eventualnej spójności, ponieważ część odczytowa jest aktualizowana asynchronicznie. 
Gdy operacja zapisu jest utrwalona, zdarzenie jest przechowywane w magazynie zdarzeń. Zdarzenia mogą być odtwarzane 
wielokrotnie w zależności od wymagań, aby stworzyć różne rodzaje magazynów zapytań.

167) Wywołania systemowe przełączania kontekstu?
a) 'shed.h': Plik nagłówkowy zawierający definicje struktur i funkcji związanych z planowaniem procesów i wątków.
b) 'clone()': Wywołanie systemowe, które tworzy nowy wątek lub proces.
c) 'pthread-create()': Funkcja w C, która tworzy nowy wątek.
d) 'pthread-yield()': Funkcja w C, która pozwala wątkowi dobrowolnie oddać czas CPU innemu wątkowi czekającemu na wykonanie.
e) 'sem-wait()' i 'sem-post()': Funkcje w C, synchronizacja za pomocą semaforów.
f) 'mutex-lock()' i 'mutex-unlock()': Funkcje w C, synchronizacja za pomocą mutexów.

168) Algorytmy przełączania kontekstu?
Round-Robin: Dzieli czas CPU na każdą zadanie w kolejności z ustalonym cyklem czasowym.
Oparte na priorytetach (Priority Based): Czas procesora przydzielany jest na podstawie priorytetów.
Najkrótsza kolejka zadań (Shortest Job First): Przydziela CPU do zadań na podstawie ich długości.
Gwarantowane planowanie (Guaranteed Scheduling): Zapewnia, że każde zadanie otrzymuje określoną ilość czasu CPU.

169) Konteneryzacja i jej znaczenie w wdrożeniach mikroserwisów?
Konteneryzacja to proces pakowania aplikacji oraz jej zależności, w tym bibliotek i plików konfiguracyjnych, 
w jedną, lekką jednostkę zwaną kontenerem.
Znaczenie w mikroserwisach: Spójność (Consistency), Izolacja (Isolation), Przenośność (Portability), 
Skalowalność (Scalability).

170) Czym jest orkiestracja kontenerów?
Zautomatyzowane zarządzanie aplikacjami kontenerowymi, w tym zadania takie jak wdrażanie (deployment), skalowanie, 
równoważenie obciążenia i odkrywanie usług.
Znaczenie zarządzania mikroserwisami: Wdrażanie, skalowanie, odkrywanie usług, równoważenie obciążenia, 
samonaprawa (self healing), Rolling Update.
Przykłady: Kubernetes, Docker Swarm, Amazon ECS.

171) Czym jest haszowanie konsystentne?
Typ haszowania, który minimalizuje liczbę kluczy, które muszą być przemapowane, gdy zmienia się tabela 
haszująca. Używane przez równoważnik obciążenia do rozdzielania ruchu, minimalizuje liczbę żądań, które zostaną 
przekierowane do innego serwera, jeśli istniejący serwer ulegnie awarii.

172) Koncepcja sieci usług (Service Mesh) w architekturze mikroserwisów?
Sieć usług to sieć proxy (sidecar) wdrożonych obok mikroserwisów, aby obsługiwać komunikację, monitorowanie 
i kwestie bezpieczeństwa. Działa jako płaszczyzna kontrolna (control plance) do zarządzania ruchem i egzekwowania zasad.
Poprawia komunikację, równoważenie obciążenia, routowanie i ponawianie żądań. Zwiększa obserwowalność, centralne logowanie, 
zbieranie metryk i śledzenie. Wzmacnia bezpieczeństwo poprzez funkcje takie jak uwierzytelnianie, autoryzacja i szyfrowanie.
Korzyści z sieci usług: zarządzanie ruchem, równoważenie obciążenia, circuit breaking, routowanie, obserwowalność, bezpieczeństwo.
Odporność: circuit breaking, ponawianie (retries). Egzekwowanie zasad. Przykład narzędzia: Istio.

173) Koncepcja architektury opartej na zdarzeniach (event driven) w kontekście mikroserwisów?
Architektura oparta na zdarzeniach opiera się na produkcji, wykrywaniu, konsumowaniu i reakcji na zdarzenia, 
które są dyskretnymi wystąpieniami zmiany stanu w systemie.
Luźne powiązania między usługami. Umożliwia reagowanie w czasie rzeczywistym. Wspiera skalowalność. Odporność na błędy.

174) Koncepcja rozproszonej bazy danych (distributed databases)?
Rozproszone bazy danych to bazy danych, które przechowują dane na wielu węzłach lub serwerach, często 
geograficznie rozproszonych. Każdy węzeł przechowuje część danych, a te węzły współpracują, aby zapewnić dostęp do 
danych i redundancję. Umożliwiają dostęp do danych, jednocześnie utrzymując zasadę C A P. Wspierają skalowalność 
horyzontalną. Egzekwują odporność na błędy. 
Korzyści z rozproszonych baz danych: skalowalność, wysoka dostępność, replikacja danych, zredukowane opóźnienie, 
spójność danych: przez systemy oparte na kworum lub model eventualnej spójności.
Wyzwania: złożoność, opóźnienie, kompromisy w spójności. 
Silna spójność (strong consistency) we wszystkich węzłach kosztem zwiększonego 
opóźnienia lub zmniejszonej dostępności.

175) Koncepcja konteneryzacji w kontekście architektury mikroserwisów z użyciem Kubernetes?
Konteneryzacja polega na budowaniu aplikacji oraz jej zależności w jeden, przenośny obraz kontenera. 
Kontenery działają spójnie w różnych środowiskach, od deweloperskiego do produkcyjnego. Ułatwia pakowanie i wdrażanie.
Zapewnia spójność między środowiskami deweloperskimi, testowymi i produkcyjnymi. Izoluje aplikacje, zapobiegając 
konfliktom między zależnościami.
Korzyści: przenośność, efektywność zasobów, spójność, izolacja. Kubernetes to platforma do orkiestracji kontenerów typu 
open-source, która automatyzuje wdrażanie, skalowanie i zarządzanie aplikacjami kontenerowymi. Oferuje funkcje takie jak 
równoważenie obciążenia, skalowanie, samonaprawa i aktualizacje rolling.
Wdrożenie Kubernetes: zarządzanie pożądanym stanem (desired state) aplikacji, liczba replik, alokacja zasobów, 
zasady skalowania. Skalowanie: w oparciu o wykorzystanie zasobów. Równoważenie obciążenia. Aktualizacje rolling.

176) Powszechne metody i narzędzia odkrywania usług (service discovery)?
Odkrywanie usług oparte na DNS: Netflix Eureka, Spring Cloud Netflix. 
Magazyny klucz-wartość: Consul, etcd, Zookeeper.
Bramka API lub reverse proxy: N G I N X, HAProxy, bramka API (API gateway) w chmurze. 
Proksy sidecar: Envoy, Linkerd, Istio.
Odkrywanie usług w chmurze: AWS ELB, Google Cloud Load Balancing.

177) Segregacja odpowiedzialności zapytań i poleceń (CQRS)?
Oddzielne operacje odczytu i zapisu. Lepsza wydajność w części odczytowej dzięki odpowiedniej technologii 
do odczytu, a także zapobieganie konfliktom z poleceniami aktualizacji. Możliwość skalowania każdej części osobno. 
Prowadzi do ostatecznej spójności (eventual consistency). Ostateczna spójność: Dane dotrą do magazynu odczytu z niewielkim opóźnieniem.

178) Czysta architektura?
Separacja odpowiedzialności poprzez podział oprogramowania na różne warstwy. Używanie wstrzykiwania zależności 
i polimorfizmu, aby stworzyć regułę zależności, która sprawi, że warstwa domeny będzie najbardziej niezależna i stabilna. 
Zależności kodu źródłowego mogą wskazywać tylko do wewnątrz, w kierunku warstwy domeny. Niezależna od frameworka, 
interfejsu użytkownika, bazy danych i jakiejkolwiek zewnętrznej agencji. Testowalna warstwa domeny bez potrzeby używania 
jakiegokolwiek zewnętrznego elementu. Zdefiniowanie encji i przypadków użycia dla logiki domeny. 
Architektura heksagonalna i cebulowa. Pozostawia jak najwięcej opcji otwartych przez jak najdłużej.
Wszystkie zależności wskazują na warstwę biznesową, co czyni ją niezależną i stabilną. 
Opóźnienie szczegółów implementacyjnych zależności.
Łatwiejsze testowanie logiki biznesowej poprzez tworzenie atrap zależności nawet przed podjęciem decyzji o szczegółach implementacji.
Łatwe zastępowanie implementacji adaptera bez dotykania logiki biznesowej. Niezależny rozwój i wdrażanie różnych części.

179) Bezpieczniki (Circuit Breaker) w kontekście architektury mikroserwisów, jak przyczyniają się do odporności?
Bezpieczniki to wzorzec projektowy używany w mikroserwisach do zarządzania awariami w zdalnych usługach lub 
zależnościach.
Monitorują zdrowie usługi i, gdy awarie lub przekroczenia czasowe przekraczają ustalony próg, tymczasowo „otwierają” 
obwód, zapobiegając dalszym żądaniom do awaryjnej usługi.
Zwiększają odporność, zapobiegając awariom kaskadowym. Zmniejszają obciążenie awaryjnych usług.
Zapewniają płynne ograniczenie funkcjonalności, gdy usługa jest niedostępna.
Przykłady narzędzi: Hystrix, Resilience-4j.

180) Przepływ rekompensacji w sagach opartych na choreografii?
Początkowy przepływ: Operacja utworzenia zamówienia. Operacja rezerwacji produktu. 
Transakcja rekompensacyjna: Anulowanie rezerwacji produktu. Odrzucenie zamówienia.
Początkowy przepływ: 1 - 2 - 3 - 4 - 5 - n. Transakcje rekompensacyjne: n - 5 - 4 - 3 - 2 - 1.

181) Wzorzec choreografii?
Skalowalność. Pomaga w skalowaniu złożonego przepływu operacji biznesowych. 
Architektura oparta na zdarzeniach z wykorzystaniem choreografii. Komunikacja jest asynchroniczna. 
Przykład: SAGA.

182) Czym jest przechwytywanie zmiany danych (Change Data Capture)?
Używa metod push w przeciwieństwie do metod pull. 
Wysyła rekordy bazy danych do docelowego źródła (Kafka) poprzez odczytywanie z dzienników transakcji.
Dziennik transakcji (Transaction logs): Rekordy zmian danych (wstawienie/aktualizacja/usunięcie) dokonane przez każdą transakcję. 
W Postgres dziennik transakcji nazywa się dziennikiem przedzapisowym (Write Ahead Log, W A L).
Zapewnia dane w prawie rzeczywistym czasie z bardzo niskim narzutem, słuchając dzienników transakcji i natychmiast 
wysyłając do docelowego źródła (Kafka).

183) Wyzwania i rozwiązania związane z utrzymywaniem spójności danych?
Wyzwania: Własność danych, transakcje rozproszone, synchronizacja danych, duplikacja danych.
Rozwiązania: Architektura oparta na zdarzeniach, wzorzec SAGA, pamięć podręczna. Projektowanie API, operacje idempotentne, 
wersjonowanie danych.
Poziom spójności, rozproszone śledzenie i monitorowanie, działania kompensacyjne, testowanie i inżynieria chaosu.

184) Rozwiązania C D N i technologie chmurowe?
Technologia chmurowa: Cloudflare, Fastly, Akamai, Amazon CloudFront, Google Cloud Platform CDN, Microsoft 
Azure Content Delivery Network.

185) Strategia publikacji C D N?
Strategia pull: Mniejsze utrzymanie z naszej strony. Wszystko jest obsługiwane przez dostawcę C D N.
Strategia push: Jeśli nasza zawartość nie zmienia się często, możemy ją po prostu raz przesłać do C D N.

186) Czy możesz wyjaśnić wyzwania związane z zarządzaniem danymi w architekturze mikroserwisów, strategię i wzorce?
Wyzwania: Spójność danych, własność danych, integralność danych, wzorzec dostępu do danych, objętość danych (data volume).
Strategie: Baza danych na usługę, współdzielona baza danych, przechwytywanie zdarzeń (Event Sourcing). 
CQRS: oddzielenie odpowiedzialności za polecenia (zapis) i zapytania (odczyt) w różne komponenty.
Strona poleceń (Command Side) aktualizuje dane, a strona zapytań pobiera dane. 
Kompozycja API: Usługa pobiera dane z wielu źródeł i łączy je w jedną odpowiedź przed wysłaniem do klienta.
Rozproszona pamięć podręczna Redis. Wersjonowanie danych i zgodność: zarządzanie zmianami w strukturach danych przy 
zachowaniu zgodności wstecznej dla istniejących usług. Zarządzanie danymi i własność danych.

187) Czym jest pamięć podręczna (Caching)?
Sprzęt lub oprogramowanie, które przechowuje dane. Używane do przechowywania odpowiedzi na żądania sieciowe,
ale także wyników długich obliczeń. Dane mogą być nieaktualne (Stale). Baza danych została zaktualizowana, ale funkcja nie.
Polityka usuwania: LRU (Least Recently Used - najmniej używane niedawno), LFU (Least Frequently Used - najmniej używane), 
FIFO (First In First Out - pierwszy wszedł, pierwszy wyszedł).
C D N (sieć dostarczania treści): Serwery rozmieszczone na całym świecie, co oznacza, że opóźnienia będą niskie.
Nazywane również P O P (Point of Presence - punkt obecności). Przykład: Cloudflare.

188) Logika biznesowa w Czystej architekturze?
Encje (Entities): Zawierają kluczową logikę biznesową oraz krytyczne zasady biznesowe.
Przypadki użycia: Koordynują encje i obejmują specyficzne zasady biznesowe dla aplikacji.

189) Czym jest sieć mostkowa (Bridge network)?
Tworzy sieć do łączenia z nazwami hostów.

190) Ograniczone zdarzenie w DDD (Bounded Event DDD)?
Zdarzenia domenowe: Służą do komunikacji między ograniczonymi kontekstami. Reprezentują coś, co się wydarzyło 
w domenie. Na przykład koszyk zakupowy. To zdarzenie jest wywoływane przez ograniczony kontekst procesu zamówienia i jest 
odbierane przez ograniczone konteksty wysyłki i płatności, aby zainicjować wysyłkę i fakturowanie zamówienia.

191) Testowanie granic (Boundary Testing) i jego znaczenie w testowaniu oprogramowania?
Identyfikacja defektów: Warunki graniczne to często miejsce, w którym występują defekty oprogramowania, 
takie jak przepełnienie, błędy braku pamięci lub nieoczekiwane zachowanie.
Robustność (Trwałość): Aplikacja powinna radzić sobie z ekstremalnymi danymi wejściowymi w sposób elegancki. Bezpieczeństwo.

192) Testowanie granic?
Polega na testowaniu wartości, które znajdują się na granicy lub krawędzi prawidłowych zakresów wejściowych 
lub warunków. Celem jest ujawnienie problemów, takich jak błędy "off-by-one", przepełnienie bufora i nieoczekiwane 
zachowanie w pobliżu limitów. Aby to osiągnąć: dokładność, bezpieczeństwo, jakość.

193) Testowanie black box kontra testowanie white box, kiedy stosować?
Testowanie black box: W wczesnych etapach testowania, aby zweryfikować, czy oprogramowanie spełnia swoje wymagania 
funkcjonalne.
Testowanie white box: Gdy należy zagłębić się w kod, aby zidentyfikować defekty, luki w bezpieczeństwie lub wąskie 
gardła wydajności.

194) Przykład testowania black box?
Testowanie strony logowania aplikacji internetowej bez znajomości podstawowego kodu. Testerzy wprowadzają 
różne dane wejściowe (prawidłowe i nieprawidłowe nazwy użytkowników/hasła) i obserwują, jak system reaguje.

195) Testowanie black box?
Skupia się na testowaniu funkcjonalności aplikacji oprogramowania bez znajomości jej wewnętrznego kodu czy struktury. 
Zastosowanie: Używane do weryfikacji, czy oprogramowanie spełnia określone wymagania i działa poprawnie z perspektywy 
użytkownika końcowego.
Zalety: Testerzy nie potrzebują znajomości wewnętrznego kodu, co sprawia, że jest to odpowiednie dla osób, które nie 
są deweloperami.
Wady: Może nie ujawniać niektórych typów defektów, które wymagają znajomości kodu.
Przypadki testowe mogą być redundantne, jeśli wymagania nie są dobrze udokumentowane.
Test poprzez wdrożenie mikroserwisu i sprawdzenie jego logów oraz metryk.
Zapewnia to, że wdrożenie (deployment), logowanie i monitorowanie są zgodne z określoną architekturą makro.
Taki test nazywa się testem czarnej skrzynki, który sprawdza zachowanie mikroserwisu z zewnątrz.

196) Opis algorytmu bankiera?
Algorytm używany w systemach operacyjnych do unikania zakleszczeń w systemach wieloprocesorowych.
Założenia: Proces musi zadeklarować maksymalną liczbę zasobów, które będzie potrzebował. Wszystkie zasoby są przypisane 
do procesu na początku, ale mogą być zwolnione i przypisane innym procesom. Algorytm utrzymuje aktualne listy dostępnych 
i używanych zasobów. Gdy proces chce zdobyć zasób, algorytm sprawdza, czy przydział nie doprowadzi do zakleszczenia. 
Symuluje przydział zasobów i przeprowadza kontrole.

197) Czym jest Avro?
Avro to kompaktowy, szybki, binarny format danych, który opiera się na schematach.

198) Dostępność (Availability): jak ją osiągnąć?
a) Równoważenie obciążenia (Load Balancing): rozkładanie obciążenia między wieloma serwerami.
b) Nadmiarowość (Redundancy).
c) Replikacja danych: kopiowanie danych między kilkoma serwerami.
d) Monitorowanie i powiadomienia.
e) Automatyczne przełączanie awaryjne.
f) Plan odzyskiwania po awarii: plan przywracania systemu po katastrofie.

199) Automatyczne testowanie w rozwoju oprogramowania i jego zalety?
Zalety:
a) Wydajność.
b) Spójność.
c) Testowanie regresji.
d) Pokrycie (Coverage).
e) Ciągła integracja.

200) Mikrousługa asynchroniczna?
Mikrousługa jest asynchroniczna, jeśli:
a) Nie wysyła żądania do innych mikrousług podczas przetwarzania żądań.
b) Wysyła żądanie do innych mikrousług podczas przetwarzania żądań, nie czekając na wynik.

201) Opis architektury rozproszonej?
Architektura rozproszona składa się z wielu urządzeń i maszyn połączonych za pomocą sieci. Wiele urządzeń współpracuje, 
aby wykonać pojedyncze zadanie lub zestaw zadań, dzieląc się zasobami i informacjami. Wyróżnia się różne modele, takie 
jak architektura klient-serwer, architektura peer-to-peer, architektura mikrousług oraz architektura oparta na chmurze.
Architektura rozproszona umożliwia lepszą skalowalność, niezawodność (reliability), dostępność oraz tolerancję na awarie. 
Obciążenie jest rozdzielane pomiędzy urządzenia, a zasoby mogą być współdzielone, co ułatwia zarządzanie i aktualizacje.
Jednakże wiąże się to także z wyzwaniami, takimi jak opóźnienia w sieci, błędy sieciowe, spójność danych oraz bezpieczeństwo.

202) Wersjonowanie API w architekturze mikrousług?
Wersjonowanie API to praktyka definiowania i zarządzania wieloma wersjami API, umożliwiająca klientom interakcję z 
konkretnymi wersjami oraz zapewniająca zgodność wsteczną (backward compatibility). Ułatwia wprowadzanie zmian i pozwala 
klientom wybierać wersje API. Strategie wersjonowania API to:
a) Wersjonowanie URI (URI Versioning).
b) Wersjonowanie nagłówków (Header Versioning).
c) Wersjonowanie parametrów zapytania (Query Params Versioning).
d) Wersjonowanie typu mediów (Media Type Versioning).

203) Bramy API (Definicja + Wyzwania)?
Definicja: Bramą API nazywamy centralny punkt dostępu do zarządzania i udostępniania API (Reverse Proxy).
Zalety:
a) Bezpieczeństwo.
b) Uproszczony interfejs klienta.
c) Równoważenie obciążenia.
d) Pamięć podręczna.
e) Analityka i monitorowanie.
Wyzwania:
a) Odkrywanie usług.
b) Opóźnienia.
c) Złożoność.
d) Konfiguracja zabezpieczeń.
e) Skalowalność.
f) Ograniczanie liczby żądań.
g) Monitorowanie i rejestrowanie.
h) Wersjonowanie API.
Przykład: Netflix Eureka.

204) Rozwiązania bram API i technologie chmurowe?
a) Netflix Zuul.
b) Amazon API Gateway.
c) Google Cloud Platform API Gateway.
d) Microsoft Azure API Management.

205) Przykład definicji API?
REST API przez HTTP:
a) Identyfikacja encji.
b) Mapowanie encji na URI.
c) Definiowanie reprezentacji zasobów.
d) Przypisywanie metod HTTP do operacji na zasobach.

206) Wydajność Apache Kafka?
a) Partycjonowanie: Dane są podzielone na wiele partycji.
b) Replikacja: Replikacja partycji w wielu węzłach, co zwiększa niezawodność i dostępność systemu.
c) Skalowalność: Możliwość skalowania w pionie (vertically) i poziomie (horizontally). 
Można zwiększyć liczbę węzłów w klastrze, aby zwiększyć przepustowość i szybkość przetwarzania. 
Wiele konsumentów w jednej partycji.
d) Wysoka przepustowość: Wykorzystuje pamięć podręczną, protokoły komunikacyjne o wysokiej wydajności oraz algorytmy 
przetwarzania danych. Niskie obciążenie sieci (kompresja danych). Elastyczność (integracja z innymi narzędziami).

207) Formaty danych Apache Kafka?
Apache Kafka przesyła dane w postaci wiadomości, które zazwyczaj są serializowane w jednym z kilku formatów:
a) J S O N: Czytelny, łatwy do przetwarzania, mniej wydajny niż bardziej zoptymalizowane formaty i zajmuje więcej miejsca.
b) Avro: Format serializacji danych używany w projektach big data, takich jak Apache Hadoop. Jego zaletą jest wysoka 
wydajność i mały rozmiar w porównaniu do innych formatów danych. Wadą jest większa złożoność i konieczność użycia 
większej ilości kodu do obsługi.
c) Protobuf: Format serializacji danych oferowany przez Google.
d) MessagePack: Zaprojektowany, aby być łatwym do odczytania przez ludzi i bardzo wydajnym w przetwarzaniu przez programy.
W niektórych przypadkach, takich jak Kafka Streams, można również używać innych formatów danych, takich jak serializacja
obiektów Java lub Apache Thrift.

208) Zatwierdzanie offsetu (commit offset) w Apache Kafka?
Zatwierdzanie offsetu to mechanizm potwierdzania odczytu wiadomości z partycji przez konsumentów. 
Offset to numer ostatniej odczytanej wiadomości. Potwierdzenie tego offsetu jest wysyłane do brokera i przechowywane 
w tak zwanej grupie konsumentów (Consumer Group).
Zapewnia to, że każdy konsument odczytuje wiadomość tylko raz. Dzięki temu, gdy pojawia się nowy konsument, Kafka wie, 
które wiadomości zostały już przetworzone. Istnieje możliwość automatycznego i ręcznego zatwierdzania (commit) offsetów.

209) Apache Avro?
Apache Avro to system serializacji danych, który umożliwia przesyłanie danych między różnymi językami programowania 
i systemami komputerowymi. Avro definiuje format serializacji danych w postaci schematów, które opisują strukturę danych. 
Schematy są niezależne od języka programowania.
Zastosowania:
a) Systemy rozproszone.
b) Hurtownie danych (Data Warehouses).
c) Systemy przetwarzania strumieniowego.
d) Big Data (np. Apache Hadoop, Apache Kafka, Apache Spark)
Zalety:
a) Kompaktowy format danych.
b) Niezależność od języka programowania.
c) Dostępność wielu narzędzi: bogata biblioteka w różnych językach programowania.
Krótko mówiąc, Avro to system, który umożliwia przesuwanie danych między różnymi językami programowania i platformami, 
co jest szczególnie przydatne w systemach rozproszonych i Big Data.

210) Czym jest A E S?
A E S, czyli Advanced Encryption Standard, to powszechnie stosowany standard szyfrowania, który ma trzy algorytmy klucza 
symetrycznego: AES-128, AES-192 i AES-256. Warto zauważyć, że A E S uznawany jest za „złoty standard” szyfrowania i jest 
wykorzystywany nawet przez Agencję Bezpieczeństwa Narodowego USA do szyfrowania informacji ściśle tajnych.

211) Zalety i wyzwania korzystania z architektury mikrousług w rozwoju systemów rozproszonych?
Zalety mikrousług:
a) Skalowalność.
b) Modularność.
c) Elastyczność.
d) Szybki rozwój (Rapid Development).
e) Izolacja awarii.
Wyzwania:
a) Złożoność.
b) Komunikacja między usługami.
c) Spójność danych.
d) Wdrażanie i monitorowanie (Deployment and Monitoring).
e) Testowanie.

212) Jakie są niektóre z celów brokera wiadomości (message broker)?
Tymczasowe lub trwałe przechowywanie wiadomości między producentem a konsumentem.
Odseparowanie producentów od konsumentów.
Zapewnienie możliwości buforowania w celu pochłaniania nagłych skoków ruchu.

213) Jaki jest główny cel równoważnika obciążenia w systemie?
Głównym celem równoważnika obciążenia jest rozdzielanie ruchu między grupą serwerów uruchamiających tę samą usługę.

214) Jakie są potencjalne wady równoważenia obciążenia opartego na D N S?
Ekspozycja adresów IP serwerów dla klientów.
Brak aktywnego monitorowania stanu serwerów.

215) Jaka jest kluczowa różnica między balancerami obciążenia sprzętowymi a programowymi?
Balansery obciążenia programowe działają na komputerach ogólnego przeznaczenia, podczas gdy balansery obciążenia 
sprzętowe to dedykowane urządzenia.

216) Jaka jest główna korzyść z Global Server Load Balancers (G S L B) w kontekście wydajności systemu?
G S L B zapewniają inteligentne kierowanie na podstawie różnych kryteriów, w tym lokalizacji geograficznej i obciążenia 
serwera w danym regionie.

217) Jaką rolę odgrywa Globalny Balancer Obciążenia Serwera (G S L B) w sytuacjach odzyskiwania po awarii?
Przekierowuje użytkowników do różnych lokalizacji w celu zwiększenia dostępności (higher availability).

218) Jakie jest najdokładniejsze stwierdzenie dotyczące bramy API?
Brama API przekierowuje żądania użytkowników do różnych mikroserwisów na podstawie API żądania.

219) Proszę wybrać funkcje, które typowo oferuje brama API.
a) Ograniczenie liczby żądań. 
b) Tłumaczenie protokołów.
c) Monitorowanie. 
d) Autoryzacja.

220) Jakie są korzyści z uczynienia aplikacji bezstanową?
Odpowiedź:
a) Mniejszy ślad pamięci aplikacji.
b) Możemy łatwo skalować nasz system poziomo, uruchamiając grupę identycznych instancji aplikacji za balancerem obciążenia.
c) Bezproblemowa integracja i wdrożenie z protokołem HTTP.

221) Jaki jest główny cel C D N?
Przyspieszenie dostarczania treści statycznych do użytkowników końcowych.

222) W jaki sposób C D N rozwiązuje problem opóznienia (latency), gdy użytkownik znajduje się daleko od centrum danych 
naszego systemu?
Treści statyczne są przechowywane na serwerach brzegowych CDN, które są fizycznie bliżej użytkownika lub są 
lepiej umiejscowione pod względem sieciowym.

223) W jaki sposób CDN poprawia dostępność naszego systemu?
Gdy nasze centra danych doświadczają obniżonej wydajności lub zmniejszonej pojemności sieci, staje się to 
mniej zauważalne dla użytkowników, ponieważ większość ruchu kierowana jest do C D N.

224) Nasi eksperci ds. bezpieczeństwa zostali powiadomieni, że nasz system jest pod atakiem D D O S
(Rozproszona Denial of Service).
Jakie opcje będą skutecznym środkiem w celu zmniejszenia naszej podatności na ten atak?
Odpowiedź:
a) Wprowadzenie C D N.
b) Dodanie ograniczenia liczby żądań do bramy API (API Gateway).
Wprowadzenie C D N pomoże rozłożyć atak na bardzo dużą liczbę serwerów brzegowych poza naszą organizacją. 
Dodatkowo, dodanie ograniczenia liczby żądań ograniczy ilość ruchu, jaką nasze usługi otrzymują w danym okresie, 
eliminując możliwość przeciążenia jednej z naszych usług, co mogłoby prowadzić do kaskady awarii.

225) Czym jest transakcja w bazie danych?
Zestaw operacji, które dla zewnętrznego obserwatora wydają się jedną operacją, która albo jest zatwierdzana, 
albo anulowana.

226) Co oznacza spójność (Consistency) w kontekście transakcji ACID?
Transakcja, która została już zatwierdzona, będzie widoczna dla wszystkich przyszłych zapytań / transakcji. 
Transakcja nie narusza żadnych ograniczeń, które ustaliliśmy dla naszych danych.

227) Trwałość (Durability)?
Trwałość gwarantuje, że po zakończeniu transakcji jej ostateczny stan będzie utrzymywany i pozostanie w bazie danych.

228) Stwierdzenia dotyczące baz danych SQL i No-SQL?
Bazy danych No-SQL zazwyczaj używają tych samych struktur danych co większość języków programowania, podczas 
gdy bazy danych SQL tego nie robią.
Bazy danych SQL są zoptymalizowane pod kątem efektywnego przechowywania. Bazy danych No-SQL są zoptymalizowane pod kątem 
efektywnych zapytań. Bazy danych No-SQL są łatwiejsze do skalowania poziomego (horizontal scaling) niż SQL.

229) Jakie operacje w bazach danych są zazwyczaj bardziej ograniczone w bazach NoSQL niż w bazach SQL?
Transakcje ACID i operacje łączenia (Join operations).

230) Przykład zastosowań dla bazy danych grafowej:
Przechowywanie danych o firmach, ich lokalizacjach w obrębie kodów pocztowych, miast i krajów oraz o tym, 
którzy klienci odwiedzili te firmy.

231) Jakie kompromisy podejmujemy, wprowadzając indeksowanie do bazy danych?
Uzyskujemy szybsze operacje odczytu kosztem dodatkowej przestrzeni i wolniejszych operacji wstawiania.

232) Jakie są korzyści z replikacji bazy danych?
Zapobieganie pojedynczemu punktowi awarii, wyższa dostępność, potencjalnie wyższa przepustowość zapytań.

233) Co NIE jest krokiem w uchwyceniu wymagań funkcjonalnych?
Decydowanie, który język programowania lub technologia są najlepsze w tej sytuacji.

234) API to umowa między:
Inżynierami, którzy wdrażają system, a klientami, którzy korzystają z systemu.

235) Przykłady operacji idempotentnych (idempotent operations)?
a) Usunięcie użytkownika po ID z bazy danych.
b) Zaktualizowanie numeru telefonu użytkownika na 902-3232-1111.
c) Sprawdzenie salda konta użytkownika po ID.
d) Mnożenie liczby przez 0.

236) Dlaczego operacje idempotentne są preferowane nad operacjami nieidempotentnymi?
Operacje idempotentne umożliwiają bezpieczne ponawianie (Retry). W rozproszonym systemie z zawodnymi komponentami 
komunikującymi się ze sobą przez zawodną sieć, operacje idempotentne pozwalają nam na bezpieczne ich powtarzanie bez 
obaw o negatywne skutki.

237) Przykłady scenariuszy, w których musimy używać paginacji API (API Pagination)?
a) Punkt końcowy API, który zapewnia użytkownikowi strumień wiadomości w mediach społecznościowych.
b) Punkt końcowy API, który zapewnia listę produktów w sklepie internetowym odpowiadającą słowu kluczowemu.
c) Punkt końcowy API, który zapewnia wszystkie transakcje finansowe na koncie bankowym użytkownika.

238) Jakie jest poprawne stwierdzenie porównujące R-E-S-T i R-P-C?
R-P-C wygląda i działa bardziej jak wywołanie lokalnej metody niż wywołanie API R-E-S-T. Zdalne wywołanie procedury 
(Remote Procedure Call) wykorzystuje przezroczystość lokalizacji i abstrahuje wszystkie złożoności związane z 
marshalingiem/demarshalingiem danych oraz zarządzaniem komunikacją sieciową między klientem a serwerem. 
Dlatego typowe wywołanie RPC wygląda jak proste wywołanie metody, nie różniące się od żadnej innej lokalnej metody.

239) Stwierdzenia dotyczące wywołań R-P-C w porównaniu do wywołań lokalnych metod?
Wywołania R-P-C są zazwyczaj wolniejsze niż wywołania lokalnych metod. Wywołania R-P-C są zazwyczaj mniej 
niezawodne niż wywołania lokalnych metod. Wywołania R-P-C abstrahują wywołanie (abstract away) sieciowe do zdalnej usługi, co wymaga 
zakodowania danych w formacie binarnym i oczekiwania na pakiety przemieszczające się przez sieć. Dlatego wywołania RPC 
są mniej niezawodne i wolniejsze niż wywołanie lokalnej metody.

240) Stwierdzenie dotyczące API R-E-S-T:
REST to zestaw ograniczeń architektonicznych i najlepszych praktyk do definiowania interfejsów API dla sieci.

241) Jedną z podstawowych zasad API RESTful jest bezstanowość. Jaką cechę jakościową ta zasada zapewnia naszemu systemowi?
Wysoka skalowalność. Gdy nasza aplikacja jest bezstanowa, możemy uruchamiać wiele jej instancji, co pozwala 
nam na poziome skalowanie naszego systemu.

242) Jedną z podstawowych zasad API R-E-S-T-ful jest możliwość buforowania.
Serwer musi pośrednio lub bezpośrednio zdefiniować odpowiedź jako buforowalną lub niebuferowalną przez klienta. 
W API R-E-S-T każda odpowiedź musi być oznaczona jako buforowalna (cacheable) lub niebuferowalna, aby klient wiedział, 
czy jest bezpieczne, aby ją buforować. Gdy używamy protokołu HTTP do implementacji API REST, każde zapytanie GET jest 
buforowalne domyślnie. Zapytania z użyciem metody POST nie są buforowalne domyślnie, ale mogą być takimi. 
Metody DELETE i PUT służą do usuwania i aktualizowania zasobów, odpowiednio, i nie są buforowalne.

243) Co oznacza skrót M-T-B-F?
Średni czas między awariami (Mean Time Between Failures).

244) Co oznacza skrót M-T-T-R?
Średni czas do wyzdrowienia (Mean Time To Recovery).

245) Zgodnie z definicją odporności na błędy (fault tolerance), co się dzieje podczas awarii w systemie "odpornym na błędy"?
System będzie kontynuował działanie na tym samym lub obniżonym poziomie wydajności.

246) Jakie są podstawowe zasady odporności na błędy?
a) Zapobieganie awariom (Failure Prevention). 
b) Wykrywanie awarii (Failure Detection). 
c) Odzyskiwanie po awarii (Recovery from Failure).

247) Jaki jest przykład redundancji przestrzennej w kontekście zapobiegania awariom?
Uruchamianie usługi jako grupy identycznych instancji na różnych komputerach.

248) Jaki jest przykład redundancji czasowej w kontekście odporności na błędy?
Ponowne wysyłanie nieudanych żądań wiele razy.

249) Jaką wadę ma komunikacja synchroniczna między usługami?
Może prowadzić do wysokiej latencji w przypadku operacji długotrwałych.

250) Jaki problem komunikacji synchronicznej rozwiązany jest przez asynchroniczną architekturę opartą na zdarzeniach?
Komunikacja synchroniczna wymaga, aby zarówno nadawca, jak i odbiorca utrzymywali aktywne połączenie i byli dostępni 
w tym samym czasie. Asynchroniczna architektura oparta na zdarzeniach tego nie wymaga.

251) Jaka jest różnica między architekturą wielowarstwową (multi-layer) a architekturą (multi-tier)?
Architektura multi-tier polega na fizycznym rozmieszczeniu komponentów w różnych jednostkach uruchomieniowych 
działających na różnych usługach. W architekturze wielowarstwowej kod jest zorganizowany w oddzielne jednostki logiczne 
dla lepszej modularności, ale działa jako jedna jednostka uruchomieniowa.

252) Dlaczego umieszczanie logiki biznesowej w warstwie prezentacji jest antywzorem?
Jest dostępna (i może być modyfikowana) przez użytkownika w przeglądarce internetowej. Jeśli jest dostarczana jako 
aplikacja mobilna lub desktopowa, może być również łatwo rozmontowana i poddana inżynierii wstecznej.

253) Co nie jest cechą (property) zdarzenia w architekturze sterowanej zdarzeniami?
Zdarzenia są mapowane 1 do 1 do wiersza lub rekordu w bazie danych. Zdarzenia nie muszą być mapowane do rekordów 
w bazie danych. Na przykład, recenzja użytkownika i ocena dla kursu online lub produktu mogą być przechowywane jako 
2 oddzielne rekordy przez 2 różne usługi i bazy danych.

254) Opisz korzyści płynące z wzorca CQRS w mikroserwisach.
W systemach złożoną logiką biznesową, gdzie operacje aktualizacji danych znacznie różnią się od operacji odczytu danych, 
CQRS pozwala na czystsze rozdzielenie odpowiedzialności, dzieląc część poleceń (command) systemu i część zapytań (query)
na 2 oddzielne usługi. Kiedy żądanie użytkownika wymaga połączenia danych z 2 różnych mikroserwisów, możemy użyć wzorca
CQRS do utrzymania połączonych danych w usłudze tylko do odczytu. Dzięki CQRS możemy zoptymalizować nasz system zarówno 
dla operacji odczytu, jak i zapisu, co w przeciwnym razie byłoby bardzo trudne do osiągnięcia.

255) Wzorce projektowe integracji (ntegration Design Patterns).
Wzorce projektowe integracji są kluczowe dla tworzenia solidnych i skalowalnych architektur oprogramowania, które 
umożliwiają różnym systemom, aplikacjom lub usługom bezproblemową współpracę. Wzorce te pomagają zarządzać złożonościami, 
poprawiają utrzymanie i zapewniają niezawodną komunikację między zintegrowanymi systemami. 
Oto niektóre kluczowe wzorce projektowe integracji:
a) Wzorce wiadomości:
Wiadomość: Podstawowa jednostka danych przekazywana między systemami.
Wiadomość polecenia (Command Message): Koduje polecenie do przetworzenia przez odbiorcę.
Wiadomość dokumentu (Document Message): Zawiera dokument, który ma być przetworzony przez odbiorcę.
Wiadomość zdarzenia: Powiadamia odbiorcę o tym, co się wydarzyło.
b) Kanały komunikacyjne:
Kanał punkt-punkt: Zapewnia, że tylko jeden odbiorca otrzyma określoną wiadomość.
Kanał publikacji-subskrypcji: Pozwala wielu odbiorcom odbierać wiadomości z jednego kanału.
Kanał martwych wiadomości (Dead Letter Channel): Obsługuje wiadomości, które nie mogą być przetworzone przez zamierzonego odbiorcę.
Kanał nieprawidłowych wiadomości: Radzi sobie z wiadomościami, które nie spełniają oczekiwanego formatu.
c) Routing wiadomości:
Router oparty na treści (Message Filter): Kieruje wiadomości do różnych kanałów na podstawie ich treści.
Filtr wiadomości: Usuwa pewne wiadomości z kanału na podstawie określonych kryteriów.
Lista odbiorców: Wysyła kopię wiadomości do listy odbiorców.
Splitter: Dzieli pojedynczą wiadomość na kilka mniejszych wiadomości.
Agregator: Łączy wiele wiadomości w jedną.
d) Transformacja wiadomości:
Tłumacz wiadomości: Konwertuje wiadomość z jednego formatu na inny.
Opakowanie wiadomości (Envelope Wrapper): Dodaje lub usuwa opakowanie z wiadomości.
Wzbogacacz treści (Content Enricher): Dodaje dane do wiadomości.
Filtr treści: Usuwa dane z wiadomości.
e) Punkty końcowe wiadomości:
Punkt końcowy wiadomości: Interfejs dla aplikacji do wysyłania lub odbierania wiadomości.
Konsument polujący (Pooling Consumer): Sprawdza wiadomości w regularnych odstępach czasu.
Konsument oparty na zdarzeniach: Nasłuchuje wiadomości i przetwarza je w miarę ich napływania.
Konsumenci rywalizujący (Competing Consumers): Wielu konsumentów rywalizuje o przetwarzanie wiadomości z tego samego kanału.
Dispatcher wiadomości: Kontroluje routing wiadomości do różnych punktów końcowych.
f) Wzorce integracji systemów:
Wspólna baza danych: Wiele aplikacji uzyskuje dostęp do tej samej bazy danych w celu integracji.
Zdalne wywołanie procedury (Remote Procedure Invocation  RPI): Bezpośrednie wywołania do zdalnych usług, podobnie jak 
wywoływanie metody w kodzie lokalnym.
Transfer plików (File Transfer): Wymiana danych za pomocą plików umieszczonych w udostępnionych lokalizacjach.
Wiadomości: Użycie kolejek wiadomości lub tematów do wymiany informacji w sposób asynchroniczny.
Usługi R-E-S-T-ful: Udostępnianie usług za pośrednictwem HTTP przy użyciu zasad REST.
Usługi S-O-A-P: Używanie protokołu S-O-A-P do wymiany ustrukturyzowanych informacji.
g) Wzorce Enterprise Service Bus (ESB):
Orkiestracja usług: Koordynowanie wielu usług w jedną jednolitą procedurę.
Choreografia usług: Usługi współpracujące bez centralnego koordynatora.
Proxy usługowe: Działanie jako pośrednik w zapytaniach o usługi.
Broker wiadomości: Oddziela nadawcę od odbiorcy, zarządzając routingiem i transformacją wiadomości.
h) Wzorce bezpieczeństwa i niezawodności:
Uwierzytelnianie wiadomości (Message Encryption): Zapewnienie, że nadawca wiadomości jest tym, za kogo się podaje.
Szyfrowanie wiadomości: Ochrona treści wiadomości przed nieautoryzowanym dostępem.
Gwarantowana dostawa: Zapewnienie, że wiadomość zostanie dostarczona przynajmniej raz.
Idempotentny odbiorca: Zapewnienie, że odbiorca może przetwarzać tę samą wiadomość wielokrotnie bez skutków ubocznych.
Transakcyjne wiadomości: Zapewnienie, że seria operacji wiadomości kończy się powodzeniem lub nie dzieje się wcale.
i) Wzorce przetwarzania wsadowego (Batch Processing Patterns):
Przetwarzanie wsadowe sekwencyjne: Przetwarzanie danych w dużych partiach w ustalonym czasie.
Przetwarzanie zdarzeń wsadowych: Przetwarzanie danych w odpowiedzi na określone zdarzenia.
Bulkhead: Izolowanie zasobów w systemie, aby zapobiec kaskadowym awariom.
j) Wzorce monitorowania i zarządzania:
Sprawdzanie stanu (Health Check): Regularne sprawdzanie stanu usług i komponentów.
Wyłącznik obwodu (CCircuit Breaker): Zapobieganie powtarzanym próbom dostępu do usługi, która jest znana z awarii.
Regulacja (Throttling): Kontrolowanie tempa, w jakim wywoływane są usługi, aby uniknąć przeciążenia.
Agregacja logów (Log Aggregation): Zbieranie i agregowanie logów z różnych części systemu.
Te wzorce są często używane w połączeniu, aby zaspokoić złożone wymagania integracyjne w uporządkowany i łatwy do 
utrzymania sposób. Zrozumienie i wdrażanie tych wzorców może pomóc zapewnić, że systemy są zintegrowane efektywnie 
i mogą sprostać wymaganiom nowoczesnych aplikacji rozproszonych.

256) Indeksy bazy danych.
Indeks bazy danych to struktura danych, która poprawia wydajność operacji na bazie danych, zapewniając szybki dostęp 
do konkretnych wierszy w tabeli. Indeksy są używane do optymalizacji wydajności instrukcji 'SELECT', 'UPDATE' i 'DELETE'. 
Istnieje kilka typów indeksów, które można zastosować w bazie danych, w tym:
a) Indeks B-Tree: To najczęściej stosowany typ indeksu w relacyjnych bazach danych. Jest to zrównoważona struktura 
danych w postaci drzewa, która umożliwia szybkie wyszukiwanie, wstawianie i usuwanie danych. Indeks B-Tree można używać 
zarówno dla małych, jak i dużych tabel, a także jest skuteczny dla zapytań zarówno dotyczących dokładnych dopasowań, 
jak i zakresów.
b) Indeks bitmapowy (Bitmap index): Ten typ indeksu używa bitmapy do reprezentowania obecności lub nieobecności wartości 
w określonej kolumnie. Indeksy bitmapowe są przydatne w hurtowniach danych i aplikacjach analizy biznesowej (B-I), 
gdzie zapytania często obejmują wiele warunków.
c) Indeks haszowy (Hash index): Ten typ indeksu używa funkcji haszującej do mapowania wartości w kolumnie na określoną 
lokalizację w indeksie. Indeksy haszowe są przydatne w przypadku zapytań dotyczących dokładnych dopasowań, ale nie nadają 
się do zapytań zakresowych.
d) Indeks klastrowy (Clustered index): Ten typ indeksu określa fizyczną kolejność danych w tabeli. Tabela może mieć tylko 
jeden indeks klastrowy, który zwykle jest tworzony na kluczu podstawowym tabeli.
e) Indeks nieklastrowy (Non-clustered index): Ten typ indeksu nie określa fizycznej kolejności danych w tabeli. 
Tabela może mieć wiele indeksów nieklastrowych.
Warto zauważyć, że chociaż indeksy mogą poprawić wydajność zapytań, mają także swoje koszty. Indeksy zajmują miejsce na 
dysku, muszą być aktualizowane, gdy dane w tabeli się zmieniają, i mogą spowolnić operacje modyfikacji danych. 
Dlatego ważne jest, aby wybrać odpowiedni typ indeksu i stosować je rozsądnie.

257) Filtr Bloom?
Filtr Bloom to efektywna przestrzennie probabilistyczna struktura danych, która służy do testowania, czy element jest 
członkiem zbioru, czy nie. Został wynaleziony przez Burtona Howarda Blooma w 1970 roku. Filtr Bloom to w zasadzie tablica 
bitów o rozmiarze m, z wszystkimi bitami początkowo ustawionymi na 0. Aby dodać element do filtru, stosuje się kilka 
funkcji haszujących do elementu, aby uzyskać k różnych pozycji bitowych w tablicy. Bity na tych pozycjach są następnie 
ustawiane na 1. Aby sprawdzić, czy element znajduje się w filtrze, stosuje się te same funkcje haszujące do elementu, 
a bity na otrzymanych pozycjach są sprawdzane. Jeśli wszystkie bity są 1, oznacza to, że element prawdopodobnie znajduje 
się w filtrze. Jeśli którykolwiek z bitów jest 0, oznacza to, że element na pewno nie znajduje się w filtrze.
Kluczową ideą filtra Bloom jest to, że używa się wielu funkcji haszujących do mapowania elementu na wiele pozycji w 
tablicy bitów. To pozwala na wysokie prawdopodobieństwo kolizji bitu 1, co skutkuje fałszywie pozytywnym wynikiem, ale 
także zapewnia, że kolizja bitu 0, prowadząca do fałszywie negatywnego wyniku, jest wysoce nieprawdopodobna.
Parametry filtra Bloom, takie jak rozmiar tablicy bitów, liczba funkcji haszujących i liczba elementów w filtrze, 
wpływają na współczynnik fałszywych pozytywów filtra. Współczynnik fałszywych pozytywów można kontrolować, dostosowując 
rozmiar tablicy bitów i liczbę funkcji haszujących. Filtry Bloom są używane w różnych aplikacjach, takich jak: 
a) Routery sieciowe do sprawdzania, czy pakiet był już wcześniej widziany.
b) Przeglądarki internetowe do sprawdzania, czy adres URL był już odwiedzany.
c) Systemy rozproszone do sprawdzania, czy klucz jest obecny w rozproszonej tablicy haszującej.
Filtry Bloom mają zalety w postaci efektywności przestrzennej (space-efficient) i stałej złożoności czasowej zarówno 
dla dodawania, jak i sprawdzania elementów, ale mają również wadę polegającą na istnieniu współczynnika 
fałszywych pozytywów (non-zero false positive) różnego od zera.

258) Drzewo Merkle'a?
Drzewo Merkle'a (znane również jako drzewo haszowe) to struktura danych, która jest używana do efektywnego weryfikowania 
integralności dużych ilości danych. Jest to rodzaj drzewa binarnego, w którym każdy węzeł liścia reprezentuje blok danych, 
a każdy węzeł nie-liściasty jest haszem swoich węzłów potomnych. Węzeł korzeniowy drzewa reprezentuje hash całego zbioru 
danych. Proces tworzenia drzewa Merkle'a zaczyna się od podziału zbioru danych na mniejsze bloki, a następnie obliczenia 
hashu każdego bloku.
Te hashe są następnie organizowane w drzewo binarne, gdzie każdy węzeł liścia zawiera hash bloku danych, a każdy węzeł 
nie-liściasty zawiera hash swoich węzłów potomnych. Taka struktura pozwala na bardzo efektywny sposób weryfikacji 
integralności zbioru danych, ponieważ zmiana jakiegokolwiek pojedynczego bloku danych spowoduje zmianę hashu na korzeniu drzewa.
Drzewa Merkle'a są używane w różnych aplikacjach, takich jak: 
a) Kryptowaluty: Drzewa Merkle'a są używane w technologii blockchain do zapewnienia integralności transakcji i zapobiegania 
podwójnemu wydawaniu. 
b) Systemy rozproszone: Drzewa Merkle'a są używane w systemach rozproszonych do efektywnego weryfikowania integralności 
danych przechowywanych na wielu węzłach. 
c) Systemy plików: Drzewa Merkle'a są używane w systemach plików do zapewnienia integralności dużych plików i umożliwienia 
efektywnego wykrywania zmian w pliku. 
d) Przechowywanie w chmurze: Drzewa Merkle'a są używane w chmurze do zapewnienia integralności danych przechowywanych 
na wielu serwerach i umożliwienia efektywnego wykrywania zmian w danych.
Podsumowując, drzewa Merkle'a są używane do efektywnego weryfikowania integralności dużych ilości danych; działają 
poprzez podział danych na mniejsze bloki, obliczanie hashu każdego bloku, a następnie organizowanie tych hashy w drzewo 
binarne. Węzeł korzeniowy drzewa reprezentuje hash całego zbioru danych. Ta struktura pozwala na bardzo efektywny sposób 
weryfikacji integralności zbioru danych, ponieważ zmiana w jakimkolwiek pojedynczym bloku danych spowoduje zmianę hashu 
na korzeniu drzewa.

259) Dlaczego potrzebujemy modelu pub/sub w systemach rozproszonych?
Pub-sub to wzorzec komunikacji używany w systemach rozproszonych, który oferuje wiele korzyści 
i rozwiązuje kluczowe wyzwania. Oto dlaczego pub-sub jest potrzebny w systemach rozproszonych:
a) Rozdzielenie komponentów:
Luźne powiązanie (Loose Coupling): Pub-sub rozdziela komponenty w systemie, umożliwiając publikującym i subskrybującym niezależne działanie. 
Publikujący nie muszą wiedzieć, kim są subskrybenci, a subskrybenci nie muszą wiedzieć, skąd pochodzą dane.
Skalowalność: To rozdzielenie pozwala systemowi na łatwiejszą skalowalność, ponieważ komponenty mogą być dodawane lub 
usuwane bez znaczącej rekonfiguracji.
b) Asynchroniczna komunikacja:
Operacje nieblokujące: Pub-sub umożliwia asynchroniczną komunikację, co oznacza, że publikujący mogą wysyłać wiadomości 
bez czekania na przetworzenie ich przez subskrybentów. To prowadzi do bardziej efektywnego wykorzystania zasobów i lepszej wydajności.
Architektura oparta na zdarzeniach: Wspiera architekturę opartą na zdarzeniach, w której systemy reagują na zdarzenia 
w czasie rzeczywistym, co jest kluczowe dla responsywnych i interaktywnych aplikacji.
c) Możliwość multicastu:
Transmisja wiadomości: Pub-sub pozwala na jednoczesne przesyłanie wiadomości do wielu subskrybentów, co czyni go wydajnym 
w scenariuszach, w których ta sama wiadomość musi dotrzeć do wielu komponentów.
Efektywna dystrybucja danych: To zmniejsza potrzebę wielu komunikacji punkt-punkt, oszczędzając przepustowość i redukując opóźnienia.
d) Niezawodność i odporność na błędy:
Trwałość wiadomości (Message Persistence): Wiele systemów pub-sub wspiera trwałość wiadomości, zapewniając, że wiadomości 
nie zostaną utracone, jeśli subskrybent jest tymczasowo niedostępny.
Redundancja i replikacja: Systemy pub-sub często mają wbudowane mechanizmy redundancji i replikacji, aby zapewnić wysoką 
dostępność i niezawodność.
e) Uproszczona komunikacja:
Prostsza baza kodu: Dzięki abstrahowaniu warstwy komunikacyjnej, pub-sub upraszcza bazę kodu systemów rozproszonych, 
co ułatwia ich rozwój, utrzymanie i rozszerzanie.
Standaryzowana komunikacja: Zapewnia standaryzowany sposób komunikacji między komponentami, co może uprościć integrację 
i interoperacyjność.
f) Dynamiczne i elastyczne systemy:
Dynamiczne odkrywanie: Subskrybenci mogą dynamicznie subskrybować interesujące ich tematy, a publikujący mogą dynamicznie 
publikować nowe rodzaje zdarzeń, nie wpływając na istniejących subskrybentów.
Elastyczność: Ta elastyczność pozwala systemowi dostosować się do zmieniających się wymagań i płynnie integrować nowe 
usługi lub komponenty.
g) Ulepszone zarządzanie obciążeniem:
Rozkład obciążenia: Systemy pub-sub mogą pomóc w rozkładzie obciążenia na wielu subskrybentów, balansując obciążenie 
przetwarzania i poprawiając ogólną wydajność systemu.
Ograniczanie i przeciwdziałanie obciążeniu: Często zawierają mechanizmy do obsługi przeciążenia i ograniczania, które 
pomagają w zarządzaniu nagłymi wzrostami obciążenia i zapobieganiu przeciążeniu systemu.
Przykłady w systemach rzeczywistych:
Systemy powiadamiania o zdarzeniach: Używane w usługach finansowych do aktualizacji cen akcji w czasie rzeczywistym lub 
w mediach społecznościowych do powiadomień.
Rejestrowanie i monitorowanie: Zbieranie logów i metryk z rozproszonych komponentów i wysyłanie ich do centralnego systemu 
monitorowania. 
Komunikacja w mikroserwisach: W architekturze mikroserwisów pub-sub ułatwia komunikację między usługami bez ścisłego 
powiązania, promując lepszą izolację usług i odporność.

260) Normalizacja i denormalizacja baz danych?
Normalizacja: Zacznij od normalizacji bazy danych, aby wyeliminować nadmiarowość i zapewnić integralność danych. 
Proces ten polega na organizowaniu danych w tabelach w celu zmniejszenia nadmiarowości i poprawy integralności danych.
Pierwsza forma normalna (1-NF): Upewnij się, że każda kolumna tabeli zawiera atomowe, niepodzielne wartości.
Druga forma normalna (2-NF): Wyeliminuj zależności częściowe; każda kolumna niekluczowa musi zależeć od całego klucza głównego.
Trzecia forma normalna (3-NF): Wyeliminuj zależności przechodnie; kolumny niekluczowe nie powinny zależeć od innych 
kolumn niekluczowych.
Denormalizacja: W niektórych przypadkach, aby zoptymalizować wydajność odczytu, denormalizacja może być konieczna. 
Polega to na łączeniu tabel w celu zmniejszenia liczby złączeń, kosztem zwiększonej nadmiarowości i potencjalnych 
anomalii danych.

261) Wybór odpowiedniej bazy danych?
Bazy relacyjne a No-SQL: Wybierz typ bazy danych, który najlepiej pasuje do Twojego przypadku użycia.
Bazy danych relacyjne (RDBMS): Używaj ich do danych strukturalnych z relacjami, wymagających zgodności z ACID 
(przykład: MySQL, PostgreSQL).
Bazy danych No-SQL: Używaj ich do danych niestrukturalnych lub półstrukturalnych, które wymagają skalowalności 
i elastyczności (przykład: MongoDB, Cassandra, Redis).

262) Projektowanie schematu bazy danych?
Modelowanie danych: Stwórz diagram związków encji (Entity Relationship Diagram) (E-R-D), aby zobrazować encje, 
atrybuty i relacje.
Klucze główne: Upewnij się, że każda tabela ma klucz główny, który unikalnie identyfikuje każdy rekord.
Klucze obce: Używaj kluczy obcych, aby zachować integralność referencyjną między tabelami.

263) Indeksowanie w bazach danych?
Indeksy: Używaj indeksów, aby poprawić wydajność zapytań. Twórz indeksy na kolumnach często używanych w klauzulach 
'WHERE', operacjach 'JOIN' i klauzulach 'ORDER BY'.
Indeksy złożone: Czasami może być potrzebna kombinacja kolumn do indeksowania, aby pokryć konkretne zapytania.
Unikaj nadmiernego indeksowania: Zbyt wiele indeksów może spowolnić operacje zapisu ('INSERT', 'UPDATE', 'DELETE').

264) Integralność danych w bazach danych?
Ograniczenia: Używaj ograniczeń (przykład: 'NOT NULL', 'UNIQUE', 'CHECK'), aby egzekwować zasady na poziomie bazy danych.
Transakcje: Używaj transakcji, aby zapewnić, że seria operacji albo całkowicie się powiedzie, albo całkowicie nie 
powiedzie (właściwości ACID: atomowość, spójność, izolacja, trwałość). (Atomicity, Consistency, Isolation, Durability).

265) Skalowalność baz danych?
Skalowanie pionowe: Zwiększ pojemność pojedynczego serwera (przykład: dodawanie większej ilości CPU, RAM).
Skalowanie poziome: Rozłóż obciążenie na wiele serwerów (przykład: sharding w bazach danych NoSQL, klastrowanie baz danych).

266) Kopiowanie zapasowe i odzyskiwanie w bazach danych?
Regularne kopie zapasowe: Zaplanuj regularne tworzenie kopii zapasowych, aby zapobiec utracie danych. 
Plan odzyskiwania po awarii: Miej plan na odzyskiwanie danych w przypadku awarii.

267) Bezpieczeństwo baz danych?
Uwierzytelnianie i autoryzacja: Zapewnij odpowiednie uwierzytelnianie i autoryzację użytkowników, aby kontrolować dostęp.
Szyfrowanie: Szyfruj wrażliwe dane zarówno w spoczynku, jak i w trakcie przesyłania.
Atak SQL Injection: Chroń przed atakami typu SQL injection, używając przygotowanych zapytań ( prepared statements) 
i zapytań parametryzowanych (parameterized queries).

268) Optymalizacja wydajności baz danych?
Optymalizacja zapytań: Pisanie efektywnych zapytań i korzystanie z narzędzi do optymalizacji zapytań oferowanych przez bazę danych.
Rozkład obciążenia: Rozkładaj zapytania do bazy danych, aby zbalansować obciążenie i poprawić wydajność.
Buforowanie: Wdrażaj strategie buforowania, aby zmniejszyć obciążenie bazy danych (przykład: użycie Redis lub Memcached).

269) Monitorowanie i konserwacja baz danych?
Narzędzia monitorujące: Używaj narzędzi monitorujących, aby śledzić wydajność i stan bazy danych 
(przykład: logi wolnych zapytań, pulpity wydajności).
Regularna konserwacja (Regular Maintenance): Wykonuj regularne zadania konserwacyjne, takie jak aktualizacja statystyk, 
ponowne indeksowanie i usuwanie starych danych.

270) Zgodność baz danych?
Wymagania regulacyjne: Upewnij się, że projekt bazy danych i praktyki dotyczące obsługi danych są zgodne z odpowiednimi 
przepisami i standardami (przykład: RODO, HIPAA).

271) Czy możesz wyjaśnić koncepcję OAuth?
Odpowiedź: OAuth (Open Authorization) to otwarty standard delegacji dostępu, powszechnie stosowany jako sposób na 
przyznanie stronom internetowym lub aplikacjom ograniczonego dostępu do informacji użytkownika bez ujawniania hasła 
użytkownika. OAuth umożliwia usługom trzecim wymianę tokenów dostępu na zasoby w imieniu użytkownika.
a) Kluczowe pojęcia OAuth.
Właściciel zasobu (Resource Owner): Użytkownik, który autoryzuje aplikację do dostępu do swojego konta.
Klient (Client): Aplikacja, która żąda dostępu do zasobów użytkownika.
Serwer autoryzacji (Authorization Server): Serwer, który wydaje tokeny dostępu klientowi po pomyślnym uwierzytelnieniu 
właściciela zasobu i uzyskaniu jego autoryzacji.
Serwer zasobów (Resource Server): Serwer, który hostuje chronione zasoby, zdolny do akceptacji i odpowiedzi na żądania 
chronionych zasobów przy użyciu tokenów dostępu.
Token dostępu (Access Token): Token, który klient używa do uzyskania dostępu do serwera zasobów w imieniu właściciela zasobu.
Token odświeżania (Refresh Token:): Token używany do uzyskania nowego tokena dostępu bez ponownego uwierzytelniania 
właściciela zasobu.
b) Przepływ OAuth.
Przepływ OAuth obejmuje kilka kroków, aby zapewnić bezpieczną autoryzację:
Żądanie autoryzacji (Authorization Request): Klient żąda autoryzacji od właściciela zasobu. Zwykle odbywa się to za 
pośrednictwem serwera autoryzacji, gdzie właściciel zasobu loguje się i udziela zgody klientowi.
Udzielenie autoryzacji (Authorization Grant): Właściciel zasobu udziela autoryzacji klientowi. Zwykle ma to formę kodu 
autoryzacyjnego.
Wymiana kodu autoryzacyjnego (Authorization Code Exchange): Klient wymienia kod autoryzacyjny na token dostępu na 
serwerze autoryzacji.
Żądanie tokena dostępu (Access Token Request): Klient żąda tokena dostępu od serwera autoryzacji, przedstawiając kod 
autoryzacyjny i swoje dane uwierzytelniające.
Odpowiedź z tokenem dostępu (Access Token Response): Serwer autoryzacji zwraca token dostępu (opcjonalnie token odświeżania) do klienta.
Dostęp do zasobu (Access Resource): Klient używa tokena dostępu do żądania chronionego zasobu z serwera zasobów.
c) Typy przepływów OAuth.
Grant kodu autoryzacyjnego: Używany w aplikacjach po stronie serwera, gdzie tajemnica klienta może być bezpiecznie 
przechowywana. Klient wymienia kod autoryzacyjny na token dostępu.
Kroki: Klient kieruje użytkownika do serwera autoryzacji. Użytkownik loguje się i wyraża zgodę. Serwer autoryzacji 
przekierowuje użytkownika z powrotem do klienta z kodem autoryzacyjnym. Klient wymienia kod na token dostępu.
Grant impliczny (Implicit Grant): Używany w aplikacjach po stronie klienta (np. aplikacje jednopodstronowe), 
gdzie tajemnica klienta nie może być bezpiecznie przechowywana. Token dostępu jest zwracany bezpośrednio do klienta bez 
kodu autoryzacyjnego.
Kroki: Klient kieruje użytkownika do serwera autoryzacji. Użytkownik loguje się i wyraża zgodę. Serwer autoryzacji 
przekierowuje użytkownika z powrotem do klienta z tokenem dostępu w fragmencie URL.
Grant z użyciem poświadczeń właściciela zasobu: Używany, gdy właściciel zasobu ufa klientowi z jego poświadczeniami. 
Klient bezpośrednio uzyskuje poświadczenia użytkownika i wymienia je na token dostępu.
Kroki: Klient zbiera poświadczenia użytkownika. Klient wysyła poświadczenia do serwera autoryzacji. Serwer autoryzacji 
weryfikuje poświadczenia i zwraca token dostępu.
Grant poświadczeń klienta (Client Credentials Grant): Używany do komunikacji maszynowej, gdzie nie ma zaangażowanego 
użytkownika. Klient uwierzytelnia się bezpośrednio z serwerem autoryzacji i uzyskuje token dostępu.
Kroki: Klient uwierzytelnia się na serwerze autoryzacji. Serwer autoryzacji zwraca token dostępu.
d) Korzyści z OAuth.
Bezpieczeństwo: Użytkownicy nie muszą dzielić się swoimi hasłami z aplikacjami zewnętrznymi. Tokeny dostępu mogą być 
ograniczone do konkretnych zasobów i mają ograniczone okresy ważności.
Decentralizacja: OAuth wspiera zdecentralizowane podejście, gdzie serwer autoryzacji może być oddzielony od serwera zasobów.
Skalowalność: OAuth może obsługiwać złożone scenariusze autoryzacji i może skalować się do dużych liczby użytkowników i aplikacji.
e) Typowe przypadki użycia.
Jednolity proces logowania (Single Sign-On SSO): Umożliwienie użytkownikom logowania się do wielu aplikacji za pomocą 
jednego zestawu poświadczeń.
Dostęp do API osób trzecich: Umożliwienie aplikacjom dostępu do API w imieniu użytkownika 
(np. integracja z API Google lub Facebooka).
Delegowany dostęp: Umożliwienie aplikacjom wykonywania działań w imieniu użytkownika (np. aplikacja kalendarza uzyskująca 
dostęp do danych kalendarza użytkownika).

272) Pamięć podręczna (Cache)?
Pamięć podręczna to mała i szybka pamięć, która jest używana do tymczasowego przechowywania często dostępnych danych. 
Głównym celem pamięci podręcznej jest zmniejszenie liczby razy, kiedy wolniejsza pamięć lub urządzenie magazynujące 
(takie jak dysk twardy lub zdalny serwer) muszą być dostępne.
Przechowując kopię często dostępnych danych w pamięci podręcznej, system może szybko odzyskać te dane z pamięci 
podręcznej, zamiast musieć uzyskiwać dostęp do wolniejszej pamięci lub urządzenia magazynującego.
Pamięć podręczna jest powszechną techniką stosowaną w projektowaniu systemów w celu poprawy wydajności.
Pamięci podręczne mogą być implementowane na różnych poziomach systemu, takich jak poziom sprzętowy (np. pamięć 
podręczna CPU), poziom systemu operacyjnego (np. pamięć podręczna systemu plików) oraz poziom aplikacji 
(np. pamięć podręczna aplikacji webowej).
W projektowaniu systemów ważne jest, aby rozważyć rozmiar pamięci podręcznej oraz politykę zastępowania używaną do 
decydowania, które dane powinny być usunięte z pamięci podręcznej, gdy ta staje się pełna.
Niektóre powszechne polityki zastępowania to: Least Recently Used (LRU), Most Recently Used (MRU), Least Frequently Used (LFU).
Pamięć podręczna może również obejmować rozproszone systemy pamięci podręcznej, gdzie grupa serwerów pamięci podręcznej 
współpracuje, aby przechowywać dane i rozkładać obciążenie. Może to być korzystne w rozproszonym systemie, gdzie dane 
muszą być szybko dostępne z wielu lokalizacji.
Ogólnie rzecz biorąc, użycie pamięci podręcznej w projektowaniu systemów może znacznie poprawić wydajność systemu, 
zmniejszając liczbę razy, kiedy wolne pamięci lub urządzenia magazynujące muszą być używane.

273) Rozproszona tablica haszująca (DHT)?
Rozproszona tablica haszująca (DHT) to rodzaj rozproszonego systemu, który zapewnia usługę wyszukiwania podobną do 
tablicy haszującej. Umożliwia grupie węzłów efektywne i odporne na błędy przechowywanie i pobieranie par klucz-wartość.
W DHT każdy klucz jest powiązany z unikalną wartością, a klucze są rozdzielane pomiędzy węzły w sieci w sposób, 
który umożliwia efektywne wyszukiwania. Gdy klucz jest dodawany do DHT, przypisywany jest do konkretnego węzła, 
nazywanego "następcą" (successor), który jest odpowiedzialny za przechowywanie pary klucz-wartość.
DHT są szczególnie przydatne w systemach rozproszonych, takich jak sieci peer-to-peer, gdzie węzły mogą dynamicznie 
dołączać lub opuszczać sieć. Zapewniają one sposób, w jaki węzły mogą efektywnie znaleźć węzły odpowiedzialne za 
przechowywanie konkretnego klucza bez potrzeby istnienia scentralizowanego katalogu lub usługi wyszukiwania.
Niektóre popularne DHT to Chord, Pastry i Kademlia. Są używane w różnych systemach P2P, takich jak BitTorrent, 
BitSwap i IPFS.
DHT są również wykorzystywane w rozproszonych bazach danych, przechowywaniu w chmurze, sieciach dostarczania treści 
oraz innych systemach rozproszonych, w których dane muszą być dostępne i możliwe do pobrania z wielu lokalizacji.

274) Routery?
Router to urządzenie sieciowe, które przekazuje pakiety danych między sieciami komputerowymi. Jest podłączony do co
najmniej dwóch sieci, zazwyczaj dwóch LAN-ów (sieci lokalnych) lub WAN-ów (sieci rozległych), i decyduje, którą trasą 
wysłać pakiety informacyjne na podstawie swojego aktualnego zrozumienia stanu sieci, z którymi jest połączony.
Routery używają tabel routingu, aby określić najlepszą trasę do przesyłania pakietów i mogą również wykonywać inne 
funkcje, takie jak translacja adresów sieciowych (N-A-T) i wsparcie dla V-P-N. Routery stosują protokoły, takie jak 
Protokół B-G-P (Border Gateway Protocol) i O-S-P-F (Open Shortest Path First), do komunikacji z innymi routerami i budowy mapy sieci.
Routery stosują różne algorytmy, aby określić najlepszą trasę do przesyłania pakietów. Niektóre powszechnie używane algorytmy to:
a) Algorytmy wektora odległości: Te algorytmy używają metryk, takich jak liczba skoków, aby określić najlepszą trasę. 
Przykłady to Protokół Informacji Routingu (R-I-P) i Protokół Routingowy Wewnętrznego Bramy (I-G-R-P).
b) Algorytmy stanu łączy (Link States): Te algorytmy utrzymują pełną mapę sieci, w tym stan wszystkich łączy, i używają 
tych informacji do określenia najlepszej trasy. Przykłady to OSPF i IS-IS (Intermediate System to Intermediate System).
c) Algorytmy hybrydowe (Hybrid Algorithms): Te algorytmy łączą elementy zarówno algorytmów wektora odległości, jak i 
stanu łączy. Przykłady to E-I-G-R-P (Enhanced Interior Gateway Routing Protocol).
d) Algorytmy wektora ścieżki (Path-vector): Te algorytmy opierają się na Protokole BGP i używają informacji o ścieżkach 
do określenia najlepszej trasy.
Algorytm Bellmana-Forda: Jest to algorytm wektora odległości, który służy do znajdowania najkrótszej ścieżki z 
pojedynczego węzła źródłowego do wszystkich innych węzłów w grafie.
Algorytm Dijkstry: To również algorytm wektora odległości, ale jest używany do znajdowania najkrótszej ścieżki z 
pojedynczego węzła źródłowego do wszystkich innych węzłów w grafie ważonym, gdzie krawędzie mają nieujemne wagi.
To niektóre z algorytmów stosowanych w routerach do znajdowania tras, a wybór algorytmu zależy od specyficznych wymagań 
sieci i używanego protokołu routingu.

275) Kompilator Javy?
Kompilator Javy to program, który tłumaczy kod źródłowy Javy na bajtkod Javy, który jest niezależną od platformy 
reprezentacją kodu źródłowego. Bajtkod Javy może być następnie wykonywany przez Maszynę Wirtualną Javy (J-V-M) 
na dowolnej platformie.
Podstawowe kroki, jak działa kompilator Javy, to:
a) Analiza leksykalna (Lexical analysis): Kod źródłowy Javy jest skanowany, a kompilator dzieli go na sekwencję tokenów. 
Tokeny to podstawowe elementy budujące kod źródłowy, takie jak słowa kluczowe, operatory i identyfikatory.
b) Analiza składniowa (Syntactic analysis): Kompilator buduje strukturę przypominającą drzewo, zwaną drzewem analizującym, 
z sekwencji tokenów. To drzewo analizujące reprezentuje składniową strukturę kodu źródłowego i jest używane do sprawdzania 
błędów gramatycznych w kodzie.
c) Analiza semantyczna (Semantic analysis): Kompilator przeprowadza analizę semantyczną na drzewie analizującym, 
sprawdzając błędy semantyczne, takie jak niezgodności typów i niezdefiniowane zmienne.
d) Generacja kodu pośredniego (Intermediate code generation): Kompilator generuje bajtkod Javy, który jest niezależną 
od platformy reprezentacją kodu źródłowego.
e) Optymalizacja kodu: Kompilator przeprowadza różne optymalizacje na bajtkodzie, takie jak usuwanie martwego kodu, 
składanie stałych i inlining funkcji.
f) Generacja pliku klasy (Class file generation): Kompilator generuje plik klasy, który zawiera bajtkod i metadane
wymagane przez J-V-M do wykonania kodu.
g) Linkowanie (Linking): Kompilator łączy wygenerowany plik klasy z wszelkimi wymaganymi bibliotekami i tworzy 
plik wykonywalny. Kompilator Javy jest kompilatorem wieloprzebiegowym, który przechodzi przez źródłowy kod wejściowy 
wiele razy, a przy każdym przebiegu wykonuje określone zadanie, takie jak analiza leksykalna, analiza semantyczna 
i generacja bajtkodu.

276) Kompilatory?
Kompilator to program, który tłumaczy kod źródłowy napisany w języku programowania wysokiego poziomu na kod maszynowy, 
który może być wykonywany przez komputer. Proces tłumaczenia jest znany jako kompilacja.
Podstawowe kroki, jak działa kompilator, to:
a) Analiza leksykalna (Lexical analysis): Kod źródłowy jest skanowany, a kompilator dzieli go na sekwencję tokenów. 
Tokeny to podstawowe elementy budujące kod źródłowy, takie jak słowa kluczowe, operatory i identyfikatory.
b) Analiza składniowa (Syntactic analysis): Kompilator buduje strukturę przypominającą drzewo, zwaną drzewem analizującym, 
z sekwencji tokenów. To drzewo analizujące reprezentuje składniową strukturę kodu źródłowego i jest używane do sprawdzania 
błędów gramatycznych w kodzie.
c) Analiza semantyczna (Semantic analysis): Kompilator przeprowadza analizę semantyczną na drzewie analizującym, 
sprawdzając błędy semantyczne, takie jak niezgodności typów i niezdefiniowane zmienne.
d) Generacja kodu pośredniego (Intermediate code generation): Kompilator generuje pośrednią reprezentację kodu źródłowego, 
taką jak język asemblera lub bajtkod.
e) Optymalizacja kodu (Code optimization): Kompilator przeprowadza różne optymalizacje na kodzie pośrednim, takie jak 
usuwanie martwego kodu, składanie stałych i inlining funkcji.
f) Generacja kodu maszynowego (Code generation): Kompilator generuje kod maszynowy z zoptymalizowanego kodu pośredniego.
g) Linkowanie (Linking): Kompilator łączy wygenerowany kod maszynowy z wszelkimi wymaganymi bibliotekami i tworzy plik 
wykonywalny. Niektóre kompilatory są jednoprzebiegowe, inne są wieloprzebiegowe; kompilator wieloprzebiegowy przechodzi 
przez kod źródłowy wiele razy, a przy każdym przebiegu wykonuje określone zadanie, takie jak analiza leksykalna, 
analiza semantyczna i generacja kodu.

277) MapReduce?
MapReduce to model programowania oraz powiązana implementacja do przetwarzania i generowania dużych zbiorów danych. 
Został opracowany przez Google i jest kluczowym elementem projektu Apache Hadoop.
Podstawową koncepcją MapReduce jest podzielenie dużego zbioru danych na mniejsze fragmenty, które są następnie 
przetwarzane równolegle przez wiele maszyn.
Krok "Map" stosuje funkcję określoną przez użytkownika do każdego fragmentu danych, aby przekształcić go w zbiór 
pośrednich par klucz-wartość.
Krok "Reduce" przyjmuje te pośrednie pary klucz-wartość i łączy je, aby wyprodukować mniejszy zbiór końcowych 
par klucz-wartość.
"MapReduce" jest szeroko stosowane w przemyśle do przetwarzania dużych danych, w tym do takich zadań jak eksploracja danych, 
uczenie maszynowe i przetwarzanie logów. Jest również używane do przetwarzania dużych zbiorów danych w dziedzinach 
takich jak finanse, e-commerce i media społecznościowe.
Rozproszona natura "MapReduce" sprawia, że jest to popularny wybór do przetwarzania dużych zbiorów danych, które nie mogą 
być obsługiwane przez pojedynczą maszynę.

278) Problem z pętlą for?
"Problem z pętlą for" to termin używany do opisania konkretnego rodzaju problemu z wydajnością, który może wystąpić przy 
użyciu pętli for w języku programowania, takim jak Java.
Problem pojawia się, gdy pętla iteruje po dużej liczbie elementów, takich jak duża tablica lub kolekcja, i wykonuje 
znaczną ilość obliczeń lub operacji "I/O" na każdym elemencie. W miarę wzrostu liczby elementów całkowity czas spędzony w 
pętli rośnie liniowo, co prowadzi do słabej wydajności i długiego oczekiwania na zakończenie operacji.
Może to być szczególnie problematyczne w środowiskach współbieżnych lub wielowątkowych, gdzie wiele wątków może być 
zablokowanych, czekając na zakończenie pętli.
Istnieje kilka sposobów na rozwiązanie problemu z pętlą for:
a) Równoległe przetwarzanie pętli: Używając konstrukcji pętli for w trybie równoległym lub interfejsu API strumieni 
równoległych ("parallel streams"), możesz podzielić pracę pętli na wiele wątków lub procesorów, co może znacznie 
przyspieszyć jej działanie.
b) Optymalizacja pętli: Optymalizując operacje wykonywane w pętli, takie jak zmniejszenie liczby wywołań funkcji czy 
minimalizowanie alokacji pamięci, możesz skrócić czas spędzony w pętli.
c) Użycie iteratora: Zamiast używać pętli for, możesz użyć iteratora, który pozwala na dostęp do elementów pojedynczo, 
zamiast ładowania wszystkich elementów do pamięci naraz.
d) Użycie bazy danych lub zewnętrznego magazynu: Jeśli dane są bardzo duże, może być bardziej wydajne przechowywanie 
ich w bazie danych lub systemie magazynowania zewnętrznego, a następnie pobieranie ich w mniejszych kawałkach przy użyciu 
kursora bazy danych lub paginacji.
e) Użycie specjalizowanej biblioteki: Istnieją biblioteki zaprojektowane specjalnie do obsługi dużych zbiorów danych 
i wykonywania na nich operacji w bardziej efektywny sposób.
f) Warto zauważyć, że "problem z pętlą for" nie zawsze jest problemem i może być całkowicie w porządku w przypadku 
małych zbiorów danych lub operacji, które nie są krytyczne pod względem wydajności.
g) Jednak w przypadku dużych zbiorów danych lub operacji, które muszą być wykonane szybko, istotne może być rozważenie 
alternatywnych rozwiązań, aby uniknąć słabej wydajności.

279) Zestawy cech ("Feature Sets")?
Zestaw cech to zbiór cech lub zmiennych, które są używane jako dane wejściowe dla modelu uczenia maszynowego. 
Cechy ("features") te są wybierane, ponieważ uważa się, że są istotne dla rozwiązywanego problemu, i służą do 
reprezentowania danych w sposób, który model może zrozumieć i na którym może się uczyć.
Na przykład w modelu uczenia maszynowego, który jest używany do przewidywania ceny domu, zestaw cech może obejmować 
zmienne takie jak liczba sypialni, powierzchnia domu, wiek domu oraz sąsiedztwo, w którym się znajduje.
Zestaw cech jest jednym z najważniejszych aspektów budowania modelu uczenia maszynowego, ponieważ jakość i istotność 
cech mogą znacząco wpłynąć na wydajność modelu. Wybór odpowiednich cech dla modelu może być trudnym zadaniem i często 
wymaga wiedzy z danej dziedziny oraz eksperymentowania.
Zestawy cech można sklasyfikować na dwa typy:
a) Cecha numeryczna ("Numeric Feature"): To wartości numeryczne, które mogą być używane jako dane wejściowe dla modelu, 
takie jak wiek, wynagrodzenie itp.
b) Cecha kategoryczna ("Categorical Feature"): To wartości nienumeryczne, takie jak imię, kolor itp.
Dodatkowo istnieją inne typy zestawów cech, takie jak:
a) Cecha binarna: Przyjmuje tylko dwie wartości (0 lub 1).
b) "One-Hot Encoding": Używane do reprezentowania zmiennej kategorycznej z n poziomami jako n zmiennych binarnych.
c) Cecha wielomianowa: Używana do dodania nieliniowości do modelu.
Tworząc zestaw cech, ważne jest również rozważenie rozmiaru zestawu cech, ponieważ włączenie zbyt wielu cech może 
prowadzić do przeuczenia ("overfitting"), w którym model dobrze radzi sobie z danymi treningowymi, ale słabo z nowymi, 
nieznanymi danymi.

280) "Load Balancers"?
"Load balancers" to urządzenia sieciowe, które rozdzielają przychodzący ruch sieciowy pomiędzy wieloma serwerami. Działają, 
kierując żądania klientów do różnych serwerów na podstawie zestawu reguł lub algorytmów. Celem jest równomierne rozłożenie 
obciążenia na serwerach i zapewnienie, że żaden pojedynczy serwer nie zostanie przytłoczony zbyt dużą liczbą żądań.
Oto ogólny przegląd działania load balancera:
a) Klient wysyła żądanie do adresu IP lub nazwy hosta load balancera.
b) Load balancer odbiera żądanie i używa algorytmu do określenia, który serwer powinien obsłużyć to żądanie.
c) Algorytm może opierać się na aktualnym obciążeniu serwera, liczbie aktywnych połączeń lub lokalizacji geograficznej klienta.
d) Load balancer kieruje żądanie do wybranego serwera.
e) Serwer przetwarza żądanie i wysyła odpowiedź z powrotem do load balancera.
f) Load balancer przekazuje odpowiedź z powrotem do klienta.
Istnieje kilka typów load balancerów i algorytmów, które można wykorzystać, takich jak:
a) "Round-robin": rozdziela żądania do serwerów w sposób okrężny, wysyłając pierwsze żądanie do pierwszego serwera, 
drugie do drugiego serwera itd.
b) Najmniej połączeń ("Least connections"): rozdziela żądania do serwera z najmniejszą liczbą aktywnych połączeń.
c) Hash IP: rozdziela żądania do serwerów na podstawie wartości haszującej adresu IP klienta.
d) Najmniejszy czas odpowiedzi ("Least response time"): rozdziela żądania do serwera z najniższym czasem odpowiedzi.
e) Load Balancing na poziomie 7 ("Layer 7 Load Balancing"): rozdziela żądania na podstawie danych warstwy aplikacji, 
takich jak URI lub nazwa hosta.
f) Load balancing DNS ("DNS Load balancing"): rozdziela żądania na podstawie rozwiązywania DNS, może być używany do 
globalnego load balancingu. Load balancery mogą również oferować inne funkcje, takie jak odciążenie SSL, caching, 
kompresję i kontrole stanu, aby zapewnić, że tylko zdrowe serwery otrzymują ruch.
Istnieje kilka typów load balancerów, w tym:
a) Load balancery sprzętowe: To fizyczne urządzenia instalowane lokalnie. Zwykle są droższe od load balancerów programowych, 
ale oferują lepszą wydajność i bardziej zaawansowane funkcje. Przykłady to "F5 BIG-IP" i "Cisco ACE".
b) Load balancery programowe: To load balancery działające na oprogramowaniu, które można zainstalować na serwerze lub 
w chmurze. Zwykle są tańsze od load balancerów sprzętowych, ale mogą nie oferować tej samej wydajności lub funkcji. 
Przykłady to "HAProxy", "NGINX" i "Amazon Elastic Load Balancer".
c) Load balancery w chmurze: Te load balancery są oferowane przez dostawców chmury i są zazwyczaj łatwe do skonfigurowania 
i zarządzania. Przykłady to "Amazon Elastic Load Balancer", "Google Cloud Load Balancer" i "Microsoft Azure Load Balancer".

281) Serwery nazw domen?
Serwer nazw domen ("DNS") to system, który tłumaczy przyjazne dla użytkownika nazwy domen, takie jak "www.example.com", 
na adresy IP, których komputery używają do lokalizowania się nawzajem w Internecie. Serwery DNS działają, utrzymując 
bazę danych nazw domen i ich odpowiadających adresów IP. Kiedy klient, na przykład przeglądarka internetowa, potrzebuje 
przetłumaczyć nazwę domeny na adres IP, wysyła zapytanie do serwera DNS. Serwer "DNS" następnie wyszukuje adres IP 
skojarzony z nazwą domeny w swojej bazie danych i zwraca go do klienta.
Serwery "DNS" są zorganizowane w hierarchiczną strukturę, gdzie różne poziomy serwerów odpowiadają za różne poziomy systemu 
nazw domen. Na najwyższym poziomie znajdują się serwery główne (root), które utrzymują listę domen najwyższego poziomu ("TLD"), 
takich jak ".com", ".org" i ".edu". Poniżej serwerów głównych znajdują się serwery "TLD", które odpowiadają za utrzymanie listy 
nazw domen w określonym "TLD". Te serwery są również odpowiedzialne za podanie adresu IP autorytatywnego serwera DNS dla 
konkretnej nazwy domeny.
Na najniższym poziomie znajdują się autorytatywne serwery DNS ("authoritative DNS servers"), które są odpowiedzialne za 
utrzymanie rekordów dla konkretnej nazwy domeny i podanie adresów IP związanych z tą nazwą domeny. Kiedy klient wysyła 
zapytanie o przetłumaczenie nazwy domeny, zapytanie jest wysyłane do serwerów głównych, które następnie kierują zapytanie 
do serwerów "TLD", a następnie do autorytatywnego serwera DNS dla konkretnej nazwy domeny.
Serwery "DNS" z pamięcią podręczną są również używane do przyspieszenia procesu rozwiązywania, przechowując ostatnio 
wyszukiwane nazwy domen i ich odpowiadające adresy IP. W ten sposób, jeśli klient ponownie poprosi o tę samą nazwę domeny, 
serwer "DNS" z pamięcią podręczną może zwrócić adres IP bez konieczności ponownego zapytania autorytatywnego serwera "DNS".
Ważne jest, aby zauważyć, że serwery "DNS" mają również rolę w zapewnieniu bezpieczeństwa, upewniając się, że zapytania 
pochodzą z autoryzowanych źródeł, oraz oferując dodatkowe funkcje bezpieczeństwa, takie jak "DNSSEC", które zapewniają, 
że odpowiedź z serwera DNS jest autentyczna i nie została zmieniona.
Istnieje kilka poleceń systemu Linux, które mogą być użyte do rozwiązywania problemów z DNS:
a) "nslookup": To polecenie pozwala na zapytanie serwerów DNS i uzyskanie informacji o nazwie domeny. Na przykład, 
"nslookup www.example.com" zwróci adres IP skojarzony z nazwą domeny.
b) "dig": To polecenie jest podobne do "nslookup" i pozwala na zapytanie serwerów DNS oraz uzyskanie informacji o nazwie domeny. 
Na przykład, "dig www.example.com" zwróci adres IP skojarzony z nazwą domeny.
c) "host": To polecenie jest podobne do "nslookup" i "dig", pozwala na zapytanie serwerów DNS i uzyskanie informacji o 
nazwie domeny. Na przykład, "host www.example.com" zwróci adres IP skojarzony z nazwą domeny.
d) "ping": To polecenie pozwala na przetestowanie dostępności hosta, wysyłając pakiety żądań "ICMP" i mierząc czas odpowiedzi. 
Na przykład, "ping www.example.com" przetestuje dostępność hosta skojarzonego z nazwą domeny.
e) "traceroute": To polecenie pokazuje ścieżkę, jaką pakiet pokonuje, aby dotrzeć do celu. Jest użyteczne w rozwiązywaniu 
problemów z trasowaniem. Na przykład, "traceroute www.example.com" pokaże ścieżkę, jaką pakiet pokonuje, aby dotrzeć do 
hosta skojarzonego z nazwą domeny.
f) "nsupdate": To polecenie pozwala na dynamiczną aktualizację rekordów "DNS", co jest przydatne w przypadkach, gdy musisz 
zaktualizować adres IP skojarzony z nazwą domeny.
g) "resolvectl": To polecenie jest użyteczne do sprawdzania konfiguracji "DNS" i statusu w systemie Linux. Na przykład, 
"resolvectl query example.com" pokaże serwer DNS, który system używa do rozwiązywania nazwy domeny.
Ważne jest, aby zauważyć, że te polecenia to tylko mały zbiór dostępnych poleceń, a konkretne opcje mogą się różnić w 
zależności od dystrybucji systemu Linux. Dobrą praktyką jest również sprawdzenie plików dziennika systemu, aby uzyskać 
dodatkowe informacje na temat tego, co się dzieje; pliki dziennika zwykle znajdują się w katalogu "/var/log/", a do ich 
przeglądania możesz użyć poleceń takich jak "tail -f /var/log/messages", aby na bieżąco obserwować pliki dziennika.

282) Zapory sieciowe ("Firewalls")?
Zapora sieciowa to urządzenie zabezpieczające, które kontroluje przychodzący i wychodzący ruch sieciowy na podstawie 
zestawu reguł i innych kryteriów. Zapory sieciowe są zazwyczaj wdrażane na bramie do sieci i mają na celu zablokowanie 
nieautoryzowanego dostępu, jednocześnie pozwalając na autoryzowaną komunikację.
Istnieje kilka różnych typów zapór sieciowych, w tym zapory sieciowe, zapory na poziomie hosta i zapory aplikacyjne.
a) Zapory sieciowe ("Network firewalls") to zazwyczaj urządzenia sprzętowe, które znajdują się na krawędzi sieci i 
kontrolują ruch na podstawie adresów IP i portów.
b) Zapory na poziomie hosta ("Host-based firewalls") to aplikacje software'owe, które działają na pojedynczych hostach 
i kontrolują ruch na podstawie aplikacji generującej lub odbierającej ten ruch.
c) Zapory aplikacyjne ("Application-based firewalls") to specjalistyczne zapory, które są zaprojektowane do kontrolowania 
ruchu dla określonych aplikacji, takich jak serwer WWW lub serwer bazy danych.
Aby skonfigurować zaporę, zazwyczaj będziesz musiał uzyskać dostęp do interfejsu zarządzania zaporą, który może być 
interfejsem opartym na przeglądarce internetowej, interfejsem wiersza poleceń lub kombinacją obu.
Z poziomu interfejsu zarządzania możesz tworzyć i edytować reguły zapory, przeglądać logi oraz wykonywać inne zadania.
Specyficzne kroki związane z konfiguracją zapory będą zależały od typu zapory i dostawcy, który ją udostępnił, ale ogólne 
kroki obejmują:
a) Określenie roli i zakresu zapory (np. zapora zewnętrzna ("perimeter firewall"), zapora wewnętrzna ("internal firewall")).
b) Konfigurowanie interfejsów i stref
c) Tworzenie reguł i polityk kontrolujących ruch
d) Włączenie logowania i monitorowania zdarzeń zabezpieczeń
Ważne jest również regularne przeglądanie i aktualizowanie konfiguracji zapory, aby zapewnić jej zgodność z politykami 
bezpieczeństwa organizacji oraz aby zająć się wszelkimi zagrożeniami bezpieczeństwa, które mogą się pojawić.
W systemie Linux konfiguracja zapory jest zazwyczaj przechowywana w pliku konfiguracyjnym "iptables", który zazwyczaj 
znajduje się w "/etc/sysconfig/iptables" lub "/etc/iptables/iptables.rules".
Ten plik zawiera reguły, które są ładowane do zapory "iptables" podczas uruchamiania systemu.
Innym sposobem na zachowanie reguł zapory jest użycie usługi, która ładuje reguły "iptables" przy uruchamianiu, 
takiej jak "iptables-persistent" (dla Ubuntu i Debiana) lub "firewalld" (dla Red Hat i CentOS), która zapewnia wygodny 
sposób zarządzania regułami zapory i automatycznego ładowania reguł przy starcie.
W przypadku "firewalld" reguły zapory są przechowywane w plikach XML w katalogu "/usr/lib/firewalld/zones/", a konfiguracja 
aktywnej strefy może być znaleziona w "/etc/firewalld/zone/".
Ważne jest, aby zauważyć, że różne dystrybucje systemu Linux mogą przechowywać konfigurację zapory w różnych lokalizacjach, 
dlatego zawsze warto zapoznać się z dokumentacją dla konkretnej dystrybucji, aby określić lokalizację pliku konfiguracyjnego iptables.

283) "VPN"?
Wirtualna sieć prywatna ("VPN") to technologia, która pozwala użytkownikom na bezpieczne łączenie się z prywatną siecią 
przez Internet. Tworzy ona bezpieczny, szyfrowany "tunel" między urządzeniem użytkownika a serwerem "VPN", co chroni dane 
podczas ich przesyłania przez publiczną sieć. Gdy użytkownik łączy się z "VPN", urządzenie użytkownika nawiązuje połączenie 
z serwerem VPN. Serwer "VPN" następnie uwierzytelnia ("authenticates") użytkownika i przypisuje urządzeniu nowy adres IP 
z puli adresów IP serwera "VPN".
Po nawiązaniu połączenia cały ruch internetowy użytkownika jest kierowany przez serwer "VPN", który działa jako proxy dla 
żądań internetowych użytkownika. Ponieważ serwer "VPN" szyfruje dane przed ich opuszczeniem urządzenia użytkownika i 
deszyfruje je po dotarciu do serwera "VPN", każdy, kto przechwyci dane w tranzycie (tak jak haker czy agencja rządowa), 
zobaczy tylko zaszyfrowane dane i nie będzie mógł odczytać informacji użytkownika.
"VPN" umożliwia także użytkownikom dostęp do treści, które są zablokowane w ich lokalizacji geograficznej, przypisując 
nowy adres "IP" z innej lokalizacji.
Istnieją dwa główne typy "VPN":
a) "VPN" dostępu zdalnego: pozwala pojedynczym użytkownikom na bezpieczne łączenie się z prywatną siecią z odległej lokalizacji.
b) "VPN" typu "site-to-site": pozwala wielu zdalnym lokalizacjom na bezpieczne łączenie się z centralną prywatną siecią.

284) Multi-core, nowoczesne ograniczenia współbieżności ("modern concurrency constraints")?
Nowoczesne komputery mają wiele rdzeni, co pozwala im na jednoczesne wykonywanie wielu zadań. Jest to znane jako 
współbieżność ("concurrency"). Jednak współbieżność może również wprowadzać nowe wyzwania i ograniczenia, które należy 
rozwiązać, aby w pełni wykorzystać wiele rdzeni.
a) Wyścig danych ("Data Race"): Gdy wiele wątków jednocześnie uzyskuje dostęp do tych samych danych współdzielonych i je 
modyfikuje bez odpowiedniej synchronizacji, może to prowadzić do nieoczekiwanych i niespójnych wyników.
b) Martwy punkt ("Deadlock"): Gdy dwa lub więcej wątków jest zablokowanych, czekając na uwolnienie zasobu przez siebie 
nawzajem, może to prowadzić do martwego punktu. Martwe punkty mogą spowodować, że program będzie się zawieszał w 
nieskończoność i mogą być trudne do wykrycia i zdiagnozowania.
c) Głodzenie ("Starvation"): Gdy wątek nie może uzyskać dostępu do współdzielonego zasobu, ponieważ inne wątki go zajmują, 
może to prowadzić do głodzenia. Głodzenie może spowodować, że wątek będzie zablokowany w nieskończoność, co prowadzi do 
słabej wydajności.
d) Odwrócenie priorytetu ("Priority Inversion"): Gdy wątek o wysokim priorytecie jest zablokowany przez wątek o niskim 
priorytecie, może to prowadzić do odwrócenia priorytetu. Odwrócenie priorytetu może spowodować, że wątek o wysokim 
priorytecie będzie zablokowany w nieskończoność, co prowadzi do słabej wydajności.
e) Spójność pamięci ("Memory Consistency"): Przy wielu rdzeniach możliwe jest, że różne rdzenie mogą mieć różne widoki 
na pamięć. Może to prowadzić do nieoczekiwanego zachowania i może być trudne do debugowania.
Aby poradzić sobie z tymi ograniczeniami, stosuje się różne konstrukcje synchronizacji, takie jak blokady, semafory i 
monitory, aby zapewnić bezpieczny i przewidywalny dostęp do współdzielonych zasobów.
Dodatkowo, języki programowania i frameworki, takie jak Java i ".NET", oferują wbudowane wsparcie dla wielowątkowości i 
współbieżności, co ułatwia programistom pisanie kodu współbieżnego.
Warto zauważyć, że choć współbieżność może poprawić wydajność, nie zawsze jest najlepszym rozwiązaniem i ważne jest 
rozważenie kompromisów między współbieżnością ("concurrency") a wydajnością ("performance").

285) Przestrzeń wymiany ("Swap Space")?
"Swap Space" to część pamięci masowej komputera (takiej jak dysk twardy lub SSD), która jest używana jako pamięć 
wirtualna, gdy fizyczna pamięć RAM (Random Access Memory) jest w pełni wykorzystana. Działa jako obszar przechowywania, 
w którym system operacyjny może tymczasowo przechowywać dane, które nie są aktualnie używane przez aktywne procesy, 
zwalniając pamięć RAM dla innych zadań.
Oto kilka kluczowych punktów dotyczących "Swap Space":
a) Rozszerzenie pamięci wirtualnej: "Swap Space" rozszerza dostępną pamięć poza fizyczną pamięć RAM, co pozwala 
systemowi na obsługę większej liczby aplikacji lub większych zbiorów danych jednocześnie.
b) Wpływ na wydajność: Choć "Swap Space" może pomóc zapobiec awariom systemu z powodu niedoboru pamięci, dostęp do 
danych z "Swap Space" jest znacznie wolniejszy niż dostęp do pamięci RAM, ponieważ prędkości odczytu/zapisu na 
dysku są znacznie niższe niż prędkości RAM.
c) Rodzaje "Swap Space":
Partycja wymiany ("Swap Partition"): Dedykowana partycja na urządzeniu pamięci masowej, specjalnie zarezerwowana dla "Swap Space".
Plik wymiany ("Swap File"): Zwykły plik w systemie plików, który pełni tę samą funkcję co partycja wymiany.
d) Zarządzanie przez system operacyjny: Większość nowoczesnych systemów operacyjnych, takich jak Linux, Windows i macOS, 
może automatycznie zarządzać "Swap Space", ale użytkownicy mogą również konfigurować jej rozmiar i lokalizację.
e) Scenariusze użycia: "Swap Space" jest szczególnie przydatna w systemach z ograniczoną pamięcią RAM lub w 
scenariuszach, gdzie użycie pamięci może sporadycznie wzrosnąć, takich jak podczas złożonych obliczeń lub uruchamiania 
aplikacji wymagających dużej ilości pamięci.
f) Swappiness: W systemach Linux parametr "swappiness" kontroluje tendencję jądra do przenoszenia danych z RAM do 
przestrzeni wymiany. Niższa wartość oznacza, że system będzie wolał przechowywać dane w RAM, podczas gdy wyższa wartość 
oznacza, że szybciej będzie używać "Swap Space".
Podsumowując, "Swap Space" jest kluczowym elementem nowoczesnych systemów operacyjnych, który zwiększa możliwości 
zarządzania pamięcią, oferując obszar zapasowy dla danych, gdy pamięć RAM jest niewystarczająca. Jednak poleganie na 
przestrzeni wymiany może wpływać na wydajność systemu z powodu wolniejszego charakteru przechowywania opartego na dysku 
w porównaniu do RAM.

286) Pamięć podręczna ("Memory Cache")?
Pamięć podręczna, znana również jako pamięć "cache", to niewielka, szybka pamięć, która jest używana do tymczasowego 
przechowywania danych, które są często dostępne przez procesor centralny (CPU) komputera. Celem pamięci "cache" jest 
przyspieszenie czasu dostępu do często używanych danych, poprzez zmniejszenie potrzeby dostępu do pamięci głównej lub 
urządzeń magazynujących.
Oto kilka kluczowych koncepcji związanych z pamięcią podręczną:
a) "Cache Hierarchy": Pamięć cache jest zazwyczaj zorganizowana w hierarchiczną strukturę, z wieloma poziomami pamięci 
podręcznej, z których każdy ma różny rozmiar i prędkość. Najniższy poziom pamięci "cache" to "cache L1", który jest 
najmniejszy i najszybszy, następnie jest "cache L2", i tak dalej.
b) "Cache Replacement Policy" Pamięć "cache" jest zazwyczaj wdrażana z wykorzystaniem polityki wymiany, która określa, 
które dane powinny zostać usunięte z pamięci "cache", gdy staje się ona pełna. Najbardziej powszechne polityki wymiany 
"cache" to "LRU", która usuwa dane, które były używane najrzadziej, oraz polityka "FIFO" , która usuwa dane, które zostały 
dodane do cache jako pierwsze.
c) "Cache miss": "Cache miss" występuje, gdy CPU żąda danych, które nie są obecne w pamięci "cache". Może się to zdarzyć, 
gdy dane zostały zastąpione przez wcześniej używane dane, lub gdy dane są dostępne po raz pierwszy.
d) "Cache hit": "Cache hit" występuje, gdy CPU żąda danych, które są obecne w pamięci "cache". To prowadzi do szybszego dostępu 
do danych, ponieważ CPU nie musi dostępu do pamięci głównej ani urządzenia magazynującego.
Pamięci "cache" są szeroko stosowane w wielu branżach, takich jak:
a) Przemysł komputerowy: Pamięci "cache" są używane w procesorach i systemach komputerowych, aby przyspieszyć czas dostępu 
do często używanych danych. Na przykład, procesory Intel mają wbudowaną pamięć "cache L1" i "cache L2", aby przyspieszyć 
dostęp do danych.
b) Przemysł pamięci masowej ("Storage Industry"): Pamięci "cache" są używane w urządzeniach magazynujących, takich jak 
dyski twarde i dyski SSD, aby przyspieszyć czas dostępu do często używanych danych. Na przykład, wiele dysków SSD ma 
wbudowaną pamięć "cache", aby przyspieszyć czas dostępu do często używanych danych.
c) Przemysł sieciowy ("Network Industry"): Pamięci "cache" są używane w sprzęcie sieciowym, takim jak routery i przełączniki, 
aby przyspieszyć czas dostępu do często używanych danych. Na przykład, wiele routerów używa pamięci "cache" 
do przechowywania ostatnio odwiedzanych stron internetowych, co zmniejsza potrzebę wielokrotnego żądania tej samej 
strony internetowej.
d) Przemysł internetowy ("Web Industry"): Pamięci "cache" są używane w serwerach internetowych i aplikacjach internetowych, 
aby przyspieszyć czas dostępu do często używanych danych. Na przykład, wiele serwerów internetowych stosuje mechanizmy 
"cache" do przechowywania ostatnio odwiedzanych stron internetowych, co zmniejsza potrzebę wielokrotnego generowania tej 
samej strony internetowej. 
Ogólnie rzecz biorąc, pamięci "cache" odgrywają kluczową rolę w poprawie wydajności systemów, poprzez zmniejszenie czasu 
dostępu do często używanych danych. Ważne jest, aby rozważyć kompromis między rozmiarem pamięci "cache" a wydajnością; 
większa pamięć "cache" może przechowywać więcej danych, ale może również zużywać więcej zasobów.

287) Harmonogramowanie ("Scheduling")?
"Scheduling" w systemie operacyjnym odnosi się do sposobu, w jaki system operacyjny przydziela czas CPU różnym 
procesom. Istnieje kilka powszechnie używanych algorytmów "Scheduling", w tym:
a) "First-Come, First-Served (FCFS)": Procesy są wykonywane w kolejności, w jakiej przybywają do kolejki gotowych procesów.
b) "Shortest Job First (SJF)": Procesy są wykonywane w kolejności ich czasu wykonania (czas, jaki potrzebują do wykonania).
c) "Priority Scheduling": Procesy są wykonywane na podstawie poziomu ich priorytetu, przy czym procesy o 
wyższym priorytecie są wykonywane przed tymi o niższym.
d) "Round Robin (RR)": Procesy są wykonywane w sposób cykliczny, z każdym procesem otrzymującym ustalony przydział czasu 
(kwant "quantum"), zanim zostanie przeniesiony na koniec kolejki.
e) "Multilevel Queue": Procesy są dzielone na różne kolejki na podstawie pewnych kryteriów (np. priorytet, typ), 
a różne algorytmy harmonogramowania są stosowane do każdej kolejki.
f) "Multilevel Feedback Queue": Jest to rozszerzenie algorytmu harmonogramowania kolejki wielopoziomowej, 
w którym procesy mogą przemieszczać się między kolejkami.

288) Jakie zasoby są potrzebne procesowi i wątkowi?
Zasób to każdy element lub zdolność, z której korzysta system komputerowy, taki jak pamięć, przestrzeń dyskowa czy 
przepustowość sieci ("network bandwidth"). Proces to instancja programu, który jest wykonywany przez system komputerowy. 
Wątki to sposób, w jaki proces może podzielić się na wiele jednostek wykonawczych. Każdy wątek działa w swoim kontekście 
i dzieli zasoby procesu, w tym pamięć i otwarte pliki. Proces może tworzyć wiele wątków, aby jednocześnie wykonywać różne 
zadania. Podsumowując, proces potrzebuje zasobów, takich jak pamięć i przestrzeń dyskowa, aby się wykonać, podczas gdy 
wątki w procesie dzielą te zasoby i mogą jednocześnie wykonywać wiele zadań.

289) "Livelock" i jak go unikać?
"Livelock" to sytuacja, w której dwa lub więcej wątków aktywnie próbują zdobyć zasób, ale żaden z nich nie może wygrać, 
ponieważ zasób jest nieustannie zdobywany i zwalniany przez inne wątki. To prowadzi do tego, że wątki ciągle zmieniają 
swoje stany w odpowiedzi na stany innych wątków, ale nie ma postępu.
Oto kilka sposobów, aby unikać "Livelocku":
a) Priorytetyzacja zasobów: Ustalenie priorytetów dla zasobów może sprawić, że wątek będzie czekać na zasób o wyższym 
priorytecie, zamiast nieustannie próbować zdobyć zasób o niższym priorytecie, który jest ciągle zdobywany i zwalniany przez inne wątki.
b) Użycie limitu czasowego ("Timeout"): Można ustawić limit czasowy na zdobycie zasobu, aby jeśli wątek nie może zdobyć 
zasobu w określonym czasie, mógł przejść do innych zadań.
c) "Preempting a resource": Wątek może mieć pozwolenie na tymczasowe przejęcie kontroli nad zasobem, nawet jeśli inny wątek go 
aktualnie używa, aby przerwać "Livelock".
d) "Breaking symmetry": Wprowadzenie losowości w procesie zdobywania zasobów może przełamać symetrię, pozwalając jednemu 
wątkowi na postęp, podczas gdy inne są zablokowane.
e) "Using a signaling mechanism": Można użyć mechanizmu sygnalizacji, aby informować inne wątki, gdy zasób jest dostępny, 
aby nie musiały nieustannie sprawdzać jego dostępności.
f) "Resource hierarchy": Podobnie jak w przypadku hierarchii blokad, stosowanie hierarchii zasobów może pomóc uniknąć 
"Livelock", zapewniając, że wątek zawsze zdobywa zasoby w konsekwentnej kolejności.
g) "Deadlock Detection": Podobnie jak w przypadku "deadlock", można stosować algorytmy wykrywania "Livelock", 
aby zidentyfikować "Livelock" i podjąć odpowiednie działania w celu ich rozwiązania.
Warto zauważyć, że, podobnie jak w przypadku martwych punktów, całkowite unikanie "Livelock" może nie być możliwe, 
ale można je złagodzić, stosując powyższe techniki.

290) Zakleszczenie (Deadlock) i jak go unikać?
Istnieje kilka sposobów, aby unikać zakleszczenia w wielowątkowości:
a) Unikanie zagnieżdżonych blokad: Zagnieżdżone blokady mogą prowadzić do martwych punktów ("Deadlock"), jeśli kolejność 
blokowania nie jest spójna we wszystkich wątkach.
b) Użycie limitów czasowych ("Timeout") dla blokad: Ustawienie limitu czasowego na zdobycie blokady może pomóc w zapobieganiu 
zakleszczeniom, pozwalając wątkowi przejść dalej, jeśli nie uda mu się zdobyć blokady.
c) Użycie hierarchii blokad: Ustanowienie hierarchii dla blokad może pomóc w zapobieganiu zakleszczeniu, zapewniając, 
że wątek zawsze zdobywa blokady w spójnej kolejności.
d) "Avoiding circular wait": Zakleszczenia mogą wystąpić, gdy dwa lub więcej wątków czeka na siebie nawzajem, 
aby zwolnić zasób. Aby tego uniknąć, upewnij się, że wątek nigdy nie czeka na zasób, który jest trzymany przez inny wątek, 
który również czeka na zasób trzymany przez pierwszy wątek.
e) "Using a global lock": Jeśli system ma wiele blokad, użycie globalnej blokady może pomóc w zapobieganiu "Deadlock", 
zapewniając, że wszystkie wątki zdobywają globalną blokadę przed próbą zdobycia innych blokad.
f) Użycie "non-blocking algorithm": Niektóre algorytmy, takie jak algorytmy "lock-free", wolne od oczekiwania i porównywania 
i wymiany, mogą pomóc unikać zakleszczeń, umożliwiając wielu wątkom jednoczesny dostęp do współdzielonych zasobów.
g) Użycie algorytmu wykrywania zakleszczeń ("deadlock detection algorithm"): Algorytmy wykrywania martwych punktów mogą 
być używane do identyfikacji zakleszczeń i podjęcia odpowiednich działań w celu ich rozwiązania.
Warto zauważyć, że całkowite unikanie zakleszczeń nie zawsze jest możliwe, ale można je złagodzić, stosując powyższe techniki.

291) Drzewa B ("B-trees")?
Drzewo B (B-tree) to rodzaj zrównoważonej struktury danych w postaci drzewa, która jest szeroko stosowana w informatyce 
do indeksowania i przechowywania dużych ilości danych.
Litera "B" w Drzewie B oznacza zrównoważone ("Balanced"), co odnosi się do faktu, że drzewo jest zaprojektowane tak, 
aby utrzymywać równowagę między liczbą elementów w swoich poddrzewach, aby zminimalizować wysokość drzewa.
Drzewa B są zazwyczaj używane w bazach danych, systemach plików i innych aplikacjach, które muszą przechowywać i szybko 
pobierać dużą ilość danych.
Jedną z głównych zalet Drzew B jest to, że umożliwiają one szybkie wyszukiwanie, wstawianie i usuwanie elementów, 
nawet gdy liczba elementów w drzewie jest bardzo duża.
Drzewo B składa się z zestawu węzłów, z których każdy zawiera szereg kluczy i powiązanych wartości.
Klucze w Drzewie B są używane do organizowania elementów w drzewie w sposób, który umożliwia szybkie wyszukiwanie i przeszukiwanie.
Każdy węzeł zawiera również zestaw wskaźników do innych węzłów, które są używane do nawigacji po drzewie.
Struktura Drzewa B jest zaprojektowana tak, aby utrzymywać jak najniższą wysokość drzewa, co pomaga zapewnić szybkie 
wykonanie operacji na drzewie.
Osiąga się to poprzez zapewnienie, że każdy węzeł zawiera dużą liczbę kluczy i umożliwienie tworzenia dodatkowych węzłów, 
gdy liczba kluczy w węźle przekroczy określony próg.
Drzewa B są szeroko stosowane w systemach baz danych i systemach plików, takich jak "NTFS", "ext3", "ext4" i wielu innych.
Są również wykorzystywane w różnych bazach danych "NoSQL", takich jak "Mongo DB", "Cassandra" i "HBase".
Drzewa B są również przydatne w algorytmach pamięci zewnętrznej, gdzie rozmiar danych jest zbyt duży, aby zmieścić się 
w pamięci wewnętrznej. Drzewa B są używane do przechowywania danych w pamięci zewnętrznej i wczytywania ich do pamięci 
wewnętrznej w razie potrzeby.

292) Drzewa AVL ("AVL Trees")?
Drzewo AVL to samobalansujące się drzewo binarne wyszukiwania.
Jest to wariant bardziej powszechnego drzewa binarnego wyszukiwania, ale jest bardziej zbalansowane, co oznacza, że 
wysokość drzewa jest zawsze utrzymywana jak najmniejsza.
Drzewa AVL utrzymują równowagę, egzekwując następującą zasadę:
Dla każdego węzła w drzewie wysokość jego lewego i prawego poddrzewa może różnić się maksymalnie o jeden.
To osiąga się poprzez wykonywanie rotacji na drzewie, gdy węzeł staje się niezbalansowany.
W drzewie AVL istnieją cztery rodzaje rotacji:
Rotacja lewo-lewa ("LL Rotation").
Rotacja prawo-prawa ("RR Rotation").
Rotacja lewo-prawa ("LR Rotation").
Rotacja prawo-lewa ("RL Rotation").

293) Drzewa Czerwono-Czarne ("Red-Black Tree")?
Drzewo Czerwono-Czarne to rodzaj samobalansującego się drzewa wyszukiwania binarnego, w którym każdy węzeł ma dodatkowy 
bit reprezentujący jego kolor: czerwony lub czarny. Drzewo zostało zaprojektowane w taki sposób, aby utrzymywać równowagę, 
co zapewnia, że wysokość drzewa zawsze wynosi "O(log(n))'" gdzie n to liczba węzłów w drzewie.
Równowagę osiąga się poprzez egzekwowanie zestawu właściwości, znanych jako właściwości czerwono-czarne:
Każdy węzeł jest albo czerwony, albo czarny. Korzeń jest czarny. Każdy liść ("NIL") jest czarny.
Jeśli węzeł jest czerwony, oboje jego dzieci muszą być czarne.
Dla każdego węzła, wszystkie proste ścieżki od węzła do jego potomnych liści zawierają tę samą liczbę czarnych węzłów.
Główną zaletą drzewa Czerwono-Czarnego jest jego zdolność do utrzymywania wysokości drzewa w równowadze, co skutkuje 
szybszymi operacjami wyszukiwania, wstawiania i usuwania.
"Collection Framework" w Javie.
"java.util.TreeMap": Ta klasa implementuje interfejs SortedMap, który przechowuje swoje elementy w drzewie czerwono-czarnym. 
Elementy są uporządkowane według ich naturalnego porządku lub według dostarczonego w momencie tworzenia komparatora. 
Klasa ta zapewnia gwarantowaną złożoność czasową
"O(log(n))" dla podstawowych operacji, takich jak get, put i remove.
"java.util.concurrent.ConcurrentSkipListMap": Ta klasa jest implementacją interfejsu "ConcurrentNavigableMap" i przechowuje 
swoje elementy w liście przeskokowej. Lista przeskokowa to struktura danych podobna do drzewa balansującego, która 
zapewnia gwarantowaną złożoność czasową  "O(log(n))" dla podstawowych operacji, takich jak get, put i remove.
"java.util.concurrent.ConcurrentHashMap": Ta klasa jest implementacją interfejsu "ConcurrentMap" i używa techniki zwanej 
stripe'owaniem blokad, aby osiągnąć wysoką współbieżność. Używa struktury danych w postaci drzewa balansującego, znanej 
jako drzewo czerwono-czarne, do przechowywania elementów, gdy rozmiar mapy rośnie.
Warto zauważyć, że rzeczywista implementacja tych klas może się różnić między różnymi wersjami API Javy i innymi 
bibliotekami, ale podstawowe zasady i algorytmy pozostają niezmienne. 

294) Różne poziomy testowania (testy jednostkowe, integracyjne, systemowe i akceptacyjne)?
a) Testy Jednostkowe.
Definicja: Testy jednostkowe polegają na testowaniu pojedynczych komponentów lub funkcji oprogramowania w izolacji. 
Celem jest weryfikacja, czy każda jednostka oprogramowania działa zgodnie z oczekiwaniami.
Kluczowe Punkty:
Zakres: Najmniejsze testowalne części aplikacji, takie jak funkcje, metody czy klasy.
Wykonują: Programiści podczas fazy rozwoju.
Narzędzia: "J Unit", "N Unit", "py test" itp.
Korzyści: Wczesne wychwytywanie błędów, uproszczenie debugowania, zapewnienie poprawności kodu, ułatwienie refaktoryzacji kodu.
Przykład: Testowanie funkcji, która oblicza sumę dwóch liczb, aby upewnić się, że zwraca poprawny wynik.
b) Testy Integracyjne.
Definicja: Testy integracyjne koncentrują się na weryfikacji interakcji i przepływu danych między zintegrowanymi 
jednostkami lub komponentami. Zapewniają, że połączone części aplikacji działają zgodnie z zamierzeniem.
Kluczowe Punkty:
Zakres: Grupy jednostek lub modułów, które są zintegrowane w celu wykonania zestawu funkcji.
Wykonują: Programiści lub testerzy po testach jednostkowych.
Podejścia: Od góry do dołu, od dołu do góry, hybrydowe, big bang.
Narzędzia: "J Unit", "N Unit", "pytest" (do testów integracyjnych) lub specjalistyczne narzędzia do testowania integracji, 
takie jak Postman do testowania API.
Korzyści: Identyfikacja problemów z interfejsami, interakcjami i zależnościami między modułami.
Przykład: Testowanie interakcji między modułem uwierzytelniania użytkowników a modułem bazy danych.
c) Testy Systemowe.
Definicja: Testy systemowe polegają na testowaniu kompletnej i zintegrowanej aplikacji oprogramowania w celu oceny 
zgodności systemu z określonymi wymaganiami. Wykonywane są w środowisku, które jak najbliżej odwzorowuje produkcję.
Kluczowe Punkty:
Zakres: Cały system, w tym scenariusze "end-to-end".
Wykonują: Niezależny zespół testerski.
Rodzaje: Testy funkcjonalne, testy wydajnościowe, testy bezpieczeństwa, testy użyteczności itp.
Narzędzia: "Selenium", "LoadRunner", "J Meter" itp.
Korzyści: Weryfikacja funkcjonalności, wydajności i niezawodności całego systemu.
Przykład: Testowanie aplikacji e-commerce w celu zapewnienia prawidłowego przetwarzania rejestracji użytkowników, 
wyszukiwania produktów i transakcji.
d) Testy Akceptacyjne.
Definicja: Testy akceptacyjne przeprowadza się, aby określić, czy system spełnia wymagania biznesowe i jest gotowy do 
wdrożenia. Często są one ostatnim poziomem testowania przed wprowadzeniem oprogramowania na rynek.
Kluczowe Punkty:
Zakres: Cały system, z naciskiem na wymagania biznesowe i potrzeby użytkowników.
Wykonują: Użytkownicy końcowi, klienci lub zespół QA w imieniu użytkowników.
Rodzaje: Testy akceptacyjne użytkowników ("U A T"), testy akceptacyjne operacyjne ("O A T"), testy akceptacyjne umowy.
Narzędzia: Mogą używać tych samych narzędzi co testy systemowe, wraz z dedykowanymi narzędziami "U A T", takimi jak "TestRail".
Korzyści: Zapewnienie, że oprogramowanie spełnia potrzeby biznesowe, redukcja ryzyka problemów po wydaniu oraz weryfikacja 
satysfakcji użytkowników.
Przykład: Użytkownicy końcowi testują nową funkcję w systemie CRM, aby upewnić się, że spełnia ich wymagania dotyczące 
przepływu pracy.
Podsumowanie.
Testy Jednostkowe: Testują pojedyncze komponenty pod kątem poprawności.
Testy Integracyjne: Testują interakcje między zintegrowanymi jednostkami.
Testy Systemowe: Testują cały system pod kątem ogólnej funkcjonalności i wydajności.
Testy Akceptacyjne: Weryfikują system w kontekście wymagań biznesowych i oczekiwań użytkowników.
Każdy poziom testowania odgrywa kluczową rolę w zapewnieniu jakości i niezawodności oprogramowania, co ostatecznie 
prowadzi do solidnego ("robust") i przyjaznego ("user-friendly") dla użytkownika produktu.

295) Różne fazy cyklu życia oprogramowania (SDLC)?
a) Planowanie.
Cel: Zdefiniowanie zakresu projektu, celów, zasobów, harmonogramu i wykonalności.
Wynik: Plan projektu oraz analiza wykonalności.
b) Analiza Wymagań.
Cel: Zbieranie i analiza wymagań biznesowych oraz użytkowników.
Wynik: Dokument specyfikacji wymagań (wymagania funkcjonalne i niefunkcjonalne).
c) Projektowanie.
Cel: Stworzenie architektury i projektu oprogramowania.
Wynik: Architektura systemu, szczegółowe dokumenty projektowe oraz prototypy.
d) Implementacja (Kodowanie).
Cel: Rozwój i kodowanie oprogramowania na podstawie specyfikacji projektowych.
Wynik: Kod źródłowy oraz wykonane komponenty oprogramowania.
e) Testowanie.
Cel: Weryfikacja, że oprogramowanie działa zgodnie z zamierzeniem i spełnia wymagania.
Wynik: Plany testów, przypadki testowe, raporty o błędach oraz zweryfikowane oprogramowanie.
f) Wdrożenie.
Cel: Instalacja i konfiguracja oprogramowania w środowisku produkcyjnym.
Wynik: Wdrożone oprogramowanie oraz dokumentacja wdrożeniowa.
g) Utrzymanie.
Cel: Zapewnienie bieżącego wsparcia oraz wprowadzanie niezbędnych aktualizacji lub ulepszeń.
Wynik: Zaktualizowane oprogramowanie, dzienniki utrzymania oraz dokumentacja wsparcia ("support documentation").
Te fazy zapewniają systematyczne podejście do rozwoju oprogramowania, poprawiając jakość i niezawodność końcowego produktu.

296) Kluczowe zasady ciągłej integracji/ciągłego wdrażania (CI/CD)?
Ciągła integracja (CI):
a) "Frequent Commits": Programiści integrują kod do wspólnego repozytorium wielokrotnie w ciągu dnia.
b) Automatyczne budowy: Każdy commit uruchamia automatyczną budowę, aby upewnić się, że kod kompiluje się i działa poprawnie.
c) Automatyczne testowanie: Automatyczne testy są uruchamiane przy każdej budowie, aby wykryć wady na wczesnym etapie.
d) "Immediate Feedback": Programiści otrzymują natychmiastową informację zwrotną na temat swojego kodu, 
co umożliwia szybkie identyfikowanie i rozwiązywanie problemów.
e) "Consistent Environment": Użycie spójnych środowisk dla rozwoju, testowania i produkcji, aby uniknąć problemów z konfiguracją.
Ciągłe wdrażanie (CD):
a) Automatyczne wdrażanie: Narzędzia automatyzacji wdrażają aplikację do produkcji bez interwencji człowieka.
b) "Continuous Delivery": Kod jest zawsze w stanie gotowym do wdrożenia, a aktualizacje są automatycznie dostarczane 
do użytkowników.
c) Mechanizmy wycofywania: Możliwość szybkiego wycofania zmian, jeśli coś pójdzie nie tak w produkcji.
d) Monitorowanie i logowanie: Ciągłe monitorowanie i logowanie, aby śledzić wydajność aplikacji i wykrywać problemy 
w czasie rzeczywistym.
e) "Incremental Updates": Wdrażanie małych, inkrementalnych zmian, aby zredukować ryzyko i uprościć rozwiązywanie problemów.
Te zasady pomagają uprościć proces rozwoju, zapewnić wysoką jakość kodu oraz przyspieszyć dostarczanie oprogramowania 
do użytkowników.

297) Czym jest cykl życia rozwoju oprogramowania (SDLC)?
Cykl życia rozwoju oprogramowania (SDLC) to efektywny kosztowo i czasowo proces, który zespoły deweloperskie wykorzystują 
do projektowania i budowania oprogramowania wysokiej jakości. Etapy SDLC obejmują:
a) Zbieranie wymagań i planowanie: Określenie celów projektu, zasobów i harmonogramu.
b) Projektowanie: Tworzenie architektury systemu oraz szczegółowych specyfikacji.
c) Rozwój: Kodowanie i rozwijanie oprogramowania zgodnie z wymaganiami i projektem.
d) Testowanie: Weryfikacja, czy oprogramowanie działa zgodnie z oczekiwaniami i spełnia wymagania.
e) Wdrażanie: Instalacja i konfiguracja oprogramowania w środowisku produkcyjnym.
f) Utrzymanie: Zapewnienie wsparcia oraz wprowadzanie niezbędnych aktualizacji i poprawek.
Te etapy zapewniają uporządkowane podejście do rozwoju oprogramowania, co prowadzi do zwiększenia jakości i niezawodności 
finalnego produktu.

298) Wyjaśnienie różnych typów złączeń SQL ("SQL JOINS")?
Złączenia SQL służą do łączenia wierszy z dwóch lub więcej tabel na podstawie pokrewnej kolumny:
a) "INNER JOIN": Zwraca tylko te wiersze, w których istnieje dopasowanie w obu tabelach na podstawie określonego warunku.
b) "LEFT JOIN" (lub "LEFT OUTER JOIN"): Zwraca wszystkie wiersze z tabeli po lewej stronie oraz odpowiadające im wiersze z 
tabeli po prawej stronie. Jeśli nie ma dopasowania, w kolumnach tabeli po prawej stronie zwracane są wartości "NULL".
c) "RIGHT JOIN" (lub "RIGHT OUTER JOIN"): Zwraca wszystkie wiersze z tabeli po prawej stronie oraz odpowiadające im wiersze 
z tabeli po lewej stronie. Jeśli nie ma dopasowania, w kolumnach tabeli po lewej stronie zwracane są wartości "NULL".
d) "FULL JOIN" (lub "FULL OUTER JOIN"): Zwraca wszystkie wiersze, gdy istnieje dopasowanie w tabeli po lewej lub prawej stronie. 
Jeśli nie ma dopasowania, w kolumnach tabeli bez dopasowania zwracane są wartości "NULL".
Te złączenia pozwalają na elastyczne łączenie danych z różnych tabel w bazach danych, co umożliwia bardziej złożone 
zapytania i analizy.

299) Czym są klucz główny i klucz obcy w SQL?
Klucz główny ("primary key"): Klucz główny to kolumna (lub zestaw kolumn) w tabeli, która unikalnie identyfikuje każdy 
rekord w tej tabeli. Zapewnia, że żadne dwa wiersze nie mają tej samej wartości w tej kolumnie, co utrzymuje integralność danych. 
Tabela może mieć tylko jeden klucz główny, który może składać się z jednej lub wielu kolumn.
Klucz obcy ("foreign key"): Klucz obcy to kolumna (lub zestaw kolumn) w jednej tabeli, która odnosi się do klucza głównego 
w innej tabeli. 
Umożliwia to ustanowienie relacji między dwiema tabelami, a także egzekwowanie integralności referencyjnej. 
Tabela może mieć wiele kluczy obcych, a klucz obcy może odnosić się do klucza głównego tej samej tabeli 
(odniesienie do samej siebie) lub innej tabeli.
Klucze główne i obce są niezbędne do utrzymania struktury i integralności relacyjnych baz danych, umożliwiając złożone 
zapytania i relacje między danymi. 

300) Jaka jest różnica między bazami danych SQL a "No SQL"?
Bazy danych SQL:
Przykłady: "My SQL", "PostgreSQL", "Oracle".
Typ: Bazy danych relacyjne.
Struktura: Używają strukturalnego schematu i organizują dane w tabelach z wierszami i kolumnami.
Skalowanie: Zwykle skalują się pionowo, dodając więcej zasobów do jednego serwera.
Zgodność z ACID: Przestrzegają właściwości ACID (Atomowość, Spójność, Izolacja, Trwałość), co zapewnia niezawodność 
i spójność transakcji.
Bazy danych "No SQL":
Przykłady: "MongoDB", "Cassandra", "Couch DB", "Redis".
Typ: Bazy danych nierelacyjne.
Struktura: Mogą przechowywać dane nieustrukturyzowane lub półstrukturalne i nie wymagają stałego schematu. Dane mogą 
być przechowywane w różnych formatach, takich jak pary klucz-wartość, dokumenty i grafy.
Skalowanie: Zaprojektowane do skalowania poziomego, dodając więcej serwerów do bazy danych.
Dostępność a Spójność: Często poświęcają ścisłą spójność na rzecz dostępności i tolerancji na podziały, przestrzegając 
modelu "A P".
Podsumowując, bazy danych SQL są strukturalne i niezawodne, podczas gdy bazy danych NoSQL oferują elastyczność i 
skalowalność dla różnorodnych typów danych.

301) Jakie są różnice między "git fetch" a "git pull"? 
"git fetch" służy do pobrania najnowszych zmian z zdalnego repozytorium bez łączenia ich z lokalnymi zmianami. 
"git pull" jest używane do pobrania najnowszych zmian z zdalnego repozytorium i natychmiastowego ich połączenia. 
Jest to kombinacja dwóch poleceń: "git fetch" i "git merge".

302) Jak działa biblioteka RestAssured? Podaj przykład. 
RestAssured to popularna biblioteka w Javie, która została zaprojektowana w celu uproszczenia testowania interfejsów 
API REST. Oferuje styl zorientowany na zachowanie do konstruowania kosztownych i czytelnych testów API.

303) Czym jest ciało żądania / ładunek ("payload") w API? 
Ciało żądania, znane również jako ładunek ("payload"), to część żądania API, w której dane są wysyłane z klienta do serwera. 
Najczęściej używane formaty to "JSON" i "XML".

304) Jaka jest różnica między parametrem ścieżki (" path parameter") a parametrem zapytania ("query parameter")? 
Parametr ścieżki jest częścią samej ścieżki URL. Parametr zapytania to dane w postaci pary klucz-wartość, które można 
dołączyć do naszego żądania, aby przekazać dane do serwera. Są one dołączane na końcu URL po znaku '?' i są oddzielane znakiem '&'.

305) Czym są parametry zapytania w API? 
Parametry zapytania to struktura danych w postaci par klucz-wartość, które dołączamy do naszego żądania, aby przekazać 
dane do serwera. Są one dołączane na końcu URL po znaku '?' i oddzielane znakiem '&'.

306) Jak działa autoryzacja w API?
a) Podstawowa autoryzacja ("Basic Authentication"): Klient dołącza nazwę użytkownika i hasło w nagłówku żądania, 
używając nagłówka "Authorization", a dane logowania są kodowane w formacie "base64".
b) Autoryzacja oparta na tokenach ("Token-based Authorization"): Użytkownicy uwierzytelniają się w serwerze, aby uzyskać 
token. Token jest następnie dołączany do nagłówków żądania podczas kolejnych wywołań API. Na przykład token typu "Bearer" 
i OAuth ("Open Authorization").
c) Autoryzacja oparta na certyfikatach ("Certificate-based Authentication"): Klienci przedstawiają certyfikat cyfrowy 
podczas procesu autoryzacji. Serwer weryfikuje certyfikat, aby zapewnić tożsamość klienta.

307) Jakie kody statusu odpowiedzi znasz? 
a) "100": Odpowiedź informacyjna. Odpowiedzi informacyjne są używane do wskazania, że zadanie jest w toku.
b) "200": Sukces.
c) "300": Przekierowanie.
d) "400": Błąd klienta.
e) "500": Błąd serwera. Ważne żądanie zostało złożone przez klienta, ale serwer nie zdołał ukończyć zadania.
Na przykład: 200 OK, 201 "Created", 404 "Not Found", 403 "Forbidden".

308) Jaka jest różnica między PUT a PATCH?
PUT oznacza zastąpienie całego zasobu danymi, które zostały podane, podczas gdy PATCH oznacza zastąpienie tylko określonych pól.

309) Jakie metody API znasz?
a) Metoda GET: odczytuje dane.
b) Metoda POST: zapisuje dane.
c) Metoda PUT: edytuje dane.
d) Metoda DELETE: usuwa dane.
e) Metoda PATCH: może być używana do częściowej aktualizacji określonych danych.
f) Metoda OPTIONS: żąda informacji z punktu końcowego ("endpoint").
g) Metoda HEAD: pobiera tylko nagłówek odpowiedzi bez treści odpowiedzi.
h) Metoda CONNECT: nawiązuje połączenie sieciowe z zasobem, zazwyczaj używane do tunelowania "SSL/TLS".
i) Metoda TRACE: serwer internetowy odpowiada na żądania wykorzystujące metodę TRACE, nasłuchując w odpowiedziach dokładne 
żądanie, które zostało odebrane w celach diagnostycznych.

310) Jaka jest różnica między "SOAP" a "REST" API? 
SOAP ("Simple Object Access Protocol") i REST ("Representational State Transfer") to dwa różne style architektoniczne 
służące do projektowania usług internetowych.
a) SOAP: używa XML i formatu wiadomości, podczas gdy REST może używać wielu formatów, ale najczęściej stosowanym jest JSON.
b) REST jest z założenia bezstanowy, podczas gdy SOAP może być stanowy lub bezstanowy, jednak często polega na sesji i 
utrzymuje stan. 
SOAP może być bardziej odpowiedni w scenariuszach, gdzie surowe standardy i bezpieczeństwo są kluczowe, podczas gdy REST 
jest często preferowany ze względu na swoją prostotę, elastyczność i efektywność w aplikacjach internetowych i mobilnych.

311) Jak przeprowadzasz testowanie API?
a) Analizuj dokumentację API.
b) Wyślij żądanie.
c) Waliduj odpowiedź: zweryfikuj kod statusu, zweryfikuj treść odpowiedzi (jeśli dotyczy).

312) Czym są HTTP i HTTPS? 
HTTP ("Hyper Text Transfer Protocol") i HTTPS ("Hyper Text Transfer Protocol Secure") to protokoły używane do komunikacji 
między klientem (takim jak przeglądarka internetowa) a serwerem przez internet. Określają, jak dane są przesyłane i 
formato-wane podczas wymiany informacji. Kluczowe różnice między HTTP a HTTPS dotyczą bezpieczeństwa. HTTPS dodaje 
warstwę szyfrowania, co czyni go bardziej bezpiecznym.

313) Czym jest API? 
API to skrót od "Application Programming Interface" (interfejs programowania aplikacji). Jest to most między warstwą danych 
a klientem aplikacji. API definiują również, jak różne komponenty oprogramowania powinny ze sobą współdziałać, 
umożliwiając im komunikację. REST ("Representational State Transfer") i SOAP ("Simple Object Access Protocol") to powszechne 
style architektoniczne webowych API.

314) Czym jest wzorzec projektowy singleton i dlaczego warto go używać w swoim frameworku? 
Singleton to kreacyjny wzorzec projektowy, który pozwala zapewnić, że klasa ma tylko jedną instancję, jednocześnie 
udostępniając globalny punkt dostępu do tej instancji. Potrzebujemy singletona, aby mieć jedną instancję Drivera podczas 
wykonywania.

315) Jakie rodzaje raportów generujesz z swojego frameworku? 
Mój framework generuje raporty "maven-cucumber-reporting" (raporty Cucumber).

316) Jak zarządzasz danymi testowymi w swoim frameworku? 
Większość moich danych testowych istnieje w scenariuszach Cucumber. Jeśli dane testowe nie nadają się do scenariusza 
Cucumber, przechowuję je w folderze zasobów. W niektórych przypadkach używam zewnętrznego przechowywania, takiego jak kosze S3.

317) Jak twój kod działa jako część "CI" i jaka jest twoja strategia "CI"? 
Mamy dwa różne zautomatyzowane zestawy testowe, które uruchamiamy jako część procesu "CI". Pierwszy to zestaw smoke. 
Utrzymujemy mały zbiór krytycznych testów, które uruchamiamy przy każdej wersji. W środowisku deweloperskim i przy każdej 
zmianie, aby upewnić się, że nowe zmiany nie złamią podstawowych funkcji i zapewnić szybki feedback. Ta praca w "Jenkinsie" 
jest częścią pipeline'u deweloperskiego i jest uruchamiana po wdrożeniu środowiska deweloperskiego. Drugi to zestaw 
regresyjny z wszystkimi naszymi zautomatyzowanymi testami. Uruchamiamy ten zestaw w "Jenkinsie" ręcznie w środowisku testowym 
przed każdą wersją jako część wykonania testów regresyjnych.

318) Czym jest Ciągła Integracja, "Continuous Integration (CI)"? 
"CI" to praktyka w zakresie rozwoju oprogramowania w "DevOps", w której deweloperzy regularnie łączą swoje zmiany w kodzie
w centralnym repozytorium, po czym uruchamiane są zautomatyzowane kompilacje i testy. "CI" najczęściej odnosi się do etapu 
kompilacji lub integracji w procesie wydania oprogramowania i obejmuje zarówno komponent automatyzacji, jak i komponent 
kulturowy (uczenie się częstej integracji). Kluczowe cele "CI" to szybkie wykrywanie i rozwiązywanie błędów, poprawa jakości 
oprogramowania oraz skrócenie czasu potrzebnego na walidację i wydanie nowych aktualizacji oprogramowania.

319) Jak możesz przekazać wartość z polecenia "Maven" do swojego kodu? 
Polecenie "Maven" z właściwością systemową: "mvn test -Denv=PROD".

320) Jakie cykle życia "Maven" znasz? 
Istnieją trzy wbudowane cykle życia budowy: domyślny, czyszczenia i witryny ("site"). Cykle życia domyślnego zajmują się
wdrażaniem projektu, cykl czyszczenia zajmuje się usuwaniem plików projektu, a cykl witryny zajmuje się tworzeniem 
strony internetowej projektu.
a) Cykle czyszczenia. "clean" - usuwa wszystkie pliki wygenerowane przez poprzednią kompilację.
b) Cykle domyślne. 
"validate" - weryfikuje, czy projekt jest poprawny i czy wszystkie niezbędne informacje są dostępne. 
"compile" - kompiluje kod źródłowy projektu. 
"test" - testuje skompilowany kod źródłowy za pomocą odpowiedniego frameworka testowego. 
"package" - pakuje skompilowany kod w jego dystrybuowalny format, taki jak "JAR". 
"verify" - wykonuje wszelkie kontrole wyników testów integracyjnych, aby upewnić się, że spełnione są kryteria jakości.
c) Cykle witryny. 
"site" - generuje dokumentację witryny projektu.

321) Czym jest "Maven"? Jaką rolę odgrywa w twoim frameworku? 
"Maven" to narzędzie do automatyzacji procesu budowy i zarządzania projektami dla projektów Java. 
"Maven" automatyzuje proces budowy, w tym zadania takie jak kompilacja kodu źródłowego, uruchamianie testów, 
pakowanie binariów i tworzenie artefaktów.
"Maven" stosuje podejście konwencji zamiast konfiguracji, definiując standardową strukturę projektu. Ułatwia to deweloperom 
zrozumienie i wniesienie wkładu w projekty, ponieważ wiedzą, gdzie znaleźć kod źródłowy, zasoby i pliki konfiguracyjne.
"Maven" zarządza zależnościami projektu, pobierając je i rozwiązując z zdalnych repozytoriów.

322) Jaka jest różnica między asercją twardą ("hard assert") a asercją miękką ("soft assert")? 
Asercja twarda to tradycyjny mechanizm asercji, który natychmiast przerywa wykonanie testu po napotkaniu pierwszej 
niepowodzenia asercji. Asercja miękka pozwala natomiast na kontynuowanie wykonywania testu, nawet jeśli asercja zawiedzie. 
Wszystkie asercje są wykonywane, a wyniki są zbierane na końcu.

323) Dlaczego potrzebujesz "JUnit" / "TestNG" w swoim frameworku? 
Mój framework to framework oparty na Javie z narzędziami Maven, Selenium, Cucumber i JUnit. Główna rola JUnit to asercja 
testów i zapewnienie silnika uruchamiającego.

324) Czym są "JUnit" i "TestNG"? 
JUnit i "TestNG" to frameworki dla języka programowania Java, zaprojektowane w celu ułatwienia rozwoju testów automatycznych 
i uproszczenia procesu testowania.
JUnit został stworzony przede wszystkim do testowania jednostkowego w Javie.
"TestNG" to framework testowy, który rozszerza "JUnit" i wprowadza nowe funkcje.

325) Jak uruchomić tylko scenariusze testów smoke lub tylko scenariusze testów regresyjnych w Cucumber?
Możemy użyć tagów Cucumber. "mvn test -Dcucumber.options=--tag @smoke".

326) Jak działa parametryzowany krok w Cucumber? 
Możesz przekazywać wartości do metody definicji kroku z kroku swojego scenariusza. Liczby są domyślnie parametryzowane. 
Ciągi znaków w podwójnych cudzysłowach są parametryzowane. Możesz użyć tabel danych, aby przekazać wiele wartości.

327) Czym są Hooki w Cucumber? 
Hooki to bloki kodu, które mogą być uruchamiane w różnych punktach cyklu wykonania Cucumber. Zwykle są używane do 
konfiguracji i sprzątania środowiska przed i po każdym scenariuszu.
Hooki scenariusza: "@Before", "@After".
Hooki kroków: "@BeforeStep", "@AfterStep".

328) Czym jest "Background" w Cucumber? 
Możemy tworzyć kroki "Background", które są uruchamiane przed każdym scenariuszem, ale po hookach. Możemy używać kroków 
Background, aby unikać duplikacji. Dobrą praktyką jest, aby były one krótkie.

329) Czym jest "Scenario Outline" w Cucumber? 
Słowo kluczowe "scenario outline" można używać do uruchamiania tego samego scenariusza wielokrotnie, z różnymi 
kombinacjami wartości.

330) Czym jest definicja kroku ("Step Definition")? 
Definicja kroku to metoda z wyrażeniem, które łączy ją z jednym lub więcej krokami Cucumber. Gdy krok jest wykonywany, 
system szuka pasującej definicji kroku do wykonania.

331) Czym są słowa kluczowe "Feature", "Scenario" i "Steps"? 
Słowo kluczowe Feature służy do dostarczenia opisu na wysokim poziomie funkcji oprogramowania i do grupowania powiązanych 
scenariuszy. Słowo kluczowe "Scenario" (przykład) ilustruje regułę biznesową dla twojego testu. Składa się z listy kroków.
Kroki: "Given", "When", "Then", "And" i "But". Reprezentują rzeczywiste kroki twojego przypadku testowego. 
Cucumber wykonuje każdy krok jeden po drugim w kolejności.

332) Czym jest klasa runner? Czy możesz opisać, co się w niej znajduje? 
Klasa runner to punkt wykonania dla Cucumber. Łączy pliki Feature z ich definicjami kroków.

333) Jakie są główne komponenty Cucumber? 
Pliki Feature: To tutaj piszemy nasze testy jako scenariusze Cucumber z krokami.
Definicje kroków: Implementacja kroków z rzeczywistym skryptem automatyzacyjnym.
Runner: To punkt wykonania dla Cucumber. Łączy pliki Feature z ich definicjami kroków.

334) Czym jest język "Gherkin"? 
To język, którego używamy do pisania scenariuszy Cucumber.

335) Jakie są korzyści z używania Cucumber? 
Kod staje się wielokrotnego użytku, gdy framework się rozwija. Łatwiejsze zrozumienie logiki testów dzięki czytelnym 
scenariuszom.

336) Czym jest Cucumber w automatyzacji testów? 
Cucumber to narzędzie do uruchamiania zautomatyzowanych testów, napisanych w prostym języku. Cucumber został stworzony, 
aby wspierać rozwój oparty na zachowaniu ("BDD").

337) Czym są "SauceLabs" i "BrowserStack"? 
"SauceLabs" i "BrowserStack" to obie platformy oparte na chmurze, które zapewniają infrastrukturę do testowania aplikacji 
internetowych i mobilnych w różnych przeglądarkach, systemach operacyjnych i urządzeniach. Oferują narzędzia i usługi, 
które umożliwiają zespołom deweloperskim i "QA" testowanie aplikacji w różnych środowiskach, aby zapewnić zgodność 
i funkcjonalność. Umożliwiają również korzystanie z chmurowej siatki Selenium do zautomatyzowanego testowania aplikacji 
internetowych i mobilnych.

338) Czym jest "Selenium Grid"? 
"Selenium Grid" pozwala na wykonywanie skryptów WebDriver na zdalnych maszynach, kierując polecenia wysyłane przez klienta 
do zdalnych instancji przeglądarek. "Grid" ma na celu: zapewnienie łatwego sposobu uruchamiania testów równolegle na wielu 
maszynach, umożliwienie testowania w różnych wersjach przeglądarek oraz wspieranie wieloplatformowych bibliotek.

339) Czym jest "Page Object Model (POM)"? 
"POM" to wzorzec projektowy w Selenium, który tworzy repozytorium obiektów do przechowywania elementów stron internetowych. 
W "POM" każda strona aplikacji jest reprezentowana jako klasa. Każda klasa zawiera tylko odpowiadające jej elementy strony 
oraz metody do pracy z tą stroną. Pomaga to zmniejszyć duplikację kodu.

340) Co robi klasa "Actions" w "Selenium"? 
Klasa "Actions" umożliwia obsługę zdarzeń klawiaturowych, zdarzeń myszy i specjalnych akcji. Niektóre z powszechnych akcji 
to: przeciąganie i upuszczanie, najeżdżanie myszą, podwójne kliknięcie oraz kliknięcie prawym przyciskiem myszy.

341) Jak wpisać tekst w polu wejściowym w "Selenium"? 
Robimy to za pomocą metody "sendKeys(text)".

342) Jak pracować z oknami popup w Selenium? 
Używamy klasy Alert, aby pracować z oknami popup opartymi na alertach w Selenium. Możemy zaakceptować, odrzucić, pobrać 
tekst oraz wysłać klawisze za pomocą klasy Alert. Musimy przełączyć się na alert. Należy jednak poczekać na obecność 
alertu (czekanie jawne).

343) Jak pracować z rozwijanym menu "UI" w Selenium? 
Gdy rozwijane menu jest oparte na tagu select, możemy użyć klasy Select, aby z nim pracować.

344) Jak pracować z "iFrame" w Selenium? 
Najpierw przełącz się na "iFrame", używając metody "switchTo()". Możesz przełączać się według indeksu, id lub nazwy. 
Gdy zakończysz swoje zadanie w "iFrame", możesz wrócić, używając "defaultContent".

345) Jak pracować z wieloma oknami w Selenium? 
Każde okno ma unikalny uchwyt okna ("window handle") (swojego rodzaju ID okna). Możemy odczytać te uchwyty okien za 
pomocą Selenium i przełączać się między oknami.

346) Co sprawdzasz, jeśli element istnieje w DOM? 
Możemy spróbować zlokalizować ten element i złapać wyjątek, jeśli to się nie powiedzie. Używamy metody findElements, 
aby sprawdzić, czy element znajduje się w "DOM". Jeśli zwraca pustą listę, oznacza to, że element nie istnieje w "DOM".

347) Jaka jest różnica między "findElement" a "findElements"? 
Metoda "findElement" zwraca pojedynczy WebElement. Rzuca wyjątek NoSuchElement, jeśli element nie istnieje. Metoda 
"findElements" zwraca listę WebElementów. Zwraca pustą listę, jeśli elementy nie istnieją.

348) Jaka jest różnica między czekaniem "implicit" a "explicit"? 
Czekanie "implicit" to globalne czekanie. Musimy je zadeklarować raz na początku wykonania. Stosuje się je do wszystkich 
lokalizatorów elementów. Używamy czekania explicit w szczególnych przypadkach, gdy musimy poczekać na wystąpienie 
określonego zdarzenia, takiego jak: pojawienie się elementu, zniknięcie elementu itp.

349) Czym jest czekanie "fluent" w Selenium? 
Używane jest do oczekiwania na określone zdarzenia. Możemy skonfigurować czas oczekiwania ("pooling time"). 
Możemy również skonfigurować konkretne wyjątki, które będą ignorowane.

350) Jakie są główne wady czekania "implicit? 
Ponieważ stosuje się do wszystkich lokalizatorów elementów, może spowolnić ogólny czas wykonania.

351) Jak obsługiwać dynamiczne elementy? 
Używając metody contains w "xpath", znajdując statyczną część elementu. Innym sposobem jest znalezienie statycznego 
elementu i budowanie ścieżki z tego elementu do mojego dynamicznego elementu.

352) Jaka jest różnica między "xpath" a "css"? 
Główne różnice: "CSS" jest szybszy niż "xpath". "Xpath" ma więcej funkcji do budowania złożonych ścieżek. Posiada wiele 
przydatnych metod, na przykład: wyszukiwanie według tekstu, zawartości tekstu i równości tekstu. "Xpath" może przemieszczać 
się po "DOM" w dowolnym kierunku (w górę, w dół), podczas gdy "CSS" tylko w dół.

353) Jaka jest różnica między ścieżkami "absolute" a "relative"? 
Ścieżka absolutna zaczyna się od korzenia HTML i używa pojedynczego ukośnika do nawigacji do docelowego elementu. 
Ścieżka relatywna może zaczynać się z dowolnego miejsca w HTML i używa podwójnego ukośnika do nawigacji do docelowego 
elementu. Ścieżka relatywna jest bardziej powszechnie używana niż ścieżka absolutna.

354) Który lokalizator Selenium preferujesz? 
Preferuję ID i nazwę, gdy są unikalne. Następnym preferowanym lokalizatorem jest "CSS", a jeśli muszę pisać bardziej 
złożone lokalizatory, będę korzystać z "xpath".

355) Jakie rodzaje lokalizatorów znasz w Selenium?
ID, Name, Class name, xpath, css, link text and partial link text, tag name.

356) Czym jest Model Obiektowy Dokumentu (DOM) w UI? 
DOM jest obiektową reprezentacją HTML. Możemy powiedzieć, że jest to model obiektowy dokumentu HTML. HTML i DOM są ze 
sobą zsynchronizowane. "MOD" - Model Obiektowy Dokumentu.

357) Jakie są zalety i wady Selenium? 
Zalety: 
a) Kompatybilność między przeglądarkami. Selenium obsługuje wiele przeglądarek, co czyni go idealnym narzędziem 
do testowania aplikacji internetowych. 
b) Open Source: Selenium jest darmowe w użyciu i ma dużą społeczność użytkowników oraz deweloperów. 
c) Rozbudowany ekosystem: Selenium oferuje szeroki wachlarz narzędzi i frameworków do różnych potrzeb testowych.
Wady: 
a) Nie nadaje się do testowania aplikacji desktopowych. 
b) Selenium koncentruje się głównie na aplikacjach internetowych i ma ograniczone wsparcie dla testowania aplikacji mobilnych.

358) Jak przeprowadzasz testy regresyjne w swoim projekcie? 
Duża część naszego zestawu testów regresyjnych jest zautomatyzowana w naszym projekcie, a my wykonujemy je w Jenkinsie 
przed każdą wersją. Pozostałe testy, które nie są zautomatyzowane, wykonujemy ręcznie.

359) Co robisz, gdy twój zautomatyzowany zestaw testów zawiedzie? 
Najpierw sprawdzamy, czy test automatyczny zawiódł z powodu samego skryptu automatyzacji, czy z powodu nieoczekiwanego 
zachowania aplikacji. Zwykle łatwo jest spojrzeć na zrzuty ekranu lub nagrania, jeśli framework je generuje. 
Jeśli problem dotyczy samego skryptu, otwieram zgłoszenie z szczegółami błędu, a w następnej iteracji sprintu to naprawimy. 
W międzyczasie przetestuję to ręcznie i dołączę szczegóły do ogólnych raportów z uruchomienia testów automatycznych. 
Jeśli problem dotyczy aplikacji, wtedy powtórzę go ręcznie i otworzę zgłoszenie błędu.

360) Jak określasz, które testy zautomatyzować? 
Test powinien być częścią zestawu testów regresyjnych, ponieważ automatyzujemy, aby zredukować ręczny wysiłek związany 
z zestawem testów regresyjnych. Test powinien być możliwy do zautomatyzowania. Narzędzia, które używamy, powinny móc 
zautomatyzować ten test. Test jest powtarzalny.

361) Jak tworzysz dane testowe dla swojej aplikacji? 
Poprosiłem dostawcę o przykład. Generujemy własne dane, aby sprawdzić podstawowe przetwarzanie (w teście akceptacyjnym).

362) Opowiedz mi o najnowszym defekcie, który odkryłeś? 
Błędna odpowiedź od dostawcy spowodowała, że nasza aplikacja zwróciła błąd zamiast ładunku XML (brakująca przestrzeń 
nazw w odpowiedzi). Zauważyłem, że nasze narzędzie do wdrożeń nie wyświetlało zgłoszenia zmian, jeśli zostały one 
umieszczone w tej samej minucie (problem z UI).

363) Czym jest testowanie wydajności? 
Testowanie wydajności to testowanie niefunkcjonalne, które określa stabilność, szybkość, skalowalność i responsywność 
aplikacji. Niektóre przykłady to: prędkość przetwarzania, prędkość transferu danych, zużycie przepustowości sieci
("network bandwidth usage"), maksymalna liczba użytkowników jednoczesnych, wykorzystanie pamięci, efektywność obciążenia 
i czasy opóźnienia.

364) Jakie różne techniki testowania znasz i stosujesz? 
Istnieje wiele różnych typów testów. Skupmy się na tych najczęściej używanych. 
a) Testowanie pozytywne: Określa, czy twoja aplikacja działa zgodnie z oczekiwaniami. 
b) Testowanie negatywne: Zapewnia, że twoja aplikacja może obsługiwać nieprawidłowe dane wejściowe i nieoczekiwane 
zachowania użytkownika. 
c) Podział na równoważne klasy ("Equivalence partitioning"): To technika testowania, która dzieli dane wejściowe jednostki 
na partycje równoważnych danych, z których można wyprowadzić przypadki testowe. 
d) Testowanie wartości brzegowych (" Boundary value testing"): To technika testowania dla wartości skrajnych. Zachowanie 
na brzegu partycji równoważnych jest bardziej prawdopodobne do bycia błędnym niż zachowanie wewnątrz partycji. 
e) Testowanie ad hoc: To nieformalny lub niestrukturalny typ testowania oprogramowania, którego celem jest złamanie 
procesu testowania w celu znalezienia możliwych defektów.

365) Jak określasz, które przypadki testowe powinny być częścią zestawu testów regresyjnych? 
Przypadek testowy powinien dotyczyć ważnej funkcjonalności. Powinien być to przypadek testowy powtarzalny.

366) Czym jest zestaw testów regresyjnych i dlaczego jest potrzebny? 
Zestaw testów regresyjnych to zbiór scenariuszy testowych, które dotyczą różnych funkcjonalności istotnych dla 
oprogramowania. Zwykle tworzymy zestaw testów regresyjnych na podstawie istniejących testów, które już zostały wykonane 
w poprzednich cyklach wydania ("release cycles"). Powód, dla którego wykonujemy testy, to możliwość, że nowe zmiany mogą 
zepsuć działające już funkcjonalności, więc przed wydaniem nowych zmian dla naszych klientów musimy wykonać nasz zestaw 
testów regresyjnych.

367) Czym jest Macierz Śledzenia Wymagań ("Requirements Traceability Matrix (RTM)") w testowaniu? 
Odpowiedź: RTM to dokument, który mapuje i śledzi wymagania użytkownika z przypadkami testowymi. Głównym celem RTM jest 
zweryfikowanie, że wszystkie wymagania są sprawdzane za pomocą przypadków testowych, aby żadna funkcjonalność nie pozostała 
niesprawdzona podczas testowania oprogramowania.

368) Czym jest cykl życia defektu? 
To konkretna faza, przez którą defekt przechodzi od zgłoszenia do rozwiązania: 
a) Defekt jest zgłaszany i przypisywany do naprawy. 
b) Naprawiony. 
c) Testowanie ponowne. 
d) Jeśli defekt nadal występuje, należy go ponownie otworzyć i rozpocząć od kroku 1. 
e) W przeciwnym razie jest zakodowany.

369) Czym jest cykl życia testowania oprogramowania (STLC) w Twoim zespole? 
Zazwyczaj każdy zespół przyjmuje własny STLC, który najlepiej odpowiada jego potrzebom. STLC to proces testowania, 
który ma określone kroki do wykonania w sekwencji, aby zapewnić osiągnięcie celów jakościowych. Fazy STLC w moim zespole to: 
a) Analiza wymagań (specyfikacje produkcyjne). 
b) Tworzenie i przegląd planu testów. 
c) Przekazanie QA ("QA handoff"). 
d) Wykonanie / Testowanie. 
e) Zatwierdzenie przez "QA".

370) Czym jest przypadek testowy i co zawiera doskonały przypadek testowy?
Przypadek testowy opisuje kroki do przeprowadzenia testu dla konkretnego czynnika lub funkcjonalności. Dobry przypadek 
testowy zawiera szczegółowe kroki, dane testowe, oczekiwane wyniki, środowisko testowe oraz wszystkie wymagania wstępne 
do przeprowadzenia testu.

371) Czym jest plan testów i co zawiera dobry plan testów?
Plan testów to szczegółowy dokument, który zawiera kryteria wejścia, zakres testowania, strategie testów, cele, 
harmonogram, terminy, zasoby potrzebne do przeprowadzenia testów oraz kryteria wyjścia. Stanowi punkt odniesienia, 
na podstawie którego realizowane i koordynowane są działania testowe w zespole QA. Plan testów jest udostępniany 
analitykom biznesowym, kierownikowi projektu i programistom, aby zapewnić przejrzystość działań testowych, dzięki czemu 
wszyscy interesariusze ("stakeholders") wiedzą, jak oprogramowanie będzie testowane. Ostatecznym celem procesu planowania 
testów jest komunikacja (nie rejestrowanie) zamiarów zespołu testów oprogramowania, jego oczekiwań i zrozumienia testów, 
które są przeprowadzane.

372) Czym jest defekt?
Defekt występuje, gdy rzeczywisty wynik nie zgadza się z oczekiwanym wynikiem. Zazwyczaj oczekiwane wyniki pochodzą z 
dokumentu specyfikacji produktu, który obejmuje wszystkie aspekty produktu. Na przykład, jeśli oprogramowanie jest trudne 
do zrozumienia, trudne w użyciu, wolne lub po prostu nie działa jak należy, a nie zostało to uwzględnione w specyfikacji 
produktu, jest to ważny defekt.

373) Funkcjonalność mockowania?
a) Użyj mocków, metod mockujących.
b) metody "given/willReturn" oraz "when/then".
c) metody "then/should" i "verify".
d) "ArgumentMatcher" i "willThrow".
e) "Argument Captor".
f) metody "willDoNothing", "willAnswer", "willCallRealMethod".
g) adnotacje "@InjectMock", "@Mock" i "@Captor".
h) Obiekty szpiegowskie ("Spy").

374) Zasada FIRST?
Szybkie ("Fast") - Testy jednostkowe uruchamiają się szybko.
Niezależne ("Independent") - Testy jednostkowe są niezależne.
Powtarzalne ("Repeatable") - Testy jednostkowe można powtarzać.
Samo-walidujące ("Self-Validating") - Testy jednostkowe walidują się same.
Dokładne i terminowe ("Thorough & Timely") - Pokrywają przypadki brzegowe. 

375) JUnit 5?
JUnit 5 = "J-Unit Platform + J-Unit Jupiter + J-Unit Vintage".
"J-Unit Platform": Platforma "J-Unit" służy jako fundament do uruchamiania frameworków testowych w JVM.
"J-Unit Jupiter": To połączenie nowego modelu programowania i modelu rozszerzeń do pisania testów i rozszerzeń w "J-Unit 5".
"J-Unit Vintage": "TestEngine" do uruchamiania testów opartych na "J-Unit 3" i "J-Unit 4" na tej platformie.

376) Rozwój napędzany testami (TDD)?
Cykl życia rozwoju napędzanego testami (Czerwony, Zielony, Refaktoryzacja, Powtórzenie).
a) Czerwony: Napisz test jednostkowy, który się nie powodzi.
b) Zielony: Napisz kod aplikacji, aby test jednostkowy przeszedł.
c) Refaktoryzacja: Uporządkuj. Popraw test jednostkowy i kod aplikacji.
d) Powtórzenie: Powtarzaj powyższe kroki, aż wszystkie funkcjonalności będą zaimplementowane.

377) Frameworki mockujące?
Ekosystem Javy obejmuje szereg frameworków mockujących.
Cechy (Features"): Minimalizują ręczne kodowanie mocków, wykorzystują adnotacje. Ustawiają oczekiwania dotyczące odpowiedzi mocków. 
Weryfikują wywołania metod, w tym liczbę wywołań. Obsługa programowa do zgłaszania wyjątków.
Spring-boot-starter-test: Zależność przejrzysta dla mockowania.

378) Mocki z Mockito i Spring Boot?
Technika używania podwójnych testów jest znana jako "mockowanie".
Korzyści z mockowania: Umożliwia testowanie danej klasy w izolacji. Testuje interakcje między daną klasą a jej zależnościami. 
Minimalizuje konfigurację/dostępność zależności. Na przykład DAO, baza danych, REST API itd.: Możemy zmockować DAO, aby 
zwróciło odpowiedź. Możemy zmockować REST API, aby zwróciło odpowiedź.

379) Wsparcie Spring Boot dla testów jednostkowych?
Czego potrzebujesz do testowania jednostkowego w Spring Boot? Dostępu do kontekstu aplikacji Spring. Wsparcia dla 
wstrzykiwania zależności Spring. Uzyskiwania danych z pliku "application.properties" Spring. Wsparcia dla obiektów mockujących 
dla aplikacji webowych, danych, REST API. 
"@SpringBootTest": Ładowanie kontekstu aplikacji. Wsparcie dla wstrzykiwania zależności Spring. 
Możesz uzyskać dostęp do danych z pliku "application.properties" Spring.

380) Testowanie z użyciem refleksji?
Potrzebujesz dostępu do pól niepublicznych. Odczytanie wartości pola. Ustawienie wartości pola. Wywołanie metod 
niepublicznych. Testowanie kodu dziedziczonego... nie można zmieniać kodu z powodu polityki korporacyjnej. Ogólnie rzecz 
biorąc, testowanie pól i metod niepublicznych jest kontrowersyjne, należy stosować je oszczędnie.

381) Adnotacja MockBean?
Zamiast używać Mockito: "@Mock" i "@InjectMock", skorzystaj ze wsparcia Spring Boot: "@MockBean" i "@Autowired".
"@MockBean": Zawiera funkcjonalność Mockito "@Mock". Dodatkowo dodaje mock bean do kontekstu aplikacji Spring. 
Jeśli istnieje już bean, mock bean go zastąpi. Dzięki temu mock bean będzie dostępny do wstrzykiwania za pomocą "@Autowired".
Używaj "@MockBean" w Spring Boot, gdy potrzebujesz wstrzykiwać mocki oraz regularne beany z kontekstu aplikacji.

382) Testy parametryzowane?
JUnit oferuje "@ParametrizedTest". Uruchamia test wielokrotnie, dostarczając różne wartości parametrów.
Źródła wartości dla "@ParametrizedTest": "@ValueSource", "@CsvSource", "@CsvFileSource", "@EnumSource", "@MethodSource".

383) Co oznacza skalowanie pionowe?
Skalowanie systemu poprzez aktualizację do bardziej wydajnych maszyn.
Skalowanie pionowe oznacza używanie jednego serwera i stopniowe zwiększanie jego mocy w miarę wzrostu ruchu. 
Może to prowadzić do pojedynczego punktu awarii i ma górną granicę, jak daleko można skalować. Jednak w przypadku małych, 
niekrytycznych systemów może to być proste i opłacalne rozwiązanie.

384) Co wymaga skalowanie poziome?
Serwery bezstanowe, gdzie każdy serwer może obsłużyć dowolne żądanie.
W systemie, który można skalować poziomo, każdy serwer powinien być "bezstanowy" i zakładać, że każde żądanie mogło być 
obsłużone przez niego lub przez inny serwer.

385) Host bazy danych, który jest replikowany do kopii zapasowej, która jest zawsze gotowa do przejęcia, jest przykładem?
("Warm Standby"). Podczas obsługi monolitycznych hostów baz danych relacyjnych, replikacja jest powszechną techniką 
tworzenia kopii zapasowych. Jednak skalowalne systemy zazwyczaj opierają się na rozproszonych magazynach danych 
klucz/wartość lub ogólnie rzecz biorąc magazynach obiektów, znanych jako "No-SQL" – ponieważ można je poziomo skalować 
z kopiami zapasowymi na każdym fragmencie.

386) Która baza danych "No-SQL" nie ma węzła głównego?
Cassandra. Cassandra składa się z węzłów, gdzie każdy węzeł może pełnić rolę głównego. Osiąga to kosztem spójności 
(Consistency).

387) Jaka jest wada denormalizowania danych? 
Obsługa aktualizacji może być kosztowna. Aktualizacje w denormalizowanej tabeli mogą wymagać przeszukiwania każdego 
wiersza w poszukiwaniu kopii danych, które muszą zostać zaktualizowane. Zajmują również więcej miejsca. 

388) Rozproszone bazy danych "N-oSQL" z węzłem głównym, który rozdziela transakcje, znajdują się po której stronie trójkąta CAP? 
Spójność i tolerancja podziału (poświęcając dostępność).
Projektowanie z pojedynczym węzłem głównym sprzyja spójności i tolerancji podziału. Choć w zasadzie to dostępność jest tym, 
co zostaje poświęcone, w praktyce nowoczesne bazy danych "No-SQL" mają wysoce redundantne węzły główne, które mogą szybko 
zastąpić się w przypadku awarii.

389) Jaką politykę usuwania danych należy zastosować przy projektowaniu pamięci podręcznej, która odrzuca dane, 
które były używane najdawniej?
LRU. Polityka "Least Recently Used" (LRU) jest najczęściej stosowana i usuwa dane, które nie były używane przez
najdłuższy czas, gdy pamięć podręczna się zapełnia.

390) Jeśli musisz zminimalizować opóźnienie klientów przy pobieraniu danych statycznych z całego świata bez względu na koszty?
"C D N": Sieci dostarczania treści ("CDN") są zaprojektowane w celu rozwiązania problemu globalnego ruchu i minimalizacji 
opóźnień spowodowanych długimi skokami sieciowymi. Load balancery i technologie "cache", takie jak Redis, mogą 
również być częścią projektów o niskim opóźnieniu, ale nie są szczególnie ukierunkowane na problem globalnego ruchu.

391) SLA wynoszące 100 ms przy "three nines" oznacza? 
Mój system odpowie w mniej niż 100 milli sekundach w 99,9% przypadków.

392) W HDFS serwer odpowiedzialny za koordynację żądań nazywa się? 
Węzeł nazw ("Name node"). W systemie Hadoop Distributed File System węzeł nazw koordynuje, jak pliki są dzielone na bloki 
i gdzie te bloki są przechowywane. W ustawieniach o wysokiej dostępności może być obecnych wiele węzłów nazw dla 
zapewnienia ciągłości działania.

393) Wzorce projektowe! 
Zestaw najlepszych praktyk, rozwiązań, ustrukturyzowane podejście do problemów w projektowaniu oprogramowania. 
OOP, funkcyjne, chmurowe, integracyjne, odporność, architektura oparta na zdarzeniach.

394) Wzorce integracji? 
Wzorzec agregatora bramek ("Gateway Aggregator"), wzorzec scatter-gather, wzorzec orkiestratora ("Orchestrator Pattern")
(przepływ pracy równoległy), wzorzec orkiestratora ("Orchestrator Pattern") (przepływ pracy sekwencyjny), 
wzorzec dzielnika ("Splitter Pattern").

395) Wzorzec odporności? 
Wzorzec timeout ("Timeout Pattern"), wzorzec ponawiania ("Retry Pattern"), wzorzec wyłącznika obwodu 
("Circuit Breaker Pattern"), wzorzec limitera ("Rate Limitter Pattern"), wzorzec przegrody ("Bulkhead Pattern").

396) Wzorzec scatter-gather? 
Agregator: Rozsyła wiadomość do wszystkich odbiorców, a następnie zbiera odpowiedzi. Przykład użycia: aplikacja lotnicza 
(Google Flights), zbierająca informacje o lotach z Frontier Airlines, Delta, United.

397) Orchestrator Pattern?
Agregator + dodatkowa logika biznesowa do zapewnienia przepływu pracy.

398) Orchestrator Pattern ("Chained / Sequential workflow")?
Wzorzec Łańcucha ("Chained Pattern"). Nie ma specjalnego agregatora. Każda usługa może przyjąć rolę agregacji.
Zalety: Łatwy do zaimplementowania.
Wady: Zwiększona latencja. Bardzo trudny do debugowania. Bardzo trudny do utrzymania i wprowadzenia zmian w wymaganiach.
Lepiej używać Orkiestratora dla Łańcuchowych Przepływów Pracy ("Chained Workflows"): Orkiestrator zapewnia łańcuchowy 
przepływ pracy. 
Zalety: Łatwy do debugowania. Łatwy do utrzymania i wprowadzenia zmian w wymaganiach.
Wady: Zwiększona latencja.

399) Resilience?
Systemy reaktywne powinny być responsywne.
Resilience = Responsywność w przypadku awarii.

400) "Timeout Pattern"?
Jeden z najprostszych wzorców odporności do zaimplementowania.
Traktuj żądanie jako nieudane, jeśli odpowiedź nie została otrzymana w określonym czasie oczekiwania.

401) "Retry Pattern"?
Błąd przejściowy. W sieci może wydarzyć się cokolwiek.
Odzyskiwanie z błędów przejściowych. Może to zwiększyć ogólny czas odpowiedzi.
Nie zapomnij ustawić limitu czasu ("Timeout").
Nie próbuj ponownie dla błędów 4xx.

402) Circuit Breaker Terms"?
"CLOSED": Usługa zależna jest dziwłająca. Wszystkie żądania są wysyłane.
"OPEN": Usługa zależna jest niedostepna. Żądania nie są wysyłane.
"HALF OPEN": Usługa zależna może być działająca. Wysyłane są tylko niektóre żądania, aby to sprawdzić

403) "Circuit Breaker"?
Aby umożliwić działanie usługi klienckiej, gdy usługa nadrzędna nie jest zdrowa. Można go używać razem z "Retry + Timeout".
"Resilience-4j": wsparcie dla Spring i Reactor. Ogranicznik szybkości ("Rate limiter"), Izolacja ("Bulkhead") itp.
Konfiguracja za pomocą pliku YAML / Nadpisywanie przez Bean.

404) "Rate Limiter Pattern"?
Ogranicznik Szybkości po Stronie Serwera ("Server Side Rate Limiter"):
Aby ograniczyć liczbę żądań obsługiwanych przez węzeł serwera. Chroni zasoby systemu przed przeciążeniem. 
Bardzo ważne dla zadań intensywnie wykorzystujących CPU.
Ogranicznik Szybkości po Stronie Klienta ("Client Side Rate Limiter"):
Aby ograniczyć liczbę żądań wysyłanych przez klienta do serwera. Zmniejsza koszty / szanuje umowę.

405) "Distributed Rate Limiter"?
Nie myl tego (odpornościowego) ogranicznika szybkości z funkcjonalnością Twojej aplikacji.
Gość: 5 na sekundę.
Użytkownik standardowy: 50 na sekundę.
Użytkownik premium: 500 na sekundę.

406) "Server Side Rate Limiter"?
Aby ograniczyć liczbę żądań obsługiwanych przez węzeł serwera.

407) "Client Side Rate Limiter"?
Aby ograniczyć wywołania do usługi nadrzędnej (upstream).

408) "RateLimiter vs Bulkhead Pattern"?
"RateLimiter": Liczba żądań na jednostkę czasu. Odrzuć inne żądania.
"Bulkhead": Liczba równoczesnych wywołań. Kolejkowanie innych wywołań.









