package com.example.appcenter3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenter3.*

class DoneFragment : Fragment() {

    private val allList: AllList by lazy{
        ViewModelProvider(this).get(AllList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_done, container, false)
        val doneList:RecyclerView  = rootView.findViewById(R.id.doneList)
        doneList.layoutManager = LinearLayoutManager(rootView.context)

        val doneadapter = DoneAdapter(allList.DoneItems)
        doneList.adapter = doneadapter

        return rootView
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
                notifyDataSetChanged()
            }
        }

        override fun getItemCount(): Int = list.size
    }
}