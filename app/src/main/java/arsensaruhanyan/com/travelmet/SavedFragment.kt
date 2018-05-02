package arsensaruhanyan.com.travelmet

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SavedFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SavedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_saved, container, false)

        val gson = GsonBuilder().setPrettyPrinting().create()
        val inputStream : InputStreamReader = context!!.resources.openRawResource(R.raw.places).reader()
        val placeSavedList : ArrayList<Place> = gson.fromJson(inputStream, object : TypeToken<List<Place>>() {}.type)
        val rvPlaces = view?.findViewById<RecyclerView>(R.id.places_saved_view)
        rvPlaces?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rvPlaces?.isNestedScrollingEnabled = false
        var tp = 1
        placeSavedList.forEach { t -> tp = t.type }
        val placesAdapter = PlaceViewAdapter(context!!, tp, placeSavedList)
        rvPlaces?.adapter = placesAdapter

        val rvHotels = view?.findViewById<RecyclerView>(R.id.hotelsView)
        rvHotels?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

        val inputStreamH : InputStreamReader = context!!.resources.openRawResource(R.raw.hotels).reader()
        val hotelList : ArrayList<Hotel> = gson.fromJson(inputStreamH, object : TypeToken<List<Hotel>>() {}.type)

        val adapter = ViewAdapter(context!!, hotelList)
        rvHotels?.isNestedScrollingEnabled = false
        rvHotels?.adapter = adapter

        return view
    }

    companion object {
        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
    }
}
