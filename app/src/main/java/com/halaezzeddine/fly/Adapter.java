package com.halaezzeddine.fly;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Form> arrayList;
    ArrayList<ImageButton> data;

    //dataBase object
    flyHelper databaseHelper;

    public Adapter(Context context, ArrayList<Form> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        databaseHelper = new flyHelper(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {

        Form form = arrayList.get(position);
        //get for view
        String id = form.getId();
        String from = form.getFrom();
        String to = form.getTo();
        String dep = form.getDeparture();
        String arr = form.getArrival();
        String tclass = form.getTravelClass();


        //set views
        holder.from.setText(from);
        holder.to.setText(to);
        holder.dep.setText(dep);
        holder.arr.setText(arr);
        holder.tclass.setText(tclass);

        //edit listener
        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+from,
                         ""+to,
                        ""+dep,
                        ""+arr,
                        ""+tclass
                );
            }
        });

        //delete upon long press
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                deleteDialog(
                        ""+id
                );

                return false;
            }
        });
    }

    private void deleteDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.delete);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper.deleteInfo(id);
                ((showflights)context).onResume();
                Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.create().show();
    }

    private void editDialog(String position, String id, String from, String to, String dep, String arr, String tclass) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Do you want to Update?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.edit_foreground);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, editForm.class);
                intent.putExtra("ID", id);
                intent.putExtra("SOURCE", id);
                intent.putExtra("DESTINATION", id);
                intent.putExtra("DEPARTURE", id);
                intent.putExtra("ARRIVAL", id);
                intent.putExtra("TRAVELCLASS", id);
                intent.putExtra("editMode", true);
                context.startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.create().show();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView plane;
        TextView from, to, dep, arr, tclass;
        ImageButton editbtn;

        public Holder(@NonNull View itemView){
            super(itemView);

            plane=itemView.findViewById(R.id.plane);
            from=itemView.findViewById(R.id.def_from);
            to=itemView.findViewById(R.id.def_to);
            dep=itemView.findViewById(R.id.def_dep);
            arr=itemView.findViewById(R.id.def_arr);
            tclass=itemView.findViewById(R.id.def_tclass);
            editbtn=itemView.findViewById(R.id.editbtn);
        }
    }


}
