package com.example.getaround_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.getaround_test.pojo.Car
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.reusable_content.view.*

class DetailsFragment: Fragment() {

    private lateinit var viewOfLayout: View
    lateinit var car: Car

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        viewOfLayout = inflater.inflate(R.layout.fragment_details, container, false)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        }
        return viewOfLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        car = arguments?.getParcelable<Car>("carBundle") as Car
        val ratingBar: RatingBar = viewOfLayout.ratingBar
        val ownerRatingBar: RatingBar = viewOfLayout.owner_rating

        ownerRatingBar.setIsIndicator(true)
        ratingBar.setIsIndicator(true)

        ownerRatingBar.rating = car.owner.rating.average
        ratingBar.rating = car.rating.average
        Picasso.get()
            .load(car.picture_url)
            .into(viewOfLayout.car_image)
        Picasso.get()
            .load(car.owner.picture_url)
            .into(viewOfLayout.owner_image)

        viewOfLayout.rating_count.text = car.rating.count.toString()
        viewOfLayout.price.text= "$${car.price_per_day}"
        viewOfLayout.text_brand.text = car.brand
        viewOfLayout.text_model.text = car.model

        viewOfLayout.owner_name.text = car.owner.name
    }
}