package org.witness.sscphase1;

import java.io.Serializable;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class ImageRegion extends FrameLayout implements OnTouchListener, OnClickListener, Serializable {

	private static final long serialVersionUID = -244965540057504061L;

	float startX;
	float startY;
	float endX;
	float endY;
	
	int imageWidth;
	int imageHeight;
	
	int index;
	
	public static final int EDIT_MODE = 0;
	public static final int NORMAL_MODE = 1;
	int mode = EDIT_MODE;
	
	public static final String SSC = "[Camera Obscura : ImageRegion] **************************** ";
			
	//public ImageRegion(Context context, String jsonVersion) {
		// Implement this from JSON
		//this(context, _scaledStartX, _scaledStartY, _scaledEndX, _scaledEndY, _scaledImageWidth, _scaledImageHeight, _imageWidth, _imageHeight, _backgroundColor);	
	//}
	
	Button leftCorner;
	Button rightCorner;
		
	public ImageRegion(
			Context context, 
			int _scaledStartX, int _scaledStartY, 
			int _scaledEndX, int _scaledEndY, 
			int _scaledImageWidth, int _scaledImageHeight, 
			int _imageWidth, int _imageHeight, 
			int _backgroundColor,
			int _index) 
	{
		super(context);
		
		/*
		original 300
		current 100
		scaled x 20
		real x 60
		original/current * scaled = real
		
		scaled = real * current/original
		*/
		
		startX = (float)_imageWidth/(float)_scaledImageWidth * (float)_scaledStartX;
		startY = (float)_imageHeight/(float)_scaledImageHeight * (float)_scaledStartY;
		endX = (float)_imageWidth/(float)_scaledImageWidth * (float)_scaledEndX;
		endY = (float)_imageHeight/(float)_scaledImageHeight * (float)_scaledEndY;
				
		imageWidth = _imageWidth;
		imageHeight = _imageHeight;
		index = _index;
		
		setBackgroundColor(_backgroundColor);
	
		// FIgure out how to do layout
		///this.setLayout(R.layout.imageregion);
		// Implement buttons/whatever
		//rightCorner = (Button) 
		
	}
	
	public void changeMode(int newMode) {
		mode = newMode;
		if (mode == EDIT_MODE) {
			leftCorner.setVisibility(View.VISIBLE);
			rightCorner.setVisibility(View.VISIBLE);
		} else {
			leftCorner.setVisibility(View.GONE);
			rightCorner.setVisibility(View.GONE);
		}
	}
	
	public Rect getScaledRect(int _scaledImageWidth, int _scaledImageHeight) {
		
		float scaledStartX = (float)startX * (float)_scaledImageWidth/(float)imageWidth;
		float scaledStartY = (float)startY * (float)_scaledImageHeight/(float)imageHeight;
		float scaledEndX = (float)endX * (float)_scaledImageWidth/(float)imageWidth;
		float scaledEndY = (float)endY * (float)_scaledImageHeight/(float)imageHeight;

		return new Rect((int)scaledStartX, (int)scaledStartY, (int)scaledEndX, (int)scaledEndY);
	}
	
	/* Is this being used??  If not, we don't need index */
	/* it is being used! (HNH 3/19/11) */
	/* Need to make a unique id that isn't passed in, hash the data */
	//http://download.oracle.com/javase/1.5.0/docs/api/java/util/UUID.html
	//http://code.google.com/p/google-gson/
	//http://javaexchange.com/aboutRandomGUID.html
	//http://benjii.me/2010/04/deserializing-json-in-android-using-gson/
	public String attachTags() {
	   	/*
    	 * this method adds the returned coordinates to our array of ROIs
    	 * and creates a JSON String for identifying it permanently
    	 */
		float[] tagCoords = {startX,startY,endX,endY};
    	String newTagCoordsDescription = "{\"id\":" + index + ",\"coords\":[";
    	for(int x=0;x<4;x++) {
    		newTagCoordsDescription += Float.toString(tagCoords[x]) + ",";
    	}
    	newTagCoordsDescription = newTagCoordsDescription.substring(0,newTagCoordsDescription.length() - 1) + "]}";
    	return newTagCoordsDescription;
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}