package util;

import cnode.CNode;
import view.AnimationController;

import java.util.Random;

import javafx.scene.paint.Color;

public class RandomCNodes {

	  public RandomCNodes() {
	
	  }
	
	  public static CNode[] randomCNodes(int n) {
		  CNode[] arr = new CNode[n]; 
		  Random r = new Random();
	
		  for (int i = 0; i < arr.length; i++) {
			  arr[i] = new CNode(1+ r.nextInt(arr.length)); //node sẽ có độ cao là từ 1 cho đến arr.length
		      arr[i].setX((i) * (AnimationController.WINDOW_WIDTH / arr.length));//xác định vị trí trục X của node
		      arr[i].setY(420-((AnimationController.WINDOW_HEIGHT-AnimationController.BUTTONROW_BOUNDARY)/n)*arr[i].getValue());
		      arr[i].setFill(Color.GREEN);
		      setCNodeDim(arr[i], arr.length);
		  }
		  return arr;
	 
	  }
	
	  private static void setCNodeDim(CNode cnode, int n) {
		  cnode.setWidth(AnimationController.WINDOW_WIDTH / n*0.95); //tính chiều rộng của node
		    cnode.setHeight(((AnimationController.WINDOW_HEIGHT - AnimationController.BUTTONROW_BOUNDARY) 
		                      / n) * cnode.getValue()); //tính chiều cao của node
	  }
}
