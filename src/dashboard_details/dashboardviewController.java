package dashboard_details;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class dashboardviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    
    URL url;
   	Media media;
   	MediaPlayer mediaplayer;
   	AudioClip audio;
       void playSong()
       {
       	
       	url=getClass().getResource("Presentation Background Music  Uplifting Music instrumental by AShamaluevMusic.mp3");
   		media=new Media(url.toString());
   		mediaplayer=new MediaPlayer(media);
   		mediaplayer.play();
   		
       }
       void playSound(){
       	url=getClass().getResource("gun-reload-1.wav");
   		audio=new AudioClip(url.toString());
   		audio.play();
       }
    	
    //	playSong();
    

    @FXML
    void docustomershow(MouseEvent event) {
    	try {
    		playSound();
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customer_registratioin/customerdetailsview.fxml")); 
			Scene scene = new Scene(root,620,360);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dofloorshow(MouseEvent event) {
    	try {
    		playSound();
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("floor_details/floordetailsview.fxml")); 
			Scene scene = new Scene(root,620,350);	
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dotablehow(MouseEvent event) {
    	Parent root;
		try {
			playSound();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("system_tables/systemtablesview.fxml"));
			Scene scene = new Scene(root,600,450);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		

    }

    @FXML
    void dovehicleentry(MouseEvent event) {
    	try {
    		playSound();
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("vehicle_details/vehicledetailsview.fxml")); 
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

    @FXML
    void dovehicleexit(MouseEvent event) {
    	try {
    		playSound();
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("vehicle_out_details/vehicle_exitview.fxml")); 
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

    @FXML
    void dox(MouseEvent event) {
    	try {
    		playSound();
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customer_registratioin/customerdetailsview.fxml")); 
			Scene scene = new Scene(root,620,360);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    

    @FXML
    void doy(MouseEvent event) {
    	try {
    		playSound();
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("floor_details/floordetailsview.fxml")); 
			Scene scene = new Scene(root,620,350);	
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
playSong();
    }
}
