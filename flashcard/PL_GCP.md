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

16) Pytanie: Który tryb transakcji w Cloud Spanner jest zalecany, aby zapewnić, że tabela nie zostanie zablokowana podczas 
masowej aktualizacji?
DML partycjonowane (Partitioned DML).

17) Jak połączyć się z Cloud SQL z środowisk bezserwerowych za pomocą prywatnego adresu IP?
Użyj dostępu do VPC bezserwerowego (serverless VPC Access).

18) Jak połączyć się z instancji VM znajdującej się w VPC do instancji Cloud SQL za pomocą prywatnego adresu IP?
Utwórz połączenie usługi prywatnej (Private Service Connection).

19) Jak połączyć się z Cloud SQL z instancji VM lub GKE w GCP za pomocą publicznego adresu IP?
Użyj Cloud SQL Auth Proxy.

20) Zalecane dla nowych aplikacji mobilnych i webowych, które potrzebują aktualizacji w czasie rzeczywistym i funkcji offline?
Firestore w trybie natywnym (Firestore in Native mode).

21) Cloud Dataflow?
Używane do eksportowania danych z BigTable do Cloud Storage. Na przykład: Cloud BigTable do Cloud Storage Avro, 
Cloud BigTable do Cloud Storage Parquet.

22) Przypadek użycia dla Anthos Service Mesh?
Canary deployments (rozproszenie ruchu pomiędzy dwoma wersjami tego samego mikrousługi). 
mTLS: dwukierunkowe TLS pomiędzy podami. Monitorowanie SLO.

23) Jak uzyskać ogólny obraz bezpieczeństwa swojego projektu?
Security Command Center.

24) Jak wykrywać zagrożenia dla kontenerów w czasie rzeczywistym?
Container Threat Detection.

25) Trzy filary obserwowalności?
Logi (Logs), metryki (metrics), ślady (traces).

26) Zalecany przepływ do obsługi danych strumieniowych w Google Cloud?
Pub/Sub, Dataflow, BigQuery.

27) Usługi do harmonogramowania asynchronicznych (schedule asynchronous tasks) zadań (z Twoich mikrousług) z wyraźnym 
wywołaniem w Google Cloud?
Cloud Tasks.

28) Prosta architektura do wykonywania zapytań w czasie rzeczywistym na pojedynczej tabeli zawierającej ogromną ilość 
danych szeregów czasowych (time series data), które są ciągle przesyłane?
Pub/Sub, Dataflow, BigTable.

29) Które usługi można wykorzystać do tworzenia dashboardów i wizualizacji danych przechowywanych w BigQuery?
Cloud Data Studio.

30) Która usługa może być użyta do czyszczenia danych załadowanych z zewnętrznych źródeł?
Cloud Dataprep.

31) Docker Images, najlepsze praktyki?
Używaj lekkich obrazów (preferuj Alpine Linux zamiast Ubuntu). Nie kopiuj niczego, co jest niepotrzebne 
(na przykład node-modules). Przenieś rzeczy, które zmieniają się rzadziej, na górę, aby umożliwić ponowne użycie warstw.

32) Tworzenie obrazów Docker - Dockerfile?
Instrukcje do tworzenia obrazu Docker:
FROM: ustawia obraz bazowy.
WORKDIR: ustawia katalog roboczy.
RUN: wykonuje polecenie.
EXPOSE: informuje Dockera o porcie, na którym kontener nasłuchuje w czasie rzeczywistym.
COPY: kopiuje nowe pliki lub katalogi do obrazu.
CMD: domyślne polecenie dla uruchamianego kontenera.

33) Która usługa jest zalecana dla zaawansowanych wdrożeń między chmurami?
Spinnaker.

34) Które podejście do wdrożeń daje możliwość testowania, czy użytkownicy lubią funkcję w środowisku produkcyjnym?
Testowanie A/B.

35) Które podejście do wdrożeń pozwala na przeprowadzenie wdrożenia bez przestojów bez używania dodatkowej infrastruktury?
Deployment kanarkowy (Canary Deployment).

36) GKE - Wydawanie nowych wersji. Spinnaker versus Cloud Build?
Cloud Build lub Spinnaker mogą być używane do budowania obrazów Docker (CI). Cloud Build może wykonywać proste wdrożenia. 
Dla zaawansowanych zautomatyzowanych wdrożeń między chmurami zalecany jest Spinnaker.

37) GKE - Wydawanie nowej wersji. Canary Deployment?
Utwórz nowe wdrożenie. Użyj Service Mesh, takiego jak Istio. Anthos Service Mesh to usługa GCP dla Istio, lub możesz 
użyć Spinnakera.

38) GKE - Releasing New Versions. Blue Green Deployment?
Create new deployment. Control traffic using Ingress (or service) or you can use Spinnaker.

39) GKE - Wydawanie nowych wersji. Rolling Update i Recreate?
Ustaw typ strategii w Deployment.

40) App Engine - Wydawanie nowych wersji?
a) Wdróż i przenieś cały ruch na raz: "gcloud app deploy".
b) Wdróż wersję "v2" bez przenoszenia ruchu: "--no-promote".
c) Przenieś cały ruch do "v2": "gcloud app service set-traffic s1 --splits v1=1".
d) Stopniowa migracja: Dodaj opcję "--migrate" do poprzedniego polecenia.
e) Testowanie A/B: "gcloud app series set-traffic s1 --splits=v2=.5,v1=.5".

41) Podejścia do wdrożeń MIG - Blue Green Deployment?
Utwórz nowy MIG i dokonaj ręcznych dostosowań do backendów load balancera w razie potrzeby.

42) Wdrażaj tylko zaufane obrazy kontenerów do Google Cloud?
Binary Authorization.

43) Skanuj obrazy kontenerów pod kątem luk bezpieczeństwa?
Container Scanning API.

44) Wykryj, czy Twoja VM jest wykorzystywana do kopania Bitcoina?
Anomaly Detection.

45) Chroń swoje aplikacje przed atakami DDoS i powszechnymi lukami bezpieczeństwa?
Cloud Armour.

46) Skanuj i znajdź powszechne luki bezpieczeństwa, takie jak cross-site scripting (XSS), w aplikacjach App Engine.
Web Security Scanner.

47) Dowiedz się, który bucket storage zawiera dane wrażliwe?
Cloud DLP (Data Loss Prevention).

48) Chcesz rozwiązać problemy z wydajnością, identyfikując części aplikacji, które zużywają najwięcej zasobów?
Cloud Profiler.

49) Chcesz przejrzeć logi dla konkretnego żądania?
Cloud Logging.

50) Najlepsze praktyki dla Cloud Firestore?
Cloud Firestore to magazyn dokumentów z elastycznym schematem: Zalecany do przechowywania takich rzeczy jak profile 
użytkowników. Indeksuj obiekty przechowywane w Cloud Storage (np. zdjęcie profilowe). Projektuj klucze i indeksy ostrożnie: 
rozkładaj klucze równomiernie, unikaj monotonicznie rosnących wartości kluczy. Twórz bazy danych najbliżej użytkowników 
i zasobów obliczeniowych. Preferuj operacje wsadowe (batch operations). Ograniczenia Firestore: 1 zapis na sekundę na 
dokument, 1 milion równoczesnych połączeń na bazę danych. Stopniowo zwiększaj ruch zgodnie z zasadą "500 - 50 - 5": 
rozpocznij z maksymalnie 500 operacjami, zwiększaj o 50% co 5 minut.

51) Chcesz debugować problem w środowisku produkcyjnym, warunkowo dodając punkt logowania lub przechwycając zrzut?
Cloud Debugger.

52) Chcesz śledzić żądanie przez wiele mikrousług?
Cloud Trace.

53) Chcesz zidentyfikować istotne wyjątki (lub błędy) dla konkretnej mikrousługi?
Error Reporting.

54) Chcesz stworzyć dashboard pokazujący, ile razy występuje podciąg (substring) w logu?
Utwórz metrykę opartą na liczniku logów.

55) Jak możesz wyzwolić alert, gdy Twoja aplikacja jest niedostępna?
Skonfiguruj sprawdzenie dostępności w Cloud Monitoring.

56) Najlepsze praktyki dla Cloud SQL?
a) Używaj proxy uwierzytelniania Cloud SQL (cloud SQL auth proxy), kiedy to możliwe.
b) Używaj puli połączeń (connection pooling) i wykładniczego opóźnienia (exponential backoff).
c) Trzymaj transakcje krótkie i małe.
d) Używaj prywatnego IP, kiedy to możliwe.
e) Zrozum skalowalność: Włącz konfigurację HA (wysoka dostępność) dla HA: instancja główna i zapasowa utworzone w tym 
samym regionie (pamiętaj - regionalne). Read replicas pomagają odciążyć obciążenia związane z odczytami 
(raportowanie, analityka itp.). Pamiętaj, że Read Replicas nie zwiększają dostępności. Preferuj kilka małych instancji 
Cloud SQL zamiast jednej dużej instancji.

57) Jak połączyć się z instancją Cloud SQL?
Preferuj prywatne adresy IP zamiast publicznych. Używaj dostępu do VPC bezserwerowego dla połączeń prywatnym IP w 
środowiskach bezserwerowych, takich jak App Engine, Cloud Functions, Cloud Run. Użyj proxy uwierzytelniania Cloud SQL 
w tych scenariuszach: Połączenie za pomocą publicznego IP z środowisk bezserwerowych (App Engine, Cloud Functions, 
Cloud Run). Połączenie za pomocą publicznych/prywatnych IP z innych środowisk, takich jak GCE VM, GKE itd. Jeśli nie 
używasz proxy uwierzytelniania Cloud SQL, zaleca się używanie własnoręcznie zarządzanych certyfikatów SSL/TLS.

58) Najlepsze praktyki dla IAM?
Zasada najmniejszych uprawnień (Principle of Least Privilege:): Przyznawaj najmniejsze możliwe uprawnienia potrzebne do roli.
Podział obowiązków: Zaangażuj co najmniej 2 osoby w wrażliwe zadania. Na przykład, stwórz oddzielne role do wdrożeń i migracji ruchu.
Ciągłe monitorowanie.
Używaj grup, kiedy to możliwe.

59) Jakie opcje należy włączyć w podsieci, aby umożliwić VM-om w podsieci łączenie się z interfejsami API Google za 
pomocą prywatnych adresów IP?
Włącz dostęp do Google przez prywatny IP (Private Google Access).

60) Cloud VPN?
Cloud VPN - Łączy sieć lokalną z siecią GCP. Zrealizowane za pomocą tunelu VPN IPsec. Ruch przechodzi przez internet 
(publiczny). Ruch jest szyfrowany przy użyciu protokołu wymiany kluczy internetowych (Internet Key Exchange). Dwa typy Cloud VPN:
a) HA VPN - wspiera tylko dynamiczne routowanie BGP.
b) Classic VPN - wspiera statyczne routowanie oparte na politykach, routowanie oparte na trasach oraz dynamiczne routowanie BGP.

61) Direct Peering?
Hybrid Cloud. Łączy sieć klienta z siecią Google za pomocą peeringu sieciowego. Bezpośrednia ścieżka z sieci lokalnej do 
usług Google. Nie jest to usługa GCP: niższy poziom połączenia sieciowego poza GCP. 
Niezalecane: używaj Cloud Interconnect i Cloud VPN.

62) Cloud Interconnect?
Hybrid Cloud. Szybkie fizyczne połączenie między siecią lokalną a siecią VPC. Wysoka dostępność i duża przepustowość. 
Dwa typy:
a) Dedicated Interconnect - 10 gigabitów na sekundę lub 100 gigabitów na sekundę.
b) Partner Interconnect - 50 megabitów na sekundę do 10 gigabitów na sekundę. Wymiana danych odbywa się przez prywatną 
sieć. Używaj tylko w przypadku dużych potrzeb pasma: w przypadku niskiej przepustowości zaleca się używanie Cloud VPN.

63) Jak połączyć sieci VPC w różnych organizacjach?
VPC Peering. Sieci w tym samym projekcie, w różnych projektach i między projektami w różnych organizacjach mogą być połączone.

64) Co można użyć do tworzenia pipeline'ów "CI/CD" w stylu Kubernetes, które działają jako rozszerzenie w klastrze Kubernetes?
Tekton Pipelines.

65) Opcje udostępniania plików między krokami Cloud Build?
Użyj folderu "/workspace".

66) SRE - Najlepsze praktyki.
Obsługa nadmiernego obciążenia: zrzut obciążenia "load shedding", zmniejszona jakość usługi "Quality of Service".
Unikanie awarii kaskadowych: zaplanuj, aby uniknąć przeciążenia.
Testowanie penetracyjne (ethical hacking).
Testowanie obciążeniowe.
Testowanie odporności - "Jak aplikacja zachowuje się pod stresem?".
Odporność "Resilience" - "Zdolność systemu do zapewnienia akceptowalnego zachowania, nawet gdy jedna lub więcej części systemu zawiedzie".
Podejścia:
a) Chaos Testing.
b) Dodanie dużego stresu do jednej z warstw.
c) Uwzględnienie sieci w testowaniu.
d) DIRT (Testowanie odzyskiwania po awarii w Google) (Disaster Recovery testing in Google).

67) Error budgets - metryki SRE?
100% - SLO. Jak dobrze zespół realizuje swoje cele dotyczące niezawodności? Używane do zarządzania prędkością rozwoju.

68) Umowa o poziomie usługi (SLA).
SLO plus konsekwencje (umowa). Jakie są konsekwencje niewypełnienia SLO? (Określone w umowie). Miej surowsze wewnętrzne 
SLO niż zewnętrzne SLA.

69) Twoje mikrousługi będą wdrożone na GKE. Chcesz nadać każdej mikrousłudze określony dostęp do zasobów Google Cloud?
Tożsamość obciążenia (Workload Identity).

70) Najlepsze praktyki zabezpieczeń Kubernetes?
Włącz automatyczne aktualizacje węzłów (Node Auto Upgrade) dla węzłów GKE.
Używaj zabezpieczonych węzłów GKE (Shielded GKE nodes) z bezpiecznym rozruchem (secure boot).
Włącz Tożsamość obciążenia (Workload Identity).
Używaj przestrzeni nazw (namespace) i RBAC do ograniczenia dostępu użytkowników do zasobów klastra.
Ogranicz ruch między Podami za pomocą polityki sieciowej (network policy).
Używaj Kubernetes secrets lub Secret Manager do zarządzania tajemnicami. Rozważ użycie HashiCorp Vault, jeśli potrzebujesz 
zarządzania tajemnicami w środowisku wielochmurowym.

71) Ważne predefiniowane role GKE?
Kubernetes Engine Admin: Pełny dostęp do klastra i obiektów API Kubernetes.
Kubernetes Engine Cluster Admin: Zapewnia dostęp do zarządzania klastrami.
Kubernetes Engine Cluster Viewer: Tylko dostęp do odczytu klastra.
Kubernetes Engine Developer: Zarządzanie obiektami API Kubernetes (i odczyt informacji o klastrze).
Kubernetes Engine Viewer: Uzyskiwanie/listowanie klastra i obiektów API Kubernetes.

72) Kubernetes RBAC?
Dokładne kontrolowanie dostępu na poziomie indywidualnych klastrów i przestrzeni nazw (namespace). Kontrolowanie dostępu 
na poziomie zasobów Kubernetes (deployment, service, pod, secrets, configMap).

73) Autoryzacja dla obciążenia Kubernetes:
Opcja 1: Przypisz uprawnienia na poziomie węzła (maszyna wirtualna Compute Engine).
Opcja 2: Przypisz uprawnienia na poziomie Poda (indywidualna mikrousługa), używając Kubernetes Secrets lub Workload Identity.
Rekomendowana opcja to użycie Workload Identity: przestrzega zasady minimalnych uprawnień (least privilege). 
Nie wymaga zarządzania tajemnicami (secret management).

74) Jak przypisać konto usługi do mikrousługi (lub Poda)?
Opcja 1: Używając Kubernetes Secrets (lub zarządzania tajemnicami).
Opcja 2: Używając Workload Identity.

75) Uprawnienia na poziomie węzła Kubernetes. Domyślne konto usługi przypisane do węzła?
Domyślne konto usługi Compute Engine.

76) Czy konto usługi to tożsamość, czy zasób?
Jest to zarówno tożsamość (identity), jak i zasób (resource). Możesz przypisać role do konta usługi (tożsamość). 
Możesz również umożliwić innym członkom dostęp do konta usługi, przyznając im rolę na koncie usługi (zasób).

77) Autoryzacja usługa do usługi?
Tokeny identyfikacyjne OpenID Connect (ID tokens).

78) Koncepcje IAM?
Członek (Member): Kto?
Role (Roles): Uprawnienia (Jakie działania? Jakie zasoby?).
Polityka (Policy): Przypisywanie uprawnień do członków.
Mapowanie Ról (Co?), Członków (Kto?) i Warunków (które zasoby?, Kiedy?, Skąd?).
Pamiętaj: Uprawnienia NIE są bezpośrednio przypisane do członka. Uprawnienia są reprezentowane przez rolę. 
Członek otrzymuje uprawnienia poprzez rolę! Rola może zawierać wiele uprawnień. Możesz przypisać członkowi wiele ról.

79) Tożsamości IAM (IAM identities)?
a) Użytkownik GCP (Konto Google lub użytkownik uwierzytelniony zewnętrznie).
b) Grupa użytkowników GCP.
c) Aplikacja działająca w GCP.
d) Aplikacja działająca w Twoim centrum danych.
e) Użytkownik nieuwierzytelniony.

80) Ustawienie projektu w konsoli chmurowej (Cloud Console)?
"gcloud config set project PRJ_NAME".

81) Zarządzanie cyklem życia obiektów (Object Lifecycle Management) – dozwolone przejścia?
a) Standardowy lub Multi-Regional lub Regionalny do Nearline, Coldline lub Archive.
b) Nearline do Coldline lub Archive.
c) Coldline do Archive.

82) Funkcje w różnych klasach przechowywania (storage classes)?
a) Wysoka trwałość (High durability).
b) Niska latencja (Low latency).
c) Nieograniczone przechowywanie (Unlimited storage): Autoskalowanie, brak minimalnej wielkości obiektu.
d) Ta sama API dla wszystkich klas przechowywania.
e) Zobowiązane SLA – brak zobowiązanego SLA (No committed SLA) dla Archive Storage.

83) Opcje Google KMS?
a) Klucz zarządzany przez Google (Google-managed key): Brak wymaganej konfiguracji.
b) Klucz zarządzany przez klienta (Customer-managed key): Użyj klucza z KMS.
c) Klucz dostarczony przez klienta (Customer-supplied key): Dostarcz własny klucz.

84) Która platforma umożliwia uruchomienie klastra Kubernetes na wielu platformach (wielochmurowych i lokalnych)?
Anthos.

85) Cloud Function, zimne uruchomienie (cold start)?
Inicjalizacja nowej instancji funkcji od zera może zająć trochę czasu.
Rozwiązanie: Skonfiguruj minimalną liczbę instancji (zwiększa to koszty).

86) Cloud Functions, zdarzenia są wywoływane z?
a) Cloud Storage.
b) Cloud Pub/Sub.
c) Metod HTTP: POST - GET - DELETE - PUT - OPTIONS.
d) Firebase.
e) Cloud Firestore.
f) Stackdriver Logging.

87) Jak usługa w klastrze Kubernetes może komunikować się z inną usługą w tym samym klastrze Kubernetes bez używania jej URL?
Odkrywanie usług Kubernetes (Kubernetes Service Discovery).

88) Jak możesz podzielić jeden fizyczny klaster Kubernetes na wiele wirtualnych klastrów z dostosowanym dostępem dla 
różnych zespołów?
Utwórz osobne przestrzenie nazw (Namespace).

89) Zalecane podejście do zapewniania zewnętrznego dostępu do usług w klastrze (jeden Load Balancer obsługuje wiele mikrousług)?
Ingress.

90) Jaką strategię wdrożenia domyślnie stosuje Kubernetes?
RollingUpdates.

91) GKE - Pamiętaj o CPU?
Część CPU na węzłach jest zarezerwowana przez Control Plane.
Pierwsze rdzenie - 6%, drugie rdzenie - 1%, trzecie i czwarte - 0,5%, pozostałe - 0,25%.

92) Status Kubernetes - PodUnschedulable?
Pod nie może zostać zaplanowany.
Czy w klastrze są wystarczające zasoby?

93) Status Kubernetes - CrashLoopBackOff?
Kontener wielokrotnie się zawiesza (repeatedly crashing).
Sprawdź logi, aby dowiedzieć się, dlaczego kontener się zawiesza.
"kubectl logs POD_NAME".
Możesz również spróbować połączyć się z pod'em i wykonać polecenia.
"kubectl exec -it POD_NAME -- ls".

94) Rozwiązywanie problemów w Kubernetes?
"kubectl describe pods POD_NAME".
Pokazuje bieżący stan Poda i ostatnie zdarzenia.

95) Status Kubernetes - ImagePullBackOff, ErrImagePull?
Czy nazwa obrazu jest poprawna?
Czy tag obrazu jest poprawny?
Czy konto usługi w puli węzłów ma dostęp do obrazu w rejestrze kontenerów?

96) Zasoby globalne (Global), regionalne (Regional) i strefowe (Zonal)?
a) Globalne (Global): Obrazy, migawki (Snapshots), szablony instancji (chyba że używasz zasobów strefowych w szablonie).
b) Regionalne (Regional): Grupa zarządzanych instancji regionalnych, trwałe dyski regionalne.
c) Strefowe (Zonal): Grupy zarządzanych instancji strefowych, instancje, trwałe dyski: możesz podłączyć dysk tylko do 
instancji w tej samej strefie co dysk.

97) Chcesz bardzo wysokich IOPS, ale Twoje dane mogą zostać utracone bez problemu?
Dyski SSD lokalne (Local SSDs).

98) Chcesz stworzyć system udostępniania plików o wysokiej wydajności w GCP, który może być podłączony do wielu maszyn 
wirtualnych?
Filestore.

99) Chcesz wykonać kopię zapasową konfiguracji swojej maszyny wirtualnej wraz ze wszystkimi jej podłączonymi trwałymi 
dyskami?
Utwórz obraz maszyny (Machine Image).

100) Chcesz ułatwić uruchamianie maszyn wirtualnych z utwardzonym systemem operacyjnym i dostosowanym oprogramowaniem?
Utwórz obraz niestandardowy (Custom Image).

101) Trwała usługa przechowywania bloków sieciowych w GCP?
Persistent Disk.

102) Co zawiera wszystko, czego potrzebujesz do utworzenia instancji VM - konfigurację VM, metadane, dysk rozruchowy i 
dane z jednego lub więcej trwałych dysków?
Obraz maszyny (Machine Image).

103) Cloud Storage?
Najbardziej popularna, bardzo elastyczna i niedroga usługa przechowywania. 
Bezserwerowa: Autoskalowanie i nieskończona skalowalność.
Przechowywanie dużych obiektów przy użyciu podejścia klucz-wartość: Traktuje cały obiekt jako jednostkę 
(częściowe aktualizacje nie są dozwolone).
Zalecane, gdy operujesz na całym obiekcie przez większość czasu.
Kontrola dostępu na poziomie obiektu. Znana również jako przechowywanie obiektów (Object storage).
Oferuje API REST do dostępu i modyfikacji obiektów. Oferuje również CLI (gsutil) oraz biblioteki klienckie 
(C++, C#, Java, Node.js, PHP, Python i Ruby).
Możliwość przechowywania wszystkich typów plików - tekstowych, binarnych, kopii zapasowych i archiwów: 
Pliki multimedialne i archiwa, paczki aplikacji i logi, kopie zapasowe baz danych lub urządzeń pamięci masowej, 
dane tymczasowe podczas migracji z lokalnych baz danych do chmury.

104) Cloud Storage - Objects i Buckets?
Obiekty są przechowywane w koszach (buckets). Nazwy koszy są unikalne globalnie. Nazwy koszy są używane jako część URL-i 
obiektów, mogą zawierać tylko małe litery, cyfry, myślniki, podkreślniki i kropki. Maksymalna długość to 3 do 63 znaków. 
Nie mogą zaczynać się od prefiksu goog ani zawierać słowa google (nawet z błędami ortograficznymi). W koszu może znajdować 
się nieograniczona liczba obiektów. Każdy bucket jest powiązany z projektem. Każdy obiekt jest identyfikowany przez 
unikalny klucz – klucz jest unikalny w obrębie bucketu. Maksymalny rozmiar obiektu to 5 terabajtów, ale można przechowywać 
nieograniczoną liczbę takich obiektów.

105) IAM - Role?
Role to uprawnienia: wykonywanie określonego zestawu działań na określonym zestawie zasobów. Trzy typy:
a) Role podstawowe (lub role pierwotne): Właściciel, Edytor, Widokowiec.
Widokowiec (roles.viewer) – Działania tylko do odczytu.
Edytor (roles.editor) – Widokowiec plus działania edytorskie.
Właściciel (roles.owner) – Edytor plus zarządzanie rolami i uprawnieniami plus rozliczenia.
Najwcześniejsza wersja: stworzona przed IAM. Nie zalecane: nie używaj w środowisku produkcyjnym.
b) Role wstępnie zdefiniowane – Role o precyzyjnych uprawnieniach, zdefiniowane i zarządzane przez Google. 
Różne role do różnych celów.
Przykłady: Administrator Storage, Administrator Obiektów Storage, Widokowiec Obiektów Storage, Twórca Obiektów Storage.
c) Role niestandardowe: Kiedy role wstępnie zdefiniowane są niewystarczające, możesz stworzyć własne role niestandardowe.

106) Cloud Identity and Access Management (IAM)
Uwierzytelnianie (Authentication) (czy to właściwy użytkownik?) i Autoryzacja (Authorization) (czy mają odpowiedni dostęp?).
Tożsamości (Identities) mogą być:
Użytkownik GCP (Konto Google lub Użytkownik uwierzytelniony zewnętrznie).
Grupa użytkowników GCP.
Aplikacja działająca w GCP.
Aplikacja działająca w Twoim centrum danych.
Użytkownik nieuwierzytelniony.
IAM zapewnia bardzo precyzyjną kontrolę.
Możesz ograniczyć pojedynczego użytkownika do: wykonywania jednej akcji, na określonym zasobie, z określonego adresu IP, 
w określonym przedziale czasowym.

107) Typowe zarządzanie tożsamościami (identity) w chmurze
Masz zasoby w chmurze (przykłady - wirtualny serwer, baza danych itd.). Masz tożsamości (human i non-human), które muszą 
uzyskać dostęp do tych zasobów i wykonywać działania. Na przykład: Uruchomienie (rozpoczęcie, zatrzymanie lub zakończenie) 
wirtualnego serwera. Jak identyfikujesz użytkowników w chmurze? Jak konfigurujesz zasoby, do których mogą uzyskać dostęp? 
Jak możesz skonfigurować, jakie działania są dozwolone? W GCP: Zarządzanie tożsamościami i dostępem (Cloud IAM) zapewnia 
tę usługę.

108) Cloud Storage - Szyfrowanie?
Cloud Storage umożliwia szyfrowanie danych po stronie serwera. Konfiguracja szyfrowania po stronie serwera: Szyfrowanie 
wykonywane przez Cloud Storage.
Klucz szyfrowania zarządzany przez Google: Domyślnie (nie wymaga konfiguracji).
Klucz szyfrowania zarządzany przez klienta: Tworzony za pomocą Cloud Key Management Service (KMS). Zarządzany przez 
klienta w KMS. Konto usługi Cloud Storage powinno mieć dostęp do kluczy w KMS, aby móc szyfrować i deszyfrować za pomocą 
kluczy szyfrowania zarządzanych przez klienta.
(Opcjonalnie) Szyfrowanie po stronie klienta: Szyfrowanie wykonywane przez klienta przed wysłaniem danych. 
GCP nie ma dostępu do używanych kluczy.

109) Wersjonowanie obiektów?
Zapobiega przypadkowemu usunięciu i zapewnia historię. Włączane na poziomie kosza. Można je włączać i wyłączać w dowolnym 
momencie.
Wersja aktywna to najnowsza wersja. Jeśli usuniesz aktywny obiekt, staje się on wersją obiektu nieaktualną. Jeśli usuniesz 
wersję obiektu nieaktualną, zostanie ona usunięta. Starsze wersje są unikalnie identyfikowane przez / klucz obiektu plus 
numer generacji.
Zmniejsz koszty, usuwając starsze (nieaktualne) wersje!

110) Cloud Filestore?
Wspólne przechowywanie plików w chmurze: Obsługuje protokół "NFS v3". Przydzielona pojemność. Odpowiedni do obciążeń 
wymagających wysokiej wydajności: Do 320 terabajtów z przepustowością 16 gigabajtów na sekundę i 480 tysiącami operacji 
wejścia/wyjścia na sekundę (IOPS). Obsługuje HDD (dla ogólnych zastosowań) oraz SSD (dla obciążeń krytycznych pod 
względem wydajności).
Przykłady zastosowań: Udostępnianie plików, przepływy pracy związane z mediami i zarządzanie klientami.

111) Chcesz usunąć stare migawki utworzone przez harmonogram migawkowy (scheduled snapshots)?
Skonfiguruj to jako część harmonogramu migawkowego (scheduled snapshots)!

112) Chcesz poprawić wydajność dysku trwałego (PD)?
Zwiększ rozmiar PD i dodaj więcej PD. Zwiększ liczbę vCPU w swojej maszynie wirtualnej.

113) Chcesz zwiększyć trwałość dysku trwałego (PD)?
Wybierz dyski PD regionalne (regional PDs) (kosztują 2 razy więcej, ale są replikowane w 2 strefach).

114) Chcesz wykonać godzinną kopię zapasową dysków trwałych (PD) w celu odzyskiwania po awarii?
Skonfiguruj godzinne migawki (hourly snapshots)!

115) Access Control - Overview?
Jak kontrolujesz dostęp do obiektów w zasobniku Cloud Storage? Istnieją dwa rodzaje kontroli dostępu: 
a) Uniform (Zalecane): Uniform bucket level access przy użyciu IAM. 
b) Fine-grained – użyj IAM i ACL do kontrolowania dostępu: zarówno na poziomie zasobnika, jak i na poziomie 
poszczególnych obiektów. Używaj jednolitego dostępu, gdy wszyscy użytkownicy mają ten sam poziom dostępu do wszystkich 
obiektów w zasobniku. Fine-grained access z użyciem ACL może być używane, gdy chcesz dostosować dostęp na poziomie 
obiektów. Przyznaj użytkownikowi specyficzny dostęp do edytowania określonych obiektów w zasobniku.

116) ACL - Access Control Lists?
ACL: Określ, kto ma dostęp do Twoich zasobników i obiektów oraz jaki poziom dostępu mają. Jak to różni się od IAM? 
Uprawnienia IAM stosują się do wszystkich obiektów w zasobniku. ACL mogą być używane do dostosowania dostępu do 
konkretnych obiektów. Użytkownik uzyskuje dostęp, jeśli ma go przyznany przez IAM lub ACL! Pamiętaj – używaj IAM do 
ogólnych uprawnień do wszystkich obiektów w zasobniku. Pamiętaj – używaj ACL, jeśli musisz dostosować dostęp do 
poszczególnych obiektów.

117) Instancja VM z domyślnym kontem serwisowym w "Projekcie-"A potrzebuje dostępu do zasobnika Cloud Storage w "Projekcie-B"?
W "Projekcie-B" dodaj konto serwisowe (service account) z "Projektu-A" i przypisz uprawnienie Storage Object Viewer 
do zasobnika.

118) Czy konto serwisowe jest tożsamością (identity) czy zasobem (resource)?
Jest to zarówno tożsamość, jak i zasób. Możesz przypisać role do konta serwisowego (tożsamość). Możesz pozwolić innym 
członkom na dostęp do konta serwisowego, przyznając im rolę do konta serwisowego (zasób).

119) Konto serwisowe (Service Account) - Aplikacja na instancji VM chce wysłać wiadomość do tematu Pub/Sub (Pub/Sub Topic)?
Skonfiguruj instancję VM, aby używała konta serwisowego (Service Account) z odpowiednimi uprawnieniami.

120) Konto serwisowe - Aplikacja na instancji VM chce komunikować się z zasobnikiem Cloud Storage?
Skonfiguruj instancję VM, aby używała konta serwisowego z odpowiednimi uprawnieniami.

121) Konto serwisowe - Przypadek użycia uprawnień do interfejsów API Google Cloud (krótkotrwałe)?
Wykonuj wywołania zewnętrzne do interfejsów API Google Cloud z krótkotrwałymi uprawnieniami. Kilka godzin lub krócej. 
Mniejsze ryzyko w porównaniu do udostępniania kluczy kont serwisowych! Typy poświadczeń: token dostępu OAuth 2.0, 
tokeny identyfikacyjne OpenID Connect, samopodpisane JSON Web Tokens (JWT). 
Przykłady: Kiedy członek zespołu potrzebuje podwyższonych uprawnień, może przyjąć rolę konta serwisowego 
(utworzyć token dostępu OAuth 2.0 dla konta serwisowego). Tokeny identyfikacyjne OpenID Connect są zalecane do 
uwierzytelniania usług między sobą: Usługa w GCP musi uwierzytelnić się do usługi w innym chmurze.

122) Konto serwisowe - Przypadek użycia z on-premises do Google Storage (długotrwałe)?
Nie możesz przypisać konta serwisowego bezpośrednio do aplikacji on-premises.
Jeden: Utwórz konto serwisowe z odpowiednimi uprawnieniami.
Dwa: Utwórz zarządzany klucz konta serwisowego użytkownika (Service Account User Managed key:): 
"gcloud iam service-accounts keys create". Pobierz plik klucza konta serwisowego, przechowuj go w bezpiecznym miejscu 
(może być użyty do podszywania się pod konto serwisowe)!
Trzy: Udostępnij plik klucza konta serwisowego swojej aplikacji: Ustaw zmienną środowiskową GOOGLE_APPLICATION_CREDENTIALS.
Cztery: Używaj bibliotek klienckich Google – Google Cloud Client Libraries – App Default Credentials/ADC.

123) Konto serwisowe - Przypadek użycia instancja VM do Cloud Storage?
Jeden: Utwórz rolę konta serwisowego (Service Account) z odpowiednimi uprawnieniami.
Dwa: Przypisz rolę konta serwisowego do instancji VM.
Używaj kluczy zarządzanych przez Google Cloud: Generowanie kluczy i ich użycie są automatycznie obsługiwane przez IAM, 
gdy przypiszemy konto serwisowe do instancji. Klucze są automatycznie rotowane. Nie ma potrzeby przechowywania poświadczeń 
w plikach konfiguracyjnych.
Nie usuwaj kont serwisowych używanych przez działające instancje. Aplikacja działająca na tych instancjach utraci dostęp!

124) Konta serwisowe (Service Account)?
Scenariusz: Aplikacja na instancji VM potrzebuje dostępu do Cloud Storage. Nie chcesz używać osobistych poświadczeń 
(credentials), aby umożliwić dostęp.
Zalecane - Używaj kont serwisowych: Identyfikowane przez adres e-mail (np. id-compute@developer.gserviceaccount.com).
Nie mają hasła: Posiadają parę kluczy RSA (prywatny/publiczny).
Nie można się zalogować za pomocą przeglądarek ani plików cookie.
Rodzaje kont serwisowych:
Domyślne konto serwisowe (Default) - Tworzone automatycznie, gdy używane są niektóre usługi. Niezalecane - 
ma rolę Editor domyślnie. 
Konto zarządzane przez użytkownika (User Managed) - Tworzone przez użytkownika: Zalecane. Zapewnia kontrolę dostępu na 
poziomie szczegółowym.
Konta serwisowe zarządzane przez Google (Google-Managed service accounts) - Tworzone i zarządzane przez Google. 
Używane przez GCP do wykonywania operacji w imieniu użytkownika. Zasadniczo nie musimy się nimi przejmować.

125) Nowy scenariusz: Aplikacje raportowe i analityczne.
Nowe aplikacje raportowe i analityczne są uruchamiane, korzystając z tej samej bazy danych. Aplikacje te będą tylko 
odczytywać dane. Po kilku dniach zauważasz, że wydajność bazy danych została wpłynięta. Jak możemy rozwiązać ten problem?
Skalowanie pionowe (Vertically) bazy danych - zwiększ CPU i pamięć.
Utworzenie klastra bazy danych (database cluster) (rozproszenie bazy danych) - Zwykle klastry baz danych są drogie w 
konfiguracji.
Utworzenie replik do odczytu (read replicas) - Uruchamiaj aplikacje tylko do odczytu na replikach do odczytu.

126) Baza danych - Replika do odczytu?
Dodaj replikę do odczytu. Podłącz aplikacje raportowe i analityczne do repliki do odczytu. 
Zmniejsza to obciążenie głównej bazy danych.
Uaktualnij replikę do odczytu do roli głównej bazy danych (obsługiwane przez niektóre bazy danych).
Utwórz repliki do odczytu w wielu regionach.
Twórz migawki z replik do odczytu.

127) Podsumowanie relacyjnych baz danych OLTP.
Cloud SQL, Cloud Spanner. Przypadki użycia transakcyjne wymagające zdefiniowanego schematu i bardzo silnych możliwości 
transakcyjnych (przechowywanie wierszy).
Cloud SQL: Bazy danych MySQL, PostgreSQL, SQL Server.
Cloud Spanner: Nielimitowana skalowalność i dostępność na poziomie "99,999%" dla aplikacji globalnych z poziomą 
skalowalnością.

128) Spójność (Consistency)?
Jak zapewnić, że dane w wielu instancjach baz danych (w trybie zapasowym i replikach) są aktualizowane jednocześnie?
Spójność przechowywania (Storing Consistency) – Replikacja synchroniczna do wszystkich replik. Będzie to wolne, 
jeśli masz wiele replik lub zapasowych instancji.
Spójność ostateczna (Eventual Consistency) – Replikacja asynchroniczna. Krótkie opóźnienie – kilka sekund – zanim zmiana 
będzie dostępna we wszystkich replikach. W tym okresie przejściowym różne repliki mogą zwracać różne wartości.
Używane, gdy skalowalność jest ważniejsza niż integralność danych.
Przykłady: Posty w mediach społecznościowych, statusy na Facebooku, tweet'y na Twitterze, posty na LinkedIn itd.
Spójność po zapisaniu i odczycie: Wstawienia są natychmiast dostępne. Jednak aktualizacje będą miały spójność ostateczną.

129) Najważniejsze pojęcia IAM?
Członek (Member): Kto?
Role (Role): Uprawnienia (Jakie działania? Jakie zasoby?).
Polityka (Policy): Przypisanie uprawnień do członków: Mapowanie ról (Co?), członków (Kto?) i warunków (Jakie zasoby?, 
Kiedy?, Z jakiego miejsca?).
Pamiętaj: Uprawnienia nie są bezpośrednio przypisywane do członka: Uprawnienia są reprezentowane przez rolę. 
Członek (Member) otrzymuje uprawnienia przez rolę! Rola może mieć wiele uprawnień. Możesz przypisać członkowi wiele ról.

130) Polityka IAM?
Role są przypisywane użytkownikom poprzez dokumenty polityki IAM. Reprezentowane przez obiekt polityki. Obiekt polityki 
zawiera listę powiązań. Powiązanie przypisuje rolę do listy członków. Typ członka jest identyfikowany przez prefiks: 
Przykład: użytkownik, konto serwisowe, grupa lub domena.

131) Uruchamiasz aplikację w instancji VM, która przechowuje swoje dane na trwałej pamięci masowej. 
Tworzysz migawki co 48 godzin. Jeśli instancja VM ulegnie awarii, możesz ręcznie przywrócić ją do działania w ciągu 
45 minut od migawki. Jaki jest twój RTO i RPO?
RTO (Recovery Time Objective): 45 minut.
RPO (Recovery Point Objective): 48 godzin.

132) RTO i RPO. Bardzo mała utrata danych (RPO - 1 minuta), bardzo krótki czas przestoju (RTO - 5 minut)?
Hot standby - Automatycznie synchronizuj dane. Miej instancję zapasową gotową do przejęcia obciążenia. 
Użyj automatycznego przełączania awaryjnego z głównego serwera na zapasowy.

133) RTO i RPO. Bardzo mała utrata danych (RPO - 1 minuta). Ale mogę tolerować pewien czas przestoju (RTO - 15 minut)?
Warm standby - Automatycznie synchronizuj dane. Miej instancję zapasową z minimalną infrastrukturą. 
Skaluj ją w górę, gdy wystąpi awaria.

134) RTO i RPO. Dane są krytyczne (RPO - 1 minuta), ale mogę tolerować przestój trwający kilka godzin (RTO - kilka godzin)?
Twórz regularne migawki danych i dzienniki transakcji. Twórz bazę danych z migawków i dzienników transakcji, gdy wystąpi awaria.

135) RTO i RPO. Dane mogą zostać utracone bez problemu (na przykład: dane w pamięci podręcznej)?
Przełączanie awaryjne na całkowicie nowy serwer.

136) Baza danych do przechowywania ogromnych wolumenów danych strumieniowych z urządzeń IoT?
BigTable.

137) Baza danych do przechowywania ogromnych strumieni danych szeregów czasowych (time series data)?
BigTable.

138) Baza danych do przetwarzania analitycznego petabajtów danych?
BigQuery.

139) Pamięć podręczna danych (z bazy danych) dla aplikacji webowej?
Memorystore.

140) Transakcyjna, globalna baza danych z predefiniowaną schemą, która musi przetwarzać miliony transakcji na sekundę?
Cloud Spanner.

141) Transakcyjna, lokalna baza danych przetwarzająca tysiące transakcji na sekundę?
Cloud SQL.

142) Startup z szybko ewoluującą schemą (strukturą tabeli)?
Cloud Datastore / Firestore.

143) Baza danych nierelacyjna z mniejszą ilością pamięci (10 GB)?
Cloud Datastore.

144) Baza danych w pamięci podręcznej (In memory database caches)?
Cloud Memorystore. Aplikacje wymagające odpowiedzi w mikrosekundach.

145) Bazy danych NoSQL?
Cloud Firestore (Datastore), Cloud BigTable. Aplikacje wymagające szybko ewoluującej struktury (bez schematu).
Cloud Firestore: Bezserwerowa, transakcyjna baza danych dokumentów wspierająca aplikacje mobilne i webowe. 
Małe do średnich baz danych (od 0 do kilku terabajtów).
Cloud BigTable: Duże bazy danych (od 10 terabajtów do petabajtów). Strumieniowanie IoT, analityka i obciążenia operacyjne. 
Nie jest bezserwerowe.

146) Podsumowanie relacyjnej bazy danych OLAP?
BigQuery. Przechowywanie kolumnowe z predefiniowaną schemą. Magazynowanie danych i obciążenia BigData.

147) Cloud BigTable?
Skala petabajtów, szerokokolumnowa baza danych NoSQL (kompatybilna z API HBase). Zaprojektowana do obsługi ogromnych 
wolumenów danych analitycznych i operacyjnych. Strumienie IoT, analityka, dane szeregów czasowych itd. Obsługuje miliony 
operacji odczytu/zapisu TPS przy bardzo niskiej latencji.
Transakcje tylko dla pojedynczych wierszy (transakcje dla wielu wierszy NIE są obsługiwane). Nie jest bezserwerowa: 
Należy utworzyć instancję serwera (użyj SSD lub HDD). Skaluje się poziomo z wieloma węzłami 
(brak przestojów przy zmianie rozmiaru klastra).
Nie można eksportować danych za pomocą konsoli chmurowej lub gcloud: Należy użyć aplikacji Java 
("export/import" za pomocą java-jar JAR). 
Użyj poleceń HBase. Użyj narzędzia wiersza poleceń cbt do pracy z BigTable (nie gcloud).
Przykład: "cbt createtable my-table".

148) Cloud Bigtable - Baza danych o szerokich kolumnach?
Na najbardziej podstawowym poziomie każda tabela to posortowana mapa klucz/wartość. Każda wartość w wierszu jest 
indeksowana za pomocą klucza wiersza (row key). Powiązane kolumny są grupowane w rodziny kolumn (column families). 
Każda kolumna jest identyfikowana za pomocą formatu column-family (lub nazwa kolumny). 
Taka struktura wspiera wysoką przepustowość odczytu i zapisu przy niskim opóźnieniu.
Zalety:
a) Skalowalność do petabajtów danych z odpowiedziami w milisekundach, obsługującymi miliony transakcji na sekundę (TPS).
Przypadki użycia:
a) Strumienie IoT (IoT streams).
b) Dane grafowe (graph data).
c) Analityka w czasie rzeczywistym (real-time analytics) - dane szeregów czasowych, dane finansowe, historia transakcji, 
ceny akcji itp.
Cloud Dataflow: Używany do eksportowania danych z BigTable do Cloud Storage.

149) Czym jest Memorystore?
Memorystore to magazyn danych w pamięci, zaprojektowany w celu zmniejszenia czasu dostępu do danych, przechowując je w 
pamięci, a nie na dysku.
W pełni zarządzana usługa: Google Cloud zajmuje się provisioningiem, replikacją, awaryjnym przełączaniem i instalowaniem 
poprawek (patching) automatycznie.
Wysoka dostępność: Memorystore oferuje SLA dostępności na poziomie "99,9%" (umowa o poziomie usług).
Monitoring: Łatwe skonfigurowanie monitorowania instancji Memorystore przy użyciu Cloud Monitoring.
Obsługiwane silniki:
Redis oraz Memcached:
a) Memcached jest używane głównie do cache'owania danych, takich jak: Dane referencyjne, zapytania do baz danych, 
cache'owanie, przechowywanie sesji itp.
b) Redis jest preferowane do: Dostępu o niskim opóźnieniu, z trwałością i wysoką dostępnością.
Przypadki użycia:
a) Tablice wyników gier (Gaming Leaderboards): Przechowywanie i zapytania dotyczące wyników gier w czasie rzeczywistym.
b) Profile graczy (Player Profiles): Przechowywanie informacji o graczach z szybkim dostępem do odczytu/zapisu.
c) Przetwarzanie strumieniowe w pamięci (In-memory stream processing): Przetwarzanie danych w czasie rzeczywistym, 
takie jak analityka w czasie rzeczywistym lub architektury oparte na zdarzeniach.

150) Zasady zapory - Zasady Ingress i Egress?
Zasady Ingress:
Definiują przychodzący ruch do zasobów Google Cloud Platform (GCP).
Cel: Określa, gdzie kierowany jest ruch. Może to być:
a) Wszystkie instancje w projekcie.
b) Instancje z określonym TAG-iem lub Konta Usługi (SA).
Źródło: Określa, skąd pochodzi ruch. Może to być:
a) Blok CIDR (zakres adresów IP).
b) Wszystkie instancje lub instancje z określonym TAG-iem lub Konta Usługi (SA).
Zasady Egress:
Definiują wychodzący ruch z zasobów GCP do określonego miejsca.
Cel: Określa źródło wychodzącego ruchu. Może to być: Wszystkie instancje lub instancje z określonym TAG-iem lub 
Konta Usługi (SA).
Destynacja: Określa adres lub zakres docelowy, zazwyczaj definiowany przez blok CIDR.
Każda zasada zapory może zawierać następujące konfiguracje:
a) Priorytet: Wartość liczbowa, gdzie niższa liczba oznacza wyższy priorytet. Zasady zapory o niższym numerze mają 
pierwszeństwo przed zasadami o wyższym numerze.
b) Akcja na dopasowanie: Zezwól lub Odmów ruchu, który pasuje do zasady.
c) Protokół: Określa typ protokołu dla ruchu. Przykłady to TCP, UDP, ICMP itp.
d) Status egzekwowania: Określa, czy zasada jest Włączona czy Wyłączona.

151) Co to jest Shared VPC?
Scenariusz:
Twoja organizacja ma wiele projektów, a Ty chcesz, aby zasoby w różnych projektach mogły się ze sobą komunikować w sposób 
bezpieczny i wydajny, używając wewnętrznych adresów IP. 
Rozwiązanie: Shared VPC.
Shared VPC tworzony jest na poziomie organizacji lub folderu współdzielonego. (Aby go skonfigurować, wymagane są 
uprawnienia Shared VPC Admin).
Umożliwia współdzielenie sieci VPC pomiędzy różnymi projektami w ramach tej samej organizacji.
Komponenty Shared VPC:
Host Project:
a) Zawiera sieć Shared VPC.
b) To projekt, który przechowuje zasoby sieciowe, które będą współdzielone z innymi projektami.
Service Projects:
a) Projekty podłączone do Host Project.
b) Projekty te mogą korzystać z zasobów sieciowych Host Project, ale nie zarządzają samą siecią VPC.
Kluczowe korzyści:
Separation of Concerns (Rozdzielenie obowiązków):
Administratorzy sieci są odpowiedzialni za zarządzanie Host Project oraz jego współdzieloną siecią.
Użytkownicy zasobów (np. deweloperzy aplikacji) mogą pracować w Service Projects, korzystając z sieci bez konieczności 
angażowania się w zarządzanie siecią.

152) Co to jest VPC Peering?
Scenariusz:
a) Chcesz połączyć sieci VPC w różnych projektach lub nawet w różnych organizacjach.
Rozwiązanie: VPC Peering.
a) VPC Peering umożliwia połączenie sieci w tym samym projekcie, w różnych projektach, a nawet w różnych organizacjach.
b) Cała komunikacja między tymi sieciami odbywa się za pomocą wewnętrznych adresów IP, co jest bardzo wydajne.
Wydajność: Ponieważ komunikacja odbywa się w ramach własnej infrastruktury Google (Google Network), jest szybka i zoptymalizowana.
Bezpieczeństwo: Sieci połączone za pomocą VPC Peering są niedostępne z internetu, co zapewnia bezpieczną komunikację.
Efektywność kosztowa: Nie są naliczane opłaty za transfer danych między połączonymi usługami.
Ważne uwagi:
a) Administracja siecią: Administratorzy sieci jednej z VPC nie otrzymują automatycznie uprawnień administracyjnych w 
połączonej sieci. Brak automatycznego dostępu administracyjnego: VPC Peering nie przyznaje uprawnień administracyjnych 
w sieci połączonej.

153) Co to jest Cloud VPN?
Cloud VPN to rozwiązanie do łączenia sieci on-premise (sieć w Twoim centrum danych lub biurze) z siecią Google Cloud Platform (GCP).
Jest realizowane przy użyciu tunelu VPN IPSec, który pozwala na szyfrowaną komunikację między siecią on-premise a siecią GCP.
Ruch odbywa się przez internet (sieć publiczną), a dane są szyfrowane za pomocą protokołu Internet Key Exchange (IKE), 
aby zapewnić bezpieczną komunikację.
Rodzaje rozwiązań Cloud VPN:
a) HA VPN (VPN o wysokiej dostępności):
SLA dostępności usługi: 99,99%. Używa dwóch zewnętrznych adresów IP dla redundancji. Obsługuje tylko dynamiczne routowanie 
za pomocą BGP (Border Gateway Protocol), co umożliwia automatyczne aktualizacje tras między sieciami.
b) Classic VPN (VPN klasyczny):
SLA dostępności usługi: 99,9%.  Używa jednego zewnętrznego adresu IP. Obsługuje zarówno statyczne routowanie 
(oparte na politykach i trasach), jak i dynamiczne routowanie za pomocą BGP.

154) Co to jest Cloud Interconnect?
Cloud Interconnect to wysokosprawne połączenie fizyczne między Twoją siecią on-premise a siecią VPC w Google Cloud.
Jest zaprojektowane, aby zapewnić wysoką dostępność i wspierać dużą przepustowość.
Rodzaje połączeń Cloud Interconnect:
a) Dedicated Interconnect (Połączenie dedykowane): Oferuje wysokoprędkościowe połączenia z opcjami konfiguracji 10 Gbps 
lub 100 Gbps. Idealne dla organizacji z dużymi wymaganiami dotyczącymi przepustowości.
b) Partner Interconnect (Połączenie partnerskie): Oferuje konfiguracje w zakresie od 10 Mbps do 10 Gbps. Odpowiednie dla 
mniejszych lub bardziej elastycznych potrzeb przepustowości, gdzie zewnętrzni dostawcy usług mogą połączyć Twoją sieć 
on-premise z Google Cloud.
Korzyści:
a) Komunikacja przez prywatną sieć: Wymiana danych odbywa się przez prywatną sieć, co czyni komunikację bardziej bezpieczną.
Możesz używać wewnętrznych adresów IP sieci VPC do komunikacji z sieci on-premise, co sprawia, że połączenie jest bardziej 
wydajne i bezpieczne.
b) Zmniejszenie kosztów egress: Ponieważ publiczny internet nie jest używany, ruch egress (dane opuszczające Google Cloud) 
jest znacznie tańszy.
c) Prywatny dostęp do usług Google: Obsługiwane usługi i API Google mogą być prywatnie dostępne z Twojej sieci on-premise.
Zastosowanie:
Wysoka przepustowość: Cloud Interconnect jest przeznaczony dla potrzeb związanych z dużą przepustowością, takich jak duże 
transfery danych lub przetwarzanie danych w czasie rzeczywistym. Dla niskiej przepustowości zaleca się raczej korzystanie 
z Cloud VPN.

155) Co to jest Direct Peering?
Direct Peering umożliwia bezpośrednie połączenie sieci on-premise z siecią Google przy użyciu peeringu sieciowego.
Zapewnia bezpośrednią ścieżkę z Twojej sieci on-premise do usług Google, omijając publiczny internet.
Kluczowe informacje:
Nie jest to usługa GCP: Direct Peering nie jest zarządzaną usługą Google Cloud Platform (GCP). Jest to połączenie 
sieciowe poza GCP, na niższym poziomie.
Niezalecane: W większości przypadków zaleca się użycie Cloud Interconnect lub Cloud VPN zamiast Direct Peering, ponieważ 
te usługi oferują bardziej niezawodne, bezpieczne i skalowalne połączenia.

156) Cloud Datastore i Firestore?
Odpowiedź:
Datastore: Wysoce skalowalna baza danych NoSQL typu Document. Automatycznie skaluje i partycjonuje dane w miarę ich wzrostu.
Zalecana dla danych o rozmiarze do kilku terabajtów. Dla większych wolumenów, zalecany jest BigTable. Obsługuje transakcje, 
indeksy i zapytania w stylu SQL (GQL). Nie obsługuje operacji Joins ani operacji agregujących (sumowanie, liczenie). 
Przeznaczony do przypadków użycia wymagających elastycznej schemy z transakcjami.
Przykłady: profil użytkownika i katalogi produktów.
Struktura: "Kind - Entity" (użyj przestrzeni nazw do grupowania encji). Można eksportować dane tylko z "gcloud" 
(nie z konsoli). Eksport zawiera plik metadanych oraz folder z danymi.
Firestore: Datastore++: Optymalizowany do dostępu z wielu urządzeń. Tryb offline oraz synchronizacja danych pomiędzy 
wieloma urządzeniami, takimi jak urządzenia mobilne, IoT itd. Udostępnia biblioteki po stronie klienta: 
Web, iOS, Android i inne. Oferuje tryby Datastore oraz Native.

157) Google Cloud VPC (Virtual Private Cloud)?
Odpowiedź:
Twoja własna izolowana sieć w chmurze GCP. Ruch sieciowy wewnątrz VPC jest izolowany (niewidoczny) od wszystkich innych 
VPC Google Cloud. Masz pełną kontrolę nad ruchem przychodzącym i wychodzącym z VPC. Najlepsza praktyka to tworzenie 
wszystkich zasobów GCP (Compute, Storage, bazy danych itp.) wewnątrz VPC. Zabezpiecz zasoby przed nieautoryzowanym dostępem. 
Umożliw komunikację między zasobami w chmurze w sposób bezpieczny. VPC to zasób globalny, który zawiera podsieci w jednej 
lub wielu strefach. Nie jest związany z regionem ani strefą. Zasoby VPC mogą znajdować się w dowolnym regionie lub strefie.

158) Potrzeba dla podsieci VPC?
Odpowiedź:
W chmurze tworzone są różne typy zasobów, takie jak bazy danych, instancje obliczeniowe. Każdy typ zasobu ma swoje własne 
potrzeby. Load Balancery są dostępne z internetu (zasoby publiczne). Bazy danych lub instancje VM nie powinny być dostępne 
z internetu. Tylko aplikacje wewnątrz Twojej sieci (VPC) powinny mieć do nich dostęp (zasoby prywatne). Jak oddzielić 
zasoby publiczne od prywatnych wewnątrz VPC? Twórz oddzielne Podsieci! Dodatkowy powód: chcesz rozdzielić zasoby pomiędzy 
różne regiony w celu zapewnienia wysokiej dostępności.

159) Podsieci VPC?
Rozwiązanie: twórz różne podsieci dla zasobów publicznych i prywatnych. Zasoby w publicznej podsieci mogą być dostępne 
z internetu. Zasoby w prywatnych podsieciach nie mogą być dostępne z internetu. Jednak zasoby w publicznej podsieci mogą 
komunikować się z zasobami w prywatnej podsieci. Każda podsieć jest tworzona w określonym regionie.
Przykład: "VPC-demo-vpc", podsieci w regionach "us-central1", "europe-west1" lub "us-west1".

160) Tworzenie VPC i Podsieci?
Domyślnie, każdy projekt ma swoją własną VPC. Możesz stworzyć własne VPC.
Opcja 1: Sieć VPC w trybie automatycznym. Podsieci są automatycznie tworzone w każdym regionie. Domyślna VPC, która jest 
tworzona automatycznie w projekcie, używa trybu automatycznego!
Opcja 2: Sieć VPC w trybie niestandardowym. Żadne podsieci nie są tworzone automatycznie. Masz pełną kontrolę nad 
podsieciami i ich zakresami IP. Zalecane dla środowiska produkcyjnego.
Opcje podczas tworzenia podsieci:
Włącz dostęp prywatny do Google – pozwala instancjom VM łączyć się z Google API za pomocą prywatnych adresów IP.
Włącz logi przepływu (Flowlogs) – umożliwia rozwiązywanie problemów związanych z siecią VPC.

161) Bloki CIDR (Classless Inter-Domain Routing)?
Zasoby w sieci używają ciągłych adresów IP, aby ułatwić routowanie. Przykład: zasób w określonej sieci może używać adresów 
IP od "69.208.0.0" do "69.208.0.15". Jak wyrazić zakres adresów, które zasoby w sieci mogą posiadać? Blok CIDR składa się 
z początkowego adresu IP ("69.208.0.0") oraz zakresu ("/28"). Przykład: blok CIDR "69.208.0.0/28" reprezentuje adresy od 
"69.208.0.0" do "69.208.0.15", łącznie 16 adresów.
Szybka wskazówka: "69.208.0.0/28" oznacza, że pierwsze 28 bitów (z 32) jest stałych. Ostatnie 4 bity mogą się zmieniać, 
2 do potęgi 4 daje 16 adresów.

162) Ile adresów reprezentuje "69.208.0.0/26"?
2 do potęgi 6 to 64 adresy. Od "69.208.0.0" do "69.208.0.63".

163) Ile adresów reprezentuje "69.208.0.0/30"?
2 do potęgi 2 to 4 adresy. Od "69.208.0.0" do "69.208.0.3".

164) Jaka jest różnica między "0.0.0.0/0" a "0.0.0.0/32"?
"0.0.0.0/0" reprezentuje wszystkie adresy IP. "0.0.0.0/32" reprezentuje tylko jeden adres IP: "0.0.0.0".

165) Zasady zapory (Firewall Rules)?
Skonfiguruj zasady zapory, aby kontrolować ruch wchodzący i wychodzący z sieci. Stateful. Każda zasada zapory ma przypisaną 
priorytet (od 0 do 65535). Priorytet 0 ma najwyższą wagę, a 65535 najniższą. Domyślna zasada z najniższym priorytetem 
(65535): Allow all egress (Zezwól na cały ruch wychodzący), Deny all ingress (Odmów wszelkiego ruchu przychodzącego). 
Domyślne zasady nie mogą być usunięte. Możesz nadpisać domyślne zasady, definiując nowe zasady z priorytetem od 0 do 65534.
Domyślna VPC ma 4 dodatkowe zasady z priorytetem 65534: "default-allow-internal", "default-allow-rdp", "default-allow-ssh", 
"default-allow-icmp".

166) Dzienniki zdarzeń systemowych (System Event Logs)?
Działania administracyjne Google. Włączone domyślnie. Przykłady: konserwacja hosta, preempcja instancji, automatyczny restart.
Wymagany dostęp: "Logging", "Logs Viewer" lub "Project Viewer".

167) Dzienniki aktywności administracyjnej (Admin Activity Logs)?
Wywołania API lub inne działania, które modyfikują konfigurację zasobów. Włączone domyślnie. Przykłady: tworzenie instancji VM, 
patchowanie zasobów, zmiana uprawnień IAM.
Cloud Storage: Modyfikacja kubełka lub obiektu.
Wymagany dostęp: "Logging", "Logs Viewer" lub "Project Viewer".

168) Przypadki użycia eksportu w Cloud Logging?
Przypadek użycia 1: Rozwiązywanie problemów przy użyciu dzienników VM. Zainstaluj agenta Cloud Logging na wszystkich 
instancjach VM i wyślij dzienniki do Cloud Logging. Wyszukaj dzienniki w Cloud Logging.
Przypadek użycia 2: Eksportuj dzienniki VM do "BigQuery" w celu zapytań za pomocą zapytań w stylu SQL. Zainstaluj agenta 
Cloud Logging na wszystkich VM i wyślij dzienniki do Cloud Logging. Utwórz zestawy danych "BigQuery" do przechowywania 
dzienników. Utwórz punkt eksportu w "Cloud Logging" z zestawem danych "BigQuery" jako miejscem docelowym.
Przypadek użycia 3: Chcesz przechować dzienniki audytowe dla zewnętrznych audytorów przy minimalnych kosztach. 
Utwórz punkt eksportu w "Cloud Logging" z kubełkami Cloud Storage (Cloud Storage buckets) jako miejscem docelowym (sink). 
Przyznaj audytorom rolę "Storage Object Viewer" dla kubełka (bucket).

169) Eksportowanie dzienników w "Cloud Logging"?
Dzienniki są idealnie przechowywane w "Cloud Logging" przez ograniczony czas. Dla długoterminowego przechowywania 
(zgodność, audyt) dzienniki można eksportować do:
Kubełka (bucket) Cloud Storage (przykład: "bucket/syslog/20250505").
Zestawu danych BigQuery (przykład: tabele "syslog-20250505 > kolumny, timestamp, log").
Tematu (topic) Cloud Pub/Sub (logi zakodowane w base64).
Jak eksportować dzienniki? Utwórz punkty eksportu do tych miejsc docelowych za pomocą "Log Router": Możesz utworzyć 
filtry włączające lub wykluczające, aby ograniczyć eksportowane dzienniki.

170) "Cloud Logging" - Kontrola i Routing?
Jak zarządzać swoimi dziennikami? Dzienniki z różnych źródeł trafiają do "Log Router". "Log Router" sprawdza je zgodnie 
ze skonfigurowanymi zasadami: Co przyjąć? Co odrzucić? Dokąd skierować?
Dwa typy kubełków dzienników (logs buckets): 
a) "_Required": Przechowuje dzienniki aktywności administratorów, zdarzenia systemowe oraz dzienniki przejrzystości 
dostępu (przechowywane przez 400 dni). Brak opłat, nie można usunąć kubełka. Nie można zmieniać okresu przechowywania. 
b) "_Default": Wszystkie inne dzienniki (przechowywane przez 30 dni). Opłaty są naliczane zgodnie z cennikiem 
"Cloud Logging". Nie można usunąć kubełka, ale można wyłączyć trasę eksportu "_Default" do wyłączenia przyjmowania 
dzienników. Możesz edytować ustawienia przechowywania (od 1 do 3650 dni).

171) "Cloud Logging" - Dzienniki audytowe i bezpieczeństwa?
"Access Transparency Log": Rejestruje działania wykonywane przez zespół GCP na Twoich zasobach (nie obsługiwane przez 
wszystkie usługi). Dostępne tylko dla organizacji z poziomem wsparcia Global i wyższym.
"Cloud Audit Logs": Odpowiada na pytanie, kto, co, kiedy i gdzie:
Dzienniki aktywności administratora (Admin activity logs).
Dzienniki dostępu do danych (Data Access Logs).
Dzienniki zdarzeń systemowych (System Event Audit Logs).
Dzienniki audytowe odmowy polityki (Policy Denied Audit Logs).

172) Potrzeba "Google Cloud VPC"?
W sieci korporacyjnej lub centrum danych w siedzibie firmy: Czy ktokolwiek z internetu może zobaczyć wymianę danych 
między aplikacjami a bazą danych? Nie. Czy ktokolwiek z internetu może bezpośrednio połączyć się z Twoją bazą danych? 
Zwykle nie. Sieć korporacyjna zapewnia bezpieczną wewnętrzną sieć, chroniącą Twoje zasoby, dane i komunikację przed 
użytkownikami zewnętrznymi. Jak stworzyć swoją prywatną sieć w chmurze? Dodaj (Virtual Private Cloud) (VPC).

173) "Pub/Sub" - Wysyłanie i odbieranie wiadomości?
Publisher wysyła wiadomość do Topic. Wiadomość jest dostarczana indywidualnie do każdej subskrypcji. Subskrybenci mogą 
odbierać wiadomości na dwa sposoby:
a) "Push": "Pub/Sub" wysyła wiadomość do subskrybenta.
b) "Pull": Subskrybenci pobierają wiadomości poprzez polling.
Subskrybenci wysyłają potwierdzenie (acknowledgement). Wiadomości są usuwane z kolejki wiadomości subskrypcji. 
"Pub/Sub" zapewnia, że wiadomości są przechowywane w ramach subskrypcji, dopóki nie zostaną potwierdzone.

174) "Cloud Monitoring"?
Narzędzia do monitorowania Twojej infrastruktury. Mierzy kluczowe aspekty usług (Metrics). Tworzy wizualizacje 
(Wykresy i Dashboards). Konfiguruje alerty (kiedy metryki nie są zdrowe). Definiowanie zasad alertów: Warunki, 
Powiadomienia - wiele kanałów, Dokumentacja. Workspaces są potrzebne do organizowania informacji monitorujących.
Krok 1: Utwórz workspace w określonym projekcie ("Host Project").
Krok 2: Dodaj inne projekty GCP (lub konta AWS) do workspace.
Domyślne metryki monitorowane (dla VM): Wykorzystanie CPU, Niektóre metryki ruchu dyskowego, Ruch sieciowy, Informacje 
o dostępności. Zainstaluj "Cloud Monitoring Agent" na VM, aby uzyskać więcej metryk dotyczących dysku, CPU, sieci i 
procesów: demon oparty na collectd. Zbiera metryki z VM i wysyła je do "Cloud Monitoring".

175) Logi dostępu do danych (Data Access Logs)?
Odczyt konfiguracji zasobów. Domyślnie wyłączone. Przykłady: Wyświetlanie zasobów (VM, obrazy itp.). 
Cloud Storage: Modyfikacja/Odczyt kubełka (bucket) lub obiektu. Wymagany dostęp: "Logging/Private Logs Viewer" 
lub "Project/Viewer".

176) Logi odrzuconych zasad (Policy Denied Logs)?
Gdy użytkownik lub konto usługi (service account) ma dostęp pochodny (derived access). Włączone domyślnie. 
Przykłady: Logi naruszenia polityki bezpieczeństwa.
Wymagany dostęp: "Logging/Logs Viewer" lub "Project/Viewer".

177) Cloud Logging?
Narzędzie do zarządzania logami w czasie rzeczywistym i analizy. Pozwala na przechowywanie, wyszukiwanie, analizowanie 
i generowanie alertów na podstawie ogromnej ilości danych.
Skala Exabyte, w pełni zarządzana usługa - brak konieczności provisionowania serwerów, łatania (patching). 
Pobieranie danych logów z dowolnego źródła.
Główne funkcje:
"Logs Explorer": Wyszukiwanie, sortowanie i analiza przy użyciu elastycznych zapytań.
"Logs Dashboard": Bogata wizualizacja.
"Logs Metrics": Zbieranie metryk z logów (przy użyciu zapytań/dopasowanych ciągów).
"Logs Router": Kierowanie różnych wpisów logów do różnych celów.

178) Cloud Logging - Zbieranie?
Większość usług zarządzanych przez GCP automatycznie wysyła logi do "Cloud Logging": GKE, App Engine, Cloud Run.
Pobieranie logów z "GCE VMs": Zainstaluj agenta logowania (opartego na "fluentd"). Uruchom agenta logowania na wszystkich 
instancjach VM.  Pobieranie logów z lokalnych środowisk: Użyj narzędzia "Bind-Plane" od "Blue Medora". 
Użyj "Cloud Logging API".

179) "Pub - Sub" - Przygotowanie tematu i subskrypcji?
Krok 1: Tworzenie tematu (topics).
Krok 2: Tworzenie subskrypcji. Subskrybenci rejestrują się do tematu. Każda subskrypcja reprezentuje oddzielne pobieranie 
wiadomości z tematu. Wielu klientów pobiera tę samą subskrypcję, wiadomość jest dzielona między klientów.
Wielu klientów tworzy subskrypcję, każdy klient otrzyma każdą wiadomość.

180) "Cloud Pub/Sub"?
Niezawodna, skalowalna, w pełni zarządzana usługa komunikacji synchronicznej. Szkielet dla rozwiązań o wysokiej dostępności 
i dużej skali. Automatyczne skalowanie do przetwarzania miliardów wiadomości dziennie. Niski koszt (płatność za użycie).
Przykłady zastosowań: Rejestracja i dostarczanie zdarzeń w pipeline'ach do analityki strumieniowej. Obsługuje dostarczanie 
wiadomości metodami push i pull.

181) "Pub - Sub" - Jak to działa?
Publisher: nadawca wiadomości. Wydawcy wysyłają wiadomości, wykonując zapytanie HTTPS do "pubsub.googleapis.com".
Subscriber: odbiorca wiadomości.
Pull: Subskrybent pobiera wiadomości, gdy jest gotowy. Subskrybent wykonuje zapytanie HTTPS do "pubsub.googleapis.com".
Push: Wiadomości są wysyłane do subskrybentów. Subskrybenci podają punkt końcowy webhook podczas rejestracji. 
Gdy wiadomość jest otrzymana na temacie, wysyłane jest zapytanie HTTP-s POST do punktu końcowego webhooka.
Bardzo elastyczna relacja pomiędzy wydawcą a subskrybentem: Jeden do wielu (One to Many), wiele do jednego (Many to One), 
wiele do wielu (Many to Many).

182) Relacyjna baza danych - Import i Eksport?
Cloud SQL: do - z Cloud Storage ("gcloud sql/csv export/import"). Z konsoli /gcloud/REST API. Format SQL i CSV.
Dla dużych baz danych, użyj trybu serverless. Redukuje wpływ na wydajność eksportu na działającą bazę danych.
Cloud Spanner: do - z Cloud Storage. Z konsoli (używa "Cloud Data Flow").
BigQuery: do - z Cloud Storage i innych źródeł ("bq extract/load"). Z konsoli/bq. Format: CSV/JSON (z kompresją gzip), 
Avro (z kompresją deflate lub snappy).
Wiele opcji importu danych. Ładowanie danych z Cloud Storage, Datastore, BigQuery. Ładowanie wsadowe za pomocą 
BigQuery Data Transfer Service. Dataflow do konfiguracji pipeline'u strumieniowego.

183) Bazy danych NoSQL - Import i Eksport?
Cloud Datastore/Firestore: do - z Cloud Storage. Z konsoli/gcloud/REST API.
"gcloud datastore/firestore export/import --kinds --namespaces".
Cloud BigTable: do - z Cloud Storage. Tworzenie zadań Dataflow.
Formaty: Avro/Parquet/SequenceFiles. Pamiętaj, aby zapewnić dostęp kont usługowych do kubełków Cloud Storage.
ACL ("gcloud acl ch -U SERVICE_ACCOUNT BUCKET"). Role: Storage Admin lub Storage Object Admin lub Storage Object Creator.

184) Bazy danych - ważne rzeczy do zapamiętania?
BigQuery, Datastore, Firebase nie wymagają konfiguracji VM, podczas gdy Cloud SQL i BigTable wymagają konfiguracji VM.
Bazy danych relacyjne: małe lokalne bazy danych: Cloud SQL. 
Bardzo skalowalne bazy danych globalne: Cloud Spanner. 
Magazyn danych: BigQuery.
Bazy danych NoSQL: Bazy danych transakcyjne dla kilku terabajtów danych - Cloud Datastore. 
Ogromna ilość danych z "Internet of Things" lub analityki strumieniowej - Cloud BigTable.

185) Komunikacja synchroniczna?
Aplikacje na Twoim serwerze internetowym wykonują synchroniczne wywołania do usługi logowania. Co się stanie, jeśli 
Twoja usługa logowania przestanie działać? Czy Twoje aplikacje również przestaną działać? Co jeśli nagle pojawi się duże 
obciążenie i napłynie wiele logów? Usługa logowania nie będzie w stanie poradzić sobie z obciążeniem i będzie często 
przestawać działać.

186) Potrzeba komunikacji asynchronicznej
Utwórz temat i pozwól aplikacjom umieszczać wiadomości logów na tym temacie. Usługa logowania odbiera je do przetworzenia, 
gdy jest gotowa.
Zalety:
Rozdzielenie: Wydawca (aplikacje) nie musi przejmować się tym, kto słucha.
Dostępność: Wydawca (aplikacja) działa nawet, jeśli subskrybent (usługa logowania) jest niedostępny.
Skalowalność: Skalowanie instancji konsumentów (usługi logowania) przy dużym obciążeniu.
Trwałość: Wiadomość nie zostanie utracona, nawet jeśli subskrybent (usługa logowania) jest niedostępny.

187) Bazy danych w pamięci
Odczyt danych z pamięci jest znacznie szybszy niż odczyt danych z dysku. Baza danych w pamięci, taka jak Redis, zapewnia 
opóźnienie na poziomie mikrosekund, przechowując trwałe dane w pamięci.
Zalecana zarządzana usługa GCP: Memory Store.
Przykłady zastosowań:
a) Caching.
b) Zarządzanie sesjami.
c) Tablice wyników w grach.
d) Aplikacje geospatialne.

188) Cloud SQL?
W pełni zarządzana usługa bazy danych relacyjnych: Skonfiguruj swoje potrzeby i nie martw się o zarządzanie bazą danych. 
Obsługuje MySQL, PostgreSQL i SQL Server. Usługa regionalna zapewniająca wysoką dostępność (99,95%). Dla najlepszej 
wydajności używaj SSD, ale dostępne są również HDD. Do 416 GB RAM i 30 TB przestrzeni dyskowej.
Używaj Cloud SQL w przypadku prostych zastosowań relacyjnych:
a) Migracja lokalnych baz danych MySQL, PostgreSQL i SQL Server.
b) Zmniejszenie kosztów utrzymania dla prostej bazy danych relacyjnej.
c) Pamiętaj, aby używać Cloud Spanner zamiast Cloud SQL w następujących przypadkach:
Ogromne wolumeny danych relacyjnych (terabajty). Potrzebujesz nieskończonej skalowalności. Wyższa dostępność (99,999%).
Potrzebujesz globalnej bazy danych.

189) Funkcje Cloud SQL?
Automatyczne szyfrowanie (tabele/kopie zapasowe), konserwacja i aktualizacje.
Wysoka dostępność i przełączanie awaryjne. Tworzenie zapasowego serwera z automatycznym przełączaniem awaryjnym.
Wymagania wstępne: Automatyczne kopie zapasowe i logowanie binarne.
Repliki do odczytu dla obciążeń odczytu: Opcje: Cross-zone, cross-region, oraz zewnętrzne (External) (bazy danych spoza Cloud SQL).
Automatyczne zwiększanie pamięci bez przestojów (dla nowszych wersji).
Odzyskiwanie do punktu w czasie: Włącz logowanie binarne.
Kopie zapasowe (automatyczne i na żądanie).
Obsługuje migrację z innych źródeł: Użyj usługi migracji baz danych (DMS).
Możliwość eksportu danych z UI (konsola) lub gcloud w formatach: SQL lub CSV.

190) Cloud SQL - Wysoka dostępność?
Utwórz konfigurację Wysokiej dostępności (HA). Wybierz strefy główną i zapasową w obrębie regionu. Będziesz mieć dwie 
instancje: główną i zapasową. Zmiany z instancji głównej są synchronizowane do zapasowej. W przypadku awarii strefy 
następuje automatyczne przełączenie awaryjne na zapasową instancję. Jeśli strefa główna stanie się ponownie dostępna, 
przełączenie awaryjne nie nastąpi automatycznie. Pamiętaj, że konfiguracja Wysokiej Dostępności nie może być używana jako 
Replika odczytowa (Read Replica).

191) Cloud Spanner
W pełni zarządzana, krytyczna dla misji (mission critical), relacyjna baza danych SQL, rozproszona globalnie z bardzo 
wysoką dostępnością (99,999%).
Silna spójność transakcyjna na globalną skalę. Skalowana do petabajtów danych z automatycznym partycjonowaniem. 
Cloud Spanner skaluje się poziomo dla operacji odczytu i zapisu. Możliwość konfiguracji liczby węzłów.
Pamiętaj, że w porównaniu, Cloud SQL oferuje repliki odczytowe, ale nie możesz poziomo skalować operacji zapisu w Cloud SQL!
Konfiguracje regionalne i multi-regionalne.
Droższe (w porównaniu do Cloud SQL): Płatność za węzły i pamięć.
Eksport danych: Użyj Cloud Console do eksportu danych. Inną opcją jest użycie Data Flow do automatyzacji eksportu.

192) Magazyn danych BigQuery
Rozwiązanie magazynu danych nowej generacji na skalę Exabyte w GCP. Baza danych relacyjna (SQL, schemat, spójność). 
Używaj zapytań podobnych do SQL, aby przetwarzać ogromne zestawy danych.
Tradycyjny (przechowywanie + obliczenia) kontra Nowoczesny (w czasie rzeczywistym + bezserwerowy).
Mówiąc o magazynie danych, importowanie i eksportowanie danych (oraz formaty) staje się bardzo ważne. 
Ładowanie danych z różnych usług, w tym danych strumieniowych.
Różnorodność formatów importu: CSV, JSON, Avro, Parquet, ORC, kopie zapasowe Datastore.
Eksport do Cloud Storage (przechowywanie długoterminowe) i Data Studio (wizualizacja).
Formaty: CSV/JSON (z kompresją gzip), Avro (z kompresją deflate lub snappy).
Automatyczne wygasanie danych (konfigurowalne wygasanie tabeli).
Zapytania do zewnętrznych źródeł danych bez przechowywania danych w BigQuery: Cloud Storage, Cloud SQL, BigTable, Google Drive.
Używaj stałych lub tymczasowych zewnętrznych tabel.

193) BigQuery - Dostęp do danych i zapytania?
Dostęp do bazy danych za pomocą:
a) Cloud Console.
b) Narzędzia wiersza poleceń 'bq' (nie gcloud).
c) BigQuery REST API.
c) Biblioteki oparte na HBase API (Java, .NET i Python).
Pamiętaj, że zapytania w BigQuery mogą być kosztowne, ponieważ wykonujesz je na dużych zbiorach danych!
Najlepsza praktyka: Oszacuj zapytania w BigQuery przed ich uruchomieniem:
Użyj UI (konsola) / 'bq' (--dry-run) - uzyskaj szacowaną objętość przetworzonych danych (oszacowanie).
Użyj Pricing Calculator: Znajdź cenę za skanowanie 1 MB danych. Oblicz koszt.

194) Cloud Firestore (Datastore) kontra Cloud BigTable?
Cloud Datastore: Zarządzana, bezserwerowa baza danych NoSQL typu dokumentowego (document database). Oferuje transakcje ACID, 
zapytania podobne do SQL oraz indeksy. Zaprojektowana do obsługi transakcyjnych aplikacji mobilnych i internetowych. 
Firestore (następna wersja Datastore) dodaje:
a) Silną spójność.
b) Biblioteki klienckie dla aplikacji mobilnych i webowych.
Zalecana dla małych i średnich baz danych (od 0 do kilku terabajtów).
Cloud BigTable: Zarządzana, skalowalna baza danych NoSQL z szerokimi kolumnami. Nie jest bezserwerowa (musisz tworzyć instancje).
Zalecana dla danych od 10 terabajtów do kilku petabajtów.
Zalecana dla dużych obciążeń analitycznych i operacyjnych.
Nie jest zalecana dla obciążeń transakcyjnych (nie obsługuje transakcji obejmujących wiele wierszy - obsługuje tylko 
transakcje na jednym wierszu).

195) Bazy danych NoSQL?
"NoSQL" = nie tylko SQL. Elastyczna struktura schematu. Strukturalizuj dane w sposób, który odpowiada Twojej aplikacji. 
Pozwól, aby schemat ewoluował w miarę upływu czasu.
Skaluj poziomo do petabajtów danych z milionami TPS. Nie jest to w 100% dokładne uogólnienie, ale świetny punkt wyjścia. 
Typowe bazy danych NoSQL dokonują kompromisu między "Silną spójnością i funkcjami SQL", aby osiągnąć 
"Skalowalność i wysoką wydajność".
Zarządzane usługi Google: "Cloud Firestore (Datastore)", "Cloud BigTable".

196) Bazy danych relacyjne - OLAP vs OLTP?
OLAP i OLTP używają podobnych struktur danych, ale bardzo różnią się podejściem do przechowywania danych. Bazy danych OLTP 
używają przechowywania wierszowego. Każdy wiersz tabeli jest przechowywany razem. Jest to wydajne w przypadku przetwarzania 
małych transakcji. Bazy danych OLAP używają przechowywania kolumnowego. Każda kolumna tabeli jest przechowywana razem. 
Wysoka kompresja - efektywne przechowywanie petabajtów danych. Rozproszona architektura danych - jedna tabela na wielu 
węzłach klastra. Wykonywanie pojedynczego zapytania na wielu węzłach - złożone zapytania mogą być wykonywane efektywnie.

197) Baza danych relacyjna - OLAP (Online Analytics Processing)?
Aplikacje umożliwiające użytkownikom analizowanie petabajtów danych. Przykłady: Aplikacje do raportowania, hurtownie danych, 
aplikacje do analityki biznesowej, systemy analityczne. Przykładowa aplikacja: Ustalanie składek ubezpieczeniowych na 
podstawie analizy danych z ostatnich stu lat. Dane są konsolidowane z wielu baz danych transakcyjnych. Zalecana zarządzana 
usługa GCP - BigQuery, hurtownia danych rozproszona na poziomie petabajtów.

198) Baza danych relacyjna - OLTP (Online Transaction Processing)?
Aplikacje, w których duża liczba użytkowników wykonuje dużą liczbę małych transakcji. Małe operacje na danych: odczyty, 
aktualizacje i usunięcia. Przykłady użycia: Większość tradycyjnych aplikacji, ERP, CRM, e-commerce, bankowość. Popularne 
bazy danych: MySQL, Oracle, SQL Server itp.
Zalecane zarządzane usługi Google: CloudSQL obsługuje PostgreSQL, MySQL i SQL Server dla baz danych regionalnych 
(do kilku terabajtów). CloudSpanner - nieskończona skalowalność (wiele petabajtów) i "99,999%" dostępności dla aplikacji 
globalnych z poziomym skalowaniem.

199) Bazy danych relacyjne?
To były jedyne opcje dekady temu! Najbardziej popularny typ baz danych. Wstępnie zdefiniowane schematy z tabelami i 
relacjami. Bardzo silne możliwości transakcyjne. Używane do: przypadków użycia OLTP (Online Transaction Processing) 
oraz OLAP (Online Analytics Processing).

200) Jakie miejsca mogą być używane jako "sinks" (odbiorniki) dla danych logów raportowania z Cloud Logging?
Cloud Storage, Cloud BigQuery, Cloud Pub/Sub.

201) Które kosze logów (log buckets) otrzymują logi z (dwóch) domyślnych "sinks" (odbiorników) logów?
'_Default', '_Required'.

202) Rodzaje logów audytu w chmurze (Cloud Audit Logs)?
Logi aktywności administratora. Logi audytu dostępu do danych. Logi audytu odrzuconych polityk.

203) Chcesz przejrzeć logi dla konkretnego żądania?
Cloud Logging.

204) Chcesz zidentyfikować istotne (prominent) wyjątki (lub błędy) dla konkretnej mikrousługi?
Error Reporting.

205) Chcesz śledzić żądanie przez wiele mikrousług?
Cloud Trace.

206) Chciałbyś rejestrować wszystkie operacje/żądania na wszystkich obiektach w koszu (bucket) (do audytu)?
Włącz logowanie audytu danych dla tego kosza.

207) Cloud Storage - Strona statyczna?
a) Utwórz kosz o tej samej nazwie, co nazwa witryny (Nazwa kosza powinna odpowiadać nazwie DNS witryny). 
Zweryfikuj, że domena należy do Ciebie.
b) Skopiuj pliki do kosza. Dodaj pliki index i error HTML, aby poprawić doświadczenie użytkownika.
c) Dodaj członka "allUsers" i przyznaj opcję "storage Object Viewer". Wybierz "Allow Public Access".

208) Podstawy baz danych?
Bazy danych zapewniają zorganizowane i trwałe przechowywanie danych. Aby wybrać odpowiedni typ bazy danych, musimy 
zrozumieć: dostępność (Availability), trwałość (Durability), RTO, RPO, spójność, transakcje.

209) Baza danych - migawki (snapshots)?
Zautomatyzuj robienie kopii bazy danych (tworzenie migawki) co godzinę do innego centrum danych w Londynie. 
Rozważmy pewne wyzwania.
Wyzwanie 1: Twoja baza danych przestanie działać, jeśli centrum danych ulegnie awarii.
Wyzwanie 2 (częściowo rozwiązane): Stracisz dane, jeśli baza danych ulegnie awarii. Możesz przywrócić bazę danych z 
najnowszej migawki. Jednak w zależności od tego, kiedy wystąpi awaria, możesz stracić do godziny danych.
Wyzwanie 3: Baza danych będzie działać wolniej podczas robienia migawek.

210) Cloud Storage Signed URL?
Chciałbyś umożliwić użytkownikowi dostęp do swoich obiektów na ograniczony czas: Użytkownicy nie muszą posiadać kont Google.
Skorzystaj z funkcji "Signed URL". Jest to URL, który przyznaje uprawnienia na określony czas, umożliwiając wykonanie 
określonych działań.
Aby utworzyć signed URL:
Utwórz klucz (YOUR_KEY) dla konta usługi / użytkownika z odpowiednimi uprawnieniami.
Utwórz Signed URL za pomocą klucza: "gsutil signurl -d 10m YOUR_KEY gs://NAZWA_KOSZA/ŚCIEŻKA_OBIEKTU".

211) Baza danych - dziennik transakcji (Transaction Log)?
Dodajmy dzienniki transakcji do bazy danych i stwórzmy proces kopiowania ich do drugiego centrum danych. Rozważmy wyzwania:
Wyzwanie 1: Twoja baza danych przestanie działać, jeśli centrum danych ulegnie awarii.
Wyzwanie 2 (rozwiązane): Stracisz dane, jeśli baza danych ulegnie awarii. Możesz przywrócić bazę danych z najnowszej 
migawki i zastosować dzienniki transakcji.
Wyzwanie 3: Baza danych będzie działać wolniej podczas robienia migawek.

212) Baza danych - Dodaj bazę zapasową (standby)?
Odpowiedź: Dodajmy zapasową bazę danych w drugim centrum danych z replikacją. Kilka wyzwań:
Wyzwanie 1 (Rozwiązane): Twoja baza danych przestanie działać, jeśli centrum danych ulegnie awarii. Możesz przełączyć 
się na zapasową bazę danych.
Wyzwanie 2 (Rozwiązane): Stracisz dane, jeśli baza danych ulegnie awarii.
Wyzwanie 3 (Rozwiązane): Baza danych będzie działać wolniej podczas robienia migawek (snapshots). 
Twórz migawki z bazy zapasowej. Aplikacje łączące się z główną bazą danych będą miały zawsze dobrą wydajność.

213) Dostępność (Availability) i trwałość (Durability)?
Dostępność: Czy będę w stanie uzyskać dostęp do moich danych teraz i kiedy ich będę potrzebować? Procent czasu, w którym 
aplikacja zapewnia oczekiwane operacje.
Trwałość: Czy moje dane będą dostępne po 10, 100 czy 1000 latach? Przykłady pomiaru dostępności i trwałości:
4 dziewiątki - "99,99%," 11 dziewiątek - "99,999999999%." Zwykle dostępność na poziomie czterech dziewiątek uznawana 
jest za bardzo dobrą. Zwykle trwałość na poziomie jedenastu dziewiątek uznawana jest za bardzo dobrą.

214) Trwałość (Durability)?
Co oznacza trwałość na poziomie 11 dziewiątek? Jeśli przechowasz milion plików przez milion lat, spodziewasz się utraty 
jednego pliku. Dlaczego trwałość powinna być wysoka? Ponieważ nie lubimy tracić danych. Gdy stracimy dane, 
są one na zawsze utracone.

215) Zwiększanie dostępności i trwałości baz danych?
Zwiększanie dostępności (Availability): Miej dostępnych wiele baz zapasowych lub rozprosz bazę danych: 
w wielu regionach, w wielu strefach.
Zwiększanie trwałości (Durability): Wiele kopii danych (bazy zapasowe, migawki, dzienniki transakcji i repliki). 
W wielu strefach, w wielu regionach. Replikowanie danych wiąże się z własnymi wyzwaniami.

216) RTO i RPO?
Wyobraź sobie utratę transakcji finansowej. Wyobraź sobie utratę transakcji handlowej. Wyobraź sobie giełdę, 
która przestaje działać przez godzinę. Zwykle firmy zgadzają się na pewien czas przestoju, ale nie znoszą utraty danych. 
Dostępność i trwałość to miary techniczne. Jak mierzymy, jak szybko możemy odzyskać dane po awarii?
RPO (Recovery Point Objective): Maksymalny akceptowalny okres utraty danych.
RTO (Recovery Time Objective): Maksymalny akceptowalny czas przestoju.
Osiągnięcie minimalnych wartości RTO i RPO jest kosztowne. Kompromis zależy od krytyczności danych.

217) Kategorie baz danych?
Relacyjne (OLTP i OLAP), Dokumentowe (Document), Klucz-Wartość (Key-Value), Grafowe (Graph), W Pamięci (in memory).
Czynniki przy wyborze bazy danych:
Czy chcesz stały schemat?
Czy chcesz elastyczności w definiowaniu i zmienianiu schematu? (Bez schematu).
Jakie właściwości transakcji są Ci potrzebne? (Atomowość i spójność) (atomicity and consistency).
Jakiego opóźnienia oczekujesz? (Sekundy, milisekundy czy mikrosekundy).
Ile transakcji przewidujesz? (Setki, tysiące, miliony transakcji na sekundę).
Ile danych będzie przechowywanych? (Megabajty, gigabajty, terabajty, petabajty).

218) Cloud Trace?
System śledzenia rozproszonego dla GCP: Zbieraj dane o opóźnieniach z: 
Obsługiwanych usług Google Cloud, Aplikacji z instrumentacją (korzystających z bibliotek śledzenia) przy użyciu API 
Cloud Trace. 
Dowiedz się:
Jak długo usługa zajmuje obsługę żądań?
Jaki jest średni czas opóźnienia żądań?
Jak nam idzie w czasie? (trend wzrostu/spadku).
Obsługiwane dla: Compute Engine, GKE, App Engine (Flexible/Standard) itp.
Dostępne biblioteki klienta Trace dla: "C#", Go, Java, Node.js, PHP, Python, Ruby.

219) Error Reporting?
Jak zidentyfikować problemy w produkcji w czasie rzeczywistym? Monitorowanie wyjątków w czasie rzeczywistym. 
Agreguje i wyświetla błędy zgłoszone przez usługi chmurowe (korzystając ze stack trace).
Centralna konsola zarządzania błędami: Identyfikuj i zarządzaj najważniejszymi błędami lub najnowszymi błędami. 
Użyj Firebase Crash Reporting do błędów z aplikacji klienckich na Androida i iOS.
Obsługiwane dla: Go, Java, .NET, Node.js, PHP, Python i Ruby.
Błędy mogą być zgłaszane przez:
A) Wysyłanie ich do Cloud Logging.
B) Wywoływanie API Error Reporting.
Error Reporting jest dostępny na desktopie.

220) Cloud Profiler?
Jak zidentyfikować wąskie gardła w wydajności w produkcji? Cloud Profiler - Statystyczny profiler o niskim narzucie.
Ciągle zbiera dane o użyciu CPU i pamięci z systemów produkcyjnych. Łączenie danych profilowania z kodem źródłowym aplikacji: 
Łatwe identyfikowanie wąskich gardeł w wydajności.
Dwa główne składniki:
a) Agent profilowania (Profiling agent) (zbiera informacje o profilowaniu).
b) Interfejs Profiler (Profiler interface) (wizualizacja).

221) Service Level Objective (SLO) - SLI plus target?
Dostępność (Availability) "99,99%", trwałość (Durability) 11 dziewiątek. 
Czas odpowiedzi: 99 percentyl - 1 sekunda. Wybór odpowiedniego SLO jest skomplikowany.

222) Którą z tych ról przypisałbyś swoim użytkownikom, aby umożliwić im dostęp do aplikacji chronionych przez 
Identity Aware Proxy (IAP)?
IAP - Użytkownik aplikacji zabezpieczonej w sieci (Secured Web App User).

223) Dzielisz klaster GKE między różne zespoły. Chcesz zapewnić, aby każdy zespół miał dostęp do wdrażania tylko do 
swoich specyficznych przestrzeni nazw (namespaces)?
Kubernetes RBAC (Role Based Access Control).

224) Która rola daje pełny dostęp do klastrów GKE i obiektów API Kubernetes?
Kubernetes Engine Admin ("roles/container.admin").

225) Masz przypisane uprawnienia za pomocą RBAC, ale nie możesz uzyskać dostępu do klastra?
Sprawdź, czy członek IAM (IAM Member) ma uprawnienie - "container.cluster.get".

226) OAuth?
Umożliwia użytkownikom udzielanie dostępu do zasobów serwera innej jednostce bez udostępniania poświadczeń 
(sharing credentials).
Przykład: Udostępnienie dostępu do swojego Google Drive aplikacji do edycji zdjęć.

227) OpenID Connect (OIDC)?
OAuth dotyczy tylko autoryzacji (Authorization). Nie interesuje się tym, jak implementowana jest autentykacja (Authentication). 
Co jeśli chcesz pozwolić użytkownikom logować się do swojej aplikacji za pomocą Google Id? Użyj OpenID Connect (OIDC). 
OpenID Connect rozszerza OAuth2.

228) Jak ACL (Access Control List) różni się od IAM?
Uprawnienia IAM mają zastosowanie do wszystkich obiektów w koszu (bucket). ACL mogą być używane do dostosowania 
specyficznych uprawnień do różnych obiektów.
IAM - uprawnienia ogólne.
ACL - dostosowanie dostępu do poszczególnych obiektów.

229) Chcesz przechowywać swoje klucze szyfrujące na miejscu, szyfrować dane na miejscu i wysyłać zaszyfrowane dane do 
Cloud Storage. Które z tych podejść byś zalecił?
Szyfrowanie po stronie klienta (Client-Side Encryption).

230) Kubernetes Stateful Deployments?
Kubernetes wspiera stanowe wdrożenia, takie jak Kafka, Redis, Zookeeper.
"Stateful-Set" – Zbiór podów z unikalnymi, trwałymi tożsamościami (identities) i stabilnymi nazwami hostów.

231) GKE - Jak uruchomić usługi na węzłach do zbierania logów lub monitorowania?
"Daemon-Set" – Jeden pod na każdym węźle! Dla usług działających w tle.

232) Kubernetes Cluster - Włącz auto-skalowanie dla mikroserwisu. Chcesz również zwiększyć liczbę węzłów w klastrze, 
jeśli istniejące węzły nie będą wystarczające?
Użyj komendy "kubectl autoscale deployment" do skalowania podów oraz komendy "gcloud container clusters update" z opcją 
"--enable-autoscaling", aby włączyć auto-skalowanie klastra.

233) Jak usługa w klastrze Kubernetes może komunikować się z inną usługą w tym samym klastrze Kubernetes bez 
twardego kodowania (hardening) URL?
Odkrywanie usług (Service Discovery). W pełni kwalifikowana nazwa domeny (FQDN) - 
"Service-Name.Namespace-Name.svc.cluster.local".

234) Kubernetes Services i Ingress?
Services to zestawy podów z punktem końcowym sieciowym, który może być używany do odkrywania usług i rozkładania obciążenia.
Ingress to zbiór reguł do routingu zewnętrznego ruchu HTTPS do usług.

235) App Engine, które typy skalowania nie pozwalają na określenie maksymalnej liczby instancji?
Manual-scaling.

236) App Engine - Pamiętaj?
App Engine jest regionalny (usługi wdrażane w różnych strefach). Nie można zmienić regionu aplikacji. To dobra opcja 
dla prostych mikroserwisów (wielu usług).
Użyj standardowej wersji v2, gdy korzystasz z obsługiwanych języków. Użyj Flexible, jeśli budujesz aplikacje kontenerowe.
Pamiętaj - przynajmniej jedna instancja kontenera jest zawsze uruchomiona w trybie flexible.
Wybierz standard, jeśli chcesz mieć możliwość skalowania liczby instancji do zera, gdy nie ma obciążenia.
Używaj kombinacji instancji stałych i dynamicznych:
Instancje stałe: działają ciągle.
Instancje dynamiczne: dodawane w zależności od obciążenia.
Wybierz tylko instancje dynamiczne, jeśli zależy Ci na kosztach. Jeśli nie jesteś bardzo wrażliwy na koszty, 
utrzymuj zawsze zestaw instancji stałych.

237) Kroki wdrożenia mikroserwisu w Kubernetes?
a) Stwórz klaster Kubernetes z domyślną pulą węzłów: 
"gcloud container cluster create", lub użyj konsoli chmurowej.
b) Zaloguj się do Cloud Shell.
c) Połącz się z klastrem Kubernetes:
"gcloud container cluster get-credentials my-cluster --zone us-central1-a --project my-project-123".
d) Wdróż mikroserwis do Kubernetes: Stwórz deployment i usługę przy użyciu komend kubectl:
"kubectl create deployment hello-world --image=img:REL".
"kubectl expose deployment hello-world --type=LoadBalancer --port=8080".
e) Zwiększ liczbę instancji mikroserwisu:
"kubectl scale deployment hello-world --replicas=2".
f) Zwiększ liczbę węzłów w klastrze Kubernetes:
"gcloud container cluster resize my-cluster --node-pool my-node-pool --replicas=2".
Nie jesteś zadowolony z ręcznego zwiększania liczby instancji i węzłów!
g) Skonfiguruj auto-skalowanie dla swojego mikroserwisu:
"kubectl autoscale deployment hello-world --max=10 --cpu-percent=70".
Nazywane również horizontal pod autoscaling (HPA) - "kubectl get hpa".
h) Skonfiguruj auto-skalowanie dla swojego klastra Kubernetes:
"gcloud container cluster update cluster-name --enable-autoscaling --min-nodes=1 --max-nodes=10".
i) Dodaj konfigurację aplikacji dla mikroserwisu. ConfigMap:
"kubectl create configmap todo-web-application-config --from-literal=RDS_DB_NAME=todos".
j) Dodaj konfigurację hasła dla mikroserwisu: secrety Kubernetes.
"kubectl create secret generic todo-web-app-secrets-1 --from-literal=RDS_PWD=dummypwd".
k) Wdróż nowy mikroserwis, który wymaga węzłów z podłączonym GPU. 
Dodaj nową pulę węzłów z instancjami GPU do swojego klastra:
"gcloud container node-pools create POOL_NAME --cluster CLUSTER_NAME".
"gcloud container node-pools list --cluster CLUSTER_NAME".
Wdróż nowy mikroserwis do nowej puli, ustawiając "nodeSelector" w pliku "deployment.yaml":
"nodeSelector: 'cloud.google.com/gke-nodepool:POOL_NAME'".
l) Wdróż mikroserwis. 
m) Usuń usługę: "kubectl delete service."
n) Usuń deployment: "kubectl delete deployment".
o) Usuń klaster: "gcloud container cluster delete".

238) Google Kubernetes Engine (GKE) Cluster
Klaster: Grupa instancji Compute Engine:
a) Węzeł Master: Zarządza klastrem.
b) Węzły robocze: Uruchamiają Twoje obciążenia (pods).
Składniki węzła Master (Control Plane):
a) API Server: Obsługuje całą komunikację w klastrze Kubernetes (zarówno z węzłami, jak i z zewnątrz).
b) Scheduler: Decyduje o rozmieszczeniu podów w klastrze.
c) Control Manager: Zarządza wdrożeniami i zestawami replik.
d) Etcd: Rozproszona baza danych przechowująca stan klastra.
Składniki węzła roboczego (Worker Node):
a) Kubelet: Zarządza komunikacją z węzłem Master.
b) Kube Proxy: Utrzymuje sieć wewnętrzną, umożliwiając komunikację pomiędzy podami. 

239) Typy Klastrów GKE (Google Kubernetes Engine)
a) Zonal Cluster: Jedna strefa - Jeden Control Plane. Węzły działają w tej samej strefie.
b) Multi-zonal Cluster: Jeden Control Plane, ale węzły działają w wielu strefach.
c) Regional Cluster: Replikacja Control Plane w wielu strefach w danym regionie. Węzły (Nodes) działają w tej samej strefie, 
w której działa Control Plane.
d) Private Cluster: VPC-native cluster. Węzły mają tylko wewnętrzne adresy IP (brak dostępu publicznego).
e) Alpha Cluster: Klastery z alpha API - wczesne wersje funkcji API. Używane do testowania nowych funkcji Kubernetes.

240) Kubernetes - Pods?
Pod to najmniejsza jednostka wdrożeniowa w Kubernetes. Pod zawiera jeden lub więcej kontenerów. Każdemu Podowi przypisany 
jest ephemeral IP address (tymczasowy adres IP). Wszystkie kontenery w Podzie dzielą wspólne zasoby:
Sieć, Pamięć masowa, Adres IP, Porty, Wolumeny (dyski współdzielone persistent).
Statusy Podów:
a) Running: Pod działa.
b) Pending: Pod czeka na przydzielenie zasobów.
c) Succeeded: Pod zakończył swoje zadanie pomyślnie.
d) Failed: Pod zakończył zadanie z błędem.
e) Unknown: Status nieznany (zwykle związany z problemami w komunikacji).

241) Kubernetes - Deployment kontra ReplicaSet?
a) Deployment:
Deployment jest tworzony dla każdej aplikacji lub mikroserwisu.
Tworzy i zarządza Podami, zapewniając ich odpowiednią liczbę w przypadku awarii.
Zapewnia zero downtime podczas aktualizacji aplikacji, zarządzając wdrożeniami nowych wersji mikroserwisów.
Przykład: "kubectl create deployment m1 --image=img:v1"
Deployment kontroluje "Replica-Set", który zapewnia, że odpowiednia liczba replik jest uruchomiona w danym czasie.
b) "Replica-Set":
"Replica-Set" zapewnia, że określona liczba Podów jest zawsze uruchomiona w danej wersji mikroserwisu.
Jeśli którykolwiek z Podów ulegnie awarii (zostanie zabity), "Replica-Set" automatycznie uruchamia nowego Poda.
W przypadku aktualizacji wersji mikroserwisu, Deployment tworzy nowy "Replica-Set" dla nowej wersji aplikacji.
Przykład: "kubectl scale deployment m2 --replicas=2".
Podsumowanie: Deployment zarządza cyklem życia aplikacji (w tym aktualizacjami), natomiast "Replica-Set" zapewnia 
stabilną liczbę replik danej wersji mikroserwisu.

242) Kubernetes - Service?
W Kubernetesie, każdy Pod ma swój własny adres IP. Aby zapewnić, że użytkownicy zewnętrzni nie zostaną zaimpaktowani, gdy:
Pod ulegnie awarii i zostanie zreplikowany przez "Replica-Set",
Następuje nowa wersja aplikacji i wszystkie stare Pody są zastępowane przez nowe wersje.
Tworzymy Service, który eksponuje aplikację w stabilny sposób. Dzięki temu zewnętrzni użytkownicy nie zauważą, 
kiedy Pody będą się zmieniały.
Jak utworzyć Service: "kubectl expose deployment Nazwa-Deploymentu --type=LoadBalancer --port=80"
Rodzaje Service w Kubernetes:
a) ClusterIP: Eksponuje usługę tylko wewnątrz klastra przy użyciu wewnętrznego IP klastra.
Przykład użycia: Chcesz, aby Twój mikroserwis był dostępny tylko wewnątrz klastra (komunikacja między mikroserwisami).
b) LoadBalancer: Eksponuje usługę zewnętrznie, korzystając z load balancera dostawcy chmurowego (np. Google Cloud, AWS).
Przykład użycia: Chcesz utworzyć osobne load balancery dla każdego mikroserwisu.
c) NodePort: Eksponuje usługę na IP każdego węzła klastra, przy użyciu stałego portu (NodePort).
Przykład użycia: Nie chcesz tworzyć load balancera dla każdego mikroserwisu, ale możesz stworzyć jeden komponent Ingress, 
który będzie równoważyć ruch pomiędzy wieloma mikroserwisami.
Podsumowanie: Service zapewnia stały punkt dostępu do aplikacji, niezależnie od zmieniających się Podów. 
Rodzaj Service zależy od tego, czy chcesz, aby usługa była dostępna tylko w klastrze, zewnętrznie, czy na poziomie węzła.

243) Container Registry - Image Repository?
Stworzyłeś obrazy Docker dla swoich mikroserwisów: Gdzie je przechowujesz? Container Registry - w pełni zarządzany 
rejestr kontenerów oferowany przez GCP. (Alternatywa) Docker Hub. Może być zintegrowany z narzędziami CI/CD do 
publikowania obrazów w repozytorium. Możesz zabezpieczyć swoje obrazy kontenerów, analizować je pod kątem podatności 
oraz egzekwować polityki wdrożeniowe. Nazewnictwo: "Host-Name / Project-ID / Image-Tag - gcr.io".

244) GKE - O czym należy pamiętać?
Replikuj węzły mastera w różnych strefach, aby zapewnić wysoką dostępność.
Część CPU na węzłach jest rezerwowana przez Control Place.
Tworzenie obrazu Docker dla Twojego mikroserwisu (Dockerfile). 
Budowanie obrazu: "docker build -y some / name : V1 .".
Testowanie lokalnie: "docker run -d -p 8080:8080 some / name : V1".
Przesyłanie do repozytorium kontenerów: "docker push some / name : V1".
Kubernetes obsługuje stanowe wdrożenia, takie jak Kafka, Redis, ZooKeeper.
"Stateful-Set": Zestaw Podów z unikalnymi, trwałymi identyfikatorami i stabilnymi nazwami hostów.
Jak uruchomić usługi na węzłach do zbierania logów lub monitorowania?
"Daemon-Set": Jeden Pod na każdym węźle! (Dla usług w tle).
Włącza się domyślnie, integruje z Cloud Monitoring i Cloud Logging.
System logowania w chmurze oraz logi aplikacji mogą być eksportowane do BigQuery lub Pub/Sub.

245) Chcesz utrzymać niskie koszty i zoptymalizować implementację GKE?
Rozważ użycie Preemptible VMs, odpowiednią regionizację oraz zniżki za zobowiązanie do długoterminowego użytkowania 
(committed use discounts). Typy maszyn E2 są tańsze niż N1.
Wybierz odpowiednie środowisko, które pasuje do typu obciążenia (jeśli to konieczne, użyj wielu pul węzłów).

246) Chcesz wydajne, w pełni automatycznie skalujące się rozwiązanie GKE?
Skonfiguruj Horizontal Pod Autoscaler dla wdrożeń i Cluster Autoscaler dla pul węzłów.

247) Chcesz uruchomić nieznany kod od osób trzecich w Kubernetes?
Utwórz nową pulę węzłów z GKE sandbox. Wdrażaj nieznany kod do puli węzłów sandbox.

248) Jaką rolę pełnią grupy instancji w klastrze Kubernetes?
Tworzą zestawy maszyn wirtualnych, które można zarządzać jako jednostką.

249) IAM - Predefiniowane Role - Przykłady Uprawnień?
Ważne role w Cloud Storage:
a) Storage Admin (roles/storage.admin) – "storage.buckets", "storage.objects".
b) Storage Object Admin (roles/storage.objectAdmin) – "storage.objects".
c) Storage Object Creator (roles/storage.objectCreator) – "storage.objects.create".
d) Storage Object Viewer (roles/storage.objectViewer) – "storage.object.get", "storage.objects.list".
Wszystkie cztery role mają te uprawnienia: "resourcemanager.projects.get", "resourcemanager.projects.list".

250) Google Kubernetes Engine - Tryb Autopilot?
Nowy tryb dla GKE. Redukuje koszty operacyjne związane z uruchamianiem klastra Kubernetes.
Zapewnia bezobsługowe doświadczenie.
Nie musisz martwić się o zarządzanie infrastrukturą klastra (węzłami, pulami węzłów). GKE zarządza klastrem za Ciebie!

251) Google Kubernetes Engine (GKE)?
Zarządzana usługa Kubernetes. Minimalizuje operacje dzięki automatycznemu naprawianiu i automatycznemu aktualizowaniu.
Zapewnia autoskalowanie Podów i klastra. Umożliwia konfigurację Cloud Logging i Cloud Monitoring w prosty sposób.
Wykorzystuje zoptymalizowany system operacyjny kontenerów, system operacyjny opracowany przez Google.
Zapewnia wsparcie dla dysków trwałych (Persistent disks) i lokalnych SSD.

252) GKE - Zarządzanie Obciążeniami za pomocą Wiersza Poleceń?
Wyświetlanie Podów, Usług, "Replica-Sets": "kubectl get pods / services / replicasets".
Tworzenie Wdrożenia: "kubectl apply -f deployment.yaml" lub "kubectl create deployment".
Tworzenie Usługi: "kubectl expose deployment hello-world --type=LoadBalancer --port=8080".
Skalowanie Wdrożenia: "kubectl scale deployment hello-world --replicas=5".
Autoskalowanie Wdrożenia: "kubectl autoscale deployment --min --max --cpu-percent".
Usuwanie Wdrożenia: "kubectl delete deployment hello-world".
Aktualizowanie Wdrożenia: "kubectl apply -f deployment.yaml".
Cofnięcie Wdrożenia: "kubectl rollout undo deployment hello-world --to-revision=1".

253) Terminologia Kubernetes:
Sprzęt (Cluster):
a) Master Node(s) – Zarządza klastrem.
b) Worker Node(s) – Uruchamiają twoje obciążenie jako pods.
c) Node Pool – Grupa węzłów w klastrze o tej samej konfiguracji.
Oprogramowanie:
a) Pods – Najmniejsza jednostka wdrożeniowa w Kubernetes. Wdrażane na Worker Node(s).
b) Deployment – Zarządza Pods.
c) Service – Udostępnia Deployments.

254) GKE - Zarządzanie Klastrem - Linia Poleceń?
a) Utwórz Klaster: "gcloud container cluster create".
b) Zmień rozmiar klastra: "gcloud container cluster resize".
c) Autoskalowanie klastra: "gcloud container cluster update".
d) Usuń klaster: "gcloud container cluster delete".
e) Dodaj Node Pool: "gcloud container node-pools create".
f) Lista obrazów: "gcloud container images list".

255) Mój pod pozostaje w stanie oczekiwania?
Prawdopodobnie problem z pobraniem obrazu.

256) Mój pod pozostaje w stanie "pending"?
Prawdopodobnie pod nie może zostać zaplanowany na węźle (brak wystarczających zasobów).

257) Chcesz umożliwić tylko wewnętrzną komunikację między wdrożeniami mikrousług w klastrze Kubernetes?
Utwórz usługę typu "Cluster-IP".

258) Polecenie do usunięcia szablonu grupy instancji:
"gcloud compute instance-template delete".

259) Kubernetes?
Najpopularniejsze open source'owe rozwiązanie do orkiestracji kontenerów. Oferuje zarządzanie klastrem (w tym aktualizacje). 
Każdy klaster może mieć różne typy maszyn wirtualnych. Zapewnia wszystkie ważne funkcje orkiestracji kontenerów:
a) Auto Scaling.
b) Odkrywanie usług (Service Discovery).
c) Load Balancer.
d) Samoleczenie (Self Healing).
e) Wdrożenie bez przestojów (Zero Downtime Deployment).

260) Polecenia do połączenia z klastrem Kubernetes:
"gcloud container cluster get-credentials".

261) Chcesz uruchomić jeden pod na każdym węźle w celu zbierania logów lub monitorowania. Którego komponentu Kubernetes użyjesz?
"Daemon-Set".

262) Który komponent węzła roboczego jest odpowiedzialny za komunikację z węzłem master?
"kubelet".

263) Cloud KMS?
Twórz i zarządzaj kluczami kryptograficznymi (symetrycznymi i asymetrycznymi). Kontroluj ich użycie w swoich aplikacjach 
i usługach GCP. Zapewnia API do szyfrowania, deszyfrowania lub podpisywania danych. Używaj istniejących kluczy 
kryptograficznych utworzonych lokalnie. Integruje się z niemal wszystkimi usługami GCP, które wymagają szyfrowania danych.
a) Klucze zarządzane przez Google (Google-managed keys): Brak konieczności konfiguracji.
b) Klucz zarządzany przez klienta (Customer-manager key): Używaj klucza z KMS.
c) Klucz dostarczony przez klienta (Customer-supplied key): Dostarcz własny klucz.

264) Szyfrowanie kluczem asymetrycznym
Dwa klucze: Klucz publiczny i klucz prywatny. Nazywane również kryptografią klucza publicznego. Szyfruj dane za pomocą 
klucza publicznego, a deszyfruj za pomocą klucza prywatnego. Udostępnij klucz publiczny wszystkim, a klucz prywatny 
zachowaj dla siebie. Nie ma dziwnych pytań: Czy ktoś nie odkryje klucza prywatnego, używając klucza publicznego?

265) Dyski trwałe - Migawki (Snapshots) - Rekomendacje?
Unikaj robienia migawek (snapshots) częściej niż raz na godzinę. Wolumeny dysków są dostępne, ale migawki obniżają 
wydajność (twórz je w godzinach poza szczytem). Tworzenie dysków z dysku jest szybsze niż z obrazów. Jednak tworzenie 
dysków z obrazu jest szybsze niż tworzenie z migawek. Jeśli wielokrotnie tworzysz dyski z migawek, stwórz obraz z migawki 
i użyj go do tworzenia dysków.
Migawki są przyrostowe (incremental): Ale nie tracisz danych usuwając starsze migawki. Usunięcie migawki usuwa tylko 
dane, które NIE są potrzebne przez inne migawki. Nie wahaj się usuwać zbędnych migawek.

266) Szyfrowanie kluczem symetrycznym
Algorytmy szyfrowania symetrycznego używają tego samego klucza do szyfrowania i deszyfrowania.
Czynnik kluczowy 1: Wybierz odpowiedni algorytm szyfrowania.
Czynnik kluczowy 2: Jak zabezpieczyć klucz szyfrowania?
Czynnik kluczowy 3: Jak udostępnić klucz szyfrowania?

267) Szyfrowanie?
Jeśli przechowujesz dane w oryginalnej formie, co by się stało, gdyby nieautoryzowana osoba uzyskała do nich dostęp? 
Wyobraź sobie utratę nieszyfrowanego dysku twardego. Pierwsza zasada bezpieczeństwa: Obrona na wielu poziomach. 
Zwykle przedsiębiorstwa szyfrują wszystkie dane. Dane na twoim dysku twardym. Dane w twojej bazie danych. 
Dane na serwerach plików. Czy wystarczy, że zaszyfrujesz dane w spoczynku? Nie. 
Szyfruj również dane w tranzycie – między aplikacją a bazą danych.

268) Stany danych (data states)?
Dane w spoczynku (Data at rest): Przechowywane na urządzeniu lub kopii zapasowej. Przykłady: dane na dysku twardym, 
w bazie danych, kopie zapasowe i archiwa.
Dane w ruchu (Data in motion): Przesyłane przez sieć. Nazywane również danymi w tranzycie. Przykłady: dane kopiowane 
z lokalnego przechowywania do chmury, aplikacja komunikująca się z bazą danych. Dwa typy: Z i do chmury (z internetu). 
W obrębie chmury.
Dane w użyciu (Data in use): Aktywne dane przetwarzane w stanie nietrwałym. Przykład: dane w pamięci RAM.

269) Jaki komponent Kubernetes odpowiada za zastąpienie niezdrowego poda?
"Replica-Set".

270) Dedykowane połączenie z Twojego centrum danych do GCP. Jaką usługę sieciową byś polecił?
Google Cloud Interconnect - Dedicated.

271) Zabawa z Dyskami - Wiersz Poleceń?
"gcloud compute disk create". Jakie powinny być rozmiar i typ? Co powinno znajdować się na dysku? 
Jak dane na dysku powinny być szyfrowane?
"gcloud compute disks resize". Obsługiwane jest tylko zwiększanie rozmiaru dysku.
"gcloud compute disks snapshot test".

272) Snapshots kontra Images kontra Machine Images?
Machine Image: Kopia zapasowa pojedynczego dysku. Kopia zapasowa wielu dysków. Kopia zapasowa różnicowa. 
Klonowanie i replikacja instancji. Konfiguracja instancji VM.
PD snapshots: Kopia zapasowa pojedynczego dysku. Kopia zapasowa różnicowa.
Custom Image: Kopia zapasowa pojedynczego dysku. Klonowanie i replikacja instancji.
Instance Template: Klonowanie i replikacja instancji. Konfiguracja instancji VM.

273) Zabawa z Machine Images?
Machine Image różni się od obrazu. Do maszyny wirtualnej (VM) można podłączyć wiele dysków. Jeden Boot Disk 
(System operacyjny działa z Boot Disk). Wiele dysków danych. Obraz jest tworzony z boot Persistent Disk. 
Jednak Machine Image jest tworzony z instancji VM. Machine Image zawiera wszystko, co jest potrzebne do utworzenia 
instancji VM: konfigurację, metadane, uprawnienia, dane z jednego lub więcej dysków. 
Zalecane do tworzenia kopii zapasowych dysków, klonowania i replikacji instancji.

274) Czy MIG może zawierać maszyny wirtualne utworzone z różnych typów maszyn?
Nie.

275) Czy te same usługi GCP mają specyficzne narzędzia CLI?
a) Cloud Storage: "gsutil".
b) Cloud BigQuery: "bq".
c) Cloud BigTable: "cbt".
d) Kubernetes: "kubectl" (oprócz gcloud, które służy do zarządzania klastrami).

276) Wyświetl wszystkie właściwości aktywnej konfiguracji?
"gcloud config list".

277) IAAS (Infrastructure as a Service)?
Korzystasz tylko z infrastruktury dostawcy chmurowego. Przykład: Używanie maszyny wirtualnej (VM) do wdrażania aplikacji 
lub baz danych. Jesteś odpowiedzialny za:
a) Kod aplikacji i środowisko uruchomieniowe.
b) Konfigurowanie Load Balancing.
c) Auto Scaling.
d) Aktualizacje i łatki systemu operacyjnego.
e) Dostępność.

278) PAAS (Platforma jako Usługa - Platform as a Service)?
Korzystasz z platformy dostarczonej przez chmurę. 
Dostawca chmurowy jest odpowiedzialny za:
a) System operacyjny (w tym aktualizacje i łatki (patches)).
b) Środowisko uruchomieniowe aplikacji.
c) Auto Scaling, dostępność i Load Balancing.
Ty jesteś odpowiedzialny za:
a) Konfigurację (usługi aplikacji).
b) Kod aplikacji (jeśli jest potrzebny).
Rodzaje:
a) CAAS (Container as a Service): Kontenery zamiast (jako) aplikacji.
b) FAAS (Function as a Service): Funkcje zamiast (jako) aplikacji.
b) Bazy danych - Relacyjne i NoSQL (RDS, Cloud SQL), Kolejki, AI, ML, Operacje

279) Compute Engine?
Maszyny wirtualne o wysokiej wydajności i ogólnego przeznaczenia (general purpose), które skalują się globalnie.

280) Google Kubernetes Engine?
Orkiestracja kontenerowych mikroserwisów na Kubernetes. Wymaga zaawansowanej konfiguracji i monitorowania klastrów. 
CaaS (Container as a Service).

281) App Engine?
Twórz wysoko skalowalne aplikacje na w pełni zarządzanej platformie, używając otwartych i znanych języków oraz narzędzi. 
PaaS (CaaS, Serverless).

282) Cloud Functions?
Twórz aplikacje wyzwalane zdarzeniami (event driven apps), używając funkcji o pojedynczym celu. FAAS, Serverless.

283) Cloud Run?
Twórz i wdrażaj wysoko skalowalne aplikacje kontenerowe. Nie wymaga klastra! CaaS (Serverless).

284) Persistent Disks kontra Local SSDs?
Persistent Disk:
a) Podłączony do instancji VM: Jako dysk sieciowy.
b) Cykl życia: Oddzielny od instancji VM.
c) Prędkość I/O: Niższa (opóźnienie sieciowe).
d) Snapshots: Obsługiwane.
e) Zastosowanie: Trwała pamięć masowa.
Local SSDs:
a) Podłączony do instancji VM: Fizycznie podłączony.
b) Cykl życia: Powiązany z instancją VM.
c) Prędkość I/O: 10-100 razy szybsza niż PD.
d) Snapshots: Nieobsługiwane.
e) Zastosowanie: Pamięć tymczasowa (Ephemeral Storage).

285) Persistent Disks - Snapshots?
Twórz migawki stanu w danym momencie dla swoich Persistent Disks. Możesz również zaplanować migawki, 
konfigurując harmonogram (schedule):
Możesz automatycznie usuwać migawki po X dniach. 
Migawki mogą być Multi-regional (wielo-regionowe) i Regional (regionalne). 
Możesz udostępniać migawki między projektami. 
Możesz tworzyć nowe dyski i instancje z migawek.
Migawki są inkrementalne: Usunięcie migawki usuwa tylko dane, które NIE są potrzebne przez inne migawki.
Trzymaj podobne dane razem na jednym Persistent Disk: Rozdziel swój system operacyjny, dane zmienne i dane trwałe. 
Podłączaj wiele dysków, jeśli to konieczne. Pomaga to lepiej zorganizować migawki i obrazy.

286) Persistent Disk (PD)?
Pamięć masowa blokowa sieciowa podłączona do Twojej instancji VM. Przydzielona pojemność. Bardzo elastyczna: 
Możliwość zwiększenia rozmiaru, kiedy tylko tego potrzebujesz – po podłączeniu do instancji VM. 
Wydajność skalowalna w zależności od rozmiaru. Aby uzyskać wyższą wydajność, zmień rozmiar lub dodaj więcej PD.
Cykl życia niezależny od instancji VM – można podłączyć/odłączyć z jednej instancji VM do innej. Opcje: Regional i Zonal.
Zonal replikowane w jednej strefie. Regional w dwóch strefach w tym samym regionie.
Zastosowanie: Uruchamianie własnej bazy danych.

287) Local SSDs - Zalety i Wady?
Zalety:
a) Bardzo szybka prędkość I/O (10 do 100 razy szybsza w porównaniu do PD).
b) Wyższa przepustowość i niższe opóźnienie.
c) Idealne do zastosowań wymagających wysokich IOPs przy przechowywaniu danych tymczasowych. Przykłady: 
Pamięci podręczne (caches), dane tymczasowe, pliki robocze itp.
Wady:
a) Pamięć tymczasowa (ephemeral storage).
b) Niższa trwałość, niższa dostępność, niższa elastyczność w porównaniu do PD.
c) Nie można odłączyć i podłączyć do innej instancji VM.

288) Local SSDs?
Fizycznie podłączone do hosta instancji VM. Zapewniają bardzo wysokie IOPs (operacje wejścia/wyjścia) oraz bardzo niskie 
opóźnienie. Ephemeral Storage - Pamięć tymczasowa (dane utrzymują się tylko do momentu, gdy instancja jest uruchomiona).
Umożliwiają migrację na żywo, dzięki czemu dane przetrwają zdarzenia związane z konserwacją. 
Dane są automatycznie szyfrowane, jednak nie można skonfigurować kluczy szyfrowania. Cykl życia powiązany z instancją VM.
Tylko niektóre typy maszyn obsługują Local SSDs. Obsługują interfejsy SCSI i NVMe.
Pamiętaj: Wybierz obrazy z obsługą NVMe i wielokolejkowego interfejsu SCSI, aby uzyskać najlepszą wydajność.
Większe Local SSDs (więcej miejsca), więcej vCPU (podłączonych do VM) = Jeszcze lepsza wydajność.

289) GCP - Block Storage?
Dwa popularne typy pamięci masowej blokowej, które mogą być podłączone do instancji VM: Local SSDs i Persistent Disks.
Local SSDs są fizycznie podłączone do hosta instancji VM. Są to dane tymczasowe, a cykl życia jest powiązany z instancją VM.
Persistent Disks to pamięć masowa sieciowa. Są bardziej trwałe. Cykl życia nie jest powiązany z instancją VM.

290) GCP - Block Storage i File Storage?
Block Storage:
a) Persistent Disks: Pamięć blokowa sieciowa. Zonal: Dane replikowane w jednej strefie. Regional: Dane replikowane w 
wielu strefach.
b) Local SSDs: Lokalna pamięć blokowa.
File Storage:
a) "File-store": Wysokowydajna pamięć masowa plików.

291) File Storage?
Przepływy pracy związane z mediami wymagają dużej, wspólnej pamięci masowej, wspierającej procesy takie jak edycja wideo. 
Użytkownicy biznesowi potrzebują szybkiego sposobu na udostępnianie plików w bezpieczny i uporządkowany sposób. 
Te udostępniane zasoby plików są współdzielone przez kilka wirtualnych serwerów.

292) Block Storage?
Dysk twardy podłączony do Twoich komputerów. Zwykle jedno urządzenie pamięci blokowej może być podłączone do jednej 
wirtualnej instancji serwera.
Wyjątki: Możesz podłączyć urządzenie pamięci blokowej tylko do odczytu do wielu wirtualnych serwerów, a niektórzy dostawcy 
chmurowi badają możliwość stosowania dysków z wieloma piszącymi (multi-writers)! Jednak możesz podłączyć wiele różnych 
urządzeń pamięci blokowej do jednej wirtualnej instancji serwera.
Stosowane jako:
a) Direct-attached storage (DAS): Podobne do dysku twardego.
b) Storage Area Network (SAN): Szybka sieć łącząca pulę urządzeń pamięci masowej; wykorzystywana przez bazy danych, 
takie jak Oracle i Microsoft SQL Server.

293) Storage Types - Block Storage i File Storage?
Jaki jest typ pamięci Twojego dysku twardego? Block Storage.
Utworzyłeś udostępniony folder, aby dzielić zestaw plików z kolegami w firmie.
Jakiego typu pamięci używasz? File Storage.

294) Skalowalność (Scalability)?
Skalowalność to zdolność systemu do radzenia sobie ze wzrostem lub spadkiem obciążenia poprzez dodawanie lub usuwanie 
zasobów w systemie w miarę potrzeby.

295) Dostepność (Availability)?
Dostępność jest definiowana jako zdolność użytkownika do uzyskania dostępu do zasobu w określonym czasie. 
Dostępność jest zwykle mierzona jako procent czasu, w którym system jest operacyjny.

296) Bootstrapping?
Instalowanie poprawek systemu operacyjnego lub oprogramowania, gdy instancja VM jest uruchamiana.

297) Niezawodność (Reliability)?
Niezawodność jest definiowana jako prawdopodobieństwo, że system spełni cele poziomu usług przez określony czas. 
Niezawodność często mierzy się jako średni czas między awariami (MTBF).

298) Utrzymywalność (Maintainability)?
Łatwość utrzymania jest definiowana jako prawdopodobieństwo przeprowadzenia pomyślnej naprawy w określonym czasie.

299) Dodawanie dodatkowego dysku przez Google Cloud Console, jakie parametry można ustawić?
a) Typ dysku.
b) Zarządzanie kluczami szyfrowania.
c) Źródłowy obraz dla dysku.

300) Polecenie gcloud do przypisania konta usługi (Service Account) podczas tworzenia VM?
"gcloud compute instance create INSTANCE_NAME --service-account SERVICE-ACCOUNT-EMAIL".

301) Jaki jest najniższy poziom hierarchii zasobów?
Projekt.

302) Cloud Dataproc?
Cloud Dataproc to zarządzana usługa dla Hadoop i Spark.

303) Jakie obiekty w GCP są czasami traktowane jako zasoby, a czasami jako tożsamości?
Konto usługi (Service Account).

304) Funkcje Kubernetes?
a) Load Balancing (równoważenie obciążenia) między maszynami wirtualnymi Compute Engine, które są wdrożone w klastrze Kubernetes.
b) Automatyczne skalowanie (Automatic scaling) węzłów w klastrze.
c) Automatyczna aktualizacja (Automatic upgrading) oprogramowania klastra w razie potrzeby.

305) Ile organizacji można utworzyć w hierarchii zasobów? 
Jedną. Hierarchia zasobów ma tylko jedną organizację. Można jednak utworzyć wiele folderów i projektów w ramach tej hierarchii.

306) Baza danych dla danych zebranych z IoT?
Cloud Bigtable. Bigtable jest zaprojektowany do przyjmowania miliardów wierszy danych.

307) Magazyn danych (Data Warehouse)?
BigQuery. BigQuery to zarządzana usługa dla magazynów danych i analiz, wspierająca dziesiątki petabajtów danych.

308) Internet of Things, komponent przetwarzania strumieniowego (filtrowany (filtered), przekształcony (transformed) 
i analizowany (analyzed))?
Cloud Dataflow. Cloud Dataflow umożliwia przetwarzanie strumieniowe (stream) i wsadowe (batch) danych i jest dobrze 
dopasowane do tego rodzaju ETL.

309) Co to jest Pod w Kubernetes? 
Pojedyncza instancja działającego procesu w klastrze.

310) Do jakich operacji używa się Stackdriver w Kubernetes Cluster? 
Powiadomienia, monitoring i logowanie.

311) Aby stworzyć tabelę o nazwie "iot-ingest-data" w BigTable, jakiego polecenia należy użyć? 
"cbt createtable iot-ingest-data".

312) Kubernetes, gdzie należy szukać informacji o liczbie Podów, które powinny być uruchomione? 
"Replica-Sets".

313) Jakie komponenty organizacyjne są dostępne w GCP? 
Organizacja, foldery, projekty.

314) Jakiego typu systemu przechowywania użyłbyś do przechowywania plików danych bazy danych? 
Przechowywanie blokowe (Block storage).

315) Polecenie do stworzenia Load Balancera sieciowego w wierszu poleceń? 
"gcloud compute forwarding-rules create".

316) Polecenie do stworzenia VPC z wiersza poleceń? 
"gcloud compute networks create".

317) Kubernetes rezerwuje zasoby CPU w procentach dostępnych rdzeni. Jaki jest ten zakres? 
Od "0,25%" do "6%".

318) Jakie rodzaje instancji są wymagane do utworzenia klastra Kubernetes? 
Master klastra i węzły (Nodes) do wykonywania obciążeń.

319) Na jakich poziomach hierarchii zasobów można utworzyć współdzieloną VPC? 
Organizacje i foldery.

320) Firestore? 
Firestore to baza danych dokumentów, która posiada funkcje wspierające aplikacje mobilne, 
takie jak synchronizacja danych.

321) Jaka część reguły zapory sieciowej decyduje o tym, czy reguła dotyczy ruchu przychodzącego, czy wychodzącego? 
Kierunek (Direction).

322) Polecenie do zarządzania zasobami w Compute Engine? 
"gcloud compute instances".

323) "iam.serviceAccountUser"? 
Kiedy użytkownik otrzyma uprawnienie "iam.serviceAccountUser" na poziomie projektu, ten użytkownik może zarządzać 
wszystkimi kontami serwisowymi w projekcie.

324) Wszystkie możliwe opcje przejścia z jednej klasy przechowywania do innej? 
a) Nearline do Coldline. 
b) Regional do Nearline. 
c) Multiregional do Coldline. 
Przejścia z Regional do Multiregional lub z Multiregional do Regional nie są możliwe.

325) Reguły zapory dla punktu końcowego usługi, który akceptuje dowolny ruch UDP, a każdy punkt końcowy będzie używać 
portu w zakresie 20000 - 30000?
"gcloud compute firewall-rules create fwr1 --allow=udp:20000-30000 --direction=ingress".

326) Polecenie do utworzenia kopii zapasowej Cloud SQL? 
"gcloud sql backup create".

327) Kiedy tworzysz Cloud Router, jaki unikalny identyfikator musisz przypisać do protokołu BGP? 
ASN (Autonomous, System number) , (Numer Systemu Autonomicznego) to numer używany do identyfikacji routera 
chmurowego w sieci.

328) Polecenie do tworzenia bucketów w Cloud Storage? 
"gsutil mb".

329) Do czego odnosi się termin "tożsamość" (identity)? 
Użytkownik (User).

330) Jaka rola daje użytkownikom pełną kontrolę nad instancją Compute Engine? 
Rola Compute Admin.

331) Rola Compute Security Admin? 
Daje użytkownikom uprawnienia do tworzenia, modyfikowania i usuwania certyfikatów SSL oraz reguł zapory sieciowej.

332) Który regionalny Load Balancer umożliwia Load Balancing oparty na protokole IP, adresie i porcie? 
Network TCP/UDP.

333) Środki bezpieczeństwa mające na celu zmniejszenie ryzyka spoofingu DNS i zatrucia pamięci podręcznej 
(cache poisoning)? 
Używanie DNSSEC.

334) Zakres jest identyfikowany za pomocą jakiego rodzaju identyfikatora? 
URL zaczynający się od "https://www.googleapis.com/auth".

335) Role w Cloud Query?
a) BigQuery Admin.
b) BigQuery Data Owner.
c) BigQuery Data Editor.
d) BigQuery Data Viewer.
e) BigQuery Job User.
f) BigQuery User.
Aby zobaczyć dane, potrzebujesz roli "BigQuery User" lub "BigQuery Data Viewer": nie możesz zobaczyć danych mając rolę 
"BigQuery Job User". Rola "BigQuery Data Owner" lub "BigQuery Data Viewer" nie ma dostępu do zadań (jobs).

336) Role w Cloud Storage?
a) Storage Admin.
b) Storage Object Admin.
c) Storage Object Creator.
d) Storage Object Viewer.
Kontrola dostępu do obrazów w "Container Registry" w bucketach "Cloud Storage": Dostęp do obrazów w "Container Registry" 
kontroluje się za pomocą uprawnień "Cloud Storage"!
"Storage Admin" kontra "Storage Object Admin":
"Storage Admin" może tworzyć buckety i zarządzać obiektami.
"Storage Object Admin" nie może tworzyć bucketów, ale może zarządzać obiektami w bucketach.

337) Role IAM w Google Kubernetes Engine (GKE)?
a) Kubernetes Engine Admin – Pełny dostęp do klastra i obiektów Kubernetes API.
b) Kubernetes Engine Cluster Admin – Zapewnia dostęp do zarządzania klastrami (nie może uzyskać dostępu do obiektów 
Kubernetes API, takich jak Deployments, Pods itp.).
c) Kubernetes Engine Developer – Zarządza obiektami Kubernetes API (może również odczytywać informacje o klastrze).
d) Kubernetes Engine Viewer – Może uzyskiwać i wyświetlać informacje o klastrze oraz obiektach Kubernetes API.

338) Bezpieczny sposób ustawienia wdrożenia aplikacji? Role.
"Application Deployer": Role: "App Engine Deployer" + "Service Account User".
Ograniczone do wdrażania nowych wersji i usuwania starych wersji, które nie obsługują ruchu. 
Nie będzie miało dostępu do konfiguracji ruchu.
Operations: Rola: "App Engine Service Admin".
Nie może wdrażać nowej wersji aplikacji ani zmieniać ruchu między wersjami.

339) Jaka jest różnica między "Compute Engine Admin" a "Compute Instance Admin"?
"Compute Instance Admin" może robić wszystko związanego z instancjami i dyskami, ale tylko z tymi zasobami.
"Compute Engine Admin" to administrator dla wszystkiego w "Compute Engine" — instancji, dysków, obrazów, sieci, 
zapór sieciowych itp. Ma pełny dostęp do całej infrastruktury "Compute Engine".

340) Role w App Engine?
a) "App Engine Creator" – Tworzenie aplikacji w App Engine.
b) "App Engine Admin" – Pełny dostęp do zarządzania aplikacjami, konfiguracją i usługami App Engine.
c) "App Engine Viewer" – Tylko odczyt informacji o aplikacji, bez możliwości modyfikacji.
d) "App Engine Code Viewer" – Odczyt kodu aplikacji.
e) "App Engine Deployer" – Wdrażanie nowych wersji aplikacji.
f) "App Engine Service Admin" – Zarządzanie usługami w App Engine.
Role App Engine nie pozwalają na:
a) Wyświetlanie i pobieranie logów aplikacji.
b) Wyświetlanie i monitorowanie wykresów w Cloud Console.
c) Włączanie i wyłączanie fakturowania (billing).
d) Dostęp do konfiguracji lub danych przechowywanych w innych usługach.

341) Role IAM w Compute Engine?
a) "Compute Engine Admin" – Pełna kontrola nad instancjami obliczeniowymi, obrazami, load balancerami, siecią, 
zaporami sieciowymi itp.
b) "Compute Instance Admin" – Tworzenie, modyfikowanie i usuwanie instancji maszyn wirtualnych oraz dysków.
c) "Compute Engine Network Admin" – Pełny dostęp do zasobów sieciowych (routery, sieci, sprawdzanie stanu, VPN, bramy itp.) 
oraz tylko do odczytu dostęp do reguł zapory i certyfikatów SSL.
d) "Compute Engine Security Admin" – Pełny dostęp do reguł zapory sieciowej i certyfikatów SSL.
e) "Compute Storage Admin" – Pełny dostęp do dysków, obrazów i migawek (snapshots).
f) "Compute Engine Viewer" – Tylko odczyt dostępu do wszystkiego w Compute Engine.
g) "Compute OS Admin Login" – Logowanie do instancji Compute Engine jako użytkownik administratora.
h) "Compute OS Login" – Logowanie do instancji Compute Engine jako standardowy użytkownik.

342) Jestem audytorem rozliczeń (billing)?
Rola, która jest potrzebna: "Billing Account Viewer".

343) Tworzę projekt i chcę powiązać istniejące konto rozliczeniowe z projektem?
Role potrzebne: "Project Creator" i "Billing Account User" (do powiązania projektu z kontem rozliczeniowym).

344) Role dla rozliczeń (billing roles) – Szybki przegląd?
a) "Billing Account Creator" – Uprawnienia do tworzenia nowych kont rozliczeniowych. Zespół finansowy.
b) "Billing Account Administrator" – Zarządza kontem rozliczeniowym, ale nie może go tworzyć. Zespół finansowy.
c) "Billing Account User" – Przypisuje projekty do kont rozliczeniowych. Właściciel projektu.
d) "Billing Account Viewer" – Tylko dostęp do odczytu do konta rozliczeniowego. Audytor.

345) Cloud Run - Z wiersza poleceń?
Wdrożenie nowego kontenera: "gcloud run deploy Service-Name --image=Image-Url --version --suffix v1".
Pierwsze wdrożenie tworzy konto serwisowe (service account) i pierwszą rewizję (first revision). 
Kolejne dla tej samej usługi tworzą nowe rewizje.
Wyświetlanie dostępnych rewizji: "gcloud run revisions list".
Dostosowanie przypisania ruchu: "gcloud run services update --traffic myservice --to-revisions=v2=1-,v2=90".

346) Cloud Run & Cloud Run for Anthos?
Cloud Run:
a) "Kontener do produkcji w kilka sekund".
b) Zbudowany na otwartym standardzie Knative.
c) W pełni zarządzana, serverless platforma dla aplikacji kontenerowych:
Zero zarządzania infrastrukturą.
Model płatności za użycie (za CPU, pamięć, zapytania i ruch sieciowy).
Kompleksowe, zintegrowane środowisko deweloperskie: Brak ograniczeń dotyczących języków, binariów i wdrożeń.
Łatwo przenośny dzięki kontenerom.
Integracje z "Cloud Code", "Cloud Build", "Cloud Monitoring" i "Cloud Logging".
Anthos:
a) Umożliwia uruchamianie klastrów Kubernetes wszędzie: w chmurze, w multi-chmurze i na miejscu (on-premises).
Cloud Run for Anthos:
a) Wdróż swoje obciążenia (workloads) do klastrów Anthos uruchamianych na miejscu (on-premises) lub w Google Cloud.

347) Cloud Functions - Pamiętaj!
a) Brak zarządzania serwerami: Nie musisz martwić się o skalowanie ani dostępność swojej funkcji. Cloud Functions 
automatycznie uruchamiają się i wyłączają w odpowiedzi na zdarzenia. Skaluje się poziomo!
b) Zalecane do reagowania na zdarzenia: Cloud Functions są idealne do krótkotrwałych, "event-driven" operacji.
c) Nie są idealne do długotrwałych procesów: Cloud Functions nie nadają się do procesów, 
które wymagają długiego czasu działania.

348) Cloud Functions - Koncepcje?
a) Event (Zdarzenie): Przykład: Wgranie obiektu do Cloud Storage.
b) Trigger (Wyzwalacz): Reakcja na zdarzenie przez wywołanie funkcji.
c) Trigger: Określa, która funkcja ma zostać uruchomiona, gdy zdarzenie wystąpi.
d) Functions (Funkcje): Przetwarzają dane zdarzenia i wykonują określone działania.
Zdarzenia mogą być wyzwalane z różnych źródeł:
a) Cloud Storage (np. wgranie pliku).
b) Cloud Pub/Sub (np. wiadomości).
c) HTTP (POST/GET/DELETE/PUT/OPTIONS).
d) Firebase.
e) Cloud Firestore.
f) Stackdriver Logging.

349) Cloud Functions?
Wyobraź sobie, że chcesz wykonać kod w odpowiedzi na zdarzenie, np. gdy wiadomość przychodzi do Pub/Sub itp.
Wchodzimy w Cloud Functions:
a) Uruchamiaj kod w odpowiedzi na zdarzenia.
b) Możesz pisać w Pythonie, Javie, Node.js i innych językach.
c) Nie martw się o serwery, skalowanie czy dostępność.
d) Płacisz tylko za to, czego używasz: liczba wywołań, przydzielona pamięć i CPU.
e) Limit czasowy: Domyślnie 1 minuta, maksymalnie 60 minut (3600 sekund).
f) 2 wersje produktu: 1. generacja i 2. generacja.

350) Jaka rola jest wymagana, aby wyświetlić dane w BigQuery?
"BigQuery Data Viewer".

351) Jaka rola jest wymagana do zarządzania kontami serwisowymi (Service Accounts)?
"Service Account Admin".

352) Jaka rola jest wymagana do zarządzania obiektami Kubernetes API?
"Kubernetes Engine Developer".

353) Jaka rola jest wymagana do wgrywania obiektów do Cloud Storage?
"Storage Object Creator".

354) Chcesz przyznać dostęp do zestawu zasobów swojemu zespołowi deweloperskiemu?
Utwórz grupę z członkami zespołu deweloperskiego. Przypisz odpowiednie predefiniowane role do tej grupy.

355) Scenariusz IAM. Chcesz zapewnić dostęp na ograniczony czas do konkretnego obiektu w Cloud Storage Bucket?
Utwórz podpisany URL (signed URL).

356) Scenariusz IAM. Chcesz przyznać permanentny dostęp do całego bucketu w Cloud Storage?
Użyj IAM.

357) Scenariusz IAM. Chcesz przyznać permanentny dostęp do wybranych obiektów w Cloud Storage Bucket?
Użyj "Access Control List" (A-C-L-s).

358) SSH do maszyn wirtualnych Linux - Szczegóły?
a) Konsola – Użyj przycisku SSH w interfejsie graficznym Google Cloud Console.
b) "gcloud" – Użyj polecenia: "gcloud compute ssh Instance-Name".
c) Niestandardowe klucze SSH – Możliwość użycia własnych kluczy SSH do połączenia z VM.

359) SSH do maszyn wirtualnych Linux – Opcje?
Compute Engine dla maszyn Linux używa autentykacji SSH opartej na kluczach. Istnieją dwie opcje:
a) Zarządzanie metadanymi (Metadata managed) – Ręczne tworzenie i konfigurowanie indywidualnych kluczy SSH.
b) OS Login – Zarządzanie dostępem SSH bez potrzeby zarządzania indywidualnymi kluczami SSH! Zalecane do zarządzania 
dostępem wielu użytkowników w ramach instancji lub projektu. Twoje konto użytkownika Linux jest powiązane z 
Twoją tożsamością Google.
Aby włączyć: Ustaw "enabled-oslogin" na true w metadanych.
Możliwość importowania istniejących kont użytkowników Linux z lokalnego Active Directory (AD) i LDAP.
Użytkownicy muszą mieć role: "roles/compute.oslogin" lub "roles/compute.osAdminLogin".
Dodatkowo, w przypadku systemów Windows, możliwe jest uwierzytelnianie za pomocą hasła.

360) Inne ważne role IAM?
a) "securityAdmin" – Zarządza uprawnieniami związanymi z bezpieczeństwem w projekcie lub organizacji.
b) "securityReviewer" – Przegląda ustawienia bezpieczeństwa, ale nie ma uprawnień do wprowadzania zmian.
c) "organizationRoleAdmin" – Zarządza rolami na poziomie organizacji, w tym tworzeniem i przypisywaniem ról.
d) "organizationRoleViewer" – Przegląda role na poziomie organizacji, ale nie ma uprawnień do ich modyfikacji.
e) "roleAdmin" – Zarządza rolami na poziomie projektu lub organizacji (tworzenie, modyfikowanie, usuwanie ról).
f) "roleViewer" – Przegląda istniejące role, ale nie ma uprawnień do ich modyfikacji.
g) "roles/browser" – Dostarcza minimalny dostęp do zasobów, w tym do przeglądania metadanych, 
ale bez możliwości wprowadzania zmian.

361) Logging IAM Roles and Service Account Roles?
Logging i Audit Logging: Logs Viewer, Private Log Viewer, Logging Admin.
Konta serwisowe (service accounts): "serviceAccountAdmin", "serviceAccountUser", "serviceAccountTokenCreator", 
"serviceAccountKeyAdmin".

362) Organization, Billing and Project Roles?
a) "Organization Administrator": Definiowanie hierarchii zasobów. Definiowanie polityk zarządzania dostępem. 
Zarządzanie innymi użytkownikami i rolami.
b) "BillingAccountCreator": Tworzenie kont rozliczeniowych.
c) "Billing Account Administrator": Zarządzanie kontami rozliczeniowymi (instrumenty płatnicze, eksporty rozliczeniowe, 
łączenie i rozłączanie projektów, zarządzanie rolami na koncie rozliczeniowym). Nie może tworzyć konta rozliczeniowego.
d) "Billing Account User": Powiązywanie (associate) projektów z kontami rozliczeniowymi: Zwykle używane w połączeniu z 
rolą "Project Creator". Te dwie role pozwalają użytkownikowi tworzyć nowe projekty i powiązać je z kontem rozliczeniowym.
e) "Billing Account Viewer": Przeglądanie szczegółów wszystkich kont rozliczeniowych.

363) Resource Hierarchy & IAM Policy?
Polityka IAM może być ustawiona na każdym poziomie hierarchii. Zasoby dziedziczą polityki wszystkich nadrzędnych 
elementów. Skuteczna polityka dla zasobu to suma polityki na tym zasobie i jego elementach nadrzędnych. Dziedziczenie 
polityk jest transitive (przechodnie):
Na przykład: Polityki organizacji są stosowane na poziomie zasobów. Nie możesz ograniczyć uprawnień na niższym poziomie, 
jeśli zostały one przyznane na wyższym poziomie.

364) Organization Policy Service?
Jak włączyć scentralizowane ograniczenia dla wszystkich zasobów tworzonych w organizacji? Skonfiguruj Organization Policy.
Przykład: Zablokowanie tworzenia konta serwisowego.
Przykład: Zezwolenie/Odmowa tworzenia zasobów w określonym regionie.
Wymaga roli - "Organization Policy Administrator".
IAM koncentruje się na Kim. Kto może wykonywać określone działania na zasobach?
"Organization Policy" koncentruje się na Co. Co może zostać zrobione na określonych zasobach?

365) Twoja aplikacja, która jest wdrożona na maszynie wirtualnej GCE (Projekt A), musi uzyskać dostęp do zasobnika 
Cloud Storage z innego projektu (Projekt B).
W Projekcie B przypisz odpowiednią rolę (assign the right role) do konta usługi maszyny wirtualnej GCE z Projektu A.

366) Chcesz udzielić zewnętrznemu audytorowi dostępu do przeglądania wszystkich zasobów w Twoim projekcie, ALE nie 
powinien on mieć możliwości wprowadzania żadnych zmian?
Przydziel im rolę "Viewer" (Generalnie podstawowe role nie są zalecane, ale jest to najprostszy sposób na udzielenie 
dostępu tylko do przeglądania wszystkich zasobów)!

367) "Cloud Functions" - Skalowanie i Współbieżność (Concurrency)?
Autoskalowanie – W miarę jak pojawiają się nowe wywołania, tworzone są nowe instancje funkcji. Jedna instancja funkcji 
obsługuje tylko jedno żądanie w danym czasie. Typowy problem: "Cold Start". Nowa instancja funkcji inicjowana od podstaw 
może zająć trochę czasu. Można skonfigurować minimalną liczbę instancji (co zwiększa koszty). Jedna instancja funkcji 
może obsługiwać wiele żądań jednocześnie.
Współbieżność (Concurrency): Ile wywołań jednoczesnych może obsłużyć jedna instancja funkcji? Maksymalnie 1000. 
Twój kod funkcji powinien być bezpieczny do wykonania współbieżnego.

368) "Cloud Functions" - Druga Generacja (Second Generation)?
Dłuższy czas oczekiwania na żądanie: Do 60 minut dla funkcji wyzwalanych przez HTTP.
Większe rozmiary instancji: Do 16 GB RAM z 4 v-CPU.
Współbieżność: Do 1000 jednoczesnych żądań na jedną instancję funkcji.
Obsługa wielu rewizji funkcji i podziału ruchu.
Wsparcie dla ponad 90 typów zdarzeń włączonych przez "Eventarc".

369) Wszyscy członkowie Twojego zespołu mają konta "G-Suite". Ustawiasz nowy projekt i chcesz zapewnić jednorazowy, 
szybki dostęp do projektu członkowi zespołu?
Przypisz odpowiednią rolę bezpośrednio do adresu e-mail "G-Suite" członka zespołu. Jeśli dostęp nie jest jednorazowy, 
zalecaną metodą jest utworzenie grupy.

370) Wszyscy członkowie Twojego zespołu mają konta "G-Suite". Tworzysz nowy projekt produkcyjny i chcesz zapewnić dostęp 
do projektu zespołowi operacyjnemu?
Utwórz grupę z wszystkimi członkami zespołu operacyjnego. Przydziel dostęp do projektu produkcyjnego tej grupie.

371) Członkowie IAM / Tożsamości (IAM Members / Identities)?
"Google Account" – Reprezentuje osobę (adres e-mail).
"Service Account" – Reprezentuje konto aplikacji (nie osobę).
"Google Group" – Zbiór kont Google i kont serwisowych: Posiada unikalny adres e-mail. Pomaga w stosowaniu polityk 
dostępu do grupy.
"Google Workspace Domain" – "Google Workspace" (dawniej "G-Suite") oferuje usługi współpracy dla firm: Narzędzia takie 
jak Gmail, Kalendarz, Meet, Chat, Drive, Docs itp. są wliczone. Jeśli Twoja firma korzysta z "Google Workspace", 
możesz zarządzać uprawnieniami za pomocą swojej domeny "Google Workspace".
"Cloud Identity Domain" – "Cloud Identity" to rozwiązanie typu "Identity as a Service (IDaaS)", które centralnie zarządza 
użytkownikami i grupami. Możesz używać IAM do zarządzania dostępem do zasobów dla każdego konta "Cloud Identity".

372) Federacja Katalogu Korporacyjnego (Corporate Directory Federation)?
"Federate Cloud Identity" lub "Google Workspace" z zewnętrznym dostawcą tożsamości (I d P), takim jak "Active Directory" 
lub "Azure Active Directory".
Włącz "Single Sign-O"n: Użytkownicy są przekierowywani do zewnętrznego (I d P) w celu uwierzytelnienia. Po uwierzytelnieniu 
użytkowników, asercja SAML jest wysyłana do "Google Sign-In".
Przykłady: "Federate Active Directory" z "Cloud Identity" za pomocą Google Cloud Directory Sync (GCDS) i 
Active Directory Federation Services (ADFS). Federate Azure AD z Cloud Identity.

373) Zarządzanie Tożsamościami Użytkowników w Google Cloud?
E-mail używany do tworzenia konta próbnego, "Super Admin". Dostęp do wszystkiego w Twojej organizacji GCP. Zarządzanie 
dostępem do innych użytkowników za pomocą ich kont Gmail.
Jednakże, nie jest to zalecane dla przedsiębiorstw.
Opcja 1: Twoje przedsiębiorstwo korzysta z Google Workspace.
Opcja 2: Twoje przedsiębiorstwo używa własnego dostawcy tożsamości. Federate Google Cloud z Twoim dostawcą tożsamości.

374) Zarządzanie Rozliczeniami - Budżet, Alerty i Eksporty?
Ustaw Cloud Billing Budget, aby uniknąć niespodzianek: Skonfiguruj Alerty. Dane rozliczeniowe mogą być eksportowane 
(zgodnie z harmonogramem) do BigQuery lub Cloud Storage.

375) Konta Rozliczeniowe?
Konto rozliczeniowe jest obowiązkowe do tworzenia zasobów w projekcie. Konto rozliczeniowe może być powiązane z jednym 
lub wieloma projektami. Możesz mieć wiele kont rozliczeniowych w jednej organizacji. Twórz konta rozliczeniowe 
odzwierciedlające strukturę organizacyjną.
Dwa typy:
a) Self Service: Faktura (Billed) jest bezpośrednio obciążana na kartę kredytową lub konto bankowe.
b) Invoiced: Generowanie faktur (invoices) (używane przez duże przedsiębiorstwa).

376) Hierarchia Zasobów - Rekomendacje dla Przedsiębiorstw?
Twórz oddzielne projekty dla różnych środowisk. Pełna izolacja między środowiskami testowymi a produkcyjnymi.
Twórz oddzielne foldery dla każdego działu: Izoluj aplikacje produkcyjne jednego działu od innych. Możemy stworzyć 
wspólny folder dla zasobów współdzielonych.
Jeden projekt na aplikację w każdym środowisku.

377) Hierarchia Zasobów w GCP?
Dobrze zdefiniowana hierarchia: Organizacja (Organization), Folder, Projekt (Project), Zasoby (Resources).
Zasoby są tworzone w projektach. Folder może zawierać wiele projektów. Organizacje mogą zawierać wiele folderów.

378) Polecenie do utworzenia maszyny wirtualnej z czterema CPU o nazwie web-server-1?
"gcloud compute instances create --machine-type=n1-standard-4 web-server-1".

379) Analiza danych (Data analytics)?
Zestaw usług specjalistycznych z zakresu analizy danych obejmuje produkty, które pomagają w ekstrakcji, transformacji 
i ładowaniu danych (ETL) oraz obsługują zarówno dane wsadowe, jak i strumieniowe.

380) Co może być podstawą skalowania grupy instancji?
Opóźnienia sieciowe (Network Latency), pojemność równoważenia obciążenia (Load Balancing capacity) oraz wykorzystanie CPU.

381) Role prymitywne w GCP (GCP Primitive Roles)?
Właściciel (Owner), Edytor (Editor), Podglądający (Viewer).

382) Polecenie do wyświetlania ról przypisanych do zasobu?
"gcloud iam list-grantable-roles".

383) BigQuery?
BigQuery to w pełni zarządzana usługa, zaprojektowana do analiz na skali petabajtów, która oferuje interfejs SQL.

384) Wyzwalacze (Triggers) Cloud Storage?
"google.storage.object.finalize": Występuje po przesłaniu pliku.
"google.storage.object.archive": Wyzwala się, gdy plik zostanie zarchiwizowany.
"google.storage.object.metadataUpdate": Wyzwala się, gdy zmieni się atrybut metadanych.

385) Co można określić podczas tworzenia polityki alertów?
Warunki (Conditions), dokumentację i czas życia.

386) Jak można nadpisać politykę odziedziczoną po innej jednostce w hierarchii zasobów?
Polityki odziedziczone można nadpisać, definiując politykę na poziomie folderu lub projektu.

387) Cloud BigTable
BigTable obsługuje dane półstrukturalne, do których dostęp uzyskuje się za pomocą klucza wiersza, i działa dobrze w 
przypadku zbiorów danych większych niż 1 terabajt.

388) Dataproc?
Dataproc obsługuje Apache Spark, który posiada biblioteki do uczenia maszynowego.

389) Usługa GCP do przechowywania i zarządzania kontenerami Docker to?
Container Registry.

390) Cloud Firestore?
Cloud Firestore to usługa bazy danych mobilnych, która umożliwia synchronizację danych między urządzeniami mobilnymi 
a centralnym magazynem.

391) "Open-Census"?
"Open-Census" to biblioteka do tworzenia niestandardowych metryk, które mogą być używane z logowaniem Stackdriver.

392) Struktura polecenia wiersza poleceń do eksportowania danych z Datastore?
"gcloud datastore export --namespace=Namespace gs://Nazwa-Bucketu".

393) Dane "I o T" (Internet of Things) z czujników, dane z kilku fabryk. Każdy czujnik zbiera i wysyła dane co 5 sekund. 
Raporty dzienne. Brak potrzeby obsługi transakcji. Który produkt do przechowywania danych byś polecił?
"Cloud BigTable".

394) Na jakiej technologii systemu plików oparty jest Cloud Filestore?
Network File System (NFS).

395) Uruchamianie klastrów BigTable w różnych regionach
Zapewni to wyższą dostępność, ponieważ będziesz w stanie obsłużyć awarie na poziomie regionu. Jednak zwiększy to również 
opóźnienie replikacji, ponieważ dane muszą być replikowane między klastrami w różnych regionach.

396) Dane dostępne w pamięci w celu skrócenia czasu odpowiedzi i zmniejszenia obciążenia serwera bazy danych. 
Jakiej usługi GCP użyłbyś do przechowywania danych w pamięci?
Cloud Memorystore.

397) Magazyn danych dla firmy. Musi obsługiwać dziesiątki petabajtów danych. SQL jako język zapytań. Jaką usługę byś wybrał?
BigQuery.

398) App Engine - Routing żądań (Request Routing)?
Możesz użyć kombinacji trzech podejść:
a) Routing za pomocą URL.
b) Routing za pomocą pliku dispatch: Skonfiguruj "dispatch.yaml" z trasami "gcloud app deploy dispatch.yaml".
c) Routing za pomocą Cloud Load Balancing.

399) App Engine - Wdrażanie nowych wersji bez przestojów (downtime)?
a) Jestem bardzo pewny - Wdróż i przekieruj cały ruch jednocześnie: "gcloud app deploy".
b) Zarządzaj migracją z wersji v1 do v2. Wdróż v2 bez przekierowywania ruchu (--no-promote). Przekieruj ruch do v2. 
Migracja stopniowa (--migrate). Podział (Splitting).

400) Jak podzielić ruch między wieloma wersjami?
Podział na podstawie IP - na podstawie adresu IP żądania.
Podział na podstawie pliku cookie - na podstawie pliku cookie (goog-app-uid).
Losowy. Opcja '--split-by' w "gcloud app services set-traffic". Opcje: cookie, ip, random.

401) Funkcje Kubernetes, które zmniejszają obciążenie inżynierów DevOps?
a) Równoważenie obciążenia pomiędzy maszynami wirtualnymi Compute Engine wdrożonymi w klastrze Kubernetes.
b) Automatyczne skalowanie węzłów w klastrze.
c) Automatyczna aktualizacja oprogramowania klastra w razie potrzeby.

402) Aplikacja działa w klastrze Kubernetes. Skonfigurowałeś "auto-skalowanie", gdy wykorzystanie CPU przekroczy 75 procent. 
Jak obliczane jest wykorzystanie CPU?
Na podstawie wszystkich CPU używanych przez wdrożenie (deployment). Repliki są dodawane do wdrożenia, dlatego wykorzystanie 
CPU należy sprawdzać na poziomie wdrożenia.

403) Co może być podstawą skalowania grupy instancji?
Opóźnienia sieciowe (Network Latency), pojemność równoważenia obciążenia (load balancing capacity), wykorzystanie CPU, 
metryki monitorowania (monitoring metrics) lub obciążenie oparte na kolejce (queue-based workload).

404) API Kubernetes?
Wszystkie interakcje z klastrem są realizowane przez mastera za pomocą API Kubernetes.

405) Zalety Dockera?
Kontenery Docker są lekkie. Docker zapewnia izolację dla kontenerów. Docker jest neutralny wobec chmury.

406) App Engine?
Najprostszy sposób na wdrożenie i skalowanie aplikacji w GCP. Zapewnia kompleksowe zarządzanie aplikacjami.
Obsługuje: Go, Java, Node.js, PHP, Python, Ruby przy użyciu wstępnie skonfigurowanych środowisk uruchomieniowych. 
Możesz używać środowiska uruchomieniowego i pisać kod w dowolnym języku. Łączy się z różnymi produktami 
Google Cloud Storage (Cloud SQL itp.). Brak opłat za użytkowanie — płacisz za zasoby, które zostały przydzielone.
Funkcje: Automatyczne równoważenie obciążenia (load balancing) i "auto-skalowanie". Zarządzane aktualizacje platformy 
i monitorowanie zdrowia aplikacji. Wersjonowanie aplikacji. Podział ruchu.

407) Compute Engine kontra App Engine?
a) Compute Engine: IaaS. Większa elastyczność. Większa odpowiedzialność: wybór obrazu, instalowanie oprogramowania, 
wybór sprzętu, szczegółowy dostęp, uprawnienia (certyfikaty/firewalle).
b) App Engine: PaaS. Bezserwerowe. Mniejsza odpowiedzialność. Mniejsza elastyczność.

408) Środowiska App Engine - Standard?
Odpowiedź: Aplikacje działają w specyficznych dla języka piaskownicach (sandboxes). Pełna izolacja od systemu 
operacyjnego, dysku i innych aplikacji (V1/V2).
V1: Java, Python, PHP, Go. Tylko dla środowisk uruchomieniowych Python i PHP: ograniczony dostęp do sieci, 
tylko do białych list (white list) rozszerzeń i bibliotek. Brak ograniczeń dla Java i Go.
V2: Java, Python, PHP, Node.js, Ruby, Go. Pełny dostęp do sieci i brak ograniczeń dotyczących rozszerzeń językowych.

409) Środowisko elastyczne App Engine?
Instancje aplikacji działają w kontenerach Docker. Wykorzystują maszyny wirtualne Compute Engine. Obsługują dowolne 
środowisko uruchomieniowe. Zapewniają dostęp do procesów w tle i lokalnych dysków.

410) Jakie są kategorie rozwiązań w Cloud Launcher?
Zbiory danych (Data Sets), systemy operacyjne i narzędzia deweloperskie (developer tools).

411) Kubernetes "Replica-set"?
"Replica-set" to kontroler, który zarządza liczbą podów uruchomionych dla wdrożenia. Wdrożenie (Deployment) to wyższy 
poziom pojęcia (higher-level concept), które zarządza Replicasetem i zapewnia deklaratywne aktualizacje.

412) Usługa GCP do tworzenia obrazów Docker to?
Cloud Build.

413) Jakie zasoby można skonfigurować podczas uruchamiania strony WordPress za pomocą Cloud Launcher?
Typ dysku. Rozmiar dysku. Typ maszyny. Reguły zapory (Firewall rules) umożliwiające HTTP i HTTPS. Zmiana strefy.

414) App Engine - Skalowanie instancji?
a) Automatyczne (Automatic): Instancje skalują się automatycznie na podstawie obciążenia. 
Zalecane dla ciągłych obciążeń roboczych.
b) Podstawowe (Basic): Instancje są tworzone w miarę potrzeby, w zależności od żądań. 
Zalecane dla obciążeń roboczych ad hoc.
c) Ręczne (Manual): Skonfiguruj określoną liczbę instancji do uruchomienia. Ręcznie dostosowuj liczbę instancji w czasie.

415) Hierarchia komponentów aplikacji App Engine?
a) Aplikacja (Application): Jedna aplikacja na projekt.
b) Usługi (Services): Wiele mikroserwisów lub komponentów aplikacji: Możesz mieć wiele usług w jednej aplikacji. 
Każda usługa może mieć różne ustawienia. Wcześniej nazywane modułami.
c) Wersja(e) (Version(s)): Każda wersja związana z kodem i konfiguracją: Każda wersja może działać na jednej lub więcej 
instancjach. Wiele wersji może współistnieć. Opcje przywracania poprzednich wersji i dzielenia ruchu.

416) Wykorzystanie CPU przekraczające 70 procent przez trzy minuty jest przykładem czego?
Polityki alertów (Alerting Policy). W Stackdriver Monitoring alerty są tworzone za pomocą polityki alertów. 
Wykorzystanie CPU to jeden z takich przykładów.

417) Kiedy przenosimy usługi z "n1-standard-1" na "n1-standard-4", nazywa się to skalowaniem pionowym?
Tak. Skalowanie pionowe polega na przenoszeniu usług z jednej maszyny wirtualnej na inną, która ma więcej lub mniej zasobów.

418) Pytanie: Jakie polecenie jest używane do uruchomienia obrazu Docker na klastrze?
"kubectl run". Polecenie kubectl jest używane do zarządzania obciążeniami na klastrze Kubernetes po jego utworzeniu.

419) Cloud Functions?
Cloud Functions najlepiej nadaje się do przetwarzania opartego na zdarzeniach, takich jak przesyłanie plików do 
Cloud Storage lub zapis zdarzenia do kolejki Pub/Sub.

420) Jaką rolę należy przypisać, aby tworzyć migawki (snapshots)?
"Compute Storage Admin".

421) Pytanie: Jakie polecenie wyświetla szczegóły wdrożenia?
"gcloud deployment-manager deployments describe".

422) Które usługi baz danych nie wymagają podania informacji konfiguracyjnych dla maszyn wirtualnych (VM)?
BigQuery, Datastore i Firebase.

423) Serwer o wysokim poziomie bezpieczeństwa. Przygotuj się na atak typu rootkit (rodzaj złośliwego oprogramowania,
które może zmieniać system operacyjny). Jaką opcję należy wybrać podczas tworzenia maszyny wirtualnej?
Shield VM. Shield VM to zaawansowany zestaw kontroli bezpieczeństwa, który obejmuje monitorowanie integralności, 
czyli sprawdzenie, czy obrazy rozruchowe nie zostały zmienione.

424) Stackdriver Trace?
Stackdriver Trace jest używany do zbierania informacji o czasie wymaganym do wykonania funkcji w stosie wywołań systemowych.

425) Dlaczego w Kubernetes nie ma polecenia deploy container?
Kubernetes używa Podów jako najmniejszej jednostki, którą można wdrożyć, a Pod zwykle zawiera jeden, ale może zawierać 
również kilka kontenerów, które są wdrażane jako całość.

426) Polecenie do utworzenia subskrypcji na temacie ace-exam-topic1?
"gcloud pubsub subscriptions create --topic=ace-exam-topic1 ace-exam-sub1".

427) Klasa przechowywania w zasobniku?
Gdy zasobnik jest ustawiony na klasę Coldline, nie można jej zmienić na inną klasę przechowywania. 
Zasobniki (buckets) regionalne (Regional) i multiregionalne (Multi-regional) mogą zostać zmienione na Nearline i Coldline. 
Zasobnik Nearline może zostać zmieniony na Coldline.

428) Co musisz utworzyć, zanim zaczniesz przechowywać dane w BigQuery?
Zbiór danych (dataset).

429) Jakiej metryki użyłbyś podczas negocjacji z dostawcą SaaS, aby zapewnić, że wymagania dotyczące niezawodności 
klienta zostaną spełnione?
Średni czas między awariami (MTBF).

430) Przechowywanie dużych wolumenów danych w Bigtable. Chcesz eksplorować środowisko Bigtable z wiersza poleceń. 
Jakie polecenie upewni cię, że narzędzie wiersza poleceń jest zainstalowane?
"gcloud components install cbt".

431) Opcje formatu pliku przy eksporcie z Cloud SQL?
CSV i SQL.

432) Jeśli chcesz przechować 50 terabajtów danych półstrukturalnych, które będą dostępne za pomocą pojedynczego klucza, 
jaki wybierzesz?
Cloud Bigtable.

433) Proces eksportu danych z Cloud Spanner do Cloud Storage. Jakie usługi GCP mogą wiązać się z opłatami podczas 
eksportu z Cloud Spanner?
Dataflow. Dataflow to usługa przetwarzania danych w czasie rzeczywistym i wsadowych, która implementuje przepływy pracy 
używane przez Cloud Spanner.

434) Bezpośrednie korzyści z używania kolejki wiadomości w systemach rozproszonych?
Oddziela usługi, dzięki czemu jeśli jedna z nich opóźnia się, nie powoduje to opóźnienia innych usług.

435) Analiza dużej partii danych każdej nocy. Zadanie będzie trwało 3,5 godziny. Decydujesz się napisać skrypt, 
który utworzy klaster Dataproc każdej nocy o północy. Przykład polecenia do utworzenia klastra o nazwie 
spark-nightly-analysis w strefie "us-west2-a"?
"gcloud dataproc cluster create spark-nightly-analysis --zone us-west2-a".

436) Jaką rolę pełnią grupy instancji (instance group) w klastrze Kubernetes?
Tworzą zestawy maszyn wirtualnych, które mogą być zarządzane jako jednostka.

437) Rozliczenia i koszty?
Maszyny wirtualne są rozliczane w przyrostach co 1 sekundę. Koszt zależy od liczby rdzeni CPU i ilości pamięci w 
typie maszyny. Można tworzyć niestandardowe typy maszyn. Maszyny wirtualne "Preemptible" kosztują nawet do 80% mniej niż 
standardowe maszyny wirtualne. Google oferuje rabaty za długotrwałe korzystanie.

438) Jakie polecenie użyjesz, aby mieć 10 replik wdrożenia o nazwie 'ch07-app-deploy'?
"kubectl scale deployment ch07-app-deploy --replicas=10".

439) Stackdriver Monitoring zbiera metryki takie jak liczba zapytań, czasy wykonania, zeskanowane bajty i liczba tabel. 
Dla których usług zarządzanych w GCP są zbierane te metryki?
Cloud BigQuery. Stackdriver Monitoring zbiera metryki dotyczące wydajności zasobów infrastrukturalnych i aplikacji. 
Wymienione metryki są zbierane dla BigQuery.

440) App Engine?
App Engine to platforma PaaS, która pozwala deweloperom na wdrażanie pełnych aplikacji bez potrzeby zarządzania serwerami 
lub klastrami.

441) Ile czasu GCP będzie czekać przed zbieraniem statystyk wydajności z instancji w przypadku autoskalowania?
Okres ochłodzenia (Cool down period).

442) Zasobnik zawierający stare dane. Nie chcesz ich usuwać, ale chcesz zminimalizować koszt przechowywania. 
Jakie polecenie zmieni klasę przechowywania na coldline?
"gsutil rewrite -s STORAGE-CLASS gs://PATH-TO-OBJECT".

443) Jak cache wpłynie na pobieranie danych?
Użycie cache zmniejszy opóźnienia, ponieważ pobieranie z cache jest szybsze niż pobieranie z SSD lub HDD.

444) Chcesz oszacować koszt uruchomienia zapytania w BigQuery. Jakie dwie usługi w Google Cloud Platform będziesz 
musiał użyć?
BigQuery i Kalkulator cen (Pricing Calculator). BigQuery zapewnia szacunkową ilość zeskanowanych danych, a Kalkulator cen 
daje szacunkowy koszt skanowania tej ilości danych. 

445) Magazyn danych lokalny działający na MySQL jest migrowany do GCP. Zespół chce użyć zarządzanej usługi, która może 
skalować się do petabajtów. Jaką usługę wybrać?
Cloud BigQuery.

446) Polecenie do eksportu bazy danych MySQL o nazwie "gcp-exam-mysql1" do pliku o nazwie "gcp-exam-mysql-export.sql" 
w zasobniku o nazwie "gcp-exam-bucket1"?
"gcloud sql export sql gcp-exam-mysql1 gs://gcp-exam-bucket1/gcp-exam-mysql-export.sql --database=mysql".

447) Jaki model danych jest używany przez Datastore?
Dokumentowy (Document).

448) Poprawne polecenie do utworzenia tematu (topic) Pub/Sub?
"gcloud pubsub topics create".

449) Polecenie do usunięcia instancji o nazwie "ch06-instance-3"?
"gcloud compute instance delete ch06-instance-3".

450) Musisz utworzyć wiele maszyn wirtualnych o różnych konfiguracjach w ramach grupy instancji. Jak możemy to osiągnąć?
Utwórz grupę instancji unmanaged instance group (niezarządzaną). Grupy instancji zarządzanych (MIG) składają się z 
identycznie skonfigurowanych maszyn wirtualnych, podczas gdy grupy instancji nie-managed pozwalają na maszynach wirtualnych 
o różnych konfiguracjach.

451) Usługi Kubernetes (Kubernetes Services)?
Usługi zapewniają poziom pośrednictwa w dostępie do Podów. Pody są efemeryczne. Klienci łączą się z usługami, 
które mogą odkrywać Pody.

452) Uruchamianie pojedynczej maszyny wirtualnej. Jaki minimalny zestaw informacji będziesz potrzebować?
Nazwa maszyny wirtualnej, typ maszyny (machine-type), region i strefa (zone).

453) Czym jest dynamiczne przydzielanie zasobów (on-demand resource provisioning)?
Przydzielanie (wynajem) zasobów, kiedy ich potrzebujesz, i ich zwracanie do chmury, gdy ich nie potrzebujesz.

454) Zalety chmury?
Zamiana wydatków kapitałowych na wydatki zmienne. Korzyści z ogromnych oszczędności skali. 
Koniec z szacowaniem pojemności (capacity).
     
455) Jak zapewnić wysoką dostępność dla globalnych aplikacji?
Rozmieszczaj serwery wirtualne w wielu regionach i wielu strefach.

456) Google Compute Engine (GCE) - Funkcje?
Google Compute Engine (GCE) - Przydzielanie i zarządzanie maszynami wirtualnymi. Tworzenie i zarządzanie cyklem życia 
instancji maszyn wirtualnych (VM). Load balancing i autoskalowanie dla wielu instancji VM. Dołączanie pamięci masowej 
(i pamięci masowej w sieci) do instancji VM. Zarządzanie łącznością sieciową i konfiguracją dla instancji VM.

457) Rodzina maszyn Compute Engine?
Na jakim typie sprzętu chcesz uruchamiać swoje obciążenia? Różne rodziny maszyn dla różnych obciążeń: 
a) Ogólnego przeznaczenia (E2, N2, N2D, N1): Najlepszy stosunek ceny do wydajności. Serwery internetowe i aplikacyjne, 
małe i średnie bazy danych, środowiska deweloperskie. 
b) Optymalizowane pod względem pamięci (M2, M1): Obciążenia wymagające ultra wysokiej pamięci. Duże bazy danych w pamięci 
RAM i analityka w pamięci RAM. 
c) Optymalizowane pod względem mocy obliczeniowej (C2): Obciążenia wymagające dużej mocy obliczeniowej. Aplikacje do gier.

458) Obraz?
Jakie oprogramowanie i system operacyjny chcesz mieć na instancji? Zmniejsz czas rozruchu i popraw bezpieczeństwo, 
tworząc niestandardowe, zabezpieczone obrazy. Możesz udostępnić obraz innym projektom. 
Typy obrazów:
a) Obrazy publiczne (Public Images): Dostarczane i utrzymywane przez Google, społeczności open source lub dostawców 
zewnętrznych.
b) Obrazy niestandardowe (Custom Images): Tworzone przez Ciebie dla Twoich projektów.

459) Strona, na której użytkownicy mogą przesyłać filmy, i musisz zapewnić redundancję dla tych plików, dlatego utworzono 
dwa zasobniki w Cloud Storage. Jakie polecenie należy użyć, aby zsynchronizować zawartość tych dwóch zasobników (buckets)?
"gsutil rsync".

460) Szacowanie objętości (volume) danych skanowanych przez BigQuery z wiersza poleceń. Przykład polecenia?
"bq --location=LOCATION query --use_legacy_sql=false --dry_run SQL-QUERY".

461) Firma zajmująca się przestrzenią "I o T" (Internet of Things. Firma przesyła duże wolumeny danych do GCP. 
Dane muszą być filtrowane, przekształcane i analizowane przed zapisaniem w GCP Datastore. Dobry wybór dla komponentu 
przetwarzania strumieniowego?
Cloud Dataflow. Umożliwia przetwarzanie strumieniowe i wsadowe danych i jest dobrze dopasowane do tego rodzaju pracy ETL.

462) Cloud Storage zapewnia dostępność na poziomie jedenastu 9 w skali roku?
Nie. Cloud Storage zapewnia (jedenaście 9) rocznej trwałości (durability). Dostępność oznacza możliwość uzyskania 
dostępu do obiektu, gdy jest to potrzebne.

463) Tworzenie oprogramowania analitycznego dla "I o T" (Internet of Things). Przesyłanie dużych wolumenów danych w 
sposób ciągły i ich przechowywanie przez kilka miesięcy. Oczekuje się, że wolumeny wzrosną do petabajtów. 
Jaką bazę danych wybrać?
BigTable. BigTable to baza danych o szerokich kolumnach, która może wchłaniać duże wolumeny danych w sposób ciągły. 
Obsługuje również opóźnienia na poziomie milisekund.

464) Kiedy wprowadzasz zapytanie w formularzu zapytań BigQuery, BigQuery analizuje zapytanie i wyświetla szacunkową 
wartość jakiego metryki?
Ilość zeskanowanych danych.

465) Typ maszyny e2-standard-2?
e2: Rodzina typów maszyn. standard: Typ obciążenia. 2: Liczba rdzeni CPU.

466) Adres IP wewnętrzny (internal) i zewnętrzny (external)?
Adresy IP zewnętrzne (publiczne) są adresowalne w Internecie.
Adresy IP wewnętrzne (prywatne) są wewnętrzne dla sieci korporacyjnej.
Nie można mieć dwóch zasobów z tym samym publicznym (zewnętrznym) adresem IP. Jednak dwa różne sieci korporacyjne mogą 
mieć zasoby z tym samym wewnętrznym (prywatnym) adresem IP. Wszystkie maszyny wirtualne mają przypisany co najmniej 
jeden wewnętrzny adres IP. Tworzenie zewnętrznego adresu IP może być włączone dla instancji VM: pamiętaj, że gdy 
zatrzymasz instancję VM, zewnętrzny adres IP zostaje utracony.

467) Adres IP statyczny (Static IP Address)?
Przypisz statyczny adres IP do maszyny wirtualnej (VM)!
Load balancery. Statyczny adres IP można przenieść na inną instancję VM w tym samym projekcie.
Statyczny adres IP pozostaje przypisany, nawet jeśli zatrzymasz instancję. Musisz ręcznie go odłączyć.
Będziesz obciążany za statyczny adres IP, nawet gdy go nie używasz!

468) Skracanie czasu uruchamiania za pomocą niestandardowego obrazu?
Instalowanie poprawek systemu operacyjnego i oprogramowania podczas uruchamiania instancji VM zwiększa czas rozruchu. 
Co powiesz na utworzenie niestandardowego obrazu z już zainstalowanymi poprawkami systemu operacyjnego i oprogramowaniem?
Można go utworzyć z instancji, dysku trwałego, migawki, innego obrazu lub pliku w Cloud Storage. 
Może być udostępniany między projektami.
Przestarzałe obrazy można usunąć (i określić obraz zastępczy).
Hardening obrazu - Dostosuj obrazy do standardów bezpieczeństwa Twojej firmy. 
Zdecydowanie lepiej jest używać niestandardowego obrazu niż skryptu startowego.

469) Szablony instancji (Instance Template)?
Dlaczego musisz określać wszystkie szczegóły instancji VM (obraz, typ instancji) za każdym razem, gdy uruchamiasz instancję?
Co powiesz na utworzenie szablonu instancji? Określ typ maszyny, obraz, etykiety, skrypty startowe i inne właściwości. 
Używany do tworzenia instancji VM i zarządzanych grup instancji.
Zapewnia wygodny sposób tworzenia podobnych instancji. Nie można go zaktualizować. Aby wprowadzić zmiany, skopiuj 
istniejący szablon i zmodyfikuj go. Opcjonalnie można określić rodzinę obrazów (na przykład debian-9).

470) Tworzenie instancji VM z serwerem HTTP, sposoby?
Skrypt startowy, Szablon instancji, Niestandardowy obraz. Bootstrapping: Instalowanie poprawek systemu operacyjnego lub 
oprogramowania, gdy instancja VM jest uruchamiana. W VM można skonfigurować skrypt startowy do przeprowadzenia bootstrappingu.

471) Która rodzina typów maszyn jest najlepiej dopasowana do uruchamiania obciążeń wymagających dużej mocy obliczeniowej?
Optymalizowana pod względem mocy obliczeniowej (Compute Optimized).

472) Jak uniknąć określania wszystkich szczegółów instancji VM (obraz, typ maszyny, ustawienia zapory ogniowej itd.) 
za każdym razem, gdy tworzysz instancję VM?
Utwórz szablon instancji.

473) Jaka jest preferowana opcja, aby skrócić czas uruchamiania instancji VM?
Użyj niestandardowego, zabezpieczonego obrazu (Custom Hardened Image).

474) Zniżki za długotrwałe użytkowanie (Sustained Use Discounts)?
Automatyczne zniżki za uruchamianie instancji VM przez znaczną część miesiąca rozliczeniowego. 
Zniżka rośnie wraz z czasem użytkowania. Dotyczy instancji utworzonych przez Google Kubernetes Engine i Compute Engine.
Ograniczenia: Nie dotyczy niektórych typów maszyn (np. E2 i A2). Nie dotyczy instancji VM utworzonych przez App Engine 
(elastyczny) i Dataflow.

475) Zniżki za zobowiązanie do użytkowania (Committed Use Discounts)?
Dla obciążeń o przewidywalnych potrzebach dotyczących zasobów. Zobowiąż się na 1 rok lub 3 lata. Zniżka do 70% w 
zależności od typu maszyny i liczby rdzeni CPU.

476) reemptible VM (Instancje VM na żądanie)?
Tymczasowe, tańsze (nawet o 80%) instancje obliczeniowe. Mogą zostać zatrzymane przez GCP w dowolnym momencie 
(preempted) w ciągu 24 godzin.
Użyj Preemptible VM, jeśli:
a) Twoje aplikacje są odporne na awarie (fault-tolerant).
b) Jesteś bardzo wrażliwy na koszty (cost sensitive).
c) Twoje obciążenie nie jest pilne (immediate).
Przykład: Niepilne zadania przetwarzania wsadowego (No- immediate batch processing jobs).
Ograniczenia:
a) Nie zawsze dostępne.
b) Brak SLA i nie można ich migrować do standardowych instancji VM.
c) Brak automatycznych ponownych uruchomień.

477) Spot VMs?
Spot VMs: najnowsza wersja preemptible VMs. Kluczowe różnice: nie mają maksymalnego czasu działania, w przeciwieństwie 
do tradycyjnych preemptible VMs, które mają maksymalny czas działania wynoszący 24 godziny. Inne cechy są podobne do 
tradycyjnych preemptible VMs. Mogą zostać odebrane w dowolnym momencie z 30-sekundowym wyprzedzeniem. 
Nie zawsze są dostępne. Dynamic Pricing: zniżka od 60% do 91% w porównaniu do on-demand VMs. 
Kredyty w ramach Free Tier nie mają zastosowania.

478) Google Compute Engine - Billing?
Opłaty są naliczane na podstawie sekundy (po minimum 1 minucie). Nie ponosisz opłat za moc obliczeniową, gdy instancja 
obliczeniowa jest zatrzymana. Jednak będziesz obciążony opłatami za wszelkie przechowywanie danych do niej podłączone! 
Zawsze twórz alerty budżetowe i korzystaj z eksportów budżetów, aby na bieżąco kontrolować opłaty! Jakie są sposoby, 
by zaoszczędzić? Wybierz odpowiedni typ maszyny i obraz dla swojego obciążenia. Bądź świadomy dostępnych zniżek.

479) Live Migration & Availability Policy?
Jak utrzymać działanie instancji VM, gdy system hosta wymaga aktualizacji (aktualizacja oprogramowania lub sprzętu)? 
Live Migration: Twoja działająca instancja jest migrowana na inny host w tej samej strefie. Nie zmienia to żadnych 
atrybutów ani właściwości VM. Obsługiwane dla instancji z lokalnymi SSD. Nieobsługiwane dla GPU i instancji preemptible.
Ważna konfiguracja - Availability Policy: Co powinno się stać podczas okresowej konserwacji infrastruktury?
a) Migrate (domyślnie): Migruj instancje VM na inne sprzęt.
b) Terminate: Zatrzymaj instancję VM.
c) Automatic restart: Ponownie uruchom instancje VM, jeśli zostały zakończone z powodów niezwiązanych z użytkownikiem 
(zdarzenie konserwacyjne, awaria sprzętu itp.).

480) Custom Machine Types?
Co zrobić, gdy zdefiniowane opcje VM nie są odpowiednie dla Twojego obciążenia? Stwórz typ maszyny dostosowany do 
Twoich potrzeb (Custom Machine Type).
Custom Machine Type: Dostosuj vCPU, pamięć i GPU. Wybierz między typami maszyn E2, N2 lub N1. Obsługiwane są różne 
systemy operacyjne: CentOS, CoreOS, Debian, Red Hat, Ubuntu, Windows itp. 
Opłaty są naliczane za vCPU i pamięć przypisaną do każdej instancji.

481) GPU?
Jak przyspieszyć obciążenia intensywnie wykorzystujące obliczenia matematyczne i grafikę, takie jak AI/ML itd.?
Dodaj GPU do swojej maszyny wirtualnej: Wysoka wydajność dla obciążeń intensywnie wykorzystujących obliczenia 
matematyczne i grafikę. Wyższy koszt.
Pamiętaj, aby używać obrazów z zainstalowanymi bibliotekami GPU (np. do uczenia głębokiego).
Ograniczenia CPU: Nieobsługiwane we wszystkich typach maszyn.
Podczas konserwacji hosta jedyną dostępną opcją jest "Terminate VM instance" (zatrzymanie instancji VM).
Zalecana polityka dostępności dla GPU: Automatic restart-on (automatyczny restart włączony).

482) Virtual Machine Remember?
Związane z projektem. Dostępność typów maszyn może różnić się w zależności od regionu. Można zmienić typ maszyny 
(dostosować liczbę vCPU i pamięć) tylko w przypadku zatrzymanej instancji. Instancje VM można filtrować według różnych 
właściwości: Nazwa, Strefa, Typ maszyny, IP wewnętrzne / zewnętrzne, Sieć, etykiety (labels) itp. 
Instancje są strefowe (zonal) (działają w określonej strefie (w określonym regionie)). 
Obrazy są globalne (global) (można udostępniać dostęp innym projektom, jeśli to konieczne). 
Szablony instancji (instance templates) są globalne (chyba że używasz zasobów strefowych w swoich szablonach). 
Automatic Basic monitoring jest włączony. Domyślne metryki: Wykorzystanie CPU, Liczba bajtów w sieci (wejście/wyjście), 
Przepustowość dysku / IOPs. W przypadku wykorzystania pamięci i przestrzeni dyskowej wymagane są agenty Cloud Monitoring.

483) Virtual Machines - Best Practices?
Wybierz strefę i region na podstawie: kosztów, przepisów, potrzeb dostępności, opóźnień i specyficznych wymagań sprzętowych.
Rozmieść instancje w wielu strefach i regionach, aby zapewnić wysoką dostępność. Wybierz odpowiedni typ maszyny dla 
swoich potrzeb. Eksperymentuj z różnymi typami maszyn, aby znaleźć ten najlepszy.
Używaj GPU do aplikacji wymagających intensywnych obliczeń matematycznych i graficznych. 
Rezerwuj dla "committed use discounts" przy stałych obciążeniach.
Używaj instancji preemptible do zapewnienia odporności na awarie i do obciążeń, które nie są krytyczne czasowo.
Korzystaj z etykiet.

484) Jakie są wymagania wstępne, aby móc utworzyć instancję VM?
Projekt. Konto rozliczeniowe. API Compute Engine powinny być włączone.

485) Chcesz uzyskać zniżkowy sprzęt dla swoich potrzeb związanych z zgodnością (compliance), licencjonowaniem i zarządzaniem?
Sole-tenant nodes.

486) Mam tysiące instancji VM i chcę zautomatyzować zarządzanie łatkami systemu operacyjnego, zarządzanie inwentaryzacją 
systemu operacyjnego oraz zarządzanie konfiguracją systemu operacyjnego (zarządzanie zainstalowanym oprogramowaniem).
Użyj VM Manager.

487) Chcesz zalogować się do swojej instancji VM, aby zainstalować oprogramowanie?
Możesz połączyć się z nią za pomocą SSH.

488) Nie chcesz wystawiać instancji VM na Internet?
Nie przypisuj zewnętrznego adresu IP.

489) Chcesz zezwolić na ruch HTTP do swojej instancji VM?
Skonfiguruj zasady zapory (Firewall Rules).

490) Typy maszyn?
Optymalna kombinacja mocy obliczeniowej (CPU, GPU), pamięci, dysku (przechowywanie danych) i sieci dla określonego obciążenia.
Możesz stworzyć własne Custom Machine Types, gdy istniejące typy maszyn nie spełniają Twoich potrzeb.

491) Statyczny adres IP. Szablon instancji. Preemptible VM?
Statyczny adres IP: Uzyskaj stały adres IP dla instancji VM.
Szablon instancji: Wstępnie skonfigurowane szablony, które upraszczają tworzenie instancji VM.
Preemptible VM: Krótkoterminowe, tańsze (do 80%) instancje obliczeniowe dla obciążeń odporne na awarie, 
które nie są krytyczne czasowo.

492) Jakie metryki są dostępne domyślnie (bez instalowania agenta Cloud Monitoring) dla maszyny wirtualnej Compute Engine?
Wykorzystanie CPU.

493) Wykonaj kopię zapasową danych z bazy danych Datastore do systemu przechowywania obiektów. 
Twoje dane są przechowywane w domyślnej przestrzeni nazw. Jakie polecenie należy wykonać, aby wyeksportować dane z 
Datastore do koszyka ace-exam-bucket1?
"gcloud datastore export --namespace="default" gs://ace-exam-bucket1".

494) Gcloud?
Interfejs wiersza poleceń do interakcji z zasobami Google Cloud. Większość usług GCP może być zarządzana z poziomu CLI 
za pomocą "gcloud":
Compute Engine Virtual Machines.
Managed Instance Groups.
Bazy danych.
Możesz tworzyć, usuwać, aktualizować, odczytywać istniejące zasoby oraz wykonywać operacje platformy, 
takie jak wdrożenia (deployments)!
Niektóre usługi GCP mają specyficzne narzędzia CLI:
Cloud Storage: "gsutil".
Cloud BigQuery: "bq".
Cloud BigTable: "cbt".
Kubernetes: "kubectl".

495) Polecenia Gcloud?
a) "gcloud init": Inicjalizuje lub ponownie inicjalizuje gcloud. Autoryzuje gcloud do używania poświadczeń konta użytkownika. 
Ustawia konfigurację: obejmuje bieżący projekt, domyślną strefę.
b) "gcloud config list": Wyświetla wszystkie właściwości aktywnej konfiguracji.

496) Gcloud config set?
Ustawia określoną właściwość w aktywnej konfiguracji:
a) "gcloud config set core/project value".
b) "gcloud config set compute/region value".
c) "gcloud config set compute/zone value".
d) "gcloud config set core/verbosity value".
Składnia: "gcloud config set section/property value".
Core i compute: sekcje. Project, region, zone: właściwości.
Określenie core jest opcjonalne, ponieważ jest to sekcja domyślna.
Przeciwieństwo: "gcloud config unset".

497) Gcloud - Monitorowanie wielu konfiguracji?
"gcloud config configurations create/list/delete".
Utwórz nową konfigurację: "gcloud config configurations create name/dev".
Aktywuj określoną konfigurację: "gcloud config configurations activate name/dev".

498) Struktura poleceń Gcloud - Praca z usługami?
"gcloud Group Subgroup Action...".
Group: config, compute, container, dataflow, functions, iam.
Subgroup: instances, images, instance-template, machine-types, regions, zones.
Action: create, list, stop, describe.
a) "gcloud compute instances list".
b) "gcloud compute instances create my-first-instance".
c) "gcloud compute instances describe".
d) "gcloud compute instances delete my-first-instance".

499) Gcloud compute instances create?
Tworzenie instancji Compute: "gcloud compute instances create Name".

500) Który z tych elementów ma najwyższy priorytet (do określenia strefy) podczas wykonywania polecenia gcloud?
Opcja "--zone" określona razem z poleceniem.

501) Grupy instancji?
Jak utworzyć grupę instancji VM?
Grupa instancji: Grupa instancji VM zarządzana jako jedna jednostka.
Zarządzaj grupą podobnych maszyn wirtualnych o podobnym cyklu życia jako jedną jednostką.
Dwa typy grup instancji:
a) Zarządzana (Managed): Identyczne VM tworzone za pomocą szablonu. Funkcje: Auto-skalowanie, auto-uzdrawianie (auto-healing) 
i zarządzane wydania (managed releases).
b) Niezarządzana (Unmanaged): Instancje VM o różnych konfiguracjach w tej samej grupie. Nie oferuje auto-skalowania, 
auto-uzdrawiania i innych usług. Nie jest zalecana, chyba że potrzebujesz różnych VM.
Lokalizacja może być strefowa lub regionalna. Regionalna zapewnia wyższą dostępność (zalecana).

502) Zarządzane grupy instancji (Managed Instance Groups MIG)?
Zarządzana grupa instancji (MIG): Identyczne maszyny wirtualne tworzone za pomocą szablonu instancji.
Ważne funkcje:
a) Utrzymanie określonej liczby instancji: Jeśli instancja ulegnie awarii, MIG uruchamia nową instancję.
b) Wykrywanie awarii aplikacji: Dzięki sprawdzaniu stanu (auto-uzdrawianie Auto Healing).
c) Zwiększanie i zmniejszanie liczby instancji w zależności od obciążenia (Auto Scaling).
d) Dodanie Load Balancer do rozdzielania obciążenia.
e) Tworzenie instancji w wielu strefach (regionalne MIGs). Regionalne MIGs zapewniają wyższą dostępność w porównaniu 
do strefowych MIGs.
f) Wydanie nowych wersji aplikacji bez przestojów.
g) Aktualizacje stopniowe: Wydawanie nowej wersji krok po kroku (stopniowo). Aktualizowanie procenta instancji do nowej 
wersji na raz.
h) Canary deployment: Testowanie nowej wersji na grupie instancji przed wydaniem jej na wszystkich instancjach.

503) Cloud Dataproc to zarządzana usługa dla których dwóch platform big data?
Spark i Hadoop.

504) Jaką usługę bazodanową należy wykorzystać, aby obsługiwała transakcje i dane tabelaryczne? 
Usługa bazodanowa powinna wspierać powszechnie stosowane narzędzia do zapytań.
Cloud SQL i Spanner.

505) Element menu "Historia zadań"?
Historia zadań pokazuje aktywne zadania, zakończone zadania oraz zadania, które wygenerowały błędy.

506) Polecenie do utworzenia tabeli o nazwie "iot-ingest-data" w BigTable?
"cbt createtable iot-ingest-data".

507) Jaki jest następny krok po utworzeniu instancji Cloud Spanner, który należy wykonać, aby umożliwić załadowanie danych?
Utwórz bazę danych w obrębie instancji.

508) Właśnie wyeksportowałeś dane z klastra Dataproc. Co zostało wyeksportowane?
Dane konfiguracyjne dotyczące klastra.

509) Polityka zarządzania obiektami wymaga, aby obiekty przechowywane w Cloud Storage były migrowane z pamięci 
regionalnej (Regional) do pamięci (Nearline) 90 dni po utworzeniu obiektu. Jak to zrobić?
Utwórz politykę konfiguracji zarządzania cyklem życia, określając wiek 90 dni i ustawiając "StorageClass" na nearline.

510) Polecenie wiersza poleceń do utworzenia koszyka Cloud Storage?
"gsutil mb".

511) Tworzenie Zarządzanej Grupy Instancji (MIG)?
Szablon instancji (instance template) jest wymagany. Skonfiguruj auto-skalowanie, aby automatycznie dostosować liczbę 
instancji w zależności od obciążenia:
a) Minimalna liczba instancji.
b) Maksymalna liczba instancji.
c) Metryki auto-skalowania (Auto-scaling metrics): Cel wykorzystania CPU, cel wykorzystania Load Balancera lub dowolna 
inna metryka z Stackdriver.
d) Okres chłodzenia (Cool-down period): Jak długo czekać, zanim ponownie sprawdzisz metryki auto-skalowania?
e) Kontrola skali w dół (Scale In Controls): Zapobiega nagłemu spadkowi liczby maszyn wirtualnych.
f) Auto-uzdrawianie (Auto-healing): Skonfiguruj sprawdzanie stanu z początkowym opóźnieniem 
(jak długo powinieneś poczekać na zainicjowanie aplikacji przed wykonaniem sprawdzenia stanu)?

512) Skalowanie w dół (Scale In), Skalowanie w górę (Scale Out)?
Skalowanie w dół (Scale In): Zmniejsza liczbę instancji VM, gdy obciążenie maleje.
Skalowanie w górę (Scale Out): Zwiększa liczbę instancji VM, gdy obciążenie rośnie.

513) Aktualizowanie Zarządzanej Grupy Instancji (MIG)?
Aktualizacja stopniowa (Rolling Update): Stopniowa aktualizacja instancji w grupie instancji do nowego szablonu instancji.
Określ nowy szablon: Opcjonalnie określ szablon do testów canary.
Określ, jak ma odbyć się aktualizacja:
a) Kiedy ma nastąpić aktualizacja? Rozpocznij aktualizację natychmiast (proaktywnie) lub Kiedy grupa instancji zostanie 
później skalowana (optymistycznie).
b) Jak ma odbyć się aktualizacja? Maksymalny wzrost (Maximum surge): Ile instancji ma zostać dodanych w danym momencie?
Maksymalna liczba niedostępnych (Maximum unavailable): Ile instancji może być niedostępnych podczas aktualizacji?
Rolling Restart/Replace: Stopniowe ponowne uruchamianie lub zastępowanie wszystkich instancji w grupie.

514) Grupa instancji. Unikanie częstego skalowania w górę i w dół?
Okres chłodzenia (Cool-down period) / początkowe opóźnienie (Initial delay).

515) Grupa instancji. Chcesz, aby niezdrowe instancje były automatycznie zastępowane?
Skonfiguruj sprawdzanie stanu (health check) w grupie instancji (auto-uzdrawianie).

516) Grupa instancji. Chcesz zapewnić wysoką dostępność w MIG, nawet podczas aktualizacji sprzętu/oprogramowania?
Użyj szablonu instancji z polityką dostępności "automatyczny restart" włączoną oraz "migracja podczas konserwacji hosta".
Zapewnia to migrację na żywo i automatyczne ponowne uruchomienie.

517) Grupa instancji. Chcesz zachować stan VM w MIG?
Stateful MIG: Zachowaj stan VM (nazwa instancji, podłączony dysk trwały i metadane).
Zalecane dla aplikacji wymagających stanu (np. bazy danych, aplikacje przetwarzania danych).

518) Grupa instancji. Chcesz utworzyć maszyny wirtualne o różnych konfiguracjach w tej samej grupie.
Utwórz niezarządzaną grupę instancji.

519) Grupa instancji. Chcesz, aby aplikacja zarządzana przez MIG przetrwała awarie strefowe?
Utwórz MIG obejmującą wiele stref (lub MIG regionalną).

520) Polecenia dla zarządzanych grup instancji (Managed Instance Groups)?
Gcloud compute instance-groups managed:
a) Utwórz grupę instancji:
"gcloud compute instance-group managed create my-mig --zone us-central1-a --template my-instance-template --size=1".
b) Skonfiguruj Auto-skalowanie: Włącz/wyłącz auto-skalowanie.
"gcloud compute instance-groups managed set-autoscaling my-mig --max-num-replicas=10".
c) Zaktualizuj istniejące polityki MIG (np. auto-uzdrawianie):
"gcloud compute instance-groups managed update my-mig".

521) Aktualizacja szablonu instancji?
"gcloud compute instance-groups managed set-instance-template my-mig --template=v2-template".
Po zaktualizowaniu szablonu instancji, możesz uruchomić wdrożenie nowego szablonu za pomocą poleceń update-instances, 
recreate-instances lub rolling-action start-update.

522) Chcę wprowadzić nową wersję bez zmniejszenia pojemności. Którą z tych opcji powinienem skonfigurować z wartością 0?
"--max-unavailable".

523) Jakie opcje konfiguracji mogą zapobiec częstym operacjom skalowania w MIG?
"--cool-down-period".

524) Cloud Load Balancing? 
Rozkłada ruch użytkowników między instancjami aplikacji w jednej lub wielu strefach.
W pełni rozproszona, zdefiniowana programowo usługa zarządzana.
Ważne cechy:
a) Sprawdzanie stanu (Health check).
b) Auto-skalowanie.
c) Globalne balansowanie obciążenia z jednym adresem IP anycast.
d) Umożliwia: Wysoką dostępność (High Availability), Auto-skalowanie, Odporność (Resilience).

525) Cloud Load Balancing - Terminologia?
a) Backend: Grupa punktów końcowych, które odbierają ruch od load balancera Google Cloud (np. grupy instancji).
b) Frontend: Określa adres IP, port i protokół. Ten adres IP to adres frontendowy dla żądań od klientów. 
W przypadku SSL należy również przypisać certyfikat.
c) Reguły hosta i ścieżki (dla load balancingu HTTP(s)): Definiują reguły przekierowujące ruch do różnych backendów:
Na podstawie ścieżki, Na podstawie hosta, Na podstawie nagłówków HTTP.

526) Load Balancing - Zakończenie (termination) / odciążenie (offloading) SSL/TLS?
Klient do Load Balancera: Przez Internet, HTTPS jest zalecane.
Load Balancer do instancji VM: Przez wewnętrzną sieć Google, HTTP jest wystarczające, ale HTTPS jest preferowane.
Zakończenie/odciążenie SSL/TLS: Klient do Load Balancera: HTTPS/TLS. Load Balancer do instancji VM: HTTP/TCP.

527) Cloud Load Balancing - Cechy (features)?
External HTTP(s): Globalny, Zewnętrzny, HTTP lub HTTPS. Proxy. HTTP na porcie 80 lub 8080, HTTPS na porcie 443.
Internal HTTP(S): Regionalny, Wewnętrzny, HTTP lub HTTPS. Proxy. HTTP na porcie 80 lub 8080, HTTPS na porcie 443.
SSL Proxy: Globalny, Zewnętrzny, TCP z odciążeniem SSL. Proxy. Duża lista portów.
TCP Proxy: Globalny, Zewnętrzny, TCP bez odciążenia SSL. Proxy. Duża lista portów.
External Network TCP/UDP: Regionalny, Zewnętrzny, TCP lub UDP. Pass-Through. Dowolne porty.
Internal TCP/UDP: Regionalny, Wewnętrzny, TCP lub UDP. Pass-Through. Dowolne porty.

528) Load Balancer. Chcesz, aby tylko zdrowe instancje otrzymywały ruch?
Skonfiguruj health-check.

529) Load Balancer. Chcesz zapewnić wysoką dostępność dla swoich instancji VM?
Utwórz wiele MIG dla swoich instancji VM w różnych regionach. Równoważ ruch za pomocą Load Balancer.

530) Load Balancer. Chcesz kierować żądania do wielu mikroserwisów, używając tego samego load balancera?
Utwórz indywidualne MIG i back-endy dla każdego mikroserwisu. Stwórz zasady Host i Path, aby przekierować ruch do 
odpowiedniego back-endu mikroserwisu w zależności od ścieżki (/microservice-a, /microservice-b, itd.). 
Możesz także kierować ruch do zasobników chmurowych.

531) Load Balancer. Chcesz równoważyć globalny zewnętrzny ruch HTTPS między instancjami back-end w wielu regionach?
Wybierz External HTTP(S) Load Balancer.

532) Chcesz zakończenie SSL dla globalnego ruchu nie-HTTPS z równoważeniem obciążenia?
Wybierz SSL Proxy Load Balancer.

533) Mikroserwisy?
Firmy zmierzają w kierunku architektury mikroserwisów. Buduj małe, skoncentrowane mikroserwisy. Elastyczność w innowacjach 
i budowie aplikacji w różnych językach (Go, Java, Python, JavaScript). Jednak rozwój staje się bardziej skomplikowany. 
Jak możemy mieć jedną metodę wdrażania mikroserwisów napisanych w Go, Java, Python lub JavaScript? Wchodzą kontenery.

534) Kontenery - Docker?
Twórz obrazy Docker dla każdego mikroserwisu. Obraz Docker zawiera wszystkie potrzeby mikroserwisu: 
środowisko uruchomieniowe aplikacji, kod aplikacji oraz zależności. Działa w ten sam sposób na każdej infrastrukturze: 
na Twojej lokalnej maszynie, w firmowym centrum danych, w chmurze.
Zalety:
a) Kontenery Docker są lekkie (brak systemu operacyjnego gościa).
b) Docker zapewnia izolację dla kontenerów.
c) Docker jest neutralny wobec chmury.

535) Orkiestracja Kontenerów?
Odpowiedź: Typowe cechy:
a) Auto Scaling: Skalowanie kontenerów w zależności od zapotrzebowania.
b) Service Discovery: Pomaga mikroserwisom znaleźć się nawzajem.
c) Load Balancer: Rozdziela obciążenie między wiele instancji mikroserwisu.
d) Self Healing: Wykonuje kontrole stanu i zastępuje uszkodzone instancje.
e) Zero Downtime Deployments: Wydanie nowych wersji bez przestojów.

536) Serverless?
Co jeśli nie musisz martwić się o serwery i możesz skupić się na swoim kodzie? Wchodzimy w świat Serverless. Nie musisz 
przejmować się infrastrukturą (brak widoczności infrastruktury). Elastyczne skalowanie i automatyczna wysoka dostępność. 
Płacisz za użycie. Skupiasz się na kodzie, a zarządzana usługa chmurowa zajmuje się wszystkim, co potrzebne, aby skalować 
Twój kod do obsługi milionów żądań! Płacisz za żądania.

537) Serverless - Ważne Cechy?
a) Brak trosk o infrastrukturę, skalowanie i dostępność. Google App Engine, AWS Fargate, Google Functions, AWS Lambda, 
Azure Functions.
b) Zero wywołań - Zero kosztów. Google App Engine, AWS Fargate, Google Functions, AWS Lambda, Azure Functions.
c) Płacisz za wywołania, a NIE za instancje (lub węzły, lub serwery). Google Functions, AWS Lambda, Azure Functions.

538) Grupy instancji?
Grupy instancji to zestawy maszyn wirtualnych (VM), które można skonfigurować do skalowania i są wykorzystywane przez 
load balancery, co przyczynia się do poprawy dostępności.

539) Przed utworzeniem grupy instancji, co musisz utworzyć?
Szablon grupy instancji.

540) App Engine. Chcę utworzyć dwie aplikacje Google App Engine w tym samym projekcie?
To niemożliwe. Można mieć tylko jedną aplikację App Engine na projekt. Jednak można mieć wiele usług i wiele wersji 
każdej z usług.

541) App Engine. Chcę przenieść moją aplikację Google App Engine do innego regionu?
App Engine jest specyficzny dla regionu. Nie możesz przenieść aplikacji do innego regionu. Utwórz nowy projekt i stwórz 
nową aplikację App Engine w nowym regionie.

542) App Engine Canary Deployment?
a) Wdróż V2 bez przenoszenia ruchu: "gcloud app deploy --no-promote".
b) Przenieś część ruchu do V2: "gcloud app services set-traffic s1 --split v1=0.9,v2=0.1".

543) Jaki flagi używa się do stopniowego migrowania ruchu do nowej wersji w App Engine? "gcloud app services set-traffic".
"--migrate".

544) Cloud Storage Archive Storage?
a) Nazwa: Archive.
b) Minimalny czas przechowywania: 365 dni.
c) Typowa dostępność miesięczna: 99,95% w regionach multi-region i dual-region, 99,9% w regionach.
c) Przypadek użycia: Mniej niż raz w roku.

545) Storage Classes Coldline Storage?
a) Nazwa: Coldline.
b) Minimalny czas przechowywania: 90 dni.
c) Typowa dostępność miesięczna: 99,95% w regionach multi-region i dual-region, 99,9% w regionach.
d) Przypadek użycia: Odczyt lub modyfikacja maksymalnie raz na kwartał.

546) Storage Class Nearline Storage?
a) Nazwa: Nearline.
b) Minimalny czas przechowywania: 30 dni.
c) Typowa dostępność miesięczna: 99,95% w regionach multi-region i dual-region, 99,9% w regionach.
d) Przypadek użycia: Odczyt lub modyfikacja średnio raz w miesiącu.

547) Storage Class Standard?
a) Nazwa: Standard.
b) Minimalny czas przechowywania: Brak.
c) Typowa dostępność miesięczna: 99,99% w regionach multi-region, region i dual-region, 99,99% w regionach.
d) Przypadek użycia: Często używane dane / krótki okres czasu.

548) Cloud Storage - Klasy przechowywania?
Różne rodzaje danych mogą być przechowywane w Cloud Storage: pliki multimedialne i archiwa, pakiety aplikacji i logi, 
kopie zapasowe baz danych lub urządzeń przechowywania, archiwa długoterminowe. Istnieją ogromne różnice w wzorcach dostępu. 
Czy mogę zapłacić niższą cenę za obiekty, do których uzyskuję dostęp rzadziej? Klasy przechowywania pomagają zoptymalizować 
koszty w zależności od potrzeb dostępu. Zaprojektowane z myślą o trwałości (jedenaście dziewiątek).

549) Cechy różnych klas pamięci (Storage Classes)?
Wysoka trwałość (11 9's rocznej trwałości). Niskie opóźnienie (pierwszy bajt zazwyczaj w ciągu kilku milisekund).
Nielimitowana pamięć: Autoskalowanie (bez potrzeby konfiguracji). Brak minimalnej wielkości obiektu. 
Te same API we wszystkich klasach pamięci.
Zobowiązane SLA to 99,95% dla pamięci multi-region i 99,9% dla pamięci single-region dla pamięci Standard, Nearline i Coldline.
Brak zobowiązanego SLA dla pamięci Archive.

550) Przykład Cloud IAM?
Chcę udzielić dostępu do zarządzania określonym koszykiem pamięci w chmurze mojemu koledze: Ważne, ogólne pojęcia.
Członek: Mój kolega. Zasoby: Określony koszyk pamięci w chmurze. Akcja: Przesyłanie/usuwanie obiektów.
W Google Cloud IAM. Role: Zestaw uprawnień (do wykonywania określonych działań na określonych zasobach). 
Jak przypisać uprawnienia do członka?
Polityka: Przypisujesz (lub wiążesz) rolę do członka. Wybierz rolę z odpowiednimi uprawnieniami 
(przykład: Storage Object Admin). Stwórz powiązanie polityki, przypisując członka (twojego kolegę) do roli (uprawnień).
IAM w AWS jest bardzo różne od GCP.

551) Jak zmienić klasę pamięci istniejącego koszyka w Cloud Storage?
a) Krok 1: Zmień domyślną klasę pamięci koszyka.
b) Krok 2: Zaktualizuj klasę pamięci obiektów w koszyku.

552) Które funkcje Cloud Storage pozwalają na optymalizację kosztów poprzez automatyczne przenoszenie plików między 
różnymi klasami pamięci?
Zarządzanie cyklem życia obiektów (Object Lifecycle Management).

553) Pliki dzienników przechowywane w Cloud Storage. Spodziewasz się dostępu do nich raz na kwartał?
Coldline.

554) Chcesz trwale przechowywać logi aplikacji z powodów regulacyjnych. Nie spodziewasz się dostępu do nich wcale?
Cloud Storage - Archive.

555) Jak przyspieszyć duże przesyłanie plików (np. 100 gigabajtów) do Cloud Storage?
Użyj równoległych przesyłek złożonych (Parallel Composite Uploads) (plik jest dzielony na małe kawałki i przesyłany).

556) Cloud Storage - Przesyłanie i pobieranie obiektów?
a) Proste przesyłanie (Simple Upload): Małe pliki (które mogą być ponownie przesyłane w przypadku awarii) oraz brak 
metadanych obiektów.
b) Przesyłanie multipart (Multipart Upload): Małe pliki (które mogą być ponownie przesyłane w przypadku awarii) 
oraz metadane obiektów.
c) Powtarzane przesyłanie (Resumable upload): Większe pliki. Zalecane w większości przypadków 
(nawet dla małych plików - kosztuje jedno dodatkowe zapytanie HTTP).
d) Przesyłanie strumieniowe (Streaming transfer): Przesyłanie obiektu o nieznanym rozmiarze.
e) Równoległe przesyłanie złożone (Parallel composite uploads): Plik podzielony na maksymalnie 32 kawałki i przesyłany 
równolegle. Znacznie szybsze, jeśli szybkość sieci i dysku nie stanowią ograniczeń.
f) Proste pobieranie (Simple download): Pobieranie obiektów do docelowego miejsca.
g) Pobieranie strumieniowe (Streaming download): Pobieranie danych do procesu.
h) Pobieranie obiektów w częściach (Sliced object download): Dzielenie i pobieranie dużych obiektów. 

557) Zarządzanie cyklem życia obiektów?
Pliki są często dostępne zaraz po ich utworzeniu. Zazwyczaj ich użytkowanie zmniejsza się z czasem. Jak zaoszczędzić 
koszty, automatycznie przenosząc pliki między klasami pamięci?
Rozwiązanie: Zarządzanie cyklem życia obiektów (Object Lifecycle management).
Zidentyfikuj obiekty, używając warunków opartych na: Age, Created Before, IsLive, MatchesStorageClass, NumberOfNewVersions itd.
Ustaw wiele warunków: wszystkie warunki muszą zostać spełnione, aby akcja miała miejsce. Dwa rodzaje działań:
a) SetStorageClass (zmiana z jednej klasy pamięci na inną).
b) Akcje usuwania (usunięcie obiektów).
Dozwolone transakcje:
a) Standardowa lub Multi-Region lub Regional do Nearline, Coldline lub Archive.
b) Nearline do Coldline lub Archive.
c) Coldline do Archive.

558) Czym jest Google Cloud Platform (GCP)?
Google Cloud Platform (GCP) to zestaw usług chmurowych, który działa na tej samej infrastrukturze, którą Google 
wykorzystuje wewnętrznie do swoich produktów końcowych, takich jak Google Search, Gmail, przechowywanie plików i YouTube.
GCP oferuje szereg usług, w tym obliczenia, przechowywanie danych, analitykę danych i uczenie maszynowe.

559) Czy możesz wyjaśnić różnicę między IaaS, PaaS i SaaS? Podaj przykłady z GCP.
a) IaaS (Infrastructure as a Service): Oferuje zwirtualizowane zasoby obliczeniowe przez internet.
W GCP przykładem IaaS jest Google Compute Engine (GCE), który oferuje maszyny wirtualne, które użytkownicy mogą 
dostosować i kontrolować.
b) PaaS (Platform as a Service): Oferuje narzędzia sprzętowe i programowe przez internet, zazwyczaj do tworzenia aplikacji.
Google App Engine (GAE) to przykład PaaS, oferujący platformę dla programistów do budowania, wdrażania i skalowania aplikacji.
c) SaaS (Software as a Service): Dostarcza aplikacje oprogramowania przez internet na zasadzie subskrypcji.
Google Workspace (dawniej G Suite) to przykład SaaS, oferujący narzędzia produktywności dla firm.

560) Czym są Google Compute Engine i Google App Engine? Jak się różnią?
Google Compute Engine (GCE) to IaaS, które oferuje maszyny wirtualne (VM) w chmurze, dając użytkownikom pełną kontrolę 
nad systemem operacyjnym, ustawieniami sieciowymi i całym stosem oprogramowania.
Z kolei Google App Engine (GAE) to PaaS, które abstrahuje większość infrastruktury, umożliwiając programistom skupienie 
się na pisaniu kodu bez martwienia się o środowisko, w którym ten kod działa.
GAE automatycznie skaluje aplikację w odpowiedzi na otrzymywany ruch.

561) Wyjaśnij pojęcie równoważenia obciążenia w GCP.
Równoważenie obciążenia to metoda używana do rozdzielania ruchu sieciowego lub aplikacyjnego pomiędzy wiele serwerów w 
farmie serwerów lub puli serwerów. W GCP równoważenie obciążenia pozwala użytkownikom rozdzielać nadchodzący ruch 
pomiędzy wieloma instancjami ich aplikacji, co poprawia wydajność i dostępność.
GCP oferuje różne rodzaje równoważników obciążenia, w tym HTTP(S) Load Balancing, TCP/SSL Proxy Load Balancing i 
Network Load Balancing, z których każdy jest zaprojektowany do specyficznych scenariuszy.

562) Czym jest Google Cloud Storage? Jakie rodzaje klas pamięci oferuje?
Google Cloud Storage to jednolite rozwiązanie do przechowywania obiektów, które pozwala użytkownikom przechowywać i 
uzyskiwać dostęp do danych z dowolnego miejsca.
Oferuje różne klasy pamięci dla różnych przypadków użycia:
a) Standard (dla często używanych danych).
b) Nearline (dla danych, które są dostępne mniej niż raz w miesiącu).
c) Coldline (dla danych, które są dostępne mniej niż raz na kwartał).
d) Archive (dla długoterminowego przechowywania, dostępnego mniej niż raz w roku). 
każda z nich różni się ceną i dostępnością.

563) Opisz różnicę między BigQuery a Cloud Bigtable.
BigQuery to w pełni zarządzane, bezserwerowe i wysoce skalowalne magazynowanie danych zaprojektowane z myślą o 
elastyczności biznesowej (business agility). Jest idealne do przeprowadzania analiz dużych zbiorów danych.
Cloud Bigtable to z kolei usługa bazy danych NoSQL, która jest odpowiednia do analiz w czasie rzeczywistym i obciążeń 
operacyjnych z dużymi ilościami danych.
Podczas gdy BigQuery jest zoptymalizowane do zapytań analitycznych, Cloud Bigtable jest zoptymalizowane do dostępu do 
danych o niskim opóźnieniu.

564) Czym jest Virtual Private Cloud (VPC) i jakie są jego korzyści w GCP?
Virtual Private Cloud (VPC) w GCP to zarządzana funkcjonalność sieciowa dla zasobów chmurowych.
Zapewnia prywatną sieć z alokacją adresów IP, routowaniem oraz politykami zapory sieciowej, aby zabezpieczyć i zarządzać 
zasobami chmurowymi.
Korzyści obejmują izolację zasobów chmurowych, skalowalne i elastyczne opcje sieciowe oraz integrację z usługami GCP, 
co pozwala na stworzenie spójnego środowiska chmurowego.

565) Jak GCP zarządza tożsamością (identity) i dostępem (access)?
GCP zarządza tożsamością i dostępem za pomocą Cloud Identity and Access Management (IAM). IAM pozwala administratorom 
autoryzować, kto może podejmować działania na określonych zasobach, dając im kontrolę nad tym, którzy użytkownicy i 
usługi mogą uzyskać dostęp do różnych zasobów.
Polityki IAM definiują uprawnienia i mogą być ustawiane na różnych poziomach hierarchii zasobów GCP.

566) Jaka jest rola Kubernetes Engine w GCP?
Google Kubernetes Engine (GKE) to zarządzane środowisko w GCP do wdrażania, zarządzania i skalowania aplikacji 
kontenerowych przy użyciu infrastruktury Google.
GKE automatyzuje wiele aspektów związanych z konfiguracją i utrzymaniem klastrów Kubernetes, ułatwiając wdrażanie aplikacji, 
skalowanie ich w razie potrzeby oraz zarządzanie ich cyklem życia, bez potrzeby posiadania głębokiej wiedzy na temat Kubernetes.

567) Wyjaśnij pojęcie obliczeń bezserwerowych w GCP. Podaj przykłady produktów bezserwerowych oferowanych przez GCP?
Obliczenia bezserwerowe pozwalają na tworzenie i uruchamianie aplikacji oraz usług bez konieczności zarządzania infrastrukturą.
Twoja aplikacja nadal działa na serwerach, ale całe zarządzanie serwerami jest realizowane przez GCP. Ten model umożliwia 
programistom skupienie się na kodzie, podczas gdy GCP zajmuje się skalowaniem, provisionowaniem i zarządzaniem serwerami.
Przykłady produktów bezserwerowych w GCP to Google Cloud Functions (funkcje wyzwalane zdarzeniami), Google App Engine 
(platforma aplikacyjna) oraz Google Cloud Run (aplikacje kontenerowe).

568) Jakie są główne usługi obliczeniowe oferowane przez GCP?
GCP oferuje kilka usług obliczeniowych, w tym:
a) Google Compute Engine (GCE): Elastyczna i skalowalna usługa IaaS, która zapewnia maszyny wirtualne (VM) w chmurze.
b) Google Kubernetes Engine (GKE): Zarządzana usługa, która pozwala użytkownikom uruchamiać i zarządzać kontenerami Docker 
oraz aplikacjami opartymi na kontenerach na klastrze maszyn wirtualnych (VM).
c) Google App Engine (GAE): PaaS do budowania skalowalnych aplikacji internetowych i zaplecza dla aplikacji mobilnych.
d) Google Cloud Functions (GCF): Bezserwerowe środowisko wykonawcze do budowania i łączenia usług chmurowych za pomocą 
funkcji o pojedynczym celu, które odpowiadają na zdarzenia chmurowe.

569) Opisz Google Compute Engine (GCE) i jego kluczowe funkcje.
Google Compute Engine zapewnia skalowalne i elastyczne instancje maszyn wirtualnych do uruchamiania obciążeń roboczych 
na infrastrukturze Google. Kluczowe funkcje to:
a) Dostosowane i predefiniowane typy maszyn, które odpowiadają na potrzeby obciążeń roboczych.
b) Trwałe dyski do przechowywania danych o wysokiej wydajności.
c) Globalne równoważenie obciążenia do rozdzielania ruchu użytkowników pomiędzy instancje.
d) Migracja maszyn wirtualnych w czasie rzeczywistym (Live Migration), aby zapewnić ich działanie podczas konserwacji.

570) Jak Google Kubernetes Engine (GKE) upraszcza zarządzanie kontenerami?
GKE upraszcza zarządzanie kontenerami, oferując zarządzane środowisko, które automatyzuje wiele aspektów konfiguracji i 
obsługi klastrów Kubernetes, w tym:
a) Automatyczne provisionowanie i skalowanie maszyn wirtualnych dla aplikacji kontenerowych.
b) Zintegrowane logowanie i monitorowanie, które umożliwiają wgląd w wydajność aplikacji.
c) Wbudowane polityki sieciowe do zabezpieczania aplikacji.
d) Bezproblemowa integracja (Seamless integration) z usługami GCP w celu zwiększenia funkcjonalności.

571) Jaka jest różnica między standardowym a elastycznym środowiskiem Google App Engine?
App Engine Standard Environment: Oferuje w pełni zarządzaną platformę, która automatycznie skaluje aplikację w górę i w dół, 
równocześnie równoważąc obciążenie. Obsługuje określone środowiska uruchomieniowe i ogranicza dostęp do systemu operacyjnego 
oraz systemu plików.
App Engine Flexible Environment: Oferuje większą elastyczność i umożliwia dostosowanie środowiska uruchomieniowego, 
dostęp do systemu operacyjnego oraz używanie dowolnego języka programowania lub oprogramowania firm trzecich. 
Jest odpowiednie dla aplikacji, które wymagają dostępu do niestandardowego lub natywnego kodu.

572) Wyjaśnij, jak działają Google Cloud Functions i jakie są ich przypadki użycia?
Google Cloud Functions to bezserwerowe środowisko wykonawcze, które uruchamia Twój kod w odpowiedzi na zdarzenia. 
Automatycznie skaluje wykonanie kodu, uruchamiając instancje równolegle w razie potrzeby.
Przypadki użycia obejmują:
a) Przetwarzanie danych przechowywanych w Google Cloud Storage.
b) Reagowanie na zmiany w Firebase Realtime Database.
c) Implementacja zaplecza webhooków dla GitHub lub Slack.
d) Tworzenie lekkich API i mikroserwisów.

573) Czym są preemptible VMs w GCE i kiedy warto ich używać?
Preemptible VMs to opłacalna opcja uruchamiania obciążeń roboczych na Google Compute Engine, które mogą zostać przerwane.
Te maszyny wirtualne są oferowane w niższej cenie niż standardowe instancje, ale mogą zostać zakończone przez GCP, 
jeśli będzie potrzebować zasobów do innych zadań.
Należy je używać do zadań wsadowych, obciążeń odpornych na błędy lub jakiejkolwiek pracy, którą można łatwo ponownie 
uruchomić i która nie wymaga trwałych zasobów obliczeniowych.

574) Jak zapewnić wysoką dostępność i odporność na awarie dla zasobów obliczeniowych GCP?
Aby zapewnić wysoką dostępność i odporność na awarie, możesz:
a) Wdrażać aplikacje w wielu strefach lub regionach, aby chronić przed awariami strefy lub regionu.
b) Korzystać z usług zarządzanych, takich jak GKE, które automatycznie obsługują wymianę uszkodzonych instancji.
c) Wdrożyć sprawdzanie stanu i automatyczne ponowne uruchamianie dla maszyn wirtualnych (VM).
d) Używać globalnego równoważenia obciążenia HTTP(S) do rozdzielania ruchu pomiędzy instancje w różnych regionach.

575) Czy możesz wyjaśnić pojęcie typów maszyn w GCE?
Typy maszyn w GCE definiują dostępne zasoby CPU, pamięci i inne zasoby dla maszyny wirtualnej.
GCE oferuje różne typy maszyn:
a) Predefiniowane typy maszyn (Predefined machine types): Optymalizowane pod kątem określonych obciążeń roboczych, 
takich jak ogólnego przeznaczenia, zoptymalizowane pod kątem pamięci oraz zoptymalizowane pod kątem obliczeń.
b) Niestandardowe typy maszyn (Custom machine types): Umożliwiają określenie dokładnej ilości CPU i pamięci, 
którą potrzebujesz, zapewniając elastyczność i oszczędności kosztów dla obciążeń roboczych, które nie pasują do 
predefiniowanych typów.

576) Jaką rolę odgrywa Cloud Load Balancing w GCP w zarządzaniu zasobami obliczeniowymi?
Cloud Load Balancing rozdziela przychodzący ruch sieciowy pomiędzy wiele instancji obliczeniowych, aby zapewnić, 
że żadna instancja nie będzie obciążona nadmiernym ruchem. Poprawia to dostępność i odporność na awarie aplikacji poprzez:
a) Globalne rozdzielanie ruchu pomiędzy różne regiony.
b) Automatyczne skalowanie i przełączanie awaryjne (failover).
c) Obsługę ruchu HTTP(S), TCP/SSL i UDP.
d) Integrację z automatycznym skalowaniem GCP, aby dynamicznie dostosować zasoby do zapotrzebowania.

577) Opisz, jak zarządzać aplikacjami stanowymi w Kubernetes Engine.
Zarządzanie aplikacjami stanowymi w GKE obejmuje:
a) Używanie StatefulSets, które zapewniają unikalne, trwałe tożsamości oraz stabilne, trwałe przechowywanie danych dla 
każdego poda.
b) Implementowanie persistent volumes, które są niezależne od cyklu życia poda, aby przechowywać dane.
c) Konfigurowanie usług, które zapewniają stabilną sieć do komunikacji z podami.
d) Stosowanie probe'ów gotowości (readiness) i probe'ów żywotności (liveness), aby upewnić się, że pody są uznawane za 
gotowe tylko wtedy, gdy mogą obsługiwać żądania i są restartowane, jeśli zawiodą.

578) Jakie są podstawowe opcje przechowywania dostępne w GCP?
GCP oferuje różnorodne opcje przechowywania, które odpowiadają na różne potrzeby:
a) Google Cloud Storage: Usługa przechowywania obiektów do przechowywania i dostępu do dowolnej ilości danych.
b) Persistent Disk: Przechowywanie blokowe dla maszyn wirtualnych Google Compute Engine.
c) Filestore: Zarządzana usługa przechowywania plików dla aplikacji, które wymagają interfejsu systemu plików i 
współdzielonego systemu plików.
d) Local SSD: Przechowywanie blokowe o wysokiej wydajności, lokalne i przejściowe (transient), zaprojektowane w celu 
zapewnienia wysokiej przepustowości i niskiej latencji.

579) Czy możesz wyjaśnić różnicę między Cloud SQL a Cloud Spanner?
Cloud SQL to w pełni zarządzana usługa baz danych relacyjnych, która obsługuje MySQL, PostgreSQL i SQL Server.
Jest idealna do tradycyjnych obciążeń baz danych, które wymagają bazy danych relacyjnej, i została zaprojektowana z myślą 
o kompatybilności z istniejącymi aplikacjami.
Cloud Spanner to w pełni zarządzana, skalowalna usługa baz danych relacyjnych dla danych aplikacji regionalnych i globalnych.
Oferuje skalowanie poziome bez kompromisów w zakresie silnej spójności i semantyki relacyjnej, co czyni ją odpowiednią 
dla większych, rozproszonych aplikacji, które wymagają wysokiej dostępności i globalnej dystrybucji.

580) Jak Google Cloud Storage zapewnia trwałość i dostępność danych?
Google Cloud Storage zapewnia wysoką trwałość i dostępność danych dzięki:
a) Replikacji danych (Data Replication): Przechowywaniu wielu kopii danych w różnych lokalizacjach fizycznych, 
aby chronić przed awariami sprzętu i katastrofami.
b) Automatycznym sumom kontrolnym (Automatic checksums): Weryfikacji integralności danych zarówno w spoczynku, 
jak i w trakcie transmisji, w celu wykrywania i naprawiania wszelkich uszkodzeń.
c) Klasom przechowywania (Storage classes): Oferowaniu różnych klas przechowywania(Standard, Nearline, Coldline i Archive), 
które równoważą częstotliwość dostępu z kosztami, umożliwiając użytkownikom wybór odpowiedniego poziomu dostępności 
i ceny dla swoich danych.

581) Czym jest BigQuery i jak się go używa?
BigQuery to w pełni zarządzane, bezserwerowe i wysoce skalowalne magazynowanie danych na GCP, zaprojektowane z myślą 
o analizie dużych danych.
Umożliwia użytkownikom analizowanie dużych zbiorów danych w czasie rzeczywistym za pomocą zapytań w stylu SQL.
BigQuery jest używane do wykonywania szybkich zapytań SQL-like na zestawach danych wielotero-bajtowych, co czyni je 
idealnym rozwiązaniem do aplikacji analitycznych, business intelligence i raportowania. 

582) Opisz przypadki użycia Firestore i Realtime Database w Firebase.
Firestore to elastyczna, skalowalna baza danych do rozwoju aplikacji mobilnych, webowych i serwerowych w Firebase i GCP.
Obsługuje zaawansowane zapytania, aktualizacje w czasie rzeczywistym oraz replikację danych w wielu regionach, co czyni 
ją odpowiednią dla aplikacji, które wymagają złożonych, hierarchicznych struktur danych i synchronizacji w czasie rzeczywistym.
Realtime Database to baza danych NoSQL hostowana w chmurze, która umożliwia przechowywanie i synchronizowanie danych 
między użytkownikami w czasie rzeczywistym.
Jest idealna dla aplikacji, które wymagają wysokiej wydajności i aktualizacji danych w czasie rzeczywistym w bardzo 
dużych zbiorach danych, takich jak aplikacje do współpracy w czasie rzeczywistym, analityka w czasie rzeczywistym oraz 
gry wieloosobowe.

583) Jakie są zalety korzystania z Cloud Bigtable?
Cloud Bigtable to w pełni zarządzana, skalowalna usługa bazy danych NoSQL przeznaczona do dużych obciążeń analitycznych 
i operacyjnych.
Zalety to:
a) Wysoka wydajność: Optymalizowana pod kątem zarówno przepustowości, jak i niskiej latencji dla operacji odczytu i zapisu.
b) Skalowalność: Automatycznie skalowalna w górę i w dół w zależności od obciążenia, zdolna do obsługi petabajtów danych 
na tysiącach maszyn wirtualnych.
c) Integracja: Bezproblemowa integracja z BigQuery, Dataflow i Hadoop, co czyni ją odpowiednią do uczenia maszynowego, 
analityki danych oraz aplikacji w czasie rzeczywistym.

584) Jak wybierasz odpowiednią opcję przechowywania danych w GCP dla swojej aplikacji?
Odpowiedź: Wybór odpowiedniej opcji przechowywania danych w GCP zależy od kilku czynników:
a) Model danych: Czy Twoje dane są strukturalne, czy niestrukturalne.
b) Wzorce dostępu: Częstotliwość dostępu i to, czy obciążenie jest bardziej odczytowe, zapisywalne, czy wymaga dostępu w czasie rzeczywistym.
c) Skalowalność: Wolumen danych i ich wzrost w czasie.
d) Wymagania geograficzne: Czy dane muszą być dostępne globalnie, czy tylko w określonych regionach.
e) Wymagania kosztowe: Ograniczenia budżetowe i potrzeba zrównoważenia kosztów z wydajnością oraz częstotliwością dostępu.

585) Wyjaśnienie pojęcia spójności (consistency) danych w Cloud Firestore?
Cloud Firestore zapewnia silną spójność dla wszystkich operacji odczytu i zapisu, co oznacza, że każdy klient widzi tę 
samą wersję danych w danym momencie. Jest to osiągane dzięki projektowi systemu rozproszonego, który synchronizuje dane 
w różnych lokalizacjach, zapewniając, że każda operacja zapisu jest natychmiast widoczna dla wszystkich kolejnych 
operacji odczytu, niezależnie od lokalizacji klienta.

586) Jaka jest rola Google Cloud Memorystore?
Google Cloud Memorystore oferuje w pełni zarządzane usługi przechowywania danych w pamięci, obsługujące Redis i Memcached. 
Jest zaprojektowany do używania jako pamięć podręczna (cache) lub magazyn sesji, oferując dostęp do danych w czasie 
poniżej milisekundy. Czyni to go idealnym rozwiązaniem dla aplikacji wymagających szybkiego dostępu do danych, 
takich jak przechowywanie często używanych informacji w celu poprawy czasów odpowiedzi aplikacji.

587) Jak możesz zabezpieczyć dane przechowywane w GCP?
Zabezpieczenie danych w GCP można osiągnąć poprzez kilka praktyk:
a) Szyfrowanie: GCP automatycznie szyfruje dane w spoczynku oraz w trakcie transmisji. Dodatkowo klienci mogą zarządzać 
swoimi kluczami szyfrującymi za pomocą Cloud Key Management Service.
b) Kontrola dostępu: Używanie Identity and Access Management (IAM) do określenia, kto ma dostęp do zasobów oraz jakie 
operacje mogą wykonywać.
c) Miejsce przechowywania danych: Wybór lokalizacji przechowywania, które są zgodne z wymaganiami regulacyjnymi.
d) Monitorowanie i logowanie: Używanie Cloud Audit Logs oraz Security Command Center do monitorowania dostępu i 
wykrywania zagrożeń bezpieczeństwa dla danych.

588) Co to jest VPC w GCP i dlaczego jest ważne?
Virtual Private Cloud (VPC) w GCP to zarządzana warstwa sieciowa dla zasobów GCP. Zapewnia prywatną, izolowaną część 
chmury, w której możesz uruchamiać zasoby w wirtualnej sieci, którą sam zdefiniujesz. VPC są kluczowe, ponieważ 
zapewniają skalowalną i elastyczną sieć dla zasobów opartych na chmurze, umożliwiając kontrolowanie zakresów adresów IP, 
podsieci, bram sieciowych i reguł zapory.

589) Wyjaśnij różnicę między Globalnym a Regionalnym VPC w GCP.
W GCP, Globalne VPC (Virtual Private Cloud) obejmuje wszystkie regiony, umożliwiając zasobom w różnych regionach 
komunikację ze sobą za pomocą wewnętrznych adresów IP, bez potrzeby korzystania z zewnętrznych adresów IP lub VPN. 
Z kolei Regionalne VPC jest ograniczone do jednego regionu. Podczas gdy Globalne VPC oferują bezproblemową łączność i 
uproszczoną architekturę sieciową dla wdrożeń wieloregionowych, Regionalne VPC mogą być korzystne w przypadku lokalnych 
wdrożeń, które mają specyficzne wymagania dotyczące zgodności lub opóźnień.

590) Czym jest Cloud Load Balancing w GCP i jakie są jego rodzaje?
Cloud Load Balancing to w pełni rozproszona, zdefiniowana programowo (software-defined) usługa zarządzana do równoważenia 
obciążenia Twojego ruchu sieciowego. W GCP występuje w różnych typach, dopasowanych do różnych potrzeb: 
a) HTTP(S) Load Balancing do ruchu HTTP i HTTPS. 
b) TCP/SSL Proxy do ruchu TCP i SSL.
c) Internal Load Balancing do rozdzielania ruchu wewnątrz GCP. 
d) Network Load Balancing do równoważenia ruchu TCP/UDP na skalę globalną. 
Każdy typ jest zaprojektowany w celu optymalizacji wydajności i dostępności Twoich aplikacji.

591) Jak działa Cloud CDN (Content Delivery Network) w GCP?
Cloud CDN wykorzystuje globalnie rozproszone punkty obecności Google'a (edge points of presence) do buforowania treści 
HTTP(S) obciążonych równoważeniem obciążenia w pobliżu użytkowników. W GCP, Cloud CDN jest zintegrowany z 
HTTP(S) Load Balancing, co pozwala na serwowanie treści bezpośrednio z pamięci podręcznej, zmniejszając opóźnienia i 
poprawiając czasy ładowania stron dla Twoich aplikacji. Działa to poprzez buforowanie treści w lokalizacjach brzegowych 
(edge locations); kiedy użytkownik żąda treści, Cloud CDN dostarcza ją z najbliższej lokalizacji brzegowej, 
jeśli jest dostępna, aby zminimalizować odległość i opóźnienia.

592) Opisz funkcję Cloud Interconnect i kiedy warto go używać.
Cloud Interconnect zapewnia szybkie, dedykowane i bezpieczne połączenie między Twoją lokalną siecią a Google Cloud VPC. 
Jest idealny w scenariuszach wymagających dużej przepustowości, niskich opóźnień lub przy przesyłaniu dużych ilości 
danych między chmurą a lokalną infrastrukturą. Istnieją dwa rodzaje: Dedicated Interconnect dla prywatnego połączenia 
przez dedykowaną fizyczną łączność oraz Partner Interconnect dla połączeń za pośrednictwem wspieranego dostawcy usług.

593) Czym są zasady zapory (Firewall Rules) w GCP i jak działają?
Zasady zapory w GCP kontrolują ruch do i z instancji w obrębie VPC. Pozwalają na określenie, który ruch przychodzący 
i wychodzący jest dozwolony lub zablokowany na podstawie adresów IP, protokołów i portów. Zasady zapory są stanowe 
(stateful), co oznacza, że odpowiedź na dozwolony ruch przychodzący jest automatycznie dozwolona, niezależnie od zasad 
dla ruchu wychodzącego. Są one kluczowe dla zapewnienia bezpieczeństwa i prawidłowego funkcjonowania zasobów chmurowych.

594) Wyjaśnij pojęcie podsieci (Subnetworks) w GCP i ich znaczenie.
Podsieci (lub subnets) w GCP to podziały sieci VPC, które pozwalają na bardziej efektywne segmentowanie sieci. Każda 
podsieć jest przypisana do określonego regionu i definiuje zakres adresów IP. Dzięki podsieciom można organizować zasoby 
według działu, aplikacji lub według innych potrzeb operacyjnych, co poprawia zarządzanie siecią, bezpieczeństwo i 
wydajność poprzez kontrolowanie przepływu ruchu na podstawie określonych zasad.

595) Jak zapewnić wysoką dostępność i odzyskiwanie po awarii w sieciach GCP?
Zapewnienie wysokiej dostępności i odzyskiwania po awarii w sieciach GCP polega na projektowaniu z redundancją, wyborze 
usług wieloregionowych, gdy to możliwe, korzystaniu z zarządzanych usług, które zapewniają automatyczne przełączanie 
awaryjne (np. Cloud SQL), oraz wdrożeniu solidnego monitorowania i alertowania. Dodatkowo, korzystanie z 
Cloud Load Balancing do rozdzielania ruchu między instancjami w różnych regionach oraz projektowanie aplikacji jako 
bezstanowych, gdzie to możliwe, może zwiększyć odporność systemu.

596) Opisz zakresy adresów IP w VPC oraz ich związek z podsieciami.
Zakresy adresów IP w VPC są definiowane podczas tworzenia VPC lub podsieci, określając zakres adresów IP dostępnych do 
użycia przez zasoby w danej sieci. Każda podsieć w VPC musi mieć unikalny zakres adresów IP, który nie nakłada się na 
zakresy innych podsieci w tej samej VPC. Taka struktura pozwala na efektywne zarządzanie adresami IP, zapobiegając 
konfliktom i zapewniając skuteczną komunikację zasobów sieciowych.

597) Co to jest Network Peering w GCP i jak różni się od VPN?
Network Peering w GCP pozwala na połączenie dwóch VPC, dzięki czemu mogą one dzielić zasoby bez korzystania z publicznego 
internetu, zapewniając bezpieczną i bezpośrednią ścieżkę sieciową. Różni się to od VPN (Virtual Private Network), ponieważ 
VPN tworzy bezpieczne, szyfrowane połączenie przez internet, aby połączyć sieci, podczas gdy peering zapewnia bezpośrednie 
połączenie bez szyfrowania. Peering jest zwykle stosowany do połączeń o dużej prędkości i niskim opóźnieniu między VPC 
w tej samej organizacji lub w różnych organizacjach, podczas gdy VPN służy do bezpiecznego łączenia użytkowników zdalnych 
lub różnych sieci przez internet.

598) Co to jest Identity and Access Management (IAM) w GCP?
IAM w GCP zarządza kontrolą dostępu, definiując, kto (tożsamość) ma dostęp do jakich zasobów (role) w GCP. Umożliwia to 
administratorom przyznawanie szczegółowych uprawnień użytkownikom, grupom i kontom serwisowym.

599) Wyjaśnij rolę kont serwisowych (Service Accounts) w GCP.
Konta serwisowe to specjalne konta używane przez aplikacje lub maszyny wirtualne (VM) do interakcji z innymi usługami 
Google Cloud. Te konta mogą mieć przypisane role IAM i służą do uwierzytelniania aplikacji w celu programowego dostępu 
do zasobów GCP.

600) Czym jest Cloud Identity w GCP i jak integruje się z IAM?
Cloud Identity to zintegrowane rozwiązanie do zarządzania tożsamościami, dostępem, aplikacjami i urządzeniami, stworzone 
z myślą o chmurze. Integruje się z IAM, umożliwiając centralne zarządzanie użytkownikami i grupami, co pozwala na 
kontrolowanie ich dostępu do usług i zasobów GCP.

601) Jak GCP obsługuje szyfrowanie danych w spoczynku (at rest) i w trakcie transmisji (in transit)?
a) W spoczynku (at rest): GCP automatycznie szyfruje dane przed zapisaniem ich na dysku, używając kluczy szyfrujących 
zarządzanych przez Google. Klienci mogą również zarządzać własnymi kluczami szyfrującymi za pomocą 
Cloud Key Management Service (KMS).
b) W trakcie transmisji (in transit): GCP szyfruje dane, gdy przesyłają je między swoimi centrami danych oraz do/z klientów 
przez internet, używając protokołów takich jak TLS.

602) Jaki jest cel Cloud Security Command Center (CSCC) w GCP?
CSCC to kompleksowa platforma zarządzania bezpieczeństwem i ryzykiem danych w GCP, która pomaga w zapobieganiu, wykrywaniu 
i reagowaniu na zagrożenia. Zapewnia wgląd w zasoby chmurowe oraz umożliwia ich kontrolowanie, pozwalając na skanowanie 
danych wrażliwych, wykrywanie typowych luk bezpieczeństwa w aplikacjach internetowych oraz przeglądanie praw dostępu.

603) Wyjaśnij pojęcie VPC Service Controls w GCP.
VPC Service Controls wzmacniają obwód bezpieczeństwa wokół usług opartych na API w GCP, pomagając w ograniczeniu ryzyka 
wycieku danych. Umożliwiają administratorom definiowanie obwodów wokół zasobów, kontrolowanie ruchu danych przez ten 
obwód oraz zapewnianie dostępu do usług z zarządzanych środowisk.

604) Jak GCP realizuje segmentację sieci w celu zapewnienia bezpieczeństwa?
GCP wykorzystuje VPC do segmentacji sieci, umożliwiając administratorom podział chmurowej sieci na podsieci. Zapory 
sieciowe (firewalle) oraz VPC Service Controls mogą być następnie używane do egzekwowania polityk i izolowania obciążeń,
minimalizując ryzyko rozprzestrzeniania się zagrożeń w przypadku naruszenia bezpieczeństwa.

605) Czym są Google Cloud Audit Logs i jakie mają znaczenie?
Google Cloud Audit Logs to rejestr działań i dostępu w Twoim środowisku GCP. Są one kluczowe dla bezpieczeństwa i zgodności, 
zapewniając wgląd w to, kto, co, gdzie i kiedy wykonał daną czynność. Pomagają w monitorowaniu i analizowaniu zdarzeń 
związanych z bezpieczeństwem oraz w przeprowadzaniu dochodzeń kryminalistycznych (forensic investigations).

606) Jak działa Security Health Analytics w GCP?
Security Health Analytics to funkcja w ramach Cloud Security Command Center, która automatycznie skanuje zasoby GCP w 
celu wykrywania podatności i odstępstw od najlepszych praktyk, takich jak otwarte pojemniki storage, publiczne adresy IP 
na maszynach wirtualnych (VM) lub błędnie skonfigurowane zapory sieciowe.

607) Czym jest model BeyondCorp Google’a i jak jest stosowany w GCP?
BeyondCorp to implementacja modelu bezpieczeństwa zero trust (zero zaufania) przez Google, który przenosi kontrolę dostępu 
z obwodu sieciowego na indywidualnych użytkowników i urządzenia. Umożliwia to bezpieczny dostęp do aplikacji i zasobów na
podstawie tożsamości użytkownika oraz kontekstu jego żądania, bez konieczności korzystania z tradycyjnego VPN. W GCP model 
ten realizowany jest za pomocą technologii takich jak Identity-Aware Proxy (IAP), która pozwala na bezpieczny dostęp do 
aplikacji na podstawie tożsamości i kontekstu.

608) Co to jest Google Cloud Console i jakie są jej kluczowe funkcje?
Google Cloud Console to oparta na przeglądarce graficzna interfejs użytkownika, który umożliwia zarządzanie zasobami 
Google Cloud. Kluczowe funkcje to m.in. możliwość wdrażania i zarządzania aplikacjami, monitorowanie usług, zarządzanie 
fakturowaniem, konfigurowanie ról i uprawnień IAM, dostęp do Cloud Shell oraz korzystanie z API. Console zapewnia 
intuicyjny sposób nawigacji i kontrolowania wszystkich aspektów Google Cloud.

609) Wyjaśnij rolę Google Cloud SDK w zarządzaniu GCP.
Google Cloud SDK to zestaw narzędzi, które można wykorzystać do zarządzania zasobami i aplikacjami hostowanymi na 
Google Cloud. Narzędzia te obejmują polecenie wiersza poleceń "gcloud", "gsutil" oraz "bq", które umożliwiają odpowiednio 
zarządzanie zasobami Google Cloud, interakcję z Google Cloud Storage oraz zapytania do zbiorów danych w BigQuery. 
SDK umożliwia tworzenie skryptów i automatyzację zadań zarządzania chmurą bezpośrednio z poziomu wiersza poleceń lub za 
pomocą skryptów.

610) Co to jest Cloud Monitoring i jak integruje się z usługami GCP?
Cloud Monitoring to usługa w ramach GCP, która zapewnia wgląd w wydajność, dostępność i ogólny stan aplikacji działających 
w chmurze. Zbiera metryki, zdarzenia i metadane z usług GCP, maszyn wirtualnych oraz instrumentacji aplikacji. 
Dzięki Cloud Monitoring, deweloperzy i operatorzy mogą uzyskać informacje na temat wydajności swoich aplikacji w czasie 
rzeczywistym, ustawiać alerty oraz tworzyć niestandardowe pulpity nawigacyjne do wizualizacji danych metryk. 
Usługa integruje się bezproblemowo z większością usług GCP, oferując wbudowane możliwości monitorowania.

611) Opisz Cloud Logging i jego znaczenie w GCP.
Cloud Logging to w pełni zarządzana usługa w GCP, która umożliwia przechowywanie, przeszukiwanie, analizowanie, 
monitorowanie i ustawianie alertów na podstawie danych dzienników oraz zdarzeń z Google Cloud i Amazon Web Services. 
Odgrywa kluczową rolę w debugowaniu i rozwiązywaniu problemów z aplikacjami, monitorowaniu bezpieczeństwa oraz utrzymaniu 
ścieżek audytów. Cloud Logging zapewnia scentralizowaną platformę do zarządzania dziennikami, wspierając zarządzanie i 
analizę dzienników w czasie rzeczywistym, co ułatwia deweloperom i administratorom systemów zrozumienie ich środowisk chmurowych.

612) Jak zestaw operacji Google Cloud (dawniej znany jako Stackdriver) poprawia monitorowanie aplikacji i infrastruktury?
Zestaw operacji Google Cloud, wcześniej znany jako Stackdriver, poprawia monitorowanie aplikacji i infrastruktury, oferując 
zintegrowane i inteligentne monitorowanie, rejestrowanie i diagnostykę. Zapewnia on potężne możliwości monitorowania, 
które obejmują metryki wydajności, pulpity nawigacyjne, sprawdzanie dostępności oraz systemy alertów. Zestaw oferuje 
również rozbudowane możliwości rejestrowania, raportowania błędów oraz analizowania śladów (trace) w celu szczegółowego 
zrozumienia wydajności i stanu aplikacji, ułatwiając proaktywne zarządzanie i optymalizację zasobów chmurowych.

613) Co to jest Cloud Deployment Manager i jaki problem rozwiązuje?
Cloud Deployment Manager to usługa zarządzania infrastrukturą, która automatyzuje wdrażanie i zarządzanie zasobami 
Google Cloud. Umożliwia użytkownikom definiowanie wszystkich zasobów potrzebnych do aplikacji w formacie deklaratywnym, 
używając YAML lub Pythona. Głównym problemem, który rozwiązuje, jest złożoność ręcznego zarządzania wieloma zasobami. 
Automatyzując tworzenie i zarządzanie zasobami, zapewnia spójność, zmniejsza ryzyko błędów ludzkich i wspiera efektywne 
procesy wdrażania.

614) Omów znaczenie Identity and Access Management (IAM) w zarządzaniu środowiskami GCP.
Identity and Access Management (IAM) w GCP jest kluczowe dla zabezpieczania i zarządzania dostępem do zasobów GCP. 
Umożliwia administratorom definiowanie, kto (tożsamość) ma dostęp do jakich zasobów oraz jakie akcje może wykonywać 
(role i uprawnienia). Ta precyzyjność pomaga wdrożyć zasadę minimalnych uprawnień, zmniejszając ryzyko nieautoryzowanego 
dostępu lub działań. IAM wspiera zarządzanie dostępem dla użytkowników, kont serwisowych oraz członkostw grup, ułatwiając 
bezpieczne i efektywne operacje w środowiskach GCP.

615) Jakie korzyści płyną z korzystania z raportów Cloud Billing i narzędzi do zarządzania kosztami w GCP?
Raporty Cloud Billing i narzędzia do zarządzania kosztami w GCP dostarczają szczegółowych informacji na temat wydatków 
chmurowych, pomagając organizacjom zrozumieć, gdzie i jak wykorzystywane są zasoby chmurowe oraz jakie ponoszą koszty. 
Narzędzia te oferują funkcje takie jak budżetowanie, alerty i szczegółowe raporty, które rozbijają koszty według projektu, 
usługi i zasobu, umożliwiając strategie optymalizacji kosztów, takie jak identyfikowanie niedostatecznie wykorzystywanych 
zasobów, prognozowanie przyszłych wydatków oraz podejmowanie świadomych decyzji o alokacji zasobów. Są one niezbędne do 
skutecznego zarządzania i optymalizacji wydatków chmurowych.

616) Wyjaśnij funkcjonalność Cloud Build i jego przypadki użycia.
Cloud Build to usługa, która wykonuje procesy kompilacji na Google Cloud Platform. Umożliwia budowanie, testowanie i 
wdrażanie aplikacji w szybki, skalowalny i bezpieczny sposób. Przypadki użycia obejmują ciągłą integrację i dostarczanie 
(CI/CD), automatyzację cyklu budowania-testowania-wdrażania aplikacji, tworzenie obrazów kontenerów oraz wdrażanie 
aplikacji na Google Kubernetes Engine (GKE) lub innych usługach GCP. Cloud Build obsługuje różnorodne języki programowania 
i integruje się z innymi usługami GCP, zapewniając płynne doświadczenie deweloperskie.

617) Jak Cloud Armor chroni aplikacje hostowane w GCP?
Cloud Armor to usługa zabezpieczeń, która zapewnia ochronę przed atakami typu Distributed Denial of Service (DDoS) oraz 
funkcję zapory aplikacji internetowych (WAF), chroniąc aplikacje działające na GCP przed różnorodnymi zagrożeniami, 
w tym atakami DDoS, wstrzykiwaniem SQL i atakami typu cross-site scripting (XSS). Działa poprzez stosowanie zasad 
bezpieczeństwa do przychodzącego ruchu, blokując lub filtrując złośliwe żądania, zanim dotrą one do aplikacji. 
Cloud Armor obsługuje niestandardowe zasady i integruje się z globalnym load balancingiem HTTP(S) w celu zapewnienia 
polityk bezpieczeństwa zorientowanych na aplikację.

618) Co to jest Cloud SDK i jak jest używane w rozwoju GCP?
Google Cloud SDK to zestaw narzędzi służących do zarządzania zasobami i aplikacjami hostowanymi na Google Cloud Platform. 
Narzędzia te obejmują komendę "gcloud" do tworzenia i zarządzania zasobami GCP, "gsutil" do interakcji z Google Cloud 
Storage oraz "bq" do zarządzania danymi w BigQuery. Jest to kluczowe narzędzie do automatyzacji procesów wdrażania i 
zarządzania oraz do skryptowania interakcji z usługami GCP.

619) Wyjaśnij cel i korzyści z używania Cloud Source Repositories.
Cloud Source Repositories to w pełni zarządzana usługa kontroli wersji, która hostuje prywatne repozytoria Git na 
Google Cloud. Zapewnia bezpieczną, skalowalną platformę do współpracy przy rozwoju oprogramowania. Korzyści obejmują 
łatwą integrację z innymi usługami GCP, takimi jak Cloud Build do tworzenia pipeline'ów CI/CD oraz IAM do kontroli dostępu. 
Usługa ta wspiera również lustrzane repozytoria GitHub i Bitbucket, umożliwiając zintegrowane wyszukiwanie i analizę 
kodu w ramach GCP.

620) Jak Cloud Build ułatwia CI/CD w GCP?
Cloud Build to platforma Google Cloud do ciągłej integracji (CI) i ciągłego wdrażania (CD), która umożliwia budowanie, 
testowanie i wdrażanie oprogramowania w różnych językach programowania. Automatyzuje procesy budowania, testowania i 
wdrażania, co pozwala deweloperom na szybkie dostarczanie wysokiej jakości kodu. Cloud Build integruje się z Cloud Source 
Repositories, GitHub i Bitbucket, umożliwiając płynne włączenie do workflow deweloperskiego. Obsługuje niestandardowe 
procesy, konteneryzację oraz wdrażanie bezserwerowe, co czyni go wszechstronnym narzędziem do nowoczesnego dostarczania 
aplikacji.

621) Opisz funkcje oferowane przez Cloud Functions?
Cloud Functions to środowisko wykonawcze bezserwerowe, umożliwiające tworzenie i łączenie usług w chmurze.
Dzięki Cloud Functions można pisać proste funkcje o jednym celu, które są powiązane z wydarzeniami emitowanymi przez 
infrastrukturę chmurową i usługi.
Automatycznie skalują się w zależności od obciążenia, obsługują środowiska uruchomieniowe Node.js, Python, Go oraz Java, 
i integrują się z usługami GCP w celu tworzenia bardziej złożonych aplikacji. Przykłady zastosowań obejmują przetwarzanie 
danych, integrację z usługami zewnętrznymi oraz implementację API i architektur mikroserwisów. 

622) Co to jest App Engine i jak różni się od Kubernetes Engine?
App Engine to w pełni zarządzana, bezserwerowa platforma do tworzenia i hostowania aplikacji internetowych na dużą skalę.
Abstrakcyjnie zarządza infrastrukturą, umożliwiając deweloperom skupienie się na kodzie. App Engine automatycznie skaluje 
aplikacje w górę i w dół, równocześnie balansując obciążenie.
Kubernetes Engine (GKE) to z kolei zarządzane środowisko do uruchamiania aplikacji w kontenerach z wykorzystaniem 
infrastruktury Google Cloud. GKE oferuje większą kontrolę nad środowiskiem i jest idealne do architektur mikroserwisów. 
Kluczowa różnica leży w poziomie abstrakcji i kontroli; App Engine oferuje środowisko o wyższym poziomie abstrakcji w 
porównaniu do GKE.

623) Jak Cloud Endpoints wspomaga zarządzanie i rozwój API?
Cloud Endpoints to rozproszone system zarządzania API, który oferuje narzędzia do tworzenia, wdrażania i zarządzania API.
Wspomaga zarządzanie API poprzez funkcje takie jak uwierzytelnianie, monitorowanie, rejestrowanie zdarzeń oraz klucze API 
do kontroli dostępu.
Obsługuje protokoły OpenAPI i gRPC, co ułatwia wdrażanie API, a także integruje się z App Engine, Compute Engine i GKE. 
Cloud Endpoints pomaga deweloperom w zabezpieczaniu, monitorowaniu i skalowaniu API, oferując płynny sposób zarządzania 
ekosystemem API.

624) Omów zalety korzystania z Firebase w tworzeniu aplikacji mobilnych i webowych.
Firebase to kompleksowa platforma do tworzenia aplikacji, która oferuje szeroki wachlarz narzędzi i usług wspierających 
deweloperów w budowie, ulepszaniu i rozwoju aplikacji mobilnych i webowych.
Zalety obejmują bazy danych w czasie rzeczywistym, uwierzytelnianie użytkowników, analitykę, raportowanie awarii oraz 
wiadomości w chmurze. Firebase upraszcza rozwój zaplecza aplikacji, zapewnia skalowalną infrastrukturę i integruje się 
z usługami GCP. Przyspiesza proces tworzenia aplikacji, umożliwiając szybkie iteracje i wdrożenie aplikacji.

625) Jaką rolę odgrywa Cloud Pub/Sub w architekturach opartych na zdarzeniach?
Cloud Pub/Sub to usługa komunikacji, która umożliwia asynchroniczną wymianę informacji pomiędzy komponentami w 
rozproszonym systemie.
Wspiera architektury oparte na zdarzeniach, zapewniając wysoce skalowalną i niezawodną infrastrukturę do publikowania i 
subskrybowania zdarzeń.
Umożliwia to luźne powiązanie pomiędzy komponentami, pozwalając im na efektywną komunikację bez bezpośrednich połączeń. 
Cloud Pub/Sub jest kluczowy w budowaniu skalowalnych i odpornych aplikacji, a jego zastosowania obejmują pobieranie 
zdarzeń, integrację danych oraz analizę w czasie rzeczywistym.

626) Jak deweloperzy mogą wykorzystać Cloud Firestore do rozwoju aplikacji?
Cloud Firestore to w pełni zarządzana, dokumentowa baza danych NoSQL, przeznaczona do tworzenia aplikacji webowych, 
mobilnych i serwerowych na dużą skalę.
Deweloperzy mogą wykorzystać Cloud Firestore do synchronizacji danych w czasie rzeczywistym, wsparcia offline oraz 
realizacji transakcji ACID w rozproszonych danych.
Oferuje płynną integrację z Firebase, umożliwiając tworzenie aplikacji bogatych w funkcje, zapewniając solidną 
skalowalność i globalną dystrybucję danych. Firestore upraszcza modelowanie danych oraz zapytania, pozwalając deweloperom 
skoncentrować się na tworzeniu angażujących doświadczeń użytkowników.

627) Wyjaśnij znaczenie Container Registry w GCP?
Container Registry to jedno miejsce, w którym Twój zespół może zarządzać obrazami Docker, przeprowadzać analizę podatności 
oraz określać, kto ma dostęp do jakich zasobów za pomocą precyzyjnej kontroli dostępu.
Jest zintegrowane z funkcjami bezpieczeństwa i prywatności Google Cloud, zapewniając bezpieczne i prywatne repozytorium 
dla obrazów Docker. Container Registry wspiera szybkie, skalowalne pobieranie i wdrażanie kontenerów do usług GCP, 
takich jak GKE, Compute Engine i App Engine. Jest kluczowe dla organizacji przyjmujących praktyki wdrażania oparte na 
kontenerach, umożliwiając efektywne zarządzanie kontenerami i workflow wdrożeniami.

628) Czym jest BigQuery i jak wspiera analizę danych w GCP?
BigQuery to w pełni zarządzany, bezserwerowy i wysoce skalowalny magazyn danych na platformie Google Cloud, 
zaprojektowany z myślą o elastyczności biznesowej.
Umożliwia super szybkie zapytania SQL na petabajtach danych, wykorzystując moc obliczeniową infrastruktury Google. 
BigQuery wspiera analizę danych w czasie rzeczywistym, umożliwiając strumieniowanie danych do BigQuery z prędkością 
milionów wierszy na sekundę.
Model bezserwerowy BigQuery abstrahuje zarządzanie infrastrukturą, pozwalając analitykom i naukowcom od danych skupić 
się na analizie danych i wyciąganiu cennych wniosków, bez martwienia się o zarządzanie sprzętem czy bazą danych.

629) Jak obsługiwać dane strumieniowe w GCP w celach analitycznych?
Do obsługi danych strumieniowych w GCP można użyć Cloud Pub/Sub do pobierania strumieni zdarzeń, które następnie mogą 
być przetwarzane w czasie rzeczywistym za pomocą Cloud Dataflow lub bezpośrednio strumieniowane do BigQuery w celu analizy.
Cloud Pub/Sub pełni rolę wysoce skalowalnej i niezawodnej usługi komunikacji, podczas gdy Cloud Dataflow to w pełni 
zarządzana usługa przetwarzania danych strumieniowych i wsadowych.
Ta kombinacja umożliwia analizę w czasie rzeczywistym oraz przetwarzanie danych strumieniowych, pozwalając na uzyskiwanie 
natychmiastowych wniosków i podejmowanie działań na podstawie napływających strumieni danych.

630) Jaką rolę pełni Cloud Dataflow w pipeline analityki danych GCP?
Cloud Dataflow to w pełni zarządzana usługa przetwarzania danych strumieniowych i wsadowych. Jest zaprojektowana do 
przetwarzania dużych ilości danych w czasie rzeczywistym lub wsadowo, oferując elastyczny i skalowalny sposób transformacji, 
agregacji i analizy danych.
Cloud Dataflow jest zbudowany na Apache Beam, co umożliwia deweloperom korzystanie z jednego modelu programowania zarówno 
dla przetwarzania wsadowego, jak i strumieniowego.
Pełni kluczową rolę w pipeline analityki danych GCP, umożliwiając złożone transformacje danych, analizy, integrację danych 
oraz procesy ETL (Extract, Transform, Load), ułatwiając uzyskiwanie wniosków z danych zarówno w czasie rzeczywistym, 
jak i historycznych.

631) Jak Google Cloud Dataprep wspiera analityków danych?
Google Cloud Dataprep by Trifacta to inteligentna usługa chmurowa umożliwiająca wizualne eksplorowanie, czyszczenie i 
przygotowywanie danych do analizy oraz uczenia maszynowego.
Jest zaprojektowana w sposób przyjazny dla analityków danych, którzy nie posiadają głębokich umiejętności technicznych. 
Dataprep automatycznie wykrywa schematy danych, typy danych oraz anomalie, takie jak brakujące wartości czy duplikaty.
Oferuje sugestie dotyczące transformacji danych i pozwala analitykom stosować te transformacje za pomocą prostego, 
interaktywnego interfejsu.
To przyspiesza proces przygotowywania danych, ułatwiając analitykom przygotowanie danych do analizy lub modeli uczenia 
maszynowego, bez potrzeby pisania kodu.

632) Jakie są główne funkcje Cloud Dataproc i kiedy warto go używać?
Cloud Dataproc to zarządzana usługa Spark i Hadoop, która upraszcza uruchamianie frameworków przetwarzania danych na 
dużą skalę w GCP.
Główne funkcje to szybkie i łatwe provisionowanie klastrów Spark i Hadoop, elastyczne opcje cenowe i skalowalność, 
oraz integracja z innymi usługami GCP, takimi jak BigQuery i Cloud Storage.
Cloud Dataproc warto używać, gdy konieczne jest przetwarzanie dużych zbiorów danych za pomocą aplikacji opartych na 
Spark lub Hadoop, ale chcemy uniknąć złożoności i obciążeń operacyjnych związanych z zarządzaniem tymi klastrami. 
Jest idealny do zadań ETL, przetwarzania wsadowego oraz uruchamiania aplikacji analityki danych na dużą skalę.

633) Jak GCP integruje uczenie maszynowe w swoich usługach analityki danych?
GCP integruje uczenie maszynowe w swoich usługach analityki danych na kilka sposobów. BigQuery ML umożliwia użytkownikom 
tworzenie i uruchamianie modeli uczenia maszynowego bezpośrednio w BigQuery za pomocą zapytań SQL, co sprawia, że uczenie 
maszynowe staje się dostępne dla analityków danych.
AutoML Tables i AutoML Vision oferują prosty sposób na tworzenie i wdrażanie modeli uczenia maszynowego bez potrzeby 
posiadania głębokiej wiedzy na temat uczenia maszynowego. Dodatkowo, AI Platform oferuje zestaw narzędzi i usług do 
budowania, trenowania i wdrażania modeli uczenia maszynowego na dużą skalę.
Te integracje pozwalają użytkownikom płynnie włączyć uczenie maszynowe do swoich procesów analityki danych, wzbogacając 
wnioski i możliwości prognozowania.

634) Opisz cel i przypadki użycia Google Data Studio.
Google Data Studio to darmowe, chmurowe narzędzie do wizualizacji danych, które umożliwia użytkownikom tworzenie 
interaktywnych pulpitów nawigacyjnych i raportów z różnych źródeł danych. Jego celem jest udostępnienie danych i ich 
zrozumienie wszystkim interesariuszom za pomocą wizualnej reprezentacji.
Przykłady użycia to tworzenie raportów, które agregują dane z wielu źródeł (takich jak BigQuery, Sheets czy Analytics), 
dzielenie się spostrzeżeniami z członkami zespołu lub klientami oraz monitorowanie metryk biznesowych w czasie rzeczywistym. 
Funkcje współpracy i łatwy interfejs Data Studio sprawiają, że jest to idealne narzędzie dla firm, które chcą upowszechnić 
analizę danych i podejmowanie decyzji.

635) Jakie znaczenie ma Cloud Datalab dla analityków danych pracujących na GCP?
Cloud Datalab to interaktywne narzędzie oparte na Jupyter (dawniej IPython), które ułatwia eksplorację, analizę, 
wizualizację i uczenie maszynowe na GCP.
Jest szczególnie istotne dla analityków, ponieważ oferuje potężne i elastyczne środowisko do badania i analizowania danych, 
budowania modeli uczenia maszynowego oraz testowania i prototypowania aplikacji.
Cloud Datalab integruje się bezproblemowo z usługami GCP, takimi jak BigQuery, Cloud Storage i Cloud Machine Learning Engine, 
umożliwiając analitykom wykorzystanie pełnej mocy GCP w projektach związanych z analizą danych. Obsługuje Pythona oraz 
biblioteki do analizy danych, co sprawia, że jest wszechstronnym narzędziem do zadań analitycznych i uczenia maszynowego.

636) Jak Looker pasuje do ekosystemu analityki danych GCP?
Looker to platforma business intelligence (BI) i analityki, która integruje się z ekosystemem analityki danych Google Cloud, 
oferując możliwości eksploracji danych, wizualizacji i raportowania.
Looker pasuje do ekosystemu GCP, oferując potężną warstwę analityczną na szczycie BigQuery i innych usług GCP, 
umożliwiając użytkownikom tworzenie modeli danych, budowanie wnikliwych wizualizacji oraz dzielenie się raportami w 
organizacjach.
Integracja Lookera z GCP wspiera podejmowanie decyzji opartych na danych, zapewniając dostęp w czasie rzeczywistym do 
danych, interaktywne pulpity nawigacyjne oraz elastyczny język modelowania LookML, który pozwala definiować zależności 
i transformacje danych.

637) Jak zabezpieczyć środowiska analityki danych na GCP?
Zabezpieczanie środowisk analityki danych na GCP obejmuje wielowarstwowe środki bezpieczeństwa. Należą do nich:
a) Zarządzanie Tożsamościami i Dostępem (IAM): Kontrola, kto ma dostęp do zasobów GCP i jakie działania mogą być wykonywane. 
IAM umożliwia przypisywanie odpowiednich ról i uprawnień do użytkowników i grup, zapewniając precyzyjne zarządzanie dostępem.
b) Szyfrowanie danych: Korzystanie z wbudowanego szyfrowania GCP dla danych w spoczynku (at rest) i w trakcie transmisji 
(in transit), aby chronić dane przed nieautoryzowanym dostępem.
c) Prywatna łączność: Wykorzystanie prywatnych sieci i rozwiązań takich jak Cloud VPN lub Cloud Interconnect do bezpiecznego 
łączenia się z zasobami GCP, eliminując ryzyko związane z otwartymi połączeniami internetowymi.
d) Logi audytowe: Włączenie logów audytowych (Cloud Audit Logs) do śledzenia działań użytkowników – kto, co i kiedy zrobił, 
zapewniając pełną widoczność dostępu i wzorców użycia.
e) Zgodność i najlepsze praktyki: Przestrzeganie najlepszych praktyk bezpieczeństwa GCP oraz spełnianie wymagań 
regulacyjnych związanych z danymi i branżą, aby zapewnić zgodność z przepisami ochrony prywatności i bezpieczeństwa.

638) Czym jest AI Platform na GCP i jak jest używane w projektach uczenia maszynowego?
AI Platform to kompleksowy zestaw usług i narzędzi na GCP, zaprojektowany w celu wspierania naukowców danych i inżynierów 
uczenia maszynowego w budowaniu, wdrażaniu i zarządzaniu modelami ML na dużą skalę.
Obsługuje różne frameworki ML, w tym TensorFlow, scikit-learn i XGBoost, oferując zarówno zarządzane usługi do trenowania 
modeli i wykonywania predykcji, jak i maszyny wirtualne dla głębokiego uczenia (VMs), które umożliwiają bardziej 
dostosowany rozwój modeli.
AI Platform wspiera cały workflow ML, począwszy od przetwarzania danych, trenowania i oceny modeli, aż po wdrożenie i 
przewidywania, co ułatwia zespołom wdrażanie uczenia maszynowego w swoich projektach.

639) Jak AutoML na GCP demokratyzuje uczenie maszynowe?
AutoML na GCP demokratyzuje uczenie maszynowe, oferując zestaw produktów, które umożliwiają użytkownikom z ograniczoną 
wiedzą na temat uczenia maszynowego trenowanie wysokiej jakości modeli, dostosowanych do ich potrzeb biznesowych.
AutoML obejmuje różne obszary, takie jak wizja (vision), przetwarzanie języka naturalnego (language) i dane strukturalne, 
pozwalając użytkownikom tworzyć modele niestandardowe za pomocą łatwego w użyciu interfejsu graficznego.
Automatyzuje proces wyboru modelu, trenowania i dostosowywania, co sprawia, że nowoczesne uczenie maszynowe staje się 
dostępne dla osób bez specjalistycznej wiedzy, jednocześnie znacząco skracając czas i zasoby potrzebne do wdrożenia 
rozwiązań ML.

640) Jaką rolę pełni BigQuery ML dla naukowców danych i analityków?
BigQuery ML umożliwia naukowcom i analitykom tworzenie i wdrażanie modeli uczenia maszynowego bezpośrednio w 
Google BigQuery za pomocą poleceń SQL. Ta integracja pozwala użytkownikom na tworzenie modeli na dużych zbiorach danych 
bez konieczności przenoszenia danych z bazy lub opanowywania skomplikowanych frameworków uczenia maszynowego.
BigQuery ML obsługuje różne modele ML, w tym regresję liniową, regresję logistyczną oraz klasteryzację k-średnich(k-means), 
i jest szczególnie przydatny do dokonywania prognoz bezpośrednio na danych strukturalnych przechowywanych w BigQuery.
BigQuery ML obniża barierę wejścia do uczenia maszynowego, wykorzystując umiejętności SQL, które są szeroko znane wśród 
profesjonalistów zajmujących się danymi.

641) Czym jest TensorFlow i dlaczego ma znaczenie w ofercie AI i uczenia maszynowego GCP?
TensorFlow to open-source'owy framework uczenia maszynowego opracowany przez Google, który jest szeroko wykorzystywany
w ofercie AI i uczenia maszynowego GCP. Jest zaprojektowany do przeprowadzania złożonych obliczeń i zapewnia elastyczną 
platformę do prowadzenia badań oraz rozwoju, jak również do wdrażania modeli uczenia maszynowego o jakości produkcyjnej.
TensorFlow obsługuje uczenie głębokie oraz inne algorytmy uczenia maszynowego, oferując rozbudowaną bibliotekę i narzędzia 
do realizacji takich zadań jak sieci neuronowe.
Jego znaczenie w ofercie GCP polega na integracji z AI Platform, co ułatwia trenowanie, wdrażanie i zarządzanie modelami 
TensorFlow na dużą skalę w ramach GCP.

642) Jak usługi Vision AI na GCP mogą być wykorzystywane w aplikacjach?
Usługi Vision AI na GCP, w tym Vision API i AutoML Vision, umożliwiają aplikacjom głębsze rozumienie obrazów i wideo.
Vision API oferuje wstępnie wytrenowane modele, które potrafią wykrywać obiekty, twarze, teksty i inne elementy w obrazach, 
co pozwala na implementację funkcji takich jak moderacja treści, wykrywanie emocji czy optyczne rozpoznawanie znaków (OCR).
AutoML Vision umożliwia tworzenie niestandardowych modeli dostosowanych do konkretnych potrzeb, nawet bez zaawansowanej 
wiedzy na temat uczenia maszynowego.
Te usługi mogą być wykorzystywane w różnych aplikacjach, od handlu detalicznego i mediów społecznościowych, 
po bezpieczeństwo i opiekę zdrowotną, w zadaniach takich jak identyfikacja produktów, wyszukiwanie oparte na obrazach 
czy automatyczne tagowanie treści.

643) W jaki sposób GCP wspiera aplikacje przetwarzania języka naturalnego (NLP)?
GCP wspiera aplikacje NLP za pomocą Cloud Natural Language API oraz AutoML Natural Language.
Cloud Natural Language API oferuje wstępnie wytrenowane modele do analizy tekstu, w tym analizę sentymentu, rozpoznawanie 
encji i analizę składniową. Umożliwia aplikacjom zrozumienie struktury i znaczenia tekstu w wielu językach.
AutoML Natural Language pozwala użytkownikom trenować niestandardowe modele do klasyfikacji, ekstrakcji encji i analizy 
sentymentu, dostosowane do ich specyficznych zbiorów danych.
Te narzędzia wspierają szeroki zakres aplikacji NLP, od klasyfikacji treści i analizy sentymentu klientów po AI 
konwersacyjne i systemy rekomendacji treści.

644) Jakie korzyści daje używanie Kubeflow na GCP w projektach uczenia maszynowego?
Kubeflow to projekt open-source, który ułatwia wdrażanie workflowów uczenia maszynowego na Kubernetesie, a jego 
integracja z GCP oferuje szereg korzyści.
Kubeflow umożliwia inżynierom ML budowanie, trenowanie i wdrażanie modeli uczenia maszynowego w skalowalnym i przenośnym 
środowisku. Na GCP Kubeflow upraszcza orkiestrację pipeline'ów ML, obsługuje różne frameworki ML i zapewnia spójne 
środowisko zarówno dla rozwoju, jak i produkcji.
Wykorzystuje zarządzane usługi GCP, takie jak AI Platform, do trenowania i przewidywania, co ułatwia skalowanie modeli 
ML oraz efektywne zarządzanie zasobami.
Kompatybilność Kubeflow z GCP zapewnia płynne połączenie z usługami GCP do przechowywania danych, przetwarzania i analityki.

645) Jak zapewnić sprawiedliwość modeli i unikać biasu w modelach uczenia maszynowego na GCP?
Zapewnienie sprawiedliwości modeli i unikanie biasu w modelach uczenia maszynowego na GCP obejmuje kilka strategii. 
GCP oferuje narzędzia i dokumentację z najlepszymi praktykami dotyczącymi sprawiedliwości ML, takie jak:
a) Wykorzystanie narzędzia What-If: Narzędzie to umożliwia wizualizację i badanie prognoz modelu w celu analizy 
sprawiedliwości i oceny, czy model działa w sposób równy i nieskrzywiony dla różnych grup danych.
b) Implementacja wskaźników sprawiedliwości: Wskaźniki te pozwalają ocenić wydajność modelu w różnych grupach, co pomaga 
zidentyfikować ewentualne nierówności w wynikach.
c) Zastosowanie technik przetwarzania i wzbogacania danych: Techniki te pomagają zmniejszyć bias w danych treningowych, 
np. przez zrównoważenie zbiorów danych lub usuwanie danych, które mogą wprowadzać uprzedzenia.
d) Regularne przeglądanie i aktualizowanie modeli: Modele powinny być regularnie aktualizowane o różnorodne zbiory danych, 
które odzwierciedlają szeroki zakres perspektyw, aby uniknąć zniekształceń w oparciu o zbyt wąskie źródła danych.
e) Przestrzeganie zasad odpowiedzialnej sztucznej inteligencji: Obejmuje to przejrzystość, odpowiedzialność i kwestie 
etyczne w rozwoju oraz wdrażaniu modeli, zapewniając, że modele są tworzone i wykorzystywane w sposób sprawiedliwy i 
zgodny z wartościami społecznymi.
Te podejścia pomagają w budowaniu bardziej sprawiedliwych, rzetelnych i odpowiedzialnych modeli uczenia maszynowego na GCP.

646) Czym jest federacyjne uczenie maszynowe i jakie ma potencjalne zastosowania w GCP?
Federacyjne uczenie maszynowe to podejście do uczenia maszynowego, które polega na trenowaniu algorytmów na wielu 
zdecentralizowanych urządzeniach lub serwerach przechowujących lokalne próbki danych, bez wymiany tych danych między nimi. 
Technika ta poprawia prywatność użytkowników, ponieważ dane nie są centralizowane, a jedynie lokalnie przetwarzane.
Chociaż GCP nie oferuje bezpośrednio usługi federacyjnego uczenia maszynowego, wspiera rozwój i wdrażanie modeli 
federacyjnych za pomocą TensorFlow i TensorFlow Federated (TFF). Dzięki tym narzędziom można implementować federacyjne 
uczenie maszynowe w ekosystemie Google Cloud.
Potencjalne zastosowania federacyjnego uczenia maszynowego obejmują:
Zwiększenie prywatności w rekomendacjach personalizowanych: Uczenie modeli na urządzeniach użytkowników bez przesyłania 
danych, co zapewnia ochronę prywatności.
Poprawa modeli na podstawie interakcji użytkowników, bez potrzeby udostępniania danych: Modele mogą być doskonalone na 
podstawie danych, które pozostają lokalnie na urządzeniach użytkowników, a nie są przesyłane do centralnych serwerów.
Wspólne uczenie się w organizacjach bez dzielenia się wrażliwymi danymi: Firmy i organizacje mogą współpracować w zakresie 
modelowania, dzieląc się tylko wynikami modelu, nie udostępniając przy tym wrażliwych danych.
Federacyjne uczenie maszynowe ma szerokie zastosowanie w branżach wymagających wysokiego poziomu ochrony danych osobowych, 
takich jak zdrowie, finanse czy sektor mobilny.

647) Jakie są najlepsze praktyki wdrażania modeli uczenia maszynowego na dużą skalę w GCP?
Wdrażanie modeli uczenia maszynowego na dużą skalę w GCP obejmuje kilka najlepszych praktyk:
a) Wykorzystaj AI Platform Predictions do skalowalnego, zarządzanego wdrażania modeli z obsługą wersjonowania i testów A/B.
b) Zaimplementuj pipeline'y ciągłej integracji i dostarczania (CI/CD) dla modeli ML, używając Cloud Build 
i AI Platform Pipelines, aby zautomatyzować procesy trenowania, oceny i wdrażania.
c) Monitoruj wydajność modelu w produkcji, korzystając z narzędzi monitorowania AI Platform, oraz wprowadzaj pętle 
sprzężenia zwrotnego w celu ciągłego doskonalenia.
d) Optymalizuj przydział zasobów i zarządzaj kosztami, wybierając odpowiednie typy maszyn i wykorzystując preempcję do 
efektywnego kosztowo trenowania.
e) Zapewnij bezpieczeństwo modelu i zgodność, korzystając z funkcji bezpieczeństwa GCP, w tym ról IAM, 
VPC Service Controls oraz szyfrowania danych w spoczynku i w trakcie transmisji.

648) Czym jest Google Kubernetes Engine (GKE) i jak upraszcza operacje Kubernetes?
Google Kubernetes Engine (GKE) to zarządzane, gotowe do produkcji środowisko do wdrażania aplikacji kontenerowych za 
pomocą Kubernetes na Google Cloud. Upraszcza operacje Kubernetes poprzez automatyzację zadań, takich jak provisionowanie, 
skalowanie i aktualizowanie klastra Kubernetes. GKE oferuje zintegrowane logowanie i monitorowanie, płynne połączenie z 
usługami Google Cloud oraz zaawansowane funkcje sieciowe i bezpieczeństwa, co ułatwia wdrażanie i zarządzanie aplikacjami 
na dużą skalę.

649) W jaki sposób GCP wspiera aplikacje kontenerowe poza Kubernetes?
Poza Kubernetes, GCP wspiera aplikacje kontenerowe za pomocą usług takich jak Cloud Run i App Engine flexible environment. 
Cloud Run to zarządzana platforma, która automatycznie skaluje kontenery bezstanowe w odpowiedzi na żądania lub zdarzenia, 
koncentrując się na łatwości użycia i wydajności. App Engine flexible environment umożliwia wdrażanie kontenerowych 
aplikacji internetowych i interfejsów API, oferując automatyczne skalowanie, wersjonowanie i funkcje bezpieczeństwa. 
Te usługi dają programistom wybór, jak uruchamiać kontenery, w zależności od ich potrzeb dotyczących elastyczności, 
skalowalności i zarządzania.

650) Wyjaśnij pojęcie Pod w Kubernetes?
Pod w Kubernetes to najmniejszy i najbardziej podstawowy obiekt, który można wdrożyć. Reprezentuje jedną instancję 
działającego procesu w klastrze. Pody zawierają jeden lub więcej kontenerów, które dzielą zasoby takie jak pamięć masową, 
sieć oraz specyfikację dotyczącą uruchamiania kontenerów. Kontenery w tym samym Podzie zawsze są planowane razem na tej 
samej maszynie hosta i dzielą ten sam adres IP oraz przestrzeń portów, co pozwala na bardziej efektywną komunikację. 
Pody są uważane za efemeryczne i jednorazowe byty, które mogą być tworzone, niszczone i zastępowane dynamicznie.

651) Jak zarządzać aplikacjami stanowymi w Kubernetes?
Zarządzanie aplikacjami stanowymi w Kubernetes polega na wykorzystaniu StatefulSets, Persistent Volumes (PVs) oraz 
Persistent Volume Claims (PVCs). StatefulSets zapewniają mechanizm do wdrażania i skalowania zestawów Podów, zachowując 
jednocześnie trwałą tożsamość dla każdego Poda. Zarządzają one wdrażaniem i skalowaniem zestawu Podów oraz gwarantują 
porządek i unikalność tych Podów. PVs i PVCs oferują sposób na przechowywanie danych w sposób trwały, niezależnie od 
cyklu życia Poda, zapewniając, że dane są zachowywane podczas restartów i ponownego przydzielania Podów.

652) Jaką rolę pełnią usługi (Services) w Kubernetes?
Usługi (Services) w Kubernetes zapewniają stabilny, abstrakcyjny sposób eksponowania zestawu Podów jako usługi sieciowej. 
Dzięki szybkiemu tworzeniu i niszczeniu Podów, Usługa zapewnia, że ruch sieciowy może być kierowany do aktualnych Podów 
aplikacji, korzystając z jednego, stałego adresu IP lub nazwy DNS. Usługi wybierają Pody na podstawie etykiet i 
selektorów oraz zarządzają równoważeniem obciążenia, oferując jeden punkt dostępu do Podów, co jest kluczowe dla skalowania 
i zapewnienia wysokiej dostępności aplikacji.

653) Opisz, jak GKE integruje się z innymi usługami GCP, tworząc kompleksowe rozwiązanie chmurowe.
GKE integruje się bezproblemowo z szeregiem usług GCP, tworząc kompleksowe rozwiązanie chmurowe. Obejmuje to Cloud Storage 
do przechowywania danych trwałych, Cloud SQL do zarządzanych usług bazodanowych, Cloud Pub/Sub do komunikacji opartej na 
zdarzeniach oraz Cloud Functions do obliczeń serverless. Integracja z Identity and Access Management (IAM) zapewnia 
bezpieczną kontrolę dostępu, podczas gdy Stackdriver oferuje logowanie i monitorowanie. Ten ekosystem umożliwia rozwój 
wysoce skalowalnych, bezpiecznych i odpornych aplikacji, które wykorzystują moc różnych usług GCP.

654) Jak zapewnić wysoką dostępność i odzyskiwanie po awarii dla klastrów Kubernetes w GKE?
Zapewnienie wysokiej dostępności i odzyskiwania po awarii w GKE polega na wdrażaniu klastrów w wielu strefach lub 
regionach (klastry multi-zonalne lub regionalne), wykorzystaniu automatycznych replik i kopii zapasowych oraz wdrożeniu 
proaktywnego monitorowania i powiadamiania. Klastry regionalne zapewniają redundancję i failover, uruchamiając węzły w 
wielu strefach w obrębie regionu. Dane trwałe należy zabezpieczać kopiami zapasowymi przy użyciu Cloud Storage, a stan 
aplikacji może być replikowany między regionami. Stackdriver może monitorować stan klastra i wyzwalać powiadomienia lub 
automatyczne procesy odzyskiwania w przypadku awarii.

655) Czym są Kubernetes Ingress i Network Policies, i jak są używane?
Kubernetes Ingress to obiekt API, który zarządza zewnętrznym dostępem do usług w klastrze, zazwyczaj poprzez HTTP. 
Ingress może zapewniać równoważenie obciążenia, zakończenie SSL oraz wirtualne hostowanie oparte na nazwach. 
Network Policies to zasób Kubernetes, który kontroluje przepływ ruchu między Podami i/lub punktami końcowymi sieci. 
Określają one, w jaki sposób grupy Podów mogą komunikować się ze sobą oraz z innymi punktami końcowymi sieci, 
umożliwiając wdrażanie bezpiecznych polityk sieciowych i segmentacji w obrębie klastra.

656) Wyjaśnij znaczenie bezpieczeństwa kontenerów w GCP i jak jest ono osiągane?
Bezpieczeństwo kontenerów jest kluczowe w GCP, aby chronić aplikacje przed zagrożeniami i lukami bezpieczeństwa. 
Osiąga się je na różne sposoby, w tym przez zapewnienie bezpiecznych obrazów kontenerów poprzez skanowanie pod kątem luk 
za pomocą Container Analysis i Artifact Registry, wdrażanie kontroli dostępu na zasadzie najmniejszych uprawnień przy 
użyciu IAM, izolowanie obciążeń roboczych za pomocą przestrzeni nazw GKE i Network Policies, oraz szyfrowanie danych w 
spoczynku i w trakcie transmisji. GCP oferuje również narzędzia takie jak Security Command Center i Cloud Armor, 
które monitorują i chronią przed zagrożeniami.

657) Jak zautomatyzować pipeline'y CI/CD dla aplikacji Kubernetes w GCP?
Automatyzację pipeline'ów CI/CD dla aplikacji Kubernetes w GCP można osiągnąć za pomocą Cloud Build, usługi, 
która wykonuje procesy budowania na infrastrukturze Google Cloud. Cloud Build może być zintegrowany z repozytoriami kodu 
(takimi jak Cloud Source Repositories, GitHub lub Bitbucket), aby automatycznie uruchamiać procesy budowania po dokonaniu 
commitów w kodzie. Może tworzyć obrazy kontenerów, uruchamiać testy i automatycznie wdrażać aplikacje na GKE lub innych 
usługach. Integracja z Spinnaker lub Tekton może dodatkowo usprawnić automatyzację w przypadku bardziej złożonych 
strategii wdrożeniowych, zapewniając płynne, zautomatyzowane przepływy pracy od commitów kodu po wdrożenie.

658) Czym jest Apigee i dlaczego jest ważne dla zarządzania API w GCP?
Apigee to platforma oferowana przez Google Cloud do zarządzania, rozwijania i zabezpieczania API. Umożliwia organizacjom 
wystawianie swoich usług zaplecza jako API w sposób bezpieczny i skalowalny. Apigee jest ważne dla zarządzania API, 
ponieważ oferuje funkcje takie jak analityka API, limitowanie liczby żądań, zarządzanie OAuth i kluczami, oraz możliwości 
transformacji, co pozwala firmom na bezpieczne udostępnianie danych i usług w różnych aplikacjach i urządzeniach.

659) Czym różni się Apigee Edge od Apigee X?
Apigee Edge to starsza platforma do zarządzania API, która oferuje narzędzia do bezpieczeństwa API, zarządzania, analityki 
i operacji. Może być wdrażana lokalnie lub jako usługa zarządzana. Apigee X, z kolei, to najnowsza wersja platformy do 
zarządzania API Google Cloud, oferująca ulepszone funkcje, takie jak bezpieczeństwo oparte na sztucznej inteligencji, 
globalna sieć oraz zintegrowane usługi Google Cloud. Apigee X jest zaprojektowane jako natywne rozwiązanie chmurowe, 
oferujące bardziej skalowalną i bezpieczną architekturę, która wykorzystuje infrastrukturę Google Cloud.

660) Czy możesz wyjaśnić rolę proxy API w Apigee i jak one działają?
Proxy API w Apigee działają jako pośrednicy między aplikacjami klienckimi a usługami zaplecza. Zapewniają one warstwę 
abstrakcji, która umożliwia deweloperom modyfikowanie zachowania API bez konieczności zmiany kodu usługi zaplecza. 
Proxy API mogą obsługiwać zadania takie jak uwierzytelnianie, limitowanie liczby żądań, transformację żądań i odpowiedzi 
oraz zarządzanie wersjami API. Działają one poprzez przechwytywanie żądań API, stosowanie skonfigurowanych polityk, 
a następnie przekazywanie żądań do odpowiedniej usługi zaplecza.

661) Czym jest wsparcie dla OAuth2.0 w Apigee i dlaczego jest to ważne?
Wsparcie dla OAuth2.0 w Apigee umożliwia platformie zabezpieczanie API za pomocą protokołu OAuth 2.0, który jest 
standardem autoryzacji. Apigee pozwala na konfigurację OAuth2.0 jako serwera autoryzacji do zarządzania tokenami dostępu, 
poświadczeniami klientów i kodami autoryzacyjnymi. Jest to ważne dla zabezpieczania API, ponieważ zapewnia, że tylko 
autoryzowani klienci mogą uzyskać dostęp do wrażliwych danych i usług, oferując solidny mechanizm zarządzania i weryfikacji 
praw dostępu.

662) Jak zarządzać wersjonowaniem API w Apigee?
Wersjonowanie API w Apigee można realizować poprzez tworzenie nowych proxy API dla każdej wersji, co pozwala na 
współistnienie wielu wersji API. Deweloperzy mogą kierować żądania do odpowiedniej wersji na podstawie ścieżki żądania, 
parametrów zapytania lub nagłówków. Apigee umożliwia również implementację niestandardowych polityk do zarządzania 
przejściem między różnymi wersjami API, zapewniając płynny rozwój API bez łamania istniejących aplikacji klienckich.

663) Opisz, jak Apigee umożliwia monitorowanie i analizę użycia API.
Apigee oferuje kompleksowe funkcje monitorowania i analityki, które umożliwiają organizacjom śledzenie wydajności API, 
wzorców użycia i wskaźników błędów w czasie rzeczywistym. Zbiera dane na temat wywołań API, czasów odpowiedzi, lokalizacji 
klientów oraz typów urządzeń, które uzyskują dostęp do API. Te informacje mogą być wykorzystywane do generowania 
szczegółowych raportów i pulpitów nawigacyjnych, oferujących wgląd w sposób używania API oraz w obszary, które mogą zostać 
zoptymalizowane w celu poprawy wydajności i doświadczeń użytkowników.

664) Czym jest limitowanie liczby żądań API w Apigee i jak można je skonfigurować?
Limitowanie liczby żądań API w Apigee to funkcja, która pozwala deweloperom kontrolować liczbę żądań, które klient może 
wysłać do API w określonym czasie. Jest to stosowane w celu ochrony usług zaplecza przed przeciążeniem oraz zapewnienia 
sprawiedliwego użytkowania wśród konsumentów. Limitowanie liczby żądań można skonfigurować za pomocą polityk w Apigee, 
gdzie deweloperzy mogą określić kryteria limitu, takie jak liczba dozwolonych żądań na minutę, i zastosować je do 
poszczególnych API lub produktów API.

665) Jak Apigee ułatwia bezpieczeństwo API?
Apigee wspiera bezpieczeństwo API poprzez szereg mechanizmów, w tym OAuth2.0 i walidację kluczy API do uwierzytelniania, 
SSL/TLS do szyfrowania danych w trakcie transmisji oraz ochronę przed zagrożeniami związanymi z JSON i XML w celu obrony 
przed atakami typu injection. Apigee wspiera również polityki dotyczące kontroli dostępu, bezpieczeństwa opartego na 
treści oraz wykrywania anomalii. Te funkcje pomagają zapewnić, że tylko autoryzowani użytkownicy mają dostęp do API oraz 
że dane są chronione zarówno w trakcie transmisji, jak i w spoczynku.

666) Wyjaśnij znaczenie produktów API w Apigee (API products)?
Produkty API w Apigee to sposób na połączenie jednego lub więcej API razem oraz określenie limitów dostępu i polityk 
kwotowych. Umożliwiają one dostawcom API pakowanie API w zależności od potrzeb konsumentów, modeli biznesowych lub 
wzorców użycia. Produkty API ułatwiają monetyzację API, upraszczają dostęp deweloperów poprzez zarządzanie kluczami oraz 
pozwalają na precyzyjną kontrolę nad tym, kto może uzyskać dostęp do konkretnych API i jak często.

667) Jak deweloperzy mogą zarządzać etapami cyklu życia API w Apigee?
Deweloperzy mogą zarządzać etapami cyklu życia API w Apigee, wykorzystując możliwości platformy do tworzenia, testowania, 
wdrażania, wersjonowania i wycofywania API. Apigee oferuje środowiska do rozwoju, testowania i produkcji, umożliwiając 
ciągłą integrację i dostarczanie (CI/CD). Funkcje zarządzania wersjami umożliwiają płynne przejścia między różnymi 
wersjami API, a funkcje analityki platformy pomagają podejmować świadome decyzje o tym, kiedy zdeprecjonować lub wycofać 
wersje API na podstawie wzorców użycia i metryk wydajności.

668) Jaką rolę pełni Google Cloud Build w praktykach DevOps w GCP?
Google Cloud Build to w pełni zarządzana platforma ciągłej integracji i dostarczania (CI/CD), która automatyzuje procesy 
budowania, testowania i wdrażania oprogramowania. W ramach praktyk DevOps w GCP odgrywa kluczową rolę, umożliwiając 
deweloperom tworzenie zautomatyzowanych przepływów pracy, które kompilują kod źródłowy, uruchamiają testy i wdrażają 
aplikacje na Google Cloud lub w innym środowisku. Obsługuje szeroką gamę języków programowania i integruje się z usługami 
Google Cloud oraz GitHub, co czyni go wszechstronnym narzędziem do pipeline'ów DevOps.

669) Jak GCP implementuje Infrastructure as Code (IaC) i jakie narzędzia oferuje w tym zakresie?
GCP implementuje Infrastructure as Code (IaC) za pomocą Google Cloud Deployment Manager i Terraform. Narzędzia te umożliwiają 
deweloperom i zespołom operacyjnym automatyzację provisioningu zasobów Google Cloud za pomocą plików konfiguracyjnych. 
Deployment Manager to natywna usługa IaC w GCP, umożliwiająca zarządzanie zasobami chmurowymi za pomocą szablonów i 
plików konfiguracyjnych. Terraform, narzędzie open-source, oferuje deklaratywny język konfiguracyjny do modelowania i 
zarządzania infrastrukturą GCP oraz innych usług chmurowych. Oba narzędzia umożliwiają wersjonowanie, powtarzalność oraz 
kodowanie infrastruktury, co stanowi kluczowe zasady w praktykach DevOps.

670) Wyjaśnij znaczenie Google Kubernetes Engine (GKE) w praktykach DevOps.
Google Kubernetes Engine (GKE) jest kluczowy dla praktyk DevOps, ponieważ zapewnia zarządzane środowisko do wdrażania, 
zarządzania i skalowania aplikacji kontenerowych przy użyciu Kubernetes. GKE wspiera DevOps poprzez automatyzację wielu 
zadań operacyjnych, takich jak wdrożenia, skalowanie i samonaprawianie aplikacji. Integruje się z narzędziami CI/CD 
w Google Cloud, co umożliwia płynne przepływy pracy związane z rozwojem i wdrażaniem aplikacji, wspiera architektury 
mikroserwisów oraz promuje zasady DevOps takie jak automatyzacja, ciągłe dostarczanie i skalowalność.

671) Czym są praktyki SRE i jak integrują się z DevOps w GCP?
Praktyki Site Reliability Engineering (SRE) to zestaw zasad i praktyk łączących inżynierię oprogramowania i inżynierię 
systemów w celu budowy i obsługi systemów oprogramowania na dużą skalę, wysoce niezawodnych i wydajnych. W GCP praktyki 
SRE integrują się z DevOps poprzez wspólne cele automatyzacji, niezawodności i szybkiej iteracji. SRE koncentruje się na 
tworzeniu skalowalnych i niezawodnych systemów oprogramowania, co uzupełnia nacisk DevOps na szybki rozwój i wdrażanie. 
SRE wprowadza takie koncepcje jak budżety błędów (error budgets) i cele poziomów usług (SLOs), aby zrównoważyć potrzebę 
niezawodności z tempem innowacji.

672) Jak Google Cloud Operations (dawniej Stackdriver) wspiera praktyki DevOps i SRE?
Google Cloud Operations suite oferuje funkcje monitorowania, rejestrowania i diagnostyki, które są niezbędne zarówno w 
praktykach DevOps, jak i SRE. Zapewnia wgląd w czasie rzeczywistym w zdrowie, wydajność i dostępność aplikacji oraz 
infrastruktury, wspierając proaktywne zarządzanie incydentami i efektywność operacyjną. W DevOps umożliwia ciągłe 
monitorowanie przez cały cykl życia oprogramowania, ułatwiając szybkie sprzężenie zwrotne i poprawki. W SRE wspiera pomiar 
i zarządzanie wskaźnikami poziomu usług (SLI) oraz celami poziomu usług (SLO), które są kluczowe dla utrzymania 
niezawodności usług.

673) Opisz koncepcję budżetów błędów w SRE. Jak są one wykorzystywane w GCP?
Budżety błędów definiują akceptowalny próg niestabilności systemu, balansując potrzebę stabilności z tempem innowacji. 
W praktykach SRE w GCP, budżety błędów służą do określenia akceptowalnego poziomu ryzyka w postaci przestojów lub błędów, 
umożliwiając zespołom podejmowanie świadomych decyzji dotyczących wdrażania nowych funkcji versus skupianie się na 
poprawie niezawodności. Jeśli budżet błędów nie zostanie wyczerpany, zespoły mogą wprowadzać nowe wydania. Jeśli jest 
wyczerpany, wysiłki koncentrują się na poprawie stabilności. Narzędzia GCP, takie jak Cloud Operations, pomagają w 
śledzeniu i zarządzaniu budżetami błędów.

674) Jaką rolę odgrywa automatyzacja w praktykach DevOps i SRE w GCP?
Automatyzacja jest fundamentem zarówno praktyk DevOps, jak i SRE w GCP, mając na celu redukcję manualnego wysiłku, 
poprawę efektywności oraz zwiększenie niezawodności. Obejmuje różne aspekty, w tym provisionowanie infrastruktury, 
wdrażanie aplikacji, testowanie, skalowanie i reagowanie na incydenty. Automatyzacja umożliwia spójne i bezbłędne operacje, 
szybkie skalowanie oraz zdolność do szybkiego odzyskiwania po awariach, wspierając tym samym ciągłą integrację 
i dostarczanie (CI/CD) oraz zapewniając wysoką dostępność i niezawodność usług.

675) Jak Service Level Objectives (SLOs) kierują praktykami DevOps i SRE w GCP?
Service Level Objectives (SLOs) to konkretne, mierzalne cele związane z niezawodnością i wydajnością usług. W praktykach 
DevOps i SRE w GCP, SLOs kierują procesem podejmowania decyzji i ustalania priorytetów, definiując pożądany poziom 
niezawodności usług. Pomagają one w dostosowaniu działań rozwoju i operacji do celów biznesowych, koncentrując się na 
satysfakcji klientów. SLOs określają również sposób alokacji zasobów, czy to na nowe funkcje, konserwację, czy poprawę 
niezawodności, w zależności od bieżącej wydajności w stosunku do tych celów.

676) Jak GCP wspiera ciągłe doskonalenie w praktykach DevOps i SRE?
GCP wspiera ciągłe doskonalenie w praktykach DevOps i SRE poprzez zintegrowane narzędzia monitorowania, rejestrowania i 
feedbacku, takie jak Cloud Operations suite, oraz praktykę blameless postmortems (bez winy po incydencie). Narzędzia i 
praktyki te pomagają zespołom uczyć się na błędach i incydentach operacyjnych bez przypisywania winy, promując kulturę 
przejrzystości i ciągłego uczenia się. Narzędzia CI/CD w GCP wspierają ciągły rozwój, testowanie i wdrażanie, umożliwiając 
zespołom iteracyjne ulepszanie aplikacji i infrastruktury na podstawie danych z rzeczywistego użytkowania i wydajności.

677) Jakie znaczenie ma architektura mikroserwisów w praktykach DevOps w GCP?
Architektura mikroserwisów odgrywa istotną rolę w praktykach DevOps w GCP, umożliwiając organizacjom rozwój, wdrażanie i 
skalowanie aplikacji jako zbioru luźno powiązanych usług. Takie podejście zwiększa zwinność, pozwalając zespołom na 
niezależne wdrażanie aktualizacji do poszczególnych usług, co jest kluczową zasadą DevOps. GCP wspiera architekturę 
mikroserwisów poprzez usługi takie jak Google Kubernetes Engine (GKE), Cloud Run i App Engine, zapewniając skalowalną i 
niezawodną infrastrukturę do wdrażania i zarządzania mikroserwisami oraz wspierając praktyki DevOps, takie jak ciągła 
integracja i dostarczanie.

678) Jakie są kluczowe kwestie do rozważenia przed rozpoczęciem migracji do GCP?
Przed rozpoczęciem migracji do GCP warto rozważyć:
a) Ocena obecnego środowiska: Zrozumienie obecnej infrastruktury, aplikacji i danych w celu określenia wykonalności 
migracji i strategii.
b) Analiza kosztów: Oszacowanie kosztów związanych z migracją i utrzymaniem infrastruktury w GCP, aby zapewnić zgodność 
z budżetem.
c) Bezpieczeństwo i zgodność: Ocena funkcji bezpieczeństwa i certyfikatów zgodności w GCP, aby upewnić się, że spełniają 
one wymagania organizacji.
d) Mapowanie zależności: Identyfikacja zależności między aplikacjami i usługami w celu zaplanowania kolejności migracji 
i strategii.
e) Wiedza i szkolenia: Ocena znajomości GCP przez zespół oraz identyfikacja potrzeb szkoleniowych, aby zapewnić płynne 
przejście.

679) Jakie narzędzia oferuje GCP do migracji maszyn wirtualnych i jak działają?
Do migracji maszyn wirtualnych GCP oferuje narzędzia takie jak Migrate for Compute Engine (dawniej Velostrata), które 
umożliwiają strumieniowanie danych z środowiska źródłowego do GCP, co pozwala na szybkie migracje z minimalnym czasem 
przestoju. Narzędzie to wspiera migracje typu lift-and-shift, umożliwiając przeniesienie maszyn wirtualnych do GCP bez 
konieczności modyfikacji.

680) Jak wygląda proces migracji danych do GCP i jakie narzędzia są zaangażowane?
Migracja danych do GCP może obejmować różne narzędzia, w zależności od rodzaju i rozmiaru danych:
a) Cloud Storage Transfer Service: Do transferu danych online z innych usług przechowywania w chmurze lub lokalizacji \
HTTP/HTTPS do Google Cloud Storage.
b) Transfer Appliance: Urządzenie sprzętowe do offline'owego transferu danych przy przenoszeniu dużych wolumenów danych.
c) BigQuery Data Transfer Service: Do migracji danych do BigQuery z aplikacji SaaS lub innych usług Google.
Proces zwykle polega na wyborze odpowiedniego narzędzia, skonfigurowaniu źródła i miejsca docelowego oraz wykonaniu 
transferu przy zapewnieniu integralności i bezpieczeństwa danych.

681) Jakie są najlepsze praktyki zapewniające płynne przejście migracji do GCP?
Najlepsze praktyki obejmują:
a) Rozpocznij od Pilotażu: Zacznij od migracji systemu, który nie jest krytyczny, aby przetestować proces.
b) Użyj wzorca Stranglera: Stopniowo zastępuj części aplikacji nowymi funkcjonalnościami w GCP.
c) Wdrażaj praktyki DevOps: Korzystaj z CI/CD pipeline'ów w celu automatyzacji i zwiększenia wydajności.
d) Optymalizuj na bieżąco: Wykorzystaj okazję do optymalizacji aplikacji pod kątem funkcji natywnych dla chmury.
e) Skup się na bezpieczeństwie: Wdrażaj najlepsze praktyki bezpieczeństwa GCP od samego początku.
f) Szkolenie zespołu: Zapewnij, że Twój zespół jest przeszkolony i zaznajomiony z usługami GCP oraz najlepszymi praktykami.

682) Jaką rolę odgrywa Organizacja Usług Profesjonalnych Google (PSO) i ekosystem partnerów w migracjach?
PSO Google i jego ekosystem partnerów odgrywają kluczową rolę w migracjach, zapewniając wiedzę ekspercką, najlepsze 
praktyki oraz wsparcie, aby zapewnić pomyślną migrację. Mogą oferować wyspecjalizowane usługi w zakresie oceny, planowania, 
realizacji i optymalizacji migracji do GCP, wykorzystując swoją głęboką wiedzę o technologiach Google Cloud oraz 
doświadczenie w złożonych scenariuszach migracyjnych.

683) Jak zarządzać zależnościami aplikacji podczas migracji?
Zarządzanie zależnościami aplikacji polega na:
a) Mapowaniu zależności: Dokładne mapowanie wszystkich zależności aplikacji przed migracją.
b) Migracji usług zależnych jako pierwszych: Upewnienie się, że usługi, od których zależą inne, są migracyjne jako 
pierwsze lub pozostają dostępne podczas migracji.
c) Używaniu Interconnect i Peering: Wykorzystanie usług Interconnect i Peering Google Cloud, aby utrzymać łączność z 
środowiskami on-premises w razie potrzeby podczas migracji.

684) Jakie strategie można zastosować do migracji baz danych do GCP?
Strategie obejmują:
a) Lift-and-Shift: Przenoszenie istniejących baz danych do GCP bez modyfikacji, z wykorzystaniem usług takich jak Cloud SQL.
b) Zrzut bazy danych i przywracanie: Eksportowanie danych z istniejącej bazy danych i importowanie ich do GCP.
c) Zarządzane usługi migracji baz danych: Korzystanie z Cloud Database Migration Service w celu bardziej 
zautomatyzowanego i zarządzanego procesu migracji.

685) Jak zapewnić ciągłość działalności podczas procesu migracji?
Zapewnienie ciągłości działalności poprzez:
a) Dokładne planowanie: Stworzenie szczegółowego planu migracji, który obejmuje procedury przywracania w razie problemów.
b) Testowanie: Przeprowadzanie obszernych testów na każdym etapie migracji.
c) Minimalizacja przestojów: Wykorzystanie narzędzi migracyjnych, które wspierają strategie minimalizacji przestojów, 
takich jak migracja inkrementalna.
d) Monitorowanie: Ciągłe monitorowanie procesu migracji oraz wydajności aplikacji w trakcie i po migracji.

686) Jak firmy mogą optymalizować koszty chmurowe po migracji do GCP?
Firmy mogą optymalizować koszty chmurowe poprzez:
a) Dobór odpowiednich zasobów (Right-Sizing): Dostosowywanie rozmiaru instancji VM i baz danych do rzeczywistego 
wykorzystania.
b) Wykorzystanie zniżek za zobowiązania (Committed Use Discounts): Zobowiązanie do określonego zużycia zasobów w zamian 
za rabaty.
c) Monitorowanie i zarządzanie zasobami: Korzystanie z narzędzi takich jak Cloud Billing Reports i Cost Management do 
monitorowania i optymalizacji wykorzystania zasobów.
d) Automatyzacja kontroli kosztów: Wdrażanie polityk automatycznego wyłączania lub skalowania zasobów w okresach 
niskiego wykorzystania.

687) Co to jest obliczenia bezserwerowe i jak GCP je wspiera?
Obliczenia bezserwerowe to model wykonawczy w chmurze, w którym dostawca chmurowy zarządza alokacją i dostarczaniem 
serwerów. GCP wspiera obliczenia bezserwerowe poprzez produkty takie jak Cloud Functions, App Engine, Cloud Run i 
Firestore, umożliwiając deweloperom tworzenie i wdrażanie aplikacji, które automatycznie skalują się bez konieczności 
zarządzania infrastrukturą.

688) Co to jest Google Cloud Functions i podaj przykład użycia?
Google Cloud Functions to skalowalna, sterowana zdarzeniami usługa, która umożliwia deweloperom uruchamianie kodu 
zaplecza w odpowiedzi na zdarzenia wyzwalane przez usługi GCP lub żądania HTTP.
Przykład użycia:
Cloud Functions może być wykorzystana do przetwarzania danych przechowywanych w Cloud Storage, na przykład do zmiany 
rozmiaru obrazów lub ekstrakcji metadanych. Dzięki tej usłudze, zadania takie jak przetwarzanie plików mogą być 
realizowane automatycznie i w pełni zarządzane przez Google Cloud, bez potrzeby tworzenia i zarządzania serwerami.

689) Jak Google Cloud Run różni się od Google Cloud Functions?
Google Cloud Run to zarządzana platforma, która umożliwia uruchamianie bezstanowych kontenerów, które są wywoływane za 
pomocą zapytań HTTP. W przeciwieństwie do Cloud Functions, które są zaprojektowane do wykonywania małych, 
jednofunkcyjnych zadań, Cloud Run jest odpowiedni do uruchamiania bardziej złożonych aplikacji lub mikroserwisów, 
które wymagają niestandardowych środowisk uruchomieniowych lub zależności, które nie są obsługiwane przez Cloud Functions.

690) Opisz kluczowe cechy i korzyści Google App Engine.
Google App Engine to w pełni zarządzana, bezserwerowa platforma do tworzenia i hostowania aplikacji webowych na dużą skalę.
Kluczowe cechy to automatyczne skalowanie, wersjonowanie i dzielenie ruchu, wsparcie dla wielu języków programowania 
oraz integracja z usługami GCP. Korzyści obejmują zmniejszenie obciążenia operacyjnego, szybkie wdrożenia oraz skupienie 
się na pisaniu kodu, a nie zarządzaniu infrastrukturą.

691) Jak zarządzać uwierzytelnianiem i autoryzacją w aplikacjach bezserwerowych na GCP?
Uwierzytelnianie i autoryzacja w aplikacjach bezserwerowych na GCP mogą być zarządzane za pomocą Google Cloud Identity 
and Access Management (IAM) do kontrolowania dostępu do zasobów oraz Google Firebase Authentication do zarządzania 
tożsamościami użytkowników. IAM pozwala określić, kto (które konta użytkowników lub konta usługowe (service accounts)) 
ma dostęp do jakich zasobów, podczas gdy Firebase Authentication zapewnia pełne rozwiązanie tożsamościowe, wspierające 
metody uwierzytelniania za pomocą e-maila/hasła, telefonu oraz mediów społecznościowych.

692) Jakie są czynniki do rozważenia przy wyborze między Cloud Functions, Cloud Run a App Engine?
Czynniki do rozważenia obejmują:
a) Wsparcie dla środowisk uruchomieniowych: Cloud Run i App Engine obsługują niestandardowe środowiska uruchomieniowe, 
podczas gdy Cloud Functions jest ograniczone do obsługiwanych języków.
b) Bezstanowość: Cloud Run jest idealne dla bezstanowych kontenerów, podczas gdy App Engine wspiera aplikacje, 
które utrzymują stan między zapytaniami.
c) Zdarzenia vs. zapytania HTTP: Cloud Functions doskonale sprawdza się w scenariuszach opartych na zdarzeniach, podczas 
gdy Cloud Run i App Engine są lepsze dla obciążeń opartych na HTTP/wykonywaniu aplikacji webowych.
d) Wymagania dotyczące skalowalności i wydajności: App Engine i Cloud Run są w stanie obsługiwać bardziej złożone 
scenariusze skalowania i oferują więcej opcji konfiguracji w porównaniu do Cloud Functions.

693) Jak GCP zapewnia skalowalność aplikacji bezserwerowych?
GCP zapewnia skalowalność poprzez automatyczne zarządzanie zasobami infrastrukturalnymi w zależności od wolumenu 
przychodzących zapytań. Dla Cloud Functions, Cloud Run i App Engine, GCP dynamicznie przydziela więcej zasobów w miarę 
wzrostu zapotrzebowania i automatycznie skaluje w dół, gdy zapotrzebowanie maleje, zapewniając wysoką dostępność 
aplikacji bez potrzeby interwencji manualnej.

694) Czym jest Firestore i jak wspiera architektury bezserwerowe?
Firestore to w pełni zarządzana, nierelacyjna baza danych dokumentowa, zaprojektowana do automatycznego skalowania, 
wysokiej wydajności i ułatwienia rozwoju aplikacji. Wspiera architektury bezserwerowe, oferując bazę danych w czasie 
rzeczywistym z płynna integracją z usługami bezserwerowymi, takimi jak Cloud Functions, umożliwiając deweloperom 
tworzenie dynamicznych aplikacji bez potrzeby zarządzania serwerami baz danych.

695) Omów model cenowy produktów bezserwerowych GCP.
Produkty bezserwerowe GCP zazwyczaj opierają się na modelu cenowym "płać za to, co zużywasz", w którym opłaty są naliczane 
na podstawie zasobów wykorzystywanych przez aplikację, takich jak czas obliczeniowy, zużycie pamięci oraz liczba zapytań. 
Taki model pozwala na kosztową skalowalność, ponieważ płacisz tylko za to, czego używasz, bez kosztów początkowych czy 
opłat za nieużywane zasoby.

696) Jak monitorować i debugować aplikacje bezserwerowe na GCP?
GCP oferuje narzędzia takie jak Cloud Monitoring, Cloud Logging i Error Reporting do monitorowania i debugowania aplikacji 
bezserwerowych. Cloud Monitoring umożliwia śledzenie metryk wydajności aplikacji, Cloud Logging zbiera i analizuje logi 
z aplikacji, a Error Reporting identyfikuje i raportuje błędy. Dodatkowo, Cloud Trace i Cloud Profiler mogą być 
wykorzystywane do analizy wydajności i identyfikacji wąskich gardeł w kodzie aplikacji.

697) Czym jest Anthos Google Cloud i jak wspiera środowiska hybrydowe i wielochmurowe?
Anthos to otwarta platforma Google Cloud, która umożliwia budowanie i zarządzanie nowoczesnymi aplikacjami w różnych 
środowiskach, w tym na GCP, u innych dostawców chmurowych oraz na infrastrukturze lokalnej. Wspiera środowiska hybrydowe 
i wielochmurowe, oferując spójne doświadczenie rozwoju i operacji, wykorzystując Kubernetes do orkiestracji kontenerów. 
Anthos umożliwia jednolite stosowanie polityk, bezpieczeństwa i usług w różnych środowiskach, ułatwiając mobilność i 
zarządzanie obciążeniami.

698) W jaki sposób Anthos ułatwia modernizację aplikacji w środowiskach hybrydowych?
Anthos ułatwia modernizację aplikacji, umożliwiając organizacjom używanie kontenerowych aplikacji w sposób spójny w 
różnych środowiskach. Wykorzystuje Kubernetes i inne technologie chmurowe, aby zmodernizować istniejące aplikacje i 
budować nowe, wspierając przejście na architekturę mikroserwisów. Zarządzane usługi Anthos upraszczają operacje, takie 
jak siatka usług do komunikacji mikroserwisów i monitorowania, oraz zarządzanie konfiguracją do egzekwowania polityk, 
pomagając firmom modernizować aplikacje bez uzależnienia od jednego dostawcy chmurowego.

699) Jaką rolę odgrywa Google Kubernetes Engine (GKE) w strategiach hybrydowych i wielochmurowych?
W strategiach hybrydowych i wielochmurowych, Google Kubernetes Engine (GKE) odgrywa kluczową rolę, zapewniając zarządzane 
środowisko do wdrażania, zarządzania i skalowania aplikacji kontenerowych przy użyciu infrastruktury Google. GKE rozszerza 
się o GKE On-Prem jako część platformy Anthos, umożliwiając organizacjom uruchamianie Kubernetes w ich własnych centrach 
danych oraz bezproblemowe łączenie z usługami Google Cloud. Ta integracja umożliwia spójne i jednolite doświadczenie 
zarządzania w środowiskach chmurowych i lokalnych, ułatwiając przenoszalność i zarządzanie obciążeniami w krajobrazie 
hybrydowym i wielochmurowym.

700) Czym jest Cloud Interconnect i jak wspiera konfiguracje chmurowe hybrydowe?
Cloud Interconnect to usługa Google Cloud, która zapewnia szybkie, bezpieczne i niezawodne połączenia między lokalną 
siecią a Google Cloud lub między Google Cloud a innymi dostawcami chmurowymi. Wspiera konfiguracje chmurowe hybrydowe, 
oferując różne opcje, takie jak Dedicated Interconnect do prywatnej łączności oraz Partner Interconnect do łączenia przez 
dostawcę usług. Umożliwia to bezproblemowe, klasy korporacyjne połączenie z Google Cloud, zapewniając niskolatencyjny 
dostęp do usług i zasobów chmurowych, co jest kluczowe dla operacji chmurowych hybrydowych.

701) W jaki sposób Traffic Director Google Cloud wspiera wdrożenia wielochmurowe?
Traffic Director Google Cloud to w pełni zarządzana platforma kontroli siatki usług, która zapewnia globalne równoważenie 
obciążenia dla mikroserwisów, działających zarówno na GKE, jak i na usługach opartych na maszynach wirtualnych w 
Google Cloud oraz w lokalnych środowiskach. W wdrożeniach wielochmurowych, Traffic Director umożliwia spójne zarządzanie 
ruchem, politykami bezpieczeństwa i telemetrią w różnych środowiskach, niezależnie od miejsca, w którym są wdrożone usługi. 
Pomaga zapewnić wysoką dostępność i optymalną wydajność aplikacji poprzez dynamiczne kierowanie ruchem do najlepszego 
punktu końcowego, na podstawie stanu zdrowia, geolokalizacji i innych parametrów.

702) Opisz rolę Cloud VPN w łączeniu lokalnych sieci z GCP.
Cloud VPN bezpiecznie łączy lokalną sieć z siecią Google Cloud VPC (Virtual Private Cloud) poprzez połączenie VPN oparty 
na protokole IPsec. Odgrywa kluczową rolę w konfiguracjach chmurowych hybrydowych, umożliwiając bezpieczną komunikację 
między serwerami lokalnymi a usługami chmurowymi, zapewniając, że dane w trakcie transmisji są szyfrowane i chronione. 
Cloud VPN obsługuje zarówno statyczne, jak i dynamiczne routowanie, co sprawia, że jest odpowiedni dla różnych scenariuszy 
sieciowych, stanowiąc kosztowne rozwiązanie do integracji zasobów lokalnych z GCP.

703) Jak organizacje mogą zarządzać API w środowiskach hybrydowych i wielochmurowych przy użyciu Google Cloud?
Organizacje mogą zarządzać API w środowiskach hybrydowych i wielochmurowych, korzystając z Apigee, platformy do 
zarządzania API Google Cloud. Apigee umożliwia firmom projektowanie, zabezpieczanie, analizowanie i skalowanie API, 
niezależnie od tego, gdzie API i aplikacje konsumentów są hostowane. Obsługuje wdrożenia wielochmurowe i hybrydowe, 
oferując funkcje takie jak bramy API wdrażane lokalnie lub w dowolnej chmurze, centralne zarządzanie API w różnych 
środowiskach oraz analitykę użycia i wydajności API. Umożliwia to spójną strategię API w całym cyfrowym ekosystemie 
organizacji.

704) Jakie funkcje bezpieczeństwa oferuje Google Cloud dla konfiguracji hybrydowych i wielochmurowych?
Google Cloud oferuje szereg funkcji bezpieczeństwa dla konfiguracji hybrydowych i wielochmurowych, w tym:
a) Context-Aware Access: Kontroluje dostęp do aplikacji i maszyn wirtualnych na podstawie tożsamości i kontekstu.
b) Cloud Armor: Chroni aplikacje przed atakami DDoS.
c) Binary Authorization: Zapewnia, że tylko zaufane obrazy kontenerów są wdrażane na GKE.
d) Cloud Identity and Access Management (IAM): Zarządza kontrolą dostępu i uprawnieniami.
e) Data Loss Prevention (DLP): Pomaga w odkrywaniu i ochronie wrażliwych danych w środowiskach hybrydowych i wielochmurowych.
Te funkcje zapewniają spójne i centralne zarządzanie bezpieczeństwem, niezależnie od lokalizacji zasobów czy użytkowników.

705) Jak zestaw operacyjny Google Cloud wspiera monitorowanie i logowanie w środowiskach hybrydowych i wielochmurowych?
Zestaw operacyjny Google Cloud (dawniej Stackdriver) oferuje zintegrowane narzędzia do monitorowania, logowania i 
diagnostyki, które działają zarówno w Google Cloud, jak i w lokalnych środowiskach oraz u innych dostawców chmurowych. 
Wspiera środowiska hybrydowe i wielochmurowe, oferując jedną centralną konsolę do monitorowania wydajności, dostępności 
i stanu zdrowia aplikacji i infrastruktury. Obejmuje to monitorowanie w czasie rzeczywistym, zarządzanie logami, 
śledzenie aplikacji i raportowanie błędów, umożliwiając organizacjom szybkie wykrywanie i rozwiązywanie problemów w 
całym środowisku chmurowym.

706) Jakie korzyści osiągają organizacje, wdrażając aplikacje w środowiskach wielochmurowych przy użyciu Google Cloud?
Wdrażanie aplikacji w środowiskach wielochmurowych przy użyciu Google Cloud oferuje organizacjom szereg korzyści, w tym:
a) Elastyczność i wolność: Uniknięcie uzależnienia od jednego dostawcy i wybór najlepszego dostawcy chmurowego w 
zależności od specyficznych potrzeb aplikacji lub wymagań geograficznych.
b) Zwiększona niezawodność: Poprawa dostępności aplikacji i odzyskiwania po awarii dzięki korzystaniu z wielu dostawców 
chmurowych.
c) Optymalizacja kosztów: Wykorzystanie różnych modeli cenowych i usług od wielu dostawców chmurowych w celu 
optymalizacji wydatków na chmurę.
d) Innowacyjność i zwinność: Dostęp do unikalnych usług i funkcji od różnych dostawców chmurowych, wspierających innowacje.
Google Cloud wspiera te korzyści dzięki szerokiemu zestawowi usług i narzędzi zaprojektowanych z myślą o 
interoperacyjności i łatwości zarządzania w środowiskach hybrydowych i wielochmurowych.

707) Czym jest Google Cloud IoT Core i jakie są jego główne funkcje?
Google Cloud IoT Core to w pełni zarządzana usługa, która umożliwia użytkownikom łatwe i bezpieczne łączenie, zarządzanie 
oraz pobieranie danych z milionów urządzeń rozproszonych na całym świecie. Jego główne funkcje to: bezpieczne łączenie 
urządzeń i zarządzanie nimi na dużą skalę, menedżer urządzeń do zarządzania tożsamościami urządzeń i metadanymi oraz 
płynna integracja z platformą analityki danych Google Cloud, umożliwiająca uzyskiwanie wglądu w czasie rzeczywistym.

708) Jak GCP zapewnia bezpieczne połączenie urządzeń i transmisję danych w projektach IoT?
GCP zapewnia bezpieczne połączenie urządzeń i transmisję danych w projektach IoT poprzez szereg mechanizmów. 
Używa standardowych protokołów branżowych, takich jak MQTT i HTTP, do bezpiecznej komunikacji, wspiera szyfrowanie 
end-to-end oraz umożliwia automatyczne provisioning urządzeń z bezpiecznymi poświadczeniami połączenia. 
Google Cloud IoT Core integruje się również z Cloud IAM, zapewniając precyzyjną kontrolę dostępu, oraz wykorzystuje 
Cloud Audit Logs do monitorowania i rejestrowania aktywności urządzeń.

709) Czy możesz wyjaśnić, jak Cloud IoT Core integruje się z innymi usługami GCP do analizy danych IoT?
Cloud IoT Core integruje się bezproblemowo z innymi usługami GCP, umożliwiając kompleksową analizę danych IoT. Dane 
pobrane przez IoT Core mogą być bezpośrednio przesyłane do BigQuery w celu magazynowania, do Cloud Pub/Sub w celu 
przesyłania wiadomości i przetwarzania opartego na zdarzeniach, do Cloud Dataflow do przetwarzania danych w czasie 
rzeczywistym i w partiach oraz do Cloud Machine Learning Engine do zaawansowanej analityki i aplikacji sztucznej 
inteligencji. Ta integracja umożliwia potężne rozwiązanie end-to-end do zbierania, przetwarzania, analizowania i 
wizualizowania danych IoT w czasie rzeczywistym.

710) Jakie korzyści płyną z używania Cloud Pub/Sub w rozwiązaniu IoT na GCP?
Cloud Pub/Sub przynosi korzyści rozwiązaniu IoT na GCP, zapewniając skalowalną i elastyczną usługę do przesyłania 
wiadomości i pobierania zdarzeń. Umożliwia komunikację w czasie rzeczywistym między urządzeniami a chmurą, pozwalając na 
odseparowanie usług, które mogą skalować się niezależnie. Cloud Pub/Sub obsługuje strumienie zdarzeń o dużej objętości, 
takie jak te generowane przez urządzenia IoT, i integruje się z innymi usługami GCP do dalszego przetwarzania i analizy, 
poprawiając ogólną wydajność i szybkość reakcji rozwiązań IoT.

711) Opisz, jak Google Cloud Functions mogą być używane w aplikacjach IoT.
Google Cloud Functions mogą być używane w aplikacjach IoT do budowania lekkich, opartych na zdarzeniach backendów, które 
reagują na zdarzenia z urządzeń IoT bez potrzeby zarządzania serwerami. Na przykład, Cloud Functions mogą przetwarzać 
dane w miarę ich napływania z urządzeń IoT, przeprowadzać analitykę w czasie rzeczywistym, aktualizować bazy danych, 
wysyłać powiadomienia lub wywoływać inne usługi chmurowe. To podejście bezserwerowe pozwala programistom skupić się na 
kodzie i logice biznesowej, podczas gdy GCP zajmuje się skalowaniem i zarządzaniem infrastrukturą.

712) Jak GCP wspiera przetwarzanie danych w czasie rzeczywistym w aplikacjach IoT?
GCP wspiera przetwarzanie danych w czasie rzeczywistym w aplikacjach IoT za pomocą usług takich jak Cloud Dataflow i 
Cloud Pub/Sub. Cloud Dataflow umożliwia użytkownikom rozwijanie i uruchamianie różnych wzorców przetwarzania danych, 
w tym ETL, obliczeń wsadowych oraz obliczeń ciągłych na danych strumieniowych. W połączeniu z Cloud Pub/Sub, służącym do 
przesyłania wiadomości i pobierania zdarzeń, te usługi zapewniają potężną infrastrukturę do analizowania i przetwarzania 
strumieni danych IoT w czasie rzeczywistym, umożliwiając natychmiastowe wglądy i podejmowanie działań.

713) Jaką rolę odgrywają AI i uczenie maszynowe w rozwiązaniach IoT na GCP?
AI i uczenie maszynowe odgrywają kluczową rolę w rozwiązaniach IoT na GCP, zapewniając zaawansowane możliwości analityczne 
do wyciągania wniosków z ogromnych ilości danych IoT. Dzięki usługom takim jak Cloud Machine Learning Engine i TensorFlow, 
użytkownicy mogą tworzyć i wdrażać niestandardowe modele uczenia maszynowego, które mogą prognozować trendy, optymalizować 
operacje i wykrywać anomalie. Ta integracja umożliwia tworzenie bardziej inteligentnych rozwiązań IoT, które uczą się na 
podstawie danych, poprawiają się z czasem i podejmują autonomiczne decyzje.

714) Jak BigQuery może być wykorzystywane do analityki danych IoT?
BigQuery może być wykorzystywane do analityki danych IoT jako wysoko skalowalna i bezserwerowa hurtownia danych do 
przechowywania i analizowania dużych wolumenów danych IoT. Umożliwia szybkie zapytania SQL na petabajtach danych, co 
czyni je odpowiednim narzędziem do analizowania danych historycznych z urządzeń IoT w celu identyfikacji wzorców, 
trendów i wniosków. Integracja BigQuery z innymi usługami GCP umożliwia łatwe pobieranie, analizowanie i wizualizowanie 
danych IoT, wspierając podejmowanie decyzji opartych na danych.

715) Jaka jest znaczenie Edge TPU w rozwiązaniach IoT na GCP?
Edge TPU to dedykowany układ ASIC (Application-Specific Integrated Circuit) stworzony przez Google, zaprojektowany do 
uruchamiania AI na brzegu sieci (edge). Znacząco przyspiesza wnioskowanie uczenia maszynowego na urządzeniach brzegowych, 
umożliwiając rozwiązaniom IoT przetwarzanie danych lokalnie, z niską latencją i zmniejszoną zależnością od chmury. 
Jest to szczególnie istotne w aplikacjach wymagających natychmiastowego podejmowania decyzji, obliczeń wrażliwych na 
prywatność lub pracy w środowiskach o ograniczonej łączności. Integracja Edge TPU z rozwiązaniami IoT na GCP pozwala na 
efektywne wdrożenie wniosków opartych na AI bezpośrednio na urządzeniach IoT.

716) Jak GCP ułatwia zarządzanie dużymi wdrożeniami IoT?
GCP ułatwia zarządzanie dużymi wdrożeniami IoT dzięki możliwościom zarządzania urządzeniami oferowanym przez Cloud IoT Core, 
które umożliwiają użytkownikom bezpieczną rejestrację, organizowanie, monitorowanie i zdalne zarządzanie urządzeniami 
IoT na dużą skalę. Funkcje takie jak konfiguracje urządzeń, raportowanie stanu i automatyczne provisioning urządzeń 
pomagają uprościć proces wdrażania i utrzymania urządzeń IoT. Dodatkowo, globalna infrastruktura GCP zapewnia skalowalność 
i niezawodność niezbędną do zarządzania ogromną liczbą urządzeń rozproszonych na całym świecie.

717) Jakie jest podejście Google Cloud do zarządzania kosztami i optymalizacji?
Podejście Google Cloud do zarządzania kosztami i optymalizacji polega na zapewnieniu wglądu w zużycie zasobów i koszty, 
oferowaniu modeli cenowych dostosowanych do potrzeb użytkowników (takich jak zniżki za długoterminowe użytkowanie, 
zniżki za zobowiązania długoterminowe oraz maszyny wirtualne o niskim priorytecie), oraz narzędzi takich jak Kalkulator 
Ceny Google Cloud, Raport rozliczeniowy i narzędzia do zarządzania kosztami w Google Cloud Console. Google Cloud zachęca 
do praktyk takich jak odpowiednie dobieranie zasobów, wybieranie odpowiednich modeli cenowych oraz monitorowanie i 
efektywne zarządzanie zasobami w celu optymalizacji kosztów.

718) Jak monitorować koszty GCP?
Koszty GCP można monitorować za pomocą sekcji "Rozliczenia (Billing)" w Google Cloud Console, gdzie można przeglądać 
szczegółowe raporty i analizy kosztów oraz zużycia. Funkcja Raportów Rozliczeniowych pozwala wizualizować wydatki w czasie, 
rozbijać koszty według projektu, usługi i lokalizacji oraz identyfikować trendy. Google Cloud Billing integruje się 
również z BigQuery, umożliwiając zaawansowaną analizę danych i tworzenie niestandardowych raportów.

719) Czym są zniżki za zobowiązania długoterminowe (Committed Use Discounts) w GCP i jak działają?
Zniżki za zobowiązania długoterminowe (CUDs) w GCP to model cenowy, który oferuje znaczne zniżki w zamian za zobowiązanie
się do utrzymania stałego poziomu zużycia (w zakresie CPU, pamięci i innych zasobów) przez okres jednego lub trzech lat. 
Te zniżki są stosowane automatycznie do wykorzystania zasobów obliczeniowych, które odpowiadają zobowiązaniom, co pomaga 
klientom zaoszczędzić pieniądze w porównaniu z cenami na żądanie w przypadku utrzymujących się, przewidywalnych obciążeń.

720) Czy możesz wyjaśnić rolę etykiet w zarządzaniu kosztami na GCP?
Etykiety w GCP to pary klucz-wartość, które można przypisać do zasobów w celu organizowania i śledzenia danych o kosztach 
na podstawie struktury biznesowej, takiej jak dział, projekt czy środowisko. Przez zastosowanie etykiet można filtrować 
i rozbijać dane rozliczeniowe w Google Cloud Console lub BigQuery, co ułatwia przypisywanie kosztów, egzekwowanie budżetów 
i analizowanie wzorców wydatków w różnych segmentach organizacji.

721) Czym jest Kalkulator Ceny Google Cloud i jak się go używa?
Kalkulator Ceny Google Cloud to narzędzie online, które pozwala użytkownikom oszacować koszt uruchamiania obciążeń w 
chmurze na Google Cloud. Użytkownicy mogą wybierać różne usługi, konfigurować swoje zasoby (takie jak instancje 
obliczeniowe, pamięć masowa i opcje sieciowe) i zobaczyć szacunkowy koszt na podstawie aktualnych cen. Jest to przydatne 
narzędzie do planowania i tworzenia budżetów, a także do porównywania kosztów różnych konfiguracji lub usług.

722) Jak funkcja Budżetów i Powiadomień w GCP pomaga w zarządzaniu kosztami?
Funkcja Budżetów i Powiadomień w GCP umożliwia użytkownikom ustalanie budżetów dla ich projektów lub kont rozliczeniowych 
oraz otrzymywanie powiadomień, gdy ich koszty przekroczą zdefiniowane progi. Pomaga to w proaktywnym zarządzaniu wydatkami 
w chmurze poprzez powiadamianie interesariuszy o potencjalnych przekroczeniach budżetu, co pozwala na podjęcie działań 
naprawczych, takich jak zmniejszenie zasobów, optymalizacja wykorzystania lub zbadanie niespodziewanych opłat.

723) Czym są preemptible VMs i jak mogą pomóc w obniżeniu kosztów?
Preemptible VMs to krótkoterminowe instancje obliczeniowe oferowane przez Google Cloud w znacznie niższej cenie niż 
standardowe instancje. Mogą zostać zakończone przez GCP w dowolnym momencie, jeśli zasoby są potrzebne gdzie indziej. 
Preemptible VMs są idealne do obciążeń, które mogą tolerować przerwy, takich jak przetwarzanie wsadowe, analiza danych 
czy zadania w tle, pozwalając użytkownikom na znaczne obniżenie kosztów obliczeniowych.

724) Jak rekomendacje dotyczące dopasowania rozmiaru (Rightsizing) mogą pomóc w optymalizacji kosztów na GCP?
Rekomendacje dotyczące dopasowania rozmiaru (Rightsizing Recommendations) w GCP analizują wzorce wykorzystania instancji 
maszyn wirtualnych i sugerują dostosowanie ich konfiguracji (takie jak zmiana typu lub rozmiaru maszyny) w celu optymalizacji 
ich wydajności i kosztów. Stosując te rekomendacje, użytkownicy mogą upewnić się, że nie nadmiernie przydzielają zasoby 
i nie płacą za więcej, niż potrzebują, co prowadzi do bezpośrednich oszczędności kosztów.

725) Jak działa zniżka za długotrwałe użytkowanie (Sustained Use Discount) w GCP?
Zniżki za długotrwałe użytkowanie (Sustained Use Discounts) w GCP automatycznie stosują rabaty do zasobów Google Compute 
Engine, oferując zniżkę na instancje, które są używane przez znaczną część miesiąca rozliczeniowego. Zniżki te są 
składające się z kilku poziomów, więc im dłużej korzystasz z zasobów, tym większy rabat otrzymujesz, co zachęca do 
ciągłego użytkowania zasobów. Ten model zniżek pomaga obniżyć koszty dla obciążeń o stałych potrzebach obliczeniowych, 
bez konieczności wcześniejszego zobowiązania.

726) Jaka jest rola raportów przydziału kosztów (Cost Allocation Reports) w GCP?
Raporty przydziału kosztów (Cost Allocation Reports) w GCP dostarczają szczegółowych informacji na temat wykorzystania 
zasobów oraz tego, jak koszty są rozdzielane pomiędzy różne projekty, usługi i zasoby w ramach konta Google Cloud. 
Analizując te raporty, organizacje mogą zidentyfikować główne czynniki generujące koszty, odkryć nieefektywności i lepiej 
przydzielać wydatki chmurowe zgodnie z potrzebami biznesowymi lub ograniczeniami budżetowymi. Jest to kluczowe dla 
utrzymania kontroli finansowej i optymalizacji wydatków na chmurę w całej organizacji.

727) Jaka jest rola Google Cloud Identity and Access Management (IAM) w zarządzaniu (governance)?
Google Cloud Identity and Access Management (IAM) odgrywa kluczową rolę w zarządzaniu (governance) poprzez zapewnienie 
precyzyjnej kontroli dostępu do zasobów w Google Cloud. Umożliwia administratorom definiowanie, kto (tożsamość) ma dostęp 
(role) do jakich zasobów, zapewniając, że tylko autoryzowani użytkownicy mogą wykonywać określone działania. Pomaga to w 
egzekwowaniu zasady najmniejszych uprawnień (least privilege), zmniejszając ryzyko nieautoryzowanego dostępu i naruszeń 
danych. 

728) Jak GCP wspiera zgodność z regulacjami takimi jak GDPR i HIPAA?
GCP wspiera zgodność z regulacjami takimi jak GDPR i HIPAA, oferując bezpieczną i zgodną z przepisami infrastrukturę, 
w tym umowy o przetwarzanie danych, umowy BAA (Business Associate Agreements) dla podmiotów objętych HIPAA oraz wbudowane 
funkcje zabezpieczeń chroniące dane wrażliwe. Google Cloud zapewnia również dokumentację i zasoby, które pomagają klientom 
zrozumieć ich obowiązki oraz wdrożyć niezbędne kontrole w celu osiągnięcia i utrzymania zgodności.

729) Czy możesz wyjaśnić cel hierarchii zasobów (Resource Hierarchy) w zarządzaniu (governance) GCP?
Celem hierarchii zasobów w zarządzaniu GCP jest organizowanie i zarządzanie zasobami w sposób uporządkowany. Składa się 
ona z Organizacji (Organization), Folderów (Folders), Projektów (Projects) i Zasobów (Resources), umożliwiając hierarchiczne 
zarządzanie kontrolą dostępu i politykami. Ta struktura umożliwia zarządzanie na dużą skalę, stosując polityki na różnych 
poziomach hierarchii, co ułatwia skuteczne zarządzanie i kontrolowanie zasobów oraz dostępu w całej organizacji.

730) Czym są Cloud Audit Logs w GCP i jakie mają znaczenie w zgodności i zarządzaniu (governance)?
Cloud Audit Logs w GCP to zapis działań, które miały wpływ na zasoby GCP. Są one kluczowe dla zgodności i zarządzania 
(governance), ponieważ oferują wgląd w to, kto, co, gdzie i kiedy wykonał dane działanie w środowisku Google Cloud. 
Te logi są niezbędne do audytów bezpieczeństwa i zgodności, pomagając organizacjom śledzić dostęp do zasobów oraz zmiany 
w nich, a także identyfikować potencjalne problemy związane z bezpieczeństwem lub naruszenia przepisów.

731) Jak GCP zapewnia suwerenność (sovereignty) danych i zgodność (compliance) z regulacjami regionalnymi?
GCP zapewnia suwerenność danych i zgodność z regulacjami regionalnymi, oferując globalną sieć centrów danych, 
umożliwiając klientom wybór lokalizacji, w której ich dane będą przechowywane i przetwarzane. GCP przestrzega regulacji 
regionalnych i standardów, wdrażając rygorystyczne środki ochrony danych i prywatności, zapewniając niezbędne kontrole 
dotyczące rezydencji danych oraz przechodząc regularne audyty, aby zapewnić zgodność z różnymi globalnymi i regionalnymi 
standardami.

732) Opisz, jak Security Command Center przyczynia się do zarządzania (governance) w GCP.
Security Command Center przyczynia się do zarządzania (governance) w GCP, oferując kompleksowy wgląd w stan bezpieczeństwa 
i ryzyka zasobów GCP. Pomaga w identyfikacji podatności i zagrożeń, przegląda ustawienia bezpieczeństwa oraz dostarcza 
wykonalne rekomendacje w celu poprawy bezpieczeństwa. Poprzez centralizację widoczności i kontroli nad zasobami chmurowymi, 
Security Command Center umożliwia organizacjom egzekwowanie polityk zarządzania i zapewnienie zgodności z wewnętrznymi 
oraz zewnętrznymi regulacjami.

733) Czym jest API Data Loss Prevention (DLP) w GCP i jaka jest jego rola w zarządzaniu (governance)?
API Data Loss Prevention (DLP) w GCP pomaga organizacjom w odkrywaniu, klasyfikowaniu i ochronie wrażliwych informacji w 
usługach GCP. Jego rola w zarządzaniu (governance) polega na zapobieganiu przypadkowemu lub nieautoryzowanemu ujawnieniu 
wrażliwych danych, takich jak dane osobowe (PII), poprzez dostarczanie narzędzi do redagowania, maskowania lub szyfrowania 
danych zgodnie z wymaganiami dotyczącymi zgodności i politykami zarządzania. Pomaga to utrzymać prywatność danych i 
zapewnić zgodność z przepisami.

734) Jak niestandardowe role w IAM pomagają w precyzyjnym dostosowywaniu kontroli dostępu w zarządzaniu?
Niestandardowe role w IAM pomagają w precyzyjnym dostosowywaniu kontroli dostępu w zarządzaniu, umożliwiając 
administratorom tworzenie określonych zestawów uprawnień, które odpowiadają unikalnym potrzebom organizacji. 
W przeciwieństwie do ról predefiniowanych, role niestandardowe można dostosować, aby zapewnić dokładny poziom dostępu 
dla użytkownika lub konta usługi, wdrażając zasadę najmniejszych uprawnień i zmniejszając ryzyko nadmiernych uprawnień, 
które mogłyby prowadzić do luk w bezpieczeństwie.

735) Wyjaśnij znaczenie VPC Service Controls w poprawie zarządzania GCP?
VPC Service Controls wzmacniają zarządzanie w GCP, tworząc obwód bezpieczeństwa wokół danych przechowywanych w usługach 
GCP, zapobiegając ich wyciekowi i zmniejszając ryzyko naruszeń danych. Umożliwiają organizacjom definiowanie szczegółowych 
polityk kontroli dostępu, zapewniając, że wrażliwe informacje są dostępne tylko dla upoważnionych użytkowników lub usług, 
nawet w ramach tej samej organizacji. Zapewnia to dodatkową warstwę ochrony dla krytycznych danych, egzekwując ograniczenia 
dostępu i izolując wrażliwe obciążenia robocze. VPC Service Controls są szczególnie ważne w kontekście spełniania wymagań 
zgodności, poprawy postawy bezpieczeństwa oraz zapewnienia zarządzania w środowisku chmurowym.

736) Jaka jest rola Compliance Reports Manager w GCP?
Compliance Reports Manager w GCP to narzędzie, które pomaga organizacjom uzyskać dostęp do raportów zgodności i zarządzać
nimi, takimi jak ISO, SOC czy PCI DSS, bezpośrednio w Google Cloud. Rola Compliance Reports Manager w zarządzaniu polega 
na uproszczeniu procesu wykazywania zgodności z różnymi standardami i regulacjami, poprzez zapewnienie łatwego dostępu 
do odpowiednich raportów audytowych i certyfikatów. Pomaga to organizacjom w zrozumieniu postawy zgodności GCP i upraszcza 
proces audytu w ramach ich własnych wymagań dotyczących zgodności.

737) Jak GCP zapewnia bezpieczeństwo danych i prywatność dla klientów biznesowych?
GCP zapewnia bezpieczeństwo danych i prywatność dla klientów biznesowych dzięki podejściu wielowarstwowego bezpieczeństwa, 
które obejmuje fizyczne zabezpieczenia, szyfrowanie danych w spoczynku i w tranzycie, zarządzanie tożsamością i dostępem, 
bezpieczeństwo sieci oraz regularne audyty bezpieczeństwa. GCP przestrzega globalnych regulacji dotyczących ochrony danych 
i oferuje narzędzia oraz funkcje, takie jak VPC Service Controls oraz API Data Loss Prevention (DLP), które pomagają 
przedsiębiorstwom w zarządzaniu i ochronie ich danych.

738) Jakie są kluczowe korzyści z używania GCP do aplikacji klasy enterprise?
Kluczowe korzyści z używania GCP do aplikacji klasy enterprise to skalowalność, niezawodność oraz globalna infrastruktura 
sieciowa. GCP oferuje wysoko skalowalne zasoby obliczeniowe, zarządzane usługi do tworzenia aplikacji oraz analityki 
big data. Jego globalna sieć światłowodowa zapewnia dostęp o niskim opóźnieniu na całym świecie. Zaangażowanie GCP w 
innowacje, ciągłą integrację oraz narzędzia do wdrażania wspierają również zwinny rozwój i efektywność operacyjną.

739) Jak przedsiębiorstwa mogą wykorzystać GCP do transformacji cyfrowej?
Przedsiębiorstwa mogą wykorzystać GCP do transformacji cyfrowej, korzystając z szerokiej gamy usług, w tym obliczeń w 
chmurze, uczenia maszynowego i analityki danych, aby wprowadzać innowacje i usprawniać procesy biznesowe. GCP umożliwia 
przedsiębiorstwom tworzenie i wdrażanie skalowalnych i bezpiecznych aplikacji, uzyskiwanie wglądu w dane dzięki 
rozwiązaniom big data oraz poprawę doświadczeń klientów za pomocą technologii opartych na sztucznej inteligencji. 
Dodatkowo, narzędzia do współpracy i produktywności GCP (Google Workspace) wspierają płynną komunikację i współpracę w 
ramach zespołów.

740) Jak przedsiębiorstwa mogą wykorzystać GCP do transformacji cyfrowej?
Przedsiębiorstwa mogą wykorzystać GCP do transformacji cyfrowej, korzystając z szerokiej gamy usług, w tym obliczeń w 
chmurze, uczenia maszynowego i analityki danych, aby wprowadzać innowacje i usprawniać procesy biznesowe. GCP umożliwia 
przedsiębiorstwom tworzenie i wdrażanie skalowalnych oraz bezpiecznych aplikacji, pozyskiwanie wglądów z danych za pomocą 
rozwiązań big data oraz poprawę doświadczeń klientów poprzez technologie oparte na sztucznej inteligencji. Dodatkowo, 
narzędzia do współpracy i produktywności GCP (Google Workspace) wspierają płynną komunikację i współpracę w zespołach.

741) Jaką rolę sztuczna inteligencja i uczenie maszynowe odgrywają w GCP dla przedsiębiorstw?
Sztuczna inteligencja (AI) i uczenie maszynowe (ML) odgrywają kluczową rolę w GCP dla przedsiębiorstw, oferując 
zaawansowane narzędzia i usługi, które wspierają innowacje, automatyzację procesów oraz pozyskiwanie wglądów z danych. 
Platforma AI GCP, AutoML oraz usługi AI oparte na wcześniej wytrenowanych modelach umożliwiają przedsiębiorstwom 
rozwijanie własnych modeli ML i integrowanie AI z aplikacjami, bez potrzeby głębokiej wiedzy z zakresu nauki o danych. 
Może to poprawić doświadczenia klientów, zoptymalizować operacje oraz stworzyć nowe możliwości biznesowe.

742) Jak przedsiębiorstwa mogą efektywnie zarządzać kosztami w GCP?
Przedsiębiorstwa mogą efektywnie zarządzać kosztami w GCP, korzystając z narzędzi i funkcji do zarządzania kosztami, 
takich jak Kalkulator Cen Google Cloud, Budżety i Powiadomienia oraz Raporty Zarządzania Kosztami. Wdrażanie najlepszych 
praktyk, takich jak dopasowanie zasobów do rzeczywistych potrzeb (rightsizing), korzystanie z rabatów na zarezerwowane 
zasoby w przypadku obciążeń o długoterminowej intensywności oraz monitorowanie i optymalizacja użycia zasobów za pomocą 
rekomendacji z pakietu Cloud Operations, mogą dodatkowo pomóc w kontrolowaniu kosztów. GCP oferuje także szczegółową 
dokumentację oraz najlepsze praktyki, które pomagają w optymalizacji kosztów.

743) Opisz usługi migracyjne dostępne w GCP dla obciążeń przedsiębiorstw.
GCP oferuje różne usługi migracyjne dla obciążeń przedsiębiorstw, w tym Migrate for Compute Engine, Migrate for Anthos, 
Database Migration Service oraz Transfer Appliance. Usługi te ułatwiają migrację maszyn wirtualnych, aplikacji, baz danych 
i dużych zestawów danych do GCP, minimalizując czas przestoju i zapewniając integralność danych. GCP zapewnia także zasoby 
oraz usługi profesjonalne, które wspierają przedsiębiorstwa w planowaniu i realizacji projektów migracyjnych.

744) W jaki sposób GCP wspiera zgodność i zarządzanie w przedsiębiorstwach?
GCP wspiera zgodność i zarządzanie w przedsiębiorstwach, oferując kompleksowy zestaw narzędzi i funkcji do zarządzania 
dostępem, monitorowania zasobów oraz zapewnienia ochrony danych. Funkcje takie jak Cloud Identity & Access Management, 
Cloud Audit Logs oraz Security Command Center pomagają przedsiębiorstwom w egzekwowaniu polityk, audytowaniu operacji 
oraz wykrywaniu podatności. GCP przechodzi regularne audyty i certyfikacje przeprowadzane przez strony trzecie, aby 
zapewnić zgodność z globalnymi oraz specyficznymi regulacjami branżowymi.

745) Jakie rozwiązania do odzyskiwania po awarii oferuje GCP dla przedsiębiorstw?
GCP oferuje solidne rozwiązania do odzyskiwania po awarii dla przedsiębiorstw, w tym opcje przechowywania danych w wielu 
regionach, usługi tworzenia migawkowych kopii zapasowych oraz możliwość replikacji obciążeń roboczych w różnych regionach. 
Globalna infrastruktura GCP zapewnia, że przedsiębiorstwa mogą szybko odzyskać się po awariach, rozdzielając aplikacje i 
dane na wiele lokalizacji, minimalizując czas przestoju i utratę danych. Pakiet operacyjny Google Cloud zapewnia również 
usługi monitorowania i rejestrowania, które umożliwiają szybkie wykrywanie i reagowanie na incydenty.

746) W jaki sposób GCP wspiera współpracę i produktywność w przedsiębiorstwach?
GCP wspiera współpracę i produktywność w przedsiębiorstwach poprzez Google Workspace, który obejmuje aplikacje takie 
jak Gmail, Docs, Drive, Calendar, Meet i inne. Google Workspace umożliwia zespołom współpracę w czasie rzeczywistym, 
bezpieczne udostępnianie dokumentów i skuteczną komunikację, niezależnie od lokalizacji. Dodatkowo, infrastruktura i 
usługi GCP wspierają rozwój i wdrażanie niestandardowych narzędzi do współpracy oraz integracji, co dodatkowo zwiększa 
produktywność i efektywność pracy zespołowej w przedsiębiorstwach.


























