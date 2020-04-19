package vehicle_out_details;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import connection_file.connection_classs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import vehicle_details.SST_SMS;

public class vehicle_exitviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtVehicleNo;

    @FXML
    private TextField txtFloor;

    @FXML
    private TextField txtSlot;

    @FXML
    private TextField txtParkedTime;

    @FXML
    private TextField txtParkedDate;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtCurrentTime;

    @FXML
    private TextField txtCurrentDate;

    @FXML
    private TextField txtBill;
    
    @FXML
    private TextField txtMobile;

    @FXML
    private Label notification;

    double bill=0;
    @FXML
    void doBill(ActionEvent event) {
    	try{
    		if((!txtParkedDate.equals(""))&&(!txtParkedTime.equals(""))&&(!txtCurrentDate.equals(""))&&(!txtCurrentTime.equals("")))
    	
    	{
    		String PD=txtParkedDate.getText();
    		String PT=txtParkedTime.getText();
    		String ED=txtCurrentDate.getText();
    		String ET=txtCurrentTime.getText();
			System.out.println(PD+" "+PT+" "+ED+" "+ET);
			int InTimeh=Integer.parseInt(PT.substring(0,2));
			System.out.println(InTimeh);
			int OutTimeh=Integer.parseInt(ET.substring(0,2));
			System.out.println(OutTimeh);
			int InTimem=Integer.parseInt(PT.substring(3,5));
			System.out.println(InTimem);
			int OutTimem=Integer.parseInt(ET.substring(3,5));
			System.out.println(OutTimem);
			int InDatey=Integer.parseInt(PD.substring(0,4));
			System.out.println(InDatey);
			int OutDatey=Integer.parseInt(ED.substring(0,4));
			System.out.println(OutDatey);
			int InDatem=Integer.parseInt(PD.substring(5,7));
			System.out.println(InDatem);
			int OutDatem=Integer.parseInt(ED.substring(5,7));
			System.out.println(OutDatem);
			int InDated=Integer.parseInt(PD.substring(8,10));
			System.out.println(InDated);
			int OutDated=Integer.parseInt(ED.substring(8,10));
			System.out.println(OutDated);
			System.out.println(InTimeh+":"+InTimem+" "+", "+InDatey+"-"+InDatem+"-"+InDated+"\t"+OutTimeh+":"+OutTimem+" "+", "+OutDatey+"-"+OutDatem+"-"+OutDated);
		    int h=(OutTimeh-InTimeh);
			int m=(OutTimem-InTimem);
			int d=(OutDated-InDated);
			int mo=(OutDatem-InDatem);
			int y=(OutDatey-InDatey);
			if(h<0)
				h=24+h;
			if(m>0)
				h++;
			if(d!=0)
				h=h+d*24;
			if(mo!=0)
				h=h+m*30*24;
			if(txtType.getText().equals("2W"))
				bill=h*5;
			else if(txtType.getText().equals("4W"))
				bill=h*10;
			if(txtType.getText().equals("12W"))
				bill=h*15;
			if(m>0)
				h--;
			if(m<0)
			{
				h--;
				m=60+m;
			}
			    notification.setText(y+" Years "+mo+" Months "+d+" Days "+h+ " Hours "+m+" Minutes");
			    txtBill.setText(String.valueOf(bill));
			    String ms="Your Parking Charges:"+bill+", Thanks for Visit!";	
		    	String resp=SST_SMS.bceSunSoftSend(txtMobile.getText(), ms);
		    	System.out.println(resp);
		    	
		    	if(resp.indexOf("Exception")!=-1)
		    		System.out.println("Check Internet Connection");
		    	
		    	else
		    		if(resp.indexOf("successfully")!=-1)
		        		System.out.println("Sent");
		    		else
		    		System.out.println( "Invalid Number");
        	    Connection con=connection_classs.doConnect();
			    PreparedStatement pst =con.prepareStatement("Update parking_database set ExitDate=?,ExitTime=?,Charges=? where VehicleNo=? && Charges=?");
			    pst.setDate(1,java.sql.Date.valueOf(LocalDate.now()));
			    pst.setTime(2,java.sql.Time.valueOf(LocalTime.now()));
			    pst.setFloat(3,(float) bill);
			    pst.setString(4, txtVehicleNo.getText());
			    pst.setDouble(5,0.00);
	    		pst.executeUpdate();
    			PreparedStatement pstinside =con.prepareStatement("Update slots_database set VehicleParked=? where VehicleParked=?");
				pstinside.setString(1,"");
    			pstinside.setString(2,txtVehicleNo.getText());
				pstinside.executeUpdate();
				PreparedStatement pstinside1 =con.prepareStatement("select slots_occupied from floor_database where Floor=?");
				pstinside1.setString(1,txtFloor.getText());
				ResultSet newtableview=pstinside1.executeQuery();
				int S=0;
				while(newtableview.next())
				{
					S=Integer.parseInt(newtableview.getString("Slots_Occupied"));
				}
				PreparedStatement pstinside3 =con.prepareStatement("Update floor_database set slots_occupied=? where Floor=?");
				pstinside3.setString(1,String.valueOf(S-1));
				pstinside3.setString(2,txtFloor.getText());
				pstinside3.executeUpdate();
    	}
    	else
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("Please Enter a Valid Date and Time");
        	al.show();
    	}}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    @FXML
    void doFetch(ActionEvent event) {
    	try{
        	Connection con=connection_classs.doConnect();
        	if(txtVehicleNo.getText().equals(""))
        	{
        		Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("Please Enter a Valid Vehicle No.");
            	al.show();
        	}
        	else
        	{
        	 PreparedStatement pst =con.prepareStatement("select Type,FloorAlloted,SlotAlloted,EntryDate,EntryTime,MobileNo from parking_database where VehicleNo=? && Charges=?");
			 pst.setString(1, txtVehicleNo.getText());
			 pst.setDouble(2,0.00);
			 ResultSet newtableview=pst.executeQuery();
			int jasoos=1;
			while(newtableview.next())
			{
				jasoos=0;
				txtFloor.setText(newtableview.getString("FloorAlloted"));
				txtSlot.setText(newtableview.getString("SlotAlloted"));
				txtType.setText(newtableview.getString("Type"));
				txtParkedDate.setText(newtableview.getString("EntryDate"));
				txtParkedTime.setText(newtableview.getString("EntryTime"));
				txtMobile.setText(newtableview.getString("MobileNo"));
				String PD=newtableview.getString("EntryDate");
				String PT=newtableview.getString("EntryTime");
				String ED=String.valueOf(java.sql.Date.valueOf(LocalDate.now()));
				String ET=String.valueOf(java.sql.Time.valueOf(LocalTime.now()));
				txtCurrentDate.setText(ED);
				txtCurrentTime.setText(ET);
				txtBill.clear();
				System.out.println(PD+" "+PT+" "+ED+" "+ET);
			}
			if(jasoos==1)
			{
				 Alert al=new Alert(AlertType.INFORMATION);
	            	al.setTitle("Error 404");
	            	al.setContentText("Record Not Found FOR THIS VEHICLE NO");
					notification.setText("Record Not Found");
	            	al.show();
	            	txtParkedDate.clear();
	            	txtParkedTime.clear();
	            	txtCurrentDate.clear();
	            	txtCurrentTime.clear();
	            	txtType.clear();
	            	txtFloor.clear();
	            	txtSlot.clear();
			}
			else
			{
				notification.setText("Record Found");
			}
        	}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void initialize() {
        assert txtVehicleNo != null : "fx:id=\"txtVehicleNo\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtFloor != null : "fx:id=\"txtFloor\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtSlot != null : "fx:id=\"txtSlot\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtParkedTime != null : "fx:id=\"txtParkedTime\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtParkedDate != null : "fx:id=\"txtParkedDate\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtType != null : "fx:id=\"txtType\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtCurrentTime != null : "fx:id=\"txtCurrentTime\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtCurrentDate != null : "fx:id=\"txtCurrentDate\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert txtBill != null : "fx:id=\"txtBill\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
        assert notification != null : "fx:id=\"notification\" was not injected: check your FXML file 'vehicle_exitview.fxml'.";
    }
}
