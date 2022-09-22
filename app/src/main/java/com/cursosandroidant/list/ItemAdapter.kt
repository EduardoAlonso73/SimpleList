package com.cursosandroidant.list
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursosandroidant.list.Model.ItemEntity
import com.cursosandroidant.list.databinding.ItemListBinding
import com.cursosandroidant.list.utils.OnClickListener

class ItemAdapter(private var mItems:MutableList<ItemEntity>,private val listener: OnClickListener):RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val itemEntity=mItems[position]
        with(holder as ViewHolder){
            with(binding){
                tvTitle.text=itemEntity.text
                setListener(itemEntity)
            }
        }
    }

    override fun getItemCount(): Int = mItems.size

    fun setListene(items: List<ItemEntity>) {
        //Realizamos un casteo list as MutableList
        this.mItems=items as MutableList<ItemEntity>
        notifyDataSetChanged()
    }



    inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding=ItemListBinding.bind(view)
        fun setListener(itemEntity: ItemEntity){
            binding.root.setOnClickListener { listener.onClick(itemEntity) }
            binding.cbDelete.setOnClickListener{ listener.onLongClick(itemEntity) }
        }
    }

}












