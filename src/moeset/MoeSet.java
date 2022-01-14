/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moeset;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MoeSet extends Application {

    private TableView<RawData> tableView = new TableView<>();
    private TableColumn<RawData, String> colShopName = new TableColumn<>("Shop Name");
    private TableColumn<RawData, Integer> colNoOfBigIcePackage = new TableColumn<>("Number of Big Ice Package");
    private TableColumn<RawData, Integer> colNoOfSmallIcePackage = new TableColumn<>("Number Of Small Ice Package");
    private TableColumn<RawData, Integer> colExternalRetailPrice = new TableColumn<>("Price Of External Retail");
    private TableColumn<RawData, Integer> colTakingDebt = new TableColumn<>("Taking Debt");
    private TableColumn<RawData, Integer> colEmployeeFees = new TableColumn<>("Employee Fees");
    private TableColumn<RawData, Integer> colNoOfRawBags = new TableColumn<>("Number Of Raw Bags");
    private TableColumn<RawData, Integer> colTotalAmount = new TableColumn<>("Total Amount");
    private TableColumn<RawData, Integer> colTodayProfit = new TableColumn<>("Today Profit");

    private TextField tefShopName = new TextField("null");
    private TextField tefNoOfBigIcePackage = new TextField("0");
    private TextField tefNoOfSmallIcePackage = new TextField("0");
    private TextField tefExternalRetailPrice = new TextField("0");
    private TextField tefTakingDebt = new TextField("0");
    private TextField tefEmployeeFees = new TextField("0");
    private TextField tefNoOfRawBags = new TextField("0");
    private TextField tefTotalAmount = new TextField("0");
    private TextField tefTodayProfit = new TextField("0");

    private Button btnAdd = new Button("Add");
    private Button btnDelete = new Button("Delete");
    private Button btnDemand = new Button("Double click for demand");
    private Button btnSave = new Button("Save");
    private Button btnOpen = new Button("Open");

    private Alert altWarning = new Alert(Alert.AlertType.NONE);

    CategoryAxis xaxis = new CategoryAxis();
    NumberAxis yaxis = new NumberAxis(1000, 20000, 1000);
    BarChart<String, Integer> bar = new BarChart(xaxis, yaxis);
    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    XYChart.Series<String, Integer> series1 = new XYChart.Series<>();

    private Text texMoeSet = new Text("MOE SET");
    private Text texShopName = new Text(15, 605, "Shop Name");
    private Text texNoOfBigIcePackage = new Text(141, 605, "Big Ice Bag");
    private Text texNoOfSmallIcePackage = new Text(222, 605, "Small Ice Bag");
    private Text texExternalRetailPrice = new Text(324, 605, "Retail");
    private Text texTakingDebt = new Text(405, 605, "Debt");
    private Text texEmployeeFees = new Text(506, 605, "Fees");
    private Text texNoOfRawBags = new Text(607, 605, "Raw Bags");
    private Text texTotalAmount = new Text(689, 605, "Total Amount");
    private Text texTodayProfit = new Text(790, 605, "Today Profit");

    private DropShadow ds = new DropShadow(8, 1, 1, Color.BLACK);
    private Reflection reflect = new Reflection();

    private RotateTransition rotate = new RotateTransition(Duration.seconds(2));
    private FadeTransition fade = new FadeTransition(Duration.seconds(2));

    private Image rawImage = new Image("/images/ice bag.png");
    ImageView image = new ImageView(rawImage);

    Desktop desktop = Desktop.getDesktop();
    
    @Override
    public void start(Stage stage) {

        try {

            reflect.setFraction(0.7f);

            ds.setOffsetY(3.0f);
            ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

            texMoeSet.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 40));
            texMoeSet.setLayoutX(1120);
            texMoeSet.setLayoutY(60);
            texMoeSet.setEffect(reflect);
            texMoeSet.setStroke(Color.web("#000099"));

            image.setLayoutX(1020);
            image.setLayoutY(20);
            image.setFitHeight(80);
            image.setFitWidth(70);

            texNoOfBigIcePackage.setWrappingWidth(tefNoOfBigIcePackage.getWidth());
            texNoOfSmallIcePackage.setWrappingWidth(tefNoOfSmallIcePackage.getWidth());
            texExternalRetailPrice.setWrappingWidth(tefExternalRetailPrice.getWidth());
            texTakingDebt.setWrappingWidth(tefTakingDebt.getWidth());
            texEmployeeFees.setWrappingWidth(tefEmployeeFees.getWidth());
            texNoOfRawBags.setWrappingWidth(tefNoOfRawBags.getWidth());
            texTotalAmount.setWrappingWidth(tefTotalAmount.getWidth());
            texTodayProfit.setWrappingWidth(tefTodayProfit.getWidth());

            texShopName.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texShopName.setEffect(ds);

            texNoOfBigIcePackage.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texNoOfBigIcePackage.setEffect(ds);

            texNoOfSmallIcePackage.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texNoOfSmallIcePackage.setEffect(ds);

            texExternalRetailPrice.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texExternalRetailPrice.setEffect(ds);

            texTakingDebt.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texTakingDebt.setEffect(ds);

            texEmployeeFees.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texEmployeeFees.setEffect(ds);

            texNoOfRawBags.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texNoOfRawBags.setEffect(ds);

            texTotalAmount.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texTotalAmount.setEffect(ds);

            texTodayProfit.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            texTodayProfit.setEffect(ds);

            fade.setFromValue(0.3);
            fade.setToValue(1);
            fade.setAutoReverse(true);
            fade.setCycleCount(RotateTransition.INDEFINITE);
            fade.setNode(texMoeSet);
            fade.play();

            tableView.getColumns().addAll(colShopName, colNoOfBigIcePackage, colNoOfSmallIcePackage,
                    colExternalRetailPrice, colTakingDebt, colEmployeeFees, colNoOfRawBags, colTotalAmount, colTodayProfit);
            tableView.setPrefHeight(600);
            tableView.setEditable(true);
            tableView.setPrefSize(900, 550);

            //For Bar Chart
            xaxis.setLabel("Shop Name");
            yaxis.setLabel("Total Amount");
            bar.setTitle("Today Demand");
            bar.setLayoutX(890);
            bar.setLayoutY(115);
            bar.setPrefSize(470, 400);

            colShopName.setMinWidth(150);
            colNoOfBigIcePackage.setMinWidth(81);
            colNoOfSmallIcePackage.setMinWidth(81);
            colExternalRetailPrice.setMinWidth(100);
            colTakingDebt.setMinWidth(100);
            colEmployeeFees.setMinWidth(100);
            colNoOfRawBags.setMinWidth(81);
            colTotalAmount.setMinWidth(100);
            colTodayProfit.setMinWidth(100);

            makeHeaderWrappable(colShopName);
            makeHeaderWrappable(colNoOfBigIcePackage);
            makeHeaderWrappable(colNoOfSmallIcePackage);
            makeHeaderWrappable(colExternalRetailPrice);
            makeHeaderWrappable(colTakingDebt);
            makeHeaderWrappable(colEmployeeFees);
            makeHeaderWrappable(colNoOfRawBags);
            makeHeaderWrappable(colTotalAmount);

            colShopName.setCellValueFactory(new PropertyValueFactory<>("shopName"));
            colNoOfBigIcePackage.setCellValueFactory(new PropertyValueFactory<>("noOfBigIcePackage"));
            colNoOfSmallIcePackage.setCellValueFactory(new PropertyValueFactory<>("noOfSmallIcePackage"));
            colExternalRetailPrice.setCellValueFactory(new PropertyValueFactory<>("externalRetailPrice"));
            colTakingDebt.setCellValueFactory(new PropertyValueFactory<>("takingDebt"));
            colEmployeeFees.setCellValueFactory(new PropertyValueFactory<>("employeeFees"));
            colNoOfRawBags.setCellValueFactory(new PropertyValueFactory<>("noOfRawBags"));
            colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
            colTodayProfit.setCellValueFactory(new PropertyValueFactory<>("todayProfit"));

            tefShopName.setLayoutX(0);
            tefShopName.setLayoutY(620);
            tefShopName.setPrefWidth(140);
            tefShopName.setOnMouseClicked((event) -> {
                tefShopName.selectAll();
            });

            //        tefShopName.setPromptText("Shop Name");
            tefNoOfBigIcePackage.setLayoutX(141);
            tefNoOfBigIcePackage.setLayoutY(620);
            tefNoOfBigIcePackage.setPrefWidth(81);
            tefNoOfBigIcePackage.setOnMouseClicked((event) -> {
                tefNoOfBigIcePackage.selectAll();
            });
            //        tefNoOfBigIcePackage.setPromptText("Big Ice");

            tefNoOfSmallIcePackage.setLayoutX(222);
            tefNoOfSmallIcePackage.setLayoutY(620);
            tefNoOfSmallIcePackage.setPrefWidth(81);
            tefNoOfSmallIcePackage.setOnMouseClicked((event) -> {
                tefNoOfSmallIcePackage.selectAll();
            });
            //        tefNoOfSmallIcePackage.setPromptText("Small Ice");

            tefExternalRetailPrice.setLayoutX(304);
            tefExternalRetailPrice.setLayoutY(620);
            tefExternalRetailPrice.setPrefWidth(100);
            tefExternalRetailPrice.setOnMouseClicked((event) -> {
                tefExternalRetailPrice.selectAll();
            });
            //        tefExternalRetailPrice.setPromptText("External Retail");

            tefTakingDebt.setLayoutX(405);
            tefTakingDebt.setLayoutY(620);
            tefTakingDebt.setPrefWidth(100);
            tefTakingDebt.setOnMouseClicked((event) -> {
                tefTakingDebt.selectAll();
            });
            //        tefTakingDebt.setPromptText("Taking Debt");

            tefEmployeeFees.setLayoutX(506);
            tefEmployeeFees.setLayoutY(620);
            tefEmployeeFees.setPrefWidth(100);
            tefEmployeeFees.setOnMouseClicked((event) -> {
                tefEmployeeFees.selectAll();
            });
            //        tefEmployeeFees.setPromptText("Employee Fees");

            tefNoOfRawBags.setLayoutX(607);
            tefNoOfRawBags.setLayoutY(620);
            tefNoOfRawBags.setPrefWidth(81);
            tefNoOfRawBags.setOnMouseClicked((event) -> {
                tefNoOfRawBags.selectAll();
            });
            //        tefNoOfRawBags.setPromptText("Raw Bag");

            tefTotalAmount.setLayoutX(689);
            tefTotalAmount.setLayoutY(620);
            tefTotalAmount.setPrefWidth(100);
            tefTotalAmount.setOnMouseClicked((event) -> {
                tefTotalAmount.selectAll();
            });
            //        tefTotalAmount.setPromptText("Total Amount");

            tefTodayProfit.setLayoutX(790);
            tefTodayProfit.setLayoutY(620);
            tefTodayProfit.setPrefWidth(100);
            tefTodayProfit.setOnMouseClicked((event) -> {
                tefTodayProfit.selectAll();
            });
            //        tefTodayProfit.setPromptText("Today Profit");

            btnAdd.setLayoutX(830);
            btnAdd.setLayoutY(655);
            btnAdd.setPrefSize(60, 40);

            btnAdd.setOnAction((event) -> {
                add();
            });

            btnDelete.setLayoutX(760);
            btnDelete.setLayoutY(655);
            btnDelete.setPrefSize(60, 40);

            btnDelete.setOnAction((event) -> {
                List<RawData> tableData = tableView.getItems();
                RawData selected = tableView.getSelectionModel().getSelectedItem();

                if (selected != null) {
                    tableView.getItems().remove(selected);
                }
            });

            btnDemand.setLayoutX(1080);
            btnDemand.setLayoutY(510);
            btnDemand.setPrefSize(160, 40);

            btnDemand.setOnAction((event) -> {
                List<RawData> tableData = tableView.getItems();
                for (RawData rawData : tableData) {
                    if (rawData.getTodayProfit() == 0) {
                        series.getData().add(new XYChart.Data(rawData.getShopName(), rawData.getTotalAmount()));
                    }
                    bar.getData().add(series);
                }
            });

            btnOpen.setLayoutX(1220);
            btnOpen.setLayoutY(655);
            btnOpen.setPrefSize(60, 40);

            btnOpen.setOnAction((event) -> {
                File selected = new File("D:\\Moe Set\\Today.txt");
                try {
                    desktop.open(selected);
                } catch (IOException ex) {
                    Logger.getLogger(MoeSet.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            btnSave.setLayoutX(1290);
            btnSave.setLayoutY(655);
            btnSave.setPrefSize(60, 40);

            btnSave.setOnAction((event) -> {
                try {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to save?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.YES) {
                        String path = "D:" + File.separator + "Moe Set" + File.separator + "Today.txt";

                        File selected = new File(path);

                        selected.getParentFile().mkdirs(); 
                        selected.createNewFile();

                        List<RawData> tableData = tableView.getItems();
                        for (RawData rawData : tableData) {

                        Files.write(selected.toPath(), new RawData(rawData.getShopName(), rawData.getNoOfBigIcePackage(),
                                rawData.getNoOfSmallIcePackage(), rawData.getExternalRetailPrice(), rawData.getTakingDebt(),
                                rawData.getEmployeeFees(), rawData.getNoOfRawBags(), rawData.getTotalAmount(), rawData.getTodayProfit()).toString().getBytes(),
                                 StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        }
                        Files.write(selected.toPath(), "\n".getBytes(), StandardOpenOption.APPEND);
                    }
                    else{
                        event.consume();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MoeSet.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Pane root = new Pane();
            root.getChildren().addAll(tableView, tefShopName, tefNoOfBigIcePackage, tefNoOfSmallIcePackage,
                    tefExternalRetailPrice, tefTakingDebt, tefEmployeeFees, tefNoOfRawBags, tefTotalAmount, tefTodayProfit,
                    btnDelete, btnAdd, bar, btnDemand, texMoeSet, texShopName, texNoOfBigIcePackage, texNoOfSmallIcePackage,
                    texExternalRetailPrice, texTakingDebt, texEmployeeFees, texNoOfRawBags, texTotalAmount, texTodayProfit,
                    btnSave, btnOpen, image);
            root.setStyle("-fx-background-color: #e6e6ff;");

            cssDesign();

            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.setResizable(false);
//            stage.setMaximized(true);
            stage.getIcons().add(new Image("/images/ice bag.png"));
            stage.setTitle("MOE SET");
            stage.show();
            stage.setOnCloseRequest((event) -> {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to exit?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    System.exit(0);
                }
                else{
                    event.consume();
                }
            });
        } catch (Exception e) {
            System.out.println("Error for clicking twice in demand button");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void calculateAmount() {
        int initialTotalAmount = 0, totalAmount, priceOfBigIcePackage, priceOfSmallIcePackage;
        int calNoOfBigIcePackage = Integer.parseInt(tefNoOfBigIcePackage.getText());
        int calNoOfSmallIcePackage = Integer.parseInt(tefNoOfSmallIcePackage.getText());
        int calExternalRetailPrice = Integer.parseInt(tefExternalRetailPrice.getText());
        int callTakingDebt = Integer.parseInt(tefTakingDebt.getText());
        int calEmployeeFees = Integer.parseInt(tefEmployeeFees.getText());

        if (calEmployeeFees == 0) {
            priceOfBigIcePackage = calNoOfBigIcePackage * 2000;
            priceOfSmallIcePackage = calNoOfSmallIcePackage * 1200;
            totalAmount = priceOfBigIcePackage + priceOfSmallIcePackage - callTakingDebt;
            tefTotalAmount.setText(String.valueOf(totalAmount));
        } else {
            List<RawData> tableData = tableView.getItems();
            for (RawData rawData : tableData) {
                initialTotalAmount += rawData.getTotalAmount();
                tefTotalAmount.setText(String.valueOf(initialTotalAmount + calExternalRetailPrice));
            }
        }
    }

    private void calculateProfit() {
        int calExternalRetailPrice = Integer.parseInt(tefExternalRetailPrice.getText());
        int callTakingDebt = Integer.parseInt(tefTakingDebt.getText());
        int calEmployeeFees = Integer.parseInt(tefEmployeeFees.getText());
        int calNoOfRawBags = Integer.parseInt(tefNoOfRawBags.getText());

        int initialTotalAmount = 0, totalAmount;
        if (calEmployeeFees != 0) {

            List<RawData> tableData = tableView.getItems();
            for (RawData rawData : tableData) {
                initialTotalAmount += rawData.getTotalAmount();
            }

            totalAmount = initialTotalAmount;

            int minusFromTotalAmount = callTakingDebt + calEmployeeFees + (calNoOfRawBags * 50);
            int todayProfit = (totalAmount + calExternalRetailPrice) - minusFromTotalAmount;
            tefTodayProfit.setText(String.valueOf(todayProfit));
        }
    }

    private void cssDesign() {
        btnAdd.setStyle("-fx-base: #9999ff; "
                + "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 );"
                + "-fx-font-size: 13px;");
        btnDelete.setStyle("-fx-base: #9999ff; "
                + "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 );"
                + "-fx-font-size: 13px;");
        btnDemand.setStyle("-fx-base: #9999ff; "
                + "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 );"
                + "-fx-font-size: 13px;");
        btnOpen.setStyle("-fx-base: #9999ff; "
                + "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 );"
                + "-fx-font-size: 13px;");
        btnSave.setStyle("-fx-base: #9999ff; "
                + "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 );"
                + "-fx-font-size: 13px;");
        tableView.setStyle("-fx-base: #ccccff; "
                + "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.3) , 10,0,0,1 );"
                + "-fx-font-size: 13px;");
        bar.setStyle("-fx-base: #ccccff; "
                + "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.3) , 5,0,0,0.5 );"
                + "-fx-font-size: 13px;");
        texMoeSet.setStyle("-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, #4d4dff 0%, #9999ff 50%);"
                + "-fx-stroke-width: 1;");
        texShopName.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texNoOfBigIcePackage.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texNoOfSmallIcePackage.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texExternalRetailPrice.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texTakingDebt.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texEmployeeFees.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texNoOfRawBags.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texTotalAmount.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
        texTodayProfit.setStyle("-fx-fill: #000080;"
                + "-fx-stroke-width: 1;");
    }

    private void add() {
        calculateAmount();
        calculateProfit();
        try {
            String getShopName = tefShopName.getText();
            int getNoOfBigIcePackage = Integer.parseInt(tefNoOfBigIcePackage.getText());
            int getNoOfSmallIcePackage = Integer.parseInt(tefNoOfSmallIcePackage.getText());
            int getExternalRetailPrice = Integer.parseInt(tefExternalRetailPrice.getText());
            int getTakingDebt = Integer.parseInt(tefTakingDebt.getText());
            int getEmployeeFees = Integer.parseInt(tefEmployeeFees.getText());
            int getNoOfRawBags = Integer.parseInt(tefNoOfRawBags.getText());
            int getTotalAmount = Integer.parseInt(tefTotalAmount.getText());
            int getTodayProfit = Integer.parseInt(tefTodayProfit.getText());
            RawData rd = new RawData(getShopName, getNoOfBigIcePackage, getNoOfSmallIcePackage, getExternalRetailPrice,
                    getTakingDebt, getEmployeeFees, getNoOfRawBags, getTotalAmount, getTodayProfit);
            tableView.getItems().add(rd);

            tefShopName.clear();
            tefNoOfBigIcePackage.clear();
            tefNoOfSmallIcePackage.clear();
            tefExternalRetailPrice.clear();
            tefTakingDebt.clear();
            tefEmployeeFees.clear();
            tefNoOfRawBags.clear();
            tefTotalAmount.clear();
            tefTodayProfit.clear();

            tefShopName.setText("null");
            tefNoOfBigIcePackage.setText("0");
            tefNoOfSmallIcePackage.setText("0");
            tefExternalRetailPrice.setText("0");
            tefTakingDebt.setText("0");
            tefEmployeeFees.setText("0");
            tefNoOfRawBags.setText("0");
            tefTotalAmount.setText("0");
            tefTodayProfit.setText("0");
        } catch (Exception e) {
            altWarning.setAlertType(Alert.AlertType.WARNING);
            altWarning.setContentText("Forget in text field?");
            altWarning.show();
        }
    }

    private void makeHeaderWrappable(TableColumn col) {
        Label label = new Label(col.getText());
        label.setStyle("-fx-padding: 8px;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().add(label);
        stack.prefWidthProperty().bind(col.widthProperty().subtract(5));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        col.setText(null);
        col.setGraphic(stack);
    }

    public static class RawData {

        private String shopName;
        private int noOfBigIcePackage;
        private int noOfSmallIcePackage;
        private int externalRetailPrice;
        private int takingDebt;
        private int employeeFees;
        private int noOfRawBags;
        private int totalAmount;
        private int todayProfit;
        private Date date = new Date();
        private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  

        public RawData(String shopName, int noOfBigIcePackage, int noOfSmallIcePackage, int externalRetailPrice, int takingDebt, int employeeFees, int noOfRawBags, int totalAmount, int todayProfit) {
            this.shopName = shopName;
            this.noOfBigIcePackage = noOfBigIcePackage;
            this.noOfSmallIcePackage = noOfSmallIcePackage;
            this.externalRetailPrice = externalRetailPrice;
            this.takingDebt = takingDebt;
            this.employeeFees = employeeFees;
            this.noOfRawBags = noOfRawBags;
            this.totalAmount = totalAmount;
            this.todayProfit = todayProfit;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getNoOfBigIcePackage() {
            return noOfBigIcePackage;
        }

        public void setNoOfBigIcePackage(int noOfBigIcePackage) {
            this.noOfBigIcePackage = noOfBigIcePackage;
        }

        public int getNoOfSmallIcePackage() {
            return noOfSmallIcePackage;
        }

        public void setNoOfSmallIcePackage(int noOfSmallIcePackage) {
            this.noOfSmallIcePackage = noOfSmallIcePackage;
        }

        public int getExternalRetailPrice() {
            return externalRetailPrice;
        }

        public void setExternalRetailPrice(int externalRetailPrice) {
            this.externalRetailPrice = externalRetailPrice;
        }

        public int getTakingDebt() {
            return takingDebt;
        }

        public void setTakingDebt(int takingDebt) {
            this.takingDebt = takingDebt;
        }

        public int getEmployeeFees() {
            return employeeFees;
        }

        public void setEmployeeFees(int employeeFees) {
            this.employeeFees = employeeFees;
        }

        public int getNoOfRawBags() {
            return noOfRawBags;
        }

        public void setNoOfRawBags(int noOfRawBags) {
            this.noOfRawBags = noOfRawBags;
        }

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getTodayProfit() {
            return todayProfit;
        }

        public void setTodayProfit(int todayProfit) {
            this.todayProfit = todayProfit;
        }

        @Override
        public String toString() {
            return "RawData{" + "shopName=" + shopName + ", noOfBigIcePackage=" + noOfBigIcePackage + ", noOfSmallIcePackage=" + noOfSmallIcePackage + ", externalRetailPrice=" + externalRetailPrice + ", takingDebt=" + takingDebt + ", employeeFees=" + employeeFees + ", noOfRawBags=" + noOfRawBags + ", totalAmount=" + totalAmount + ", todayProfit=" + todayProfit + ", Date=" + formatter.format(date) + '}' + "\n";
        }

    }

}
