# Advent of Code 2025 - Día 6: Cephalopod Math

Este repositorio contiene la solución para el Día 6 del Advent of Code 2025. El enfoque principal ha sido aplicar principios de ingeniería de software robustos (SOLID, Clean Code) y patrones de diseño adecuados para garantizar un código mantenible, legible y extensible.

## Principios de Diseño y Arquitectura

El proyecto se divide en dos partes (`a` y `b`), donde la parte `b` representa una evolución en la complejidad y robustez de la solución.

### Principios Generales Aplicados

- **Single Responsibility Principle (SRP)**: Cada clase tiene una única responsabilidad. Por ejemplo, `FileInstructionReader` se encarga exclusivamente de la entrada de datos, mientras que `OperationProcessor` (en la parte A) y `CephalopodMathSolver` (en la parte B) manejan la lógica de negocio.
- **Clean Code**: Se ha priorizado la legibilidad mediante nombres descriptivos de métodos y variables, evitando "números mágicos" y encapsulando lógica compleja en métodos privados auxiliares.
- **Inmutabilidad**: Uso extensivo de `record` (como `FileOutput`, `Problem`) para garantizar que los datos transferidos no se modifiquen inesperadamente, favoreciendo la seguridad en hilos (aunque no se use concurrencia aquí) y la predictibilidad.

---

## Parte A: Solución con Arquitectura MVC

En la primera parte, hemos implementado una solución estructurada bajo el patrón **Modelo-Vista-Controlador (MVC)**, garantizando una estricta separación de responsabilidades y un flujo de control claro.

### Decisiones Técnicas y Patrones

1.  **Patrón MVC (Model-View-Controller)**:

    - **Controlador (`Controller`)**: Orquestador principal. Gestiona el flujo de la aplicación: solicita la lectura de datos, invoca al solver y ordena a la vista mostrar los resultados.
    - **Vista (`ConsoleView`)**: Responsable única de la interacción con el usuario (salida por consola). No contiene lógica de negocio.
    - **Modelo**: Compuesto por `OperationProcessor` (lógica) y `FileInstructionReader` (acceso a datos).

2.  **Factory Method Pattern**:

    - **`SolverFactory`**: Encapsula la lógica de creación del `Solver`. El Controlador no conoce qué implementación concreta de `Solver` está usando, solo le pide uno a la factoría. Esto facilita la extensión futura (ej. devolver distintos solvers según el input).

3.  **Inversión de Dependencias (DIP) y Abstracción**:
    - **`Solver` Interface**: Definimos un contrato para los solucionadores. `OperationProcessor` implementa esta interfaz. Esto permite que el sistema dependa de abstracciones y no de implementaciones concretas.

### Diagrama de Clases (Parte A - MVC)

```mermaid
classDiagram
    class Main {
        +main()
    }
    class Controller {
        -ConsoleView view
        -FileInstructionReader reader
        +run()
    }
    class ConsoleView {
        +showResult(long)
        +showError(String)
    }
    class SolverFactory {
        +createSolver(FileOutput): Solver
    }
    class Solver {
        <<interface>>
        +solve() long
    }
    class OperationProcessor {
        -FileOutput data
        +solve() long
    }
    class FileInstructionReader {
        -filePath: String
        +readAllData() FileOutput
    }
    class FileOutput {
        <<record>>
        +List dataLine1...
    }

    Main ..> Controller : crea
    Main ..> ConsoleView : inyecta
    Main ..> FileInstructionReader : inyecta

    Controller --> ConsoleView : actualiza
    Controller --> FileInstructionReader : usa
    Controller ..> SolverFactory : usa
    Controller --> Solver : usa

    SolverFactory ..> OperationProcessor : crea
    OperationProcessor ..|> Solver : implementa
    OperationProcessor --> FileOutput : consume
    FileInstructionReader ..> FileOutput : produce
```

---

## Parte B: Solución Avanzada (Refactorización y Modelado de Dominio)

La segunda parte introduce una complejidad mayor en el parsing (columnas variables, estructura de grid). Para manejar esto, la arquitectura evoluciona hacia un diseño más orientado a objetos y al dominio.

### Decisiones Técnicas y Patrones

1.  **Grid Abstraction (Gestión de Estructuras de Datos)**:

    - Se introduce `Grid` para abstraer el acceso a los datos crudos. Esto permite consultar coordenadas `(x, y)` sin preocuparse por los límites de las listas subyacentes, encapsulando la manejo de errores de índice.

2.  **Strategy Pattern (vía Enum)**:

    - La clase `Operator` es un Enum que encapsula la lógica de cada operación aritmética (`+`, `*`). Esto cumple con el **Open/Closed Principle (OCP)**: si se añaden nuevos operadores en el futuro (ej. `-` o `/`), solo se modifica el Enum, sin tocar la lógica de resolución de problemas.

3.  **Separation of Concerns (Parsing vs Solving)**:

    - **`ProblemScanner`**: Su única responsabilidad es _identificar_ problemas dentro del `Grid`. No los resuelve. Implementa una lógica de escaneo visual (de derecha a izquierda) similar a como lo haría un humano.
    - **`NumberParser`**: Extrae valores numéricos de columnas, aisladando la lógica de parsing de texto sucia.
    - **`Problem`**: Representa un problema matemático válido y autocontenido. Sabe cómo resolverse a sí mismo (Information Expert), delegando la operación matemática al `Operator`.

4.  **Gestión de Flujos de Datos**:
    - El flujo es `Raw File -> Grid -> ProblemScanner -> List<Problem> -> Result`. Cada transformación refina los datos, pasando de texto sin estructura a objetos de dominio rico.

### Diagrama de Clases (Parte B)

```mermaid
classDiagram
    class Main {
        +main()
    }
    class CephalopodMathSolver {
        +solve() long
    }
    class Grid {
        -FileOutput rawData
        +getChar(row, col) String
        +getWidth() int
    }
    class ProblemScanner {
        -Grid grid
        -NumberParser numberParser
        +scan() List~Problem~
    }
    class NumberParser {
        -Grid grid
        +extractNumber(col) long
    }
    class Problem {
        <<record>>
        -List~Long~ numbers
        -Operator operator
        +solve() long
    }
    class Operator {
        <<enumeration>>
        PLUS
        MULTIPLY
        +apply(long, long) long
    }

    Main --> CephalopodMathSolver : usa
    CephalopodMathSolver --> Grid : crea
    CephalopodMathSolver --> ProblemScanner : usa
    ProblemScanner --> Grid : consulta
    ProblemScanner --> NumberParser : usa
    ProblemScanner ..> Problem : crea
    Problem --> Operator : usa
```
