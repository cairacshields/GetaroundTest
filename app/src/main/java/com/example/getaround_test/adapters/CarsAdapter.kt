package com.example.getaround_test.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import com.example.getaround_test.R
import com.example.getaround_test.pojo.Car
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.reusable_content.view.*


class CarsAdapter (private val listener : Listener) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    private var carsList: ArrayList<Car> = arrayListOf()

    interface Listener {
        fun onItemClick(car : Car)
    }

    fun setData(newList: ArrayList<Car>) {
        carsList.clear()
        carsList.addAll(newList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int{
        try {
            return carsList.size
        } catch (ex:  NullPointerException) {
            return 0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(carsList[position], listener, position)
    }

    //Create a ViewHolder class for the RecyclerView items
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

      fun bind(car: Car, listener: Listener,  position: Int) {

        //Listen for user input events
        itemView.setOnClickListener{ listener.onItemClick(car) }
        val ratingBar: RatingBar = itemView.ratingBar
        ratingBar.setIsIndicator(true)

        ratingBar.rating = car.rating.average
        Picasso.get()
            .load(car.picture_url)
            .into(itemView.car_image)
        itemView.rating_count.text = car.rating.count.toString()
        itemView.price.text= "$${car.price_per_day}"
        itemView.text_brand.text = car.brand
        itemView.text_model.text = car.model
      }

    }

}