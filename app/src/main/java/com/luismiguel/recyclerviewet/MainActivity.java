package com.luismiguel.recyclerviewet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvLsPallet;
    String [] pallet = {"165","160","150","110","186","170","140","101","140","126","190","172","113","171","109","146","119","155","136","187","111","102","170","113","142","131","143","198","191","110"};
    String [] temperatura = {"4.4","-2.4","2.4","1.4","3.4","1.6","4.6","2.7","0.7","-2.7","1.8","4.3","-1.4","0.6","3.7","0.4","2.2","0.7","2.3","-3.9","-4","-4.5","4.2","2.2","0.8","0.4","0.9","-1","4.8","4.9"};
    int [] rutaPallet = {0,0,1,1,0,1,1,1,0,1,0,1,0,0,0,0,1,1,0,0,0,1,0,0,0,1,1,0,0,0};
    private List<PalletTempModel> palletTempModelListSinModificar = new ArrayList<PalletTempModel>();
    private int contador = 0;
    private Button btnEnviarTemperatura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvLsPallet = findViewById(R.id.rvLsPalletOrden);
        btnEnviarTemperatura = findViewById(R.id.btnEnviarTemperatura);
        List<PalletTempModel> palletTempModelList = new ArrayList<PalletTempModel>();

        for(int i= 0; i < 30; i++){
            PalletTempModel palletTempModel = new PalletTempModel();
            palletTempModel.numePallet = pallet[i];
            palletTempModel.temperatura = temperatura[i];
            palletTempModel.rutaPallet = rutaPallet[i];
            palletTempModelList.add(palletTempModel);
        }
        for(PalletTempModel item: palletTempModelList){
            try {
                palletTempModelListSinModificar.add((PalletTempModel) item.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        OrdenTempAdapter ordenTempAdapter = new OrdenTempAdapter(getApplicationContext());
        ordenTempAdapter.setPalletTempModelList(palletTempModelList);

        rvLsPallet.setHasFixedSize(true);
        rvLsPallet.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvLsPallet.setAdapter(ordenTempAdapter);
        btnEnviarTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                for(int i = 0; i <palletTempModelListSinModificar.size(); i++){
                    message = message + palletTempModelListSinModificar.get(i).getNumePallet() + " | ";
                    message = message + palletTempModelListSinModificar.get(i).getTemperatura() + " | ";
                    message = message + Integer.toString(palletTempModelListSinModificar.get(i).getRutaPallet()) + " | ";
                    message = message + "\n";
                }
                message = message + "-----------------------------\n";
                for(int i = 0; i <ordenTempAdapter.getPalletTempModelList().size(); i++){
                    message = message + ordenTempAdapter.getPalletTempModelList().get(i).getNumePallet() + " | ";
                    message = message + ordenTempAdapter.getPalletTempModelList().get(i).getTemperatura() + " | ";
                    message = message + Integer.toString(ordenTempAdapter.getPalletTempModelList().get(i).getRutaPallet()) + " | ";
                    message = message + "\n";
                }
                Toast.makeText(getApplicationContext(), "Enviar Temperatura \n" + message, Toast.LENGTH_LONG).show();
                Log.i("enviar Temperatura", "Temperatura\n" + message);
            }
        });
    }
}