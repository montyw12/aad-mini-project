package com.example.studentverification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private final Context context;
    private final ArrayList<Model> arrayList;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Model model = arrayList.get(position);


        holder.username.setText(model.getStudentName());
        holder.merit.setText(model.getTenthMeritRank().toString());
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), EditData.class);
                i.putExtra("student_name", model.getStudentName());
                i.putExtra("father_name", model.getFatherName());
                i.putExtra("mother_name", model.getMotherName());
                i.putExtra("student_aadhar_number", model.getStudentAadahrNumber());
                i.putExtra("father_mobile_number", model.getFatherMobileNumber());
                i.putExtra("permanent_address", model.getPermanentAddress());
                i.putExtra("citizen", model.getCitizen());
                i.putExtra("gender", model.getGender());
                i.putExtra("category_of_admission", model.getCategoryOfAdmission());
                i.putExtra("parents_annual_income", model.getParentsAnnualIncome());
                i.putExtra("tenth_merit_rank", model.getTenthMeritRank());
                i.putExtra("email_id", model.getEmailId());
                i.putExtra("student_whatsapp_number", model.getStudentWhatsappNumber());
                i.putExtra("birth_date", model.getBirthDate());
                i.putExtra("religion", model.getReligion());
                i.putExtra("sId", model.getsId());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView username, merit;
        Button submit;

        public Holder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.idusername);
            merit = itemView.findViewById(R.id.idmerit);
            submit = itemView.findViewById(R.id.idsubmit);
        }
    }
}
