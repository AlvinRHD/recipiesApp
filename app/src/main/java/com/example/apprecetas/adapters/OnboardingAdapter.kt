package com.example.apprecetas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecetas.R
import com.example.apprecetas.models.OnboardingItem

class OnboardingAdapter (private val onboardingItems: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val item = onboardingItems[position]
        holder.bind(item)
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_i)
    }

    override fun getItemCount(): Int = onboardingItems.size

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageOnboarding)
        private val titleView: TextView = itemView.findViewById(R.id.textTitle)
        private val descriptionView: TextView = itemView.findViewById(R.id.textDescription)

        fun bind(item: OnboardingItem) {
            imageView.setImageResource(item.image)
            titleView.text = item.title
            descriptionView.text = item.description
        }
    }
}