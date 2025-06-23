package projeto.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import projeto.controller.ClassController;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
    ClassController cont = new ClassController();
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        // Inicia na tela de login
        primaryStage.setTitle("Sistema de Estoque");
        primaryStage.setScene(telaLogin());
        primaryStage.show();
    }

    // Tela 1: Login
    private Scene telaLogin() {
        Label titulo = new Label("Sistema de Estoque");
        titulo.setFont(new Font("Arial", 20));
        titulo.setTextFill(Color.BLACK);
        titulo.setPadding(new Insets(10));
        titulo.setMaxWidth(Double.MAX_VALUE);
        titulo.setAlignment(Pos.CENTER);
        titulo.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Button btnAcessar = new Button("Acessar");
        btnAcessar.setFont(new Font(16));
        btnAcessar.setTextFill(Color.WHITE);
        btnAcessar.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        btnAcessar.setMinWidth(100);
        btnAcessar.setOnAction(e -> {
            primaryStage.setScene(telaGerenciamento());
        }); // troca de cena

        Button btnSair = new Button("Sair");
        btnSair.setFont(new Font(16));
        btnSair.setTextFill(Color.WHITE);
        btnSair.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        btnSair.setMinWidth(100);
        btnSair.setOnAction(e -> primaryStage.close());

        VBox centro = new VBox(10, btnAcessar, btnSair);
        centro.setAlignment(Pos.CENTER);

        BorderPane painel = new BorderPane();
        painel.setTop(titulo);
        painel.setCenter(centro);
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");
        painel.setPrefSize(400, 300);

        return new Scene(new StackPane(painel), 800, 500, Color.LIGHTGRAY);
    }

    // Tela 2: Gerenciamento de Estoque
    private Scene telaGerenciamento() {
        TextField campoPesquisa = new TextField();
        campoPesquisa.setPromptText("Pesquisa");

        Button botaoPesquisar = new Button("🔍");
        HBox barraPesquisa = new HBox(5, campoPesquisa, botaoPesquisar);
        barraPesquisa.setAlignment(Pos.CENTER);
        barraPesquisa.setPadding(new Insets(20));

        Button btnListar = new Button("Listar Estoque");
        Button btnRemover = new Button("Remover Roupa");
        Button btnAdicionar = new Button("Adicionar Roupa");
        Button btnVoltar = new Button("Voltar");

        botaoPesquisar.setOnAction(e -> primaryStage.setScene(telaPesquisaDetalhada()));
        btnListar.setOnAction(e -> primaryStage.setScene(telaListagemEstoque()));
        btnAdicionar.setOnAction(e -> primaryStage.setScene(telaAdicionarRoupa()));
        btnRemover.setOnAction(e -> primaryStage.setScene(telaExcluirRoupa()));

        btnListar.setMinWidth(150);
        btnRemover.setMinWidth(150);
        btnAdicionar.setMinWidth(150);
        btnVoltar.setMinWidth(80);

        btnVoltar.setOnAction(e -> primaryStage.setScene(telaLogin())); // volta para tela 1

        GridPane gridBotoes = new GridPane();
        gridBotoes.setHgap(40);
        gridBotoes.setVgap(20);
        gridBotoes.setAlignment(Pos.CENTER);
        gridBotoes.add(btnListar, 0, 0);
        gridBotoes.add(btnRemover, 1, 0);
        gridBotoes.add(btnAdicionar, 0, 1);

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setPadding(new Insets(20));
        boxVoltar.setAlignment(Pos.BOTTOM_LEFT);

        BorderPane painel = new BorderPane();
        painel.setTop(barraPesquisa);
        painel.setCenter(gridBotoes);
        painel.setBottom(boxVoltar);
        painel.setPadding(new Insets(20));
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");
        painel.setPrefSize(700, 450);

        return new Scene(new StackPane(painel), 800, 500, Color.LIGHTGRAY);

    }

    // Tela 3: Pesquisa Detalhada
    private Scene telaPesquisaDetalhada() {
        TextField campoPesquisa = new TextField();
        campoPesquisa.setPromptText("Pesquisa");
        campoPesquisa.setMinWidth(400);

        Button botaoBuscar = new Button("🔍");
        botaoBuscar.setOnAction(e -> primaryStage.setScene(telaPesquisaDetalhada()));

        HBox barraPesquisa = new HBox(5, campoPesquisa, botaoBuscar);
        barraPesquisa.setAlignment(Pos.CENTER);
        barraPesquisa.setPadding(new Insets(20));

        TextArea resultado = new TextArea();
        resultado.setEditable(false);
        resultado.setPrefHeight(300);

        VBox layout = new VBox(10, barraPesquisa, resultado);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        // Botão de Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(80);
        btnVoltar.setFont(new Font(14));
        btnVoltar.setTextFill(Color.BLACK);
        btnVoltar.setOnAction(e -> primaryStage.setScene(telaGerenciamento()));

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setPadding(new Insets(10));
        boxVoltar.setAlignment(Pos.BOTTOM_RIGHT);

        BorderPane painel = new BorderPane();
        painel.setCenter(layout);
        painel.setBottom(boxVoltar);
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(new StackPane(painel), 800, 500, Color.LIGHTGRAY);
    }

    // Tela 4: Listagem de Estoque
    private Scene telaListagemEstoque() {
        FlowPane gridRoupas = new FlowPane();
        gridRoupas.setHgap(20);
        gridRoupas.setVgap(20);
        gridRoupas.setPadding(new Insets(10));
        gridRoupas.setPrefWrapLength(750); // Quebra de linha automática

        for (int i = 1; i <= 20; i++) {
            VBox card = new VBox(5);
            card.setPadding(new Insets(10));
            card.setAlignment(Pos.CENTER);
            card.setStyle("-fx-border-color: black; -fx-background-color: #d3d3d3;");
            card.setPrefSize(100, 120);

            Label nomeRoupa = new Label("Roupa " + i);
            Button btnModificar = new Button("Modificar");
            btnModificar.setOnAction(e -> primaryStage.setScene(telaModificarPeca()));

            card.getChildren().addAll(nomeRoupa, btnModificar);
            gridRoupas.getChildren().add(card);
        }

        ScrollPane scrollPane = new ScrollPane(gridRoupas);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(380);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(100);
        btnVoltar.setFont(new Font(14));
        btnVoltar.setTextFill(Color.BLACK);
        btnVoltar.setOnAction(e -> primaryStage.setScene(telaGerenciamento()));

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER);
        boxVoltar.setPadding(new Insets(10));

        VBox layout = new VBox(10, scrollPane, boxVoltar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(layout, 800, 500, Color.LIGHTGRAY);
    }

    // Tela 5: Modificação de Peça
    private Scene telaModificarPeca() {
        // Área de informações (pode ser substituída por imagem ou preview futuramente)
        TextArea infoRoupa = new TextArea("Informações da Roupa");
        infoRoupa.setPrefSize(300, 300);
        infoRoupa.setEditable(false);

        // Campo Marca
        Label lblMarca = new Label("Marca:");
        TextField campoMarca = new TextField();
        campoMarca.setPromptText("Selecionar marca");
        campoMarca.setMinWidth(150);
        VBox boxMarca = new VBox(5, lblMarca, campoMarca);

        // Campo Tipo
        Label lblTipo = new Label("Tipo:");
        TextField campoTipo = new TextField();
        campoTipo.setPromptText("Selecionar tipo");
        campoTipo.setMinWidth(150);
        VBox boxTipo = new VBox(5, lblTipo, campoTipo);

        // Campo Tamanho
        Label lblTamanho = new Label("Tamanho:");
        TextField campoTamanho = new TextField();
        campoTamanho.setPromptText("Selecionar tamanho");
        campoTamanho.setMinWidth(150);
        VBox boxTamanho = new VBox(5, lblTamanho, campoTamanho);

        // Botão Modificar
        Button btnModificar = new Button("Modificar");
        btnModificar.setFont(new Font(14));
        btnModificar.setMinWidth(120);
        btnModificar.setStyle("-fx-background-color: #66cccc; -fx-border-color: black;");

        VBox colunaDireita = new VBox(20, boxMarca, boxTipo, boxTamanho, btnModificar);
        colunaDireita.setAlignment(Pos.CENTER);
        colunaDireita.setPadding(new Insets(10));

        HBox conteudo = new HBox(30, infoRoupa, colunaDireita);
        conteudo.setAlignment(Pos.CENTER);
        conteudo.setPadding(new Insets(20));

        // Botão Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(80);
        btnVoltar.setOnAction(e -> primaryStage.setScene(telaListagemEstoque()));

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setPadding(new Insets(10));
        boxVoltar.setAlignment(Pos.BOTTOM_LEFT);

        BorderPane painel = new BorderPane();
        painel.setCenter(conteudo);
        painel.setBottom(boxVoltar);
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(painel, 800, 500, Color.LIGHTGRAY);
    }

    // Tela 6: Adicionar Roupa
    private Scene telaAdicionarRoupa() {
        // Campo Nome
        Label lblNome = new Label("Nome:");
        TextField campoNome = new TextField();
        campoNome.setPromptText("Adicionar nome");
        campoNome.setMinWidth(600);

        VBox boxNome = new VBox(5, lblNome, campoNome);
        boxNome.setPadding(new Insets(10));

        // Campo Marca
        Label lblMarca = new Label("Marca:");
        TextField campoMarca = new TextField();
        campoMarca.setPromptText("Adicionar marca");
        campoMarca.setMinWidth(150);

        VBox boxMarca = new VBox(5, lblMarca, campoMarca);

        // Campo Tamanho
        Label lblTamanho = new Label("Tamanho:");
        TextField campoTamanho = new TextField();
        campoTamanho.setPromptText("Adicionar tamanho");
        campoTamanho.setMinWidth(150);

        VBox boxTamanho = new VBox(5, lblTamanho, campoTamanho);

        // ComboBox Tipo
        Label lblTipo = new Label("Tipo:");
        ComboBox<String> comboTipo = new ComboBox<>();
        comboTipo.getItems().addAll("Camisa", "Calça", "Jaqueta", "Vestido", "Outro");
        comboTipo.setPromptText("Selecionar Tipo");
        comboTipo.setMinWidth(150);

        VBox boxTipo = new VBox(5, lblTipo, comboTipo);

        // Campo Quantidade
        Label lblQuantidade = new Label("Quantidade:");
        TextField campoQuantidade = new TextField();
        campoQuantidade.setPromptText("Adicionar quantidade");
        campoQuantidade.setMinWidth(200);

        VBox boxQuantidade = new VBox(5, lblQuantidade, campoQuantidade);

        // RadioButtons de Gênero
        Label lblGenero = new Label("Gênero:");
        ToggleGroup grupoGenero = new ToggleGroup();
        RadioButton rbM = new RadioButton("M");
        RadioButton rbF = new RadioButton("F");
        RadioButton rbT = new RadioButton("T");
        rbM.setToggleGroup(grupoGenero);
        rbF.setToggleGroup(grupoGenero);
        rbT.setToggleGroup(grupoGenero);

        HBox boxRadios = new HBox(20, rbM, rbF, rbT);
        boxRadios.setAlignment(Pos.CENTER_LEFT);

        VBox boxGenero = new VBox(5, lblGenero, boxRadios);

        // Botão Salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setMinWidth(150);

        // Botão Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> primaryStage.setScene(telaGerenciamento()));

        // Esquerda
        VBox esquerda = new VBox(20, boxMarca, boxTamanho, boxTipo);
        esquerda.setAlignment(Pos.CENTER_LEFT);

        // Direita
        VBox direita = new VBox(20, boxQuantidade, boxGenero, btnSalvar);
        direita.setAlignment(Pos.CENTER_RIGHT);

        HBox centro = new HBox(100, esquerda, direita);
        centro.setAlignment(Pos.CENTER);
        centro.setPadding(new Insets(20));

        VBox layout = new VBox(20, boxNome, centro, btnVoltar);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(layout, 800, 500, Color.LIGHTGRAY);
    }

    // Tela 7: Exclusão de Roupa 
    private Scene telaExcluirRoupa() {
        // Campo de ID
        Label lblID = new Label("Informe o ID:");
        TextField campoID = new TextField();
        campoID.setPromptText("ID da roupa");

        VBox boxID = new VBox(5, lblID, campoID);
        boxID.setAlignment(Pos.CENTER_LEFT);

        // TextArea de informações (preview)
        TextArea infoRoupa = new TextArea("Informações da roupa serão exibidas aqui...");
        infoRoupa.setPrefSize(400, 300);
        infoRoupa.setEditable(false);

        // Botão Excluir
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setMinWidth(100);
        btnExcluir.setFont(new Font(14));
        btnExcluir.setTextFill(Color.WHITE);
        btnExcluir.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox colunaDireita = new VBox(30, boxID, btnExcluir);
        colunaDireita.setAlignment(Pos.CENTER_LEFT);

        HBox centro = new HBox(40, infoRoupa, colunaDireita);
        centro.setAlignment(Pos.CENTER);
        centro.setPadding(new Insets(20));

        // Botão Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> primaryStage.setScene(telaGerenciamento()));

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.BOTTOM_LEFT);
        boxVoltar.setPadding(new Insets(10));

        BorderPane painel = new BorderPane();
        painel.setCenter(centro);
        painel.setBottom(boxVoltar);
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(painel, 800, 500, Color.LIGHTGRAY);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
