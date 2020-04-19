package floor_details_table;
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
import floor_details_table.floorlayoutBean;;
public class floordatabaseviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<String> comboid;

    @FXML
    private TableView<floorlayoutBean> txttable;

    ObservableList<floorlayoutBean> list;
    Connection con;
    @SuppressWarnings("unchecked")
	@FXML
    
    
    void createtable()
    {
    	TableColumn<floorlayoutBean, String> f=new TableColumn<floorlayoutBean, String>("Floors");//Dikhava Title
    	f.setCellValueFactory(new PropertyValueFactory<>("Floors"));//bean field name, no link with table col name
    	TableColumn<floorlayoutBean, String> s=new TableColumn<floorlayoutBean, String>("Slots");//Dikhava Title
    	s.setCellValueFactory(new PropertyValueFactory<>("slotss"));//bean field name, no link with table col name
    	TableColumn<floorlayoutBean, String> so=new TableColumn<floorlayoutBean, String>("Slots_Occupied");//Dikhava Title
    	so.setCellValueFactory(new PropertyValueFactory<>("slots_occupied"));//bean field name, no link with table col name
    	TableColumn<floorlayoutBean, String> t=new TableColumn<floorlayoutBean, String>("Type");//Dikhava Title
    	t.setCellValueFactory(new PropertyValueFactory<>("type"));//bean field name, no link with table col name
    	txttable.getColumns().clear();
    	txttable.getColumns().addAll(f,s,so,t);
    	txttable.setItems(list);
    }
    @FXML
void dofecthselected(ActionEvent event) 
    {
    	con=connection_classs.doConnect();
    	try{
    		if(comboid.getSelectionModel().getSelectedItem().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
    			al.setTitle("Error 404");
    			al.setContentText("Please Enter Valid Floor");
    			al.show();
    		}
    		else
    		{
    			try{
    				PreparedStatement pst =con.prepareStatement("select * from floor_database where floor=?");
    				pst.setString(1, comboid.getSelectionModel().getSelectedItem());
    				fetchresult(pst);
    				}catch(Exception ex)
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
    void dofetchall(ActionEvent event) {

    	 con=connection_classs.doConnect();
    		try{
			PreparedStatement pst =con.prepareStatement("select * from floor_database");
			fetchresult(pst);
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		
    	
    }
    void fetchresult(PreparedStatement pst)
    {
    	list=FXCollections.observableArrayList();
    	try{ResultSet tableview= pst.executeQuery();	
		while(tableview.next())
		{
			String slot=tableview.getString("Slot");//col. name acc. to table
			String floor=tableview.getString("Floor");
			String occupied=tableview.getString("Slots_Occupied");
			String Type=tableview.getString("Type");
			System.out.println(slot+"  "+floor+"  "+occupied+"   "+Type);
			floorlayoutBean sb=new floorlayoutBean(floor,slot,occupied,Type);
			list.add(sb);

		}
		createtable();}catch(Exception e)
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
		  	comboid.getItems().addAll(floorsAry);	
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
