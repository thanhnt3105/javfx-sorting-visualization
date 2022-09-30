package view;

import cnode.CNode;
import util.RandomCNodes;
import sortingalgorithms.*;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationController extends BorderPane {
	
	  public static final int WINDOW_WIDTH = 800;
	  public static final int WINDOW_HEIGHT = 500;
	  public static final int BUTTONROW_BOUNDARY = 100; 
	  public static int SPEED=500; //tốc độ sort

	  public static int NO_OF_CNODES = 40; 
	
	  private static AbstractSort abstractSort;
	  
	  private Pane display;
	  private HBox buttonRow;
	  private HBox labelBox;
	
	  private Button sortButton;
	  private Button randomButton;
	  private Button pauseButton;
	  private Button newArrButton;
	  private Slider slider;
	  
	  private ChoiceBox<AbstractSort> choiceBox;
	  SequentialTransition st;
	  boolean running = false;
	  private CNode[] cnodes;
	  private ArrayList<Label> labels;

	  public AnimationController() {
		  	
		    this.display = new Pane();
		    this.buttonRow = new HBox();
		    this.labelBox = new HBox();
		
		    this.setCenter(display);
		    this.setBottom(buttonRow);
		
		    this.slider = new Slider();
		    slider.setMin(0.1);	   
	        
		    slider.setMax(10);	       

		    slider.setValue(2);
		       
		    slider.setShowTickLabels(true);
		    slider.setShowTickMarks(true);
		       
		    slider.setBlockIncrement(0.1);
		    this.newArrButton = new Button("Input Array");
		    this.pauseButton = new Button("Pause");
		    this.sortButton = new Button("Sort");
		    this.randomButton = new Button("New");
		    this.choiceBox = new ChoiceBox<>();
		    
		    this.cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);
		    this.labels = RandomCNodes.randomLabels(NO_OF_CNODES);
		    for(int i=0;i<NO_OF_CNODES;i++) {
		    	labels.get(i).setText(cnodes[i].toString());
		    }
		    
		    labelBox.getChildren().addAll(labels);
		    
		    buttonRow.getChildren().add(slider);
		    buttonRow.getChildren().add(newArrButton);
		    buttonRow.getChildren().add(pauseButton);
		    buttonRow.getChildren().add(sortButton);
		    buttonRow.getChildren().add(randomButton);
		    buttonRow.getChildren().add(choiceBox);
		
		    
		    buttonRow.setAlignment(Pos.CENTER);
		    
		    for (Node b : buttonRow.getChildren()) {
		    	HBox.setMargin(b, new Insets(5, 5, 20, 5));
		    }	
		    HBox.setMargin(slider, new Insets(5, 5, 0, 5));
		
		    List<AbstractSort> abstractSortList = new ArrayList<>();
		    abstractSortList.add(new BubbleSort());
		    abstractSortList.add(new SelectionSort());
		    abstractSortList.add(new InsertionSort());
		    abstractSortList.add(new MergeSort());
		    abstractSortList.add(new QuickSort());
		    abstractSortList.add(new HeapSort());
		
		    display.getChildren().addAll(Arrays.asList(cnodes));
		    display.getChildren().add(labelBox);
		    
		    sortButton.setOnAction(event -> {
		    	sortButton.setDisable(true);
		    	randomButton.setDisable(true);
		    	newArrButton.setDisable(true);
		    	pauseButton.setDisable(false);
		    	running = true;
		
		    	abstractSort = choiceBox.getSelectionModel().getSelectedItem();	
		    	st = new SequentialTransition();	
		    	st.getChildren().addAll(abstractSort.startSort(cnodes));
		    	
		    	st.rateProperty().bind(slider.valueProperty());
		    			    	
		    	
		    	st.setOnFinished(e -> {
		    		randomButton.setDisable(false);
		    		pauseButton.setDisable(true);
		    		newArrButton.setDisable(false); 	
		    		running = false;
		    		st.pause();
		    	});   
		    	st.play();	
		    	
		    		
		    });
		    
		   pauseButton.setOnAction(event ->{;
		        if (running) {
		            st.pause();
		            pauseButton.setText("Continue");
		            running = false;
		           
		            
		        }
		        else {
		            st.play();
		            pauseButton.setText("Pause");
		            running = true;
		            
		        }
		    });
		
		    randomButton.setOnAction(event -> {
		    	sortButton.setDisable(false);
		    	display.getChildren().clear();
		
		    	cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);
		
		    	display.getChildren().addAll(Arrays.asList(cnodes));
		    });
		    		    
		    
		    
		       
		       
		
		    choiceBox.setItems(FXCollections.observableArrayList(
		    	abstractSortList
		    ));
		
		    choiceBox.getSelectionModel().select(5);
		
		    choiceBox.setConverter(new StringConverter<AbstractSort>() {
		    	@Override
		    	public String toString(AbstractSort abstractSort) {
		    		if(abstractSort == null) {
		    			return "";
		    		} else {
		    			return abstractSort.getClass().getSimpleName();
		    		}
		    	}	
		    	@Override
		    	public AbstractSort fromString(String s) {
		    		return null;
		    	}
		    });
		
	 }

}
