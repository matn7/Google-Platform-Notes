GCP

1) Wskaźnik Poziomu Usługi (SLI)?
Ilościowy pomiar aspektu usługi. Kategorie: dostępność, opóźnienia, przepustowość, trwałość, poprawność (wskaźnik błędów).
("availability", "latencies", "throughput", "durability"). Zwykle agregowane - np. „przez 1 minutę”.

2) Jaki jest najprostszy sposób implementacji uwierzytelniania i autoryzacji dla aplikacji wdrożonych w "App Engine", 
"Cloud Run" i "GKE"?
"Identity-Aware Proxy (IAP)".

3) Twoje mikrousługi będą wdrażane w "GKE". Chcesz nadać wspólne uprawnienia do "Cloud Logging" i "Cloud Monitoring"?
Konto Usługowe Puli Węzłów ("Node Pool Service Account").

4) Która usługa Google Cloud jest zalecana do implementacji pipeline "CI-CD"?
"Cloud Build".

5) Projektowanie "API gRPC"?
"gRPC": Oparte na modelu Wywołania Zdalnej Procedury ("RPC"). 
a) "createTodo(username, description)" - "todo_id". Jednostki adresowalne - procedury. 
b) Zalety: Ukrywa szczegóły HTTP przed deweloperami (używa HTTP/2 w tle). Generowanie stubów i szkieletów "gRPC". 
Klienci używają stubów do wykonywania wywołań gRPC. Dostawcy usług używają szkieletów do implementacji usług. 
Zapewnia bardzo dobrą wydajność: używa ładunków binarnych - "Protobuff". Efektywne zarządzanie połączeniami (dzięki HTTP2). 
c) Wady: API REST są łatwiejsze w użyciu, nie wymagają żadnych specjalnych frameworków/bibliotek.

6) Bazy danych w pamięci / pamięci podręczne?
"Cloud Memorystore". Aplikacje wymagające odpowiedzi w czasie mikrosekund.

7) Bazy danych "No SQL"?
"Cloud Firestore (Datastore)", "Cloud BigTable". Aplikacje wymagające szybko ewoluującej struktury (bez schematu).
"Cloud Firestore": Bezserwerowa transakcyjna baza danych dokumentów wspierająca aplikacje mobilne i internetowe. 
Małe do średnich baz danych (od 0 do kilku terabajtów).
"Cloud BigTable": Duże bazy danych (10 TB - PB). Przesyłanie strumieniowe (IoT), obciążenia analityczne i operacyjne. 
NIE jest bezserwerowe ("serverless").

8) Relacyjne bazy danych OLAP?
"BigQuery". Przechowywanie kolumnowe z predefiniowanym schematem. Hurtownie danych i obciążenia "BigData".

9) Relacyjne bazy danych OLTP?
"Cloud SQL", "Cloud Spanner". Przypadki użycia transakcyjnego wymagające predefiniowanego schematu oraz bardzo silnych 
zdolności transakcyjnych (przechowywanie wierszy).
"CloudSQL": Bazy danych "MySQL", "PostgreSQL", "SQL Server".
"Cloud Spanner": Nielimitowana skala i "99.999%" dostępności dla aplikacji globalnych z poziomym skalowaniem.

10) Cloud SQL Auth Proxy?
Jak uprościć zarządzanie połączeniami i zapewnić bezpieczne (HTTPS) połączenie między klientami a serwerem SQL? 
"Cloud SQL Auth Proxy". Komunikacja szyfrowana protokołem "TLS1.2" z użyciem szyfru "AES 128-bit". 
Nie martw się o certyfikaty. Prostszy menedżer połączeń. Wymaga izolacji klienta Proxy na maszynie klienckiej. 
Może być używane do połączeń z lokalnego środowiska. Działa zarówno z prywatnymi, jak i publicznymi adresami IP.
Cloud SQL Auth Proxy jest automatycznie wspierane przez niektóre usługi GCP, takie jak "App Engine" "Standard" i "Cloud Run".

11) "Cloud SQL" - Wysoka dostępność?
Utwórz konfigurację Wysokiej Dostępności (HA). Wybierz strefy "Primary" i "Secondary" w ramach regionu. 
Będziesz mieć dwie instancje: instancję "Primary" i instancję "Secondary". Zmiany z instancji "Primary" są synchronizowane 
do instancji "Secondary". W przypadku awarii strefy, następuje automatyczne przełączenie na instancję "Secondary": 
Jeśli strefa "Primary" stanie się dostępna, przełączenie na instancję "Secondary" zostaje zachowane. 
Jeśli strefa "Primary" stanie się dostępna, przełączenie nie następuje automatycznie. Pamiętaj, że konfiguracja 
Wysokiej Dostępności nie może być używana jako "Read Replica".

12) Scenariusze "Cloud Storage". Zgodność z przepisami: Obiekt nie powinien być modyfikowany przez 2 lata?
Skonfiguruj i zablokuj politykę przechowywania danych ("data retention policy").

13) Scenariusze "Cloud Storage". Klient chce zarządzać swoimi kluczami.
Klucze zarządzane przez klienta ("Customer managed key"), zarządzane przez klienta w "Cloud KMS".

14) Scenariusze "Cloud Storage". Będę często uzyskiwać dostęp do obiektów w "bucket" przez 30 dni. Po tym czasie nie 
będę oczekiwał dostępu do obiektów. Mamy wymagania dotyczące zgodności, które nakazują przechowywanie obiektów przez 4 lata. 
Jak mogę zminimalizować koszty?
Początkowa klasa przechowywania: "Standard". Polityka cyklu życia ("Lifecycle policy"): 
Przenieś do klasy "Archive" po 30 dniach. Usuń po 4 latach.

15) Konsumpcja API Google Cloud?
Jak działa uwierzytelnianie dla bibliotek klienckich? 
a) Biblioteki klienckie szukają zmiennej środowiskowej "GOOGLE_APPLICATION_CREDENTIALS" - jeśli jest obecna, używany jest 
przypisany do niej konto usługi. 
b) Użyj konta usługi przypisanego do zasobu, na którym działa Twój kod. W przeciwnym razie zostanie zgłoszony błąd. 
Zalecane, jeśli używasz "Compute Engine", "Google Kubernetes Engine", "App Engine", "Cloud Run" lub "Cloud Functions" do 
uruchamiania aplikacji: przypisz konto usługi ("service account") do zasobów.

























