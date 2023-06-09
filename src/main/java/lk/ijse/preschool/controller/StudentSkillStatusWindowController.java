package lk.ijse.preschool.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.preschool.bo.BOFactory;
import lk.ijse.preschool.bo.costom.SkillStatusBO;
import lk.ijse.preschool.bo.costom.StudentBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.SkillStatusDTO;
import lk.ijse.preschool.dto.StudentDTO;
import lk.ijse.preschool.entity.SkillStatus;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentSkillStatusWindowController implements Initializable {


    @FXML
    private JFXComboBox<String> cmbStId;

    @FXML
    private TextField txtStudentName;

   // private ObservableList<SkillStatusTM> obList = FXCollections.observableArrayList();
    private String searchText="";

    @FXML
    private JFXComboBox<String> cmbCountngStatus;

    @FXML
    private JFXComboBox<String> cmbCraftingStatus;

    @FXML
    private JFXComboBox<String> cmbDrawingStatus;

    @FXML
    private JFXComboBox<String> cmbReadingStatus;

    @FXML
    private JFXComboBox<String> cmbSingingStatus;

    @FXML
    private JFXComboBox<String> cmbWritingStatus;

    private static ObservableList<String> items = FXCollections.observableArrayList("Excellent", "Good", "Weak");

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    private SkillStatusBO skillStatusBO =BOFactory.getInstance().getBO(BOFactory.BOTypes.SKILLSTATUS);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadStid();
        loadStatus();

    }

    private void loadStatus()  {
        cmbCountngStatus.getItems().addAll(items);
        cmbCraftingStatus.getItems().addAll(items);
        cmbDrawingStatus.getItems().addAll(items);
        cmbReadingStatus.getItems().addAll(items);
        cmbSingingStatus.getItems().addAll(items);
        cmbWritingStatus.getItems().addAll(items);
    }

    private void loadStid() {
        try {
            ArrayList<String> ids = studentBO.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String id : ids) {
                obList.add(id);
            }
            cmbStId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbStIdOnActon(ActionEvent event) {
        String studentId = cmbStId.getSelectionModel().getSelectedItem();

        try {
            StudentDTO student = studentBO.searchStudent(studentId);
            txtStudentName.setText(student.getName());
            SkillStatusDTO skillStatus=skillStatusBO.search(studentId);
            //ArrayList<String> skillStatus= skillStatusBO.getStatus();

            if (skillStatus!=null){
                cmbWritingStatus.setValue(skillStatus.getWriting());
                cmbSingingStatus.setValue(skillStatus.getSinging());
                cmbReadingStatus.setValue(skillStatus.getReading());
                cmbDrawingStatus.setValue(skillStatus.getDrawing());
                cmbCraftingStatus.setValue(skillStatus.getCrafting());
                cmbCountngStatus.setValue(skillStatus.getCrafting());
                loadStid();
                loadStatus();
            }else{
                cmbWritingStatus.getItems().clear();
                cmbDrawingStatus.getItems().clear();
                cmbReadingStatus.getItems().clear();
                cmbSingingStatus.getItems().clear();
                cmbCraftingStatus.getItems().clear();
                cmbCountngStatus.getItems().clear();
                loadStid();
                loadStatus();
                new Alert(Alert.AlertType.ERROR, "No input for Students Skill Please Input Student Skills").show();
               // return;
            }


        } catch (SQLException | ClassNotFoundException e) {
          //  e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = cmbStId.getSelectionModel().getSelectedItem();
        String name=txtStudentName.getText();
        String counting = cmbCountngStatus.getSelectionModel().getSelectedItem();
        String crafting = cmbCraftingStatus.getSelectionModel().getSelectedItem();
        String drawing = cmbDrawingStatus.getSelectionModel().getSelectedItem();
        String reading = cmbReadingStatus.getSelectionModel().getSelectedItem();
        String singing = cmbSingingStatus.getSelectionModel().getSelectedItem();
        String writing = cmbWritingStatus.getSelectionModel().getSelectedItem();

        boolean isSaved;

            try {
                isSaved = skillStatusBO.add(new SkillStatusDTO(id,name,counting, crafting,drawing, reading,singing, writing) );
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Skills saved!!!").show();
                  //  tblStudent.getItems().clear();
                  //  getAllStudentsToTable("");
                    // clearFieldsRefreshTable();
                    cmbStIdOnActon(event);
                }
            } catch (SQLException | ClassNotFoundException e) {
                // System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "OOPSSS!! something happened!!!").show();
                // clearFieldsRefreshTable();
            }
        }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = cmbStId.getSelectionModel().getSelectedItem();
        String name=txtStudentName.getText();
        String counting = cmbCountngStatus.getSelectionModel().getSelectedItem();
        String crafting = cmbCraftingStatus.getSelectionModel().getSelectedItem();
        String drawing = cmbDrawingStatus.getSelectionModel().getSelectedItem();
        String reading = cmbReadingStatus.getSelectionModel().getSelectedItem();
        String singing = cmbSingingStatus.getSelectionModel().getSelectedItem();
        String writing = cmbWritingStatus.getSelectionModel().getSelectedItem();

            try {
                boolean isUpdated = skillStatusBO.update(new SkillStatusDTO(id,name,counting, crafting,drawing, reading,singing, writing));
                if (isUpdated) {

                    new Alert(Alert.AlertType.CONFIRMATION, "huree! Student Skill Updated!").show();
                  //  cmbStIdOnActon(event);
                }else{
                    System.out.println("hello");
                }
            } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "oops! something happened!").show();
                //  clearFieldsRefreshTable();
            }
        }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        cmbStId.getItems().clear();
        txtStudentName.clear();
        cmbCountngStatus.getItems().clear();
        cmbCraftingStatus.getItems().clear();
        cmbDrawingStatus.getItems().clear();
        cmbReadingStatus.getItems().clear();
        cmbSingingStatus.getItems().clear();
        cmbWritingStatus.getItems().clear();

        loadStatus();

    }
    @FXML
    void btnReportOnAction(ActionEvent event) {
        Thread t1=new Thread(
                () -> {
                    String reportPath = "H:\\2nd semester\\Layered Architecture Module\\first sem proj to layered archi\\Little Sunshine_Project\\src\\main\\resources\\reports\\remedialreport.jrxml";
                    String sql="SELECT stId, stName,counting,crafting,drawing,reading,singing,writing \n" +
                            "FROM student_skill_status\n" +
                            "WHERE counting = 'Weak' \n" +
                            "OR crafting = 'Weak' \n" +
                            "OR drawing = 'Weak' \n" +
                            "OR reading = 'Weak' \n" +
                            "OR singing = 'Weak' \n" +
                            "OR writing = 'Weak';";
                    String path = FileSystems.getDefault().getPath("/reports/remedialreport.jrxml").toAbsolutePath().toString();
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
}
