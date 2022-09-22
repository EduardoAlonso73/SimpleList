package com.cursosandroidant.list.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursosandroidant.list.ItemAdapter
import com.cursosandroidant.list.Model.ItemEntity
import com.cursosandroidant.list.ViewModel.MainViewModel
import com.cursosandroidant.list.databinding.ActivityMainBinding
import com.cursosandroidant.list.utils.OnClickListener
import kotlin.random.Random


class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var  mAdapter: ItemAdapter
    private lateinit var  mainViewModel: MainViewModel
    private  var optAction =false
    private  lateinit var  entity: ItemEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //function
        setupViewModel()
        setupRecyclerView()
        setupBottom()

    }
    private fun setupViewModel(){
        //Here prepared our model for that it are observing the modification
        mainViewModel= ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.items.observe(this) { items ->
            mAdapter.setListene(items)

        }
        mainViewModel.errorMsg.observe(this){
            Toast.makeText(this,"Error ", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.getChangeTextInpText().observe(this){text->
            binding.btnSave.text = text
            optAction=true
        }


    }


    private fun setupBottom(){

        binding.btnSave.setOnClickListener {
            when(optAction){
                false->{
                    val itemEntity= ItemEntity(Random.nextLong(), binding.etText.text.toString())
                    mainViewModel.addItem(itemEntity)
                }
                true->{
                    entity.text=binding.etText.text.toString().trim()
                    mainViewModel.updateItem(entity)
                    mainViewModel.setChangeTextInpText("ADD")
                    optAction=false

                }
            }
            binding.etText.setText("")
        }
    }
    private  fun setupRecyclerView(){
        mAdapter= ItemAdapter(mutableListOf(),this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=mAdapter
        }

    }

    override fun onClick(itemEntity: ItemEntity) {
        mainViewModel.setChangeTextInpText("Edit")
        val value=itemEntity.text
        binding.etText.setText(value)
        entity=itemEntity
    }

    override fun onLongClick(itemEntity: ItemEntity) {
        mainViewModel.deleteItem(itemEntity)
    }
}