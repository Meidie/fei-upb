# Zadanie 7: TLS (HTTPS) komunikácia s web serverom

Cieľom tohto zadania je vytvorenie bezpečnej komunikácie medzi užívateľom (browserom) a serverom a testovanie možností zneužitia nezabezpečeného, alebo nedostatočne zabezpečeného spojenia.

#### Úlohy zadania:
- Vytvorte vlastný server a nainštalujte web server (apache, ngnix atd.)
- Vytvorte jednoduchú web aplikáciu na testovacie účely (jednoduchú stránku, popr. Formulár na prihlasovanie )
- Nakonfigurujte web server aby počúval cez http protokol na štandardnom porte
- Pomocou nástroja na sniffovanie komunikácie overte možnosť sledovania komunikácie medzi browserom a vašim serverom. Zistenia popíšte a zdôvodnite vo vašej dokumentácii k zadaniu
- Nakonfigurujte web server na komunikáciu pomocou https protokolu. Pri konfigurácii sa riaďte odporúčaniami pre vytvorenie správnych bezpečnostných nastavení (odporúčané verzie TLS, odporúčané veľkosti kľúčov a použitie šifrovacích algoritmov, atď)
- Vykonajte základne testovanie bezpečnosti https konfigurácie a odhalené nedostatky odstránte)
