package projeto.view;

import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import projeto.controller.ClassController;
import projeto.model.EntityRoupa;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
    ClassController cont = new ClassController();
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws SQLException, ClassNotFoundException {
        this.primaryStage = stage;
        // Inicia na tela de login
        primaryStage.setTitle("Sistema de Estoque");
        primaryStage.setScene(telaLogin());
        primaryStage.show();
    }

    // Tela 1: Login
    private Scene telaLogin() throws SQLException, ClassNotFoundException {
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
            try {
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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

    // Gerenciamento de Estoque
    private Scene telaGerenciamento() throws SQLException, ClassNotFoundException {
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

        botaoPesquisar.setOnAction(e -> {
            primaryStage.setScene(telaPesquisaDetalhada(campoPesquisa.getText()));
        });
        btnListar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaListagemEstoque());
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        });
        btnAdicionar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaAdicionarRoupa());
            } catch (Exception e1) {
                e1.printStackTrace();
                ;
            }
        });
        btnRemover.setOnAction(e -> {
            try {
                primaryStage.setScene(telaExcluirRoupa());
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        });

        btnListar.setMinWidth(150);
        btnRemover.setMinWidth(150);
        btnAdicionar.setMinWidth(150);
        btnVoltar.setMinWidth(80);

        btnVoltar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaLogin());
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        }); // volta para tela 1

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

    // Pesquisa Detalhada
    private Scene telaPesquisaDetalhada(String pesquisa) {
        TextField campoPesquisa = new TextField();
        campoPesquisa.setPromptText("Pesquisa");
        campoPesquisa.setMinWidth(400);
        campoPesquisa.setText(pesquisa); // mantém o valor pesquisado

        TextArea resultado = new TextArea();
        resultado.setEditable(false);
        resultado.setPrefHeight(300);

        // Pesquisa e preenche a caixa de texto
        EntityRoupa roupa = cont.pesquisar(pesquisa);
        if (roupa.getId() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(roupa.getId()).append("\n");
            sb.append("Tipo: ").append(roupa.getTipo()).append("\n");
            sb.append("Estoque ID: ").append(roupa.getIdEstoque()).append("\n");
            sb.append("Marca: ").append(roupa.getMarca()).append("\n");
            sb.append("Tamanho: ").append(roupa.getTamanho()).append("\n");
            sb.append("Gênero: ").append(roupa.getGenero()).append("\n");
            sb.append("Quantidade: ").append(roupa.getQuantidade()).append("\n");
            resultado.setText(sb.toString());
        } else {
            resultado.setText("Nenhuma roupa encontrada para: \"" + pesquisa + "\"");
        }

        Button botaoBuscar = new Button("🔍");
        botaoBuscar.setOnAction(e -> primaryStage.setScene(telaPesquisaDetalhada(campoPesquisa.getText())));

        HBox barraPesquisa = new HBox(5, campoPesquisa, botaoBuscar);
        barraPesquisa.setAlignment(Pos.CENTER);
        barraPesquisa.setPadding(new Insets(20));

        VBox layout = new VBox(10, barraPesquisa, resultado);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        // Botão de Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(80);
        btnVoltar.setFont(new Font(14));
        btnVoltar.setTextFill(Color.BLACK);
        btnVoltar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setPadding(new Insets(10));
        boxVoltar.setAlignment(Pos.BOTTOM_RIGHT);

        BorderPane painel = new BorderPane();
        painel.setCenter(layout);
        painel.setBottom(boxVoltar);
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(new StackPane(painel), 800, 500, Color.LIGHTGRAY);
    }

    // Listagem de Estoque
    private Scene telaListagemEstoque() throws SQLException, ClassNotFoundException {
        FlowPane gridRoupas = new FlowPane();
        gridRoupas.setHgap(20);
        gridRoupas.setVgap(20);
        gridRoupas.setPadding(new Insets(10));
        gridRoupas.setPrefWrapLength(750); // Quebra de linha automática

        List<EntityRoupa> lista = cont.listar(); // busca roupas

        for (EntityRoupa roupa : lista) {
            VBox card = new VBox(5);
            card.setPadding(new Insets(10));
            card.setAlignment(Pos.CENTER);
            card.setStyle("-fx-border-color: black; -fx-background-color: #d3d3d3;");
            card.setPrefSize(100, 120);
            String id = "";
            id += (roupa.getId());
            Label nomeRoupa = new Label(id);

            Button btnModificar = new Button("Modificar");
            btnModificar.setOnAction(e -> {
                try {
                    primaryStage.setScene(telaModificarPeca(roupa));
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            });

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
        btnVoltar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER);
        boxVoltar.setPadding(new Insets(10));

        VBox layout = new VBox(10, scrollPane, boxVoltar);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(layout, 800, 500, Color.LIGHTGRAY);
    }

    // Modificação de Peça
    private Scene telaModificarPeca(EntityRoupa roupa) throws SQLException, ClassNotFoundException {
        EntityRoupa ropa = new EntityRoupa();
        TextArea infoRoupa = new TextArea();
        infoRoupa.setPrefSize(300, 300);
        infoRoupa.setEditable(false);
        if (roupa.getId() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(roupa.getId()).append("\n");
            sb.append("Tipo: ").append(roupa.getTipo()).append("\n");
            sb.append("Estoque ID: ").append(roupa.getIdEstoque()).append("\n");
            sb.append("Marca: ").append(roupa.getMarca()).append("\n");
            sb.append("Tamanho: ").append(roupa.getTamanho()).append("\n");
            sb.append("Gênero: ").append(roupa.getGenero()).append("\n");
            sb.append("Quantidade: ").append(roupa.getQuantidade()).append("\n");
            infoRoupa.setText(sb.toString());
        }

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
        btnModificar.setOnAction(e -> {
            ropa.setMarca(campoMarca.getText());
            ropa.setTipo(campoTipo.getText());
            ropa.setTamanho(campoTamanho.getText());
            ropa.setId(roupa.getId());
            ropa.setGenero(roupa.getGenero());
            try {
                cont.alterar(ropa);
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            
        });

        VBox colunaDireita = new VBox(20, boxMarca, boxTipo, boxTamanho, btnModificar);
        colunaDireita.setAlignment(Pos.CENTER);
        colunaDireita.setPadding(new Insets(10));

        HBox conteudo = new HBox(30, infoRoupa, colunaDireita);
        conteudo.setAlignment(Pos.CENTER);
        conteudo.setPadding(new Insets(20));

        // Botão Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(80);
        btnVoltar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaListagemEstoque());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setPadding(new Insets(10));
        boxVoltar.setAlignment(Pos.BOTTOM_LEFT);

        BorderPane painel = new BorderPane();
        painel.setCenter(conteudo);
        painel.setBottom(boxVoltar);
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(painel, 800, 500, Color.LIGHTGRAY);
    }

    // Adicionar Roupa
    private Scene telaAdicionarRoupa() throws SQLException, ClassNotFoundException {
    
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
        btnSalvar.setOnAction(e -> {
            EntityRoupa roupa = new EntityRoupa();
            Toggle selectedToggle = grupoGenero.getSelectedToggle();
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String generoSelecionado = selectedRadioButton.getText();
            roupa.setGenero(generoSelecionado);
            roupa.setMarca(campoMarca.getText());
            roupa.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
            roupa.setTipo(comboTipo.getValue());
            roupa.setTamanho(campoTamanho.getText());
            try {
                cont.addRoupa(roupa);
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Botão Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        VBox esquerda = new VBox(20, boxMarca, boxTamanho, boxTipo);
        esquerda.setAlignment(Pos.CENTER_LEFT);
        VBox direita = new VBox(20, boxQuantidade, boxGenero, btnSalvar);
        direita.setAlignment(Pos.CENTER_RIGHT);

        HBox centro = new HBox(100, esquerda, direita);
        centro.setAlignment(Pos.CENTER);
        centro.setPadding(new Insets(20));

        VBox layout = new VBox(20, centro, btnVoltar);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(layout, 800, 500, Color.LIGHTGRAY);
    }

    // Exclusão de Roupa
    private Scene telaExcluirRoupa() throws SQLException, ClassNotFoundException {
        // Campo de ID
        Label lblID = new Label("Informe o ID:");
        TextField campoID = new TextField();
        campoID.setPromptText("ID da roupa");

        VBox boxID = new VBox(5, lblID, campoID);
        boxID.setAlignment(Pos.CENTER_LEFT);
        

        // Botão Excluir
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setMinWidth(100);
        btnExcluir.setFont(new Font(14));
        btnExcluir.setTextFill(Color.WHITE);
        btnExcluir.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        btnExcluir.setOnAction(e -> {
            int id = Integer.parseInt(campoID.getText());
            cont.excluiRoupa(id);
            try {
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        VBox colunaDireita = new VBox(30, boxID, btnExcluir);
        colunaDireita.setAlignment(Pos.CENTER_LEFT);

        HBox centro = new HBox(40, colunaDireita);
        centro.setAlignment(Pos.CENTER);
        centro.setPadding(new Insets(20));

        // Botão Voltar
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            try {
                primaryStage.setScene(telaGerenciamento());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.BOTTOM_LEFT);
        boxVoltar.setPadding(new Insets(10));

        BorderPane painel = new BorderPane();
        painel.setCenter(centro);
        painel.setBottom(boxVoltar);
        painel.setStyle("-fx-background-color: #808080; -fx-border-color: black; -fx-border-width: 2px;");

        return new Scene(painel, 800, 500, Color.LIGHTGRAY);
    }

    public static void main(String[] args){
        launch(args);
    }
}
