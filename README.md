[Programação Concorrente](https://www.dcc.fc.up.pt/~edrdo/aulas/pc), CC3037, DCC/FCUP

_Eduardo R. B. Marques, DCC/FCUP_


# Ficha 1

## 1

- Compile o código de `HelloWorld.java` usando o comando:

  `javac HelloWorld.java`
  

- Execute agora `HelloWorld` várias vezes. 

  `
  java HelloWorld
  `

   Pelo menos para `hello4` deverá observar com frequência um "output" diferente para o programa. 

- Em `main()`, comente as chamadas a `hello1()`, `hello2` e `hello3()` para nos podermos focar na funcionalidade de `hello4()`. 

- Agora comente a chamada a `join()` no método `hello4()`. Compile e depois execute algumas vezes o programa indicando 4 threads. Observe que o facto de a "main thread" terminar não implica que o programa termine. 


- Em `hello4()`, descomente a linha com `t[i].setDaemon(true)` código (antes de `t[i].start()`).

  A chamada a [setDaemon()](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#setDaemon-boolean-) faz com que as threads lançadas da "main thread" passem a ser "daemons". O programa deverá agora acabar logo que a "main thread" termine. Se necessário especifique um número de threads maior para observar esse comportamento (ex. 32).

## 2

Compile e depois execute o código em `SleepAndInterrupt.java` várias vezes. O programa pode ter 2 comportamentos distintos. Interprete o fluxo do programa em cada um deles.

## 3

Considere o programa em `CounterExample.java`. 

- Compile o código.
- Execute o programa, começando por especificar um número de threads igual a 2 e número de incrementos por thread igual a 200. Execute 5 a 10 vezes. É perceptível algum erro para `BuggyCounter` ?
- Agora teste com 4 threads e número de incrementos por thread igual a 5000. A situação mantém-se? Deverá conseguir observar um comportamento incorrecto para `BuggyCounter` com alguma frequência. Experimente duplicar o número de threads e/ou número de passos. Deverá observar um comportamento incorrecto com ainda maior frequência.

## 4

Considere o programa em `ArraySum.java` que cria um array com números pseudo-aleatórios (em `buildArray()`) e depois calcula a soma dos seus elementos (em `sumArray()`). 

Conceba uma variante deste programa em nova classe/novo ficheiro tal que:

1. O trabalho em `sumArray()` seja dividido por várias threads em número definido por `numberOfThreads` em `main()` para além da "main thread". A "main thread" deverá ter apenas o papel de "coordenadora": cria as threads para o trabalho de soma e depois aguarda pelo término destas.

2. Cada thread faça a soma de uma secção distinta do array  em partes iguais (por simplicidade pode especificar o tamanho do array por forma a ser divísivel pelo número de threads).

3. As somas parciais de cada thread devem permitir obter o resultado final. Para tal será preciso algum cuidado na sincronização entre "threads"! Três possíveis aproximações são (sugere-se que experimente todas!):
    
    1. Defina uma classe auxiliar (ex. `Result`) com um campo inteiro `value` e use blocos `synchronized` para a actualização de `value`.
  
    2. Emprege um objecto de tipo `java.util.concurrent.atomic.AtomicInteger` - veja a [documentação dessa classe para perceber o que poderá ser adequado](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html).

    3. Defina um array de "resultados parciais" com tamanho igual ao número de threads, para que cada thread no fim actualize apenas a "sua" posição com a soma parcial que fez. No final a "main thread" pode somar só os valores no array de resultados parciais.

Compare os resultados com o programa original `ArraySum.java` para validar a correção da sua implementação. 







