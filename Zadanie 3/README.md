# Zadanie 3 – aplikácia na šifrovanie a dešifrovanie s využitím RSA a kontroly integrity

Vytvorenú aplikáciu na šifrovanie súborov (zadanie 2)  rozšírte tak, že vykonáte kontrolu integrity,  prostredníctvom MAC alebo AEAD módu. Mali by ste byť schopný overiť či došlo k modifikácii vášho zašifrovaného súboru. Na šifrovanie odporúčame použiť AES GCM alebo AES CCM mód.

Zároveň vygenerovaný symetrický kľuč K zašifrujete asymetricky a pripojíte k zašifrovanému súboru v rámci hlavičky/päty súboru s metadátami potrebnými k dešifrovaniu (pridáte aj  informáciu na kontrolu integrity). Dbajte na to, aby ste vedeli skontrolovať aj integritu zašifrovaného súboru.

Aplikácia vygeneruje náhodný kľúč, ktorým plain text (alebo súbor) zašifrujete. Vygenerovaný AES kľúč zašifrujete RSA vereným kľúčom (verejny kľuč osoby, ktorá má súbor dešifrovať) a priložíte do hlavičky zašifrovaného súboru. Do hlavičky priložíte aj ostatne údaje potrebné na dešifrovanie a kontrolu integrity.

Pri dešifrovaní si osoba, ktorej ste súbor poslali najskôr prečíta informácie z hlavičky a vykoná kontrolu integrity. Ak súbor nie je poškodený, dešifruje pomocou RSA šifrovací kľuč uložený v hlavičke a pomocou dešifrovaného symetrického kľúča dešifruje súbor.
