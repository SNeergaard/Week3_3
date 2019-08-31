package com.example.week3_day3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DDRVAdapter extends RecyclerView.Adapter<DDRVAdapter.ViewHolder> {
    private ArrayList<DndCharacter> dndCharacterArrayList;

    public DDRVAdapter(ArrayList<DndCharacter> dndCharacterArrayList) {this.dndCharacterArrayList = dndCharacterArrayList;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflatedItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.dd_item, parent, false);
        return new ViewHolder(inflatedItem);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        DndCharacter currentDndCharacter = dndCharacterArrayList.get(position);
        holder.populateValues(currentDndCharacter);
    }

    @Override
    public int getItemCount() { return  dndCharacterArrayList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvDesc;
        private TextView tvRace;
        private TextView tvClass;
        private TextView tvWhat;
        private DndCharacter itemDndCharacter;

        public ViewHolder(View itemView){
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvRace = itemView.findViewById(R.id.tvRace);
            tvClass = itemView.findViewById(R.id.tvClass);
            tvWhat = itemView.findViewById(R.id.tvWhat);
            itemView.setOnClickListener(this);
        }

        public void setItemDndCharacter(DndCharacter itemDndCharacter) {this.itemDndCharacter = itemDndCharacter;}

        public void populateValues(DndCharacter dndCharacter){
            tvDesc.setText(dndCharacter.getDescriptor());
            tvRace.setText(dndCharacter.getRace());
            tvClass.setText(dndCharacter.getCclass());
            tvWhat.setText(dndCharacter.getWhatDo());
            setItemDndCharacter(dndCharacter);
        }

        @Override
        public void onClick(View view){
            Intent detailsIntent = new Intent(view.getContext(), MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("DD", itemDndCharacter);
            detailsIntent.putExtras(bundle);
            view.getContext().startActivity(detailsIntent);
        }
    }
}
