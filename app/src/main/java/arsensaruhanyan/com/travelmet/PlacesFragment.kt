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
 * [PlacesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlacesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlacesFragment : Fragment() {
    private var city: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            city = arguments.getString(CITY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_places, container, false)

        val gson = GsonBuilder().setPrettyPrinting().create()
        val inputStream : InputStreamReader = context!!.resources.openRawResource(R.raw.places).reader()
        val placeList : ArrayList<Place> = gson.fromJson(inputStream, object : TypeToken<List<Place>>() {}.type)

        //First
        val rv1 = view?.findViewById<RecyclerView>(R.id.places1View)
        rv1?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv1?.isNestedScrollingEnabled = false
        val placeList1 = fil(1, placeList)
        val adapter1 = PlaceViewAdapter(context!!, 1, placeList1)
        rv1?.adapter = adapter1
        //Second
        val rv2 = view?.findViewById<RecyclerView>(R.id.places2View)
        rv2?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv2?.isNestedScrollingEnabled = false
        val placeList2 = fil(2, placeList)
        val adapter2 = PlaceViewAdapter(context!!, 2, placeList2)
        rv2?.adapter = adapter2
        //Third
        val rv3 = view?.findViewById<RecyclerView>(R.id.places3View)
        rv3?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv3?.isNestedScrollingEnabled = false
        val placeList3 = fil(3, placeList)
        val adapter3 = PlaceViewAdapter(context!!, 3, placeList3)
        rv3?.adapter = adapter3
        //Fourth
        val rv4 = view?.findViewById<RecyclerView>(R.id.places4View)
        rv4?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv4?.isNestedScrollingEnabled = false
        val placeList4 = fil(4, placeList)
        val adapter4 = PlaceViewAdapter(context!!, 4, placeList4)
        rv4?.adapter = adapter4
        //Fifth
        val rv5 = view?.findViewById<RecyclerView>(R.id.places5View)
        rv5?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv5?.isNestedScrollingEnabled = false
        val placeList5 = fil(5, placeList)
        val adapter5 = PlaceViewAdapter(context!!, 5, placeList5)
        rv5?.adapter = adapter5
        //Sixth
        val rv6 = view?.findViewById<RecyclerView>(R.id.places6View)
        rv6?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv6?.isNestedScrollingEnabled = false
        val placeList6 = fil(6, placeList)
        val adapter6 = PlaceViewAdapter(context!!, 6, placeList6)
        rv6?.adapter = adapter6
        //Seventh
        val rv7 = view?.findViewById<RecyclerView>(R.id.places7View)
        rv7?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv7?.isNestedScrollingEnabled = false
        val placeList7 = fil(7, placeList)
        val adapter7 = PlaceViewAdapter(context!!, 7, placeList7)
        rv7?.adapter = adapter7
        //Eighth
        val rv8 = view?.findViewById<RecyclerView>(R.id.places8View)
        rv8?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv8?.isNestedScrollingEnabled = false
        val placeList8 = fil(8, placeList)
        val adapter8 = PlaceViewAdapter(context!!, 8, placeList8)
        rv8?.adapter = adapter8
        //Ninth
        val rv9 = view?.findViewById<RecyclerView>(R.id.places9View)
        rv9?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv9?.isNestedScrollingEnabled = false
        val placeList9 = fil(9, placeList)
        val adapter9 = PlaceViewAdapter(context!!, 9, placeList9)
        rv9?.adapter = adapter9
        //Tenth
        val rv10 = view?.findViewById<RecyclerView>(R.id.places10View)
        rv10?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv10?.isNestedScrollingEnabled = false
        val placeList10 = fil(10, placeList)
        val adapter10 = PlaceViewAdapter(context!!, 10, placeList10)
        rv10?.adapter = adapter10
        //Eleventh
        val rv11 = view?.findViewById<RecyclerView>(R.id.places11View)
        rv11?.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        rv11?.isNestedScrollingEnabled = false
        val placeList11 = fil(11, placeList)
        val adapter11 = PlaceViewAdapter(context!!, 11, placeList11)
        rv11?.adapter = adapter11

        return view
    }

    private fun fil(id: Int, list: List<Place>): List<Place> = when (id) {
        1 -> { list.filter { l -> l.type == 1 } }
        2 -> { list.filter { l -> l.type == 2 } }
        3 -> { list.filter { l -> l.type == 3 } }
        4 -> { list.filter { l -> l.type == 4 } }
        5 -> { list.filter { l -> l.type == 5 } }
        6 -> { list.filter { l -> l.type == 6 } }
        7 -> { list.filter { l -> l.type == 7 } }
        8 -> { list.filter { l -> l.type == 8 } }
        9 -> { list.filter { l -> l.type == 9 } }
        10 -> { list.filter { l -> l.type == 10 } }
        11 -> { list.filter { l -> l.type == 11 } }
        else -> { list }
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val CITY = "CITY"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlacesFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(city: String?): PlacesFragment {
            val fragment = PlacesFragment()
            val args = Bundle()
            args.putString(CITY, city)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
