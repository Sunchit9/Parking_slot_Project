package parking_detail_table;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import connection_file.connection_classs;
import parking_detail_table.parkingBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class parkingtableviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> txtvehicle;
    
    @FXML
    private TableView<parkingBean> txttable;

    @FXML
    private DatePicker txtED;

    @FXML
    private DatePicker txtLD;

    ObservableList<parkingBean> list;
    Connection con;
    
    @SuppressWarnings("unchecked")
   	void createtable()
       {
       
       	TableColumn<parkingBean, String> rid=new TableColumn<parkingBean, String>("Registration Id");//Dikhava Title
       	rid.setCellValueFactory(new PropertyValueFactory<>("RID"));//bean field name, no link with table col name
       	TableColumn<parkingBean, String> vno=new TableColumn<parkingBean, String>("Vehicle No");//Dikhava Title
       	vno.setCellValueFactory(new PropertyValueFactory<>("V_NO"));//bean field name, no link with table col name
    	TableColumn<parkingBean, String> m=new TableColumn<parkingBean, String>("MobileNo");//Dikhava Title
       	m.setCellValueFactory(new PropertyValueFactory<>("MOB"));//bean field name, no link with table col name
       	TableColumn<parkingBean, String> ed=new TableColumn<parkingBean, String>("Entry Date");//Dikhava Title
       	ed.setCellValueFactory(new PropertyValueFactory<>("ENTRYD"));//bean field name, no link with table col name
       	TableColumn<parkingBean, String> et=new TableColumn<parkingBean, String>("Entry Time");//Dikhava Title
       	et.setCellValueFactory(new PropertyValueFactory<>("ENTRYT"));//bean field name, no link with table col name
       	TableColumn<parkingBean, String> ld=new TableColumn<parkingBean, String>("Exit Date");//Dikhava Title
       	ld.setCellValueFactory(new PropertyValueFactory<>("EXITD"));//bean field name, no link with table col name
       	TableColumn<parkingBean, String> lt=new TableColumn<parkingBean, String>("Exit Time");//Dikhava Title
       	lt.setCellValueFactory(new PropertyValueFactory<>("EXITT"));//bean field name, no link with table col name
    	TableColumn<parkingBean, String> s=new TableColumn<parkingBean, String>("SlotAlloted");//Dikhava Title
       	s.setCellValueFactory(new PropertyValueFactory<>("SLOT"));//bean field name, no link with table col name
       	TableColumn<parkingBean, String> f=new TableColumn<parkingBean, String>("Floor");//Dikhava Title
       	f.setCellValueFactory(new PropertyValueFactory<>("FLOOR"));//bean field name, no link with table col name   
    	TableColumn<parkingBean, String> t=new TableColumn<parkingBean, String>("Type");//Dikhava Title
       	t.setCellValueFactory(new PropertyValueFactory<>("TYPE"));//bean field name, no link with table col name
       	txttable.getColumns().clear();
       	txttable.getColumns().addAll(rid,vno,m,ed,et,ld,lt,s,f,t);
       	txttable.setItems(list);
       }
    
    @FXML
    void doentrydate(ActionEvent event) {
    	try{
    		LocalDate local=txtED.getValue();
    	java.sql.Date dob=java.sql.Date.valueOf(local);
    	 con=connection_classs.doConnect();	
   	      PreparedStatement	pst=con.prepareStatement("select* from parking_database where EntryDate=?");
   	    	pst.setDate(1,dob);
   	      queryrunningfun(pst);
   	      txtvehicle.getSelectionModel().clearSelection();
   	      txtLD.setValue(null);
   	  	}
   	    	catch(Exception ex)
   	    	{
   	    		ex.printStackTrace();
   	    	}
    }

    @FXML
    void doexitdate(ActionEvent event) {
    	try{
    		LocalDate local=txtLD.getValue();
    	java.sql.Date dob=	java.sql.Date.valueOf(local);
    	 con=connection_classs.doConnect();
   	      PreparedStatement	pst=con.prepareStatement("select* from parking_database where ExitDate=?");
   	    	pst.setDate(1,dob);
   	      queryrunningfun(pst);
   	      txtvehicle.getSelectionModel().clearSelection();
   	      txtED.setValue(null);
   	  	}
   	    	catch(Exception ex)
   	    	{
   	    		ex.printStackTrace();
   	    	}
    }

    @FXML
    void doselectedvno(ActionEvent event) {
    	 con=connection_classs.doConnect();
  	  	try{
  	      PreparedStatement	pst=con.prepareStatement("select* from parking_database where VehicleNo=?");
  	    	pst.setString(1, txtvehicle.getSelectionModel().getSelectedItem());
  	      queryrunningfun(pst);
   	      txtED.setValue(null);
   	      txtLD.setValue(null);
  	  	}
  	    	catch(Exception ex)
  	    	{
  	    		ex.printStackTrace();
  	    	}
    }
    @FXML
    void doshowall(ActionEvent event) {
    	
    	con=connection_classs.doConnect();
      	try{
          PreparedStatement	pst=con.prepareStatement("select* from parking_database");
          queryrunningfun(pst);
   	      txtED.setValue(null);
   	      txtLD.setValue(null);

      	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    }

    @FXML
    void doshowselected(ActionEvent event) {
    	
    	 con=connection_classs.doConnect();
   	  	try{
   	      PreparedStatement	pst=con.prepareStatement("select* from parking_database where VehicleNo=?");
   	    	pst.setString(1, txtvehicle.getValue());
   	      queryrunningfun(pst);
    	      txtED.setValue(null);
    	      txtLD.setValue(null);
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
			String ri=tableview.getString("RID");
			String mob=tableview.getString("MobileNo");
			String type=tableview.getString("Type");
			String ed=tableview.getString("EntryDate");//col. name acc. to table
			String et=tableview.getString("EntryTime");
			String ld=tableview.getString("ExitDate");
			String lt=tableview.getString("ExitTime");
			String s=tableview.getString("SlotAlloted");
			String f=tableview.getString("FloorAlloted");
			System.out.println(ri+" "+svn+" "+mob+" "+ed+" "+et+" "+ld+" "+lt+" "+s+" "+f+" "+type);
			parkingBean sb=new parkingBean(ri,svn,mob,ed,et,ld,lt,s,f,type);
			list.add(sb);
		}
		createtable();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}
    }
    void fetchini()
    {
    	con=connection_classs.doConnect();
      	try{
      		ArrayList<String> vnoAry=new ArrayList<>();
          PreparedStatement	pst=con.prepareStatement("select VehicleNo from parking_database order by VehicleNo");
        	ResultSet tableview= pst.executeQuery();
        	while(tableview.next())
        		{
        			String v=tableview.getString("VehicleNo");
        			vnoAry.add(String.valueOf(v));
        		}
		  	txtvehicle.getItems().addAll(vnoAry);	
      	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    }
    @FXML
    void initialize() {
        assert txtvehicle != null : "fx:id=\"txtvehicle\" was not injected: check your FXML file 'parkingtableview.fxml'.";
        assert txtED != null : "fx:id=\"txtED\" was not injected: check your FXML file 'parkingtableview.fxml'.";
        assert txtLD != null : "fx:id=\"txtLD\" was not injected: check your FXML file 'parkingtableview.fxml'.";
        fetchini();
    }
}
