package com.example.bikesinnantes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bikesinnantes.R
import com.example.bikesinnantes.model.Parking
import com.example.bikesinnantes.model.currentLocation
import com.example.bikesinnantes.model.parkingSelected
import kotlinx.coroutines.test.withTestContext


class ParkingAdapter(private val parkings:List<Parking>, private val context: Context) :
    RecyclerView.Adapter<ParkingAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val name : TextView = itemView.findViewById(R.id.name)
        val grpDisponible : TextView = itemView.findViewById(R.id.grpDisponible)
        val distance : TextView = itemView.findViewById((R.id.distance))
        val grpExploitation : TextView = itemView.findViewById((R.id.grpExploitation))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item, parent, false)
        return ViewHolder(view);
    }

    //Pour chaque view_id on met à jour les composants de la view (cardView: CardView,  name:TextView)
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parking = parkings[position]
        holder.name.text = parking.grpNom
        holder.grpDisponible.text = parking.grpDisponible.toString()
        holder.grpExploitation.text = parking.grpExploitation

        if (currentLocation != null){
            holder.distance.text = "${String.format("%.2f", currentLocation!!.distanceTo(parking.toLocation()) /1000)} km"
        }else{
            holder.distance.text = "- km"
        }

        //Quand on click sur la card view -> on ouvre une nouvelle fenetre
    }

    override fun getItemCount(): Int {
        return parkings.size
    }
}