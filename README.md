## Sistema Computacional Hipotético Z808
Implementação da máquina virtual(emulador) do sistema computacional Z808, composto por dois módulos: o executor e a interface visual. Projeto criado para a disciplina de **Programação de Sistemas**, o emulador foi desenvolvido em conjunto utilizando a linguagem **Java**.

## Descrição do processador
1. **Memória:** a memória é definida em palavras de dois bytes(ou 16 bits), com endereçamento a palavra e uma memoria dinâmica. 

2. **Registradores de dados:** Localizado no processador central, permitem um acesso muito mais rápido aos dados armazenados, oferecendo mais velocidade ao processador. **AX** é o acumulador, utilizado como base para a grande maioria das operações, **DX** é o registrador de dados, utilizado como auxiliar ao AX em operações de multiplicação e divisão, além dos demais registradores de pilha.

3. **Endereçamento:** endereçamento imediato, endereçamento direto e endereçamento via registrador.

Definição da máquina e dos conjuntos de instruções aqui disponível [aqui](https://drive.google.com/file/d/1Avzrq8FdZ4Op9Bi3BUXKk6UueELAzjMY/view?usp=sharing).

## Instalação e execução
Será necessária a instalação do **JDK 11** que pode ser encontrado [aqui](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html).

Crie um diretório e baixe o código disponível com o comando `git clone https://github.com/tscientist/virtual-machine-z808.git`.

Nós realizamos a implementação utilizando o Intellij da [JetBrains](https://www.jetbrains.com/pt-br/idea/), e executamos o programa através de uma configuração de run da IDE utilizando o **Java 11**.

Configuração conforme descrito nesta [imagem.](https://drive.google.com/file/d/18fpyTjuA1Y1twbCz83tpAeXtde_y8EEa/view?usp=sharing)
