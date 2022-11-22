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
import com.example.bikesinnantes.model.Station
import com.example.bikesinnantes.model.currentLocation
import com.example.bikesinnantes.model.stationSelected
import com.example.bikesinnantes.ui.stationDetail.StationMapsActivity

class StationAdapter(private val stations:List<Station>, private val context: Context) :
    RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.adress)
        val status : ImageView = itemView.findViewById(R.id.status)
        val availability : TextView = itemView.findViewById(R.id.availabilty)
        val distance : TextView = itemView.findViewById((R.id.distance))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item, parent, false)
        return ViewHolder(view);
    }

    //Pour chaque view_id on met à jour les composants de la view (cardView: CardView,  name:TextView)
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.name.text = station.name
        holder.address.text = station.address
        holder.availability.text = station.showDetails()

        if (currentLocation != null){
            holder.distance.text = "${String.format("%.2f", currentLocation!!.distanceTo(station.toLocation()) /1000)} km"
        }else{
            holder.distance.text = "- km"
        }

        if ("OPEN" == station.status){
            holder.status.setImageResource(R.drawable.ic_baseline_circle_green)
        }else{
            holder.status.setImageResource(R.drawable.ic_baseline_circle_red)
        }

        if (station.availableBikes.toInt() == 0 ){
            holder.name.setTextColor(context.getColor(R.color.empty_bikes))
        }else {
            holder.name.setTextColor(context.getColor(R.color.bikes))
        }

        //Quand on click sur la card view -> on ouvre une nouvelle fenetre
        holder.cardView.setOnClickListener {
            val intent = Intent(context, StationMapsActivity::class.java)
            //intent.putExtra("station", station.name)
            stationSelected = station
            context.startActivity(intent)
        }
    }

    //On retourne le nombre d'éléments de la liste stations
    override fun getItemCount(): Int {
        return stations.size
    }
}