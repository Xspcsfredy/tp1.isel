# tp1 - Sistema Academico (tp1.isel)

Projeto em Java focado no package `tp1.isel`, que implementa um sistema academico de consola para gestao de alunos, docentes e unidades curriculares.

## Estrutura

- `src/tp1/isel` - Sistema academico com menu interativo (`AppISEL`).

## Requisitos

- Java 8 ou superior.

## Compilar e executar

Os exemplos abaixo assumem PowerShell no Windows. Execute a partir da pasta do projeto.

```powershell
cd src

# Sistema academico (ISEL)
javac tp1/isel/*.java
java tp1.isel.AppISEL
```

## Funcionalidades

- Registar, remover e listar alunos.
- Registar e remover docentes.
- Criar UCs, inscrever e anular inscricoes.
- Lancar avaliacoes e calcular nota final.
- Gerar relatorio por UC.

## Notas importantes

- O estado e mantido em memoria; nao existe persistencia em ficheiros ou base de dados.
- Validacao basica de email (contem `@` e `.`).
