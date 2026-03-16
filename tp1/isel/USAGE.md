# Usage (tp1.isel)

## Sistema academico (`tp1.isel.AppISEL`)

Objetivo:

- Registar alunos e docentes.
- Criar UCs e gerir inscricoes.
- Lancar avaliacoes e calcular nota final.
- Gerar relatorio por UC.

Fluxo tipico:

1. Criar UC.
2. Registar aluno.
3. Inscrever aluno na UC.
4. Lancar avaliacao.
5. Calcular nota final ou gerar relatorio.

Entradas e validacoes:

- Email e validado de forma simples (contem `@` e `.`).
- Ano do aluno aceita valores 1 a 3.
- Nota (0-20) e peso (0-100) sao validados.
- Peso total por UC nao pode exceder 100.

Saidas:

- Mensagens de sucesso/erro na consola.
- `RelatorioUc` com inscritos, avaliados, media, aprovados e reprovados.
