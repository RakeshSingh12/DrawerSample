package app.icashier.com.drawersample;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MainActivity extends AppCompatActivity {

    boolean showing=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showing) {

                    collapse(findViewById(R.id.btn1));
                    collapse(findViewById(R.id.btn2));
                    collapse(findViewById(R.id.btn3));
                    showing=false;
                }else{

                    expand(findViewById(R.id.btn1));
                    expand(findViewById(R.id.btn2));
                    expand(findViewById(R.id.btn3));

                    showing=true;
                }
            }
        });
    }

    public static void expand(final View v) {
        v.measure(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredWidth();

        v.getLayoutParams().width = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().width = interpolatedTime == 1
                        ? ConstraintLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(300);//(int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density)
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredWidth();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().width = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(300);//(int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density)
        v.startAnimation(a);
    }
}
