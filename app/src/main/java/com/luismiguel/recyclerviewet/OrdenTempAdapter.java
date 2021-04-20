package com.luismiguel.recyclerviewet;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdenTempAdapter extends RecyclerView.Adapter<OrdenTempAdapter.OrdentTempViewHolder> {

    private final Context context;
    private List<PalletTempModel> palletTempModelList;

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

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    palletTempModelList.get(getAdapterPosition()).temperatura = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }
}
