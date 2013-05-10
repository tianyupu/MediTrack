package org.uwhealthkids.MediTrack;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.parse.ParseFile;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class UploadProfilePicActivity extends Activity {
	private static final int SELECT_PHOTO = 100;
	protected ParseFile imageFile = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_pic);
	}

	public void selectPic(View v) { 
		Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
		startActivityForResult(intent, SELECT_PHOTO);
	}
	
	public void uploadPic(View v) {
		if (imageFile != null) {
			ParseObject currBaby = CustomApplication.getInstance().getCurrBaby();
			currBaby.put("baby_pic", imageFile);
			currBaby.saveEventually();
			finish();
		}
		else {
			Toast.makeText(CustomApplication.getInstance(), "Please select a photo to upload", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

		switch (requestCode) { 
		case SELECT_PHOTO:
			if (resultCode == RESULT_OK) {  
				Uri selectedImage = imageReturnedIntent.getData();
				InputStream imageStream1;
				InputStream imageStream;
				byte[] inputData;

				try {
					imageStream = getContentResolver().openInputStream(selectedImage);		
					inputData = getBytes(imageStream);
					imageFile = new ParseFile("babypic.jpg", inputData);
					imageFile.saveInBackground();

					imageStream1 = getContentResolver().openInputStream(selectedImage);		
					Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream1);
					ImageView imageShowing = (ImageView)findViewById(R.id.baby_pic_preview);
					imageShowing.setImageBitmap(yourSelectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
	public byte[] getBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}
		return byteBuffer.toByteArray();
	}

}
