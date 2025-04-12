Kubernetes

1) Czym jest kontener i czym różni się od maszyny wirtualnej?
Kontener to lekki, samodzielny pakiet wykonawczy, który zawiera wszystko, co potrzebne do uruchomienia programu, 
w tym kod, środowisko wykonawcze, narzędzia systemowe, biblioteki i ustawienia. Kontenery są izolowane od siebie nawzajem 
oraz od systemu gospodarza, ale dzielą jądro systemu gospodarza. Dzięki temu są bardziej wydajne, szybsze i mniej zasobożerne 
niż maszyny wirtualne (VM), które zawierają całe systemy operacyjne gościa.

2) Czym jest Docker i dlaczego jest popularny w konteneryzacji?
Docker to platforma, która umożliwia programistom tworzenie, wdrażanie i zarządzanie kontenerami. Stał się synonimem 
konteneryzacji, ponieważ upraszcza proces tworzenia, uruchamiania i zarządzania kontenerami. Docker wykorzystuje pliki 
Dockerfile do automatyzacji wdrażania aplikacji w kontenerach, co sprawia, że jest to wydajne i skalowalne rozwiązanie 
dla rozwoju oprogramowania.

3) Wyjaśnij pojęcie orkiestracji kontenerów.
Orkiestracja kontenerów to zautomatyzowane ustawienie, koordynacja i zarządzanie systemami komputerowymi, oprogramowaniem 
pośredniczącym oraz usługami. Polega na zarządzaniu cyklem życia kontenerów, szczególnie w dużych, dynamicznych środowiskach. 
Narzędzia takie jak "Kubernetes", "Docker Swarm" i "Apache Mesos" są używane do orkiestracji, oferując funkcje takie jak 
skalowanie, wdrażanie i zarządzanie aplikacjami kontenerowymi.

4) Jak działa rejestr kontenerów?
Rejestr kontenerów to system przechowywania i dostarczania treści, który przechowuje nazwane obrazy kontenerów, dostępne 
w różnych wersjach oznaczonych tagami. Użytkownicy mogą przesyłać ("push") lub pobierać ("pull") obrazy z rejestru, 
co sprawia, że jest to kluczowe narzędzie w cyklu życia kontenerów, służące do kontroli wersji i dystrybucji obrazów 
kontenerów. Popularne przykłady to "Docker Hub" i "Google Container Registry".

5) Czym są przestrzenie nazw w kontekście kontenerów?
Przestrzenie nazw to funkcja jądra systemu Linux, która dzieli zasoby jądra, aby jeden zestaw procesów widział jeden 
zestaw zasobów, podczas gdy inny zestaw procesów widzi inny zestaw zasobów. W kontekście kontenerów, przestrzenie nazw 
zapewniają warstwę izolacji, gwarantując, że kontenery widzą tylko swoje własne procesy, systemy plików, sieci i użytkowników.

6) Opisz sieć kontenerów. Jak kontenery komunikują się ze sobą?
Sieć kontenerów umożliwia kontenerom komunikowanie się ze sobą oraz ze światem zewnętrznym. Osiąga się to poprzez różne 
modele sieciowe, takie jak sieci mostkowe ("bridge networks"), sieci nakładkowe ("overlay networks") lub sieci oparte na 
hoście ("host-based networks"). Docker, na przykład, tworzy wirtualny most, umożliwiając kontenerom komunikację przez 
niego. Kontenery mogą być również konfigurowane tak, aby udostępniały określone porty do komunikacji z zewnętrznymi systemami.

7) Czym jest obraz kontenera ("container image") i jak różni się od kontenera?
Obraz kontenera to lekki, samodzielny, wykonawczy pakiet, który zawiera wszystko, co potrzebne do uruchomienia oprogramowania. 
Składa się z kodu, środowiska uruchomieniowego, narzędzi systemowych, bibliotek i ustawień. Kontener, z kolei, to instancja 
obrazu uruchomiona w czasie rzeczywistym. Gdy obraz jest uruchamiany, istnieje w pamięci i ma swój stan, podczas gdy obraz 
jest plikiem statycznym.

8) Wyjaśnij rolę Dockerfile.
Dockerfile to skrypt zawierający szereg instrukcji i poleceń używanych do tworzenia obrazu kontenera. Automatyzuje proces 
budowania obrazu Docker. Dockerfile definiuje, co dzieje się w środowisku wewnątrz kontenera. Może zawierać instrukcje do 
instalacji określonego oprogramowania, zmienne środowiskowe oraz polecenia uruchamiające.

9) Czym są mikro-serwisy i w jaki sposób kontenery im służą?
Architektura mikro-serwisów to metoda tworzenia aplikacji oprogramowania jako zestawu niezależnie wdrażalnych, modułowych usług. 
Kontenery są idealne dla mikro-serwisów ze względu na swoją lekkość, umożliwiając każdej usłudze uruchomienie w oddzielnym 
kontenerze z własnymi zależnościami, co zapewnia izolację, efektywność zasobów i skalowalność.

10) Jak monitorujesz kontenery i ich wydajność?
Monitorowanie kontenerów polega na śledzeniu metryk, takich jak zużycie CPU, pamięć, I/O, wykorzystanie sieci oraz status 
zdrowia kontenerów. Narzędzia takie jak Prometheus, Grafana, Docker Monitoring, "c-Advisor" i inne są używane do monitorowania. 
Pomagają one w zrozumieniu wydajności i statusu kontenerów, co ułatwia zarządzanie wdrożeniami kontenerów na dużą skalę.

11) Jakie są główne komponenty klastra Kubernetes?
Klaster Kubernetes ma dwa główne typy komponentów: płaszczyznę sterującą ("control plane") i węzły robocze ("worker nodes"). 
Płaszczyzna sterująca zawiera komponenty takie jak "kube-apiserver", "etcd", "kube-scheduler" i "kube-controller-manager".
Węzły robocze uruchamiają "kubelet", "kube-proxy" oraz środowisko uruchomieniowe kontenerów. Płaszczyzna sterująca 
zarządza klastrem, podczas gdy węzły robocze uruchamiają aplikacje.

12) Wyjaśnij rolę "kube-apiserver" w Kubernetes?
"Kube-apiserver" to interfejs front-end płaszczyzny sterującej Kubernetes i pełni rolę głównego interfejsu API Kubernetes. 
Przetwarza zapytania RESTful, aby zarządzać zasobami Kubernetes, takimi jak pody ("pods"), usługi ("services"), 
kontrolery replikacji ("replication controllers") i inne. 
Działa jako brama do magazynu "Etcd" i zapewnia, że stan klastra odpowiada pożądanym ustawieniom opisanym w API.

13) Czym jest "Etcd" i dlaczego jest ważne w Kubernetes?
"Etcd" to rozproszony magazyn klucz-wartość używany przez Kubernetes do przechowywania wszystkich danych klastra. Jest to 
kluczowy element Kubernetes, ponieważ przechowuje cały stan klastra, w tym informacje o węzłach, pody, konfiguracje, 
sekrety ("secrets") i inne. Jego rozproszona natura zapewnia wysoką dostępność i niezawodność.

14) Opisz funkcję "kube-scheduler"?
"Kube-scheduler" jest odpowiedzialny za przypisywanie nowych podów do węzłów. Wybiera najbardziej odpowiedni węzeł dla poda 
na podstawie kilku kryteriów, w tym wymagań dotyczących zasobów, wymagań dotyczących jakości usług, specyfikacji związanych 
z "affinity" i "anti-affinity" oraz innych ograniczeń. Scheduler zapewnia, że obciążenia są umieszczane na odpowiednich 
węzłach, aby utrzymać efektywność.

15) Jak działa "kube-controller-manager"?
"Kube-controller-manager" uruchamia różne procesy kontrolerów w tle. Te kontrolery obejmują kontroler węzłów ("node controller"), 
kontroler replikacji ("replication controller"), kontroler punktów końcowych ("endpoints controller") i inne. 
Każdy kontroler monitoruje stan klastra za pomocą "kube-apiserver" i wprowadza zmiany, aby przesunąć bieżący stan w 
kierunku pożądanego stanu.

16) Czym jest "kubelet" i jaka jest jego rola w węźle Kubernetes?
"Kubelet" to agent uruchamiany na każdym węźle w klastrze. Zapewnia, że kontenery są uruchomione w podzie. 
"Kubelet" pobiera zestaw "Pod-Specs" dostarczonych przez "apiserver" i zapewnia, że kontenery opisane w tych "Pod-Specs" 
są uruchomione i działają prawidłowo. Komunikuje się ze środowiskiem uruchomieniowym kontenerów w celu zarządzania cyklem 
życia kontenerów.

17) Wyjaśnij funkcję "kube-proxy" w Kubernetes?
"Kube-proxy" to proxy sieciowe, które działa na każdym węźle w klastrze, utrzymując zasady sieciowe, które umożliwiają 
komunikację sieciową z podami z sesji sieciowych wewnątrz lub na zewnątrz klastra. Zapewnia, że środowisko sieciowe jest 
przewidywalne i dostępne, ale także izolowane tam, gdzie to konieczne.

18) Czym jest "Pod" w Kubernetes i jak odnosi się do kontenerów?
"Pod" to najmniejsza jednostka wdrożeniowa tworzona i zarządzana przez Kubernetes. "Pod" to grupa jednego lub więcej 
kontenerów, które dzielą przestrzeń przechowywania/sieci oraz specyfikację dotyczącą sposobu uruchamiania kontenerów. 
Kontenery w Podzie dzielą adres IP i przestrzeń portów, i mogą znaleźć się nawzajem przez localhost. Mają również dostęp 
do wspólnych wolumenów ("volumes"), co umożliwia wymianę danych między nimi.

19) Opisz rolę środowiska uruchomieniowego kontenerów ("container runtime") w Kubernetes?
Środowisko uruchomieniowe kontenerów to oprogramowanie odpowiedzialne za uruchamianie kontenerów. Kubernetes obsługuje 
kilka środowisk uruchomieniowych kontenerów, takich jak "Docker", "containerd" i "CRI-O". Zapewnia ono środowisko do 
uruchamiania kontenerów, pobiera obrazy z rejestru obrazów kontenerów oraz uruchamia i zatrzymuje kontenery.

20) Czym są usługi ("Services") Kubernetes i jak działają?
Usługa Kubernetes ("Kubernetes Service") to abstrakcja, która definiuje logiczny zbiór podów oraz politykę ich dostępu, 
zwykle za pomocą adresów IP. Usługi umożliwiają aplikacjom działającym w klastrze Kubernetes komunikację ze sobą oraz ze 
światem zewnętrznym. Przypisują stały adres IP do grupy podów, zapewniając spójną komunikację i równoważenie obciążenia.

21) Jakie są wymagania wstępne do instalacji Kubernetes?
Przed instalacją Kubernetes potrzebujesz zestawu maszyn (fizycznych lub wirtualnych) do uruchomienia komponentów Kubernetes. 
Maszyny te powinny mieć kompatybilny system operacyjny Linux, środowisko uruchomieniowe kontenerów, takie jak "Docker", 
oraz łączność sieciową między nimi. Dodatkowo powinieneś mieć sposób dostępu do klastra, zazwyczaj za pomocą narzędzia 
linii poleceń "kubectl".

22) Czym jest "kubeadm" i jak jest używane podczas instalacji Kubernetes?
"Kubeadm" to narzędzie dostarczone przez Kubernetes, które pomaga w prosty i ustandaryzowany sposób skonfigurować i 
uruchomić klaster Kubernetes. Automatyzuje wiele zadań związanych z konfiguracją klastra, takich jak tworzenie niezbędnych 
certyfikatów, ustawianie płaszczyzny sterującej oraz konfigurowanie "kubelet".

23) Wyjaśnij kroki instalacji klastra Kubernetes za pomocą "kubeadm"? 
Podstawowe kroki instalacji klastra Kubernetes za pomocą "kubeadm" obejmują: 
a) Zainstalowanie "kubeadm", "kubelet" i "kubectl" na wszystkich węzłach. 
b) Inicjalizacja klastra na węźle master za pomocą polecenia "kubeadm init". 
c) Skonfigurowanie pliku kubeconfig do uzyskiwania dostępu do klastra. 
d) Zainstalowanie dodatku sieciowego dla podów w klastrze. 
e) Dołączenie węzłów roboczych do klastra za pomocą tokenu i polecenia wygenerowanego przez "kubeadm init".

24) Czym jest "Minikube" i jak różni się od pełnej instalacji Kubernetes? 
"Minikube" to narzędzie, które pozwala uruchomić Kubernetes lokalnie na komputerze osobistym. Tworzy maszynę wirtualną 
na komputerze i konfiguruje prosty klaster zawierający tylko jeden węzeł. "Minikube" jest idealny do nauki i celów 
testowych, ale różni się od pełnej instalacji Kubernetes, która zazwyczaj obejmuje wiele węzłów i działa na serwerach.

25) Jak skonfigurować wtyczki sieciowe w Kubernetes? 
Po zainicjowaniu klastra Kubernetes należy zainstalować wtyczkę sieciową, aby umożliwić komunikację między podami. 
Wykonuje się to, stosując plik konfiguracyjny "YAML" wtyczki sieciowej za pomocą polecenia "kubectl apply". 
Istnieje kilka dostępnych wtyczek sieciowych, takich jak "Calico", "Flannel", "Weave" itp., z różnymi konfiguracjami 
i instrukcjami instalacji.

26) Jakie są uwagi przy konfigurowaniu wysokiej dostępności ("HA") w Kubernetes? 
Aby zapewnić wysoką dostępność w Kubernetes, należy skonfigurować wiele węzłów master. Wymaga to skonfigurowania 
load balansera, który kieruje ruch do wszystkich aktywnych węzłów master, utworzenia klastra "Etcd" na wielu węzłach do 
przechowywania stanu oraz zapewnienia, że komponenty kontrolne są replikowane i synchronizowane między tymi węzłami.

27) Czy Kubernetes może być zainstalowany na każdej platformie chmurowej? Jak? 
Tak, Kubernetes może być zainstalowany na każdej platformie chmurowej. Większość dostawców chmurowych oferuje zarządzane 
usługi Kubernetes (takie jak "AWS EKS", "Azure AKS", "Google GKE"), które upraszczają proces instalacji. Alternatywnie, 
Kubernetes można zainstalować ręcznie za pomocą "kubeadm" lub innych narzędzi, konfigurując maszyny wirtualne lub 
instancje w infrastrukturze dostawcy chmurowego.

28) Czym jest Helm i jak ma się do instalacji Kubernetes? 
Helm to menedżer pakietów dla Kubernetes, który upraszcza instalowanie, konfigurowanie i aktualizowanie aplikacji na 
klastrach Kubernetes. Choć nie instaluje samego Kubernetes, jest używany do zarządzania aplikacjami uruchomionymi na 
klastrze Kubernetes. Helm używa formatu pakietów o nazwie "charts", które są wstępnie skonfigurowanymi zasobami Kubernetes.

29) Jak zaktualizować klaster Kubernetes? 
Aby zaktualizować klaster Kubernetes, zazwyczaj najpierw aktualizuje się komponenty kontrolne, a następnie węzły robocze. 
Narzędzia takie jak "kubeadm" mogą automatyzować część tego procesu. Ważne jest, aby stosować się do konkretnych instrukcji 
aktualizacji dla danej wersji Kubernetes, ponieważ proces może nieznacznie różnić się w zależności od wersji.

30) Jakie są typowe wyzwania napotykane podczas instalacji Kubernetes? 
Typowe wyzwania to problemy z konfiguracją sieci, problemy z kompatybilnością między Kubernetes a systemem operacyjnym 
hosta lub środowiskiem uruchomieniowym kontenerów, trudności z konfigurowaniem wysokiej dostępności, zapewnieniem 
bezpieczeństwa klastra oraz radzeniem sobie z ograniczeniami zasobów na mniejszych lub starszych urządzeniach.

31) Czym jest Deployment w Kubernetes i jak działa? 
Deployment w Kubernetes to obiekt API, który zapewnia deklaratywne aktualizacje aplikacji. Pozwala na opisanie pożądanego 
stanu aplikacji, takiego jak obrazy kontenerów do użycia oraz liczba replik podów. Kontroler Deployment ("Deployment controller")
zmienia rzeczywisty stan na pożądany w kontrolowanym tempie, zarządzając wdrażaniem zaktualizowanych instancji aplikacji i, 
jeśli to konieczne, przywracając wcześniejszą wersję deploymentu.

32) Jak zaktualizować Deployment w Kubernetes? 
Aby zaktualizować Deployment w Kubernetes, zazwyczaj aktualizuje się szablon podów Deploymentu, na przykład zmieniając 
obraz kontenera. Kubernetes następnie przeprowadza aktualizację w trybie "rolling update", stopniowo zastępując stare 
pody nowymi. Szybkość zastępowania oraz liczba podów uruchamiających starą i nową wersję mogą być kontrolowane za pomocą 
konfiguracji Deploymentu.

33) Wyjaśnij "Rolling Updates" i "Rollbacks" w Deploymentach Kubernetes?
"Rolling updates" to domyślna strategia aktualizacji Deploymentu. Podczas "Rolling update" Kubernetes stopniowo aktualizuje 
instancje podów, zastępując je nowymi. Jeśli zostanie wykryty problem, można przywrócić Deployment do poprzedniej wersji. 
"Rollbacks" można wywołać za pomocą poleceń "kubectl", a Kubernetes przywróci Deployment do poprzedniego, stabilnego stanu.

34) Jak Kubernetes obsługuje skalowanie Deploymentów? 
Kubernetes skaluje Deploymenty, dostosowując liczbę replik – liczbę instancji podów uruchomionych dla danego Deploymentu. 
Można to zrobić ręcznie, używając poleceń takich jak "kubectl scale", lub automatycznie, korzystając z 
"Horizontal Pod Autoscalers", które dostosowują liczbę replik na podstawie użycia CPU lub innych wybranych metryk.

35) Co się dzieje z Deploymentami, gdy węzeł ulega awarii? 
Gdy węzeł ulega awarii, pody na tym węźle stają się niedostępne. Kontroler Deploymentu (" Deployment controller")
zauważa to i tworzy nowe pody na innych dostępnych węzłach, aby zastąpić te, które działały na awaryjnym węźle. 
Zapewnia to, że Deployment utrzymuje pożądaną liczbę replik.

36) Jak monitorować status Deploymentu w Kubernetes? 
Status Deploymentu w Kubernetes można monitorować za pomocą poleceń "kubectl", takich jak "kubectl get deployments" do 
sprawdzenia statusu deploymentu, "kubectl describe deployment" do uzyskania szczegółowych informacji oraz 
"kubectl rollout status" do śledzenia statusu aktualizacji deploymentu.

37) Czy możesz wyjaśnić pojęcie pożądanego stanu w Deploymentach Kubernetes? 
Pożądany stan w Deploymentach Kubernetes odnosi się do stanu opisanego w konfiguracji Deploymentu. Obejmuje to takie aspekty 
jak liczba replik, obrazy kontenerów i limity zasobów. Kubernetes nieprzerwanie działa, aby zapewnić, że rzeczywisty stan 
Deploymentu odpowiada temu pożądanemu stanowi.

38) Jakie znaczenie mają "Replica-Sets" w Deploymentach Kubernetes? 
"Replica-Set" to następna generacja "ReplicationController". Zapewnia, że określona liczba replik podów jest uruchomiona 
w danym momencie. W Deploymentach, dla każdej nowej aktualizacji Deploymentu tworzony jest "ReplicaSet", który odpowiada 
za zapewnienie, że odpowiednia liczba podów dla danej wersji Deploymentu jest uruchomiona.

39) Jak są zarządzane konfiguracje specyficzne dla środowiska w Deploymentach Kubernetes? 
Konfiguracje specyficzne dla środowiska w Deploymentach Kubernetes mogą być zarządzane za pomocą "ConfigMap" i "Secrets", 
które są oddzielone od konfiguracji deploymentu. Te obiekty mogą być montowane do podów jako wolumeny lub udostępniane 
jako zmienne środowiskowe, co pozwala na różne konfiguracje dla różnych środowisk bez zmiany definicji deploymentu.

40) Czym są "Probes" i jak są używane w Deploymentach? 
"Probes" są używane w Kubernetes do określania stanu zdrowia kontenera w podzie. Istnieją dwa rodzaje "probes": 
"Liveness Probes" służą do określenia, kiedy należy zrestartować kontener, a "Readiness Probes" służą do określenia, 
kiedy kontener jest gotowy do przyjmowania ruchu. "Pprobes" pomagają zapewnić, że w deploymentach wykorzystywane są 
tylko zdrowe kontenery.

41) Czym jest "Stateful-Set" w Kubernetes i jak różni się od Deploymentu? 
"Stateful-Set" to obiekt API w Kubernetes, który służy do zarządzania aplikacjami stanowymi, czyli aplikacjami, które muszą 
utrzymywać stan lub tożsamość. W odróżnieniu od "Deployments", "Stateful-Sets" zapewniają, że identyfikatory podów i nazwy 
hostów sieciowych pozostają spójne, nawet podczas ponownego planowania. Są idealne dla aplikacji, takich jak bazy danych, 
które wymagają stabilnych, unikalnych identyfikatorów sieciowych, trwałego magazynowania oraz uporządkowanego i łagodnego 
wdrażania i skalowania.

42) Jak Kubernetes zarządza tożsamością ("Identity") podu w "Stateful-Set"? 
W "Stateful-Set" każdy pod otrzymuje unikalny, porządkowy indeks oraz stabilną tożsamość sieciową opartą na tym indeksie. 
Nazwy podów są spójne i używają wzoru: 'Stateful-Name Ordinal-Index'. Ta tożsamość utrzymuje się, nawet jeśli pod zostanie 
przeniesiony na inny węzeł.

43) Jak "Stateful-Sets" obsługują skalowanie i aktualizacje? 
"Stateful-Sets" skalują i aktualizują pody pojedynczo, w kolejności, od najniższego indeksu porządkowego do najwyższego. 
Podczas skalowania w dół, pody są usuwane w odwrotnej kolejności porządkowej. W trakcie aktualizacji "Stateful-Sets" 
obsługują "Rolling updates", gdzie aktualizacje są propagowane sekwencyjnie od pierwszego podu do ostatniego.

44) Jaką rolę odgrywają "Persistent Volumes" w "Stateful-Sets"? 
"Persistent Volumes (PVs)" są niezbędne dla "Stateful-Sets", aby zapewnić stabilne przechowywanie danych, które utrzymuje 
się po zakończeniu życia poszczególnych podów. Każdy pod w "Stateful-Set" może być powiązany z "Persistent Volume" za pomocą 
"Persistent Volume Claims (PVC)". Dzięki temu, nawet jeśli pod zostanie przeniesiony, jego dane pozostają nienaruszone 
i przypisane do tego podu.

45) Czy możesz opisać usługi "Headless" i ich zastosowanie w "Stateful-Sets"? 
Usługa "Headless" w Kubernetes to usługa, której "cluster-IP" jest ustawiony na "None". Jest często używana z 
"Stateful-Sets", aby zapewnić unikalną tożsamość sieciową dla każdego podu. Usługa "headless" zapewnia, że każdy pod otrzyma 
stabilny wpis "DNS", co jest kluczowe dla aplikacji stanowych, które polegają na stabilnych identyfikatorach sieciowych.

46) Jak "Stateful-Set" utrzymuje porządek i unikalność podów? 
"Stateful-Sets" utrzymują porządek i unikalność podów za pomocą usług rządzących ("governing service,"), które są usługami 
"headless" kontrolującymi domenę sieciową "Stateful-Sets". Kontroler "Stateful-Set" tworzy pody na podstawie indeksu 
porządkowego, zapewniając, że każdy pod jest tworzony i usuwany w przewidywalnej kolejności.

47) Co się dzieje z "Stateful-Set", gdy węzeł ulega awarii? 
Gdy węzeł ulega awarii, pody w "Stateful-Set" na tym węźle stają się niedostępne. Kubernetes nie przeskaluje automatycznie 
tych podów na inne węzły. Zamiast tego, jeśli awaryjny węzeł powróci do stanu funkcjonalnego, pody zostaną na nim 
ponownie uruchomione, zachowując swój stan. Aby zapewnić automatyczne przełączanie awaryjne, należy wdrożyć dodatkowe 
mechanizmy, takie jak "pod disruption budgets" lub "node health checks".

48) Jak zaktualizować "Stateful-Set" i jakie ryzyka są związane z tym procesem? 
"Stateful-Sets" są aktualizowane domyślnie za pomocą strategii "Rolling update". Aktualizujesz konfigurację "Stateful-Set", 
a Kubernetes stosuje zmiany do każdego podu sekwencyjnie, zachowując ograniczenia porządkowe. Główne ryzyko związane z 
aktualizacją "Stateful-Set" dotyczy wymagań specyficznych dla aplikacji dotyczących integralności danych i spójności, 
ponieważ aplikacje te często zarządzają stanem.

49) Wyjaśnij znaczenie "podManagementPolicy" w Stateful-Set? 
"podManagementPolicy" w "Stateful-Set" określa sposób zarządzania podami w ramach "Stateful-Set". Istnieją dwie polityki: 
"OrderedReady", w której pody są uruchamiane sekwencyjnie, oraz "Parallel", w której pody są uruchamiane lub usuwane 
jednocześnie. 
"OrderedReady" jest domyślną polityką i zapewnia uporządkowane wdrażanie oraz skalowanie.

50) Jakie są najlepsze praktyki tworzenia kopii zapasowych danych w "Stateful-Set"? 
Najlepsze praktyki tworzenia kopii zapasowych danych w "Stateful-Set" obejmują regularne tworzenie migawkowych ("snapshot") 
kopii "Persistent Volumes" przy użyciu narzędzi takich jak "Velero", wdrożenie solidnej ("robust") strategii replikacji, 
jeśli aplikacja ją wspiera (np. w bazach danych), oraz zapewnienie spójności danych podczas tworzenia kopii zapasowych. 
Zaleca się również przechowywanie kopii zapasowych w lokalizacji niezależnej od klastra Kubernetes.

51) Czym jest "Replica-Set" w Kubernetes? 
"Replica-Set" w Kubernetes to obiekt API służący do zapewnienia, że określona liczba replik podów jest uruchomiona w 
danym momencie. Jest głównie używany do utrzymania dostępności zestawu identycznych podów. Jeśli jest za mało replik, 
tworzy nowe; jeśli jest ich za dużo, usuwa niektóre.

52) Czym różni się "Replica-Set" od ReplicationController? 
"Replica-Set" to następna generacja ReplicationController. Kluczowa różnica polega na tym, że "Replica-Sets" obsługują wymagania 
selektora oparte na zbiorach, w przeciwieństwie do selektorów opartych na równości w ReplicationControllerach. 
Oznacza to, że "Replica-Sets" mogą wybierać szerszy zakres podów na podstawie etykiet.

53) Jak zdefiniować i używać "Replica-Set" w Kubernetes? 
ReplicaSet definiuje się za pomocą pliku "YAML", który określa liczbę replik i szablon podu do użycia. Zawiera on selektor 
do identyfikacji podów, którymi ma zarządzać. Używasz ReplicaSetu, tworząc go poleceniem "kubectl apply -f file.yaml". 
ReplicaSet następnie zapewnia, że określona liczba replik podu jest uruchomiona.

54) Co się stanie, jeśli pod w "Replica-Set" ulegnie awarii? 
Jeśli pod w "Replica-Set" ulegnie awarii (z powodu awarii węzła lub zakończenia działania), "Replica-Set" zauważa spadek 
liczby replik i tworzy nowy pod, aby go zastąpić. Nowy pod jest tworzony na podstawie szablonu podu zdefiniowanego w 
"Replica-Set".

55) Czy można skalować ReplicaSet? Jak to zrobić? 
Tak, można skalować ReplicaSet, zmieniając pole replicas w definicji "ReplicaSet", a następnie stosując aktualizację. 
Alternatywnie, można użyć polecenia "kubectl scale", aby zmienić liczbę replik, na przykład: 
"kubectl scale replicaset replicaset-name --replicas=number".

56) Jak ReplicaSet współpracuje z Deploymentem w Kubernetes? 
W Kubernetes Deploymenty to wyższy poziom abstrakcji, który zarządza "ReplicaSets". Gdy tworzysz Deployment, tworzy on 
ReplicaSet, który zarządza podami. Deployment automatycznie obsługuje aktualizowanie ReplicaSetu i jego podów zgodnie z 
określoną strategią, na przykład "Rolling update".

57) Jakie są przypadki użycia ReplicaSetu? 
ReplicaSety są używane do zapewnienia dostępności i skalowalności zestawu identycznych podów. Typowe przypadki użycia to 
uruchamianie wielu instancji aplikacji lub usługi bezstanowej, zapewnienie, że określona liczba podów jest zawsze 
uruchomiona, oraz zapewnianie równoważenia obciążenia i tolerancji na awarie.

58) Jak zaktualizować pody w ReplicaSecie? 
Aby zaktualizować pody w ReplicaSecie, zazwyczaj aktualizujesz szablon podu w definicji ReplicaSetu i stosujesz zmianę. 
Warto jednak zauważyć, że chociaż ReplicaSet zapewnia, że określona liczba podów jest uruchomiona, nie zapewnia on 
mechanizmu do aktualizacji podów. Do "Rolling update" należy używać Deploymentu, który zarządza ReplicaSetami.

59) Jaka jest rola selektorów etykiet ("Label Selectors") w ReplicaSecie? 
Selektory etykiet w ReplicaSecie określają, które pody są kontrolowane przez ReplicaSet. Selektor dopasowuje etykiety 
przypisane do podów i zapewnia, że ReplicaSet zarządza wszystkimi podami z określonymi etykietami. Jest to kluczowe dla 
powiązania ReplicaSetu z jego podami.

60) Jak zapewnić wysoką dostępność z ReplicaSetami? 
Aby zapewnić wysoką dostępność z ReplicaSetami, należy uruchomić wiele replik podów na różnych węzłach. W ten sposób, 
jeśli jeden węzeł ulegnie awarii, inne replikowane pody na różnych węzłach mogą kontynuować obsługę żądań. Dodatkowo, 
użycie reguł anti-affinity może pomóc w rozprzestrzenianiu podów na różnych węzłach, aby uniknąć pojedynczych punktów awarii.

61) Czym jest Pod w Kubernetes? 
Pod to najmniejszy i najbardziej podstawowy obiekt, który można wdrożyć w Kubernetes. Reprezentuje pojedynczą instancję 
działającego procesu w Twoim klastrze i może zawierać jeden lub więcej kontenerów. Te kontenery w Podzie są planowane 
("scheduled") na tym samym węźle i dzielą ten sam przestrzeń nazw sieci, adres IP oraz przestrzeń portów.

62) Jak Pody komunikują się ze sobą w Kubernetes? 
Pody komunikują się ze sobą za pomocą sieci. Ponieważ każdy Pod ma unikalny adres IP w obrębie klastra, mogą się komunikować 
za pomocą standardowej sieci TCP/IP. Pody mogą również komunikować się ze sobą za pomocą współdzielonych wolumenów, gdzie 
dane są dzielone między kontenerami w tym samym Podzie.

63) Jaki jest cykl życia Podu w Kubernetes? 
Cykl życia Podu w Kubernetes obejmuje kilka stanów: 
"Pending" (Pod został utworzony, ale niektóre jego kontenery jeszcze nie działają). 
"Running" (Pod został przypisany do węzła, a wszystkie kontenery zostały utworzone). 
"Succeeded" (wszystkie kontenery w Podzie zakończyły działanie pomyślnie). 
"Failed" (wszystkie kontenery w Podzie zakończyły działanie, a przynajmniej jeden kontener zakończył działanie z błędem). 
"Unknown" (nie udało się uzyskać stanu Podu).

64) Wyjaśnij różnicę między Podem a kontenerem? 
Kontener to najmniejsza jednostka obliczeniowa, która zawiera aplikację oraz jej zależności. Z kolei Pod jest abstrakcją 
w Kubernetes, która reprezentuje grupę jednego lub więcej kontenerów (takich jak kontenery Docker), ze współdzieloną 
pamięcią masową/siecią oraz specyfikacją, jak uruchamiać te kontenery.

65) Jak zasoby są przydzielane do Podu? 
Zasoby, takie jak CPU i pamięć, są przydzielane do Podu na podstawie specyfikacji w definicji Podu. Każdy kontener w Podzie 
może określić żądanie (ilość zasobu gwarantowaną) oraz limit (maksymalną ilość zasobu, którą kontener może wykorzystać). 
Kubernetes wykorzystuje te specyfikacje, aby zaplanować Pody na węzłach z dostępnymi zasobami i zarządzać wykorzystaniem zasobów.

66) Czym są Pody wielokontenerowe i kiedy je używać? 
Pody wielokontenerowe to Pody, które zawierają więcej niż jeden kontener. Kontenery te są ze sobą ściśle powiązane i 
dzielą zasoby, takie jak wolumeny i sieć. Używaj Podów wielokontenerowych, gdy kontenery muszą działać ściśle razem, na 
przykład główny kontener aplikacji i kontener pomocniczy, który przesyła dane do lub pobiera dane z zewnętrznego źródła.

67) Jak zarządzać skalowalnością Podów w Kubernetes? 
Skalowalność Podów w Kubernetes zazwyczaj zarządzana jest za pomocą kontrolerów, takich jak "Deployments", "ReplicaSets" 
lub "StatefulSets". Te kontrolery tworzą i zarządzają wieloma instancjami Podu, aby zapewnić, że określona liczba replik 
jest uruchomiona w danym momencie, co pozwala na skalowanie w górę lub w dół w zależności od potrzeb.

68) Czym są kontenery "init" i jak różnią się od zwykłych kontenerów w Podzie? 
Kontenery "init" to wyspecjalizowane kontenery, które uruchamiają się przed kontenerami aplikacji w Podzie. Służą one do 
przygotowania środowiska dla głównego kontenera aplikacji, na przykład konfigurowania ustawień, aktualizowania 
plików/katalogów lub czekania na spełnienie określonego warunku. W przeciwieństwie do zwykłych kontenerów, kontenery "init"
uruchamiają się do zakończenia, a kontenery aplikacji w Podzie zaczynają działać dopiero po pomyślnym zakończeniu 
wszystkich kontenerów "init".

69) Jak Kubernetes radzi sobie z awariami Podów? 
Kubernetes radzi sobie z awariami Podów za pomocą kontrolerów, takich jak "Deployments" i "ReplicaSets". 
Jeśli Pod ulegnie awarii (na przykład w przypadku awarii węzła), kontroler zauważa rozbieżność między stanem pożądanym a 
aktualnym i tworzy nowy Pod, aby utrzymać pożądaną liczbę replik.

70) Czy możesz opisać proces udostępniania Podu na zewnętrzną sieć? 
Aby udostępnić Poda na zewnętrzną sieć, zazwyczaj używa się usługi Kubernetes ("Service"). Usługa zapewnia stabilny adres 
IP i wpis "DNS" do uzyskania dostępu do Poda. Dla dostępu zewnętrznego używana jest usługa typu "LoadBalancer" lub 
"NodePort", która kieruje ruch zewnętrzny do Poda, korzystając z load balansera dostawcy chmurowego lub poprzez 
udostępnienie portu na węzłach.

71) Czym jest "Job" w Kubernetes i jaki jest jego cel? 
"Job" w Kubernetes to zasób używany do zarządzania zadaniem, które ma zostać wykonane do końca, w przeciwieństwie do 
długoterminowych usług. Jest używany do zadań takich jak przetwarzanie wsadowe ("Batch processing"), analiza danych czy 
obliczenia wsadowe, które muszą zostać wykonane raz i zakończyć się pomyślnie. Kubernetes zapewnia, że "Job" będzie 
działał do momentu osiągnięcia określonej liczby zakończeń.

72) Jak Job w Kubernetes różni się od Podu? 
"Pod" w Kubernetes reprezentuje pojedynczą instancję uruchomionego procesu, natomiast "Job" zarządza jednym lub więcej 
Podami, które mają zakończyć działanie pomyślnie (tzn. zakończyć się statusem zero). Pody tworzone przez "Job" mają na 
celu wykonanie określonego zadania, a następnie zakończenie działania pomyślnie, co wskazuje na ukończenie "Jobu".

73) Czy możesz wyjaśnić cykl życia "Job" w Kubernetes? 
Cykl życia obiektu "Job" w Kubernetes zaczyna się, gdy jest on tworzony. "Job" następnie uruchamia jeden lub więcej Podów na 
podstawie swojej specyfikacji i śledzi ich postęp. Jeśli Pod zakończy działanie niepowodzeniem (status wyjścia różny od 
zera) i "restartPolicy" obiektu "Job" jest ustawione na "OnFailure", "Job" ponownie uruchamia Poda. Gdy wymagania 
dotyczące liczby zakończonych pomyślnie Podów zostaną spełnione, "Job" jest oznaczony jako zakończony.

74) Czym są "CronJobs" w Kubernetes i jak są one powiązane z obiektami "Job"? 
"CronJobs" w Kubernetes są rozszerzeniem obiektów "Job". Podczas gdy "Job" wykonuje zadanie do końca tylko raz, "CronJob" 
tworzy obiekty "Job" na podstawie harmonogramu czasowego, podobnie jak narzędzie cron w systemie Unix. Jest używane do 
zadań cyklicznych, takich jak kopie zapasowe, generowanie raportów czy wysyłanie e-maili.

75) Jak monitorować postęp lub status obiektu "Job" w Kubernetes? 
Postęp lub status Jobu w Kubernetes można monitorować za pomocą komendy "kubectl". Na przykład, 
"kubectl describe job [job-name]" dostarcza szczegółowych informacji na temat postępu i statusu obiektu "Job", a 
"kubectl get pods --selector=job-name=[job-name]" wyświetla listę Podów utworzonych przez "Job", pokazując ich 
indywidualny status.

76) Co się dzieje z Podami, gdy obiekt "Job" Kubernetesa zostanie zakończony? 
Gdy Kubernetes Job zostanie zakończony, Pody, które zostały utworzone, pozostają i nie są automatycznie usuwane. Pozwala 
to na sprawdzenie logów zakończonych Podów w celu weryfikacji wyników lub debugowania, jeśli to konieczne. Można jednak 
skonfigurować "Job", aby automatycznie usuwał Pody po zakończeniu, używając mechanizmu TTL lub ustawiając "restartPolicy" 
Podu na "Never".

77) Jak możesz kontrolować równoległe wykonanie Podów w Kubernetes "Job"? 
Możesz kontrolować równoległe wykonanie Podów w Kubernetes "Job", używając właściwości "completions" i "parallelism" 
w specyfikacji obiektu "Job". Completions określa wymaganą liczbę pomyślnie zakończonych Podów, a "parallelism" określa 
maksymalną liczbę Podów, które mogą być uruchomione równocześnie.

78) Wyjaśnij, jak użyć "Job" do przetwarzania kolejki zadań w Kubernetes.
Aby przetwarzać kolejkę zadań przy użyciu Kubernetes "Job", tworzysz "Job", który uruchamia Pody, z których każdy 
przetwarza jeden element z kolejki, a następnie kończy działanie. "Job" powinien być skonfigurowany tak, aby tworzył tyle 
Podów, ile jest elementów do przetworzenia. Idealnie, aplikacja powinna być zaprojektowana w taki sposób, aby obsługiwała 
przypadek, w którym wiele instancji przetwarza ten sam element.

79) Jak radzisz sobie z awariami i ponownymi próbami "Job" w Kubernetes?
W Kubernetes, jeśli Pody "Job" zakończą się niepowodzeniem (zakończą działanie z kodem innym niż zero), Job spróbuje 
ponownie uruchomić Poda na podstawie swojej polityki restartu ("restartPolicy"). "Backoff-Limit" określa liczbę prób ponownego 
uruchomienia, zanim "Job" zostanie oznaczony jako nieudany. Ustawienie odpowiedniego "backoff-Limit" pozwala zarządzać 
liczbą prób ponownych, zanim Job zostanie uznany za zakończony niepowodzeniem.

80) Czy "Job" w Kubernetes może zaktualizować swój szablon Poda po utworzeniu?
Po utworzeniu "Job" w Kubernetes, jego szablon Poda nie może zostać zmodyfikowany. Jeśli chcesz zaktualizować szablon Poda, 
musisz utworzyć nowy "Job" z zaktualizowanym szablonem. Ta niemutowalność zapewnia, że wszystkie Pody uruchomione przez 
"Job" będą równoważne z perspektywy szablonu użytego do ich uruchomienia.

81) Czym są przestrzenie nazw ("Namespaces") w Kubernetes i jaki jest ich cel?
Przestrzenie nazw w Kubernetes to sposób podziału zasobów klastra między wielu użytkowników i aplikacje. Zapewniają one 
zakres dla grupowania i izolowania zasobów, takich jak Pody, Usługi ("Services") i Wdrażania ("Deployments"). 
Przestrzenie nazw są szczególnie przydatne w środowiskach z wieloma użytkownikami i zespołami, aby uniknąć konfliktów i 
efektywnie zarządzać dostępem oraz zasobami.

82) Jak przestrzenie nazw wpływają na alokację zasobów i izolację w Kubernetes?
Przestrzenie nazw pomagają w alokacji zasobów, umożliwiając administratorom przypisanie limitów zasobów, takich jak CPU 
i pamięć, do każdej przestrzeni nazw, zapewniając sprawiedliwy podział zasobów i zapobiegając nadmiernemu zużyciu zasobów 
klastra przez jedną przestrzeń nazw. Zapewniają także poziom izolacji, ponieważ zasoby w jednej przestrzeni nazw są ukryte 
przed innymi przestrzeniami nazw, co poprawia bezpieczeństwo i organizację.

83) Czy możliwa jest komunikacja między różnymi przestrzeniami nazw w Kubernetes? Jeśli tak, to jak?
Tak, komunikacja między różnymi przestrzeniami nazw w Kubernetes jest możliwa. Domyślnie nie ma ograniczeń w ruchu między 
przestrzeniami nazw. Usługi mogą być dostępne z innych przestrzeni nazw za pomocą pełnej kwalifikowanej nazwy domenowej 
(FQDN) usługi. Polityki sieciowe mogą być wdrożone w celu kontrolowania i ograniczania komunikacji między przestrzeniami 
nazw ze względów bezpieczeństwa.

84) Jaka jest domyślna przestrzeń nazw w Kubernetes i kiedy ją stosować?
Domyślna przestrzeń nazw w Kubernetes nazywa się "default". Jest przeznaczona do użytku w środowiskach, gdzie nie ma 
potrzeby korzystania z wielu przestrzeni nazw. Zasoby tworzone bez określenia przestrzeni nazw trafiają do przestrzeni 
"default". Jednak dla lepszej organizacji i bezpieczeństwa zaleca się tworzenie dodatkowych przestrzeni nazw dla różnych 
środowisk lub projektów.

85) Jak tworzysz i zarządzasz przestrzeniami nazw w Kubernetes?
Przestrzenie nazw w Kubernetes można tworzyć za pomocą komendy "kubectl create namespace NAME". Zarządzanie 
przestrzeniami nazw obejmuje stosowanie konfiguracji i limitów zasobów, a także przypisywanie ról i uprawnień za pomocą 
Kontroli Dostępu Opartej na Rolach ("RBAC") w każdej przestrzeni nazw. Przestrzenie nazw można usuwać za pomocą komendy 
"kubectl delete namespace NAME", co skutkuje usunięciem wszystkich zasobów w nich zawartych.

86) Wyjaśnij zastosowanie limitów zasobów w przestrzeniach nazw Kubernetes?
Limity zasobów w przestrzeniach nazw Kubernetes służą do ograniczenia ilości zasobów, które przestrzeń nazw może 
wykorzystać. Obejmuje to limity CPU i pamięci, kwoty przechowywania, a także limity liczby obiektów, takich jak Pod'y, 
Serwisy i "PersistentVolumeClaims". Limity zasobów zapewniają sprawiedliwy dostęp do zasobów klastra i zapobiegają 
wyczerpaniu zasobów klastra przez jedną przestrzeń nazw.

87) Jakie są najlepsze praktyki używania przestrzeni nazw w Kubernetes w dużej organizacji?
W dużej organizacji najlepszą praktyką jest używanie przestrzeni nazw do oddzielania różnych zespołów, projektów lub 
etapów rozwoju (takich jak dev, staging i produkcja). Każda przestrzeń nazw powinna mieć przypisane limity zasobów i 
polityki sieciowe w celu zapewnienia izolacji i bezpieczeństwa. Dodatkowo warto korzystać z RBAC (Role-Based Access Control), 
aby kontrolować dostęp do każdej przestrzeni nazw, zapewniając użytkownikom tylko niezbędne uprawnienia.

88) Jak działają etykiety ("Labels") i adnotacje ("Annotations") w przestrzeniach nazw w Kubernetes?
Etykiety i adnotacje mogą być przypisane do przestrzeni nazw tak jak do innych zasobów Kubernetes. Etykiety służą do 
organizowania i wybierania podzbiorów przestrzeni nazw do określonych operacji. Z kolei adnotacje pozwalają przechowywać 
dodatkowe, nieidentyfikujące informacje o przestrzeniach nazw, co może być przydatne dla narzędzi i bibliotek pracujących 
z metadanymi Kubernetes.

89) Czy można migrować zasoby między przestrzeniami nazw?
Migracja zasobów między przestrzeniami nazw nie jest prosta, ponieważ większość zasobów Kubernetes jest powiązana z 
konkretną przestrzenią nazw. Aby przeprowadzić migrację, zazwyczaj trzeba ponownie utworzyć zasób w docelowej przestrzeni 
nazw i usunąć go z oryginalnej. Trwałe dane i konfiguracje muszą być starannie obsługiwane podczas tego procesu.

90) Jak Kubernetes zarządza bezpieczeństwem w przestrzeniach nazw ("Namespaces")?
Kubernetes sam w sobie nie egzekwuje silnej izolacji między przestrzeniami nazw; jest to raczej narzędzie organizacyjne.
Bezpieczeństwo w przestrzeniach nazw zależy od wdrożenia polityk sieciowych do kontrolowania ruchu między podami i 
przestrzeniami nazw, używania RBAC (Role-Based Access Control) do kontroli dostępu oraz stosowania kontekstów bezpieczeństwa 
do podów i kontenerów, aby kontrolować uprawnienia i dostęp na bardziej szczegółowym poziomie.

91) Co to jest obiekt "Service" Kubernetes i dlaczego jest ważna?
Usługa Kubernetes ("Kubernetes Service") to abstrakcyjny sposób wystawienia aplikacji działającej na zestawie podów jako 
usługi sieciowej. Zapewnia ona spójną i stabilną adresację IP, nazwę "DNS" oraz port, a także równoważy ruch pomiędzy 
podami. Usługi są kluczowe w zarządzaniu dostępem klientów do aplikacji, ponieważ zapewniają stabilny interfejs do 
dynamicznie zmieniającego się zestawu podów.

92) Wyjaśnij różne typy usług w Kubernetes?
Główne typy usług ("Services") w Kubernetes to:
a) "ClusterIP": Wystawia usługę na wewnętrznym adresie IP w klastrze, co sprawia, że jest dostępna tylko wewnątrz klastra.
b) "NodePort": Wystawia usługę na tym samym porcie na IP każdego wybranego węzła, co pozwala na dostęp do niej spoza klastra.
c) "LoadBalancer": Wystawia usługę na zewnątrz, korzystając z load balansera dostawcy chmurowego.
d) "ExternalName": Mapuje usługę na zewnętrzną nazwę DNS.

93) Jak obiekty "Services" odkrywają i zarządzają ruchem do podów?
Usługi ("Services") odkrywają Pody za pomocą selektorów etykiet ("Label Selectors"). Kiedy definiujesz usługę, określasz 
zbiór etykiet, które pasują do grupy podów. Usługa kieruje ruch do tych podów, domyślnie używając algorytmu round-robin. 
W miarę tworzenia i usuwania podów usługa automatycznie aktualizuje grupę podów, do których kieruje ruch.

94) Jaka jest rola "Endpoints" w usługach Kubernetes?
"Endpoints" w usługach Kubernetes to obiekty, które śledzą adresy IP i porty podów, które pasują do selektora usługi. 
Są one automatycznie zarządzane przez płaszczyznę sterowania Kubernetes ("Kubernetes Control Plane"). Kiedy Pody w usłudze 
się zmieniają, obiekt Endpoints jest automatycznie aktualizowany, aby odzwierciedlić te zmiany.

95) Jak działa usługa ("NodePort") i kiedy ją stosować?
Usługa NodePort udostępnia usługę na IP każdego węzła w klastrze Kubernetes na stałym porcie. Gdy klient wysyła zapytanie 
na IP węzła i port NodePort, zapytanie jest przekazywane do jednego z podów usługi. NodePort jest zwykle stosowany, gdy 
chcesz udostępnić usługę zewnętrznym użytkownikom spoza klastra Kubernetes, ale nie masz dostępnego "LoadBalancer".

96) Czym jest usługa "Load-Balancer" i jak różni się od "Node-Port" oraz "Cluster-IP"?
Usługa "Load-Balancer" udostępnia usługę na zewnątrz, korzystając z load balansera dostawcy chmurowego. Przypisuje stały, 
zewnętrzny adres IP do usługi. W przeciwieństwie do "Node-Port", który udostępnia usługę na porcie na wszystkich węzłach, 
"Load-Balancer" zapewnia jeden punkt dostępu. Z kolei "Cluster-IP" udostępnia usługę tylko wewnętrznie w klastrze.

97) Jak zabezpieczyć usługę ("Service") w Kubernetes?
Aby zabezpieczyć usługę ("Service") w Kubernetes, możesz:
a) Użyć Polityk Sieciowych (Network Policies) do ograniczenia dostępu do usługi.
b) Wdrożyć TLS/SSL do szyfrowania ruchu przychodzącego i wychodzącego z usługi.
c) Zastosować mechanizmy uwierzytelniania i autoryzacji dla klientów uzyskujących dostęp do usługi.

98) Czy możesz zaktualizować usługę Kubernetes bez przestojów ("Downtime")? Jak?
Tak, możesz zaktualizować niektóre aspekty usługi Kubernetes bez przestojów ("downtime"), takie jak aktualizacja etykiet 
("labels") lub adnotacji ("annotations"). Jednak zmiana typu usługi lub portów może prowadzić do przestojów. Aby zaktualizować 
usługę bez przestojów ("downtime"), możesz utworzyć nową usługę i stopniowo przekierować ruch do niej.

99) Wyjaśnij usługi "headless" w Kubernetes?
Usługa "headless" w Kubernetes to usługa, która nie posiada adresu IP klastra ("cluster IP"). Jest używana w przypadkach, 
gdy aplikacje wymagają bezpośredniego dostępu do poszczególnych Podów. Dzięki usługom "headless" możesz używać "DNS" do 
odkrywania adresów poszczególnych Podów.

100) Jak działają usługi z "Stateful-Sets" w Kubernetes?
Usługi są często używane z "Stateful-Sets", aby zapewnić stabilną tożsamość sieciową dla każdego poda w zestawie. Każdy pod 
w "Stateful-Set" otrzymuje stabilną nazwę "DNS", zarządzaną przez usługę, co jest kluczowe dla aplikacji stanowych, takich 
jak bazy danych, które polegają na stabilnych identyfikatorach sieciowych dla każdej repliki.

101) Czym są Proby w Kubernetes?
Proby to narzędzia diagnostyczne używane przez Kubernetes do określenia stanu zdrowia kontenera w podzie. Wykonują one 
kontrole, aby upewnić się, że kontenery działają zgodnie z oczekiwaniami. Kubernetes wykorzystuje te informacje do 
podejmowania decyzji o podzie, takich jak ponowne uruchomienie kontenera, który nie działa prawidłowo, lub zatrzymanie 
ruchu do kontenera, który nie jest gotowy na przyjmowanie zapytań.

102) Wyjaśnij różnicę między probami "Liveness" a "Readiness"?
Proby "Liveness" określają, czy kontener działa i jest zdrowy. Jeśli "Liveness Probe" zakończy się niepowodzeniem, 
Kubernetes ponownie uruchamia kontener. Proby "Readiness" sprawdzają, czy kontener jest gotowy do obsługi zapytań. 
Jeśli "Readiness Probe" zakończy się niepowodzeniem, Kubernetes przestaje kierować ruch do podu, aż proba "Readiness" 
przejdzie pomyślnie. Proby "Liveness" zapewniają niezawodność, podczas gdy proby "Readiness" zapewniają płynne 
zarządzanie ruchem.

103) Jak działa "Startup Probe" w Kubernetes?
"Startup Probe" służy do określenia, czy aplikacja w kontenerze została uruchomiona. Jest to przydatne w przypadku 
aplikacji, które mają długi czas uruchamiania. Dopóki "Startup Probe" się nie powiedzie, proby "liveness" i "readiness" 
są wyłączone. Dzięki temu zapobiega się zabiciu aplikacji, jeśli jej uruchomienie zajmuje dużo czasu.

104) Jakie rodzaje "Probes" są dostępne w Kubernetes?
Kubernetes obsługuje trzy rodzaje prób:
a) "HTTP GET": Wysyła zapytanie "HTTP GET" do adresu IP kontenera na określonym porcie i ścieżce.
b) "TCP Socket": Próbuje nawiązać połączenie TCP z określonym portem kontenera.
c) "Exec": Wykonuje określone polecenie wewnątrz kontenera. Sukces zależy od statusu zakończenia polecenia.

105) Jak skonfigurować próby ("Probes") w podzie Kubernetes?
Próby są konfigurowane w specyfikacji poda. Dla każdej próby ("liveness", "readiness", "startup") należy określić typ 
próby ("HTTP GET", "TCP Socket", "Exec"), a także parametry takie jak "initialDelaySeconds", "periodSeconds", 
"timeoutSeconds", "successThreshold" i "failureThreshold".

106) Czy próby ("Probes") mogą wpływać na wydajność poda?
Tak, próby mogą wpływać na wydajność poda, zwłaszcza jeśli są skonfigurowane z agresywnymi interwałami sprawdzania 
("periodSeconds"). Może to prowadzić do zwiększonego zużycia zasobów i ruchu sieciowego. Ważne jest, aby znaleźć równowagę 
między potrzebą szybkich kontroli zdrowia a potencjalnym wpływem na wydajność.

107) Jakie jest znaczenie "initialDelaySeconds" w konfiguracji próby ("probes")?
"initialDelaySeconds" to czas, który Kubernetes czeka przed rozpoczęciem pierwszej próby. Jest to istotne dla aplikacji,
które wymagają czasu na uruchomienie, zanim będą mogły obsługiwać ruch lub zanim ich stan zdrowia będzie można niezawodnie 
sprawdzić. Odpowiednie ustawienie tej wartości zapobiega niepotrzebnym restartom dla kontenerów, które uruchamiają się wolniej.

108) Jak Kubernetes reaguje na nieudane próby "Liveness"?
Jeśli próba "Liveness" zakończy się niepowodzeniem, Kubernetes zrestartuje kontener. Parametr "failureThreshold" określa, 
ile razy próba musi zakończyć się niepowodzeniem, zanim Kubernetes podejmie działanie. Ten mechanizm zapewnia, że kontenery, 
które przestają być zdrowe, będą restartowane w celu przywrócenia normalnej pracy.

109) W jakich scenariuszach należy unikać używania prób "Liveness"?
Należy unikać używania prób "Liveness" w scenariuszach, gdzie tymczasowe problemy, takie jak krótkotrwałe wzrosty zużycia 
zasobów, przejściowe problemy z siecią lub krótkoterminowe zależności od zewnętrznych usług, mogłyby spowodować niepowodzenie 
próby. W takich przypadkach użycie próby "Liveness" może prowadzić do niepotrzebnych restartów i niestabilności aplikacji.

110) Jak próby "Readiness" wpływają na równoważenie obciążenia w Kubernetes?
Próby "Readiness" wpływają na równoważenie obciążenia, informując "kube-proxy" oraz kontroler wejścia ("ingress controller"),
czy pod jest gotowy do przyjmowania ruchu. Jeśli próba "Readiness" zakończy się niepowodzeniem, Kubernetes oznaczy pod 
jako "niegotowy", co spowoduje, że nie będzie on odbierał ruchu od usług ani kontrolerów wejścia. Zapewnia to, że ruch 
trafia tylko do podów, które są w pełni uruchomione i gotowe do obsługi żądań.

111) Czym jest Helm w Kubernetes i jakie są jego główne komponenty?
Helm to menedżer pakietów dla Kubernetes, który upraszcza wdrażanie i zarządzanie aplikacjami w klastrach Kubernetes. 
Jego główne komponenty to "Helm Client" oraz "Tiller Server" (w wersji Helm 2; Helm 3 nie zawiera "Tillera"). 
Helm 3 wprowadza ulepszenia, takie jak lepsze bezpieczeństwo, uproszczoną architekturę opartą wyłącznie na kliencie oraz 
ulepszone zarządzanie chartami. "Charts", format pakietów Helm, opisują zestaw zasobów Kubernetes i konfiguracji.

112) W jaki sposób Helm 3 poprawia Helm 2?
Helm 3 wprowadził znaczące zmiany: usunięcie "Tillera" (co poprawia bezpieczeństwo), nowy trzystronicowy strategiczny
"merge patch", uproszczone zarządzanie zależnościami chartów, lepsze zarządzanie przestrzeniami nazw oraz walidację 
wartości chartów za pomocą schematu "JSON". Te ulepszenia upraszczają doświadczenie użytkownika oraz zwiększają 
bezpieczeństwo i niezawodność.

113) Czym są Charty Helm i jak są przydatne? 
Charty Helm ("Helm Charts") to pakiety w Helm, które zawierają wszystkie niezbędne pliki i konfiguracje do wdrożenia 
aplikacji, narzędzia lub usługi w klastrze Kubernetes. Promują one ponowne użycie i mogą enkapsulować skomplikowane zasoby 
Kubernetes, co ułatwia dzielenie się i wdrażanie aplikacji.

114) Czy możesz wyjaśnić, czym są Chart Hooks w Helm?
Chart Hooks to zaawansowana funkcja w Helm, która pozwala deweloperom chartów dodawać zdarzenia cyklu życia do swoich 
chartów. Zdarzenia (hooki) te mogą wykonywać operacje na różnych etapach procesu instalacji, aktualizacji lub usuwania 
chartu, takie jak migracje baz danych lub operacje czyszczenia.

115) Jak zarządza się zależnościami w "Helm 3"?
Helm 3 zarządza zależnościami za pomocą pola dependencies w pliku "Chart.yaml". Można tu wymienić wszystkie zależne 
charty oraz ich wersje. Helm 3 upraszcza zarządzanie zależnościami, eliminując potrzebę osobnego pliku "requirements.yaml" 
i pozwalając na umieszczanie zależności bezpośrednio w katalogu "charts/" lub dynamiczne linkowanie ich za pomocą pliku 
"Chart.yaml".

116) Czym jest repozytorium Helm i jak się go używa?
Repozytorium Helm to miejsce, w którym przechowywane i udostępniane są spakowane charty. Jest to w zasadzie zbiór plików 
"index.yaml", które odnoszą się do wersji chartów. Można dodać repozytoria do instalacji Helm, wyszukiwać w nich charty 
i instalować je do klastra Kubernetes.

117) Jak zaktualizować Helm Chart w klastrze?
Aby zaktualizować Helm Chart w klastrze, używa się polecenia "helm upgrade". To polecenie przyjmuje nazwę wydania oraz 
nowy chart lub nową wersję chartu i aktualizuje istniejące wdrożenie, szanując wszelkie modyfikacje konfiguracji. Jest to 
operacja atomowa, która umożliwia przywrócenie poprzedniej wersji w przypadku niepowodzeń.

118) Czym jest Helm Rollback i jak działa?
Helm Rollback służy do przywrócenia chartu do poprzedniej wersji. Jeśli aktualizacja Helm zakończy się niepowodzeniem lub 
aplikacja ma problemy, można użyć polecenia "helm rollback Release Revision", aby przywrócić aplikację do poprzedniego, 
stabilnego stanu.

119) Wyjaśnij proces tworzenia niestandardowego Helm Chart?
Tworzenie niestandardowego Helm Chart obejmuje kilka kroków:
a) Zainicjowanie nowego chartu za pomocą polecenia "helm create Nazwa-Chartu".
b) Edytowanie pliku "Chart.yaml" i zdefiniowanie metadanych.
c) Modyfikowanie domyślnych szablonów lub dodawanie nowych szablonów obiektów Kubernetes w katalogu "templates/".
d) Definiowanie domyślnych wartości konfiguracji w pliku "values.yaml".
e) Opcjonalnie dodanie zależności chartów i niestandardowych hooków.
f) Spakowanie chartu za pomocą polecenia "helm package Katalog-Diagramu".
g) Testowanie chartu za pomocą polecenia "helm install --dry-run --debug".

120) Jak Helm zarządza zarządzaniem wydaniami ("Release Management") w Kubernetes?
Helm zarządza wydaniami aplikacji Kubernetes za pomocą chartów Helm. Za każdym razem, gdy chart jest instalowany, 
aktualizowany lub usuwany, tworzone jest nowe wydanie. Helm śledzi i zarządza tymi wydaniami, umożliwiając takie funkcje 
jak rollbacki, śledzenie rewizji i personalizację wydań. Zapewnia wyższą abstrakcję do zarządzania zasobami Kubernetes i 
upraszcza skomplikowane scenariusze wdrożeniowe.

121) Czym jest Deployment w Kubernetes i jak działa?
Deployment w Kubernetes to obiekt API, który zarządza tworzeniem i aktualizowaniem ReplicaSetów oraz Podów. 
Umożliwia opisanie cyklu życia aplikacji, na przykład określenie, jakich obrazów używać w aplikacji, liczby podów i sposobu 
ich aktualizacji, oraz innych aspektów. Deployments używają ReplicaSetu do zarządzania podami. Gdy aktualizujesz Deployment, 
uruchamiana jest operacja "rollout", która tworzy nowy ReplicaSet i skaluje go w górę, jednocześnie skalując w dół stary 
ReplicaSet.

122) Wyjaśnij wzorzec "Blue/Green" Deployment w Kubernetes.
Wzorzec "Blue/Green" deployment to technika, która zmniejsza czas przestoju i ryzyko, uruchamiając dwa identyczne środowiska, 
z których tylko jedno ("Blue") obsługuje ruch produkcyjny na żywo. Dla nowego wydania nowa wersja ("Green") jest wdrażana 
obok wersji "Blue". Po przetestowaniu środowiska "Green", ruch jest przekierowywany na nowe środowisko. Jeśli pojawią się 
problemy, ruch może zostać przekierowany z powrotem do "Blue". Kubernetes ułatwia ten wzorzec za pomocą usług, które 
przekierowują ruch sieciowy do różnych deploymentów.

123) Opisz wzorzec Canary Deployment w Kubernetes.
Canary deployment polega na stopniowym wdrażaniu nowej wersji aplikacji do wybranej grupy użytkowników, aby zapewnić 
stabilność przed pełnym wdrożeniem. W Kubernetes można to osiągnąć, wdrażając nową wersję aplikacji (wersję canary) i 
stopniowo kierując mały procent ruchu do niej. Na podstawie opinii i wydajności, ruch jest stopniowo zwiększany, aż wersja 
canary będzie obsługiwać cały ruch.

124) Czym jest Rolling Update w Kubernetes i jak jest przeprowadzany?
Rolling Update to domyślna strategia aktualizacji działającej wersji aplikacji w Kubernetes. Polega na stopniowej wymianie 
instancji starej wersji aplikacji na nową. Jest to zarządzane przez kontroler Deployment w Kubernetes. Zapewnia to, że 
tylko określona liczba podów jest usuwana, a nowe są uruchamiane jednocześnie, co pozwala utrzymać dostępność aplikacji.

125) Jak wdrożyć testy "A/B" w Kubernetes?
Testy "A/B" w Kubernetes polegają na wdrożeniu dwóch wersji aplikacji (A i B), a następnie kierowaniu określonego procentu 
ruchu użytkowników do wersji B, podczas gdy reszta nadal korzysta z wersji A. Często realizuje się to za pomocą zaawansowanych 
możliwości routingu oferowanych przez kontroler "ingress" lub "mesh" usługowy, taki jak Istio, który umożliwia kierowanie 
zapytań na podstawie różnych kryteriów.

126) Czym jest "Stateful-Set" w Kubernetes i jak różni się od Deployment?
"Stateful-Set" to obiekt API w Kubernetes używany do zarządzania aplikacjami stanowymi. Jest podobny do Deployment, ponieważ 
zarządza Podami, które bazują na identycznej specyfikacji kontenerów. Jednak w przeciwieństwie do Deployment, "Stateful-Set" 
utrzymuje trwałą tożsamość dla każdego z podów. Pody te są tworzone z tej samej specyfikacji, ale nie są wymienne: każdy 
z nich ma trwały identyfikator, który jest utrzymywany nawet po przeskalowaniu.

127) Wyjaśnij wzorzec "Shadow Deployment" w Kubernetes?
Odpowiedź: Shadow deployment to wzorzec, w którym nadchodzący ruch jest duplikowany i wysyłany zarówno do starej, jak i 
nowej wersji aplikacji. Nowa wersja (shadow) przetwarza ruch równolegle, nie wpływając na rzeczywiste odpowiedzi użytkowników. 
Pozwala to na obserwację zachowania nowej wersji w warunkach rzeczywistych bez ryzyka dla faktycznej aplikacji. Kubernetes 
może zaimplementować ten wzorzec przy użyciu mesh usługowych, takich jak "Istio" lub "Linkerd".

128) Czym są "Jobs" i "CronJobs" w Kubernetes?
Kubernetes "Jobs" to sposób na uruchomienie obciążenia (podu), które nie wymaga trwałej tożsamości ani długoterminowego 
przechowywania danych. Tworzy on jeden lub więcej podów i zapewnia, że określona liczba z nich zakończy się pomyślnie. 
"CronJobs", z kolei, są podobne do "Jobs", ale umożliwiają zaplanowanie wykonania zadań w określonych czasach lub interwałach. 
Są przydatne do zadań takich jak kopie zapasowe, generowanie raportów i inne zadania cykliczne.

129) Jak Kubernetes obsługuje rollback Deploymentu?
Kubernetes umożliwia przywrócenie wcześniejszej rewizji Deploymentu, jeśli bieżący stan jest niestabilny. Działa to za 
pomocą polecenia "kubectl rollout undo". Kubernetes przechowuje historię zmian wprowadzonych do Deploymentu, a gdy rollback 
jest uruchomiony, odnosi się do tej historii i aktualizuje Deployment do poprzedniej wersji.

130) Czym jest podejście deklaratywne w Kubernetes Deployments?
Podejście deklaratywne w Kubernetes polega na definiowaniu pożądanego stanu aplikacji w plikach konfiguracyjnych YAML 
lub JSON, a następnie używaniu Kubernetes do osiągnięcia tego stanu. Zamiast określać szereg kroków do wykonania, 
definiujesz pożądany stan i pozwalasz Kubernetesowi zarządzać procesem osiągania tego stanu. To podejście jest kluczowe 
dla Kubernetes i pozwala na większą skalowalność oraz łatwiejsze zarządzanie.

131) Czym jest autoskalowanie w Kubernetes i dlaczego jest ważne?
Autoskalowanie w Kubernetes odnosi się do automatycznego dostosowywania liczby podów lub węzłów w klastrze Kubernetes, 
w zależności od wymagań obciążenia. Jest to kluczowe dla efektywnego zarządzania zasobami, zapewnienia wydajności aplikacji 
oraz redukcji kosztów. Autoskalowanie pomaga w obsłudze wzrostów ruchu i zmniejszaniu zasobów w okresach niskiego zużycia.

132) Wyjaśnij "Horizontal Pod Autoscaling (HPA)" w Kubernetes.
"Horizontal Pod Autoscaling (HPA)" automatycznie skaluje liczbę podów w "deployment", "replica set" lub "stateful set" na 
podstawie obserwowanego wykorzystania CPU, lub innych wybranych metryk. "HPA" dostosowuje liczbę podów w kontrolerze 
replikacji, "deployment" lub "replica set", w zależności od obserwowanego wykorzystania CPU lub, przy wsparciu metryk 
niestandardowych, innych metryk.

133) Czym jest "Vertical Pod Autoscaler (VPA)" i jak działa?
"Vertical Pod Autoscaler (VPA)" automatycznie dostosowuje rezerwacje CPU i pamięci dla podów w klastrze Kubernetes. 
Zwiększa lub zmniejsza limity i żądania CPU oraz pamięci w zależności od wykorzystania. Pomaga to w optymalizacji zasobów 
potrzebnych dla podów i jest szczególnie przydatne w przypadku obciążeń, których wymagania zasobów zmieniają się w czasie.

134) Jak działa "Cluster Autoscaler" w Kubernetes?
"Cluster Autoscaler" automatycznie dostosowuje rozmiar klastra Kubernetes, dodając lub usuwając węzły w zależności od 
wymagań obciążeń. Monitoruje dostępność podów oraz wykorzystanie węzłów, a następnie skaluje węzły w górę, gdy pody nie 
mogą zostać uruchomione z powodu ograniczeń zasobów, i skaluje w dół niedostatecznie wykorzystane węzły w celu 
optymalizacji kosztów.

135) Omów rolę "Metrics Server" w autoskalowaniu Kubernetes.
"Metrics Server" w Kubernetes zbiera dane o wykorzystaniu zasobów, takie jak CPU i pamięć, z każdego węzła i podu w klastrze. 
Dane te są następnie wykorzystywane przez komponenty, takie jak "Horizontal Pod Autoscaler (HPA)" i 
"Vertical Pod Autoscaler (VPA)", do podejmowania decyzji o skalowaniu. Jest to agregator danych o wykorzystaniu zasobów 
w całym klastrze.

136) Czym są metryki niestandardowe i zewnętrzne w autoskalowaniu Kubernetes?
Metryki niestandardowe to metryki zdefiniowane przez użytkownika w obrębie klastra, natomiast metryki zewnętrzne to metryki 
pochodzące spoza klastra. Obie mogą być wykorzystywane z "Horizontal Pod Autoscaler (HPA)" do skalowania obciążeń na 
podstawie bardziej złożonych metryk, takich jak liczba zapytań na sekundę, długość kolejki czy inne metryki specyficzne 
dla aplikacji, zamiast tylko zużycia CPU i pamięci. 

137) Jak skonfigurować autoskalowanie w Kubernetes?
Autoskalowanie w Kubernetes konfiguruje się za pomocą zasobów "HPA" lub "VPA". Określasz zasób docelowy (np. Deployment), 
metryki, które mają być użyte do skalowania (np. wykorzystanie CPU), oraz minimalną i maksymalną liczbę podów. W przypadku 
"Cluster Autoscaler" obejmuje to skonfigurowanie klastra z określonymi możliwościami dostawcy chmurowego i ustawienie 
parametrów, kiedy dodawać lub usuwać węzły.

138) Wyjaśnij różnicę między skalowaniem poziomym a pionowym?
Skalowanie poziome (scalowanie na zewnątrz/wewnątrz) polega na dodawaniu lub usuwaniu instancji podów w celu zmiany 
pojemności obsługi. Skalowanie pionowe (scalowanie w górę/w dół) oznacza natomiast dodawanie większej ilości zasobów 
(CPU, pamięci) do istniejących podów. Skalowanie poziome polega na zmianie liczby komponentów, podczas gdy skalowanie 
pionowe polega na zmianie rozmiaru każdego komponentu.

139) Czy autoskalowanie w Kubernetes może działać z aplikacjami stanowymi?
Choć autoskalowanie w Kubernetes jest zazwyczaj lepiej dopasowane do aplikacji bezstanowych, może również działać z 
aplikacjami stanowymi. Jednak wymaga to starannego zarządzania, aby zapewnić, że akcje skalowania nie zakłócą stanu aplikacji, 
takich jak wymagania dotyczące przechowywania danych, zarządzanie sesjami itp.

140) Jakie czynniki należy wziąć pod uwagę przy konfiguracji autoskalowania?
Przy konfiguracji autoskalowania ważne jest, aby uwzględnić takie czynniki, jak rodzaj obciążenia (stanowe lub bezstanowe), 
metryki, które będą używane do podejmowania decyzji o skalowaniu, minimalne i maksymalne progi skalowania, reaktywność 
aplikacji na akcje skalowania oraz wpływ na koszty. Ważne jest również monitorowanie i dostosowywanie konfiguracji 
autoskalowania, aby dopasować ją do rzeczywistych wzorców obciążenia.

141) Czym są "namespaces" w Kubernetes i jak odnoszą się do bezpieczeństwa?
"Namespaces" w Kubernetes to sposób podziału zasobów klastra między wielu użytkowników. Z perspektywy bezpieczeństwa 
zapewniają one logiczne rozdzielenie zasobów klastra, umożliwiając wdrażanie polityk, limitów i uprawnień na poziomie 
każdego "namespace". Pomaga to w tworzeniu środowiska "multi-tenant" z kontrolowanym dostępem do zasobów.

142) Wyjaśnij "Role-Based Access Control (RBAC)" w Kubernetes.
RBAC w Kubernetes to metoda regulowania dostępu do zasobów na podstawie ról poszczególnych użytkowników w organizacji. 
Umożliwia administratorom definiowanie ról z określonymi uprawnieniami (takimi jak odczyt, zapis, usunięcie) i przypisywanie 
tych ról do użytkowników, grup lub kont serwisowych. "RBAC" zapewnia, że użytkownicy mają dostęp tylko do zasobów, których 
potrzebują, zgodnie z zasadą najmniejszych uprawnień ("least privilege").

143) Czym jest "Pod Security Policy (PSP)" w Kubernetes?
"Pod Security Policy (PSP)" to zasób na poziomie klastra, który kontroluje aspekty bezpieczeństwa w specyfikacji podu. 
PSP definiują zestaw warunków, które muszą być spełnione, aby pod mógł zostać zaakceptowany do systemu, w tym uprawnienia, 
dostęp do zasobów hosta i inne aspekty związane z bezpieczeństwem. Są one kluczowe dla utrzymania bezpiecznego środowiska 
Kubernetes.

144) Jak działają polityki sieciowe w Kubernetes?
Polityki sieciowe w Kubernetes umożliwiają definiowanie zasad dotyczących tego, które pody mogą komunikować się ze sobą 
oraz z innymi punktami końcowymi w sieci. Służą do izolowania i kontrolowania przepływu ruchu między podami a zewnętrznymi 
usługami, co zwiększa bezpieczeństwo klastra Kubernetes.

145) Jakie znaczenie ma zarządzanie obiektami "Secrets" w Kubernetes?
Zarządzanie obiektami "Secrets" w Kubernetes polega na bezpiecznym przechowywaniu i zarządzaniu wrażliwymi informacjami, 
takimi jak hasła, tokeny OAuth czy klucze SSH. Dzięki Kubernetes "Secrets" możesz kontrolować i bezpiecznie dystrybuować 
te wrażliwe dane do aplikacji uruchomionych w klastrze, bez ujawniania ich w kodzie aplikacji lub plikach konfiguracyjnych.

146) Jak działa zarządzanie certyfikatami w Kubernetes?
Kubernetes zarządza certyfikatami "TLS" dla różnych komponentów, aby zapewnić bezpieczną komunikację wewnątrz klastra. 
Kubernetes "Certificate Authority (CA)" wydaje certyfikaty dla węzłów, serwera API i innych komponentów. Administratorzy 
mogą także zarządzać i rotować tymi certyfikatami, zapewniając, że komunikacja pozostaje bezpieczna, a certyfikaty są 
zawsze ważne.

147) Omów najlepsze praktyki dotyczące bezpieczeństwa w Kubernetes.
Najlepsze praktyki dotyczące bezpieczeństwa w Kubernetes obejmują stosowanie "RBAC" do kontroli dostępu, ograniczanie 
uprawnień do zasobów za pomocą "namespaces", zabezpieczanie sieci klastra za pomocą polityk sieciowych, stosowanie 
"Pod Security Policies", regularne aktualizowanie i łatanie ("patching") Kubernetes, korzystanie z bezpiecznych kanałów 
komunikacyjnych oraz wdrażanie solidnej strategii zarządzania tajemnicami. Regularne audyty bezpieczeństwa oraz 
przestrzeganie benchmarków bezpieczeństwa, takich jak "CIS Kubernetes Benchmark", są również kluczowe.

148) Jaka jest rola bezpieczeństwa obrazów ("Image Security") kontenerów w Kubernetes?
Bezpieczeństwo obrazów ("Image Security") kontenerów w Kubernetes polega na zapewnieniu, że obrazy kontenerów używane w 
klastrze są wolne od podatności ("vulnerabilities"). Obejmuje to skanowanie obrazów pod kątem podatności, podpisywanie 
obrazów w celu zapewnienia ich integralności, korzystanie z zaufanych rejestrów obrazów oraz wdrażanie polityk, które 
pozwalają tylko na obrazy spełniające określone standardy bezpieczeństwa.

149) Jak implementować logowanie i monitorowanie bezpieczeństwa w Kubernetes?
Implementacja logowania i monitorowania w Kubernetes polega na zbieraniu i analizowaniu logów z różnych komponentów, 
takich jak serwer API Kubernetes, węzły i kontenery, w celu wykrywania i reagowania na incydenty bezpieczeństwa. 
Narzędzia takie jak "ELK Stack (Elasticsearch, Logstash, Kibana)" lub "Prometheus" i "Grafana" są powszechnie 
wykorzystywane do tego celu.

150) Wyjaśnij znaczenie zgodności i audytów bezpieczeństwa ("Compliance and Security Auditing") w Kubernetes.
Zgodność i audyty bezpieczeństwa w Kubernetes polegają na regularnej ocenie klastra i jego komponentów pod kątem standardów 
bezpieczeństwa i najlepszych praktyk. Pomaga to w identyfikowaniu i łagodzeniu ryzyk bezpieczeństwa, zapewnianiu zgodności 
z normami branżowymi i regulacjami oraz utrzymaniu ogólnej postawy bezpieczeństwa w środowisku Kubernetes.

151) Jakie znaczenie ma monitorowanie i logowanie w Kubernetes?
Monitorowanie i logowanie w Kubernetes są kluczowe dla utrzymania zdrowia, wydajności i bezpieczeństwa aplikacji oraz 
samego klastra Kubernetes. Monitorowanie pomaga w śledzeniu metryk wydajności i stanu podów, węzłów oraz innych zasobów, 
umożliwiając proaktywne rozwiązywanie problemów i optymalizację. Logowanie dostarcza informacji na temat zachowań i wyników 
aplikacji oraz komponentów Kubernetes, co wspomaga debugowanie i audytowanie.

152) Wyjaśnij kluczowe metryki do monitorowania w klastrze Kubernetes?
Kluczowe metryki do monitorowania w klastrze Kubernetes obejmują wykorzystanie zasobów (CPU, pamięć, dysk, sieć), 
status podów, stan węzłów, status wdrożenia, metryki serwera API oraz wskaźniki błędów. Monitorowanie tych metryk pomaga 
zapewnić optymalną wydajność klastra i niezawodność uruchomionych aplikacji.

153) Jakie narzędzia są powszechnie używane do monitorowania klastrów Kubernetes?
Powszechnie używane narzędzia do monitorowania Kubernetes to "Prometheus" do zbierania metryk i generowania alertów, 
"Grafana" do wizualizacji danych, "c-Advisor" do monitorowania kontenerów oraz wbudowane narzędzia monitorujące Kubernetes, 
takie jak "Metrics Server". Te narzędzia pomagają w zbieraniu, analizowaniu i wizualizowaniu danych o wydajności z 
klastra Kubernetes.

154) Jak działa "Prometheus" w środowisku Kubernetes?
"Prometheus" zbiera i przechowuje metryki jako dane szeregów czasowych ("time series"). W środowisku Kubernetes zbiera 
metryki ze skonfigurowanych punktów końcowych, takich jak metryki udostępniane przez węzły i pody Kubernetes. "Prometheus" 
używa potężnego ("powerful") języka zapytań ("PromQL") do przetwarzania danych i wspiera reguły alertów, które powiadamiają 
o określonych warunkach.

155) Czym są "Daemon-Sets" i jak są używane w monitorowaniu?
"Daemon-Sets" są używane w Kubernetes, aby zapewnić, że kopia poda działa na wszystkich (lub wybranych) węzłach w klastrze. 
W monitorowaniu "Daemon-Sets" są często używane do wdrażania agentów monitorujących na poziomie węzła, takich jak kolektory 
logów lub monitory użycia zasobów, zapewniając spójność monitorowania w całym klastrze.

156) Opisz rolę "Grafany" w monitorowaniu Kubernetes.
"Grafana" to platforma open-source do monitorowania i obserwowalności, szeroko stosowana w Kubernetes. Umożliwia zapytania, 
wizualizację, tworzenie alertów i zrozumienie metryk. "Grafana" jest często używana w połączeniu z "Prometheusem" do 
tworzenia kompleksowych pulpitów nawigacyjnych, które dostarczają wizualnych informacji na temat wydajności i stanu 
zdrowia klastrów Kubernetes.

157) Jak implementowane jest logowanie w Kubernetes?
Logowanie w Kubernetes zazwyczaj realizowane jest za pomocą kombinacji agentów logujących, backendów przechowywania i 
narzędzi wizualizacyjnych. Powszechne praktyki to używanie "fluentd" lub "Logstash" jako agentów przesyłających logi, 
"Elasticsearch" jako backendu przechowywania oraz "Kibana" do wizualizacji danych logów. Logi mogą być zbierane na 
poziomie węzła, poda oraz aplikacji.

158) Czym jest stos "EFK" i jak jest używany w Kubernetes?
Stos "EFK", składający się z "Elasticsearch", "Fluentd" i "Kibana", to popularny stos do logowania używany w Kubernetes. 
"Elasticsearch" to silnik wyszukiwania i analizy, "Fluentd" to kolektor i przesyłacz logów, a "Kibana" to narzędzie do 
wizualizacji. Stos ten jest używany do efektywnego agregowania, przechowywania i wizualizowania logów z całego klastra 
Kubernetes.

159) Wyjaśnij, jak zdarzenia Kubernetes ("Kubernetes Events") są przydatne w monitorowaniu?
Zdarzenia Kubernetes to obiekty, które dostarczają informacji o tym, co dzieje się wewnątrz klastra, na przykład, jakie 
decyzje zostały podjęte przez "scheduler", dlaczego niektóre pody zostały usunięte z węzła lub dlaczego niektóre pody 
są niezdrowe. Monitorowanie tych zdarzeń pomaga w zrozumieniu zmian stanu w klastrze i może być kluczowe przy 
rozwiązywaniu problemów.

160) Omów najlepsze praktyki skutecznego monitorowania i logowania w Kubernetes?
Najlepsze praktyki obejmują konfigurację kompleksowych i istotnych alertów, tworzenie pulpitów nawigacyjnych do 
wizualizacji kluczowych metryk, logowanie na odpowiednim poziomie (ani zbyt szczegółowe, ani zbyt ogólne), zapewnienie, 
że logi są strukturalne i scentralizowane, monitorowanie metryk na poziomie klastra i aplikacji, oraz regularne 
przeglądanie i aktualizowanie konfiguracji monitorowania i logowania, aby dostosować je do zmian w klastrze i aplikacjach.

161) Czym jest zarządzanie zasobami ("Resource Management") w Kubernetes i dlaczego jest to ważne?
Zarządzanie zasobami w Kubernetes polega na przydzielaniu i zarządzaniu zasobami obliczeniowymi, takimi jak CPU i pamięć, 
dla podów i kontenerów. Jest to kluczowe dla zapewnienia efektywnego wykorzystania zasobów sprzętowych, utrzymania 
wydajności aplikacji oraz unikania rywalizacji o zasoby między aplikacjami. Właściwe zarządzanie zasobami pomaga w 
maksymalizacji efektywności i stabilności zarówno aplikacji, jak i klastra Kubernetes.

162) Jak określa się żądania i limity zasobów w Kubernetes?
Żądania i limity zasobów określa się w specyfikacji poda. Żądanie to ilość zasobu, którą Kubernetes gwarantuje dla poda, 
podczas gdy limit to maksymalna ilość, którą pod może wykorzystać. Jeśli pod przekroczy swój limit zasobów, może zostać 
zakończony lub spowolniony, w zależności od rodzaju zasobu. Poprawne ustawienie tych wartości jest kluczowe dla niezawodnej 
i efektywnej pracy zarówno aplikacji, jak i klastra.

163) Wyjaśnij pojęcie "Quality of Service (QoS)" w Kubernetes?
Kubernetes używa klas "Quality of Service (QoS)" do podejmowania decyzji o harmonogramowaniu ("scheduling") i usuwaniu podów. 
Istnieją trzy klasy "QoS": "Guaranteed", "Burstable" i "BestEffort". 
Pody klasy "Guaranteed" mają najwyższy priorytet i są ostatnie do usunięcia w przypadku niedoboru zasobów, pody klasy 
"Burstable" mają zapewnione minimalne zasoby, ale mogą korzystać z większej ilości, jeśli są dostępne, natomiast pody 
klasy "BestEffort" nie mają żadnej gwarancji zasobów i są pierwsze do usunięcia.

164) Jaka jest rola harmonogramu ("Scheduler") w zarządzaniu zasobami w Kubernetes?
Harmonogram ("Scheduler") Kubernetes jest odpowiedzialny za przypisywanie podów do węzłów. Decyzje te podejmowane są na 
podstawie dostępności zasobów, żądań i limitów zasobów poda, zasad "affinity/anti-affinity", zanieczyszczeń ("taints") i 
tolerancji ("tolerations") oraz innych kryteriów harmonogramowania. Harmonogram zapewnia, że każdy pod zostanie umieszczony 
na węźle, który ma wystarczające zasoby, aby spełnić jego potrzeby.

165) Jak działają "LimitRanges" w Kubernetes?
"LimitRanges" to polityka ograniczająca przydziały zasobów (CPU i pamięci) w przestrzeni nazw ("namespace"). 
Mogą ustalać wartości domyślne i maksymalne dla zasobów na pod lub kontener, a także zapewniać, że żądania zasobów przez 
pody mieszczą się w określonym zakresie. "LimitRanges" pomagają w zarządzaniu zużyciem zasobów w środowiskach 
wielodomenowych ("multi-tenant") i zapobiegają nadmiernemu wykorzystaniu zasobów przez pojedynczą przestrzeń nazw lub aplikację.

166) Opisz "Node Affinity" w Kubernetes?
"Node Affinity" w Kubernetes to zbiór zasad używanych przez harmonogram ("scheduler") do określenia, na którym węźle pod 
może zostać umieszczony. Pozwala to na ograniczenie, na których węzłach pod może zostać zaplanowany, w zależności od 
etykiet przypisanych do węzłów. Na przykład, możesz zapewnić, że pod będzie uruchamiany na węźle z określoną konfiguracją 
CPU lub pamięci, lub w określonej lokalizacji geograficznej.

167) Czym jest "Resource Quota" w Kubernetes?
"Resource Quotas" to narzędzie w Kubernetes, które umożliwia administratorom ograniczenie łącznego zużycia zasobów w 
przestrzeni nazw ("namespace"). Mogą one ustalać twarde limity na takie zasoby, jak całkowita ilość pamięci lub CPU, 
które mogą być używane przez wszystkie pody w przestrzeni nazw, liczba podów, usług ("Services") lub żądań wolumenów 
trwałych ("PersistentVolumeClaims") w przestrzeni nazw i inne. Jest to szczególnie przydatne w klastrach wielodomenowych 
("multi-tenant"), aby zapobiec monopolizowaniu zasobów klastra przez jednego użytkownika.

168) Jak Kubernetes radzi sobie z nadmiernym przydzielaniem zasobów?
Kubernetes pozwala na nadmierne przydzielanie zasobów, aby maksymalizować wykorzystanie zasobów. Nadmierne przydzielanie 
oznacza planowanie większej liczby podów na węźle, niż węzeł faktycznie dysponuje zasobami, na podstawie założenia, 
że nie wszystkie pody będą stale wykorzystywać swoje przydzielone zasoby. Jednak jeśli zapotrzebowanie na zasoby przekroczy 
rzeczywiście dostępne zasoby, Kubernetes polega na klasach "QoS" i limitach zasobów, aby określić, które pody należy 
spowolnić lub usunąć.

169) Czym są "Taints" i "Tolerations", i jak wpływają na zarządzanie zasobami?
"Taints" i "Tolerations" to mechanizm w Kubernetes, który umożliwia węzłom odpychanie ("repel") określonych podów. 
"Taints" są stosowane do węzłów, a "Tolerations" do podów. "Taints" i "Tolerations" współpracują, aby zapewnić, że pody 
nie będą planowane na niewłaściwych węzłach. Może to być użyteczne w zarządzaniu zasobami, aby zapewnić, że niektóre węzły 
będą zarezerwowane dla określonych typów obciążeń (workloads) na podstawie ich potrzeb zasobowych.

170) Omów użycie "Horizontal Pod Autoscalers" w zarządzaniu zasobami?
"Horizontal Pod Autoscalers (HPA)" automatycznie skalują liczbę podów w wdrożeniu ("deployment"), zestawie replik 
("replica set") lub zestawie stanowym ("stateful set") na podstawie obserwowanego wykorzystania CPU lub innych 
niestandardowych metryk. "HPAs" pomagają w zarządzaniu zasobami poprzez automatyczne dostosowywanie liczby podów do 
aktualnego obciążenia, zapewniając w ten sposób, że aplikacja ma niezbędne zasoby, jednocześnie unikając nadmiernego 
przydzielania zasobów.

171) Czym jest RBAC w Kubernetes i dlaczego jest to ważne?
RBAC (Role-Based Access Control) w Kubernetes to metoda regulowania dostępu do zasobów API Kubernetes na podstawie ról 
poszczególnych użytkowników lub procesów. Jest to ważne, ponieważ umożliwia precyzyjną kontrolę nad tym, kto może uzyskać 
dostęp do zasobów w klastrze i wykonywać na nich operacje, poprawiając bezpieczeństwo i minimalizując ryzyko 
nieautoryzowanego dostępu.

172) Wyjaśnij "Role" i "Role-Bindings" w "RBAC" Kubernetes?
W "RBAC" Kubernetes rola ("Role") to zbiór uprawnień, które mają zastosowanie do konkretnej przestrzeni nazw ("namespace"). 
Określa ona, jakie akcje użytkownik, grupa lub konto serwisowe może wykonywać (np. odczyt, tworzenie, edytowanie, usuwanie) 
na różnych zasobach. "Role-Binding" przyznaje uprawnienia zdefiniowane w roli ("Role") użytkownikowi, grupie lub kontu 
serwisowemu ("Service Account"). "RoleBindings" mają zastosowanie w obrębie konkretnej przestrzeni nazw.

173) Czym są "Cluster-Roles" i "Cluster-Role-Bindings"?
"Cluster-Roles" i "Cluster-Role-Bindings" są podobne do "Roles" i "Role-Bindings", ale mają zastosowanie w całym klastrze. 
"Cluster-Role" może być używana do przyznawania uprawnień w obrębie całego klastra lub do specyficznych zasobów, które 
nie są przestrzenią nazw (takich jak węzły). "Cluster-Role-Binding" przyznaje uprawnienia zdefiniowane w "Cluster-Role" 
użytkownikom, grupom lub kontom serwisowym w całym klastrze.

174) Jak tworzysz i zarządzasz politykami "RBAC" w Kubernetes?
Polityki "RBAC" w Kubernetes są tworzone i zarządzane za pomocą plików "YAML" lub "JSON", które definiują "Roles", 
"Role-Bindings", "Cluster-Roles" i "Cluster-Role-Bindings". Pliki te są stosowane do klastra za pomocą komendy "kubectl apply". 
Administratorzy mogą tworzyć niestandardowe role lub korzystać z predefiniowanych ról dostarczonych przez Kubernetes. 
Zarządzanie RBAC obejmuje także regularne przeglądanie i aktualizowanie ról oraz powiązań w razie potrzeby.

175) Omów zasadę najmniejszych uprawnień ("Least Privilege") w kontekście "RBAC" w Kubernetes?
Zasada najmniejszych uprawnień to praktyka bezpieczeństwa, która zaleca przyznawanie użytkownikom tylko tych uprawnień, 
które są niezbędne do wykonywania ich pracy. W Kubernetes "RBAC" zasada ta oznacza przypisywanie użytkownikom, grupom lub 
kontom serwisowym tylko takich ról, które przyznają im minimalne niezbędne uprawnienia. Dzięki temu zmniejsza się 
potencjalny wpływ błędów lub naruszeń bezpieczeństwa.

176) Jaka jest domyślna polityka "RBAC" w nowym klastrze Kubernetes?
W nowym klastrze Kubernetes "RBAC" jest zazwyczaj włączony domyślnie. Jednak domyślna polityka "RBAC" może się różnić w 
zależności od tego, jak klaster został skonfigurowany. Zwykle tworzone są role systemowe i powiązania dla krytycznych 
komponentów i użytkowników, ale żadne dodatkowe role lub powiązania specyficzne dla użytkowników nie są konfigurowane 
domyślnie.

177) Jak przestrzenie nazw ("Namespaces") wpływają na RBAC w Kubernetes?
Przestrzenie nazw ("Namespaces") zapewniają zakres dla ról "RBAC" i powiązań ról. Rola ("Role") lub powiązanie roli 
("Role-Binding") jest specyficzne dla przestrzeni nazw, co oznacza, że przyznaje dostęp tylko do zasobów w tej samej 
przestrzeni nazw. Natomiast "Cluster-Roles" i "Cluster-Role-Bindings" nie są ograniczone przez przestrzenie nazw i mają 
zastosowanie do całego klastra.

178) Wyjaśnij użycie kont serwisowych ("Service Accounts") z RBAC?
Konta serwisowe w Kubernetes są używane do nadawania tożsamości procesom, które działają w Podzie. W RBAC konta serwisowe 
mogą być przypisane do ról lub ról klastra ("Cluster-Roles"), co przyznaje im określone uprawnienia. Jest to szczególnie 
przydatne do nadawania odpowiedniego poziomu dostępu aplikacjom lub usługom działającym w obrębie klastra.

179) Czy można rozszerzyć lub dostosować "RBAC" w Kubernetes?
"RBAC" w Kubernetes można rozszerzać i dostosowywać poprzez tworzenie niestandardowych ról i powiązań ról. Administratorzy 
mogą definiować niestandardowe role z określonymi uprawnieniami dopasowanymi do potrzeb ich organizacji. Jednak ogólna 
struktura i funkcjonalność "RBAC" nie mogą być zmieniane, ponieważ stanowią one część rdzenia architektury Kubernetes.

180) Jakie są powszechne wyzwania w zarządzaniu "RBAC" w Kubernetes?
Powszechne wyzwania w zarządzaniu "RBAC" w Kubernetes obejmują złożoność w środowiskach o dużej skali, zapewnienie 
przestrzegania zasady najmniejszych uprawnień ("least privilege"), utrzymanie polityk "RBAC" w zgodzie ze zmieniającymi 
się wymaganiami oraz audytowanie i śledzenie zgodności. Aby sprostać tym wyzwaniom, organizacje często korzystają z 
narzędzi do zarządzania "RBAC" i wdrażają rygorystyczne polityki definiowania ról oraz przypisywania uprawnień.

181) Czym są "Volumes" w Kubernetes i dlaczego są ważne?
"Volumes" w Kubernetes służą do przechowywania danych i stanu pomiędzy restartami kontenerów w tym samym podzie. 
Są ważne, ponieważ kontenery są efemeryczne i zwykle mają system plików, który nie przetrwa poza cyklem życia kontenera. 
"Volumes" w Kubernetes obsługują wiele backendów storage'owych i pomagają w zapewnieniu trwałości danych, dzieleniu się 
danymi pomiędzy kontenerami w podzie oraz przechowywaniu konfiguracji lub sekretów.

182) Wyjaśnij różne typy "Volumes" w Kubernetes.
Kubernetes obsługuje kilka typów "volumes", takich jak:
a) "emptyDir": Prosty pusty katalog używany do przechowywania danych tymczasowych.
b) "hostPath": Używany do montowania katalogów z systemu plików węzła gospodarza.
c) "nfs": System plików "NFS (Network File System)" zamontowany w podzie.
d) "persistentVolumeClaim": Odwołanie do "Persistent Volume", używane do trwałego przechowywania danych.
e) "configMap", "secret", "downwardAPI": Specjalne typy "volumes" używane do udostępniania określonych zasobów Kubernetes 
i informacji o klastrze do podu.

183) Czym jest "Persistent Volume (PV)" i "Persistent Volume Claim (PVC)" w Kubernetes?
"Persistent Volume (PV)" to kawałek pamięci masowej w klastrze, który został przydzielony przez administratora lub 
dynamicznie przydzielony za pomocą klas storage'owych ("Storage Classes"). "Persistent Volume Claim (PVC)" to żądanie 
pamięci masowej zgłoszone przez użytkownika. Określa rozmiar, tryby dostępu i jest dopasowane do "PV". "PV" i "PVC" 
obsługują "volumes", które utrzymują dane poza cyklem życia poszczególnych podów.

184) Jak Kubernetes obsługuje dynamiczne przydzielanie pamięci masowej ("Dynamic Volume Provisioning")?
Dynamiczne przydzielanie pamięci masowej pozwala na tworzenie volumenów na żądanie. Dzieje się to za pomocą 
"StorageClasses", które są szablonami opisującymi sposób tworzenia volume. Kiedy tworzone jest "PVC" i żąda określonej 
"StorageClass", nowy volume jest tworzony zgodnie z tym szablonem i powiązany z "PVC".

185) Czym są "StorageClasses" w Kubernetes? Jak działają?
"StorageClasses" w Kubernetes umożliwiają opisanie różnych „klas” pamięci masowej. Pozwalają administratorom zdefiniować 
różne typy pamięci masowej (takie jak "SSD", "HDD" czy pamięć sieciowa) o różnych charakterystykach, a użytkownikom 
wybierać klasę pamięci za pomocą "PVC". "StorageClass" definiuje "provisioner", parametry oraz "reclaimPolicy".

186) Czy możesz wyjaśnić, jak dzielić dane między kontenerami w podzie?
Dane mogą być dzielone między kontenerami w tym samym podzie za pomocą volumenów. Częstym typem volume wykorzystywanym w 
tym celu jest "emptyDir". "Volume emptyDir" jest tworzony, gdy pod jest przypisany do węzła i istnieje tak długo, jak długo 
pod działa. Kontenery w podzie mogą odczytywać i zapisywać te same pliki w "volume emptyDir".

187) Jak skonfigurować pod do używania "PVC"?
Aby skonfigurować pod do używania "PVC", należy określić "PVC" w pliku manifestu poda. "PVC" jest montowane jako "volume" 
wewnątrz kontenera. W definicji poda należy odwołać się do "PVC" po nazwie w sekcji "volumes", a następnie określić ścieżkę
montowania wewnątrz kontenera w sekcji "volumeMounts".

188) Co to jest "Reclaim Policy" w pamięci masowej Kubernetes?
"Reclaim Policy" w Kubernetes określa, co dzieje się z "Persistent Volume", gdy "PVC" zostaje zwolnione. Opcje to:
a) "Retain" (zachowanie danych i volume w nienaruszonym stanie).
b) "Delete" (usunięcie zarówno "PV", jak i powiązanej pamięci masowej w backendzie).
c) "Recycle" (przestarzała opcja, która usuwa dane i ponownie udostępnia volume do nowego żądania).

189) Omów tryby dostępu w pamięci masowej Kubernetes ("Access Modes in Kubernetes Storage")?
Tryby dostępu w Kubernetes określają, jak "Persistent Volume" może być dostępny z węzła. Podstawowe tryby dostępu to:
a) "Read-Write-Once" (volume może być zamontowany jako odczyt-zapis przez jeden węzeł).
b) "Read-Only-Many" (volume może być zamontowany jako tylko do odczytu przez wiele węzłów).
c) "Read-Write-Many" (volume może być zamontowany jako odczyt-zapis przez wiele węzłów).

190) Jak Kubernetes zapewnia trwałość danych podczas ("Rescheduling") poda?
Kubernetes zapewnia trwałość danych podczas przekształcania ("resheduling") poda za pomocą "Persistent Volumes (PV)". 
"PV" istnieje niezależnie od cyklu życia poda, więc gdy pod jest usuwany lub przekształcany na inny węzeł, dane w "PV" 
pozostają zachowane. "PVC" zapewnia, że gdy pod jest przekształcany lub odtwarzany, może ponownie podłączyć się do swoich 
danych przechowywanych w "PV".

191) Jaka jest rola "schedulera" w Kubernetes?
"Scheduler" w Kubernetes odpowiada za przydzielanie nowo utworzonych lub niezaplanowanych podów do węzłów w klastrze. 
Decyzję tę podejmuje na podstawie kilku czynników, takich jak wymagania dotyczące zasobów, ograniczenia 
sprzętowe, oprogramowania, polityk, specyfikacje "affinity" i "anti-affinity", lokalizacja danych oraz zakłócenia między 
zadaniami roboczymi.

192) Jak "Scheduler" w Kubernetes zapewnia umiejscowienie poda zgodnie z wymaganiami dotyczącymi zasobów?
"Scheduler" w Kubernetes dopasowuje wymagania zasobów, takie jak CPU i pamięć określone w specyfikacji poda, 
do dostępnych zasobów na węzłach. Zapewnia, że pod jest zaplanowany na węźle tylko wtedy, gdy węzeł ma wystarczające 
dostępne zasoby, aby spełnić wymagania poda.

193) Czym są "Taints" i "Tolerations" w Kubernetes i jak wpływają na planowanie?
"Taints" i "tolerations" współpracują, aby zapewnić, że pody nie będą planowane na niewłaściwych węzłach. "Taint" jest 
stosowany do węzła i oznacza go w taki sposób, że odpycha określoną grupę podów, chyba że te pody tolerują ten "taint". 
"Tolerations" są stosowane do podów i pozwalają (ale nie wymagają), aby pody były planowane na węzłach z odpowiadającymi 
"taintami".

194) Wyjaśnij "Node Affinity" i "Anti-affinity" w Kubernetes.
"Node affinity" i "anti-affinity" to zasady używane przez "scheduler" w Kubernetes do umieszczania podów na węzłach na 
podstawie etykiet ("labels") przypisanych do tych węzłów. Zasady "affinity" przyciągają ("attract") pody do węzłów z 
określonymi etykietami, podczas gdy zasady "anti-affinity" odpychają ("repel") pody od węzłów z określonymi etykietami. 
Używa się tego, aby zapewnić, że pody są planowane zgodnie z wymaganiami biznesowymi, bezpieczeństwa lub zgodności.

195) Jak można wpływać na decyzje dotyczące planowania poda oprócz używania "Taints", "Tolerations" i "Affinity"?
Oprócz "Taints", "Tolerations" i "Affinity", na decyzje dotyczące planowania poda można wpływać za pomocą:
a) "Node Selectors": Prosty sposób ograniczenia poda do węzłów z określonymi etykietami.
b) "Resource Limits" i "Requests": Definiowanie wymagań dotyczących zasobów w specyfikacji poda.
c) "Pod Priority" i "Preemption": Przypisywanie priorytetu podom i umożliwienie podom o wyższym priorytecie preemptowania 
poda o niższym priorytecie.
d) "Custom Scheduler": Implementowanie schedulera dopasowanego do określonych potrzeb lub polityk.

196) Co się dzieje, jeśli pod nie może zostać zaplanowany ("Scheduled")?
Jeśli pod nie może zostać zaplanowany (np. z powodu ograniczeń zasobów, "taints" lub zasad "affinity"), pozostaje w stanie 
"Pending". Scheduler nadal próbuje znaleźć odpowiedni węzeł dla poda, dopóki nie zostanie on pomyślnie zaplanowany lub 
pod nie zostanie usunięty.

197) Jak Kubernetes radzi sobie z niepowodzeniem w planowaniu poda ("Scheduling Failure")?
W przypadku niepowodzenia planowania ("Scheduling Failure"), Kubernetes dostarcza informacje zwrotne na temat przyczyny, 
dla której pod nie mógł zostać zaplanowany. Można je uzyskać za pomocą polecenia"kubectl describe pod NAME", 
które wyświetla zdarzenia, w tym wszelkie błędy planowania. Administratorzy klastra mogą wykorzystać te informacje do 
diagnozowania i rozwiązywania problemów związanych z planowaniem poda.

198) Co to jest "DaemonSet" i czym wyróżnia się jego planowanie?
"DaemonSet" zapewnia, że kopia poda działa na wszystkich (lub wybranych) węzłach w klastrze. Kiedy węzły są dodawane do 
klastra, pody są automatycznie na nie dodawane. Gdy węzły są usuwane, te pody są usuwane przez garbage collector. 
"DaemonSets" są zazwyczaj używane do monitorowania węzłów, zbierania logów lub uruchamiania demonów na poziomie systemu.

199) Czy można zaplanować uruchamianie Podów na konkretnych węzłach w zależności od ich specyfikacji sprzętowych (CPU/GPU)?
Tak, można zaplanować uruchamianie Podów na węzłach w zależności od określonych wymagań sprzętowych, używając etykiet 
("labels") węzłów oraz selektorów węzłów lub "affinities" (preferencji). Węzły mogą być oznaczone etykietami zgodnymi 
z ich specyfikacjami sprzętowymi, a Pody można skonfigurować tak, aby były uruchamiane tylko na węzłach, które pasują do 
tych etykiet.

200) Czym są "Pod Disruption Budgets (PDB)" i jak wpływają na planowanie?
"Pod Disruption Budget (PDB)" to polityka, która ogranicza liczbę Podów aplikacji, które mogą być niedostępne jednocześnie 
podczas dobrowolnych zakłóceń (takich jak aktualizacje). "PDB" są brane pod uwagę przez scheduler Kubernetes, aby zapobiec 
ewakuacjom, które naruszałyby budżet, zapewniając wysoką dostępność podczas operacji konserwacyjnych.

201) Czym jest "kubectl" i jaka jest jego rola w Kubernetes?
"kubectl" to narzędzie wiersza poleceń do interakcji z serwerem API Kubernetes. Umożliwia użytkownikom wdrażanie aplikacji, 
przeglądanie i zarządzanie zasobami klastra oraz oglądanie logów. "kubectl" przekształca polecenia wiersza poleceń w 
wywołanie API i komunikuje się z klastrem Kubernetes w celu wykonania tych żądań.

202) Jak tworzysz i zarządzasz zasobami w Kubernetes za pomocą "kubectl"?
Zasoby w Kubernetes można tworzyć za pomocą "kubectl", stosując pliki konfiguracyjne w formacie "YAML" lub "JSON". 
Polecenia takie jak "kubectl create", "apply", "delete" i "edit" służą do zarządzania tymi zasobami. Na przykład, 
polecenie "kubectl apply -f deployment.yaml" utworzy lub zaktualizuje zasoby zdefiniowane w pliku "deployment.yaml".

203) Jak używać "kubectl" do debugowania Podów i Usług?
"kubectl" oferuje różne polecenia do debugowania, takie jak: 
a) "kubectl logs", aby pobrać logi z kontenera w Podzie. 
b) "kubectl describe", aby zobaczyć szczegółowe informacje o zasobach. 
c) "kubectl exec", aby wykonać polecenia wewnątrz kontenera. 
d) "kubectl get", aby wyświetlić listę zasobów i sprawdzić ich status. 
Te polecenia pomagają w diagnozowaniu problemów i zrozumieniu stanu klastra.

204) Jak skalujesz aplikacje za pomocą "kubectl"?
Aplikacje w Kubernetes można skalować za pomocą "kubectl scale". Na przykład, polecenie: 
"kubectl scale deployment my-deployment --replicas=5", zmienia liczbę replik w Deployment "my-deployment" na "5". 
Dzięki temu dostosowuje się liczbę działających Podów, co umożliwia skalowanie w górę lub w dół w zależności od wymagań.

205) Jaki jest proces aktualizacji aplikacji za pomocą "kubectl"?
Aplikacje w Kubernetes są aktualizowane za pomocą "Rolling Updates", które zapewniają brak przestojów. Można to 
zrobić za pomocą "kubectl", aktualizując obraz lub konfigurację Deployment. Na przykład, polecenie: 
"kubectl set image deployment/my-deployment my-container=newimage", aktualizuje obraz kontenera, uruchamiając "Rolling Update".

206) Jak monitorować stan zdrowia i status klastra Kubernetes za pomocą "kubectl"?
"kubectl" oferuje polecenia takie jak "kubectl get", które pozwala wyświetlić zasoby i sprawdzić ich status, 
"kubectl describe", które umożliwia uzyskanie szczegółowych informacji o zasobie, oraz "kubectl top", które pokazuje 
wykorzystanie zasobów. Te polecenia pomagają monitorować stan zdrowia i wydajność klastra oraz jego komponentów.

207) Czy możesz wyjaśnić, jak używać kontekstów i konfiguracji w "kubectl"?
Konteksty w "kubectl" służą do przełączania między różnymi klastrami i przestrzeniami nazw. Polecenie "kubectl config"
jest używane do zarządzania plikami "kube-config", które przechowują konfiguracje klastra, użytkownika i kontekstu. 
Na przykład, polecenie "kubectl config use-context my-context", przełącza na inny klaster lub przestrzeń nazw zapisane 
w pliku "kube-config".

208) Jak "kubectl" współpracuje z API Kubernetes?
"kubectl" współpracuje z API Kubernetes, wysyłając żądania HTTP do serwera API. Argumenty i flagi w poleceniach "kubectl" 
są konwertowane na ścieżki zasobów API i parametry zapytań. "kubectl" obsługuje uwierzytelnianie ("authentication"), 
agreguje odpowiedź API i wyświetla wynik użytkownikowi.

209) Jaka jest rola "kubectl" w pipeline "CI - CD"?
W pipeline "CI - CD", "kubectl" jest używane do wdrażania aplikacji, zarządzania zasobami i zapewnienia pożądanego stanu 
klastra na różnych etapach pipeline'u. Może być używane w skryptach automatyzujących i zintegrowane z narzędziami "CI - CD" 
do stosowania konfiguracji, aktualizacji obrazów i wdrażania zmian w klastrze Kubernetes.

210) Omów zaawansowane polecenia "kubectl" i ich zastosowanie?
Zaawansowane polecenia "kubectl" to: 
a) "kubectl port-forward", do przekierowywania jednego lub więcej lokalnych portów do Poda. 
b) "kubectl proxy", do tworzenia serwera proxy między twoją maszyną a serwerem API Kubernetes. 
c) "kubectl cp", do kopiowania plików i katalogów do i z kontenerów w Podach. 
d) "kubectl patch", do aktualizowania obiektów Kubernetes w miejscu. 
Te polecenia zapewniają większą kontrolę nad interakcją i zarządzaniem zasobami Kubernetes.

211) Czym jest "Kustomize" i jak jest zintegrowane z Kubernetes?
"Kustomize" to samodzielne narzędzie do dostosowywania obiektów Kubernetes za pomocą pliku "kustomization". Wprowadza ono 
sposób dostosowywania konfiguracji aplikacji bez szablonów, co upraszcza korzystanie z gotowych aplikacji. Zintegrowane 
bezpośrednio z "kubectl" od wersji Kubernetes "v 1 14", Kustomize pozwala użytkownikom na modyfikowanie dowolnych zasobów 
API w sposób deklaratywny, przy użyciu plików nakładek, które zmieniają zasoby bez konieczności modyfikowania oryginalnych 
plików YAML.

212) Wyjaśnij koncepcję "Nakładek" ("Overlays") w Kustomize?
Nakładki ("Overlays") w Kustomize to zbiór modyfikacji, które są stosowane do podstawowych zasobów. Pozwalają one na 
utrzymanie różnych wariantów konfiguracji (np. środowisk deweloperskich, stagingowych i produkcyjnych) bez konieczności 
duplikowania zasobów. Nakładki mogą zmieniać konfiguracje dla konkretnych środowisk, modyfikując właściwości, 
dodając etykiety, zmieniając tagi obrazów itp.

213) Czym różni się "Kustomize" od Helm?
"Kustomize" i "Helm" to narzędzia używane do zarządzania konfiguracjami Kubernetes, ale różnią się podejściem. 
"Kustomize" stosuje podejście bez szablonów i dostosowuje pliki "YAML" za pomocą nakładek, podczas gdy "Helm" używa 
silnika szablonów do generowania plików "YAML" Kubernetes na podstawie szablonów. "Kustomize" koncentruje się na 
dostosowywaniu i patchowaniu obiektów Kubernetes, podczas gdy "Helm" skupia się bardziej na pakowaniu i dystrybucji 
aplikacji Kubernetes.

214) Czym jest plik "kustomization" i jaki jest jego cel?
Plik "kustomization" to plik konfiguracyjny w formacie "YAML" w "Kustomize". Określa zasoby Kubernetes, które mają zostać 
dostosowane, oraz modyfikacje do zastosowania. Może zawierać odniesienia do innych zasobów, poprawki do zastosowania oraz 
inne ustawienia, takie jak prefiksy/sufiksy nazw, etykiety i adnotacje. Plik "kustomization" służy jako punkt wejścia 
dla "Kustomize", aby zrozumieć, jak przetwarzać zasoby.

215) Jak zarządzać różnymi środowiskami Kubernetes za pomocą "Kustomize"?
"Kustomize" zarządza różnymi środowiskami za pomocą nakładek ("overlays"). Każde środowisko (np. deweloperskie, stagingowe, 
produkcyjne) ma swój katalog nakładki, który zawiera plik "kustomization" określający dostosowania specyficzne dla danego 
środowiska. To podejście pozwala na ponowne użycie podstawowej konfiguracji i zastosowanie tylko niezbędnych zmian dla 
każdego środowiska.

216) Czy "Kustomize" może dynamicznie generować konfiguracje zasobów?
Tak, "Kustomize" może dynamicznie generować konfiguracje zasobów. Może tworzyć nowe zasoby lub modyfikować istniejące na 
podstawie różnych danych wejściowych i transformacji. Obejmuje to tworzenie "ConfigMap" i "Secrets" z plików, stosowanie 
wspólnych etykiet ("Labels") oraz ustawianie lub zmienianie konkretnych pól w wielu zasobach.

217) Jaka jest rola "Poprawek" ("Patches") w "Kustomize"?
Poprawki ("Patches") w "Kustomize" służą do modyfikowania lub aktualizowania zasobów Kubernetes. Umożliwiają zmianę 
konkretnych części zasobu, takich jak dodanie kontenerów do deploymentu, aktualizacja zmiennych środowiskowych czy zmiana 
liczby replik. Poprawki ("patches") oferują potężny sposób na zastosowanie celowanych zmian bez konieczności modyfikowania 
całej definicji zasobu.

218) Jak "Kustomize" obsługuje "ConfigMap" i "Secrets"?
"Kustomize" ma specjalne funkcje do tworzenia i zarządzania "ConfigMap" i "Secrets". Może generować te zasoby z plików 
lub literali i pozwala na ich modyfikowanie za pomocą nakładek ("overlays"). "Kustomize" zapewnia, że gdy zawartość 
"ConfigMap" lub "Secret" ulegnie zmianie, dostosowuje on "hash suffix" tych zasobów, uruchamiając "rolling update", 
jeśli jest to konieczne.

219) Omów, jak "Kustomize" poprawia ponowne wykorzystanie ("Re-usability") manifestów Kubernetes?
"Kustomize" poprawia ponowne wykorzystanie manifestów Kubernetes, oddzielając podstawowe konfiguracje od dostosowań 
specyficznych dla środowisk. Ta struktura pozwala na utrzymanie jednego zbioru podstawowych manifestów, które mogą być 
ponownie używane w różnych środowiskach lub scenariuszach poprzez stosowanie nakładek ("overlays"). Zmniejsza to duplikację 
i upraszcza aktualizacje manifestów.

220) Jakie są najlepsze praktyki stosowania "Kustomize" w pipeline "CI - CD"?
W pipeline "CI - CD" najlepiej jest trzymać podstawowe konfiguracje i nakładki w systemie kontroli wersji, używać "Kustomize" 
do dynamicznego generowania ostatecznych manifestów podczas procesu "CI/CD", a następnie stosować wygenerowane manifesty 
do odpowiednich klastrów Kubernetes. Ważne jest również, aby przed wdrożeniem walidować i testować wygenerowane konfiguracje 
w procesie "CI".

221) Czym jest "Git-Ops" i jak różni się od tradycyjnego "Dev-Ops"?
"Git-Ops" to paradygmat lub zbiór praktyk, które wykorzystują "Git" jako jedyne źródło prawdy dla deklaratywnej 
infrastruktury i aplikacji. W "Git-Ops" repozytoria "Git" przechowują cały stan systemu oraz pożądany stan infrastruktury, 
który może być automatycznie stosowany i aktualizowany w docelowym środowisku. W przeciwieństwie do tradycyjnego "DevOps", 
które często wymaga ręcznych kroków, "Git-Ops" automatyzuje proces wdrażania, wykorzystując oparte na "Git workflow", 
co zwiększa wydajność, przejrzystość ("transparency") i spójność.

222) Wyjaśnij rolę Gita w "Git-Ops"?
W "Git-Ops" Git nie jest tylko systemem kontroli wersji, ale pełni rolę centralnego źródła prawdy ("source of truth") dla 
całego systemu. Wszystkie zmiany w infrastrukturze i aplikacjach są dokonywane za pomocą commitów Git. Te zmiany uruchamiają 
zautomatyzowane procesy, które stosują je do środowiska produkcyjnego. To podejście zapewnia, że repozytorium Git zawsze 
odzwierciedla aktualny stan systemu, co ułatwia śledzenie zmian, przywracanie poprzednich wersji oraz utrzymanie zgodności.

223) Jak "Git-Ops" poprawia częstotliwość i niezawodność wdrożeń?
"Git-Ops" poprawia częstotliwość i niezawodność wdrożeń poprzez automatyzację procesu wdrażania. Każda zmiana w repozytorium 
Git uruchamia proces wdrożenia, co umożliwia częste i spójne aktualizacje. Zautomatyzowane testowanie i integracja w 
ramach pipeline "Git-Ops" zapewniają, że zmiany są niezawodne ("reliable") i stabilne, zmniejszając prawdopodobieństwo 
błędów i przestojów.

224) Jakie są korzyści z używania "Git-Ops" do zarządzania Kubernetes?
"Git-Ops" oferuje kilka korzyści w zarządzaniu Kubernetes, w tym: 
a) Zautomatyzowane wdrażanie i cofanie ("Rollback") aplikacji oraz konfiguracji. 
b) Zwiększona widoczność i śledzenie zmian za pomocą Gita. 
c) Poprawiona spójność i zgodność, ponieważ repozytorium Git odzwierciedla pożądany stan. 
d) Uproszczone zarządzanie manifestami i konfiguracjami Kubernetes. 
e) Uproszczone procesy aktualizacji i skalowania klastrów Kubernetes.

225) Jak wdraża się "Git-Ops" w organizacji?
Wdrażanie "Git-Ops" obejmuje: 
a) Utworzenie repozytorium Git do przechowywania całej konfiguracji i stanu infrastruktury. 
b) Używanie narzędzi Infrastructure as Code ("I a C"), takich jak "Terraform" lub "Ansible", do definiowania infrastruktury. 
c) Konfigurowanie zautomatyzowanych pipeline'ów do testowania, integracji i wdrożenia za pomocą narzędzi takich jak 
"Jenkins", "Argo CD" lub "Flux". 
d) Wdrażanie polityk dotyczących przepływów pracy "Git" i zapewnienie, że wszystkie zmiany przechodzą przez Git. 
e) Regularne przeglądanie i aktualizowanie przepływów pracy oraz konfiguracji w razie potrzeby.

226) Jaka jest rola "Continuous Integration" i "Continuous Deployment (CI/CD)" w "Git-Ops"?
"Git-Ops", "CI/CD" odgrywa kluczową rolę w automatyzacji testowania i wdrażania zmian w kodzie. "Continuous Integration (CI)" 
polega na automatycznym testowaniu zmian w kodzie od wielu współpracowników i łączeniu ich z główną gałęzią. 
"Continuous Deployment (CD)" automatyzuje wdrażanie tych zmian do środowisk produkcyjnych. W "Git-Ops", pipeline "CI/CD" 
jest uruchamiany przez zmiany w repozytorium Git, zapewniając, że wdrożenia są spójne z kodem źródłowym.

227) Omów implikacje bezpieczeństwa związane z "Git-Ops"?
"Git-Ops" może poprawić bezpieczeństwo, zapewniając ślad audytowy zmian i umożliwiając łatwe przywracanie do znanych 
dobrych stanów. Niemniej jednak, wymaga to również silnych praktyk bezpieczeństwa związanych z dostępem do repozytorium 
Git oraz jego zarządzaniem. Bezpieczne zarządzanie sekretami ("secrets"), kontrola dostępu, podpisywanie kodu ("code signing") 
oraz regularne skanowanie w poszukiwaniu podatności w bazie kodu są niezbędne.

228) Jak "Git-Ops" ułatwia przywracanie poprzednich wersji i odzyskiwanie po awarii?
"Git-Ops" ułatwia przywracanie poprzednich wersji i odzyskiwanie po awarii, przechowując cały stan systemu w repozytorium Git. 
Jeśli pojawi się problem, system może zostać szybko przywrócony do poprzedniego stanu, poprzez cofnięcie do wcześniejszego 
commita. Takie podejście upraszcza również odzyskiwanie po awarii, ponieważ repozytorium Git może zostać użyte do 
rekonstrukcji stanu systemu.

229) Jakie narzędzia są powszechnie używane w procesie "Git-Ops"?
Do najczęściej używanych narzędzi w procesach Git-Ops należą: 
a) Systemy kontroli wersji, takie jak Git ("GitHub", "GitLab", "Bitbucket").
b) Narzędzia "Infrastructure as Code" ("Terraform", "Ansible").
c) Narzędzia do ciągłego wdrażania ("Argo CD", "Flux").
d) Narzędzia do orkiestracji kontenerów ("Kubernetes").
e) Narzędzia do monitorowania i obserwacji ("Prometheus", "Grafana").

230) Jak zarządzać konfiguracją w Git-Ops?
W "Git-Ops" zarządzanie konfiguracją odbywa się poprzez Git. Wszystkie konfiguracje są przechowywane jako kod w repozytorium. 
Zmiany w konfiguracjach wprowadzane są za pomocą commitów i pull requestów, co zapewnia proces przeglądu i ślad audytowy 
("audit trail"). Narzędzia automatyczne stosują te konfiguracje do infrastruktury, zapewniając, że rzeczywisty stan zawsze 
odpowiada stanowi zdefiniowanemu w repozytorium Git.

231) Czym jest "AKS" ("Azure Kubernetes Service") i jakie są jego kluczowe cechy?
"AKS" to zarządzana usługa orkiestracji kontenerów w chmurze "Microsoft Azure", oparta na Kubernetes. Kluczowe cechy to 
zintegrowane doświadczenia "CI - CD", bezpieczeństwo i zarządzanie na poziomie przedsiębiorstwa, uproszczone zarządzanie 
klastrami, automatyczne skalowanie oraz integracja z innymi usługami Azure.

232) Czym różni się "EKS (Amazon Elastic Kubernetes Service)" od innych usług Kubernetes?
"EKS" to zarządzana usługa Kubernetes oferowana przez Amazon. Głęboko integruje się z usługami AWS, takimi jak "IAM" do 
uwierzytelniania, "ELB" do balansowania obciążenia i "VPC" do networkingu. "EKS" wyróżnia się swoją niezawodnością i 
płynnością integracji z infrastrukturą "AWS", co czyni go silnym wyborem dla firm głęboko zaangażowanych w ekosystem "AWS".

233) Jakie są zalety korzystania z "GKE (Google Kubernetes Engine)"?
"GKE", usługa Kubernetes od Google, oferuje w pełni zarządzane doświadczenie z automatycznym provisionowaniem, naprawami 
i skalowaniem. Do najważniejszych zalet należą silna integracja z usługami "Google Cloud", zaawansowane funkcje zarządzania 
klastrami, solidne bezpieczeństwo oraz globalny rejestr kontenerów.

234) Czy można migrować istniejące klastry Kubernetes do "AKS", "EKS" lub "GKE"? Jak?
Tak, można migrować istniejące klastry Kubernetes do tych zarządzanych usług. Proces zazwyczaj obejmuje utworzenie nowego 
klastra w danej usłudze ("AKS", "EKS" lub "GKE"), skonfigurowanie sieci, przechowywania i innych ustawień specyficznych 
dla chmury, a następnie migrację obciążeń roboczych i danych. Narzędzia takie jak "kube-migrate" lub narzędzia migracyjne 
specyficzne dla dostawcy chmury mogą ułatwić ten proces.

235) Jak implementować wysoką dostępność w "AKS", "EKS" i "GKE"?
Wysoka dostępność w tych usługach polega na konfiguracjach klastrów rozproszonych na wielu strefach dostępności oraz 
automatycznych mechanizmach replikacji i przełączania awaryjnego. "AKS", "EKS" i "GKE" wspierają wdrażanie klastrów w 
różnych strefach dostępności, zapewniając odporność i wysoką dostępność obciążeń Kubernetes.

236) Jakie funkcje bezpieczeństwa są dostępne w "AKS", "EKS" i "GKE"?
Wszystkie trzy usługi oferują solidne funkcje bezpieczeństwa:
a) "AKS": Integruje się z "Azure Active Directory", oferuje polityki sieciowe, integrację z "Azure Policy" oraz 
monitorowanie bezpieczeństwa za pomocą "Azure Security Center".
b) "EKS": Integruje się z "AWS Identity and Access Management (IAM)", wspiera sieciowanie "VPC" oraz oferuje szyfrowanie 
za pomocą AWS KMS.
c) "GKE": Oferuje zintegrowane "Google Cloud IAM", używa "Container-Optimized OS" dla węzłów oraz zapewnia wbudowane 
polityki sieciowe i funkcje automatycznej aktualizacji węzłów.

237) Jak działa auto-skalowanie w AKS, EKS i GKE?
Auto-skalowanie w tych usługach umożliwia automatyczne dostosowanie rozmiaru klastrów na podstawie obciążenia:
a) "AKS": Używa zestawów skalowania maszyn wirtualnych ("VM Scale Sets") w "Azure" do auto-skalowania węzłów.
b) "EKS": Wykorzystuje grupy auto-skalowania "AWS (AWS Auto Scaling groups)".
c) "GKE": Oferuje auto-skalowanie klastra oraz auto-skalowanie wertykalne podów.

238) Wyjaśnienie modeli cenowych "AKS", "EKS" i "GKE"?
a) "AKS": Nie pobiera opłat za usługę zarządzania Kubernetes; płacisz tylko za używane maszyny wirtualne (VM), 
przestrzeń dyskową i zasoby sieciowe.
b) "EKS": Pobiera opłatę za zarządzanie dla każdego klastra Kubernetes, plus opłaty za zasoby (takie jak instancje EC2) 
wykorzystywane przez klaster.
c) "GKE": Pobiera opłatę za zarządzanie klastrem oraz za używane zasoby. Oferuje bezpłatny poziom dla klastra w jednej 
strefie z ograniczonymi zasobami.

239) Jakie są możliwości sieciowe AKS, EKS i GKE?
Wszystkie trzy usługi oferują zaawansowane możliwości sieciowe:
a) "AKS": Integruje się z "Azure Virtual Network", oferując polityki sieciowe, load balancing oraz izolację sieci.
b) "EKS": Wspiera "Amazon VPC" do zarządzania siecią, umożliwiając szczegółową kontrolę nad ustawieniami sieciowymi.
c) "GKE": Integruje się z "Google Cloud VPC", oferując zaawansowane polityki sieciowe oraz funkcje load balansingu.

240) Jak monitorować i rejestrować klastry Kubernetes w "AKS", "EKS" i "GKE"?
a) "AKS": Integruje się z "Azure Monitor" i "Log Analytics" do monitorowania i rejestrowania danych.
b) "EKS": Można monitorować za pomocą "Amazon CloudWatch" i integruje się z "AWS CloudTrail" do rejestrowania danych.
c) "GKE": Oferuje zintegrowane rejestrowanie i monitorowanie za pomocą "Stackdriver (Operations)".

241) Wyjaśnij rolę "Dockerfile".
"Dockerfile" to skrypt zawierający serię instrukcji i poleceń używanych do tworzenia obrazu kontenera. Automatyzuje 
proces budowania obrazu "Docker" ("Docker image"). "Dockerfile" definiuje, co ma się dziać w środowisku wewnątrz kontenera. 
Może zawierać instrukcje instalacji konkretnego oprogramowania, zmienne środowiskowe oraz polecenia uruchomieniowe.

242) Czym są przestrzenie nazw ("Namespaces") w Kubernetes i jaki jest ich cel?
Przestrzenie nazw w Kubernetes to sposób dzielenia zasobów klastra między wielu użytkowników i aplikacje. Zapewniają one 
zakres do grupowania i izolowania zasobów, takich jak "Pods", "Services" i "Deployments". Przestrzenie nazw są szczególnie 
przydatne w środowiskach z wieloma użytkownikami i zespołami, aby uniknąć konfliktów oraz skuteczniej zarządzać dostępem 
i zasobami.

243) Czym jest "Pod" w Kubernetes?
"Pod" to najmniejszy i najbardziej podstawowy obiekt do wdrożenia w Kubernetes. Reprezentuje pojedynczą instancję 
działającego procesu w klastrze i może zawierać jeden lub więcej kontenerów. Te kontenery w Podzie są planowane na tym 
samym węźle i dzielą tę samą przestrzeń nazw sieci ("network namespace"), adres IP oraz przestrzeń portów.

244) Czym są "Helm Charts" i jak są przydatne?
"Helm Charts" to pakiety w "Helm", które zawierają wszystkie niezbędne pliki i konfiguracje do wdrożenia aplikacji, 
narzędzia lub usługi w klastrze Kubernetes. Promują one ponowne użycie i mogą enkapsulować złożone zasoby Kubernetes, 
co ułatwia dzielenie się i wdrażanie aplikacji.

245) Wyjaśnij "Horizontal Pod Autoscaling (HPA)" w Kubernetes?
"Horizontal Pod Autoscaling (HPA)" automatycznie skaluje liczbę Podów we wdrożeniu, zestawie replik lub zestawie stanowym 
na podstawie obserwowanego wykorzystania CPU, lub innych wybranych metryk. "HPA" dostosowuje liczbę Podów w kontrolerze 
replik, wdrożeniu lub zestawie replik na podstawie obserwowanego wykorzystania CPU, lub, przy wsparciu niestandardowych 
metryk, innych metryk.

246) Czym są mikro-serwisy i jak kontenery je wspierają?
Architektura mikro-serwisów to metoda tworzenia aplikacji oprogramowania jako zestawu niezależnie wdrażalnych, modułowych 
usług. Kontenery są idealne do mikro-serwisów ze względu na swoją lekkość, pozwalając na wdrożenie każdej usługi w osobnym 
kontenerze wraz z jej zależnościami, co zapewnia izolację, efektywność zasobów i skalowalność.

247) Czym są mikro-serwisy i jak kontenery je wspierają?
Architektura mikro-serwisów to metoda tworzenia aplikacji oprogramowania jako zestawu niezależnie wdrażalnych, modułowych 
usług. Kontenery są idealne do mikro-serwisów ze względu na swoją lekkość, pozwalając na wdrożenie każdej usługi w osobnym 
kontenerze wraz z jej zależnościami, co zapewnia izolację, efektywność zasobów i skalowalność.

248) Czy Kubernetes może być zainstalowany na dowolnej platformie chmurowej? Jak?
Tak, Kubernetes może być zainstalowany na dowolnej platformie chmurowej. Większość dostawców chmurowych oferuje zarządzane 
usługi Kubernetes (takie jak "AWS - EKS", "Azure - AKS", "Google - GKE"), które upraszczają proces instalacji. Alternatywnie, 
Kubernetes można zainstalować ręcznie przy użyciu narzędzia "kubeadm" lub innych narzędzi, konfigurując maszyny wirtualne 
lub instancje w ramach infrastruktury dostawcy chmurowego.

249) Czym jest Helm i jak ma się do instalacji Kubernetes?
Helm to menedżer pakietów dla Kubernetes, który upraszcza instalowanie, konfigurowanie i aktualizowanie aplikacji w 
klastrach Kubernetes. Choć Helm nie instaluje samego Kubernetes, służy do zarządzania aplikacjami działającymi na klastrze 
Kubernetes. Helm używa formatu pakietów o nazwie charts, które są wstępnie skonfigurowanymi zasobami Kubernetes.

250) Opisz "Node Affinity" w Kubernetes?
"Node Affinity" w Kubernetes to zestaw zasad używanych przez "scheduler" do określenia, na którym węźle może zostać 
umieszczony "Pod". Pozwala to na ograniczenie, na których węzłach Twój Pod może zostać zaplanowany, na podstawie etykiet
("labels") przypisanych do węzłów. Na przykład, możesz zapewnić, że "Pod" będzie uruchamiany na węźle z określoną 
konfiguracją CPU lub pamięci, lub w konkretnej lokalizacji geograficznej.

251) Czym jest "Resource Quota" w Kubernetes?
"Resource Quotas" to narzędzie w Kubernetes, które umożliwia administratorom ograniczenie łącznego zużycia zasobów 
w przestrzeni nazw ("namespace"). Mogą ustawiać twarde limity na takie rzeczy, jak całkowita ilość pamięci lub CPU, 
które mogą być używane przez wszystkie "Pody" w danej przestrzeni nazw, liczba Podów, "Services" czy "PersistentVolumeClaims" 
w przestrzeni nazw, i inne. Jest to szczególnie przydatne w klastrach wielo-tenantowych ("multi-tenant"), aby zapobiec 
monopolizowaniu zasobów klastra przez pojedynczego użytkownika.

252) Czym jest "Kubernetes Service" i dlaczego jest to ważne?
"Kubernetes Service" to abstrakcyjny sposób na udostępnienie aplikacji uruchomionej na zestawie Podów jako usługę sieciową. 
Zapewnia ona stały i stabilny adres IP, nazwę "DNS" oraz port, a także równoważy ruch sieciowy pomiędzy Podami. Usługi 
są kluczowe dla zarządzania dostępem klientów do aplikacji, ponieważ zapewniają stabilny interfejs do dynamicznego zestawu Podów.

253) Wyjaśnij różne typy usług w Kubernetes?
Główne typy usług ("Services") w Kubernetes to:
a) "Cluster-IP": Udostępnia usługę na wewnętrznym adresie IP w klastrze, dzięki czemu jest dostępna tylko wewnątrz klastra.
b) "Node-Port": Udostępnia usługę na tym samym porcie każdego wybranego węzła ("Node") w klastrze, dzięki czemu jest 
dostępna z zewnątrz klastra.
c) "Load-Balancer": Udostępnia usługę na zewnątrz, korzystając z load balansera dostawcy chmurowego.
d) "External-Name": Mapuje usługę na zewnętrzną nazwę "DNS".

254) Jak usługi odkrywają ("Discover") i zarządzają ("Manage") ruchem do Podów?
Usługi odkrywają Pody za pomocą selektorów etykiet ("label selectors"). Kiedy definiujesz usługę, określasz zestaw etykiet, 
które pasują do grupy Podów. Usługa kieruje ruchem do tych Podów, domyślnie używając algorytmu "round-robin". Gdy Pody są 
tworzone lub usuwane, usługa automatycznie aktualizuje grupę Podów, do których kieruje ruch.

255) Czym są przestrzenie nazw ("Namespaces") w Kubernetes i jak mają się do bezpieczeństwa?
Przestrzenie nazw w Kubernetes to sposób dzielenia zasobów klastra między wielu użytkowników. Z perspektywy bezpieczeństwa 
zapewniają one logiczną separację zasobów klastra ("logical separation of cluster resources"), umożliwiając implementację 
polityk, limitów i uprawnień na poziomie każdej przestrzeni nazw. Pomaga to w tworzeniu środowiska wielo-tenantowego z 
kontrolowanym dostępem do zasobów.

256) Wyjaśnij "Role-Based Access Control (RBAC)" w Kubernetes?
"RBAC" w Kubernetes to metoda regulowania dostępu do zasobów na podstawie ról poszczególnych użytkowników w organizacji. 
Umożliwia administratorom definiowanie ról z określonymi uprawnieniami (takimi jak odczyt, zapis, usuwanie) i przypisywanie 
tych ról do użytkowników, grup lub kont serwisowych. "RBAC" zapewnia, że użytkownicy mają dostęp tylko do zasobów, których 
potrzebują, zgodnie z zasadą minimalnych uprawnień.

257) Czym jest "AKS (Azure Kubernetes Service)" i jakie ma kluczowe funkcje?
"AKS" to zarządzana usługa orkiestracji kontenerów firmy Microsoft, oparta na Kubernetes. Kluczowe funkcje to zintegrowane 
doświadczenia "Ci - Cd", bezpieczeństwo i zarządzanie na poziomie przedsiębiorstwa, uproszczone zarządzanie klastrem, 
automatyczne skalowanie oraz integracja z innymi usługami Azure.

258) Czym jest "kubectl" i jaka jest jego rola w Kubernetes?
"kubectl" to narzędzie wiersza poleceń do interakcji z serwerem API Kubernetes. Umożliwia użytkownikom wdrażanie aplikacji, 
inspekcję i zarządzanie zasobami klastra oraz przeglądanie logów. "kubectl" przekształca polecenia wiersza poleceń na 
wywołania API i komunikuje się z klastrem Kubernetes, aby wykonać te żądania.

259) Jak tworzysz i zarządzasz zasobami w Kubernetes za pomocą "kubectl"?
Zasoby w Kubernetes można tworzyć za pomocą "kubectl", stosując pliki konfiguracyjne w formacie "YAML" lub "JSON". 
Polecenia takie jak "kubectl create", "apply", "delete" i "edit" służą do zarządzania tymi zasobami. Na przykład, 
"kubectl apply -f deployment.yaml", utworzy lub zaktualizuje zasoby zdefiniowane w pliku "deployment.yaml".

260) Wyjaśnij, jak używać "kubectl" do debugowania Podów i Usług?
"kubectl" oferuje różne polecenia do debugowania, takie jak:
a) "kubectl logs", aby pobrać logi z kontenera w Podzie.
b) "kubectl describe", aby zobaczyć szczegółowe informacje o zasobach.
c) "kubectl exec", aby wykonać polecenia wewnątrz kontenera.
d) "kubectl get", aby wylistować zasoby i sprawdzić ich status.
Te polecenia pomagają diagnozować problemy i zrozumieć stan klastra.

261) Opisz wzorzec "Canary Deployment" w Kubernetes?
"Canary deployment" polega na stopniowym wprowadzaniu nowej wersji aplikacji do wybranej grupy użytkowników, aby zapewnić 
stabilność przed pełnym wdrożeniem. W Kubernetes można to osiągnąć, wdrażając nową wersję aplikacji (wersja canary) i 
stopniowo kierując do niej mały procent ruchu. Na podstawie opinii i wydajności ruch jest stopniowo zwiększany, aż wersja 
canary zacznie obsługiwać cały ruch.

262) Czym jest "Kubernetes Job" i jaki jest jego cel?
"Kubernetes Job" to zasób używany do zarządzania zadaniem, które ma zostać wykonane do końca, w przeciwieństwie do usług 
działających przez długi czas. Jest używany do zadań takich jak przetwarzanie wsadowe ("batch processing"), analiza danych 
czy obliczenia wsadowe ("batch computation"), które muszą zostać uruchomione raz i zakończyć się sukcesem. Kubernetes 
zapewnia, że "Job" będzie uruchomiony, dopóki nie osiągnie określonej liczby zakończeń.

263) Jak "Kubernetes Job" różni się od Poda?
Pod w Kubernetes reprezentuje pojedynczą instancję działającego procesu, podczas gdy "Job" zarządza jednym lub więcej Podami, 
które mają zakończyć się pomyślnie (tzn. zakończyć działanie z kodem wyjścia równym zero). Pody utworzone przez "Job" są 
przeznaczone do wykonania określonego zadania, a następnie zakończenia się sukcesem, co wskazuje na zakończenie "Job".

264) Czym jest "Replica-Set" w Kubernetes?
"Replica-Set" w Kubernetes to obiekt API służący do zapewnienia, że określona liczba replik Podów jest uruchomiona w danym 
momencie. Jest głównie używany do utrzymania dostępności zestawu identycznych Podów. Jeśli jest za mało replik, 
tworzy więcej; jeśli jest ich za dużo, usuwa niektóre.

265) Czym różni się "Replica-Set" od "Replication-Controller"?
"Replica-Set" to nowa generacja "Replication-Controller". Kluczową różnicą jest to, że "Replica-Sets" wspierają selektory 
oparte na zbiorach ("set-based selectors"), w przeciwieństwie do selektorów opartych na równości w "ReplicationController". 
Oznacza to, że "Replica-Sets" mogą wybierać szerszy zakres Podów na podstawie etykiet.

266) Co się stanie, jeśli Pod w "Replica-Set" ulegnie awarii?
Jeśli Pod w "Replica-Set" "fail" (na skutek awarii węzła lub zakończenia), "Replica-Set" zauważy spadek liczby replik i 
utworzy nowy Pod, aby go zastąpić. Nowy Pod jest tworzony na podstawie szablonu Poda zdefiniowanego w "ReplicaSet".

267) Kubernetes Cluster?
Master (zwany również control-plane): 1 lub więcej (dla środowisk produkcyjnych w celu zapewnienia wysokiej dostępności).
Węzły: Do 5000.

268) Komponenty klastra Kubernetes?
Pierwszy komponent - master / "control plane" zawiera:
a) "api-server": API, które pozwalają klientom komunikować się z klastrem i tworzyć obciążenia (obciążeniem jest aplikacja, 
np. "MySQL", "Nginx" itp.).
b) "etcd": Rozproszona baza danych typu klucz-wartość, w której przechowywane są dane klastra.
c) "controller-manager": Proces, który ciągle monitoruje obciążenia / węzły itp.
d) "scheduler": Harmonogram obciążeń.
Drugi komponent - węzeł zawiera ("node"):
a) "kubelet": Agent, który tworzy kontenery i je monitoruje.
b) "container runtime (docker)": Aby stworzyć kontener, węzeł musi posiadać runtime kontenerów.
c) "kube-proxy": Utrzymuje zasady sieciowe na węźle dla komunikacji między obciążeniami w klastrze.

269) Narzędzia Kubernetes?
a) "kind / minikube": Do tworzenia klastra Kubernetes w celu nauki.
b) "kubectl": Narzędzie Cli do interakcji z masterem "Kubernetes / api-server".

270) Kube Config?
Plik konfiguracyjny do organizowania informacji o klastrze. Lokalizacja: "$HOME/.kube/config". 
Zmienna środowiskowa: "KubeConfig".

271) Podsumowanie Kubernetes?
Klastr Kubernetes:
a) "master / control-plane" (1 lub więcej): "api-server", "etcd", "scheduler", "controller-manager".
b) "node / worker" (1 lub więcej): "container runtime", "kubelet", "kube-proxy".
"Kubectl" to narzędzie Cli do komunikacji z "api-server".
"Kube-config" to plik przechowujący informacje o klastrze.
"Kind" to narzędzie do tworzenia klastra Kubernetes w celach edukacyjnych.

272) Czym jest Pod?
Obciążenie ("workload") to aplikacja uruchomiona w klastrze Kubernetes. Pod to podstawowy element do tworzenia obciążeń. 
Jest to najmniejsza jednostka, którą można wdrożyć w Kubernetes. Pod może uruchamiać jeden lub więcej kontenerów. Tylko 
jeden z kontenerów to kontener aplikacji! Pozostałe kontenery pełnią funkcję pomocniczą. Pod reprezentuje maszynę wirtualną, 
a kontenery reprezentują procesy.

273) Czym jest Docker?
Pakujemy aplikację, wszystkie pliki wykonywalne wraz ze wszystkimi zależnościami w obrazie Docker. Tworzymy instancję 
obrazu – kontener. Traktujemy każdy kontener jak maszynę wirtualną. Kontenery mogą komunikować się ze sobą za pomocą 
adresu IP lub nazwy usługi.

274) Status Poda?
a) "Pending": Węzeł nie został jeszcze przydzielony.
b) "Container-Creating": "Kubelet" pracuje nad utworzeniem kontenera.
c) "Running": "Kubelet" uruchomił kontener.
d) "Err-Image-Pull / Image-Pull-Back-Off": Nie udało się pobrać obrazu. "Kubelet" będzie ponawiał próbę z opóźnieniem.
e) "Completed": Kontener zakończył działanie pomyślnie.
f) "Error": Kontener zakończył działanie z błędem.
g) "Crash-Loop-Back-Off": Wystąpił problem z uruchomieniem kontenera. "Kubelet" ponawia próbę z opóźnieniem 
(brak problemu z pobraniem obrazu).
h) "Terminating": Pod jest usuwany.

275) Etykiety Poda. Polecenie do pobrania poda po etykiecie?
"kubectl get pod -l dept=dep-1,team=team-a".

276) Pod, formatowanie wyjścia?
"kubectl get pod -o wide".

277) Pod, formatowanie wyjścia w pliku "YAML"?
"kubectl get pod pod-1 -o yaml".

278) Pod, usuwanie podów za pomocą pliku?
"kubectl delete -f 03-multiple-pods.yaml".

279) Pod, usuwanie pojedynczego poda?
"kubectl delete pod pod-2".

280) Port-Forward?
"kubectl port-forward my-pod 8080:80".

281) "EntryPoint" kontra "CMD"?
Nie możemy nadpisać "EntryPoint" przez wydanie polecenia:
"docker run IMAGE OVERRIDE".
Możemy nadpisać "CMD":
"docker run IMAGE OVERRIDE".

282) Docker - Kubernetes?
"EntryPoint" to polecenie.
"CMD" to argumenty.

283) Polityka ponownego uruchamiania kontenera Poda?
a) "Never": Zakończony / Błąd - Nie uruchamiaj ponownie.
b) "Always": Zakończony / Błąd - Uruchom ponownie.
c) "OnFailure": Błąd - Uruchom ponownie.

284) Polecenia "Kubectl"!
a) "kubectl get nodes": Aby wyświetlić listę węzłów w klastrze.
b) "kubectl create -f FILE-NAME": Aby utworzyć zasoby na podstawie podanego pliku YAML.
c) "kubectl delete -f FILE-NAME": Aby usunąć zasoby na podstawie podanego pliku YAML.
d) "kubectl get pod": Aby wyświetlić wszystkie pody (w domyślnym namespace).
e) "kubectl get pod --show-label": Aby wyświetlić etykiety podów.
f) "kubectl get pod -l team=team-a", "kubectl get pod -l team!=team-a": Aby zapytać pody na podstawie etykiet.
g) "kubectl get pod POD-NAME -o yaml": Aby wyświetlić informacje o podzie w formacie "YAML".
h) "kubectl describe pod POD-NAME": Aby opisać pod.
i) "kubectl delete pod POD-NAME": Aby usunąć dany pod.
j) "kubectl delete pod --all": Aby usunąć wszystkie pody.
k) "kubectl logs POD-NAME": Aby sprawdzić logi poda (zakładając jeden kontener).
l) "kubectl logs POD-NAME -c CONTAINER-NAME": Aby sprawdzić logi konkretnego kontenera w podzie, gdy jest ich więcej.
m) "kubectl exec -it POD-NAME -c CONTAINER-NAME -- bash": Aby uzyskać dostęp do kontenera poda.
n) "kubectl port-forward POD-NAME 8080:80": Aby uzyskać dostęp do API naszej aplikacji z hosta w celach debugowania.

285) "Replica-Set"?
Zarządza Podami. Zapewnia, że pożądana liczba replik dla określonej specyfikacji poda jest uruchomiona.
"Replica-Set - restartPolicy: Always". W klastrze węzeł może ulec awarii, cała strefa może być niedostępna z powodu 
problemów sieciowych.
"Replica-Set" zapewnia, że liczba replik jest zgodna z wymaganiami. Jest to zasób niższego poziomu i nie jest zalecane 
tworzenie go bezpośrednio!
"Deployment" zarządza "Replica-Set". Aby zarządzać zasobami, będziemy używać "metadata.label".

286) "Deployment"?
Zarządza "Replica-Set". To jest to, czego będziemy używać głównie do tworzenia obciążeń roboczych! Do wdrażania aplikacji 
bezstanowych. Jeden Deployment - Jeden mikro-serwis. Deployment - Lista ReplicaSet - Lista Podów.

287) "Create" kontra "Apply"?
a) "Kubectl create: kubectl create -f FILE-NAME": Aby utworzyć zasoby z podanego pliku.
b) "kubectl create -f .": Aby utworzyć zasoby ze wszystkich plików "YAML" w bieżącym katalogu.
c) "kubectl create -f SomeSite.com": Aby utworzyć zasoby z podanego URL.
d) "Kubectl apply: kubectl apply -f FILE-NAME": Aby utworzyć / zaktualizować zasoby z podanego pliku.
e) "kubectl apply -f .": Aby utworzyć / zaktualizować zasoby ze wszystkich plików "YAML" w bieżącym katalogu.
f) "kubectl apply -f SomeSite.com": Aby utworzyć / zaktualizować zasoby z podanego URL.

288) Strategia "Deploymentu"?
a) "recreate": Zakończenie działania starych podów i utworzenie nowych.
b) "rolling update": Stopniowe wprowadzanie zmian. Możemy mieć tymczasowo mieszankę starych i nowych podów. 
Poniższe właściwości mogą być liczbą lub procentem:
"max-Surge": Maksymalna liczba dodatkowych podów, które mogą zostać utworzone.
"max-Unavailable": Maksymalna liczba podów, które mogą zostać zakończone.

289) "Service"?
Logiczna abstrakcja dla zestawu Podów. Jedno niezawodne punkt końcowy sieciowy do dostępu do Podów. Stabilny adres IP.
Nazwa "DNS".

290) "Service / kube-proxy"?
Prosty proxy. Zasób "Service" w Kubernetes nie zużywa "CPU / Memory". Nie jest to load balancer działający na zasadzie 
"round-robin". Jest losowy. Nie oczekuj przepisywania URL-i / routingu opartego na ścieżkach itd. 
(To jest "Ingress" w Kubernetes).

291) Typy "Kubernetes Service"?
a) "Cluster-IP": Do komunikacji wewnątrz klastra Kubernetes. Nie można go uzyskać z zewnątrz klastra. 
(W chmurach AWS / GCP - komunikacja w ramach prywatnej podsieci). Jest to domyślna opcja, jeśli nie określimy innej. 
Zwykle to jest to, co będziemy używać.
b) "Node-Port": Może być dostępny z zewnątrz przez mastera/noda Kubernetes za pomocą określonego portu 
(można używać do testowania).
c) "Load-Balancer": Używane w chmurach takich jak "AWS / GCP / Azure". Może być używane do odbierania ruchu z zewnątrz.

292) "Node-Port"?
Dozwolone zakresy portów dla "Node-Port" to 30000 - 32767. Każdy węzeł jest ustawiony do nasłuchiwania na określonym porcie.
Każde zapytanie do jednego z węzłów na tym porcie jest przekazywane do podu.

293) "Load-Balancer"?
Używane w dostawcy chmurowym. Służy do odbierania ruchu z zewnątrz i przekazywania go do podów.

294) "Namespace"?
Wirtualny klaster / partycjonowanie w ramach klastra. Izolowanie grupy zasobów w klastrze. Przykłady użycia:
Środowiska Dev / QA. Izolowanie zasobów dla różnych zespołów. "Namespace" mogą być również używane do ustawiania kwot 
zasobów przez administratorów.

295) "Probes"?
Problem: Pody są uznawane za żywe i gotowe do użycia, gdy tylko kontenery zostaną uruchomione. Jeśli pod jest gotowy, 
serwis zacznie wysyłać zapytania do poda. "Rolling-Update" zakończy działanie starych podów. Musimy upewnić się, że nasze 
pody są żywe i gotowe, aby uniknąć niespodzianek! Proby to narzędzia/usprawnienia do mierzenia stanu zdrowia poda. 
Czy został uruchomiony? Czy jest żywy? Czy jest gotowy do obsługi zapytań?

296) Probe Types?
Terminy: Live – Czy Pod jest żywy? Ready – Czy Pod może obsługiwać zapytania?
a) "startup-Probe": Sprawdza, czy aplikacja w kontenerze została uruchomiona. Działanie w przypadku niepowodzenia: "restart".
b) "liveness-Probe": Sprawdza, czy aplikacja jest nadal żywa. Działanie w przypadku niepowodzenia: "restart".
c) "readiness-Probe": Sprawdza, czy aplikacja jest gotowa do przyjmowania zapytań od serwisu. Działanie w przypadku 
niepowodzenia: usunięcie z serwisu.

297) Faza Wykonania Probe ("Probe Execution Phase")?
a) "startup-Probe": Rozpoczyna się natychmiast po uruchomieniu kontenera! Jeśli test się powiedzie, "startup-Probe" 
zatrzymuje się.
b) "liveness-Probe": Rozpoczyna się po zakończeniu "startup-Probe". Jest wykonywana w trakcie cyklu życia podu.
c) "readiness-Probe": Rozpoczyna się po zakończeniu "startup-Probe". Jest wykonywana w trakcie cyklu życia podu.

298) Opcje Probe?
a) "exec": Wykonuje dowolne polecenie w celu sprawdzenia stanu. Na przykład: "cat /tmp/app.log".
b) "http-Get": Wywołuje punkt końcowy HTTP. Na przykład: "/health".
c) "tcp-Socket": Sprawdza, czy aplikacja zaczęła nasłuchiwać na określonym porcie.

299) Właściwości Probe?
a) "initialDelaySeconds": "0".
b) "periodSeconds": "10".
c) "timeoutSeconds": "1".
d) "successThreshold": "1".
e) "failureThreshold": "3".

300) "ConfigMap" i "Secret"?
Aby przechowywać dane konfiguracyjne oddzielnie od aplikacji: "ConfigMap", "Secret".
"ConfigMap": dane niewrażliwe, "application.properties".
"Secret": dane wrażliwe, "credentials".
Wstrzyknij wszystkie pary klucz/wartość jako zmienne środowiskowe. Wstrzyknij jedną z par klucz/wartość jako zmienną 
środowiskową. Wstrzyknij wartość klucza jako plik w określonej lokalizacji.

301) "Config-Map"?
Właściwości jako Klucz/Wartość. Właściwości jako plik. Przechowuj dowolny plik binarny. Maksymalny rozmiar 1MB.

302) "Secret"?
To samo co "ConfigMap" - ale dla danych wrażliwych. Wartość jest zakodowana w "base64". Przykłady użycia: pliki kluczy SSH, 
dane logowania, konta serwisowe, itd.

303) "Cluster"?
Zadanie wymaga ("Workload requires"): Instancji obliczeniowych ("Compute Instance"). 
Storage: Cykl życia pamięci masowej powinien być oddzielony od cyklu życia Pod.

304) "Persistent Volumes"?
Znane jako "PV". Abstrakcja pamięci masowej, wtyczki woluminów. Zapewnia pamięć masową - Podobnie jak węzeł w klastrze, 
który zapewnia CPU/Pamięć.

305) Storage Terminologies?
a) "Storage Class": Typ pamięci masowej. Przykład: "AWS EBS SSD" - super szybki. "AWS EBS" oparty na dyskach - wolniejszy. 
"GCP - PD standard". "GCP - PD SSD". "GCP - PD extreme".
b) "Persistent Volume Claim": Żądanie utworzenia "PV". Zasób łączący "PV" i "Pod". Przykład: Żądanie utworzenia 
"5 GB GCP PD SSD" dla aplikacji.
c) "Persistent Volume": Faktyczna pamięć masowa utworzona dla konkretnej klasy pamięci masowej. 
Przykład: 
a) "5 GB GCP PD SSD"
b) "100 GB GCP PD standard".

306) "Access Modes"?
a) "Read-Write-Once" - na węzeł.
b) "Read-Write-Once-Pod" - na pod.
c) "Read-Only-Many".
d) "Read-Write-Many".

307) "Stateful-Set"?
To samo co "Deployment" - ale dla pracy opartej na stanie. Każdy Pod będzie miał unikalną/stabilną nazwę hosta. 
"Stateful-Set" NIE jest przeznaczone tylko dla baz danych. Zamiast tego, jest to rozwiązanie dla każdej aplikacji, 
która wymaga trwałej tożsamości.

308) "Headless Service"?
"Service" nie będzie miał żadnego adresu IP, a "Kube-proxy" NIE będzie przeprowadzać żadnego równoważenia obciążenia. 
Wpisy DNS zostaną utworzone dla "pod-name.svc-name".

309) Konsekwencje przekroczenia limitu, ("Consequences of Exceeding Limit")?
a) Pamięć: "Kubelet" zabije kontener i uruchomi go ponownie.
b) CPU: Kontener NIE zostanie zabity. Zostanie spowolniony.

310) "Ingress"?
"Service": "Cluster-IP", "Node-Port" (30000 - 32767), "Load-Balancer" (AWS, GCP, itd.).
Sprytny ("Smart") "router/proxy", który przekierowuje ruch do klastra. Zawiera zestaw reguł routingu. Potrzebujemy 
"Ingress Controller" do zarządzania "Ingress".

311) "Ingress Controller"?
"Ingress Controller" zarządza zasobami "Ingress" (podobnie jak "Deployment Controller"). Implementuje reguły "Ingress". 
Istnieje wiele implementacji: "AWS", "GCP".

312) Kubernetes podstawowe pojęcia, ("Kubernetes Core Concepts")?
a) "Cluster": Zestaw maszyn Node, które uruchamiają aplikację kontenerową ("Worker Nodes") lub kontrolują inne 
Węzły ("Master Node").
b) "Nodes": Fizyczna lub wirtualna maszyna o określonej pojemności sprzętowej, która hostuje jeden lub więcej Pod i 
komunikuje się z "Cluster".
"Master Node": "Cluster Control Plane", zarządzający Podami na "Worker Nodes".
"Worker Node": Hostuje Pody, uruchamia kontenery aplikacji (+ zasoby).
c) Pods: Pod zawiera faktycznie uruchomiony kontener aplikacji + wymagane zasoby (np. woluminy).
d) "Containers": Standardowe kontenery Docker.
e) "Services": Logiczną grupą Podów z unikalnym adresem IP, niezależnym od Pod i kontenera.

313) "Kubernetes Objects"?
"Pods", "Deployments", "Services", "Volume". Obiekty mogą być tworzone na dwa sposoby: imperatywnie lub deklaratywnie.

314) "Pod Object"?
Obiekt Pod: Najmniejsza jednostka, z którą komunikuje się Kubernetes.
a) Kontenery i uruchamia jeden lub wiele kontenerów: Najczęstszy przypadek użycia to "jeden kontener na pod".
b) Pody zawierają wspólne zasoby (np. woluminy "Volumes") dla wszystkich kontenerów w Podzie.
c) Posiada wewnętrzny adres IP w klastrze domyślnie: Kontenery wewnątrz Poda mogą komunikować się za pomocą localhost.
Pody zostały zaprojektowane jako efemeryczne: Kubernetes będzie je uruchamiać, zatrzymywać i zastępować w razie potrzeby. 
Aby Pody były zarządzane przez Ciebie, potrzebujesz "Kontrolera", na przykład "Deployment".

315) "Deployment Object"?
"Deployment" zarządza (wieloma) Podami. Ustawiasz pożądany stan, a Kubernetes zmienia rzeczywisty stan. Określasz, które 
Pody i kontenery mają być uruchamiane oraz liczbę instancji. "Deployments" mogą być wstrzymywane, usuwane i wycofywane. 
"Deployments" mogą być skalowane dynamicznie (i automatycznie): Możesz zmieniać liczbę pożądanych Podów w zależności od 
potrzeb. "Deployments" zarządzają Podami za Ciebie, możesz również tworzyć wiele Deploymentów. Dlatego zazwyczaj nie 
kontrolujesz Podów bezpośrednio, tylko używasz "Deploymentów" do ustawienia pożądanego stanu końcowego.

316) Obiekt "Service"?
"Service" udostępnia Pody dla klastra lub zewnętrznie. Pody mają domyślnie wewnętrzny adres IP - zmienia się on, gdy Pod 
jest zastępowany: Znalezienie Podów jest trudne, jeśli adres IP zmienia się cały czas. 
"Services" grupują Pody ze wspólnym adresem IP. "Services" mogą umożliwiać dostęp zewnętrzny do Podów: 
Domyślnie (tylko wewnętrzny) można to nadpisać. Bez "Services", Pody są bardzo trudne do osiągnięcia, a komunikacja jest 
utrudniona. Osiągnięcie Poda z zewnątrz klastra jest niemożliwe bez "Services".

317) Komenda do tworzenia Obiektu "Service" ("Kubernetes Create Service Object Command")?
"kubectl expose deployment FIRST-APP --type=LoadBalancer --port 8080".

318) Komenda Kubernetes do tworzenia Obiektu "Deployment" (Kubernetes Create Deployment Object Command)?
"kubectl create deployment FIRST-APP --image=panda/kub-first-app".

319) Kubernetes Autoscaling Command?
"kubectl scale deployment/FIRST-APP --replicas=3".

320) Kubernetes Updating Deployments?
Odpowiedź: Zmiana tagu obrazu:
a) "docker tag Kub-First-App panda/kub-first-app:2".
b) "docker push panda/kub-first-app:2".
Aktualizacja obrazu:
a) "kubectl set image deployment/first-app kube-first-app=panda/kub-first-app:2".
b) "kubectl rollout status deployment/first-app".

321) "Namespacing" i "Control Groups"?
"Namespacing": Izolowanie zasobów dla każdego procesu (lub grupy procesów).
"Control Groups (cgroups)": Ograniczanie ilości zasobów używanych przez każdy proces.
"Namespacing": Proces, Sieć, Nazwy hostów, Dysk twardy, Użytkownicy, "I P C".
"Control Groups (cgroups)": Pamięć, I/O dysku twardego, Użycie CPU, Szerokość pasma sieciowego.

322) Tworzenie i uruchamianie kontenera z obrazu?
"docker run Image-Name".
"docker": Odniesienie do klienta Docker. 
"run": Próba utworzenia i uruchomienia kontenera.
"Image-Name": Nazwa obrazu, który ma być użyty do utworzenia kontenera.
"docker run" = "docker create + docker start".

323) Zatrzymywanie działających kontenerów?
"docker stop Container-Id". 
"docker kill Container-Id".
Stop: "SIGTERM", "kill": "SIGKILL".

324) Wykonywanie dodatkowego polecenia w kontenerze?
"docker exec -it CONTAINER-ID COMMAND".
"docker": Odniesienie do klienta Docker.
"exec": Uruchomienie innego polecenia.
"-it": Pozwala na wprowadzanie danych do kontenera.

325) Cel flagi "-it"?
"-i": Podłącza nasz terminal do stdin procesu działającego w kontenerze.
"-t": Umożliwia wyświetlanie ładnego interfejsu (np. kolorów, formatowania).

326) Tworzenie obrazów Docker?
"Dockerfile": Konfiguracja definiująca, jak nasz kontener powinien działać.
"Docker Client", "Docker Server", Obraz gotowy do użycia!

327) Tworzenie pliku "Dockerfile"?
Określ obraz bazowy.
Uruchom kilka poleceń, aby zainstalować dodatkowe programy.
Określ polecenie do uruchomienia przy starcie kontenera.

328) Oznaczanie obrazu?
a) "docker build -t panda/redis .".
b) "docker run panda/redis".

329) Ręczne tworzenie obrazu za pomocą "docker commit"?
a) "docker run -it alpine sh".
b) "docker commit -c Cmd Redis-server Container-Id".

330) Kopiowanie plików budowania?
"COPY ./ ./".
a) Pierwsze "./": Ścieżka do folderu, który ma zostać skopiowany (twoja maszyna), względem kontekstu budowania.
b) Drugie "./": Miejsce, do którego mają zostać skopiowane pliki wewnątrz kontenera.

331) Mapowanie portów kontenera. Uruchomienie "Docker" z mapowaniem portów?
a) "docker run -p 8080:8080 Image-Id".

332) Określanie katalogu roboczego?
"Workdir /usr/app".
Każde kolejne polecenie będzie wykonywane względem ścieżki "/usr/app" w kontenerze.

333) Typ obiektu "Pod"?
Uruchamia jeden lub więcej ściśle powiązanych kontenerów.

334) Typ obiektu "Service"?
Ustawia sieć w klastrze Kubernetes.

335) Typ obiektu "Deployment"?
Utrzymuje zbiór indywidualnych Podów, zapewniając, że mają one odpowiednią konfigurację i że istnieje właściwa ich liczba.

336) "Pods" kontra "Deployment"?
a) "Pod": Uruchamia pojedynczy zestaw kontenerów. Dobre do jednorazowych celów deweloperskich. Rzadko używane bezpośrednio 
w produkcji.
b) "Deployment": Uruchamia zestaw indywidualnych Podów (jeden lub więcej). Monitoruje stan każdego Poda, aktualizując je 
w razie potrzeby. Dobre zarówno do deweloperki, jak i do produkcji.

337) Kubernetes "Pods", "Services", Status Deploymentów?
a) "kubectl get pods".
b) "kubectl get services".
c) Aby uzyskać szczegółowe informacje o obiekcie: "kubectl describe OBJECT-TYPE OBJECT-NAME".
d) "kubectl describe pod client-pod".
e) "kubectl get deployments".
f) "kubectl get pods -o wide".

338) Ważne wnioski ("Takeaways") o Kubernetes?
a) "Kubernetes" to system do wdrażania aplikacji kontenerowych.
b) "Nodes" to indywidualne maszyny (lub maszyny wirtualne), które uruchamiają kontenery.
c) "Masters" to maszyny (lub maszyny wirtualne) z zestawem programów do zarządzania węzłami.
d) "Kubernetes" nie tworzył naszych obrazów, tylko pobrał je z innego źródła.
e) "Kubernetes (master)" decyduje, gdzie uruchomić każdy kontener. Każdy węzeł może uruchomić różny zestaw kontenerów.
f) Aby, coś wdrożyć, aktualizujemy pożądany stan mastera za pomocą pliku konfiguracyjnego.
g) Master działa nieustannie, aby osiągnąć pożądany stan.

339) Ścieżka do produkcji w Kubernetes?
a) Tworzenie plików konfiguracyjnych dla każdego "Service" i "Deployment".
b) Testowanie lokalnie na "minikube".
c) Tworzenie przepływu ("flow") w "Github/Trevis" do budowania obrazów i wdrażania.
d) Wdrażanie aplikacji do dostawcy chmurowego.

340) "NodePort" kontra "Cluster-Ip Service"?
"Services": Ustawia sieć w klastrze Kubernetes.
a) "Cluster-Ip": Udostępnia zestaw Podów innym obiektom w klastrze.
b) "NodePort": Udostępnia zestaw Podów światu zewnętrznemu (dobrze tylko do celów deweloperskich!).

341) "Volume" w ogólnej terminologii kontenerów?
Jakikolwiek mechanizm, który pozwala kontenerowi uzyskać dostęp do systemu plików poza nim.

342) "Volume" w Kubernetes?
Obiekt, który pozwala kontenerowi przechowywać dane na poziomie Poda.

343) "Persistent Volume", Tryb dostępu ("Access Mode")?
a) "Read-Write-One": Może być używany przez pojedynczy węzeł.
b) "Read-Only-Many": Wielu węzłów może odczytywać dane z tego woluminu.
c) "Read-Write-Many": Może być odczytywany i zapisywany przez wiele węzłów.

344) Obiekt Kubernetes Secrets?
Bezpieczne przechowywanie informacji w klastrze, takich jak hasło do bazy danych. Tworzenie sekretu:
"kubectl create secret generic Secret-Name --from-literal key=value".
"create": Rozkaz imperatywny do tworzenia nowego obiektu.
"secret": Typ obiektu.
"generic": Typ sekretu.
"Secret-Name": Nazwa, do późniejszego użycia w kodzie.

345) "Services", "Ingress", "Load Balancer"?
a) "Ingress": Udostępnia zestaw usług światu zewnętrznemu.
b) "Load Balancer": Tradycyjny sposób kierowania ruchem sieciowym do klastra.

346) Zmienne środowiskowe w "Docker Compose"?
a) "variableName=value": Ustawia zmienną w kontenerze podczas uruchamiania.
b) "variableName": Ustawia zmienną w kontenerze podczas uruchamiania, wartość jest pobierana z twojego komputera.

347) Typy obiektów Kubernetes i wersje API?
Plik konfiguracyjny, "Stateful-Set", "ReplicaController", "Pod", "Service".
Obiekty pełnią różne funkcje, takie jak uruchamianie kontenera, monitorowanie kontenera, ustawianie sieci itp.

348) Pliki konfiguracyjne usługi Kubernetes?
"Kubernetes Node". 
"Kube-proxy": "Service NodePort", użyję mojego 'selector component', aby znaleźć każdy obiekt z etykietą 'component'. 
Następnie udostępnię jego port '3000' światu zewnętrznemu.

349) Rollbacki i polecenia historii Deploymentów w Kubernetes?
a) "kubectl rollout undo deployment/first-app".
b) "kubectl rollout status deployment/first-app".
c) "kubectl rollout history deployment/first-app".
d) "kubectl rollout history deployment/first-app --revision=3".
e) "kubectl rollout undo deployment/first-app --to-revision=1".

350) Stan w Kubernetes?
Stan to dane tworzone i wykorzystywane przez Twoją aplikację, które nie mogą zostać utracone.
a) Dane generowane przez użytkownika, konta użytkowników, często przechowywane w bazie danych, ale mogą to być również 
pliki (np. przesyłane pliki).
b) Wyniki pośrednie uzyskane przez aplikację. Często przechowywane w pamięci, tymczasowych tabelach bazy danych lub plikach.

351) Kubernetes i "Volumes"?
Kubernetes może montować "Volumes" do kontenerów. Obsługiwanych jest wiele różnych typów "Volumes" oraz sterowników ("drivers").
"Local Volumes" (na węzłach), "Volumes" specyficzne dla dostawcy chmurowego.
Czas życia "Volume" zależy od czasu życia "Poda". "Volumes" przetrwają ponowne uruchomienia kontenerów (oraz ich usunięcie). 
"Volumes" są usuwane, gdy "Pody" zostaną zniszczone.

352) "Kubernetes Volumes" kontra "Docker Volumes"?
a) "Kubernetes Volumes": Obsługuje wiele różnych sterowników i typów. "Volumes" nie muszą być trwałe. "Volumes" przetrwają 
ponowne uruchomienia i usunięcia kontenerów.
b) "Docker Volumes": W zasadzie zależy od wsparcia sterownika/typu. "Volumes" są trwałe, dopóki nie zostaną ręcznie usunięte. 
"Volumes" przetrwają ponowne uruchomienia i usunięcia kontenerów.

353) Co to jest "Hypervisor"?
Menedżer maszyn wirtualnych.

354) Normalne "Normal Volumes" kontra "Persistent Volumes" w Kubernetes?
"Volumes" pozwalają na przechowywanie danych.
a) "Normal Volumes": "Volume" jest przypisane do Poda i cyklu życia Poda. Zdefiniowane i tworzone razem z Podem. 
Są powtarzalne i trudne do zarządzania na poziomie globalnym.
b) "Persistent Volumes": "Volume" jest samodzielnym zasobem klastra (nie jest przypisane do Poda). Tworzone samodzielnie, 
przydzielane przez "PVC". Może być zdefiniowane raz i używane wielokrotnie.

355) Wyjaśnij, jakie są wzorce użycia Podów?
Pody są używane do uruchamiania wielu kontenerów jako jednej jednostki, co pozwala im dzielić zasoby i konfiguracje sieciowe.
To umożliwia łatwiejsze zarządzanie i koordynację kontenerów.

356) Oznaczanie obrazu ("Tagging an image")?
"docker build -t panda/redis .".

357) Tworzenie obrazów Docker?
"Dockerfile": Konfiguracja definiująca, jak nasz kontener powinien działać. Przepływ:
a) Tworzenie "Dockerfile".
b) Określenie obrazu bazowego.
c) Uruchomienie kilku poleceń w celu zainstalowania dodatkowych programów.
d) Określenie polecenia do uruchomienia przy starcie kontenera.

358) Architektura Dockera?
"Docker Client", "Docker Daemon", Kontenery, Lokalne obrazy, Repozytorium obrazów.

359) Orkiestracja kontenerów ('Container Orchestration')?
Wymóg: Chcę 10 instancji mikroserwisu A, 15 instancji mikro-serwisu B.
Typowe funkcje:
a) Auto Skalowanie ("Auto Scaling"): Skalowanie kontenerów w zależności od zapotrzebowania.
b) Odkrywanie usług ("Service Discovery"): Pomaga mikro-serwisom znaleźć się nawzajem.
c) "Load Balancer": Rozdzielanie obciążenia między wieloma instancjami mikro-serwisu.
d) "Self Healing": Wykonywanie testów zdrowia i zastępowanie nieudanych instancji.
e) Instalacje bez przestojów ("Zero Downtime Deployments"): Wydawanie nowych wersji bez przestojów.

360) Deploymenty w Kubernetes?
"Deployment" umożliwia deklaratywne aktualizacje dla Podów i "Replica-Sets". Jest to bardzo ważne, aby móc aktualizować 
nowe wersje aplikacji bez przestojów. Strategia ta nazywa się "rolling updates". Zapewnia, że aktualizacja wersji odbywa 
się bez problemów (bez przestojów).

361) "Service" w Kubernetes?
Zapewnia zawsze dostępny interfejs zewnętrzny do aplikacji uruchamianych wewnątrz Podów. "Service" pozwala Twojej aplikacji 
odbierać ruch przez stały adres IP o nieograniczonym czasie życia. "Service" jest tworzony za pomocą: 
"kubectl expose deployment". "Cluster-IP" service może być dostępny tylko z wnętrza klastra.

362) Kubernetes - "Liveness" i "Readiness Probe"?
Kubernetes używa probes do sprawdzania zdrowia mikroserwisu. Jeśli "readiness probe" nie zakończy się sukcesem, 
żaden ruch nie jest kierowany do aplikacji. Jeśli "liveness probe" nie zakończy się sukcesem, Pod jest restartowany. 
"Spring Boot Actuator" (w wersji "2.3" i wyższej) oferuje wbudowane "readiness" i "liveness probes": "health/readiness", 
"/health/liveness".

363) "Scheduler (kube-scheduler)" na węźle Master?
"Scheduler" odpowiada za przydzielanie Podów do Węzłów ("Nodes"). Decyzja ta może być oparta na dostępnej ilości pamięci 
oraz dostępnej mocy CPU.

364) API Server (kube-apiserver) na węźle Master?
Jeśli próbuję wprowadzić zmianę za pomocą "kubectl" lub jeśli próbuję dokonać zmiany przez konsolę "Google Cloud", 
zmiana ta jest przesyłana do tego API server i przetwarzana stąd.

365) Rozproszona baza danych ("etcd") na węźle Master?
Wszystkie zmiany konfiguracyjne, które wprowadzamy, wszystkie tworzone deploymenty, wszystkie operacje skalowania, 
które wykonujemy, są przechowywane w rozproszonej bazie danych.

366) "Container Runtime (CRI - docker, rkt)" na węźle Worker?
Aby uruchomić kontenery wewnątrz naszych Podów, potrzebujemy "Container Runtime".

367) Node Agent ("kubelet") na węźle Worker?
Zadaniem "kubelet" jest monitorowanie tego, co dzieje się na węźle, i przekazywanie tych informacji z powrotem do węzła 
Master. Jeśli Pod przestaje działać, "Node Agent" zgłasza to do "Controller Manager".

368) Komponent sieciowy ("kube-proxy") na węźle Worker?
Pomaga w udostępnianiu usług wokół węzłów i Podów.

369) Jaka jest różnica między "CMD" a "Entrypoint" w Dockerfile?
CMD służy do określenia polecenia, które powinno być zawsze uruchamiane, gdy kontener jest uruchamiany z obrazu.
"Entrypoint" służy do określenia domyślnego polecenia, które powinno być uruchamiane, gdy kontener jest uruchamiany z obrazu.

370) Kontenery Docker a Sieci?
Kontenery są izolowane, ale mogą być połączone, aby wysyłać do siebie zapytania (np. HTTPS).
a) Określenie adresu IP kontenera i używanie go. Adres IP może się zmieniać, przez co jego ustalanie staje się 
niepotrzebną (ręczną) pracą.
b) Utworzenie sieci Docker i dodanie obu kontenerów. Kontenery mogą używać nazw innych kontenerów jako adresów zapytań.

371) Dane, Volumes i Sieci ("Data, Volumes and Networking")?
Kontenery są izolowane i bezstanowe.
Izolowane: Kontenery mają swoje własne dane i system plików, oddzielony od systemu plików maszyny gospodarza. 
Użyj "BindMounts", aby połączyć foldery maszyny gospodarza: "-v local - path - container - path".
Bezstanowe: Kontenery mogą przechowywać dane wewnętrznie, ale dane zostaną utracone, jeśli kontener zostanie usunięty i 
zastąpiony nowym. Użyj "Volumes", aby przechować dane: "-v NAME:/container/path".

372) Kluczowe polecenia Dockera?
a) Budowanie obrazu na podstawie Dockerfile: "docker build -t NAME .".
b) Uruchamianie kontenera na podstawie obrazu lokalnego lub zdalnego: "docker run --name NAME --rm -d IMAGE".
c) Udostępnianie / Pobieranie obrazów do/z DockerHub:
"docker push Repository / Name', 'docker pull Repository / Name'.

373) "Bind Mounts", "Volumes" i "COPY"?
a) Obraz/Kontener to "jedno źródło prawdy".
b) Kontener powinien rzadko działać samodzielnie, nie powinno się trzymać kodu źródłowego na zdalnej maszynie.
c) Użyj "COPY", aby skopiować migawkę kodu do obrazu.
d) Zapewnia, że każdy obraz działa bez dodatkowej konfiguracji lub kodu wokół niego.

374) Co dokładnie oznacza Dockeryzowany węzeł ("Dockernized node")? Czy ten węzeł może być lokalny, czy może być w chmurze?
Dockeryzowany węzeł to fizyczna lub wirtualna maszyna, która została skonfigurowana do uruchamiania kontenerów Docker. 
Może to być maszyna lokalna (on-premises) lub znajdująca się w chmurze.

375) Jaka jest różnica między "expose" a "publish" w Docker?
"Expose" jest używane do określenia portów, które powinny być dostępne dla powiązanych kontenerów, podczas gdy "publish" 
jest używane do określenia portów, które powinny być dostępne dla maszyny gospodarza.

376) Jaki jest cel polecenia "EXPOSE" w Dockerfile?
Celem polecenia "EXPOSE" jest określenie portów, które powinny być dostępne dla powiązanych kontenerów.

377) Czy możesz wyjaśnić, czym jest emulacja?
Emulacja to proces naśladowania zachowań jednego systemu przy użyciu innego systemu.

378) Automatyczne restarty kontenerów Docker?
Polityki restartu:
a) 'no': Nigdy nie próbuj ponownie uruchomić tego kontenera, jeśli zatrzyma się lub ulegnie awarii.
b) "always": Jeśli kontener zatrzyma się z jakiegokolwiek powodu, zawsze próbuj go ponownie uruchomić.
c) "on-failure": Uruchom ponownie tylko wtedy, gdy kontener zatrzyma się z kodem błędu.
d) "unless-stopped": Zawsze uruchamiaj ponownie, chyba że (my, deweloperzy) ręcznie go zatrzymamy.

379) Pisanie plików Docker Compose?
Usługi (kontenery): Publikowanie portów. Zmienne środowiskowe. Wolumeny ("Volumes"). Sieci ("Networks").

380) Czym "Docker Compose" NIE JEST?
"Docker Compose" NIE zastępuje Dockerfile do tworzenia niestandardowych obrazów. 
"Docker Compose" NIE zastępuje obrazów ani kontenerów. 
"Docker Compose" NIE jest odpowiedni do zarządzania wieloma kontenerami na różnych hostach (maszynach).

381) Tworzenie sieci kontenerów?
W ramach sieci "Docker" wszystkie kontenery mogą ze sobą komunikować się, a adresy IP są automatycznie rozwiązywane.
"docker run --network nazwa-mojej-sieci".

382) Czy kontenery uruchamiane na twoim systemie (za pomocą "docker run") mogą komunikować się z siecią światową?
Tak.

383) Jak kontenery komunikują się ze sobą, jeśli znajdują się w tej samej sieci?
Możesz używać nazw kontenerów jako adresów.

384) Jak kontenery mogą komunikować się z innymi kontenerami?
Poprzez ręczne znalezienie adresu IP lub przez użycie sieci.

385) Jak aplikacje uruchomione w kontenerze mogą komunikować się z twoją lokalną maszyną gospodarza?
Poprzez użycie specjalnego adresu "host.docker.internal".

386) Tworzenie i usuwanie klastra Kubernetes?
a) Ustawienie lokalizacji geograficznej, w której chcemy go uruchomić:
"gcloud config set compute/zone europe-west-2-a".
b) Uruchomienie klastra Kubernetes:
"gcloud container clusters create my-cluster --issue-client-certificate --enable-basic-auth".
c) Usunięcie:
"gcloud container clusters delete my-cluster --async --quiet".

387) Argumenty i Zmienne Środowiskowe (ENV)?
Docker obsługuje "ARG" dla czasu budowania i "ENV" dla zmiennych środowiskowych w czasie wykonywania.
a) "ARG": Dostępne wewnątrz "Dockerfile", Niedostępne w "CMD" ani w żadnym innym kodzie aplikacji. Ustawiane podczas 
budowania obrazu ("docker build") za pomocą "--build-arg".
b) "ENV": Dostępne wewnątrz "Dockerfile" i w kodzie aplikacji. Ustawiane za pomocą "ENV" w "Dockerfile" lub przez 
"--env" w "docker run".

388) Jak działa wirtualizacja na niskim poziomie?
Wirtualizacja działa na poziomie sprzętu, abstrahując ("abstracting") fizyczne zasoby maszyny i prezentując je systemowi 
operacyjnemu jako zasoby wirtualne. 

389) Co to jest para-wirtualizacja?
Para-wirtualizacja to rodzaj wirtualizacji, w której system operacyjny gościa jest modyfikowany, aby działać w środowisku 
wirtualnym.

390) Jak połączyć kontenery?
Poprzez użycie polecenia "docker run" z opcją "--link".

391) Jaka jest różnica między poleceniami "COPY" a "ADD" w Dockerfile?
Polecenie "COPY" kopiuje pliki z hosta do kontenera, podczas gdy polecenie "ADD" kopiuje pliki z hosta do obrazu.

392) Koncepcje Kubernetes?
a) Węzły ("Nodes"): Są to serwery, na których działa Kubernetes. Są one zorganizowane w klaster.
b) "Pod": To wiele kontenerów Docker, które razem zapewniają usługę. Kontenery należące do jednego Pod uruchamiają się 
na jednym węźle.
c) "Replica-Set": Zapewnia, że uruchamiana jest określona liczba instancji Pod. Dzięki temu obciążenie jest rozdzielane 
między Pod. Dodatkowo system jest odporny na awarie.
d) "Deployment": Generuje "Replica-Set" i zapewnia wymagane obrazy Docker.
e) Usługi ("Services"): Umożliwiają dostęp do Pod. Usługi są rejestrowane pod jedną nazwą w DNS i mają stały adres IP, 
pod którym można je skontaktować przez klaster. Ponadto usługi umożliwiają routowanie z zewnątrz.
f) Deklaratywność: Oznacza, że konfiguracja definiuje pożądany stan. Kubernetes dba o to, by system pasował do tego stanu.

393) Jaka jest różnica między repozytorium a rejestrem?
Repozytorium to zbiór obrazów Docker, podczas gdy rejestr to miejsce do przechowywania i dystrybucji obrazów Docker.

394) Funkcje Kubernetes?
Uruchamia kontenery Docker w klastrze węzłów. Zabezpieczenie przed awariami. Obsługuje równoważenie obciążenia. 
Obsługuje wykrywanie usług ("Service Discovery"). Mikro-serwisy nie mają zależności kodowych od Kubernetes.

395) Czy anonimowe wolumeny są bezużyteczne?
Nie, możesz je wykorzystać do nadania priorytetu ścieżkom wewnętrznym kontenera nad ścieżkami zewnętrznymi.

396) Czym jest "Bind Mount"?
Ścieżka na twojej maszynie hosta, którą znasz i którą określiłeś, a która jest mapowana na wewnętrzną ścieżkę kontenera.

397) Bind Mounts?
Lokalizacja w systemie plików hosta, niezwiązana z żadnym konkretnym kontenerem. Przetrwa zamknięcie / ponowne uruchomienie 
kontenera — usunięcie na systemie plików hosta. Może być udostępniana między kontenerami. Może być ponownie użyta dla 
kontenera (po ponownych uruchomieniach).

398) Anonimowe wolumeny ("Anonymous Volumes")?
Tworzone specjalnie dla pojedynczego kontenera. Przetrwają zamknięcie / ponowne uruchomienie kontenera, chyba że użyto 
opcji "--rm". Nie mogą być udostępniane między kontenerami. Ponieważ są anonimowe, nie mogą być ponownie używane 
(nawet na tym samym obrazie).

399) Zmienne środowiskowe w Docker Compose?
"variableName=value": Ustala zmienną w kontenerze w czasie uruchomienia.
"variableName": Ustala zmienną w kontenerze w czasie uruchomienia. Wartość jest pobierana z twojego komputera.

400) Dwa typy zewnętrznych magazynów danych w Dockerze?
Wolumeny ("Volumes") zarządzane przez Docker. 
"Bind Mounts" zarządzane przez Ciebie.
a) Anonimowe Wolumeny ("Anonymous Volumes"): Docker ustawia folder/ścieżkę na twojej maszynie hosta, dokładna lokalizacja 
jest nieznana (np. "=dev"). Zarządzane za pomocą poleceń "docker volume".
b) Nazwane Wolumeny ("Named Volumes"): Zdefiniowana ścieżka w kontenerze jest mapowana na utworzony wolumen/montowanie. 
Przykład: "/some-path" na twojej maszynie hosta jest mapowane na "/app/data". Świetne dla danych, które powinny być trwałe, 
ale których nie musisz edytować bezpośrednio.

401) Kubernetes "Service Discovery"?
Usługa sprawia, że zestaw replik jest dostępny.
a) Usługa zapewnia adres IP i rekord DNS dla Poda.
b) Inne Pody komunikują się z usługą, odczytując adres IP z rekordu "DNS".
c) W ten sposób Kubernetes implementuje odkrywanie usług za pomocą "DNS". Dodatkowo mikro-serwisy otrzymują adresy IP 
innych mikro-serwisów za pośrednictwem zmiennych środowiskowych.

402) Kubernetes "Namespaces"?
Odpowiedź: "Namespace""" to wirtualne klastry, które umożliwiają całkowite oddzielenie usług i wdrożeń. Dzięki nim można 
oddzielić mikro-serwisy od infrastruktury.
"Namespaces" pomagają zarządzać wieloma środowiskami w jednym klastrze Kubernetes, na przykład różnymi środowiskami 
deweloperskimi, testowymi i produkcyjnymi, bez ryzyka pomieszania zasobów.

403) Kubernetes Apps ze stanami ('States')?
W przypadku aplikacji, które przechowują stan (np. dane), Kubernetes oferuje "Persistent Volumes" oraz "Stateful-Sets" 
jako rozwiązania.
a) "Persistent Volumes (PV)": Są to zasoby, które przechowują dane niezależnie od cyklu życia kontenerów. Mogą być używane 
do przechowywania danych, które muszą przetrwać restart kontenera lub zmiany podów.
b) 'StatefulSets': Używane do zarządzania aplikacjami stanowymi, które wymagają unikalnych identyfikatorów (ID), trwałych 
danych oraz zorganizowanej kolejności uruchamiania i skalowania podów. 'StatefulSets' zapewniają stabilne identyfikatory 
sieciowe i trwałe wolumeny dla każdego z podów, dzięki czemu aplikacje stanowe, takie jak bazy danych, mogą być poprawnie 
uruchamiane i skalowane.
Dzięki tym mechanizmom Kubernetes umożliwia zarządzanie aplikacjami, które muszą przechowywać stan lub korzystać z 
trwałych zasobów.

404) Jaka jest rola demona Docker w środowisku kontenerów?
"Docker daemon" (zwany również "dockerd") to kluczowy komponent Dockera, który działa jako usługa w tle na maszynie hosta. 
Jego rola polega na zarządzaniu i uruchamianiu kontenerów, obrazów, sieci i wolumenów. "Demon Dockera" wykonuje kilka 
głównych funkcji:
a) Zarządzanie cyklem życia kontenerów: "Docker daemon" obsługuje tworzenie, uruchamianie, zatrzymywanie i usuwanie 
kontenerów. Zapewnia, że kontenery działają zgodnie ze specyfikacjami dostarczonymi przez użytkownika.
b) "Zarządzanie obrazami": Demon odpowiada za budowanie, pobieranie i wysyłanie obrazów Docker. Komunikuje się z 
rejestrami Docker (takimi jak "Docker Hub") w celu pobierania obrazów lub przesyłania nowo utworzonych obrazów.
c) "Zarządzanie siecią": Demon zarządza aspektami sieciowymi kontenerów, w tym tworzeniem mostów sieciowych i obsługą 
komunikacji między kontenerami oraz z otoczeniem zewnętrznym.
d) "Zarządzanie wolumenami": Demon obsługuje tworzenie i zarządzanie wolumenami, które służą do przechowywania danych 
poza kontenerami, co pozwala na przechowywanie danych, które przetrwają ponowne uruchomienie kontenerów.
e) "Obsługa API": "Docker daemon" udostępnia "API REST", które pozwala klientowi Docker ("Cli" lub "Gui") lub innym 
narzędziom automatyzacyjnym komunikować się z nim i zarządzać kontenerami.
Podsumowując, "Docker daemon" jest centralnym elementem, który kontroluje i ułatwia operacje kontenerów oraz całe 
środowisko Docker.

405) Zaleta "namespaces" w Kubernetes?
"Namespaces" w Kubernetes umożliwiają tworzenie odrębnych przestrzeni nazw, które pozwalają na izolację zasobów i usług 
w ramach jednego klastra. Dzięki temu różne środowiska (np. deweloperskie, testowe, produkcyjne) mogą współistnieć w tym 
samym klastrze Kubernetes, zachowując jednocześnie swoje własne zasoby i konfiguracje. Główne zalety namespaces to:
a) Izolacja zasobów: Każdy "namespace" może mieć własne zasoby, takie jak Pody, usługi ("Services" konfiguracje czy 
sekretne dane ("Secrets"), co pozwala na separację środowisk i uniknięcie konfliktów między nimi.
b) Bezpieczeństwo i kontrola dostępu: "Namespace" pozwala na precyzyjne zarządzanie dostępem do zasobów, umożliwiając 
kontrolowanie, które aplikacje i użytkownicy mogą mieć dostęp do określonych zasobów w danym "namespace".
c) "Łatwiejsze zarządzanie": Dzięki "namespace" można łatwiej zarządzać i monitorować aplikacje, grupując je według 
środowisk (np. dev, prod) i udostępniając dedykowane narzędzia do monitorowania oraz logowania dla każdego z nich.
d) "Separation of concerns": Dzięki namespace można oddzielić różne mikro-serwisy i ich zależności od infrastruktury, 
co ułatwia ich zarządzanie, aktualizacje i skalowanie w obrębie jednego klastra.
Podsumowując, "namespace" w Kubernetes pozwala na stworzenie logicznych, odizolowanych jednostek w obrębie tego samego 
klastra, co wspomaga zarządzanie aplikacjami, ułatwia bezpieczeństwo i wspiera organizację zasobów w bardziej 
uporządkowany sposób.

406) Jaka jest różnica między rejestrem Docker a repozytorium Docker? 
Odpowiedź: Rejestr Docker ("Docker registry") to zbiór repozytoriów (tam gdzie jest kod), podczas gdy repozytorium Docker 
("Docker repository") to zbiór obrazów.

407) Namespacing? 
Izolowanie zasobów per proces (lub grupa procesów). mProcess, dysk twardy, sieć, użytkownicy, nazwy hostów, IPC.

408) W Kubernetes Engine, co to jest pula węzłów? 
Podzbiór instancji węzłów w obrębie klastra, które mają tę samą konfigurację.

409) Ile pamięci węzła Kubernetes wymaga jako narzut? 
Skalowana ilość zaczynająca się od 25% pamięci i malejąca do 2% pamięci marginalnej w miarę wzrostu całkowitej ilości pamięci.

410) Komenda do utworzenia 10 replik deploymentu o nazwie "ch07-app-deploy"? 
"kubectl scale deployment ch07-app-deploy --replicas=10".

411) W jakich stanach mogą znajdować się deploymenty w Kubernetes? 
Przetwarzanie ("Processing"), Zakończony ("Completed"), Niepowodzenie ("Failed").

412) Wolumeny ("Volumes")? 
Wolumeny to foldery na twardym dysku maszyny hosta, które są montowane ("udostępniane", mapowane) do kontenera. 
Kontener może zapisywać dane w wolumenie i odczytywać dane z niego. Wolumeny zachowują dane, nawet jeśli kontener zostanie 
zatrzymany. Jeśli kontener zostanie ponownie uruchomiony i zamontuje wolumen, dane wewnątrz wolumenu będą dostępne w 
kontenerze.

413) Dane Docker? 
Dane aplikacji (kod + zmienne środowiskowe). Pisane i dostarczane przez dewelopera. Dodawane do obrazu i kontenera w 
fazie budowania. 
a) "Stałe" ("Fixed"): Nie można ich zmienić po zbudowaniu obrazu. Tylko do odczytu, przechowywane w obrazach.
b) Tymczasowe ("Temporary") dane aplikacji (np. dane wejściowe użytkownika). Pobierane / Produkowane w działającym 
kontenerze. Przechowywane w pamięci lub plikach tymczasowych. Dynamiczne i zmieniające się, ale regularnie usuwane. 
Odczyt + zapis tymczasowy, przechowywane w kontenerach.
"Stałe" dane aplikacji (np. konto użytkownika). Pobierane / Produkowane w działającym kontenerze. Przechowywane w plikach 
lub bazie danych. Nie mogą zostać utracone, jeśli kontener zostanie zatrzymany / ponownie uruchomiony. 
Odczyt + zapis, stałe, przechowywane z kontenerami i wolumenami.

414) Jaka jest konwencja nazewnictwa obrazów Docker? 
"username - repository : tag'.

415) Co to jest punkt kontrolny kontenera Docker? 
Punkt kontrolny kontenera Docker to zapisany stan kontenera, w tym jego konfiguracja i dane.

416) Polecenia usuwania w Kubernetes? 
a) "kubectl delete -f kafka-client.yaml".
b) "helm uninstall gke-container-kafka".
c) "'gcloud container clusters delete panda-cluster --zone europe-west4-a".

417) Jak Docker obsługuje ograniczenia zasobów dla kontenerów? 
Docker ustawia limity zasobów za pomocą flag konfiguracyjnych lub poleceń zarządzania zasobami, takich jak "--memory" i 
"--cpu-shares", umożliwiając kontrolowanie zasobów, z których kontener może korzystać.

418) Google Cloud Kubernetes?
a) "gcloud auth login".
b) "gcloud config set project".
c) "gcloud container clusters create".
d) "kubectl get nodes".

419) "Namespace" w Kubernetes?
Zapewnia mechanizm izolowania grup zasobów w obrębie jednego klastra.

420) Kubernetes "Pods", "Services"?
Pod to najmniejsza jednostka wdrożeniowa. Pod może składać się z jednego lub więcej kontenerów.
Usługi ("Services") służą do udostępniania wdrożenia poprzez "Node-Port" lub "Load-Balancer".

421) Udostępnianie usługi Kubernetes ("Exposing a Kubernetes Service")?
Wskaż zewnętrzny adres IP na węzły, ustaw zasady zapory (firewall), aby umożliwić dostęp do węzła.

422) Maszyna wirtualna kontra Kontener Docker?
a) Maszyny wirtualne:
ZALETY: Oddzielone środowiska. Możliwość konfiguracji specyficznych dla środowiska. Konfiguracje środowiskowe mogą być 
dzielone i redukowane w sposób niezawodny.
WADY: Redundantna duplikacja, marnowanie przestrzeni. Wydajność może być wolna, czas uruchamiania może być długi. 
Reprodukcja na innym komputerze / serwerze jest możliwa, ale może być trudna.
Większy wpływ na system operacyjny, wolniejsze działanie, wyższe zużycie przestrzeni dyskowej. Dzielenie, odbudowa i 
ponowne tworzenie mogą być wyzwaniem. Kapsułkują "całe maszyny", a nie tylko aplikacje / środowiska.
b) Kontenery Docker: Mały wpływ na system operacyjny, bardzo szybkie, minimalne zużycie przestrzeni dyskowej. Dzielenie, 
odbudowa i dystrybucja są łatwe. Kapsułkują aplikacje / środowiska, a nie "całe maszyny".

423) Jaka jest idea tagu obrazu?
Obraz może mieć nazwę, a następnie przypisane do tej samej nazwy różne "wersje" tego obrazu.

424) Jaka jest różnica między "CMD" a "Entrypoint" w pliku "Dockerfile"?
"Entrypoint" to domyślne polecenie do uruchomienia po starcie kontenera, podczas gdy "CMD" to polecenie, które musi 
zostać uruchomione przy starcie kontenera.

425) Jakie są różne stany, w jakich kontener Docker może się znajdować w dowolnym momencie?
"Running", "Exited", "Paused".

426) Terminologie Docker?
a) Dockerfile (bez rozszerzeń): Instrukcje w formie czytelnej dla człowieka, które określają, jak uruchomić aplikację 
(zależności + środowisko uruchomieniowe). "Jak klasa java": Instrukcje w formie czytelnej dla człowieka dotyczące 
zachowania aplikacji.
b) "Build": Tworzenie migawki (snapshot) na podstawie Dockerfile. Budowanie / kompilowanie / pakowanie.
c) "Image": Lekka maszyna wirtualna (migawka). ".class" / ".jar".
d) "Tag": Wersja obrazu. Wersja pliku "Jak plik jar".
e) "Container": Instancja obrazu. Działająca lekka maszyna wirtualna. Możemy tworzyć wiele kontenerów z jednego obrazu. 
Instancje obiektów. "Tworzenie nowego obiektu w Javie".
f) "DockerHub": Rejestr obrazów. Repozytorium Maven.

427) Docker Exec?
"docker exec CONTAINER-NAME COMMAND": Uruchamia polecenie na działającym kontenerze.
"run" – tworzy nowy kontener i wykonuje polecenie.
"exec" – wykonuje podobną operację na działającym kontenerze.

428) Sterowniki sieciowe Docker ("Docker Network Drivers")?
a) "Bridge": Domyślny, niestandardowy / zdefiniowany przez użytkownika.
b) "None": Wyłącza całą sieć.
c) "Host": Działa tylko na maszynach z systemem Linux.
d) "Overlay": Dla Docker Swarm.

429) Polecenia Dockerfile?
a) "FROM image": Obraz bazowy dla Twojego obrazu Docker. Każdy obraz powinien rozszerzać inny obraz.
b) "ADD host-dir container-dir": Dodaje pliki z katalogu hosta do obrazu. "ADD" może również przyjmować URL.
"COPY host-dir container-dir": Dodaje pliki z katalogu hosta do obrazu.
c) "RUN command": Polecenie do wykonania podczas procesu budowania obrazu. Przydatne do instalowania oprogramowania lub 
tworzenia katalogów itp.
d) "Env key value": Ustawia zmienną środowiskową.
e) "Workdir path": Tworzy przestrzeń roboczą / domyślny katalog roboczy. Jeśli go zignorujemy, użyty zostanie katalog główny.
f) "EXPOSE port": Udostępnia "port".
g) "CMD (Podaj komendę jaką wykonać kiedy kontener się uruchamia)": Polecenie do wykonania, proces do uruchomienia po 
stworzeniu kontenera. Można nadpisać polecenie.
h) "Entrypoint (Podaj komendę jaką wykonać kiedy kontener się uruchamia)": Polecenie do wykonania, proces do uruchomienia 
po stworzeniu kontenera. Nie można nadpisać polecenia.

430) Polecenia Docker Compose?
a) "docker-compose up": Uruchamia wszystkie kontenery z niestandardową siecią mostu.
b) "docker-compose down": Zatrzymuje aplikację, usuwa kontenery i sieci.
c) "docker-compose ps": Sprawdza status usługi.
d) "docker-compose logs SERVICE-NAME": Sprawdza logi usługi w celu debugowania.

431) Wymień przestrzenie nazw w systemie Linux:
a) "Mount" - mnt.
b) "Process ID" - PID.
c) "Network" - net.
d) "Inter-process communication" - IPC.
e) "Work station and domain" - UTS.
f) "User ID" - user.
