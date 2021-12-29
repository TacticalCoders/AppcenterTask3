package com.example.appcenter3

import android.app.Activity
import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenter3.fragment.BeforeFragment
import com.example.appcenter3.fragment.DoneFragment
import com.example.appcenter3.fragment.IngFragment

class MyAdapter(var list: MutableList<ItemData>,var allList: MutableList<ItemData>) : RecyclerView.Adapter<MyViewHolder>(){

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

            }
            else{
                when(Item.state){
                    0->{  //시작 전인 경우 -> 진행중으로 이동, 이미지 변화 없음.
                        list.removeAt(position)
                        allList.remove(Item)
                        Item.state = 1
                        allList.add(Item)
                    }
                    1->{  //진행 중인 경우
                        list.removeAt(position)
                        allList.remove(Item)
                        Item.state = 2
                        allList.add(Item)
                    }
                    2->{   //완료인 경우
                        list.removeAt(position)
                        allList.remove(Item)
                    }
                }
            }
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = list.size
}