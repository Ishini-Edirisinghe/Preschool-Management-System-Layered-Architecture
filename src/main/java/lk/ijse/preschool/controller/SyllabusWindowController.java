package lk.ijse.preschool.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.preschool.bo.BOFactory;
import lk.ijse.preschool.dao.DAOFactory;
import lk.ijse.preschool.bo.costom.SyllabusBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.SyllabusDTO;
import lk.ijse.preschool.dto.tm.SyllabusTM;
import lk.ijse.preschool.util.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SyllabusWindowController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colConName;

    @FXML
    private TableColumn<?, ?> colConNo;

    @FXML
    private TableView<SyllabusTM> tblSyllabus;

    @FXML
    private TextField txtConName;

    @FXML
    private TextField txtConNo;


    private ObservableList<SyllabusTM> obList = FXCollections.observableArrayList();
    private String searchText="";

    private SyllabusBO syllabusBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.SYLLABUS);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory(); //To show table data
        getAllSyllabusToTable(searchText); //To get all students details to table(Not show)

        tblSyllabus.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { //Add ActionListener to selected column and display text field values
            //Check select value is not null
            if(null!=newValue) { //newValue!=null --> Get more time to compare (newValue object compare)
                // btnSaveSupplier.setText("Update Supplier");
                setDataToTextFields(newValue); //Set data to text field of selected row data of table
            }
        });
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = txtConNo.getText();

        boolean isDeleted = syllabusBO.deleteSyllabus(code);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Content deleted !").show();
            clearFieldsRefreshTable();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "Content not deleted !").show();
            clearFieldsRefreshTable();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String subject_id = txtConNo.getText();
        String sub_name = txtConName.getText();

        try {
            boolean isSaved = syllabusBO.addSyllabus(new SyllabusDTO(subject_id,sub_name));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Content saved!!!").show();
                clearFieldsRefreshTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "OOPSSS!! something happened!!!").show();
            clearFieldsRefreshTable();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String code = txtConNo.getText();
        try {
            SyllabusDTO syllabus = syllabusBO.searchSyllabus(code);
            if (syllabus != null) {
                txtConNo.setText(syllabus.getSubject_id());
                txtConName.setText(syllabus.getSub_name());

            } else {
                new Alert(Alert.AlertType.WARNING, "no Content found :(").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "oops! something went wrong :(").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String subject_id = txtConNo.getText();
        String sub_name = txtConName.getText();


        try {
            boolean isUpdated = syllabusBO.updateSyllabus(new SyllabusDTO(subject_id,sub_name));
            if (isUpdated) {

                new Alert(Alert.AlertType.CONFIRMATION, "huree! Content Updated!").show();
                clearFieldsRefreshTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
               new Alert(Alert.AlertType.ERROR, "oops! something happened!").show();
                clearFieldsRefreshTable();
        }
    }

    @FXML
    void txtConNoOnAction(ActionEvent event) {
        String subject_id=txtConNo.getText();
        if (Regex.validateContentNo(subject_id)){
            btnSearchOnAction(event);
            txtConName.requestFocus();
        }else {
            txtConNo.clear();
            new Alert(Alert.AlertType.WARNING, "No matching Content No please Input SUP format!!!").show();
        }


    }



    private void setDataToTextFields(SyllabusTM syllabusTM) {
        txtConNo.setText(syllabusTM.getSubject_id());
        txtConName.setText(syllabusTM.getSub_name());
    }

    private void setCellValueFactory() {
        colConNo.setCellValueFactory(new PropertyValueFactory<>("subject_id")); //SupplierTM class attributes names
        colConName.setCellValueFactory(new PropertyValueFactory<>("sub_name"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void getAllSyllabusToTable(String searchText) {
        try {
            List<SyllabusDTO> syllabusList = syllabusBO.getAllSyllabus();
            for(SyllabusDTO syllabus : syllabusList) {
                if (syllabus.getSubject_id().contains(searchText) || syllabus.getSub_name().contains(searchText)){  //Check pass text contains of the supName
                    JFXButton btnDel=new JFXButton("Delete");
                    btnDel.setAlignment(Pos.CENTER);
                    btnDel.setStyle("-fx-background-color: #686de0; ");
                    btnDel.setCursor(Cursor.HAND);


                    SyllabusTM tm=new SyllabusTM(
                            syllabus.getSubject_id(),
                            syllabus.getSub_name(),

                            btnDel);

                    obList.add(tm);

                    setDeleteButtonTableOnAction(btnDel);
                }
            }
            tblSyllabus.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    private void setDeleteButtonTableOnAction(JFXButton btnDel) {
        btnDel.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete?", yes, no).showAndWait();

            if (buttonType.get() == yes) {
               // txtConNo.setText(tblSyllabus.getSelectionModel().getSelectedItem().getStId());
                //btnSearchOnAction(e);
                try {
                    btnDeleteOnAction(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


                tblSyllabus.getItems().clear();
                getAllSyllabusToTable(searchText);
            }
        });
    }
    private void clearFieldsRefreshTable(){
        txtConNo.clear();
        txtConName.clear();
        tblSyllabus.getItems().clear();
        getAllSyllabusToTable("");

    }
    @FXML
    void btnGetReportOnAction(ActionEvent event) {
        Thread t1=new Thread(
                () -> {
                    String reportPath = "H:\\2nd semester\\Layered Architecture Module\\first sem proj to layered archi\\Little Sunshine_Project\\src\\main\\resources\\reports\\syllabus.jrxml";
                    String sql="select * from syllabus";
                    String path = FileSystems.getDefault().getPath("/reports/syllabus.jrxml").toAbsolutePath().toString();
                    JasperDesign jasdi = null;
                    try {
                        jasdi = JRXmlLoader.load(reportPath);
                        JRDesignQuery newQuery = new JRDesignQuery();
                        newQuery.setText(sql);
                        jasdi.setQuery(newQuery);
                        JasperReport js = JasperCompileManager.compileReport(jasdi);
                        JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());
                        JasperViewer viewer = new JasperViewer(jp, false);
                        viewer.show();
                    } catch (JRException e) {
                        e.printStackTrace();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }

                });

        t1.start();
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtConNo.clear();
        txtConName.clear();
        tblSyllabus.getItems().clear();
        getAllSyllabusToTable("");
    }


}
