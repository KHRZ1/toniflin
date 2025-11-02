package com.etflin.toniflin;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangAdapterItemView> {
    private Context context;
    ArrayList<BarangItem> list;
    TextView totalTF;

    public BarangAdapter(Context context, ArrayList<BarangItem> list, TextView totalTF, TextView Adjust) {
        this.context = context;
        this.list = list;
        this.totalTF = totalTF;

        Adjust.setOnClickListener(v -> {
            adjustTonicity();
        });
    }

    public class BarangAdapterItemView extends RecyclerView.ViewHolder {
        TextView tonItemName, tonItemTf, tonItemTfJudul, tonItemDelete, tonItemSatuan;
        EditText tonItemAmmount;
        Switch tonItemSwitch;

        public BarangAdapterItemView(final View itemView) {
            super(itemView);
            tonItemName = itemView.findViewById(R.id.tonItemName);
            tonItemTf = itemView.findViewById(R.id.tonItemTf);
            tonItemTfJudul = itemView.findViewById(R.id.tonItemTfJudul);
            tonItemDelete = itemView.findViewById(R.id.tonItemDelete);
            tonItemSwitch = itemView.findViewById(R.id.tonItemSwitch);
            tonItemAmmount = itemView.findViewById(R.id.tonItemAmmount);
            tonItemSatuan = itemView.findViewById(R.id.tonItemSatuan);
        }

        public void setItemName(String name) {
            tonItemName.setText(name);
        }

        public void setItemTf(String number) {
            tonItemTf.setText(number);
        }

        public void setTonItemAmmount(String ammount) {
            tonItemAmmount.setText(ammount);
        }

        public void setTonItemSwitch(boolean status) {tonItemSwitch.setChecked(status);}
    }

    @NonNull
    @Override
    public BarangAdapterItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ton_item_layout, parent, false);
        return new BarangAdapterItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapterItemView holder, int position) {
        final BarangItem barangItem = list.get(position);

        holder.setItemName(barangItem.getItemName());
        holder.setItemTf(barangItem.getItemTf());
        holder.setTonItemAmmount(barangItem.getItemAmmount());
        holder.setTonItemSwitch(barangItem.getItemLock());


        holder.tonItemDelete.setOnClickListener(v -> {
            list.remove(barangItem);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
        });


        holder.tonItemSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(checkIso(barangItem.getItemAmmount(), barangItem.getItemTf()) > 0.525){
                holder.setTonItemSwitch(false);
                testAja("The ⅀(∆Tf x C) locked ingredient is over");
            }
            barangItem.setItemLock(isChecked);
        });


        holder.tonItemAmmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("")){
                    barangItem.setItemAmmount("0");
                } else {
                    barangItem.setItemAmmount(s.toString());
                }
                countTf();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

        countTf();
    }

    @Override
    public int getItemCount() {
        return list == null? 0: list.size();
    }



    public void countTf(){
        Double total = Double.valueOf(0);
        for (int i = 0; i < getItemCount(); i++) {
            BarangItem barangItem = list.get(i);
            total += Double.valueOf(barangItem.itemAmmount) * Double.valueOf(barangItem.itemTf);
        }

        totalTF.setText(String.format("%.2f", total));
    }




    public double checkIso(String ammount, String Tf){
        Double value = Double.valueOf(0);
        for (int i = 0; i < getItemCount(); i++) {
            BarangItem barangItem = list.get(i);
            if(barangItem.getItemLock()) {
                value += Double.valueOf(barangItem.getItemAmmount()) * Double.valueOf(barangItem.getItemTf());
            }
        }

        value += Double.valueOf(ammount) * Double.valueOf(Tf);
        return value;
    }



    public double checkIsoLock(){
        Double value = Double.valueOf(0);
        for (int i = 0; i < getItemCount(); i++) {
            BarangItem barangItem = list.get(i);
            if(barangItem.getItemLock()) {
                value += Double.valueOf(barangItem.getItemAmmount()) * Double.valueOf(barangItem.getItemTf());
            }
        }
        return value;
    }




    public void adjustTonicity(){
        Double totalTfSumLock = Double.valueOf(0);
        Double voLock, voFree;

        for (int i = 0; i < getItemCount(); i++) {
            BarangItem barangItem = list.get(i);
            if(barangItem.getItemLock()){
                totalTfSumLock += Double.valueOf(barangItem.getItemAmmount()) * Double.valueOf(barangItem.getItemTf());
            }
        }

        voLock = 1.92*100*totalTfSumLock;

        voFree = 100 - voLock;
        Double totalTfSumFree = Double.valueOf(0);
        Double newC;

        for (int i = 0; i < getItemCount(); i++) {
            BarangItem barangItem = list.get(i);
            if(!barangItem.getItemLock()){
                newC = (Double.valueOf(barangItem.getItemAmmount())/voFree) * 100;
                totalTfSumFree += newC * Double.valueOf(barangItem.getItemTf());
            }
        }


        for (int i = 0; i < getItemCount(); i++) {
            BarangItem barangItem = list.get(i);
            if(!barangItem.getItemLock()){
                Double newCi = Double.valueOf(barangItem.getItemAmmount().replace(",", ".")) / (1.92 * totalTfSumFree);
                barangItem.setItemAmmount(String.valueOf(newCi));
            }
        }

        notifyDataSetChanged();
    }


    public void testAja(String string){
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
