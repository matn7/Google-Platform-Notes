## NETWORKING

## APIS

## CACHING BASICS

## PROXIES

## STORAGE

1) Co to jest SQL?
Bazy danych SQL, znane również jako systemy zarządzania relacyjnymi bazami danych (RDBMS), to bazy danych, które przechowują
trwałe dane za pomocą tabel. RDBMS oferują sposób strukturalizacji danych na dysku, aby odczyt i zapis danych były wydajne.
Dane są formatowane w ściśle określonych polach, co umożliwia ich łatwe zapytanie.

2) Czym są B+ Trees?
Co sprawia, że zapytania są wydajne, to wykorzystanie struktury danych zwanej drzewem B+ (B+ Tree). Drzewa B+ to drzewa
wielodrogowe, które mogą mieć więcej niż dwoje dzieci w węźle i przechowują wszystkie swoje dane w węzłach liściowych,
które są połączone w posortowanej kolejności. Wszystkie dane są przechowywane w węzłach liściowych. Jest to różnica w
porównaniu do węzła drzewa B, w którym węzły wewnętrzne zawierają tylko klucze i wskaźniki do innych węzłów.
Ponadto, każdy węzeł może mieć co najwyżej M dzieci. Jeśli węzeł ma M dzieci, musi zawierać M-1 kluczy. Na przykład,
jeśli węzeł korzenia zawiera wartości [2, 5], będzie miał trzy dzieci. Klucze pełnią rolę wartości separacyjnych,
które dzielą jego poddrzewa. Pierwsze dziecko węzła zawierającego [2, 5] będzie miało wartości [0, 1],
drugie wartości [3, 4], a trzecie wartości [6, 7]. Zauważ, że pierwsze dziecko zawiera wszystkie wartości mniejsze niż 2.
Drugie dziecko zawiera wartości pomiędzy 2 a 5. Trzecie dziecko zawiera wartości większe niż 5.
Innym powodem, dla którego używa się drzew B+, jest to, że zapewniają one indeksowanie. Indeksowanie to sposób na
poprawienie szybkości operacji pobierania danych z tabeli bazy danych, kosztem dodatkowych zapisów i miejsca na
przechowywanie struktury danych indeksu. Aby podać szybki przykład, jeśli mielibyśmy zbiór nazwisk powiązanych z ich
numerami telefonów, moglibyśmy wybrać pole "imię" jako indeks, co pozwoliłoby nam szybciej pobierać numery telefonów w
przyszłości. Jednak indeksowanie ma także kilka wad, które wykraczają poza zakres tej dyskusji.

3) Jak Dane są przechowywane w SQL?
W bazie danych SQL dane są przechowywane w tabelach. Tabele to sposób organizacji danych, gdzie każdy wiersz zawiera
informacje o jednym kluczu głównym. Klucz główny unikalnie identyfikuje każdy rekord, przy czym rekord to wiersz.
Jeśli wrócimy do przykładu z numerami telefonów, załóżmy, że chcemy unikalnie zidentyfikować każdą osobę po jej numerze
telefonu i przechować jej imię w tabeli o nazwie "People". Musimy najpierw zadeklarować strukturę tabeli,
zanim wstawimy jakiekolwiek rekordy.
Jeśli chcielibyśmy mieć inną tabelę, która powiąże każdy numer telefonu w tabeli "People" z adresem, wskazując, że każda
osoba musi mieć adres, możemy to zrobić w tabeli o nazwie "HOMES". Chcemy zapewnić, że żaden numer telefonu, który nie
istnieje w tabeli "People", nie może zostać wstawiony do tabeli "HOMES". Jest to przykład ograniczenia KLUCZA OBCEGO
(FOREIGN KEY). Oznacza to, że każdy wiersz w tabeli "HOMES" jest powiązany z wierszem w tabeli "People" za pomocą numeru telefonu.

4) Czym są JOINS?
Jeśli chcielibyśmy pobrać imiona i adresy osób na podstawie ich numerów telefonów, musielibyśmy wykonać złączenie dwóch
tabel. Złączenia (Joins) to sposób łączenia rekordów z dwóch lub więcej tabel na podstawie powiązanego kolumny między nimi.

5) Wady i zalety systemów zarządzania relacyjnymi bazami danych (RDBMS)?
Bazy danych relacyjne przestrzegają zasady ACID. ACID to właściwość, która oznacza Atomowość, Spójność, Izolację i Trwałość.
Ten akronim odnosi się do czterech kluczowych właściwości transakcji. Transakcja, w kontekście bazy danych, odnosi się do
ciągu jednej lub więcej operacji SQL, które traktowane są jako jednostka.

6) Trwałość (Durability)?
Po pomyślnym zakończeniu transakcji, zmiany w danych są trwałe i nie zostaną cofnięte, nawet w przypadku awarii systemu.
Na przykład, w aplikacji transferującej środki z jednego konta na drugie, wszystkie transakcje zostaną zapamiętane.
Więc jeśli po wykonaniu transakcji nastąpi przerwa w dostawie prądu, transakcja nadal będzie zapisana w bazie danych.

7) Atomowość (Atomicity)
Wszystkie zmiany w danych są wykonywane tak, jakby były jedną operacją. Oznacza to, że wszystkie zmiany zostaną wykonane,
lub żadna z nich nie zostanie. Działa to na zasadzie „Begin” i „Commit”. Jeśli którykolwiek z kroków transakcji zawiedzie,
oznacza to, że cała transakcja nie zostanie zatwierdzona. Transakcja zostanie zatwierdzona tylko wtedy, gdy wszystkie
operacje zakończą się sukcesem. Możemy to wyjaśnić na przykładzie. Załóżmy, że Alice i Bob mają odpowiednio 1000 i 500
dolarów na swoich kontach bankowych. Alice decyduje się wysłać Bobowi 500 dolarów. Jeśli baza danych ulegnie awarii podczas
realizacji transferu, atomowość zapewni, że pieniądze nie zostaną pobrane z konta Alice. Ponownie, albo wszystkie kroki
zostaną wykonane, albo żaden z nich nie zostanie wykonany. W przeciwnym razie pieniądze zostałyby zniszczone i zniknęły.

8) Izolacja (Isolation)
Izolacja odnosi się do stanu pośredniego transakcji, w którym transakcja jest niewidoczna dla innych transakcji.
W rezultacie transakcje wykonywane jednocześnie wyglądają na zserializowane. Właściwość izolacji zapewnia, że transakcje
równoległe nie będą sobie wzajemnie przeszkadzać, a skutki jednej transakcji są izolowane od innych transakcji,
dopóki nie zostanie ona zatwierdzona.
Załóżmy, że Alice ma 1000 dolarów na swoim koncie, a Bob ma 500 dolarów. Pierwsza transakcja składa się z dwóch operacji:
Odejmij 500 dolarów od konta Alice.
Dodaj te 500 dolarów do konta Boba.
Zatwierdź transakcję.
Jednak zanim to nastąpi, występuje druga transakcja, która dodaje 200 dolarów do konta Alice i zatwierdza ją.
To narusza zasadę izolacji, a także prowadzi do stanu zwanego brudnym odczytem (dirty read). Brudny odczyt ma miejsce,
gdy jedna transakcja odczytuje dane z innej transakcji, zanim ta została zatwierdzona. Oznacza to, że transakcja 2
odczytuje wartość z transakcji, która jeszcze nie została zatwierdzona. Aby zachować izolację, transakcja druga powinna
poczekać, aż transakcja pierwsza zostanie zatwierdzona, zanim odczyta jakiekolwiek dane i dokona zmian w bazie danych.
W tym kontekście serializacja oznacza, że transakcje są wykonywane jedna po drugiej w sposób sekwencyjny,
tak jakby działały w izolacji. Serializacja zapewnia, że porządek transakcji jest zachowany, a skutki jednej transakcji
są widoczne dla kolejnych transakcji dopiero po jej zatwierdzeniu. Dzięki temu zapobiega się brudnym odczytom.

9) Spójność (Consistency)
Spójność w modelu ACID zapewnia integralność danych. Mówiąc prosto, spójność w bazach danych odnosi się do przestrzegania
wcześniej zdefiniowanych reguł i ograniczeń, które utrzymują ważność danych przez całą realizację wielu transakcji.
Zapewnia to, że każda transakcja wykonywana przez bazę danych przenosi ją ze stanu ważnego do innego, również ważnego stanu.
W bazach danych mamy możliwość definiowania, jakie ograniczenia spójności powinny być przestrzegane. W naszym przykładzie
reguła, że saldo konta nie może być ujemne, jest ograniczeniem spójności zdefiniowanym przez bazę danych.

10) Czym są dazy danych NoSQL?
NoSQL oznacza "Not Only SQL" (Nie tylko SQL). Te bazy danych są zasadniczo inne od baz danych SQL – nie organizują danych
w tabelach, dlatego nie można używać składni SQL do skomplikowanych zapytań i złączeń. Są znacznie bardziej elastyczne i
skalowalne niż bazy danych SQL i zostały zaprojektowane, aby przezwyciężyć ograniczenia baz SQL, które mają pewne restrykcje
dotyczące skalowalności i wydajności. Bazy danych SQL można skalować pionowo, ale istnieją ograniczenia tej metody,
o czym już wcześniej rozmawialiśmy.
Bazy danych NoSQL są specjalnie zaprojektowane do obsługi dużych zbiorów danych i obciążeń o dużej prędkości.
Można je skalować poziomo, co stanowi istotną zaletę przy zarządzaniu dużymi aplikacjami. Zgłębimy szczegóły tych korzyści
później w artykule. Na razie przyjrzyjmy się różnym typom baz danych NoSQL.

11) Bazy danych klucz-wartość (key-value)?
Baza danych typu klucz-wartość używa prostego podejścia opartego na parze klucz-wartość do przechowywania danych.
Podobnie jak w przypadku mapy (hashmap), baza danych przechowuje zbiór par klucz-wartość, a klucz pełni rolę unikalnego
identyfikatora. Zarówno klucz, jak i wartość mogą być od prostych obiektów po złożone struktury.
W przeciwieństwie do baz danych relacyjnych, bazy danych klucz-wartość są bezschematowe (co oznacza, że zarządzają danymi
bez potrzeby posiadania szablonu) i różne klucze oraz wartości mogą mieć zupełnie różne struktury. Jeden klucz może być
ciągiem znaków i odnosić się do obiektu JSON, a inny może być liczbą całkowitą i odnosić się do listy.
Dobrym przykładem bazy danych typu klucz-wartość jest Redis. Redis to baza danych typu in-memory, która przechowuje dane
w pamięci. Dzięki temu Redis jest wyjątkowo szybki w pobieraniu danych, ponieważ pamięć RAM jest znacznie szybsza w
porównaniu do przechowywania danych na dysku. Należy jednak pamiętać, że nie wszystkie bazy danych NoSQL przechowują
dane w pamięci RAM.

12) Bazy danych dokumentowe (Document)?
Bazy danych dokumentowe przechowują dane w postaci "dokumentów". Te "dokumenty" są podobne do formatu JSON. Ułatwia to
programistom przechowywanie i zapytania dotyczące danych, ponieważ format, w jakim baza danych przechowuje dane, jest tym
samym formatem modelu dokumentów, który programiści używają w swoim kodzie aplikacji. Zapewnia to elastyczność, ponieważ
poszczególne pola w dokumencie mogą być dodawane lub usuwane niezależnie. Przykładem bazy danych dokumentowej jest MongoDB.

13) Baza danych szerokokolumnowa (Wide-Column Database)?
Baza danych szerokokolumnowa przechowuje dane w kolumnach, a nie wierszach, co pozwala na efektywne przechowywanie i
pobieranie dużych zbiorów danych. Zamiast przechowywać dane w wierszach, jak w tradycyjnych bazach danych relacyjnych,
baza danych szerokokolumnowa przechowuje dane w kolumnach. Każda kolumna odpowiada konkretnemu atrybutowi lub polu danych.
Taka struktura nie tylko umożliwia wysoką przepustowość zapisów, ale także optymalizuje odczyty i agregacje na określonym
podzbiorze danych, co jest operacją, do której tradycyjne przechowywanie oparte na wierszach nie jest przystosowane.
Prawdziwa siła baz danych szerokokolumnowych ujawnia się w scenariuszach wymagających bardzo dużej skali, takich jak
wyszukiwanie w internecie czy systemy wiadomości internetowych. Jeśli aplikacja musi zarządzać dużą ilością danych
związaną z czasem (timestamp), bazy danych szerokokolumnowe będą doskonale radzić sobie z takim zadaniem. Chociaż nie
będziemy wchodzić zbyt głęboko w szczegóły – ponieważ te bazy danych mogą być skomplikowane – ważne jest, aby zauważyć,
że są one specjalizowane w określonych przypadkach użycia i niekoniecznie będą najlepszym rozwiązaniem dla każdej aplikacji.
Innym przykładem tego typu bazy danych jest Cassandra oraz BigTable od Google. BigTable to w pełni zarządzana baza danych
NoSQL typu szerokokolumnowa i klucz-wartość, przeznaczona do obsługi dużych obciążeń analitycznych i operacyjnych w ramach
oferty Google Cloud. Cassandra, podobnie jak BigTable, została zaprojektowana do obsługi dużych ilości danych rozproszonych
na wielu serwerach standardowych, zapewniając wysoką dostępność bez pojedynczego punktu awarii.

14) Bazy danych grafowe (Graph Databases)?
Baza danych grafowa używa struktury przypominającej graf, gdzie każdy węzeł odnosi się do jednostki. Podobnie jak grafy,
które poznaliśmy w algorytmach, węzły w bazie danych grafowej są połączone ze sobą za pomocą krawędzi lub relacji.
Mogą być używane do reprezentowania różnych typów relacji, przyjaźni i powiązań między jednostkami.
Reprezentując te jednostki jako węzły, bazy danych grafowe oferują elastyczny i intuicyjny sposób modelowania złożonych
struktur danych oraz wydajne zapytanie połączeń między jednostkami.
Bazy danych grafowe są szczególnie przydatne, gdy dane mają złożone relacje i wzajemne powiązania. Facebook jest doskonałym
przykładem przypadku, w którym baza danych grafowa może być dobrym rozwiązaniem. Platforma Facebooka opiera się na
relacjach między użytkownikami: przyjaciele, przyjaciele przyjaciół, zainteresowania, hobby. Bazy danych grafowe będą
bardziej odpowiednie do modelowania i zapytań o różne rodzaje interakcji społecznych, komentarzy, polubień, udostępnień
i reakcji. W ten sposób możemy zrozumieć, kiedy może być potrzebna baza danych oparta na grafie.

15) Głębsze zrozumienie, dlaczego potrzebujemy baz danych NoSQL?
Jak wcześniej omawialiśmy, największym problemem baz danych SQL jest skalowalność i ich ograniczenia. Ponieważ brak jest
ograniczeń związanych z kluczami obcymi i złączami, dane mogą być dzielone i przechowywane na różnych serwerach.
Oznacza to, że połowa danych może być przechowywana w jednej bazie danych, w jednej części świata, a druga połowa w innej
bazie danych, w zupełnie innej części świata. Bazy danych NoSQL zostały zaprojektowane z myślą o architekturze rozproszonej.
Często są budowane od podstaw, aby obsługiwać duże ilości danych rozproszone na wielu węzłach.
Rezygnacja z ograniczenia klucza obcego umożliwia to, ponieważ nie musimy przeprowadzać odniesień krzyżowych między tabelami,
co może być nieefektywne.
Innym kluczowym pojęciem, które omawialiśmy w bazach danych relacyjnych, było akronim ACID. Czy bazy danych NoSQL mogą
być zgodne z ACID? Odpowiedź brzmi: tak. Na przykład, MongoDB jest jedną z baz danych NoSQL, która jest zgodna z ACID.
Akronim, który jest używany w kontekście baz danych NoSQL, to BASE. ACID koncentruje się na ścisłej spójności wewnątrz
baz danych, podczas gdy BASE skupia się bardziej na spójności ostatecznej (co wyjaśnimy wkrótce). Nie oznacza to, że jedno
podejście jest lepsze od drugiego, ale ACID jest bardziej powszechne w bazach danych SQL, a BASE lepiej opisuje bazy danych NoSQL.
BASE oznacza "Basically Available, Soft state, Eventual consistency" – część „spójności ostatecznej” (eventual consistency)
będzie tym, na czym się skoncentrujemy.

16) Spójność ostateczna (Eventual Consistency)?
Mechanizm zapewniający spójność ostateczną to architektura (leader/follower). Załóżmy, że mamy trzy repliki bazy danych,
a zapytanie o dane może być skierowane do dowolnej z tych replik. Jednak jedna z nich może być uznana za główny węzeł
bazy danych. Jeden z węzłów w zestawie replik będzie węzłem głównym. Aktualizacje i zapisy mogą być dokonywane tylko w
węźle głównym, który ostatecznie aktualizuje pozostałe węzły. W tym przypadku węzeł główny można nazwać liderem,
a pozostałe węzły to śledzące.
Ponieważ węzły, z wyjątkiem węzła głównego, są aktualizowane dopiero ostatecznie, mogą występować sytuacje,
gdy użytkownik żąda danych, które są nieaktualne. Może to wystąpić w aplikacjach takich jak Twitter czy Instagram,
gdzie liczba obserwujących może być opóźniona, ponieważ węzeł główny nie zaktualizował jeszcze pozostałych węzłów.
Innym terminem używanym dla architektury leader / follower, jest architektura master/slave.

17) Replikacja i Sharding?
Replikacja i sharding to dwie techniki, które często są stosowane razem w systemie rozproszonym, aby osiągnąć wysoką
dostępność i przepustowość.

18) Replikacja?
Kiedy jedna baza danych nie jest w stanie obsłużyć wszystkich nadchodzących zapytań, wchodzi w grę replikacja.
Replikacja polega na tworzeniu kopii bazy danych, zwanej repliką. Replika jest hostowana na osobnej maszynie lub serwerze
i jest synchronizowana z oryginalną bazą danych. Replikacja jest stosowana w celu zwiększenia dostępności danych,
poprawy skalowalności i zwiększenia trwałości danych.
Oryginalna baza danych to lider lub master, natomiast replika to follower lub slave.
W architekturze lider/follower replikacja danych odbywa się z lidera do followera, a lider odpowiada za aktualizowanie
followera. Gdyby replikacja była próbą z followera do lidera, lider nie byłby w pełni zaktualizowany.
Replikacja może przebiegać na dwa sposoby: asynchronicznie i synchronicznie.

19) Replikacja synchroniczna?
W replikacji synchronicznej każda transakcja zapisu na liderze jest natychmiast replikowana na followerze, zapewniając
spójność między dwiema replikami. Jednak podejście to wprowadza opóźnienie. Korzyścią jest to, że jeśli lider ulegnie awarii,
w pełni zaktualizowany follower może przejąć jego rolę, zapewniając wysoką dostępność.
Więc choć replikacja jest synchroniczna i dane będą spójne, klient 1 otrzyma informację zwrotną dopiero po zakończeniu
replikacji przez lidera.

20) Replikacja asynchroniczna?
Replikacja asynchroniczna wiąże się z opóźnieniem w replikacji danych. Baza danych lidera zatwierdza transakcję i wysyła
dane replikacji do followera, nie czekając, aż follower potwierdzi lub zastosuje zmiany natychmiast. To zmniejsza opóźnienia,
ale oznacza, że jeśli klient wysyła zapytanie do followera, zanim lider go zaktualizuje, dane mogą być nieaktualne,
aż lider je zaktualizuje. Jest to kompromis, który umożliwia zwiększoną dostępność.
Ponieważ dane są replikowane asynchronicznie, klient 2 może otrzymać nieaktualne dane po wysłaniu zapytania.

21) Replikacja Master-Master (Multi-Master)?
Replikacja Master-Master jest stosowana, gdy dane muszą być serwowane w różnych regionach. Na przykład, jeden lider może
obsługiwać zachód, a inny wschód. Oba liderzy mogą być zapisywane i odczytywane, co czyni tę metodę idealną do dystrybucji
danych w różnych częściach świata. Jednak synchronizacja między liderami może stanowić wyzwanie, a środki takie jak
okresowe aktualizacje są potrzebne, aby utrzymać je w synchronizacji.

22) Sharding
Sharding jest stosowane, gdy sama replikacja nie wystarcza do obsługi dużego wolumenu ruchu na jednej bazie danych.
Polega to na podzieleniu bazy danych na mniejsze fragmenty, zwane shardami, z których każdy jest hostowany na osobnej
maszynie lub serwerze. Rozdzielając dane i obciążenie na wiele shardów, system osiąga lepszą wydajność, skalowalność
i dostępność. Każdy shard zawiera tylko część całego zestawu danych i nie ma pełnej kopii oryginalnej bazy danych.
Teraz, ponieważ mamy wiele shardów, co decyduje o tym, jak dane są partycjonowane?
Jednym z podejść jest podejście oparte na zakresach. W tym podejściu dane są dzielone według zakresów. Jeśli zastosujemy
to do naszego przykładu z 100 wierszami i czterema shardami, moglibyśmy podzielić je na zakresy: 1-25, 26-50, 51-75 i 76-100,
gdzie te numery to identyfikatory.
Określenie, jak dane są partycjonowane między shardami, odbywa się za pomocą klucza shardu. Klucz shardu to wybrany
kryterium lub atrybut, który decyduje, do którego sharda należy każde dane. Na przykład w bazie danych relacyjnej klucz
shardu może opierać się na płci, dzieląc dane na mężczyzn i kobiety. Alternatywnie, może to być podział na podstawie
imienia i nazwiska, na przykład dzieląc dane na shardzie od A do L i od M do Z.

23) Wyzwania związane z shardingiem
Chociaż sharding jest skuteczny w obsłudze dużego ruchu, wiąże się z własnymi wyzwaniami. Na przykład, przy pracy z
setkami tabel, zapewnienie, że powiązane tabele z powiązanymi danymi trafią do tego samego shardu, może być skomplikowane.
Dodatkowo, utrzymanie właściwości ACID (Atomowość, Spójność, Izolacja, Trwałość) stanowi wyzwanie przy shardingu baz
danych relacyjnych, ponieważ nie zostały one zaprojektowane z myślą o dystrybucji. Bazy danych SQL, takie jak MySQL czy
PostgreSQL, nie obsługują natywnie sharding, co zmusza programistów do implementowania logiki shardingu na poziomie
aplikacji, co może stać się skomplikowane.
Z drugiej strony, bazy danych NoSQL, zaprojektowane z myślą o skalowaniu poziomym, lepiej nadają się do shardingu,
ponieważ nie mają tych samych ograniczeń co bazy danych relacyjne. Oferują one spójność ostateczną, gdzie spójność
danych między węzłami osiągana jest w czasie.
Ponieważ nasze dane w bazach danych NoSQL są nierelacyjne, nie mamy tych samych ograniczeń, które występują przy używaniu
baz danych relacyjnych. Bazy danych NoSQL zostały zaprojektowane z myślą o skalowaniu poziomym, więc te ograniczenia nie
mają aż takiego zastosowania w przypadku baz nierelacyjnych. Dane nie muszą być nawet spójne między węzłami — wystarczy,
że będą spójne ostatecznie.
Spójność ostateczna odnosi się do właściwości systemu rozproszonego, szczególnie w bazach danych NoSQL, gdzie repliki
danych ostatecznie staną się spójne ze sobą w czasie. Oznacza to, że po operacji zapisu różne repliki mogą tymczasowo
przechowywać różne wersje danych, ale ostatecznie zostaną one zaktualizowane do spójnego stanu.
Możemy również zastosować sharding oparty na haszowaniu, co jest dobrym przypadkiem użycia haszowania spójnego,
które omawialiśmy wcześniej. Haszowanie spójne pomaga rozdzielać dane między shardy w sposób, który minimalizuje ruch
danych i procesy przekształcania (rebalansowania), gdy węzły są dodawane lub usuwane z systemu.

24) CAP Theorem
CAP oznacza Spójność (Consistency), Dostępność (Availability) i Tolerancję na Podziały (Partition Tolerance).
Koncepcja sugeruje, że system rozproszony może zapewnić jedynie dwa z tych trzech gwarancji jednocześnie –
albo Tolerancję na Podziały i Dostępność, albo Tolerancję na Podziały i Spójność, ale nigdy wszystkie trzy naraz.
W istocie, kompromisy są nieuniknione. Teoria CAP jest w dużej mierze stosowana do baz danych NoSQL, ponieważ te bazy
danych naturalnie wspierają replikację. W związku z tym, kluczowym aspektem tej teorii jest to, że odnosi się ona tylko
do baz danych podzielonych (partitioned databases).

25) Partition Tolerance
Podział w systemie rozproszonym występuje, gdy awaria komunikacji między węzłem lidera a węzłem naśladowcy uniemożliwia
liderowi zaktualizowanie naśladowcy. Do takich sytuacji mogą prowadzić różne czynniki, takie jak awarie sieci,
problemy sprzętowe lub inne incydenty, które uniemożliwiają niektórym węzłom interakcję z innymi.
Jeśli system wykazuje tolerancję na podziały, oznacza to, że może nadal funkcjonować pomimo awarii sieciowych,
unikając całkowitego załamania systemu.
W teorii CAP tolerancja na podziały jest traktowana jako założenie.

26) Consistency
Spójność, w kontekście teorii CAP, odnosi się do jednolitości danych między węzłami lidera a węzłami naśladowcy.
Nie należy tego mylić ze spójnością w modelu ACID.
Spójność zapewnia, że wszystkie węzły w systemie postrzegają dane w sposób identyczny w danym momencie.
W przypadku aktualizacji danych klienci zawsze będą mieć dostęp do spójnych danych.
Bez względu na to, z którego węzła klient pobiera dane, informacje będą spójne we wszystkich potencjalnych węzłach
odczytujących dane. Każdy odczyt zwróci najnowsze zapisane dane.
Jeśli nasz system pozostaje podzielony, a dane są zapisywane w bazie danych lidera, klient odczytujący dane z bazy danych
lidera otrzyma najnowsze dane. Jednakże, ponieważ aktualizacje bazy danych naśladowcy są zablokowane, odczyt z niej może
zwrócić nieaktualne dane. Możliwym rozwiązaniem może być uczynienie węzła naśladowcy redundantnym, aby zapewnić, że nie
będą odczytywane nieaktualne dane. To wprowadza pojęcie dostępności.

27) Availability
Dostępność, w kontekście teorii CAP, różni się od jej definicji w modelu ACID. Tutaj dostępność oznacza, że każde żądanie
systemowe otrzymuje odpowiedź, niezależnie od tego, czy jest to odpowiedź pozytywna, czy błąd, bez względu na awarie systemu.
Zapewnia to, że system pozostaje operacyjny i może zarządzać żądaniami nawet w obliczu awarii.
Jak wspomniano wcześniej, zgodnie z teorią CAP, system może posiadać jedynie dwie z trzech właściwości —
Spójność, Dostępność i Tolerancję na Podziały. Zwiększenie dostępności systemu wpływa na jego spójność, co oznacza,
że każdy indywidualny węzeł będzie odpowiadał na każde ważne żądanie.

28) Consistency or Availability
Decyzja o priorytetach spójności lub dostępności w dużej mierze zależy od specyficznych wymagań aplikacji oraz jej celów.

29) Wybór dostępności kosztem spójności.
W systemach takich jak system zarządzania nauczaniem na uczelni (LMS), wysoka dostępność może być bardziej kluczowa.
Na przykład, jeśli student próbuje przesłać zadanie, LMS musi być dostępny, aby przyjąć przesyłkę. Nawet jeśli nastąpi
drobne opóźnienie w aktualizacji oceny, prawdopodobnie nie wpłynie to negatywnie na działanie systemu.
Wiele nowoczesnych baz danych stara się znaleźć równowagę między spójnością a dostępnością, zamiast ściśle trzymać się
modelu CP (Consistency and Partition tolerance) lub AP (Availability and Partition tolerance). Te bazy danych często
oferują "tunable consistency" (dopasowywaną spójność), pozwalając systemowi dynamicznie przełączać się między większą
spójnością i mniejszą dostępnością, a większą dostępnością i mniejszą spójnością.

30) Choosing Consistency over Availability
W scenariuszach, w których dokładność danych jest absolutnie kluczowa, takich jak systemy bankowe i opieki zdrowotnej,
należy priorytetowo traktować spójność. W systemie bankowym saldo konta musi być poprawne i spójne na wszystkich węzłach.
Jeśli wystąpi podział sieci, może być akceptowalne wstrzymanie operacji, ale nadal należy zapewnić, że dane pozostaną spójne.
Podobnie, w opiece zdrowotnej, posiadanie dokładnych i aktualnych danych medycznych jest sprawą życia i śmierci,
a niespójne dane prowadzą do poważnych konsekwencji, dlatego priorytetowanie spójności jest kluczowe.

31) C A P Theorem Visualized
Ludzie często wizualizują teorię C A P za pomocą diagramu Venna. Zgodnie z tradycyjnym rozumieniem teorii C A P, nie jest
możliwe osiągnięcie przecięcia wszystkich trzech aspektów: dostępności, spójności i tolerancji na podziały.
Dlatego możliwe jest jedynie osiągnięcie przecięcia między spójnością a tolerancją na podziały, lub między dostępnością
a tolerancją na podziały.

32) PACELC Theorem (rozszerzenie teorii CAP)
Teoria C A P głównie dotyczy zachowania systemu w przypadku braku podziału sieciowego. Jednak teoria PACELC dostarcza
dodatkowych wskazówek, stwierdzając:
"Podczas podziału sieciowego (P), wybierz A (dostępność) lub C (spójność). W przeciwnym razie preferuj Latencję lub Spójność."
Aby zgłębić temat, gdy występuje podział sieciowy, mamy dwie opcje: priorytetem może być dostępność lub spójność.
Kontynuując w paradygmacie lider-naśladowca (leader - follower), w przypadku braku podziału sieciowego musimy wybrać,
czy preferujemy latencję, czy spójność. Oznacza to, że użytkownicy będą otrzymywać dane albo spójne, albo dane o niskiej latencji.
Warto zauważyć, że spójność w teorii CAP odnosi się do zapewnienia, że najbardziej aktualne informacje są dostępne na
wszystkich węzłach w systemie rozproszonym, podczas gdy spójność w modelu ACID odnosi się do utrzymania integralności
transakcyjnej i zapewnienia, że transakcje nie naruszają żadnych ograniczeń integralności.
Wybór między dostępnością a spójnością jest w rzeczywistości kwestią projektowania systemu i jego wymagań.
Na przykład, niektóre aplikacje mogą pozwolić sobie na serwowanie nieco przestarzałych danych.

33) Object Storage
Zajmiemy się tym, czym jest przechowywanie obiektów i gdzie znajduje swoje zastosowanie w wywiadach dotyczących
projektowania systemów. Systemy przechowywania obiektów zyskały dużą popularność w ciągu ostatnich 10-15 lat.
Przechowywanie obiektów jest bardziej podobne do systemów przechowywania plików niż do tradycyjnych baz danych.

34) Bazy danych vs. Przechowywanie obiektów – Jaka jest różnica?
W bazach danych sposób filtrowania i wyszukiwania danych ma ogromne znaczenie. Natomiast w przypadku przechowywania
obiektów pojęcie folderów nie istnieje. Zarówno bazy danych, jak i przechowywanie obiektów mogą przechowywać dane,
ale kluczowe różnice dotyczą struktury, dostępności i skalowalności danych. W systemie plików dane są organizowane
w strukturze hierarchicznej przypominającej drzewo, w którym pliki są przechowywane w folderach, które mogą być
zagnieżdżone w innych folderach.
Z kolei przechowywanie obiektów traktuje każdą jednostkę danych jako obiekt, który składa się z samego data, metadanych
i unikalnego identyfikatora. W przechowywaniu obiektów nie ma hierarchii, w przeciwieństwie do systemu plików.
Obiekty są przechowywane w płaskich przestrzeniach adresowych, co ułatwia skalowalność w porównaniu do systemów
przechowywania plików, dzięki braku złożoności hierarchicznej. Przechowywanie obiektów wyewoluowało z przechowywania
B L O B (Binary Large Object) i jest powszechnie stosowane do przechowywania takich elementów jak obrazy, filmy i kopie
zapasowe baz danych. Przykłady to AWS S3 i Google Cloud Storage.
Z perspektywy projektowania systemów warto zauważyć, że przechowywanie obrazów lub filmów w bazie danych zwykle nie jest
zalecaną praktyką. Zapytania o konkretne obrazy lub filmy w bazie danych są rzadkie, a przechowywanie takich danych w
bazie może negatywnie wpływać na wydajność, zwiększać wymagania dotyczące pamięci masowej i prowadzić do częstych
operacji odczytu i zapisu na bazie danych.
Tradycyjne RDBMS (relacyjne systemy zarządzania bazami danych) nie są zoptymalizowane do obsługi dużych plików,
ale przechowywanie obiektów stanowi rozwiązanie tego problemu. Zostało ono specjalnie zaprojektowane do efektywnego
przechowywania danych niestrukturalnych i świetnie nadaje się do przechowywania dużych plików. Jedną z istotnych zalet
korzystania z przechowywania opartego na obiektach jest jego skalowalność, umożliwiająca łatwe skalowanie płaskiej
architektury bez napotkania ograniczeń związanych z przechowywaniem plików.
Podczas pobierania danych z magazynu obiektów, zazwyczaj nie wykonuje się bezpośrednich odczytów z samego magazynu
obiektów. Zamiast tego, wysyłane jest żądanie sieciowe HTTP bezpośrednio do przechowywania obiektów w celu pobrania danych.
W wywiadach projektowych systemów, przechowywanie obiektów jest często wykorzystywane do przechowywania obrazów i filmów,
na przykład za pomocą Amazon Simple Storage Service (Amazon S3).

## BIG DATA

1) Message Queues?
Kolejki wiadomości oferują rozwiązanie w scenariuszach, w których serwer aplikacji zmaga się z dużą liczbą żądań, których 
nie jest w stanie przetworzyć jednocześnie. Można by się zastanawiać, dlaczego po prostu nie skalować serwera poziomo lub 
pionowo? Choć takie podejście jest możliwe, nie zawsze jest opłacalne lub praktyczne. Dodatkowo, mogą występować przypadki, 
w których natychmiastowe przetworzenie tych żądań nie jest konieczne, co pozwala na ich umieszczenie w kolejce do późniejszego 
przetworzenia.
Kolejki wiadomości służą do oddzielania producentów (wydarzenia aplikacji) od konsumentów (serwery aplikacji), pełniąc rolę 
bufora do zarządzania skokami w danych.
Przykład:
Przetwarzanie płatności stanowi dobrą ilustrację systemu, w którym kolejki wiadomości mogą przynieść znaczne korzyści.
Obsługa szczytów obciążenia: W okresach dużego obciążenia, takich jak wielka wyprzedaż, liczba żądań płatności zazwyczaj 
znacznie wzrasta. Jeśli te żądania byłyby przetwarzane synchronicznie, mogłoby to prowadzić do złych doświadczeń użytkowników 
z powodu długich czasów oczekiwania i potencjalnych przekroczeń czasu oczekiwania (timeoutów). Jednak przy użyciu kolejek 
wiadomości, płatności mogą być przechowywane i przetwarzane asynchronicznie.
Oddzielanie usług: Gdy składane jest nowe zamówienie, wiadomość może zostać opublikowana w kolejce. Usługa płatności, 
działająca jako subskrybent, może następnie przetworzyć płatność i zaktualizować status zamówienia.

2) Model Push/Pull?
Istnieje kilka metod, w jaki sposób kolejka wiadomości może współpracować z serwerem aplikacji. W zależności od usługi i 
architektury systemu, aktualizacje mogą być oparte na modelu pull lub push.
Model Pull (ciągnięcie): W tym scenariuszu aplikacja jest odpowiedzialna za monitorowanie kolejki wiadomości w poszukiwaniu 
nowych wiadomości. Jeśli pojawią się nowe wiadomości i serwer aplikacji ma odpowiednią pojemność, "ciągnie" wiadomości 
z kolejki. To podejście może być bardziej efektywne pod względem zarządzania obciążeniem po stronie serwera. Jednak jeśli 
kolejka jest pusta, może to wprowadzić opóźnienia.
Model Push (pchanie): W tym przypadku to kolejka odpowiada za "pchnięcie" wiadomości do serwera. Jednak ta strategia może 
przeciążyć serwer, jeśli tempo napływających wiadomości będzie zbyt wysokie.
Kiedy kolejka wiadomości wysyła wiadomość do serwera aplikacji, konsument lub serwer wysyła potwierdzenie po pomyślnym 
przetworzeniu wiadomości (podobnie jak omówiliśmy to w sekcji dotyczącej sieci).
Jeśli kolejka nie otrzyma potwierdzenia wiadomości w określonym czasie, może wnioskować, że wiadomość nie została 
przetworzona, co skłania ją do ponownego jej wysłania. To podejście, podobne do tego, o którym mówiliśmy w rozdziale o 
sieciach, zapewnia dostarczenie wiadomości, nawet w przypadku tymczasowych problemów z serwerem.

3) Model Pub/Sub?
Model publikujący/subskrybujący (Pub/Sub) zapewnia oddzielenie wydawców (publisherów) i subskrybentów (subscriberów), 
eliminując potrzebę wzajemnej świadomości ich istnienia. Pozwala to na łatwą skalowalność systemu i zapewnia, że wiadomości 
nie zostaną utracone, jeśli subskrybent tymczasowo nie będzie w stanie ich przetworzyć.
W kontekście kolejki wiadomości, model pub/sub przechodzi przez następujące kroki:
Wydawca (publisher) wysyła wiadomości do określonej kolejki lub tematu.
Jeden lub więcej subskrybentów (subscribers) nasłuchuje określoną kolejkę lub temat.
Broker wiadomości zapewnia, że wszystkie wiadomości opublikowane w danym temacie są pomyślnie dostarczane do wszystkich 
subskrybentów tego tematu. Subskrybenci przetwarzają wiadomości niezależnie i we własnym tempie.
W kontekście modelu pub/sub, wydawcy wysyłają swoje wiadomości do określonych tematów, a subskrybenci wskazują swoje 
zainteresowanie, subskrybując te tematy. „Temat” (topic) to kategoria lub etykieta, która służy jako kanał dla podobnych 
wiadomości. Pomaga to w kategoryzowaniu ogromnej liczby wiadomości, które mogą być odbierane. Możemy mieć wielu 
subskrybentów subskrybujących ten sam temat. Z tego miejsca logika aplikacji odbiera dane od tych subskrybentów.
W naszym przykładzie płatności, temat „OrderPlaced” może mieć wielu subskrybentów, takich jak Usługa Zarządzania Zapasami 
(aktualizująca poziom zapasów) i Usługa Rozliczeń (która obciąża klienta). Wszyscy ci subskrybenci muszą zostać powiadomieni, 
gdy złożono zamówienie.
Inną zaletą modelu pub/sub jest to, że możemy wprowadzić zupełnie nowe API jako subskrybenta, nie zmieniając naszej 
architektury pub/sub. Dzięki temu nowi subskrybenci mogą zostać dodani do tematu bez modyfikowania wydawców. To sprawia, 
że system jest bardziej elastyczny i łatwiejszy do dostosowania do zmieniających się wymagań.























