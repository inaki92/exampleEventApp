package com.example.todoappcat23.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappcat23.databinding.EventItemBinding
import com.example.todoappcat23.model.MyEvent

/**
 * This is the adapter class to work with the recycler view
 *
 * 1. Extend the [RecyclerView.Adapter] class in your custom adapter
 * 2. Create View Holder class and extend [RecyclerView.ViewHolder]
 * 3. Create your layout item view XML to be inflated (you can use view binding)
 * 4. Override the onCreateViewHolder method and inflate your view holder
 * 5. Create you data set (any list of items that you need usually objects)
 * 6. Get the items count meaning the size of your data set
 * 7. Bind the data to the view using the onBindViewHolder method
 * 8. Under your View Holder class create a bind method to accept the data to be bound to the view
 * 9. Create a helper function to set new data to your data set, this is in order to avoid the creation of many instances
 */
class EventAdapter(
    // this is handling the click with interface
    private val onEventClickHandler: OnEventClickHandler,
    private val eventDataSet: MutableList<MyEvent> = mutableListOf(),
    // this is handling the click with high order function
    private val onClickEventHandlerHighOrderFunction: (MyEvent) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    /**
     * This is a helper method to update the data set with new data and avoid
     * having many instances of the adapter
     */
    fun updateEvent(newEvent: MyEvent) {
        // TODO we need to sort the data set with the date
        eventDataSet.add(newEvent).also {
            if (it) {
                eventDataSet.sortBy { data -> data.date }
            }
        }

        // this will notify the recycler view a new was inserted and needs to recreate the view
        notifyItemInserted(eventDataSet.indexOf(newEvent))
    }

    /**
     * This guy will create and inflate the views that are going to be display in the recycler view
     *
     * 1. Create a layout XML file for the item View
     * 2. Use view binding to inflate the XML file
     * 3. Create a [LayoutInflater.from] using the parent context from the @parameters
     *
     * here you can use view binding
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            EventItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    /**
     * Allows you to bind the data to the view
     *
     * @param holder - It is your view holder class defined above in the create view holder
     * @param position - It is the position int he data set to get the Object or data
     */
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(eventDataSet[position], onEventClickHandler, onClickEventHandlerHighOrderFunction)

    /**
     * This is the size of your data set
     */
    override fun getItemCount(): Int = eventDataSet.size
}

/**
 * This is the view holder that will allow you to inflate the view
 * and bind the data to that specific view.
 *
 * 1. You need to extend the [RecyclerView.ViewHolder] and pass the view that wil be inflated
 *
 * @param binding - This is the view that will be inflated. If using view binding you need to pass
 * the [EventItemBinding] (binding class created by view binding)
 */
class EventViewHolder(
    private val binding: EventItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * This function will accept an item form your data set in order to set data to the view
     */
    fun bind(
        event: MyEvent,
        onEventClickHandler: OnEventClickHandler,
        highOrderFunctionClickHandler: (MyEvent) -> Unit
    ) {
        binding.eventName.text = event.name
        binding.eventCategory.text = event.category
        binding.eventDate.text = event.date

        binding.root.setOnClickListener {
            // handling the click with an interface approach
            onEventClickHandler.onEventClicked(event)

            // handling the click with the high order function in KOTLIN
            highOrderFunctionClickHandler(event)

            // every time you click the card, you will be performing an action
            // in this you move to the details fragment
            // TODO navigate to the Details fragment
        }
    }
}

interface OnEventClickHandler {
    fun onEventClicked(event: MyEvent)
}