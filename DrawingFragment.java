package first.app_components;

import java.util.ArrayList;

import first.app_components.BlockScoringFragment.BlockScoreChangedListener;
import first.blockpartyguitest.R;
import first.blockpartyguitest.custom_components.DrawingView;
import android.R.color;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

public class DrawingFragment extends Fragment implements OnClickListener{
	
	private ImageButton bluePicker, redPicker, lightBluePicker, lightRedPicker;
	private DrawingView d;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.drawing_fragment, container,false);
		
		
		Button clearBtn = (Button)v.findViewById(R.id.clearDrawingsButton);
		clearBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				d.clear();
				
			}
			
		});
		
		Button undoBtn = (Button)v.findViewById(R.id.undoBtn);
		undoBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				d.undoLastPath();
				Toast.makeText(getActivity(), "undo", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		//set up color picker buttons
		bluePicker = (ImageButton)v.findViewById(R.id.blueColorBtn);
		lightBluePicker = (ImageButton)v.findViewById(R.id.lightBlueColorBtn);
		redPicker = (ImageButton)v.findViewById(R.id.redColorBtn);
		lightRedPicker = (ImageButton)v.findViewById(R.id.lightRedColorBtn);
		
		bluePicker.setOnClickListener(this);
		lightBluePicker.setOnClickListener(this);
		redPicker.setOnClickListener(this);
		lightRedPicker.setOnClickListener(this);
		
		d = new DrawingView(getActivity());
		FrameLayout f = (FrameLayout)v.findViewById(R.id.draw_frag_frame);
		f.addView(d);
		
		return v;
	}
	
	/***
	 * @return: the autonomous path, null if a path was never created
	 */
	public ArrayList<Path> getAutonomousPaths()
	{
		if(d == null)
			return null;
		
		return d.getPaths();
	}
	
	/***
	 * @return bitmap of the view 
	 */
	@SuppressLint("WrongCall")
	public Bitmap getAutonomousBitmap()
	{
		if(d == null)
			return null;
		d.setDrawingCacheEnabled(true);
		return d.getDrawingCache();
		
//		Bitmap b = Bitmap.createBitmap(d.getWidth(), d.getHeight(), Bitmap.Config.ARGB_8888);
//		Canvas c = new Canvas(b);
//		d.layout(0, 0, d.getLayoutParams().width, d.getLayoutParams().height);
//		d.drawToGivenCanvas(c);
//		return b;
	}
	
//	/***
//	 * a view that supports drawing with touch input
//	 *
//	 */
//	public class DrawingView extends View
//	{
//		private Paint mPaint;
//		private Bitmap mScaledBackgroundImage;
//		private Bitmap mOriginalBackgroundImage;
//		private int mViewWidth, mViewHeight;
//		//private ArrayList<Point> points;//holds the current path as points
//		private ArrayList<Path> paths;//all paths
//		private ArrayList<Integer> pathColors;//holds colors that correspond with the paths in paths
//		private Path currentPath;
//		
//		public DrawingView(Context context) {
//			super(context);
//			mPaint = new Paint();
//			mPaint.setColor(Color.parseColor("#0059FF"));
//			initialize();
//			paths = new ArrayList<Path>();
//			pathColors = new ArrayList<Integer>();
//		}
//		
//		/***
//		 * perform intializing work:
//		 * load content and ect.
//		 */
//		private void initialize()
//		{
//			mPaint.setStyle(Paint.Style.STROKE);
//			mPaint.setColor(Color.GREEN);
//			mPaint.setStrokeWidth(10);
//			
//			try{
//			mOriginalBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.field_picture);
//			}catch(Exception e)
//			{
//				Log.i("abc", e.getMessage());
//			}
//		}
//		
//		@Override
//		public void onDraw(Canvas canvas)
//		{
//			super.onDraw(canvas);
//			canvas.drawBitmap(mScaledBackgroundImage, 0, 0, mPaint);
//			
//			int originalColor = mPaint.getColor();//save color to be restored later
//			
//			if(currentPath != null)
//				canvas.drawPath(currentPath, mPaint);
//			
//			for(int i = 0 ; i < paths.size() ; i++)
//			{
//				mPaint.setColor(pathColors.get(i));
//				canvas.drawPath(paths.get(i), mPaint);
//			}
//			
//			mPaint.setColor(originalColor);
//
//		
//		}
//		
//		@Override
//		public void onSizeChanged(int w, int h, int oldw, int oldh)
//		{
//			this.mViewWidth = w;
//			this.mViewHeight = h;
//			
//			scaleBitmap();
//		}
//		
//		//sets mScaledBackgroundImage to a scaled version of the original based off of the width of the view
//		private void scaleBitmap()
//		{
//			try{
//			float newHeight = mViewWidth/(float)mOriginalBackgroundImage.getWidth()*mOriginalBackgroundImage.getHeight();
//			this.mScaledBackgroundImage = Bitmap.createScaledBitmap(mOriginalBackgroundImage, mViewWidth, Math.round(newHeight), true);
//			}catch(Exception e)
//			{
//				Log.i("abc", e.getMessage());
//			}
//		}
//
//		@Override
//		public boolean onTouchEvent(MotionEvent event) {
//			if(event.getAction() == MotionEvent.ACTION_UP)//if the line draw has finsihed, add the path to the array and start a new one
//			{
//				paths.add(currentPath);
//				pathColors.add(mPaint.getColor());
//				currentPath = null;
//				return true;
//			}
//			
//			if(event.getAction() == MotionEvent.ACTION_DOWN )//starts a new line draw
//			{
//				//points.clear();
//				currentPath = new Path();
//				currentPath.setLastPoint(Math.round(event.getX()), Math.round(event.getY()));
//				//points.add(new Point(Math.round(event.getX()),Math.round(event.getY())));
//			}
//			currentPath.lineTo(Math.round(event.getX()), Math.round(event.getY()));
//			
//			
//			this.invalidate();
//			return true;
//		}
//		
//		public void setDrawColor(int c)
//		{
//			mPaint.setColor(c);
//		}
//		
//		/***
//		 * clears all paths
//		 */
//		public void clear()
//		{
//			paths.clear();
//			pathColors.clear();
//			invalidate();
//		}
//		
//		/***
//		 * @return: an array list containing the paths drawn in the view - currently cannot save color
//		 */
//		public ArrayList<Path> getPaths()
//		{
//			ArrayList<Path> allPaths = new ArrayList<Path>(paths);
//			allPaths.add(currentPath);
//			return allPaths;
//		}
//	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == redPicker)
			d.setDrawColor(Color.parseColor("#FF0000"));
		else if(v == bluePicker)
			d.setDrawColor(Color.parseColor("#0059FF"));
		else if(v == lightBluePicker)
			d.setDrawColor(Color.parseColor("#5AB7FA"));
		else if(v == lightRedPicker)
			d.setDrawColor(Color.parseColor("#FFCCCC"));
			
		
			
	}
	
}
