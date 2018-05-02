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

class ViewAdapter(val context: Context, val userList: List<Hotel>): RecyclerView.Adapter<ViewAdapter.ViewHolder>() {
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle?.text = userList[position].title
        holder.textPrice?.text = "$" + userList[position].price
        val url = userList[position].photoId
        Picasso.with(HotelViewFragment.context_this).load(url).placeholder(R.drawable.image).centerCrop().fit().into(holder.photoView)
//        holder.photoView?.setOnClickListener { startHotelDetailActivity(userList[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_adapter, parent, false)
        return ViewHolder(v)
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

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTitle = itemView.findViewById<TextView>(R.id.text_title)
        val textPrice = itemView.findViewById<TextView>(R.id.text_price)
        val photoView = itemView.findViewById<ImageView>(R.id.photoView)
    }
}