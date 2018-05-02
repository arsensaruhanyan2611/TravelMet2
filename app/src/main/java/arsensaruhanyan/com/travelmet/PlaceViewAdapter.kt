package arsensaruhanyan.com.travelmet

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PlaceViewAdapter(val context: Context, val type: Int, val userList: List<Place>): RecyclerView.Adapter<PlaceViewAdapter.PlaceViewHolder>() {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.textTitle?.text = userList[position].title
        holder.textPrice?.text = "$" + userList[position].price
        val city = userList[position].city
        val bullet = context.resources.getString(R.string.bullet)
        when (type) {
            1 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_1)} $bullet $city"
            2 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_2)} $bullet $city"
            3 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_3)} $bullet $city"
            4 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_4)} $bullet $city"
            5 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_5)} $bullet $city"
            6 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_6)} $bullet $city"
            7 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_7)} $bullet $city"
            8 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_8)} $bullet $city"
            9 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_9)} $bullet $city"
            10 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_10)} $bullet $city"
            11 -> holder.textTypeCity?.text = "${str(R.string.title_place_type_11)} $bullet $city"
        }
        val hours = context.resources.getString(R.string.hour_short)
        holder.textTimeAmount.text = "${userList[position].timeAmount} $hours"
        val url = userList[position].photoId
        Picasso.with(context).load(url).placeholder(R.drawable.image).centerCrop().fit().into(holder.photoView)
//        holder.photoView?.setOnClickListener { startHotelDetailActivity(userList[position]) }
    }

    fun str(res: Int): String = context.resources.getString(res)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.place_view_adapter, parent, false)
        return PlaceViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

//    private fun startHotelDetailActivity(hotel: Hotel) {
//        val i = Intent(context, ScrollingHotelActivity::class.java)
//        i.putExtra("ACTIVITY_IMAGE", hotel.photoId)
//        i.putExtra("ACTIVITY_TITLE", hotel.title)
//        i.putExtra("PRICE", hotel.price)
//        i.putExtra("CITY", hotel.city)
//        i.putExtra("ADDRESS", hotel.address)
//        i.putExtra("DESC", hotel.description)
//        i.putExtra("BUSY_DAYS", hotel.daysBusy)
//        i.putExtra("MAX_ADULTS", hotel.maxAdults)
//        i.putExtra("MAX_CHILDREN", hotel.maxChildren)
//        i.putExtra("MAX_INFANTS", hotel.maxInfants)
//        //TODO()
//        i.putExtra("IS_DRY_MACHINE_OK", hotel.dryingMachine)
//        i.putExtra("IS_WASH_MACHINE_OK", hotel.washingMachine)
//        i.putExtra("ALLOW_PET", hotel.pet)
//        i.putExtra("IS_ANY_TV", hotel.tv)
//        i.putExtra("IS_WIFI", hotel.wifi)
//        i.putExtra("IS_CABINET", hotel.workplace)
//        i.putExtra("IS_FOOD", hotel.foodEnabled)
//        i.putExtra("IS_KITCHEN", hotel.kitchen)
//        context.startActivity(i)
//    }

    class PlaceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTitle = itemView.findViewById<TextView>(R.id.text_place_title)
        val textPrice = itemView.findViewById<TextView>(R.id.text_place_price)
        val photoView = itemView.findViewById<ImageView>(R.id.photoView)
        val textTypeCity = itemView.findViewById<TextView>(R.id.text_type_city)
        val textTimeAmount = itemView.findViewById<TextView>(R.id.text_time_amount)
    }
}