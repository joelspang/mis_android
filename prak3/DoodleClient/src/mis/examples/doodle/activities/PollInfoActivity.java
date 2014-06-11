package mis.examples.doodle.activities;

import mis.examples.doodle.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class PollInfoActivity extends Activity {

    private String pollInfo;

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	setContentView(R.layout.show_poll);

	this.pollInfo = (String) getIntent().getSerializableExtra("pollInfo");

	// Display the info
	TextView txtPollInfo = (TextView) findViewById(R.id.txtPollinfo);
	txtPollInfo.setText(pollInfo);
    }

    public void onBackPressed() {
	// Jump back to the "New Poll"-activity
	Intent i = new Intent(getApplicationContext(), MainActivity.class);

	startActivity(i);
	finish();
    }

}
