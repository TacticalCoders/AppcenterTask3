package com.example.appcenter3.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenter3.*
import com.google.android.material.tabs.TabLayout

class DoneFragment : Fragment() {

    private val sharedViewModel:AllList by activityViewModels()

    lateinit var doneadapter: DoneFragment.DoneAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_done, container, false)
        val doneList:RecyclerView  = rootView.findViewById(R.id.doneList)
        doneList.layoutManager = LinearLayoutManager(rootView.context)

        doneadapter = DoneAdapter(sharedViewModel.DoneItems)
        doneList.adapter = doneadapter

        val viewTab : TabLayout = (activity as MainActivity).findViewById(R.id.viewTab)
        doneList.setOnScrollListener(ScrollListener(viewTab))

        return rootView
    }

    override fun onResume() {
        super.onResume()
        Log.d("IngFragment","onResume()실행됨")
        Log.d("AllList.IngItems의 크기","${sharedViewModel.IngItems.size}")
        doneadapter.notifyDataSetChanged()
        Log.d("IngFragment에서 ","notifyDataSetChanged()실행됨")
    }

    inner class DoneAdapter(var list: MutableList<ItemData>) : RecyclerView.Adapter<MyViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_item,parent,false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val Item = list[position]
            holder.bind(Item)
            holder.itemView.setOnClickListener {
                list.removeAt(position)
                Log.d("sharedViewModel.DoneItems의 개수","${sharedViewModel.DoneItems.size}")
                notifyDataSetChanged()
            }
        }

        override fun getItemCount(): Int = list.size
    }
}