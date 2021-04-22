package com.example.mypa

import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypa.databinding.ItemTodoBinding
//import kotlinx.android.synthetic.main.item_todo.view.*


class TodoAdapter(private var myDB: MyDBHelper, private var todos: MutableList<ToDo> ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTodoBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_todo,parent,false
                )
        )

    }


    private fun toggleStrikeThrough(tvTodoTitle: TextView) {
        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
    }

    private fun untoggleStrikeThrough(tvTodoTitle: TextView) {
        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    fun setTasks(list : MutableList<ToDo>){
        todos = list
        notifyDataSetChanged()
    }

    /*   fun deleteTask(){
           var i = 0
           for(item in todos){
               if(item.isChecked == 1) {
                   myDB.deleteTask(item.id)
                   todos.remove(item)
               }
           }
       }*/

    fun deleteTask(lista: MutableList<ToDo>){
        todos=lista
        for(item in todos){
            if(item.isChecked==1){
                myDB.deleteTask(item.id)
                notifyItemRemoved(todos.indexOf(item))
            }
        }
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var item: ToDo = todos[position]
        holder.binding.tvTodoTitle.setText(item.title)
        holder.binding.cbDone.isChecked = item.isChecked == 1
        if (item.isChecked == 1) {
            myDB.updateTask(item.id, 1)
            toggleStrikeThrough(holder.binding.tvTodoTitle)
        } else {
            myDB.updateTask(item.id, 0)
            untoggleStrikeThrough(holder.binding.tvTodoTitle)

        }
        holder.binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                myDB.updateTask(item.id, 1)
                toggleStrikeThrough(holder.binding.tvTodoTitle)
            } else {
                myDB.updateTask(item.id, 0)
                untoggleStrikeThrough(holder.binding.tvTodoTitle)

            }
        }
    }


    override fun getItemCount(): Int {
        return todos.size
    }

}