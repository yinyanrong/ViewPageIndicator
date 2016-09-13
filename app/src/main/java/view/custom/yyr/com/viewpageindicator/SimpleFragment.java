package view.custom.yyr.com.viewpageindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by My on 2016/9/12.
 */
public class SimpleFragment extends Fragment {
    public static  String FRAGMENT_TITLE="title";
    String  title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title=getArguments().getString(FRAGMENT_TITLE);

        TextView tx=  new TextView(getContext());
        tx.setText(title);
        tx.setGravity(Gravity.CENTER);
        return tx;
    }
    public static Fragment newInstance(String title){
        SimpleFragment  simpleFragment=new SimpleFragment();
        Bundle bundle=new Bundle();
        bundle.putString(FRAGMENT_TITLE,title);
        simpleFragment.setArguments(bundle);
        return simpleFragment;
    }
}
