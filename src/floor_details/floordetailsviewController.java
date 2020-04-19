package floor_details;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import connection_file.connection_classs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class floordetailsviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCapacity;

    @FXML
    private ComboBox<String> txtfloor;

    @FXML
    private TextField txtAvailiabilty;

    @FXML
    private TextField txtType;
    @FXML
    void dofecthoncombo(ActionEvent event) {
    	try{
    		if(txtfloor.getSelectionModel().getSelectedItem().equals(""))
        	{
        		Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("Please Enter Valid Floor");
            	al.show();
        	}
    		else
    		{
    			Connection con=connection_classs.doConnect();
    		
			PreparedStatement pst =con.prepareStatement("select * from floor_database where Floor=?");
			pst.setString(1,txtfloor.getSelectionModel().getSelectedItem());
			ResultSet table= pst.executeQuery();
			int jasoos=0;
        		while(table.next())
        		{
        			jasoos=1;
        			txtCapacity.setText(table.getString("Slot"));
        			txtType.setText(table.getString("Type"));
        			txtAvailiabilty.setText(String.valueOf(Integer.parseInt(table.getString("Slot"))-Integer.parseInt(table.getString("Slots_Occupied"))));
        		}
        		if(jasoos==0)
        		{
        			Alert al=new Alert(AlertType.INFORMATION);
                	al.setTitle("Error 404");
                	al.setContentText("Please Enter Valid Floor");
                	al.show();
        		}
        	}}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}

    }

    @FXML
    void doDelete(ActionEvent event) {
    	Connection con=connection_classs.doConnect();
    	try{
    	if(txtfloor.getSelectionModel().getSelectedItem().equals(""))
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("Please Enter Valid Floor");
        	al.show();
    	}
    	else
    	{
    	String floorvar=(String) txtfloor.getSelectionModel().getSelectedItem();	
			PreparedStatement pst =con.prepareStatement("delete from floor_database where Floor=?");
			pst.setString(1, floorvar);
			pst.executeUpdate();
			PreparedStatement pstinside=con.prepareStatement("delete from slots_database where Floor=?");
				pstinside.setString(1,floorvar);
				pstinside.executeUpdate();
			}}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			//notification.setText("Record Added");
			txtCapacity.clear();
			txtfloor.setSelectionModel(null);
			txtType.clear();			
    	}
    @FXML
    void doFetch(ActionEvent event) {
    	try{
    		if(txtfloor.getSelectionModel().getSelectedItem().equals(""))
        	{
        		Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("Please Enter Valid Floor");
            	al.show();
        	}
    		else
    		{
    			Connection con=connection_classs.doConnect();
    		
			PreparedStatement pst =con.prepareStatement("select * from floor_database where Floor=?");
			pst.setString(1,txtfloor.getSelectionModel().getSelectedItem());
			ResultSet table= pst.executeQuery();
			int jasoos=0;
        		while(table.next())
        		{
        			jasoos=1;
        			txtCapacity.setText(table.getString("Slot"));
        			txtType.setText(table.getString("Type"));
        			txtAvailiabilty.setText(String.valueOf(Integer.parseInt(table.getString("Slot"))-Integer.parseInt(table.getString("Slots_Occupied"))));
        		}
        		if(jasoos==0)
        		{
        			Alert al=new Alert(AlertType.INFORMATION);
                	al.setTitle("Error 404");
                	al.setContentText("Please Enter Valid Floor");
                	al.show();
        		}
        	}}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    }

    @FXML
    void doSave(ActionEvent event) {
    	Connection con=connection_classs.doConnect();
    	try{
    	if(txtfloor.getItems().equals(""))
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("Please Enter Valid Floor");
        	al.show();
    	}
    	else
    	{
    	if(txtCapacity.getText().equals(""))
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("Please Enter Valid Capacity");
        	al.show();
    	}
    	else if(txtType.getText().equals(""))
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("Please Enter Valid Type");
        	al.show();
    	}
    	else
    	{
    		
    	String floorvar= txtfloor.getSelectionModel().getSelectedItem();	
    	String capacityvar=txtCapacity.getText();
    	String typevar=txtType.getText();
			PreparedStatement pst =con.prepareStatement("insert into floor_database values(?,?,0,?)");
			pst.setString(1, floorvar);
			pst.setString(2, capacityvar);
			pst.setString(3, typevar);
			pst.executeUpdate();
			int slotno=(Integer.parseInt(floorvar)*100)+1;
			try{
			for(int i=0;i<Integer.parseInt(capacityvar);i++)
			{
				System.out.println(i);
				PreparedStatement pstinside=con.prepareStatement("insert into slots_database values(?,?,?,?)");
				pstinside.setString(1,String.valueOf(slotno));
				slotno=slotno+1;
				pstinside.setString(2,floorvar);
				pstinside.setString(3,"");
				pstinside.setString(4,typevar);
				pstinside.executeUpdate();
			}}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			//notification.setText("Record Added");
			txtCapacity.clear();
			txtfloor.setSelectionModel(null);
			txtType.clear();
			}
    	}
    	}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void one_imp_fun()
    {	
    	ArrayList<String> rollsAry=new ArrayList<>();
    	try{
    		Connection con=connection_classs.doConnect();
			PreparedStatement pst =con.prepareStatement("select Floor from floor_database");
			ResultSet table= pst.executeQuery();	
        		while(table.next())
        		{
        			String f=table.getString("Floor");
        			rollsAry.add(String.valueOf(f));
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	txtfloor.getItems().addAll(rollsAry);
    }
    @FXML
    void doUpdate(ActionEvent event) {
    	Connection con=connection_classs.doConnect();
    	try{
    	if(txtfloor.getSelectionModel().getSelectedItem().equals(""))
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("Please Enter Valid Floor");
        	al.show();
    	}
    	else
    	{
    	String floorvar=(String) txtfloor.getSelectionModel().getSelectedItem();	
			PreparedStatement pst =con.prepareStatement("delete from floor_database where Floor=?");
			pst.setString(1, floorvar);
			pst.executeUpdate();
			PreparedStatement pstinside=con.prepareStatement("delete from slots_database where Floor=?");
				pstinside.setString(1,floorvar);
				pstinside.executeUpdate();
			}}
    	catch(Exception t)
    	{
    		t.printStackTrace();
    	}
    	try{
        	if(txtfloor.getSelectionModel().getSelectedItem().equals(""))
        	{
        		Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("Please Enter Valid Floor");
            	al.show();
        	}
        	else
        	{
        	if(txtCapacity.getText().equals(""))
        	{
        		Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("Please Enter Valid Capacity");
            	al.show();
        	}
        	else if(txtType.getText().equals(""))
        	{
        		Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("Please Enter Valid Type");
            	al.show();
        	}
        	else
        	{
        	String floorvars=(String) txtfloor.getSelectionModel().getSelectedItem();	
        	String capacityvars=txtCapacity.getText();
        	String typevars=txtType.getText();
    			PreparedStatement pst =con.prepareStatement("insert into floor_database values(?,?,0,?)");
    			pst.setString(1, floorvars);
    			pst.setString(2, capacityvars);
    			pst.setString(3, typevars);
    			pst.executeUpdate();
    			int slotno=(Integer.parseInt(floorvars)*100)+1;
    			try{
    			for(int i=0;i<Integer.parseInt(capacityvars);i++)
    			{
    				System.out.println(i);
    				PreparedStatement pstinside=con.prepareStatement("insert into slots_database values(?,?,?,?)");
    				pstinside.setString(1,String.valueOf(slotno));
    				slotno=slotno+1;
    				pstinside.setString(2,floorvars);
    				pstinside.setString(3,"");
    				pstinside.setString(4,typevars);
    				pstinside.executeUpdate();
    			}}catch(Exception ex)
    			{
    				ex.printStackTrace();
    			}
    			//notification.setText("Record Added");
    			txtCapacity.clear();
    			txtfloor.setSelectionModel(null);
    			txtType.clear();
    			}
        	}
        	}
    		 catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		 }
    	}
    	

    @FXML
    void initialize() {
    	one_imp_fun();
    }
}
