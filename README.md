# MobileTechnicalChallange

Løsningen min har stort fokus på "back-end" funksjonalitet, og litt mindre på designsiden. Dette er på grunn av det er der mine styrker som utvikler ligger. Nå har det blitt en del snarveier i koden som et resultat av tidsnød, og i ettertid skulle jeg ønske jeg hadde brukt mer tid på å forsikre høy kodekvalitet fremfor å prøve å bli ferdig med all funksjonalitet.
Jeg har prøvd å forholde meg til best-practices når det kommer til Android-arkitektur, og jeg har prøvd å holde ting separert. Jeg har også forsøkt å bruke anbefalte biblioteker og komponenter for å opprettholde responsiviteten i appen.

Personlig er jeg veldig fornøyd med å klare å holde meg tett på Android-arkitekturprinsipper ved å bruke ViewModels, ha repositories og ulike datakilder.
Jeg er også veldig fornøyd med å ha fått til såpass mye funksjonalitet, selv om den andre av de store committene bærer preg av at det har gått litt fort i svingene.
Med mer tid så er dette noe som selvfølgelig hadde blitt refakturert og rettet opp i. Jeg har også tidligere fått satt opp Room for offline lagring, men denne gangen fant jeg ikke ut hvordan jeg skulle kjøre DAO-funksjonene på en annen tråd. Tror det har sammenheng med den litt mer omfattende arkitekturen.

En ide som slo meg i starten av prosjektet var også å legge inn longPress-funksjonalitet for å midlertidig blåse opp annonsen. Dette hadde kanskje også vært en ide som hadde fått mer tid dersom annonsedataen innehold mer informasjon.
Kanskje også få brukt litt mer av dataen som var tilgjengelig, f.eks sortere listen etter popularitet. Filtrere til bare "Gis bort" hadde også vært en ide dersom jeg hadde hatt mer tid.

Alt i alt en morsom oppgave, og gøy å jobbe litt under press igjen. Jeg føler jeg har fått vist frem en del av det jeg kan, men ikke alt. Føler også at jeg har fått vist at jeg i stand til å bruke verktøy som kommer både gjennom Kotlin, og det nye og moderne Android-rammeverket.

Liste over ting som burde utbedres før det går til prod:
- Burde ikke være mulig å trykke seg tilbake til Splash Screen
- Splash screen burde også sjekke om dataen er klar til å vises frem, og ikke stole blindt på at alt er klart etter 4 sekunder
- Refakturering av kode, særlig fra stor commit nr. 2
- Offline lagring
- Noget bedre testdekning
- Kanskje bytte ut Shrek med en logo