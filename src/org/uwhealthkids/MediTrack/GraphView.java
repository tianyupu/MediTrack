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
	private List<Float> values;
	private String[] horlabels;
	private String[] verlabels;
	private String title;
	private boolean type;

	public GraphView(Context gView, List<Float> values,  
			String title, String[] horlabels, String[] verlabels, boolean type) {
		super(gView);
		this.values = values;
		
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
		float max = 180;
		float min = 60;
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
				float colwidth = (width - (2 * border)) / values.size();
				for (int i = 0; i < values.size(); i++) {
					float h1 = graphheight * ((values.get(i) - min) / diff);
					canvas.drawRect((i * colwidth) + horstart, (border - h1) + 
							graphheight, ((i * colwidth) + horstart) + (colwidth - 1), 
							height - (border - 1), paint);
				}
			} else {
				float lasth1 = graphheight * ((values.get(0) - min) / diff);
				float nextOne = graphwidth/(values.size() - 1);
				float a = 0;
				
				//each iteration draws two new lines of the graph
				//red for systolic and blue for diastolic
				for (int i = 1; i < values.size(); i++) {
					float h = graphheight * ((values.get(i) - min) / diff);
					paint.setColor(Color.RED);
					canvas.drawLine(horstart + a, (border - lasth1) + graphheight, 
							horstart + a + nextOne, (border - h) + graphheight, paint);
					paint.setColor(Color.BLUE);
					lasth1 = h;
					a += nextOne;
				}
			}
		}
	}
}

