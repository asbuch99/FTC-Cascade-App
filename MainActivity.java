package ftc.app.ftcapp;



import android.app.Activity;
//import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.ToggleButton;
//import android.os.Build;
import android.widget.TextView;




public class MainActivity extends Activity implements NumberPicker.OnValueChangeListener {
int x=0 ,y=0 ,a=0 , z = 0 , nps=0,nps1=0;
	NumberPicker np , np1;
    
	@Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
            np = (NumberPicker) findViewById(R.id.numberPicker1);
            
            np.setMinValue(0);
            np.setMaxValue(3);
            np.setWrapSelectorWheel(false); 
            NumberPicker np = (NumberPicker)findViewById(R.id.numberPicker1);
            
            np.setOnValueChangedListener(this);
            
            np1 = (NumberPicker) findViewById(R.id.numberPicker2);
            
            np1.setMinValue(0);
            np1.setMaxValue(3);
            np1.setWrapSelectorWheel(false);     
            NumberPicker np1 = (NumberPicker)findViewById(R.id.numberPicker2);
      
            np1.setOnValueChangedListener(this);
        }
    }
	
	void updatescore(){
		 TextView text = (TextView) findViewById(R.id.textView6);
	   		text.setText(x + y + a + nps1+nps+ " pts"); 
	}
	
    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        
        if (on) {
        x = 20;
        
        	
        } else {
        	x = 0;
        	
        }
        updatescore();
    }
    
    public void onToggleClicked1(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        
        if (on) {
        	y = 30;
        	
        } else {
        	y = 0;
        	
        }
        updatescore();
    }
    public void onToggleClicked2(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        
        if (on) {
        	a= 60; 
        } else {
        	a=0;
        	
        } 
        updatescore();
    }
    
   
        public void onClick(View v) {
            // TODO Auto-generated method stub
           
            //Creating TextView Variable
             x=0;
             y=0;
             a=0;
             z=0;
        	TextView text = (TextView) findViewById(R.id.textView6);
           
            //Sets the new text to TextView (runtime click event)
            text.setText("0 pts");
        }
   
        @Override
    	public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
        	 if(arg0==np1 ){
        		 nps = arg2*30;        	   		         	         		        	   			
        	 }
        	 else{
        		 nps1 = arg2*20;
        	
        	 }
             updatescore();
        	 }
          

        


        
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
