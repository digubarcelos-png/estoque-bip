# Estoque Bip — Android

Aplicativo nativo de contagem de estoque, Android 8.0 ou superior.

## Novidades da versão 1.6

- Tabela de conversão por produto incluída, usando a coluna Fator Conv - PC tanto para CX quanto para PL.
- Identificação por Item Number e códigos alternativos não ambíguos de 3rd Item Number.
- Para CX e PL, há quantidade de embalagens e um campo separado de peças soltas. Quando peças soltas ultrapassam o fator em PC da unidade selecionada, mostra Quantidade excede o pack e impede salvar. A quantidade igual ao pack é aceita.
- Total em PC = quantidade de embalagens × fator em PC + peças soltas. Para PC, a quantidade é registrada diretamente, sem limite de caixa. Não há conversão automática entre unidades.
- Pack, peças soltas e total em PC são gravados junto com a contagem e exportados no CSV.
- Fatores zerados, não inteiros e divergentes não são usados na validação. Código alternativo ambíguo exige código principal. Sem fator válido para CX/PL, é exibido Pack indisponível; não há capacidade inventada.
- Registros antigos mantêm os valores originais, sem conversão retroativa.

## Novidades da versão 1.5

- Bases NTW, Suprimento e Flow incluídas no APK e validadas offline.
- Colunas Local / Local_ importadas; códigos vazios são descartados e duplicados unificados.
- Espaços externos e diferença entre maiúsculas/minúsculas não invalidam um código; letras, números e pontuação restantes precisam corresponder a um local cadastrado.
- Leitura de local inválido mostra pop-up e não avança para produto. Digitação é validada ao avançar do campo, iniciar leitura do produto ou salvar.
- Histórico antigo é preservado; ao editar, o local precisa pertencer às bases.
- As bases desta versão correspondem às três planilhas fornecidas nesta atualização, sem atualização automática.

## Novidades da versão 1.4

- Imagens de porta-paletes e garrafa sem legendas abaixo.
- Ao ler o local pela câmera, abre automaticamente a câmera para ler o produto. Cancelar a leitura do local não avança; cancelar a do produto mantém o local e permite tentar novamente.
- Seletor exclusivo PC (peça), CX (caixa), PL (palete). A unidade é salva e acompanha histórico, edição e CSV. A última escolha é mantida para agilizar contagens.
- Totais separados por unidade: não soma peças, caixas e paletes. Registros anteriores à atualização permanecem sem unidade até serem editados.

## Novidades da versão 1.3

- Porta-paletes e garrafa maiores, botões destacados e identificação visual de versão no cabeçalho.
- Cabeçalho compacto e botões de leitura antes dos campos, para destacar as ações na primeira tela.

## Novidades da versão 1.2

- Botão de bipagem do local acompanhado por ícone nítido de porta-paletes.
- Botão de bipagem do produto acompanhado por ilustração de garrafa e identificação Tupperware.
- Perguntas explicam o que deve ser lido: endereço do estoque ou código do produto.
- Botões com texto maior e contraste reforçado.

## Novidades da versão 1.1

- Visual renovado com cartões e destaque para as contagens.
- Moldura de leitura com cantos verdes e linha animada; apenas a região enquadrada é decodificada.
- Confirmação sonora ao ler um código.
- Envio de CSV por e-mail: informe o solicitante, escolha seu aplicativo de e-mail e conclua o envio nele. Destinatário, assunto e anexo são preparados; o aplicativo não envia automaticamente.
- O último destinatário informado é lembrado neste aparelho.

## Instalação e uso

1. Transfira EstoqueBip.apk para o Android e abra o arquivo.
2. Se solicitado pelo Android, autorize a instalação para o aplicativo pelo qual abriu o APK.
3. Abra Estoque Bip. Bipe o local e o item com os botões da câmera, ou digite os códigos.
4. Informe a quantidade e toque em Salvar bipagem. A data e hora são capturadas automaticamente do aparelho nesse momento.
5. Marque Manter local para contar vários itens no mesmo endereço.
6. Para leitor Bluetooth/USB configurado como teclado (HID), toque no campo desejado e faça a leitura. Enter avança de local para item, de item para quantidade; na quantidade, salva.
7. Toque em um registro do histórico para editar ou excluir. A edição mantém a data original e grava uma data de alteração.
8. Use Exportar histórico completo para salvar CSV em uma pasta à sua escolha. O arquivo tem separador ponto e vírgula e codificação UTF-8 com BOM, para facilitar abertura no Excel. Importe Item e Local como Texto no Excel para preservar zeros à esquerda.

## Dados e limites desta versão

- Funciona offline, inclusive o decodificador da câmera incorporado ao APK.
- Códigos comuns: EAN/UPC, Code 128, Code 39, ITF, QR e Data Matrix, entre os suportados por ZXing.
- Quantidades positivas, com até três casas decimais. Cada bipagem é uma contagem independente; não é baixa ou movimentação de estoque.
- Os códigos não são comparados com cadastro de produtos: são armazenados como lidos.
- SQLite local no aparelho. Sem login, servidor ou sincronização entre aparelhos.
- Histórico mostra até 200 registros correspondentes à pesquisa; CSV inclui todos os registros, mesmo os que não aparecem na tela.
- Desinstalar ou limpar os dados do aplicativo remove o histórico. Guarde exportações frequentes; esta versão não tem importação/restauração.
- Data e hora dependem da configuração de relógio e fuso do celular.
- A compilação e assinatura foram verificadas. O APK ainda precisa ser validado em aparelho real, principalmente câmera, leitor externo e seletor de arquivos.

## Projeto e compilação

Projeto Java sem Gradle, com recursos Android nativos e ZXing Core 3.5.3 (Apache License 2.0).
SDK: compile API 35, target API 34, mínimo API 26. Java 8; build tools 35.0.0.

Para recompilar, instale um JDK, o SDK Android e utilize o ZXing Core 3.5.3 incluído em `lib/zxing.jar`. Configure `ANDROID_HOME` e execute `bash build.sh` a partir desta pasta.

A chave `signing/estoquebip.jks` permite assinar atualizações que preservam a instalação e o banco de dados. A chave e sua senha ficam fora do repositório. Configure ESTOQUEBIP_KEYSTORE_PASSWORD com a senha original e, se necessário, ESTOQUEBIP_KEYSTORE com o caminho da chave. Conserve a chave original para assinar atualizações compatíveis com o APK instalado.

Fonte ZXing: https://github.com/zxing/zxing
Licença: https://www.apache.org/licenses/LICENSE-2.0
