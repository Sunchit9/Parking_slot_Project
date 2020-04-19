package system_tables;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class systemtablesviewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doback(ActionEvent event) {
    	

    }
    
    URL url;
   	Media media;
   	MediaPlayer mediaplayer;
   	AudioClip audio;
       void playSong()
       {
       	
       	url=getClass().getResource("bg.mp3");
   		media=new Media(url.toString());
   		mediaplayer=new MediaPlayer(media);
   		mediaplayer.play();
   		
       }
       void playSound(){
       	url=getClass().getResource("gun-reload-1.wav");
   		audio=new AudioClip(url.toString());
   		audio.play();
       }
    @FXML
    void showINC(MouseEvent event) {
    	Parent root;
		try {
			  playSound();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("income_details/incomeview.fxml"));
			Scene scene = new Scene(root,600,400);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void showa(MouseEvent event) {
    	Parent root;
		try {
			  playSound();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("parking_detail_table/parkingtableview.fxml"));
			Scene scene = new Scene(root,600,400);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

    }

    @FXML
    void showb(MouseEvent event) {
    	
    }

    @FXML
    void showc(MouseEvent event) {
    	Parent root;
		try {
			  playSound();

			root = FXMLLoader.load(getClass().getClassLoader().getResource("floor_details_table/floordetailsview.fxml"));
			Scene scene = new Scene(root,600,400);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void showcustomer(MouseEvent event) {
    	Parent root;
		try {
			  playSound();

			root = FXMLLoader.load(getClass().getClassLoader().getResource("customer_details_tables/customertableview.fxml"));
			Scene scene = new Scene(root,620,430);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void showd(MouseEvent event) {

    }

    @FXML
    void showe(MouseEvent event) {

    	showINC(null);
    }

    @FXML
    void showfloor(MouseEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("floor_details_table/floordatabaseview.fxml"));
			  playSound();

			Scene scene = new Scene(root,500,400);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void showparking(MouseEvent event) {
		  playSound();
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("parking_detail_table/parkingtableview.fxml"));
			Scene scene = new Scene(root,870,520);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    @FXML
    void showslot(MouseEvent event) {
    	Parent root;
		  playSound();
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("slot_details_table/slotdetailsview.fxml"));
			Scene scene = new Scene(root,600,400);
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {

    }
}
