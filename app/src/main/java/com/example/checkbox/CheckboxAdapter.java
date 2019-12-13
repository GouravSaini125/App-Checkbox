package com.example.checkbox;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckboxAdapter extends RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder>{

        private Context mCtx;
        private List<String> checkList;
        View view;

        public CheckboxAdapter(Context mCtx, List<String> checkList) {
            this.mCtx = mCtx;
            this.checkList = checkList;
        }

        @NonNull
        @Override
        public CheckboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
                view = inflater.inflate(R.layout.checkboxes, null);
            return new CheckboxViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final CheckboxViewHolder holder, int position) {
            final String text = checkList.get(position);

            for(String s : checkList) {
                CheckBox cb = new CheckBox(mCtx);
                cb.setText(s);
//            cb.setChecked(true);
                holder.linearLayout.addView(cb);
            }



            holder.addBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    LayoutInflater layoutInflater = LayoutInflater.from(mCtx);

                    View promptView = layoutInflater.inflate(R.layout.prompts, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);

                    alertDialogBuilder.setView(promptView);

                    final EditText input = promptView.findViewById(R.id.userInput);

                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    CheckBox cb = new CheckBox(mCtx);
                                    cb.setText(input.getText().toString());
//                                  cb.setChecked(true);
                                    holder.linearLayout.addView(cb);
                                }
                            })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog alertD = alertDialogBuilder.create();

                    alertD.show();





                }
            });

        }

        @Override
        public int getItemCount() {
//            return checkList.size();
            return 1;
        }

        class CheckboxViewHolder extends RecyclerView.ViewHolder {

            LinearLayout linearLayout;
            Button addBtn;

            public CheckboxViewHolder(@NonNull View itemView) {
                super(itemView);
               linearLayout  = itemView.findViewById(R.id.l_layout);
                addBtn = itemView.findViewById(R.id.addBtn);
            }
        }

}
