package rishabh.example.jwcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AdmssionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admssion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Online Forms");

        TextView textView = (TextView) findViewById(R.id.inter);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://jsrwomensintercollege.in/examination_registration.php'> Intermediate </a>";
        textView.setText(Html.fromHtml(text));

        TextView textView2 = (TextView) findViewById(R.id.voca);
        textView2.setClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='http://cas.jsrwomenscollege.ac.in/jwccollegeautomation/jwcadmission.aspx?'> Vocational </a>";
        textView2.setText(Html.fromHtml(text2));

        TextView textView3 = (TextView) findViewById(R.id.ug);
        textView3.setClickable(true);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        String text3 = "<a href='https://jharkhanduniversities.nic.in/home'> UG </a>";
        textView3.setText(Html.fromHtml(text3));

        TextView textView4 = (TextView) findViewById(R.id.pg);
        textView4.setClickable(true);
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
        String text4 = "<a href='http://cas.jsrwomenscollege.ac.in/jwccollegeautomation/mphil_phd.aspx'> Masters </a>";
        textView4.setText(Html.fromHtml(text4));
    }

}