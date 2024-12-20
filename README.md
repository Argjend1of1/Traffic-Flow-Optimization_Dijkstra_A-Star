# Traffic Flow Optimization in a City Grid

---

## **Përshkrimi i Projektit**
Ky projekt përdor algoritmet **Dijkstra** dhe **A\*** për të optimizuar qarkullimin e trafikut në një qytet të modeluar si një rrjet nyjesh dhe rrugë me vonesa trafiku. Projekti është krijuar për të gjetur rrugën më të shpejtë midis dy nyjeve, duke marrë parasysh të dhënat në kohë reale për trafikun dhe bllokimet e mundshme.

---

## **Karakteristikat Kryesore**
1. **Dijkstra's Algorithm**  
   - Përdoret për raste statike ku trafiku nuk ndryshon gjatë kohës së analizës.
2. **A\* Algorithm**  
   - Shfrytëzon heuristikën (distanca Euclidean) për të bërë parashikime dhe për të optimizuar rrugët në kohë reale.
3. **Simulimi i Trafikut**  
   - Rregullon peshat e skajeve në graf duke imituar ndryshime dinamike në trafik çdo disa sekonda.
4. **Output Dinamik**  
   - Gjen rrugën më të shpejtë dhe shfaq kostot dhe kohën e udhëtimit, të përshtatura me kushtet aktuale të trafikut.

---
## Kërkesat Teknike
- Java Development Kit (JDK): Versioni 8 ose më i ri.
- IDE: IntelliJ IDEA, Eclipse, ose terminal me mjedis të konfiguruar për Java.
- Git: Për klonimin e repository-it dhe menaxhimin e kodit.

---

## Si të Ekzekutoni Projektin

1. Klononi repository-in:
   ```bash
   git clone https://github.com/Argjend1of1/Traffic-Flow-Optimization_Dijkstra_A-Star.git
   cd src

## Ekzekutimi i Programit
1. Hapni klasën `Main` dhe ekzekutoni programin.
2. Zgjidhni një nyje fillestare dhe një nyje përfundimtare.
3. Programi do të ekzekutojë të dy algoritmet:
   - Dijkstra për raste statike.
   - A\* për raste dinamike, duke përdorur heuristikën.
4. Simulimi i trafikut përditëson peshat e rrugëve automatikisht çdo 20 sekonda.

---

## Struktura e Projektit
1. `Graph`
   - Përfaqëson grafikun si një listë fqinjësh dhe përmban funksione për menaxhimin e nyjeve dhe skajeve.
2. `Pathfinding`
   - Përmban algoritmet për gjetjen e rrugës më të shkurtër dhe llogaritjen e heuristikës.
3. `TrafficSimulator`
   - Simulon ndryshimet dinamike të trafikut në graf.
4. `Main`
   - Është pika hyrëse e programit që lidh të gjitha pjesët dhe ofron ndërfaqe përdoruesi.
