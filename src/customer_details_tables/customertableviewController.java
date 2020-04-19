package customer_details_tables;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
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
import javafx.stage.FileChooser;
import parking_detail_table.parkingBean;
import customer_details_tables.customerBean;
public class customertableviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomob;
    

    @FXML
    private TableView<customerBean> txttable;
    
    ObservableList<customerBean> list;
    Connection con;
    
	@SuppressWarnings("unchecked")
	void createtable()
    {
    
    	TableColumn<parkingBean, String> rid=new TableColumn<parkingBean, String>("Registration Id");//Dikhava Title
    	rid.setCellValueFactory(new PropertyValueFactory<>("RID"));//bean field name, no link with table col name
    	TableColumn<customerBean, String> n=new TableColumn<customerBean, String>("Name");//Dikhava Title
    	n.setCellValueFactory(new PropertyValueFactory<>("NAME"));//bean field name, no link with table col name
 	    TableColumn<customerBean, String> m=new TableColumn<customerBean, String>("MobileNo");//Dikhava Title
    	m.setCellValueFactory(new PropertyValueFactory<>("MOB"));//bean field name, no link with table col name
    	TableColumn<customerBean, String> c=new TableColumn<customerBean, String>("City");//Dikhava Title
    	c.setCellValueFactory(new PropertyValueFactory<>("CITY"));//bean field name, no link with table col name
    	TableColumn<customerBean, String> ad=new TableColumn<customerBean, String>("Address");//Dikhava Title
    	ad.setCellValueFactory(new PropertyValueFactory<>("ADDR"));//bean field name, no link with table col name
    	txttable.getColumns().clear();
    	txttable.getColumns().addAll(n,m,c,ad);
    	txttable.setItems(list);
    }

    @FXML
    void showall(ActionEvent event) {
    	
    	con=connection_classs.doConnect();
      	try{
          PreparedStatement	pst=con.prepareStatement("select* from customer_database");
          queryrunningfun(pst);
      	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    }
    @FXML
    void doselected(ActionEvent event) {
    	
    	 con=connection_classs.doConnect();
   	  	try{
   	      PreparedStatement	pst=con.prepareStatement("select* from customer_database where MobileNo=?");
   	    	pst.setString(1, combomob.getSelectionModel().getSelectedItem());
   	      queryrunningfun(pst);
    	     
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
			String name=tableview.getString("Customer_Name");//col. name acc. to table
			String mob=tableview.getString("MobileNo");
			String add=tableview.getString("Address");
			String city=tableview.getString("City");
			System.out.println(name+" "+mob+" "+add+" "+city);
			customerBean sb=new customerBean(name,mob,add,city);
			list.add(sb);
		}
		createtable();
		}catch(Exception e)
    	{
			e.printStackTrace();
    	}
    }
    void initials()
    {
    	con=connection_classs.doConnect();
      	try{
      		ArrayList<String> mAry=new ArrayList<>();
          PreparedStatement	pst=con.prepareStatement("select MobileNo from customer_database order by MobileNo");
        	ResultSet tableview= pst.executeQuery();
        	while(tableview.next())
        		{
        			String m=tableview.getString("MobileNo");
        			mAry.add(String.valueOf(m));
        		}
		  	combomob.getItems().addAll(mAry);	
      	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    }
    @FXML
    void doExportToExcel(ActionEvent event) {
    	try {
			writeExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	FileChooser chooser=new FileChooser();
	    	
        	chooser.setTitle("Select Path:");
        	
        	chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                    
                );
        	 File file=chooser.showSaveDialog(null);
        	String filePath=file.getAbsolutePath();
        	if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
        	{
        		filePath=filePath+".csv";
        	}
        	 file = new File(filePath);
        	 
        	 
        	 
            writer = new BufferedWriter(new FileWriter(file));
            String text="Name,Mobile,City,Address\n";
            writer.write(text);
            for (customerBean p : list)
            {
				text = p.getNAME()+ "," + p.getMOB()+ "," + p.getADDR()+ "," + p.getCITY()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    @FXML
    void initialize() {
        assert combomob != null : "fx:id=\"combomob\" was not injected: check your FXML file 'customertableview.fxml'.";
        initials();
    }
   
    
}
