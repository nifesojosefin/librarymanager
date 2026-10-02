Jag har skapat ett bibliotekshanteringsprogram som består av Book (record),
Member (klass), Library (klass) och LibrarySystem (klass med main-metoden).
Jag löste uppgiften genom att ha en array (booksBorrowingStatus) som håller reda på vilka böcker
som är utlånade och till vem. Varje jämnt index i arrayen representerar index för
bokobjekt i arrayen books. Efterföljande udda index i arrayen representerar index för eventuellt
medlemsobjekt i arrayen members som har lånat boken. I början sätts alla udda index i arrayen 
booksBorrowingStatus till -1 då det i programmet betyder att boken inte är utlånad. 
När bokobjekt skapas läggs indexvärdet till bokobjektets position i arrayen books in i 
arrayen booksBorrowingStatus. När en medlem lånar ett bokobjekt ändras indexvärdet till höger 
om bokobjektets indexposition i arrayen booksBorrowingStatus till 
medlemobjektets index i arrayen members. När boken returneras ändras indexvärdet i 
arrayen booksBorrowingStatus till -1 igen.

För att skapa bokobjekten passar record som datatyp. Detta eftersom en bok med dess titel, författare
och årtal har oföränderliga värden och record är en oföränderlig datatyp där objektets värden
inte kan ändras efter att objektet har skapats. För att skapa medlemsobjekten använde jag däremot en 
klass, vilket passar bra eftersom värdena då kan ändras efter att objektet har skapats om det 
finns sådana metoder. I member-klassen ändras värdet för fältet numberOfBooksBorrowed när medlemmen
lånar eller returnerar en bok i programmet. Jag valde även att lägga main-metoden i
en egen klass istället för att ha den i Library-klassen, för bättre struktur.

I Library-klassen används samma kod för att verifiera att giltig data matats in av användaren vid
frågan om medlemsnummer i både borrowBook-metoden och returnBook-metoden. Jag testade att
skapa metoder som kontrollerar inputen för att minska upprepning av kod men valde sedan 
att behålla upprepningen då metodernas steg blir mer lättlästa och enklare att följa.