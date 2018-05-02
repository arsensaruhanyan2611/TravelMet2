package arsensaruhanyan.com.travelmet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri
import android.support.v4.app.Fragment
import android.widget.Button
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Places
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), HotelViewFragment.OnFragmentInteractionListener, PlacesFragment.OnFragmentInteractionListener, AHBottomNavigation.OnTabSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val PLACE_AUTOCOMPLETE_REQUEST_CODE = 0

    private var city: String? = null

    private val auth = FirebaseAuth.getInstance()

    @SuppressLint("ResourceAsColor", "ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val m = GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)

        m.build()

        println(auth.currentUser)

        val isFirstRun = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
                .getBoolean("isFirstRun", true)
        if (isFirstRun || FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            Toast.makeText(this@MainActivity, "Run only once", Toast.LENGTH_LONG)
                    .show()
        }
        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit()

        val hotels = AHBottomNavigationItem(getString(R.string.title_hotels),
                R.drawable.ic_hotel)
        val places = AHBottomNavigationItem(getString(R.string.title_places),
                R.drawable.ic_place)
        val saved = AHBottomNavigationItem(getString(R.string.title_liked),
                R.drawable.ic_liked)
        val inbox = AHBottomNavigationItem(getString(R.string.title_inbox),
                R.drawable.ic_inbox)
        val account = AHBottomNavigationItem(getString(R.string.title_account),
                R.drawable.ic_account)

        bottom_navigation.addItem(hotels)
        bottom_navigation.addItem(places)
        bottom_navigation.addItem(saved)
        bottom_navigation.addItem(inbox)
        bottom_navigation.addItem(account)

        bottom_navigation.defaultBackgroundColor = R.color.colorPrimary
        bottom_navigation.inactiveColor = Color.WHITE
        bottom_navigation.accentColor = R.color.colorAccent
        bottom_navigation.currentItem = 0

        bottom_navigation.setOnTabSelectedListener(this)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        when (position) {
            0 -> setFragment(HotelViewFragment.newInstance(city))
            1 -> setFragment(PlacesFragment.newInstance(city))
            2 -> setFragment(SavedFragment.newInstance())
            3 -> setFragment(InboxFragment.newInstance())
            4 -> setFragment(AccountFragment.newInstance())
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.search) {
//            openPlaceActivity()
            auth.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openPlaceActivity() {
        val i = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build())
                    .build(this)
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Places Activity Result
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = PlaceAutocomplete.getPlace(this, data)
                    Log.i("GOOGLE API", "Place: " + place.name)
                    supportActionBar?.title = place.name.toString()
                    city = place.name.toString()
                }
                PlaceAutocomplete.RESULT_ERROR -> {
                    val status = PlaceAutocomplete.getStatus(this, data)
                    Log.i("GOOGLE API", status.statusMessage)
                }
                Activity.RESULT_CANCELED -> return
            }
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
