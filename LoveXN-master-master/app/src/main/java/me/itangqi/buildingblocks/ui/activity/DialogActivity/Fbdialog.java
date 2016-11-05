package me.itangqi.buildingblocks.ui.activity.DialogActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.itangqi.buildingblocks.R;

/**
 * Created by wade on 2016/11/2.
 */
public class Fbdialog extends Dialog {
    EditText pn;
    EditText pt;
    EditText pp;
    private Button fb;
    public Fbdialog(Context context) {
        super(context, R.style.fbdialog);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.play_fabu,null);
        pn=(EditText)view.findViewById(R.id.playn);
         pt=(EditText)view.findViewById(R.id.playt);
         pp=(EditText)view.findViewById(R.id.playp);
        fb=(Button)findViewById(R.id.fbb);
        setContentView(view);
    }
    public String Playn()
    {
        return pn.getText().toString();
    }
    public String Playt()
    {
        return pn.getText().toString();
    }
    public String Playp()
    {
        return pn.getText().toString();
    }
//    public void setdjListener(View.OnClickListener listener){
//        fb.setOnClickListener(listener);
//    }
}
