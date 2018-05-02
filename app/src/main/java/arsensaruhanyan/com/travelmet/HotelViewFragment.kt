package arsensaruhanyan.com.travelmet

import android.annotation.SuppressLint
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
 * [HotelViewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HotelViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelViewFragment : Fragment() {
    private var city: String? = null

    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            city = arguments.getString(CITY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        context_this = context
        if (city != null) {

        }

        val view = inflater!!.inflate(R.layout.fragment_hotel_view, container, false)

        val rv = view?.findViewById<RecyclerView>(R.id.hotelsView)
        rv?.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val gson = GsonBuilder().setPrettyPrinting().create()
        val inputStream : InputStreamReader = context!!.resources.openRawResource(R.raw.hotels).reader()
        val hotelList : ArrayList<Hotel> = gson.fromJson(inputStream, object : TypeToken<List<Hotel>>() {}.type)

        val adapter = ViewAdapter(context!!, hotelList)
        rv?.isNestedScrollingEnabled = false
        rv?.adapter = adapter

        return view
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private val CITY = "CITY"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HotelViewFragment.
         */
        fun newInstance(city: String?): HotelViewFragment {
            val fragment = HotelViewFragment()
            val args = Bundle()
            args.putString(CITY, city)
            fragment.arguments = args
            return fragment
        }

        @SuppressLint("StaticFieldLeak")
        lateinit var context_this: Context
    }
}
