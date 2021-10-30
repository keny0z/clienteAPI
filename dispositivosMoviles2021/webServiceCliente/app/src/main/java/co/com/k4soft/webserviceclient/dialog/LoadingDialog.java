package co.com.k4soft.webserviceclient.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.com.k4soft.webserviceclient.R;

public class LoadingDialog extends DialogFragment {



    private String title;

    public static LoadingDialog build(){
        return new LoadingDialog();
    }



    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_bar_layout,container,false);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        return view;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            if(!manager.isDestroyed()){
                ft.commitAllowingStateLoss();
            }
        } catch (IllegalStateException e) {
            Log.d("Error", "Exception", e);
        }
    }
}
