
# Simulatore della Macchina Enigma - Implementazione JavaFX

![image](https://github.com/user-attachments/assets/f3c3e4e6-5c9f-42a9-998c-9c76fa4647e6)

Un'implementazione completa in JavaFX della famosa macchina crittografica Enigma utilizzata durante la Seconda Guerra Mondiale. Questo progetto simula fedelmente il meccanismo di cifratura elettromeccanico con tutti i suoi componenti principali.

## Presentazione
[Enigma Machine - Canva](https://www.canva.com/design/DAGj4ZGeQdc/248OLdC1lps4uS5iKQxCRA/view?utm_content=DAGj4ZGeQdc&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=h3898c9c09a)

## Caratteristiche

### Componenti Principali
- **Rotori (I, II, III)**: Configurabili con posizioni di scatto
- **Riflessore (A, B, C)**: Schemi di cablaggio fissi
- **Pannello Scambiatore**: Scambi di lettere configurabili (fino a 13 coppie)
- **Tastiera & Pannello Lampade**: Layout QWERTZ (stile tedesco)

### Funzionalità
- Processo autentico di cifratura/decifratura
- Meccanismo di rotazione dei rotori in tempo reale
- Feedback visivo con lampade indicatori
- Posizioni iniziali configurabili
- Supporto per operazioni di backspace/cancellazione
- Inserimento automatico di spazi ogni 5 caratteri (precisione storica)
- Viasualizzazione dei caratteri da cifrare e cifrati (versione moderna)

## Implementazione Tecnica

### Struttura delle Classi
| Classe | Descrizione |
|--------|-------------|
| `EnigmaMachine` | Controller principale della logica |
| `Rotore` | Implementazione del rotore con logica di rotazione |
| `Riflessore` | Componente riflettore |
| `PannelloScambiatore` | Implementazione del pannello scambiatore |
| `Lampadina` | Componente lampada visiva con animazioni |
| `UploadConfigurazioni` | Caricatore delle configurazioni |

### Algoritmi Chiave
1. **Flusso di Codifica dei Caratteri**:
   ```
   Tastiera → Pannello Scambiatore → Rotore Destro → Rotore Centrale → Rotore Sinistro → Riflessore → 
   Rotore Sinistro → Rotore Centrale → Rotore Destro → Pannello Scambiatore → Lampada
   ```

2. **Rotazione dei Rotori**:
   - Il rotore destro avanza ad ogni pressione di tasto (e quando il rotore sinistro raggiunge lo scatto anche se è molto raro)
   - Il rotore centrale avanza quando il rotore destro raggiunge la tacca/scatto (se si trova già nella sua posizione di scatto quando viene attivato si verifica un doppio avanzamento)
   - Il rotore sinistro avanza quando il rotore centrale raggiunge la tacca/scatto

## Guida all'Avvio

### Prerequisiti
- Java 11+
- JavaFX SDK
- Maven

### Installazione
```bash
git clone https://github.com/paglieranipietro/enigma_machine.git
cd enigma_machine
mvn clean install
```

### Esecuzione dell'Applicazione
```bash
mvn javafx:run
```

## Configurazione

### Impostazioni dei Rotori
Modificare `configurazioni.txt` per cambiare:
- Cablaggio dei rotori (righe 1-3)
- Posizioni delle tacche (ultimo carattere di ogni riga dei rotori)
- Cablaggio dei riflessori (righe 4-6)

Esempio di formato (quello attuale):
```
EKMFLGDQVZNTOWYHXUSPAIBRCJ Q  # Cablaggio Rotore I + tacca
AJDKSIRUXBLHWTMCQGZNPYFVOE E  # Cablaggio Rotore II + tacca
BDFHJLCPRTXVZNYEIWGAKMUSQO V  # Cablaggio Rotore III + tacca
EJMZALYXVBWFCRQUONTSPIKHGD    # Riflessore A
YRUHQSLDPXNGOKMIEBFZCWVJAT    # Riflessore B
FVPJIAOYEDRZXWGCTKUQSBNMHL    # Riflessore C
```

## Controlli dell'Interfaccia Utente

| Controllo | Funzione |
|-----------|----------|
| Selettori Rotori | Scegliere il tipo di rotore (I-III) |
| Pulsanti Posizione | Impostare le posizioni iniziali dei rotori |
| Campi Pannello Scambiatore | Inserire coppie di lettere (es. "AB") |
| Interruttore Pannello | Attiva/disattiva il pannello scambiatore |
| Tastiera | Inserire testo da cifrare/decifrare |

## Contesto Storico

Questa implementazione modella la macchina Enigma I della Wehrmacht (versione a 3 rotori) con:
- Layout tastiera QWERTZ standard
- Riflessore UKW-B (variante più comune di base ma ce ne sono altri da poter scegliere)
- Processo di cifratura di livello militare

## Documentazione

### Diagrammi delle Classi
![Diagramma delle Classi](docs/class_diagram.png)

### Diagramma di Sequenza
![Sequenza di Cifratura](docs/sequence_diagram.png)

## Testing

Eseguire i test con:
```bash
mvn test
```

La copertura dei test include:
- Codifica/decodifica dei caratteri
- Logica di rotazione dei rotori
- Sostituzioni del pannello scambiatore
- Caricamento delle configurazioni
- Accensione delle lampadine

## Come Contribuire

1. Fai un fork del progetto
2. Crea un branch per la tua feature (`git checkout -b feature/FeatureIncredibile`)
3. Fai commit delle tue modifiche (`git commit -m 'Aggiungi una feature incredibile'`)
4. Push del branch (`git push origin feature/FeatureIncredibile`)
5. Apri una Pull Request

## Riconoscimenti
- Progettisti originali della macchina Enigma
- Alan Turing e il team di Bletchley Park
- Comunità JavaFX
- Ricercatori di crittografia
