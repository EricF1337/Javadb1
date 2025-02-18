# Biblioteksapplikation

## Beskrivning
Detta är en enkel biblioteksapplikation skriven i Java som använder JDBC för att kommunicera med en MySQL-databas. Applikationen tillåter användare att låna och lämna tillbaka böcker samt se sina lån. Administratörer kan lägga till och ta bort böcker från bibliotekets katalog.

## Funktioner
### För användare
- Låna en bok (endast om den är tillgänglig)
- Lämna tillbaka en bok
- Lista sina nuvarande lån
- Lista alla böcker och se deras tillgänglighet

### För administratörer
- Lägga till nya böcker i biblioteket
- Ta bort böcker ur biblioteket
- Lista alla böcker och deras lånestatus

## Krav
- Java 8 eller högre
- MySQL-databas
- JDBC-drivrutin för MySQL

## Installation
1. Klona detta repository:
   ```bash
   git clone <repository-url>
