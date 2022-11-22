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
import com.example.bikesinnantes.model.stationSelected
import com.example.bikesinnantes.ui.stationDetail.StationMapsActivity
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.test.withTestContext


class ParkingAdapter(private val parkings:List<Parking>, private val context: Context) :
    RecyclerView.Adapter<ParkingAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val name : TextView = itemView.findViewById(R.id.name)
        val distance : TextView = itemView.findViewById((R.id.distance))
        val availability : TextView = itemView.findViewById((R.id.availabilty))
        val status : ImageView = itemView.findViewById((R.id.status))


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item_parkings, parent, false)
        return ViewHolder(view);
    }

    //Pour chaque view_id on met à jour les composants de la view (cardView: CardView,  name:TextView)
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parking = parkings[position]
        holder.name.text = "${parking.grpIdentifiant}-${parking.grpNom}"
        holder.availability.text = "${parking.grpDisponible} 🅿️ / ${parking.grpExploitation} max"

        if (currentLocation != null){
            holder.distance.text = "${String.format("%.2f", currentLocation!!.distanceTo(parking.toLocation()) /1000)} km"
        }else{
            holder.distance.text = "- km"
        }

        if (parking.grpStatut.toInt() == 1  ) {
            holder.name.setTextColor(context.getColor(R.color.parkingIsClose))
            holder.status.setImageResource(R.drawable.ic_baseline_circle_red)
        } else if (parking.grpStatut.toInt() == 5) {
            holder.name.setTextColor(context.getColor(R.color.parkingIsOpen))
            holder.status.setImageResource(R.drawable.ic_baseline_circle_green)
        } else if (parking.grpStatut.toInt() == 2) {
            holder.name.setTextColor(context.getColor(R.color.parkingIsOpenForSubscribers))
            holder.status.setImageResource(R.drawable.ic_baseline_group_add_24)
        } else {
            holder.name.setTextColor(context.getColor(R.color.parkingIsClose))
            holder.status.setImageResource(R.drawable.ic_baseline_not_interested_24)
        }
        holder.cardView.setOnClickListener {
            //intent.putExtra("station", station.name)
        }

    }

    override fun getItemCount(): Int {
        return parkings.size
    }
}