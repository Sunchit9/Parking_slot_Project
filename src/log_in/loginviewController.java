package log_in;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connection_file.connection_classs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class loginviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Uid;

    @FXML
    private PasswordField Pwd;

    @FXML
    private Label notification;

    @FXML
    void dodone(ActionEvent event) {
    	System.exit(1);
    }

    @FXML
    void dosubmit(ActionEvent event) {
    	if((!Uid.getText().equals(""))&&(!Pwd.getText().equals("")))
    	{
Connection con=connection_classs.doConnect();
    		
			try{
			PreparedStatement pst =con.prepareStatement("select * from login_database where Username=?");
			pst.setString(1,Uid.getText());
			ResultSet table= pst.executeQuery();
			int jasoos=0;
			String pwddd="";
        		while(table.next())
        		{
        			jasoos=1;
        		    pwddd=table.getString("Password");
        		}
        		if(jasoos==0)
        		{
        			Alert al=new Alert(AlertType.INFORMATION);
                	al.setTitle("Error 404");
                	al.setContentText("This Username Doesn't Exist");
                	al.show();
        		}
        		else
        		{
        			if(Pwd.getText().equals(pwddd))
        			{
        				Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard_details/dashboardview.fxml")); 
        				Scene scene = new Scene(root,780,650);
        				Stage primaryStage=new Stage();
        				primaryStage.setScene(scene);
        				primaryStage.show();
        				 Scene scene1=(Scene)Uid.getScene();
        				   scene1.getWindow().hide();
        			}
        			else
        			{
        				notification.setText("Incorrect Password");
        			}
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    	}
    	else
    	{
    		if(Uid.getText().equals(""))
    		{
    			notification.setText("Please enter Username" );
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Menu");
            	al.setContentText("Please Enter Username");
            	al.show();
    		}
    		else
    		{
    			notification.setText("Please enter Password" );
    			Alert al=new Alert(AlertType.INFORMATION);
            	al.setTitle("Menu");
            	al.setContentText("Please enetr Password");
            	al.show();
    		}
    	}

    }

    @FXML
    void initialize() {
        assert Uid != null : "fx:id=\"Uid\" was not injected: check your FXML file 'loginview.fxml'.";
        assert Pwd != null : "fx:id=\"Pwd\" was not injected: check your FXML file 'loginview.fxml'.";
        assert notification != null : "fx:id=\"notification\" was not injected: check your FXML file 'loginview.fxml'.";

    }
}
