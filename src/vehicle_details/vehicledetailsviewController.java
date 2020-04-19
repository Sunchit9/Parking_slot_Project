package vehicle_details;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ResourceBundle;
import connection_file.connection_classs;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vehicle_details.SST_SMS;

public class vehicledetailsviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton multipleWL;

    @FXML
    private ToggleGroup vehicletype;

    @FXML
    private RadioButton fourWL;

    @FXML
    private RadioButton twoWL;

    @FXML
    private ListView<String> floorsAvail;

    @FXML
    private ListView<String> slotsAvail;

    @FXML
    private TextField txtvehicleFloor;

    @FXML
    private TextField txtvehicleSlot;

    @FXML
    private Button checkavail;
    
    @FXML
    private TextField txtvehicleNo;

    @FXML
    private TextField txtmobile;
    
    @FXML
    private Label notification;

    @FXML
    void doAllot(ActionEvent event) {
    	ObservableList<String>all1=floorsAvail.getSelectionModel().getSelectedItems();
    	System.out.println(all1.toString());
    	String floorvar=all1.toString();
    	floorvar=floorvar.substring(1,floorvar.length()-1);
    	txtvehicleFloor.setText(floorvar);
    	ObservableList<String>all2=slotsAvail.getSelectionModel().getSelectedItems();
    	System.out.println(all2.toString());
    	String slotvar=all2.toString();
    	slotvar=slotvar.substring(1,slotvar.length()-1);
    	txtvehicleSlot.setText(slotvar);
    }
    String typesvar="";
    @FXML
    void doCheckAvail(ActionEvent event) {
    	Connection con=connection_classs.doConnect();
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("select Floor,Slot from slots_database where VehicleParked=? and Type=?");
			pst.setString(1, "");
			pst.setString(2, typesvar);
		    ResultSet newtableview=pst.executeQuery();
		    	
		    int jasoos=0;
		    ObservableList<String>all=floorsAvail.getItems();
		    floorsAvail.getItems().removeAll(all);
		    ObservableList<String>all1=slotsAvail.getItems();
		    slotsAvail.getItems().removeAll(all1);
		   while(newtableview.next())
		   { 
			jasoos=1;
			String s=newtableview.getString("Slot");
			String f=newtableview.getString("Floor");
			System.out.print("floor-"+f+" ");
	    	floorsAvail.getItems().addAll(f);
	    	System.out.println("slot-"+s);
	     	slotsAvail.getItems().addAll(s);
		    }
		if(jasoos==0)
		{
			Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("----No vaccany for this type-----");
        	al.show();
        	txtvehicleFloor.clear();
        	txtvehicleSlot.clear();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doDblClick1(MouseEvent event) {
    	ObservableList<String>all1=floorsAvail.getSelectionModel().getSelectedItems();
    	System.out.println(all1.toString());
    	String floorvar=all1.toString();
    	floorvar=floorvar.substring(1,floorvar.length()-1);
    	int indx=floorsAvail.getSelectionModel().getSelectedIndex();
		slotsAvail.getSelectionModel().select(indx);
    	txtvehicleFloor.setText(floorvar);
    	ObservableList<String>all2=slotsAvail.getSelectionModel().getSelectedItems();
    	System.out.println(all2.toString());
    	String slotvar=all2.toString();
    	slotvar=slotvar.substring(1,slotvar.length()-1);
    	txtvehicleSlot.setText(slotvar);
    }

    @FXML
    void doDblClick2(MouseEvent event) {
    	ObservableList<String>all2=slotsAvail.getSelectionModel().getSelectedItems();
    	System.out.println(all2.toString());
    	String slotvar=all2.toString();
    	slotvar=slotvar.substring(1,slotvar.length()-1);
    	txtvehicleSlot.setText(slotvar);
    	int indx=slotsAvail.getSelectionModel().getSelectedIndex();
		floorsAvail.getSelectionModel().select(indx);
		ObservableList<String>all1=floorsAvail.getSelectionModel().getSelectedItems();
    	System.out.println(all1.toString());
    	String floorvar=all1.toString();
    	floorvar=floorvar.substring(1,floorvar.length()-1);
		slotsAvail.getSelectionModel().select(indx);
    	txtvehicleFloor.setText(floorvar);
    }

    @FXML
    void doDelete(ActionEvent event) {
    	
    }

    @FXML
    void doSave(ActionEvent event) {
    	if(!txtmobile.getText().equals("")&&(!txtvehicleNo.getText().equals(""))&& (!txtvehicleFloor.getText().equals(""))&&(!txtvehicleSlot.getText().equals("")) )
    	{
    	Connection con=connection_classs.doConnect();
    	
		try 
		{
			PreparedStatement	pst = con.prepareStatement("insert into parking_database values(null,?,?,?,?,?,?,?,null,null,0.00)");
			pst.setString(1,txtvehicleNo.getText());
			pst.setString(2, txtmobile.getText());
			pst.setString(3, typesvar);
			pst.setString(4, txtvehicleFloor.getText());
			pst.setString(5, txtvehicleSlot.getText());
			pst.setDate(6,java.sql.Date.valueOf(LocalDate.now()));
			pst.setTime(7,java.sql.Time.valueOf(LocalTime.now()));
			pst.executeUpdate();
			try{
				PreparedStatement	pstinside = con.prepareStatement("select Slots_Occupied from floor_database where Floor=?");
				pstinside.setString(1, txtvehicleFloor.getText());
				ResultSet newtableview=pstinside.executeQuery();
				while(newtableview.next())
				{
					String occupiedvar=newtableview.getString("Slots_Occupied");
					occupiedvar=String.valueOf(Integer.parseInt(occupiedvar)+1);
					PreparedStatement	pstinside2 = con.prepareStatement("update floor_database set Slots_Occupied=? where Floor=?");
					pstinside2.setString(1, occupiedvar);
					pstinside2.setString(2,txtvehicleFloor.getText());
					pstinside2.executeUpdate();
					PreparedStatement	pstinside3 = con.prepareStatement("update slots_database set VehicleParked=? where Floor=? && Slot=?");
					pstinside3.setString(1, txtvehicleNo.getText());
					pstinside3.setString(2,txtvehicleFloor.getText());
					pstinside3.setString(3,txtvehicleSlot.getText());
					
					String m="Your Parking Details are :Floor- "+txtvehicleFloor.getText()+"     Slot -"+txtvehicleSlot.getText()+"       VehicleNo-"+txtvehicleNo.getText();	
			    	String resp=SST_SMS.bceSunSoftSend(txtmobile.getText(), m);
			    	System.out.println(resp);
			    	
			    	if(resp.indexOf("Exception")!=-1)
			    		System.out.println("Check Internet Connection");
			    	
			    	else
			    		if(resp.indexOf("successfully")!=-1)
			        		System.out.println("Sent");
			    		else
			    		System.out.println( "Invalid Number");
					
					pstinside3.executeUpdate();
			    	floorsAvail.getItems().removeAll();
			    	slotsAvail.getItems().removeAll();
			    	txtvehicleNo.clear();
			    	txtmobile.clear();
			    	txtvehicleFloor.clear();
			    	txtvehicleSlot.clear();
			    	try {
						Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customer_registratioin/customerdetailsview.fxml")); 
						Scene scene = new Scene(root,600,400);
						
						Stage primaryStage=new Stage();
						primaryStage.setScene(scene);
						primaryStage.show();		 
				       } 
			    	catch(Exception e)
				 	{
						e.printStackTrace();
				 	}
				}
		    	notification.setText("Data Saved");

			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		catch(Exception e)
		{
				e.printStackTrace();
		}
    	}
    	else
    	{
    		if(txtmobile.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Enter Valid Mobile_No-----");
            	al.show();
    		}
    		else if(txtvehicleNo.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Enter Valid Vehicle_No-----");
            	al.show();
    		}
    		else if(txtvehicleFloor.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Please Select Valid Floor_No-----");
            	al.show();
    		}
    		else
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Please Select Valid Slot_No-----");
            	al.show();	
    		}
    	}

    }

    @FXML
    void doUpdate(ActionEvent event) {

    }

    @FXML
    void show_four_wheeler(ActionEvent event) {
    	checkavail.setDisable(false);
    	typesvar="4W";
    	Connection con=connection_classs.doConnect();
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("select Floor,Slot from slots_database where VehicleParked=? and Type=?");
			pst.setString(1, "");
			pst.setString(2, typesvar);
		    ResultSet newtableview=pst.executeQuery();
		    	
		    int jasoos=0;
		    ObservableList<String>all=floorsAvail.getItems();
		    floorsAvail.getItems().removeAll(all);
		    ObservableList<String>all1=slotsAvail.getItems();
		    slotsAvail.getItems().removeAll(all1);
		   while(newtableview.next())
		   { 
			jasoos=1;
			String s=newtableview.getString("Slot");
			String f=newtableview.getString("Floor");
			System.out.print("floor-"+f+" ");
	    	floorsAvail.getItems().addAll(f);
	    	System.out.println("slot-"+s);
	     	slotsAvail.getItems().addAll(s);
		    }
		if(jasoos==0)
		{
			Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("----No vaccany for this type-----");
        	al.show();
        	txtvehicleFloor.clear();
        	txtvehicleSlot.clear();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void show_multiple_wheeler(ActionEvent event) {
    	checkavail.setDisable(false);
    	typesvar="12W";
    	Connection con=connection_classs.doConnect();
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("select Floor,Slot from slots_database where VehicleParked=? and Type=?");
			pst.setString(1, "");
			pst.setString(2, typesvar);
		    ResultSet newtableview=pst.executeQuery();
		    	
		    int jasoos=0;
		    ObservableList<String>all=floorsAvail.getItems();
		    floorsAvail.getItems().removeAll(all);
		    ObservableList<String>all1=slotsAvail.getItems();
		    slotsAvail.getItems().removeAll(all1);
		   while(newtableview.next())
		   { 
			jasoos=1;
			String s=newtableview.getString("Slot");
			String f=newtableview.getString("Floor");
			System.out.print("floor-"+f+" ");
	    	floorsAvail.getItems().addAll(f);
	    	System.out.println("slot-"+s);
	     	slotsAvail.getItems().addAll(s);
		    }
		if(jasoos==0)
		{
			Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("----No vaccany for this type-----");
        	al.show();
        	txtvehicleFloor.clear();
        	txtvehicleSlot.clear();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void show_two_wheeler(ActionEvent event) {
    	checkavail.setDisable(false);
    	typesvar="2W";
    	Connection con=connection_classs.doConnect();
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("select Floor,Slot from slots_database where VehicleParked=? and Type=?");
			pst.setString(1, "");
			pst.setString(2, typesvar);
		    ResultSet newtableview=pst.executeQuery();
		    	
		    int jasoos=0;
		    ObservableList<String>all=floorsAvail.getItems();
		    floorsAvail.getItems().removeAll(all);
		    ObservableList<String>all1=slotsAvail.getItems();
		    slotsAvail.getItems().removeAll(all1);
		   while(newtableview.next())
		   { 
			jasoos=1;
			String s=newtableview.getString("Slot");
			String f=newtableview.getString("Floor");
			System.out.print("floor-"+f+" ");
	    	floorsAvail.getItems().addAll(f);
	    	System.out.println("slot-"+s);
	     	slotsAvail.getItems().addAll(s);
		    }
		if(jasoos==0)
		{
			Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("----No vaccany for this type-----");
        	al.show();
        	txtvehicleFloor.clear();
        	txtvehicleSlot.clear();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert multipleWL != null : "fx:id=\"multipleWL\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert vehicletype != null : "fx:id=\"vehicletype\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert fourWL != null : "fx:id=\"fourWL\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert twoWL != null : "fx:id=\"twoWL\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert floorsAvail != null : "fx:id=\"floorsAvail\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert slotsAvail != null : "fx:id=\"slotsAvail\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert txtvehicleFloor != null : "fx:id=\"txtvehicleFloor\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert txtvehicleSlot != null : "fx:id=\"txtvehicleSlot\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert txtvehicleNo != null : "fx:id=\"txtvehicleNo\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'vehicledetailsview.fxml'.";
        checkavail.setDisable(true);
    }
}
