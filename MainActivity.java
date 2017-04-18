package com.samuelrenteria.alertas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_ListDialog;
    LinearLayout mainLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ListDialog=(Button)findViewById(R.id.btn_ListDialog);
        mainLay=(LinearLayout)findViewById(R.id.mainLayout);
        mainLay.setBackgroundResource(R.drawable.kratos);
    }

    public void lanzaToast(View v){
        Toast.makeText(getApplicationContext(),"Se lanzò el toast =)",Toast.LENGTH_SHORT).show();
    }

    public void lanzaDialogSimple (View v){
        new AlertDialog.Builder(this)
                .setTitle("Dialogo bàsico")
                .setMessage("Descripcion del cuerpo")
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
        .setPositiveButton("Positivo", null)
        .show(); //El listener puede ser null

    }


    public void listDialog(View v){
        new AlertDialog.Builder(this)
                .setTitle("Lista de Colores")
                .setItems(R.array.colores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //wich indica el indice del elemento seleccionado de la cadena
                        switch (which) {
                            case 0:
                                btn_ListDialog.setBackgroundResource(R.drawable.kratos);
                                break;
                            case 1:
                                btn_ListDialog.setBackgroundResource(R.color.rojo);
                                break;
                            case 2:
                                btn_ListDialog.setBackgroundResource(R.color.verde);
                        }

                    }
                })
        .setNegativeButton("Color neutro", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn_ListDialog.setBackgroundResource(R.color.neutro);
            }
        }).show();
    }


    public void lanzaNotificacion (View v){
        Intent regresar =
                new Intent(MainActivity.this, MainActivity.class);

        PendingIntent contIntent =
                PendingIntent.getActivity(MainActivity
                        .this, 0
                        ,regresar
                        ,0);
        NotificationCompat.Builder notiBuilder;
        notiBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Alerta de aplicaciòn")
                .setContentText("Se pulsò el boton de notificaciòn")
                .setContentInfo("Informaciòn")
                .setTicker("Urgente!!");
        notiBuilder.setContentIntent(contIntent);
        
        NotificationManager elManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        elManager.notify(066, notiBuilder.build());
    }


}
