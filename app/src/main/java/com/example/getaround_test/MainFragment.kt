package com.example.getaround_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getaround_test.adapters.CarsAdapter
import com.example.getaround_test.pojo.Car
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(), CarsAdapter.Listener {


    private var adapter: CarsAdapter? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var carsArrayList = arrayListOf<Car>()
    private val BASE_URL = "https://raw.githubusercontent.com/"

    private lateinit var viewOfLayout: View

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        }
        viewOfLayout = inflater.inflate(R.layout.fragment_main, container, false)
        return viewOfLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable = CompositeDisposable()
        initRecyclerView()
    }


    private fun initRecyclerView() {
        //Use a layout manager to position your items to look like a standard ListView
        val layoutManager  = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL
        viewOfLayout.cars_list.layoutManager = layoutManager

        adapter = CarsAdapter(this)
        viewOfLayout.cars_list.adapter = adapter

        loadData()
    }

    private fun loadData() {
        //Define the Retrofit request
        val requestInterface = Retrofit.Builder()
            //Set the API’s base URL
            .baseUrl(BASE_URL)
            //Specify the converter factory to use for serialization and deserialization
            .addConverterFactory(GsonConverterFactory.create())

            //Add a call adapter factory to support RxJava return types
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //Build the Retrofit instance
            .build().create(GetData::class.java)

        //Add all RxJava disposables to a CompositeDisposable
        compositeDisposable?.add(requestInterface.getData()
            //Send the Observable’s notifications to the main UI thread
            .observeOn(AndroidSchedulers.mainThread())
            //Subscribe to the Observer away from the main UI thread
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))

    }

    private fun handleResponse(carsList: List<Car>) {
        adapter?.setData(ArrayList(carsList))
    }

    override fun onItemClick(car: Car) {
        //If the user clicks on an item, then take them to the details view
        viewOfLayout.findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundleOf("carBundle" to car))
    }

    override fun onDestroy() {
        super.onDestroy()
        //Clear all your disposables
        compositeDisposable?.clear()

    }
}
