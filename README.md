# PostViewer

**Aluno:** Bernardo Fernandes Filho 

**Disciplina:** Programação para Dispositivos Móveis

---

## Descrição

O PostViewer é um aplicativo Android que consome a API pública [JSONPlaceholder](https://jsonplaceholder.typicode.com) para exibir uma lista de posts e os comentários de cada post. O usuário também pode adicionar comentários locais, que são persistidos no dispositivo entre sessões.

### Funcionalidades

- Listagem de posts carregados da API
- Visualização dos comentários de cada post
- Adição de comentários locais, salvos no banco de dados do dispositivo
- Comentários locais persistem mesmo após fechar e reabrir o aplicativo
- Tratamento de estados de carregamento e erro na interface

---

## Como executar o projeto

### Pré-requisitos

- [Android Studio](https://developer.android.com/studio) Hedgehog ou superior
- JDK 11 ou superior (incluso no Android Studio)
- Conexão com a internet (para carregar os posts e comentários da API)

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/BernardoFFilho/postviewer.git
   ```

2. Abra o Android Studio e selecione **File → Open**, depois navegue até a pasta do projeto clonado.

3. Aguarde o Gradle Sync terminar automaticamente. Caso não inicie, acesse **File → Sync Project with Gradle Files**.

4. Conecte um dispositivo Android (API 26 ou superior) ou inicie um emulador com a mesma versão mínima.

5. Clique em **Run → Run 'app'** ou pressione `Shift + F10`.

---

## Tecnologias e Bibliotecas

| Tecnologia / Biblioteca | Finalidade |
|---|---|
| Kotlin | Linguagem principal do projeto |
| Jetpack Compose | Construção da interface declarativa |
| Navigation Compose | Navegação entre telas |
| ViewModel + StateFlow | Gerenciamento de estado da UI |
| Retrofit | Consumo da API REST JSONPlaceholder |
| Gson Converter | Desserialização das respostas JSON |
| Room | Persistência local dos comentários |
| Corrotinas (kotlinx.coroutines) | Operações assíncronas (rede e banco) |

---

## Decisões de Design

### Retrofit para consumo de API
O Retrofit foi escolhido por ser a biblioteca padrão do ecossistema Android para consumo de APIs REST. Sua integração com corrotinas Kotlin (funções `suspend`) simplifica o código assíncrono e elimina a necessidade de callbacks.

### Room para persistência local
O Room foi utilizado por ser a solução oficial do Android para bancos de dados SQLite. Ele oferece verificação de queries em tempo de compilação e suporte nativo a `Flow`, permitindo que a lista de comentários locais seja atualizada na tela automaticamente sempre que um novo comentário é inserido, sem recarregamento manual.

### ViewModel + StateFlow para gerenciamento de estado
O ViewModel garante que os dados sobrevivam a mudanças de configuração (como rotação de tela). O StateFlow foi preferido ao LiveData por ser nativo de Kotlin e integrar-se naturalmente com Jetpack Compose via `collectAsState()`.

### Arquitetura em camadas
O projeto foi organizado em três camadas distintas:
- **`data/remote`** — modelos e cliente da API (Retrofit)
- **`data/local`** — entidade e DAO do banco de dados (Room)
- **`data/repository`** — repositório que centraliza o acesso a ambas as fontes
- **`ui/`** — ViewModels e telas Compose

Essa separação mantém cada componente com uma única responsabilidade e facilita a manutenção do código.

### Navigation Compose para navegação
A navegação foi implementada com Navigation Compose por ser a abordagem recomendada pelo Google para projetos com Jetpack Compose. O ID do post é passado diretamente na rota como argumento inteiro (`post_detail/{postId}`), mantendo a navegação simples e sem dependências extras.

---

## Prints das Telas

Os prints do aplicativo em execução estão disponíveis na pasta [`docs/`](./docs/).
