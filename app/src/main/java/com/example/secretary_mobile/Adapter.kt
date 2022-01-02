package com.example.secretary_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val mList: List<ItemsViewModel>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        holder.textViewId.text = "id: " + ItemsViewModel.id
        holder.textViewName.text = "name: " + ItemsViewModel.firstName
        holder.textViewSecondName.text = "second name: " + ItemsViewModel.secondName
        holder.textViewLastName.text = "last name: " + ItemsViewModel.lastName
        holder.textViewMaidenName.text = "maiden name: " + ItemsViewModel.maidenName
        holder.textViewFathersName.text = "fathers name: " + ItemsViewModel.fathersName
        holder.textViewMothersName.text = "mothers name: " + ItemsViewModel.mothersName
        holder.textViewBirthDateName.text = "birthdate: " + ItemsViewModel.birthDate
        holder.textViewPesel.text = "pesel: " + ItemsViewModel.pesel
        holder.textViewGender.text = "gender: " + ItemsViewModel.gender
        holder.textViewAdditionalField1.text =ItemsViewModel.additionalField1
        holder.textViewAdditionalField2.text =ItemsViewModel.additionalField2
        holder.textViewAdditionalField3.text =ItemsViewModel.additionalField3
        holder.textViewAdditionalField4.text =ItemsViewModel.additionalField4

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewSecondName: TextView = itemView.findViewById(R.id.textViewSecondName)
        val textViewLastName: TextView = itemView.findViewById(R.id.textViewLastName)
        val textViewMaidenName: TextView = itemView.findViewById(R.id.textViewMaidenName)
        val textViewFathersName: TextView = itemView.findViewById(R.id.textViewFathersName)
        val textViewMothersName: TextView = itemView.findViewById(R.id.textViewMothersName)
        val textViewBirthDateName: TextView = itemView.findViewById(R.id.textViewBirthdate)
        val textViewPesel: TextView = itemView.findViewById(R.id.textViewPesel)
        val textViewGender: TextView = itemView.findViewById(R.id.textViewGender)
        val textViewAdditionalField1: TextView =
            itemView.findViewById(R.id.textViewAdditionalField1)
        val textViewAdditionalField2: TextView =
            itemView.findViewById(R.id.textViewAdditionalField2)
        val textViewAdditionalField3: TextView =
            itemView.findViewById(R.id.textViewAdditionalField3)
        val textViewAdditionalField4: TextView =
            itemView.findViewById(R.id.textViewAdditionalField4)
    }
}