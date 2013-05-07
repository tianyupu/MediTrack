package org.uwhealthkids.MediTrack;


import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;

/**
 * GraphView creates a scaled line or bar graph with x and y axis labels. 
 *
 */
public class GraphView extends View {

	public static boolean BAR = true;
	public static boolean LINE = false;
	private Paint paint;
	private List<Float> valuesOne;
	private List<Float> valuesTwo;
	private String[] horlabels;
	private String[] verlabels;
	private String title;
	private boolean type;

	public GraphView(Context gView, List<Float> valuesOne, List<Float> valuesTwo,
			String title, String[] horlabels, String[] verlabels, boolean type) {
		super(gView);
		this.valuesOne = valuesOne;
		this.valuesTwo = valuesTwo;		
		if (title == null)
			title = "";
		else
			this.title = title;
		if (horlabels == null)
			this.horlabels = new String[0];
		else
			this.horlabels = horlabels;
		if (verlabels == null)
			this.verlabels = new String[0];
		else
			this.verlabels = verlabels;
		this.type = type;
		paint = new Paint();
	}
	
	public GraphView(Context gView, List<Float> values,  
			String title, String[] horlabels, String[] verlabels, boolean type) {
		super(gView);
		this.valuesOne = values;
		
		if (title == null)
			title = "";
		else
			this.title = title;
		if (horlabels == null)
			this.horlabels = new String[0];
		else
			this.horlabels = horlabels;
		if (verlabels == null)
			this.verlabels = new String[0];
		else
			this.verlabels = verlabels;
		this.type = type;
		paint = new Paint();
	}
	
	//method that does the actual drawing on the canvas
	//
	//PARAMETERS: canvas where the graph will be drawn
	@Override
	protected void onDraw(Canvas canvas) {
		float border = 20;
		float horstart = border * 2;
		float height = getHeight() - 20;
		float width = getWidth() - 20;
		float max = Float.parseFloat(verlabels[0]);
		float min = Float.parseFloat(verlabels[1]);
		float diff = max - min;
		float graphheight = height - (2 * border);
		float graphwidth = width - (2 * border);

		paint.setTextAlign(Align.LEFT);
		
		//construct the lines that will serve as vertical guides
		int vers = verlabels.length - 1;
		for (int i = 0; i < verlabels.length; i++) {
			paint.setColor(Color.DKGRAY);
			float y = ((graphheight / vers) * i) + border;
			canvas.drawLine(horstart, y, width, y, paint);
			paint.setColor(Color.BLACK);
			canvas.drawText(verlabels[i], 0, y, paint);
		}
		
		//construct the lines that will serve as horizontal guides
		for (int i = 0; i < horlabels.length; i++) {
			paint.setColor(Color.DKGRAY);
			float x = ((graphwidth / (horlabels.length - 1)) * i) + horstart;
			canvas.drawLine(x, height - border, x, border, paint);
			paint.setTextAlign(Align.CENTER);
			if (i==horlabels.length-1)
				paint.setTextAlign(Align.RIGHT);
			if (i==0)
				paint.setTextAlign(Align.LEFT);
			paint.setColor(Color.BLACK);
			if (horlabels.length > 9) {
				paint.setTextSize(9);
			}
			canvas.drawText(horlabels[i], x, height - 4, paint);
		}

		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(20);
		canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);

		if (max != min) {
			paint.setColor(Color.LTGRAY);
			if (type == BAR) {
				float colwidth = (width - (2 * border)) / valuesOne.size();
				for (int i = 0; i < valuesOne.size(); i++) {
					float h1 = graphheight * ((valuesOne.get(i) - min) / diff);
					float h2 = graphheight * ((valuesTwo.get(i) - min) / diff);
					canvas.drawRect((i * colwidth) + horstart, ((border - h1) + 
							graphheight), ((i * colwidth) + horstart) + (colwidth - 1), 
							height - (border - 1), paint);
					canvas.drawRect((i * colwidth) + horstart, ((border - h2) + 
							graphheight), ((i * colwidth) + horstart) + (colwidth - 1), 
							height - (border - 1), paint);
				}
			} else {
				float lasth1 = graphheight * ((valuesOne.get(0) - min) / diff);
				float lasth2 = 0;
				if (valuesTwo != null) {
					lasth2 = graphheight * ((valuesTwo.get(0) - min) / diff);
				}
				float nextOne = graphwidth/(valuesOne.size() - 1);
				float a = 0;
				
				//each iteration draws two new lines of the graph
				for (int i = 1; i < valuesOne.size(); i++) {
					float h1 = graphheight * ((valuesOne.get(i) - min) / diff);
					paint.setColor(Color.BLUE);
					canvas.drawLine(horstart + a, ((border - lasth1) + graphheight), 
							horstart + a + nextOne, ((border - h1) + graphheight), paint);
					lasth1 = h1;
					
					if (valuesTwo != null) {
						float h2 = graphheight * ((valuesTwo.get(i) - min) / diff);
						paint.setColor(Color.RED);
						canvas.drawLine(horstart + a, ((border - lasth2) + graphheight), 
						horstart + a + nextOne, ((border - h2) + graphheight), paint);
						lasth2 = h2;
					}
					
					a += nextOne;
				}
			}
		}
	}
}

