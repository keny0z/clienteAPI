package co.com.k4soft.webserviceclient.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.com.k4soft.webserviceclient.R;

public class CustomDialog {

    public static LoadingDialog showLoading(AppCompatActivity appCompatActivity, String mensaje) {
        LoadingDialog loadingDialog = LoadingDialog.build();
        loadingDialog.setTitle(mensaje);
        loadingDialog.show(appCompatActivity.getSupportFragmentManager(), null);
        loadingDialog.setCancelable(false);
        return loadingDialog;
    }

    public static void okToast(Context context, String mensaje) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.toast_layout, null);
        View layout = inflater.inflate(R.layout.toast_layout,
                v.findViewById(R.id.toast_layout_root));
        ImageView image = layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_done);
        TextView text = layout.findViewById(R.id.text);
        text.setText(mensaje);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }

}
