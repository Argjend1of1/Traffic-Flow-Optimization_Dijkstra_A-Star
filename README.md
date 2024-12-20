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
---
### **Shembull i Ekzekutimit**

**1. Grafin Inicial**  
Ky grafik përmban nyje dhe lidhje me peshat përkatëse si më poshtë:  

```java
// Shton nyje në graf.
graph.addNode("A");
graph.addNode("B");
graph.addNode("C");
graph.addNode("D");
graph.addNode("E");
graph.addNode("F");

// Shton lidhje (edge) midis nyjeve me peshat përkatëse.
graph.addEdge("A", "B", 10);
graph.addEdge("B", "C", 15);
graph.addEdge("C", "D", 20);
graph.addEdge("D", "E", 25);
graph.addEdge("E", "F", 30);
graph.addEdge("A", "D", 40);
graph.addEdge("B", "E", 35);
```
## **Rezultati fillestar pa përditësim trafiku:**
![image](https://github.com/user-attachments/assets/9418ddc3-12cd-461e-ab13-f54edc72632d)

## **Rezultati pas një përditësimi të trafikut pas 20 sekondave:**
![image](https://github.com/user-attachments/assets/81dfd584-f37b-4163-9781-5ce38fcb11a5)
---
## **Kontribuesit në këtë projekt:** 
- Argjend Nimanaj
- Alfred Paloku
- Brikenda Zogaj
- Gresa Halili
- Syzana Kryeziu
