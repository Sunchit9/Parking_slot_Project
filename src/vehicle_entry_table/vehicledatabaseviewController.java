package vehicle_entry_table;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import connection_file.connection_classs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import vehicle_entry_table.vehicleBean;

public class vehicledatabaseviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    ObservableList<vehicleBean> list;
    Connection con;

    @FXML
    private TableView<vehicleBean> txttable;

    @FXML
    private ComboBox<String> combomobileno;

    @SuppressWarnings("unchecked")
	void createtable()
    {
    
    	TableColumn<vehicleBean, String> m=new TableColumn<vehicleBean, String>("MobileNo");//Dikhava Title
    	m.setCellValueFactory(new PropertyValueFactory<>("MOB"));//bean field name, no link with table col name
    	TableColumn<vehicleBean, String> rid=new TableColumn<vehicleBean, String>("Registration Id");//Dikhava Title
    	rid.setCellValueFactory(new PropertyValueFactory<>("RID"));//bean field name, no link with table col name
    	TableColumn<vehicleBean, String> vno=new TableColumn<vehicleBean, String>("Vehicle No");//Dikhava Title
    	vno.setCellValueFactory(new PropertyValueFactory<>("V_NO"));//bean field name, no link with table col name
    	TableColumn<vehicleBean, String> t=new TableColumn<vehicleBean, String>("Type");//Dikhava Title
    	t.setCellValueFactory(new PropertyValueFactory<>("TYPE"));//bean field name, no link with table col name
    	TableColumn<vehicleBean, String> ed=new TableColumn<vehicleBean, String>("Entry Date");//Dikhava Title
    	ed.setCellValueFactory(new PropertyValueFactory<>("ENTRYD"));//bean field name, no link with table col name
    	TableColumn<vehicleBean, String> et=new TableColumn<vehicleBean, String>("Entry Time");//Dikhava Title
    	et.setCellValueFactory(new PropertyValueFactory<>("ENTRYT"));//bean field name, no link with table col name
    	TableColumn<vehicleBean, String> ld=new TableColumn<vehicleBean, String>("Exit Date");//Dikhava Title
    	ld.setCellValueFactory(new PropertyValueFactory<>("EXITD"));//bean field name, no link with table col name
    	TableColumn<vehicleBean, String> lt=new TableColumn<vehicleBean, String>("Exit Time");//Dikhava Title
    	lt.setCellValueFactory(new PropertyValueFactory<>("EXITT"));//bean field name, no link with table col name
    	txttable.getColumns().clear();
    	txttable.getColumns().addAll(m,rid,vno,t,ed,et,ld,lt);
    	txttable.setItems(list);
    }
    
    @FXML
    void doshowAll(ActionEvent event) {
    	con=connection_classs.doConnect();
      	try{
          PreparedStatement	pst=con.prepareStatement("select* from parking_database");
          queryrunningfun(pst);
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
    	      PreparedStatement	pst=con.prepareStatement("select* from parking_database where MobileNo=?");
    	    	pst.setString(1, combomobileno.getSelectionModel().getSelectedItem());
    	      queryrunningfun(pst);
    	  	}
    	    	catch(Exception ex)
    	    	{
    	    		ex.printStackTrace();
    	    	}
    }
    void  queryrunningfun(PreparedStatement pst)
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
			vehicleBean sb=new vehicleBean(mob,ri,svn,type,ed,et,ld,lt);
			list.add(sb);
		}
		createtable();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}
    }

    @FXML
   
    void fetchall()
    {
    	con=connection_classs.doConnect();
      	try{
      		ArrayList<String> vehicleAry=new ArrayList<>();
          PreparedStatement	pst=con.prepareStatement("select MobileNo from parking_database order by MobileNo");
        	ResultSet tableview= pst.executeQuery();
        	while(tableview.next())
        		{
        			String m=tableview.getString("MobileNo");
        			vehicleAry.add(String.valueOf(m));
        		}
		  	combomobileno.getItems().addAll(vehicleAry);	
      	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    }
    @FXML
    void initialize() {
        assert txttable != null : "fx:id=\"txttable\" was not injected: check your FXML file 'vehicledatabaseview.fxml'.";
     //   assert combomobileno != null : "fx:id=\"combovehicleno\" was not injected: check your FXML file 'vehicledatabaseview.fxml'.";
        fetchall();
    }
}
