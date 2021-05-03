package com.luismiguel.recyclerviewet;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class OrdenTempAdapter extends RecyclerView.Adapter<OrdenTempAdapter.OrdentTempViewHolder> {

    private final Context context;
    private List<PalletTempModel> palletTempModelList;
    //String[] beforeTextChanged = {""};
    //final List<Integer>[] posiciones = new List[]{new ArrayList<Integer>()};
    //final int[] comienzo = {0};

    String beforeTextChanged = "";
    List<Integer> posicionesGuiones = new ArrayList<Integer>();
    List<Integer> posicionesPuntos = new ArrayList<Integer>();
    int comienzo = 0;
    int despues = 0;
    private Gson gson = new Gson();
    public OrdenTempAdapter(Context context) {
        this.context = context;
    }

    public List<PalletTempModel> getPalletTempModelList() {
        return palletTempModelList;
    }

    public void setPalletTempModelList(List<PalletTempModel> palletTempModelList) {
        this.palletTempModelList = palletTempModelList;
    }

    @NonNull
    @Override
    public OrdentTempViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ls_orden_temp, parent, false);
        return new OrdentTempViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdentTempViewHolder holder, int position) {
        holder.setPalletTemp(palletTempModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return palletTempModelList.size();
    }

    class OrdentTempViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPallet;
        private final EditText tvTemperatura;
        private final CheckBox checkboxCamara;
        private final RelativeLayout rlItem;

        OrdentTempViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPallet = itemView.findViewById(R.id.tvPallet);
            tvTemperatura = itemView.findViewById(R.id.tvTemperatura);
            checkboxCamara = itemView.findViewById(R.id.tvCheckboxCamara);
            rlItem = itemView.findViewById(R.id.rlItem);
            rlItem.setBackgroundResource(R.drawable.selector_item_pallets);
        }

        void setPalletTemp(PalletTempModel palletTemp) {
            tvPallet.setText(palletTemp.getNumePallet());
            tvTemperatura.setText(palletTemp.getTemperatura());
            checkboxCamara.setChecked(palletTemp.rutaPallet == 1 ? true: false);
            checkboxCamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    palletTempModelList.get(getAdapterPosition()).rutaPallet = (checkboxCamara.isChecked() == true ? 1: 0);
                }
            });

            tvTemperatura.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //beforeTextChanged = s.toString();
                    //Log.i("beforeTextChanged", "---------------------------");
                    //Log.i("beforeTextChanged", "CharSequence: " + s.toString());
                    //Log.i("beforeTextChanged", "start: " + start);
                    //Log.i("beforeTextChanged", "count: " + count);
                    //Log.i("beforeTextChanged", "after: " + after);
                    //Log.i("beforeTextChanged", "---------------------------");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //Log.i("onTextChanged", "beforeTextChanged: " + beforeTextChanged);
                    //Log.i("onTextChanged", "CharSequence: " + s.toString());
                    //Log.i("onTextChanged", "start: " + start);
                    //Log.i("onTextChanged", "before: " + before);
                    //Log.i("onTextChanged", "count: " + count);
                    //Log.i("onTextChanged", "---------------------------");
                    posicionesGuiones = contarCaracteres(s.toString(), '-');
                    posicionesPuntos = contarCaracteres(s.toString(), '.');
                    comienzo = start;
                    despues = before;


                }

                @Override
                public void afterTextChanged(Editable s) {
                    //Log.i("afterTextChanged", "Editable: " + s.toString());
                    //Log.i("afterTextChanged", "Comienzo + 1: " + (comienzo + 1));
                    //Log.i("afterTextChanged", "tvTemperatura.getText().toString() " + tvTemperatura.getText().toString());
                    //Log.i("afterTextChanged", "tvTemperatura.getText().length() " + tvTemperatura.getText().length());
                    //StringBuilder stringBuilder = new StringBuilder(s.toString());
                    int guiones = posicionesGuiones.size();
                    int puntos = posicionesPuntos.size();
                    //Log.i("afterTextChanged", "posiciones: " + gson.toJson(posicionesGuiones) + " tamaño: " + guiones);
                    //Log.i("afterTextChanged", "comienzo: "+comienzo);
                    //Log.i("afterTextChanged", "before: " + despues);
                    String text = s.toString();


                    if(guiones > 1){
                        StringBuilder stringBuilder = new StringBuilder(s.toString());
                        //Log.i("afterTextChanged", "stringBuilder.toString() antes: " + stringBuilder.toString());
                        stringBuilder = stringBuilder.deleteCharAt(posicionesGuiones.get(posicionesGuiones.size() - 1));
                        text = stringBuilder.toString();
                        //Log.i("afterTextChanged", "stringBuilder.toString() despues: " +stringBuilder.toString());
                        if(despues == 0){
                            comienzo--;
                        }else{
                            comienzo++;
                        }
                    }else if(guiones == 1){
                        StringBuilder stringBuilder = new StringBuilder(s.toString());
                        if (posicionesGuiones.get(0) != 0) {
                            //Log.i("afterTextChanged", "stringBuilder.toString() antes: " + stringBuilder.toString());
                            stringBuilder = stringBuilder.deleteCharAt(posicionesGuiones.get(0));
                            text = stringBuilder.toString();
                            //Log.i("afterTextChanged", "stringBuilder.toString() despues: " +stringBuilder.toString());
                            if(despues == 0){
                                comienzo--;
                            }else{
                                comienzo++;
                            }
                        }
                    }

                    if(puntos > 1){
                        StringBuilder stringBuilder = new StringBuilder(s.toString());
                        //Log.i("afterTextChanged", "stringBuilder.toString() antes: " + stringBuilder.toString());
                        stringBuilder = stringBuilder.deleteCharAt(posicionesPuntos.get(posicionesPuntos.size() - 1));
                        text = stringBuilder.toString();
                        //Log.i("afterTextChanged", "stringBuilder.toString() despues: " +stringBuilder.toString());
                        if(despues == 0){
                            comienzo--;
                        }else{
                            comienzo++;
                        }
                    }


                    tvTemperatura.removeTextChangedListener(this);
                    tvTemperatura.setText(text);
                    tvTemperatura.addTextChangedListener(this);
                    palletTempModelList.get(getAdapterPosition()).temperatura = text;
                    if(comienzo == 5 && despues == 0){
                        tvTemperatura.setSelection(tvTemperatura.getText().length());
                    }else if(comienzo < 5 && despues == 0){
                        tvTemperatura.setSelection(comienzo + 1);
                    }else if(comienzo >= 1 && despues == 1){
                        tvTemperatura.setSelection(comienzo);
                    }
                    //Log.i("afterTextChanged", "------------------------------------");
                }
            });
        }
    }

    public List<Integer> contarCaracteres(String cadena, char caracter) {
        List<Integer> posiciones = new ArrayList<Integer>();
        int posicion, contador = 0;
        //se busca la primera vez que aparece
        posicion = cadena.indexOf(caracter);

        while (posicion != -1) { //mientras se encuentre el caracter
            contador++;           //se cuenta
            //se sigue buscando a partir de la posición siguiente a la encontrada
            posiciones.add(posicion);
            posicion = cadena.indexOf(caracter, posicion + 1);

        }
        return posiciones;
    }
}
