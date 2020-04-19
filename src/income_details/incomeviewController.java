package income_details;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import connection_file.connection_classs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class incomeviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dtepicker;

    @FXML
    private TextField incomeID;

    @FXML
    private TableView<incomeBean> txttable;
    ObservableList<incomeBean> list;
    Connection con;
    
	@SuppressWarnings("unchecked")
	void createtable()
    {
    
    	TableColumn<incomeBean, String> vno=new TableColumn<incomeBean, String>("Vehicle No");//Dikhava Title
    	vno.setCellValueFactory(new PropertyValueFactory<>("V_NO"));//bean field name, no link with table col name
    	TableColumn<incomeBean, String> t=new TableColumn<incomeBean, String>("Type");//Dikhava Title
    	t.setCellValueFactory(new PropertyValueFactory<>("TYPE"));//bean field name, no link with table col name
    	TableColumn<incomeBean, String> ed=new TableColumn<incomeBean, String>("Entry Date");//Dikhava Title
    	ed.setCellValueFactory(new PropertyValueFactory<>("ENTRYD"));//bean field name, no link with table col name
    	TableColumn<incomeBean, String> et=new TableColumn<incomeBean, String>("Entry Time");//Dikhava Title
    	et.setCellValueFactory(new PropertyValueFactory<>("ENTRYT"));//bean field name, no link with table col name
    	TableColumn<incomeBean, String> ld=new TableColumn<incomeBean, String>("Exit Date");//Dikhava Title
    	ld.setCellValueFactory(new PropertyValueFactory<>("EXITD"));//bean field name, no link with table col name
    	TableColumn<incomeBean, String> lt=new TableColumn<incomeBean, String>("Exit Time");//Dikhava Title
    	lt.setCellValueFactory(new PropertyValueFactory<>("EXITT"));//bean field name, no link with table col name
 	    TableColumn<incomeBean, String> c=new TableColumn<incomeBean, String>("Amount Charged");//Dikhava Title
    	c.setCellValueFactory(new PropertyValueFactory<>("CHARGES"));//bean field name, no link with table col name
       	txttable.getColumns().clear(); 
    	txttable.getColumns().addAll(vno,t,ed,et,ld,lt,c);
    	txttable.setItems(list);
    }
    @FXML
    void showall(ActionEvent event) {
    	try{
    	 con=connection_classs.doConnect();
   	      PreparedStatement	pst=con.prepareStatement("select* from parking_database");
   	   PreparedStatement	pst2=con.prepareStatement("select sum(Charges) from parking_database");
	      queryrunningfun(pst);
	      queryrunning2(pst2);
   	  	}
   	    	catch(Exception ex)
   	    	{
   	    		ex.printStackTrace();
   	    	}
    }

    @FXML
    void showselcted(ActionEvent event) {
    	try{
    		LocalDate local=dtepicker.getValue();
    	java.sql.Date ed=	java.sql.Date.valueOf(local);
    	 con=connection_classs.doConnect();
   	      PreparedStatement	pst=con.prepareStatement("select* from parking_database where ExitDate=?");
   	    	pst.setDate(1,ed);
     	      PreparedStatement	pst2=con.prepareStatement("select sum(Charges) from parking_database where ExitDate=?");
     	    	pst2.setDate(1,ed);
     	      queryrunningfun(pst);
   	      queryrunning2(pst2);
   	  	}
   	    	catch(Exception ex)
   	    	{
   	    		ex.printStackTrace();
   	    	}
    }
    void queryrunningfun(PreparedStatement pst)
    {
    	list=FXCollections.observableArrayList();
    	try{
    	ResultSet tableview= pst.executeQuery();	
		while(tableview.next())
		{
			String svn=tableview.getString("VehicleNo");//col. name acc. to table
			String type=tableview.getString("Type");
			String ed=tableview.getString("EntryDate");//col. name acc. to table
			String et=tableview.getString("EntryTime");
			String ld=tableview.getString("ExitDate");
			String lt=tableview.getString("ExitTime");
			String bill=tableview.getString("Charges");
			System.out.println(svn+" "+ed+" "+et+" "+ld+" "+lt+" "+type);
			incomeBean sb=new incomeBean(svn,type,ed,et,ld,lt,bill);
			list.add(sb);
		}
		createtable();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}
    }
    void queryrunning2(PreparedStatement pst)
    {
    	try{
        	ResultSet tableview= pst.executeQuery();
        	String sum="0";
        	while(tableview.next())
    		{
        		 sum=tableview.getString("sum(charges)");
    		}
        	incomeID.setText(sum);
        	incomeID.setDisable(true);
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    @FXML
    void initialize() {
        assert dtepicker != null : "fx:id=\"dtepicker\" was not injected: check your FXML file 'incomeview.fxml'.";
        assert incomeID != null : "fx:id=\"incomeID\" was not injected: check your FXML file 'incomeview.fxml'.";
        assert txttable != null : "fx:id=\"txttable\" was not injected: check your FXML file 'incomeview.fxml'.";

    }
}
