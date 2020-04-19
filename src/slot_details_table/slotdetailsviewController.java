package slot_details_table;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import slot_details_table.slotlayoutBean;

public class slotdetailsviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboidf;

    @FXML
    private ComboBox<String> comboids;

    @FXML
    private TableView<slotlayoutBean> txttable;
    ObservableList<slotlayoutBean> list;
    Connection con;
    
    @SuppressWarnings("unchecked")
	void createtable()
    {
    	TableColumn<slotlayoutBean, String> f=new TableColumn<slotlayoutBean, String>("Slots");//Dikhava Title
    	f.setCellValueFactory(new PropertyValueFactory<>("Floors"));//bean field name, no link with table col name
    	TableColumn<slotlayoutBean, String> s=new TableColumn<slotlayoutBean, String>("Floors");//Dikhava Title
    	s.setCellValueFactory(new PropertyValueFactory<>("slotss"));//bean field name, no link with table col name
    	TableColumn<slotlayoutBean, String> v=new TableColumn<slotlayoutBean, String>("Vehicle_Parked");//Dikhava Title
    	v.setCellValueFactory(new PropertyValueFactory<>("vehicle"));//bean field name, no link with table col name
    	TableColumn<slotlayoutBean, String> t=new TableColumn<slotlayoutBean, String>("Type");//Dikhava Title
    	t.setCellValueFactory(new PropertyValueFactory<>("type"));//bean field name, no link with table col name
    	txttable.getColumns().clear();
    	txttable.getColumns().addAll(s,f,v,t);
    	txttable.setItems(list);
    }

    @FXML
    void FetchAll(ActionEvent event) {
    	con=connection_classs.doConnect();
		try{
		PreparedStatement pst =con.prepareStatement("select * from slots_database");
		queryrunningfunction(pst);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}

    }
    @FXML
    void fecthallfloors(ActionEvent event) {
    	comboids.getItems().clear();
    	con=connection_classs.doConnect();
    	try{
    		if(comboidf.getSelectionModel().getSelectedItem().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
    			al.setTitle("Error 404");
    			al.setContentText("Please Enter Valid Floor");
    			al.show();
    		}
    		else
    		{
    			try{
    				PreparedStatement pst=con.prepareStatement("select * from slots_database where floor=? order by slot");
      	          pst.setString(1, comboidf.getSelectionModel().getSelectedItem());
      	          queryrunningfunction(pst);
    				
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
   
    			try{
    	      		ArrayList<String> slotsAry=new ArrayList<>();
    	          PreparedStatement	pst1=con.prepareStatement("select slot from slots_database where floor=? order by slot");
    	          pst1.setString(1, comboidf.getSelectionModel().getSelectedItem());
    	        	ResultSet tableview= pst1.executeQuery();
    	        	while(tableview.next())
    	        		{
    	        			String s=tableview.getString("Slot");
    	        			slotsAry.add(String.valueOf(s));
    	        		}
    			  	comboids.getItems().addAll(slotsAry);	
    	      	}
    	        	catch(Exception ex)
    	        	{
    	        		ex.printStackTrace();
    	        	}
    		}
    	}
    	catch (Exception e) {
		e.printStackTrace();
    	}
    }

    @FXML
    void fetchallslots(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	try{ PreparedStatement	pst=con.prepareStatement("select * from slots_database where floor=? and slot=?");
         pst.setString(1, comboidf.getSelectionModel().getSelectedItem());
         pst.setString(2, comboids.getSelectionModel().getSelectedItem());
    	ResultSet tableview= pst.executeQuery();	
		while(tableview.next())
		{
			String slot=tableview.getString("Slot");//col. name acc. to table
			String floor=tableview.getString("Floor");
			String VP=tableview.getString("VehicleParked");
			String Type=tableview.getString("Type");
			System.out.println(slot+"  "+floor+"  "+VP+"   "+Type);
			slotlayoutBean sb=new slotlayoutBean(slot,floor,VP,Type);
			list.add(sb);
		}
		createtable();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}

    }
    void queryrunningfunction(PreparedStatement pst)
    {
    	list=FXCollections.observableArrayList();
    	try{ResultSet tableview= pst.executeQuery();	
		while(tableview.next())
		{
			String slot=tableview.getString("Slot");//col. name acc. to table
			String floor=tableview.getString("Floor");
			String VP=tableview.getString("VehicleParked");
			String Type=tableview.getString("Type");
			System.out.println(slot+"  "+floor+"  "+VP+"   "+Type);
			slotlayoutBean sb=new slotlayoutBean(floor,slot,VP,Type);
			list.add(sb);
		}
		createtable();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}
    }
    void fetchinitial()
    {
    	con=connection_classs.doConnect();
      	try{
      		ArrayList<String> floorsAry=new ArrayList<>();
          PreparedStatement	pst=con.prepareStatement("select floor from floor_database order by Floor");
        	ResultSet tableview= pst.executeQuery();
        	while(tableview.next())
        		{
        			String f=tableview.getString("Floor");
        			floorsAry.add(String.valueOf(f));
        		}
		  	comboidf.getItems().addAll(floorsAry);	
      	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    }
    @FXML
    void initialize() {
    	fetchinitial();
    }
}
