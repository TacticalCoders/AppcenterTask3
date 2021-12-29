package com.example.appcenter3.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenter3.*

class BeforeFragment : Fragment() {

    private val allList: AllList by lazy{
        ViewModelProvider(this).get(AllList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("BeforeFragment","객체 생성됨")

        val rootView = inflater.inflate(R.layout.fragment_before, container, false)
        val beforeList:RecyclerView = rootView.findViewById(R.id.beforeList)

        beforeList.layoutManager = LinearLayoutManager(rootView.context)
        allList.BeforeItems.add(ItemData("추가 버튼",0,true))
        //val beforeadapter = BeforeAdapter(allList.BeforeItems)
        val beforeadapter = BeforeAdapter(allList.BeforeItems)
        beforeList.adapter = beforeadapter

        return rootView
    }


    inner class BeforeAdapter(val list: MutableList<ItemData>) : RecyclerView.Adapter<MyViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_item,parent,false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val Item = list[position]
            holder.bind(Item)
            holder.itemView.setOnClickListener {
                if(Item.isLastItme){
                    val newItemData = ItemData("추가된 항목",0,false)
                    list.add(list.indexOf(Item),newItemData)
                }
                else{
                    list.removeAt(position)
                    allList.addToIng(Item)
                    Log.d("시작 전 ","아이템 클릭")
                    Log.d("allList.IngItems에","state1로 변경된 아이템 추가됨")
                }
                notifyDataSetChanged()
            }
        }
        override fun getItemCount(): Int = list.size
    }

}