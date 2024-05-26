# Programų sistemų kūrimo laboratoriniai

## Konfigūracija
- OpenJDK 17.0.10
- Boss/Wildfly 26.1.3.Final
- H2 (driver 1.4.196)

## TODO:
- Pademonstruoti darbo su pasirinktais įrankiais (IDE, Application Server, Build tool, Version Control System) minimalų ciklą (**0.15**):
  - [x] naudojantis IDE padaryti minimalų pakeitimą projekto išeities tekstuose ir surinkti projektą, (**0.05 balo**)
  - [x] gebėti parodyti, kurioje vietoje IDE yra pasiekiamas dalykinis serveris (Application Server), kaip startuoti/sustabdyti dalykinį serverį, paleisti surinkimo rezultatus vykdymui dalykiniame serveryje, (**0.05 balo**)
  - [x] projekto išeities tekstų pakeitimus patalpinti versijų kontrolės sistemoje. (**0.05 balo**)
- DB, ORM/JPA ir DataMapper/MyBatis (**0.25**):
    - [x] Sukurti duomenų bazę. Turi būti naudojami one-to-many ir many-to-many ryšiai. (**0.05 balo)**
    - [x] Sugeneruotoms/parašytoms JPA esybėms gebėti parodyti ir paaiškinti, kaip esybių laukai (Java klasių laukai) atitinka DB lentelių stulpelius; ką daryti, jei norime lauką pavadinti kitaip nei pavadintas atitinkamas stulpelis. Gebėti parodyti ir paaiškinti, kur esybėse yra one-to-many bei many-to-many ryšiai ir kaip jie atitinka DB lenteles. (**0.1 balo**)
    - [x] Tą patį gebėti paaiškinti MyBatis esybėms. (**0.1**)
- Įgyvendinti vieną panaudos atvejį (**0.6**):
  - UI: su laisvai pasirinkta technologija:
    - [x] reikia sukurti bent vieną puslapį, pateikiantį DB esančius duomenis/dalį duomenų. Būtina pateikti kelių susijusių DB esybių duomenis (pvz.: universitetas ir jo studentai; studentai ir jų lankomi kursai). T.y., formuojant UI turi būti naviguojama per DB ryšius one-to-many/many-to-one/many-to-many (bent per vieną). (**0.1**)
    - [x] reikia sukurti bent vieną formą, leidžiančią įvesti duomenis. Įvesti duomenys turi būti automatiškai įrašomi į kokios nors klasės objektą (data binding), o vėliau ir į DB. (**0.1**)
  - [x] Dalykinio funkcionalumo komponentas_ (Business logic component): turi būti bent vienas CDI komponentas, apdorojantis per UI įvestus duomenis. Reikia gebėti paaiškinti, kada Java klasė virsta komponentu, ką reiškia @RequestScoped, @SessionScoped, @ApplicationScoped bei @Inject anotacijos. (**0.05**)
  - [x] Duomenų prieigos komponentas_ (Data Access component - DAO): Turi būti duomenų išsaugojimo/modifikavimo duomenų bazėje DAO komponentas:
    - Naudojantis ORM/JPA (**0.1**)
    -Naudojantis DataMapper/MyBatis (**0.1**)
  - [x] Reikia gebėti paaiškinti skirtumus/privalumus/trūkumus tarp ORM ir DataMapper (kada ką geriau naudoti). (**0.1**)
  - [x] Būtinos automatinės/deklaratyvios DB transakcijos (rankomis rašyti "begin()/commit()" negalima). (**0.05**)
  - [x] Optimistinis rakinimas: pademonstruoti, kaip gaunamas OptimisticLockException, ir kaip programuotojas gali įgyvendinti tinkamą apdorojimą (0.3)
    - Kas nutinka su einamąja transakcija, kai gaunamas OptimisticLockException?
    - Kas nutinka su einamuoju EntityManager, kai gaunamas OptimisticLockException? Kaip išsaugoti esybę į DB po OptimisticLockException?
  - [x] Asinchroninis komunikavimas: pateikti ilgą skaičiavimą atliekančio komponento (galima naudoti Thread.sleep(...) ir teisingo komunikavimo su juo pavyzdį (0.2)
    -  Ar gali asinchroninis komponentas įsijungti į kvietėjo pradėtą transakciją?
    -  Ar gali asinchroninis komponentas naudoti @RequestScoped EntityManager?
  - Glass-box extensibility: pateikti po vieną pavyzdį:
    - [x] CDI alternatives (@Alternative), alternatyvos pasirinkimas beans.xml faile (0.1)
    - [x] CDI specialization (@Specializes) (0.1)
    - [x] CDI Interceptor, interceptor'ių įjungimas/išjungimas beans.xml faile (0.1)
    - [x] CDI Decorator, dekoratorių įjungimas/išjungimas beans.xml faile ( 0.1)
  - [x] RESTful paslaugos pavyzdys: įgyvendinti RESTful paslaugas, leidžiančias gauti (GET), sukurti (POST) ir modifikuoti (PUT) kokią nors esybę duomenų bazėje (0.1)