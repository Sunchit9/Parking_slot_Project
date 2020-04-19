package customer_registratioin;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class customerdetailsviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private ImageView imgID;

    @FXML
    private TextField txtCity;

    @FXML
    private ComboBox<String> txtMobile;

    @FXML
    private TextArea txtAddress;
    
    @FXML
    private Label notification;

    String filename=null;

    @FXML
    void doBrowse(ActionEvent event) {
    	FileChooser file=new FileChooser();
    	File selected=file.showOpenDialog(null);
    	if(selected!=null)
    	{
    		filename=selected.getAbsolutePath();
    		Image imageicon=new Image(selected.toURI().toString());
    		imgID.setImage(imageicon);
    	}
    	else
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Menu");
        	al.setContentText("Please Choose an Image");
        	al.show();
    	}
    }

    @FXML
    void doDelete(ActionEvent event) {
    	if(!txtMobile.getSelectionModel().getSelectedItem().equals(""))
    	{
    	Connection con=connection_classs.doConnect();
		try 
		{
			PreparedStatement	pst = con.prepareStatement("delete from customer_database where MobileNo=?");
			pst.setString(1, txtMobile.getSelectionModel().getSelectedItem());
			pst.executeUpdate();
			notification.setText("Data Deleted");
			filename=null;
			txtName.clear();
			txtCity.clear();
			txtAddress.clear();
			imgID.setImage(null);
		}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
    	else
    	{
    		
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Enter Valid Mobile_No-----");
            	al.show();
    	}
    	filename=null;
		txtName.clear();
		txtCity.clear();
		txtAddress.clear();
		imgID.setImage(null);
    	
    }

    @FXML
    void doFetch(ActionEvent event) {
    	if(!txtMobile.getSelectionModel().getSelectedItem().equals(""))
    	{
    		Connection con=connection_classs.doConnect();
    		try{
    			PreparedStatement	pst = con.prepareStatement("select* from customer_database where MobileNo=?");
    			pst.setString(1, txtMobile.getSelectionModel().getSelectedItem());
    			ResultSet tableview= pst.executeQuery();	
    			int jasoos=1;
    			while(tableview.next())
    			{
    				jasoos=0;
    			txtName.setText(tableview.getString("Customer_Name"));
    			txtCity.setText(tableview.getString("City"));
    			txtAddress.setText(tableview.getString("Address"));
    			String filename=tableview.getString("Image");
				Image img=new Image(new FileInputStream(filename));
				imgID.setImage(img);    			 }
    			if(jasoos==1)
    				{
    				Alert al=new Alert(AlertType.INFORMATION);
                	al.setTitle("Error 404");
                	al.setContentText("----Record not Found-----");
                	al.show();
                	filename=null;
        			txtName.clear();
        			txtCity.clear();
        			txtAddress.clear();
        			imgID.setImage(null);
    				}
    			}
    		catch(Exception ex)
    			{
    				ex.printStackTrace();
    			}}
    	else
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("----Enter Valid Mobile_No-----");
        	al.show();
        	filename=null;
			txtName.clear();
			txtCity.clear();
			txtAddress.clear();
			imgID.setImage(null);
    	}
    }
    @FXML
    void doSave(ActionEvent event) {    	
    	
    	if(filename!=null&&!txtMobile.getSelectionModel().getSelectedItem().equals("")&&(!txtName.getText().equals(""))&& (!txtAddress.getText().equals(""))&&(!txtCity.getText().equals("")) )
    	{
    	Connection con=connection_classs.doConnect();
		try 
		{
			PreparedStatement	pst = con.prepareStatement("insert into customer_database values(?,?,?,?,?)");
			pst.setString(1,txtName.getText());
			pst.setString(2, txtMobile.getSelectionModel().getSelectedItem());
			pst.setString(3, txtAddress.getText());
			pst.setString(4, txtCity.getText());
			pst.setString(5, filename);
			pst.executeUpdate();
			notification.setText("Data Saved");
			filename=null;
			txtName.clear();
			txtCity.clear();
			txtAddress.clear();
			imgID.setImage(null);
			
		}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
    	else
    	{
    		if(txtMobile.getSelectionModel().getSelectedItem().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Enter Valid Mobile_No-----");
            	al.show();
    		}
    		else if(txtName.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Enter Valid Name-----");
            	al.show();
    		}
    		else if(txtCity.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Please Enter Valid City-----");
            	al.show();
    		}
    		else if(txtAddress.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Please Enter valid Address-----");
            	al.show();	
    		}
    		else
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
	        	al.setTitle("----");
	        	al.setContentText("Please Enter a Valid PHOTO PROOF");
	        	al.show();
    		}
    	}
    }
    @FXML
    void dofetch2(ActionEvent event) {
    	if(txtMobile.getSelectionModel().getSelectedItem()!=null)
    	{
    		Connection con=connection_classs.doConnect();
    		try{
    			PreparedStatement	pst = con.prepareStatement("select* from customer_database where MobileNo=?");
    			pst.setString(1, txtMobile.getSelectionModel().getSelectedItem());
    			ResultSet tableview= pst.executeQuery();	
    			int jasoos=1;
    			while(tableview.next())
    			{
    				jasoos=0;
    			txtName.setText(tableview.getString("Customer_Name"));
    			txtCity.setText(tableview.getString("City"));
    			txtAddress.setText(tableview.getString("Address"));
    			filename=tableview.getString("Image");
				Image img=new Image(new FileInputStream(filename));
				imgID.setImage(img);
    			 }
    			if(jasoos==1)
    				{
    				Alert al=new Alert(AlertType.INFORMATION);
                	al.setTitle("Error 404");
                	al.setContentText("----Record not Found-----");
                	al.show();
                	filename=null;
        			txtName.clear();
        			txtCity.clear();
        			txtAddress.clear();
        			imgID.setImage(null);
    				}
    			}
    		catch(Exception ex)
    			{
    				ex.printStackTrace();
    			}}
    	else
    	{
    		Alert al=new Alert(AlertType.INFORMATION);
        	al.setTitle("Error 404");
        	al.setContentText("----Enter Valid Mobile_No-----");
        	al.show();
        	filename=null;
			txtName.clear();
			txtCity.clear();
			txtAddress.clear();
			imgID.setImage(null);
    	}
    }
    @FXML
    void doUpdate(ActionEvent event) {
    	
    	if(!txtMobile.getSelectionModel().getSelectedItem().equals("")&&(!txtName.getText().equals(""))&& (!txtAddress.getText().equals(""))&&(!txtCity.getText().equals("")) )
    	{
    	Connection con=connection_classs.doConnect();
		try 
		{
			PreparedStatement	pst = con.prepareStatement("update customer_database set Customer_Name=?,MobileNo=?,Address=?,City=?,Image=? where MobileNo=?");
			pst.setString(1,txtName.getText());
			pst.setString(2, txtMobile.getSelectionModel().getSelectedItem());
			pst.setString(3, txtAddress.getText());
			pst.setString(4, txtCity.getText());
			pst.setString(5, filename);
			pst.setString(6, txtMobile.getSelectionModel().getSelectedItem());
			pst.executeUpdate();
			notification.setText("Data Updated");
			filename=null;
			txtName.clear();
			txtCity.clear();
			txtAddress.clear();
			imgID.setImage(null);
		}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
    	else
    	{
    		if(txtMobile.getSelectionModel().getSelectedItem().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Enter Valid Mobile_No-----");
            	al.show();
    		}
    		else if(txtName.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Enter Valid Name-----");
            	al.show();
    		}
    		else if(txtCity.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Please Enter Valid City-----");
            	al.show();
    		}
    		else if(txtAddress.getText().equals(""))
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Please Enter valid Address-----");
            	al.show();	
    		}
    		else
    		{
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Error 404");
            	al.setContentText("----Please upload a photo-----");
            	al.show();	
    		}
    	}

    }

    @FXML
    void dodone(ActionEvent event) {

    }

    @FXML
    void doprev(ActionEvent event) {

    }

    void initialss()
    {
    	 Connection con=connection_classs.doConnect();
      	try{
      		ArrayList<String> floorsAry=new ArrayList<>();
          PreparedStatement	pst=con.prepareStatement("select MobileNo from parking_database");
        	ResultSet tableview= pst.executeQuery();
        	while(tableview.next())
        		{
        			String m=tableview.getString("MobileNo");
        			floorsAry.add(String.valueOf(m));
        		}
		  	txtMobile.getItems().addAll(floorsAry);	
      	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    }
    @FXML
    void initialize() {
       initialss();
   }
}