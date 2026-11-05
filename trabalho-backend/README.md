# Trabalho final - Backend (Restaurante)

Extensão do servidor visto na disciplina Back-End: Web Services (`authserver`), adicionando
categorias, produtos e pedidos de um restaurante.

## Membros

* [NOME DO MEMBRO 1]
* [NOME DO MEMBRO 2]
* [NOME DO MEMBRO 3]

## Vídeo

[LINK DO VÍDEO NO YOUTUBE]

## Como executar

Requer Java 21.

```
./gradlew bootRun
```

* Swagger: http://localhost:8080/api/
* Administrador criado pelo `Bootstrapper`: `admin@authserver.com` / `admin`

## Classes novas

| Classe | Relacionamento |
|---|---|
| `Category` | one-to-many com `Product` |
| `Product` | many-to-one com `Category` |
| `Order` | many-to-many com `Product`, usando `OrderItem` como tabela no meio |
| `OrderItem` | tabela no meio entre `Order` e `Product`, com a quantidade |

## Endpoints

Rotas relativas a `/api`.

| Método | Rota | Acesso | Situação |
|---|---|---|---|
| POST | `/categories` | ADMIN | Pronto |
| GET | `/categories` | Público | Pronto |
| GET | `/categories/{id}` | Público | A fazer |
| PATCH | `/categories/{id}` | ADMIN | A fazer |
| DELETE | `/categories/{id}` | ADMIN | A fazer |
| POST | `/products` | ADMIN | Pronto |
| GET | `/products?category=1&sortDir=DESC` | Público | Pronto |
| GET | `/products/{id}` | Público | Pronto |
| PATCH | `/products/{id}` | ADMIN | A fazer |
| DELETE | `/products/{id}` | ADMIN | Pronto |
| POST | `/orders` | Autenticado | Pronto |
| GET | `/orders/{id}` | Dono ou ADMIN | Pronto |
| PUT | `/orders/{id}/products/{idProduct}?quantity=2` | Dono ou ADMIN | Pronto |
| DELETE | `/orders/{id}/products/{idProduct}` | Dono ou ADMIN | A fazer |

## Uso de inteligência artificial

Durante a preparação deste código, os autores usaram Claude (Opus 5.5) para gerar a primeira
versão das classes de categorias, produtos e pedidos, seguindo o servidor da disciplina. Após usar
essa ferramenta, os autores revisaram e editaram o conteúdo conforme necessário e assumem total
responsabilidade pelo conteúdo.
